package com.fluidcodes.crm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.fluidcodes.crm.dao.UsersRepo;

import lombok.extern.log4j.Log4j2;

/* Date: Mar 10, 2020 to Mar 27, 2020
 * Creater : Jafer Mohammed Alhaboubi
 * Ms Bookkeeping is a application based on a company need for an ERP system
 */

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UsersRepo.class)
@Slf4j
public class CrmApplication {

	// enabling thymeleaf sec for view controlling and session information
	@Bean
	public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver, SpringSecurityDialect sec) {
		final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
		templateEngine.addDialect(sec); // Enable use of "sec"
		return templateEngine;
	}

	// enabling thymeleaf sec for view controlling and session information
	@Bean
	public SpringSecurityDialect securityDialect() {
		return new SpringSecurityDialect();
	}

	// Main method
	public static void main(String[] args) {
		SpringApplication.run(CrmApplication.class, args);
		log.info("SpringBoot Main Method...");
	}

}
