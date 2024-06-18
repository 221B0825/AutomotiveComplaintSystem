package model;

import java.time.LocalDateTime;

public class Complaint {
	private Long receptionNumber;
	private String serviceName;
	private LocalDateTime date;
	private Admin admin;
	// 대표 소유자
	private Customer representativeOwner;
	// 공동 소유자
	private Customer coOwner;
	private ComplaintStatus complaintStatus;
	
	public Complaint() {}
	


	public Complaint(Long receptionNumber, String serviceName, LocalDateTime date, Admin admin,
			Customer representativeOwner, Customer coOwner, ComplaintStatus complaintStatus) {
		super();
		this.receptionNumber = receptionNumber;
		this.serviceName = serviceName;
		this.date = date;
		this.admin = admin;
		this.representativeOwner = representativeOwner;
		this.coOwner = coOwner;
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



	public ComplaintStatus getComplaintStatus() {
		return complaintStatus;
	}

	public void setComplaintStatus(ComplaintStatus complaintStatus) {
		this.complaintStatus = complaintStatus;
	}



	@Override
	public String toString() {
		return "Complaint [receptionNumber=" + receptionNumber + ", serviceName=" + serviceName + ", date=" + date
				+ ", admin=" + admin + ", representativeOwner=" + representativeOwner + ", coOwner=" + coOwner
				+ ", complaintStatus=" + complaintStatus + "]";
	}

	
}
