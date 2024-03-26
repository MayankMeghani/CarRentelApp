package com.carRental.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carRental.entities.Role;
import com.carRental.services.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleService;

	public RoleController(RoleService roleService) {
		super();
		this.roleService=roleService;
		}

	@GetMapping("/home")
	public String home() {
		return "welcome to home";
	}
	

    @PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/roles")
	public List<Role> getRoles(){
		return this.roleService.findAll();
	}
	

    @PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/{id}")
	public Role getRole(@PathVariable int id){
    	return this.roleService.findById(id);
	}
	
	

    @PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/add")
	public Role addRole(@RequestBody Role role) {
		roleService.save(role);
		return role;
	}
	

    @PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/update")
	public Role updateRenter(@RequestBody Role role) {
		roleService.save(role);
		return role;
	}
	

    @PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public int deleteRenter(@PathVariable int id) {
		roleService.deleteById(id);
		return id;
	}

	
}
