package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the customerinvoicebreakdown database table.
 * 
 */
@Entity
@Table(name="customerinvoicebreakdown")
public class Customerinvoicebreakdown implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int customerInvoiceBreakdownId;

	@Column(length=15)
	private String discount;

	@Column(precision=10, scale=2)
	private BigDecimal discountAmount;

	@Column(precision=10, scale=2)
	private BigDecimal purchasePrice;

	private int quantity;

	@Column(length=45)
	private String salesOrderNo;

	@Column(precision=10, scale=2)
	private BigDecimal subTotal;

	@Column(precision=10, scale=2)
	private BigDecimal unitPrice;

	//bi-directional many-to-one association to Customerinvoice
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CustomerInvoiceId")
	private Customerinvoice customerinvoice;

	//bi-directional many-to-one association to Product
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ProductId")
	private Product product;

	public Customerinvoicebreakdown() {
	}

	public int getCustomerInvoiceBreakdownId() {
		return this.customerInvoiceBreakdownId;
	}

	public void setCustomerInvoiceBreakdownId(int customerInvoiceBreakdownId) {
		this.customerInvoiceBreakdownId = customerInvoiceBreakdownId;
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

	public BigDecimal getPurchasePrice() {
		return this.purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
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

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Customerinvoice getCustomerinvoice() {
		return this.customerinvoice;
	}

	public void setCustomerinvoice(Customerinvoice customerinvoice) {
		this.customerinvoice = customerinvoice;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}