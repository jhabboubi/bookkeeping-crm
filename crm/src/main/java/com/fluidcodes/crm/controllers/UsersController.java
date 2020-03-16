package com.fluidcodes.crm.controllers;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.fluidcodes.crm.services.OfficesService;
import com.fluidcodes.crm.services.UsersService;

@Controller
public class UsersController {
	@Autowired
	private OfficesService officesservice;
	@Autowired
	private UsersService usersservice;

	public UsersController(UsersService usersservice) {
		this.usersservice=usersservice;
	}
	
//	@RequestMapping("userlistadmin")
//	public String listUsers(Model model) {
//		List<Users> listUsers = service.findAll();
//		model.addAttribute("listUsers", listUsers);
//		
//		return"admin";
//	}
	
	@RequestMapping("newuser")
	public String adduser(Model modelUsers, Model modelOffices) {
		List<Offices> officeInfo = officesservice.findAll();
		Users newUser = new Users();
		
		modelOffices.addAttribute("listOfficesAva",officeInfo);
		modelUsers.addAttribute("newUser", newUser);
		
		
		return "userform";
	}
	
	
	@RequestMapping("edituser")
	public String editUser(@RequestParam("userId") Long userId, Model model) {
		
		Users editUser = usersservice.findById(userId);
		System.out.println("ID for User Edit: "+userId);
		System.out.println("On User edit form"+editUser);
		model.addAttribute("newUser", editUser);
		
		return "userform";
	}
	
	@PostMapping("saveuser")
	public String saveUser(@Valid @ModelAttribute("newUser") Users newUser, BindingResult bind) {

		if(bind.hasErrors()) {
			System.out.println("error count:"+bind.getErrorCount());
			return "userform";
		}
		
		System.out.println("after submit button: "+ newUser);
		
		usersservice.save(newUser);
		
		
		return "redirect:/admin";
	}
	
	
	@GetMapping("deleteuser")
	public String deleteUser(@RequestParam("userId") Long userId) {
		usersservice.deleteById(userId);
		
		
		return "redirect:/admin";
	}
}
