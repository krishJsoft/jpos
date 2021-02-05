package com.project.model.datamodel.purchase;


public class PurchaseorderdeliveryaddressModel {

	private int purchaseOrderDeliveryId;
	private String deliveryAddress;
	private Integer branchId;
	private Integer purchaseOrderId;

	public int getPurchaseOrderDeliveryId() {
		return purchaseOrderDeliveryId;
	}

	public void setPurchaseOrderDeliveryId(int purchaseOrderDeliveryId) {
		this.purchaseOrderDeliveryId = purchaseOrderDeliveryId;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public Integer getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(Integer purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

}
