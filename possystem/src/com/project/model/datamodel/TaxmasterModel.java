package com.project.model.datamodel;

import java.math.BigDecimal;

public class TaxmasterModel {

	private Integer taxid;

	private String id;

	private String taxname;

	private String glcode;

	private BigDecimal taxvalue;

	private BranchModel branch;

	public Integer getTaxid() {
		return taxid;
	}

	public void setTaxid(Integer taxid) {
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

	public BranchModel getBranch() {
		return branch;
	}

	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
