package com.project.model.datamodel;

public class RolefunctionlinkModel {

	private int idrolefunctionlink;
	private int iduserrole;
	private String rolename;
	private int idFunctions;
	private String functionname;

	public int getIdrolefunctionlink() {
		return idrolefunctionlink;
	}

	public void setIdrolefunctionlink(int idrolefunctionlink) {
		this.idrolefunctionlink = idrolefunctionlink;
	}

	public int getIduserrole() {
		return iduserrole;
	}

	public void setIduserrole(int iduserrole) {
		this.iduserrole = iduserrole;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public int getIdFunctions() {
		return idFunctions;
	}

	public void setIdFunctions(int idFunctions) {
		this.idFunctions = idFunctions;
	}

	public String getFunctionname() {
		return functionname;
	}

	public void setFunctionname(String functionname) {
		this.functionname = functionname;
	}

}
