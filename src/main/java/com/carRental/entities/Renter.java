package com.carRental.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Renter extends Person {

	private String rto_registraion_no;
	
	@OneToMany(mappedBy = "renter")
	@JsonIgnore
	private List<Car> cars;
	
	
	public Renter(String rto_registraion_no, List<Car> cars) {
		super();
		this.rto_registraion_no = rto_registraion_no;
		this.cars = cars;
	}


	public String getRto_registraion_no() {
		return rto_registraion_no;
	}


	public void setRto_registraion_no(String rto_registraion_no) {
		this.rto_registraion_no = rto_registraion_no;
	}


	public List<Car> getCars() {
		return cars;
	}


	public void setCars(List<Car> cars) {
		this.cars = cars;
	}


	public Renter() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
