package com.carRental.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.carRental.entities.Person;
import com.carRental.entities.Role;
import com.carRental.services.PersonService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	PersonService personService;

    @GetMapping("/customers")
    @PreAuthorize("hasAnyRole('ADMIN','RENTER','CUSTOMER')")
    @ResponseBody
    public List<Person> findAll(){
        List<Person> persons= personService.findAll();
        List<Person> customers = new ArrayList<Person>(); ;
        for(Person p:persons) {
        	if((p.getRole().getRole()).equals("CUSTOMER")) {
        		customers.add(p);
        	}
        }
        return customers;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','RENTER','CUSTOMER')")
    public Person userById(@PathVariable int id){
    	Person p= personService.findById(id);
    	if((p.getRole().getRole()).equals("CUSTOMER")) {
    		return p;
    	}
    	return null;
    }
    
    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN','RENTER','CUSTOMER')")
    public Person addUser(@RequestBody Person person){
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder(10);
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        Role role=new Role(3,"CUSTOMER",null);
        person.setRole(role);
        personService.save(person);
        return person;
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','RENTER','CUSTOMER')")
    public Person updateUser(@PathVariable int id, @RequestBody Person person){
    	person.setId(id);
    	if((person.getRole().getRole()).equals("CUSTOMER")) {	
		personService.save(person); 
    	return person;
    	}
    	return null;
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public int deleteUser(@PathVariable int id){
    	Person person=personService.findById(id);
    	if((person.getRole().getRole()).equals("CUSTOMER")) {	
    	personService.deleteById(id);
    	return id;
    	}
    	return 0;
    }

}
