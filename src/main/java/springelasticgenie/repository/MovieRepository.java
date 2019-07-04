package springelasticgenie.repository;

import org.springframework.data.repository.CrudRepository;
import springelasticgenie.model.Movie;

//import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
//, ElasticsearchCrudRepository<Movie, Integer> {

public interface MovieRepository extends CrudRepository<Movie, Integer> {

}