package com.project.model.datamodel;

import java.math.BigDecimal;
import java.util.Date;

public class MembershipPaymentTransactionModel {

	private int Id;
	
	private int memberId;
	
	private BigDecimal amount;
	
	private Date createdDate;
	
	private String durationAdded;
	
	private int createdBy;
	
	private int branchId;
	
	private int status;
	
	MembershipModel member=new MembershipModel();
	
	BranchstaffmemberModel branchStaff=new BranchstaffmemberModel();
	
	BranchModel branch=new BranchModel();

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDurationAdded() {
		return durationAdded;
	}

	public void setDurationAdded(String durationAdded) {
		this.durationAdded = durationAdded;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public MembershipModel getMember() {
		return member;
	}

	public void setMember(MembershipModel member) {
		this.member = member;
	}

	public BranchstaffmemberModel getBranchStaff() {
		return branchStaff;
	}

	public void setBranchStaff(BranchstaffmemberModel branchStaff) {
		this.branchStaff = branchStaff;
	}

	public BranchModel getBranch() {
		return branch;
	}

	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}
	
}
