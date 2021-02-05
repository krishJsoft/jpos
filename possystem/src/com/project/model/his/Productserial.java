package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the productserial database table.
 * 
 */
@Entity
@Table(name="productserial")
public class Productserial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=10)
	private String amount;

	@Column(length=20)
	private String serialno;

	public Productserial() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getSerialno() {
		return this.serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

}