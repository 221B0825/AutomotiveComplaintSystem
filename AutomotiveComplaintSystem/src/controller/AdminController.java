package controller;

import java.util.ArrayList;

import model.CarList;
import model.CarOwner;
import model.CarStatus;
import model.Complaint;
import model.ComplaintStatus;
import model.UserList;
import view.TUI;

public class AdminController {

	private TUI TUI;
	private UserList userList;
	private CarList carList;
	private ArrayList<CarOwner> carOwnerList;
	private ArrayList<Complaint> complaintList;

	public AdminController(TUI TUI, UserList userList, CarList carList, ArrayList<CarOwner> carOwnerList,
			ArrayList<Complaint> complaintList) {
		this.TUI = TUI;
		this.carList = carList;
		this.carOwnerList = carOwnerList;
		this.userList = userList;
		this.complaintList = complaintList;

	}

	public void showComplaintList() {
		if (complaintList == null) {
			TUI.printMessage("민원 목록이 존재하지 않습니다.");
		} else {
			for (Complaint complaint : complaintList) {
				TUI.printMessage(complaint.getReceptionNumber() + " " + complaint.toString());
			}
		}
	}

	public void approveComplaint() {
		showComplaintList();
		Complaint complaint = selectComplaint();
		if (complaint == null) {
			TUI.printMessage("존재하지 않는 민원 번호입니다.");
		} else {
			TUI.printMessage(complaint.toString());
			TUI.printMessage("승인하시겠습니까?");
			TUI.printMessage("[Y/N]");
			String answer = view.DataInput.sc.nextLine();

			if (answer.equals("Y")) {

				switch (complaint.getServiceName()) {
				case "자동차신규등록신청":
					carOwnerList.add(new CarOwner(carList.findByIdentificationNumber(complaint.getCarID()),
							complaint.getRepresentativeOwner(), complaint.getCoOwner()));
					carList.findByIdentificationNumber(complaint.getCarID()).setCarStatus(CarStatus.REGISTER);
					complaint.setComplaintStatus(ComplaintStatus.AWAITING_PAYMENT);
					TUI.printMessage("승인 완료");
					break;

				default:
					break;
				}
			} else if (answer.equals("N")) {
				complaint.setComplaintStatus(ComplaintStatus.REVIEW_REJECTED);
				TUI.printMessage("심사 반려 완료");
			} else {
				TUI.printWrongInputMessage();
			}
		}

	}

	private Complaint selectComplaint() {
		TUI.printMessage("승인하려는 민원 번호 입력");
		String select = view.DataInput.sc.nextLine();
		Complaint complaint = null;

		for (Complaint c : complaintList) {
			if (c.getReceptionNumber() == Long.parseLong(select)) {
				complaint = c;
			}
		}

		return complaint;
	}

}
