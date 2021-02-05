package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the invoices database table.
 * 
 */
@Entity
@Table(name="invoices")
public class Invoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int invoice_ID;

	private double balance;

	private double balance_Due_Invoice;

	private double billed;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date date;

	@Column(nullable=false, length=200)
	private String description;

	private Time duration;

	private double insurance_Balance;

	private double late_Fee;

	private double paid;

	private double patient_Total_Balance;

	private double payment;

	private double rate;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Charge
	@OneToMany(mappedBy="invoice")
	private List<Charge> charges;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchId")
	private Branch branch;

	//bi-directional many-to-one association to Insurance
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Insurance_ID")
	private Insurance insurance;

	//bi-directional many-to-one association to Customer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CustomerId")
	private Customer customer;

	//bi-directional many-to-one association to Payment
	@OneToMany(mappedBy="invoice")
	private List<Payment> payments;

	public Invoice() {
	}

	public int getInvoice_ID() {
		return this.invoice_ID;
	}

	public void setInvoice_ID(int invoice_ID) {
		this.invoice_ID = invoice_ID;
	}

	public double getBalance() {
		return this.balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getBalance_Due_Invoice() {
		return this.balance_Due_Invoice;
	}

	public void setBalance_Due_Invoice(double balance_Due_Invoice) {
		this.balance_Due_Invoice = balance_Due_Invoice;
	}

	public double getBilled() {
		return this.billed;
	}

	public void setBilled(double billed) {
		this.billed = billed;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Time getDuration() {
		return this.duration;
	}

	public void setDuration(Time duration) {
		this.duration = duration;
	}

	public double getInsurance_Balance() {
		return this.insurance_Balance;
	}

	public void setInsurance_Balance(double insurance_Balance) {
		this.insurance_Balance = insurance_Balance;
	}

	public double getLate_Fee() {
		return this.late_Fee;
	}

	public void setLate_Fee(double late_Fee) {
		this.late_Fee = late_Fee;
	}

	public double getPaid() {
		return this.paid;
	}

	public void setPaid(double paid) {
		this.paid = paid;
	}

	public double getPatient_Total_Balance() {
		return this.patient_Total_Balance;
	}

	public void setPatient_Total_Balance(double patient_Total_Balance) {
		this.patient_Total_Balance = patient_Total_Balance;
	}

	public double getPayment() {
		return this.payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	public double getRate() {
		return this.rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
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

	public List<Charge> getCharges() {
		return this.charges;
	}

	public void setCharges(List<Charge> charges) {
		this.charges = charges;
	}

	public Charge addCharge(Charge charge) {
		getCharges().add(charge);
		charge.setInvoice(this);

		return charge;
	}

	public Charge removeCharge(Charge charge) {
		getCharges().remove(charge);
		charge.setInvoice(null);

		return charge;
	}

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Insurance getInsurance() {
		return this.insurance;
	}

	public void setInsurance(Insurance insurance) {
		this.insurance = insurance;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Payment> getPayments() {
		return this.payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public Payment addPayment(Payment payment) {
		getPayments().add(payment);
		payment.setInvoice(this);

		return payment;
	}

	public Payment removePayment(Payment payment) {
		getPayments().remove(payment);
		payment.setInvoice(null);

		return payment;
	}

}