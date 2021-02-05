package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rolefunctionlink database table.
 * 
 */
@Entity
@Table(name="rolefunctionlink")
public class Rolefunctionlink implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int roleFunctionLinkId;

	//bi-directional many-to-one association to Function
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FunctionId")
	private Function function;

	//bi-directional many-to-one association to Role
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="RoleId")
	private Role role;

	public Rolefunctionlink() {
	}

	public int getRoleFunctionLinkId() {
		return this.roleFunctionLinkId;
	}

	public void setRoleFunctionLinkId(int roleFunctionLinkId) {
		this.roleFunctionLinkId = roleFunctionLinkId;
	}

	public Function getFunction() {
		return this.function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}