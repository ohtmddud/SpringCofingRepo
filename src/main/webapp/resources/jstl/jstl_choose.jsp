<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL CHOOSE</title>
</head>
<body>
	<h2>c:choose가 if ~else if ~else 의 의미</h2>
	<h3>age 변수 20세 미만: XXX님 청소년 입니다. 20세 이상: XXX님 성인 입니다.</h3>
	<p>if문 else문 → c:when</p>
	<p>else → c:otherwise</p>
	<c:choose>
		<c:when test="${param.age<20 }">
			<h4>${param.name }님은 청소년입니다.</h4>
		</c:when>
		<c:otherwise>
			<h4>${param.name }님은 성인입니다.</h4>
		</c:otherwise>
	</c:choose>


	<c:choose>
		<c:when test='${param.grade==" A" || param.grade=="a"}'>
			<h4>${param.name }님의 등급은 A 입니다.</h4>
		</c:when>
		<c:when test='${param.grade=="B" }'>
			<h4>${param.name }님의 등급은 B 입니다.</h4>
		</c:when>
		<c:when test='${param.grade=="C" }'>
			<h4>${param.name }님의 등급은 C 입니다.</h4>
		</c:when>
		<c:otherwise>
			<h4>기타</h4>
		</c:otherwise>
	</c:choose>


</body>
</html>