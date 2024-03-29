<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.BoardVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
function dataReset(){ // 댓글을 완전히 새로 쓰고 싶을 때 쓰는 메소드(모든 글을 지워준다.)
	let array = document.querySelectorAll(".data");
	for(let i=0; i<array.length; i++){
		array[i].value = "";
	}
	
}

function deleteimg(){ // 이미지를 지워주고 싶을 때 쓰는 메소드
	let array = document.querySelectorAll("#img");
	for(let i=0; i<array.length; i++){
		array[i].src = "";
	}
	
}
	
	
</script>
<h1>게시판 수정</h1>
<hr>
<form method="post" action="/bbs/editboard" enctype="multipart/form-data">
<input type="hidden" name = "update" value="${updatevo.boardNO}" required>
아이디 : <input name="gid" value="${updatevo.userID}" readonly><br>
제목 : <input class="data" name="gtitle" value="${updatevo.title}" required><br>
내용물 : <br>
<textarea class = "data" name="gmemo" rows="10" cols="35" required maxlength="300">${updatevo.content}</textarea>
<br>
<%
BoardVO vo = (BoardVO)request.getAttribute("updatevo");
if(vo.getFileurl()!=null){
	System.out.println(vo.getFileurl());
%>	
	이미지<br>
	<img id = "img" src="<%=vo.getFileurl()%>" width="200px" height="200px">
<%	
}
%>
<br>
파일 업로드
<input type="file" name="file" >
<input type="submit" value="수정완료" onclick="alert('수정이 완료되었습니다.')">
<input type="reset" value="다시작성">
</form>
<br>
<button onclick="dataReset();">글 전부 지우기</button>
<button onclick="deleteimg();">이미지 지우기</button>
<button onclick="location.href='/bbs/board?editNum=${updatevo.boardNO}'">수정 취소</button>
</body>
</html>