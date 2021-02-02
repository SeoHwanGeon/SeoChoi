<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-Type" content="text/html; charset=UTF-8 " >
<title>jsp 게시판</title>
</head>
<body>
	<%
		session.invalidate();
	// 세션에 저장되어 있는 값 무효화
	%> 
	<script>
		location.href = "main.jsp";
	</script>
</body>
</html>