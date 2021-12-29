package base;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/basket")     
public class BasketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
	
		String name = request.getParameter("pid");
		out.print("<h1>선택한 상품 : " + name + "</h1>");
		String picture = String.valueOf(Integer.valueOf(name.replace("p", ""))) + ".png";
		out.printf("<img src=\"http://localhost:8080/edu/images/%s\" width=300px height=400px/>",picture);
		// printf를 활용해 String을 넣을 경우 img src를 '로 넣어도 작동이 된다.
		out.print("<br>");
//		out.print("<a href='/edu/htmlexam/productlog.html'>"
//				+ "상품 선택 화면</a>");
		out.print("<hr><a href=" + request.getHeader("referer") + "상품 선택 화면</a>");
		out.close();
	}

}
