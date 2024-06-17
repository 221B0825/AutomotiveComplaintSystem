package view;

import controller.MainComplaintController;

public class TUI {

	private MainComplaintController CM;

	public TUI() {

	}

	// associate View & MainSystem
	public void associate(MainComplaintController CM) {
		this.CM = CM;
	}

	public void printLoginMenu() {
		System.out.println("환영합니다. 자동차 민원 관리 시스템입니다.");
		System.out.println("1.로그인 2.회원가입 3.종료");
		System.out.print("입력: ");
	}

	public void printFailMessage(String reason) {
		System.out.println(reason + " :: Fail");
	}

	public void printWrongInputMessage() {
		System.out.println("잘못된 입력입니다.");
	}

	public void showMainMenu() {
		System.out.println("1.등록 민원 신청 2.발급 민원 신청 3.신청 승인 4.납부 5.종료");
		System.out.print("입력: ");
	}

	public void printExit() {
		System.out.println(BasicMessage.Exit);
	}
}
