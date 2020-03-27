package com.fluidcodes.crm.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fluidcodes.crm.entities.Users;

	// interface extending spring jpa
public interface UsersRepo extends JpaRepository<Users, Long> {
	// custom query for finding by email
	Optional<Users> findByUserEmail(String userEmail);

}
