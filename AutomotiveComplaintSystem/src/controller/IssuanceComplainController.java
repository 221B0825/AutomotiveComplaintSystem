package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Car;
import model.CarList;
import model.CarOwner;
import model.CarStatus;
import model.Customer;
import model.User;
import model.UserList;
import view.TUI;

public class IssuanceComplainController {

	private TUI TUI;

	private CarList carList;
	private ArrayList<CarOwner> carOwnerList;
	private UserList userList;

	private Customer loginUser;

	public IssuanceComplainController(TUI TUI, CarList carList, ArrayList<CarOwner> carOwnerList, UserList userList) {
		this.TUI = TUI;
		this.carList = carList;
		this.carOwnerList = carOwnerList;
		this.userList = userList;
	}

	public void carRegistrationReIssuance(User loginUser) {
		
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
				Customer ROwner = (Customer)userList.findByEmail(representativeOwner);
				Customer COwner = (Customer)userList.findByEmail(coOwner);
				
				CarOwner carOwner = new CarOwner(car,ROwner, COwner);
				carOwnerList.add(carOwner);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		// 자신의 차 가져오기
		List<Car> userCarList = new ArrayList<Car>();
		for (CarOwner carOwner : carOwnerList) {
			if (carOwner.getRepresentativeOwner().getEmail().equals(loginUser.getEmail()))
				userCarList.add(carOwner.getCar());
		}

		// 차 목록 출력
		for (Car car : userCarList) {
			System.out.println(car.toString());
		}
		// 차량 번호로 차량 선택
		TUI.printSelectCarMessage();
		String identificationNumber = view.DataInput.sc.nextLine();
		Car car = carList.findByIdentificationNumber(identificationNumber);

		if (car == null) { // 존재하지 않는 차량
			TUI.printNotFoundCarMessage();
			return;
		}
		TUI.printReissueReasons();
		int reasonIndex = view.DataInput.sc.nextInt();
		view.DataInput.sc.nextLine(); // 개행 문자 처리
		String reason = "";
		switch (reasonIndex) {
		case 1:
			reason = "분실";
			break;
		case 2:
			reason = "훼손";
			break;
		case 3:
			reason = "도난";
			break;
		case 4:
			reason = "기타";
			break;

		default:
			break;
		}

		TUI.printReceiveMethod();
		TUI.printConfirmation();

		String confirmation = view.DataInput.sc.nextLine();
		if (confirmation.equalsIgnoreCase("Y")) {
			TUI.printMessage("자동차 등록증 재발급 신청을 위한 납부로 넘어갑니다.");
			// 납부 로직 시작
			TUI.printOwnerInfo(loginUser);
			TUI.printCarInfo(car);
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
				return;
			}

			TUI.printPaymentConfirmation();

			String paymentConfirmation = view.DataInput.sc.nextLine();
			if (paymentConfirmation.equalsIgnoreCase("Y")) {
				TUI.printMessage("결제가 완료되었습니다. 자동차 등록증 재발급 신청이 완료되었습니다.");
				TUI.printCarRegistrationCertificate(loginUser, car);
			} else {
				TUI.printMessage("자동차 등록증 재발급 신청이 취소되었습니다.");
				return;
			}

		} else {
			TUI.printMessage("자동차 등록증 재발급 신청이 취소되었습니다.");
			return;
		}

	}

}
