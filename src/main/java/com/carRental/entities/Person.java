package com.carRental.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class Person {
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private int id;
	    @Column(name = "first_name", nullable = false, length = 50)
	    private String firstName;

	    @Column(name = "last_name", nullable = false, length = 50)
	    private String lastName;

	    @Column(name = "username", nullable = false, unique = true, length = 50)
	    private String username;

	    @Column(name = "email", nullable = false, unique = true, length = 50)
	    private String email;

	    @Column(name = "password", nullable = false, length = 255)
	    private String password;

	    @Column(name = "phone_no", nullable = false, unique = true, length = 10)
	    private String phoneNo;
	    
	    @ManyToOne
	    @JoinColumn(name="Role", nullable = false)
	    private Role role;
	    
	    @Override
	    public String toString() {
	        return "User{" +
	                "id=" + id +
	                ", firstName='" + firstName + '\'' +
	                ", lastName='" + lastName + '\'' +
	                ", userName='" + username + '\'' +
	                ", email='" + email + '\'' +
	                ", password='" + password + '\'' +
	                ", phoneNo='" + phoneNo + '\'' +
	                ", role='" + role + '\'' +
	                '}';
	    }

		public Role getRole() {
			// TODO Auto-generated method stub
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

}
