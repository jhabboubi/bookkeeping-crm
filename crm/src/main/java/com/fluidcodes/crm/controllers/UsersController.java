package com.fluidcodes.crm.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fluidcodes.crm.entities.Offices;
import com.fluidcodes.crm.entities.Users;

import com.fluidcodes.crm.services.UsersService;

@Controller
public class UsersController {

	private UsersService service;
	
	public UsersController(UsersService service) {
		this.service=service;
	}
	
	@RequestMapping("userlistadmin")
	public String listUsers(Model model) {
		List<Users> listUsers = service.findAll();
		model.addAttribute("listUsers", listUsers);
		
		return"admin";
	}
	
	@RequestMapping("newuser")
	public String adduser(Model model) {
		
		Users newUser = new Users();
		
	
		model.addAttribute("newUser", newUser);
		
		
		return "userform";
	}
	
	
	@RequestMapping("edituser")
	public String editUser(@RequestParam("userId") Long userId, Model model) {
		
		Users editUser = service.findById(userId);
		System.out.println("ID for User Edit: "+userId);
		System.out.println("On User edit form"+editUser);
		model.addAttribute("newUser", editUser);
		
		return "userform";
	}
	
	@PostMapping("saveuser")
	public String saveUser(@ModelAttribute("newUser") Users newUser) {

		
		
		System.out.println("after submit button: "+ newUser);
		
		service.save(newUser);
		
		
		return "redirect:/admin";
	}
	
	
	@GetMapping("deleteuser")
	public String deleteUser(@RequestParam("userId") Long userId) {
		service.deleteById(userId);
		
		
		return "redirect:/admin";
	}
}
