package com.fluidcodes.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.fluidcodes.crm.dao.UsersRepo;


@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UsersRepo.class)
public class CrmApplication {
	
	@Bean
	public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver, SpringSecurityDialect sec) {
	    final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	    templateEngine.setTemplateResolver(templateResolver);
	    templateEngine.addDialect(sec); // Enable use of "sec"
	    return templateEngine;
	}
	@Bean
    public SpringSecurityDialect securityDialect() {
        return new SpringSecurityDialect();
    }

	public static void main(String[] args) {
		SpringApplication.run(CrmApplication.class, args);
	}

}
