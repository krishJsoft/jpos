package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the productbatch database table.
 * 
 */
@Entity
@Table(name="productbatch")
public class Productbatch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int batchId;

	@Column(length=25)
	private String batchNo;

	@Column(length=15)
	private String deliveryOrderNo;

	@Temporal(TemporalType.DATE)
	private Date expairyDate;

	private int quantity;
	
	private int avilableQuantity;

	@Column(length=1)
	private String status;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchRecordId")
	private Branch branch;

	//bi-directional many-to-one association to Product
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ProductId")
	private Product product;

	public Productbatch() {
	}

	public int getBatchId() {
		return this.batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public String getBatchNo() {
		return this.batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getDeliveryOrderNo() {
		return this.deliveryOrderNo;
	}

	public void setDeliveryOrderNo(String deliveryOrderNo) {
		this.deliveryOrderNo = deliveryOrderNo;
	}

	public Date getExpairyDate() {
		return this.expairyDate;
	}

	public void setExpairyDate(Date expairyDate) {
		this.expairyDate = expairyDate;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getAvilableQuantity() {
		return avilableQuantity;
	}

	public void setAvilableQuantity(int avilableQuantity) {
		this.avilableQuantity = avilableQuantity;
	}
	
	

}