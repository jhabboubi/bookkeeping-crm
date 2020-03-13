package com.fluidcodes.crm.entities;

import java.math.BigDecimal;
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
@Table(name="accounts")
public class Accounts {

	
	
		//fields
	@Id
	@Column(name="accountId")
	private BigDecimal accountId;
	@Column(name="officeId")
	private Integer officeId;
	@Column(name="accountIban")
	private String accountIban;
	@Column(name="swiftCode")
	private String swiftCode;
	@Column(name="bankName")
	private String bankName;
	@Column(name="balance")
	private Double balance;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="accountId")
	private List<Transactions> transactions;
	
	//default constructor 

	public Accounts() {
		System.out.println("Accounts default constructor called!");
	}
	
	
	/**
	 * @param accountId
	 * @param officeId
	 * @param accountIban
	 * @param swiftCode
	 * @param bankName
	 * @param balance
	 */
	public Accounts(BigDecimal accountId, int officeId, String accountIban, String swiftCode, String bankName,
			Double balance) {
		this.accountId = accountId;
		this.officeId = officeId;
		this.accountIban = accountIban;
		this.swiftCode = swiftCode;
		this.bankName = bankName;
		this.balance = balance;
	}
	

	public BigDecimal getAccountId() {
		return accountId;
	}


	public void setAccountId(BigDecimal accountId) {
		this.accountId = accountId;
	}


	public int getOfficeId() {
		return officeId;
	}


	public void setOfficeId(int officeId) {
		this.officeId = officeId;
	}


	public String getAccountIban() {
		return accountIban;
	}


	public void setAccountIban(String accountIban) {
		this.accountIban = accountIban;
	}


	public String getSwiftCode() {
		return swiftCode;
	}


	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}


	public String getBankName() {
		return bankName;
	}


	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


	public Double getBalance() {
		return balance;
	}


	public void setBalance(Double balance) {
		this.balance = balance;
	}





	@Override
	public String toString() {
		return "Accounts [accountId=" + accountId + ", officeId=" + officeId + ", accountIban=" + accountIban
				+ ", swiftCode=" + swiftCode + ", bankName=" + bankName + ", balance=" + balance + "]";
	}
	
	
	
	
}
