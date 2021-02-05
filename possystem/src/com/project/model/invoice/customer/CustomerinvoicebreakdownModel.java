package com.project.model.invoice.customer;

import java.math.BigDecimal;

import com.project.model.datamodel.ProductModel;

public class CustomerinvoicebreakdownModel {

	private int customerInvoiceBreakdownId;
	private String discount;
	private BigDecimal discountAmount;
	private int quantity;
	private BigDecimal subTotal;
	private BigDecimal unitPrice;
	private String salesOrderNo;
	private int productId;
	private String productCode;
	private String productName;
	private String uom;
	private BigDecimal purchasePrice;

	ProductModel product;

	public int getCustomerInvoiceBreakdownId() {
		return customerInvoiceBreakdownId;
	}

	public void setCustomerInvoiceBreakdownId(int customerInvoiceBreakdownId) {
		this.customerInvoiceBreakdownId = customerInvoiceBreakdownId;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
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

	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}

	public String getSalesOrderNo() {
		return salesOrderNo;
	}

	public void setSalesOrderNo(String salesOrderNo) {
		this.salesOrderNo = salesOrderNo;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

}
