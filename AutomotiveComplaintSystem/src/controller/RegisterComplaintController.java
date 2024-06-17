package controller;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.Admin;
import model.Car;
import model.CarList;
import model.CarOwner;
import model.Complaint;
import model.ComplaintStatus;
import model.Customer;
import model.User;
import model.UserList;
import view.TUI;

public class RegisterComplaintController {

	private TUI TUI;
	private CarList carList;
	private UserList userList;
	private ArrayList<Complaint> complaintList;
	private ArrayList<CarOwner> carOwnerList;
	private Long complaintNumber;

	public RegisterComplaintController(TUI TUI, CarList carList, UserList userList, ArrayList<Complaint> complaintList,
			ArrayList<CarOwner> carOwnerList) {
		this.TUI = TUI;
		this.carList = carList;
		this.userList = userList;
		this.complaintList = complaintList;
		this.carOwnerList = carOwnerList;
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

		complaintList.add(new Complaint(complaintNumber++, "자동차신규등록신청", LocalDateTime.now(),
				(Admin) userList.findByEmail("admin"), (Customer) loginUser, ComplaintStatus.PENDING_REVIEW));

	}

	public void update(Customer loginUser) {
		List<Car> userCarList = new ArrayList<Car>();
		for(CarOwner carOwner : carOwnerList) {
			if(	carOwner.getRepresentativeOwner() == loginUser )
				userCarList.add(carOwner.getCar());

		}

		for (Car car : userCarList) {
			System.out.println(car.toString());
		}

		TUI.printSelectCarUpdateMessage(); //

		String identificationNumber = view.DataInput.sc.nextLine();

		Car car = carList.findByIdentificationNumber(identificationNumber);

		if (car == null) { // 존재하지 않는 차량
			TUI.printNotFoundCarMessage();
			return;
		}

		// 소유인 정보 변경

		// 공동소유자 변경

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
