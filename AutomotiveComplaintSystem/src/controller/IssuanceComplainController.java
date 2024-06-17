package controller;

import model.Car;
import model.CarList;
import model.CarOwnerList;
import model.Customer;
import model.User;
import view.TUI;

public class IssuanceComplainController {

	private TUI TUI;

	private CarList carList;
	private CarOwnerList carOwnerList;

	private Customer loginUser;

	public IssuanceComplainController(TUI TUI, CarList carList, CarOwnerList carOwnerList) {
		this.TUI = TUI;
		this.carList = carList;
		this.carOwnerList = carOwnerList;
	}

	public void carRegistrationReIssuance(User loginUser) {
		this.loginUser = (Customer) loginUser;

		this.TUI.printSelectCarStatus();

	}

}
