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
import vo.BoardVO;

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
		String action = request.getParameter("action"); 
		String search = request.getParameter("search"); // 검색 기능에 필요한 변수
		String word = request.getParameter("word");  // 검색 기능에 필요한 변수
		String page = request.getParameter("page"); // 페이징 기능에 필요한 변수
		String readUpdate = request.getParameter("readUpdate"); // 되돌리기에 필요한 변수
		
		BoardDAO dao = new BoardDAO();
		BoardVO vo = new BoardVO();
		boolean result=false;	
		HttpSession session = request.getSession();
		
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
	

//		List<BoardVO> dataList = dao.selectAll();
//		request.setAttribute("data", dataList);
//		RequestDispatcher rd = request.getRequestDispatcher(target);
//		rd.forward(request, response);	
		
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

		
	


