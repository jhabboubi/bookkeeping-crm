package com.fluidcodes.crm.models;

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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attachments {
	

	
	// fields
	// auto timestamp created
	@CreationTimestamp
	private Date attDateCreate;
	// auto timestamp updated
	@Column(nullable = false, updatable = false)
	@UpdateTimestamp
	private Date attDateUpdated;

	// id auto generate
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long attId;

	// url to file, not null
	@NotNull(message = "Field is required!")
	private String attPath;

	// relational table if delete dont delete transaction
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JoinColumn(name = "transId")
	private Transactions transaction;
	

	

}
