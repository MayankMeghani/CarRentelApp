package com.carRental.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carRental.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

	Person findByUsername(String username);

}
