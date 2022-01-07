package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import vo.UserVO;



@WebServlet("/join")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			// 쿼리 문자열로 보낸 name,value 받아오기
			String id = request.getParameter("user_id");
			String pw = request.getParameter("user_pw");
			String name = request.getParameter("user_name");
			String phone = request.getParameter("user_phone");
			String email = request.getParameter("user_email");
			String idout = request.getParameter("idout");
			String userout = request.getParameter("userout");
			boolean result = false;
			
			UserDAO dao = new UserDAO();
			UserVO vo = new UserVO();
			
			// 회원 탈퇴를 하기 위한 조건
			if(idout!=null && userout!=null) {
				if(userout.equals("회원탈퇴")) {
					result = dao.userDelete(idout);
					if(result) {
						HttpSession session = request.getSession();
						List<String> user = (List<String>)session.getAttribute("user");
						session.invalidate();
					    out.print("<script>");
					    out.print("alert('회원 탈퇴에 성공하셨습니다.지금까지 이용해주셔서 감사합니다.');");
					    out.print("location='/bbs/board';");
					    out.print("</script>");
						}
				}
				else {
					    out.print("<script>");
					    out.print("alert('탈퇴를 원하시면 회원탈퇴 제대로 입력하세요');");
					    out.print("location='jsp/userout.jsp';");
					    out.print("</script>");
				}
	
			}
				
			else {
				// 유저 중복 체크
				List<UserVO> userList = dao.selectAll();
				boolean isUser = false;
				for(UserVO user : userList) {
					if(user.getUserID().equals(id)) {
						isUser=true;
						break;
					}
				}
				if(isUser) { // 이미 아이디가 있는 경우
				    out.print("<script>");
				    out.print("alert('중복되는 아이디가 있습니다.');");
				    out.print("location='jsp/join.jsp';");
				    out.print("</script>");
				}
				else {
					vo.setUserID(id);
					vo.setPassword(pw);
					vo.setName(name);
					vo.setPhone(phone);
					vo.setEmail(email);
					dao.insert(vo);
						    
				    out.print("<script>");
				    out.print("alert('회원가입에 성공하셨습니다.');");
				    out.print("location='jsp/login.jsp';");
				    out.print("</script>");
				}
				
		}
	}
		
		
	}


