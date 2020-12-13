package com.fluidcodes.crm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fluidcodes.crm.models.Transactions;

//interface extending spring jpa
public interface TransactionsRepo extends JpaRepository<Transactions, Long> {

}
