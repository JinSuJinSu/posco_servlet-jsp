package base;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    protected String nullCheck(String value) {
    	if(value==null || value=="") {
    		return "없음";
    	}
    	else {
    		return value;
    	}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String phoneNumber = request.getParameter("phoneNumber");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		name=nullCheck(name);
		phoneNumber=nullCheck(phoneNumber);
		id=nullCheck(id);
		password=nullCheck(password);
		
		MemberVO member = new MemberVO();
		member.setName(name);
		member.setPhoneNumber(phoneNumber);
		member.setId(id);
		member.setPassword(password);
		request.setAttribute("member", member);
		
		RequestDispatcher rd = request.getRequestDispatcher("/jspsrc/memberView.jsp");
		rd.forward(request, response);	
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
