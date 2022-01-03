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
<h1 style="color:red"><%= hour %>시 <%= minute %>분에 실패하셨습니다. 다시 도전하세요</h1>
<img src="/edu/images/failgun.png" alt="fail" width="500px" height="500px"/>
<br>
<a href="/edu/htmlexam/lottoForm2.html">로또 응모</a>
</body>
</html>