package com.carRental.controller;

import com.carRental.entities.Booking;
import com.carRental.entities.Car;
import com.carRental.entities.Person;
import com.carRental.services.BookingService;
import com.carRental.services.CarService;
import com.carRental.services.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookingMvcController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private CarService carService;
    @Autowired
    private PersonService personService;

    public BookingMvcController(BookingService bookingService, CarService carService, PersonService personService) {
        this.bookingService = bookingService;
        this.carService = carService;
        this.personService = personService;
    }

    @GetMapping("/list")
    public String listBookings(Model model) {
        List<Booking> bookings = bookingService.findAll();
        model.addAttribute("bookings", bookings);
        model.addAttribute("cars", carService.findAll());
        model.addAttribute("customers", personService.findAll());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication != null) {
	        Object principal = authentication.getPrincipal();
	        if (principal instanceof UserDetails) {
	            UserDetails userDetails = (UserDetails) principal;
	            String username = userDetails.getUsername();
	            model.addAttribute("username", username);
	            Person user= personService.findByUsername(username);
	            model.addAttribute("role",user.getRole().getName());
	        }
	    }
        
        return "bookings/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        List<Car> cars = carService.findAll();
        List<Car> available = new ArrayList<Car>();
        for(Car c:cars) {
        	if(c.isAvailable()) {
        		available.add(c);
        	}
        }
        model.addAttribute("cars",available );
        List<Person> persons = personService.findAll();
        List<Person> customers = new ArrayList<Person>();
        for(Person p : persons) {
        	if(p.getRole().getName().equals("CUSTOMER")) {
        		customers.add(p);
        	}
        }
        
        model.addAttribute("customers",customers );
        model.addAttribute("booking", new Booking());
        return "bookings/add";
    }

    @PostMapping("/save")
    public String saveBooking(@ModelAttribute("booking") Booking booking) {
    	
    	int id = booking.getCar().getId();
    	Car c  = carService.findById(id);
    	c.setAvailable(false);
        carService.save(c);
        booking.setCar(c);
        bookingService.save(booking);
        return "redirect:/bookings/list";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable int id, Model model) {
        Booking booking = bookingService.findById(id);
        model.addAttribute("booking", booking);
        model.addAttribute("cars", carService.findAll());
        model.addAttribute("customers", personService.findAll());
        return "bookings/add"; // Assuming the form is the same for adding and updating
    }

    @PostMapping("/update")
    public String updateBooking(@RequestParam("bookingId") int id, Model model) {
    	List<Car> cars = carService.findAll();
        List<Car> available = new ArrayList<Car>();
        for(Car c:cars) {
        	if(c.isAvailable()) {
        		available.add(c);
        	}
        }
        model.addAttribute("cars",available );
        List<Person> persons = personService.findAll();
        List<Person> customers = new ArrayList<Person>();
        for(Person p : persons) {
        	if(p.getRole().getName().equals("CUSTOMER")) {
        		customers.add(p);
        	}
        }
        
        model.addAttribute("customers",customers );
        
        Booking booking = bookingService.findById(id);
        Car currentCar = booking.getCar();
        currentCar.setAvailable(true); // Set the current car back to available
        carService.save(currentCar); // Save the updated current car
        model.addAttribute("booking", booking);
//        model.addAttribute("cars", carService.findAll());
//        model.addAttribute("customers", personService.findAll());
        return "bookings/add";
    }

    @PostMapping("/delete")
    public String deleteBooking(@RequestParam("bookingId") int id) {
        Booking booking = bookingService.findById(id);
        booking.getCar().setAvailable(true);
    	bookingService.deleteById(id);
        return "redirect:/bookings/list";
    }
}
