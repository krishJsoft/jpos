package com.project.model.datamodel;

public class KitchenprintercategorylinkModal {
	
	private int id;
	
	private int categoryId;
	
	KitchenprinterlistModal printer = new KitchenprinterlistModal();

	BranchModel branch = new BranchModel();

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


	public KitchenprinterlistModal getPrinter() {
		return printer;
	}


	public void setPrinter(KitchenprinterlistModal printer) {
		this.printer = printer;
	}


	public BranchModel getBranch() {
		return branch;
	}


	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}


}
