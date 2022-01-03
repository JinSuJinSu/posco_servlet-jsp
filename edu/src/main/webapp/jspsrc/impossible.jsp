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

if(minute+30>=60){
	minute=minute+30-60;
	hour+=1;
}
else{
	minute+=30;
}
if(hour>24){
	hour=0;
}

%>
<h1 style="color:red">더이상 응모가 불가능합니다.
<%= hour %>시 <%= minute %>분 이후에 응모하거나 브라우저를 재기동시키세요</h1>
<img src="/edu/images/impossible.png" alt="impossible" width="500px" height="500px" />
</body>
</html>
<!-- jsp에서도 자바 구현에 필요한 라이브러리들을 얼마든지 import 할 수 있다. -->
<!-- simpledateformat은 자바 날짜 관련 데이터를 출력하기 위해 쓰이는 좋은 놈이다. -->