package com.carRental.controller;

import com.carRental.entities.Role;
import com.carRental.services.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/roles")
public class RoleMvcController {

    @Autowired
    private RoleService roleService;

    public RoleMvcController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/list")
    public String listRoles(Model model) {
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        return "roles/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("role", new Role());
        return "roles/add";
    }

    @PostMapping("/save")
    public String saveRole(@ModelAttribute("role") Role role) {
        roleService.save(role);
        return "redirect:/roles/list";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable int id, Model model) {
        Role role = roleService.findById(id);
        model.addAttribute("role", role);
        return "roles/add"; // Assuming the form is the same for adding and updating
    }

    @PostMapping("/update")
    public String updateRole(@RequestParam("roleId") int id ,Model model) {
        Role role = roleService.findById(id);
        model.addAttribute("role", role);
        return "/roles/add";
    }

    @PostMapping("/delete")
    public String deleteRole(@RequestParam("roleId") int id) {
        roleService.deleteById(id);
        return "redirect:/roles/list";
    }
}
