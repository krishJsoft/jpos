package com.project.model.datamodel.purchase;

import java.util.List;

public class PurchaseorderConsolidateModel {

	private int supplierId;
	private String address;
	private String branchName;
	private String city;
	private String companyName;
	private String companyRegNo;
	private String companyOldRegNo;
	private String contactPerson;
	private String contactPersonNumber;

	private List<PurchaserequestbreakdownModel> productList;

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyRegNo() {
		return companyRegNo;
	}

	public void setCompanyRegNo(String companyRegNo) {
		this.companyRegNo = companyRegNo;
	}

	public String getCompanyOldRegNo() {
		return companyOldRegNo;
	}

	public void setCompanyOldRegNo(String companyOldRegNo) {
		this.companyOldRegNo = companyOldRegNo;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactPersonNumber() {
		return contactPersonNumber;
	}

	public void setContactPersonNumber(String contactPersonNumber) {
		this.contactPersonNumber = contactPersonNumber;
	}

	public List<PurchaserequestbreakdownModel> getProductList() {
		return productList;
	}

	public void setProductList(List<PurchaserequestbreakdownModel> productList) {
		this.productList = productList;
	}

}
