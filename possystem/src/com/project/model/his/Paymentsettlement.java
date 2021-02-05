package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the paymentsettlements database table.
 * 
 */
@Entity
@Table(name = "paymentsettlements")
public class Paymentsettlement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private int settlementId;

	@Column(length = 45)
	private String approvedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date approvedDate;

	@Column(precision = 10, scale = 2)
	private BigDecimal clearAmount;

	@Column(length = 500)
	private String comments;

	@Column(length = 25)
	private String paymentMode;

	@Column(length = 45)
	private String paymentNo;

	@Column(length = 45)
	private String processedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date processedDate;

	@Column(length = 45)
	private String referenceNumber;

	@Column(precision = 10, scale = 2)
	private BigDecimal settlementAmount;

	@Temporal(TemporalType.TIMESTAMP)
	private Date settlementDate;

	@Column(length = 1)
	private String settlementType;

	@Column(length = 1)
	private String status;

	@Column(length = 1)
	private String paymentType;

	@Column(precision = 10, scale = 2)
	private BigDecimal unclearAmount;

	// bi-directional many-to-one association to Paymentsettlementapportion
	@OneToMany(mappedBy = "paymentsettlement")
	private List<Paymentsettlementapportion> paymentsettlementapportions;

	// bi-directional many-to-one association to Branch
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branchRecordId")
	private Branch branch;

	// bi-directional many-to-one association to Branch
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplyBranchId")
	private Branch branch1;

	// bi-directional many-to-one association to Supplier
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplierId")
	private Supplier supplier;

	public Paymentsettlement() {
	}

	public int getSettlementId() {
		return this.settlementId;
	}

	public void setSettlementId(int settlementId) {
		this.settlementId = settlementId;
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

	public BigDecimal getClearAmount() {
		return this.clearAmount;
	}

	public void setClearAmount(BigDecimal clearAmount) {
		this.clearAmount = clearAmount;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getPaymentMode() {
		return this.paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getPaymentNo() {
		return this.paymentNo;
	}

	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}

	public String getProcessedBy() {
		return this.processedBy;
	}

	public void setProcessedBy(String processedBy) {
		this.processedBy = processedBy;
	}

	public Date getProcessedDate() {
		return this.processedDate;
	}

	public void setProcessedDate(Date processedDate) {
		this.processedDate = processedDate;
	}

	public String getReferenceNumber() {
		return this.referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public BigDecimal getSettlementAmount() {
		return this.settlementAmount;
	}

	public void setSettlementAmount(BigDecimal settlementAmount) {
		this.settlementAmount = settlementAmount;
	}

	public Date getSettlementDate() {
		return this.settlementDate;
	}

	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}

	public String getSettlementType() {
		return this.settlementType;
	}

	public void setSettlementType(String settlementType) {
		this.settlementType = settlementType;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getUnclearAmount() {
		return this.unclearAmount;
	}

	public void setUnclearAmount(BigDecimal unclearAmount) {
		this.unclearAmount = unclearAmount;
	}

	public List<Paymentsettlementapportion> getPaymentsettlementapportions() {
		return this.paymentsettlementapportions;
	}

	public void setPaymentsettlementapportions(
			List<Paymentsettlementapportion> paymentsettlementapportions) {
		this.paymentsettlementapportions = paymentsettlementapportions;
	}

	public Paymentsettlementapportion addPaymentsettlementapportion(
			Paymentsettlementapportion paymentsettlementapportion) {
		getPaymentsettlementapportions().add(paymentsettlementapportion);
		paymentsettlementapportion.setPaymentsettlement(this);

		return paymentsettlementapportion;
	}

	public Paymentsettlementapportion removePaymentsettlementapportion(
			Paymentsettlementapportion paymentsettlementapportion) {
		getPaymentsettlementapportions().remove(paymentsettlementapportion);
		paymentsettlementapportion.setPaymentsettlement(null);

		return paymentsettlementapportion;
	}

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Supplier getSupplier() {
		return this.supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Branch getBranch1() {
		return branch1;
	}

	public void setBranch1(Branch branch1) {
		this.branch1 = branch1;
	}

	
	
}