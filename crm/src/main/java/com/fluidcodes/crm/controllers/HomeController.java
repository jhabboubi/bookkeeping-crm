package com.fluidcodes.crm.controllers;

//import java.util.List;
//import java.util.Optional;

//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * Home Controller to view information 
 */

@Controller
public class HomeController {
	
	@GetMapping("/log")
	public String logging() {
		return "log";
	}
}
