package com.project.model.sale.sales;

import java.math.BigDecimal;

public class SalesTotalModel {

	private Integer totalQuantity;
	private BigDecimal normalPriceTotal;
	private BigDecimal purchasePriceTotal;
	private BigDecimal marginTotal;
	private BigDecimal margin;

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

}
