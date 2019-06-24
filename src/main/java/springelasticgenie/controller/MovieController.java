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
    public ResponseEntity<?> addNewMovie(@RequestParam String movieName) {
        String omdbURL = String.format("http://www.omdbapi.com/?apikey=%s&s=%s",omdbKey, movieName);
        RestTemplate omdbTemplate = new RestTemplate();
        Map<String, ArrayList<Map<String, String>>> omdbTemplateResult = omdbTemplate.getForObject(omdbURL, Map.class);

        try {
            ArrayList<Map<String, String>> omdbMovies = omdbTemplateResult.get("Search");
            for(int i = 0; i < omdbMovies.size(); i++) {
                Map<String, String> omdbMovie = omdbMovies.get(i);
                Movie movie = new Movie();
                movie.setTitle(omdbMovie.get("Title"));
                movie.setImdbId(omdbMovie.get("imdbID"));
                movie.setPoster(omdbMovie.get("Poster"));
                movie.setYear(omdbMovie.get("Year"));
                movie.setType(omdbMovie.get("Type"));
                movieRepository.save(movie);
            }
            // TODO add response body
            HttpHeaders headers = new HttpHeaders();
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch(Exception e) {
            System.out.println(e.toString());
            // TODO add response body
            HttpHeaders headers = new HttpHeaders();
            return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
}
