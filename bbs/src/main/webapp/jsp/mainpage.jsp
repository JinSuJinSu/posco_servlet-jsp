<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.BoardVO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.lang.Math" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
</style>
</head>
<body>
<script>
function dislpayDiv(number){ // 새로운 글을 작성하는 함수
	let value = (document.getElementById("userinfo")) //사용자에 대한 정보를 받아온다.
	if(number==1){
		if(value.innerText==='사용자 : 비회원'){
			alert("로그인 하셔야 작성이 가능합니다.") // 비회원일 경우 사용 불가능
		}
		else{
			// 비회원일 경우 글 작성 가능, write라는 id를 가진 태그를 보여주고 add라는 id를 가진 태그를 숨긴다.
			document.getElementById("write").style.display='block';
			document.getElementById("add").style.display='none';	
		}

	}
}
function reject(){ // 글 작성 취소
	document.getElementById("write").style.display='none';
	document.getElementById("add").style.display='block';	
	
}
function reload(){ // 새로고침 함수 : 조회수 최신 반영이 안되어 있을 경우 실행
	let value = (document.getElementById("userinfo"))
	if(value.innerText==='사용자 : 비회원'){
		alert("로그인 하셔야 사용 가능합니다.")
	}
	else{
		window.location.reload();
	}
}
</script>

<%
List<String> userList = new ArrayList<String>();
if(session.getAttribute("user") == null){ // 사용자 세션 정보가 없으면 비회원으로 처리
	userList = new ArrayList<String>(Arrays.asList("비회원","B"));
}
else{
	userList = (List<String>)session.getAttribute("user"); // 사용자 세션 정보 얻기
}

%>	
<h3 id="userinfo">사용자 : <%=userList.get(0)%></h3>
<%
if(userList.get(0).equals("비회원")){
%>
<h3><a href="/bbs/jsp/login.jsp">로그인</a></h3>
<h3><a href="/bbs/jsp/join.jsp">회원가입</a></h3>
<%
}
else{
%>
<h3><a href="/bbs/login?logout=yes">로그아웃</a></h3>
<h3><a href="/bbs/jsp/userout.jsp">회원탈퇴</a></h3>

<%
}
%>
<h2><a href="/bbs/jsp/firstpage.jsp">메인 화면으로</a></h2>
<button onClick="reload()" display="block">조회수 최신반영</button>
<table border="1">
<tr><th>글번호</th><th>작성자</th><th>글제목</th><th>조회수</th><th>댓글수</th><th>작성 날짜</th></tr>  

<%
  List<BoardVO> list = (List<BoardVO>)request.getAttribute("data");
  for (BoardVO value : list){// request객체로 받아온 게시판 데이터들을 for문으로 돌리면서 하나씩 받아온다.
  %>
		 <tr><td><%= value.getBoardNO()%></td>
		 <td><%= value.getUserID()%></td>
		 <% 
		 if(userList.get(0).equals("비회원")){ // 로그인이 아직 안되어 있으면 열람은 불가능하다.
		 %> <td><a href="/bbs/jsp/login.jsp"><%= value.getTitle()%></a></td>
		 
		 <%
		 }
		 else{
		 %> <!-- 제목을 클릭하면 상세내용 조회가 가능하다. -->
			<td><a href="/bbs/board?boardNo=<%= value.getBoardNO() %>&read=read">
			<%= value.getTitle()%></a></td>
			
		 <%
		 }		 
		 %>
		 <td><%= value.getReadCount()%></td>
		 <td><%= value.getReplyCount()%></td>
		 <td><%= value.getWriteDate()%></td></tr>	 
<%	
	}
 %>
</table>
<%
   List<BoardVO> totalList = (List<BoardVO>)request.getAttribute("totalpage");
   int totalPage = totalList.size();
   if(totalPage==0){
	   totalPage=list.size();
   }
   else{
	   int endPage = (int)Math.ceil((double)totalPage/10);
	   for(int i=1; i<=endPage; i++){
	%>
		<a href="/bbs/board?page=<%=i %>" style="font-size:20px; text-decoration-line:none;"><%=i %>  </a>
	<%   
	   }  
   }
   %>
<h3>총 글의 개수 : <%= totalPage%></h3>
<form method=get action="/bbs/board"> 
<select name="search">
    <option value="" disabled>검색</option>
    <option value="subtitle">제목</option>
    <option value="subcontent">내용</option>
    <option value="subid">작성자</option>
    <input name = "word" placeholer="글자를 입력하세요">
</select>
</form>

<button id = "add" onclick="dislpayDiv(1);"> 게시판 작성</button>
<div id="write"  style="display:none">
<h1>글을 작성하세요</h1>
<form method="post" action="/bbs/editboard"> <!-- 서블릿으로 request -->
<input type="hidden" name = "insert" value="<%= totalPage%>" required>
아이디 : <input name = "gid" value="<%=userList.get(0)%>" readonly><br>
글 제목 : <input name="gtitle" type="text" required maxlength="20" placeholder="제목은 최대 20자까지 작성 가능합니다."><br>
글의 내용 : <br>
<textarea name="gmemo" rows="10" cols="40" required maxlength="300"
placeholder="텍스트는 최대 300까지 작성 가능합니다."></textarea>
<br>
<input type="submit" value="등록" onclick="alert('글이 추가되었습니다.')">
<input type="reset" value="재작성">
<input type="submit" value="작성취소" onclick="reject();">
</form>
</div>
</body>
</html>