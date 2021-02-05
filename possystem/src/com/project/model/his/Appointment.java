package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the appointments database table.
 * 
 */
@Entity
@Table(name="appointments")
public class Appointment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int apointment_id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date appointment_Date;

	@Temporal(TemporalType.TIMESTAMP)
	private Date appointment_Validity_Date;

	@Lob
	private String comments;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	@Column(nullable=false, length=64)
	private String description;

	@Column(length=16)
	private String urgency;

	@Column(length=16)
	private String validity_Status;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchId")
	private Branch branch;

	//bi-directional many-to-one association to Doctor
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Doctor_Id")
	private Doctor doctor;

	//bi-directional many-to-one association to Customer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CustomerId")
	private Customer customer;

	public Appointment() {
	}

	public int getApointment_id() {
		return this.apointment_id;
	}

	public void setApointment_id(int apointment_id) {
		this.apointment_id = apointment_id;
	}

	public Date getAppointment_Date() {
		return this.appointment_Date;
	}

	public void setAppointment_Date(Date appointment_Date) {
		this.appointment_Date = appointment_Date;
	}

	public Date getAppointment_Validity_Date() {
		return this.appointment_Validity_Date;
	}

	public void setAppointment_Validity_Date(Date appointment_Validity_Date) {
		this.appointment_Validity_Date = appointment_Validity_Date;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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

	public String getUrgency() {
		return this.urgency;
	}

	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}

	public String getValidity_Status() {
		return this.validity_Status;
	}

	public void setValidity_Status(String validity_Status) {
		this.validity_Status = validity_Status;
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

	public Doctor getDoctor() {
		return this.doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}