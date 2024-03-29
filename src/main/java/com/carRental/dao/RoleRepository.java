package com.carRental.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carRental.entities.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {

	Role findByName(String role);
}
