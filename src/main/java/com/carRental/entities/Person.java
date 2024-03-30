package com.carRental.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Person {


		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    @Column(name = "first_name", nullable = false)
	    private String firstName;

	    @Column(name = "last_name", nullable = false)
	    private String lastName;

	    @Column(name = "username", nullable = false, unique = true)
	    private String username;

	    @Column(name = "email", nullable = false, unique = true)
	    private String email;

	    @Column(name = "password", nullable = false, length = 255)
	    private String password;

	    @Column(name = "phone_no", nullable = false, unique = true, length = 10)
	    private String phoneNo;

	    @JsonIgnore
	    @ManyToOne
	    @JoinColumn(name="Role", nullable = false)
	    private Role role;
	    

		@OneToMany(mappedBy = "renter", cascade=CascadeType.REMOVE)
		@JsonIgnore
		private List<Car> cars;
		

		@OneToMany(mappedBy="customer", cascade=CascadeType.REMOVE)
		@JsonIgnore
		List<Booking> records;
	    
	    

		public List<Booking> getRecords() {
			return records;
		}

		public List<Car> getCars() {
			return cars;
		}

		public void setCars(List<Car> cars) {
			this.cars = cars;
		}

		public void setRecords(List<Booking> records) {
			this.records = records;
		}

		public Role getRole() {
			return role;
		}

		public void setRole(Role role) {
			this.role=role;
		}

		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getPhoneNo() {
			return phoneNo;
		}

		public void setPhoneNo(String phoneNo) {
			this.phoneNo = phoneNo;
		}

		public Person(int id, String firstName, String lastName, String username, String email, String password,
				String phoneNo, Role role, List<Car> cars, List<Booking> records) {
			super();
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
			this.username = username;
			this.email = email;
			this.password = password;
			this.phoneNo = phoneNo;
			this.role = role;
			this.cars = cars;
			this.records = records;
		}

		public Person() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Person(int id) {
			super();
			this.id = id;
		}
	    @Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", email=" + email + ", password=" + password + ", phoneNo=" + phoneNo + ", role=" + role + ", cars="
				+ cars + ", records=" + records + "]";
	}

		
}
