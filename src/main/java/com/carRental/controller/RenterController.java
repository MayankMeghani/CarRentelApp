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

import com.carRental.entities.Renter;
import com.carRental.services.RenterService;

@RestController
@RequestMapping("/renter")
public class RenterController {

	@Autowired
	private RenterService renterService;

	public RenterController(RenterService renterService) {
		super();
		this.renterService = renterService;
	}

	@GetMapping("/home")
	public String home() {
		return "welcome to home";
	}
	
	@GetMapping("/renters")
	public List<Renter> getCourses(){
		return this.renterService.findAll();
	}
	
	@GetMapping("/{id}")
	public Renter getCourse(@PathVariable int id){
		return this.renterService.findById(id);
	}
	
	@PostMapping("/add")
	public Renter addUser(@RequestBody Renter user) {
		renterService.save(user);
		return user;
	}
	
	@PutMapping("/update")
	public Renter updateUser(@RequestBody Renter renter) {
		renterService.save(renter);
		return renter;
	}
	
	@DeleteMapping("/delete/{id}")
	public int updateUser(@PathVariable int id) {
		renterService.deleteById(id);
		return id;
	}

}
