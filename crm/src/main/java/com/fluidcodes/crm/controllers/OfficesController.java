package com.fluidcodes.crm.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.fluidcodes.crm.entities.Offices;
import com.fluidcodes.crm.services.OfficesService;

@Controller
public class OfficesController {

	private OfficesService service;
	
	public OfficesController(OfficesService service) {
		this.service=service;
	}
	
	@GetMapping("admin")
	public String listOffices(Model model) {
		List<Offices> listOffices = service.findAll();
		model.addAttribute("listOffices", listOffices);
		
		return"admin";
	}
	
	@GetMapping("newoffice")
	public String addOffice(Model model) {
		Offices newOffice = new Offices();
		model.addAttribute("newOffice", newOffice);
		
		return "newoffice";
	}
	
	@GetMapping("saveoffice")
	public String saveOffice(@ModelAttribute("newOffice") Offices newOffice) {
		service.save(newOffice);
		
		return "redirect:/admin";
	}
}
