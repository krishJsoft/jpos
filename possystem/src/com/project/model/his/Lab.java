package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the labs database table.
 * 
 */
@Entity
@Table(name="labs")
public class Lab implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int lab_id;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date_Analysis;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date_Requested;

	@Column(length=128)
	private String description;

	private int pathologist;

	private int requestor;

	@Lob
	private String results;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Diagno
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Diagno_Id")
	private Diagno diagno;

	//bi-directional many-to-one association to Doctor
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Doctor_Id")
	private Doctor doctor;

	//bi-directional many-to-one association to Exam
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Exam_id")
	private Exam exam;

	//bi-directional many-to-one association to Customer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CustomerId")
	private Customer customer;

	public Lab() {
	}

	public int getLab_id() {
		return this.lab_id;
	}

	public void setLab_id(int lab_id) {
		this.lab_id = lab_id;
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

	public Date getDate_Analysis() {
		return this.date_Analysis;
	}

	public void setDate_Analysis(Date date_Analysis) {
		this.date_Analysis = date_Analysis;
	}

	public Date getDate_Requested() {
		return this.date_Requested;
	}

	public void setDate_Requested(Date date_Requested) {
		this.date_Requested = date_Requested;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPathologist() {
		return this.pathologist;
	}

	public void setPathologist(int pathologist) {
		this.pathologist = pathologist;
	}

	public int getRequestor() {
		return this.requestor;
	}

	public void setRequestor(int requestor) {
		this.requestor = requestor;
	}

	public String getResults() {
		return this.results;
	}

	public void setResults(String results) {
		this.results = results;
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

	public Diagno getDiagno() {
		return this.diagno;
	}

	public void setDiagno(Diagno diagno) {
		this.diagno = diagno;
	}

	public Doctor getDoctor() {
		return this.doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Exam getExam() {
		return this.exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}