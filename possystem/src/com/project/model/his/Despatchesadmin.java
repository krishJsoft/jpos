package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the despatchesadmin database table.
 * 
 */
@Entity
@Table(name="despatchesadmin")
public class Despatchesadmin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int despatchesAdminId;

	@Column(length=45)
	private String despatchesAdmincol;

	@Column(length=45)
	private String specification;

	@Column(length=45)
	private String type;

	@Column(length=45)
	private String unitMeasure;

	@Column(precision=10, scale=2)
	private BigDecimal unitPrice;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchRecordId")
	private Branch branch;

	public Despatchesadmin() {
	}

	public int getDespatchesAdminId() {
		return this.despatchesAdminId;
	}

	public void setDespatchesAdminId(int despatchesAdminId) {
		this.despatchesAdminId = despatchesAdminId;
	}

	public String getDespatchesAdmincol() {
		return this.despatchesAdmincol;
	}

	public void setDespatchesAdmincol(String despatchesAdmincol) {
		this.despatchesAdmincol = despatchesAdmincol;
	}

	public String getSpecification() {
		return this.specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUnitMeasure() {
		return this.unitMeasure;
	}

	public void setUnitMeasure(String unitMeasure) {
		this.unitMeasure = unitMeasure;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

}