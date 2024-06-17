package controller;

import dto.LoginResult;
import model.User;
import view.BasicMessage;
import view.TUI;

public class MainComplaintController {

	private TUI TUI;
	private LoginController loginController = new LoginController();

	private User loginUser;

	public MainComplaintController() {

	}

	public String login() {
		LoginResult result = this.loginController.login();
		if (result.getMessage().equals(BasicMessage.LoginSuccess.getMessage())) {
			loginUser = result.getLoginUser();
			return result.getMessage();
		} else {
			return result.getMessage();
		}
	}

	public String join() {
		return "";
	}

	public void associate(TUI TUI) {
		this.TUI = TUI;
	}

	// *****************
	// after login logic
	// sub Main Logic
	// *****************
	public void run() {

		while (true) {
			// show MainMenu
			this.TUI.showMainMenu();
			String selected = view.DataInput.sc.nextLine();
			switch (selected) {
			case "1":

				break;
			case "2":

				break;
			case "3":

				break;
			case "4":

				break;
			case "5":
				TUI.printLogoutMessage(loginUser.getName());
				return;

			default:
				break;
			}

		}
	}

}
