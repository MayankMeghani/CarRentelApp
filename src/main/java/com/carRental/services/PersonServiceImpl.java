package com.carRental.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.carRental.dao.PersonRepository;
import com.carRental.entities.Person;

@Service
public class PersonServiceImpl implements UserDetailsService {
    @Autowired
    PersonRepository personRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByUsername(username);
        if (person == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new PersonDetailsInfo(person);
    }
}