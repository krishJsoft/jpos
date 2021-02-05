package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the functionmaster database table.
 * 
 */
@Entity
@Table(name="functionmaster")
public class Functionmaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int masterfunctionId;

	@Column(length=50)
	private String masterfunctionName;

	//bi-directional many-to-one association to Function
	@OneToMany(mappedBy="functionmaster")
	private List<Function> functions;

	public Functionmaster() {
	}

	public int getMasterfunctionId() {
		return this.masterfunctionId;
	}

	public void setMasterfunctionId(int masterfunctionId) {
		this.masterfunctionId = masterfunctionId;
	}

	public String getMasterfunctionName() {
		return this.masterfunctionName;
	}

	public void setMasterfunctionName(String masterfunctionName) {
		this.masterfunctionName = masterfunctionName;
	}

	public List<Function> getFunctions() {
		return this.functions;
	}

	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}

	public Function addFunction(Function function) {
		getFunctions().add(function);
		function.setFunctionmaster(this);

		return function;
	}

	public Function removeFunction(Function function) {
		getFunctions().remove(function);
		function.setFunctionmaster(null);

		return function;
	}

}