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

import com.fluidcodes.crm.services.AccountsService;
import com.fluidcodes.crm.services.OfficesService;

@Controller
public class AccountsController {
	@Autowired
	private OfficesService officesservice;
	@Autowired
	private AccountsService accountsservice;

	public AccountsController(AccountsService accountsservice) {
		this.accountsservice=accountsservice;
	}
	

	
	@RequestMapping("newaccount")
	public String addAccount(Model modelAccounts, Model modelOffices, Model office) {
		Accounts newAccount = new Accounts();
		System.out.println("new account before add"+newAccount);
		List<Offices> officeInfo = officesservice.findAll();
		
		
		
		
		modelOffices.addAttribute("listOfficesAva",officeInfo);
		modelAccounts.addAttribute("newAccount", newAccount);
		
		
		
		Offices off = new Offices();
		off.setOfficeId(1);
		office.addAttribute("off", off);
		System.out.println("off id "+off.getOfficeId());
		officesservice.findById(off.getOfficeId());
		System.out.println("new account after  add"+newAccount);
		
		return "accountform";
	}
	
	
	@RequestMapping("editaccount")
	public String editAccount(@RequestParam("accountId") Long accountId, Model modelAccounts, Model modelOffices, Model office) {
		List<Offices> officeInfo = officesservice.findAll();
		modelOffices.addAttribute("listOfficesAva",officeInfo);
		
		
		Accounts editAccount = accountsservice.findById(accountId);
		Offices accountOffice = editAccount.getOffice();
		office.addAttribute("off", accountOffice);
		System.out.println("ID for Account Edit: "+accountId);
		System.out.println("On Account edit form"+editAccount);
		modelOffices.addAttribute("listOfficesAva",officeInfo);
		modelAccounts.addAttribute("newAccount", editAccount);
		
		return "accountform";
	}
	
	@PostMapping("saveaccount")
	public String saveAccount(@Valid @ModelAttribute("newAccount") Accounts newAccount, BindingResult bind, Model modelOffices, @ModelAttribute("off") Offices off) {
		System.out.println(newAccount);
		if(bind.hasErrors()) {
			System.out.println("error count:"+bind.getErrorCount());
			System.out.println(bind.getFieldErrors());
			List<Offices> officeInfo = officesservice.findAll();
			modelOffices.addAttribute("listOfficesAva",officeInfo);
			System.out.println("Error after submit button: "+ newAccount);
			return "accountform";
		}
		
	
		
		accountsservice.save(newAccount, off.getOfficeId());
		
		return "redirect:/admin";
	}
	
	
	@GetMapping("deleteaccount")
	public String deleteAccount(@RequestParam("accountId") Long accountId) {
		accountsservice.deleteById(accountId);
		
		
		return "redirect:/admin";
	}
}
