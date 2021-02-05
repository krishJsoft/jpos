package com.project.model.datamodel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.project.model.sale.sales.QuotationbreakdownModel;

public class ProductsupplierModel {

	private int productsupplierId;
	private int productId;
	private int supplierId;
	private String supplierName;
	private String productName;
	private String productCode;
	private String supplierAddress;
	private SupplierModel supplier = new SupplierModel();
	List<QuotationbreakdownModel> quotationbreakdownList = new ArrayList<QuotationbreakdownModel>();
	
	private BigDecimal purchasePrice;
	
	public int getProductsupplierId() {
		return productsupplierId;
	}

	public void setProductsupplierId(int productsupplierId) {
		this.productsupplierId = productsupplierId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public SupplierModel getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierModel supplier) {
		this.supplier = supplier;
	}

	public List<QuotationbreakdownModel> getQuotationbreakdownList() {
		return quotationbreakdownList;
	}

	public void setQuotationbreakdownList(
			List<QuotationbreakdownModel> quotationbreakdownList) {
		this.quotationbreakdownList = quotationbreakdownList;
	}

	
	
}
