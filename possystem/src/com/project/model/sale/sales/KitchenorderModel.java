package com.project.model.sale.sales;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.BranchstaffmemberModel;
import com.project.model.datamodel.CustomerModel;


public class KitchenorderModel {

	private int salesOrderId;

	private BigDecimal balanceAmount;

	private String createdBy;

	private Date createdDate;

	private String customerName;

	private String customerBranchName;
	
	private String salesBranchName;
	
	private String salesBranchAddress;
	
	private String salesBranchPhone;
	
	private String salesBranchPost;

	private Integer customerBranchId;

	private BigDecimal invoiceAmount;

	private Date dueDate;

	private Date lastModifiedDate;

	private String orderOnhold;

	private String orderPayment;

	private String orderReturned;

	private String paymentType;

	private String pricingCurrency;

	private String quoteNo;

	private BigDecimal receivedAmount;
	
	private BigDecimal changeAmount;

	private String remarks;

	private Date requestedShipDate;

	private String salesDate;

	private String salesOrderNo;

	private String deliveryOrderNo;

	private String salesRep;

	private Date shippedDate;

	private String status;

	private String taxingScheme;

	private BigDecimal totalAmount;
	
	private BigDecimal totalTax;

	private String salesType;
	
	String branchSales;

	private String prescriptionNo;

	private BigDecimal commissionAmount;
	
	String loyaltyCardCode;

	CustomerModel customer = new CustomerModel();

	BranchModel branch = new BranchModel();
	
	BranchModel salesbranch = new BranchModel();

	private int branchId;
	private String branchtype;
	private int branchRecordId;
	private int counterId;

	private List<SalesorderbreakdownModel> salesorderbreakdowns;

	List<PaymentCollectionModel> pospayments = new ArrayList<PaymentCollectionModel>();

	PoscashtransactionModel poscashtransaction = new PoscashtransactionModel();

	DoctorsPrescriptionsModel dpModel = new DoctorsPrescriptionsModel();

	BranchstaffmemberModel branchstaffmember = new BranchstaffmemberModel();

	public int getSalesOrderId() {
		return salesOrderId;
	}

	public void setSalesOrderId(int salesOrderId) {
		this.salesOrderId = salesOrderId;
	}

	public BigDecimal getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	

	public String getSalesBranchName() {
		return salesBranchName;
	}

	public void setSalesBranchName(String salesBranchName) {
		this.salesBranchName = salesBranchName;
	}

	public String getSalesBranchAddress() {
		return salesBranchAddress;
	}

	public void setSalesBranchAddress(String salesBranchAddress) {
		this.salesBranchAddress = salesBranchAddress;
	}

	public String getSalesBranchPhone() {
		return salesBranchPhone;
	}

	public void setSalesBranchPhone(String salesBranchPhone) {
		this.salesBranchPhone = salesBranchPhone;
	}

	public String getSalesBranchPost() {
		return salesBranchPost;
	}

