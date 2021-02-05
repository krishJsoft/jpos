package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the deliveryorderbreakdown database table.
 * 
 */
@Entity
@Table(name="deliveryorderbreakdown")
public class Deliveryorderbreakdown implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int deliveryOrderBreakdownId;

	@Column(precision=10, scale=2)
	private BigDecimal balanceQuantity;

	@Temporal(TemporalType.DATE)
	private Date createdDate;

	@Column(length=45)
	private String discount;

	@Column(precision=10, scale=2)
	private BigDecimal discountAmount;

	@Temporal(TemporalType.TIMESTAMP)
	private Date expDate;

	@Column(precision=10, scale=2)
	private BigDecimal quantity;

	@Column(precision=10, scale=2)
	private BigDecimal quantityAfter;

	@Column(precision=10, scale=2)
	private BigDecimal quantityBefore;

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

	//bi-directional many-to-one association to Deliveryorder
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="DeliveryOrderId")
	private Deliveryorder deliveryorder;

	//bi-directional many-to-one association to Product
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ProductId")
	private Product product;

	//bi-directional many-to-one association to Purchaseorderbreakdown
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="purchaseOrderBreakdownId")
	private Purchaseorderbreakdown purchaseorderbreakdown;

	//bi-directional many-to-one association to Productlot
	@OneToMany(mappedBy="deliveryorderbreakdown")
	private List<Productlot> productlots;

	//bi-directional many-to-one association to Salesorderbreakdown
	@OneToMany(mappedBy="deliveryorderbreakdown")
	private List<Salesorderbreakdown> salesorderbreakdowns;

	public Deliveryorderbreakdown() {
	}

	public int getDeliveryOrderBreakdownId() {
		return this.deliveryOrderBreakdownId;
	}

	public void setDeliveryOrderBreakdownId(int deliveryOrderBreakdownId) {
		this.deliveryOrderBreakdownId = deliveryOrderBreakdownId;
	}

	public BigDecimal getBalanceQuantity() {
		return this.balanceQuantity;
	}

	public void setBalanceQuantity(BigDecimal balanceQuantity) {
		this.balanceQuantity = balanceQuantity;
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

	public Date getExpDate() {
		return this.expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}	

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getQuantityAfter() {
		return quantityAfter;
	}

	public void setQuantityAfter(BigDecimal quantityAfter) {
		this.quantityAfter = quantityAfter;
	}

	public BigDecimal getQuantityBefore() {
		return quantityBefore;
	}

	public void setQuantityBefore(BigDecimal quantityBefore) {
		this.quantityBefore = quantityBefore;
	}

	public BigDecimal getSalesbalanceQuantity() {
		return salesbalanceQuantity;
	}

	public void setSalesbalanceQuantity(BigDecimal salesbalanceQuantity) {
		this.salesbalanceQuantity = salesbalanceQuantity;
	}

	public BigDecimal getSoldQuantity() {
		return soldQuantity;
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

	public Deliveryorder getDeliveryorder() {
		return this.deliveryorder;
	}

	public void setDeliveryorder(Deliveryorder deliveryorder) {
		this.deliveryorder = deliveryorder;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Purchaseorderbreakdown getPurchaseorderbreakdown() {
		return this.purchaseorderbreakdown;
	}

	public void setPurchaseorderbreakdown(Purchaseorderbreakdown purchaseorderbreakdown) {
		this.purchaseorderbreakdown = purchaseorderbreakdown;
	}

	public List<Productlot> getProductlots() {
		return this.productlots;
	}

	public void setProductlots(List<Productlot> productlots) {
		this.productlots = productlots;
	}

	public Productlot addProductlot(Productlot productlot) {
		getProductlots().add(productlot);
		productlot.setDeliveryorderbreakdown(this);

		return productlot;
	}

	public Productlot removeProductlot(Productlot productlot) {
		getProductlots().remove(productlot);
		productlot.setDeliveryorderbreakdown(null);

		return productlot;
	}

	public List<Salesorderbreakdown> getSalesorderbreakdowns() {
		return this.salesorderbreakdowns;
	}

	public void setSalesorderbreakdowns(List<Salesorderbreakdown> salesorderbreakdowns) {
		this.salesorderbreakdowns = salesorderbreakdowns;
	}

	public Salesorderbreakdown addSalesorderbreakdown(Salesorderbreakdown salesorderbreakdown) {
		getSalesorderbreakdowns().add(salesorderbreakdown);
		salesorderbreakdown.setDeliveryorderbreakdown(this);

		return salesorderbreakdown;
	}

	public Salesorderbreakdown removeSalesorderbreakdown(Salesorderbreakdown salesorderbreakdown) {
		getSalesorderbreakdowns().remove(salesorderbreakdown);
		salesorderbreakdown.setDeliveryorderbreakdown(null);

		return salesorderbreakdown;
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