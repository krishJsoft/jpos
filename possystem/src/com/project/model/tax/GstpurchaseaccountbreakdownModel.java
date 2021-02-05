package com.project.model.tax;

import java.math.BigDecimal;

import com.project.model.invoice.supplier.SupplierinvoiceModel;

public class GstpurchaseaccountbreakdownModel {

	private int gstpurchaseaccountbreakdownId;

	private BigDecimal tax;

	private SupplierinvoiceModel supplierinvoice;

	private GstaccountModel gstaccount;

	public int getGstpurchaseaccountbreakdownId() {
		return gstpurchaseaccountbreakdownId;
	}

	public void setGstpurchaseaccountbreakdownId(
			int gstpurchaseaccountbreakdownId) {
		this.gstpurchaseaccountbreakdownId = gstpurchaseaccountbreakdownId;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public SupplierinvoiceModel getSupplierinvoice() {
		return supplierinvoice;
	}

	public void setSupplierinvoice(SupplierinvoiceModel supplierinvoice) {
		this.supplierinvoice = supplierinvoice;
	}

	public GstaccountModel getGstaccount() {
		return gstaccount;
	}

	public void setGstaccount(GstaccountModel gstaccount) {
		this.gstaccount = gstaccount;
	}

}
