package com.project.model.datamodel;

public class SystemconfigurationModel {
	
	private int systemConfigId;
	
	private Boolean kitchenOrder;
	
	private Boolean terminalKitchenOrder;
	
	private Boolean mobileKitchenOrder;
	
	private Boolean cashierKitchenOrder;
	
	private Boolean billPrinter;
	
	private Boolean paxNo;
	
	private String orderScreenProductSearchBy;
	
	private String printMode;
	
	BranchModel branch=new BranchModel();

	SystemTypeModel systemType=new SystemTypeModel(); 
	
	public int getSystemConfigId() {
		return systemConfigId;
		
	}

	public void setSystemConfigId(int systemConfigId) {
		this.systemConfigId = systemConfigId;
	}


	public Boolean getKitchenOrder() {
		return kitchenOrder;
	}

	public void setKitchenOrder(Boolean kitchenOrder) {
		this.kitchenOrder = kitchenOrder;
	}

	public Boolean getTerminalKitchenOrder() {
		return terminalKitchenOrder;
	}

	public void setTerminalKitchenOrder(Boolean terminalKitchenOrder) {
		this.terminalKitchenOrder = terminalKitchenOrder;
	}

	public Boolean getMobileKitchenOrder() {
		return mobileKitchenOrder;
	}

	public void setMobileKitchenOrder(Boolean mobileKitchenOrder) {
		this.mobileKitchenOrder = mobileKitchenOrder;
	}

	public Boolean getCashierKitchenOrder() {
		return cashierKitchenOrder;
	}

	public void setCashierKitchenOrder(Boolean cashierKitchenOrder) {
		this.cashierKitchenOrder = cashierKitchenOrder;
	}

	public Boolean getBillPrinter() {
		return billPrinter;
	}

	public void setBillPrinter(Boolean billPrinter) {
		this.billPrinter = billPrinter;
	}

	public Boolean getPaxNo() {
		return paxNo;
	}

	public void setPaxNo(Boolean paxNo) {
		this.paxNo = paxNo;
	}

	public String getOrderScreenProductSearchBy() {
		return orderScreenProductSearchBy;
	}

	public void setOrderScreenProductSearchBy(String orderScreenProductSearchBy) {
		this.orderScreenProductSearchBy = orderScreenProductSearchBy;
	}

	public String getPrintMode() {
		return printMode;
	}

	public void setPrintMode(String printMode) {
		this.printMode = printMode;
	}

	public BranchModel getBranch() {
		return branch;
	}

	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}

	public SystemTypeModel getSystemType() {
		return systemType;
	}

	public void setSystemType(SystemTypeModel systemType) {
		this.systemType = systemType;
	}

	
}
