<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="bbs.BbsDAO" %>
<%@ page import="bbs.Bbs" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-Type" content="text/html; charset=UTF-8 " >
<meta name="viewport" content="width-device-width", initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<title>jsp 게시판</title>
<style type="text/css">
	a{
		color : #000000;
		text-decoration: none;
	}
	a:hover{
		color: #337ab7
	}
</style>
</head>
<body>
	<% 
	// login한 사람이라면 userid에 해당 아이디가 담김 그렇지 않으면 null값
		String userID = null;
		if(session.getAttribute("userID") != null){
			userID = (String) session.getAttribute("userID");
		}
		int pageNumber = 1;
		// 파라미터로 페이지 넘버가 넘어왔다면
		if(request.getParameter("pageNumber") != null){
			//파라미터 정수형태로
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		}
	%>
	<nav class = "number navbar-default">
		<div class="navbar-header">

			<a class="navbar-brand" href="main.jsp">로그인 및 게시판</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="main.jsp">메인</a></li>
				<%
					if(userID == null){
				%>
					<li><a href="login.jsp">로그인</a></li>
					<li><a href="join.jsp">회원가입</a></li>
				<% 
					}else{
				%>
					<li><a href="LogoutAction.jsp">로그아웃</a></li>
					<li class="active"><a href="bbs.jsp">게시판</a></li>
				<% 
					}
				%>
				
			</ul>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<table class="table" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th style="background-color : #eeeeee; text-align : center;">번호</th>
						<th style="background-color : #eeeeee; text-align : center;">제목</th>
						<th style="background-color : #eeeeee; text-align : center;">작성자</th>
						<th style="background-color : #eeeeee; text-align : center;">작성일</th>
					</tr>
				</thead>
				<tbody>
					<%
						BbsDAO bbsDAO = new BbsDAO();
						ArrayList<Bbs> list = bbsDAO.getList(pageNumber);
						for (int i=0; i < list.size(); i++){
					%>	
					<tr>
						<td><%= list.get(i).getBbsID() %></td>
						<td><a href="view.jsp?bbsID=<%= list.get(i).getBbsID() %>"><%= list.get(i).getBbsTitle().replaceAll(" ", "&nbsp;").replace("<", "&lt;").replace(">", "&gt;").replaceAll("\n", "<br>") %></a></td>
						<td><%= list.get(i).getUserID() %></td>
						<td><%= list.get(i).getBbsDate().substring(0,11) + list.get(i).getBbsDate().substring(11,13)+ ":" + list.get(i).getBbsDate().substring(14,16) %></td>
					</tr>
					<% 
						}
					%>	

				</tbody>
			</table>
			<%
				if(pageNumber != 1){
			%>
				<a href="bbs.jsp?pageNumber=<%=pageNumber -1 %>" class="btn btn-success btn-arrow-left">이전</a> 
			<%
				} else if(bbsDAO.nextPage(pageNumber+1)){
			%>
				<a href="bbs.jsp?pageNumber=<%=pageNumber +1 %>" class="btn btn-success btn-arrow-left">다음</a>
			<%		
				}
			%>
			<a href="write.jsp" class="btn btn-primary pull-right">글쓰기</a>
		</div>
	</div>
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>