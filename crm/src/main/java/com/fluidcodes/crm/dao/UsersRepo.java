package com.fluidcodes.crm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fluidcodes.crm.entities.Users;

public interface UsersRepo extends JpaRepository<Users,Long>{

}
