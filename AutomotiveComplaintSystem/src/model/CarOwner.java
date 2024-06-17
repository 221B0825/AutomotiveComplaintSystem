package model;

public class CarOwner {
	
	private Car car;
	// 대표 소유자
	private Customer representativeOwner;
	// 공동 소유자
	private Customer coOwner;
	
	public CarOwner() {
		// TODO Auto-generated constructor stub
	}

	public CarOwner(Car car, Customer representativeOwner, Customer coOwner) {
		super();
		this.car = car;
		this.representativeOwner = representativeOwner;
		this.coOwner = coOwner;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Customer getRepresentativeOwner() {
		return representativeOwner;
	}

	public void setRepresentativeOwner(Customer representativeOwner) {
		this.representativeOwner = representativeOwner;
	}

	public Customer getCoOwner() {
		return coOwner;
	}

	public void setCoOwner(Customer coOwner) {
		this.coOwner = coOwner;
	}

	

}
