package controller;

import dto.LoginResult;
import model.CarList;
import model.CarOwnerList;
import model.ComplaintList;
import model.User;
import model.UserList;
import view.BasicMessage;
import view.TUI;

public class MainComplaintController {

	private TUI TUI = new TUI();
	// Data
	private CarList carList;
	private ComplaintList complaintList;
	private UserList userList;
	private CarOwnerList carOwnerList;
	

	// Controller
	private LoginController loginController;
	private IssuanceComplainController issuanceComplainController;
	private RegisterComplaintController registerComplaintController;

	private User loginUser;

	public MainComplaintController() {
		carList = new CarList();
		userList = new UserList();
		complaintList = new ComplaintList();
		carOwnerList = new CarOwnerList();
		
		
		loginController = new LoginController(TUI, userList);
		issuanceComplainController = new IssuanceComplainController(TUI, carList, carOwnerList);
		registerComplaintController = new RegisterComplaintController(TUI, carList, userList, complaintList);
	}

	public void associate(TUI TUI) {
		this.TUI = TUI;
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
			registerComplaintController.register(loginUser);
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
			issuanceComplainController.carRegistrationReIssuance(loginUser);
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
