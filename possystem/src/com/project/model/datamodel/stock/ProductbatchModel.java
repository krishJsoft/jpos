package com.project.model.datamodel.stock;

import java.util.Date;

import com.project.model.datamodel.purchase.PurchaserequestbreakdownModel;

public class ProductbatchModel {

	private int batchId;

	private String batchNo;

	private String deliveryOrderNo;

	private Date expairyDate;

	private int quantity;
	
	private int avilableQuantity;

	private String status;

	private String productName;

	private String productDesc;

	private String productCode;

	private String uom;

	private int productId;
	
	private Integer branchId;

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getDeliveryOrderNo() {
		return deliveryOrderNo;
	}

	public void setDeliveryOrderNo(String deliveryOrderNo) {
		this.deliveryOrderNo = deliveryOrderNo;
	}

	public Date getExpairyDate() {
		return expairyDate;
	}

	public void setExpairyDate(Date expairyDate) {
		this.expairyDate = expairyDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public int getAvilableQuantity() {
		return avilableQuantity;
	}

	public void setAvilableQuantity(int avilableQuantity) {
		this.avilableQuantity = avilableQuantity;
	}
	
	

}
