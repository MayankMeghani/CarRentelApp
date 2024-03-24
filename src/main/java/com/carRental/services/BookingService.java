package com.carRental.services;

import java.util.List;

import com.carRental.entities.Booking;
import com.carRental.entities.User;

public interface BookingService {

	List<Booking> findAll();
	
//	List<Booking> findByUser(User user);
	
//	Booking findByCar(Car car);
	
	Booking findById(int theId);
	
	void save(Booking record);
	
	void deleteById(int theId);

}
