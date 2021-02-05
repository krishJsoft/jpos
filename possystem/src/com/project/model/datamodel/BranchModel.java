package com.project.model.datamodel;

import java.math.BigDecimal;
import java.util.Date;

public class BranchModel {

	private int branchId;
	private String address;
	private String branchCode;
	private String branchName;

	private String branchOldCode;
	private String branchOldName;

	private String city;
	private String country;
	private String createdBy;
	private Date createdDate;
	private String emailAddress;
	private String emailOldAddress;
	
	private String faxNo;
	private BigDecimal fixedLicenseFee;
	private BigDecimal franchiseeFee;
	private String isHQ;
	private Date lastModifiedDate;
	private String phoneNo;
	private String postCode;
	private String state;
	private String status;
	private String contactPerson;
	private String branchtype;
	private String branchuseremailAddress;
	private String branchuserpassword;
	private Integer hdbranchId;
	
	private int customersalesPoint;
	private BigDecimal customersalesValue;
	
	private int redemPoint;
	private BigDecimal redemValue;
	
	private BigDecimal totalPurchaseTax;
	private BigDecimal totalSalesTax;
	
	private Integer onlineBranchId;
	
	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchOldCode() {
		return branchOldCode;
	}

	public void setBranchOldCode(String branchOldCode) {
		this.branchOldCode = branchOldCode;
	}

	public String getBranchOldName() {
		return branchOldName;
	}

	public void setBranchOldName(String branchOldName) {
		this.branchOldName = branchOldName;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public BigDecimal getFixedLicenseFee() {
		return fixedLicenseFee;
	}

	public void setFixedLicenseFee(BigDecimal fixedLicenseFee) {
		this.fixedLicenseFee = fixedLicenseFee;
	}

	public BigDecimal getFranchiseeFee() {
		return franchiseeFee;
	}

	public void setFranchiseeFee(BigDecimal franchiseeFee) {
		this.franchiseeFee = franchiseeFee;
	}

	public String getIsHQ() {
		return isHQ;
	}

	public void setIsHQ(String isHQ) {
		this.isHQ = isHQ;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getBranchtype() {
		return branchtype;
	}

	public void setBranchtype(String branchtype) {
		this.branchtype = branchtype;
	}

	public String getBranchuseremailAddress() {
		return branchuseremailAddress;
	}

	public void setBranchuseremailAddress(String branchuseremailAddress) {
		this.branchuseremailAddress = branchuseremailAddress;
	}

	public String getBranchuserpassword() {
		return branchuserpassword;
	}

	public void setBranchuserpassword(String branchuserpassword) {
		this.branchuserpassword = branchuserpassword;
	}

	public String getEmailOldAddress() {
		return emailOldAddress;
	}

	public void setEmailOldAddress(String emailOldAddress) {
		this.emailOldAddress = emailOldAddress;
	}

	public Integer getHdbranchId() {
		return hdbranchId;
	}

	public void setHdbranchId(Integer hdbranchId) {
		this.hdbranchId = hdbranchId;
	}
	

	public int getCustomersalesPoint() {
		return customersalesPoint;
	}

	public void setCustomersalesPoint(int customersalesPoint) {
		this.customersalesPoint = customersalesPoint;
	}

	public BigDecimal getCustomersalesValue() {
		return customersalesValue;
	}

	public void setCustomersalesValue(BigDecimal customersalesValue) {
		this.customersalesValue = customersalesValue;
	}

	public int getRedemPoint() {
		return redemPoint;
	}

	public void setRedemPoint(int redemPoint) {
		this.redemPoint = redemPoint;
	}

	public BigDecimal getRedemValue() {
		return redemValue;
	}

	public void setRedemValue(BigDecimal redemValue) {
		this.redemValue = redemValue;
	}

	public BigDecimal getTotalPurchaseTax() {
		return totalPurchaseTax;
	}

	public void setTotalPurchaseTax(BigDecimal totalPurchaseTax) {
		this.totalPurchaseTax = totalPurchaseTax;
	}

	public BigDecimal getTotalSalesTax() {
		return totalSalesTax;
	}

	public void setTotalSalesTax(BigDecimal totalSalesTax) {
		this.totalSalesTax = totalSalesTax;
	}

	public Integer getOnlineBranchId() {
		return onlineBranchId;
	}

	public void setOnlineBranchId(Integer onlineBranchId) {
		this.onlineBranchId = onlineBranchId;
	}

	
}
