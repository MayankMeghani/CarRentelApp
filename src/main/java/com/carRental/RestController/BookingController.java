package com.carRental.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.carRental.exception.BadRequestException;
import com.carRental.exception.NotFoundException;
import com.carRental.services.BookingService;
import com.carRental.services.CarService;
import com.carRental.services.PersonService;

@RestController
@RequestMapping("/booking")
public class BookingController {

		@Autowired
		BookingService recordService;
		@Autowired
		CarService carService;
		@Autowired
		PersonService personService;

		public BookingController(BookingService recordService) {
			super();
			this.recordService = recordService;
		}
		

		@GetMapping("")
		public String home() {
			return "Welcome to Booking Section "
					+ "<br> For All bookings :  booking/bookings"
					+ "<br> For particular booking :  booking/{id}"
					+"<br> For Renter of booking : booking/{id}/renter"
					+ "<br> For add booking : booking/add"
					+ "<br> Find booking of car :  car/{id}/booking"
					+ "<br> To update booking :  booking/update"
					+ "<br> To delete booking :  booking/delete/{id}";
		}

	    @PreAuthorize("hasAnyRole('CUSTOMER','RENTER','ADMIN')")
		@GetMapping("/bookings")
		public List<Booking> getRecords(){
			return this.recordService.findAll();
		}
		

	    @PreAuthorize("hasAnyRole('CUSTOMER','RENTER','ADMIN')")
		@GetMapping("/{id}")
		public Booking getRecord(@PathVariable int id){
			return this.recordService.findById(id);
		}
	    
	    @PreAuthorize("hasAnyRole('CUSTOMER','RENTER','ADMIN')")
		@GetMapping("/{id}/renter")
		public Person getRenterBybooking(@PathVariable int id){
	    	Booking record=recordService.findById(id);
	    	Car car=record.getCar();
	    	return car.getRenter();
	    }
		

	    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
		@PostMapping("/add")
		public Booking addRecord(@RequestBody Booking record) {
			Car car= carService.findById(record.getCar().getId());
	    	if( car.isAvailable() ) {
	    		Person customer = personService.findById(record.getCustomer().getId());
		    	if((customer.getRole().getName()).equals("CUSTOMER") ) {
				    car.setAvailable(false);
			    	carService.save(car);
			    	record.setCar(car);
			    	record.setCustomer(customer);
					recordService.save(record);
					return record;
		    	}
		    	else {
		    		throw new NotFoundException("Provided Id is not of customer");
			    		
		    	}
	    	}
	    	else {
	    		throw new BadRequestException("Sorry! Car is not available");
	    	}
	    }
		

	    @PreAuthorize("hasAnyRole('CUSTOMER','RENTER','ADMIN')")
		@PutMapping("/update")
		public Booking updateRecord(@RequestBody Booking record) {
			recordService.save(record);
			return record;
		}
		

	    @PreAuthorize("hasAnyRole('RENTER','ADMIN')")
		@DeleteMapping("/delete/{id}")
		public int deleteRecord(@PathVariable int id) {
	    	Booking record = recordService.findById(id);
	    	Car car=record.getCar();
	    	car.setAvailable(true);
	    	carService.save(car);
	    	recordService.deleteById(id);
			return id;
		}
		
	    
	    @ExceptionHandler(NotFoundException.class)
	    public ResponseEntity<String> handleBookingException(NotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	    @ExceptionHandler(BadRequestException.class)
	    public ResponseEntity<String> handleBookingException(BadRequestException ex) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	    }
}