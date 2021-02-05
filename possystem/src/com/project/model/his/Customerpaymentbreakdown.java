package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the customerpaymentbreakdown database table.
 * 
 */
@Entity
@Table(name="customerpaymentbreakdown")
public class Customerpaymentbreakdown implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int customerPaymentBreakdownId;

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

	@Column(length=45)
	private String salesOrderNo;

	//bi-directional many-to-one association to Customerpayment
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CustomerPaymentId")
	private Customerpayment customerpayment;

	public Customerpaymentbreakdown() {
	}

	public int getCustomerPaymentBreakdownId() {
		return this.customerPaymentBreakdownId;
	}

	public void setCustomerPaymentBreakdownId(int customerPaymentBreakdownId) {
		this.customerPaymentBreakdownId = customerPaymentBreakdownId;
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

	public String getSalesOrderNo() {
		return this.salesOrderNo;
	}

	public void setSalesOrderNo(String salesOrderNo) {
		this.salesOrderNo = salesOrderNo;
	}

	public Customerpayment getCustomerpayment() {
		return this.customerpayment;
	}

	public void setCustomerpayment(Customerpayment customerpayment) {
		this.customerpayment = customerpayment;
	}

}