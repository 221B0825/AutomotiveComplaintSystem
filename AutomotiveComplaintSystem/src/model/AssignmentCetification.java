package model;

public class AssignmentCetification {

	private Car car;
	// 양도인
	private Customer transferor;
	// 양수인
	private Customer transferee;

	private long salePrice;
	private String saleDate;
	private String carDeliveryDate;

	public AssignmentCetification(Car car, Customer transferor, Customer transferee, long salePrice, String saleDate,
			String carDeliveryDate) {
		super();
		this.car = car;
		this.transferor = transferor;
		this.transferee = transferee;
		this.salePrice = salePrice;
		this.saleDate = saleDate;
		this.carDeliveryDate = carDeliveryDate;
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

	public long getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(long salePrice) {
		this.salePrice = salePrice;
	}

	public String getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}

	public String getCarDeliveryDate() {
		return carDeliveryDate;
	}

	public void setCarDeliveryDate(String carDeliveryDate) {
		this.carDeliveryDate = carDeliveryDate;
	}

}
