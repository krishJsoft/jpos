package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the gstpurchaseaccountbreakdown database table.
 * 
 */
@Entity
@Table(name="gstpurchaseaccountbreakdown")
public class Gstpurchaseaccountbreakdown implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int gstpurchaseaccountbreakdownId;

	@Column(precision=10, scale=2)
	private BigDecimal tax;

	//bi-directional many-to-one association to Supplierinvoice
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SupplierInvoiceId")
	private Supplierinvoice supplierinvoice;

	//bi-directional many-to-one association to Gstaccount
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="accounttaxid")
	private Gstaccount gstaccount;

	public Gstpurchaseaccountbreakdown() {
	}

	public int getGstpurchaseaccountbreakdownId() {
		return this.gstpurchaseaccountbreakdownId;
	}

	public void setGstpurchaseaccountbreakdownId(int gstpurchaseaccountbreakdownId) {
		this.gstpurchaseaccountbreakdownId = gstpurchaseaccountbreakdownId;
	}

	public BigDecimal getTax() {
		return this.tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public Supplierinvoice getSupplierinvoice() {
		return this.supplierinvoice;
	}

	public void setSupplierinvoice(Supplierinvoice supplierinvoice) {
		this.supplierinvoice = supplierinvoice;
	}

	public Gstaccount getGstaccount() {
		return this.gstaccount;
	}

	public void setGstaccount(Gstaccount gstaccount) {
		this.gstaccount = gstaccount;
	}

}