package com.project.model.payment.collection;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.CustomerModel;
import com.project.model.invoice.branch.BranchinvoiceModel;

public class PaymentcollectionModel {

	private int collectionId;

	private String approvedBy;

	private Date approvedDate;

	private BigDecimal clearAmount;

	private BigDecimal collectionAmount;

	private Date collectionDate;

	private String collectionType;

	private String comments;

	private String paymentMode;

	private String processedBy;

	private Date processedDate;

	private String referenceNumber;

	private String status;

	private String paymentNo;

	private BigDecimal unclearAmount;
	
	Integer branchRecordId;

	List<PaymentcollectionapportionModel> paymentcollectionapportions = new ArrayList<PaymentcollectionapportionModel>();

	private BranchModel branch;

	private CustomerModel customer;
	
	private String branchcustomerName;
	
	private int settlementId;
	
	List<BranchinvoiceModel> invoiceList=new ArrayList<BranchinvoiceModel>();
	

	public int getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(int collectionId) {
		this.collectionId = collectionId;
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

	public BigDecimal getClearAmount() {
		return clearAmount;
	}

	public void setClearAmount(BigDecimal clearAmount) {
		this.clearAmount = clearAmount;
	}

	public BigDecimal getCollectionAmount() {
		return collectionAmount;
	}

	public void setCollectionAmount(BigDecimal collectionAmount) {
		this.collectionAmount = collectionAmount;
	}

	public Date getCollectionDate() {
		return collectionDate;
	}

	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate;
	}

	public String getCollectionType() {
		return collectionType;
	}

	public void setCollectionType(String collectionType) {
		this.collectionType = collectionType;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getProcessedBy() {
		return processedBy;
	}

	public void setProcessedBy(String processedBy) {
		this.processedBy = processedBy;
	}

	public Date getProcessedDate() {
		return processedDate;
	}

	public void setProcessedDate(Date processedDate) {
		this.processedDate = processedDate;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getUnclearAmount() {
		return unclearAmount;
	}

	public void setUnclearAmount(BigDecimal unclearAmount) {
		this.unclearAmount = unclearAmount;
	}	

	public List<PaymentcollectionapportionModel> getPaymentcollectionapportions() {
		return paymentcollectionapportions;
	}

	public void setPaymentcollectionapportions(
			List<PaymentcollectionapportionModel> paymentcollectionapportions) {
		this.paymentcollectionapportions = paymentcollectionapportions;
	}

	public BranchModel getBranch() {
		return branch;
	}

	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}

	public CustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

	public String getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}

	public Integer getBranchRecordId() {
		return branchRecordId;
	}

	public void setBranchRecordId(Integer branchRecordId) {
		this.branchRecordId = branchRecordId;
	}

	public String getBranchcustomerName() {
		return branchcustomerName;
	}

	public void setBranchcustomerName(String branchcustomerName) {
		this.branchcustomerName = branchcustomerName;
	}

	public List<BranchinvoiceModel> getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(List<BranchinvoiceModel> invoiceList) {
		this.invoiceList = invoiceList;
	}

	public int getSettlementId() {
		return settlementId;
	}

	public void setSettlementId(int settlementId) {
		this.settlementId = settlementId;
	}

	
}
