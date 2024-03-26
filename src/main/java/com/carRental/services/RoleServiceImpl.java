package com.carRental.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carRental.dao.RoleRepository;
import com.carRental.entities.Role;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleRepository roleRepository;
	
	
	public RoleServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoleServiceImpl(RoleRepository roleRepository) {
		super();
		this.roleRepository = roleRepository;
	}

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Role findById(int theId) {
		Optional<Role> result = roleRepository.findById(theId);
		
		Role theRole = null;
		
		if (result.isPresent()) {
			theRole = result.get();
		}
		else {
			throw new RuntimeException("Did not find renter id - " + theId);
		}
		
		return theRole;
	
	}

	@Override
	public void save(Role theRole) {
		roleRepository.save(theRole);
	}

	@Override
	public void deleteById(int theId) {
		roleRepository.deleteById(theId);
	}

}
