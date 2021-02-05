package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the branchpaymentbreakdown database table.
 * 
 */
@Entity
@Table(name="branchpaymentbreakdown")
public class Branchpaymentbreakdown implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int branchPaymentBreakdownId;

	@Column(length=45)
	private String bankName;

	@Column(length=45)
	private String chequeNo;

	@Column(length=45)
	private String invoiceNo;

	@Column(length=45)
	private String receiptNo;

	@Column(precision=10, scale=2)
	private BigDecimal receivedAmount;

	//bi-directional many-to-one association to Branchpayment
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BranchPaymentId")
	private Branchpayment branchpayment;

	public Branchpaymentbreakdown() {
	}

	public int getBranchPaymentBreakdownId() {
		return this.branchPaymentBreakdownId;
	}

	public void setBranchPaymentBreakdownId(int branchPaymentBreakdownId) {
		this.branchPaymentBreakdownId = branchPaymentBreakdownId;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getChequeNo() {
		return this.chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}

	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getReceiptNo() {
		return this.receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public BigDecimal getReceivedAmount() {
		return this.receivedAmount;
	}

	public void setReceivedAmount(BigDecimal receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	public Branchpayment getBranchpayment() {
		return this.branchpayment;
	}

	public void setBranchpayment(Branchpayment branchpayment) {
		this.branchpayment = branchpayment;
	}

}