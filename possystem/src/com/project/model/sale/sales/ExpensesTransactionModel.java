package com.project.model.sale.sales;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.BranchstaffmemberModel;

public class ExpensesTransactionModel {

	private int expTransId;
	
	private BigDecimal ammount;
	
	private Date createdDate;
	
	private String supplierResistNo;
	
	private Date supplierResitDate;
	
	private int expNameID;
	
	private int staffID;
	
	private int branchID;
	
	private int activeStatus;
	
	ExpensesListModel expList=new ExpensesListModel();
	
	BranchstaffmemberModel branchStaff=new BranchstaffmemberModel();
	
	BranchstaffmemberModel staffCreditedTo=new BranchstaffmemberModel();
	
	
	BranchModel branch=new BranchModel();
	
	PoscounterModel poscounter;


	public int getExpTransId() {
		return expTransId;
	}

	public void setExpTransId(int expTransId) {
		this.expTransId = expTransId;
	}

	public BigDecimal getAmmount() {
		return ammount;
	}

	public void setAmmount(BigDecimal ammount) {
		this.ammount = ammount;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getSupplierResistNo() {
		return supplierResistNo;
	}

	public void setSupplierResistNo(String supplierResistNo) {
		this.supplierResistNo = supplierResistNo;
	}

	public Date getSupplierResitDate() {
		return supplierResitDate;
	}

	public void setSupplierResitDate(Date supplierResitDate) {
		this.supplierResitDate = supplierResitDate;
	}

	public BranchstaffmemberModel getBranchStaff() {
		return branchStaff;
	}

	public void setBranchStaff(BranchstaffmemberModel branchStaff) {
		this.branchStaff = branchStaff;
	}

	public ExpensesListModel getExpList() {
		return expList;
	}

	public void setExpList(ExpensesListModel expList) {
		this.expList = expList;
	}

	public BranchModel getBranch() {
		return branch;
	}

	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}

	public int getExpNameID() {
		return expNameID;
	}

	public void setExpNameID(int expNameID) {
		this.expNameID = expNameID;
	}

	public int getStaffID() {
		return staffID;
	}

	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}

	public BranchstaffmemberModel getStaffCreditedTo() {
		return staffCreditedTo;
	}

	public void setStaffCreditedTo(BranchstaffmemberModel staffCreditedTo) {
		this.staffCreditedTo = staffCreditedTo;
	}

	public int getBranchID() {
		return branchID;
	}

	public void setBranchID(int branchID) {
		this.branchID = branchID;
	}

	public int getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}

	public PoscounterModel getPoscounter() {
		return poscounter;
	}

	public void setPoscounter(PoscounterModel poscounter) {
		this.poscounter = poscounter;
	}
	
	
	
}
