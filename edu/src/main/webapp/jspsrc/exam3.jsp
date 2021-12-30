<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>표현식 태그 점검</h1>
<hr>
<% int num = 10; %>
<ul>
	<li><%= 100 %></li>
	<li><%= num %></li>
	<li><%= ++num %></li>
	<li><%= num*2 %></li>
	<li><%= "가나다".length() %></li>
	<li><%= num%2==0 %></li>
	<li><%= request.getParameter("stname") %></li>
	<li><%= new java.util.Date(session.getCreationTime()) %></li>
	<li><%= application.getServerInfo() %></li>
	<li><%= application.getContextPath() %></li>
</ul>
<!-- l-value = r-value
변수			변수, 상수, 리터럴, 연산식, 리턴값이 있는 함수 호출식 -->
</body>
</html>