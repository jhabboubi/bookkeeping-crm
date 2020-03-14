package com.fluidcodes.crm.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.fluidcodes.crm.dao.OfficesRepo;
import com.fluidcodes.crm.entities.Offices;
@Service
public class OfficesService  {

	@Autowired
	private OfficesRepo repo;
	
	public List<Offices> findAll() {
		
		return repo.findAll();
	}


	public Offices getOne(Integer id) {
		
		return repo.getOne(id);
	}

	public Offices findById(Integer id) {
		if(existsById(id))
			return repo.findById(id).get();
		else
		return null;
	}

	public boolean existsById(Integer id) {

		return repo.existsById(id);
	}

	public long count() {
		List<Offices> officeCount = repo.findAll();
		
		return (int)officeCount.size();
	}

	public void deleteById(Integer id) {
		
		if(existsById(id))
			repo.deleteById(id);

	}
	public void save(Offices newOffice) {
		repo.save(newOffice);
	}
	



}
