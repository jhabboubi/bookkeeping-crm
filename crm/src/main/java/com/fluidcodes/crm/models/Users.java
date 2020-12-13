 package com.fluidcodes.crm.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


import org.springframework.format.annotation.DateTimeFormat;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

	// fields
	// id can't be null
	@Id
	private Long userId;

	// relational field with offices, if delete don't delete office
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JoinColumn(name = "officeId")
	private Offices office;

	// en name , can't be null or less than 2 letters or more than 100 letter
	@NotNull(message = "Field is required!")
	@Size(min = 2, max = 100, message = "Field must be more than two letters!")
	private String userNameEn;

	// ar name , can't be null or less than 2 letters or more than 100 letter
	@NotNull(message = "Field is required!")
	@Size(min = 2, max = 100, message = "Field must be more than two letters!")
	private String userNameAr;

	// date of renew id change date recored to db to be only date without time,
	// can't be null
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Field is required!")
	private Date userIdRenewalDateGorg;

	// date in hijri
	private String userIdRenewalDateHijri;

	// user email must be unique and not null with regex check
	@Column(unique = true)
	@NotNull(message = "Field is required!")
	@Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$", message = "Ex: joe@aol.com or joe@wrox.com.sa")
	private String userEmail;

	// mobile number , can't be null , with regex check
	@NotNull(message = "Field is required!")
	@Pattern(regexp = "^(1?(-?\\d{3})-?)?(\\d{3})(-?\\d{4})$", message = "Allows 7,10,11# optional hyphens")
	private String userMobile;

	// user password cant be null will be hashed in db
	@Pattern(regexp = "^[a-zA-Z]\\w{3,50}$", message="Begin with a letter, 4 minimum characters, and underscore may be used")
	@NotNull(message = "Field is required!")
	private String userPass;

	// user img url
	private String userImgUrl;

	// user role , can't be null
	@NotNull(message = "Field is required!")
	private String userRole;

	// user is active or disabled by default active
	@Column(columnDefinition = "boolean default true")
	private Boolean userActive;


}
