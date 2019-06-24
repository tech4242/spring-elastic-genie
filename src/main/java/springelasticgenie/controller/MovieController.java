package springelasticgenie.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import springelasticgenie.model.Movie;
import springelasticgenie.repository.MovieRepository;

@Controller
@RequestMapping(path="/movie")
public class MovieController {
    @Autowired

    private MovieRepository movieRepository;

    @Value("${OMDB_API_KEY}")
    private String omdbKey;

    @PostMapping(path="/add")
    public @ResponseBody String addNewMovie(@RequestParam String movieName) {
        String omdbURL = String.format("http://www.omdbapi.com/?apikey=%s&s=%s",omdbKey, movieName);
        RestTemplate omdbTemplate = new RestTemplate();
        String omdbResult = omdbTemplate.getForObject(omdbURL, String.class);
        System.out.println(omdbResult);

        Movie movie = new Movie();
        movie.setName(movieName);
        movieRepository.save(movie);

        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
}
