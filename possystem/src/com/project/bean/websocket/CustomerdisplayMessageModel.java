package com.project.bean.websocket;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CustomerdisplayMessageModel {
	
	String terminalName;
	String messageType;
	BigDecimal totalAmount;
	BigDecimal paidAmount;
	BigDecimal discountAmount;
	BigDecimal balanceAmount;
	BigDecimal totalBeforediscount;

	List<SalesbreakdowndisplayModel> salesbreakdown=new ArrayList<SalesbreakdowndisplayModel>();
	
	public String getTerminalName() {
		return terminalName;
	}

	public void setTerminalName(String terminalName) {
		this.terminalName = terminalName;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public BigDecimal getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public BigDecimal getTotalBeforediscount() {
		return totalBeforediscount;
	}

	public void setTotalBeforediscount(BigDecimal totalBeforediscount) {
		this.totalBeforediscount = totalBeforediscount;
	}

	public List<SalesbreakdowndisplayModel> getSalesbreakdown() {
		return salesbreakdown;
	}

	public void setSalesbreakdown(List<SalesbreakdowndisplayModel> salesbreakdown) {
		this.salesbreakdown = salesbreakdown;
	}

	
}
