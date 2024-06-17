package controller;

import dto.LoginResult;
import model.User;
import model.UserList;
import view.BasicMessage;

public class LoginController {

	private UserList userList;

	public LoginController() {
		this.userList = new UserList();
	}

	public LoginResult login() {

		// test result
		LoginResult result = new LoginResult(BasicMessage.LoginSuccess.getMessage(), userList.getUserList().get(0));
		// login logic
		// userList 검사해서 유저 확인
		// ---------------------------
		// if login success
		// return BasicMessage.LoginSuccess.getMessage();
		// ---------------------------
		// login 실패 이유
		// 1. 아이디 없음
		return result;

		// 2. 비밀번호 틀림

	}
}
