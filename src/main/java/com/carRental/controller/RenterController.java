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

import com.carRental.entities.Car;
import com.carRental.entities.Renter;
import com.carRental.services.CarService;
import com.carRental.services.RenterService;

@RestController
@RequestMapping("/renter")
public class RenterController {

	@Autowired
	private RenterService renterService;
	@Autowired
	private CarService carService;

	public RenterController(RenterService renterService,CarService carService) {
		super();
		this.renterService = renterService;
		this.carService=carService;
		}

	@GetMapping("/home")
	public String home() {
		return "welcome to home";
	}
	
	@GetMapping("/renters")
	public List<Renter> getRenters(){
		return this.renterService.findAll();
	}
	
	@GetMapping("/{id}")
	public Renter getRenter(@PathVariable int id){
		return this.renterService.findById(id);
	}
	
	@GetMapping("/{id}/cars")
	public List<Car> getReterCars(@PathVariable int id){
		Renter renter=renterService.findById(id);
		return this.carService.findByRenter(renter);
	}
	
	@GetMapping("/{renter_id}/{car_id}")
	public Car getReterCars(@PathVariable int renter_id,@PathVariable int car_id){
		Renter renter=renterService.findById(renter_id);
		List<Car> cars= carService.findByRenter(renter);
		for( Car car:cars) {
			if(car.getId()==car_id)
				return car;
		}
		return null;
	}
	
	@PostMapping("/add")
	public Renter addRenter(@RequestBody Renter user) {
		renterService.save(user);
		return user;
	}
	
	@PutMapping("/update")
	public Renter updateRenter(@RequestBody Renter renter) {
		renterService.save(renter);
		return renter;
	}
	
	@DeleteMapping("/delete/{id}")
	public int deleteRenter(@PathVariable int id) {
		renterService.deleteById(id);
		return id;
	}

}
