package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the gstsalesaccountbreakdown database table.
 * 
 */
@Entity
@Table(name="gstsalesaccountbreakdown")
public class Gstsalesaccountbreakdown implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int gstsalesaccountbreakdownid;

	@Column(precision=10, scale=2)
	private BigDecimal tax;

	//bi-directional many-to-one association to Gstaccount
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="accounttaxid")
	private Gstaccount gstaccount;

	//bi-directional many-to-one association to Branchinvoice
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BranchInvoiceId")
	private Branchinvoice branchinvoice;

	public Gstsalesaccountbreakdown() {
	}

	public int getGstsalesaccountbreakdownid() {
		return this.gstsalesaccountbreakdownid;
	}

	public void setGstsalesaccountbreakdownid(int gstsalesaccountbreakdownid) {
		this.gstsalesaccountbreakdownid = gstsalesaccountbreakdownid;
	}

	public BigDecimal getTax() {
		return this.tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public Gstaccount getGstaccount() {
		return this.gstaccount;
	}

	public void setGstaccount(Gstaccount gstaccount) {
		this.gstaccount = gstaccount;
	}

	public Branchinvoice getBranchinvoice() {
		return this.branchinvoice;
	}

	public void setBranchinvoice(Branchinvoice branchinvoice) {
		this.branchinvoice = branchinvoice;
	}

}