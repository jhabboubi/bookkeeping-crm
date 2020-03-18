package com.fluidcodes.crm.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="offices")
public class Offices {

	
	//fields 
	@Id
	@Column(name="officeId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer officeId;
	
	
	@Column(name="officeName",unique=true)
	@NotNull(message="Field is required!")
	@Size(min=2, max=50,message = "Field must be more than two letters!")
	private String officeName;
	
	
	@Column(name="officeAddressLineOne")
	@NotNull(message="Field is required!")
	@Size(min=2, max=100,message = "Field must be more than two letters!")
	private String officeAddressLineOne;
	
	
	@Column(name="officeAddressLineTwo")
	@NotNull(message="Field is required!")
	@Size(min=2, max=100,message = "Field must be more than two letters!")
	private String officeAddressLineTwo;
	
	
	@Column(name="officeCity")
	@NotNull(message="Field is required!")
	@Size(min=2, max=50,message = "Field must be more than two letters!")
	private String officeCity;
	

	@Column(name="officeZipCode")
	@NotNull(message="Field is required!")
	@Pattern(regexp = "^\\d{5}$",message = "Five numeric digits only!")
	private String officeZipCode;
	
	
	@Column(name="officeTel")
	@NotNull(message="Field is required!")
	@Pattern(regexp = "^(1?(-?\\d{3})-?)?(\\d{3})(-?\\d{4})$",message = "Allows 7,10,11# optional hyphens")
	private String officeTel;
	
	
	@Column(name="officeCountry")
	@NotNull(message="Field is required!")
	@Size(min=2, max=50,message = "Field must be more than two letters!")
	private String officeCountry; 
	
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="officeId")
	private List<Accounts> accounts;
	
	
	@OneToMany( mappedBy = "office",fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@JsonIgnore
	private List<Users> users;
	
	
	
	//default constructor 
	public Offices() {
		System.out.println("Offices default constructor called!");
	}
	
	
	

	/**
	 * @param officeName
	 * @param officeAddressLineOne
	 * @param officeAddressLineTwo
	 * @param officeCity
	 * @param officeZipCode
	 * @param officeTel
	 * @param officeCountry
	 */
	public Offices(
			@NotNull(message = "Field is required!") @Size(min = 2, max = 50, message = "Field must be more than two letters!") String officeName,
			@NotNull(message = "Field is required!") @Size(min = 2, max = 100, message = "Field must be more than two letters!") String officeAddressLineOne,
			@NotNull(message = "Field is required!") @Size(min = 2, max = 100, message = "Field must be more than two letters!") String officeAddressLineTwo,
			@NotNull(message = "Field is required!") @Size(min = 2, max = 50, message = "Field must be more than two letters!") String officeCity,
			@NotNull(message = "Field is required!") @Pattern(regexp = "^\\d{5}$", message = "Five numeric digits only!") String officeZipCode,
			@NotNull(message = "Field is required!") @Pattern(regexp = "^(1?(-?\\d{3})-?)?(\\d{3})(-?\\d{4})$", message = "Allows 7,10,11# optional hyphens") String officeTel,
			@NotNull(message = "Field is required!") @Size(min = 2, max = 50, message = "Field must be more than two letters!") String officeCountry) {
		this.officeName = officeName;
		this.officeAddressLineOne = officeAddressLineOne;
		this.officeAddressLineTwo = officeAddressLineTwo;
		this.officeCity = officeCity;
		this.officeZipCode = officeZipCode;
		this.officeTel = officeTel;
		this.officeCountry = officeCountry;
	}




	//convenince method for bi-directional relationship
	
	public void add(Users tempUser) {
		if(users == null) {
			users = new ArrayList<>();
		}
		users.add(tempUser);
		tempUser.setOffice(this);
	}
	

	

	public Integer getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Integer officeId) {
		this.officeId = officeId;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getOfficeAddressLineOne() {
		return officeAddressLineOne;
	}

	public void setOfficeAddressLineOne(String officeAddressLineOne) {
		this.officeAddressLineOne = officeAddressLineOne;
	}

	public String getOfficeAddressLineTwo() {
		return officeAddressLineTwo;
	}

	public void setOfficeAddressLineTwo(String officeAddressLineTwo) {
		this.officeAddressLineTwo = officeAddressLineTwo;
	}

	public String getOfficeCity() {
		return officeCity;
	}

	public void setOfficeCity(String officeCity) {
		this.officeCity = officeCity;
	}

	public String getOfficeZipCode() {
		return officeZipCode;
	}

	public void setOfficeZipCode(String officeZipCode) {
		this.officeZipCode = officeZipCode;
	}

	public String getOfficeTel() {
		return officeTel;
	}

	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}

	public String getOfficeCountry() {
		return officeCountry;
	}

	public void setOfficeCountry(String officeCountry) {
		this.officeCountry = officeCountry;
	}

	public List<Accounts> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Accounts> accounts) {
		this.accounts = accounts;
	}

	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}




	@Override
	public String toString() {
		return "Offices [officeId=" + officeId + ", officeName=" + officeName + ", officeAddressLineOne="
				+ officeAddressLineOne + ", officeAddressLineTwo=" + officeAddressLineTwo + ", officeCity=" + officeCity
				+ ", officeZipCode=" + officeZipCode + ", officeTel=" + officeTel + ", officeCountry=" + officeCountry
				;
	}




	

	

	
	
}
