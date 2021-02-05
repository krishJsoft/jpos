package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the supplierpayment database table.
 * 
 */
@Entity
@Table(name="supplierpayment")
public class Supplierpayment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int supplierPaymentId;

	@Column(length=45)
	private String approvedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date approvedDate;

	@Column(length=45)
	private String chequeNo;

	@Column(length=45)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(length=45)
	private String deliveryOrderNo;

	@Column(length=45)
	private String invoiceDate;

	@Column(length=45)
	private String invoiceNo;

	@Column(length=45)
	private String paymentDate;

	@Column(length=45)
	private String supplierAccountNo;

	@Column(length=45)
	private String supplierAddress;

	@Column(length=45)
	private String supplierBankName;

	@Column(length=45)
	private String supplierName;

	@Column(length=45)
	private String supplierReceiptNo;

	@Column(precision=10, scale=2)
	private BigDecimal totalAmountPaid;

	@Column(precision=10, scale=2)
	private BigDecimal totalInvoiceAmount;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchRecordId")
	private Branch branch;

	public Supplierpayment() {
	}

	public int getSupplierPaymentId() {
		return this.supplierPaymentId;
	}

	public void setSupplierPaymentId(int supplierPaymentId) {
		this.supplierPaymentId = supplierPaymentId;
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

	public String getChequeNo() {
		return this.chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
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

	public String getDeliveryOrderNo() {
		return this.deliveryOrderNo;
	}

	public void setDeliveryOrderNo(String deliveryOrderNo) {
		this.deliveryOrderNo = deliveryOrderNo;
	}

	public String getInvoiceDate() {
		return this.invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getPaymentDate() {
		return this.paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getSupplierAccountNo() {
		return this.supplierAccountNo;
	}

	public void setSupplierAccountNo(String supplierAccountNo) {
		this.supplierAccountNo = supplierAccountNo;
	}

	public String getSupplierAddress() {
		return this.supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	public String getSupplierBankName() {
		return this.supplierBankName;
	}

	public void setSupplierBankName(String supplierBankName) {
		this.supplierBankName = supplierBankName;
	}

	public String getSupplierName() {
		return this.supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierReceiptNo() {
		return this.supplierReceiptNo;
	}

	public void setSupplierReceiptNo(String supplierReceiptNo) {
		this.supplierReceiptNo = supplierReceiptNo;
	}

	public BigDecimal getTotalAmountPaid() {
		return this.totalAmountPaid;
	}

	public void setTotalAmountPaid(BigDecimal totalAmountPaid) {
		this.totalAmountPaid = totalAmountPaid;
	}

	public BigDecimal getTotalInvoiceAmount() {
		return this.totalInvoiceAmount;
	}

	public void setTotalInvoiceAmount(BigDecimal totalInvoiceAmount) {
		this.totalInvoiceAmount = totalInvoiceAmount;
	}

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

}