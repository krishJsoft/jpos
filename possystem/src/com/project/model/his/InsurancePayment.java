package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the insurance_payments database table.
 * 
 */
@Entity
@Table(name="insurance_payments")
public class InsurancePayment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int insurance_Payment_id;

	@Column(precision=10, scale=4)
	private BigDecimal amount_Insurance;

	@Column(precision=10, scale=4)
	private BigDecimal amount_Patient;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	@Column(length=50)
	private String description;

	@Column(length=50)
	private String fee_or_Cost;

	@Column(length=50)
	private String name;

	@Column(precision=10, scale=4)
	private BigDecimal percentage_paid_for_Insurance;

	@Column(precision=10, scale=4)
	private BigDecimal percentage_paid_for_Patient;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Insurance
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Insurance_ID", nullable=false)
	private Insurance insurance;

	public InsurancePayment() {
	}

	public int getInsurance_Payment_id() {
		return this.insurance_Payment_id;
	}

	public void setInsurance_Payment_id(int insurance_Payment_id) {
		this.insurance_Payment_id = insurance_Payment_id;
	}

	public BigDecimal getAmount_Insurance() {
		return this.amount_Insurance;
	}

	public void setAmount_Insurance(BigDecimal amount_Insurance) {
		this.amount_Insurance = amount_Insurance;
	}

	public BigDecimal getAmount_Patient() {
		return this.amount_Patient;
	}

	public void setAmount_Patient(BigDecimal amount_Patient) {
		this.amount_Patient = amount_Patient;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateUid() {
		return this.createUid;
	}

	public void setCreateUid(String createUid) {
		this.createUid = createUid;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFee_or_Cost() {
		return this.fee_or_Cost;
	}

	public void setFee_or_Cost(String fee_or_Cost) {
		this.fee_or_Cost = fee_or_Cost;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPercentage_paid_for_Insurance() {
		return this.percentage_paid_for_Insurance;
	}

	public void setPercentage_paid_for_Insurance(BigDecimal percentage_paid_for_Insurance) {
		this.percentage_paid_for_Insurance = percentage_paid_for_Insurance;
	}

	public BigDecimal getPercentage_paid_for_Patient() {
		return this.percentage_paid_for_Patient;
	}

	public void setPercentage_paid_for_Patient(BigDecimal percentage_paid_for_Patient) {
		this.percentage_paid_for_Patient = percentage_paid_for_Patient;
	}

	public String getWriteDate() {
		return this.writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public String getWriteUid() {
		return this.writeUid;
	}

	public void setWriteUid(String writeUid) {
		this.writeUid = writeUid;
	}

	public Insurance getInsurance() {
		return this.insurance;
	}

	public void setInsurance(Insurance insurance) {
		this.insurance = insurance;
	}

}