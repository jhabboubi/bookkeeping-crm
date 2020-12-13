package com.fluidcodes.crm.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Offices {

	// fields
	// id auto generate
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer officeId;

	// office name must be unique , cant be null, min 2 letters, max 50
	@Column(unique = true)
	@NotNull(message = "Field is required!")
	@Size(min = 2, max = 50, message = "Field must be more than two letters!")
	private String officeName;

	// address not null, min 2 and max 100 letters
	@NotNull(message = "Field is required!")
	@Size(min = 2, max = 100, message = "Field must be more than two letters!")
	private String officeAddressLineOne;

	// address not null, min 2 and max 100 letters
	@NotNull(message = "Field is required!")
	@Size(min = 2, max = 100, message = "Field must be more than two letters!")
	private String officeAddressLineTwo;

	// city not null, min 2 and max 50 letters
	@NotNull(message = "Field is required!")
	@Size(min = 2, max = 50, message = "Field must be more than two letters!")
	private String officeCity;

	// not null , must be 5 numbers only
	@NotNull(message = "Field is required!")
	@Pattern(regexp = "^\\d{5}$", message = "Five numeric digits only!")
	private String officeZipCode;

	// cant be null , regex
	@NotNull(message = "Field is required!")
	@Pattern(regexp = "^(1?(-?\\d{3})-?)?(\\d{3})(-?\\d{4})$", message = "Allows 7,10,11# optional hyphens")
	private String officeTel;

	// not null, min 2, max 50
	@NotNull(message = "Field is required!")
	@Size(min = 2, max = 50, message = "Field must be more than two letters!")
	private String officeCountry;

	// relational table account if delete dont delete accounts
	@OneToMany(mappedBy = "office", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH })
	@JsonIgnore
	@ToString.Exclude
	private List<Accounts> accounts;

	// relational table with users if delete dont delete users
	@OneToMany(mappedBy = "office", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH })
	@JsonIgnore
	@ToString.Exclude
	private List<Users> users;

	
	// Convenience method for bi-directional relationship for users

	public void add(Users tempUser) {
		if (users == null) {
			users = new ArrayList<>();
		}
		users.add(tempUser);
		tempUser.setOffice(this);
	}

	// Convenience method for bi-directional relationship for accounts

	public void add(Accounts tempAccount) {
		if (accounts == null) {
			accounts = new ArrayList<>();
		}
		accounts.add(tempAccount);
		tempAccount.setOffice(this);
	}

	
}
