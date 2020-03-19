package com.fluidcodes.crm.entities;


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


@Entity
@Table(name="accounts")
public class Accounts {

	
	
		//fields
	@Id
	@Column(name="accountId")
	@NotNull(message="Field is required!")
	private Long accountId;
	
	
	@Column(name="accountIban")
	@NotNull(message="Field is required!")
	@Pattern(regexp = "^[A-Z]{2}[0-9]{2}(?:[ ]?[0-9]{4}){5}(?:[ ]?[0-9]{1,2})?$", message="Ex: SA70 0500 0068 2013 0036 3000")
	//
	///^(?:(?:IT|SM)\\d{2}[A-Z]\\d{22}|CY\\d{2}[A-Z]\\d{23}|NL\\d{2}[A-Z]{4}\\d{10}|LV\\d{2}[A-Z]{4}\\d{13}|(?:BG|BH|GB|IE)\\d{2}[A-Z]{4}\\d{14}|GI\\d{2}[A-Z]{4}\\d{15}|RO\\d{2}[A-Z]{4}\\d{16}|KW\\d{2}[A-Z]{4}\\d{22}|MT\\d{2}[A-Z]{4}\\d{23}|NO\\d{13}|(?:DK|FI|GL|FO)\\d{16}|MK\\d{17}|(?:AT|EE|KZ|LU|XK)\\d{18}|(?:BA|HR|LI|CH|CR)\\d{19}|(?:GE|DE|LT|ME|RS)\\d{20}|IL\\d{21}|(?:AD|CZ|ES|MD|SA)\\d{22}|PT\\d{23}|(?:BE|IS)\\d{24}|(?:FR|MR|MC)\\d{25}|(?:AL|DO|LB|PL)\\d{26}|(?:AZ|HU)\\d{27}|(?:GR|MU)\\d{28})$/i
	private String accountIban;
	
	@Column(name="swiftCode")
//	@Pattern(regexp = "^[a-zA-Z]{4}[a-zA-Z]{2}[a-zA-Z0-9]{2}[XXX0-9]{0,3}\n", message="NEDSZAJJ, NEDSZAJJXXX, NEDSZAJJ100")
	private String swiftCode;
	
	
	@Column(name="bankName")
	@NotNull(message="Field is required!")
	private String bankName;
	
	
	@Column(name="balance")
	@NotNull(message="Field is required!")
	private Double balance;
	
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="accountId")
	private List<Transactions> transactions;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade= {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name = "officeId")
	private Offices office;
	
	//default constructor 

	public Accounts() {
		System.out.println("Accounts default constructor called!");
	}

	/**
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

	@Override
	public String toString() {
		return "Accounts [accountId=" + accountId + ", accountIban=" + accountIban + ", swiftCode=" + swiftCode
				+ ", bankName=" + bankName + ", balance=" + balance + "]";
	}
	
	
	
	
	
}
