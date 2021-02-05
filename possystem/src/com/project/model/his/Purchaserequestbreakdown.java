package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the purchaserequestbreakdown database table.
 * 
 */
@Entity
@Table(name="purchaserequestbreakdown")
public class Purchaserequestbreakdown implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int purchaseRequestBreakdownId;

	@Column(length=15)
	private String discount;

	@Column(precision=10, scale=2)
	private BigDecimal discountAmount;

	@Column(nullable=false)
	private int quantityRequired;

	@Column(length=45)
	private String referenceNo;

	@Column(length=1)
	private String status;

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
	
	//bi-directional many-to-one association to Product
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ProductId")
	private Product product;

	//bi-directional many-to-one association to Purchaserequest
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PurchaseRequestId")
	private Purchaserequest purchaserequest;

	//bi-directional many-to-one association to Supplier
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SupplierId")
	private Supplier supplier;

	public Purchaserequestbreakdown() {
	}

	public int getPurchaseRequestBreakdownId() {
		return this.purchaseRequestBreakdownId;
	}

	public void setPurchaseRequestBreakdownId(int purchaseRequestBreakdownId) {
		this.purchaseRequestBreakdownId = purchaseRequestBreakdownId;
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

	public int getQuantityRequired() {
		return this.quantityRequired;
	}

	public void setQuantityRequired(int quantityRequired) {
		this.quantityRequired = quantityRequired;
	}

	public String getReferenceNo() {
		return this.referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Purchaserequest getPurchaserequest() {
		return this.purchaserequest;
	}

	public void setPurchaserequest(Purchaserequest purchaserequest) {
		this.purchaserequest = purchaserequest;
	}

	public Supplier getSupplier() {
		return this.supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
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