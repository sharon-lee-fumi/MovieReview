<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<title>Search Movie</title>
</head>
<body>
<div class="alert alert-success" role="alert">
	<br /><h3 class="alert-heading">Movie List</h3><hr><br />
	<c:forEach var="item" items="${movieList}">
		<h4 class="alert-heading">Title: <b>${item.title}</b></h4>
		<h4>Director:  ${item.director}</h4>
		<h4>Genre: ${item.genre}</h4>
		<h4>Star Rating:  ${item.starRating}</h4>
		<br />
	</c:forEach>
	<hr>
	<br />
	<h5 class="alert-heading"><b>Search and Filter By</b></h5>
	<form method="post" action="SearchController">
		Title: <input type="text" name="title" /><br /><br />
		Director: <input type="text" name="director" /><br /><br />
		Genre: <input type="text" name="genre" /><br /><br />
		Star Rating >=: 
		<select name="starRating">
		  <option value=5>5</option>
		  <option value=6>6</option>
		  <option value=7>7</option>
		  <option value=8>8</option>
		</select><br /><br />
		Display descending order: <input type="checkbox" name="order" value="desc"/><br /><br />
		<input type="submit" value="Search!" /><br /><br />
	</form>
</div>
</body>
</html>