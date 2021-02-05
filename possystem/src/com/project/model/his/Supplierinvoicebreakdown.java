package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;

/**
 * The persistent class for the supplierinvoicebreakdown database table.
 * 
 */
@Entity
@Table(name = "supplierinvoicebreakdown")
public class Supplierinvoicebreakdown implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private int supplierInvoiceBreakdownId;

	@Column(length = 45)
	private String deliveryOrderNo;

	@Column(length = 10)
	private String discount;

	@Column(precision = 10, scale = 2)
	private BigDecimal discountAmount;

	@Column(precision = 10, scale = 2)
	private BigDecimal quantity;

	@Column(precision = 10, scale = 2)
	private BigDecimal subTotal;

	@Column(precision = 10, scale = 2)
	private BigDecimal unitPrice;

	@Column(precision = 3, scale = 2)
	private BigDecimal taxCode;

	@Column(precision = 10, scale = 2)
	private BigDecimal taxAmount;

	@Column(precision = 3, scale = 2)
	private BigDecimal gstCode;

	@Column(precision = 10, scale = 2)
	private BigDecimal gstAmount;

	// bi-directional many-to-one association to Product
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ProductId")
	private Product product;

	// bi-directional many-to-one association to Supplierinvoice
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SupplierInvoiceId")
	private Supplierinvoice supplierinvoice;

	public Supplierinvoicebreakdown() {
	}

	public int getSupplierInvoiceBreakdownId() {
		return this.supplierInvoiceBreakdownId;
	}

	public void setSupplierInvoiceBreakdownId(int supplierInvoiceBreakdownId) {
		this.supplierInvoiceBreakdownId = supplierInvoiceBreakdownId;
	}

	public String getDeliveryOrderNo() {
		return this.deliveryOrderNo;
	}

	public void setDeliveryOrderNo(String deliveryOrderNo) {
		this.deliveryOrderNo = deliveryOrderNo;
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

	public BigDecimal getQuantity() {
		return this.quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
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

	public Supplierinvoice getSupplierinvoice() {
		return this.supplierinvoice;
	}

	public void setSupplierinvoice(Supplierinvoice supplierinvoice) {
		this.supplierinvoice = supplierinvoice;
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