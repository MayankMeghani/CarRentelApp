package com.carRental.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.carRental.dao.PersonRepository;
import com.carRental.entities.Car;
import com.carRental.entities.Person;
import com.carRental.entities.Role;
import com.carRental.exception.NotFoundException;

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
		List<Person> persons = personRepository.findAll();
		if(persons.isEmpty()) {
			throw new NotFoundException("Did not find car for given renter");
			}
			else {
				return persons;
			}
	}

	@Override
	public Person findById(int theId) {
		Optional<Person> result = personRepository.findById(theId);
		
		Person thePerson = null;
		
		if (result.isPresent()) {
			thePerson = result.get();
		}
		else {
			throw new NotFoundException("Did not find Person with  given id ");
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
			throw new NotFoundException("Car is Not Owned by given renter");
			
		}
		else {
		throw new NotFoundException("No renter is registered with given Id");
		}
	}

	@Override
	public List<Person> findByRole(Role role) {
		List<Person> persons= personRepository.findByRole(role);
		if(persons.isEmpty()) {
		throw new NotFoundException("Did not find car for given renter");
		}
		else {
			return persons;
		}
	}

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleBookingException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}