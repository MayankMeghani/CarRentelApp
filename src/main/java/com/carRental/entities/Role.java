package com.carRental.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
    @Column(name = "role", nullable = false)
    private String name;
    
    @JsonIgnore
    @OneToMany(mappedBy="role",cascade=CascadeType.REMOVE)
    private List<Person> persons;
    

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String role) {
		this.name = role;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", role=" + name + ", persons=" + persons + "]";
	}

	public Role() {
		super();
	}
	public Role(int id, String role, List<Person> persons) {
		super();
		this.id = id;
		this.name = role;
		this.persons = persons;
	}
	public Role(int id, String role) {
		super();
		this.id = id;
		this.name = role;
		this.persons = null;
	}

}
