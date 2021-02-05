package com.project.model.datamodel;

import java.math.BigDecimal;

public class OrganizationModel {

	private int idorganization;
	private String address;
	private String city;
	private String country;
	private BigDecimal depositamount;
	private String email;
	private String fax;
	private String name;
	private String phone;
	private String postcode;
	private String type;

	public int getIdorganization() {
		return idorganization;
	}

	public void setIdorganization(int idorganization) {
		this.idorganization = idorganization;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public BigDecimal getDepositamount() {
		return depositamount;
	}

	public void setDepositamount(BigDecimal depositamount) {
		this.depositamount = depositamount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
