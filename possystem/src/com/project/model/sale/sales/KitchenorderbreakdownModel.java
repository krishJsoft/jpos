package com.project.model.sale.sales;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.project.model.datamodel.ItemRemarksListModel;
import com.project.model.datamodel.SupplierModel;

public class KitchenorderbreakdownModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private int salesOrderBreakdownId;

	private String discount;

	private BigDecimal discountAmount;

	private BigDecimal quantity;

	private BigDecimal returnquantity;

	private BigDecimal quantityAfter;

	private Date expiryDate;

	private String batchNo;

	private BigDecimal quantityBefore;

	private String status;

	private BigDecimal subTotal;

	private int toalItemSupplied;

	private int totalItemOrdered;

	private int totalItemPending;

	private BigDecimal unitPrice;

	private BigDecimal purchasePrice;

	private int productId;

	private Date createdDate;

	private String productName;

	private String productCode;

	private String uom;

	private String id;

	private int deliveryOrderBreakdownId;

	private Date expDate;

	private String salesOrderNo;

	private BigDecimal balanceEditQuantity;

	private BigDecimal deliveryQuoationBalanceQuantity;

	private BigDecimal deliveryQuoationSoldQuantity;

	private int sno;
	
	private BigDecimal salesCommission;	
	
	private BigDecimal taxCode;
	private BigDecimal taxAmount;
	private BigDecimal gstCode;
	private BigDecimal gstAmount;

	SupplierModel supplier = new SupplierModel();

	ItemRemarksListModel remarks=new ItemRemarksListModel();	
	
	private int packing;
	
	private int setOption;
	
	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public int getSalesOrderBreakdownId() {
		return salesOrderBreakdownId;
	}

	public void setSalesOrderBreakdownId(int salesOrderBreakdownId) {
		this.salesOrderBreakdownId = salesOrderBreakdownId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public int getToalItemSupplied() {
		return toalItemSupplied;
	}

	public void setToalItemSupplied(int toalItemSupplied) {
		this.toalItemSupplied = toalItemSupplied;
	}

	public int getTotalItemOrdered() {
		return totalItemOrdered;
	}

	public void setTotalItemOrdered(int totalItemOrdered) {
		this.totalItemOrdered = totalItemOrdered;
	}

	public int getTotalItemPending() {
		return totalItemPending;
	}

	public void setTotalItemPending(int totalItemPending) {
		this.totalItemPending = totalItemPending;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public String getSalesOrderNo() {
		return salesOrderNo;
	}

	public void setSalesOrderNo(String salesOrderNo) {
		this.salesOrderNo = salesOrderNo;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getDeliveryOrderBreakdownId() {
		return deliveryOrderBreakdownId;
	}

	public void setDeliveryOrderBreakdownId(int deliveryOrderBreakdownId) {
		this.deliveryOrderBreakdownId = deliveryOrderBreakdownId;
	}

	public BigDecimal getBalanceEditQuantity() {
		return balanceEditQuantity;
	}

	public void setBalanceEditQuantity(BigDecimal balanceEditQuantity) {
		this.balanceEditQuantity = balanceEditQuantity;
	}

	public BigDecimal getDeliveryQuoationBalanceQuantity() {
		return deliveryQuoationBalanceQuantity;
	}

	public void setDeliveryQuoationBalanceQuantity(
			BigDecimal deliveryQuoationBalanceQuantity) {
		this.deliveryQuoationBalanceQuantity = deliveryQuoationBalanceQuantity;
	}

	public BigDecimal getDeliveryQuoationSoldQuantity() {
		return deliveryQuoationSoldQuantity;
	}

	public void setDeliveryQuoationSoldQuantity(BigDecimal deliveryQuoationSoldQuantity) {
		this.deliveryQuoationSoldQuantity = deliveryQuoationSoldQuantity;
	}

	public SupplierModel getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierModel supplier) {
		this.supplier = supplier;
	}

	public BigDecimal getReturnquantity() {
		return returnquantity;
	}

	public void setReturnquantity(BigDecimal returnquantity) {
		this.returnquantity = returnquantity;
	}

	public BigDecimal getSalesCommission() {
		return salesCommission;
	}

	public void setSalesCommission(BigDecimal salesCommission) {
		this.salesCommission = salesCommission;
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

	public ItemRemarksListModel getRemarks() {
		return remarks;
	}

	public void setRemarks(ItemRemarksListModel remarks) {
		this.remarks = remarks;
	}

	public int getPacking() {
		return packing;
	}

	public void setPacking(int packing) {
		this.packing = packing;
	}

	public int getSetOption() {
		return setOption;
	}

	public void setSetOption(int setOption) {
		this.setOption = setOption;
	}


	

}
