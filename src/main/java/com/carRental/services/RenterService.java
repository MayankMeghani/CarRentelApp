package com.carRental.services;

import java.util.List;

import com.carRental.entities.Renter;

public interface RenterService {

	List<Renter> findAll();
	
	Renter findById(int theId);
	
	void save(Renter theRenter);
	
	void deleteById(int theId);
	
}
