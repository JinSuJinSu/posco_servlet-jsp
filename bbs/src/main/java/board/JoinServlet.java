package board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/join")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("user_id");
		String pw = request.getParameter("user_pw");
		String name = request.getParameter("user_name");
		String phone = request.getParameter("user_phone");
		String email = request.getParameter("user_email");
//		String memo = request.getParameter("gmemo");
		boolean result = false;
		
		UserDAO user_dao = new UserDAO();
		UserVO user_vo = new UserVO();
		
//		user_vo.setName(id);
//		user_vo.setMemo(memo);
		user_vo.setUserID(id);
		user_vo.setPassword(pw);
		user_vo.setName(name);
		user_vo.setPhone(phone);
		user_vo.setEmail(email);
//		
		user_dao.insert(user_vo);
		
//		if(id!=null) {
//			result = user_dao.c(user_vo);
//		}
		
		
	}

}
