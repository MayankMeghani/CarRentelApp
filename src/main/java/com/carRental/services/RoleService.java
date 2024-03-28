package com.carRental.services;

import java.util.List;

import com.carRental.entities.Role;

public interface RoleService {

	Role findByRole(String role);

	List<Role> findAll();
	
	Role findById(int theId);
	
	void save(Role theRole);
	
	void deleteById(int theId);

}
