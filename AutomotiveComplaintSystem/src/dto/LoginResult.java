package dto;

import model.User;

public class LoginResult {

	private String message;
	private User loginUser;

	public LoginResult() {
		// TODO Auto-generated constructor stub
	}

	public LoginResult(String message, User loginUser) {
		this.message = message;
		this.loginUser = loginUser;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(User loginUser) {
		this.loginUser = loginUser;
	}

}
