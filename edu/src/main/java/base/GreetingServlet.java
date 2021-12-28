package base;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/greeting")
public class GreetingServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter(); // 수행 전에 반드시 UTF-8을 설정해줘야 한다.
		LocalDate currentDate = LocalDate.now();
		
		
//        LocalDate date = LocalDate.now();
//        String a = date.format(DateTimeFormatter.ofPattern("E")); 이것도 매우 좋은 방법
		// 여기선 case 보단 이방법이 좋음
		String week = currentDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.KOREAN);
//		int number = currentDate.getDayOfWeek().getValue();
//		String day = "";
//		switch(number) {
//		case 1:
//			day="월";
//			break;
//		case 2:
//			day="화";
//			break;
//		case 3:
//			day="수";
//			break;
//		case 4:
//			day="목";
//			break;
//		case 5:
//			day="금";
//			break;
//		case 6:
//			day="토";
//			break;
//		case 7:
//			day="일";
//			break;
//		default:
//			break;
//		}
		
		String name = request.getParameter("guestname");
		if(name==null || name.length()==0) {
			name="손님";
		}
		out.print("<h2> " + name + "님! 반가워요.. 오늘은 " +  week + "요일입니다!! </h2>");
	}
	
}


