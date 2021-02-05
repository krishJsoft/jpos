package com.project.model.his;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the salesorderreturn database table.
 * 
 */
@Entity
@Table(name="salesorderreturn")
public class Salesorderreturn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int salesreturnid;

	@Temporal(TemporalType.TIMESTAMP)
	private Date laetupdatedDate;

	@Column(length=45)
	private String replacementNo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date returndate;

	@Column(length=45)
	private String salesOrderNo;

	@Column(length=1)
	private String status;

	@Column(length=1)
	private String supplierstatus;

	@Column(length=45)
	private String updatedBy;
	
	@Column(precision=10, scale=2)
	private BigDecimal totalAmount;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchRecordId")
	private Branch branch;

	//bi-directional many-to-one association to Salesreturn
	@OneToMany(mappedBy="salesorderreturn", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Salesreturn> salesreturns;

	public Salesorderreturn() {
	}

	public int getSalesreturnid() {
		return this.salesreturnid;
	}

	public void setSalesreturnid(int salesreturnid) {
		this.salesreturnid = salesreturnid;
	}

	public Date getLaetupdatedDate() {
		return this.laetupdatedDate;
	}

	public void setLaetupdatedDate(Date laetupdatedDate) {
		this.laetupdatedDate = laetupdatedDate;
	}

	public String getReplacementNo() {
		return this.replacementNo;
	}

	public void setReplacementNo(String replacementNo) {
		this.replacementNo = replacementNo;
	}

	public Date getReturndate() {
		return this.returndate;
	}

	public void setReturndate(Date returndate) {
		this.returndate = returndate;
	}

	public String getSalesOrderNo() {
		return this.salesOrderNo;
	}

	public void setSalesOrderNo(String salesOrderNo) {
		this.salesOrderNo = salesOrderNo;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSupplierstatus() {
		return this.supplierstatus;
	}

	public void setSupplierstatus(String supplierstatus) {
		this.supplierstatus = supplierstatus;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public List<Salesreturn> getSalesreturns() {
		return this.salesreturns;
	}

	public void setSalesreturns(List<Salesreturn> salesreturns) {
		this.salesreturns = salesreturns;
	}

	public Salesreturn addSalesreturn(Salesreturn salesreturn) {
		getSalesreturns().add(salesreturn);
		salesreturn.setSalesorderreturn(this);

		return salesreturn;
	}

	public Salesreturn removeSalesreturn(Salesreturn salesreturn) {
		getSalesreturns().remove(salesreturn);
		salesreturn.setSalesorderreturn(null);

		return salesreturn;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	
}