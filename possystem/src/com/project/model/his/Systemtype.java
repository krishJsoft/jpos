package com.project.model.his;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="systemtype")
public class Systemtype {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;
	
	@Column(length=30)
	private String name;
	
	@Column(length=15)
	private String kitchenOrderType;
	
	@Column(nullable=false)
	private Boolean mergeBillOption;
	
	@Column(nullable=false)
	private Boolean splitBillOption;
	
	@Column(nullable=false)
	private Boolean advanceBillOption;
	
	@Column(nullable=false)
	private Boolean hasTableNo;
	
	@Column(nullable=false)
	private Boolean hasCardNo;

	@Column(nullable=false)
	private Boolean hasPaxNo;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
