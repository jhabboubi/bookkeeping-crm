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
import com.fluidcodes.crm.entities.Transactions;
import com.fluidcodes.crm.services.AccountsService;
import com.fluidcodes.crm.services.TransactionsService;

@Controller
public class TransactionsController {
	@Autowired
	private AccountsService accountsservice;
	@Autowired
	private TransactionsService transservice;

	public TransactionsController(TransactionsService transservice) {
		this.transservice=transservice;
	}
	
	@RequestMapping("expenses")
	public String listExpenses(Model modelExpenses) {
		List<Transactions> listExpenses = transservice.findAll();
		for(int i=0;i<listExpenses.size();i++) {
			if(listExpenses.get(i).getTransIsCredit()) {
				listExpenses.remove(i);
			}
				
		}
		
		modelExpenses.addAttribute("listExpenses", listExpenses);
		
	
		

		return"expenses";
	}
	
	
	@RequestMapping("income")
	public String listIncome(Model modelIncome) {
		List<Transactions> listIncome = transservice.findAll();
		for(int i=0;i<listIncome.size();i++) {
			if(!listIncome.get(i).getTransIsCredit()) {
				listIncome.remove(i);
			}
				
		}
		
		modelIncome.addAttribute("listIncome", listIncome);
		
	
		

		return"income";
	}
	
	
	
	
	@RequestMapping("newtrans")
	public String addTrans(Model modelTrans, Model modelAccounts, Model account) {
		Transactions newTrans = new Transactions();
		System.out.println("new trans before add"+newTrans);
		List<Accounts> accountInfo = accountsservice.findAll();
		
		
		
		
		modelAccounts.addAttribute("listAccountsAva",accountInfo);
		modelTrans.addAttribute("newTrans", newTrans);
		
		
		
		Accounts acc = new Accounts();
		acc.setAccountId(accountInfo.get(0).getAccountId());
		account.addAttribute("acc", acc);
		System.out.println("Account model after  setting"+account);
		System.out.println("acc id "+acc);
		accountsservice.findById(acc.getAccountId());
		System.out.println("new trans after  add"+newTrans);
		
		return "transform";
	}
	
	
	@RequestMapping("edittrans")
	public String editTrans(@RequestParam("transId") Long transId, Model modelTrans, Model modelAccounts, Model account) {
		List<Accounts> accountInfo = accountsservice.findAll();
		modelAccounts.addAttribute("listAccountsAva",accountInfo);
		
		
		Transactions editTrans = transservice.findById(transId);
		Accounts transAccount = editTrans.getAccount();
		account.addAttribute("acc", transAccount);
		System.out.println("ID for Trans Edit: "+transId);
		System.out.println("On Trans edit form"+editTrans);
		modelAccounts.addAttribute("listAccountsAva",accountInfo);
		modelTrans.addAttribute("newTrans", editTrans);
		
		return "transform";
	}
	
	@PostMapping("savetrans")
	public String saveTrans(@Valid @ModelAttribute("newTrans") Transactions newTrans, BindingResult bind, Model modelAccounts, @ModelAttribute("acc") Accounts acc) {
		System.out.println(newTrans);
		if(bind.hasErrors()) {
			System.out.println("error count:"+bind.getErrorCount());
			System.out.println(bind.getFieldErrors());
			List<Accounts> accountInfo = accountsservice.findAll();
			modelAccounts.addAttribute("listAccountsAva",accountInfo);
			System.out.println("Error after submit button: "+ newTrans);
			return "transform";
		}
		
	
		System.out.println("before save: "+ newTrans);
		
		
		transservice.save(newTrans, acc.getAccountId());
		
		if(newTrans.getTransIsCredit()) 
		return "redirect:/income";
		else
		return "redirect:/expenses";
	}
	
	
	@GetMapping("deletetrans")
	public String deleteTrans(@RequestParam("transId") Long transId) {
		transservice.deleteById(transId);
		
		
		return "redirect:/expenses";
	}
}
