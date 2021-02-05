package com.project.model.sale.sales;


import com.project.model.datamodel.BranchModel;

public class DiscountremarksModel {
	
	private int discountRemarksId;

	private String remarks;
	
	BranchModel branch = new BranchModel();


	public int getDiscountRemarksId() {
		return discountRemarksId;
	}

	public void setDiscountRemarksId(int discountRemarksId) {
		this.discountRemarksId = discountRemarksId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public BranchModel getBranch() {
		return branch;
	}

	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}

}
