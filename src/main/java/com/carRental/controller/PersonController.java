package com.carRental.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.carRental.dao.PersonRepository;
import com.carRental.entities.Person;
import com.carRental.entities.Role;
import com.carRental.services.PersonService;
@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	PersonService personService;

    @GetMapping("/persons")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public List<Person> all()
    {
        return personService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Person userById(@PathVariable int id){
    	return personService.findById(id);
    }
    
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public Person addUser(@RequestBody Person person){
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder(10);
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        Role role=new Role(1,"ADMIN",null);
        person.setRole(role);
        personService.save(person);
        return person;
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Person updateUser(@PathVariable int id, @RequestBody Person person){
		personService.save(person); 
    	return person;
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public int deleteUser(@PathVariable int id){
    	personService.deleteById(id);
    	return id;
    }
}