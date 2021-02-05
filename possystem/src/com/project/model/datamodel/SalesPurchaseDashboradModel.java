package com.project.model.datamodel;

import java.math.BigDecimal;
import java.util.Date;

public class SalesPurchaseDashboradModel {

	private BigDecimal subTotal;
	private BigDecimal purcahsesubTotal;
	String monthName;
	Integer monthNo;
	private BigDecimal quantity;
	private BigDecimal originalTax;
	private BigDecimal duplicateTax;
	Date startDate;
	Date endDate;
	private BigDecimal expensesAmmount;
	
	private BigDecimal creditamount = new BigDecimal(0.00);
	
	private BigDecimal debitAmount = new BigDecimal(0.00);
	
	private BigDecimal salesamount = new BigDecimal(0.00);
	
	private BigDecimal totalAmount = new BigDecimal(0.00);

	private BigDecimal balanceAmount = new BigDecimal(0.00);

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	public Integer getMonthNo() {
		return monthNo;
	}

	public void setMonthNo(Integer monthNo) {
		this.monthNo = monthNo;
	}

	public BigDecimal getPurcahsesubTotal() {
		return purcahsesubTotal;
	}

	public void setPurcahsesubTotal(BigDecimal purcahsesubTotal) {
		this.purcahsesubTotal = purcahsesubTotal;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getOriginalTax() {
		return originalTax;
	}

	public void setOriginalTax(BigDecimal originalTax) {
		this.originalTax = originalTax;
	}

	public BigDecimal getDuplicateTax() {
		return duplicateTax;
	}

	public void setDuplicateTax(BigDecimal duplicateTax) {
		this.duplicateTax = duplicateTax;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getExpensesAmmount() {
		return expensesAmmount;
	}

	public void setExpensesAmmount(BigDecimal expensesAmmount) {
		this.expensesAmmount = expensesAmmount;
	}

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
	
}
