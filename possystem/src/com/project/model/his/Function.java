package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the functions database table.
 * 
 */
@Entity
@Table(name="functions")
public class Function implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int functionId;

	@Column(length=45)
	private String functionName;

	//bi-directional many-to-one association to Module
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ModuleId")
	private Module module;

	//bi-directional many-to-one association to Functionmaster
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MasterfunctionId")
	private Functionmaster functionmaster;

	//bi-directional many-to-one association to Rolefunctionlink
	@OneToMany(mappedBy="function")
	private List<Rolefunctionlink> rolefunctionlinks;

	public Function() {
	}

	public int getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(int functionId) {
		this.functionId = functionId;
	}

	public String getFunctionName() {
		return this.functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public Module getModule() {
		return this.module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Functionmaster getFunctionmaster() {
		return this.functionmaster;
	}

	public void setFunctionmaster(Functionmaster functionmaster) {
		this.functionmaster = functionmaster;
	}

	public List<Rolefunctionlink> getRolefunctionlinks() {
		return this.rolefunctionlinks;
	}

	public void setRolefunctionlinks(List<Rolefunctionlink> rolefunctionlinks) {
		this.rolefunctionlinks = rolefunctionlinks;
	}

	public Rolefunctionlink addRolefunctionlink(Rolefunctionlink rolefunctionlink) {
		getRolefunctionlinks().add(rolefunctionlink);
		rolefunctionlink.setFunction(this);

		return rolefunctionlink;
	}

	public Rolefunctionlink removeRolefunctionlink(Rolefunctionlink rolefunctionlink) {
		getRolefunctionlinks().remove(rolefunctionlink);
		rolefunctionlink.setFunction(null);

		return rolefunctionlink;
	}

}