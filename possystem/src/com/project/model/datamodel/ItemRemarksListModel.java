package com.project.model.datamodel;

public class ItemRemarksListModel {
	private int remarksListID;

	private String remarksName;
	
	BranchModel branch=new BranchModel();
	
	public int getRemarksListID() {
		return remarksListID;
	}
	
	public void setRemarksListID(int remarksListID) {
		this.remarksListID = remarksListID;
	}
	

	public String getRemarksName() {
		return remarksName;
	}

	public void setRemarksName(String remarksName) {
		this.remarksName = remarksName;
	}

	public BranchModel getBranch() {
		return branch;
	}
	
	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}
	
	
}
