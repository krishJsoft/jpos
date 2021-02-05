package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the branchtransfer database table.
 * 
 */
@Entity
@Table(name = "branchtransfer")
public class Branchtransfer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private int branchtransferId;

	private int balanceQuantityCount;

	@Column(length = 1)
	private String branchStatus;

	@Column(length = 45)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifedDate;

	@Column(length = 45)
	private String transferNo;

	@Column(length = 400)
	private String remarks;

	private int soldQuantityCount;

	@Column(length = 1)
	private String status;

	private int totalItemCount;

	@Temporal(TemporalType.TIMESTAMP)
	private Date transferDate;

	// bi-directional many-to-one association to Branch
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BranchId")
	private Branch branch1;

	// bi-directional many-to-one association to Branch
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branchRecordId")
	private Branch branch2;

	// bi-directional many-to-one association to Branchtransferbreakdown
	@OneToMany(mappedBy = "branchtransfer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Branchtransferbreakdown> branchtransferbreakdowns;

	public Branchtransfer() {
	}

	public int getBranchtransferId() {
		return this.branchtransferId;
	}

	public void setBranchtransferId(int branchtransferId) {
		this.branchtransferId = branchtransferId;
	}

	public int getBalanceQuantityCount() {
		return this.balanceQuantityCount;
	}

	public void setBalanceQuantityCount(int balanceQuantityCount) {
		this.balanceQuantityCount = balanceQuantityCount;
	}

	public Branch getBranch1() {
		return branch1;
	}

	public void setBranch1(Branch branch1) {
		this.branch1 = branch1;
	}

	public Branch getBranch2() {
		return branch2;
	}

	public void setBranch2(Branch branch2) {
		this.branch2 = branch2;
	}

	public String getBranchStatus() {
		return this.branchStatus;
	}

	public void setBranchStatus(String branchStatus) {
		this.branchStatus = branchStatus;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifedDate() {
		return this.lastModifedDate;
	}

	public void setLastModifedDate(Date lastModifedDate) {
		this.lastModifedDate = lastModifedDate;
	}

	public String getTransferNo() {
		return transferNo;
	}

	public void setTransferNo(String transferNo) {
		this.transferNo = transferNo;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getSoldQuantityCount() {
		return this.soldQuantityCount;
	}

	public void setSoldQuantityCount(int soldQuantityCount) {
		this.soldQuantityCount = soldQuantityCount;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public int getTotalItemCount() {
		return this.totalItemCount;
	}

	public void setTotalItemCount(int totalItemCount) {
		this.totalItemCount = totalItemCount;
	}

	public Date getTransferDate() {
		return this.transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

	public List<Branchtransferbreakdown> getBranchtransferbreakdowns() {
		return this.branchtransferbreakdowns;
	}

	public void setBranchtransferbreakdowns(
			List<Branchtransferbreakdown> branchtransferbreakdowns) {
		this.branchtransferbreakdowns = branchtransferbreakdowns;
	}

	public Branchtransferbreakdown addBranchtransferbreakdown(
			Branchtransferbreakdown branchtransferbreakdown) {
		getBranchtransferbreakdowns().add(branchtransferbreakdown);
		branchtransferbreakdown.setBranchtransfer(this);

		return branchtransferbreakdown;
	}

	public Branchtransferbreakdown removeBranchtransferbreakdown(
			Branchtransferbreakdown branchtransferbreakdown) {
		getBranchtransferbreakdowns().remove(branchtransferbreakdown);
		branchtransferbreakdown.setBranchtransfer(null);

		return branchtransferbreakdown;
	}

}