package com.fluidcodes.crm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fluidcodes.crm.dao.OfficesRepo;
import com.fluidcodes.crm.dao.UsersRepo;
import com.fluidcodes.crm.entities.Offices;
import com.fluidcodes.crm.entities.Users;

@Service
public class UsersService {
	
	@Autowired
    private BCryptPasswordEncoder encode;

	@Autowired
	private UsersRepo userRepo;

	@Autowired
	private OfficesRepo officeRepo;

	public List<Users> findAll() {

		return userRepo.findAll();
	}

	public Users getOne(Long id) {

		return userRepo.getOne(id);
	}

	public Users findById(Long id) {

		return userRepo.findById(id).get();

	}

	public boolean existsById(Long id) {

		return userRepo.existsById(id);
	}

	public long count() {
		List<Users> usersCount = userRepo.findAll();

		return usersCount.size();
	}

	public void deleteById(Long id) {

		if (existsById(id))
			userRepo.deleteById(id);

	}

	public void save(Users newUser, Integer id) {

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
		String pass = newUser.getUserPass();
		String encrPass = encode.encode(pass);
		newUser.setUserPass(encrPass);
		office.add(newUser);
		System.out.println("Set office after add user: " + office);

		System.out.println("Set users after add office: " + newUser);
		officeRepo.save(office);
		userRepo.save(newUser);

	}

}
