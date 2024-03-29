package com.carRental.controller;

import com.carRental.entities.Car;
import com.carRental.entities.Person;
import com.carRental.services.BookingService;
import com.carRental.services.CarService;
import com.carRental.services.PersonService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars")
public class CarMvcController {

	@Autowired
	private CarService carService;
	@Autowired
	private PersonService personService;
	@Autowired
	private BookingService bookingService;
	
	public CarMvcController(CarService carService, PersonService personService, BookingService bookingService) {
		super();
		this.carService = carService;
		this.personService = personService;
		this.bookingService = bookingService;
	}

    
    

    @GetMapping("/list")
    public String listCars(Model model) {
        model.addAttribute("cars", carService.findAll());
        model.addAttribute("renters",personService.findAll() );
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
        return "cars/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("car", new Car());
        List<Person> persons= personService.findAll();
        List<Person> renters = new ArrayList<Person>();
        for(Person p:persons) {
        	if((p.getRole().getName()).equals("RENTER")) {
        		renters.add(p);
        	}
        }
        model.addAttribute("renters",renters );
        return "cars/add";
    }

    @PostMapping("/save")
    public String saveCar(@ModelAttribute("car") Car car) {
        car.setRenter(personService.findById(car.getRenter().getId()));
        car.setAvailable(true);
        carService.save(car);
        return "redirect:/cars/list";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable int id, Model model) {
    	List<Person> persons= personService.findAll();
        List<Person> renters = new ArrayList<Person>();
        for(Person p:persons) {
        	if((p.getRole().getName()).equals("RENTER")) {
        		renters.add(p);
        	}
        }
        model.addAttribute("renters",renters );
    	Car car = carService.findById(id);
        model.addAttribute("car", car);
        return "cars/add";
    }

    @PostMapping("/update")
    public String updateCar(@RequestParam("carId") int id,Model model) {
    	List<Person> persons= personService.findAll();
        List<Person> renters = new ArrayList<Person>();
        for(Person p:persons) {
        	if((p.getRole().getName()).equals("RENTER")) {
        		renters.add(p);
        	}
        }
        model.addAttribute("renters",renters );	
        Car car = carService.findById(id);
        model.addAttribute("car", car);
        return "/cars/add";
    }

    @GetMapping("/delete/{id}")
    public String deleteCarById(@PathVariable int id){
        carService.deleteById(id);
        return "redirect:/cars/list";
    }
    
    @PostMapping("/delete")
    public String deleteCar(@RequestParam("carId") int id){
        carService.deleteById(id);
        return "redirect:/cars/list";
    }
}
