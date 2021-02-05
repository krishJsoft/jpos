package com.project.model.redemption;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.project.model.datamodel.CustomerModel;

public class LoyaltyredemptionModel {

	private int redemptionId;

	private String approvedBy;

	private Date approvedDate;

	private String createdBy;

	private String loyaltyCode;

	private Date redemptionDate;

	private BigDecimal totalredemAmount;

	private int totalredemPoint;

	CustomerModel customer;

	private String status;

	Integer branchRecordId;

	List<LoyaltyredemptionbreakdownModel> loyaltyredemptionbreakdowns;

	public int getRedemptionId() {
		return redemptionId;
	}

	public void setRedemptionId(int redemptionId) {
		this.redemptionId = redemptionId;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLoyaltyCode() {
		return loyaltyCode;
	}

	public void setLoyaltyCode(String loyaltyCode) {
		this.loyaltyCode = loyaltyCode;
	}

	public Date getRedemptionDate() {
		return redemptionDate;
	}

	public void setRedemptionDate(Date redemptionDate) {
		this.redemptionDate = redemptionDate;
	}

	public BigDecimal getTotalredemAmount() {
		return totalredemAmount;
	}

	public void setTotalredemAmount(BigDecimal totalredemAmount) {
		this.totalredemAmount = totalredemAmount;
	}

	public int getTotalredemPoint() {
		return totalredemPoint;
	}

	public void setTotalredemPoint(int totalredemPoint) {
		this.totalredemPoint = totalredemPoint;
	}

	public CustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

	public List<LoyaltyredemptionbreakdownModel> getLoyaltyredemptionbreakdowns() {
		return loyaltyredemptionbreakdowns;
	}

	public void setLoyaltyredemptionbreakdowns(
			List<LoyaltyredemptionbreakdownModel> loyaltyredemptionbreakdowns) {
		this.loyaltyredemptionbreakdowns = loyaltyredemptionbreakdowns;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getBranchRecordId() {
		return branchRecordId;
	}

	public void setBranchRecordId(Integer branchRecordId) {
		this.branchRecordId = branchRecordId;
	}

}
