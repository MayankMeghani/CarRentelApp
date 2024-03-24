package com.carRental.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {
	@Id
	private int id;
	private String name;
	private String email;
	
	@OneToMany(mappedBy="user")
//	@JsonBackReference
	List<Booking> records;
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public User(int id, String name, String email, List<Booking> records) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.records = records;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", records=" + records + "]";
	}

	public List<Booking> getRecords() {
		return records;
	}
	public void setRecords(List<Booking> records) {
		this.records = records;
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
