package board;

import java.sql.Connection;
import java.util.List;

public interface BoardDAO {
	public List<BoardDAO> selectAll(Connection conn);

}
