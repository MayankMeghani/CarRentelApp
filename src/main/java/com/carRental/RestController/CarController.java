package com.carRental.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.carRental.exception.NotFoundException;
import com.carRental.services.BookingService;
import com.carRental.services.CarService;
import com.carRental.services.PersonService;

@RestController
@RequestMapping("/car")
public class CarController {

	@Autowired
	private CarService carService;
	@Autowired
	private PersonService personService;
	@Autowired
	private BookingService bookingService;

	public CarController(CarService carService, PersonService personService, BookingService bookingService) {
		super();
		this.carService = carService;
		this.personService = personService;
		this.bookingService = bookingService;
	}


	@GetMapping("")
	public String home() {
		return "Welcome to Car Section "
				+ "<br> For All cars :  car/cars"
				+ "<br> For particular car :  car/{id}"
				+ "<br> For available car :  car/available"
				+ "<br> For adding car :  car/add"
				+ "<br> Find booking of car :  car/{id}/booking"
				+ "<br> To update car :  car/update"
				+ "<br> To delete car :  car/delete/{id}";
	}
	

    @PreAuthorize("hasAnyRole('CUSTOMER','RENTER','ADMIN')")
	@GetMapping("/cars")
	public List<Car> getCars(){
		return this.carService.findAll();
	}
    
    @PreAuthorize("hasAnyRole('CUSTOMER','RENTER','ADMIN')")
	@GetMapping("/available")
	public List<Car> getAvailableCars(){
		List<Car> cars=carService.findAll();
		List<Car>available=new ArrayList<Car>();
		for(Car c:cars) {
			if(c.isAvailable()) {
				available.add(c);
			}
		}
		return available;
	}

    @PreAuthorize("hasAnyRole('CUSTOMER','RENTER','ADMIN')")
	@GetMapping("/{id}")
	public Car getCar(@PathVariable int id){
		return this.carService.findById(id);
	}
	
    @PreAuthorize("hasAnyRole('CUSTOMER','RENTER','ADMIN')")
	@GetMapping("/{id}/renter")
	public Person getCarRenter(@PathVariable int id){
		Car car=carService.findById(id);
		return car.getRenter();
	}

    @PreAuthorize("hasAnyRole('RENTER','ADMIN')")
	@GetMapping("/{id}/booking")
	public Booking FindCarBooking(@PathVariable int id){
    	Car car=carService.findById(id);
		return this.bookingService.findByCar(car);
	}
	

    @PreAuthorize("hasAnyRole('RENTER','ADMIN')")
	@PostMapping("/add")
	public Car addCars(@RequestBody Car car) {
    	Person renter=car.getRenter();
    	int id= renter.getId();
		renter = personService.findById(id);
		if((renter.getRole().getName()).equals("RENTER")) {
    	car.setRenter(renter);
    	car.setAvailable(true);
		carService.save(car);
		return car;
		}
		else {
			throw new NotFoundException("No Renter is registerd for given Id");
    	}
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
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleBookingException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }


}
