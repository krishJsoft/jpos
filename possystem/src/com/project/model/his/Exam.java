package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the exams database table.
 * 
 */
@Entity
@Table(name="exams")
public class Exam implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int exam_id;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Column(length=50)
	private String description;

	private byte done_and_ready;

	@Column(length=50)
	private String reason;

	@Lob
	private String results;

	@Lob
	private byte[] scan_Image;

	@Column(length=50)
	private String status;

	@Lob
	private String summary;

	@Column(nullable=false, length=50)
	private String type;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Bodyregion
	@OneToMany(mappedBy="exam")
	private List<Bodyregion> bodyregions;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchId")
	private Branch branch;

	//bi-directional many-to-one association to Consultation
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Consultation_id")
	private Consultation consultation;

	//bi-directional many-to-one association to Diagno
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Diagno_Id")
	private Diagno diagno;

	//bi-directional many-to-one association to Doctor
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Doctor_Id")
	private Doctor doctor;

	//bi-directional many-to-one association to Customer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CustomerId")
	private Customer customer;

	//bi-directional many-to-one association to Examtype
	@OneToMany(mappedBy="exam")
	private List<Examtype> examtypes;

	//bi-directional many-to-one association to Lab
	@OneToMany(mappedBy="exam")
	private List<Lab> labs;

	public Exam() {
	}

	public int getExam_id() {
		return this.exam_id;
	}

	public void setExam_id(int exam_id) {
		this.exam_id = exam_id;
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

	public byte getDone_and_ready() {
		return this.done_and_ready;
	}

	public void setDone_and_ready(byte done_and_ready) {
		this.done_and_ready = done_and_ready;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getResults() {
		return this.results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	public byte[] getScan_Image() {
		return this.scan_Image;
	}

	public void setScan_Image(byte[] scan_Image) {
		this.scan_Image = scan_Image;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
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

	public List<Bodyregion> getBodyregions() {
		return this.bodyregions;
	}

	public void setBodyregions(List<Bodyregion> bodyregions) {
		this.bodyregions = bodyregions;
	}

	public Bodyregion addBodyregion(Bodyregion bodyregion) {
		getBodyregions().add(bodyregion);
		bodyregion.setExam(this);

		return bodyregion;
	}

	public Bodyregion removeBodyregion(Bodyregion bodyregion) {
		getBodyregions().remove(bodyregion);
		bodyregion.setExam(null);

		return bodyregion;
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

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Examtype> getExamtypes() {
		return this.examtypes;
	}

	public void setExamtypes(List<Examtype> examtypes) {
		this.examtypes = examtypes;
	}

	public Examtype addExamtype(Examtype examtype) {
		getExamtypes().add(examtype);
		examtype.setExam(this);

		return examtype;
	}

	public Examtype removeExamtype(Examtype examtype) {
		getExamtypes().remove(examtype);
		examtype.setExam(null);

		return examtype;
	}

	public List<Lab> getLabs() {
		return this.labs;
	}

	public void setLabs(List<Lab> labs) {
		this.labs = labs;
	}

	public Lab addLab(Lab lab) {
		getLabs().add(lab);
		lab.setExam(this);

		return lab;
	}

	public Lab removeLab(Lab lab) {
		getLabs().remove(lab);
		lab.setExam(null);

		return lab;
	}

}