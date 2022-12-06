<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>JSTL IF</title>
</head>
<body>
	<!-- 
		JSTL (자바커스텀 태그 라이브러리)
		 - core: 일반적으로 JSP 에서 많이 사용하는 문법을 태그로 표현(prefix: c)
		 - formatting: 숫자, 날짜, 시간을 포맷팅하는 기능(prefix: fmt)
		 - database: DB 관련(prefix: sql)
		 - xml: xml 문서 처리 할 때(prefix: x)
		 - Function: 문자열 제공(prefix: fn)
		  
		[EL에서 사용되는 연산자]
		 - ==: 같다.
		 - empty 변수(파라미터명, 어트리뷰트명 - 컨트롤러가 공유한 데이터 or 클라이언트에서 넘긴 파라미터)  
		 - !: not
	-->
	<h3>c:if 의 사용 - 자바의 if문과 비슷(true인 경우만 체크)</h3>
	1. 전송된 파라미터의 name이 홍길동이면 "홍길동님 환영합니다."
	
	<c:if test="${param.name=='홍길동'}">
		<h3>${param.name }님 환영합니다.</h3>
	</c:if>
	
</body>
</html>