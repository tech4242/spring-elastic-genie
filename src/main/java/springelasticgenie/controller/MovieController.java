package springelasticgenie.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springelasticgenie.model.Movie;
import springelasticgenie.repository.MovieRepository;

@Controller
@RequestMapping(path="/movie")
public class MovieController {
    @Autowired

    private MovieRepository movieRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewMovie(@RequestParam String name) {
        Movie movie = new Movie();
        movie.setName(name);
        movieRepository.save(movie);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
}
