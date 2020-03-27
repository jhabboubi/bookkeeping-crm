package com.fluidcodes.crm.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "accounts")
public class Accounts {

	// fields
	// id can't be null
	@Id
	@Column(name = "accountId")
	@NotNull(message = "Field is required!")
	private Long accountId;

	// iban with regex, can't be null
	@Column(name = "accountIban")
	@NotNull(message = "Field is required!")
	@Pattern(regexp = "^[A-Z]{2}[0-9]{2}(?:[ ]?[0-9]{4}){5}(?:[ ]?[0-9]{1,2})?$", message = "Ex: SA70 0500 0068 2013 0036 3000")
	private String accountIban;

	// swift code
	@Column(name = "swiftCode")
//	@Pattern(regexp = "^[a-zA-Z]{4}[a-zA-Z]{2}[a-zA-Z0-9]{2}[XXX0-9]{0,3}\n", message="NEDSZAJJ, NEDSZAJJXXX, NEDSZAJJ100")
	private String swiftCode;

	// can't be null
	@Column(name = "bankName")
	@NotNull(message = "Field is required!")
	private String bankName;

	// can't be null
	@Column(name = "balance")
	@NotNull(message = "Field is required!")
	private Double balance;

	// relational table if delete account don't delete transactions
	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH })
	@JsonIgnore
	private List<Transactions> transactions;

	// relational table if account is deleted don't delete office
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JoinColumn(name = "officeId")
	private Offices office;

	// convenince method for bi-directional relationship for accounts

	public void add(Transactions tempTrans) {
		if (transactions == null) {
			transactions = new ArrayList<>();
		}
		transactions.add(tempTrans);
		tempTrans.setAccount(this);

	}
	// default constructor

	public Accounts() {
		System.out.println("Accounts default constructor called!");
	}

	/**
	 * my constructor
	 * 
	 * @param accountId
	 * @param accountIban
	 * @param swiftCode
	 * @param bankName
	 * @param balance
	 * @param transactions
	 * @param office
	 */
	public Accounts(@NotNull(message = "Field is required!") Long accountId,
			@NotNull(message = "Field is required!") @Pattern(regexp = "AD\\d{2}[ ]\\d{4}[ ]\\d{4}[ ]\\d{4}[ ]\\d{4}[ ]\\d{4}|AD\\d{22}\n", message = "Ex: SA31 1200 0000 1987 4263 7541 | SA3112000000198742637541") String accountIban,
			@Pattern(regexp = "^[a-zA-Z]{4}[a-zA-Z]{2}[a-zA-Z0-9]{2}[XXX0-9]{0,3}\n", message = "NEDSZAJJ, NEDSZAJJXXX, NEDSZAJJ100") String swiftCode,
			@NotNull(message = "Field is required!") String bankName,
			@NotNull(message = "Field is required!") Double balance, List<Transactions> transactions, Offices office) {
		this.accountId = accountId;
		this.accountIban = accountIban;
		this.swiftCode = swiftCode;
		this.bankName = bankName;
		this.balance = balance;
		this.transactions = transactions;
		this.office = office;
	}

	// getters and setters
	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
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

	public List<Transactions> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transactions> transactions) {
		this.transactions = transactions;
	}

	public Offices getOffice() {
		return office;
	}

	public void setOffice(Offices office) {
		this.office = office;
	}

	// to string method
	@Override
	public String toString() {
		return "Accounts [accountId=" + accountId + ", accountIban=" + accountIban + ", swiftCode=" + swiftCode
				+ ", bankName=" + bankName + ", balance=" + balance + "]";
	}

}
