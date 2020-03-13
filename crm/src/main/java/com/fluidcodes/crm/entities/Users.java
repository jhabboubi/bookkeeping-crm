package com.fluidcodes.crm.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="users")
public class Users {

	
		// fields
	@Id
	@Column(name="userId")
	private BigDecimal userId;
	@Column(name="officeId")
	private Integer officeId;
	@Column(name="userNameEn")
	private String userNameEn;
	@Column(name="userNameAr")
	private String userNameAr;
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
	private Integer userRole;

	//default constructor
	
	public Users() {
		System.out.println("Users default constructor called!");
	}

	/**
	 * @param userId
	 * @param officeId
	 * @param userNameEn
	 * @param userNameAr
	 * @param userIdRenewalDateGorg
	 * @param userIdRenewalDateHijri
	 * @param userName
	 * @param userPass
	 * @param userImgUrl
	 * @param userRole
	 */
	public Users(BigDecimal userId, int officeId, String userNameEn, String userNameAr, Date userIdRenewalDateGorg,
			String userIdRenewalDateHijri, String userName, String userPass, String userImgUrl, int userRole) {
		this.userId = userId;
		this.officeId = officeId;
		this.userNameEn = userNameEn;
		this.userNameAr = userNameAr;
		this.userIdRenewalDateGorg = userIdRenewalDateGorg;
		this.userIdRenewalDateHijri = userIdRenewalDateHijri;
		this.userName = userName;
		this.userPass = userPass;
		this.userImgUrl = userImgUrl;
		this.userRole = userRole;
	}

	public BigDecimal getUserId() {
		return userId;
	}

	public void setUserId(BigDecimal userId) {
		this.userId = userId;
	}

	public int getOfficeId() {
		return officeId;
	}

	public void setOfficeId(int officeId) {
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

	public int getUserRole() {
		return userRole;
	}

	public void setUserRole(int userRole) {
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
