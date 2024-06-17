package model;

import java.time.LocalDateTime;

public class Complaint {
	private Long receptionNumber;
	private String serviceName;
	private LocalDateTime date;
	private Admin admin;
	private Customer customer;
	private ComplaintStatus complaintStatus;
	
	public Complaint() {}
	
	public Complaint(Long receptionNumber, String serviceName, LocalDateTime date, Admin admin, Customer customer,
			ComplaintStatus complaintStatus) {
		super();
		this.receptionNumber = receptionNumber;
		this.serviceName = serviceName;
		this.date = date;
		this.admin = admin;
		this.customer = customer;
		this.complaintStatus = complaintStatus;
	}

	public Long getReceptionNumber() {
		return receptionNumber;
	}

	public void setReceptionNumber(Long receptionNumber) {
		this.receptionNumber = receptionNumber;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ComplaintStatus getComplaintStatus() {
		return complaintStatus;
	}

	public void setComplaintStatus(ComplaintStatus complaintStatus) {
		this.complaintStatus = complaintStatus;
	}
	
	
}
