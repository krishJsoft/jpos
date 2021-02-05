package com.project.model.his;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the country database table.
 * 
 */
@Entity
@Table(name = "taxmaster")
public class Taxmaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private int taxid;

	@Column(length = 100)
	private String taxname;

	@Column(length = 100)
	private String glcode;

	@Column(precision = 10, scale = 2)
	private BigDecimal taxvalue;

	// bi-directional many-to-one association to Branch
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branchrecordid")
	private Branch branch;

	public Taxmaster() {
	}

	public int getTaxid() {
		return taxid;
	}

	public void setTaxid(int taxid) {
		this.taxid = taxid;
	}

	public String getTaxname() {
		return taxname;
	}

	public void setTaxname(String taxname) {
		this.taxname = taxname;
	}

	public String getGlcode() {
		return glcode;
	}

	public void setGlcode(String glcode) {
		this.glcode = glcode;
	}

	public BigDecimal getTaxvalue() {
		return taxvalue;
	}

	public void setTaxvalue(BigDecimal taxvalue) {
		this.taxvalue = taxvalue;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

}