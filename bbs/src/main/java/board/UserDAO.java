package board;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UserDAO {

	public boolean insert(UserVO uo) {
		boolean result = false;
		Connection conn = JDBC.getConnection();
		try (PreparedStatement pstmt = conn.prepareStatement("insert into userinfo (user_id, name, password, phone, email) values (?, ?,?,?,?)")) { // 동적파라미터인 ? 를 줄수 있는 개수는 정해지지 않는다. 무한대
			pstmt.setString(1, uo.getUserID());
			pstmt.setString(2, uo.getName());
			pstmt.setString(3, uo.getPassword());
			pstmt.setString(4, uo.getPhone());
			pstmt.setString(5, uo.getEmail());

			pstmt.executeUpdate();	 //업데이트 필요한 값을 서블릿이 보내준다.	
			result = true;  // 업데이트에 성공하면 TRUE 리턴 한다.
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		JDBC.close(conn);
		return result;
	}
	
	public int selectOne(String user_id, String user_pw) {
		Connection conn = JDBC.getConnection();
//		UserVO uo = null; // vo변수를 반드시 초기화 시켜줘야 한다.
//		boolean result = false;
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(
					"select user_id, password from userinfo where user_id =  '" + user_id + "'"
					); //최근에 작성한 것일 수 록 위로 정렬

			// VISIOR ARRAY를 넣는 객체다.
//			VisitorVO vo = null; // visitorvo는 하나만 만들고 
			if(rs.next()) { // VISITOR 테이블이 가지고 있는 행의 개수 만큼 반복하게 된다. 행이 5개면 5번
//				uo = new UserVO(); //반복문이 수행되는 동안 VOSITOR를 생성 
//				uo.setUser_id(rs.getString("user_id"));   // mysql에서 user_id를 가져오고
				
//				uo.setUser_pw(rs.getString("user_pw"));          
//		        uo = new UserVO();
//		        uo.setUser_id(user_id);
		        
				if(rs.getString("password").equals(user_pw)) {
					System.out.println("성공");

					return 1; //로그인 성공

				}else {
					System.out.println("비번 아이디 잘못");

					return 0; //비밀번호랑 아이디 틀림
				}
//			    result = true;
				
			}
			System.out.println("아이디 없음");

			return -1; //아이디 없음
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		JDBC.getConnection();
		System.out.println("에러");

		return -2; //오류
		
		
	}
	
	
}
