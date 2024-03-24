package com.carRental.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carRental.entities.Car;

public interface CarRepository extends JpaRepository<Car, Integer>{

}
