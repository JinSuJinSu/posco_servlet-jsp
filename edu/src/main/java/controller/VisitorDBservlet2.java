package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.VisitorDAO;
import vo.VisitorVO;

/**
 * Servlet implementation class VisitorDBservlet2
 */
@WebServlet("/visitordb2")
public class VisitorDBservlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// get 방식 - 방명록 삭제, 전체 리스트 출력, 원하는 글 한개 추출(방명록 수정, 입력폼)
	// post 방식 - 방명록 작성, 수정
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String action = request.getParameter("action");
		VisitorDAO dao = new VisitorDAO();
		String target = "/jspsrc/visitorView.jsp";
		if(action!=null && action.equals("delete")) {
			boolean result = dao.d(Integer.valueOf(id));
			if(result) {
				request.setAttribute("msg", "글이 성공적으로 삭제되었어요");
			}
			else {
				request.setAttribute("msg", "글 삭제를 실패했어요");
			}
		}
		else if(action!=null && action.equals("update")) {
			VisitorVO vo = dao.rOne(Integer.valueOf(id));
			request.setAttribute("updatevo", vo);
			target="/jspsrc/visitorUpdateView.jsp";
		}
		else {
			List<VisitorVO> list = dao.r();
			for(VisitorVO vo : list) {
				System.out.println(vo.getName());
			}
			request.setAttribute("list", list);
		}
		request.getRequestDispatcher(target).forward(request, response);
		
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");	
		String id = request.getParameter("id");
		String name = request.getParameter("gname");
		String memo = request.getParameter("gmemo");
		boolean result = false;
		String word="삽입";
		VisitorDAO dao = new VisitorDAO();
		VisitorVO vo = new VisitorVO();
		vo.setName(name);
		vo.setMemo(memo);
		if(id==null) {
			result=dao.c(vo);
		}
		else {
			vo.setId(Integer.valueOf(id));
			result=dao.u(vo);
			word="수정";
		}
		if(result) {
			request.setAttribute("msg", name + "님의 글이 성공적으로 " + word + "되었어요");
		}
		else {
			request.setAttribute("msg", name + "님의 글이 성공적으로 " + word + "에 실패했어요");
		}
		
		request.getRequestDispatcher("/jspsrc/visitorView.jsp").forward(request, response);
	
	}

}
