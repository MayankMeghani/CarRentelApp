package com.carRental.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carRental.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
