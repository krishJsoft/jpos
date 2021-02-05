package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the customerpayment database table.
 * 
 */
@Entity
@Table(name="customerpayment")
public class Customerpayment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int customerPaymentId;

	@Column(length=45)
	private String approvedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date approvedDate;

	@Column(length=45)
	private String bankName;

	@Column(length=45)
	private String chequeNo;

	@Column(precision=10, scale=2)
	private BigDecimal collectionAmount;

	@Temporal(TemporalType.TIMESTAMP)
	private Date collectionDate;

	@Lob
	private String comments;

	@Column(length=45)
	private String invoiceNo;

	@Column(length=45)
	private String paymentMode;

	@Column(length=45)
	private String processedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date processedDate;

	@Column(length=45)
	private String receiptNo;

	@Column(length=45)
	private String referenceNumber;

	@Column(length=1)
	private String status;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchRecordId")
	private Branch branch;

	//bi-directional many-to-one association to Customerpaymentbreakdown
	@OneToMany(mappedBy="customerpayment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Customerpaymentbreakdown> customerpaymentbreakdowns;

	public Customerpayment() {
	}

	public int getCustomerPaymentId() {
		return this.customerPaymentId;
	}

	public void setCustomerPaymentId(int customerPaymentId) {
		this.customerPaymentId = customerPaymentId;
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

	public String getChequeNo() {
		return this.chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}

	public BigDecimal getCollectionAmount() {
		return this.collectionAmount;
	}

	public void setCollectionAmount(BigDecimal collectionAmount) {
		this.collectionAmount = collectionAmount;
	}

	public Date getCollectionDate() {
		return this.collectionDate;
	}

	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getPaymentMode() {
		return this.paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
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

	public String getReceiptNo() {
		return this.receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public String getReferenceNumber() {
		return this.referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public List<Customerpaymentbreakdown> getCustomerpaymentbreakdowns() {
		return this.customerpaymentbreakdowns;
	}

	public void setCustomerpaymentbreakdowns(List<Customerpaymentbreakdown> customerpaymentbreakdowns) {
		this.customerpaymentbreakdowns = customerpaymentbreakdowns;
	}

	public Customerpaymentbreakdown addCustomerpaymentbreakdown(Customerpaymentbreakdown customerpaymentbreakdown) {
		getCustomerpaymentbreakdowns().add(customerpaymentbreakdown);
		customerpaymentbreakdown.setCustomerpayment(this);

		return customerpaymentbreakdown;
	}

	public Customerpaymentbreakdown removeCustomerpaymentbreakdown(Customerpaymentbreakdown customerpaymentbreakdown) {
		getCustomerpaymentbreakdowns().remove(customerpaymentbreakdown);
		customerpaymentbreakdown.setCustomerpayment(null);

		return customerpaymentbreakdown;
	}

}