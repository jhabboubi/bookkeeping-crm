package com.fluidcodes.crm.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fluidcodes.crm.dao.UsersRepo;
import com.fluidcodes.crm.entities.Users;

@Service
public class MyUserDeatilsService implements UserDetailsService {

	// created a query to find user by email
	@Autowired
	UsersRepo usersRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// find if user exist
		Optional<Users> user = usersRepo.findByUserEmail(email);
		// throw an error if not
		user.orElseThrow(() -> new UsernameNotFoundException("Not Found: " + email));
		// get me the user
		return user.map(MyUserDetails::new).get();
	}

}
