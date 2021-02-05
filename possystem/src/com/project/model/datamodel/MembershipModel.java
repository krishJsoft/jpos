package com.project.model.datamodel;

import java.util.Date;

public class MembershipModel {

	private int id;
	
	private String name;
	
	private String identificationNumber;
	
	private String gender;
	
	private String address;
	
	private String contactNo;
	
	private String emailAddress;
	
	private String userName;
	
	private String password;
	
	private Date expirationDate; 
	
	private Date createdDate;
	
	private int createdBy;
	
	private int status;
	
	private int branchId;
	

	BranchstaffmemberModel branchStaff=new BranchstaffmemberModel();
	
	BranchModel branch=new BranchModel();

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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
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

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int id) {
		this.createdBy = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int i) {
		this.status = i;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	} 
	
	public BranchstaffmemberModel getBranchStaff() {
		return branchStaff;
	}

	public void setBranchStaff(BranchstaffmemberModel branchStaff) {
		this.branchStaff = branchStaff;
	}
	
	public BranchModel getBranch() {
		return branch;
	}

	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}

}
