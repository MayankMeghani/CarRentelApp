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

import com.carRental.entities.User;
import com.carRental.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("/home")
	public String home() {
		return "welcome to home";
	}
	
	@GetMapping("/users")
	public List<User> getCourses(){
		return this.userService.findAll();
	}
	
	@GetMapping("/{id}")
	public User getCourse(@PathVariable int id){
		return this.userService.findById(id);
	}
	
	@PostMapping("/add")
	public User addUser(@RequestBody User user) {
		userService.save(user);
		return user;
	}
	
	@PutMapping("/update")
	public User updateUser(@RequestBody User user) {
		userService.save(user);
		return user;
	}
	
	@DeleteMapping("/delete/{id}")
	public int updateUser(@PathVariable int id) {
		userService.deleteById(id);
		return id;
	}
}

