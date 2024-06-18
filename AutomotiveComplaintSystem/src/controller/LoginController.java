package controller;

import dto.LoginResult;
import model.User;
import model.UserList;
import view.BasicMessage;
import view.TUI;

public class LoginController {

	private UserList userList;
	private TUI TUI;

	public LoginController(TUI TUI, UserList userList) {
		this.userList = userList;
		this.TUI = TUI;
	}

	public LoginResult login() {

		// default
		LoginResult result = new LoginResult(BasicMessage.LoginFail.getMessage(), null);

		TUI.printMessage("[아이디(이메일)] ");
		String inputEmail = view.DataInput.sc.nextLine();
		TUI.printMessage("[비밀번호]");
		String password = view.DataInput.sc.nextLine();

		for (User user : userList.getUserList()) {
			if (user.getEmail().equals(inputEmail)) {
				if (user.getPassword().equals(password)) {
					return new LoginResult(BasicMessage.LoginSuccess.getMessage(), user);
				} else {
					return new LoginResult(new String("비밀번호 틀림"), null);
				}

			}
		}
		return result;

	}

	public String join() {
		TUI.printMessage("[아이디(이메일)] ");
		String inputEmail = view.DataInput.sc.nextLine();

		if(!inputEmail.matches("^[_0-9a-zA-Z-]+@[0-9a-zA-Z-]+(.[_0-9a-zA-Z]+)*$")) {
			return BasicMessage.JoinFail.getMessage()+ " : 이메일 형식이 아닙니다. ";
		}
		
		for (User user : userList.getUserList()) {
			if (user.getEmail().equals(inputEmail)) {
				return BasicMessage.JoinFail.getMessage() + " : 이미 회원가입된 이메일입니다.";

			}
		}
		
	
		long id = userList.getUserList().get(userList.getUserList().size() - 1).getId() + 1;

		TUI.printMessage("[비밀번호]");
		String password = view.DataInput.sc.nextLine();

		TUI.printMessage("[이름]");
		String name = view.DataInput.sc.nextLine();

		TUI.printMessage("[사는 지역(구)]");
		String address = view.DataInput.sc.nextLine();

		TUI.printMessage("[주민번호]");
		String socialNumber = view.DataInput.sc.nextLine();

		TUI.printMessage("[전화번호]");
		String phoneNumber = view.DataInput.sc.nextLine();

		userList.getUserList().add(new User(id, inputEmail, password, name, address, socialNumber, phoneNumber));

		return BasicMessage.JoinSuccess.getMessage();
	}
}
