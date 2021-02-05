package com.project.model.payment.settlement;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.SupplierModel;

public class PaymentsettlementModel {

	private int settlementId;
	private String approvedBy;
	private Date approvedDate;
	private BigDecimal clearAmount;
	private String comments;

	private String branchName;
	
	private String paymentMode;
	
	private String collectionType;
	
	private String paymentNo;

	private String processedBy;

	private Date processedDate;

	private String referenceNumber;

	private BigDecimal settlementAmount;

	private Date settlementDate;

	private String settlementType;

	private String status;

	private BigDecimal unclearAmount;

	SupplierModel supplier;

	Integer branchRecordId;

	List<PaymentsettlementapportionModel> paymentsettlementapportions;
	private BranchModel branch;
	
	public int getSettlementId() {
		return settlementId;
	}

	public void setSettlementId(int settlementId) {
		this.settlementId = settlementId;
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

	public String getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
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

	public BigDecimal getSettlementAmount() {
		return settlementAmount;
	}

	public void setSettlementAmount(BigDecimal settlementAmount) {
		this.settlementAmount = settlementAmount;
	}

	public Date getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}

	public String getSettlementType() {
		return settlementType;
	}

	public void setSettlementType(String settlementType) {
		this.settlementType = settlementType;
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

	public SupplierModel getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierModel supplier) {
		this.supplier = supplier;
	}

	public List<PaymentsettlementapportionModel> getPaymentsettlementapportions() {
		return paymentsettlementapportions;
	}

	public void setPaymentsettlementapportions(
			List<PaymentsettlementapportionModel> paymentsettlementapportions) {
		this.paymentsettlementapportions = paymentsettlementapportions;
	}

	public Integer getBranchRecordId() {
		return branchRecordId;
	}

	public void setBranchRecordId(Integer branchRecordId) {
		this.branchRecordId = branchRecordId;
	}

	public String getCollectionType() {
		return collectionType;
	}

	public void setCollectionType(String collectionType) {
		this.collectionType = collectionType;
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
