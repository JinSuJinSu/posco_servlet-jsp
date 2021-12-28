

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/first")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter(); // 수행 전에 반드시 UTF-8을 설정해줘야 한다.
		out.print("<h1>FirstServlet 수행 완료</h1>");
		out.print("<hr>");
		out.print("<h2>요청 방식 : " + request.getMethod() + "</h2>");
		out.print("<h2>반갑다... " + request.getParameter("name") + "님</h2>");
		System.out.println("서블릿에서의 표준 출력은 어디로 나갈까");
		
	}

}
