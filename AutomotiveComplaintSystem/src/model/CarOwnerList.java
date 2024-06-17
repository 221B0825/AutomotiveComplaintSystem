package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarOwnerList {

	private List<CarOwner> carOwnerList;

	public CarOwnerList() {
		carOwnerList = new ArrayList<>();
		File file = new File("data/carOwner/carOwner.txt");
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNext()) {
				// add user : read line

				String[] split = sc.nextLine().split("\\|");
				String carID = split[0];
				String representativeOwner = split[1];
				String coOwner = split[2];
				CarOwner carOwner = new CarOwner(carID, representativeOwner, coOwner);
				carOwnerList.add(carOwner);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public List<CarOwner> getCarOwnerList() {
		return carOwnerList;
	}

	public void setCarOwnerList(List<CarOwner> carOwnerList) {
		this.carOwnerList = carOwnerList;
	}

}
