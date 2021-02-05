package com.project.model.sale.sales;

import com.project.model.datamodel.BranchModel;

public class HoteltableareaModel {

	private int areaId;

	private String areaName;

	BranchModel branch = new BranchModel();

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public BranchModel getBranch() {
		return branch;
	}

	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}

}
