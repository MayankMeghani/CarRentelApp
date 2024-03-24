package com.carRental.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.carRental.entities.Booking;
import com.carRental.entities.Car;
import com.carRental.entities.User;

public interface BookingRepository extends JpaRepository<Booking , Integer> {
//	List <Booking> FindByUser(User user);
//	Booking FindByCar(Car car);
}
