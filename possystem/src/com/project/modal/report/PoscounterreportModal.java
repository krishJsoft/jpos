package com.project.modal.report;

import java.math.BigDecimal;
import java.util.Date;

import com.project.model.sale.sales.PoscounterModel;

public class PoscounterreportModal implements Comparable<PoscounterreportModal>{


	private BigDecimal creditamount = new BigDecimal(0.00);
	
	private BigDecimal debitAmount = new BigDecimal(0.00);
	
	private Date lastupdatedDate;
	
	private BigDecimal salesamount = new BigDecimal(0.00);
	
	private BigDecimal totalAmount = new BigDecimal(0.00);

	private BigDecimal balanceAmount = new BigDecimal(0.00);
	
	private BigDecimal expensesAmount = new BigDecimal(0.00);
	
	private BigDecimal receivedAmount = new BigDecimal(0.00);
	
	private String shiftName;

	private BigDecimal totalTax;

	public BigDecimal getCreditamount() {
		return creditamount;
	}

	public void setCreditamount(BigDecimal creditamount) {
		this.creditamount = creditamount;
	}

	public BigDecimal getDebitAmount() {
		return debitAmount;
	}

	public void setDebitAmount(BigDecimal debitAmount) {
		this.debitAmount = debitAmount;
	}

	public Date getLastupdatedDate() {
		return lastupdatedDate;
	}

	public void setLastupdatedDate(Date lastupdatedDate) {
		this.lastupdatedDate = lastupdatedDate;
	}

	public BigDecimal getSalesamount() {
		return salesamount;
	}

	public void setSalesamount(BigDecimal salesamount) {
		this.salesamount = salesamount;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public BigDecimal getReceivedAmount() {
		return receivedAmount;
	}

	public void setReceivedAmount(BigDecimal receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}

	public BigDecimal getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(BigDecimal totalTax) {
		this.totalTax = totalTax;
	}

	public BigDecimal getExpensesAmount() {
		return expensesAmount;
	}

	public void setExpensesAmount(BigDecimal expensesAmount) {
		this.expensesAmount = expensesAmount;
	}

	@Override
	public int compareTo(PoscounterreportModal arg0) {
	    return getLastupdatedDate().compareTo(arg0.getLastupdatedDate());
	}

}
