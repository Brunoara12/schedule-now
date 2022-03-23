package com.bruno.provider.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "JOB")
@Getter
@Setter
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "CUSTOMER_NAME", nullable = false)
	private String customerName;
	
	@Column(name = "ADDRESS", nullable = false)
	private String address;
	@Column(name = "EMAIL")
	private String email;
	@Column(name="PHONE", nullable = false)
	private String phone;
	@Column(name = "DATE_TIME", nullable = false)
	private Date dateTime;
	@Column(name = "DESCRIPTION",  nullable = false)
	private String description;
	@Override
	public String toString() {
		return "Job [id=" + id + ", customerName=" + customerName + ", address=" + address + ", email=" + email
				+ ", phone=" + phone + ", dateTime=" + dateTime + ", Date Type: " + dateTime + ", description=" + description + "]";
	}
	
	
}
