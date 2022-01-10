package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.BoardDAO;
import dao.ReplyDAO;
import vo.BoardVO;
import vo.ReplyVO;

/**
 * Servlet implementation class BoardEditServlet
 */
@MultipartConfig
@WebServlet("/editboard")
public class BoardEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		// 글을 편집하거나 수행 후 쿼리 문자열로 받은 후 코드 실행에 필요한 변수
		String id = request.getParameter("gid");
		String memo = request.getParameter("gmemo");
		String title = request.getParameter("gtitle");
		String update = request.getParameter("update");
		String insertNumber = request.getParameter("insert");
		
		// 댓글을 달았을 때 쿼리 문자열로 받은 후 코드 실행에 필요한 변수
		String reply = request.getParameter("reply");
		String replyer = request.getParameter("replyer");
		String boardNo = request.getParameter("boardNo");
		
		// 게시판 dao,vo와 댓글을 추가한 사람rdao, rvo를 받아온다.
		boolean result = false;
		BoardDAO dao = new BoardDAO();
		BoardVO vo = new BoardVO();
		ReplyDAO rdao = new ReplyDAO();
		ReplyVO rvo = new ReplyVO();

		
		// 글 삽입, 수정 요청을 받았을 때만 수행이 가능
		if(id!=null && memo!=null & title!=null) {		
			vo.setUserID(id);
			vo.setContent(memo);
			vo.setTitle(title);
					
		try {

			String uploadPath = getServletContext().getRealPath("") + File.separator + "/uploadimages";
			File uploadDir = new File(uploadPath);
		    Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
		    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
		    InputStream fileContent = filePart.getInputStream();
			String realPath = "C:/Users/BIT/eclipse-workspace/bbs/src/main/webapp/uploadimages/" +  fileName;
			FileOutputStream fos = new FileOutputStream(realPath);
			String urlPath = "/bbs/uploadimages/" + fileName;
		
			byte[] b = new byte[2048];
			int n;
			while ((n = fileContent.read(b)) > 0) {
				fos.write(b, 0, n);
			}
			vo.setFileurl(urlPath);
			
			}
			catch(Exception e) {
				// 파일 업로드가 안되어 있을 경우 패스한다.
			}
			
		}
		
		// 글 수정 요청을 받았을 때
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
		// 글 삽입 요청을 받았을 때
		if(insertNumber!=null) {
		result = dao.insert(vo);
		if(result) {
			response.sendRedirect("/bbs/board");

			}
		}		
		// 댓글 추가 요청을 받았을 때만 사용이 가능하다.
		if(reply!=null && replyer!=null && boardNo!=null) {
			int replyNo = Integer.valueOf(boardNo);
			rvo.setBoardNo(replyNo);
			rvo.setReplyer(replyer);
			rvo.setReplyContent(reply);
			result = rdao.insert(rvo);
			if(result) {
				response.sendRedirect("/bbs/board?reply=reply&replyNo=" +replyNo);
			}
		}
		
	}
}

