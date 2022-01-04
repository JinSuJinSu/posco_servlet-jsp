package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BoardDAOImpl implements BoardDAO{
	@Override
	public List<BoardDTO> selectAll(Connection conn) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
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
			
		return list;
	}

		
	}



	


