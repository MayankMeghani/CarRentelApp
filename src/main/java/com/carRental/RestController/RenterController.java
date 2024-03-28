package com.carRental.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.carRental.entities.Car;
import com.carRental.entities.Person;
import com.carRental.entities.Role;
import com.carRental.exception.NotFoundException;
import com.carRental.services.CarService;
import com.carRental.services.PersonService;
import com.carRental.services.RoleService;


@RestController
@RequestMapping("/renter")
public class RenterController {

	@Autowired
	PersonService personService;
	@Autowired
	CarService carService;
	@Autowired
	RoleService roleService;
	

	@GetMapping("")
	public String home() {
		return "Welcome to Reter Section "
				+ "<br> For All renter :  renter/renters"
				+ "<br> For particular renter :  renter/{id}"
				+ "<br> For finding cars of renter :  renter/{id}/cars"
				+ "<br> For particular car of renter :  renter/{renter_id}/{car_id}"
				+ "<br> For adding renter :  renter/add"
				+ "<br> To update renter :  renter/update"
				+ "<br> To delete renter :  renter/delete/{id}";
	}
	
    @GetMapping("/renters")
    @PreAuthorize("hasAnyRole('ADMIN','RENTER','CUSTOMER')")
    @ResponseBody
    public List<Person> findAll(){
        List<Person> persons= personService.findAll();
        List<Person> renters = new ArrayList<Person>(); ;
        for(Person p:persons) {
        	if((p.getRole().getRole()).equals("RENTER")) {
        		renters.add(p);
        	}
        }
        if(renters.isEmpty()) {
			throw new NotFoundException("Did not find any renter");
        }
        return renters;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','RENTER','CUSTOMER')")
    public Person userById(@PathVariable int id){
    	Person p= personService.findById(id);
    	if((p.getRole().getRole()).equals("RENTER")) {
    		return p;
    	}
    	else {
    		throw new NotFoundException("No Renter is registerd for given Id");
    	}
    }

    @GetMapping("/{id}/cars")
    @PreAuthorize("hasAnyRole('ADMIN','RENTER','CUSTOMER')")
    public List<Car> carsById(@PathVariable int id){
    	Person p= personService.findById(id);
    	if((p.getRole().getRole()).equals("RENTER")) {
    		List<Car> owned=p.getCars();
    		if(owned.isEmpty()) {
    			throw new NotFoundException("No Car is owned by renter");
    		}
    		return owned;
    	}
    	else {
			throw new NotFoundException("No Renter is registerd for given Id");
    	}
    }

    @GetMapping("/{renter_id}/{car_id}")
    @PreAuthorize("hasAnyRole('ADMIN','RENTER','CUSTOMER')")
    public Car carByRenerId(@PathVariable int renter_id,@PathVariable int car_id){
    	return personService.FindCarByrenter(renter_id, car_id);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN','RENTER')")
    public Person addUser(@RequestBody Person person){
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder(10);
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        Role role=roleService.findByRole("RENTER");
        person.setRole(role);
        personService.save(person);
        return person;
    }


    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN','RENTER')")
    public Person updateUser(@PathVariable int id, @RequestBody Person person){
    	person.setId(id);
    	if((person.getRole().getRole()).equals("RENTER")) {	
		personService.save(person); 
    	return person;
    	}
    	else {
			throw new NotFoundException("No Renter is registerd for given Id");
    	}
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public int deleteUser(@PathVariable int id){
    	Person person=personService.findById(id);
    	if((person.getRole().getRole()).equals("RENTER")) {	
    	personService.deleteById(id);
    	return id;
    	}
    	else {
			throw new NotFoundException("No Renter is registerd for given Id");
    	}
    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleBookingException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
