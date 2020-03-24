package com.fluidcodes.crm.entities;



import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Table(name="attachments")
public class Attachments {

	
	//fields
	@Column(name="attDateCreate")
	@CreationTimestamp
	private Date attDateCreate;
	
	@Column(name="attDateUpdated")
	@UpdateTimestamp
	private Date attDateUpdated;
	
	
	@Id
	@Column(name="attId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long attId;
	
	
	
	
	@Column(name="attPath")
	@NotNull(message="Field is required!")
    private String attPath;
	
	
	@ManyToOne(fetch=FetchType.LAZY,cascade= {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name = "transId")
	private Transactions transaction;
	
	
	public Attachments() {System.out.println("Attachments default constructor called");}


	/**
	 * @param attDateCreate
	 * @param attDateUpdated
	 * @param attId
	 * @param attPath
	 * @param transaction
	 */
	public Attachments(Date attDateCreate, Date attDateUpdated, Long attId,
			@NotNull(message = "Field is required!") String attPath, Transactions transaction) {
		this.attDateCreate = attDateCreate;
		this.attDateUpdated = attDateUpdated;
		this.attId = attId;
		this.attPath = attPath;
		this.transaction = transaction;
	}


	public Date getAttDateCreate() {
		return attDateCreate;
	}


	public void setAttDateCreate(Date attDateCreate) {
		this.attDateCreate = attDateCreate;
	}


	public Date getAttDateUpdated() {
		return attDateUpdated;
	}


	public void setAttDateUpdated(Date attDateUpdated) {
		this.attDateUpdated = attDateUpdated;
	}


	public Long getAttId() {
		return attId;
	}


	public void setAttId(Long attId) {
		this.attId = attId;
	}


	public String getAttPath() {
		return attPath;
	}


	public void setAttPath(String attPath) {
		this.attPath = attPath;
	}


	public Transactions getTransaction() {
		return transaction;
	}


	public void setTransaction(Transactions transaction) {
		this.transaction = transaction;
	}


	@Override
	public String toString() {
		return "Attachments [attDateCreate=" + attDateCreate + ", attDateUpdated=" + attDateUpdated + ", attId=" + attId
				+ ", attPath=" + attPath + ", transaction=" + transaction + "]";
	}


	
	

	
	
	
	
	
	
	
}
