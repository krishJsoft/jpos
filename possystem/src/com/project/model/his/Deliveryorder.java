package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the deliveryorder database table.
 * 
 */
@Entity
@Table(name = "deliveryorder")
public class Deliveryorder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private int deliveryOrderId;

	private int balanceQuantityCount;

	@Column(length = 45)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(length = 45)
	private String deliveryOrderNo;

	@Column(length = 45)
	private String invoiceNo;

	@Column(nullable = false, length = 1)
	private String invoicestatus;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date purchaseOrderDate;

	@Column(length = 45)
	private String purchaseOrderNo;

	@Column(length = 400)
	private String remarks;

	@Column(nullable = false, length = 1)
	private String salesstatus;

	private int soldQuantityCount;

	@Column(length = 1)
	private String status;

	@Column(length = 1)
	private String branchtype;

	@Column(length = 1)
	private String deliveryType;

	@Column(precision = 10, scale = 2)
	private BigDecimal totalAmount;

	private int totalItemCount;

	// bi-directional many-to-one association to Branch
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branchRecordId")
	private Branch branch1;

	// bi-directional many-to-one association to Branch
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BranchId")
	private Branch branch2;

	// bi-directional many-to-one association to Supplier
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SupplierId")
	private Supplier supplier;

	// bi-directional many-to-one association to Branch
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplyBranchId")
	private Branch branch3;

	// bi-directional many-to-one association to Deliveryorderbreakdown
	@OneToMany(mappedBy = "deliveryorder", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Deliveryorderbreakdown> deliveryorderbreakdowns;

	public Deliveryorder() {
	}

	public int getDeliveryOrderId() {
		return this.deliveryOrderId;
	}

	public void setDeliveryOrderId(int deliveryOrderId) {
		this.deliveryOrderId = deliveryOrderId;
	}

	public int getBalanceQuantityCount() {
		return this.balanceQuantityCount;
	}

	public void setBalanceQuantityCount(int balanceQuantityCount) {
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

	public String getDeliveryOrderNo() {
		return this.deliveryOrderNo;
	}

	public void setDeliveryOrderNo(String deliveryOrderNo) {
		this.deliveryOrderNo = deliveryOrderNo;
	}

	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getInvoicestatus() {
		return this.invoicestatus;
	}

	public void setInvoicestatus(String invoicestatus) {
		this.invoicestatus = invoicestatus;
	}

	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
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

	public String getSalesstatus() {
		return this.salesstatus;
	}

	public void setSalesstatus(String salesstatus) {
		this.salesstatus = salesstatus;
	}

	public int getSoldQuantityCount() {
		return this.soldQuantityCount;
	}

	public void setSoldQuantityCount(int soldQuantityCount) {
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

	public int getTotalItemCount() {
		return this.totalItemCount;
	}

	public void setTotalItemCount(int totalItemCount) {
		this.totalItemCount = totalItemCount;
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

	public Supplier getSupplier() {
		return this.supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public List<Deliveryorderbreakdown> getDeliveryorderbreakdowns() {
		return this.deliveryorderbreakdowns;
	}

	public void setDeliveryorderbreakdowns(
			List<Deliveryorderbreakdown> deliveryorderbreakdowns) {
		this.deliveryorderbreakdowns = deliveryorderbreakdowns;
	}

	public Deliveryorderbreakdown addDeliveryorderbreakdown(
			Deliveryorderbreakdown deliveryorderbreakdown) {
		getDeliveryorderbreakdowns().add(deliveryorderbreakdown);
		deliveryorderbreakdown.setDeliveryorder(this);

		return deliveryorderbreakdown;
	}

	public Deliveryorderbreakdown removeDeliveryorderbreakdown(
			Deliveryorderbreakdown deliveryorderbreakdown) {
		getDeliveryorderbreakdowns().remove(deliveryorderbreakdown);
		deliveryorderbreakdown.setDeliveryorder(null);

		return deliveryorderbreakdown;
	}

	public String getBranchtype() {
		return branchtype;
	}

	public void setBranchtype(String branchtype) {
		this.branchtype = branchtype;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public Branch getBranch3() {
		return branch3;
	}

	public void setBranch3(Branch branch3) {
		this.branch3 = branch3;
	}
	
	

}