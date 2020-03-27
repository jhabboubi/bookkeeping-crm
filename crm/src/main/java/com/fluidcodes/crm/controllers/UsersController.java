package com.fluidcodes.crm.controllers;


import java.util.List;
import java.util.Optional;

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

import com.fluidcodes.crm.dao.UsersRepo;
import com.fluidcodes.crm.entities.Offices;
import com.fluidcodes.crm.entities.Users;
import com.fluidcodes.crm.services.OfficesService;
import com.fluidcodes.crm.services.SecurityUtils;
import com.fluidcodes.crm.services.UsersService;

@Controller
public class UsersController {
	@Autowired
	private OfficesService officesservice;
	@Autowired
	private UsersService usersservice;
	
	@Autowired
	private UsersRepo usersrepo;

	public UsersController(UsersService usersservice) {
		this.usersservice=usersservice;
	}
	
	 @GetMapping("/login")
	    public String showLogin(Model model) {
	        Users user = new Users();
	        model.addAttribute("user", user);
	      
	        return "login";
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
	
	
	
	@RequestMapping("settings")
	public String editCurrentUser( Model user, Model office) {
		Users currentUser =null;
		
		
		Optional<Users> o = usersrepo.findByUserEmail(SecurityUtils.getUserName());
		if(o.isPresent()) 
			currentUser = o.get();
		else
			System.out.println("No user!");
		
		Offices userOffice = currentUser.getOffice();
		System.out.println("AuthenticationPrincipal"+currentUser);
		user.addAttribute("newUser", currentUser);
		
		office.addAttribute("off", userOffice);
	
		
		return "currentuserform";
	}
	
	
	@PostMapping("saveuser")
	public String saveUser(@Valid @ModelAttribute("newUser") Users newUser, BindingResult bind, Model modelOffices, @ModelAttribute("off") Offices off) {
		
		Users now = new Users();
		Optional<Users> o = usersrepo.findByUserEmail(SecurityUtils.getUserName());
		if(o.isPresent()) {
			 now = o.get();
		}
		
		System.out.println(newUser);
		if(bind.hasErrors()) {
			System.out.println("error count:"+bind.getErrorCount());
			System.out.println(bind.getFieldErrors());
			List<Offices> officeInfo = officesservice.findAll();
			modelOffices.addAttribute("listOfficesAva",officeInfo);
			System.out.println("Error after submit button: "+ newUser);
			if(now.getUserRole().equals("ROLE_ADMIN"))
			return "userform";
			else
				return"currentuserform";
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
