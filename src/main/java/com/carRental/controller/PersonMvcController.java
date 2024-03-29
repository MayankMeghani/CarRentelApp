package com.carRental.controller;

import com.carRental.entities.Booking;
import com.carRental.entities.Person;
import com.carRental.services.BookingService;
import com.carRental.services.CarService;
import com.carRental.services.PersonService;
import com.carRental.services.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/persons")
public class PersonMvcController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private PersonService personService;
	@Autowired
	private BookingService bookingService;
	
	public PersonMvcController(RoleService roleService, PersonService personService, BookingService bookingService) {
		super();
		this.roleService = roleService;
		this.personService = personService;
		this.bookingService = bookingService;
	}
    @GetMapping("/list")
    public String listPersons(Model model) {
        List<Person> persons = personService.findAll();
        model.addAttribute("persons", persons);
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
        return "persons/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("roles", roleService.findAll());
    	model.addAttribute("person", new Person());
    	 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
 	    if (authentication != null) {
 	        Object principal = authentication.getPrincipal();
 	        if (principal instanceof UserDetails) {
 	            UserDetails userDetails = (UserDetails) principal;
 	            String username = userDetails.getUsername();
 	            model.addAttribute("username", username);
 	        }
 	    }
        return "persons/add";
    }

    @PostMapping("/save")
    public String savePerson(@ModelAttribute("person") Person person) {
    	BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder(10);
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        personService.save(person);
        return "redirect:/persons/list";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable int id, Model model) {
        Person person = personService.findById(id);
        model.addAttribute("person", person);
        return "persons/add"; // Assuming the form is the same for adding and updating
    }

    @PostMapping("/update")
    public String updatePerson(@RequestParam("personId") int id ,Model model) {
    	Person person = personService.findById(id);
    	model.addAttribute("roles", roleService.findAll());
    	model.addAttribute("person", person);
    	return "/persons/add";
    }

    @PostMapping("/delete")
    public String deletePerson(@RequestParam("personId") int id) {
    	Person p=personService.findById(id);
    	if(p.getRole().getName().equals("CUSTOMER")) {
    		List<Booking> bookings = bookingService.findByCustomer(p);
    		if(!bookings.isEmpty()) {
    			for(Booking b:bookings) {
    				b.getCar().setAvailable(true);
    			}
    		}
    	}
        personService.deleteById(id);
        return "redirect:/persons/list";
    }
}
