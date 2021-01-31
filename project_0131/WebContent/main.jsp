<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-Type" content="text/html; charset=UTF-8 " >
<meta name="viewport" content="width-device-width", initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<title>jsp 게시판</title>
</head>
<body>
	<% 
	// login한 사람이라면 userid에 해당 아이디가 담김 그렇지 않으면 null값
		String userID = null;
		if(session.getAttribute("userID") != null){
			userID = (String) session.getAttribute("userID");
		}
	%>
	<nav class = "number navbar-default">
		<div class="navbar-header">
			<a class="navbar-brand" href="main.jsp">로그인 및 게시판</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="main.jsp">메인</a></li>
				<%
					if(userID == null){
				%>
					<li><a href="login.jsp">로그인</a></li>
				<% 
					}else{
				%>
					<li><a href="LogoutAction.jsp">로그아웃</a></li>
					<li><a href="bbs.jsp">게시판</a></li>
				<% 
					}
				%>
				
			</ul>
		</div>
	</nav>
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>