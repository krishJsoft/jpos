package com.project.model.datamodel;

import java.util.ArrayList;
import java.util.List;

public class RolePrivilegesDynamicModel {

	private String moduleName;
	private String moduleId;

	List<RolePrivilegesModel> rolePrivilegesModelList = new ArrayList<RolePrivilegesModel>();

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public List<RolePrivilegesModel> getRolePrivilegesModelList() {
		return rolePrivilegesModelList;
	}

	public void setRolePrivilegesModelList(
			List<RolePrivilegesModel> rolePrivilegesModelList) {
		this.rolePrivilegesModelList = rolePrivilegesModelList;
	}

}
