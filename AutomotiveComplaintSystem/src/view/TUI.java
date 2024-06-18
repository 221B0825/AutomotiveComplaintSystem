package view;

import java.util.List;

import controller.MainComplaintController;
import model.Car;
import model.Customer;
import model.User;

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

	public void printSelectCarRegisterMessage() {
		System.out.println("등록 신청할 차량번호를 입력해주세요 : ");
	}

	public void printNotFoundCarMessage() {
		System.out.println("존재하지 않는 차량입니다.");
	}

	public void printNotFoundUserMessage() {
		System.out.println("존재하지 않는 회원입니다.");

	}

	public void printCheckCarInformationMessage() {
		System.out.println("본인의 차량 정보가 맞습니까?");

	}

	public void printCheckUserInformationMessage() {
		System.out.println("신청자 본인의 정보가 맞습니까?");

	}

	public void printConfirmChoiceMessage() {
		System.out.println("[Yes/No]");
	}

	public void printMissingDocument(String fileName) {
		// TODO Auto-generated method stub
		System.out.println("'" + fileName + "'파일이 누락되었습니다.");
	}

	public void printSelectCarUpdateMessage() {
		System.out.println("변경 신청할 자동차의 등록번호를 입력해주십시오");
	}

	// **********
	// 발급 민원 TUI
	// **********

	public void printSelectCarStatus() {
		System.out.println("차량 상태 선택");
		System.out.println("1. ");

	}

	public void printIssuanceOptions() {
		System.out.println("발급 민원 신청을 선택하세요:");
		System.out.println("1. 자동차 등록증 재발급 신청");
		// 다른 옵션들은 생략
	}

	public void printReissueReasons() {
		System.out.println("[재교부 사유 목록]");
		System.out.println("1. 분실");
		System.out.println("2. 훼손");
		System.out.println("3. 도난");
		System.out.println("4. 기타");
		System.out.print("선택: ");
	}

	public void printReceiveMethod() {
		System.out.println("수령 방법:");
		System.out.println("수령물: 자동차 등록증");
		System.out.println("수령인: 본인");
		System.out.println("수령방법: 온라인 발급(인쇄)");
	}

	public void printSelectCarMessage() {
		System.out.println("선택할 차량의 차량번호를 입력해 주십시오.");
		System.out.print("입력: ");
	}

	public void printConfirmation() {
		System.out.println("신청하시겠습니까? [Y/N]");
		System.out.print("입력: ");
	}

	public void printMessage(String message) {
		System.out.println(message);
	}

	public void printOwnerInfo(User owner) {
		System.out.println("*****소유자 정보*****");
		System.out.println("이름: " + owner.getName());
		System.out.println("주민번호: " + owner.getSocialNumber());
		System.out.println("휴대전화번호: " + owner.getPhoneNumber());
		System.out.println("이메일: " + owner.getEmail());
	}

	public void printCarInfo(Car car) {
		System.out.println("*****차량 정보*****");
		System.out.println("차량 번호: " + car.getIdentificationNumber());
		System.out.println("차량 모델: " + car.getName());
		System.out.println("차량 색상: " + car.getColor());
		System.out.println("차량 연식: " + car.getModelYear());
	}

	public void printPaymentOptions() {
		System.out.println("결제 방법을 선택하세요:");
		System.out.println("1. 카드");
		System.out.println("2. 가상계좌");
		System.out.print("입력: ");
	}

	public void printPaymentConfirmation() {
		System.out.print("납부하시겠습니까? (Y/N): ");
	}

	public void printCarRegistrationCertificate(User owner, Car car) {
		System.out.println("======================================");
		System.out.println("[자동차 등록증]");
		System.out.println("사용자 정보");
		System.out.println("- 이름 : " + owner.getName());
		System.out.println("- 주민번호 : " + owner.getSocialNumber());
		System.out.println("차량 정보");
		System.out.println("- 차량 번호 : " + car.getIdentificationNumber());
		System.out.println("- 차종 : " + car.getName());
		System.out.println("======================================");
	}


	public void printRepresentativeOwner(Customer representativeOwner) {
		System.out.println("대표 소유자 정보");
		System.out.println(representativeOwner.toString());
	}
	
	public void printCoOwnerInformation(Customer coOwner) {
		System.out.println("공동 소유자 정보");
		System.out.println(coOwner.toString());

	}


	public void printChangeOwnerSelectMessage() {
		System.out.println("변경할 소유인을 선택해주세요(1.대표소유자 2.공동소유자) >");
	}

	public void printEmailInputMessage() {
		System.out.print("e-mail을 입력해주세요 > ");
		
	}

	public void printAddressInputMessage() {
		System.out.print("주소를 입력해주세요 > ");
	}

	public void printOwnerInfoChangeAskMessage() {
		System.out.print("소유인 정보를 변경하시겠습니까? > ");
	}

	public void printChangeRepresentativeOwnerSelectMessage() {
		System.out.print("대표 소유자를 변경하시겠습니까? > ");

	}

	public void printPrimaryOwnerRestrictionMessage() {
		System.out.println("대표소유자만이 변경할 수 있습니다.");
	}

}
