<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="base.MemberVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>회원 정보</h1>
<hr>
<%
	MemberVO member = (MemberVO)request.getAttribute("member");
%>
<ul>
	<li> 회원 이름 : <%=  member.getName() %></li>
	<li> 회원 계정 : <%=  member.getId() %></li>
	<li> 회원 암호 : <%=  member.getPassword() %></li>
	<li> 회원 전화번호 : <%=  member.getPhoneNumber() %></li>
</ul>
</body>
</html>