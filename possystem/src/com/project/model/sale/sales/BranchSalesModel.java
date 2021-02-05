package com.project.model.sale.sales;

import java.math.BigDecimal;

public class BranchSalesModel {

	private String productName;
	private Integer productId;
	private String productCode;
	private Integer totalQuantity;
	private BigDecimal normalPriceTotal;
	private BigDecimal purchasePriceTotal;
	private BigDecimal marginTotal;
	private BigDecimal margin;

	SalesTotalModel total = new SalesTotalModel();

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Integer getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Integer totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public BigDecimal getNormalPriceTotal() {
		return normalPriceTotal;
	}

	public void setNormalPriceTotal(BigDecimal normalPriceTotal) {
		this.normalPriceTotal = normalPriceTotal;
	}

	public BigDecimal getPurchasePriceTotal() {
		return purchasePriceTotal;
	}

	public void setPurchasePriceTotal(BigDecimal purchasePriceTotal) {
		this.purchasePriceTotal = purchasePriceTotal;
	}

	public BigDecimal getMarginTotal() {
		return marginTotal;
	}

	public void setMarginTotal(BigDecimal marginTotal) {
		this.marginTotal = marginTotal;
	}

	public BigDecimal getMargin() {
		return margin;
	}

	public void setMargin(BigDecimal margin) {
		this.margin = margin;
	}

	public SalesTotalModel getTotal() {
		return total;
	}

	public void setTotal(SalesTotalModel total) {
		this.total = total;
	}

}
