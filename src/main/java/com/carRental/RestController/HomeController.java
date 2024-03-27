package com.carRental.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "Welcome to CarRentalapp "
				+ "<br> "
				+ "<br> We have following Sections"
				+ "<br> customer :  /customer"
				+ "<br> renter :  /renter"
				+ "<br> person :  /person"
				+ "<br> car : /car"
				+ "<br> booking : /booking"
				+ "<br> role : /role";
	}
	
}
