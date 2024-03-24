package com.carRental.services;

import java.util.List;

import com.carRental.entities.User;


public interface UserService {

	List<User> findAll();
	
	User findById(int theId);
	
	void save(User theUser);
	
	void deleteById(int theId);
}
