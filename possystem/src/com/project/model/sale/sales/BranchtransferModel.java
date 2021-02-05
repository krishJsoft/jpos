package com.project.model.sale.sales;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.project.model.datamodel.BranchModel;

public class BranchtransferModel {

	private int branchtransferId;

	private int balanceQuantityCount;

	private int branchId;

	private int branchRecordId;

	private String branchStatus;

	private String createdBy;

	private Date createdDate;

	private Date lastModifedDate;

	private String transferNo;

	private String remarks;

	private String status;

	private int totalItemCount;

	List<BranchtransferbreakdownModel> branchtransferbreakdowns = new ArrayList<BranchtransferbreakdownModel>();

	BranchModel branch = new BranchModel();
	BranchModel supplybranch = new BranchModel();

	public int getBranchtransferId() {
		return branchtransferId;
	}

	public void setBranchtransferId(int branchtransferId) {
		this.branchtransferId = branchtransferId;
	}

	public int getBalanceQuantityCount() {
		return balanceQuantityCount;
	}

	public void setBalanceQuantityCount(int balanceQuantityCount) {
		this.balanceQuantityCount = balanceQuantityCount;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getBranchRecordId() {
		return branchRecordId;
	}

	public void setBranchRecordId(int branchRecordId) {
		this.branchRecordId = branchRecordId;
	}

	public String getBranchStatus() {
		return branchStatus;
	}

	public void setBranchStatus(String branchStatus) {
		this.branchStatus = branchStatus;
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

	public Date getLastModifedDate() {
		return lastModifedDate;
	}

	public void setLastModifedDate(Date lastModifedDate) {
		this.lastModifedDate = lastModifedDate;
	}

	public String getTransferNo() {
		return transferNo;
	}

	public void setTransferNo(String transferNo) {
		this.transferNo = transferNo;
	}
	

	public List<BranchtransferbreakdownModel> getBranchtransferbreakdowns() {
		return branchtransferbreakdowns;
	}

	public void setBranchtransferbreakdowns(
			List<BranchtransferbreakdownModel> branchtransferbreakdowns) {
		this.branchtransferbreakdowns = branchtransferbreakdowns;
	}

	public BranchModel getBranch() {
		return branch;
	}

	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}

	public BranchModel getSupplybranch() {
		return supplybranch;
	}

	public void setSupplybranch(BranchModel supplybranch) {
		this.supplybranch = supplybranch;
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

	public int getTotalItemCount() {
		return totalItemCount;
	}

	public void setTotalItemCount(int totalItemCount) {
		this.totalItemCount = totalItemCount;
	}

}
