package com.project.model.tax;

import java.math.BigDecimal;

import com.project.model.invoice.branch.BranchinvoiceModel;

public class GstsalesaccountbreakdownModel {

	private int gstsalesaccountbreakdownid;

	private BigDecimal tax;

	private GstaccountModel gstaccount;

	private BranchinvoiceModel branchinvoice;

	public int getGstsalesaccountbreakdownid() {
		return gstsalesaccountbreakdownid;
	}

	public void setGstsalesaccountbreakdownid(int gstsalesaccountbreakdownid) {
		this.gstsalesaccountbreakdownid = gstsalesaccountbreakdownid;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public GstaccountModel getGstaccount() {
		return gstaccount;
	}

	public void setGstaccount(GstaccountModel gstaccount) {
		this.gstaccount = gstaccount;
	}

	public BranchinvoiceModel getBranchinvoice() {
		return branchinvoice;
	}

	public void setBranchinvoice(BranchinvoiceModel branchinvoice) {
		this.branchinvoice = branchinvoice;
	}

}
