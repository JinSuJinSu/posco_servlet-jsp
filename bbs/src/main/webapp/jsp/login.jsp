<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form name="login" method="post" action="/bbs/login">
            아이디 : <input type="text" name="user_id"/><br />
            패스워드 : <input type="password" name="user_pw" /><br />
            
            <input type="submit" value="로그인"><br>
  </form>

<a href="/bbs/jsp/join.jsp">회원가입</a>

</body>
</html>