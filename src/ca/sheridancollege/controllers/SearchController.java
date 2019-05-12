package ca.sheridancollege.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.sheridancollege.beans.Movie;
import ca.sheridancollege.dao.DAO;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("/SearchController")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DAO dao = new DAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("reviewMovie.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("title");
		String director = request.getParameter("director");
		String genre = request.getParameter("genre");
		int starRating = Integer.parseInt(request.getParameter("starRating"));
		String order = request.getParameter("order");
		
		if (order == null){
			List<Movie> movieList = dao.queryMovieBySearch(title, director, genre, starRating);
			request.setAttribute("movieList", movieList);
		}
		else {
			List<Movie> movieList = dao.queryMovieBySearchCriteria(title, director, genre, starRating);
			request.setAttribute("movieList", movieList);
		}
		
		doGet(request, response);
	}

}
