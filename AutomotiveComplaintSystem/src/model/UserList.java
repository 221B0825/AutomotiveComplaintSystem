package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserList {

	private ArrayList<User> userList = new ArrayList<User>();

	public UserList() {
		File file = new File("data/user/user.txt");
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNext()) {
				// add user : read line

				String[] split = sc.nextLine().split("\\|");
				Long id = Long.parseLong(split[0]);
				String email = split[1];
				String password = split[2];
				String name = split[3];
				String address = split[4];
				String socialNumber = split[5];
				String phoneNumber = split[6];

				User user;
				if(email.equals("admin"))
					user = new Admin(id, email, password, name, address, socialNumber, phoneNumber, "국토교통부");
				else
					user = new Customer(id, email, password, name, address, socialNumber, phoneNumber);
				userList.add(user);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public ArrayList<User> getUserList() {

		return userList;
	}


	public User findByEmail(String email) {
		for(User user : userList) {
			if(user.getEmail().equals(email))
				return user;
		}
		return null;
	}

}
