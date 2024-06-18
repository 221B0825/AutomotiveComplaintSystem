package model;

public class AssignmentCetification {

	private Car car;
	// 양도인
	private Customer transferor;
	// 양수인
	private Customer transferee;

	public AssignmentCetification(Car car, Customer transferor, Customer transferee) {
		super();
		this.car = car;
		this.transferor = transferor;
		this.transferee = transferee;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Customer getTransferor() {
		return transferor;
	}

	public void setTransferor(Customer transferor) {
		this.transferor = transferor;
	}

	public Customer getTransferee() {
		return transferee;
	}

	public void setTransferee(Customer transferee) {
		this.transferee = transferee;
	}

}
