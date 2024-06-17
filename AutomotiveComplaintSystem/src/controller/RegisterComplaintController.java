package controller;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.Admin;
import model.Car;
import model.CarList;
import model.Complaint;
import model.ComplaintList;
import model.ComplaintStatus;
import model.Customer;
import model.User;
import model.UserList;
import view.TUI;

public class RegisterComplaintController {

	private TUI TUI;
	private CarList carList;
	private UserList userList;
	private ComplaintList complaintList;
	private Long complaintNumber;

	public RegisterComplaintController(TUI TUI, CarList carList, UserList userList, ComplaintList complaintList) {
		this.TUI = TUI;
		this.carList = carList;
		this.userList = userList;
		this.complaintList = complaintList;
	}

	public void register(User loginUser) {
		TUI.printInputIdentificationNumber();
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

		if (confirmRegistrationDocument(loginUser) == false) {
			return;
		}

		new Complaint(complaintNumber++, "자동차신규등록신청", LocalDateTime.now(), new Admin(""), (Customer) loginUser,
				ComplaintStatus.PENDING_REVIEW);

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
			if (isExistsDocument(new File("data/file/user" + loginUser.getId() + fileName)))
				return false;
		}

		return true;
	}

	private boolean isExistsDocument(File file) {
		if (file.exists())
			return true;
		return false;
	}

}
