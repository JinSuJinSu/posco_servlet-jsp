package board;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
		
		BoardBizImpl biz = new BoardBizImpl();
		List<BoardDTO> dataList = biz.selectAll();
		out.println(dataList);
//		
//		request.setAttribute("data", dataList);
//		out.println(dataList);
//		Connection conn = null;
//		try {
//			//Class.forName("com.mysql.cj.jdbc.Driver"); Java 6 버전부터는 생략 가능
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			String url = "jdbc:mysql://localhost:3306/boarddb?characterEncoding=UTF-8&serverTimezone=UTC";
//			String user = "root";
//			String passwd = "1234";
//			conn = DriverManager.getConnection(url, user, passwd);						
//		} catch (Exception e) {
//			System.out.println("MYSQL 연결 실패");
//			System.out.print("사유 : " + e.getMessage());
//		}
//		
//		List<BoardDTO> list = new ArrayList<BoardDTO>();
//		Statement stmt = null;
//		ResultSet rs = null;
//		try {
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery("select board_no, user_id, title, content, "
//					+ "read_count, write_date from board");
//			while(rs.next()) {
//				BoardDTO temp = new BoardDTO(rs.getInt("board_no"),rs.getString("user_id"),
//						rs.getString("title"), rs.getString("content"), rs.getInt("read_count"),
//						rs.getString("write_date"));
//				
//				list.add(temp);
//			}
//			}
//			 catch (SQLException se) {
//				System.out.println(se.getMessage());
//			}
//		System.out.println(list);
		

//		RequestDispatcher rd = request.getRequestDispatcher("/jsp/mainpage.jsp");
//		rd.forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
