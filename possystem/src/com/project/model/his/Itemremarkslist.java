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
@Table(name="itemremarkslist")
public class Itemremarkslist implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true,nullable=false)
	private int id;
	
	@Column(length = 20)
	private String remarksName;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchID")
	private Branch branch;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getRemarksName() {
		return remarksName;
	}


	public void setRemarksName(String remarksName) {
		this.remarksName = remarksName;
	}


	public Branch getBranch() {
		return branch;
	}


	public void setBranch(Branch branch) {
		this.branch = branch;
	}
}
