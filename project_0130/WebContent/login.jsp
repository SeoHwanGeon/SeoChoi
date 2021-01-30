<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-Type" content="text/html; charset=UTF-8 " >
<meta name="viewport" content="width-device-width", initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<title>jsp 게시판</title>
</head>
<body>
	<nav class = "number navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" 
			data-toggle="collapse" data-target="bs-example-navbar-collaspe-1"
			aria-expanded="false">
			<span class="sr-only">Toggle navigation</span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="main.jsp">로그인 및 게시판</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="main.jsp">메인</a></li>
				<li><a href="login.jsp">로그인</a></li>
				<li><a href="bbs.jsp">게시판</a></li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top:20px;">
				<form method="post" action="loginAction.jsp">
					<h3 style="text-align: center;">로그인 화면</h3>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="ID" name="userID" maxlength="20">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="PASSWORD" name="userPW" maxlength="20">
					</div>
					<input type="submit" class="btn btn-primary form-control" value="Login"> 
				</form>
			</div>
		</div>
	</div>
	
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>