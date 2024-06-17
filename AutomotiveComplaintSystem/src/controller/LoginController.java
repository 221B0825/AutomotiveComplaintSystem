package controller;

import model.UserList;

public class LoginController {

	private UserList userList;

	public LoginController() {
		this.userList = new UserList();
	}

	public String login() {
		// login logic
		// userList 검사해서 유저 확인
		//---------------------------
		// if login success
		//return BasicMessage.LoginSuccess.getMessage();
		//---------------------------
		// login 실패 이유
		// 1. 아이디 없음
		return "아이디 없음";
		
		// 2. 비밀번호 틀림
	
	}
}
