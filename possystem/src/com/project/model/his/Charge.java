package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the charges database table.
 * 
 */
@Entity
@Table(name="charges")
public class Charge implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int charge_Id;

	private double balance_Due;

	private double charge_Amount;

	@Column(nullable=false, length=200)
	private String charge_Document_Info;

	@Column(nullable=false, length=200)
	private String charge_Reason;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private double insurance_Balance;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchId")
	private Branch branch;

	//bi-directional many-to-one association to Invoice
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Invoice_ID")
	private Invoice invoice;

	public Charge() {
	}

	public int getCharge_Id() {
		return this.charge_Id;
	}

	public void setCharge_Id(int charge_Id) {
		this.charge_Id = charge_Id;
	}

	public double getBalance_Due() {
		return this.balance_Due;
	}

	public void setBalance_Due(double balance_Due) {
		this.balance_Due = balance_Due;
	}

	public double getCharge_Amount() {
		return this.charge_Amount;
	}

	public void setCharge_Amount(double charge_Amount) {
		this.charge_Amount = charge_Amount;
	}

	public String getCharge_Document_Info() {
		return this.charge_Document_Info;
	}

	public void setCharge_Document_Info(String charge_Document_Info) {
		this.charge_Document_Info = charge_Document_Info;
	}

	public String getCharge_Reason() {
		return this.charge_Reason;
	}

	public void setCharge_Reason(String charge_Reason) {
		this.charge_Reason = charge_Reason;
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

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getInsurance_Balance() {
		return this.insurance_Balance;
	}

	public void setInsurance_Balance(double insurance_Balance) {
		this.insurance_Balance = insurance_Balance;
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

	public Invoice getInvoice() {
		return this.invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

}