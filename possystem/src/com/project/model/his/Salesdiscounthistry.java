package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the salesdiscounthistry database table.
 * 
 */
@Entity
@Table(name="salesdiscounthistry")
public class Salesdiscounthistry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int salesDiscountId;

	@Column(precision=10, scale=2)
	private BigDecimal discount;

	@Column(length=10)
	private String discountAmount;

	@Temporal(TemporalType.DATE)
	private Date fromdate;

	@Temporal(TemporalType.DATE)
	private Date lastupdateDate;

	@Column(length=50)
	private String lastupdateUser;

	@Temporal(TemporalType.DATE)
	private Date todate;

	//bi-directional many-to-one association to Product
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="productId")
	private Product product;

	public Salesdiscounthistry() {
	}

	public int getSalesDiscountId() {
		return this.salesDiscountId;
	}

	public void setSalesDiscountId(int salesDiscountId) {
		this.salesDiscountId = salesDiscountId;
	}

	public BigDecimal getDiscount() {
		return this.discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public String getDiscountAmount() {
		return this.discountAmount;
	}

	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Date getFromdate() {
		return this.fromdate;
	}

	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}

	public Date getLastupdateDate() {
		return this.lastupdateDate;
	}

	public void setLastupdateDate(Date lastupdateDate) {
		this.lastupdateDate = lastupdateDate;
	}

	public String getLastupdateUser() {
		return this.lastupdateUser;
	}

	public void setLastupdateUser(String lastupdateUser) {
		this.lastupdateUser = lastupdateUser;
	}

	public Date getTodate() {
		return this.todate;
	}

	public void setTodate(Date todate) {
		this.todate = todate;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}