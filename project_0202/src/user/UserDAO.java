// 데이터베이스에서 회원 정보에 접근할 수 있도록
// DAO = 데이터베이스 접근 객체, 실질적으로 dB에서 회원정보를 불러오거나 회원정보 넣고자 할 때 사용

package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
//	connection > db에 접근할 수 있도록 하는 하나의 객체
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
//	mysql에 접속을 하도록 하는 부분
	public UserDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/BBS";
			String dbID = "root";
			String dbPassword = "today210131";
//			mysql에 접속할 수 있도록 도와주는 하나의 라이브러리
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
//	하나의 계정에 대한 로그인 함수
	public int login(String userID, String userPW) {
//		sql에 입력할 명령어
		String SQL = "SELECT userPW FROM USER WHERE userID = ?";
		
		try {
			pstmt = conn.prepareStatement(SQL);
//			매개변수로 넘어온 userID를 ?에 넣어주는 것 ID가 실제로 존재하는지 DB에서 가져오도록
			pstmt.setString(1, userID); //sql injection해킹 방어
//			result set에 실행한 결과 담기
			rs = pstmt.executeQuery();
//			next > 결과가 존재한다면
			if(rs.next()) {
//				입력된 pw받아서 입력된 유저와 동일하다면 결과로 1 출력
				if(rs.getString(1).equals(userPW)) {
					return 1; //로그인 성공
				}
				else 
					return 0; //비밀번호 불일치
			}
//			결과가 없다면
			return -1; //아이디가 없음
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return -2; //데이터베이스 오류
	}
	
	public int join(User user) {
		String SQL = "insert into user values(?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPW());
			pstmt.setString(3, user.getUserGender());
			pstmt.setString(4, user.getUserBirth());
			pstmt.setString(5, user.getUserName());
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; //db오류
	}

}
