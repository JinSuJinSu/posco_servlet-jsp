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

import dao.BoardDAO;
import dao.ReplyDAO;
import vo.BoardVO;
import vo.ReplyVO;

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
		String delete = request.getParameter("delete"); // 삭제 기능에 필요한 변수
		String update = request.getParameter("update"); // 수정 기능에 필요한 변수
		String read = request.getParameter("read");     // 조회 기능에 필요한 변수
		String search = request.getParameter("search"); // 검색 기능에 필요한 변수
		String word = request.getParameter("word");  // 검색 기능에 필요한 변수
		String page = request.getParameter("page"); // 페이징 기능에 필요한 변수
		String readUpdate = request.getParameter("readUpdate"); // 되돌리기에 필요한 변수
		String editNum = request.getParameter("editNum"); // 되돌리기에 필요한 변수
		
		String reply = request.getParameter("reply"); // 응답을 받기 위해 필요한 변수
		String replyNo = request.getParameter("replyNo"); // 응답을 받기 위해 필요한 변수
		String replyDelete = request.getParameter("replyDelete"); // 응답을 받기 위해 필요한 변수
		
		BoardDAO dao = new BoardDAO();
		BoardVO vo = new BoardVO();
		ReplyDAO rdao = new ReplyDAO();
		ReplyVO rvo = new ReplyVO();
		

		boolean result=false;	
		HttpSession session = request.getSession();
		
		
		if(read!=null && read.equals("read")) {
			int boardNo = Integer.valueOf(request.getParameter("boardNo"));
			vo = dao.selectOne(boardNo);
			vo.setReadCount(vo.getReadCount()+1);
			result = dao.readUpdate(vo);
			List<ReplyVO> dataList = rdao.selectReply(boardNo);
			request.setAttribute("readvo", vo);
			request.setAttribute("reply", dataList);
			target = "/jsp/readpage.jsp";	
		}	

		// 데이터 삭제하기
		if(delete!=null && !delete.equals("")) {
			int boardNo = Integer.valueOf(delete);	
			List<BoardVO> dataList = dao.selectAll();
			int startNum=1;
			for(BoardVO value : dataList) {
				if(value.getBoardNO()==boardNo) {
					break;
				}
				else {
					startNum+=1;
				}
			}
			int rangeNumber = (int)Math.ceil((double)startNum/10);
			
			result = dao.delete(boardNo);
			if(result) {
				page = String.valueOf(rangeNumber);
			}
		
		}
		
		// 수정 취소했을 때 최근 페이지로 이동하기
		if(editNum!=null) {
			int boardNo = Integer.valueOf(editNum);	
			List<BoardVO> dataList = dao.selectAll();
			int startNum=1;
			for(BoardVO value : dataList) {
				if(value.getBoardNO()==boardNo) {
					break;
				}
				else {
					startNum+=1;
				}
			}
			int rangeNumber = (int)Math.ceil((double)startNum/10);
			page = String.valueOf(rangeNumber);
			
		
		}
		
		// 업데이트 하기 위해 편집 페이지 이동
		if(update!=null && !update.equals("")) {
			int boardNo = Integer.valueOf(request.getParameter("update"));
			vo = dao.selectOne(boardNo);
			request.setAttribute("updatevo", vo);
			target="/jsp/updatepage.jsp";		
			
		}
		
		// 제목 검색 조건
		if(search!=null && search.equals("subtitle")) {
			List<BoardVO> dataList = dao.search(word, "title");
			request.setAttribute("data", dataList);
			request.setAttribute("totalpage", new ArrayList<BoardVO>());		
			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);		
						
		}
		
		// 콘텐트 검색 조건
		if(search!=null && search.equals("subcontent")) {
			List<BoardVO> dataList = dao.search(word, "content");
			request.setAttribute("data", dataList);
			request.setAttribute("totalpage", new ArrayList<BoardVO>());		
			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);						
		}
		
		// 아이디 검색 조건
		if(search!=null && search.equals("subid")) {
			List<BoardVO> dataList = dao.searchUser(word);
			request.setAttribute("data", dataList);
			request.setAttribute("totalpage", new ArrayList<BoardVO>());		
			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);							
		}
		// 사용자가 최근에 접속한 게시판 목록으로 돌아갈 때
		if(readUpdate!=null) {
			
			int boardNo = Integer.valueOf(readUpdate);	
			List<BoardVO> dataList = dao.selectAll();
			int startNum=1;
			for(BoardVO value : dataList) {
				if(value.getBoardNO()==boardNo) {
					break;
				}
				else {
					startNum+=1;
				}
			}
			int rangeNumber = (int)Math.ceil((double)startNum/10);
			page = String.valueOf(rangeNumber);			
		}
		// 답변 달기 위한 조건
		if(reply!=null && replyNo!=null) {
			// 답변을 달기 시작한 시점으로 돌아가야 한다.
			int boardNo = Integer.valueOf(replyNo);	
			List<BoardVO> dataList = dao.selectAll();
			int startNum=1;
			for(BoardVO value : dataList) {
				if(value.getBoardNO()==boardNo) {
					break;
				}
				else {
					startNum+=1;
				}
			}
			int rangeNumber = (int)Math.ceil((double)startNum/10);
			page = String.valueOf(rangeNumber);	
			
			// 답변을 달았으므로 답변수를 추가해준다.
			vo = dao.selectOne(boardNo);
			vo.setReplyCount(vo.getReplyCount()+1);
			result = dao.replyUpdate(vo);			
				
		}
		
		List<BoardVO> dataList = dao.selectAll();
		request.setAttribute("totalpage", dataList);
	
		int startPage;
		if(page==null) {
			startPage = 1;
			
		}
		else if(page.equals("1")) {
			startPage=1;		
		}
		else {
			startPage = Integer.valueOf(page);
			startPage = ((startPage-1)*10)+1;
		}

		List<BoardVO> limitList = dao.selectPage(startPage-1, 10);
		request.setAttribute("data", limitList);
		RequestDispatcher rd = request.getRequestDispatcher(target);
		rd.forward(request, response);		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		}
	}

		
	


