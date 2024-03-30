package com.carRental.services;

import java.util.List;

import com.carRental.entities.Car;
import com.carRental.entities.Person;
import com.carRental.entities.Role;


public interface PersonService {

	List<Person> findByRole(Role role);

	List<Person> findAll();
	
	Person findById(int theId);

	Person findByUsername(String username);
	
	void save(Person thePerson);
	
	void deleteById(int theId);
	
	Car FindCarByrenter(int renter_id,int car_id);


}
