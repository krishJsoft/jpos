package com.project.model.payment.collection;

import java.math.BigDecimal;
import java.util.Date;

import com.project.model.invoice.branch.BranchinvoiceModel;

public class PaymentcollectionapportionModel {

	private int collectionInvoiceId;

	private BigDecimal allocatedAmount;

	private String allocatedBy;
	
	private String invoiceNo;

	private Date allocatedDate;

	private BranchinvoiceModel branchinvoice;

	private PaymentcollectionModel paymentcollection;

	public int getCollectionInvoiceId() {
		return collectionInvoiceId;
	}

	public void setCollectionInvoiceId(int collectionInvoiceId) {
		this.collectionInvoiceId = collectionInvoiceId;
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

	public BranchinvoiceModel getBranchinvoice() {
		return branchinvoice;
	}

	public void setBranchinvoice(BranchinvoiceModel branchinvoice) {
		this.branchinvoice = branchinvoice;
	}

	public PaymentcollectionModel getPaymentcollection() {
		return paymentcollection;
	}

	public void setPaymentcollection(PaymentcollectionModel paymentcollection) {
		this.paymentcollection = paymentcollection;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	
}
