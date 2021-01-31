package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BbsDAO {
//	connection > db�� ������ �� �ֵ��� �ϴ� �ϳ��� ��ü
	private Connection conn;
	private ResultSet rs;
	
//	mysql�� ������ �ϵ��� �ϴ� �κ�
	public BbsDAO() {
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
//	���� �ð��� �������� �Լ� �Խ��� �ۼ� �� ���� �ð��� ������ �־��ִ� �Լ�
	public String getDate() {
		String SQL= "SELECT now()";
		try {
			//sql������ �����غ� �ܰ��
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			//������ �������� �� ������ ��� ������
			rs = pstmt.executeQuery();
			// ����� �ִ� ���
			if(rs.next()) {
				//���� ��¥ �״�� ��ȯ�ϵ���
				return rs.getString(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return ""; //DB����
	}
	
	public int getNext() {
		// �Խñ� ��ȣ 1������ �ϳ��� �þ���� 
		// ������ �Խñ� +1 ������������
		String SQL= "SELECT bbsID FROM BBS ORDER BY bbsID DESC";
		try {
			//sql������ �����غ� �ܰ��
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			//������ �������� �� ������ ��� ������
			rs = pstmt.executeQuery();
			// ����� �ִ� ���
			if (rs.next()) {
				//���� ��¥ �״�� ��ȯ�ϵ���
				return rs.getInt(1)+1;
			}
			return 1; //���� �ۼ��ϴ� �Խù��� ù ��° �Խù��� ���
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // DB����
	}
	//�Խù� �Է��ϴ� �Լ�
	public int write(String bbsTitle, String userID, String bbsContent) {
		String SQL= "INSERT INTO BBS VALUES(?, ?, ?, ?, ?, ?)";
		try {
			//sql������ �����غ� �ܰ��
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext());
			pstmt.setString(2, bbsTitle);
			pstmt.setString(3, userID);
			pstmt.setString(4, getDate());
			pstmt.setString(5, bbsContent);
			pstmt.setInt(6, 1); //available ���� �������� ���� >> 1��
			// ���������� �����Ѵٸ� 0�̻��� ��� ��ȯ
			return pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // DB����
	}
	
	// 10�� �Խñ� ����Ʈ�� ����� �� �ֵ��� �ϴ� �Լ�
	public ArrayList<Bbs> getList(int pageNumber){
		// Ư���� �������� 10���� �Խñ��� ���������� bbsID�� Ư���� ���ں��� ������ �������� ���� �� �������� ���� ������ 10�������� ����������
		String SQL= "SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable = 1 ORDER BY bbsID DESC LIMIT 10";
		ArrayList<Bbs> list = new ArrayList<Bbs>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			// getnext : �������� �ۼ��� ���� ��ȣ  ���� �Խñ� ������ �ϳ� ����  �ͱ��� �� �������� 10���� ����ϵ��� 
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
	
	// �Խñ��� 10���� ��� ������������ �ʿ����
	// pageó���� ���� �Լ�
	// Ư���� �������� �����ϴ��� �� �� �ִ� �Լ�
	public boolean nextPage(int pageNumber) {
		String SQL= "SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable = 1";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			// getnext : �������� �ۼ��� ���� ��ȣ  ���� �Խñ� ������ �ϳ� ����  �ͱ��� �� �������� 10���� ����ϵ��� 
			pstmt.setInt(1, getNext() - (pageNumber-1)*10);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// ����� �ϳ��� �����Ѵٸ� ������������ �Ѿ �� �ֵ���
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//�ϳ��� �� ������ �ҷ����� �Լ�
	public Bbs getBbs(int bbsID) {
		String SQL= "SELECT * FROM BBS WHERE bbsID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			// Ư���� bbsID �ϳ��� �Խñ� ���������� 
			pstmt.setInt(1, bbsID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// ����� �ϳ��� �����Ѵٸ� ������������ �Ѿ �� �ֵ���
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
		return null; //�ش� ���� �������� �ʴ� ���
	}
	
	// Ư���� ��ȣ�� �Ű������� �ٲٱ�
	public int update(int bbsID, String bbsTitle, String bbsContent) {
		// Ư���� ���̵� �ش��ϴ� ����� ���� ����
		String SQL= "UPDATE BBS SET bbsTitle = ?, bbsContent = ? WHERE bbsID = ?";
		try {
			//sql������ �����غ� �ܰ��
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, bbsTitle);
			pstmt.setString(2, bbsContent);
			pstmt.setInt(3, bbsID);
			// ���������� ���� �� ��� 0�̻��� �� ���
			return pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // DB����
	}
	
}
