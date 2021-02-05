package com.project.model.invoice.customer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.project.model.datamodel.CustomerModel;

public class CustomerinvoiceModel {

	private int customerInvoiceId;

	private String approvedBy;

	private Date approvedDate;

	private String createdBy;

	private Date createdDate;

	private BigDecimal dispatchAmount;

	private BigDecimal invoiceAmount;
	
	private BigDecimal totalAmount;

	private Date invoiceDate;

	private String invoiceNo;

	private BigDecimal paidAmount;

	private BigDecimal pendingAmount;

	private Date salesOrderDate;

	private String salesOrderNo;

	private String status;

	private BigDecimal tax;
	
	CustomerModel customer = new CustomerModel();

	InvoicetaxshipModel invoicetaxship = new InvoicetaxshipModel();

	InvoicedispatchModel invoicedispatch =new InvoicedispatchModel();

	List<CustomerinvoicebreakdownModel> customerinvoicebreakdowns = new ArrayList<CustomerinvoicebreakdownModel>();

	public int getCustomerInvoiceId() {
		return customerInvoiceId;
	}

	public void setCustomerInvoiceId(int customerInvoiceId) {
		this.customerInvoiceId = customerInvoiceId;
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

	public Date getSalesOrderDate() {
		return salesOrderDate;
	}

	public void setSalesOrderDate(Date salesOrderDate) {
		this.salesOrderDate = salesOrderDate;
	}

	public String getSalesOrderNo() {
		return salesOrderNo;
	}

	public void setSalesOrderNo(String salesOrderNo) {
		this.salesOrderNo = salesOrderNo;
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

	public CustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

	public List<CustomerinvoicebreakdownModel> getCustomerinvoicebreakdowns() {
		return customerinvoicebreakdowns;
	}

	public void setCustomerinvoicebreakdowns(
			List<CustomerinvoicebreakdownModel> customerinvoicebreakdowns) {
		this.customerinvoicebreakdowns = customerinvoicebreakdowns;
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

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	

}
