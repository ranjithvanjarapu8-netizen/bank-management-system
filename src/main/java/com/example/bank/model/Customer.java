package com.example.bank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customer_id;
	private String name;
	private String email;
	private String mobile;
	private String address;
	public Customer() {
		
	}
	public Customer(Integer customer_id, String name, String email, String mobile, String address) {
		super();
		this.customer_id = customer_id;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
	}
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "customer [customer_id=" + customer_id + ", name=" + name + ", email=" + email + ", mobile=" + mobile
				+ ", address=" + address + "]";
	}
	
}
