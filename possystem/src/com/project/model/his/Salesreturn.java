package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the salesreturn database table.
 * 
 */
@Entity
@Table(name="salesreturn")
public class Salesreturn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int resturnid;

	@Column(length=50)
	private String approvedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date approvedDate;

	@Column(length=50)
	private String processBy;

	private int replacementQuantity;

	@Temporal(TemporalType.TIMESTAMP)
	private Date returndate;

	private int returnQuantity;

	@Column(length=1)
	private String status;

	//bi-directional many-to-one association to Salesorderbreakdown
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="salesOrderBreakdownId")
	private Salesorderbreakdown salesorderbreakdown;

	//bi-directional many-to-one association to Salesorderreturn
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="salesreturnid")
	private Salesorderreturn salesorderreturn;

	//bi-directional many-to-one association to Supplier
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="supplierId")
	private Supplier supplier;

	public Salesreturn() {
	}

	public int getResturnid() {
		return this.resturnid;
	}

	public void setResturnid(int resturnid) {
		this.resturnid = resturnid;
	}

	public String getApprovedBy() {
		return this.approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Date getApprovedDate() {
		return this.approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getProcessBy() {
		return this.processBy;
	}

	public void setProcessBy(String processBy) {
		this.processBy = processBy;
	}

	public int getReplacementQuantity() {
		return this.replacementQuantity;
	}

	public void setReplacementQuantity(int replacementQuantity) {
		this.replacementQuantity = replacementQuantity;
	}

	public Date getReturndate() {
		return this.returndate;
	}

	public void setReturndate(Date returndate) {
		this.returndate = returndate;
	}

	public int getReturnQuantity() {
		return this.returnQuantity;
	}

	public void setReturnQuantity(int returnQuantity) {
		this.returnQuantity = returnQuantity;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Salesorderbreakdown getSalesorderbreakdown() {
		return this.salesorderbreakdown;
	}

	public void setSalesorderbreakdown(Salesorderbreakdown salesorderbreakdown) {
		this.salesorderbreakdown = salesorderbreakdown;
	}

	public Salesorderreturn getSalesorderreturn() {
		return this.salesorderreturn;
	}

	public void setSalesorderreturn(Salesorderreturn salesorderreturn) {
		this.salesorderreturn = salesorderreturn;
	}

	public Supplier getSupplier() {
		return this.supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

}