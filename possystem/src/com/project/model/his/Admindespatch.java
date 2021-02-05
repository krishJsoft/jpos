package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the admindespatch database table.
 * 
 */
@Entity
@Table(name="admindespatch")
public class Admindespatch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int despatchId;

	@Column(length=50)
	private String despatchType;

	@Column(length=200)
	private String specification;

	@Column(precision=10, scale=2)
	private BigDecimal unitPrice;

	@Column(length=20)
	private String uom;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchRecordId")
	private Branch branch;

	public Admindespatch() {
	}

	public int getDespatchId() {
		return this.despatchId;
	}

	public void setDespatchId(int despatchId) {
		this.despatchId = despatchId;
	}

	public String getDespatchType() {
		return this.despatchType;
	}

	public void setDespatchType(String despatchType) {
		this.despatchType = despatchType;
	}

	public String getSpecification() {
		return this.specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getUom() {
		return this.uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

}