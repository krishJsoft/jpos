package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the gstaccount database table.
 * 
 */
@Entity
@Table(name = "gstaccountpayment")
public class GstaccountPayment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private int accounttaxpaymentid;

	@Column(precision = 10, scale = 2)
	private BigDecimal paidamount;

	@Temporal(TemporalType.TIMESTAMP)
	private Date genaratedDate;

	@Column(length = 1)
	private String status;

	@Column(length = 50)
	private String referenceNo;

	@Column(length = 500)
	private String remarks;

	@Column(length = 25)
	private String paymentType;

	@Column(length = 50)
	private String genaratedBy;

	// bi-directional many-to-one association to Branch
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branchRecordId")
	private Branch branch;

	public GstaccountPayment() {
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

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}