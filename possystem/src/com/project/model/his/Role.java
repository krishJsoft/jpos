package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@Table(name="role")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int roleId;

	@Column(length=45)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;

	@Column(length=150)
	private String roleDescription;

	@Column(length=45)
	private String roleName;

	//bi-directional many-to-one association to Branchstaffmember
	@OneToMany(mappedBy="role")
	private List<Branchstaffmember> branchstaffmembers;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchRecordId")
	private Branch branch;

	//bi-directional many-to-one association to Rolefunctionlink
	@OneToMany(mappedBy="role")
	private List<Rolefunctionlink> rolefunctionlinks;

	public Role() {
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
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

	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getRoleDescription() {
		return this.roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<Branchstaffmember> getBranchstaffmembers() {
		return this.branchstaffmembers;
	}

	public void setBranchstaffmembers(List<Branchstaffmember> branchstaffmembers) {
		this.branchstaffmembers = branchstaffmembers;
	}

	public Branchstaffmember addBranchstaffmember(Branchstaffmember branchstaffmember) {
		getBranchstaffmembers().add(branchstaffmember);
		branchstaffmember.setRole(this);

		return branchstaffmember;
	}

	public Branchstaffmember removeBranchstaffmember(Branchstaffmember branchstaffmember) {
		getBranchstaffmembers().remove(branchstaffmember);
		branchstaffmember.setRole(null);

		return branchstaffmember;
	}

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public List<Rolefunctionlink> getRolefunctionlinks() {
		return this.rolefunctionlinks;
	}

	public void setRolefunctionlinks(List<Rolefunctionlink> rolefunctionlinks) {
		this.rolefunctionlinks = rolefunctionlinks;
	}

	public Rolefunctionlink addRolefunctionlink(Rolefunctionlink rolefunctionlink) {
		getRolefunctionlinks().add(rolefunctionlink);
		rolefunctionlink.setRole(this);

		return rolefunctionlink;
	}

	public Rolefunctionlink removeRolefunctionlink(Rolefunctionlink rolefunctionlink) {
		getRolefunctionlinks().remove(rolefunctionlink);
		rolefunctionlink.setRole(null);

		return rolefunctionlink;
	}

}