package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the autonum database table.
 * 
 */
@Entity
@Table(name = "hoteltablearea")
public class Hoteltablearea implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private int areaId;

	@Column(length = 25,nullable=false)
	private String areaName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branchId")
	private Branch branch;

	public Hoteltablearea() {
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

}