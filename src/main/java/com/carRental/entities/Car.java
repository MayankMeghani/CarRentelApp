package com.carRental.entities;

import java.time.Year;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Car {

	@Id
	private int id;
	private String model;
	private String brand;
	@Column(columnDefinition = "SMALLINT")
    private Year year;
	private int price;
	@Column(columnDefinition = "SMALLINT")
	private int renting_period;
	boolean available;
	
	@ManyToOne
    @JoinColumn(name = "renter_Id")
	private Renter renter;

	@OneToOne(mappedBy="car")
	@JsonIgnore
	Booking record;
	
	
	
	public Car(int id, String model, String brand, Year year, int price, int renting_period,boolean available, Renter renter,
			Booking record) {
		super();
		this.id = id;
		this.model = model;
		this.brand = brand;
		this.year = year;
		this.price = price;
		this.renting_period = renting_period;
		this.renter = renter;
		this.record = record;
		this.available = true;
	}
	
	public Booking getRecord() {
		return record;
	}
	public void setRecord(Booking record) {
		this.record = record;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Year getYear() {
		return year;
	}
	public void setYear(Year year) {
		this.year = year;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getRenting_period() {
		return renting_period;
	}
	public void setRenting_period(int renting_period) {
		this.renting_period = renting_period;
	}
	public Renter getRenter() {
		return renter;
	}
	public void setRenter(Renter renter) {
		this.renter = renter;
	}
	@Override
	public String toString() {
		return "Car [id=" + id + ", model=" + model + ", brand=" + brand + ", year=" + year + ", price=" + price
				+ ", renting_period=" + renting_period + ", renter=" + renter + ", available=" + available +  "]";
	}
	public Car() {
		super();
	}
	
}
