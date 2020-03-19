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

import com.fluidcodes.crm.entities.Accounts;
import com.fluidcodes.crm.entities.Offices;
import com.fluidcodes.crm.entities.Users;
import com.fluidcodes.crm.services.AccountsService;
import com.fluidcodes.crm.services.OfficesService;
import com.fluidcodes.crm.services.UsersService;

@Controller
public class OfficesController {
	@Autowired
	private OfficesService officesservice;
	@Autowired
	private UsersService usersservice;
	@Autowired
	private AccountsService accountsservice;
	
	public OfficesController(OfficesService officeservice) {
		this.officesservice=officeservice;
	}
	
	@RequestMapping("admin")
	public String listOffices(Model modelOffices,Model modelUsers, Model modelAccounts) {
		List<Offices> listOffices = officesservice.findAll();
		modelOffices.addAttribute("listOffices", listOffices);
		
		
		List<Users> listUsers = usersservice.findAll();
		modelUsers.addAttribute("listUsers", listUsers);
		
		List<Accounts> listAccounts = accountsservice.findAll();
		modelAccounts.addAttribute("listAccounts", listAccounts);
		

		return"admin";
	}
	
	@RequestMapping("newoffice")
	public String addOffice(Model model) {
		
		Offices newOffice = new Offices();
		model.addAttribute("newOffice", newOffice);
		
		
		
		
		
		return "officeform";
	}
	
	
	@RequestMapping("editoffice")
	public String editOffice(@RequestParam("officeId") Integer officeId, Model model) {
		
		Offices editOffice = officesservice.findById(officeId);
		System.out.println("ID for Edit: "+officeId);
		System.out.println("On edit form"+editOffice);
		model.addAttribute("newOffice", editOffice);
		
		return "officeform";
	}
	
	@PostMapping("saveoffice")
	public String saveOffice(@Valid @ModelAttribute("newOffice") Offices newOffice, BindingResult bind) {

		if(bind.hasErrors()) {
			System.out.println("error count:"+bind.getErrorCount());
			return "officeform";
		}
		
		System.out.println("after submit button: "+ newOffice);
		
		officesservice.save(newOffice);
		
		
		return "redirect:/admin";
	}
	
	
	@GetMapping("deleteoffice")
	public String deleteOffice(@RequestParam("officeId") Integer officeId) {
		officesservice.deleteById(officeId);
		
		
		return "redirect:/admin";
	}
}
