<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.BoardVO" %>
<%@ page import="vo.ReplyVO" %>
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
		document.getElementById("replybtn").style.display="none";
		document.getElementById("rejectbtn").style.display="none";
		document.getElementById("btn").style.display="none";
		alert('한번 수정하면 복구할 수 없으니 주의 바랍니다.')
}
}
function reply(){ // 변수 정의
	let div = document.createElement("div")
	let tag = document.getElementById("append")
	let form = document.createElement("form")
	let textarea = document.createElement("textarea")
	let hidden_input = document.createElement("input")
	let input = document.createElement("input")
	let button = document.createElement("input")
	let br = document.createElement("br")
	let user = document.getElementById("user").innerText.slice(6)
	
	// 속성 정의
	div.className = "addedword"
	form.method='post'
	form.action='/bbs/editboard'
	textarea.rows="7"
	textarea.cols = "20"
	textarea.required = true
	textarea.maxlength = "100"
	textarea.name = 'reply'
	hidden_input.type='hidden'
	hidden_input.name = 'boardNo'
	hidden_input.value = document.getElementById("reply").value
	input.name = 'replyer'
	input.value = document.getElementById("user").innerText.slice(6)
	input.style.display='none'
   	button.type='submit'
	button.value='작성완료'
	
	
	// 태그 생성	
	tag.after(div)
	div.prepend(form)
	form.prepend(hidden_input)
	hidden_input.after(input)
	input.after(textarea)
	textarea.after(br)
	br.after(button)
	document.getElementById("reply").style.display="none";
	
	// 버튼 숨기기
	document.getElementById("btn").style.display="none";
	document.getElementById("replybtn").style.display="none";
	// 버튼 보여주기
	document.getElementById("rejectbtn").style.display="block";
}
function reject(){
	// 버튼 보여주기
	document.getElementById("btn").style.display="block";
	document.getElementById("replybtn").style.display="block";
	document.getElementById("rejectbtn").style.display="none";
	
	let array = document.querySelectorAll(".addedword");
	for(let i=0; i<array.length; i++){
		array[i].remove()
	}
	
}


</script>
<%
List<String> userList = (List<String>)session.getAttribute("user");
List<ReplyVO> replyList = (List<ReplyVO>)request.getAttribute("reply");

%>


<h1>게시판 조회</h1>
<hr>
<h2 id="user">작성자 : <%=userList.get(0)%></h2>
<form method="get" action="/bbs/board">
<input id = "reply" name="readUpdate" type="hidden" value="${readvo.boardNO}" >
아이디 : <input id="idcheck1" value="${readvo.userID}" readonly><br>
제목 : <input value="${readvo.title}" readonly> <br>
내용물 : <br>
<textarea rows="10" cols="35" readonly>${readvo.content}</textarea>
<br>
<input type="submit" value="게시판 목록으로">
</form>

<input id="idcheck2" style="display:none" value=<%=userList.get(0)%>>
<div id = "div1" style="display:none">
<form method="get" action="/bbs/board">
<input type="hidden" name="update" value="${readvo.boardNO}">
<input type="submit" value="수정">
</form>
</div>

<div id="div2" style="display:none">
<form method="get" action="/bbs/board">
<input id="replyboard" type="hidden" name="delete" value="${readvo.boardNO}">
<input type="submit" value="삭제" onclick="alert('삭제가 완료되었습니다.')">
</form>
</div>

<button id="btn" onclick="check();">편집하기(유저 본인만 가능)</button>
<button id = "replybtn" onclick="reply();">답변 달기</button>
<button id = "rejectbtn" onclick="reject();" style="display:none">답변 취소</button>
<h2>댓글 목록</h2>

<%
if(replyList.size()>0){
%>
	
	<table border="1">
	<tr><th>글번호</th><th>작성자</th><th>답글</th><th>작성날짜</th><th colspan="2">댓글편집</th></tr>
<%
	for(ReplyVO vo : replyList){
%>
		 <tr>
		 <td><%= vo.getReplyNo() %> </td>
		 <td><%= vo.getReplyer() %> </td>
		 <td><%= vo.getReplyContent() %> </td>
		 <td><%= vo.getReplyDate() %> </td>
		 <td><button onclick="location.href='/bbs/reply?editNo=<%= vo.getReplyNo()%>'" >수정</button></td>
		 <td><button onclick="location.href='/bbs/reply?deleteNo=<%= vo.getReplyNo()%>'">삭제</button></td>
		 </tr>
		
<%	
	}
%>
</table>
<%
}

%>

<div  id = "append" >
</div>
</body>
</html>