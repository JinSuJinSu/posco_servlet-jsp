package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDAO;
import vo.BoardVO;

/**
 * Servlet implementation class BoardEditServlet
 */
@WebServlet("/editboard")
public class BoardEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");	
		String id = request.getParameter("gid");
		String memo = request.getParameter("gmemo");
		String title = request.getParameter("gtitle");
		String update = request.getParameter("update");
		String insertNumber = request.getParameter("insert");
		
		boolean result = false;
		BoardDAO dao = new BoardDAO();
		BoardVO vo = new BoardVO();
		
		vo.setUserID(id);
		vo.setContent(memo);
		vo.setTitle(title);
		
		if(update!=null) {
			int boardNumber = Integer.valueOf(update);
			vo.setBoardNO(boardNumber);
			result = dao.update(vo);
			if(result) {
			List<BoardVO> dataList = dao.selectAll();
			int startNum=1;
			for(BoardVO value : dataList) {
				if(value.getBoardNO()==boardNumber) {
					break;
				}
				else {
					startNum+=1;
				}
			}
			
			int rangeNumber = (int)Math.ceil((double)startNum/10);
			response.sendRedirect("/bbs/board?page=" + String.valueOf(rangeNumber));
			}

		}
		else {
		result = dao.insert(vo);
		if(result) {
			int number = Integer.valueOf(insertNumber);
			int raungeNumber = (int)Math.ceil((double)number/10);
			response.sendRedirect("/bbs/board?page=" + String.valueOf(raungeNumber));

		}

		}
	}
}
