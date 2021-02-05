package com.project.model.sale.sales;

import com.project.model.datamodel.BranchModel;

public class HoteltableModel {

	private int tableid;

	private String tableNo;

	private String tableName;

	private String status;
	
	private String topPosition;
	
	private String leftPosition;
	
	private String tableShape;
	
	private HoteltableareaModel tableArea=new HoteltableareaModel();
	

	BranchModel branch = new BranchModel();

	public int getTableid() {
		return tableid;
	}

	public void setTableid(int tableid) {
		this.tableid = tableid;
	}

	public String getTableNo() {
		return tableNo;
	}

	public void setTableNo(String tableNo) {
		this.tableNo = tableNo;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTopPosition() {
		return topPosition;
	}

	public void setTopPosition(String topPosition) {
		this.topPosition = topPosition;
	}

	public String getLeftPosition() {
		return leftPosition;
	}

	public void setLeftPosition(String leftPosition) {
		this.leftPosition = leftPosition;
	}

	public String getTableShape() {
		return tableShape;
	}

	public void setTableShape(String tableShape) {
		this.tableShape = tableShape;
	}

	public HoteltableareaModel getTableArea() {
		return tableArea;
	}

	public void setTableArea(HoteltableareaModel tableArea) {
		this.tableArea = tableArea;
	}

	public BranchModel getBranch() {
		return branch;
	}

	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}

}
