package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the designation database table.
 * 
 */
@Entity
@Table(name="designation")
public class Designation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int iddesignation;

	@Column(length=100)
	private String designationname;

	

	public Designation() {
	}

	public int getIddesignation() {
		return this.iddesignation;
	}

	public void setIddesignation(int iddesignation) {
		this.iddesignation = iddesignation;
	}

	public String getDesignationname() {
		return this.designationname;
	}

	public void setDesignationname(String designationname) {
		this.designationname = designationname;
	}

	
}