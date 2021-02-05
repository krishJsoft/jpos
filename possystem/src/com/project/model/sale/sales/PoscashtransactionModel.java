package com.project.model.sale.sales;

import java.math.BigDecimal;
import java.util.Date;

public class PoscashtransactionModel {

	private int cashid;

	private String counterNo;

	private int counterId;

	private BigDecimal creditamount = new BigDecimal(0.00);
	
	private BigDecimal salesamount = new BigDecimal(0.00);

	private BigDecimal amount = new BigDecimal(0.00);

	private BigDecimal debitAmount = new BigDecimal(0.00);

	private String lastupdatedBy;

	private Date lastupdatedDate;

	private String remarks;

	private String type;

	private String transactionType;

	private String status;

	private String salesOrderNo;

	PoscounterModel poscounter;

	private BigDecimal totalAmount = new BigDecimal(0.00);

	private BigDecimal balanceAmount = new BigDecimal(0.00);

	private String transactionStatus;

	private String approvedBy;

	private Date approvedDate;

	private String paymentType;

	private Integer paymentCount;

	private String collectedBy;

	private BigDecimal receivedAmount = new BigDecimal(0.00);

	private BigDecimal totalTax;

	private String branchtype;
	private int branchRecordId;

	public int getCashid() {
		return cashid;
	}

	public void setCashid(int cashid) {
		this.cashid = cashid;
	}

	public String getCounterNo() {
		return counterNo;
	}

	public void setCounterNo(String counterNo) {
		this.counterNo = counterNo;
	}

	public BigDecimal getCreditamount() {
		return creditamount;
	}

	public void setCreditamount(BigDecimal creditamount) {
		this.creditamount = creditamount;
	}

	public BigDecimal getDebitAmount() {
		return debitAmount;
	}

	public void setDebitAmount(BigDecimal debitAmount) {
		this.debitAmount = debitAmount;
	}

	public String getLastupdatedBy() {
		return lastupdatedBy;
	}

	public void setLastupdatedBy(String lastupdatedBy) {
		this.lastupdatedBy = lastupdatedBy;
	}

	public Date getLastupdatedDate() {
		return lastupdatedDate;
	}

	public void setLastupdatedDate(Date lastupdatedDate) {
		this.lastupdatedDate = lastupdatedDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public int getCounterId() {
		return counterId;
	}

	public void setCounterId(int counterId) {
		this.counterId = counterId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSalesOrderNo() {
		return salesOrderNo;
	}

	public void setSalesOrderNo(String salesOrderNo) {
		this.salesOrderNo = salesOrderNo;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public PoscounterModel getPoscounter() {
		return poscounter;
	}

	public void setPoscounter(PoscounterModel poscounter) {
		this.poscounter = poscounter;
	}

	public BigDecimal getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
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

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Integer getPaymentCount() {
		return paymentCount;
	}

	public void setPaymentCount(Integer paymentCount) {
		this.paymentCount = paymentCount;
	}

	public String getCollectedBy() {
		return collectedBy;
	}

	public void setCollectedBy(String collectedBy) {
		this.collectedBy = collectedBy;
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

	public BigDecimal getReceivedAmount() {
		return receivedAmount;
	}

	public void setReceivedAmount(BigDecimal receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	public BigDecimal getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(BigDecimal totalTax) {
		this.totalTax = totalTax;
	}

	public BigDecimal getSalesamount() {
		return salesamount;
	}

	public void setSalesamount(BigDecimal salesamount) {
		this.salesamount = salesamount;
	}

	
}
