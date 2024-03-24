package com.carRental.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Booking {
	@Id
	private int id;

	@ManyToOne
	@JoinColumn(name = "user_Id")
	private User user;

	@OneToOne	
	@JoinColumn(name = "car_Id")
	private Car car;
	

	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Booking(int id, Car car, User user) {
		super();
		this.id = id;
		this.car = car;
		this.user = user;
	}

	@Override
	public String toString() {
		return "Record [id=" + id + ", car=" + car + ", user=" + user + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
