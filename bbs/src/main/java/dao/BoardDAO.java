package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vo.BoardVO;


public class BoardDAO {
	
	// 전체 글 선택
	public List<BoardVO> selectAll() {
		Connection conn = JDBC.getConnection();
		List<BoardVO> list = new ArrayList<BoardVO>();
		try(Statement stmt = conn.createStatement();){
			ResultSet rs = stmt.executeQuery("select board_no, user_id, title, "
					+ "read_count, reply_count, date_format(write_date, '%Y-%m-%d %H:%i:%s') write_date from board "
					+ "order by board_no desc");
			while(rs.next()) {
				BoardVO temp = new BoardVO(rs.getInt("board_no"),rs.getString("user_id"),
						rs.getString("title"), rs.getInt("read_count"), rs.getInt("reply_count"),
						rs.getString("write_date"));
				
				list.add(temp);
			}
			}
			 catch (SQLException se) {
				System.out.println(se.getMessage());
			}
			JDBC.close(conn);		
		return list;
	}
	
	// 특정 번호를 가지고 있는 글을 찾기
	public BoardVO selectOne(int number) {
		Connection conn = JDBC.getConnection();
		BoardVO vo=null;
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("select board_no, user_id, title, content, "
					+ "read_count, reply_count, date_format(write_date, '%Y-%m-%d %H:%i:%s') write_date from board "
					+ "where board_no=" + number);
			if (rs.next()) {
				vo = new BoardVO();
				vo.setBoardNO(rs.getInt("board_no"));
				vo.setUserID(rs.getString("user_id"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setReadCount(rs.getInt("read_count"));	
				vo.setReplyCount(rs.getInt("reply_count"));
				vo.setWriteDate(rs.getString("write_date"));

			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		JDBC.close(conn);
		return vo;
	}
	
	// 특정 글자를 포함하고 있는 글 찾는데 필요한 메소드
	public List<BoardVO> search(String value, String condition){
		Connection conn = JDBC.getConnection();
		List<BoardVO> list = new ArrayList<BoardVO>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("select board_no, user_id, title, content, "
					+ "read_count, reply_count, date_format(write_date, '%Y-%m-%d %H:%i:%s') write_date from board "
					+ "where " +  condition + " like " + "'%" + value + "%'" + " order by board_no desc");
			while(rs.next()) {
				BoardVO temp = new BoardVO(rs.getInt("board_no"),rs.getString("user_id"),
						rs.getString("title"), rs.getInt("read_count"), rs.getInt("reply_count"),
						rs.getString("write_date"));
				
				list.add(temp);
			}
			}
			 catch (SQLException se) {
				System.out.println(se.getMessage());
			}
			JDBC.close(conn);		
		return list;
				
	}
	
	// 특정 유저의 글만 볼 수 있도록 하는데 필요한 메소드
	public List<BoardVO> searchUser(String user){
		Connection conn = JDBC.getConnection();
		List<BoardVO> list = new ArrayList<BoardVO>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("select board_no, user_id, title, content, "
					+ "read_count, reply_count, date_format(write_date, '%Y-%m-%d %H:%i:%s') write_date from board "
					+ "where user_id='" + user + "'" + " order by board_no desc");
			while(rs.next()) {
				BoardVO temp = new BoardVO(rs.getInt("board_no"),rs.getString("user_id"),
						rs.getString("title"), rs.getInt("read_count"), rs.getInt("reply_count"),
						rs.getString("write_date"));
				list.add(temp);
			}
			}
			 catch (SQLException se) {
				System.out.println(se.getMessage());
			}
			JDBC.close(conn);		
		return list;
				
	}
	
	// 글 추가 시 필요한 메소드
	public boolean insert(BoardVO vo) {
		boolean result = false;
		Connection conn = JDBC.getConnection();
		try (PreparedStatement pstmt = conn
				.prepareStatement("insert into board (user_id, title, content, read_count, reply_count, write_date) "
						+ "values(?, ?, ?, ?, ?, now())")) {
				pstmt.setString(1, vo.getUserID());
				pstmt.setString(2, vo.getTitle());
				pstmt.setString(3, vo.getContent());
				pstmt.setInt(4, 0);
				pstmt.setInt(5, 0);
				pstmt.executeUpdate();
				result = true;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		JDBC.close(conn);
		return result;
		
	}
	// 글 삭제시 필요한 메소드
	public boolean delete(int number) {
		boolean result = false;
		Connection conn = JDBC.getConnection();
		try (PreparedStatement pstmt = conn.prepareStatement("delete from board where board_no = ?")) {
			pstmt.setInt(1, number);
			pstmt.executeUpdate();
			result = true;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		JDBC.close(conn);
		return result;
	}
	
	// 글 작성 시 수정을 위한 메소드
	public boolean update(BoardVO vo) {
		boolean result = false;
		Connection conn = JDBC.getConnection();
		try (PreparedStatement pstmt = conn.prepareStatement("update board "
				+ "set user_id = ?,  title = ?, content = ? where board_no = ?")) {
			pstmt.setString(1, vo.getUserID());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.setInt(4, vo.getBoardNO());
			pstmt.executeUpdate();		
			result = true;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		JDBC.close(conn);
		return result;
	}
	
	// 조회수 증가시키기는데 필요한 메소드
	public boolean readUpdate(BoardVO vo) {
		boolean result = false;
		Connection conn = JDBC.getConnection();
		try (PreparedStatement pstmt = conn.prepareStatement("update board "
				+ "set read_count = ? where board_no = ?")) {
			pstmt.setInt(1, vo.getReadCount());
			pstmt.setInt(2, vo.getBoardNO());
			pstmt.executeUpdate();		
			result = true;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		JDBC.close(conn);
		return result;
	}
	
	// 답변수 증가시키기는데 필요한 메소드
	public boolean replyUpdate(BoardVO vo) {
		boolean result = false;
		Connection conn = JDBC.getConnection();
		try (PreparedStatement pstmt = conn.prepareStatement("update board "
				+ "set reply_count = ? where board_no = ?")) {
			pstmt.setInt(1, vo.getReplyCount());
			pstmt.setInt(2, vo.getBoardNO());
			pstmt.executeUpdate();		
			result = true;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		JDBC.close(conn);
		return result;
	}
	
	// 페이징 처리를 위한 메소드
	public List<BoardVO> selectPage(int startPage, int endPage) {
		Connection conn = JDBC.getConnection();
		List<BoardVO> list = new ArrayList<BoardVO>();
		try(Statement stmt = conn.createStatement();){
			ResultSet rs = stmt.executeQuery("select board_no, user_id, title, "
					+ "read_count, reply_count, date_format(write_date, '%Y-%m-%d %H:%i:%s') write_date from board "
					  +"order by board_no desc limit " + startPage + "," + endPage);
			while(rs.next()) {
				BoardVO temp = new BoardVO(rs.getInt("board_no"),rs.getString("user_id"),
						rs.getString("title"), rs.getInt("read_count"), rs.getInt("reply_count"),
						rs.getString("write_date"));
				
				list.add(temp);
			}
			}
			 catch (SQLException se) {
				System.out.println(se.getMessage());
			}
			JDBC.close(conn);		
		return list;
	}

		
	}



	


