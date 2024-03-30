package com.carRental.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Booking {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "customer_Id",nullable =false)
	private Person customer;

	@OneToOne
	@JoinColumn(name = "car_Id",nullable=false)
	private Car car;
	

	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Booking(int id, Car car, Person customer) {
		super();
		this.id = id;
		this.car = car;
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Record [id=" + id + ", car=" + car + ", customer=" + customer + "]";
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

	public Person getCustomer() {
		return customer;
	}

	public void setCustomer(Person customer) {
		this.customer = customer;
	}

}
