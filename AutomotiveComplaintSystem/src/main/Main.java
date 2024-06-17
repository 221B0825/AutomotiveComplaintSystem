package main;

import controller.MainComplaintController;
import view.BasicMessage;
import view.DataInput;
import view.TUI;

public class Main {

	public static void main(String[] args) {

		MainComplaintController MC = new MainComplaintController();
		TUI TUI = new TUI();

		// associate View & Controller
		MC.associate(TUI);
		TUI.associate(MC);

		// login logic
		while (true) {
			TUI.printLoginMenu();
			String selected = DataInput.sc.nextLine();
			switch (selected) {
			case "1":
				String result = MC.login();
				if (result.equals(BasicMessage.LoginSuccess.getMessage())) {
					// login success
					// start main logic
					MC.run();
				} else {
					// login fail
					TUI.printFailMessage(result);
				}
				break;
			case "2":
				if (MC.join().equals(BasicMessage.JoinSuccess.getMessage())) {
					continue;
				} else {
					System.out.println(MC.join());
				}
				break;

			case "3":
				TUI.printExit();
				return;
			default:
				TUI.printWrongInputMessage();
				break;
			}
		}

	}

}
