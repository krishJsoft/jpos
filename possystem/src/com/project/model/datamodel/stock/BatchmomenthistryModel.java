package com.project.model.datamodel.stock;

import java.math.BigDecimal;
import java.util.Date;
import com.project.model.datamodel.ProductModel;


public class BatchmomenthistryModel {

	
	private int batchMomentId;

	private String batchNo;

	private Date expdate;

	private Date lastupdateDate;

	private String lastupdateUser;

	private BigDecimal quantity;
	
	private String status;

	private BigDecimal soldQuantity;

	private Integer branchRecordId;

	private ProductModel product;
	
	ProductbatchModel batch = new ProductbatchModel();

	public int getBatchMomentId() {
		return batchMomentId;
	}

	public void setBatchMomentId(int batchMomentId) {
		this.batchMomentId = batchMomentId;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Date getExpdate() {
		return expdate;
	}

	public void setExpdate(Date expdate) {
		this.expdate = expdate;
	}

	public Date getLastupdateDate() {
		return lastupdateDate;
	}

	public void setLastupdateDate(Date lastupdateDate) {
		this.lastupdateDate = lastupdateDate;
	}

	public String getLastupdateUser() {
		return lastupdateUser;
	}

	public void setLastupdateUser(String lastupdateUser) {
		this.lastupdateUser = lastupdateUser;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getSoldQuantity() {
		return soldQuantity;
	}

	public void setSoldQuantity(BigDecimal soldQuantity) {
		this.soldQuantity = soldQuantity;
	}

	public Integer getBranchRecordId() {
		return branchRecordId;
	}

	public void setBranchRecordId(Integer branchRecordId) {
		this.branchRecordId = branchRecordId;
	}

	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}

	public ProductbatchModel getBatch() {
		return batch;
	}

	public void setBatch(ProductbatchModel batch) {
		this.batch = batch;
	}
	
	
	
	
}
