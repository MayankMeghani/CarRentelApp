package com.carRental.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carRental.entities.Booking;
import com.carRental.entities.Car;
import com.carRental.entities.Person;
import com.carRental.entities.Role;
import com.carRental.exception.NotFoundException;
import com.carRental.services.BookingService;
import com.carRental.services.CarService;
import com.carRental.services.PersonService;
import com.carRental.services.RoleService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	PersonService personService;
	@Autowired
	BookingService bookingService;
	@Autowired
	RoleService roleService;
	@Autowired
	CarService carService;
	@GetMapping("")
	public String home() {
		return "Welcome to Customre Section "
				+ "<br> For All customers :  customer/customers"
				+ "<br> For particular customer :  customer/{id}"
				+ "<br> For adding customer :  customer/add"
				+ "<br> Find bookings of customer :  customer/{id}/booking"
				+ "<br> To update customer :  customer/update"
				+ "<br> To delete customer :  customer/delete/{id}";
	}
	
    @GetMapping("/customers")
    @PreAuthorize("hasAnyRole('ADMIN','RENTER','CUSTOMER')")
    public List<Person> findAll(){
        List<Person> persons= personService.findAll();
        List<Person> customers = new ArrayList<Person>(); ;
        for(Person p:persons) {
        	if((p.getRole().getRole()).equals("CUSTOMER")) {
        		customers.add(p);
        	}
        }
        	if(customers.isEmpty()) {
    			throw new NotFoundException("Did not find any customer");
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
    	else {
			throw new NotFoundException("No Customer is registerd for given Id");
    	}
    }

    @GetMapping("/{id}/bookings")
    @PreAuthorize("hasAnyRole('ADMIN','RENTER','CUSTOMER')")
    public List<Booking> BookingByUser(@PathVariable int id){
    	Person p= personService.findById(id);
    	return bookingService.findByCustomer(p);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN','RENTER','CUSTOMER')")
    public Person addUser(@RequestBody Person person){
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder(10);
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        Role role=roleService.findByRole("CUSTOMER");
        person.setRole(role);
        personService.save(person);
        return person;
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN','RENTER','CUSTOMER')")
    public Person updateUser(@PathVariable int id, @RequestBody Person person){
    	person.setId(id);
    	if((person.getRole().getRole()).equals("CUSTOMER")) {	
		personService.save(person); 
    	return person;
    	}
    	else {
			throw new NotFoundException("No Customer is registerd for given Id");
    	}
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public int deleteUser(@PathVariable int id){
    	Person person=personService.findById(id);
    	if((person.getRole().getRole()).equals("CUSTOMER")) {
    	Person Customer=personService.findById(id);
    	List<Booking> bookings = bookingService.findByCustomer(Customer);
    	if(!bookings.isEmpty()) {
    		for(Booking b:bookings) {
    			Car c =b.getCar();
    			c.setAvailable(true);
    			carService.save(c);
    		}
    	}
    	personService.deleteById(id);
    	return id;
    	}
    	else {
			throw new NotFoundException("No Customer is registerd for given Id");
    	}    
    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleBookingException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
