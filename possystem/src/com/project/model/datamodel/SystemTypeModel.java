package com.project.model.datamodel;

public class SystemTypeModel {
	
	private int typeId;
	
	private String name;
	
	private String kitchenOrderType;
	
	private Boolean mergeBillOption;
	
	private Boolean splitBillOption;
	
	private Boolean advanceBillOption;
	
	private Boolean hasTableNo;
	
	private Boolean hasCardNo;
	
	private Boolean hasPaxNo;

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKitchenOrderType() {
		return kitchenOrderType;
	}

	public void setKitchenOrderType(String kitchenOrderType) {
		this.kitchenOrderType = kitchenOrderType;
	}

	public Boolean getMergeBillOption() {
		return mergeBillOption;
	}

	public void setMergeBillOption(Boolean mergeBillOption) {
		this.mergeBillOption = mergeBillOption;
	}

	public Boolean getSplitBillOption() {
		return splitBillOption;
	}

	public void setSplitBillOption(Boolean splitBillOption) {
		this.splitBillOption = splitBillOption;
	}

	public Boolean getAdvanceBillOption() {
		return advanceBillOption;
	}

	public void setAdvanceBillOption(Boolean advanceBillOption) {
		this.advanceBillOption = advanceBillOption;
	}

	public Boolean getHasTableNo() {
		return hasTableNo;
	}

	public void setHasTableNo(Boolean hasTableNo) {
		this.hasTableNo = hasTableNo;
	}

	public Boolean getHasCardNo() {
		return hasCardNo;
	}

	public void setHasCardNo(Boolean hasCardNo) {
		this.hasCardNo = hasCardNo;
	}

	public Boolean getHasPaxNo() {
		return hasPaxNo;
	}

	public void setHasPaxNo(Boolean hasPaxNo) {
		this.hasPaxNo = hasPaxNo;
	}
	
	
}
