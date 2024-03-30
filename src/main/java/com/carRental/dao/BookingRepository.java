package com.carRental.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.carRental.entities.Booking;
import com.carRental.entities.Car;
import com.carRental.entities.Person;

public interface BookingRepository extends JpaRepository<Booking , Integer> {
	List <Booking> findByCustomer(Person customer);
	Booking findByCar(Car car);
}
