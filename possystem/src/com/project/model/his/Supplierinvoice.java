package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the supplierinvoice database table.
 * 
 */
@Entity
@Table(name = "supplierinvoice")
public class Supplierinvoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private int supplierInvoiceId;

	@Column(length = 45)
	private String approvedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date approvedDate;

	@Column(length = 45)
	private String branchName;

	@Column(length = 45)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date deliveryOrderDate;

	@Column(length = 45)
	private String deliveryOrderNo;

	@Column(precision = 10, scale = 2)
	private BigDecimal dispatchAmount;

	@Column(precision = 10, scale = 2)
	private BigDecimal invoiceAmount;

	@Temporal(TemporalType.TIMESTAMP)
	private Date invoiceDate;

	@Column(length = 45)
	private String invoiceNo;

	@Column(precision = 10, scale = 2)
	private BigDecimal paidAmount;

	@Column(precision = 10, scale = 2)
	private BigDecimal pendingAmount;

	@Temporal(TemporalType.TIMESTAMP)
	private Date purchaseOrderDate;

	@Column(length = 45)
	private String purchaseOrderNo;

	@Column(length = 350)
	private String remarks;

	@Column(length = 1)
	private String status;
	
	@Column(length=1)
	private String accountstatus;

	@Column(length = 1)
	private String invoicetype;

	@Column(precision = 10, scale = 2)
	private BigDecimal tax;

	@Column(precision = 10, scale = 2)
	private BigDecimal totalAmount;

	// bi-directional many-to-one association to Paymentsettlementapportion
	@OneToMany(mappedBy = "supplierinvoice", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Paymentsettlementapportion> paymentsettlementapportions;

	// bi-directional many-to-one association to Branch
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branchRecordId")
	private Branch branch;

	// bi-directional many-to-one association to Supplier
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SupplierId")
	private Supplier supplier;

	// bi-directional many-to-one association to Branch
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplyBranchId")
	private Branch branch1;

	// bi-directional many-to-one association to Supplierinvoicebreakdown
	@OneToMany(mappedBy = "supplierinvoice", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Supplierinvoicebreakdown> supplierinvoicebreakdowns;

	public Supplierinvoice() {
	}

	public int getSupplierInvoiceId() {
		return this.supplierInvoiceId;
	}

	public void setSupplierInvoiceId(int supplierInvoiceId) {
		this.supplierInvoiceId = supplierInvoiceId;
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

	public String getBranchName() {
		return this.branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getDeliveryOrderDate() {
		return this.deliveryOrderDate;
	}

	public void setDeliveryOrderDate(Date deliveryOrderDate) {
		this.deliveryOrderDate = deliveryOrderDate;
	}

	public String getDeliveryOrderNo() {
		return this.deliveryOrderNo;
	}

	public void setDeliveryOrderNo(String deliveryOrderNo) {
		this.deliveryOrderNo = deliveryOrderNo;
	}

	public BigDecimal getDispatchAmount() {
		return this.dispatchAmount;
	}

	public void setDispatchAmount(BigDecimal dispatchAmount) {
		this.dispatchAmount = dispatchAmount;
	}

	public BigDecimal getInvoiceAmount() {
		return this.invoiceAmount;
	}

	public void setInvoiceAmount(BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public Date getInvoiceDate() {
		return this.invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public BigDecimal getPaidAmount() {
		return this.paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public BigDecimal getPendingAmount() {
		return this.pendingAmount;
	}

	public void setPendingAmount(BigDecimal pendingAmount) {
		this.pendingAmount = pendingAmount;
	}

	public Date getPurchaseOrderDate() {
		return this.purchaseOrderDate;
	}

	public void setPurchaseOrderDate(Date purchaseOrderDate) {
		this.purchaseOrderDate = purchaseOrderDate;
	}

	public String getPurchaseOrderNo() {
		return this.purchaseOrderNo;
	}

	public void setPurchaseOrderNo(String purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTax() {
		return this.tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public BigDecimal getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
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
		paymentsettlementapportion.setSupplierinvoice(this);

		return paymentsettlementapportion;
	}

	public Paymentsettlementapportion removePaymentsettlementapportion(
			Paymentsettlementapportion paymentsettlementapportion) {
		getPaymentsettlementapportions().remove(paymentsettlementapportion);
		paymentsettlementapportion.setSupplierinvoice(null);

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

	public List<Supplierinvoicebreakdown> getSupplierinvoicebreakdowns() {
		return this.supplierinvoicebreakdowns;
	}

	public void setSupplierinvoicebreakdowns(
			List<Supplierinvoicebreakdown> supplierinvoicebreakdowns) {
		this.supplierinvoicebreakdowns = supplierinvoicebreakdowns;
	}

	public Supplierinvoicebreakdown addSupplierinvoicebreakdown(
			Supplierinvoicebreakdown supplierinvoicebreakdown) {
		getSupplierinvoicebreakdowns().add(supplierinvoicebreakdown);
		supplierinvoicebreakdown.setSupplierinvoice(this);

		return supplierinvoicebreakdown;
	}

	public Supplierinvoicebreakdown removeSupplierinvoicebreakdown(
			Supplierinvoicebreakdown supplierinvoicebreakdown) {
		getSupplierinvoicebreakdowns().remove(supplierinvoicebreakdown);
		supplierinvoicebreakdown.setSupplierinvoice(null);

		return supplierinvoicebreakdown;
	}

	public String getInvoicetype() {
		return invoicetype;
	}

	public void setInvoicetype(String invoicetype) {
		this.invoicetype = invoicetype;
	}

	public Branch getBranch1() {
		return branch1;
	}

	public void setBranch1(Branch branch1) {
		this.branch1 = branch1;
	}

	public String getAccountstatus() {
		return accountstatus;
	}

	public void setAccountstatus(String accountstatus) {
		this.accountstatus = accountstatus;
	}
	
	
	

}