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

	// wiring office,account services for use in controller
	@Autowired
	private OfficesService officesservice;
	@Autowired
	private AccountsService accountsservice;

	public AccountsController(AccountsService accountsservice) {
		this.accountsservice = accountsservice;
	}

	// requesting controller for new account
	@RequestMapping("newaccount")
	public String addAccount(Model modelAccounts, Model modelOffices, Model office) {
		// new account object to save information to it
		Accounts newAccount = new Accounts();
		// showing all offices available when issuing a new account
		System.out.println("new account before add" + newAccount);
		List<Offices> officeInfo = officesservice.findAll();
		// model to save all offices available to show on html
		modelOffices.addAttribute("listOfficesAva", officeInfo);
		// container to save new account information
		modelAccounts.addAttribute("newAccount", newAccount);

		// required to choose the office desired for new account
		Offices off = new Offices();
		off.setOfficeId(1);
		office.addAttribute("off", off);
		System.out.println("off id " + off.getOfficeId());
		officesservice.findById(off.getOfficeId());
		System.out.println("new account after  add" + newAccount);
		// return page accountform.html
		return "accountform";
	}

	// edit existing account with param account ID to modify
	@RequestMapping("editaccount")
	public String editAccount(@RequestParam("accountId") Long accountId, Model modelAccounts, Model modelOffices,
			Model office) {
		// required to show all offices available
		List<Offices> officeInfo = officesservice.findAll();
		modelOffices.addAttribute("listOfficesAva", officeInfo);
		// find the account by ID using account repo
		Accounts editAccount = accountsservice.findById(accountId);
		Offices accountOffice = editAccount.getOffice();
		office.addAttribute("off", accountOffice);
		System.out.println("ID for Account Edit: " + accountId);
		System.out.println("On Account edit form" + editAccount);
		modelOffices.addAttribute("listOfficesAva", officeInfo);
		// show on form edit account information
		modelAccounts.addAttribute("newAccount", editAccount);
		// return accountform.html
		return "accountform";
	}

	// save all informtion from edit or new account
	@PostMapping("saveaccount")
	public String saveAccount(@Valid @ModelAttribute("newAccount") Accounts newAccount, BindingResult bind,
			Model modelOffices, @ModelAttribute("off") Offices off) {

		System.out.println(newAccount);
		// check all fields for errors via hibernate validation
		if (bind.hasErrors()) {
			System.out.println("error count:" + bind.getErrorCount());
			System.out.println(bind.getFieldErrors());
			List<Offices> officeInfo = officesservice.findAll();
			modelOffices.addAttribute("listOfficesAva", officeInfo);
			System.out.println("Error after submit button: " + newAccount);

			// if there are errors return to accountform.html
			return "accountform";
		}

		// no errors then save account to office
		accountsservice.save(newAccount, off.getOfficeId());
		// once done return to admin.html
		return "redirect:/admin";
	}

	// delete an account! will not delete all transactions related
	@GetMapping("deleteaccount")
	public String deleteAccount(@RequestParam("accountId") Long accountId) {
		accountsservice.deleteById(accountId);

		// once done goto admin.html
		return "redirect:/admin";
	}
}
