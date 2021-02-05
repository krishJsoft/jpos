package com.project.model.datamodel;

public class ItemRemarksFunctionListModel {
	private int remarksID;

	private ItemRemarksListModel remarks;
	
	BranchModel branch=new BranchModel();
	
	ProductcategoryModel productcategory;

	public int getRemarksID() {
		return remarksID;
	}

	public void setRemarksID(int remarksID) {
		this.remarksID = remarksID;
	}

	
	public ItemRemarksListModel getRemarks() {
		return remarks;
	}

	public void setRemarks(ItemRemarksListModel remarks) {
		this.remarks = remarks;
	}

	public BranchModel getBranch() {
		return branch;
	}

	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}

	public ProductcategoryModel getProductcategory() {
		return productcategory;
	}

	public void setProductcategory(ProductcategoryModel productcategory) {
		this.productcategory = productcategory;
	}
	
}
