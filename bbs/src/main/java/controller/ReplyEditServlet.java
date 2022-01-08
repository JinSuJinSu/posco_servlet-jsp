package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dao.ReplyDAO;
import vo.BoardVO;
import vo.ReplyVO;

/**
 * Servlet implementation class ReplyEditServlet
 */
@WebServlet("/reply")
public class ReplyEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String deleteNo = request.getParameter("deleteNo");
		String editNo = request.getParameter("editNo");
		boolean result = false;
		BoardDAO dao = new BoardDAO(); // 게시판 DAO
		BoardVO vo = new BoardVO(); // 게시판 VO
		ReplyDAO rdao = new ReplyDAO(); // 댓글 DAO
		ReplyVO rvo = new ReplyVO(); // 댓글 VO
		
		if(deleteNo!=null) {
			int boardNo = rdao.getBoard(Integer.valueOf(deleteNo));
			vo = dao.selectOne(boardNo);
			vo.setReplyCount(vo.getReplyCount()-1);
			result = rdao.replyDelete(Integer.valueOf(deleteNo));
			result = dao.replyUpdate(vo);	
			if(result){
				RequestDispatcher rd = request.getRequestDispatcher("/board");
				rd.forward(request, response);		
			}
		}
		
		if(editNo!=null) {
			int replyNo = Integer.valueOf(editNo);
			rvo = rdao.selectOne(replyNo);
			request.setAttribute("updatervo", rvo);
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/replyupdate.jsp");
			rd.forward(request, response);		
			
		}
		
		
		
		
		
		
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	}



