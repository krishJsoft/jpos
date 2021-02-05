package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the customerinvoice database table.
 * 
 */
@Entity
@Table(name="customerinvoice")
public class Customerinvoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int customerInvoiceId;

	@Column(length=45)
	private String approvedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date approvedDate;

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

	@Column(precision=10, scale=2)
	private BigDecimal paidAmount;

	@Column(precision=10, scale=2)
	private BigDecimal pendingAmount;

	@Column(length=400)
	private String remarks;

	@Temporal(TemporalType.TIMESTAMP)
	private Date salesOrderDate;

	@Column(length=45)
	private String salesOrderNo;

	@Column(length=1)
	private String status;

	@Column(precision=10, scale=2)
	private BigDecimal tax;

	@Column(precision=10, scale=2)
	private BigDecimal totalAmount;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchRecordId")
	private Branch branch;

	//bi-directional many-to-one association to Customer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CustomerId")
	private Customer customer;

	//bi-directional many-to-one association to Customerinvoicebreakdown
	@OneToMany(mappedBy="customerinvoice", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Customerinvoicebreakdown> customerinvoicebreakdowns;

	public Customerinvoice() {
	}

	public int getCustomerInvoiceId() {
		return this.customerInvoiceId;
	}

	public void setCustomerInvoiceId(int customerInvoiceId) {
		this.customerInvoiceId = customerInvoiceId;
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

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getSalesOrderDate() {
		return this.salesOrderDate;
	}

	public void setSalesOrderDate(Date salesOrderDate) {
		this.salesOrderDate = salesOrderDate;
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

	public List<Customerinvoicebreakdown> getCustomerinvoicebreakdowns() {
		return this.customerinvoicebreakdowns;
	}

	public void setCustomerinvoicebreakdowns(List<Customerinvoicebreakdown> customerinvoicebreakdowns) {
		this.customerinvoicebreakdowns = customerinvoicebreakdowns;
	}

	public Customerinvoicebreakdown addCustomerinvoicebreakdown(Customerinvoicebreakdown customerinvoicebreakdown) {
		getCustomerinvoicebreakdowns().add(customerinvoicebreakdown);
		customerinvoicebreakdown.setCustomerinvoice(this);

		return customerinvoicebreakdown;
	}

	public Customerinvoicebreakdown removeCustomerinvoicebreakdown(Customerinvoicebreakdown customerinvoicebreakdown) {
		getCustomerinvoicebreakdowns().remove(customerinvoicebreakdown);
		customerinvoicebreakdown.setCustomerinvoice(null);

		return customerinvoicebreakdown;
	}

}