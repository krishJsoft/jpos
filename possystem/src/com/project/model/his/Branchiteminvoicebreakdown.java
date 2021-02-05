package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the branchiteminvoicebreakdown database table.
 * 
 */
@Entity
@Table(name = "branchiteminvoicebreakdown")
public class Branchiteminvoicebreakdown implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private int branchInvoiceBreakdownId;

	@Column(length = 10)
	private String discount;

	@Column(precision = 10, scale = 2)
	private BigDecimal discountAmount;

	@Column(precision = 10, scale = 2)
	private BigDecimal gstAmount;

	@Column(precision = 10, scale = 2)
	private BigDecimal gstCode;

	@Column(precision = 10, scale = 2)
	private BigDecimal purchasePrice;

	@Column(precision = 10, scale = 2)
	private BigDecimal quantity;

	@Column(length = 45)
	private String salesOrderNo;

	@Column(precision = 10, scale = 2)
	private BigDecimal subTotal;

	@Column(precision = 10, scale = 2)
	private BigDecimal taxAmount;

	@Column(precision = 10, scale = 2)
	private BigDecimal taxCode;

	@Column(precision = 10, scale = 2)
	private BigDecimal unitPrice;

	// bi-directional many-to-one association to Branchiteminvoice
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BranchInvoiceId")
	private Branchiteminvoice branchinvoice;

	// bi-directional many-to-one association to Product
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ProductId")
	private Product product;

	public Branchiteminvoicebreakdown() {
	}

	public int getBranchInvoiceBreakdownId() {
		return this.branchInvoiceBreakdownId;
	}

	public void setBranchInvoiceBreakdownId(int branchInvoiceBreakdownId) {
		this.branchInvoiceBreakdownId = branchInvoiceBreakdownId;
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

	public BigDecimal getGstAmount() {
		return this.gstAmount;
	}

	public void setGstAmount(BigDecimal gstAmount) {
		this.gstAmount = gstAmount;
	}

	public BigDecimal getGstCode() {
		return this.gstCode;
	}

	public void setGstCode(BigDecimal gstCode) {
		this.gstCode = gstCode;
	}

	public BigDecimal getPurchasePrice() {
		return this.purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public BigDecimal getQuantity() {
		return this.quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getSalesOrderNo() {
		return this.salesOrderNo;
	}

	public void setSalesOrderNo(String salesOrderNo) {
		this.salesOrderNo = salesOrderNo;
	}

	public BigDecimal getSubTotal() {
		return this.subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getTaxAmount() {
		return this.taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public BigDecimal getTaxCode() {
		return this.taxCode;
	}

	public void setTaxCode(BigDecimal taxCode) {
		this.taxCode = taxCode;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Branchiteminvoice getBranchinvoice() {
		return branchinvoice;
	}

	public void setBranchinvoice(Branchiteminvoice branchinvoice) {
		this.branchinvoice = branchinvoice;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}