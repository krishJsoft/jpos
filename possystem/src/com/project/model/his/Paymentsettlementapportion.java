package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the paymentsettlementapportion database table.
 * 
 */
@Entity
@Table(name="paymentsettlementapportion")
public class Paymentsettlementapportion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int settlementInvoiceId;

	@Column(precision=10, scale=2)
	private BigDecimal allocatedAmount;

	@Column(length=45)
	private String allocatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date allocatedDate;

	//bi-directional many-to-one association to Supplierinvoice
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="invoiceId")
	private Supplierinvoice supplierinvoice;

	//bi-directional many-to-one association to Paymentsettlement
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="settlementId")
	private Paymentsettlement paymentsettlement;

	public Paymentsettlementapportion() {
	}

	public int getSettlementInvoiceId() {
		return this.settlementInvoiceId;
	}

	public void setSettlementInvoiceId(int settlementInvoiceId) {
		this.settlementInvoiceId = settlementInvoiceId;
	}

	public BigDecimal getAllocatedAmount() {
		return this.allocatedAmount;
	}

	public void setAllocatedAmount(BigDecimal allocatedAmount) {
		this.allocatedAmount = allocatedAmount;
	}

	public String getAllocatedBy() {
		return this.allocatedBy;
	}

	public void setAllocatedBy(String allocatedBy) {
		this.allocatedBy = allocatedBy;
	}

	public Date getAllocatedDate() {
		return this.allocatedDate;
	}

	public void setAllocatedDate(Date allocatedDate) {
		this.allocatedDate = allocatedDate;
	}

	public Supplierinvoice getSupplierinvoice() {
		return this.supplierinvoice;
	}

	public void setSupplierinvoice(Supplierinvoice supplierinvoice) {
		this.supplierinvoice = supplierinvoice;
	}

	public Paymentsettlement getPaymentsettlement() {
		return this.paymentsettlement;
	}

	public void setPaymentsettlement(Paymentsettlement paymentsettlement) {
		this.paymentsettlement = paymentsettlement;
	}

}