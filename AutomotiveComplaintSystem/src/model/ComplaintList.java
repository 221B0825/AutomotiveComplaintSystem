package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class ComplaintList {

	private List<Complaint> complaintList;

	public ComplaintList() {
		File file = new File("data/user/user.txt");
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNext()) {
				// add user : read line

				String[] split = sc.nextLine().split("\\|");
				Long id = Long.parseLong(split[0]);
				String receptionNumber = split[1];
				String serviceName = split[2];
				String date = split[3];
				String admin = split[4];
				String customer = split[5];
				String complaintStatus = split[6];
				Complaint complaint = new Complaint();
				complaintList.add(complaint);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public List<Complaint> getComplaintList() {
		return complaintList;
	}

}
