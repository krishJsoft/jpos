package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the loyaltyredemptionbreakdown database table.
 * 
 */
@Entity
@Table(name="loyaltyredemptionbreakdown")
public class Loyaltyredemptionbreakdown implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int redembreakdownId;

	private BigDecimal quantity;

	@Column(precision=10, scale=2)
	private BigDecimal redemAmount;

	private int redemPoint;

	@Column(length=1)
	private String redemType;

	@Column(precision=10, scale=2)
	private BigDecimal subtotalAmount;

	private int subtotalPoint;

	//bi-directional many-to-one association to Product
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="productId")
	private Product product;

	//bi-directional many-to-one association to Loyaltyredemption
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="redemptionId")
	private Loyaltyredemption loyaltyredemption;

	public Loyaltyredemptionbreakdown() {
	}

	public int getRedembreakdownId() {
		return this.redembreakdownId;
	}

	public void setRedembreakdownId(int redembreakdownId) {
		this.redembreakdownId = redembreakdownId;
	}

	public BigDecimal getQuantity() {
		return this.quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getRedemAmount() {
		return this.redemAmount;
	}

	public void setRedemAmount(BigDecimal redemAmount) {
		this.redemAmount = redemAmount;
	}

	public int getRedemPoint() {
		return this.redemPoint;
	}

	public void setRedemPoint(int redemPoint) {
		this.redemPoint = redemPoint;
	}

	public String getRedemType() {
		return this.redemType;
	}

	public void setRedemType(String redemType) {
		this.redemType = redemType;
	}

	public BigDecimal getSubtotalAmount() {
		return this.subtotalAmount;
	}

	public void setSubtotalAmount(BigDecimal subtotalAmount) {
		this.subtotalAmount = subtotalAmount;
	}

	public int getSubtotalPoint() {
		return this.subtotalPoint;
	}

	public void setSubtotalPoint(int subtotalPoint) {
		this.subtotalPoint = subtotalPoint;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Loyaltyredemption getLoyaltyredemption() {
		return this.loyaltyredemption;
	}

	public void setLoyaltyredemption(Loyaltyredemption loyaltyredemption) {
		this.loyaltyredemption = loyaltyredemption;
	}

}