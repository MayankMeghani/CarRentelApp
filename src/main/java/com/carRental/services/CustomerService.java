package com.carRental.services;

import java.util.List;

import com.carRental.entities.Customer;


public interface CustomerService {

	List<Customer> findAll();
	
	Customer findById(int theId);
	
	void save(Customer theUser);
	
	void deleteById(int theId);
}
