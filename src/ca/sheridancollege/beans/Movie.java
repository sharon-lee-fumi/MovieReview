package ca.sheridancollege.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQueries({
	@NamedQuery(name="Movie", query="from Movie"),
	@NamedQuery(name="Movie.byTitle", query="from Movie where title like :title"),
	@NamedQuery(name="Movie.bySearch", query="from Movie where title like :title and director like :director and genre like :genre and starRating >= :starRating"),
}) 
public class Movie implements Serializable{
	
	@Id
	private String title;
	private String director;
	private String genre;
	private int starRating;
		
	public Movie(String title) {
		super();
		this.title = title;
	}
	
	public Movie(String title, String director, String genre, int starRating) {
		super();
		this.title = title;
		this.director = director;
		this.genre = genre;
		this.starRating = starRating;
	}
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<Review> reviewList = new ArrayList<Review>();
}
