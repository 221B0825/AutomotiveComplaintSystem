package controller;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.Admin;
import model.AssignmentCetification;
import model.Car;
import model.CarList;
import model.CarOwner;
import model.Complaint;
import model.ComplaintStatus;
import model.Customer;
import model.User;
import model.UserList;
import view.DataInput;
import view.TUI;

public class RegisterComplaintController {

	private TUI TUI;
	private CarList carList;
	private UserList userList;
	private ArrayList<Complaint> complaintList;
	private ArrayList<CarOwner> carOwnerList;
	private ArrayList<AssignmentCetification> assignmentCetificationList;
	private Long complaintNumber;

	public RegisterComplaintController(TUI TUI, CarList carList, UserList userList, ArrayList<Complaint> complaintList,
			ArrayList<CarOwner> carOwnerList, ArrayList<AssignmentCetification> assignmentCetificationList) {
		this.TUI = TUI;
		this.carList = carList;
		this.userList = userList;
		this.complaintList = complaintList;
		this.carOwnerList = carOwnerList;
		this.assignmentCetificationList = assignmentCetificationList;
	}

	public void register(User loginUser) {
		TUI.printSelectCarRegisterMessage();
		String identificationNumber = view.DataInput.sc.nextLine();

		Car car = carList.findByIdentificationNumber(identificationNumber);

		if (car == null) { // 존재하지 않는 차량
			TUI.printNotFoundCarMessage();
			return;
		}
		System.out.println(car.toString()); // 차량 정보 표기
		TUI.printCheckCarInformationMessage();

		if (confirmChoice() == false) // 자신의 차가 아님
			return;

		if (loginUser == null) { // 존재하지 않는 유저
			TUI.printNotFoundUserMessage();
			return;
		}

		System.out.println(loginUser.toString());
		TUI.printCheckUserInformationMessage(); // 유저 정보 표기

		if (confirmChoice() == false) // 자신의 정보와 일치하지 않음
			return;

		if (confirmRegistrationDocument(loginUser) == false) { // 문서 확인
			return;
		}

		TUI.printMessage("공동소유자 등록");
		TUI.printMessage("공동소유자로 등록할 고객의 주민번호를 입력해주세요 : ");
		String socialNumber = DataInput.sc.nextLine();

		User coOwner = userList.findBySocialNumber(socialNumber);

		complaintList.add(new Complaint(complaintNumber++, "자동차신규등록신청", LocalDateTime.now(),
				(Admin) userList.findByEmail("admin"), (Customer) loginUser, (Customer) coOwner,
				ComplaintStatus.PENDING_REVIEW));

	}

	public void update(Customer loginUser) {
		TUI.printMessage("자동차 소유인 정보 변경을 시작합니다.");

		printCarList(loginUser); // 차량 목록 출력
		Car selectedCar = selectCar();

		if (selectedCar == null) { // 존재하지 않는 차량
			TUI.printNotFoundCarMessage();
			return;
		}

		Customer representativeOwner = null;
		Customer coOwner = null;

		for (CarOwner carOwner : carOwnerList) { // 차의 대표소유자와 공동소유자 조회
			if (carOwner.getCar() == selectedCar) {
				representativeOwner = carOwner.getRepresentativeOwner();
				coOwner = carOwner.getCoOwner();
				TUI.printRepresentativeOwner(representativeOwner);
				TUI.printCoOwnerInformation(coOwner);

			}
		}

		// 소유인 정보 변경
		while (true) {
			TUI.printOwnerInfoChangeAskMessage();

			if (confirmChoice()) {
				TUI.printChangeOwnerSelectMessage();

				int option = Integer.parseInt(DataInput.sc.nextLine());
				switch (option) {
				case 1:
					updateInformation(representativeOwner);

					break;
				case 2:
					updateInformation(coOwner);
					break;
				}
			} else
				break;
		}

		// 공동소유자 변경
		TUI.printChangeRepresentativeOwnerSelectMessage();
		if (confirmChoice()) {
			if (representativeOwner != loginUser) {
				TUI.printPrimaryOwnerRestrictionMessage();
				return;
			}
			Customer temp = representativeOwner;
			representativeOwner = coOwner;
			coOwner = temp;
		}

	}

	public void assignment(Customer loginUser) {
		TUI.printMessage("양도증명 신청을 시작합니다.");

		printCarList(loginUser);
		Car selectedCar = selectCar();

		if (selectedCar == null) { // 존재하지 않는 차량
			TUI.printNotFoundCarMessage();
			return;
		}

		Customer representativeOwner = null;
		Customer coOwner = null;

		for (CarOwner carOwner : carOwnerList) { // 차의 대표소유자와 공동소유자 조회
			if (carOwner.getCar() == selectedCar) {
				representativeOwner = carOwner.getRepresentativeOwner();
				TUI.printMessage("양도인 정보");
				TUI.printMessage(representativeOwner.toString());
			}
		}

		// 양수인 설정
		TUI.printMessage("양수인 입력");
		TUI.printMessage("양수인으로 등록할 고객의 주민번호를 입력해주세요 : ");
		String socialNumber = DataInput.sc.nextLine();

		User transferee = userList.findBySocialNumber(socialNumber);

		if (transferee == null) {
			System.out.println("존재하지 않는 고객입니다.");
			return;
		}
		TUI.printMessage("양도증명 신청하시겠습니까? > ");
		
		if (confirmChoice() == false) // 
			return;
		
		// 양도증명 등록
		assignmentCetificationList.add(new AssignmentCetification(selectedCar, representativeOwner, (Customer)transferee));
		
		
	}

	private Car selectCar() {
		TUI.printSelectCarUpdateMessage();
		String identificationNumber = view.DataInput.sc.nextLine();
		return carList.findByIdentificationNumber(identificationNumber);
	}

	private void printCarList(Customer loginUser) {
		TUI.printMessage("소유 차량 목록");
		List<Car> userCarList = new ArrayList<Car>();
		for (CarOwner carOwner : carOwnerList) {
			if (carOwner.getRepresentativeOwner() == loginUser)
				userCarList.add(carOwner.getCar());

		}

		for (Car car : userCarList) {
			System.out.println(car.toString());
		}
	}

	private void updateInformation(Customer customer) {
		TUI.printEmailInputMessage();

		String email1 = DataInput.sc.nextLine();
		TUI.printAddressInputMessage();

		String address1 = DataInput.sc.nextLine();

		customer.setEmail(email1);
		customer.setAddress(address1);

	}

	private boolean confirmChoice() {
		TUI.printConfirmChoiceMessage();
		String inputChoice = view.DataInput.sc.nextLine();
		if (inputChoice.equalsIgnoreCase("YES"))
			return true;
		return false;
	}

	private boolean confirmRegistrationDocument(User loginUser) {
		for (String fileName : List.of("/주민등록등본", "/자동차제작증", "/의무보험 가입증명서")) {
			if (!isExistsDocument(new File("data/file/user" + loginUser.getId() + fileName))) {
				TUI.printMissingDocument(fileName);
				return false;
			}
		}

		return true;
	}

	private boolean isExistsDocument(File file) {
		if (file.exists())
			return true;
		return false;
	}

}
