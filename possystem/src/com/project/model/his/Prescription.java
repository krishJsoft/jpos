package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the prescriptions database table.
 * 
 */
@Entity
@Table(name="prescriptions")
public class Prescription implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int prescription_id;

	@Column(length=100)
	private String active_Ingredient;

	@Column(nullable=false, length=128)
	private String adminstration_Times;

	@Column(nullable=false, length=200)
	private String adverse_Reaction;

	@Column(nullable=false)
	private byte allow_Refils;

	private byte allow_Substitutions;

	@Column(nullable=false)
	private int common_Dosage;

	@Column(length=100)
	private String common_Name;

	private byte course_Completed;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date_Prescribed;

	private int dosage;

	@Column(length=50)
	private String dosage_units;

	private int duration_in_Days;

	@Lob
	private String extra_Info;

	@Temporal(TemporalType.TIMESTAMP)
	private Date finish_Treatment;

	@Column(length=150)
	private String foods_to_avoid;

	@Lob
	private String freq_Unit;

	private int frequency;

	@Column(length=100)
	private String generic_Name;

	private int how_Many_Refils;

	@Column(length=200)
	private String instructions;

	private byte patient_Discontinued_Treatment;

	@Column(length=50)
	private String pharmacy;

	@Column(length=200)
	private String reason_for_Discontinuation;

	@Temporal(TemporalType.TIMESTAMP)
	private Date review;

	@Lob
	private String side_Effects;

	@Temporal(TemporalType.TIMESTAMP)
	private Date start_Treatment;

	@Column(length=200)
	private String status;

	private int treatment_Duration;

	private byte treatment_Is_Active;

	@Column(nullable=false, length=50)
	private String treatment_Period;

	private byte use_Generic;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Medicament
	@OneToMany(mappedBy="prescription")
	private List<Medicament> medicaments;

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

	//bi-directional many-to-one association to Customer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CustomerId")
	private Customer customer;

	//bi-directional many-to-one association to Doctor
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Doctor_Id")
	private Doctor doctor;

	public Prescription() {
	}

	public int getPrescription_id() {
		return this.prescription_id;
	}

	public void setPrescription_id(int prescription_id) {
		this.prescription_id = prescription_id;
	}

	public String getActive_Ingredient() {
		return this.active_Ingredient;
	}

	public void setActive_Ingredient(String active_Ingredient) {
		this.active_Ingredient = active_Ingredient;
	}

	public String getAdminstration_Times() {
		return this.adminstration_Times;
	}

	public void setAdminstration_Times(String adminstration_Times) {
		this.adminstration_Times = adminstration_Times;
	}

	public String getAdverse_Reaction() {
		return this.adverse_Reaction;
	}

	public void setAdverse_Reaction(String adverse_Reaction) {
		this.adverse_Reaction = adverse_Reaction;
	}

	public byte getAllow_Refils() {
		return this.allow_Refils;
	}

	public void setAllow_Refils(byte allow_Refils) {
		this.allow_Refils = allow_Refils;
	}

	public byte getAllow_Substitutions() {
		return this.allow_Substitutions;
	}

	public void setAllow_Substitutions(byte allow_Substitutions) {
		this.allow_Substitutions = allow_Substitutions;
	}

	public int getCommon_Dosage() {
		return this.common_Dosage;
	}

	public void setCommon_Dosage(int common_Dosage) {
		this.common_Dosage = common_Dosage;
	}

	public String getCommon_Name() {
		return this.common_Name;
	}

	public void setCommon_Name(String common_Name) {
		this.common_Name = common_Name;
	}

	public byte getCourse_Completed() {
		return this.course_Completed;
	}

	public void setCourse_Completed(byte course_Completed) {
		this.course_Completed = course_Completed;
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

	public Date getDate_Prescribed() {
		return this.date_Prescribed;
	}

	public void setDate_Prescribed(Date date_Prescribed) {
		this.date_Prescribed = date_Prescribed;
	}

	public int getDosage() {
		return this.dosage;
	}

	public void setDosage(int dosage) {
		this.dosage = dosage;
	}

	public String getDosage_units() {
		return this.dosage_units;
	}

	public void setDosage_units(String dosage_units) {
		this.dosage_units = dosage_units;
	}

	public int getDuration_in_Days() {
		return this.duration_in_Days;
	}

	public void setDuration_in_Days(int duration_in_Days) {
		this.duration_in_Days = duration_in_Days;
	}

	public String getExtra_Info() {
		return this.extra_Info;
	}

	public void setExtra_Info(String extra_Info) {
		this.extra_Info = extra_Info;
	}

	public Date getFinish_Treatment() {
		return this.finish_Treatment;
	}

	public void setFinish_Treatment(Date finish_Treatment) {
		this.finish_Treatment = finish_Treatment;
	}

	public String getFoods_to_avoid() {
		return this.foods_to_avoid;
	}

	public void setFoods_to_avoid(String foods_to_avoid) {
		this.foods_to_avoid = foods_to_avoid;
	}

	public String getFreq_Unit() {
		return this.freq_Unit;
	}

	public void setFreq_Unit(String freq_Unit) {
		this.freq_Unit = freq_Unit;
	}

	public int getFrequency() {
		return this.frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public String getGeneric_Name() {
		return this.generic_Name;
	}

	public void setGeneric_Name(String generic_Name) {
		this.generic_Name = generic_Name;
	}

	public int getHow_Many_Refils() {
		return this.how_Many_Refils;
	}

	public void setHow_Many_Refils(int how_Many_Refils) {
		this.how_Many_Refils = how_Many_Refils;
	}

	public String getInstructions() {
		return this.instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public byte getPatient_Discontinued_Treatment() {
		return this.patient_Discontinued_Treatment;
	}

	public void setPatient_Discontinued_Treatment(byte patient_Discontinued_Treatment) {
		this.patient_Discontinued_Treatment = patient_Discontinued_Treatment;
	}

	public String getPharmacy() {
		return this.pharmacy;
	}

	public void setPharmacy(String pharmacy) {
		this.pharmacy = pharmacy;
	}

	public String getReason_for_Discontinuation() {
		return this.reason_for_Discontinuation;
	}

	public void setReason_for_Discontinuation(String reason_for_Discontinuation) {
		this.reason_for_Discontinuation = reason_for_Discontinuation;
	}

	public Date getReview() {
		return this.review;
	}

	public void setReview(Date review) {
		this.review = review;
	}

	public String getSide_Effects() {
		return this.side_Effects;
	}

	public void setSide_Effects(String side_Effects) {
		this.side_Effects = side_Effects;
	}

	public Date getStart_Treatment() {
		return this.start_Treatment;
	}

	public void setStart_Treatment(Date start_Treatment) {
		this.start_Treatment = start_Treatment;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTreatment_Duration() {
		return this.treatment_Duration;
	}

	public void setTreatment_Duration(int treatment_Duration) {
		this.treatment_Duration = treatment_Duration;
	}

	public byte getTreatment_Is_Active() {
		return this.treatment_Is_Active;
	}

	public void setTreatment_Is_Active(byte treatment_Is_Active) {
		this.treatment_Is_Active = treatment_Is_Active;
	}

	public String getTreatment_Period() {
		return this.treatment_Period;
	}

	public void setTreatment_Period(String treatment_Period) {
		this.treatment_Period = treatment_Period;
	}

	public byte getUse_Generic() {
		return this.use_Generic;
	}

	public void setUse_Generic(byte use_Generic) {
		this.use_Generic = use_Generic;
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

	public List<Medicament> getMedicaments() {
		return this.medicaments;
	}

	public void setMedicaments(List<Medicament> medicaments) {
		this.medicaments = medicaments;
	}

	public Medicament addMedicament(Medicament medicament) {
		getMedicaments().add(medicament);
		medicament.setPrescription(this);

		return medicament;
	}

	public Medicament removeMedicament(Medicament medicament) {
		getMedicaments().remove(medicament);
		medicament.setPrescription(null);

		return medicament;
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

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Doctor getDoctor() {
		return this.doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

}