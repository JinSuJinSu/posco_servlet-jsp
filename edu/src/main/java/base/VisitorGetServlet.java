package base;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/visitorget")     

public class VisitorGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		LocalDate currentDate = LocalDate.now();
		int year = currentDate.getYear();
		int month = currentDate.getMonthValue();
		int day = currentDate.getDayOfMonth();
//		SimpleDateFormat이나 DateTimeFormatter을 사용해 연월일을 한번에 처리하는 것도 좋은 방법이다.
		
		String name = request.getParameter("name");
		if(name==null || name.length()==0) {
			name="익명";
		}
		String content = request.getParameter("op");
		if(content==null || content.length()==0) {
			content="작성한 내용이 없습니다.";
		}
		
		out.print("<h2> " + name + " 님이 " + year + "년 " + month + "월 " + day + "일에 남긴 글입니다." +  "</h2>");
		out.print("<hr>");
		out.print("내용 : " + content);
		out.print("<br>");
		out.print("<a href='/edu/htmlexam/visitorForm.html'>"
				+ "입력화면으로...</a>");
		out.close();
		
	}

}
