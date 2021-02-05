package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the branchsalesbreakdown database table.
 * 
 */
@Entity
@Table(name = "branchsalesbreakdown")
public class Branchsalesbreakdown implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private int branchsalesBreakdownId;

	@Column(precision = 10, scale = 2)
	private BigDecimal balanceQuantity;

	@Column(length = 45)
	private String discount;
	
	@Column(length = 45)
	private String batchNo;
	
	

	@Column(precision = 10, scale = 2)
	private BigDecimal discountAmount;

	@Temporal(TemporalType.DATE)
	private Date expiryDate;

	@Column(precision = 10, scale = 2)
	private BigDecimal gstAmount;

	@Column(precision = 10, scale = 2)
	private BigDecimal gstCode;

	@Column(precision = 10, scale = 2)
	private BigDecimal quantity;

	@Column(precision = 10, scale = 2)	
	private BigDecimal salesbalanceQuantity;
	
	@Column(precision = 10, scale = 2)
	private BigDecimal soldQuantity;

	@Column(precision = 10, scale = 2)
	private BigDecimal subTotal;

	@Column(precision = 10, scale = 2)
	private BigDecimal taxAmount;

	@Column(precision = 10, scale = 2)
	private BigDecimal taxCode;

	@Column(precision = 10, scale = 2)
	private BigDecimal unitPrice;

	// bi-directional many-to-one association to Branchsale
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branchsalesId")
	private Branchsale branchsale;

	// bi-directional many-to-one association to Product
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ProductId")
	private Product product;

	public Branchsalesbreakdown() {
	}

	public int getBranchsalesBreakdownId() {
		return branchsalesBreakdownId;
	}

	public void setBranchsalesBreakdownId(int branchsalesBreakdownId) {
		this.branchsalesBreakdownId = branchsalesBreakdownId;
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

	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
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

	public Branchsale getBranchsale() {
		return this.branchsale;
	}

	public void setBranchsale(Branchsale branchsale) {
		this.branchsale = branchsale;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
	

}