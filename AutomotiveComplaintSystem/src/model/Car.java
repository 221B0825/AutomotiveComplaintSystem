package model;

public class Car {
	private String identificationNumber;
	private String name;
	private String color;
	private String modelYear;
	private CarStatus carStatus;

	public Car() {
	}

	public Car(String identificationNumber, String name, String color, String modelYear, CarStatus carStatus) {
		super();
		this.identificationNumber = identificationNumber;
		this.name = name;
		this.color = color;
		this.modelYear = modelYear;
		this.carStatus = carStatus;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getModelYear() {
		return modelYear;
	}

	public void setModelYear(String modelYear) {
		this.modelYear = modelYear;
	}

	public CarStatus getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(CarStatus carStatus) {
		this.carStatus = carStatus;
	}

	@Override
	public String toString() {
		return "============= 차량 정보 =============\n" + "차 명 : " + this.getName() + "  연식 : " + this.getModelYear()
				+ "\n" + "차대번호 : " + this.getIdentificationNumber() + " 색상 : " + this.getColor() + "\n" + "등록 상태 : "
				+ this.getCarStatus();
	}

}
