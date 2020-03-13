package com.fluidcodes.crm.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="transactions")
public class Transactions {

	//fields
	@Column(name="transDateTime")
	private Timestamp transDateTime;
	@Id
	@Column(name="transId")
	private Integer transId;
	@Column(name="officeId")
	private Integer officeId;
	@Column(name="userId")
	private BigDecimal userId;
	@Column(name="accountId")
	private BigDecimal accountId;
	
//	@Column(name="attId")
//	@JoinColumn(name="attId", referencedColumnName="attId")
//	private Integer attId;
	@Column(name="transDescription")
	private String transDescription;
	@Column(name="transAmount")
	private Double transAmount;
	@Column(name="transMethod")
	private String transMethod;
	@Column(name="transIsCredit")
	private Boolean transIsCredit; 
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="transId")
	private List<Attachments> attachments;
	
	// default constructor
	
	public Transactions() {
		System.out.println("Transactions default constructor called!");
	}

	/**
	 * @param transDateTime
	 * @param transId
	 * @param officeId
	 * @param userId
	 * @param accountId
	 * @param attId
	 * @param transDescription
	 * @param transAmount
	 * @param transMethod
	 * @param transIsCredit
	 */
	public Transactions(Timestamp transDateTime, Integer transId, Integer officeId, BigDecimal userId,
			BigDecimal accountId, String transDescription, Double transAmount, String transMethod,
			Boolean transIsCredit) {
		this.transDateTime = transDateTime;
		this.transId = transId;
		this.officeId = officeId;
		this.userId = userId;
		this.accountId = accountId;
		
		this.transDescription = transDescription;
		this.transAmount = transAmount;
		this.transMethod = transMethod;
		this.transIsCredit = transIsCredit;
	}

	public Timestamp getTransDateTime() {
		return transDateTime;
	}

	public void setTransDateTime(Timestamp transDateTime) {
		this.transDateTime = transDateTime;
	}

	public Integer getTransId() {
		return transId;
	}

	public void setTransId(Integer transId) {
		this.transId = transId;
	}

	public Integer getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Integer officeId) {
		this.officeId = officeId;
	}

	public BigDecimal getUserId() {
		return userId;
	}

	public void setUserId(BigDecimal userId) {
		this.userId = userId;
	}

	public BigDecimal getAccountId() {
		return accountId;
	}

	public void setAccountId(BigDecimal accountId) {
		this.accountId = accountId;
	}



	public String getTransDescription() {
		return transDescription;
	}

	public void setTransDescription(String transDescription) {
		this.transDescription = transDescription;
	}

	public Double getTransAmount() {
		return transAmount;
	}

	public void setTransAmount(Double transAmount) {
		this.transAmount = transAmount;
	}

	public String getTransMethod() {
		return transMethod;
	}

	public void setTransMethod(String transMethod) {
		this.transMethod = transMethod;
	}

	public Boolean getTransIsCredit() {
		return transIsCredit;
	}

	public void setTransIsCredit(Boolean transIsCredit) {
		this.transIsCredit = transIsCredit;
	}

	@Override
	public String toString() {
		return "Transactions [transDateTime=" + transDateTime + ", transId=" + transId + ", officeId=" + officeId
				+ ", userId=" + userId + ", accountId=" + accountId +  ", transDescription="
				+ transDescription + ", transAmount=" + transAmount + ", transMethod=" + transMethod
				+ ", transIsCredit=" + transIsCredit + "]";
	}
	
	
	
}
