package com.fluidcodes.crm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.fluidcodes.crm.dao.AccountsRepo;
import com.fluidcodes.crm.dao.OfficesRepo;

import com.fluidcodes.crm.entities.Accounts;
import com.fluidcodes.crm.entities.Offices;

@Service
public class AccountsService {

	@Autowired
	private AccountsRepo accountRepo;

	@Autowired
	private OfficesRepo officeRepo;

	public List<Accounts> findAll() {

		return accountRepo.findAll();
	}

	public Accounts getOne(Long id) {

		return accountRepo.getOne(id);
	}

	public Accounts findById(Long id) {

		return accountRepo.findById(id).get();

	}

	public boolean existsById(Long id) {

		return accountRepo.existsById(id);
	}

	public long count() {
		List<Accounts> usersCount = accountRepo.findAll();

		return usersCount.size();
	}

	public void deleteById(Long id) {

		if (existsById(id))
			accountRepo.deleteById(id);

	}

	public void save(Accounts newAccount, Integer id) {

		// get permissions and assign

		Offices office = null;
		Optional<Offices> o = officeRepo.findById(id);

		if (!o.isPresent()) {

			System.out.println("Office id dont exist");

		} else {

			office = o.get();
			System.out.println("Set office: " + office);
		}

		// office.getUsers().add(newUser);
		// newUser.setOffice(office);
		office.add(newAccount);
		System.out.println("Set office after add account: " + office);

		System.out.println("Set accounts after add office: " + newAccount);
		officeRepo.save(office);
		accountRepo.save(newAccount);

	}

}
