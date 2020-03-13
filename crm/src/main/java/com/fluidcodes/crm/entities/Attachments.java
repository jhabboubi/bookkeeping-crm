package com.fluidcodes.crm.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="attachments")
public class Attachments {

	
	//fields
	@Column(name="attDateTime")
	private Timestamp attDateTime;
	@Id
	@Column(name="attId")
	private Integer attId;
	@Column(name="transId")
	private Integer transId;
	@Column(name="attAttachment")
	private String attAttachment;
	
	public Attachments() {
		
	}


	/**
	 * @param attDateTime
	 * @param attId
	 * @param transId
	 * @param attAttachment
	 */
	public Attachments(Timestamp attDateTime, Integer attId, Integer transId, String attAttachment) {
		this.attDateTime = attDateTime;
		this.attId = attId;
		this.transId = transId;
		this.attAttachment = attAttachment;
	}


	public Timestamp getAttDateTime() {
		return attDateTime;
	}


	public void setAttDateTime(Timestamp attDateTime) {
		this.attDateTime = attDateTime;
	}


	public Integer getAttId() {
		return attId;
	}


	public void setAttId(Integer attId) {
		this.attId = attId;
	}


	public Integer getTransId() {
		return transId;
	}


	public void setTransId(Integer transId) {
		this.transId = transId;
	}


	public String getAttAttachment() {
		return attAttachment;
	}


	public void setAttAttachment(String attAttachment) {
		this.attAttachment = attAttachment;
	}





	@Override
	public String toString() {
		return "Attachments [attDateTime=" + attDateTime + ", attId=" + attId + ", transId=" + transId
				+ ", attAttachment=" + attAttachment + "]";
	}
	
	
	
}
