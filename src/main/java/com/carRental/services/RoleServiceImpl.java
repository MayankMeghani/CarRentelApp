package com.carRental.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.carRental.dao.RoleRepository;
import com.carRental.entities.Role;
import com.carRental.exception.NotFoundException;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleRepository roleRepository;
	
	
	public RoleServiceImpl() {
		super();
	}

	public RoleServiceImpl(RoleRepository roleRepository) {
		super();
		this.roleRepository = roleRepository;
	}

	@Override
	public List<Role> findAll() {
		List<Role> roles= roleRepository.findAll();
		if(roles.isEmpty()) {
			throw new NotFoundException("Did not Any role");
		}
		else {
			return roles;
		}
	}

	@Override
	public Role findById(int theId) {
		Optional<Role> result = roleRepository.findById(theId);
		
		Role theRole = null;
		
		if (result.isPresent()) {
			theRole = result.get();
		}
		else {
			throw new NotFoundException("No role is Found for given Id");	
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

	@Override
	public Role findByRole(String role) {
		Role r=roleRepository.findByRole(role);
		if(r==null) {
		throw new NotFoundException("Role with provided details is not registered");
		}
		return r;
	}
	
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleBookingException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

	
}
