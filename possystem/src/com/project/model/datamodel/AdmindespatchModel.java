package com.project.model.datamodel;

import java.math.BigDecimal;

public class AdmindespatchModel {

	private int despatchId;
	private String despatchType;
	private String despatchOldType;
	private String specification;
	private BigDecimal unitPrice;
	private String uom;
	private String uomold;
	private int branchId;

	public int getDespatchId() {
		return despatchId;
	}

	public void setDespatchId(int despatchId) {
		this.despatchId = despatchId;
	}

	public String getDespatchType() {
		return despatchType;
	}

	public void setDespatchType(String despatchType) {
		this.despatchType = despatchType;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getDespatchOldType() {
		return despatchOldType;
	}

	public void setDespatchOldType(String despatchOldType) {
		this.despatchOldType = despatchOldType;
	}

	public String getUomold() {
		return uomold;
	}

	public void setUomold(String uomold) {
		this.uomold = uomold;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	
	
}
