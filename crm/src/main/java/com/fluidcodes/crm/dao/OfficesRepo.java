package com.fluidcodes.crm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fluidcodes.crm.models.Offices;
//interface extending spring jpa
public interface OfficesRepo extends JpaRepository<Offices, Integer> {

}
