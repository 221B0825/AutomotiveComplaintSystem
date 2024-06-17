package model;

public class CarOwner {
	
	private String carID;
	// 대표 소유자
	private String RepresentativeOwner;
	// 공동 소유자
	private String coOwner;
	
	public CarOwner() {
		// TODO Auto-generated constructor stub
	}

	public CarOwner(String carID, String representativeOwner, String coOwner) {
		super();
		this.carID = carID;
		RepresentativeOwner = representativeOwner;
		this.coOwner = coOwner;
	}



	public String getCarID() {
		return carID;
	}

	public void setCarID(String carID) {
		this.carID = carID;
	}

	public String getRepresentativeOwner() {
		return RepresentativeOwner;
	}

	public void setRepresentativeOwner(String representativeOwner) {
		RepresentativeOwner = representativeOwner;
	}

	public String getCoOwner() {
		return coOwner;
	}

	public void setCoOwner(String coOwner) {
		this.coOwner = coOwner;
	}
	
	

}
