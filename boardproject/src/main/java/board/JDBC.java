package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
		String url = "jdbc:mysql://localhost:3306/sqldb?characterEncoding=UTF-8&serverTimezone=UTC";
		String user = "root";
		String passwd = "1234";
		try {
			conn = DriverManager.getConnection(url, user, passwd);
			conn.close();
			System.out.println("MYSQL 연결 성공");
			return conn;
		}	
		catch (SQLException e) {
			e.printStackTrace();
			}
		
		return conn;		
	}
	
	public static boolean isConnection(Connection conn) {
		boolean valid = true;
		try {
			if(conn!=null || conn.isClosed()) {
				valid=false;
			}	
		}
		catch(SQLException e) {
			valid=true;
			e.printStackTrace();
		}
		return valid;
	}
	
	public static void close(Connection conn) throws ClassNotFoundException, SQLException{
		if(isConnection(conn)) {
			conn.close();
		}
		
	}

}
