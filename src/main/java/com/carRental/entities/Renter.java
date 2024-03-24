package com.carRental.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Renter {

	@Id
	private int id;
	private String name;
	private String email;
	@OneToMany(mappedBy = "renter")
//	@JsonBackReference
	private List<Car> cars;
	
	public Renter(int id, String name, String email, List<Car> cars) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.cars = cars;
	}
	public Renter() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Renter [id=" + id + ", name=" + name + ", email=" + email + ", cars=" + cars + "]";
	}

	public List<Car> getCars() {
		return cars;
	}
	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
}
