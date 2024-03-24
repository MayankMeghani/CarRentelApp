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
import com.carRental.services.CarService;

@RestController
@RequestMapping("/car")
public class CarController {

	@Autowired
	private CarService carService;


	public CarController(CarService carService) {
		super();
		this.carService = carService;
	}

	@GetMapping("/home")
	public String home() {
		return "welcome to home";
	}
	
	@GetMapping("/cars")
	public List<Car> getCars(){
		return this.carService.findAll();
	}
	
	@GetMapping("/{id}")
	public Car getCourse(@PathVariable int id){
		return this.carService.findById(id);
	}
	
	@PostMapping("/add")
	public Car addcar(@RequestBody Car car) {
		carService.save(car);
		return car;
	}
	
	@PutMapping("/update")
	public Car updateUser(@RequestBody Car car) {
		carService.save(car);
		return car;
	}
	
	@DeleteMapping("/delete/{id}")
	public int updateCar(@PathVariable int id) {
		carService.deleteById(id);
		return id;
	}


}
