package com.fluidcodes.crm.services;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;



import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fluidcodes.crm.entities.Offices;
import com.fluidcodes.crm.entities.Users;

public class MyUserDetails implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long userId;
	private Offices office;
	private String userNameEn;
	private String userNameAr;
	private Date userIdRenewalDateGorg;
	private String userIdRenewalDateHijri;
	private String userEmail;
	private String userMobile;
	private String userPass;
	private String userImgUrl;
	private String userRole;
	private Boolean userActive;
	private List<GrantedAuthority> authorities;
	

	
	public MyUserDetails(Users user) {
		this.userId = user.getUserId();
		this.office = user.getOffice();
		this.userNameEn = user.getUserNameEn();
		this.userNameAr = user.getUserNameAr();
		this.userIdRenewalDateGorg = user.getUserIdRenewalDateGorg();
		this.userIdRenewalDateHijri = user.getUserIdRenewalDateHijri();
		this.userEmail = user.getUserEmail();
		this.userMobile = user.getUserMobile();
		this.userPass = user.getUserPass();
		this.userImgUrl = user.getUserImgUrl();
		this.userRole = user.getUserRole();
		this.userActive=user.getUserActive();
		this.authorities = Arrays.stream(user.getUserRole().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		System.out.println(authorities);
		return authorities;
	}



	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return userPass;
	}



	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userEmail;
	}



	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}



	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}



	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}



	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}