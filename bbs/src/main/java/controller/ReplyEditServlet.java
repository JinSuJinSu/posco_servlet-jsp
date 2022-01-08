package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
	
	// 페이징을 하기 위해선 현재 처리하고 있는 게시판의 위치를 알아야만한다.
	protected String pageCheck(BoardDAO dao, int boardNumber) {
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
		return String.valueOf(rangeNumber);
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String deleteNo = request.getParameter("deleteNo"); // 댓글 삭제 쿼리 문자열
		String editNo = request.getParameter("editNo"); // 댓글 편집 쿼리 문자열
		
		boolean result = false;
		BoardDAO dao = new BoardDAO(); // 게시판 DAO
		BoardVO vo = new BoardVO(); // 게시판 VO
		ReplyDAO rdao = new ReplyDAO(); // 댓글 DAO
		ReplyVO rvo = new ReplyVO(); // 댓글 VO
		
		if(deleteNo!=null) { // 댓글 삭제 요청을 받았을 때
			int replyNumber = Integer.valueOf(deleteNo);
			int boardNumber = rdao.getBoard(replyNumber); // 댓글이 달린 게시판 번호 조회
			vo = dao.selectOne(boardNumber); // 특정 게시판 번호의 vo 객체 생성
			result = rdao.replyDelete(replyNumber); // 댓글삭제
			List<ReplyVO> replyList = rdao.selectReply(boardNumber); // 나머지 댓글들 조회
			vo.setReplyCount(replyList.size()); // 댓글 수 설정하기
			dao.replyUpdate(vo); // 수정된 댓글 수의 값을 update 해준다.
			if(result){
				String rangeNumber = pageCheck(dao,boardNumber);
				response.sendRedirect("/bbs/board?page=" + rangeNumber);
			}
		}
		
		if(editNo!=null) { // 답변글을 수정하는 jsp 페이지로 이동한다.
			int replyNo = Integer.valueOf(editNo);
			rvo = rdao.selectOne(replyNo);
			request.setAttribute("updatervo", rvo);
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/replyupdate.jsp");
			rd.forward(request, response);					
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("gid"); // 댓글 작성자 아이디
		String memo = request.getParameter("gmemo"); // 댓글의 내용
		String update = request.getParameter("update"); // 댓글의 번호
		boolean result = false;
		BoardDAO dao = new BoardDAO();
		BoardVO vo = new BoardVO();
		ReplyDAO rdao = new ReplyDAO();
		ReplyVO rvo = new ReplyVO();
		
	// 글 수정 요청을 받았을 때
		if(update!=null) {
			int replyNumber = Integer.valueOf(update);
			rvo.setReplyNo(replyNumber);
			rvo.setReplyer(id);
			rvo.setReplyContent(memo);
			result = rdao.replyUpdate(rvo);
			if(result) {
			int boardNumber = rdao.getBoard(replyNumber);
			String rangeNumber = pageCheck(dao,boardNumber);
			response.sendRedirect("/bbs/board?page=" + rangeNumber);
			}
	}
	}
}



