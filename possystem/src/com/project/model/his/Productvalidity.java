package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the productvalidity database table.
 * 
 */
@Entity
@Table(name="productvalidity")
public class Productvalidity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int authenticationId;

	@Column(length=20)
	private String authenicationDate;

	@Column(length=150)
	private String authenticationKay;

	public Productvalidity() {
	}

	public int getAuthenticationId() {
		return this.authenticationId;
	}

	public void setAuthenticationId(int authenticationId) {
		this.authenticationId = authenticationId;
	}

	public String getAuthenicationDate() {
		return this.authenicationDate;
	}

	public void setAuthenicationDate(String authenicationDate) {
		this.authenicationDate = authenicationDate;
	}

	public String getAuthenticationKay() {
		return this.authenticationKay;
	}

	public void setAuthenticationKay(String authenticationKay) {
		this.authenticationKay = authenticationKay;
	}

}