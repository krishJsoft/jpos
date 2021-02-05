package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the salesorder database table.
 * 
 */
@Entity
@Table(name = "salesorderhold")
public class Salesorderhold implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private int salesOrderId;

	@Column(precision = 10, scale = 2)
	private BigDecimal balanceAmount;

	@Column(precision = 10, scale = 2)
	private BigDecimal totalTax;

	@Column(length = 45)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(length = 45)
	private String customerName;

	@Column(length = 45)
	private String deliveryOrderNo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dueDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;

	@Column(length = 1)
	private String orderOnhold;

	@Column(length = 1)
	private String orderPayment;

	@Column(length = 1)
	private String orderReturned;

	@Column(length = 45)
	private String paymentType;

	@Column(length = 45)
	private String prescriptionNo;

	@Column(length = 45)
	private String pricingCurrency;

	@Column(length = 45)
	private String quoteNo;

	@Column(precision = 10, scale = 2)
	private BigDecimal receivedAmount;

	@Column(precision = 10, scale = 2)
	private BigDecimal changeAmount;

	@Column(length = 400)
	private String remarks;

	@Temporal(TemporalType.TIMESTAMP)
	private Date requestedShipDate;

	@Column(length = 45)
	private String salesDate;

	@Column(length = 45)
	private String salesOrderNo;

	@Column(length = 45)
	private String salesRep;

	@Column(length = 1)
	private String salesType;

	@Temporal(TemporalType.TIMESTAMP)
	private Date shippedDate;

	@Column(length = 15)
	private String status;

	@Column(length = 45)
	private String taxingScheme;

	@Column(precision = 10, scale = 2)
	private BigDecimal totalAmount;

	@Column(length = 1)
	private String branchtype;

	// bi-directional many-to-one association to Commissionbreakdown
	@OneToMany(mappedBy = "salesorder")
	private List<Commissionbreakdown> commissionbreakdowns;

	// removed to prevent exception when making sales without entering the payment ammount
	// bi-directional many-to-one association to Pospayment
	//@OneToMany(mappedBy = "salesorder", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//private List<Pospayment> pospayments;

	// bi-directional many-to-one association to Branch
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branchRecordId")
	private Branch branch1;

	// bi-directional many-to-one association to Branch
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BranchId")
	private Branch branch2;

	// bi-directional many-to-one association to Customer
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CustomerId")
	private Customer customer;

	// bi-directional many-to-one association to Customer
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "counterId")	
	private Poscounter poscounter;

	// bi-directional many-to-one association to Salesorderbreakdown
	@OneToMany(mappedBy = "salesorder", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Salesorderbreakdownhold> salesorderbreakdowns;

	public Salesorderhold() {
	}

	public int getSalesOrderId() {
		return this.salesOrderId;
	}

	public void setSalesOrderId(int salesOrderId) {
		this.salesOrderId = salesOrderId;
	}

	public BigDecimal getBalanceAmount() {
		return this.balanceAmount;
	}

	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
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

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDeliveryOrderNo() {
		return this.deliveryOrderNo;
	}

	public void setDeliveryOrderNo(String deliveryOrderNo) {
		this.deliveryOrderNo = deliveryOrderNo;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getOrderOnhold() {
		return this.orderOnhold;
	}

	public void setOrderOnhold(String orderOnhold) {
		this.orderOnhold = orderOnhold;
	}

	public String getOrderPayment() {
		return this.orderPayment;
	}

	public void setOrderPayment(String orderPayment) {
		this.orderPayment = orderPayment;
	}

	public String getOrderReturned() {
		return this.orderReturned;
	}

	public void setOrderReturned(String orderReturned) {
		this.orderReturned = orderReturned;
	}

	public String getPaymentType() {
		return this.paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPrescriptionNo() {
		return this.prescriptionNo;
	}

	public void setPrescriptionNo(String prescriptionNo) {
		this.prescriptionNo = prescriptionNo;
	}

	public String getPricingCurrency() {
		return this.pricingCurrency;
	}

	public void setPricingCurrency(String pricingCurrency) {
		this.pricingCurrency = pricingCurrency;
	}

	public String getQuoteNo() {
		return this.quoteNo;
	}

	public void setQuoteNo(String quoteNo) {
		this.quoteNo = quoteNo;
	}

	public BigDecimal getReceivedAmount() {
		return this.receivedAmount;
	}

	public void setReceivedAmount(BigDecimal receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getRequestedShipDate() {
		return this.requestedShipDate;
	}

	public void setRequestedShipDate(Date requestedShipDate) {
		this.requestedShipDate = requestedShipDate;
	}

	public String getSalesDate() {
		return this.salesDate;
	}

	public void setSalesDate(String salesDate) {
		this.salesDate = salesDate;
	}

	public String getSalesOrderNo() {
		return this.salesOrderNo;
	}

	public void setSalesOrderNo(String salesOrderNo) {
		this.salesOrderNo = salesOrderNo;
	}

	public String getSalesRep() {
		return this.salesRep;
	}

	public void setSalesRep(String salesRep) {
		this.salesRep = salesRep;
	}

	public String getSalesType() {
		return this.salesType;
	}

	public void setSalesType(String salesType) {
		this.salesType = salesType;
	}

	public Date getShippedDate() {
		return this.shippedDate;
	}

	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTaxingScheme() {
		return this.taxingScheme;
	}

	public void setTaxingScheme(String taxingScheme) {
		this.taxingScheme = taxingScheme;
	}

	public BigDecimal getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<Commissionbreakdown> getCommissionbreakdowns() {
		return this.commissionbreakdowns;
	}

	public void setCommissionbreakdowns(
			List<Commissionbreakdown> commissionbreakdowns) {
		this.commissionbreakdowns = commissionbreakdowns;
	}

//	public List<Pospayment> getPospayments() {
	//	return this.pospayments;
//	}

	//public void setPospayments(List<Pospayment> pospayments) {
	//	this.pospayments = pospayments;
	//}

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

	public List<Salesorderbreakdownhold> getSalesorderbreakdowns() {
		return this.salesorderbreakdowns;
	}

	public void setSalesorderbreakdowns(
			List<Salesorderbreakdownhold> salesorderbreakdowns) {
		this.salesorderbreakdowns = salesorderbreakdowns;
	}

	public String getBranchtype() {
		return branchtype;
	}

	public void setBranchtype(String branchtype) {
		this.branchtype = branchtype;
	}

	public BigDecimal getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(BigDecimal totalTax) {
		this.totalTax = totalTax;
	}

	public BigDecimal getChangeAmount() {
		return changeAmount;
	}

	public void setChangeAmount(BigDecimal changeAmount) {
		this.changeAmount = changeAmount;
	}

	public Poscounter getPoscounter() {
		return poscounter;
	}

	public void setPoscounter(Poscounter poscounter) {
		this.poscounter = poscounter;
	}

	
	
}