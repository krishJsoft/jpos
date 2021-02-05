package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the branchpayment database table.
 * 
 */
@Entity
@Table(name="branchpayment")
public class Branchpayment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int branchPaymentId;

	@Column(length=245)
	private String branchAddress;

	@Column(length=45)
	private String branchName;

	@Temporal(TemporalType.TIMESTAMP)
	private Date invoiceDate;

	@Column(length=45)
	private String invoiceNo;

	@Column(precision=10, scale=2)
	private BigDecimal outstandingAmount;

	@Column(length=1)
	private String status;

	@Column(precision=10, scale=2)
	private BigDecimal totalInvoiceAmount;

	@Column(precision=10, scale=2)
	private BigDecimal totalPaidAmount;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchRecordId")
	private Branch branch;

	//bi-directional many-to-one association to Branchpaymentbreakdown
	@OneToMany(mappedBy="branchpayment")
	private List<Branchpaymentbreakdown> branchpaymentbreakdowns;

	public Branchpayment() {
	}

	public int getBranchPaymentId() {
		return this.branchPaymentId;
	}

	public void setBranchPaymentId(int branchPaymentId) {
		this.branchPaymentId = branchPaymentId;
	}

	public String getBranchAddress() {
		return this.branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	public String getBranchName() {
		return this.branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public Date getInvoiceDate() {
		return this.invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public BigDecimal getOutstandingAmount() {
		return this.outstandingAmount;
	}

	public void setOutstandingAmount(BigDecimal outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTotalInvoiceAmount() {
		return this.totalInvoiceAmount;
	}

	public void setTotalInvoiceAmount(BigDecimal totalInvoiceAmount) {
		this.totalInvoiceAmount = totalInvoiceAmount;
	}

	public BigDecimal getTotalPaidAmount() {
		return this.totalPaidAmount;
	}

	public void setTotalPaidAmount(BigDecimal totalPaidAmount) {
		this.totalPaidAmount = totalPaidAmount;
	}

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public List<Branchpaymentbreakdown> getBranchpaymentbreakdowns() {
		return this.branchpaymentbreakdowns;
	}

	public void setBranchpaymentbreakdowns(List<Branchpaymentbreakdown> branchpaymentbreakdowns) {
		this.branchpaymentbreakdowns = branchpaymentbreakdowns;
	}

	public Branchpaymentbreakdown addBranchpaymentbreakdown(Branchpaymentbreakdown branchpaymentbreakdown) {
		getBranchpaymentbreakdowns().add(branchpaymentbreakdown);
		branchpaymentbreakdown.setBranchpayment(this);

		return branchpaymentbreakdown;
	}

	public Branchpaymentbreakdown removeBranchpaymentbreakdown(Branchpaymentbreakdown branchpaymentbreakdown) {
		getBranchpaymentbreakdowns().remove(branchpaymentbreakdown);
		branchpaymentbreakdown.setBranchpayment(null);

		return branchpaymentbreakdown;
	}

}