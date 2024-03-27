package com.carRental.RestController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
import com.carRental.exception.NotFoundException;
import com.carRental.services.PersonService;
@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	PersonService personService;

	@GetMapping("")
	public String home() {
		return "Welcome to Admin's Section "
				+ "<br> For All person :  person/persons"
				+ "<br> For particular person :  person/{id}"
				+ "<br> For finding persons by role :  person/role"
				+ "<br> For adding person :  person/add"
				+ "<br> To update person :  person/update"
				+ "<br> To delete person :  person/delete/{id}";
	}
	
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
    	Person person=personService.findById(id);
    	if((person.getRole().getRole()).equals("ADMIN")) {
    	return personService.findById(id);
    	}
    	else {
    		throw new NotFoundException("No Admin is registerd for given Id");
    	}
    }

    @GetMapping("/role")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Person> userByRole(@RequestBody Role role){
    	return personService.findByRole(role);
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

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public Person updateUser(@PathVariable int id, @RequestBody Person person){
    	if((person.getRole().getRole()).equals("ADMIN")) {
    	personService.save(person); 
    	return person;
    	}
    	else {
    		throw new NotFoundException("No Admin is registerd for given Id");
    	}
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public int deleteUser(@PathVariable int id){
    	Person person=personService.findById(id);
    	if((person.getRole().getRole()).equals("ADMIN")) {
    	personService.deleteById(id);
    	return id;
    	}
    	else {
    		throw new NotFoundException("No Admin is registerd for given Id");
    	}
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleBookingException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}