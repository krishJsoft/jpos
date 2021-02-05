package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the branchinvoice database table.
 * 
 */
@Entity
@Table(name="branchinvoice")
public class Branchinvoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int branchInvoiceId;

	@Column(length=45)
	private String accountNo;

	@Column(length=45)
	private String approvedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date approvedDate;

	@Column(length=45)
	private String bankName;

	@Column(length=45)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(precision=10, scale=2)
	private BigDecimal dispatchAmount;

	@Column(precision=10, scale=2)
	private BigDecimal invoiceAmount;

	@Temporal(TemporalType.TIMESTAMP)
	private Date invoiceDate;

	@Column(length=45)
	private String invoiceNo;

	@Column(length=1)
	private String invoiceType;

	@Column(precision=10, scale=2)
	private BigDecimal paidAmount;

	@Column(precision=10, scale=2)
	private BigDecimal pendingAmount;

	@Column(length=45)
	private String referenceNo;

	@Column(length=400)
	private String remarks;

	@Column(precision=10, scale=2)
	private BigDecimal requestDate;

	@Column(length=45)
	private String salesOrderNo;

	@Column(length=1)
	private String status;
	
	@Column(length=1)
	private String accountstatus;	
	

	@Column(precision=10, scale=2)
	private BigDecimal tax;

	@Column(precision=10, scale=2)
	private BigDecimal totalAmount;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchRecordId")
	private Branch branch1;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BranchId")
	private Branch branch2;

	//bi-directional many-to-one association to Customer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CustomerId")
	private Customer customer;

	//bi-directional many-to-one association to Branchinvoicebreakdown
	@OneToMany(mappedBy="branchinvoice", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Branchinvoicebreakdown> branchinvoicebreakdowns;

	//bi-directional many-to-one association to Paymentcollectionapportion
	@OneToMany(mappedBy="branchinvoice", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Paymentcollectionapportion> paymentcollectionapportions;

	public Branchinvoice() {
	}

	public int getBranchInvoiceId() {
		return this.branchInvoiceId;
	}

	public void setBranchInvoiceId(int branchInvoiceId) {
		this.branchInvoiceId = branchInvoiceId;
	}

	public String getAccountNo() {
		return this.accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
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

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
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

	public String getInvoiceType() {
		return this.invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
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

	public String getReferenceNo() {
		return this.referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public BigDecimal getRequestDate() {
		return this.requestDate;
	}

	public void setRequestDate(BigDecimal requestDate) {
		this.requestDate = requestDate;
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

	public Branch getBranch1() {
		return this.branch1;
	}

	public void setBranch1(Branch branch1) {
		this.branch1 = branch1;
	}

	public Branch getBranch2() {
		return this.branch2;
	}

	public void setBranch2(Branch branch2) {
		this.branch2 = branch2;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Branchinvoicebreakdown> getBranchinvoicebreakdowns() {
		return this.branchinvoicebreakdowns;
	}

	public void setBranchinvoicebreakdowns(List<Branchinvoicebreakdown> branchinvoicebreakdowns) {
		this.branchinvoicebreakdowns = branchinvoicebreakdowns;
	}

	public Branchinvoicebreakdown addBranchinvoicebreakdown(Branchinvoicebreakdown branchinvoicebreakdown) {
		getBranchinvoicebreakdowns().add(branchinvoicebreakdown);
		branchinvoicebreakdown.setBranchinvoice(this);

		return branchinvoicebreakdown;
	}

	public Branchinvoicebreakdown removeBranchinvoicebreakdown(Branchinvoicebreakdown branchinvoicebreakdown) {
		getBranchinvoicebreakdowns().remove(branchinvoicebreakdown);
		branchinvoicebreakdown.setBranchinvoice(null);

		return branchinvoicebreakdown;
	}

	public List<Paymentcollectionapportion> getPaymentcollectionapportions() {
		return this.paymentcollectionapportions;
	}

	public void setPaymentcollectionapportions(List<Paymentcollectionapportion> paymentcollectionapportions) {
		this.paymentcollectionapportions = paymentcollectionapportions;
	}

	public Paymentcollectionapportion addPaymentcollectionapportion(Paymentcollectionapportion paymentcollectionapportion) {
		getPaymentcollectionapportions().add(paymentcollectionapportion);
		paymentcollectionapportion.setBranchinvoice(this);

		return paymentcollectionapportion;
	}

	public Paymentcollectionapportion removePaymentcollectionapportion(Paymentcollectionapportion paymentcollectionapportion) {
		getPaymentcollectionapportions().remove(paymentcollectionapportion);
		paymentcollectionapportion.setBranchinvoice(null);

		return paymentcollectionapportion;
	}

	public String getAccountstatus() {
		return accountstatus;
	}

	public void setAccountstatus(String accountstatus) {
		this.accountstatus = accountstatus;
	}

	
	
}