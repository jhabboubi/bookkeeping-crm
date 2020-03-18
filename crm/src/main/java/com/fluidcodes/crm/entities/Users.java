package com.fluidcodes.crm.entities;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class Users {

	
		// fields
	@Id
	@Column(name="userId")
	@NotNull(message="Field is required!")
	private Long userId;
	
	
	@ManyToOne(fetch=FetchType.LAZY,cascade= {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name = "officeId")
	private Offices office;
	
	@Column(name="userNameEn")
	@NotNull(message="Field is required!")
	@Size(min=2, max=100,message = "Field must be more than two letters!")
	private String userNameEn;
	
	
	@Column(name="userNameAr")
	@NotNull(message="Field is required!")
	@Size(min=2, max=100,message = "Field must be more than two letters!")
	private String userNameAr;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="userIdRenewalDateGorg")
	@NotNull(message="Field is required!")
	private Date userIdRenewalDateGorg;
	
	
	@Column(name="userIdRenewalDateHijri")
	private String userIdRenewalDateHijri;
	
	
	@Column(name="userEmail", unique=true)
	@NotNull(message="Field is required!")
	@Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$", message="Ex: joe@aol.com or joe@wrox.com.sa")
	private String userEmail;
	
	
	@Column(name="userMobile")
	@NotNull(message="Field is required!")
	@Pattern(regexp = "^(1?(-?\\d{3})-?)?(\\d{3})(-?\\d{4})$",message = "Allows 7,10,11# optional hyphens")
	private String userMobile;
	
	
	@Column(name="userPass")
	@NotNull(message="Field is required!")
	@Pattern(regexp = "^[a-zA-Z]\\w{3,14}$", message="Begin with a letter, 4-15 characters, and underscore may be used")
	private String userPass;
	
	
	@Column(name="userImgUrl")
	private String userImgUrl;
	
	
	@Column(name="userRole")
	@NotNull(message="Field is required!")
	private String userRole;

	//default constructor
	
	public Users() {
		System.out.println("Users default constructor called!");
	}
	
	


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	

	public String getUserNameEn() {
		return userNameEn;
	}

	public void setUserNameEn(String userNameEn) {
		this.userNameEn = userNameEn;
	}

	public String getUserNameAr() {
		return userNameAr;
	}

	public void setUserNameAr(String userNameAr) {
		this.userNameAr = userNameAr;
	}

	public Date getUserIdRenewalDateGorg() {
		return userIdRenewalDateGorg;
	}

	public void setUserIdRenewalDateGorg(Date userIdRenewalDateGorg) {
		this.userIdRenewalDateGorg = userIdRenewalDateGorg;
	}

	public String getUserIdRenewalDateHijri() {
		return userIdRenewalDateHijri;
	}

	public void setUserIdRenewalDateHijri(String userIdRenewalDateHijri) {
		this.userIdRenewalDateHijri = userIdRenewalDateHijri;
	}

	

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getUserImgUrl() {
		return userImgUrl;
	}

	public void setUserImgUrl(String userImgUrl) {
		this.userImgUrl = userImgUrl;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	



	public String getUserEmail() {
		return userEmail;
	}




	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}




	public String getUserMobile() {
		return userMobile;
	}




	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}




	




	public Offices getOffice() {
		return office;
	}




	public void setOffice(Offices office) {
		this.office = office;
	}




	@Override
	public String toString() {
		return "Users [userId=" + userId + ", office=" + office + ", userNameEn=" + userNameEn + ", userNameAr="
				+ userNameAr + ", userIdRenewalDateGorg=" + userIdRenewalDateGorg + ", userIdRenewalDateHijri="
				+ userIdRenewalDateHijri + ", userEmail=" + userEmail + ", userMobile=" + userMobile + ", userPass="
				+ userPass + ", userImgUrl=" + userImgUrl + ", userRole=" + userRole + "]";
	}

	

	
	
	

}
