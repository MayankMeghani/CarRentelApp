package com.carRental.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carRental.entities.Car;
import com.carRental.entities.Renter;

public interface CarRepository extends JpaRepository<Car, Integer>{
	List <Car> findByRenter(Renter renter);
}
