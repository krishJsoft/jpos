package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the department database table.
 * 
 */
@Entity
@Table(name="department")
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int departmentId;

	@Column(length=15)
	private String departmentCode;

	@Column(length=145)
	private String departmentName;
	
	@Column(length=500)
	private String description;
	
	

	//bi-directional many-to-one association to Branchstaffmember
	@OneToMany(mappedBy="department")
	private List<Branchstaffmember> branchstaffmembers;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchRecordId")
	private Branch branch;

	public Department() {
	}

	public int getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentCode() {
		return this.departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public List<Branchstaffmember> getBranchstaffmembers() {
		return this.branchstaffmembers;
	}

	public void setBranchstaffmembers(List<Branchstaffmember> branchstaffmembers) {
		this.branchstaffmembers = branchstaffmembers;
	}

	public Branchstaffmember addBranchstaffmember(Branchstaffmember branchstaffmember) {
		getBranchstaffmembers().add(branchstaffmember);
		branchstaffmember.setDepartment(this);

		return branchstaffmember;
	}

	public Branchstaffmember removeBranchstaffmember(Branchstaffmember branchstaffmember) {
		getBranchstaffmembers().remove(branchstaffmember);
		branchstaffmember.setDepartment(null);

		return branchstaffmember;
	}

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}