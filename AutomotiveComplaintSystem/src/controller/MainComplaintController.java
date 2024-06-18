package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import dto.LoginResult;

import model.Car;
import model.AssignmentCetification;
import model.CarList;
import model.CarOwner;
import model.CarStatus;
import model.Complaint;
import model.ComplaintStatus;
import model.Customer;
import model.User;
import model.UserList;
import view.BasicMessage;
import view.TUI;

public class MainComplaintController {

	private TUI TUI = new TUI();
	// Data
	private CarList carList;
	private UserList userList = new UserList();
	private ArrayList<Complaint> complaintList;
	private ArrayList<CarOwner> carOwnerList;
	private ArrayList<AssignmentCetification> assignmentCetificationList;

	// Controller
	private LoginController loginController;
	private IssuanceComplainController issuanceComplainController;
	private RegisterComplaintController registerComplaintController;

	private AdminController adminController;

	private User loginUser;

	public MainComplaintController() {
		carList = new CarList();
		userList = new UserList();
		complaintList = new ArrayList<Complaint>();
		carOwnerList = new ArrayList<CarOwner>();
		assignmentCetificationList = new ArrayList<AssignmentCetification>();

		// init carOwner
		File file = new File("data/carOwner/carOwner.txt");
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNext()) {
				// add user : read line

				String[] split = sc.nextLine().split("\\|");
				String identificationNumber = split[0];
				String representativeOwner = split[1];
				String coOwner = split[2];
				Car car = carList.findByIdentificationNumber(identificationNumber);
				// change status
				car.setCarStatus(CarStatus.REGISTER);
				Customer ROwner = (Customer) userList.findByEmail(representativeOwner);
				Customer COwner = (Customer) userList.findByEmail(coOwner);

				CarOwner carOwner = new CarOwner(car, ROwner, COwner);
				carOwnerList.add(carOwner);
			}
			carList.findByIdentificationNumber("EEEEEEE").setCarStatus(CarStatus.CANCELLATION);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

		loginController = new LoginController(TUI, userList);
		issuanceComplainController = new IssuanceComplainController(TUI, carList, carOwnerList, userList);
		registerComplaintController = new RegisterComplaintController(TUI, carList, userList, complaintList,
				carOwnerList, assignmentCetificationList);

