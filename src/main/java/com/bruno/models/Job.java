package com.bruno.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "JOB")
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "CUSTOMER_NAME", nullable = false)
	private String customerName;
	
	@Column(name = "ADDRESS", nullable = false)
	private String address;
	@Column(name = "EMAIL", nullable = false)
	private String email;
	@Column(name="PHONE", nullable = false)
	private String phone;
	@Column(name = "SCHEDULED_DATE_TIME", nullable = false)
	private Timestamp dateTime;
	@Column(name = "DESCRIPTION",  nullable = false)
	private String description;
	
	public Job() {
		this.id = 0L;
	}
	
	public Job(Long id, String customerName, String address, String email, String phone, 
			Timestamp date, String description) {
		this.id = id;
		this.customerName = customerName;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.dateTime = date;
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

	public Timestamp getDate() {
		return dateTime;
	}

	public void setDate(Timestamp date) {
		this.dateTime = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	
}
