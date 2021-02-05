package com.project.model.his;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="kitchenorderremarksbreakdown")
public class Kitchenorderremarksbreakdown {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;
	
	//bi-directional many-to-one association to Salesorder
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="orderbreakdownId")
	private Kitchenorderbreakdown kitchenorderbreakdown;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="remarksId")
	private Itemremarkslist remarks;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Kitchenorderbreakdown getKitchenorder() {
		return kitchenorderbreakdown;
	}

	public void setKitchenorder(Kitchenorderbreakdown kitchenorderbreakdown) {
		this.kitchenorderbreakdown = kitchenorderbreakdown;
	}

	public Itemremarkslist getRemarks() {
		return remarks;
	}

	public void setRemarks(Itemremarkslist remarks) {
		this.remarks = remarks;
	}
	
}
