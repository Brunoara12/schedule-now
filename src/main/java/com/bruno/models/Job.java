package com.bruno.models;

public class Job {

	private Long id;
	private String customerName;
	private String address;
	private String email;
	private String phone;
	private String date;
	private String description;
	
	public Job() {
		this.id = 0L;
	}
	
	public Job(Long id, String customerName, String address, String email, String phone, 
			String date, String description) {
		this.id = id;
		this.customerName = customerName;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.date = date;
		this.description = description;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	
}
