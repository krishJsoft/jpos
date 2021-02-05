package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the purchaseorderbreakdown database table.
 * 
 */
@Entity
@Table(name="purchaseorderbreakdown")
public class Purchaseorderbreakdown implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int purchaseOrderBreakdownId;

	private int balanceQuantity;

	@Column(length=15)
	private String discount;

	@Column(precision=10, scale=2)
	private BigDecimal discountAmount;

	@Column(length=45)
	private String purchaseRequestReferenceNo;

	@Column(nullable=false)
	private int quantity;

	private int receivedQuantity;

	@Column(precision=10, scale=2)
	private BigDecimal subTotal;

	@Column(precision=10, scale=2)
	private BigDecimal unitPrice;
	
	@Column(precision = 3, scale = 2)
	private BigDecimal taxCode;

	@Column(precision = 10, scale = 2)
	private BigDecimal taxAmount;

	@Column(precision = 3, scale = 2)
	private BigDecimal gstCode;

	@Column(precision = 10, scale = 2)
	private BigDecimal gstAmount;

	//bi-directional many-to-one association to Deliveryorderbreakdown
	@OneToMany(mappedBy="purchaseorderbreakdown")
	private List<Deliveryorderbreakdown> deliveryorderbreakdowns;

	//bi-directional many-to-one association to Product
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ProductId")
	private Product product;

	//bi-directional many-to-one association to Purchaseorder
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PurchaseOrderId")
	private Purchaseorder purchaseorder;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchId")
	private Branch branch;

	public Purchaseorderbreakdown() {
	}

	public int getPurchaseOrderBreakdownId() {
		return this.purchaseOrderBreakdownId;
	}

	public void setPurchaseOrderBreakdownId(int purchaseOrderBreakdownId) {
		this.purchaseOrderBreakdownId = purchaseOrderBreakdownId;
	}

	public int getBalanceQuantity() {
		return this.balanceQuantity;
	}

	public void setBalanceQuantity(int balanceQuantity) {
		this.balanceQuantity = balanceQuantity;
	}

	public String getDiscount() {
		return this.discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public BigDecimal getDiscountAmount() {
		return this.discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getPurchaseRequestReferenceNo() {
		return this.purchaseRequestReferenceNo;
	}

	public void setPurchaseRequestReferenceNo(String purchaseRequestReferenceNo) {
		this.purchaseRequestReferenceNo = purchaseRequestReferenceNo;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getReceivedQuantity() {
		return this.receivedQuantity;
	}

	public void setReceivedQuantity(int receivedQuantity) {
		this.receivedQuantity = receivedQuantity;
	}

	public BigDecimal getSubTotal() {
		return this.subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public List<Deliveryorderbreakdown> getDeliveryorderbreakdowns() {
		return this.deliveryorderbreakdowns;
	}

	public void setDeliveryorderbreakdowns(List<Deliveryorderbreakdown> deliveryorderbreakdowns) {
		this.deliveryorderbreakdowns = deliveryorderbreakdowns;
	}

	public Deliveryorderbreakdown addDeliveryorderbreakdown(Deliveryorderbreakdown deliveryorderbreakdown) {
		getDeliveryorderbreakdowns().add(deliveryorderbreakdown);
		deliveryorderbreakdown.setPurchaseorderbreakdown(this);

		return deliveryorderbreakdown;
	}

	public Deliveryorderbreakdown removeDeliveryorderbreakdown(Deliveryorderbreakdown deliveryorderbreakdown) {
		getDeliveryorderbreakdowns().remove(deliveryorderbreakdown);
		deliveryorderbreakdown.setPurchaseorderbreakdown(null);

		return deliveryorderbreakdown;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Purchaseorder getPurchaseorder() {
		return this.purchaseorder;
	}

	public void setPurchaseorder(Purchaseorder purchaseorder) {
		this.purchaseorder = purchaseorder;
	}

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public BigDecimal getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(BigDecimal taxCode) {
		this.taxCode = taxCode;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public BigDecimal getGstCode() {
		return gstCode;
	}

	public void setGstCode(BigDecimal gstCode) {
		this.gstCode = gstCode;
	}

	public BigDecimal getGstAmount() {
		return gstAmount;
	}

	public void setGstAmount(BigDecimal gstAmount) {
		this.gstAmount = gstAmount;
	}

	
	
}