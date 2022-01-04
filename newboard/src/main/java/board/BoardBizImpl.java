package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class BoardBizImpl implements BoardBiz{
	BoardDAO dao = new BoardDAOImpl();

	@Override
	public List<BoardDTO> selectAll() {
		Connection conn = JDBC.getConnection();
		List<BoardDTO> result = dao.selectAll(conn);
		return result;
	}
	
	
	}


