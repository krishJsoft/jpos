package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the quotation database table.
 * 
 */
@Entity
@Table(name = "quotation")
public class Quotation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private int quotationId;

	@Column(precision = 10, scale = 2)
	private BigDecimal balanceQuantityCount;

	@Column(length = 45)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(length = 145)
	private String deliveryTerms;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifedDate;

	@Column(length = 145)
	private String paymentTerms;

	@Temporal(TemporalType.TIMESTAMP)
	private Date quotationDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date quotationDueDate;

	@Column(length = 45)
	private String quotationNo;

	@Column(length = 45)
	private String remarks;

	@Column(precision = 10, scale = 2)
	private BigDecimal soldQuantityCount;

	@Column(length = 1)
	private String status;

	@Column(precision = 10, scale = 2)
	private BigDecimal totalAmount;

	@Column(precision = 10, scale = 2)
	private BigDecimal totalItemCount;

	@Column(precision = 10, scale = 2)
	private BigDecimal totalTax;

	@Column(length=145)
	private String deliveryTime;
	
	@Column(length=250)
	private String location;
	
	@Column(length=30)
	private String noofquest;
	
	
	@Column(length=100)
	private String customerName;
	
	@Column(length=300)
	private String deliveryAddress;
	
	@Column(length=400)
	private String addressLocationmap;
	
	@Column(length=30)
	private String ordertype;
	
	
	// bi-directional many-to-one association to Branch
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branchRecordId")
	private Branch branch;

	// bi-directional many-to-one association to Customer
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CustomerId")
	private Customer customer;

	// bi-directional many-to-one association to Quotationbreakdown
	@OneToMany(mappedBy = "quotation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Quotationbreakdown> quotationbreakdowns;

	public Quotation() {
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getNoofquest() {
		return noofquest;
	}

	public void setNoofquest(String noofquest) {
		this.noofquest = noofquest;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getAddressLocationmap() {
		return addressLocationmap;
	}

	public void setAddressLocationmap(String addressLocationmap) {
		this.addressLocationmap = addressLocationmap;
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

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Quotationbreakdown> getQuotationbreakdowns() {
		return this.quotationbreakdowns;
	}

	public void setQuotationbreakdowns(
			List<Quotationbreakdown> quotationbreakdowns) {
		this.quotationbreakdowns = quotationbreakdowns;
	}

	public Quotationbreakdown addQuotationbreakdown(
			Quotationbreakdown quotationbreakdown) {
		getQuotationbreakdowns().add(quotationbreakdown);
		quotationbreakdown.setQuotation(this);

		return quotationbreakdown;
	}

	public Quotationbreakdown removeQuotationbreakdown(
			Quotationbreakdown quotationbreakdown) {
		getQuotationbreakdowns().remove(quotationbreakdown);
		quotationbreakdown.setQuotation(null);

		return quotationbreakdown;
	}

	public BigDecimal getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(BigDecimal totalTax) {
		this.totalTax = totalTax;
	}

	public String getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}

}