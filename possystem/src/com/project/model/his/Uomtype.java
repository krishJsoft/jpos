package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the uomtype database table.
 * 
 */
@Entity
@Table(name="uomtype")
public class Uomtype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int UOMId;

	@Column(length=50)
	private String UOMName;
	
	@Column(length=150)
	private String description;

	public Uomtype() {
	}

	public int getUOMId() {
		return this.UOMId;
	}

	public void setUOMId(int UOMId) {
		this.UOMId = UOMId;
	}

	public String getUOMName() {
		return this.UOMName;
	}

	public void setUOMName(String UOMName) {
		this.UOMName = UOMName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}