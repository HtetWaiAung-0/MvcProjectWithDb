package model;

public class UserBean {
	private String userId;
	private String userMail;
	private String userPassword;
	private String userConPassword;
	private String userRole;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserConPassword() {
		return userConPassword;
	}
	@Override
	public String toString() {
		return "UserBean [userId=" + userId + ", userMail=" + userMail + ", userPassword=" + userPassword
				+ ", userConPassword=" + userConPassword + ", userRole=" + userRole + "]";
	}
	public void setUserConPassword(String userConPassword) {
		this.userConPassword = userConPassword;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	
}
