package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarList {
	
	private List<Car> carList;
	
	public CarList() {
		carList = new ArrayList<>();
		File file = new File("data/car/car.txt");
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNext()) {
				// add user : read line

				
				String[] split = sc.nextLine().split("\\|");
				String identificationNumber = split[0];
				String name = split[1];
				String color = split[2];
				String modelYear = split[3];
				Car car = new Car(identificationNumber, name, color, modelYear, CarStatus.DEFAULT);
				carList.add(car);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public List<Car> getCarList() {
		return carList;
	}

	public Car findByIdentificationNumber(String identificationNumber) {
		for(Car car : carList) {
			if(car.getIdentificationNumber().equals(identificationNumber))
				return car;
		}
		return null;
		
	}

}
