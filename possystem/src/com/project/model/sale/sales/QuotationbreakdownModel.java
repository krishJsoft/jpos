package com.project.model.sale.sales;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.project.model.datamodel.ProductModel;

public class QuotationbreakdownModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5206535360145945721L;

	private int quotationBreakdownId;
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
	private ProductModel product;
	private BigDecimal balanceQuantity;
	private BigDecimal soldQuantity;
	private BigDecimal salesbalanceQuantity;
	String id = "";
	private int sno;
	private BigDecimal purchasePrice;
	private BigDecimal taxCode;
	private BigDecimal taxAmount;
	private BigDecimal gstCode;
	private BigDecimal gstAmount;

	List<SupplierQuotationPriceModel> supplierPriceList = new ArrayList<SupplierQuotationPriceModel>();

	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getUomName() {
		return uomName;
	}

	public void setUomName(String uomName) {
		this.uomName = uomName;
	}

	public BigDecimal getQuantityRequired() {
		return quantityRequired;
	}

	public void setQuantityRequired(BigDecimal quantityRequired) {
		this.quantityRequired = quantityRequired;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	private int quotationId;

	public int getQuotationBreakdownId() {
		return quotationBreakdownId;
	}

	public void setQuotationBreakdownId(int quotationBreakdownId) {
		this.quotationBreakdownId = quotationBreakdownId;
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

	public int getQuotationId() {
		return quotationId;
	}

	public void setQuotationId(int quotationId) {
		this.quotationId = quotationId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getSalesbalanceQuantity() {
		return salesbalanceQuantity;
	}

	public void setSalesbalanceQuantity(BigDecimal salesbalanceQuantity) {
		this.salesbalanceQuantity = salesbalanceQuantity;
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

	public List<SupplierQuotationPriceModel> getSupplierPriceList() {
		return supplierPriceList;
	}

	public void setSupplierPriceList(
			List<SupplierQuotationPriceModel> supplierPriceList) {
		this.supplierPriceList = supplierPriceList;
	}

}
