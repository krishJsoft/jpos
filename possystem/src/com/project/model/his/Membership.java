package com.project.model.his;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="membership")
public class Membership implements Serializable{
	private static long serialVersionUID=1L;


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true,nullable=false)
	private int id;
	
	@Column(length = 50)
	private String name;
	
	@Column(length = 25)
	private String identificationNumber;
	
	@Column(length = 6)
	private String gender;
	
	@Column(length = 50)
	private String address;
	
	@Column(length = 20)
	private String contactNo;
	
	@Column(length = 50)
	private String email;
	
	@Column(length = 25)
	private String userName;
	
	@Column(length = 25)
	private String password;
	
	@Temporal(TemporalType.DATE)
	private Date expirationDate;
	
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	
	private int status;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="createdBy")
	private Branchstaffmember createdBy;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchID")
	private Branch branch;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Branchstaffmember getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Branchstaffmember createdBy) {
		this.createdBy = createdBy;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
}
