package com.carRental.services;

import java.util.List;

import com.carRental.entities.Booking;
import com.carRental.entities.Car;
import com.carRental.entities.Person;

public interface BookingService {

	List<Booking> findAll();
	
	List<Booking> findByCustomer(Person user);
	
	Booking findByCar(Car car);
	
	Booking findById(int theId);
	
	void save(Booking record);
	
	void deleteById(int theId);

}
