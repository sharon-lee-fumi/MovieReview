# MovieReview
The application allows customers to search and filter my favorite movies, as well as to write their own reviews on them!

Begin with a servlet called MovieReview that uses a DAO to hard-code 6 movies to a
database with a DAO call from its constructor. Its doGet requests dispatch to a
searchMovie.jsp JSP.

searchMovie.jsp display the movie titles, directors, genre, star rating, etc. Beneath the
list of movies, a search form with text fields allowing clients to keyword search the
various fields like title, director, as well as by star rating. 
Includes filter possibilities inside the form which allow the reviewers to
narrow and/or reorder the list of movies displayed. 

The user will launch the search and filter operations by clicking the form’s submit
button(s), and bounce off a servlet called SearchController. SearchController performs the
required operations with the help of a DAO, POJO, etc., and eventually request dispatches
to a reviewMovie.jsp JSP.

In reviewMovie.jsp, display the filtered list in an appealing fashion. Allow users to read
existing reviews and / or write new reviews. Users are able to post their own
comments, in addition to reading all the previous reviews, for each movie listed. Users
only are able to add one movie review per search, though the option to review are visible.

The reviewMovie.jsp review forms post to the MovieReview servlet. MovieReview
processes the review request in its doPost, uses Hibernate to add/save/update/word of
choice the appropriate POJO(s) in the database, and ultimately request dispatches back to
searchMovie.jsp again.
