package com.project.model.sale.sales;

import java.util.Date;

import com.project.model.datamodel.ProductModel;

public class BranchtransferbreakdownModel {

	private int branchtransferBreakdownId;

	private int balanceQuantity;

	private String batchNo;

	private Date expiryDate;

	private int productId;
	private String productCode;
	private String uomName;
	private String productName;

	String id = "";
	private int sno;

	private int quantity;

	private int salesbalanceQuantity;

	private int soldQuantity;

	ProductModel product = new ProductModel();

	BranchtransferModel transfer = new BranchtransferModel();

	public int getBranchtransferBreakdownId() {
		return branchtransferBreakdownId;
	}

	public void setBranchtransferBreakdownId(int branchtransferBreakdownId) {
		this.branchtransferBreakdownId = branchtransferBreakdownId;
	}

	public int getBalanceQuantity() {
		return balanceQuantity;
	}

	public void setBalanceQuantity(int balanceQuantity) {
		this.balanceQuantity = balanceQuantity;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getSalesbalanceQuantity() {
		return salesbalanceQuantity;
	}

	public void setSalesbalanceQuantity(int salesbalanceQuantity) {
		this.salesbalanceQuantity = salesbalanceQuantity;
	}

	public int getSoldQuantity() {
		return soldQuantity;
	}

	public void setSoldQuantity(int soldQuantity) {
		this.soldQuantity = soldQuantity;
	}

	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}

	public BranchtransferModel getTransfer() {
		return transfer;
	}

	public void setTransfer(BranchtransferModel transfer) {
		this.transfer = transfer;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
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

}
