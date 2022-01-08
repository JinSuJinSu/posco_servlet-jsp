package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import vo.UserVO;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/login")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String logout = request.getParameter("logout");
		HttpSession session = request.getSession();
		List<String> user = (List<String>)session.getAttribute("user");
		if(logout!=null && !logout.equals("")) {
			session.invalidate(); // 로그아웃 시 유저 정보를 가지고 있는 세션을 삭제해준다.
		    out.print("<script>");
		    out.print("alert('로그아웃에 성공하셨습니다.');");
		    out.print("location='/bbs/board';");
		    out.print("</script>");
		}
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		
		List<String> userList = new ArrayList<>();
		userList.add(id);
		userList.add(password);
		UserDAO dao = new UserDAO();
		UserVO vo = dao.selectOne(id);
		if(vo!=null) { // 로그인할 때 아이디와 비밀번호가 일치하는지 체크
			if(userList.get(0).equals(vo.getUserID()) && userList.get(1).equals(vo.getPassword())) {
				if (session.getAttribute("user") == null)
					session.setAttribute("user", userList);
					session.setAttribute("user", userList);
				    out.print("<script>");
				    out.print("alert('로그인에 성공하셨습니다.');");
				    out.print("location='/bbs/board';");
				    out.print("</script>");
			}
			else {
					out.print("<script>");
				    out.print("alert('비밀번호가 일치하지 않습니다.');");
				    out.print("location='jsp/login.jsp';");
				    out.print("</script>");
			}
		}
		
		else {
				   out.print("<script>");
				   out.print("alert('아이디가 존재하지 않습니다.');");
				   out.print("location='jsp/login.jsp';");
				   out.print("</script>");
			}
			}			
		
		}