		adminController = new AdminController(TUI, userList, carList, carOwnerList, complaintList);
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
		String result = this.loginController.join();
		return result;
	}

	// *****************
	// after login logic
	// sub Main Logic
	// *****************
	public void run() {

		if (loginUser.getEmail().equals("admin")) {

			while (true) {
				this.TUI.printAdminMenu();
				String selected = view.DataInput.sc.nextLine();
				switch (selected) {
				case "1":
					adminController.showComplaintList();
					break;
				case "2":
					adminController.approveComplaint();
					break;

				default:
					break;
				}
			}

		} else {

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
					userApprove();
					break;
				// 납부
				case "4":
					pay();
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
	}

	// 사용자의 승인 요청
	private void userApprove() {
		ArrayList<Complaint> userComplaintList = new ArrayList<Complaint>();
		for (Complaint complaint : complaintList) {
			if (complaint.getComplaintStatus().equals(ComplaintStatus.PENDING_APPROVAL)
					&& complaint.getCoOwner() == loginUser) {
				userComplaintList.add(complaint);
			}
		}

		// print 사용자 승인 요청 목록
		for (Complaint compalint : userComplaintList) {
			TUI.printMessage(compalint.getReceptionNumber() + " " + compalint.toString());
		}

		// 민원 선택
		Complaint selectComplaint = selectComplaint(userComplaintList);
		TUI.printMessage("승인하시겠습니까?");
		TUI.printMessage("[Y/N]");

		String selected = view.DataInput.sc.nextLine();

		if (selected.equals("Y")) {
			selectComplaint.setComplaintStatus(ComplaintStatus.PENDING_REVIEW);
			TUI.printMessage("승인 완료");
		} else {
			TUI.printMessage("승인하지 않았습니다.");
		}

	}

	private void pay() {
		if (complaintList == null) {
			TUI.printMessage("민원 목록이 존재하지 않습니다.");
		} else {
			for (Complaint complaint : complaintList) {
				if (complaint.getRepresentativeOwner() == loginUser)
					TUI.printMessage(complaint.getReceptionNumber() + " " + complaint.toString());
			}
		}
		Complaint selectComplaint = selectComplaint();
		if (selectComplaint == null)
			TUI.printMessage("존재하지 않는 민원입니다.");

		switch (selectComplaint.getServiceName()) {
		case "자동차신규등록신청":
			register(selectComplaint);
			break;
		case "자동차이전등록신청":
			transfer(selectComplaint);
			break;
		case "자동차말소등록신청":
			cancellation(selectComplaint);
			break;

		}
	}



	private void register(Complaint selectComplaint) {
		Car car = carList.findByIdentificationNumber(selectComplaint.getCarID());
		
		if(payForIssuance(car, loginUser, "자동차 신규 등록")) {
			TUI.printMessage("결제에 실패했습니다");
			return;
		}
		
		selectComplaint.setComplaintStatus(ComplaintStatus.COMPLETED); // 민원 처리완료
		car.setCarStatus(CarStatus.REGISTER); // 등록 상태로 변경
		carOwnerList.add(new CarOwner(car, selectComplaint.getRepresentativeOwner(), selectComplaint.getCoOwner()));
		TUI.printMessage("신규 등록 처리 완료");		
	}
	
	private void transfer(Complaint selectComplaint) {
		Car car = carList.findByIdentificationNumber(selectComplaint.getCarID());
		
		if(payForIssuance(car, loginUser, "자동차 이전 등록")) {
			TUI.printMessage("결제에 실패했습니다");
			return;
		}
		
		for(CarOwner carOwner : carOwnerList) {
			if(carOwner.getCar() == car)
			{
				carOwner.setRepresentativeOwner((Customer)loginUser);
				carOwner.setCoOwner(null);
			}
		}
		
		selectComplaint.setComplaintStatus(ComplaintStatus.COMPLETED); // 민원 처리완료
		carOwnerList.add(new CarOwner(car, selectComplaint.getRepresentativeOwner(), selectComplaint.getCoOwner()));
		TUI.printMessage("이전 신청 처리 완료");		
	}
	

	private void cancellation(Complaint selectComplaint) {
		Car car = carList.findByIdentificationNumber(selectComplaint.getCarID());
		
		if(payForIssuance(car, loginUser, "자동차 말소 등록")) {
			TUI.printMessage("결제에 실패했습니다");
			return;
		}
				
		selectComplaint.setComplaintStatus(ComplaintStatus.COMPLETED); // 민원 처리완료
		car.setCarStatus(CarStatus.CANCELLATION); // 등록 상태로 변경
		TUI.printMessage("말소 등록 처리 완료");
	}
	
	private boolean payForIssuance(Car car, User loginUser, String type) {
		TUI.printReceiveMethod(type);
		TUI.printConfirmation();

		String confirmation = view.DataInput.sc.nextLine();
		if (confirmation.equalsIgnoreCase("Y")) {
			TUI.printMessage("민원 신청을 위한 납부로 넘어갑니다.");
			// 납부 로직 시작
			TUI.printMessage("==========납부 절차===========");
			TUI.printOwnerInfo(loginUser);
			TUI.printCarInfo(car);
			TUI.printMessage("===========================");
			TUI.printMessage("납부 금액(" + type + "): 1000");
			TUI.printPaymentOptions();
			int paymentOption = view.DataInput.sc.nextInt();
			view.DataInput.sc.nextLine(); // 개행 문자 처리

			String paymentMethod = "";
			switch (paymentOption) {
			case 1:
				paymentMethod = "카드";
				break;
			case 2:
				paymentMethod = "가상계좌";
				break;
			default:
				TUI.printMessage("잘못된 결제 방법입니다.");
				return false;
			}

			TUI.printPaymentConfirmation();

			String paymentConfirmation = view.DataInput.sc.nextLine();
			if (paymentConfirmation.equals("Y")) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	private Complaint selectComplaint() {
		TUI.printMessage("승인하려는 민원 번호 입력");
		String select = view.DataInput.sc.nextLine();
		Complaint complaint = null;

		for (Complaint c : complaintList) {
			if (c.getReceptionNumber().equals(select)) {
				complaint = c;
			}
		}

		return complaint;
	}

	private Complaint selectComplaint(ArrayList<Complaint> list) {
		TUI.printMessage("승인하려는 민원 번호 입력");
		String select = view.DataInput.sc.nextLine();
		Complaint complaint = null;

		for (Complaint c : list) {
			if (c.getReceptionNumber().equals(select)) {
				complaint = c;
			}
		}

		return complaint;
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
			registerComplaintController.update((Customer) loginUser);
			break;
		// 자동차 양도증명
		case "3":
			registerComplaintController.assignment((Customer) loginUser);
			break;
		// 자동차 이전등록신청
		case "4":
			registerComplaintController.transfer((Customer) loginUser);

			break;
		// 저당권설정등록신청
		case "5":
			// 시간 남으면 진행
			break;
		// 자동차 말소등록신청
		case "6":
			registerComplaintController.Cancellation((Customer) loginUser);
			break;
		// 정기/종합검사 유효기간 연장(유예)신청
		case "7":
			// 시간 남으면 진행
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
		// 자동차 등록원부 등본(초본) 발급 신청
		case "2":
			issuanceComplainController.carRegistration(loginUser);
			break;
		// 자동차 말소사실증명서 발급신청
		case "3":
			issuanceComplainController.carCertificateOfExpungement(loginUser);
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
