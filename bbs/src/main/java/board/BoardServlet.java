package board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardServlet
 */
@WebServlet("/board")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		String target = "/jsp/mainpage.jsp";
		String delete = request.getParameter("delete");
		String update = request.getParameter("update");
		String action = request.getParameter("action");
		String search = request.getParameter("search");
		String word = request.getParameter("word");
		BoardDAO dao = new BoardDAO();
		BoardVO vo = new BoardVO();
		boolean result=false;
		
		if(action!=null && action.equals("read")) {
			int boardNo = Integer.valueOf(request.getParameter("boardNo"));
			vo = dao.selectOne(boardNo);
			vo.setReadCount(vo.getReadCount()+1);
			result = dao.readUpdate(vo);
			request.setAttribute("readvo", vo);
			target = "/jsp/readpage.jsp";	
		}
		
		if(action!=null && action.equals("edit")) {
			int boardNo = Integer.valueOf(request.getParameter("boardNo"));
			vo = dao.selectOne(boardNo);
			request.setAttribute("readvo", vo);
			target = "/jsp/readpage.jsp";	
		}
		

		if(delete!=null && !delete.equals("")) {
			int boardNo = Integer.valueOf(delete);
			result = dao.delete(boardNo);
		}
		
		if(update!=null && !update.equals("")) {
			int boardNo = Integer.valueOf(request.getParameter("update"));
			vo = dao.selectOne(boardNo);
			request.setAttribute("updatevo", vo);
			target="/jsp/updatepage.jsp";		
			
		}
		
		if(search!=null && search.equals("subtitle")) {
			List<BoardVO> dataList = dao.search(word, "title");
			request.setAttribute("data", dataList);
			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);	
						
		}
		
		if(search!=null && search.equals("subcontent")) {
			List<BoardVO> dataList = dao.search(word, "content");
			request.setAttribute("data", dataList);
			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);						
		}
	

		List<BoardVO> dataList = dao.selectAll();
		request.setAttribute("data", dataList);
		RequestDispatcher rd = request.getRequestDispatcher(target);
		rd.forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");	
		String id = request.getParameter("gid");
		String memo = request.getParameter("gmemo");
		String title = request.getParameter("gtitle");
		String update = request.getParameter("update");
		
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
			response.sendRedirect("/bbs/jsp/firstpage.jsp");
//			RequestDispatcher rd = request.getRequestDispatcher("/jsp/firstpage.jsp");
//			rd.forward(request, response);	
		}
		else {
		result = dao.insert(vo);
		if(result) {
			response.sendRedirect("/bbs/jsp/firstpage.jsp");
//			RequestDispatcher rd = request.getRequestDispatcher("/jsp/firstpage.jsp");
//			rd.forward(request, response);
		}
		}
		}
	}

		
	


