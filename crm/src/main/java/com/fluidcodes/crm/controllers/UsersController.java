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
	

	
	@RequestMapping("newuser")
	public String adduser(Model modelUsers, Model modelOffices, Model office) {
		Users newUser = new Users();
		System.out.println("new user before add"+newUser);
		List<Offices> officeInfo = officesservice.findAll();
		
		
		
		
		modelOffices.addAttribute("listOfficesAva",officeInfo);
		modelUsers.addAttribute("newUser", newUser);
		
		
		
		Offices off = new Offices();
		off.setOfficeId(1);
		office.addAttribute("off", off);
		System.out.println("off id "+off.getOfficeId());
		officesservice.findById(off.getOfficeId());
		System.out.println("new user after  add"+newUser);
		
		return "userform";
	}
	
	
	@RequestMapping("edituser")
	public String editUser(@RequestParam("userId") Long userId, Model modelUsers, Model modelOffices, Model office) {
		List<Offices> officeInfo = officesservice.findAll();
		modelOffices.addAttribute("listOfficesAva",officeInfo);
		
		
		Users editUser = usersservice.findById(userId);
		Offices userOffice = editUser.getOffice();
		office.addAttribute("off", userOffice);
		System.out.println("ID for User Edit: "+userId);
		System.out.println("On User edit form"+editUser);
		modelOffices.addAttribute("listOfficesAva",officeInfo);
		modelUsers.addAttribute("newUser", editUser);
		
		return "userform";
	}
	
	@PostMapping("saveuser")
	public String saveUser(@Valid @ModelAttribute("newUser") Users newUser, BindingResult bind, Model modelOffices, @ModelAttribute("off") Offices off) {
		System.out.println(newUser);
		if(bind.hasErrors()) {
			System.out.println("error count:"+bind.getErrorCount());
			System.out.println(bind.getFieldErrors());
			List<Offices> officeInfo = officesservice.findAll();
			modelOffices.addAttribute("listOfficesAva",officeInfo);
			System.out.println("Error after submit button: "+ newUser);
			return "userform";
		}
		
	
		
		usersservice.save(newUser, off.getOfficeId());
		
		return "redirect:/admin";
	}
	
	
	@GetMapping("deleteuser")
	public String deleteUser(@RequestParam("userId") Long userId) {
		usersservice.deleteById(userId);
		
		
		return "redirect:/admin";
	}
}
