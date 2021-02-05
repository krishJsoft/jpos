package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the commissionbreakdown database table.
 * 
 */
@Entity
@Table(name="commissionbreakdown")
public class Commissionbreakdown implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int commissionbreakdownId;

	@Column(precision=10, scale=2)
	private BigDecimal amount;

	//bi-directional many-to-one association to Commission
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="commissionId")
	private Commission commission;

	//bi-directional many-to-one association to Doctorprescription
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="doctorPrescriptionId")
	private Doctorprescription doctorprescription;

	//bi-directional many-to-one association to Salesorder
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="salesOrderId")
	private Salesorder salesorder;

	public Commissionbreakdown() {
	}

	public int getCommissionbreakdownId() {
		return this.commissionbreakdownId;
	}

	public void setCommissionbreakdownId(int commissionbreakdownId) {
		this.commissionbreakdownId = commissionbreakdownId;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Commission getCommission() {
		return this.commission;
	}

	public void setCommission(Commission commission) {
		this.commission = commission;
	}

	public Doctorprescription getDoctorprescription() {
		return this.doctorprescription;
	}

	public void setDoctorprescription(Doctorprescription doctorprescription) {
		this.doctorprescription = doctorprescription;
	}

	public Salesorder getSalesorder() {
		return this.salesorder;
	}

	public void setSalesorder(Salesorder salesorder) {
		this.salesorder = salesorder;
	}

}