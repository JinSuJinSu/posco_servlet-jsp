package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class BoardBizImpl implements BoardBiz{
	private static BoardDAO dao = new BoardDAOImpl();

	@Override
	public List<BoardDTO> selectAll() {
		Connection conn = JDBC.getConnection();
		List<BoardDTO> data = dao.selectAll(conn);
		JDBC.close(conn);
		
		return data;
	}
	
	
	}


