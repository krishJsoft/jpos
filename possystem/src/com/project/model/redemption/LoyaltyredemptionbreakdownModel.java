package com.project.model.redemption;

import java.math.BigDecimal;

import com.project.model.datamodel.ProductModel;

public class LoyaltyredemptionbreakdownModel {

	private int redembreakdownId;

	private BigDecimal quantity;

	private BigDecimal redemAmount;

	private int redemPoint;

	private String redemType;

	private BigDecimal subtotalAmount;

	private int subtotalPoint;

	ProductModel product;

	private BigDecimal discountAmount;

	int sno;

	public int getRedembreakdownId() {
		return redembreakdownId;
	}

	public void setRedembreakdownId(int redembreakdownId) {
		this.redembreakdownId = redembreakdownId;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getRedemAmount() {
		return redemAmount;
	}

	public void setRedemAmount(BigDecimal redemAmount) {
		this.redemAmount = redemAmount;
	}

	public int getRedemPoint() {
		return redemPoint;
	}

	public void setRedemPoint(int redemPoint) {
		this.redemPoint = redemPoint;
	}

	public String getRedemType() {
		return redemType;
	}

	public void setRedemType(String redemType) {
		this.redemType = redemType;
	}

	public BigDecimal getSubtotalAmount() {
		return subtotalAmount;
	}

	public void setSubtotalAmount(BigDecimal subtotalAmount) {
		this.subtotalAmount = subtotalAmount;
	}

	public int getSubtotalPoint() {
		return subtotalPoint;
	}

	public void setSubtotalPoint(int subtotalPoint) {
		this.subtotalPoint = subtotalPoint;
	}

	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

}
