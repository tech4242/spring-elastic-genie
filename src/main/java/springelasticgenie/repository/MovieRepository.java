package springelasticgenie.repository;

import org.springframework.data.repository.CrudRepository;
import springelasticgenie.model.Movie;

public interface MovieRepository extends CrudRepository<Movie, Integer> {

}