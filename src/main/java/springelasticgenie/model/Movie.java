package springelasticgenie.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//import org.springframework.data.elasticsearch.annotations.Document;
//@Document(indexName = "springelasticgenie", type = "movie")

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String title;
    private Integer year;
    private String imdbId;
    private String type;
    private String poster;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = Integer.parseInt(year);
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}