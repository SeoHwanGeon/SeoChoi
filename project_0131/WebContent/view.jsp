<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="bbs.Bbs" %>
<%@ page import="bbs.BbsDAO" %>
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
		int bbsID = 0;
		if (request.getParameter("bbsID") != null){
			bbsID = Integer.parseInt(request.getParameter("bbsID"));
		}
		if (bbsID == 0){
			// 번호가 존재해야 함
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('유효하지 않은 글입니다..')");
			script.println("location.href = 'bbs.jsp'");
			script.println("</script>");
		}
		// 해당 글의 구체적인 내용 가져올 수 있도록 유효한 글이 전제
		Bbs bbs = new BbsDAO().getBbs(bbsID);
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
						<th colspan="3" style="background-color : #eeeeee; text-align : center;">게시판 글보기</th>
						
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width: 20%;">글 제목</td>
						<td colspan="2"><%= bbs.getBbsTitle().replaceAll(" ", "&nbsp;").replace("<", "&lt;").replace(">", "&gt;").replaceAll("\n", "<br>") %></td>
					</tr>
					<tr>
						<td>작성자</td>
						<td colspan="2"><%= bbs.getUserID() %></td>
					</tr>
					<tr>
						<td>작성일</td>
						<td colspan="2"><%= bbs.getBbsDate().substring(0,11) + bbs.getBbsDate().substring(11,13)+ ":" + bbs.getBbsDate().substring(14,16) %></td>
					</tr>
					<tr>
						<td>내용</td>
						<td colspan="2" style="min-height: 200px; text-align: Left;"><%= bbs.getBbsContent().replaceAll(" ", "&nbsp;").replace("<", "&lt;").replace(">", "&gt;").replaceAll("\n", "<br>") %></td>
					</tr>
				</tbody>
			</table>
			<a href = "bbs.jsp" class="btn btn-primary">목록</a>
			<%
			//작성자가 본인이라면 수정 및 삭제 가능하도록
				if(userID != null && userID.equals(bbs.getUserID())){
			%>	
				<a href="update.jsp?bbsID=<%= bbsID %>" class="btn btn-primary">수정</a>
				<a href="deleteAction.jsp?bbsID=<%= bbsID %>" class="btn btn-primary">삭제</a>
			<%
				}
			%>
		</div>
	</div>
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>