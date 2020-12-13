package com.fluidcodes.crm.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Accounts {

	// fields
	// id can't be null
	@Id
	private Long accountId;
	
	// iban with regex, can't be null
	@NotNull(message = "Field is required!")
	@Pattern(regexp = "^[A-Z]{2}[0-9]{2}(?:[ ]?[0-9]{4}){5}(?:[ ]?[0-9]{1,2})?$", message = "Ex: SA70 0500 0068 2013 0036 3000")
	private String accountIban;

	// swift code
//	@Pattern(regexp = "^[a-zA-Z]{4}[a-zA-Z]{2}[a-zA-Z0-9]{2}[XXX0-9]{0,3}\n", message="NEDSZAJJ, NEDSZAJJXXX, NEDSZAJJ100")
	private String swiftCode;

	// can't be null
	@NotNull(message = "Field is required!")
	private String bankName;

	// can't be null
	@NotNull(message = "Field is required!")
	private Double balance;

	// relational table if delete account don't delete transactions
	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH })
	@JsonIgnore
	@ToString.Exclude
	private List<Transactions> transactions;

	// relational table if account is deleted don't delete office
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JoinColumn(name = "officeId")
	private Offices office;

	// Convenience method for bi-directional relationship for accounts
	public void add(Transactions tempTrans) {
		if (transactions == null) {
			transactions = new ArrayList<>();
		}
		transactions.add(tempTrans);
		tempTrans.setAccount(this);

	}
	


}
