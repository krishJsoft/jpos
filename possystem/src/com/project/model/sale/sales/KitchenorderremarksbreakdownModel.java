package com.project.model.sale.sales;

import com.project.model.datamodel.ItemRemarksListModel;

public class KitchenorderremarksbreakdownModel {
	
	private int id;
	
	ItemRemarksListModel remarks=new ItemRemarksListModel();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ItemRemarksListModel getRemarks() {
		return remarks;
	}

	public void setRemarks(ItemRemarksListModel remarks) {
		this.remarks = remarks;
	}

}
