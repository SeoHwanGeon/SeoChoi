package user;

public class User {
// 회원 정보들이 DB와 동일하게 자바안에 담길 수 있음
	private String userID;
	private String userPW;
	private String userGender;
	private String userBirth;
	private String userName;
//	jsp서버에서 사용할 수 있도록 함수를 만듦
//	한명의 회원 데이터를 다룰 수 있는 데이터베이스 및 자바빈즈 완성
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
