<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="base.ProductVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>선택된 상품 정보는 다음과 같습니다.</h1>
<hr>
<%
ProductVO product = (ProductVO)session.getAttribute("object");
%>
선택된 사과의 개수 : <%= product.getAppleCount() %><br>
선택된 바나나의 개수 : <%= product.getBananaCount() %><br>
선택된 한라봉의 개수 : <%= product.getHalabongCount() %><br>
<hr>
<a href="/edu/htmlexam/product.html">상품 선택화면</a>	

</body>
</html>