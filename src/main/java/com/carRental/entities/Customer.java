package com.carRental.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Customer extends Person{
	private String licence_no;
	@OneToMany(mappedBy="customer")
	@JsonIgnore
	List<Booking> records;
	public String getLicence_no() {
		return licence_no;
	}
	public void setLicence_no(String licence_no) {
		this.licence_no = licence_no;
	}
	public List<Booking> getRecords() {
		return records;
	}
	public void setRecords(List<Booking> records) {
		this.records = records;
	}
	@Override
	public String toString() {
		return "Customer [licence_no=" + licence_no + ", records=" + records + "]";
	}
	public Customer(String licence_no, List<Booking> records) {
		super();
		this.licence_no = licence_no;
		this.records = records;
	}
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
