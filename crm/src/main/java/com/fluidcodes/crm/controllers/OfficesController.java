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

import com.fluidcodes.crm.models.Accounts;
import com.fluidcodes.crm.models.Offices;
import com.fluidcodes.crm.models.Users;
import com.fluidcodes.crm.services.AccountsService;
import com.fluidcodes.crm.services.OfficesService;
import com.fluidcodes.crm.services.UsersService;

@Controller
public class OfficesController {

	// wiring all repos needed for office controller
	@Autowired
	private OfficesService officesservice;
	@Autowired
	private UsersService usersservice;
	@Autowired
	private AccountsService accountsservice;

	public OfficesController(OfficesService officeservice) {
		this.officesservice = officeservice;
	}

	// once admin is requested all offices, accounts, and users are displayed
	@RequestMapping("admin")
	public String listOffices(Model modelOffices, Model modelUsers, Model modelAccounts) {

		// list of offices available
		List<Offices> listOffices = officesservice.findAll();
		modelOffices.addAttribute("listOffices", listOffices);

		// list of users available
		List<Users> listUsers = usersservice.findAll();
		modelUsers.addAttribute("listUsers", listUsers);
		// list of accounts available
		List<Accounts> listAccounts = accountsservice.findAll();
		modelAccounts.addAttribute("listAccounts", listAccounts);

		// return admin.html
		return "admin";
	}

	// issuing a new office
	@RequestMapping("newoffice")
	public String addOffice(Model model) {
		// contain all information in newOffice model
		Offices newOffice = new Offices();
		model.addAttribute("newOffice", newOffice);
		// return officeform.html
		return "officeform";
	}

	// editing an existing office with param office id
	@RequestMapping("editoffice")
	public String editOffice(@RequestParam("officeId") Integer officeId, Model model) {
		// find the office by id
		Offices editOffice = officesservice.findById(officeId);
		System.out.println("ID for Edit: " + officeId);
		System.out.println("On edit form" + editOffice);
		// load information to editOffice
		model.addAttribute("newOffice", editOffice);
		// return officeform.html
		return "officeform";
	}

	// save the office from edit or new
	@PostMapping("saveoffice")
	public String saveOffice(@Valid @ModelAttribute("newOffice") Offices newOffice, BindingResult bind) {
		// find any errors via hibernate validation
		if (bind.hasErrors()) {
			System.out.println("error count:" + bind.getErrorCount());
			System.out.println(bind.getFieldErrors());
			// if any errors return to officeform.html
			return "officeform";
		}

		System.out.println("after submit button: " + newOffice);
		// save information to db
		officesservice.save(newOffice);

		// goto admin.html
		return "redirect:/admin";
	}

	// delete office by ID
	@GetMapping("deleteoffice")
	public String deleteOffice(@RequestParam("officeId") Integer officeId) {
		officesservice.deleteById(officeId);

		// goto admin.html
		return "redirect:/admin";
	}
}
