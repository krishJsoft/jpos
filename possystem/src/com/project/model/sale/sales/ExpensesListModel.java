package com.project.model.sale.sales;

import com.project.model.datamodel.BranchModel;

public class ExpensesListModel {

	private int expID;
	
	private String expName;
	
	private int branchId;
	
	BranchModel branch=new BranchModel();

	public int getExpID() {
		return expID;
	}

	public void setExpID(int expID) {
		this.expID = expID;
	}

	public String getExpName() {
		return expName;
	}

	public void setExpName(String expName) {
		this.expName = expName;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public BranchModel getBranch() {
		return branch;
	}

	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}
	
}
