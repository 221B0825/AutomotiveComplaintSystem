package controller;

import java.util.ArrayList;
import java.util.List;

import model.Car;
import model.CarList;
import model.CarOwner;
import model.Customer;
import model.User;
import model.UserList;
import view.TUI;

public class IssuanceComplainController {

	private TUI TUI;

	private CarList carList;
	private ArrayList<CarOwner> carOwnerList;
	private UserList userList;

	public IssuanceComplainController(TUI TUI, CarList carList, ArrayList<CarOwner> carOwnerList, UserList userList) {
		this.TUI = TUI;
		this.carList = carList;
		this.carOwnerList = carOwnerList;
		this.userList = userList;
	}

	private Car findOwnCarAndSelect(User loginUser) {
		// 자신의 차 가져오기
		List<Car> userCarList = new ArrayList<Car>();
		for (CarOwner carOwner : carOwnerList) {
			if (carOwner.getRepresentativeOwner().getEmail().equals(loginUser.getEmail()))
				userCarList.add(carOwner.getCar());
		}

		// 소유한 차량이 존재하지 않을 때
		if (userCarList.size() == 0) {
			TUI.printMessage("소유한 차량이 존재하지 않습니다.");
			return null;
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
			return null;
		}
		return car;
	}

	// 납부 방법 선택하기
	private boolean payForIssuance(Car car, User loginUser, String type) {
		TUI.printReceiveMethod(type);
		TUI.printConfirmation();

		String confirmation = view.DataInput.sc.nextLine();
		if (confirmation.equalsIgnoreCase("Y")) {
			TUI.printMessage("민원 신청을 위한 납부로 넘어갑니다.");
			// 납부 로직 시작
			TUI.printMessage("=====납부 절차======");
			TUI.printOwnerInfo(loginUser);
			TUI.printCarInfo(car);
			TUI.printMessage("=================");
			TUI.printMessage("납부 금액("+type+"): 1000");
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
	public void carRegistrationReIssuance(User loginUser) {

		// 유저에 맞는 차량 목록에서 차량 선택
		Car car = findOwnCarAndSelect(loginUser);
		if (car == null) {
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
		String type = "자동차 등록증";

		if (payForIssuance(car, loginUser, type)) {
			TUI.printMessage("결제가 완료되었습니다. 자동차 등록증 재발급 신청이 완료되었습니다.");
			TUI.printCarRegistrationCertificate(loginUser, car);
		} else {
			TUI.printMessage("자동차 등록증 재발급 신청이 취소되었습니다.");
			return;
		}

	}


	// 자동차 등록원부
	public void carRegistration(User loginUser) {
		Car car = findOwnCarAndSelect(loginUser);
		if (car == null) {
			return;
		}

	}

}
