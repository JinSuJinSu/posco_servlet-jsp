<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
	if(request.getParameter("study")!=null){
		if(request.getParameter("study").equals("javascript")){
			 response.sendRedirect("http://www.w3schools.com/js/default.asp");
		}
		 else if(request.getParameter("study").equals("dom")){
			 response.sendRedirect("http://www.w3schools.com/js/js_htmldom.asp");
		 }

		 else if(request.getParameter("study").equals("ajax")){
			 response.sendRedirect("http://www.w3schools.com/xml/ajax_intro.asp");
		 }

		 else if(request.getParameter("study").equals("json")){
			 response.sendRedirect("http://www.w3schools.com/js/js_json_intro.asp");
		 }

		 else if(request.getParameter("study").equals("jsp")){ %>
			   <jsp:forward page="exam6.jsp">
			  	<jsp:param name="dan" value="7" />
			  </jsp:forward>	 
	 <%	}
		 else if(request.getParameter("study").equals("html")){ %>
		   <jsp:forward page="../htmlexam/first.html">
		  	<jsp:param name="dan" value="7" />
		  </jsp:forward>
	<%}
		 else{%>		 
		 <h2>study 쿼리 문자열을 제대로 입력하세요</h2>	
	<%}
	  }	
		
	else{ %>
		<h2>study 라는 이름으로 전달된 쿼리가 존재하지 않습니다.</h2>
<% } %>
		 
		

</body>
</html>