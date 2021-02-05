package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the payments database table.
 * 
 */
@Entity
@Table(name="payments")
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int payment_Id;

	private double balance_Due;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private double insurance_Balance;

	private double payment_Amount;

	@Column(nullable=false, length=200)
	private String payment_Document_Info;

	@Column(nullable=false, length=200)
	private String payment_Extra_Info;

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

	public Payment() {
	}

	public int getPayment_Id() {
		return this.payment_Id;
	}

	public void setPayment_Id(int payment_Id) {
		this.payment_Id = payment_Id;
	}

	public double getBalance_Due() {
		return this.balance_Due;
	}

	public void setBalance_Due(double balance_Due) {
		this.balance_Due = balance_Due;
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

	public double getPayment_Amount() {
		return this.payment_Amount;
	}

	public void setPayment_Amount(double payment_Amount) {
		this.payment_Amount = payment_Amount;
	}

	public String getPayment_Document_Info() {
		return this.payment_Document_Info;
	}

	public void setPayment_Document_Info(String payment_Document_Info) {
		this.payment_Document_Info = payment_Document_Info;
	}

	public String getPayment_Extra_Info() {
		return this.payment_Extra_Info;
	}

	public void setPayment_Extra_Info(String payment_Extra_Info) {
		this.payment_Extra_Info = payment_Extra_Info;
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