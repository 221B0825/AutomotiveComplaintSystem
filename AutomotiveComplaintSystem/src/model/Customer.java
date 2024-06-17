package model;

import java.util.List;

public class Customer extends User {
	List<String> carIDList;


	
	public Customer(Long id, String email, String password, String name, String address, String socialNumber,
			String phoneNumber, List<String> carIDList) {
		super(id, email, password, name, address, socialNumber, phoneNumber);
		this.carIDList = carIDList;
	}
	

}
