package controller;

import dto.LoginResult;
import model.User;
import view.BasicMessage;
import view.TUI;

public class MainComplaintController {

	private TUI TUI;
	private LoginController loginController = new LoginController();
	private InissuanceComplainController inissuanceComplainController = new InissuanceComplainController(TUI);

	private User loginUser;

	public MainComplaintController() {

	}

	public String login() {
		LoginResult result = this.loginController.login();
		if (result.getMessage().equals(BasicMessage.LoginSuccess.getMessage())) {
			this.loginUser = result.getLoginUser();
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
			this.TUI.printMainMenu();
			String selected = view.DataInput.sc.nextLine();
			switch (selected) {
			// 등록민원 신청
			case "1":
				registerationComplain();
				break;
			// 발급/열람 민원 신청
			case "2":
				issuanceComplain();
				break;
			// 사용자 신청 승인
			case "3":

				break;
			// 납부
			case "4":

				break;
			// 로그아웃
			case "5":
				TUI.printLogoutMessage(loginUser.getName());
				return;

			default:
				TUI.printWrongInputMessage();
				break;
			}

		}
	}

	// 등록 민원 신청 처리
	private void registerationComplain() {
		this.TUI.printRegistrationComplaintMenu();

		String selected = view.DataInput.sc.nextLine();
		switch (selected) {
		// 자동차 신규등록 신청
		case "1":

			break;
		// 자동차 변경등록 신청
		case "2":

			break;
		// 자동차 양도증명
		case "3":

			break;
		// 자동차 이전등록신청
		case "4":

			break;
		// 저당권설정등록신청
		case "5":

			break;
		// 자동차 말소등록신청
		case "6":

			break;
		// 정기/종합검사 유효기간 연장(유예)신청
		case "7":

			break;

		default:
			TUI.printWrongInputMessage();
			break;
		}
	}

	// 발급 민원 신청 처리
	private void issuanceComplain() {
		this.TUI.printInissuanceComplainMenu();
		String selected = view.DataInput.sc.nextLine();

		switch (selected) {
		// 자동차 등록증 재발급신청
		case "1":
			inissuanceComplainController.carRegistrationReIssuance();
			break;
		// 자동차 등록원부 등본(초본) 발급·열람신청
		case "2":

			break;
		// 자동차 말소사실증명서 발급신청
		case "3":

			break;
		// 이륜차 사용폐지증명서 발급신청
		case "4":

			break;
		// 이륜차 사용신고필증 재발급신청
		case "5":

			break;

		default:
			TUI.printWrongInputMessage();
			break;
		}

	}

}
