package com.fluidcodes.crm.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.fluidcodes.crm.entities.Transactions;


public interface TransactionsRepo extends JpaRepository<Transactions,Long>{

	
		
}
