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
import com.carRental.entities.User;
import com.carRental.services.BookingService;
import com.carRental.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private BookingService bookingService;

	

	public UserController(UserService userService, BookingService bookingService) {
		super();
		this.userService = userService;
		this.bookingService = bookingService;
	}

	@GetMapping("/home")
	public String home() {
		return "welcome to home";
	}
	
	@GetMapping("/users")
	public List<User> getUsers(){
		return this.userService.findAll();
	}
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable int id){
		return this.userService.findById(id);
	}
	
	@GetMapping("/{id}/bookings")
	public List<Booking> getUserBookings(@PathVariable int id){
		User user=userService.findById(id);
//		return this.bookingService.findByUser(user);
		return null;
	}
	
	@PostMapping("/add")
	public User addUser( @RequestBody User user) {
		userService.save(user);
		return user;
	}
	
	@PutMapping("/update")
	public User updateUser(@RequestBody User user) {
		userService.save(user);
		return user;
	}
	
	@DeleteMapping("/delete/{id}")
	public int deleteUser(@PathVariable int id) {
		userService.deleteById(id);
		return id;
	}
}

