package com.carRental.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carRental.entities.Renter;

public interface RenterRepository extends JpaRepository<Renter, Integer> {

}
