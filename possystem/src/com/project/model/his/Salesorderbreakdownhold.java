package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the salesorderbreakdown database table.
 * 
 */
@Entity
@Table(name="salesorderbreakdownhold")
public class Salesorderbreakdownhold implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int salesOrderBreakdownId;

	@Column(length=25)
	private String batchNo;

	@Temporal(TemporalType.DATE)
	private Date createdDate;

	@Column(length=15)
	private String discount;

	@Column(precision=10, scale=2)
	private BigDecimal discountAmount;

	@Temporal(TemporalType.DATE)
	private Date expDateDate;

	@Column(precision=10, scale=2)
	private BigDecimal purchasePrice;
	
	@Column(precision=10, scale=2)
	private BigDecimal quantity;

	@Column(precision=10, scale=2)
	private BigDecimal quantityAfter;

	@Column(precision=10, scale=2)	
	private BigDecimal quantityBefore;
	
	@Column(precision=10, scale=2)
	private BigDecimal returnquantity;

	@Column(precision=10, scale=2)
	private BigDecimal salesCommission;

	@Column(length=15)
	private String status;

	@Column(precision=10, scale=2)
	private BigDecimal subTotal;

	@Column(nullable=false)
	private int toalItemSupplied;

	@Column(nullable=false)
	private int totalItemOrdered;

	@Column(nullable=false)
	private int totalItemPending;

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

	//bi-directional many-to-one association to Salesorder
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SalesOrderId")
	private Salesorderhold salesorder;

	//bi-directional many-to-one association to Deliveryorderbreakdown
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="DeliveryOrderBreakdownId")
	private Deliveryorderbreakdown deliveryorderbreakdown;

	//bi-directional many-to-one association to Quotationbreakdown
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="QuotationBreakdownId")
	private Quotationbreakdown quotationbreakdown;

	//bi-directional many-to-one association to Salesreturn
	@OneToMany(mappedBy="salesorderbreakdown")
	private List<Salesreturn> salesreturns;

	public Salesorderbreakdownhold() {
	}

	public int getSalesOrderBreakdownId() {
		return this.salesOrderBreakdownId;
	}

	public void setSalesOrderBreakdownId(int salesOrderBreakdownId) {
		this.salesOrderBreakdownId = salesOrderBreakdownId;
	}

	public String getBatchNo() {
		return this.batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

	public Date getExpDateDate() {
		return this.expDateDate;
	}

	public void setExpDateDate(Date expDateDate) {
		this.expDateDate = expDateDate;
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

	public BigDecimal getQuantityAfter() {
		return this.quantityAfter;
	}

	public void setQuantityAfter(BigDecimal quantityAfter) {
		this.quantityAfter = quantityAfter;
	}

	public BigDecimal getQuantityBefore() {
		return this.quantityBefore;
	}

	public void setQuantityBefore(BigDecimal quantityBefore) {
		this.quantityBefore = quantityBefore;
	}

	public BigDecimal getReturnquantity() {
		return this.returnquantity;
	}

	public void setReturnquantity(BigDecimal returnquantity) {
		this.returnquantity = returnquantity;
	}

	public BigDecimal getSalesCommission() {
		return this.salesCommission;
	}

	public void setSalesCommission(BigDecimal salesCommission) {
		this.salesCommission = salesCommission;
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

	public int getToalItemSupplied() {
		return this.toalItemSupplied;
	}

	public void setToalItemSupplied(int toalItemSupplied) {
		this.toalItemSupplied = toalItemSupplied;
	}

	public int getTotalItemOrdered() {
		return this.totalItemOrdered;
	}

	public void setTotalItemOrdered(int totalItemOrdered) {
		this.totalItemOrdered = totalItemOrdered;
	}

	public int getTotalItemPending() {
		return this.totalItemPending;
	}

	public void setTotalItemPending(int totalItemPending) {
		this.totalItemPending = totalItemPending;
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

	public Salesorderhold getSalesorder() {
		return this.salesorder;
	}

	public void setSalesorder(Salesorderhold salesorder) {
		this.salesorder = salesorder;
	}

	public Deliveryorderbreakdown getDeliveryorderbreakdown() {
		return this.deliveryorderbreakdown;
	}

	public void setDeliveryorderbreakdown(Deliveryorderbreakdown deliveryorderbreakdown) {
		this.deliveryorderbreakdown = deliveryorderbreakdown;
	}

	public Quotationbreakdown getQuotationbreakdown() {
		return this.quotationbreakdown;
	}

	public void setQuotationbreakdown(Quotationbreakdown quotationbreakdown) {
		this.quotationbreakdown = quotationbreakdown;
	}

	public List<Salesreturn> getSalesreturns() {
		return this.salesreturns;
	}

	public void setSalesreturns(List<Salesreturn> salesreturns) {
		this.salesreturns = salesreturns;
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