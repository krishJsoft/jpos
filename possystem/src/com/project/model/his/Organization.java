package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the organization database table.
 * 
 */
@Entity
@Table(name="organization")
public class Organization implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idorganization;

	@Column(length=200)
	private String address;

	@Column(length=45)
	private String city;

	@Column(length=45)
	private String country;

	@Column(nullable=false)
	private double depositamount;

	@Column(length=45)
	private String email;

	@Column(length=45)
	private String fax;

	@Column(length=45)
	private String name;

	@Column(length=45)
	private String phone;

	@Column(length=45)
	private String postcode;

	@Column(length=45)
	private String type;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchRecordId")
	private Branch branch;

	public Organization() {
	}

	public int getIdorganization() {
		return this.idorganization;
	}

	public void setIdorganization(int idorganization) {
		this.idorganization = idorganization;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public double getDepositamount() {
		return this.depositamount;
	}

	public void setDepositamount(double depositamount) {
		this.depositamount = depositamount;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

}