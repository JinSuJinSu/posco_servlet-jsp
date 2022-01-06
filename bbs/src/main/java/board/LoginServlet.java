package board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("user_id");
		String pw = request.getParameter("user_pw");
		
		boolean result = false;
		int login_result;
		UserDAO user_dao = new UserDAO();
		UserVO user_vo =  new UserVO();
		
		
//		user_vo.setUser_id(id);
//		user_vo.setUser_pw(pw);
		
		login_result = user_dao.selectOne(id, pw);
		
		if(login_result == 1) {
			request.setAttribute("login_test", id +"로 로그인 되었습니다.");
		}else if(login_result == 0) {
			request.setAttribute("login_test",  "password가 틀렸습니다.");
			}
		else if(login_result ==-1) {
			request.setAttribute("login_test", id + "인 아이디가 존재하지 않습니다.");
		}
		else if(login_result == -2){
			request.setAttribute("login_test", "데이터베이스 오류");
		}
		request.getRequestDispatcher("/jsp/loginView.jsp").forward(request, response);
		
	}

}
