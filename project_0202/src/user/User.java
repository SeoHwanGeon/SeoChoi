package user;

public class User {
// ȸ�� �������� DB�� �����ϰ� �ڹپȿ� ��� �� ����
	private String userID;
	private String userPW;
	private String userGender;
	private String userBirth;
	private String userName;
//	jsp�������� ����� �� �ֵ��� �Լ��� ����
//	�Ѹ��� ȸ�� �����͸� �ٷ� �� �ִ� �����ͺ��̽� �� �ڹٺ��� �ϼ�
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPW() {
		return userPW;
	}
	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserBirth() {
		return userBirth;
	}
	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
	
}
