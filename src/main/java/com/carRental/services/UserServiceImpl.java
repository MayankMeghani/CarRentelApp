package com.carRental.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carRental.dao.UserRepository;
import com.carRental.entities.User;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findById(int theId) {
	Optional<User> result = userRepository.findById(theId);
	
	User theUser = null;
	
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
	public void save(User theEmployee) {
		userRepository.save(theEmployee);
		
	}

	@Override
	public void deleteById(int theId) {
		userRepository.deleteById(theId);
		
	}

}
