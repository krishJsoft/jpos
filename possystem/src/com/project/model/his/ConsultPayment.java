package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the consult_payments database table.
 * 
 */
@Entity
@Table(name="consult_payments")
public class ConsultPayment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int consult_Payment_id;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	@Column(precision=10, scale=4)
	private BigDecimal fee_Charge;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fee_Date;

	@Column(precision=10, scale=4)
	private BigDecimal fee_Due;

	@Column(precision=10, scale=4)
	private BigDecimal fee_Paid;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchId")
	private Branch branch;

	//bi-directional many-to-one association to Consultation
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Consultation_id")
	private Consultation consultation;

	//bi-directional many-to-one association to Customer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CustomerId")
	private Customer customer;

	public ConsultPayment() {
	}

	public int getConsult_Payment_id() {
		return this.consult_Payment_id;
	}

	public void setConsult_Payment_id(int consult_Payment_id) {
		this.consult_Payment_id = consult_Payment_id;
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

	public BigDecimal getFee_Charge() {
		return this.fee_Charge;
	}

	public void setFee_Charge(BigDecimal fee_Charge) {
		this.fee_Charge = fee_Charge;
	}

	public Date getFee_Date() {
		return this.fee_Date;
	}

	public void setFee_Date(Date fee_Date) {
		this.fee_Date = fee_Date;
	}

	public BigDecimal getFee_Due() {
		return this.fee_Due;
	}

	public void setFee_Due(BigDecimal fee_Due) {
		this.fee_Due = fee_Due;
	}

	public BigDecimal getFee_Paid() {
		return this.fee_Paid;
	}

	public void setFee_Paid(BigDecimal fee_Paid) {
		this.fee_Paid = fee_Paid;
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

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Consultation getConsultation() {
		return this.consultation;
	}

	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}