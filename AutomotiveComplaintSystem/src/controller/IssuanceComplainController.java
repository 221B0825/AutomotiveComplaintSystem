package controller;

import java.util.ArrayList;

import model.CarList;
import model.CarOwner;
import model.Customer;
import model.User;
import view.TUI;

public class IssuanceComplainController {

	private TUI TUI;

	private CarList carList;
	private ArrayList<CarOwner> carOwnerList;

	private Customer loginUser;

	public IssuanceComplainController(TUI TUI, CarList carList, ArrayList<CarOwner> carOwnerList) {
		this.TUI = TUI;
		this.carList = carList;
		this.carOwnerList = carOwnerList;
	}

	public void carRegistrationReIssuance(User loginUser) {
		this.loginUser = (Customer) loginUser;

		this.TUI.printSelectCarStatus();

	}

}
