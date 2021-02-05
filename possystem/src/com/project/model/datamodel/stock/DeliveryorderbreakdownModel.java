package com.project.model.datamodel.stock;

import java.math.BigDecimal;
import java.util.Date;

public class DeliveryorderbreakdownModel {

	private int deliveryOrderBreakdownId;

	private String discount;

	private BigDecimal discountAmount;

	private String productName;

	private String productCode;

	private String uomName;

	private BigDecimal quantity;

	private BigDecimal quantityAfter;

	private BigDecimal quantityBefore;

	private BigDecimal subTotal;

	private BigDecimal unitPrice;
	
	private BigDecimal purchasePrice;

	private int productId;

	private String barcode;

	private BigDecimal balanceQuantity;

	private BigDecimal balanceEditQuantity;

	private BigDecimal soldQuantity;

	private BigDecimal salesbalanceQuantity;

	private BigDecimal receivedQuantity;

	private Date expDate;

	private Date createdDate;

	private String id;

	private int purchaseOrderBreakdownId;

	private int purchaseOrderBalanceQuantity;

	private int purchaseOrderReceivedQuantity;

	private String deliveryOrderNo;
	
	private String supplierName;	
	private Integer supplierId;
	
	private BigDecimal taxCode;
	private BigDecimal taxAmount;
	private BigDecimal gstCode;
	private BigDecimal gstAmount;
	

	public BigDecimal getSoldQuantity() {
		return soldQuantity;
	}

	public void setSoldQuantity(BigDecimal soldQuantity) {
		this.soldQuantity = soldQuantity;
	}

	public int getDeliveryOrderBreakdownId() {
		return deliveryOrderBreakdownId;
	}

	public void setDeliveryOrderBreakdownId(int deliveryOrderBreakdownId) {
		this.deliveryOrderBreakdownId = deliveryOrderBreakdownId;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public BigDecimal getBalanceQuantity() {
		return balanceQuantity;
	}

	public void setBalanceQuantity(BigDecimal balanceQuantity) {
		this.balanceQuantity = balanceQuantity;
	}

	public BigDecimal getReceivedQuantity() {
		return receivedQuantity;
	}

	public void setReceivedQuantity(BigDecimal receivedQuantity) {
		this.receivedQuantity = receivedQuantity;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public String getUomName() {
		return uomName;
	}

	public void setUomName(String uomName) {
		this.uomName = uomName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public int getPurchaseOrderBreakdownId() {
		return purchaseOrderBreakdownId;
	}

	public void setPurchaseOrderBreakdownId(int purchaseOrderBreakdownId) {
		this.purchaseOrderBreakdownId = purchaseOrderBreakdownId;
	}

	public int getPurchaseOrderBalanceQuantity() {
		return purchaseOrderBalanceQuantity;
	}

	public void setPurchaseOrderBalanceQuantity(int purchaseOrderBalanceQuantity) {
		this.purchaseOrderBalanceQuantity = purchaseOrderBalanceQuantity;
	}

	public int getPurchaseOrderReceivedQuantity() {
		return purchaseOrderReceivedQuantity;
	}

	public void setPurchaseOrderReceivedQuantity(
			int purchaseOrderReceivedQuantity) {
		this.purchaseOrderReceivedQuantity = purchaseOrderReceivedQuantity;
	}

	public BigDecimal getBalanceEditQuantity() {
		return balanceEditQuantity;
	}

	public void setBalanceEditQuantity(BigDecimal balanceEditQuantity) {
		this.balanceEditQuantity = balanceEditQuantity;
	}

	public String getDeliveryOrderNo() {
		return deliveryOrderNo;
	}

	public void setDeliveryOrderNo(String deliveryOrderNo) {
		this.deliveryOrderNo = deliveryOrderNo;
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

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	
	

}
