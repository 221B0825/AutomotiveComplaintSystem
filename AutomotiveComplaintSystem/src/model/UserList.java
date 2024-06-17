package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserList {

	private ArrayList<User> userList = new ArrayList<User>();

	public UserList() {
		File file = new File("data/user/user.txt");
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNext()) {
				// add user : read line
				String id = sc.next();
				String password = sc.next();
				String name = sc.next();

				User user = new User(id, password, name);
				userList.add(user);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public ArrayList<User> getUserList() {
		return userList;
	}

}
