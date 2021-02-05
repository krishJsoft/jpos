package com.project.model.datamodel;

import java.util.Date;



public class UserroleModel {

	private int iduserrole;
	private String createdBy;
	private Date createdDate;
	private Date lastmodifyDate;
	private String roledescription;
	private String rolename;

	public int getIduserrole() {
		return iduserrole;
	}

	public void setIduserrole(int iduserrole) {
		this.iduserrole = iduserrole;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastmodifyDate() {
		return lastmodifyDate;
	}

	public void setLastmodifyDate(Date lastmodifyDate) {
		this.lastmodifyDate = lastmodifyDate;
	}

	public String getRoledescription() {
		return roledescription;
	}

	public void setRoledescription(String roledescription) {
		this.roledescription = roledescription;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

}
