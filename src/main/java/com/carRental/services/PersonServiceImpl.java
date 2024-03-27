package com.carRental.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.carRental.dao.PersonRepository;
import com.carRental.entities.Car;
import com.carRental.entities.Person;

@Service
public class PersonServiceImpl implements PersonService,UserDetailsService {
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

	@Override
	public List<Person> findAll() {
		return personRepository.findAll();
	}

	@Override
	public Person findById(int theId) {
		Optional<Person> result = personRepository.findById(theId);
		
		Person thePerson = null;
		
		if (result.isPresent()) {
			thePerson = result.get();
		}
		else {
			throw new RuntimeException("Did not find person id - " + theId);
		}
		
		
		return thePerson;
	}

	@Override
	public void save(Person thePerson) {
		personRepository.save(thePerson);
	}

	@Override
	public void deleteById(int theId) {
		personRepository.deleteById(theId);
	}


	@Override
	public Car FindCarByrenter(int renter_id, int car_id) {
		Person person= findById(renter_id);
		if((person.getRole().getRole()).equals("RENTER")) {
			List<Car> all = person.getCars();
			for(Car c:all) {
				if(c.getId()==car_id) {
					return c;
				}
			}
		}
		return null;
	}
}