<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<title>Content</title>
</head>
<body>
	<h4>
		<%-- <small>즐거운 web(<%= loginUser.getName() %>님의 홈)</small> --%>
		<small>즐거운 web</small>
	</h4>
	<hr>
	<div class="col-sm-10">
		<div id="myCarousel" class="carousel slide" data-ride="carousel">
			<!-- Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
				<li data-target="#myCarousel" data-slide-to="3"></li>
			</ol>
			<!-- Wrapper for slides -->
			<div class="carousel-inner" role="listbox">
				<div class="item active">
					<img src="/springmvc/images/test/leejieun.jpg" alt="Image" width="800" height="320">
					<div class="carousel-caption">
						<h3>아이유</h3>
						<p>대존예</p>
					</div>
				</div>
				<div class="item">
					<img src="/springmvc/images/test/leejieun2.gif" alt="Image" width="800" height="320">
					<div class="carousel-caption">
						<h3>이지은</h3>
					</div>
				</div>
				<div class="item">
					<img src="/springmvc/images/test/jisu.webp" alt="Image" width="800" height="320">
					<div class="carousel-caption">
						<h3>김지수</h3>
					</div>
				</div>
				<div class="item">
					<img src="/springmvc/images/test/karina.jpg" alt="Image" width="800" height="320">
					<div class="carousel-caption">
						<h3>유지민</h3>
					</div>
				</div>
			</div>
			<!-- Left and right controls -->
			<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a> 
			<a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
				<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>
		<h5>
			<span class="label label-success">Lorem</span>
		</h5>
		<p>
			Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 
			Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Excepteur sint occaecat cupidatat
			non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt
			ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo	consequat.
		</p>
		<hr>
		<h4>Leave a Comment: </h4>
		<form role="form">
			<div class="form-group">
				<textarea class="form-control" rows="3" required></textarea>
			</div>
			<button type="submit" class="btn btn-success">Submit</button>
		</form><br>
	</div>
</body>
</html>