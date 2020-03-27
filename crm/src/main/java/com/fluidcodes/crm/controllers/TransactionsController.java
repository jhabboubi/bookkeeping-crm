package com.fluidcodes.crm.controllers;

import java.util.ArrayList;
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

	// wiring all repos needed
	@Autowired
	private AccountsService accountsservice;
	@Autowired
	private TransactionsService transservice;

	public TransactionsController(TransactionsService transservice) {
		this.transservice = transservice;
	}

	// requesting expenses
	@RequestMapping("expenses")
	public String listExpenses(Model modelExpenses) {
		// find all transaction that are expenses
		List<Transactions> all = transservice.findAll();
		List<Transactions> listExpenses = new ArrayList<Transactions>();
		System.out.println("expense: "+all);
		for (int i = 0; i < all.size(); i++) {
			if (all.get(i).getTransIsCredit() == false) {
				listExpenses.add(all.get(i));
			}

		}
		System.out.println("after expense: "+listExpenses);
		// load all expenses to listExpenses
		modelExpenses.addAttribute("listExpenses", listExpenses);
		// goto expenses.html
		return "expenses";
	}

	// requesting income
	@RequestMapping("income")
	public String listIncome(Model modelIncome) {

		// find all transaction that are income
		List<Transactions> all = transservice.findAll();
		List<Transactions> listIncome = new ArrayList<Transactions>();
		System.out.println(all.size());
		System.out.println("income: "+all);
		for (int i = 0; i < all.size(); i++) {
			
			if (all.get(i).getTransIsCredit()) {
				listIncome.add(all.get(i));
			}

		}
		System.out.println("after income: "+listIncome);
		// load all expenses to listIncome
		modelIncome.addAttribute("listIncome", listIncome);
		// goto income.html
		return "income";
	}

	// issuing a new transaction
	@RequestMapping("newtrans")
	public String addTrans(Model modelTrans, Model modelAccounts, Model account) {
		// new object of Transactions
		Transactions newTrans = new Transactions();
		System.out.println("new trans before add" + newTrans);
		// find all accounts available
		List<Accounts> accountInfo = accountsservice.findAll();
		// contain all accounts available in listAccountsAva
		modelAccounts.addAttribute("listAccountsAva", accountInfo);
		// contain new transaction in newTrans
		modelTrans.addAttribute("newTrans", newTrans);

		// new object of accounts
		Accounts acc = new Accounts();
		// set it to the first account as a place holder until user choose the account
		// desired
		acc.setAccountId(accountInfo.get(0).getAccountId());
		account.addAttribute("acc", acc);
		System.out.println("Account model after  setting" + account);
		System.out.println("acc id " + acc);
		// find account by id
		accountsservice.findById(acc.getAccountId());
		System.out.println("new trans after  add" + newTrans);
		// goto transform.html
		return "transform";
	}

	// edit existing transaction via param trans ID
	@RequestMapping("edittrans")
	public String editTrans(@RequestParam("transId") Long transId, Model modelTrans, Model modelAccounts,
			Model account) {

		// find all accounts available
		List<Accounts> accountInfo = accountsservice.findAll();
		modelAccounts.addAttribute("listAccountsAva", accountInfo);

		// find transaction by ID
		Transactions editTrans = transservice.findById(transId);
		// save transaction account to reuse later
		Accounts transAccount = editTrans.getAccount();
		// contain informaton in acc
		account.addAttribute("acc", transAccount);
		System.out.println("ID for Trans Edit: " + transId);
		System.out.println("On Trans edit form" + editTrans);
		// contain all accounts in listAccountsAva
		modelAccounts.addAttribute("listAccountsAva", accountInfo);
		// contain edit transaction in newTrans
		modelTrans.addAttribute("newTrans", editTrans);
		// goto transform.html
		return "transform";
	}

	// saving the new or edit transaction
	@PostMapping("savetrans")
	public String saveTrans(@Valid @ModelAttribute("newTrans") Transactions newTrans, BindingResult bind,
			Model modelAccounts, @ModelAttribute("acc") Accounts acc) {
		System.out.println(newTrans);
		// Validate form fields
		if (bind.hasErrors()) {
			System.out.println("error count:" + bind.getErrorCount());
			System.out.println(bind.getFieldErrors());
			List<Accounts> accountInfo = accountsservice.findAll();
			modelAccounts.addAttribute("listAccountsAva", accountInfo);
			System.out.println("Error after submit button: " + newTrans);
			// if errors goto transform.html
			return "transform";
		}
		System.out.println("before save: " + newTrans);

		// save transaction
		transservice.save(newTrans, acc.getAccountId());
		// if transaction is income goto income.html, else if expenses goto
		// expenses.html
		if (newTrans.getTransIsCredit())
			return "redirect:/income";
		else
			return "redirect:/expenses";
	}

	// delete a transaction via param ID
	@GetMapping("deletetrans")
	public String deleteTrans(@RequestParam("transId") Long transId) {
		transservice.deleteById(transId);

		// goto expenses.html
		return "redirect:/expenses";
	}
}
