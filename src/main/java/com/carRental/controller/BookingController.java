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
import com.carRental.services.BookingService;

@RestController
@RequestMapping("/record")
public class BookingController {

		@Autowired
		BookingService recordService;

		public BookingController(BookingService recordService) {
			super();
			this.recordService = recordService;
		}
		

		@GetMapping("/home")
		public String home() {
			return "welcome to home";
		}
		
		@GetMapping("/records")
		public List<Booking> getRecords(){
			return this.recordService.findAll();
		}
		
		@GetMapping("/{id}")
		public Booking getRecord(@PathVariable int id){
			return this.recordService.findById(id);
		}
		
		
		
		@PostMapping("/add")
		public Booking addRecord(@RequestBody Booking record) {
			recordService.save(record);
			return record;
		}
		
		@PutMapping("/update")
		public Booking updateRecord(@RequestBody Booking record) {
			recordService.save(record);
			return record;
		}
		
		@DeleteMapping("/delete/{id}")
		public int deleteRecord(@PathVariable int id) {
			recordService.deleteById(id);
			return id;
		}
		
}
