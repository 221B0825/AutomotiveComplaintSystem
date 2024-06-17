package model;

public class User {

	Long id;
	String email;
	String password;
	String name;
	String address;
	String socialNumber;
	String phoneNumber;

	
	public User() {}


	public User(Long id, String email, String password, String name, String address, String socialNumber,
			String phoneNumber) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.address = address;
		this.socialNumber = socialNumber;
		this.phoneNumber = phoneNumber;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getSocialNumber() {
		return socialNumber;
	}


	public void setSocialNumber(String socialNumber) {
		this.socialNumber = socialNumber;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	


	

	

}
