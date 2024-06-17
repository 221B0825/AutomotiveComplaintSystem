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

	public void printMainMenu() {
		System.out.println("1.등록 민원 신청 2.발급/열람 민원 신청 3.신청 승인 4.납부 5.로그아웃");
		System.out.print("입력: ");
	}

	public void printRegistrationComplaintMenu() {
		System.out.println(
				"1.자동차 신규등록신청 2.자동차 변경등록신청 3.자동차 양도증명 4.자동차 이전등록신청 5.저당권설정등록신청 6.자동차 말소등록신청 7.정기/종합검사 유효기간 연장(유예)신청");
		System.out.print("입력: ");
	}

	public void printInissuanceComplainMenu() {
		System.out.println(
				"1.자동차 등록증 재발급신청 2.자동차 등록원부 등본(초본) 발급·열람신청 3.자동차 말소사실증명서 발급신청 4.이륜차 사용폐지증명서 발급신청 5.이륜차 사용신고필증 재발급신청");
		System.out.print("입력: ");
	}

	public void printLogoutMessage(String userName) {
		System.out.println(userName + " :: " + BasicMessage.Logout.getMessage());
	}

	public void printExit() {
		System.out.println(BasicMessage.Exit.getMessage());
	}
}
