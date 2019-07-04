package springelasticgenie.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import springelasticgenie.model.Movie;
import springelasticgenie.repository.MovieRepository;

import java.util.ArrayList;
import java.util.Map;

@Controller
@RequestMapping(path="/movie")
public class MovieController {
    @Autowired

    private MovieRepository movieRepository;

    @Value("${OMDB_API_KEY}")
    private String omdbKey;

    @PostMapping(path="/add")
    public ResponseEntity<?> addNewMovie(@RequestBody Map<String, ?> movieSearchParams) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");

        String omdbURL = String.format("http://www.omdbapi.com/?apikey=%s&s=%s",omdbKey, movieSearchParams.get("search"));
        RestTemplate omdbTemplate = new RestTemplate();
        Map<String, ArrayList<Map<String, String>>> omdbTemplateResult = omdbTemplate.getForObject(omdbURL, Map.class);

        try {
            ArrayList<Map<String, String>> omdbMovies = omdbTemplateResult.get("Search");
            for (Map<String, String> omdbMovie : omdbMovies) {
                Movie movie = new Movie();
                movie.setTitle(omdbMovie.get("Title"));
                movie.setImdbId(omdbMovie.get("imdbID"));
                movie.setPoster(omdbMovie.get("Poster"));
                movie.setYear(omdbMovie.get("Year"));
                movie.setType(omdbMovie.get("Type"));
                movieRepository.save(movie);
            }
            return new ResponseEntity<>("Success", headers, HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>("Error", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
}
