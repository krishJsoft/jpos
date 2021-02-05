package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the poscounter database table.
 * 
 */
@Entity
@Table(name="poscounter")
public class Poscounter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int counterId;

	@Column(length=15)
	private String counterNo;

	@Column(precision=10, scale=2)
	private BigDecimal currentbalance;

	@Column(precision=10, scale=2)
	private BigDecimal openingbalance;

	@Column(length=1)
	private String status;
	
	@Column(length=1)
	private String branchtype;

	//bi-directional many-to-one association to Poscashtransaction
	@OneToMany(mappedBy="poscounter")
	private List<Poscashtransaction> poscashtransactions;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchRecordId")
	private Branch branch;

	public Poscounter() {
	}

	public int getCounterId() {
		return this.counterId;
	}

	public void setCounterId(int counterId) {
		this.counterId = counterId;
	}

	public String getCounterNo() {
		return this.counterNo;
	}

	public void setCounterNo(String counterNo) {
		this.counterNo = counterNo;
	}

	public BigDecimal getCurrentbalance() {
		return this.currentbalance;
	}

	public void setCurrentbalance(BigDecimal currentbalance) {
		this.currentbalance = currentbalance;
	}

	public BigDecimal getOpeningbalance() {
		return this.openingbalance;
	}

	public void setOpeningbalance(BigDecimal openingbalance) {
		this.openingbalance = openingbalance;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Poscashtransaction> getPoscashtransactions() {
		return this.poscashtransactions;
	}

	public void setPoscashtransactions(List<Poscashtransaction> poscashtransactions) {
		this.poscashtransactions = poscashtransactions;
	}

	public Poscashtransaction addPoscashtransaction(Poscashtransaction poscashtransaction) {
		getPoscashtransactions().add(poscashtransaction);
		poscashtransaction.setPoscounter(this);

		return poscashtransaction;
	}

	public Poscashtransaction removePoscashtransaction(Poscashtransaction poscashtransaction) {
		getPoscashtransactions().remove(poscashtransaction);
		poscashtransaction.setPoscounter(null);

		return poscashtransaction;
	}

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public String getBranchtype() {
		return branchtype;
	}

	public void setBranchtype(String branchtype) {
		this.branchtype = branchtype;
	}

	
}