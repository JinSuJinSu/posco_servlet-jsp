<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%! int member_v=0; %>
<%
	int local_v=0;
	member_v+=10;
	local_v+=10;
%>
<!-- 서블릿으로 바뀔 때 service method 안으로 들어간다. -->
<!-- jsp는 get과 post를 나누지 않는다는 것 기억하자 -->
<hr>
<h1>JSP의 멤버 변수와 지역 변수</h1>
<ul>
	<li>멤버 변수 : <%= member_v %></li>
	<li>지역 변수 : <%= local_v %></li>
</ul>
<!-- 표현식은 옆에 세미콜론을 붙이지는 않지만 변수를 선언할 때는 옆에 반드시 붙여야 한다. -->
</body>
</html>