package com.project.model.sale.sales;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SalesorderreturnModel {

	private int salesreturnid;

	private Date laetupdatedDate;

	private String replacementNo;

	private String salesOrderNo;

	private String updatedBy;

	private Date returndate;

	private String status;
	
	private String supplierstatus;
	
	private int branchRecordId;
	
	private BigDecimal totalAmount = new BigDecimal(0.00);

	List<SalesreturnModel> salesreturn = new ArrayList<SalesreturnModel>();

	PoscashtransactionModel poscashtransaction = new PoscashtransactionModel();
	
	SalesorderModel salesorder = new SalesorderModel();

	public int getSalesreturnid() {
		return salesreturnid;
	}

	public void setSalesreturnid(int salesreturnid) {
		this.salesreturnid = salesreturnid;
	}

	public Date getLaetupdatedDate() {
		return laetupdatedDate;
	}

	public void setLaetupdatedDate(Date laetupdatedDate) {
		this.laetupdatedDate = laetupdatedDate;
	}

	public String getReplacementNo() {
		return replacementNo;
	}

	public void setReplacementNo(String replacementNo) {
		this.replacementNo = replacementNo;
	}

	public String getSalesOrderNo() {
		return salesOrderNo;
	}

	public void setSalesOrderNo(String salesOrderNo) {
		this.salesOrderNo = salesOrderNo;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getReturndate() {
		return returndate;
	}

	public void setReturndate(Date returndate) {
		this.returndate = returndate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<SalesreturnModel> getSalesreturn() {
		return salesreturn;
	}

	public void setSalesreturn(List<SalesreturnModel> salesreturn) {
		this.salesreturn = salesreturn;
	}

	public SalesorderModel getSalesorder() {
		return salesorder;
	}

	public void setSalesorder(SalesorderModel salesorder) {
		this.salesorder = salesorder;
	}

	public String getSupplierstatus() {
		return supplierstatus;
	}

	public void setSupplierstatus(String supplierstatus) {
		this.supplierstatus = supplierstatus;
	}

	public int getBranchRecordId() {
		return branchRecordId;
	}

	public void setBranchRecordId(int branchRecordId) {
		this.branchRecordId = branchRecordId;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public PoscashtransactionModel getPoscashtransaction() {
		return poscashtransaction;
	}

	public void setPoscashtransaction(PoscashtransactionModel poscashtransaction) {
		this.poscashtransaction = poscashtransaction;
	}
	
	

}
