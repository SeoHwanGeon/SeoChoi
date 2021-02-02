// �����ͺ��̽����� ȸ�� ������ ������ �� �ֵ���
// DAO = �����ͺ��̽� ���� ��ü, ���������� dB���� ȸ�������� �ҷ����ų� ȸ������ �ְ��� �� �� ���

package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
//	connection > db�� ������ �� �ֵ��� �ϴ� �ϳ��� ��ü
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
//	mysql�� ������ �ϵ��� �ϴ� �κ�
	public UserDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/BBS";
			String dbID = "root";
			String dbPassword = "today210131";
//			mysql�� ������ �� �ֵ��� �����ִ� �ϳ��� ���̺귯��
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
//	�ϳ��� ������ ���� �α��� �Լ�
	public int login(String userID, String userPW) {
//		sql�� �Է��� ��ɾ�
		String SQL = "SELECT userPW FROM USER WHERE userID = ?";
		
		try {
			pstmt = conn.prepareStatement(SQL);
//			�Ű������� �Ѿ�� userID�� ?�� �־��ִ� �� ID�� ������ �����ϴ��� DB���� ����������
			pstmt.setString(1, userID); //sql injection��ŷ ���
//			result set�� ������ ��� ���
			rs = pstmt.executeQuery();
//			next > ����� �����Ѵٸ�
			if(rs.next()) {
//				�Էµ� pw�޾Ƽ� �Էµ� ������ �����ϴٸ� ����� 1 ���
				if(rs.getString(1).equals(userPW)) {
					return 1; //�α��� ����
				}
				else 
					return 0; //��й�ȣ ����ġ
			}
//			����� ���ٸ�
			return -1; //���̵� ����
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return -2; //�����ͺ��̽� ����
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
		return -1; //db����
	}

}
