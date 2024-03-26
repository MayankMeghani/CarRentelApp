package com.carRental.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Role {

	@Id
	private int id;
	
    @Column(name = "role", nullable = false, length = 10)
    private String role;
    
    @JsonIgnore
    @OneToMany(mappedBy="role")
    private List<Person> persons;
    
    
    public enum UserRole {
        USER,
        ADMIN,
        MANAGER
    }
    public void setRole(String role) {
        if (!isValidRole(role)) {
            throw new IllegalArgumentException("Invalid role: " + role);
        }
        this.role = role;
    }

    private boolean isValidRole(String role) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.name().equals(role)) {
                return true;
            }
        }
        return false;
    }

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

	public String getRole() {
		return role;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", role=" + role + ", persons=" + persons + "]";
	}
	public Role() {
		super();
	}
	public Role(int id, String role, List<Person> persons) {
		super();
		this.id = id;
		this.role = role;
		this.persons = persons;
	}

}
