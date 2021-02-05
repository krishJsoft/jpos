package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the commissions database table.
 * 
 */
@Entity
@Table(name="commissions")
public class Commission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int commissionId;

	@Column(precision=10, scale=2)
	private BigDecimal commisionAmount;

	@Temporal(TemporalType.DATE)
	private Date commisionDate;

	@Column(length=20)
	private String commissionPeriod;

	@Column(length=1)
	private String commissionType;

	@Column(length=1)
	private String status;

	//bi-directional many-to-one association to Commissionbreakdown
	@OneToMany(mappedBy="commission", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Commissionbreakdown> commissionbreakdowns;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchRecordId")
	private Branch branch;

	//bi-directional many-to-one association to Branchstaffmember
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="staffId")
	private Branchstaffmember branchstaffmember;

	public Commission() {
	}

	public int getCommissionId() {
		return this.commissionId;
	}

	public void setCommissionId(int commissionId) {
		this.commissionId = commissionId;
	}

	public BigDecimal getCommisionAmount() {
		return this.commisionAmount;
	}

	public void setCommisionAmount(BigDecimal commisionAmount) {
		this.commisionAmount = commisionAmount;
	}

	public Date getCommisionDate() {
		return this.commisionDate;
	}

	public void setCommisionDate(Date commisionDate) {
		this.commisionDate = commisionDate;
	}

	public String getCommissionPeriod() {
		return this.commissionPeriod;
	}

	public void setCommissionPeriod(String commissionPeriod) {
		this.commissionPeriod = commissionPeriod;
	}

	public String getCommissionType() {
		return this.commissionType;
	}

	public void setCommissionType(String commissionType) {
		this.commissionType = commissionType;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Commissionbreakdown> getCommissionbreakdowns() {
		return this.commissionbreakdowns;
	}

	public void setCommissionbreakdowns(List<Commissionbreakdown> commissionbreakdowns) {
		this.commissionbreakdowns = commissionbreakdowns;
	}

	public Commissionbreakdown addCommissionbreakdown(Commissionbreakdown commissionbreakdown) {
		getCommissionbreakdowns().add(commissionbreakdown);
		commissionbreakdown.setCommission(this);

		return commissionbreakdown;
	}

	public Commissionbreakdown removeCommissionbreakdown(Commissionbreakdown commissionbreakdown) {
		getCommissionbreakdowns().remove(commissionbreakdown);
		commissionbreakdown.setCommission(null);

		return commissionbreakdown;
	}

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Branchstaffmember getBranchstaffmember() {
		return this.branchstaffmember;
	}

	public void setBranchstaffmember(Branchstaffmember branchstaffmember) {
		this.branchstaffmember = branchstaffmember;
	}

}