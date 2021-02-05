package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the purchaseorderdeliveryaddress database table.
 * 
 */
@Entity
@Table(name="purchaseorderdeliveryaddress")
public class Purchaseorderdeliveryaddress implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int purchaseOrderDeliveryId;

	@Lob
	private String deliveryAddress;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BranchId")
	private Branch branch;

	//bi-directional many-to-one association to Purchaseorder
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PurchaseOrderId")
	private Purchaseorder purchaseorder;

	public Purchaseorderdeliveryaddress() {
	}

	public int getPurchaseOrderDeliveryId() {
		return this.purchaseOrderDeliveryId;
	}

	public void setPurchaseOrderDeliveryId(int purchaseOrderDeliveryId) {
		this.purchaseOrderDeliveryId = purchaseOrderDeliveryId;
	}

	public String getDeliveryAddress() {
		return this.deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Purchaseorder getPurchaseorder() {
		return this.purchaseorder;
	}

	public void setPurchaseorder(Purchaseorder purchaseorder) {
		this.purchaseorder = purchaseorder;
	}

}