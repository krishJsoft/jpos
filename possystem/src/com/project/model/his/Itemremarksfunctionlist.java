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
@Table(name="itemremarksfunctionlist")
public class Itemremarksfunctionlist implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true,nullable=false)
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="remarksId")
	private Itemremarkslist remarks;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchID")
	private Branch branch;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId")
	private Productcategory productcategory;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Itemremarkslist getRemarks() {
		return remarks;
	}

	public void setRemarks(Itemremarkslist remarks) {
		this.remarks = remarks;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Productcategory getProductcategory() {
		return productcategory;
	}

	public void setProductcategory(Productcategory productcategory) {
		this.productcategory = productcategory;
	}
}
