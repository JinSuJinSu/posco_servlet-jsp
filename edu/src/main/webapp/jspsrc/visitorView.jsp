<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.VisitorVO" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	fd{
		border-bottom : 1px dotted green;
	}
	tr:hover{
		background-color: pink;
		font-weight:bold;
	}
	td:nth-child(3){
	width:400px;
	}
</style>
</head>
<body>
<%
ArrayList<VisitorVO> list = (ArrayList<VisitorVO>)request.getAttribute("list");
if(list!=null) {
%>
<h2>방명록 테이블</h2>
<table>
<tr id="trheader">
	<td><strong>작성자</strong></td>
	<td><strong>작성일</strong></td>
	<td><strong>내용</strong></td>
	<td><strong>삭제</strong></td>
	<td><strong>수정</strong></td>
</tr>
<%
	for(VisitorVO vo : list) {
%>	
	<tr>
	<td><%= vo.getName() %></td>
	<td><%= vo.getWriteDate() %></td>
	<td><%= vo.getMemo() %></td>
	<td><a href="/edu/visitordb2?id=<%= vo.getId() %>&action=delete">
		<img src="/edu/images/delete.png" width="30px"></a>
	</td>
		<td><a href="/edu/visitordb2?id=<%= vo.getId() %>&action=update">
		<img src="/edu/images/edit.png" width="30px"></a>
	</td>
	</tr>
			
<%
	}
%>
	</table>
<%   
} else{
%>
<h2>${msg}</h2>
<% 
}
%>
<hr>
<a href="/edu/htmlexam/visitorMain.html">방명록 홈 화면으로 가기</a>
</body>
</html>