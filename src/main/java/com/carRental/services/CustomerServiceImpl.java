package com.carRental.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carRental.dao.CustomerRepository;
import com.carRental.entities.Customer;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerRepository customerRepository;
	
	

	public CustomerServiceImpl(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public Customer findById(int theId) {
	Optional<Customer> result = customerRepository.findById(theId);
	
	Customer theUser = null;
	
	if (result.isPresent()) {
		theUser = result.get();
	}
	else {
		// we didn't find the employee
		throw new RuntimeException("Did not find user id - " + theId);
	}
	
	return theUser;
	}

	@Override
	public void save(Customer theEmployee) {
		customerRepository.save(theEmployee);
		
	}

	@Override
	public void deleteById(int theId) {
		customerRepository.deleteById(theId);
		
	}

}
