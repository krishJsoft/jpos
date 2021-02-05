package com.project.model.sale.sales.branchsale;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.project.model.datamodel.BranchModel;


public class BranchsaleModel {

	private int branchsalesId;
	private String createdBy;
	private Date createdDate;
	private String deliveryTerms;
	private Date lastModifedDate;
	private String paymentTerms;
	private String salesNo;
	private String deliveryOrderNo;
	private Date salesDate;
	private int itemCount;
	private String remarks;
	private String status;
	private BigDecimal totalAmount;
	private int branchId;
	private String branchName;
	private int sno;
	private int totalItemCount;
	private int SoldQuantityCount;
	private int balanceQuantityCount;
	private BigDecimal totalTax;
	private String branchStatus;
	private String branchtype;
	private int branchRecordId;

	private List<BranchsalesbreakdownModel> branchsalesbreakdowns;
	BranchModel branch = new BranchModel();
	BranchModel supplybranch = new BranchModel();
	
	public int getBranchsalesId() {
		return branchsalesId;
	}

	public void setBranchsalesId(int branchsalesId) {
		this.branchsalesId = branchsalesId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDeliveryTerms() {
		return deliveryTerms;
	}

	public void setDeliveryTerms(String deliveryTerms) {
		this.deliveryTerms = deliveryTerms;
	}

	public Date getLastModifedDate() {
		return lastModifedDate;
	}

	public void setLastModifedDate(Date lastModifedDate) {
		this.lastModifedDate = lastModifedDate;
	}

	public String getPaymentTerms() {
		return paymentTerms;
	}

	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	public Date getSalesDate() {
		return salesDate;
	}

	public void setSalesDate(Date salesDate) {
		this.salesDate = salesDate;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public int getTotalItemCount() {
		return totalItemCount;
	}

	public void setTotalItemCount(int totalItemCount) {
		this.totalItemCount = totalItemCount;
	}

	public int getSoldQuantityCount() {
		return SoldQuantityCount;
	}

	public void setSoldQuantityCount(int soldQuantityCount) {
		SoldQuantityCount = soldQuantityCount;
	}

	public int getBalanceQuantityCount() {
		return balanceQuantityCount;
	}

	public void setBalanceQuantityCount(int balanceQuantityCount) {
		this.balanceQuantityCount = balanceQuantityCount;
	}

	public BigDecimal getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(BigDecimal totalTax) {
		this.totalTax = totalTax;
	}

	public String getBranchtype() {
		return branchtype;
	}

	public void setBranchtype(String branchtype) {
		this.branchtype = branchtype;
	}

	public int getBranchRecordId() {
		return branchRecordId;
	}

	public void setBranchRecordId(int branchRecordId) {
		this.branchRecordId = branchRecordId;
	}

	public List<BranchsalesbreakdownModel> getBranchsalesbreakdowns() {
		return branchsalesbreakdowns;
	}

	public void setBranchsalesbreakdowns(
			List<BranchsalesbreakdownModel> branchsalesbreakdowns) {
		this.branchsalesbreakdowns = branchsalesbreakdowns;
	}

	public BranchModel getBranch() {
		return branch;
	}

	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}

	public String getSalesNo() {
		return salesNo;
	}

	public void setSalesNo(String salesNo) {
		this.salesNo = salesNo;
	}

	public String getDeliveryOrderNo() {
		return deliveryOrderNo;
	}

	public void setDeliveryOrderNo(String deliveryOrderNo) {
		this.deliveryOrderNo = deliveryOrderNo;
	}

	public BranchModel getSupplybranch() {
		return supplybranch;
	}

	public void setSupplybranch(BranchModel supplybranch) {
		this.supplybranch = supplybranch;
	}

	public String getBranchStatus() {
		return branchStatus;
	}

	public void setBranchStatus(String branchStatus) {
		this.branchStatus = branchStatus;
	}

	
	
}
