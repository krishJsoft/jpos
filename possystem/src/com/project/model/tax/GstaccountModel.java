package com.project.model.tax;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.project.model.datamodel.BranchModel;

public class GstaccountModel {

	private int accounttaxid;

	private BigDecimal balancepaid;

	private BigDecimal balancepay;

	private Date genaratedDate;

	private Date taxinvoiceperiod;
	
	private Date taxinvoiceperiodto;

	private BigDecimal totalpurchasetax;

	private BigDecimal totalsalestax;
	
	private String status;

	private List<GstpurchaseaccountbreakdownModel> gstpurchaseaccountbreakdowns;

	private List<GstsalesaccountbreakdownModel> gstsalesaccountbreakdowns;

	private BranchModel branch;

	public int getAccounttaxid() {
		return accounttaxid;
	}

	public void setAccounttaxid(int accounttaxid) {
		this.accounttaxid = accounttaxid;
	}

	public BigDecimal getBalancepaid() {
		return balancepaid;
	}

	public void setBalancepaid(BigDecimal balancepaid) {
		this.balancepaid = balancepaid;
	}

	public BigDecimal getBalancepay() {
		return balancepay;
	}

	public void setBalancepay(BigDecimal balancepay) {
		this.balancepay = balancepay;
	}

	public Date getGenaratedDate() {
		return genaratedDate;
	}

	public void setGenaratedDate(Date genaratedDate) {
		this.genaratedDate = genaratedDate;
	}

	public Date getTaxinvoiceperiod() {
		return taxinvoiceperiod;
	}

	public void setTaxinvoiceperiod(Date taxinvoiceperiod) {
		this.taxinvoiceperiod = taxinvoiceperiod;
	}

	public BigDecimal getTotalpurchasetax() {
		return totalpurchasetax;
	}

	public void setTotalpurchasetax(BigDecimal totalpurchasetax) {
		this.totalpurchasetax = totalpurchasetax;
	}

	public BigDecimal getTotalsalestax() {
		return totalsalestax;
	}

	public void setTotalsalestax(BigDecimal totalsalestax) {
		this.totalsalestax = totalsalestax;
	}

	public List<GstpurchaseaccountbreakdownModel> getGstpurchaseaccountbreakdowns() {
		return gstpurchaseaccountbreakdowns;
	}

	public void setGstpurchaseaccountbreakdowns(
			List<GstpurchaseaccountbreakdownModel> gstpurchaseaccountbreakdowns) {
		this.gstpurchaseaccountbreakdowns = gstpurchaseaccountbreakdowns;
	}

	public List<GstsalesaccountbreakdownModel> getGstsalesaccountbreakdowns() {
		return gstsalesaccountbreakdowns;
	}

	public void setGstsalesaccountbreakdowns(
			List<GstsalesaccountbreakdownModel> gstsalesaccountbreakdowns) {
		this.gstsalesaccountbreakdowns = gstsalesaccountbreakdowns;
	}

	public BranchModel getBranch() {
		return branch;
	}

	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}

	public Date getTaxinvoiceperiodto() {
		return taxinvoiceperiodto;
	}

	public void setTaxinvoiceperiodto(Date taxinvoiceperiodto) {
		this.taxinvoiceperiodto = taxinvoiceperiodto;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
