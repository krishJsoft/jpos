package com.project.model.datamodel;

import java.util.ArrayList;
import java.util.List;

public class ModuleMasterFunctionModel {

	private String moduleMasterName;
	private int moduleMasterId;

	List<MasterFunctionModel> functions = new ArrayList<MasterFunctionModel>();

	public String getModuleMasterName() {
		return moduleMasterName;
	}

	public void setModuleMasterName(String moduleMasterName) {
		this.moduleMasterName = moduleMasterName;
	}

	public int getModuleMasterId() {
		return moduleMasterId;
	}

	public void setModuleMasterId(int moduleMasterId) {
		this.moduleMasterId = moduleMasterId;
	}

	public List<MasterFunctionModel> getFunctions() {
		return functions;
	}

	public void setFunctions(List<MasterFunctionModel> functions) {
		this.functions = functions;
	}

}
