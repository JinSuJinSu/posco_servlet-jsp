<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.BoardVO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
function dislpayDiv(number){
	if(number==1){
		document.getElementById("write").style.display='block';
		document.getElementById("add").style.display='none';
	}
}
</script>

<%
List<String> userList = new ArrayList<>();
try{
userList = (List<String>)session.getAttribute("user");

%>	
<h3><%=userList.get(0)%></h3>
<h3><a href="/bbs/login?logout=yes">로그아웃</a></h3>
<%	
}
catch(Exception e){
	userList = new ArrayList<String>(Arrays.asList("A","B"));
	%>
	<a href="/bbs/html/userForm.html" style="float:right">로그인</a>	
<%	
}
%>



<table border="1">
	<tr><th>글번호</th><th>아이디</th><th>글제목</th><th>조회수</th><th>작성 날짜</th></tr>  

<%
  List<BoardVO> list = (List<BoardVO>)request.getAttribute("data");

  for (BoardVO value : list){
  %>
		 <tr><td><%= value.getBoardNO()%></td>
		 <td><%= value.getUserID()%></td>
		 <% 
		 if(userList.get(0).equals("A")){ // 로그인이 아직 안되어 있으면 열람은 불가능하다.
		 %> <td><a href="/bbs/html/userForm.html"><%= value.getTitle()%></a></td>
		 
		 <%
		 }
		 else{
		 %>
			<td><a href="/bbs/board?boardNo=<%= value.getBoardNO() %>&action=read">
			<%= value.getTitle()%></a></td>
			
		 <%
		 }
		 
		 %>
		 <td><%= value.getReadCount()%></td>
		 <td><%= value.getWriteDate()%></td></tr>

		 
<%	
	}
 %>
 
</table>
<button id = "add" onclick="dislpayDiv(1);">게시판 작성</button>
<div id="write"  style="display:none">
<h1>글을 작성하세요</h1>
<form method="post" action="/bbs/board">
아이디 : <input name = "gid" value=<%=userList.get(0)%> readonly><br>
글 제목 : <input name="gtitle" type="text" required><br>
글의 내용 : <br>
<textarea name="gmemo" rows="10" cols="40"></textarea>
<br>
<input type="submit" value="등록">
<input type="reset" value="재작성">
</form>
</div>
<form>
<select name="search">
    <option value="" placeholer="검색 조건을 제대로 설정하세요">검색</option>
    <option value="subtitle">제목</option>
    <option value="subcontent">내용</option>
    <option value="subid">아이디</option>
    <input name = "word" placeholer="글자를 입력하세요">
</select>
</form>
</body>
</html>