package com.carRental.entities;

import java.time.Year;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
	    uniqueConstraints = {
	        @UniqueConstraint(name = "unique", columnNames = {"model","brand"})
	    })
public class Car {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable =false)
	private String model;
	@Column(nullable =false)
	private String brand;
	@Column(columnDefinition = "SMALLINT", nullable=false)
    private Year year;
	private int price;
	@Column(columnDefinition = "SMALLINT",nullable=false)
	private int renting_period;
	boolean available;
	

    @JsonIgnore
	@ManyToOne
    @JoinColumn(name = "renter_Id")
	private Person renter;

	@OneToOne(mappedBy="car")
	@JsonIgnore
	Booking record;
	
	
	
	public Person getRenter() {
		return renter;
	}
	public void setRenter(Person renter) {
		this.renter = renter;
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
	
	@Override
	public String toString() {
		return "Car [id=" + id + ", model=" + model + ", brand=" + brand + ", year=" + year + ", price=" + price
				+ ", renting_period=" + renting_period + ", available=" + available + ", renter=" + renter + ", record="
				+ record + "]";
	}
	public Car() {
		super();
	}
	public Car(int id, String model, String brand, Year year, int price, int renting_period, boolean available,
			Person renter, Booking record) {
		super();
		this.id = id;
		this.model = model;
		this.brand = brand;
		this.year = year;
		this.price = price;
		this.renting_period = renting_period;
		this.available = available;
		this.renter = renter;
		this.record = record;
	}
	
}
