package com.fluidcodes.crm.entities;

import java.math.BigDecimal;
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
@Entity
@Table(name="offices")
public class Offices {

	
	//fields 
	@Id
	@Column(name="officeId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer officeId;
	@Column(name="officeName")
	private String officeName;
	@Column(name="officeAddressLineOne")
	private String officeAddressLineOne;
	@Column(name="officeAddressLineTwo")
	private String officeAddressLineTwo;
	@Column(name="officeCity")
	private String officeCity;
	@Column(name="officeZipCode")
	private Integer officeZipCode;
	@Column(name="officeTel")
	private BigDecimal officeTel;
	@Column(name="officeCountry")
	private String officeCountry; 
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="officeId")
	private List<Accounts> accounts;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="officeId")
	private List<Users> users;
	
	
	
	//default constructor 
	public Offices() {
		System.out.println("Offices default constructor called!");
	}

	/**
	 * @param officeId
	 * @param officeName
	 * @param officeAddressLineOne
	 * @param officeAddressLineTwo
	 * @param officeCity
	 * @param officeZipCode
	 * @param officeTel
	 * @param officeCountry
	 */
	public Offices(int officeId, String officeName, String officeAddressLineOne, String officeAddressLineTwo,
			String officeCity, int officeZipCode, BigDecimal officeTel, String officeCountry) {
		this.officeId = officeId;
		this.officeName = officeName;
		this.officeAddressLineOne = officeAddressLineOne;
		this.officeAddressLineTwo = officeAddressLineTwo;
		this.officeCity = officeCity;
		this.officeZipCode = officeZipCode;
		this.officeTel = officeTel;
		this.officeCountry = officeCountry;
	}
	
	public Offices(String officeName, String officeAddressLineOne, String officeAddressLineTwo,
			String officeCity, int officeZipCode, BigDecimal officeTel, String officeCountry) {
		this.officeName = officeName;
		this.officeAddressLineOne = officeAddressLineOne;
		this.officeAddressLineTwo = officeAddressLineTwo;
		this.officeCity = officeCity;
		this.officeZipCode = officeZipCode;
		this.officeTel = officeTel;
		this.officeCountry = officeCountry;
	}

	public int getOfficeId() {
		return officeId;
	}

	public void setOfficeId(int officeId) {
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

	public int getOfficeZipCode() {
		return officeZipCode;
	}

	public void setOfficeZipCode(int officeZipCode) {
		this.officeZipCode = officeZipCode;
	}

	public BigDecimal getOfficeTel() {
		return officeTel;
	}

	public void setOfficeTel(BigDecimal officeTel) {
		this.officeTel = officeTel;
	}

	public String getOfficeCountry() {
		return officeCountry;
	}

	public void setOfficeCountry(String officeCountry) {
		this.officeCountry = officeCountry;
	}

	@Override
	public String toString() {
		return "Offices [officeId=" + officeId + ", officeName=" + officeName + ", officeAddressLineOne="
				+ officeAddressLineOne + ", officeAddressLineTwo=" + officeAddressLineTwo + ", officeCity=" + officeCity
				+ ", officeZipCode=" + officeZipCode + ", officeTel=" + officeTel + ", officeCountry=" + officeCountry
				+ "]";
	}
	
	
}
