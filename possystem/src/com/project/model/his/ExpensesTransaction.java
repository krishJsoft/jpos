package com.project.model.his;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="expensestransaction")
public class ExpensesTransaction implements Serializable{
	private static long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true,nullable=false)
	private int id;
	
	@Column(precision = 10, scale = 2)
	private BigDecimal ammount;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Column(length = 25)
	private String supplierResitNo;
	
	@Temporal(TemporalType.DATE)
	private Date suppResitDate;
	
	private int activeStatus;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchID")
	private Branch branch;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="expensesID")
	private ExpensesList expList;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userID")
	private Branchstaffmember staff;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="staffCreditedTo",nullable=true)
	private Branchstaffmember staffCreditedTo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "counterId")
	private Poscounter poscounter;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getSupplierResitNo() {
		return supplierResitNo;
	}

	public void setSupplierResitNo(String supplierResitNo) {
		this.supplierResitNo = supplierResitNo;
	}

	public Date getSuppResitDate() {
		return suppResitDate;
	}

	public void setSuppResitDate(Date supResitDate) {
		this.suppResitDate = supResitDate;
	}

	public int getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}


	public ExpensesList getExpList() {
		return expList;
	}

	public void setExpList(ExpensesList expList) {
		this.expList = expList;
	}

	public Branchstaffmember getStaff() {
		return staff;
	}

	public void setStaff(Branchstaffmember staff) {
		this.staff = staff;
	}

	public Branchstaffmember getStaffCreditedTo() {
		return staffCreditedTo;
	}

	public void setStaffCreditedTo(Branchstaffmember staffCreditedTo) {
		this.staffCreditedTo = staffCreditedTo;
	}

	public Poscounter getPoscounter() {
		return poscounter;
	}

	public void setPoscounter(Poscounter poscounter) {
		this.poscounter = poscounter;
	}


	
	

}
