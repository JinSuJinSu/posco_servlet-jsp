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
		alert('한번 수정하면 복구할 수 없으니 주의 바랍니다.');
		// 본인이 쓴 글인지 체크해서 맞으면 수정 가능하도록 숨겨져 있던 버튼을 보여준다.
}
	
}
function reply(){ // 변수 정의(댓글을 작성하기 위해서 필요한 태그들을 만들어준다.)
	let div = document.createElement("div");
	let tag = document.getElementById("append"); // 태그를 추가하기 위해서 시작점이 필요한다.
	let form = document.createElement("form");
	let textarea = document.createElement("textarea");
	let hidden_input = document.createElement("input");
	let input = document.createElement("input");
	let button = document.createElement("input");
	let br = document.createElement("br");
	let user = document.getElementById("user").innerText.slice(6);
	
	// 속성 정의(태그 마다 필요한 속성들을 전부 추가해준다.)
	div.className = "addedword";
	form.method='post';
	form.action='/bbs/editboard';
	textarea.rows="8";
	textarea.cols = "35";
	textarea.required = true;
	textarea.maxlength = "200";
	textarea.name = 'reply';
	hidden_input.type='hidden';
	hidden_input.name = 'boardNo';
	hidden_input.value = document.getElementById("reply").value;
	input.name = 'replyer';
	input.value = document.getElementById("user").innerText.slice(6);
	input.style.display='none';
   	button.type='submit';
	button.value='작성완료';
	button.onclick="alert('댓글 작성이 완료되었습니다.')";
	
	// 태그 생성(after는 바로 아래줄, prepend는 부모 밑으로 추가해준다.)
	tag.after(div);
	div.prepend(form);
	form.prepend(hidden_input);
	hidden_input.after(input);
	input.after(textarea);
	textarea.after(br);
	br.after(button);
	document.getElementById("reply").style.display="none";
	
	// 댓글 작성시 필요없는 버튼 숨기기
	document.getElementById("btn").style.display="none";
	document.getElementById("replybtn").style.display="none";
	// 댓글을 작성하는 버튼을 보여준다.
	document.getElementById("rejectbtn").style.display="block";
}

function reject(){
	// 댓글 작성을 취소하면 form으로 형성된 태그들을 다시 지워준다.
	document.getElementById("btn").style.display="block";
	document.getElementById("replybtn").style.display="block";
	document.getElementById("rejectbtn").style.display="none";
	let reject = document.querySelector(".addedword");
	reject.remove();
	
}

function reply_check1(index){
	let user = String(document.getElementById("idcheck2").value);
	let replyer = String(document.querySelector(".replyer" + String(index)).innerText);
	let replyno = document.querySelector(".replyno" + String(index)).innerText
	if(user===replyer){
		location.href="/bbs/reply?editNo=" + String(replyno)
		// 댓글을 작성한 사람이 로그인한 유저와 같은지 체크하고 맞으면 지정한 url로 이동(편집)
	}
	else{
		alert("댓글 작성자 외에는 편집이 불가능합니다.")
		// 작성자, 로그인 유저가 일치 하지 않을 경우 경고창 메시지
	}
	
}

function reply_check2(index){
	let user = String(document.getElementById("idcheck2").value);
	let replyer = String(document.querySelector(".replyer" + String(index)).innerText);
	let replyno = document.querySelector(".replyno" + String(index)).innerText
	if(user===replyer){
		alert("삭제가 완료되었습니다.")	
		location.href="/bbs/reply?deleteNo=" + String(replyno)
		// 댓글을 작성한 사람이 로그인한 유저와 같은지 체크하고 맞으면 지정한 url로 이동(삭제)
	}
	else{
		alert("댓글 작성자 외에는 편집이 불가능합니다.")
		// 작성자, 로그인 유저가 일치 하지 않을 경우 경고창 메시지
	}
}

</script>
<%
List<String> userList = (List<String>)session.getAttribute("user");
List<ReplyVO> replyList = (List<ReplyVO>)request.getAttribute("reply");
// 댓글을 단 사람들이 저장되어 있는 데이터 리스트
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
<button id = "replybtn" onclick="reply();">댓글 달기</button>
<button id = "rejectbtn" onclick="reject();" style="display:none">작성 취소</button>
<h2>댓글 목록</h2>

<%
if(replyList.size()>0){ // for문으로 답글을 단 사람들 정보를 하나씩 출력
%>

	<table border="1">
	<tr><th>글번호</th><th>작성자</th><th>답글</th><th>작성날짜</th><th colspan="2">댓글편집</th></tr>
<%
	for(int i=0; i<replyList.size(); i++){
%>
		 <tr>
		 <td class="replyno<%=i%>"><%= replyList.get(i).getReplyNo() %> </td>
		 <td class="replyer<%=i%>"><%= replyList.get(i).getReplyer() %> </td>
		 <td><%= replyList.get(i).getReplyContent() %> </td>
		 <td><%= replyList.get(i).getReplyDate() %> </td>	 
		 <td><button onclick="reply_check1(<%=i%>);">수정</button></td>
		 <td><button onclick="reply_check2(<%=i%>);">삭제</button></td>
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