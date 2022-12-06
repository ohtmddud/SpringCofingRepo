<%@page import="emp.EmpDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL BASIC FOREACH</title>
</head>
<body>
<h1>주로 셀렉션에 저장된 데이터에 엑세스</h1>
<p>- jsp에서 하는 일이 컨트롤러에서 공유된 attribute를 꺼내서 출력

	<h2>1. 배열 데이터</h2>
	<% String[] arr = {"jsp", "java", "spring", "servlet"}; %>
	<c:forEach varStatus="mystatus" var="data" items="<%= arr %>" begin="1" end="10" step="2">	
		<h3>${data }, current → ${mystatus.current }, 
			index → ${mystatus.index }, 
			first → ${mystatus.first }, 
			last → ${mystatus.last }, 
			begin → ${mystatus.begin}, 
			end → ${mystatus.end }, 
			step → ${mystatus.step }
		</h3>
	</c:forEach>
	
	<h2>2. ArrayList - String</h2>
	<%
		// ArrayList를 정의하고 attribute로 저장(컨트롤러에서 공유한 데이터라 가정)
		ArrayList<String> list = new ArrayList<String>();
		list.add("하둡");
		list.add("스파크");
		list.add("몽고디비");
		list.add("피그");
		list.add("퍼피");
		request.setAttribute("list", list); // 컨트롤러에서 넘어온 데이터라 가정
	%>
	<c:forEach varStatus="status" var="data" items="${list }">
		<h3>${data }, current →  ${status.current }, index → ${status.index }</h3>
	</c:forEach>
	
	<h2>3. ArrayList - DTO</h2>
	<%
		// ArrayList를 정의하고 attribute로 저장(컨트롤러에서 공유한 데이터라 가정)
		ArrayList<EmpDTO> userlist = new ArrayList<EmpDTO>();
		userlist.add(new EmpDTO("001", "장동건", "", "", "", ""));
		userlist.add(new EmpDTO("001", "아이유", "", "", "", ""));
		userlist.add(new EmpDTO("002", "이지은", "", "", "", ""));
		userlist.add(new EmpDTO("002", "카리나", "", "", "", ""));
		userlist.add(new EmpDTO("002", "김채원", "", "", "", ""));
		request.setAttribute("userlist", userlist); // 컨트롤러에서 넘어온 데이터라 가정
	%>
	<c:forEach var = "data" items="${userlist }">
		<h3>${data }, ${data.name }</h3>
	</c:forEach>
</body>
</html>