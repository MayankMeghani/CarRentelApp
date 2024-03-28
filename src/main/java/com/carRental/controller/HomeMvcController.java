package com.carRental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeMvcController {


	@GetMapping("/")
	public String Nohome() {
		return "home";
	}
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	@GetMapping("/login1")
	public String login() {
		return "login";
	}
}
