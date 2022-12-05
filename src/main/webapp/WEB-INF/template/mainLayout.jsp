<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<title>Index</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<!-- <Top 영역> -->
	<tiles:insertAttribute name="top" />
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-2 sidenav">
				<!-- <Menu 영역> -->
				<tiles:insertAttribute name="menu" />
			</div>
			<div class="col-lg-10">
				<!-- <Content 영역> -->
				<tiles:insertAttribute name="content" />
			</div>
		</div>
	</div>
</body>
</html>