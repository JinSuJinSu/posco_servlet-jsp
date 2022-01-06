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
<ul>
	<li> 회원 이름 : ${member.name}</li>
	<li> 회원 계정 : ${member.id}</li>
	<li> 회원 암호 : ${member.password}</li>
	<li> 회원 전화번호 : ${member.phoneNumber}</li>
</ul>
</body>
</html>