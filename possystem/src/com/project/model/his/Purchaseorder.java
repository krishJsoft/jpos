package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the purchaseorder database table.
 * 
 */
@Entity
@Table(name="purchaseorder")
public class Purchaseorder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int purchaseOrderId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date agreedDateDelivery;

	private int balanceQuantityCount;

	@Lob
	private String billingAddress;

	@Column(length=45)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Lob
	private String deliveryAddress;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;

	@Column(length=1)
	private String orderPayment;

	@Column(length=1)
	private String orderReceived;

	@Column(length=1)
	private String orderReturned;

	@Column(length=500)
	private String paymentTerms;

	@Temporal(TemporalType.TIMESTAMP)
	private Date purchaseOrderDate;

	@Column(length=45)
	private String purchaseOrderNo;

	@Column(length=45)
	private String purchaseRequestReferenceNo;

	private int receivedQuantityCount;

	@Column(length=245)
	private String remarks;

	@Column(length=15)
	private String status;

	@Column(length=245)
	private String supportingFileName;

	@Column(length=500)
	private String termsConditions;

	@Column(precision=10, scale=2)
	private BigDecimal totalAmount;
	
	@Column(precision=10, scale=2)
	private BigDecimal totalTax;

	private int totalItemCount;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchRecordId")
	private Branch branch;

	//bi-directional many-to-one association to Supplier
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SupplierId")
	private Supplier supplier;

	//bi-directional many-to-one association to Purchaseorderbreakdown
	@OneToMany(mappedBy="purchaseorder", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Purchaseorderbreakdown> purchaseorderbreakdowns;

	//bi-directional many-to-one association to Purchaseorderdeliveryaddress
	@OneToMany(mappedBy="purchaseorder", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Purchaseorderdeliveryaddress> purchaseorderdeliveryaddresses;

	public Purchaseorder() {
	}

	public int getPurchaseOrderId() {
		return this.purchaseOrderId;
	}

	public void setPurchaseOrderId(int purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public Date getAgreedDateDelivery() {
		return this.agreedDateDelivery;
	}

	public void setAgreedDateDelivery(Date agreedDateDelivery) {
		this.agreedDateDelivery = agreedDateDelivery;
	}

	public int getBalanceQuantityCount() {
		return this.balanceQuantityCount;
	}

	public void setBalanceQuantityCount(int balanceQuantityCount) {
		this.balanceQuantityCount = balanceQuantityCount;
	}

	public String getBillingAddress() {
		return this.billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
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

	public String getDeliveryAddress() {
		return this.deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getOrderPayment() {
		return this.orderPayment;
	}

	public void setOrderPayment(String orderPayment) {
		this.orderPayment = orderPayment;
	}

	public String getOrderReceived() {
		return this.orderReceived;
	}

	public void setOrderReceived(String orderReceived) {
		this.orderReceived = orderReceived;
	}

	public String getOrderReturned() {
		return this.orderReturned;
	}

	public void setOrderReturned(String orderReturned) {
		this.orderReturned = orderReturned;
	}

	public String getPaymentTerms() {
		return this.paymentTerms;
	}

	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
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

	public String getPurchaseRequestReferenceNo() {
		return this.purchaseRequestReferenceNo;
	}

	public void setPurchaseRequestReferenceNo(String purchaseRequestReferenceNo) {
		this.purchaseRequestReferenceNo = purchaseRequestReferenceNo;
	}

	public int getReceivedQuantityCount() {
		return this.receivedQuantityCount;
	}

	public void setReceivedQuantityCount(int receivedQuantityCount) {
		this.receivedQuantityCount = receivedQuantityCount;
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

	public String getSupportingFileName() {
		return this.supportingFileName;
	}

	public void setSupportingFileName(String supportingFileName) {
		this.supportingFileName = supportingFileName;
	}

	public String getTermsConditions() {
		return this.termsConditions;
	}

	public void setTermsConditions(String termsConditions) {
		this.termsConditions = termsConditions;
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

	public List<Purchaseorderbreakdown> getPurchaseorderbreakdowns() {
		return this.purchaseorderbreakdowns;
	}

	public void setPurchaseorderbreakdowns(List<Purchaseorderbreakdown> purchaseorderbreakdowns) {
		this.purchaseorderbreakdowns = purchaseorderbreakdowns;
	}

	public Purchaseorderbreakdown addPurchaseorderbreakdown(Purchaseorderbreakdown purchaseorderbreakdown) {
		getPurchaseorderbreakdowns().add(purchaseorderbreakdown);
		purchaseorderbreakdown.setPurchaseorder(this);

		return purchaseorderbreakdown;
	}

	public Purchaseorderbreakdown removePurchaseorderbreakdown(Purchaseorderbreakdown purchaseorderbreakdown) {
		getPurchaseorderbreakdowns().remove(purchaseorderbreakdown);
		purchaseorderbreakdown.setPurchaseorder(null);

		return purchaseorderbreakdown;
	}

	public List<Purchaseorderdeliveryaddress> getPurchaseorderdeliveryaddresses() {
		return this.purchaseorderdeliveryaddresses;
	}

	public void setPurchaseorderdeliveryaddresses(List<Purchaseorderdeliveryaddress> purchaseorderdeliveryaddresses) {
		this.purchaseorderdeliveryaddresses = purchaseorderdeliveryaddresses;
	}

	public Purchaseorderdeliveryaddress addPurchaseorderdeliveryaddress(Purchaseorderdeliveryaddress purchaseorderdeliveryaddress) {
		getPurchaseorderdeliveryaddresses().add(purchaseorderdeliveryaddress);
		purchaseorderdeliveryaddress.setPurchaseorder(this);

		return purchaseorderdeliveryaddress;
	}

	public Purchaseorderdeliveryaddress removePurchaseorderdeliveryaddress(Purchaseorderdeliveryaddress purchaseorderdeliveryaddress) {
		getPurchaseorderdeliveryaddresses().remove(purchaseorderdeliveryaddress);
		purchaseorderdeliveryaddress.setPurchaseorder(null);

		return purchaseorderdeliveryaddress;
	}

	public BigDecimal getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(BigDecimal totalTax) {
		this.totalTax = totalTax;
	}
	
	

}