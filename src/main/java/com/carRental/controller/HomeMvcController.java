package com.carRental.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.carRental.entities.Person;
import com.carRental.services.PersonService;

@Controller
@RequestMapping("/")
public class HomeMvcController {

	@Autowired
	PersonService personService;
	
	@GetMapping("/")
	public String Nohome(Model model) {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication != null) {
	        Object principal = authentication.getPrincipal();
	        if (principal instanceof UserDetails) {
	            UserDetails userDetails = (UserDetails) principal;
	            String username = userDetails.getUsername();
	            model.addAttribute("username", username);
	            // You can also access other user information, such as the authorities, using the userDetails object
	            // For example, to get the user's authorities, you can use the following code:
//	             Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
//	             model.addAttribute("authorities", authorities);
	            Person user= personService.findByUsername(username);
	            model.addAttribute("role",user.getRole().getName());
//	            model.addAttribute("role",user.getRole().getRole());
	            
	        }
	    }
	    return "home";
	}
	
	
	
}
