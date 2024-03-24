package com.carRental.services;

import java.util.List;

import com.carRental.entities.Car;
import com.carRental.entities.Renter;

public interface CarService {
	List<Car> findAll();
	
	Car findById(int theId);
	
	List<Car> findByRenter(Renter theRenter);
	
	void save(Car theRenter);
	
	void deleteById(int theId);
	
}
