package com.carRental.services;

import java.util.List;

import com.carRental.entities.Car;

public interface CarService {
	List<Car> findAll();
	
	Car findById(int theId);
	
	void save(Car theRenter);
	
	void deleteById(int theId);
	
}
