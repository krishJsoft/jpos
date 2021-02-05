package com.project.model.sale.sales.branchsale;

import java.math.BigDecimal;
import java.util.Date;

import com.project.model.datamodel.ProductModel;

public class BranchsalesbreakdownModel {

	
	private int branchsalesBreakdownId;
	private String discount;
	private BigDecimal discountAmount;
	private BigDecimal quantity;
	private BigDecimal subTotal;
	private BigDecimal unitPrice;
	private int productId;
	private String productCode;
	private BigDecimal quantityRequired;
	private String uomName;
	private String productName;
	private Date expiryDate;
	private String batchNo;
	private ProductModel product;
	private BigDecimal balanceQuantity;
	private BigDecimal soldQuantity;
	private int salesbalanceQuantity;
	String id = "";
	private int sno;
	private BigDecimal purchasePrice;
	private BigDecimal taxCode;
	private BigDecimal taxAmount;
	private BigDecimal gstCode;
	private BigDecimal gstAmount;
	public int getBranchsalesBreakdownId() {
		return branchsalesBreakdownId;
	}
	public void setBranchsalesBreakdownId(int branchsalesBreakdownId) {
		this.branchsalesBreakdownId = branchsalesBreakdownId;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public BigDecimal getQuantityRequired() {
		return quantityRequired;
	}
	public void setQuantityRequired(BigDecimal quantityRequired) {
		this.quantityRequired = quantityRequired;
	}
	public String getUomName() {
		return uomName;
	}
	public void setUomName(String uomName) {
		this.uomName = uomName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public ProductModel getProduct() {
		return product;
	}
	public void setProduct(ProductModel product) {
		this.product = product;
	}
	public BigDecimal getBalanceQuantity() {
		return balanceQuantity;
	}
	public void setBalanceQuantity(BigDecimal balanceQuantity) {
		this.balanceQuantity = balanceQuantity;
	}
	public BigDecimal getSoldQuantity() {
		return soldQuantity;
	}
	public void setSoldQuantity(BigDecimal soldQuantity) {
		this.soldQuantity = soldQuantity;
	}
	public int getSalesbalanceQuantity() {
		return salesbalanceQuantity;
	}
	public void setSalesbalanceQuantity(int salesbalanceQuantity) {
		this.salesbalanceQuantity = salesbalanceQuantity;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
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
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
	
	
}
