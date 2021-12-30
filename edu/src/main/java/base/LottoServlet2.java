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
@WebServlet("/lotto2")     
public class LottoServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();	
		HttpSession session = request.getSession();
		int number = Integer.valueOf(request.getParameter("number"));
		int lottoValue =(int)(Math.random()*6)+1;
		if(session.getAttribute("cnt") == null)
			session.setAttribute("cnt", new int[1]); // 데이터를 저장할 방을 등록
		int[] lottoCount = (int[])session.getAttribute("cnt"); // 객체타입을 하위타입으로 바꿔야 되므로 강제 형변환은 필수다.
		System.out.println("전달된 값 : " + number + ", 추출된 값 : " + lottoValue);
		lottoCount[0] += 1;
		
		if(lottoCount[0]<=3) {
			if(number==lottoValue) {
				RequestDispatcher rd = request.getRequestDispatcher("/htmlexam/success.html");
				rd.forward(request, response);	
				lottoCount[0]=3;
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("/htmlexam/fail.html");
				rd.forward(request, response);
			}
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("/htmlexam/impossible.html");
			rd.forward(request, response);
		}
		
	

		out.close();
	}

}
