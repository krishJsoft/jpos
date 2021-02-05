package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the modulemaster database table.
 * 
 */
@Entity
@Table(name="modulemaster")
public class Modulemaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int mastermoduleId;

	@Column(length=100)
	private String mastermoduleName;

	//bi-directional many-to-one association to Module
	@OneToMany(mappedBy="modulemaster")
	private List<Module> modules;

	public Modulemaster() {
	}

	public int getMastermoduleId() {
		return this.mastermoduleId;
	}

	public void setMastermoduleId(int mastermoduleId) {
		this.mastermoduleId = mastermoduleId;
	}

	public String getMastermoduleName() {
		return this.mastermoduleName;
	}

	public void setMastermoduleName(String mastermoduleName) {
		this.mastermoduleName = mastermoduleName;
	}

	public List<Module> getModules() {
		return this.modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public Module addModule(Module module) {
		getModules().add(module);
		module.setModulemaster(this);

		return module;
	}

	public Module removeModule(Module module) {
		getModules().remove(module);
		module.setModulemaster(null);

		return module;
	}

}