	public void setSalesBranchPost(String salesBranchPost) {
		this.salesBranchPost = salesBranchPost;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	public String getLoyaltyCardCode() {
		return loyaltyCardCode;
	}

	public void setLoyaltyCardCode(String loyaltyCardCode) {
		this.loyaltyCardCode = loyaltyCardCode;
	}

	public String getOrderOnhold() {
		return orderOnhold;
	}

	public void setOrderOnhold(String orderOnhold) {
		this.orderOnhold = orderOnhold;
	}

	public String getOrderPayment() {
		return orderPayment;
	}

	public void setOrderPayment(String orderPayment) {
		this.orderPayment = orderPayment;
	}

	public String getOrderReturned() {
		return orderReturned;
	}

	public void setOrderReturned(String orderReturned) {
		this.orderReturned = orderReturned;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPricingCurrency() {
		return pricingCurrency;
	}

	public void setPricingCurrency(String pricingCurrency) {
		this.pricingCurrency = pricingCurrency;
	}

	public String getQuoteNo() {
		return quoteNo;
	}

	public void setQuoteNo(String quoteNo) {
		this.quoteNo = quoteNo;
	}

	public BigDecimal getReceivedAmount() {
		return receivedAmount;
	}

	public void setReceivedAmount(BigDecimal receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getRequestedShipDate() {
		return requestedShipDate;
	}

	public void setRequestedShipDate(Date requestedShipDate) {
		this.requestedShipDate = requestedShipDate;
	}

	public String getSalesDate() {
		return salesDate;
	}

	public void setSalesDate(String salesDate) {
		this.salesDate = salesDate;
	}

	public String getSalesOrderNo() {
		return salesOrderNo;
	}

	public void setSalesOrderNo(String salesOrderNo) {
		this.salesOrderNo = salesOrderNo;
	}

	public String getSalesRep() {
		return salesRep;
	}

	public void setSalesRep(String salesRep) {
		this.salesRep = salesRep;
	}

	public Date getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTaxingScheme() {
		return taxingScheme;
	}

	public void setTaxingScheme(String taxingScheme) {
		this.taxingScheme = taxingScheme;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getDeliveryOrderNo() {
		return deliveryOrderNo;
	}

	public void setDeliveryOrderNo(String deliveryOrderNo) {
		this.deliveryOrderNo = deliveryOrderNo;
	}

	public String getSalesType() {
		return salesType;
	}

	public void setSalesType(String salesType) {
		this.salesType = salesType;
	}

	public List<SalesorderbreakdownModel> getSalesorderbreakdowns() {
		return salesorderbreakdowns;
	}

	public void setSalesorderbreakdowns(
			List<SalesorderbreakdownModel> salesorderbreakdowns) {
		this.salesorderbreakdowns = salesorderbreakdowns;
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

	public CustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

	public BranchModel getBranch() {
		return branch;
	}

	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}

	public BigDecimal getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public List<PaymentCollectionModel> getPospayments() {
		return pospayments;
	}

	public void setPospayments(List<PaymentCollectionModel> pospayments) {
		this.pospayments = pospayments;
	}

	public PoscashtransactionModel getPoscashtransaction() {
		return poscashtransaction;
	}

	public void setPoscashtransaction(PoscashtransactionModel poscashtransaction) {
		this.poscashtransaction = poscashtransaction;
	}

	public String getPrescriptionNo() {
		return prescriptionNo;
	}

	public void setPrescriptionNo(String prescriptionNo) {
		this.prescriptionNo = prescriptionNo;
	}

	public DoctorsPrescriptionsModel getDpModel() {
		return dpModel;
	}

	public void setDpModel(DoctorsPrescriptionsModel dpModel) {
		this.dpModel = dpModel;
	}

	public BigDecimal getCommissionAmount() {
		return commissionAmount;
	}

	public void setCommissionAmount(BigDecimal commissionAmount) {
		this.commissionAmount = commissionAmount;
	}

	public BranchstaffmemberModel getBranchstaffmember() {
		return branchstaffmember;
	}

	public void setBranchstaffmember(BranchstaffmemberModel branchstaffmember) {
		this.branchstaffmember = branchstaffmember;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getBranchtype() {
		return branchtype;
	}

	public void setBranchtype(String branchtype) {
		this.branchtype = branchtype;
	}

	public int getBranchRecordId() {
		return branchRecordId;
	}

	public void setBranchRecordId(int branchRecordId) {
		this.branchRecordId = branchRecordId;
	}

	public BigDecimal getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(BigDecimal totalTax) {
		this.totalTax = totalTax;
	}

	public String getBranchSales() {
		return branchSales;
	}

	public void setBranchSales(String branchSales) {
		this.branchSales = branchSales;
	}

	public BranchModel getSalesbranch() {
		return salesbranch;
	}

	public void setSalesbranch(BranchModel salesbranch) {
		this.salesbranch = salesbranch;
	}

	public BigDecimal getChangeAmount() {
		return changeAmount;
	}

	public void setChangeAmount(BigDecimal changeAmount) {
		this.changeAmount = changeAmount;
	}

	public int getCounterId() {
		return counterId;
	}

	public void setCounterId(int counterId) {
		this.counterId = counterId;
	}
	
	

}
