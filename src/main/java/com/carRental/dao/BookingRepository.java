package com.carRental.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.carRental.entities.Booking;
public interface BookingRepository extends JpaRepository<Booking , Integer> {

	

}
