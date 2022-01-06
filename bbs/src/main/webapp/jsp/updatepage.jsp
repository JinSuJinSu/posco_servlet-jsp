<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>게시판 수정</h1>
<hr>
<form method="post" action="/bbs/board">
<input type="hidden" name = "update" value="${updatevo.boardNO}" required>
아이디 : <input name="gid" value="${updatevo.userID}"><br>
제목 : <input name="gtitle" value="${updatevo.title}"><br>
내용물 : <br>
<textarea name="gmemo" rows="10" cols="35">${updatevo.content}</textarea>
<br>
<input type="submit" value="수정완료">
<input type="reset" value="재작성">
</form>
<button onclick="location.href='/bbs/board' ">수정 취소</button>
</body>
</html>