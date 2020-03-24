package com.fluidcodes.crm.entities;

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
import javax.persistence.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="transactions")
public class Transactions {

	//fields
	@Column(name="transDateCreate", nullable = false, updatable = false)
	@CreationTimestamp

	private Date transDateCreate;
	
	@Column(name="transDateUpdated")
	@UpdateTimestamp
	private Date transDateUpdated;
	
	
	@Id
	@Column(name="transId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transId;

	
	@Column(name="transDescription")
	@Size(min=2, max=100,message = "Field must be more than two letters!")
	@NotNull(message="Field is required!")
	private String transDescription;
	
	
	@Column(name="transAmount")
	@NotNull(message="Field is required!")
	private Double transAmount;
	
	
	@Column(name="transMethod")
	private String transMethod;
	
	
	@Column(name="transIsCredit", columnDefinition = "boolean default false")
	private Boolean transIsCredit; 
	
	@ManyToOne(fetch=FetchType.LAZY,cascade= {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name = "accountId")
	private Accounts account;
	
	@OneToMany( mappedBy = "transaction",fetch=FetchType.LAZY,cascade= CascadeType.ALL)	
	@JsonIgnore
	private List<Attachments> attachments;
	
	
	
	//Convenience method for bi-directional relationship
	
		public void add(Attachments tempAtt) {
			if(attachments == null) {
				attachments = new ArrayList<>();
			}
			attachments.add(tempAtt);
			tempAtt.setTransaction(this);
		}
	
	// default constructor
	
	public Transactions() {
		System.out.println("Transactions default constructor called!");
	}

	/**
	 * @param transDateCreate
	 * @param transDateUpdated
	 * @param transId
	 * @param transDescription
	 * @param transAmount
	 * @param transMethod
	 * @param transIsCredit
	 * @param account
	 * @param attachments
	 */
	public Transactions(Date transDateCreate, Date transDateUpdated, Long transId,
			@Size(min = 2, max = 100, message = "Field must be more than two letters!") @NotNull(message = "Field is required!") String transDescription,
			@NotNull(message = "Field is required!") Double transAmount, String transMethod, Boolean transIsCredit,
			Accounts account, List<Attachments> attachments) {
		this.transDateCreate = transDateCreate;
		this.transDateUpdated = transDateUpdated;
		this.transId = transId;
		this.transDescription = transDescription;
		this.transAmount = transAmount;
		this.transMethod = transMethod;
		this.transIsCredit = transIsCredit;
		this.account = account;
		this.attachments = attachments;
	}

	public Date getTransDateCreate() {
		return transDateCreate;
	}

	public void setTransDateCreate(Date transDateCreate) {
		this.transDateCreate = transDateCreate;
	}

	public Date getTransDateUpdated() {
		return transDateUpdated;
	}

	public void setTransDateUpdated(Date transDateUpdated) {
		this.transDateUpdated = transDateUpdated;
	}

	public Long getTransId() {
		return transId;
	}

	public void setTransId(Long transId) {
		this.transId = transId;
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

	public Accounts getAccount() {
		return account;
	}

	public void setAccount(Accounts account) {
		this.account = account;
	}

	public List<Attachments> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachments> attachments) {
		this.attachments = attachments;
	}

	@Override
	public String toString() {
		return "Transactions [transDateCreate=" + transDateCreate + ", transDateUpdated=" + transDateUpdated
				+ ", transId=" + transId + ", transDescription=" + transDescription + ", transAmount=" + transAmount
				+ ", transMethod=" + transMethod + ", transIsCredit=" + transIsCredit + ", account=" + account + "]";
	}

	



	
	
	
	
}
