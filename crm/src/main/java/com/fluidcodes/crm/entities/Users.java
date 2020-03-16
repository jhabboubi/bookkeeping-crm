package com.fluidcodes.crm.entities;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class Users {

	
		// fields
	@Id
	@Column(name="userId")	
	private Long userId;
	
	
	@Column(name="officeId")	
	private Integer officeId;
	
	
	@Column(name="userNameEn")
	private String userNameEn;
	
	
	@Column(name="userNameAr")
	private String userNameAr;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="userIdRenewalDateGorg")
	private Date userIdRenewalDateGorg;
	
	
	@Column(name="userIdRenewalDateHijri")
	private String userIdRenewalDateHijri;
	
	
	@Column(name="userName")
	private String userName;
	
	
	@Column(name="userPass")
	private String userPass;
	
	
	@Column(name="userImgUrl")
	private String userImgUrl;
	
	
	@Column(name="userRole")
	private String userRole;

	//default constructor
	
	public Users() {
		System.out.println("Users default constructor called!");
	}
	
	
	public static Date StringToDate(String dob) throws ParseException {
	      //Instantiating the SimpleDateFormat class
	      SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	      //Parsing the given String to Date object
	      Date date = formatter.parse(dob);
	      System.out.println("Date object value: "+date);
	      return date;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Integer officeId) {
		this.officeId = officeId;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", officeId=" + officeId + ", userNameEn=" + userNameEn + ", userNameAr="
				+ userNameAr + ", userIdRenewalDateGorg=" + userIdRenewalDateGorg + ", userIdRenewalDateHijri="
				+ userIdRenewalDateHijri + ", userName=" + userName + ", userPass=" + userPass + ", userImgUrl="
				+ userImgUrl + ", userRole=" + userRole + "]";
	}

	

	
	
	

}
