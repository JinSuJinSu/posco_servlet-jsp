<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.BoardVO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
function check(){
	let value1 = String(document.getElementById("idcheck1").value);
	let value2 = String(document.getElementById("idcheck2").value);

	if(value1===value2){
		document.getElementById("div1").style.display="block";
		document.getElementById("div2").style.display="block";
		document.getElementById("btn").style.display="none";
		alert('한번 수정하면 복구할 수 없으니 주의 바랍니다.')
}
	
}

</script>
<%
List<String> userList = (List<String>)session.getAttribute("user");
%>
<h1>게시판 조회</h1>
<hr>
<h2><%=userList.get(0)%></h2>
<form method="get" action="/bbs/board">
<input name="readUpdate" type="hidden" value="${readvo.boardNO}" >
아이디 : <input id="idcheck1" value="${readvo.userID}" readonly><br>
제목 : <input value="${readvo.title}" readonly> <br>
내용물 : <br>
<textarea rows="10" cols="35" readonly>${readvo.content}</textarea>
<br>
<input type="submit" value="게시판 목록으로">
</form>

<input id="idcheck2" style="display:none" value=<%=userList.get(0)%>>

<div id="div1" style="display:none">
<form method="get" action="/bbs/board">
<input type="hidden" name="update" value="${readvo.boardNO}">
<input type="submit" value="수정">
</form>
</div>

<div id="div2" style="display:none">
<form method="get" action="/bbs/board">
<input type="hidden" name="delete" value="${readvo.boardNO}">
<input type="submit" value="삭제" onclick="alert('삭제가 완료되었습니다.')">
</form>
</div>

<button id="btn" onclick="check();">편집하기(유저 본인만 가능)</button>


</body>
</html>