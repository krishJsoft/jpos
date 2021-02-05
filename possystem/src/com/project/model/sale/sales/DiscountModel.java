package com.project.model.sale.sales;

import java.math.BigDecimal;

import com.project.model.datamodel.BranchModel;

public class DiscountModel {
	
	private int discountId;

	private String discountTag;
	
	private Integer discountRate;

	BranchModel branch = new BranchModel();

	public int getDiscountId() {
		return discountId;
		
	}

	public void setDiscountId(int discountId) {
		this.discountId = discountId;
	}

	public String getDiscountTag() {
		return discountTag;
	}

	public void setDiscountTag(String discountTag) {
		this.discountTag = discountTag;
	}

	public Integer getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(Integer discountRate) {
		this.discountRate = discountRate;
	}

	public BranchModel getBranch() {
		return branch;
	}

	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}

}
