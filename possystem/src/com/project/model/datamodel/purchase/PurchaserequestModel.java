package com.project.model.datamodel.purchase;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.project.model.datamodel.BranchModel;


public class PurchaserequestModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7721935633169479574L;
	private int purchaseRequestId;
	private String createdBy;
	private Date createdDate;
	private Date deliveryDate;
	private String deliveryType;
	private Date lastModifiedDate;
	private String lastPurchaseOrderNo;
	private String purchaseType;
	private int quantityRequired;
	private String reason;
	private String referenceNo;
	private String remarks;
	private String status;
	private int branchId;
	private String branchName;
	private String branchtype;
	private String branchstatus;
	private String branchview;

	BranchModel branch = new BranchModel();
	
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

	private List<PurchaserequestbreakdownModel> purchaserequestbreakdowns;

	public int getPurchaseRequestId() {
		return purchaseRequestId;
	}

	public void setPurchaseRequestId(int purchaseRequestId) {
		this.purchaseRequestId = purchaseRequestId;
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

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getLastPurchaseOrderNo() {
		return lastPurchaseOrderNo;
	}

	public void setLastPurchaseOrderNo(String lastPurchaseOrderNo) {
		this.lastPurchaseOrderNo = lastPurchaseOrderNo;
	}

	public String getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}

	public int getQuantityRequired() {
		return quantityRequired;
	}

	public void setQuantityRequired(int quantityRequired) {
		this.quantityRequired = quantityRequired;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
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

	public List<PurchaserequestbreakdownModel> getPurchaserequestbreakdowns() {
		return purchaserequestbreakdowns;
	}

	public void setPurchaserequestbreakdowns(
			List<PurchaserequestbreakdownModel> purchaserequestbreakdowns) {
		this.purchaserequestbreakdowns = purchaserequestbreakdowns;
	}

	public String getBranchtype() {
		return branchtype;
	}

	public void setBranchtype(String branchtype) {
		this.branchtype = branchtype;
	}

	public String getBranchstatus() {
		return branchstatus;
	}

	public void setBranchstatus(String branchstatus) {
		this.branchstatus = branchstatus;
	}

	public String getBranchview() {
		return branchview;
	}

	public void setBranchview(String branchview) {
		this.branchview = branchview;
	}

	public BranchModel getBranch() {
		return branch;
	}

	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}

	
	
}
