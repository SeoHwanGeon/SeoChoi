package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BbsDAO {
//	connection > db에 접근할 수 있도록 하는 하나의 객체
	private Connection conn;
	private ResultSet rs;
	
//	mysql에 접속을 하도록 하는 부분
	public BbsDAO() {
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
//	현재 시간을 가져오는 함수 게시판 작성 시 현재 시간을 서버로 넣어주는 함수
	public String getDate() {
		String SQL= "SELECT now()";
		try {
			//sql문장을 실행준비 단계로
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			//실제로 실행했을 때 나오는 결과 가져옴
			rs = pstmt.executeQuery();
			// 결과가 있는 경우
			if(rs.next()) {
				//현재 날짜 그대로 반환하도록
				return rs.getString(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return ""; //DB오류
	}
	
	public int getNext() {
		// 게시글 번호 1번부터 하나씩 늘어나도록 
		// 마지막 게시글 +1 내림차순으로
		String SQL= "SELECT bbsID FROM BBS ORDER BY bbsID DESC";
		try {
			//sql문장을 실행준비 단계로
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			//실제로 실행했을 때 나오는 결과 가져옴
			rs = pstmt.executeQuery();
			// 결과가 있는 경우
			if (rs.next()) {
				//현재 날짜 그대로 반환하도록
				return rs.getInt(1)+1;
			}
			return 1; //현재 작성하는 게시물이 첫 번째 게시물인 경우
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // DB오류
	}
	//게시물 입력하는 함수
	public int write(String bbsTitle, String userID, String bbsContent) {
		String SQL= "INSERT INTO BBS VALUES(?, ?, ?, ?, ?, ?)";
		try {
			//sql문장을 실행준비 단계로
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext());
			pstmt.setString(2, bbsTitle);
			pstmt.setString(3, userID);
			pstmt.setString(4, getDate());
			pstmt.setString(5, bbsContent);
			pstmt.setInt(6, 1); //available 글이 보여지는 형태 >> 1옴
			// 성공적으로 수행한다면 0이상의 결과 반환
			return pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // DB오류
	}
	
	// 10개 게시글 리스트를 출력할 수 있도록 하는 함수
	public ArrayList<Bbs> getList(int pageNumber){
		// 특정한 페이지에 10개의 게시글을 가져오도록 bbsID가 특정한 숫자보다 작은데 삭제되지 않은 글 내림차순 정렬 위에서 10개까지만 가져오도록
		String SQL= "SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable = 1 ORDER BY bbsID DESC LIMIT 10";
		ArrayList<Bbs> list = new ArrayList<Bbs>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			// getnext : 다음으로 작성될 글의 번호  현재 게시글 수보다 하나 적은  것까지 다 나오도록 10개만 출력하도록 
			pstmt.setInt(1, getNext() - (pageNumber-1)*10);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Bbs bbs = new Bbs();
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailable(rs.getInt(6));
				list.add(bbs);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 게시글이 10개인 경우 다음페이지가 필요없음
	// page처리를 위한 함수
	// 특정한 페이지가 존재하는지 알 수 있는 함수
	public boolean nextPage(int pageNumber) {
		String SQL= "SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable = 1";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			// getnext : 다음으로 작성될 글의 번호  현재 게시글 수보다 하나 적은  것까지 다 나오도록 10개만 출력하도록 
			pstmt.setInt(1, getNext() - (pageNumber-1)*10);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 결과가 하나라도 존재한다면 다음페이지로 넘어갈 수 있도록
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//하나의 글 내용을 불러오는 함수
	public Bbs getBbs(int bbsID) {
		String SQL= "SELECT * FROM BBS WHERE bbsID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			// 특정한 bbsID 하나의 게시글 가져오도록 
			pstmt.setInt(1, bbsID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 결과가 하나라도 존재한다면 다음페이지로 넘어갈 수 있도록
				Bbs bbs = new Bbs();
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailable(rs.getInt(6));
				return bbs;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null; //해당 글이 존재하지 않는 경우
	}
	
	// 특정한 번호와 매개변수로 바꾸기
	public int update(int bbsID, String bbsTitle, String bbsContent) {
		// 특정한 아이디에 해당하는 내용과 제목 변경
		String SQL= "UPDATE BBS SET bbsTitle = ?, bbsContent = ? WHERE bbsID = ?";
		try {
			//sql문장을 실행준비 단계로
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, bbsTitle);
			pstmt.setString(2, bbsContent);
			pstmt.setInt(3, bbsID);
			// 성공적으로 실행 될 경우 0이상의 값 출력
			return pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // DB오류
	}
	
}
