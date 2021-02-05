package com.project.model.datamodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.project.model.sale.sales.QuotationModel;
import com.project.model.sale.sales.QuotationSupplierModel;
import com.project.model.sale.sales.QuotationbreakdownModel;

public class SupplierModel {

	private int supplierId;
	private String accountNumber;
	private String address;
	private String bankName;
	private String branchName;
	private String city;
	private String companyName;
	private String companyRegNo;
	private String companyOldRegNo;
	private String contactPerson;
	private String contactPersonNumber;
	private String country;
	private String createdBy;
	private Date createdDate;
	private String currency;
	private String deliveryMethod;
	private String email;
	private String faxNo;
	private Date lastModifiedDate;
	private String mobileNo;
	private String paymentTerms;
	private String phoneNo;
	private String postCode;
	private String remarks;
	private String state;
	private String status;
	private String supplierName;
	private String supplierCode;
	private String supplierOldCode;
	private String supportingFileName;
	private String taxSchedule;
	private String websiteName;
	
	private String password;
	private String themeName;
	private int invalidAttempts;
	private String forceReset;
	

	List<SupplierdocumentModel> doclist = new ArrayList<SupplierdocumentModel>();
	List<QuotationbreakdownModel> quotationbreakdownList = new ArrayList<QuotationbreakdownModel>();
	QuotationSupplierModel quotationsupplier = new QuotationSupplierModel();
	
	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
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

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPaymentTerms() {
		return paymentTerms;
	}

	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierOldCode() {
		return supplierOldCode;
	}

	public void setSupplierOldCode(String supplierOldCode) {
		this.supplierOldCode = supplierOldCode;
	}

	public String getSupportingFileName() {
		return supportingFileName;
	}

	public void setSupportingFileName(String supportingFileName) {
		this.supportingFileName = supportingFileName;
	}

	public String getTaxSchedule() {
		return taxSchedule;
	}

	public void setTaxSchedule(String taxSchedule) {
		this.taxSchedule = taxSchedule;
	}

	public String getWebsiteName() {
		return websiteName;
	}

	public void setWebsiteName(String websiteName) {
		this.websiteName = websiteName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<SupplierdocumentModel> getDoclist() {
		return doclist;
	}

	public void setDoclist(List<SupplierdocumentModel> doclist) {
		this.doclist = doclist;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	public int getInvalidAttempts() {
		return invalidAttempts;
	}

	public void setInvalidAttempts(int invalidAttempts) {
		this.invalidAttempts = invalidAttempts;
	}

	public String getForceReset() {
		return forceReset;
	}

	public void setForceReset(String forceReset) {
		this.forceReset = forceReset;
	}

	public List<QuotationbreakdownModel> getQuotationbreakdownList() {
		return quotationbreakdownList;
	}

	public void setQuotationbreakdownList(
			List<QuotationbreakdownModel> quotationbreakdownList) {
		this.quotationbreakdownList = quotationbreakdownList;
	}

	public QuotationSupplierModel getQuotationsupplier() {
		return quotationsupplier;
	}

	public void setQuotationsupplier(QuotationSupplierModel quotationsupplier) {
		this.quotationsupplier = quotationsupplier;
	}


	

}
