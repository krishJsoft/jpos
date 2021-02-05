package com.project.model.sale.sales;

import java.util.Date;

import com.project.model.datamodel.SupplierModel;

public class SalesreturnModel {

	private int resturnid;

	private String approvedBy;

	private Date approvedDate;

	private String processBy;

	private int replacementQuantity;

	private Date returndate;

	private int returnQuantity;

	private String status;
	
	private SupplierModel supplier= new SupplierModel();

	SalesorderbreakdownModel salesorderbreakdownmodel = new SalesorderbreakdownModel();	

	public int getResturnid() {
		return resturnid;
	}

	public void setResturnid(int resturnid) {
		this.resturnid = resturnid;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getProcessBy() {
		return processBy;
	}

	public void setProcessBy(String processBy) {
		this.processBy = processBy;
	}

	public int getReplacementQuantity() {
		return replacementQuantity;
	}

	public void setReplacementQuantity(int replacementQuantity) {
		this.replacementQuantity = replacementQuantity;
	}

	public Date getReturndate() {
		return returndate;
	}

	public void setReturndate(Date returndate) {
		this.returndate = returndate;
	}

	public int getReturnQuantity() {
		return returnQuantity;
	}

	public void setReturnQuantity(int returnQuantity) {
		this.returnQuantity = returnQuantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public SalesorderbreakdownModel getSalesorderbreakdownmodel() {
		return salesorderbreakdownmodel;
	}

	public void setSalesorderbreakdownmodel(
			SalesorderbreakdownModel salesorderbreakdownmodel) {
		this.salesorderbreakdownmodel = salesorderbreakdownmodel;
	}

	public SupplierModel getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierModel supplier) {
		this.supplier = supplier;
	}

	

}
