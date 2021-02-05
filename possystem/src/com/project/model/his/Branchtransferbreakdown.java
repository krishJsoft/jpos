package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the branchtransferbreakdown database table.
 * 
 */
@Entity
@Table(name = "branchtransferbreakdown")
public class Branchtransferbreakdown implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private int branchtransferBreakdownId;

	private int balanceQuantity;

	@Column(length = 45)
	private String batchNo;

	@Temporal(TemporalType.DATE)
	private Date expiryDate;

	

	private int quantity;

	private int salesbalanceQuantity;

	private int soldQuantity;

	// bi-directional many-to-one association to Product
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ProductId")
	private Product product;

	// bi-directional many-to-one association to Branchtransfer
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branchtransferId")
	private Branchtransfer branchtransfer;

	public Branchtransferbreakdown() {
	}

	public int getBranchtransferBreakdownId() {
		return this.branchtransferBreakdownId;
	}

	public void setBranchtransferBreakdownId(int branchtransferBreakdownId) {
		this.branchtransferBreakdownId = branchtransferBreakdownId;
	}

	public int getBalanceQuantity() {
		return this.balanceQuantity;
	}

	public void setBalanceQuantity(int balanceQuantity) {
		this.balanceQuantity = balanceQuantity;
	}

	public String getBatchNo() {
		return this.batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getSalesbalanceQuantity() {
		return this.salesbalanceQuantity;
	}

	public void setSalesbalanceQuantity(int salesbalanceQuantity) {
		this.salesbalanceQuantity = salesbalanceQuantity;
	}

	public int getSoldQuantity() {
		return this.soldQuantity;
	}

	public void setSoldQuantity(int soldQuantity) {
		this.soldQuantity = soldQuantity;
	}

	public Branchtransfer getBranchtransfer() {
		return this.branchtransfer;
	}

	public void setBranchtransfer(Branchtransfer branchtransfer) {
		this.branchtransfer = branchtransfer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}