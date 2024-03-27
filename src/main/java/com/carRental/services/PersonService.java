package com.carRental.services;

import java.util.List;

import com.carRental.entities.Car;
import com.carRental.entities.Person;


public interface PersonService {


	List<Person> findAll();
	
	Person findById(int theId);
	
	void save(Person thePerson);
	
	void deleteById(int theId);
	
	Car FindCarByrenter(int renter_id,int car_id);


}
