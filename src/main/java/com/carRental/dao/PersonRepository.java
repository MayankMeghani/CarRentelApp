package com.carRental.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carRental.entities.Person;
import com.carRental.entities.Role;

public interface PersonRepository extends JpaRepository<Person, Integer> {

	Person findByUsername(String username);

	List<Person> findByRole(Role role);
}
