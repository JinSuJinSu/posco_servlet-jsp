<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
int hour  = java.time.LocalDateTime.now().getHour();
int minute  = java.time.LocalDateTime.now().getMinute();

%>
<h1 style="color:blue"><%= hour %>시 <%= minute %>분에 당첨되셨습니다. 축하합니다.</h1>
<img src="/edu/images/successgun.png" alt="success" width="500px" height="500px"/>
</body>
</html>
<!-- “xx시 xx 분에 당첨~~~ 추카추카” -->