package com.carRental.services;

import java.util.List;

import com.carRental.entities.Booking;

public interface BookingService {

	List<Booking> findAll();
	
	Booking findById(int theId);
	
	void save(Booking record);
	
	void deleteById(int theId);

}
