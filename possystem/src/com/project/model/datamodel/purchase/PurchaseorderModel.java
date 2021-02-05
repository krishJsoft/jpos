package com.project.model.datamodel.purchase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.SupplierModel;

public class PurchaseorderModel {

	private int purchaseOrderId;

	private Date agreedDateDelivery;

	private String billingAddress;

	private String createdBy;

	private Date createdDate;

	private String deliveryAddress;

	private Date lastModifiedDate;

	private String orderPayment;

	private String orderReceived;

	private String orderReturned;

	private String paymentTerms;

	private Date purchaseOrderDate;

	private String purchaseOrderNo;

	private String purchaseRequestReferenceNo;

	private String remarks;

	private String status;

	private String supportingFileName;

	private String termsConditions;

	private BigDecimal totalAmount;

	private int supplierId;

	private int branchId;
	
	private String branchName;

	private String companyName;

	private String email;

	private String filePath;

	private int totalItemCount;

	private int receivedQuantityCount;

	private int balanceQuantityCount;

	private String branchtype;

	private Integer branchRecordId;
	
	private BigDecimal totalTax;

	SupplierModel supplier = new SupplierModel();
	
	BranchModel branch = new BranchModel();

	private List<PurchaseorderbreakdownsModel> purchaseorderbreakdowns;

	private List<PurchaserequestBranchModel> branchModel;

	private List<PurchaseorderdeliveryaddressModel> purchaseorderdeliveryaddresses;

	List<PurchaseorderbreakdownsModel> purchaseRevertItemlList = new ArrayList<PurchaseorderbreakdownsModel>();

	public int getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(int purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public Date getAgreedDateDelivery() {
		return agreedDateDelivery;
	}

	public void setAgreedDateDelivery(Date agreedDateDelivery) {
		this.agreedDateDelivery = agreedDateDelivery;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
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

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getOrderPayment() {
		return orderPayment;
	}

	public void setOrderPayment(String orderPayment) {
		this.orderPayment = orderPayment;
	}

	public String getOrderReceived() {
		return orderReceived;
	}

	public void setOrderReceived(String orderReceived) {
		this.orderReceived = orderReceived;
	}

	public String getOrderReturned() {
		return orderReturned;
	}

	public void setOrderReturned(String orderReturned) {
		this.orderReturned = orderReturned;
	}

	public String getPaymentTerms() {
		return paymentTerms;
	}

	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	public Date getPurchaseOrderDate() {
		return purchaseOrderDate;
	}

	public void setPurchaseOrderDate(Date purchaseOrderDate) {
		this.purchaseOrderDate = purchaseOrderDate;
	}

	public String getPurchaseOrderNo() {
		return purchaseOrderNo;
	}

	public void setPurchaseOrderNo(String purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo;
	}

	public String getPurchaseRequestReferenceNo() {
		return purchaseRequestReferenceNo;
	}

	public void setPurchaseRequestReferenceNo(String purchaseRequestReferenceNo) {
		this.purchaseRequestReferenceNo = purchaseRequestReferenceNo;
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

	public String getSupportingFileName() {
		return supportingFileName;
	}

	public void setSupportingFileName(String supportingFileName) {
		this.supportingFileName = supportingFileName;
	}

	public String getTermsConditions() {
		return termsConditions;
	}

	public void setTermsConditions(String termsConditions) {
		this.termsConditions = termsConditions;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<PurchaseorderbreakdownsModel> getPurchaseorderbreakdowns() {
		return purchaseorderbreakdowns;
	}

	public void setPurchaseorderbreakdowns(
			List<PurchaseorderbreakdownsModel> purchaseorderbreakdowns) {
		this.purchaseorderbreakdowns = purchaseorderbreakdowns;
	}

	public List<PurchaserequestBranchModel> getBranchModel() {
		return branchModel;
	}

	public void setBranchModel(List<PurchaserequestBranchModel> branchModel) {
		this.branchModel = branchModel;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getTotalItemCount() {
		return totalItemCount;
	}

	public void setTotalItemCount(int totalItemCount) {
		this.totalItemCount = totalItemCount;
	}

	public int getReceivedQuantityCount() {
		return receivedQuantityCount;
	}

	public void setReceivedQuantityCount(int receivedQuantityCount) {
		this.receivedQuantityCount = receivedQuantityCount;
	}

	public int getBalanceQuantityCount() {
		return balanceQuantityCount;
	}

	public void setBalanceQuantityCount(int balanceQuantityCount) {
		this.balanceQuantityCount = balanceQuantityCount;
	}

	public SupplierModel getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierModel supplier) {
		this.supplier = supplier;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public List<PurchaseorderdeliveryaddressModel> getPurchaseorderdeliveryaddresses() {
		return purchaseorderdeliveryaddresses;
	}

	public void setPurchaseorderdeliveryaddresses(
			List<PurchaseorderdeliveryaddressModel> purchaseorderdeliveryaddresses) {
		this.purchaseorderdeliveryaddresses = purchaseorderdeliveryaddresses;
	}

	public List<PurchaseorderbreakdownsModel> getPurchaseRevertItemlList() {
		return purchaseRevertItemlList;
	}

	public void setPurchaseRevertItemlList(
			List<PurchaseorderbreakdownsModel> purchaseRevertItemlList) {
		this.purchaseRevertItemlList = purchaseRevertItemlList;
	}

	public String getBranchtype() {
		return branchtype;
	}

	public void setBranchtype(String branchtype) {
		this.branchtype = branchtype;
	}

	public Integer getBranchRecordId() {
		return branchRecordId;
	}

	public void setBranchRecordId(Integer branchRecordId) {
		this.branchRecordId = branchRecordId;
	}

	public BigDecimal getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(BigDecimal totalTax) {
		this.totalTax = totalTax;
	}

	public BranchModel getBranch() {
		return branch;
	}

	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	

}
