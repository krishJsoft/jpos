package com.project.model.invoice.branch;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.CustomerModel;
import com.project.model.invoice.customer.InvoicedispatchModel;
import com.project.model.invoice.customer.InvoicetaxshipModel;

public class BranchinvoiceModel {

	private int branchInvoiceId;

	private String accountNo;

	private String approvedBy;

	private Date approvedDate;

	private String bankName;

	private String createdBy;

	private Date createdDate;

	private BigDecimal dispatchAmount;

	private BigDecimal invoiceAmount;

	private BigDecimal totalAmount;

	private Date invoiceDate;

	private String invoiceNo;

	private String salesOrderNo;

	private BigDecimal paidAmount;

	private BigDecimal pendingAmount;

	private String referenceNo;

	private BigDecimal requestDate;

	private String status;
	
	private String accountstatus;

	private BigDecimal tax;

	private String invoiceType;
	
	private String salesType;

	private String customerBranchName;

	private Integer customerBranchId;

	private Integer branchRecordId;
	
	private String remarks;
	
	private String branchcustomerName;

	InvoicetaxshipModel invoicetaxship = new InvoicetaxshipModel();

	InvoicedispatchModel invoicedispatch = new InvoicedispatchModel();

	List<BranchinvoicebreakdownModel> branchinvoicebreakdowns = new ArrayList<BranchinvoicebreakdownModel>();

	BranchModel branch = new BranchModel();

	CustomerModel customer = new CustomerModel();

	public int getBranchInvoiceId() {
		return branchInvoiceId;
	}

	public void setBranchInvoiceId(int branchInvoiceId) {
		this.branchInvoiceId = branchInvoiceId;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
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

	public BigDecimal getDispatchAmount() {
		return dispatchAmount;
	}

	public void setDispatchAmount(BigDecimal dispatchAmount) {
		this.dispatchAmount = dispatchAmount;
	}

	public BigDecimal getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getSalesOrderNo() {
		return salesOrderNo;
	}

	public void setSalesOrderNo(String salesOrderNo) {
		this.salesOrderNo = salesOrderNo;
	}

	public BigDecimal getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public BigDecimal getPendingAmount() {
		return pendingAmount;
	}

	public void setPendingAmount(BigDecimal pendingAmount) {
		this.pendingAmount = pendingAmount;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public BigDecimal getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(BigDecimal requestDate) {
		this.requestDate = requestDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public InvoicetaxshipModel getInvoicetaxship() {
		return invoicetaxship;
	}

	public void setInvoicetaxship(InvoicetaxshipModel invoicetaxship) {
		this.invoicetaxship = invoicetaxship;
	}

	public InvoicedispatchModel getInvoicedispatch() {
		return invoicedispatch;
	}

	public void setInvoicedispatch(InvoicedispatchModel invoicedispatch) {
		this.invoicedispatch = invoicedispatch;
	}

	public List<BranchinvoicebreakdownModel> getBranchinvoicebreakdowns() {
		return branchinvoicebreakdowns;
	}

	public void setBranchinvoicebreakdowns(
			List<BranchinvoicebreakdownModel> branchinvoicebreakdowns) {
		this.branchinvoicebreakdowns = branchinvoicebreakdowns;
	}

	public BranchModel getBranch() {
		return branch;
	}

	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public CustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

	public Integer getBranchRecordId() {
		return branchRecordId;
	}

	public void setBranchRecordId(Integer branchRecordId) {
		this.branchRecordId = branchRecordId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCustomerBranchName() {
		return customerBranchName;
	}

	public void setCustomerBranchName(String customerBranchName) {
		this.customerBranchName = customerBranchName;
	}

	public Integer getCustomerBranchId() {
		return customerBranchId;
	}

	public void setCustomerBranchId(Integer customerBranchId) {
		this.customerBranchId = customerBranchId;
	}

	public String getBranchcustomerName() {
		return branchcustomerName;
	}

	public void setBranchcustomerName(String branchcustomerName) {
		this.branchcustomerName = branchcustomerName;
	}

	public String getSalesType() {
		return salesType;
	}

	public void setSalesType(String salesType) {
		this.salesType = salesType;
	}

	public String getAccountstatus() {
		return accountstatus;
	}

	public void setAccountstatus(String accountstatus) {
		this.accountstatus = accountstatus;
	}

	
}
