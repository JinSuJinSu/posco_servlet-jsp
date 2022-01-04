package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Sibal {
	
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/boardproject?characterEncoding=UTF-8&serverTimezone=UTC";
		String user = "root";
		String passwd = "1234";
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		
		try {
			conn = DriverManager.getConnection(url, user, passwd);	
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select board_no, user_id, title, content, "
					+ "read_count, write_date from board");
			while(rs.next()) {
				BoardDTO temp = new BoardDTO(rs.getInt("board_no"),rs.getString("user_id"),
						rs.getString("title"), rs.getString("content"), rs.getInt("read_count"),
						rs.getString("write_date"));
				
				list.add(temp);
			}
			}
			 catch (SQLException se) {
				System.out.println(se.getMessage());
			}
			finally {
				try {
				rs.close();
				stmt.close();
				conn.close();
					}
				catch(SQLException e) {
					e.printStackTrace();
				}
			}
		System.out.println(list);
		System.out.println(JDBC.getConnection());


	}
		
	}


