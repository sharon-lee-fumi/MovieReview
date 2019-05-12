package ca.sheridancollege.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.sheridancollege.beans.Movie;
import ca.sheridancollege.beans.Review;
import ca.sheridancollege.dao.DAO;

/**
 * Servlet implementation class MovieReview
 */
@WebServlet("/MovieReview")
public class MovieReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DAO dao = new DAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieReview() {
        super();

        dao.populateMovies();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Movie> movieList = dao.queryMovie();

		request.setAttribute("movieList", movieList);
		request.getRequestDispatcher("searchMovie.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String title = request.getParameter("title");
		String review = request.getParameter("review");
		
		List<Movie> movieList = dao.queryMoviesByTitle(title);
		
		Review r = new Review(review);

		movieList.get(0).getReviewList().add(r);
		dao.updateMovie(movieList.get(0));

		doGet(request, response);
	}

}
