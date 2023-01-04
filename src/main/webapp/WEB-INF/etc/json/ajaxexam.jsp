<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Ajax Exam</title>
	<style type="text/css">
		#result {
			color: pink;
		}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#testBtn").on("click", function() {
				var data = {"board_no":$("#board_no").val()}
				$.ajax({
					url:"/erp/ajax/exam01",
					type:"get",
					data: data,
					dataType: "text",
					success:success_run,
					error:error_run
				});
			});
			$("#testJsonBtn").on("click", function() {
				var data = {"board_no":$("#board_no").val()}
				alert(data);
				$.ajax({
					url:"/erp/ajax/exam02/getJsonData",
					type:"get",
					data: data,
					dataType: "json",
					success:function(data){
						$("#no").html(data.board_no);
						$("#title").html(data.title);
						$("#writer").html(data.id);
						$("#content").html(data.content);
					},
					error:error_run
				});
			});
		});
		function success_run(txt) {
			$("#result").html("<h3>" + txt + "</h3>");
		}
		function error_run(obj, msg, statusMsg) {
			alert("오류발생 → " + obj + ", " + msg + ", " + statusMsg);
		}
	</script>
</head>

<body>
	<form action="">
		글번호: <input type="text" name="board_no" id="board_no" />
		<input type="button" value="Ajax Test" id="testBtn" />
		<input type="button" value="Ajax Json Test" id="testJsonBtn" />
	</form>
	<div id="result">
		<h4>글번호: <span id="no"></span></h4>
		<h4>제목: <span id="title"></span></h4>
		<h4>작성자: <span id="writer"></span></h4>
		<h4>내용: <span id="content"></span></h4>
	</div>
</body>

</html>