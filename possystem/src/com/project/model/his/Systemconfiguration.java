package com.project.model.his;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="systemconfiguration")
public class Systemconfiguration {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false)
	private Boolean kitchenOrder;
	
	@Column(nullable=false)
	private Boolean terminalKitchenOrder;
	
	@Column(nullable=false)
	private Boolean mobileKitchenOrder;
	
	@Column(nullable=false)
	private Boolean cashierKitchenOrder;
	
	@Column(nullable=false)
	private Boolean billPrinter;
	
	@Column(nullable=false)
	private Boolean paxNo;
	
	@Column(nullable=false,name="orderScreenProductSearchBy", columnDefinition="varchar(5) DEFAULT 'name'")
	private String orderScreenProductSearchBy;
	
	@Column(nullable=false,name="printMode", columnDefinition="varchar(10) DEFAULT 'single'")
	private String printMode;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchId")
	private Branch branch;


	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="systemtype")
	private Systemtype systemtype;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean getKitchenOrder() {
		return kitchenOrder;
	}

	public void setKitchenOrder(Boolean kitchenOrder) {
		this.kitchenOrder = kitchenOrder;
	}

	public Boolean getTerminalKitchenOrder() {
		return terminalKitchenOrder;
	}

	public void setTerminalKitchenOrder(Boolean terminalKitchenOrder) {
		this.terminalKitchenOrder = terminalKitchenOrder;
	}

	public Boolean getMobileKitchenOrder() {
		return mobileKitchenOrder;
	}

	public void setMobileKitchenOrder(Boolean mobileKitchenOrder) {
		this.mobileKitchenOrder = mobileKitchenOrder;
	}

	public Boolean getCashierKitchenOrder() {
		return cashierKitchenOrder;
	}

	public void setCashierKitchenOrder(Boolean cashierKitchenOrder) {
		this.cashierKitchenOrder = cashierKitchenOrder;
	}

	public Boolean getBillPrinter() {
		return billPrinter;
	}

	public void setBillPrinter(Boolean billPrinter) {
		this.billPrinter = billPrinter;
	}

	public Boolean getPaxNo() {
		return paxNo;
	}

	public void setPaxNo(Boolean paxNo) {
		this.paxNo = paxNo;
	}

	public String getOrderScreenProductSearchBy() {
		return orderScreenProductSearchBy;
	}

	public void setOrderScreenProductSearchBy(String orderScreenProductSearchBy) {
		this.orderScreenProductSearchBy = orderScreenProductSearchBy;
	}

	public String getPrintMode() {
		return printMode;
	}

	public void setPrintMode(String printMode) {
		this.printMode = printMode;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Systemtype getSystemtype() {
		return systemtype;
	}

	public void setSystemtype(Systemtype systemtype) {
		this.systemtype = systemtype;
	}
	
	
}
