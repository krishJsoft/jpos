package com.project.model.sale.sales;

import java.io.Serializable;
import java.math.BigDecimal;

public class SupplierQuotationPriceModel implements Serializable {

	private String supplierName;
	private Integer supplierId;
	private BigDecimal unitPrice;

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

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

}
