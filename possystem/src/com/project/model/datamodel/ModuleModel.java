package com.project.model.datamodel;

import java.util.ArrayList;
import java.util.List;

public class ModuleModel {

	private int idModule;
	private String modulename;

	private String moduleMasterName;
	private int moduleMasterId;

	List<MasterFunctionModel> masterFunctionModel = new ArrayList<MasterFunctionModel>();

	public int getIdModule() {
		return idModule;
	}

	public void setIdModule(int idModule) {
		this.idModule = idModule;
	}

	public String getModulename() {
		return modulename;
	}

	public void setModulename(String modulename) {
		this.modulename = modulename;
	}

	public List<MasterFunctionModel> getMasterFunctionModel() {
		return masterFunctionModel;
	}

	public void setMasterFunctionModel(
			List<MasterFunctionModel> masterFunctionModel) {
		this.masterFunctionModel = masterFunctionModel;
	}

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

}
