package com.carRental.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carRental.entities.Booking;
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
	

    @PreAuthorize("hasAnyRole('CUSTOMER','RENTER','ADMIN')")
	@GetMapping("/cars")
	public List<Car> getCars(){
		return this.carService.findAll();
	}
	

    @PreAuthorize("hasAnyRole('CUSTOMER','RENTER','ADMIN')")
	@GetMapping("/{id}")
	public Car getCar(@PathVariable int id){
		return this.carService.findById(id);
	}
	

    @PreAuthorize("hasAnyRole('RENTER','ADMIN')")
	@GetMapping("/{id}/booking")
	public Booking FindCarBooking(@PathVariable int car_id){
//		return this.recordService.findByCar(car_id);
		return null;
	}
	

    @PreAuthorize("hasAnyRole('RENTER','ADMIN')")
	@PostMapping("/add")
	public Car addCars(@RequestBody Car car) {
		carService.save(car);
		return car;
	}
	

    @PreAuthorize("hasAnyRole('RENTER','ADMIN')")
	@PutMapping("/update")
	public Car updateCar(@RequestBody Car car) {
		carService.save(car);
		return car;
	}
	

    @PreAuthorize("hasAnyRole('RENTER','ADMIN')")
	@DeleteMapping("/delete/{id}")
	public int deleteCar(@PathVariable int id) {
		carService.deleteById(id);
		return id;
	}


}
