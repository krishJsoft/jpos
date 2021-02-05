package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the consultations database table.
 * 
 */
@Entity
@Table(name="consultations")
public class Consultation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int consultation_id;

	private int addominal_Circunference;

	@Column(length=250)
	private String allergies_Notes;

	private byte allergy_List_reviewed;

	private byte asymptomatic_with_usual_activity;

	@Column(name="at_risk")
	private byte atRisk;

	private int blood_Pressure_Diastolic;

	private int blood_Pressure_Systolic;

	private byte blurry_Vision;

	private byte chest_Pain;

	private byte chest_pain_on_exercise_only;

	private int cholesterol;

	@Column(length=510)
	private String clinical_Notes;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	@Column(length=250)
	private String current_Illness_Notes;

	private byte current_Medications_were_reviewed;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Column(length=510)
	private String diagnosis;

	private byte dificulty_Breathing;

	@Lob
	private String directions_Medical_Advice;

	@Temporal(TemporalType.TIMESTAMP)
	private Date directions_Next_Consult;

	@Column(length=510)
	private String disposition;

	@Column(nullable=false, length=100)
	private String doctor_for_next_Appointment;

	private byte easily_Bruised_or_Bleeding;

	private byte fainting_Episode;

	private byte feels_tired;

	private byte food_or_Drug_Allergies;

	private byte frequently_Headaches;

	private byte frequently_Hungry;

	private byte frequently_Thirsty;

	private byte frequently_Urination;

	private int glycemia;

	private int hdl;

	private int heart_Rate;

	private int height;

	private byte increased_cough;

	@Column(name="increased_dyspnea")
	private byte increasedDyspnea;

	@Column(name="increased_sputum_production")
	private byte increasedSputumProduction;

	private int ldl;

	@Column(length=510)
	private String location;

	@Column(length=250)
	private String medication_Notes;

	private byte memory_loss;

	private byte mild;

	private byte moderate;

	private byte mood_Swings;

	@Lob
	private String other_Symptoms;

	@Column(length=20)
	private String pain;

	@Column(length=510)
	private String patient_recomendations;

	private byte planned_air_travel_in_near_future;

	private byte pregnant;

	private int pulse;

	@Column(length=200)
	private String purpose;

	private byte recent_Antibiotic_use;

	private byte recent_ER_Visit;

	private byte recent_Hospital_admission;

	private byte recent_Oral_steroid_use;

	private byte recent_Weight_loss_or_decreased_appetite;

	private int respiratory_Rate;

	private byte severe;

	@Column(length=510)
	private String severity;

	private byte spirometry_evaluation_performed_within_previous_12_months;

	private byte stressed_out;

	private byte sudden_Weight_change;

	private byte symptomatic_at_rest;

	private byte symptomatic_with_minimal_activity;

	private byte symptomatic_with_usual_activity;

	private int temperature;

	@Temporal(TemporalType.TIMESTAMP)
	private Date time_In;

	@Temporal(TemporalType.TIMESTAMP)
	private Date time_Out;

	private int triglicerides;

	private byte very_Severe;

	private int weight;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to ConsultPayment
	@OneToMany(mappedBy="consultation")
	private List<ConsultPayment> consultPayments;

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

	//bi-directional many-to-one association to Diagno
	@OneToMany(mappedBy="consultation")
	private List<Diagno> diagnos;

	//bi-directional many-to-one association to Exam
	@OneToMany(mappedBy="consultation")
	private List<Exam> exams;

	//bi-directional many-to-one association to Insurance
	@OneToMany(mappedBy="consultation")
	private List<Insurance> insurances;

	//bi-directional many-to-one association to Pathology
	@OneToMany(mappedBy="consultation")
	private List<Pathology> pathologies;

	//bi-directional many-to-one association to Prescription
	@OneToMany(mappedBy="consultation")
	private List<Prescription> prescriptions;

	public Consultation() {
	}

	public int getConsultation_id() {
		return this.consultation_id;
	}

	public void setConsultation_id(int consultation_id) {
		this.consultation_id = consultation_id;
	}

	public int getAddominal_Circunference() {
		return this.addominal_Circunference;
	}

	public void setAddominal_Circunference(int addominal_Circunference) {
		this.addominal_Circunference = addominal_Circunference;
	}

	public String getAllergies_Notes() {
		return this.allergies_Notes;
	}

	public void setAllergies_Notes(String allergies_Notes) {
		this.allergies_Notes = allergies_Notes;
	}

	public byte getAllergy_List_reviewed() {
		return this.allergy_List_reviewed;
	}

	public void setAllergy_List_reviewed(byte allergy_List_reviewed) {
		this.allergy_List_reviewed = allergy_List_reviewed;
	}

	public byte getAsymptomatic_with_usual_activity() {
		return this.asymptomatic_with_usual_activity;
	}

	public void setAsymptomatic_with_usual_activity(byte asymptomatic_with_usual_activity) {
		this.asymptomatic_with_usual_activity = asymptomatic_with_usual_activity;
	}

	public byte getAtRisk() {
		return this.atRisk;
	}

	public void setAtRisk(byte atRisk) {
		this.atRisk = atRisk;
	}

	public int getBlood_Pressure_Diastolic() {
		return this.blood_Pressure_Diastolic;
	}

	public void setBlood_Pressure_Diastolic(int blood_Pressure_Diastolic) {
		this.blood_Pressure_Diastolic = blood_Pressure_Diastolic;
	}

	public int getBlood_Pressure_Systolic() {
		return this.blood_Pressure_Systolic;
	}

	public void setBlood_Pressure_Systolic(int blood_Pressure_Systolic) {
		this.blood_Pressure_Systolic = blood_Pressure_Systolic;
	}

	public byte getBlurry_Vision() {
		return this.blurry_Vision;
	}

	public void setBlurry_Vision(byte blurry_Vision) {
		this.blurry_Vision = blurry_Vision;
	}

	public byte getChest_Pain() {
		return this.chest_Pain;
	}

	public void setChest_Pain(byte chest_Pain) {
		this.chest_Pain = chest_Pain;
	}

	public byte getChest_pain_on_exercise_only() {
		return this.chest_pain_on_exercise_only;
	}

	public void setChest_pain_on_exercise_only(byte chest_pain_on_exercise_only) {
		this.chest_pain_on_exercise_only = chest_pain_on_exercise_only;
	}

	public int getCholesterol() {
		return this.cholesterol;
	}

	public void setCholesterol(int cholesterol) {
		this.cholesterol = cholesterol;
	}

	public String getClinical_Notes() {
		return this.clinical_Notes;
	}

	public void setClinical_Notes(String clinical_Notes) {
		this.clinical_Notes = clinical_Notes;
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

	public String getCurrent_Illness_Notes() {
		return this.current_Illness_Notes;
	}

	public void setCurrent_Illness_Notes(String current_Illness_Notes) {
		this.current_Illness_Notes = current_Illness_Notes;
	}

	public byte getCurrent_Medications_were_reviewed() {
		return this.current_Medications_were_reviewed;
	}

	public void setCurrent_Medications_were_reviewed(byte current_Medications_were_reviewed) {
		this.current_Medications_were_reviewed = current_Medications_were_reviewed;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDiagnosis() {
		return this.diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public byte getDificulty_Breathing() {
		return this.dificulty_Breathing;
	}

	public void setDificulty_Breathing(byte dificulty_Breathing) {
		this.dificulty_Breathing = dificulty_Breathing;
	}

	public String getDirections_Medical_Advice() {
		return this.directions_Medical_Advice;
	}

	public void setDirections_Medical_Advice(String directions_Medical_Advice) {
		this.directions_Medical_Advice = directions_Medical_Advice;
	}

	public Date getDirections_Next_Consult() {
		return this.directions_Next_Consult;
	}

	public void setDirections_Next_Consult(Date directions_Next_Consult) {
		this.directions_Next_Consult = directions_Next_Consult;
	}

	public String getDisposition() {
		return this.disposition;
	}

	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}

	public String getDoctor_for_next_Appointment() {
		return this.doctor_for_next_Appointment;
	}

	public void setDoctor_for_next_Appointment(String doctor_for_next_Appointment) {
		this.doctor_for_next_Appointment = doctor_for_next_Appointment;
	}

	public byte getEasily_Bruised_or_Bleeding() {
		return this.easily_Bruised_or_Bleeding;
	}

	public void setEasily_Bruised_or_Bleeding(byte easily_Bruised_or_Bleeding) {
		this.easily_Bruised_or_Bleeding = easily_Bruised_or_Bleeding;
	}

	public byte getFainting_Episode() {
		return this.fainting_Episode;
	}

	public void setFainting_Episode(byte fainting_Episode) {
		this.fainting_Episode = fainting_Episode;
	}

	public byte getFeels_tired() {
		return this.feels_tired;
	}

	public void setFeels_tired(byte feels_tired) {
		this.feels_tired = feels_tired;
	}

	public byte getFood_or_Drug_Allergies() {
		return this.food_or_Drug_Allergies;
	}

	public void setFood_or_Drug_Allergies(byte food_or_Drug_Allergies) {
		this.food_or_Drug_Allergies = food_or_Drug_Allergies;
	}

	public byte getFrequently_Headaches() {
		return this.frequently_Headaches;
	}

	public void setFrequently_Headaches(byte frequently_Headaches) {
		this.frequently_Headaches = frequently_Headaches;
	}

	public byte getFrequently_Hungry() {
		return this.frequently_Hungry;
	}

	public void setFrequently_Hungry(byte frequently_Hungry) {
		this.frequently_Hungry = frequently_Hungry;
	}

	public byte getFrequently_Thirsty() {
		return this.frequently_Thirsty;
	}

	public void setFrequently_Thirsty(byte frequently_Thirsty) {
		this.frequently_Thirsty = frequently_Thirsty;
	}

	public byte getFrequently_Urination() {
		return this.frequently_Urination;
	}

	public void setFrequently_Urination(byte frequently_Urination) {
		this.frequently_Urination = frequently_Urination;
	}

	public int getGlycemia() {
		return this.glycemia;
	}

	public void setGlycemia(int glycemia) {
		this.glycemia = glycemia;
	}

	public int getHdl() {
		return this.hdl;
	}

	public void setHdl(int hdl) {
		this.hdl = hdl;
	}

	public int getHeart_Rate() {
		return this.heart_Rate;
	}

	public void setHeart_Rate(int heart_Rate) {
		this.heart_Rate = heart_Rate;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public byte getIncreased_cough() {
		return this.increased_cough;
	}

	public void setIncreased_cough(byte increased_cough) {
		this.increased_cough = increased_cough;
	}

	public byte getIncreasedDyspnea() {
		return this.increasedDyspnea;
	}

	public void setIncreasedDyspnea(byte increasedDyspnea) {
		this.increasedDyspnea = increasedDyspnea;
	}

	public byte getIncreasedSputumProduction() {
		return this.increasedSputumProduction;
	}

	public void setIncreasedSputumProduction(byte increasedSputumProduction) {
		this.increasedSputumProduction = increasedSputumProduction;
	}

	public int getLdl() {
		return this.ldl;
	}

	public void setLdl(int ldl) {
		this.ldl = ldl;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMedication_Notes() {
		return this.medication_Notes;
	}

	public void setMedication_Notes(String medication_Notes) {
		this.medication_Notes = medication_Notes;
	}

	public byte getMemory_loss() {
		return this.memory_loss;
	}

	public void setMemory_loss(byte memory_loss) {
		this.memory_loss = memory_loss;
	}

	public byte getMild() {
		return this.mild;
	}

	public void setMild(byte mild) {
		this.mild = mild;
	}

	public byte getModerate() {
		return this.moderate;
	}

	public void setModerate(byte moderate) {
		this.moderate = moderate;
	}

	public byte getMood_Swings() {
		return this.mood_Swings;
	}

	public void setMood_Swings(byte mood_Swings) {
		this.mood_Swings = mood_Swings;
	}

	public String getOther_Symptoms() {
		return this.other_Symptoms;
	}

	public void setOther_Symptoms(String other_Symptoms) {
		this.other_Symptoms = other_Symptoms;
	}

	public String getPain() {
		return this.pain;
	}

	public void setPain(String pain) {
		this.pain = pain;
	}

	public String getPatient_recomendations() {
		return this.patient_recomendations;
	}

	public void setPatient_recomendations(String patient_recomendations) {
		this.patient_recomendations = patient_recomendations;
	}

	public byte getPlanned_air_travel_in_near_future() {
		return this.planned_air_travel_in_near_future;
	}

	public void setPlanned_air_travel_in_near_future(byte planned_air_travel_in_near_future) {
		this.planned_air_travel_in_near_future = planned_air_travel_in_near_future;
	}

	public byte getPregnant() {
		return this.pregnant;
	}

	public void setPregnant(byte pregnant) {
		this.pregnant = pregnant;
	}

	public int getPulse() {
		return this.pulse;
	}

	public void setPulse(int pulse) {
		this.pulse = pulse;
	}

	public String getPurpose() {
		return this.purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public byte getRecent_Antibiotic_use() {
		return this.recent_Antibiotic_use;
	}

	public void setRecent_Antibiotic_use(byte recent_Antibiotic_use) {
		this.recent_Antibiotic_use = recent_Antibiotic_use;
	}

	public byte getRecent_ER_Visit() {
		return this.recent_ER_Visit;
	}

	public void setRecent_ER_Visit(byte recent_ER_Visit) {
		this.recent_ER_Visit = recent_ER_Visit;
	}

	public byte getRecent_Hospital_admission() {
		return this.recent_Hospital_admission;
	}

	public void setRecent_Hospital_admission(byte recent_Hospital_admission) {
		this.recent_Hospital_admission = recent_Hospital_admission;
	}

	public byte getRecent_Oral_steroid_use() {
		return this.recent_Oral_steroid_use;
	}

	public void setRecent_Oral_steroid_use(byte recent_Oral_steroid_use) {
		this.recent_Oral_steroid_use = recent_Oral_steroid_use;
	}

	public byte getRecent_Weight_loss_or_decreased_appetite() {
		return this.recent_Weight_loss_or_decreased_appetite;
	}

	public void setRecent_Weight_loss_or_decreased_appetite(byte recent_Weight_loss_or_decreased_appetite) {
		this.recent_Weight_loss_or_decreased_appetite = recent_Weight_loss_or_decreased_appetite;
	}

	public int getRespiratory_Rate() {
		return this.respiratory_Rate;
	}

	public void setRespiratory_Rate(int respiratory_Rate) {
		this.respiratory_Rate = respiratory_Rate;
	}

	public byte getSevere() {
		return this.severe;
	}

	public void setSevere(byte severe) {
		this.severe = severe;
	}

	public String getSeverity() {
		return this.severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public byte getSpirometry_evaluation_performed_within_previous_12_months() {
		return this.spirometry_evaluation_performed_within_previous_12_months;
	}

	public void setSpirometry_evaluation_performed_within_previous_12_months(byte spirometry_evaluation_performed_within_previous_12_months) {
		this.spirometry_evaluation_performed_within_previous_12_months = spirometry_evaluation_performed_within_previous_12_months;
	}

	public byte getStressed_out() {
		return this.stressed_out;
	}

	public void setStressed_out(byte stressed_out) {
		this.stressed_out = stressed_out;
	}

	public byte getSudden_Weight_change() {
		return this.sudden_Weight_change;
	}

	public void setSudden_Weight_change(byte sudden_Weight_change) {
		this.sudden_Weight_change = sudden_Weight_change;
	}

	public byte getSymptomatic_at_rest() {
		return this.symptomatic_at_rest;
	}

	public void setSymptomatic_at_rest(byte symptomatic_at_rest) {
		this.symptomatic_at_rest = symptomatic_at_rest;
	}

	public byte getSymptomatic_with_minimal_activity() {
		return this.symptomatic_with_minimal_activity;
	}

	public void setSymptomatic_with_minimal_activity(byte symptomatic_with_minimal_activity) {
		this.symptomatic_with_minimal_activity = symptomatic_with_minimal_activity;
	}

	public byte getSymptomatic_with_usual_activity() {
		return this.symptomatic_with_usual_activity;
	}

	public void setSymptomatic_with_usual_activity(byte symptomatic_with_usual_activity) {
		this.symptomatic_with_usual_activity = symptomatic_with_usual_activity;
	}

	public int getTemperature() {
		return this.temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public Date getTime_In() {
		return this.time_In;
	}

	public void setTime_In(Date time_In) {
		this.time_In = time_In;
	}

	public Date getTime_Out() {
		return this.time_Out;
	}

	public void setTime_Out(Date time_Out) {
		this.time_Out = time_Out;
	}

	public int getTriglicerides() {
		return this.triglicerides;
	}

	public void setTriglicerides(int triglicerides) {
		this.triglicerides = triglicerides;
	}

	public byte getVery_Severe() {
		return this.very_Severe;
	}

	public void setVery_Severe(byte very_Severe) {
		this.very_Severe = very_Severe;
	}

	public int getWeight() {
		return this.weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
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

	public List<ConsultPayment> getConsultPayments() {
		return this.consultPayments;
	}

	public void setConsultPayments(List<ConsultPayment> consultPayments) {
		this.consultPayments = consultPayments;
	}

	public ConsultPayment addConsultPayment(ConsultPayment consultPayment) {
		getConsultPayments().add(consultPayment);
		consultPayment.setConsultation(this);

		return consultPayment;
	}

	public ConsultPayment removeConsultPayment(ConsultPayment consultPayment) {
		getConsultPayments().remove(consultPayment);
		consultPayment.setConsultation(null);

		return consultPayment;
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

	public List<Diagno> getDiagnos() {
		return this.diagnos;
	}

	public void setDiagnos(List<Diagno> diagnos) {
		this.diagnos = diagnos;
	}

	public Diagno addDiagno(Diagno diagno) {
		getDiagnos().add(diagno);
		diagno.setConsultation(this);

		return diagno;
	}

	public Diagno removeDiagno(Diagno diagno) {
		getDiagnos().remove(diagno);
		diagno.setConsultation(null);

		return diagno;
	}

	public List<Exam> getExams() {
		return this.exams;
	}

	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}

	public Exam addExam(Exam exam) {
		getExams().add(exam);
		exam.setConsultation(this);

		return exam;
	}

	public Exam removeExam(Exam exam) {
		getExams().remove(exam);
		exam.setConsultation(null);

		return exam;
	}

	public List<Insurance> getInsurances() {
		return this.insurances;
	}

	public void setInsurances(List<Insurance> insurances) {
		this.insurances = insurances;
	}

	public Insurance addInsurance(Insurance insurance) {
		getInsurances().add(insurance);
		insurance.setConsultation(this);

		return insurance;
	}

	public Insurance removeInsurance(Insurance insurance) {
		getInsurances().remove(insurance);
		insurance.setConsultation(null);

		return insurance;
	}

	public List<Pathology> getPathologies() {
		return this.pathologies;
	}

	public void setPathologies(List<Pathology> pathologies) {
		this.pathologies = pathologies;
	}

	public Pathology addPathology(Pathology pathology) {
		getPathologies().add(pathology);
		pathology.setConsultation(this);

		return pathology;
	}

	public Pathology removePathology(Pathology pathology) {
		getPathologies().remove(pathology);
		pathology.setConsultation(null);

		return pathology;
	}

	public List<Prescription> getPrescriptions() {
		return this.prescriptions;
	}

	public void setPrescriptions(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

	public Prescription addPrescription(Prescription prescription) {
		getPrescriptions().add(prescription);
		prescription.setConsultation(this);

		return prescription;
	}

	public Prescription removePrescription(Prescription prescription) {
		getPrescriptions().remove(prescription);
		prescription.setConsultation(null);

		return prescription;
	}

}