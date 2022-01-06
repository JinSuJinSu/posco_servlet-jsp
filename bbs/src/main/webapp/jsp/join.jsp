<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form method ="post" action="/bbs/join">
id 입력<input type="text" name="user_id"><br>
password 입력<input type="password" name="user_pw"><br>
이름 <input type="text" name ="user_name"><br>
전화번호 <input type="tel" name="user_phone"><br>
이메일 <input type="email" name="user_email"><br>

<input type="submit" value="회원가입">
<input type="reset" value="재작성">

</form>
</body>
</html>