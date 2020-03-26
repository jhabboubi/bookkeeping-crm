package com.fluidcodes.crm.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fluidcodes.crm.dao.UsersRepo;
import com.fluidcodes.crm.entities.Accounts;
import com.fluidcodes.crm.entities.Offices;
import com.fluidcodes.crm.entities.Users;
import com.fluidcodes.crm.services.AccountsService;
import com.fluidcodes.crm.services.OfficesService;
import com.fluidcodes.crm.services.SecurityUtils;
import com.fluidcodes.crm.services.UsersService;

@Controller
public class HomeController {
	@Autowired
	private OfficesService officesservice;
	@Autowired
	private UsersService usersservice;
	@Autowired
	private AccountsService accountsservice;
	
	@Autowired
	private UsersRepo usersrepo;
	
	public HomeController(OfficesService officeservice) {
		this.officesservice=officeservice;
	}
	
	
	@ControllerAdvice
	public class CurrentUserControllerAdvice {
	    @ModelAttribute("currentUser")
	    public UserDetails getCurrentUser(Authentication authentication) {
	        return (authentication == null) ? null : (UserDetails) authentication.getPrincipal();
	    }
	}
	
	
	
	
	@RequestMapping("/")
	public String showUser( Model model) {
		
		
		Users currentUser =null;
		Optional<Users> o = usersrepo.findByUserEmail(SecurityUtils.getUserName());
		if(o.isPresent()) 
			currentUser = o.get();
		else
			System.out.println("No user!");
		
		System.out.println("AuthenticationPrincipal"+currentUser);
		model.addAttribute("currentUser", currentUser);
	

		return"index";
	}
	
	
}
