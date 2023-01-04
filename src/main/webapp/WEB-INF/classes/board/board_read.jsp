<%@page import="com.multi.erp.board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" session="true"%>

<!DOCTYPE>
<html>
<head>
	<title>Board Read</title>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){ // 자바스크립트가 실행되면
			data="${board.category}" // 서버에서 받아온 board객체의 category 값을 저장
			$("#category").val(data).attr("selected", "selected")
		})
	</script>
</head>
<body>
	<form class="form-horizontal" action="/springmvc/board/read.do?state=UPDATE&board_no=${board.board_no }" method="post" >
		<div class="form-group">
			<div class="col-md-2 text-right">
				<label for="id" class="control-label">번호</label>
			</div>
			<div class="col-md-8">${board.board_no }</div>
		</div>
		<div class="form-group">
			<div class="col-md-2 text-right">
				<label for="id" class="control-label">게시글종류</label>
			</div>
			<div class="col-md-3">
				<select name="category" class="form-control" id="category">
					<option value="경조사">경조사</option>
					<option value="사내소식">사내소식</option>
					<option value="게시판">게시판(익명)</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-2 text-right">
				<label for="id" class="control-label">작성자</label>
			</div>
			<div class="col-md-8">${board.id }</div>
		</div>
		<div class="form-group">
			<div class="col-md-2 text-right">
				<label for="title" class="control-label">제목</label>
			</div>
			<div class="col-md-8">${board.title }</div>
		</div>
		<div class="form-group">
			<div class="col-md-2 text-right">
				<label for="title" class="control-label">작성날짜</label>
			</div>
			<div class="col-md-8">${board.write_date }</div>
		</div>
		<div class="form-group">
			<div class="col-md-2 text-right">
				<label for="text" class="control-label">내용</label>
			</div>
			<div class="col-md-8" style="width: 500px; height: 400px; border: solid 1px;">
				${board.content }
			</div>
		</div>

		<div class="form-group">
			<div class="col-md-10 text-center">
				<input type="submit" class="btn btn-lg btn-primary" value="수정">
					<button type="button" class="btn btn-danger btn-lg"	onclick="location.href='/springmvc/board/list.do'">
						<i class="fa fa-fw fa-close"></i> 목록
					</button>
					<button type="button" class="btn btn-danger btn-lg"	id="deletebtn">
						<i class="fa fa-fw fa-close"></i> 삭제
					</button>				
			</div>
		</div>
	</form>

</body>
</html>