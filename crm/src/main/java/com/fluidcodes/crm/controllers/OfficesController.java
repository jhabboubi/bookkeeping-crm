package com.fluidcodes.crm.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fluidcodes.crm.entities.Offices;
import com.fluidcodes.crm.services.OfficesService;

@Controller
public class OfficesController {

	private OfficesService service;
	
	public OfficesController(OfficesService service) {
		this.service=service;
	}
	
	@RequestMapping("admin")
	public String listOffices(Model model) {
		List<Offices> listOffices = service.findAll();
		model.addAttribute("listOffices", listOffices);
		
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
		
		Offices editOffice = service.findById(officeId);
		System.out.println("ID for Edit: "+officeId);
		System.out.println("On edit form"+editOffice);
		model.addAttribute("newOffice", editOffice);
		
		return "officeform";
	}
	
	@PostMapping("saveoffice")
	public String saveOffice(@ModelAttribute("newOffice") Offices newOffice) {

		
		
		System.out.println("after submit button: "+ newOffice);
		
		service.save(newOffice);
		
		
		return "redirect:/admin";
	}
	
	
	@GetMapping("deleteoffice")
	public String deleteOffice(@RequestParam("officeId") Integer officeId) {
		service.deleteById(officeId);
		
		
		return "redirect:/admin";
	}
}
