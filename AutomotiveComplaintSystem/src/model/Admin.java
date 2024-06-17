package model;

import java.util.List;

public class Admin extends User{
	private String department;

	
	public Admin(Long id, String email, String password, String name, String address, String socialNumber,
			String phoneNumber, String department) {
		super(id, email, password, name, address, socialNumber, phoneNumber);
		this.department = department;
	}
	
	
}
