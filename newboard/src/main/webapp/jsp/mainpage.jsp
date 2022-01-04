<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.BoardDTO" %>
<%@ page import="java.util.List;" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
List<BoardDTO> list = (List<BoardDTO>)request.getAttribute("data");
pageContext.setAttribute("list", list);

for (BoardDTO value : list){	
%>
		 <%= value.getBoardNO()%>
		 <%= value.getUserID()%>
		 <%= value.getTitle()%>
		 <%= value.getContent()%>
		 <%= value.getReadCount()%>
		 <%= value.getWriteDate()%>
		 <br>
		 
<%	
	}
 %>


</body>
</html>