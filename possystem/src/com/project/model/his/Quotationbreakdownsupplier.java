package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the quotationbreakdown database table.
 * 
 */
@Entity
@Table(name="quotationbreakdownsupplier")
public class Quotationbreakdownsupplier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int quotationBreakdownId;

	@Column(precision=10, scale=2)
	private BigDecimal balanceQuantity;

	@Column(length=45)
	private String discount;

	@Column(precision=10, scale=2)
	private BigDecimal discountAmount;

	@Column(precision=10, scale=2)
	private BigDecimal quantity;

	@Column(precision=10, scale=2)
	private BigDecimal salesbalanceQuantity;

	@Column(precision=10, scale=2)
	private BigDecimal soldQuantity;

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
	
	
	@Temporal(TemporalType.DATE)
	private Date expiryDate;

	//bi-directional many-to-one association to Product
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ProductId")
	private Product product;

	//bi-directional many-to-one association to Quotation
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="QuotationId")
	private Quotationsupplier quotation;


	public Quotationbreakdownsupplier() {
	}

	public int getQuotationBreakdownId() {
		return this.quotationBreakdownId;
	}

	public void setQuotationBreakdownId(int quotationBreakdownId) {
		this.quotationBreakdownId = quotationBreakdownId;
	}

	public BigDecimal getBalanceQuantity() {
		return this.balanceQuantity;
	}

	public void setBalanceQuantity(BigDecimal balanceQuantity) {
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

	public BigDecimal getQuantity() {
		return this.quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getSalesbalanceQuantity() {
		return this.salesbalanceQuantity;
	}

	public void setSalesbalanceQuantity(BigDecimal salesbalanceQuantity) {
		this.salesbalanceQuantity = salesbalanceQuantity;
	}

	public BigDecimal getSoldQuantity() {
		return this.soldQuantity;
	}

	public void setSoldQuantity(BigDecimal soldQuantity) {
		this.soldQuantity = soldQuantity;
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

	public Quotationsupplier getQuotation() {
		return this.quotation;
	}

	public void setQuotation(Quotationsupplier quotation) {
		this.quotation = quotation;
	}

	
	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
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