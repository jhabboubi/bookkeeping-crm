package com.fluidcodes.crm.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.fluidcodes.crm.dao.UsersRepo;

import com.fluidcodes.crm.entities.Users;
@Service
public class UsersService  {

	@Autowired
	private UsersRepo repo;
	
	public List<Users> findAll() {
		
		return repo.findAll();
	}


	public Users getOne(Long id) {
		
		return repo.getOne(id);
	}

	public Users findById(Long id) {
		
			return repo.findById(id).get();
		
	}

	public boolean existsById(Long id) {

		return repo.existsById(id);
	}

	public long count() {
		List<Users> officeCount = repo.findAll();
		
		return officeCount.size();
	}

	public void deleteById(Long id) {
		
		if(existsById(id))
			repo.deleteById(id);

	}
	public void save(Users newUser) {
		repo.save(newUser);
		
	}
	



}
