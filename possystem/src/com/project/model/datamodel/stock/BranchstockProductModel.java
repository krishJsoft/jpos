package com.project.model.datamodel.stock;

import java.math.BigDecimal;

import com.project.model.datamodel.BranchModel;

public class BranchstockProductModel {

	BranchModel branch = new BranchModel();
	private BigDecimal quantityonHand;

	public BranchModel getBranch() {
		return branch;
	}

	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}

	public BigDecimal getQuantityonHand() {
		return quantityonHand;
	}

	public void setQuantityonHand(BigDecimal quantityonHand) {
		this.quantityonHand = quantityonHand;
	}

}
