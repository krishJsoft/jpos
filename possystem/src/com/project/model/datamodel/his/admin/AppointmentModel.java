package com.project.model.datamodel.his.admin;

import java.util.Date;


import com.project.model.datamodel.CustomerModel;

public class AppointmentModel {


	private int apointment_id;

	private Date appointment_Date;

	private Date appointment_Validity_Date;

	private String comments;

	private String createDate;

	private String createUid;

	private String description;

	private String urgency;

	private String validity_Status;

	private String writeDate;

	private String writeUid;

	private DoctorModel doctor;

	private CustomerModel customer;
	
	private Integer branchRecordId;

	public int getApointment_id() {
		return apointment_id;
	}

	public void setApointment_id(int apointment_id) {
		this.apointment_id = apointment_id;
	}

	public Date getAppointment_Date() {
		return appointment_Date;
	}

	public void setAppointment_Date(Date appointment_Date) {
		this.appointment_Date = appointment_Date;
	}

	public Date getAppointment_Validity_Date() {
		return appointment_Validity_Date;
	}

	public void setAppointment_Validity_Date(Date appointment_Validity_Date) {
		this.appointment_Validity_Date = appointment_Validity_Date;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateUid() {
		return createUid;
	}

	public void setCreateUid(String createUid) {
		this.createUid = createUid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrgency() {
		return urgency;
	}

	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}

	public String getValidity_Status() {
		return validity_Status;
	}

	public void setValidity_Status(String validity_Status) {
		this.validity_Status = validity_Status;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public String getWriteUid() {
		return writeUid;
	}

	public void setWriteUid(String writeUid) {
		this.writeUid = writeUid;
	}

	public DoctorModel getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorModel doctor) {
		this.doctor = doctor;
	}

	public CustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

	public Integer getBranchRecordId() {
		return branchRecordId;
	}

	public void setBranchRecordId(Integer branchRecordId) {
		this.branchRecordId = branchRecordId;
	}
	
	
}
