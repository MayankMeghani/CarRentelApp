package com.carRental.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carRental.entities.Booking;
import com.carRental.entities.Customer;
import com.carRental.services.BookingService;
import com.carRental.services.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private BookingService bookingService;

	

	public CustomerController(CustomerService customerService, BookingService bookingService) {
		super();
		this.customerService = customerService;
		this.bookingService = bookingService;
	}

	@GetMapping("/home")
	public String home() {
		return "welcome to home";
	}
	
	@GetMapping("/customers")
	public List<Customer> getUsers(){
		return this.customerService.findAll();
	}
	
	@GetMapping("/{id}")
	public Customer getUser(@PathVariable int id){
		return this.customerService.findById(id);
	}
	
	@GetMapping("/{id}/bookings")
	public List<Booking> getUserBookings(@PathVariable int id){
		Customer customer=customerService.findById(id);
//		return this.bookingService.findByUser(user);
		return null;
	}
	
	@PostMapping("/add")
	public Customer addUser( @RequestBody Customer customer) {
		customerService.save(customer);
		return customer;
	}
	
	@PutMapping("/update")
	public Customer updateUser(@RequestBody Customer customer) {
		customerService.save(customer);
		return customer;
	}
	
	@DeleteMapping("/delete/{id}")
	public int deleteUser(@PathVariable int id) {
		customerService.deleteById(id);
		return id;
	}
}

