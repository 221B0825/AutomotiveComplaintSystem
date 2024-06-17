package controller;

import view.BasicMessage;
import view.TUI;

public class MainComplaintController {

	private TUI TUI;
	private LoginController loginController = new LoginController(); 

	public MainComplaintController() {

	}

	public String login() {
		String result = loginController.login();
		if (result.equals(BasicMessage.LoginSuccess.getMessage())) {
			return BasicMessage.LoginSuccess.getMessage();
		} else {
			return result;
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
				TUI.printExit();
				return;

			default:
				break;
			}

		}
	}

}
