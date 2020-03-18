package com.fluidcodes.crm.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fluidcodes.crm.entities.Users;

public interface UsersRepo extends JpaRepository<Users,Long>{

	
		
}
