<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<title>Review Movie</title>
</head>
<body>
<div class="alert alert-success" role="alert">
	<br /><a href="MovieReview">Go back to Movie Review</a><br /><hr><br />
	<c:forEach var="item" items="${movieList}">
		<h4 class="alert-heading">Title: <b>${item.title}</b></h4>
		<h4>Director:  ${item.director}</h4>
		<h4>Genre: ${item.genre}</h4>
		<h4>Star Rating:  ${item.starRating}</h4>
		<br />
		<h4>Reviews:</h4>
		<c:forEach var="review" items="${item.reviewList}">
			<h5>${review.review}</h5>
		</c:forEach>
		
		<form method="post" action="MovieReview">
		<input type="hidden" name="title" value="${item.title}" /><br /><br />
		Your Review: <textarea name="review" cols="60"></textarea><br /><br />
		<input type="submit" value="Submit" /><br /><br />
		</form>
		<hr>
	</c:forEach>
</div>
</body>
</html>