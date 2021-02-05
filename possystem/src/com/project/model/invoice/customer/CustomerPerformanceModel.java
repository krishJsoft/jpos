package com.project.model.invoice.customer;

import java.math.BigDecimal;
import java.util.Date;

import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.CustomerModel;

public class CustomerPerformanceModel {

	private CustomerModel customer = new CustomerModel();
	private BranchModel branch = new BranchModel();

	private BigDecimal last30Amount;
	private BigDecimal last30_60Amount;
	private BigDecimal last60_90Amount;
	private BigDecimal lastOneyearAmount;
	private Integer last30DaysNos;
	private Integer last30_60DaysNos;
	private Integer last60_90DaysNos;
	private Integer lastOneyearNos;
	private BigDecimal OutStandingAmount;
	private Date last30Date;
	private Date last30_60Date;
	private Date last60_90Date;
	private Date lastoneyearDate;

	private String branchCustomerName;
	private Integer branchCustomerId;

	public CustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

	public BranchModel getBranch() {
		return branch;
	}

	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}

	public BigDecimal getLast30Amount() {
		return last30Amount;
	}

	public void setLast30Amount(BigDecimal last30Amount) {
		this.last30Amount = last30Amount;
	}

	public BigDecimal getLast30_60Amount() {
		return last30_60Amount;
	}

	public void setLast30_60Amount(BigDecimal last30_60Amount) {
		this.last30_60Amount = last30_60Amount;
	}

	public BigDecimal getLast60_90Amount() {
		return last60_90Amount;
	}

	public void setLast60_90Amount(BigDecimal last60_90Amount) {
		this.last60_90Amount = last60_90Amount;
	}

	public BigDecimal getLastOneyearAmount() {
		return lastOneyearAmount;
	}

	public void setLastOneyearAmount(BigDecimal lastOneyearAmount) {
		this.lastOneyearAmount = lastOneyearAmount;
	}

	public Integer getLast30DaysNos() {
		return last30DaysNos;
	}

	public void setLast30DaysNos(Integer last30DaysNos) {
		this.last30DaysNos = last30DaysNos;
	}

	public Integer getLast30_60DaysNos() {
		return last30_60DaysNos;
	}

	public void setLast30_60DaysNos(Integer last30_60DaysNos) {
		this.last30_60DaysNos = last30_60DaysNos;
	}

	public Integer getLast60_90DaysNos() {
		return last60_90DaysNos;
	}

	public void setLast60_90DaysNos(Integer last60_90DaysNos) {
		this.last60_90DaysNos = last60_90DaysNos;
	}

	public Integer getLastOneyearNos() {
		return lastOneyearNos;
	}

	public void setLastOneyearNos(Integer lastOneyearNos) {
		this.lastOneyearNos = lastOneyearNos;
	}

	public BigDecimal getOutStandingAmount() {
		return OutStandingAmount;
	}

	public void setOutStandingAmount(BigDecimal outStandingAmount) {
		OutStandingAmount = outStandingAmount;
	}

	public Date getLast30Date() {
		return last30Date;
	}

	public void setLast30Date(Date last30Date) {
		this.last30Date = last30Date;
	}

	public Date getLast30_60Date() {
		return last30_60Date;
	}

	public void setLast30_60Date(Date last30_60Date) {
		this.last30_60Date = last30_60Date;
	}

	public Date getLast60_90Date() {
		return last60_90Date;
	}

	public void setLast60_90Date(Date last60_90Date) {
		this.last60_90Date = last60_90Date;
	}

	public Date getLastoneyearDate() {
		return lastoneyearDate;
	}

	public void setLastoneyearDate(Date lastoneyearDate) {
		this.lastoneyearDate = lastoneyearDate;
	}

	public String getBranchCustomerName() {
		return branchCustomerName;
	}

	public void setBranchCustomerName(String branchCustomerName) {
		this.branchCustomerName = branchCustomerName;
	}

	public Integer getBranchCustomerId() {
		return branchCustomerId;
	}

	public void setBranchCustomerId(Integer branchCustomerId) {
		this.branchCustomerId = branchCustomerId;
	}

}
