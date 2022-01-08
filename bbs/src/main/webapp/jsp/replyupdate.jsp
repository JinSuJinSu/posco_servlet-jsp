<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
function dataReset(){ // 완전히 글을 새로 쓰고 싶을 경우 모든 글을 완전히 지워준다.
	let array = document.querySelectorAll(".data");
	for(let i=0; i<array.length; i++){
		array[i].value = "";
	}
	
}
</script>
<h1>댓글 수정하기</h1>
<form method="post" action="/bbs/reply">
<input type="hidden" name = "update" value="${updatervo.replyNo}" required>
아이디 : <input name="gid" value="${updatervo.replyer}" readonly><br>
내용물 : <br>
<textarea class = "data" name="gmemo" rows="8" cols="35" required maxlength="300">${updatervo.replyContent}</textarea>
<br>
<input type="submit" value="수정완료" onclick="alert('수정이 완료되었습니다.')">
<input type="reset" value="다시작성">
</form>
<button onclick="dataReset();">글 전부 지우기</button>
</body>
</html>