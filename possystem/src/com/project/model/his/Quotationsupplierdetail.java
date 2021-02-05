package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the quotationsupplierdetail database table.
 * 
 */
@Entity
@Table(name="quotationsupplierdetail")
public class Quotationsupplierdetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int quotationId;

	@Column(precision=10, scale=2)
	private BigDecimal balanceQuantityCount;

	@Column(length=45)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(length=145)
	private String deliveryTerms;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifedDate;

	@Column(length=145)
	private String paymentTerms;

	@Temporal(TemporalType.TIMESTAMP)
	private Date quotationDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date quotationDueDate;

	@Column(length=45)
	private String quotationNo;

	@Column(length=400)
	private String remarks;

	@Column(precision=10, scale=2)
	private BigDecimal soldQuantityCount;

	@Column(length=1)
	private String status;

	@Column(precision=10, scale=2)
	private BigDecimal totalAmount;

	@Column(precision=10, scale=2)
	private BigDecimal totalItemCount;

	@Column(precision=10, scale=2)
	private BigDecimal totalTax;

	//bi-directional many-to-one association to Quotationbreakdownsupplierdetail
	@OneToMany(mappedBy="quotationsupplierdetail", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Quotationbreakdownsupplierdetail> quotationbreakdownsupplierdetails;

	//bi-directional many-to-one association to Quotationsupplier
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="quotationsupplierId")
	private Quotationsupplier quotationsupplier;

	//bi-directional many-to-one association to Supplier
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="supplierId")
	private Supplier supplier;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchRecordId")
	private Branch branch;

	public Quotationsupplierdetail() {
	}

	public int getQuotationId() {
		return this.quotationId;
	}

	public void setQuotationId(int quotationId) {
		this.quotationId = quotationId;
	}

	public BigDecimal getBalanceQuantityCount() {
		return this.balanceQuantityCount;
	}

	public void setBalanceQuantityCount(BigDecimal balanceQuantityCount) {
		this.balanceQuantityCount = balanceQuantityCount;
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

	public String getDeliveryTerms() {
		return this.deliveryTerms;
	}

	public void setDeliveryTerms(String deliveryTerms) {
		this.deliveryTerms = deliveryTerms;
	}

	public Date getLastModifedDate() {
		return this.lastModifedDate;
	}

	public void setLastModifedDate(Date lastModifedDate) {
		this.lastModifedDate = lastModifedDate;
	}

	public String getPaymentTerms() {
		return this.paymentTerms;
	}

	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	public Date getQuotationDate() {
		return this.quotationDate;
	}

	public void setQuotationDate(Date quotationDate) {
		this.quotationDate = quotationDate;
	}

	public Date getQuotationDueDate() {
		return this.quotationDueDate;
	}

	public void setQuotationDueDate(Date quotationDueDate) {
		this.quotationDueDate = quotationDueDate;
	}

	public String getQuotationNo() {
		return this.quotationNo;
	}

	public void setQuotationNo(String quotationNo) {
		this.quotationNo = quotationNo;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public BigDecimal getSoldQuantityCount() {
		return this.soldQuantityCount;
	}

	public void setSoldQuantityCount(BigDecimal soldQuantityCount) {
		this.soldQuantityCount = soldQuantityCount;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getTotalItemCount() {
		return this.totalItemCount;
	}

	public void setTotalItemCount(BigDecimal totalItemCount) {
		this.totalItemCount = totalItemCount;
	}

	public BigDecimal getTotalTax() {
		return this.totalTax;
	}

	public void setTotalTax(BigDecimal totalTax) {
		this.totalTax = totalTax;
	}

	public List<Quotationbreakdownsupplierdetail> getQuotationbreakdownsupplierdetails() {
		return this.quotationbreakdownsupplierdetails;
	}

	public void setQuotationbreakdownsupplierdetails(List<Quotationbreakdownsupplierdetail> quotationbreakdownsupplierdetails) {
		this.quotationbreakdownsupplierdetails = quotationbreakdownsupplierdetails;
	}

	public Quotationbreakdownsupplierdetail addQuotationbreakdownsupplierdetail(Quotationbreakdownsupplierdetail quotationbreakdownsupplierdetail) {
		getQuotationbreakdownsupplierdetails().add(quotationbreakdownsupplierdetail);
		quotationbreakdownsupplierdetail.setQuotationsupplierdetail(this);

		return quotationbreakdownsupplierdetail;
	}

	public Quotationbreakdownsupplierdetail removeQuotationbreakdownsupplierdetail(Quotationbreakdownsupplierdetail quotationbreakdownsupplierdetail) {
		getQuotationbreakdownsupplierdetails().remove(quotationbreakdownsupplierdetail);
		quotationbreakdownsupplierdetail.setQuotationsupplierdetail(null);

		return quotationbreakdownsupplierdetail;
	}

	public Quotationsupplier getQuotationsupplier() {
		return this.quotationsupplier;
	}

	public void setQuotationsupplier(Quotationsupplier quotationsupplier) {
		this.quotationsupplier = quotationsupplier;
	}

	public Supplier getSupplier() {
		return this.supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

}