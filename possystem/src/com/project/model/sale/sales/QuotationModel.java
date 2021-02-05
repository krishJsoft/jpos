package com.project.model.sale.sales;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.CustomerModel;
import com.project.model.datamodel.SupplierModel;

public class QuotationModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int quotationId;
	private String createdBy;
	private Date createdDate;
	private String deliveryTerms;
	private Date lastModifedDate;
	private String paymentTerms;
	private Date quotationDate;
	private Date quotationDueDate;
	private String quotationNo;
	private int itemCount;
	private String remarks;
	private String status;
	private BigDecimal totalAmount;
	private int customerId;
	private String customerName;
	private int sno;
	private BigDecimal totalItemCount;
	private BigDecimal SoldQuantityCount;
	private BigDecimal balanceQuantityCount;
	private BigDecimal totalTax;
	private String requestIds;	
	
	private String branchtype;
	private int branchRecordId;
	private Integer quotationsupplierId;
	
	private String deliveryTime;
	private String location;
	private String noofquest;
	private String deliveryAddress;
	private String addressLocationmap;
	private String ordertype;

	private List<QuotationbreakdownModel> quotationbreakdowns;
	CustomerModel customer = new CustomerModel();
	SupplierModel supplier = new SupplierModel();
	BranchModel branch = new BranchModel();
	
	List<SupplierModel> supplierList;
	
	public int getQuotationId() {
		return quotationId;
	}

	public void setQuotationId(int quotationId) {
		this.quotationId = quotationId;
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

	public String getDeliveryTerms() {
		return deliveryTerms;
	}

	public void setDeliveryTerms(String deliveryTerms) {
		this.deliveryTerms = deliveryTerms;
	}

	public Date getLastModifedDate() {
		return lastModifedDate;
	}

	public void setLastModifedDate(Date lastModifedDate) {
		this.lastModifedDate = lastModifedDate;
	}

	public String getPaymentTerms() {
		return paymentTerms;
	}

	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	public Date getQuotationDate() {
		return quotationDate;
	}

	public void setQuotationDate(Date quotationDate) {
		this.quotationDate = quotationDate;
	}

	public Date getQuotationDueDate() {
		return quotationDueDate;
	}

	public void setQuotationDueDate(Date quotationDueDate) {
		this.quotationDueDate = quotationDueDate;
	}

	public String getQuotationNo() {
		return quotationNo;
	}

	public void setQuotationNo(String quotationNo) {
		this.quotationNo = quotationNo;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public List<QuotationbreakdownModel> getQuotationbreakdowns() {
		return quotationbreakdowns;
	}

	public void setQuotationbreakdowns(
			List<QuotationbreakdownModel> quotationbreakdowns) {
		this.quotationbreakdowns = quotationbreakdowns;
	}

	public CustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

	public BigDecimal getTotalItemCount() {
		return totalItemCount;
	}

	public void setTotalItemCount(BigDecimal totalItemCount) {
		this.totalItemCount = totalItemCount;
	}

	public BigDecimal getSoldQuantityCount() {
		return SoldQuantityCount;
	}

	public void setSoldQuantityCount(BigDecimal soldQuantityCount) {
		SoldQuantityCount = soldQuantityCount;
	}

	public BigDecimal getBalanceQuantityCount() {
		return balanceQuantityCount;
	}

	public void setBalanceQuantityCount(BigDecimal balanceQuantityCount) {
		this.balanceQuantityCount = balanceQuantityCount;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
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

	public SupplierModel getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierModel supplier) {
		this.supplier = supplier;
	}

	public Integer getQuotationsupplierId() {
		return quotationsupplierId;
	}

	public void setQuotationsupplierId(Integer quotationsupplierId) {
		this.quotationsupplierId = quotationsupplierId;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getNoofquest() {
		return noofquest;
	}

	public void setNoofquest(String noofquest) {
		this.noofquest = noofquest;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getAddressLocationmap() {
		return addressLocationmap;
	}

	public void setAddressLocationmap(String addressLocationmap) {
		this.addressLocationmap = addressLocationmap;
	}

	public BranchModel getBranch() {
		return branch;
	}

	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}

	public List<SupplierModel> getSupplierList() {
		return supplierList;
	}

	public void setSupplierList(List<SupplierModel> supplierList) {
		this.supplierList = supplierList;
	}

	public String getRequestIds() {
		return requestIds;
	}

	public void setRequestIds(String requestIds) {
		this.requestIds = requestIds;
	}

	public String getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}

	

	
}
