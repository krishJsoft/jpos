package com.project.model.datamodel;

import java.math.BigDecimal;

public class TaxcodeModel {

	private int taxcodeId;

	private BigDecimal taxCode;

	public int getTaxcodeId() {
		return taxcodeId;
	}

	public void setTaxcodeId(int taxcodeId) {
		this.taxcodeId = taxcodeId;
	}

	public BigDecimal getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(BigDecimal taxCode) {
		this.taxCode = taxCode;
	}

}
