package controller;

import java.util.ArrayList;

import model.CarList;
import model.CarOwner;
import model.Complaint;
import model.UserList;
import view.TUI;

public class AdminController {

	private TUI TUI;
	private UserList userList;
	private CarList carList;
	private ArrayList<CarOwner> carOwnerList;
	private ArrayList<Complaint> complaintList;

	public AdminController(TUI TUI, UserList userList, CarList carList, ArrayList<CarOwner> carOwnerList, ArrayList<Complaint> complaintList) {
		this.TUI = TUI;
		this.carList = carList;
		this.carOwnerList = carOwnerList;
		this.userList = userList;
		this.complaintList = complaintList;
		
	}

	public void showComplaintList() {
		if(complaintList == null) {
			TUI.printMessage("민원 목록이 존재하지 않습니다.");
		}else {
			for(Complaint complaint : complaintList) {
				TUI.printMessage(complaint.toString());
			}
		}
	}

}
