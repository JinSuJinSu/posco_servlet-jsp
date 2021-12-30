package base;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/lotto1")     
public class LottoServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();	
		int number = Integer.valueOf(request.getParameter("number"));
		int lottoValue =(int)(Math.random()*6)+1;
		System.out.println("전달된 값 : " + number + ", 추출된 값 : " + lottoValue);
		
		if(number==lottoValue) {
			RequestDispatcher rd = request.getRequestDispatcher("/htmlexam/success.html");
			rd.forward(request, response);	
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("/htmlexam/fail.html");
			rd.forward(request, response);
		}

		out.close();
	}

}
