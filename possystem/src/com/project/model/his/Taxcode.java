package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the taxcode database table.
 * 
 */
@Entity
@Table(name="taxcode")
public class Taxcode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int taxcodeId;

	@Column(precision=10, scale=2)
	private BigDecimal taxCode;

	public Taxcode() {
	}
	
	// bi-directional many-to-one association to Branch
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BranchId")
	private Branch branch;
	
	public int getTaxcodeId() {
		return this.taxcodeId;
	}

	public void setTaxcodeId(int taxcodeId) {
		this.taxcodeId = taxcodeId;
	}

	public BigDecimal getTaxCode() {
		return this.taxCode;
	}

	public void setTaxCode(BigDecimal taxCode) {
		this.taxCode = taxCode;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
	

}