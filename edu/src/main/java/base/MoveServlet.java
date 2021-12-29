package base;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/move")
public class MoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String webname = request.getParameter("action");
		if(webname != null) {
			String website="";
			if(webname.equals("naver")){
				website = "http://www.naver.com";
			}
			else if(webname.equals("daum")) {
				website = "http://www.daum.net";
			}
			else if(webname.equals("google")){
				website = "http://www.google.com";
			}
			System.out.println(website);
			response.sendRedirect(website);
		}
		else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<h2>전달된 쿼리 문자열이 없어서 MoveServlet이 직접 응답합니다.</h2>");
			out.close();
		}
		
		System.out.println("GET 방식 수행");
	}


}