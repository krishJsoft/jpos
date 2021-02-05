package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the paymentcollectionapportion database table.
 * 
 */
@Entity
@Table(name="paymentcollectionapportion")
public class Paymentcollectionapportion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int collectionInvoiceId;

	@Column(precision=10, scale=2)
	private BigDecimal allocatedAmount;

	@Column(length=45)
	private String allocatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date allocatedDate;

	//bi-directional many-to-one association to Paymentcollection
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="collectionId")
	private Paymentcollection paymentcollection;

	//bi-directional many-to-one association to Branchinvoice
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="invoiceId")
	private Branchinvoice branchinvoice;

	public Paymentcollectionapportion() {
	}

	public int getCollectionInvoiceId() {
		return this.collectionInvoiceId;
	}

	public void setCollectionInvoiceId(int collectionInvoiceId) {
		this.collectionInvoiceId = collectionInvoiceId;
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

	public Paymentcollection getPaymentcollection() {
		return this.paymentcollection;
	}

	public void setPaymentcollection(Paymentcollection paymentcollection) {
		this.paymentcollection = paymentcollection;
	}

	public Branchinvoice getBranchinvoice() {
		return this.branchinvoice;
	}

	public void setBranchinvoice(Branchinvoice branchinvoice) {
		this.branchinvoice = branchinvoice;
	}

}