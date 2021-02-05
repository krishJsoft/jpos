package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the poscashtransaction database table.
 * 
 */
@Entity
@Table(name = "poscashtransaction")
public class Poscashtransaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private int cashid;

	@Column(length = 50)
	private String approvedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date approvedDate;

	@Column(length = 50)
	private String collectedBy;

	@Column(precision = 10, scale = 2)
	private BigDecimal creditamount;

	@Column(precision = 10, scale = 2)
	private BigDecimal debitAmount;

	@Column(precision = 10, scale = 2)
	private BigDecimal receivedAmount;

	@Column(length = 50)
	private String lastupdatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastupdatedDate;

	private int paymentCount;

	@Column(length = 30)
	private String paymentType;

	@Column(length = 500)
	private String remarks;

	@Column(length = 45)
	private String salesOrderNo;

	@Column(length = 1)
	private String status;

	@Column(length = 1)
	private String transactionStatus;

	@Column(length = 1)
	private String transactionType;

	@Column(length = 1)
	private String type;

	@Column(length = 1)
	private String branchtype;

	@Column(precision = 10, scale = 2)
	private BigDecimal totalTax;

	// bi-directional many-to-one association to Branch
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branchRecordId")
	private Branch branch;

	// bi-directional many-to-one association to Poscounter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "counterId")
	private Poscounter poscounter;

	public Poscashtransaction() {
	}

	public int getCashid() {
		return this.cashid;
	}

	public void setCashid(int cashid) {
		this.cashid = cashid;
	}

	public String getApprovedBy() {
		return this.approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Date getApprovedDate() {
		return this.approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getCollectedBy() {
		return this.collectedBy;
	}

	public void setCollectedBy(String collectedBy) {
		this.collectedBy = collectedBy;
	}

	public BigDecimal getCreditamount() {
		return this.creditamount;
	}

	public void setCreditamount(BigDecimal creditamount) {
		this.creditamount = creditamount;
	}

	public BigDecimal getDebitAmount() {
		return this.debitAmount;
	}

	public void setDebitAmount(BigDecimal debitAmount) {
		this.debitAmount = debitAmount;
	}

	public String getLastupdatedBy() {
		return this.lastupdatedBy;
	}

	public void setLastupdatedBy(String lastupdatedBy) {
		this.lastupdatedBy = lastupdatedBy;
	}

	public Date getLastupdatedDate() {
		return this.lastupdatedDate;
	}

	public void setLastupdatedDate(Date lastupdatedDate) {
		this.lastupdatedDate = lastupdatedDate;
	}

	public int getPaymentCount() {
		return this.paymentCount;
	}

	public void setPaymentCount(int paymentCount) {
		this.paymentCount = paymentCount;
	}

	public String getPaymentType() {
		return this.paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getSalesOrderNo() {
		return this.salesOrderNo;
	}

	public void setSalesOrderNo(String salesOrderNo) {
		this.salesOrderNo = salesOrderNo;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTransactionStatus() {
		return this.transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public String getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Poscounter getPoscounter() {
		return this.poscounter;
	}

	public void setPoscounter(Poscounter poscounter) {
		this.poscounter = poscounter;
	}

	public String getBranchtype() {
		return branchtype;
	}

	public void setBranchtype(String branchtype) {
		this.branchtype = branchtype;
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

}