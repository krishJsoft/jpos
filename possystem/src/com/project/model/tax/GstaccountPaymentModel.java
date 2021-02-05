package com.project.model.tax;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.project.model.datamodel.BranchModel;

public class GstaccountPaymentModel implements Serializable {

	private int accounttaxpaymentid;

	private BigDecimal paidamount;

	private Date genaratedDate;

	private String status;

	private String referenceNo;

	private String remarks;

	private String paymentType;

	private String genaratedBy;

	private BranchModel branch;

	public GstaccountPaymentModel() {
	}

	public int getAccounttaxpaymentid() {
		return accounttaxpaymentid;
	}

	public void setAccounttaxpaymentid(int accounttaxpaymentid) {
		this.accounttaxpaymentid = accounttaxpaymentid;
	}

	public BigDecimal getPaidamount() {
		return paidamount;
	}

	public void setPaidamount(BigDecimal paidamount) {
		this.paidamount = paidamount;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getGenaratedBy() {
		return genaratedBy;
	}

	public void setGenaratedBy(String genaratedBy) {
		this.genaratedBy = genaratedBy;
	}

	public Date getGenaratedDate() {
		return this.genaratedDate;
	}

	public void setGenaratedDate(Date genaratedDate) {
		this.genaratedDate = genaratedDate;
	}

	public BranchModel getBranch() {
		return this.branch;
	}

	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}