package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the modules database table.
 * 
 */
@Entity
@Table(name="modules")
public class Module implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int moduleId;

	@Column(length=45)
	private String moduleName;
	
	@Column(length=2)
	private String status;
	

	//bi-directional many-to-one association to Function
	@OneToMany(mappedBy="module")
	private List<Function> functions;

	//bi-directional many-to-one association to Modulemaster
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="matermoduleId")
	private Modulemaster modulemaster;

	public Module() {
	}

	public int getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public List<Function> getFunctions() {
		return this.functions;
	}

	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}

	public Function addFunction(Function function) {
		getFunctions().add(function);
		function.setModule(this);

		return function;
	}

	public Function removeFunction(Function function) {
		getFunctions().remove(function);
		function.setModule(null);

		return function;
	}

	public Modulemaster getModulemaster() {
		return this.modulemaster;
	}

	public void setModulemaster(Modulemaster modulemaster) {
		this.modulemaster = modulemaster;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}