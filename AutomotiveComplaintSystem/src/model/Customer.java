package model;

import java.util.List;

public class Customer extends User {
	
	public Customer(Long id, String email, String password, String name, String address, String socialNumber,
			String phoneNumber) {
		super(id, email, password, name, address, socialNumber, phoneNumber);
	}
}
