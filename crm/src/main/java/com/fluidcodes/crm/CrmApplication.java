package com.fluidcodes.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.fluidcodes.crm.dao.UsersRepo;


@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UsersRepo.class)
public class CrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrmApplication.class, args);
	}

}
