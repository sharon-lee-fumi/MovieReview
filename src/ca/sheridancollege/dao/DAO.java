package ca.sheridancollege.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ca.sheridancollege.beans.Movie;
import ca.sheridancollege.beans.Review;
import lombok.Data;

//@Data
public class DAO {
	
	SessionFactory sessionFactory = new Configuration()
			.configure("ca/sheridancollege/config/hibernate.cfg.xml")
			.buildSessionFactory();
	
	public void populateMovies() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		if (queryMovie().size() != 6)
		{
			Movie m1 = new Movie("Her", "Spike Jonze", "Drama", 8);
			Movie m2 = new Movie("The Croods", "Kirk DeMicco", "Animation", 7);
			Movie m3 = new Movie("Furious 7", "James Wan", "Action", 7);
			Movie m4 = new Movie("Avengers: Infinity War", "Anthony Russo", "Action", 8);
			Movie m5 = new Movie("Wonder", "Stephen Chbosky", "Drama", 8);
			Movie m6 = new Movie("Gifted", "Marc Webb", "Drama", 6);
			
			Review r1 = new Review("Amazing!");
			Review r2 = new Review("Funny!");
			Review r3 = new Review("Cool!");
			Review r4 = new Review("Great!");
			Review r5 = new Review("Awesome!");
			Review r6 = new Review("Wonderful!");

			m1.getReviewList().add(r1);
			m2.getReviewList().add(r2);
			m3.getReviewList().add(r3);
			m4.getReviewList().add(r4);
			m5.getReviewList().add(r5);
			m6.getReviewList().add(r6);
			
			session.saveOrUpdate(m1);
			session.saveOrUpdate(m2);
			session.saveOrUpdate(m3);
			session.saveOrUpdate(m4);
			session.saveOrUpdate(m5);
			session.saveOrUpdate(m6);
		}
		
		session.getTransaction().commit();
		session.close();
	}
	
	public void updateMovie(Movie movie) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.saveOrUpdate(movie);
			
		session.getTransaction().commit();
		session.close();
	}
	
	public List<Movie> queryMovie() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.getNamedQuery("Movie");
		
		List<Movie> movieList = query.getResultList();
		
		session.getTransaction().commit();
		session.close();
		
		return movieList;
	}
	
	public List<Movie> queryMoviesByTitle(String title) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.getNamedQuery("Movie.byTitle");
		query.setParameter("title", "%" + title + "%");

		List<Movie> movieList = query.getResultList();

		session.getTransaction().commit();
		session.close();
		
		return movieList;
	}
	
	public List<Movie> queryMovieBySearch(String title, String director, String genre, int starRating) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.getNamedQuery("Movie.bySearch");
		query.setParameter("title", "%" + title + "%");
		query.setParameter("director", "%" + director + "%");
		query.setParameter("genre", "%" + genre + "%");
		query.setParameter("starRating", starRating);

		List<Movie> movieList = query.getResultList();

		session.getTransaction().commit();
		session.close();
		
		return movieList;
	}
	

	public List<Movie> queryMovieBySearchCriteria(String title, String director, String genre, int starRating) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Movie> criteria = criteriaBuilder.createQuery(Movie.class);

		Root<Movie> root = criteria.from(Movie.class);

		criteria.where(
				criteriaBuilder.and(
					criteriaBuilder.like(root.get("title"), "%" + title + "%"), 
					criteriaBuilder.like(root.get("director"), "%" + director + "%"),
					criteriaBuilder.like(root.get("genre"), "%" + genre + "%"),
					criteriaBuilder.greaterThanOrEqualTo(root.get("starRating"), starRating)
					)
				);
		
		criteria.orderBy(criteriaBuilder.desc(root.get("title")));
				
		List<Movie> movieList = session.createQuery(criteria).getResultList();
		session.getTransaction().commit();
		session.close();
		
		return movieList;
	}
}
