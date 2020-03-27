package com.fluidcodes.crm.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	/*
	 * 
	 * Spring Security Config file
	 * 
	 */

	// wire services needed
	@Autowired
	UserDetailsService userDetailsService;

	// method to config user details class
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);

	}

	// method to encode password in db
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(getPasswordEncoder());
		return authenticationProvider;
	}

	// method to instantiate new object of BCrypt
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

// if saving password as string to db	
//	@Bean
//	public PasswordEncoder getPasswordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}

	// main config method to allow users to which page
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/admin/**", "/expenses/**", "/income/**", "/newoffice/**", "/editoffice/**",
						"/deleteoffice/**", "/saveoffice/**", "/newuser/**", "/edituser/**", "/deleteuser/**",
						"/newaccount/**", "/editaccount/**", "/saveaccount/**", "/deleteaccount/**", "/newtrans/**",
						"/edittrans/**", "/savetrans/**", "/deletetrans/**")
				.hasRole("ADMIN").antMatchers("/").hasAnyRole("ADMIN", "EMPLOYEE").and().httpBasic()

				.and()

				.csrf().disable().formLogin().loginPage("/login").permitAll().loginProcessingUrl("/login/authenticate")
				.failureUrl("/login?error=true").defaultSuccessUrl("/").usernameParameter("userEmail")
				.passwordParameter("userPass")

				.and()

				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").and()
				.exceptionHandling().accessDeniedPage("/");

	}

	// pages not to follow the security
	@Override
	public void configure(final WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

}
