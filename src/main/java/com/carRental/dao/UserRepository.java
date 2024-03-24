package com.carRental.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carRental.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
