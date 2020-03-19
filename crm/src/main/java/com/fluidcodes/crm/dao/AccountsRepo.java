package com.fluidcodes.crm.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.fluidcodes.crm.entities.Accounts;


public interface AccountsRepo extends JpaRepository<Accounts,Long>{

	
		
}
