package com.project.model.payment.settlement;

import java.math.BigDecimal;
import java.util.Date;

import com.project.model.invoice.supplier.SupplierinvoiceModel;

public class PaymentsettlementapportionModel {

	private int settlementInvoiceId;

	private BigDecimal allocatedAmount;

	private String allocatedBy;

	private Date allocatedDate;

	private String invoiceNo;
	
	SupplierinvoiceModel supplierinvoice;

	PaymentsettlementModel paymentsettlement;

	public int getSettlementInvoiceId() {
		return settlementInvoiceId;
	}

	public void setSettlementInvoiceId(int settlementInvoiceId) {
		this.settlementInvoiceId = settlementInvoiceId;
	}

	public BigDecimal getAllocatedAmount() {
		return allocatedAmount;
	}

	public void setAllocatedAmount(BigDecimal allocatedAmount) {
		this.allocatedAmount = allocatedAmount;
	}

	public String getAllocatedBy() {
		return allocatedBy;
	}

	public void setAllocatedBy(String allocatedBy) {
		this.allocatedBy = allocatedBy;
	}

	public Date getAllocatedDate() {
		return allocatedDate;
	}

	public void setAllocatedDate(Date allocatedDate) {
		this.allocatedDate = allocatedDate;
	}

	public SupplierinvoiceModel getSupplierinvoice() {
		return supplierinvoice;
	}

	public void setSupplierinvoice(SupplierinvoiceModel supplierinvoice) {
		this.supplierinvoice = supplierinvoice;
	}

	public PaymentsettlementModel getPaymentsettlement() {
		return paymentsettlement;
	}

	public void setPaymentsettlement(PaymentsettlementModel paymentsettlement) {
		this.paymentsettlement = paymentsettlement;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	
}
