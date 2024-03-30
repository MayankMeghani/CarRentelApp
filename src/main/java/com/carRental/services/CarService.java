package com.carRental.services;

import java.util.List;

import com.carRental.entities.Car;
import com.carRental.entities.Person;

public interface CarService {
	List<Car> findAll();
	
	Car findById(int theId);
	
	List<Car> findByRenter(Person theRenter);
	
	void save(Car theRenter);
	
	void deleteById(int theId);
	
}
