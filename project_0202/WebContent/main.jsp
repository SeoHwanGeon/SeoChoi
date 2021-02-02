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
					<li><a href="join.jsp">회원가입</a></li>
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
	<div class="container">
		<div class="jombotron">
			<div class="container">
				<h1>Main</h1>
				<p>로그인 및 게시판 기능을 jsp를 이용하여 개발하였습니다. 디자인 템플릿은 부트스트랩을 사용하였습니다.</p>
			</div>
		</div>
	</div>
	
	<div class="container">
		<div id="myCarousel" class="carousel slide" data-ride="carousel">
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class=active></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
			</ol>
			<div class="carousel-inner" role="listbox">
				<div class="item active">
					<img src="image/1.jpg">
				</div>
				<div class="item">
					<img src="image/2.jpg">
				</div>
			</div>
			<a class="left carousel-control" href="#myCarousel" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
			</a>
			<a class="right carousel-control" href="#myCarousel" data-slide="next">
				<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			</a>
		</div>
	</div>
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>

</body>
</html>