package com.fluidcodes.crm.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transactions {

	// fields
	// auto stamp for date created
	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private Date transDateCreate;

	// auto stamp for date updated
	@UpdateTimestamp
	private Date transDateUpdated;

	// id auto generate
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transId;

	// desc , can't be null, min 2 and max 200
	@Size(min = 2, max = 200, message = "Field must be more than two letters!")
	@NotNull(message = "Field is required!")
	private String transDescription;

	// amount of transaction , can't be null
	@NotNull(message = "Field is required!")
	private Double transAmount;

	// method for transaction
	private String transMethod;

	// boolean if credit or expense
	@Column(columnDefinition = "boolean default false")
	private Boolean transIsCredit;

	// relational table for account , if delete transaction dont delete account
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JoinColumn(name = "accountId")
	private Accounts account;

	// relational table for attachment if delete transaction delete attachments
	@OneToMany(mappedBy = "transaction", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	@ToString.Exclude
	private List<Attachments> attachments;

	// Convenience method for bi-directional relationship

	public void add(Attachments tempAtt) {
		if (attachments == null) {
			attachments = new ArrayList<>();
		}
		attachments.add(tempAtt);
		tempAtt.setTransaction(this);
	}

	
}
