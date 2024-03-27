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
@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	PersonRepository personRepository;

    @GetMapping("/persons")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public List<Person> all()
    {
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Person userById(@PathVariable int id)
    {
        if(personRepository.findById(id).isEmpty())
        {
            throw new UsernameNotFoundException("No Such User Found");
        }
        Optional<Person> optionalUser = personRepository.findById(id);
        return optionalUser.orElse(null);
    }
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public Person addUser(@RequestBody Person person)
    {
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder(10);
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        personRepository.save(person);
        return person;
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Person updateUser(@PathVariable int id, @RequestBody Person user)
    {
        if(personRepository.findById(id).isEmpty())
        {
            throw new UsernameNotFoundException("No Such User Found");
        }
        return personRepository.save(user);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable int id)
    {
        if(personRepository.findById(id).isEmpty())
        {
            throw new UsernameNotFoundException("No Such User Found");
        }
        personRepository.deleteById(id);
    }
}