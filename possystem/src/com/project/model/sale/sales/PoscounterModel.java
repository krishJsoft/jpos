package com.project.model.sale.sales;

import java.math.BigDecimal;
import java.util.List;

public class PoscounterModel {

	private int counterId;

	private String counterNo;

	private BigDecimal currentbalance;

	private BigDecimal openingbalance;

	private String status;
	
	private int branchId;

	List<PoscashtransactionModel> poscashtransactions;

	public int getCounterId() {
		return counterId;
	}

	public void setCounterId(int counterId) {
		this.counterId = counterId;
	}

	public String getCounterNo() {
		return counterNo;
	}

	public void setCounterNo(String counterNo) {
		this.counterNo = counterNo;
	}

	public BigDecimal getCurrentbalance() {
		return currentbalance;
	}

	public void setCurrentbalance(BigDecimal currentbalance) {
		this.currentbalance = currentbalance;
	}

	public BigDecimal getOpeningbalance() {
		return openingbalance;
	}

	public void setOpeningbalance(BigDecimal openingbalance) {
		this.openingbalance = openingbalance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<PoscashtransactionModel> getPoscashtransactions() {
		return poscashtransactions;
	}

	public void setPoscashtransactions(
			List<PoscashtransactionModel> poscashtransactions) {
		this.poscashtransactions = poscashtransactions;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	
	

}
