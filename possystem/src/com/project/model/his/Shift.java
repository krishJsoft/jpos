package com.project.model.his;

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
@Table(name = "shift")
public class Shift {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private int id;
	
	@Column(length = 25)
	private String shiftName;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date shiftStart;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date shiftEnd;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BranchId")
	private Branch branch;
	
	public int getId() {
		return id;
	}

	public String getShiftName() {
		return shiftName;
	}

	public Date getShiftStart() {
		return shiftStart;
	}

	public Date getShiftEnd() {
		return shiftEnd;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}

	public void setShiftStart(Date shiftStart) {
		this.shiftStart = shiftStart;
	}

	public void setShiftEnd(Date shiftEnd) {
		this.shiftEnd = shiftEnd;
	}
	
	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}



}
