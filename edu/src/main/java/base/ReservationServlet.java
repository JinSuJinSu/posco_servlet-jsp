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
@WebServlet("/reservation")     
public class ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
	
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");	
		String mountain = request.getParameter("mountain");
		String[] item = request.getParameterValues("item");
		String date = request.getParameter("reservation_date");

		if(name==null || name.length()==0) {
			name="익명";
		}
		if(mountain==null || mountain.length()==0) {
			mountain="입력이 제대로 되지 않았습니다.";
		}
		
		out.print("<h2>" + name + "님의 예약 내용" +  "</h2>");
		out.print("<hr>");
		out.print("<ul>");
		out.print("<li> 휴양림 : " + mountain + "</li>");
		
		out.print("<li> 추가 요청 사항 : ");
		if (item == null) {
			out.print("입력된 데이터가 없습니다. 다시 확인하세요");	
		}
		else {
			for (int i=0 ; i<item.length ; ++i) {
				if (i == item.length -1) {
					out.print(item[i]);
					break;
				}
				out.print(item[i] +", ");
			}
		}
		out.print("</li>");
		if(date==null || date.length()==0) {
			out.print("<li> 예약날짜 : 입력이 제대로 되지 않았습니다.</li>");
		}
		else {
			String[] dateArray = date.split("-");
			out.print("<li> 예약날짜 : " + dateArray[0] + "년 " + dateArray[1] + "월 " + dateArray[2] + "일" + "</li>");
		}	
		out.close();
	}

}
