package com.project.model.datamodel;

public class RolePrivilegesModel {
	
	private int functionId;
	private String functionName;
	private String moduleName;	
	private int roleId;
	private String roleName;	
	private int roleFunctionLinkId;
	private int masterfunctionId;
	
	// used while deleting a roleFunctionLink
	
	
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public int getFunctionId() {
		return functionId;
	}
	public void setFunctionId(int functionId) {
		this.functionId = functionId;
	}
	public int getRoleFunctionLinkId() {
		return roleFunctionLinkId;
	}
	public void setRoleFunctionLinkId(int roleFunctionLinkId) {
		this.roleFunctionLinkId = roleFunctionLinkId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getMasterfunctionId() {
		return masterfunctionId;
	}
	public void setMasterfunctionId(int masterfunctionId) {
		this.masterfunctionId = masterfunctionId;
	}
	
	
	
}