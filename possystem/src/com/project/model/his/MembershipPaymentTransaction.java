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
@Table(name="membershippaymenttransaction")
public class MembershipPaymentTransaction implements Serializable{
	private static long serialVersionUID=1L;


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true,nullable=false)
	private int id;
	
	@Column(precision = 10, scale = 2)
	private BigDecimal amount;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Column(length = 8)
	private String addedDuration;
	
	private int status;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="memberId")
	private Membership member;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="createdBy")
	private Branchstaffmember staff;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchId")
	private Branch branch;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getAddedDuration() {
		return addedDuration;
	}

	public void setAddedDuration(String addedDuration) {
		this.addedDuration = addedDuration;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Membership getMember() {
		return member;
	}

	public void setMember(Membership member) {
		this.member = member;
	}

	public Branchstaffmember getStaff() {
		return staff;
	}

	public void setStaff(Branchstaffmember staff) {
		this.staff = staff;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}
}
