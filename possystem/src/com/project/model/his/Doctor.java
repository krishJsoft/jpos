package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the doctors database table.
 * 
 */
@Entity
@Table(name="doctors")
public class Doctor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int doctor_Id;

	@Column(length=50)
	private String address1;

	@Column(length=50)
	private String address2;

	@Column(length=50)
	private String AFP_Number;

	@Column(length=50)
	private String colonia;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date_of_Birth;

	@Lob
	private String degrees;

	@Column(length=50)
	private String departamento;

	@Lob
	@Column(nullable=false)
	private String doctor_Name;

	@Column(length=50)
	private String DUI_Number;

	@Column(length=50)
	private String email_Personal;

	@Column(nullable=false, length=50)
	private String email_Work;

	@Column(length=50)
	private String fax;

	@Column(length=50)
	private String first_Name;

	@Column(precision=10, scale=4)
	private BigDecimal hourly_Rate;

	@Column(length=5)
	private String initials;

	@Column(length=50)
	private String ISSS_Number;

	@Column(length=50)
	private String last_Name;

	@Column(length=50)
	private String licence_Number;

	@Column(length=50)
	private String middle_Name;

	@Column(length=50)
	private String municipio;

	@Column(length=50)
	private String NIT_Number;

	@Column(length=50)
	private String NUP_Number;

	@Column(length=50)
	private String other_Phone;

	@Column(length=50)
	private String pais;

	private byte pay_By_Amount;

	@Column(precision=10, scale=4)
	private BigDecimal pay_By_Amount_Value;

	private byte pay_By_Percent;

	@Column(precision=10, scale=4)
	private BigDecimal pay_By_Percent_Value;

	@Column(length=50)
	private String phone_Cell_1;

	@Column(length=50)
	private String phone_Cell_2;

	@Column(length=50)
	private String phone_Home_1;

	@Column(length=50)
	private String phone_Home_2;

	@Lob
	private byte[] photo;

	@Column(length=50)
	private String position_Held;

	@Lob
	private String resposabilities;

	@Lob
	private byte[] signature;

	@Column(length=150)
	private String specialities;

	@Column(length=50)
	private String spouse_Email;

	@Column(length=50)
	private String spouse_Name;

	@Column(length=50)
	private String spouse_Phone;

	@Column(length=50)
	private String supervisor;

	@Column(length=10)
	private String title;

	@Temporal(TemporalType.TIMESTAMP)
	private Date working_here_since;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to Appointment
	@OneToMany(mappedBy="doctor")
	private List<Appointment> appointments;

	//bi-directional many-to-one association to Consultation
	@OneToMany(mappedBy="doctor")
	private List<Consultation> consultations;

	//bi-directional many-to-one association to Diagno
	@OneToMany(mappedBy="doctor")
	private List<Diagno> diagnos;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchId")
	private Branch branch;

	//bi-directional many-to-one association to Exam
	@OneToMany(mappedBy="doctor")
	private List<Exam> exams;

	//bi-directional many-to-one association to Lab
	@OneToMany(mappedBy="doctor")
	private List<Lab> labs;

	//bi-directional many-to-one association to Patient
	@OneToMany(mappedBy="doctor")
	private List<Patient> patients;

	//bi-directional many-to-one association to Pediatricpsc
	@OneToMany(mappedBy="doctor")
	private List<Pediatricpsc> pediatricpscs;

	//bi-directional many-to-one association to Prescription
	@OneToMany(mappedBy="doctor")
	private List<Prescription> prescriptions;

	//bi-directional many-to-one association to Surgery
	@OneToMany(mappedBy="doctor")
	private List<Surgery> surgeries;

	public Doctor() {
	}

	public int getDoctor_Id() {
		return this.doctor_Id;
	}

	public void setDoctor_Id(int doctor_Id) {
		this.doctor_Id = doctor_Id;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAFP_Number() {
		return this.AFP_Number;
	}

	public void setAFP_Number(String AFP_Number) {
		this.AFP_Number = AFP_Number;
	}

	public String getColonia() {
		return this.colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
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

	public Date getDate_of_Birth() {
		return this.date_of_Birth;
	}

	public void setDate_of_Birth(Date date_of_Birth) {
		this.date_of_Birth = date_of_Birth;
	}

	public String getDegrees() {
		return this.degrees;
	}

	public void setDegrees(String degrees) {
		this.degrees = degrees;
	}

	public String getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getDoctor_Name() {
		return this.doctor_Name;
	}

	public void setDoctor_Name(String doctor_Name) {
		this.doctor_Name = doctor_Name;
	}

	public String getDUI_Number() {
		return this.DUI_Number;
	}

	public void setDUI_Number(String DUI_Number) {
		this.DUI_Number = DUI_Number;
	}

	public String getEmail_Personal() {
		return this.email_Personal;
	}

	public void setEmail_Personal(String email_Personal) {
		this.email_Personal = email_Personal;
	}

	public String getEmail_Work() {
		return this.email_Work;
	}

	public void setEmail_Work(String email_Work) {
		this.email_Work = email_Work;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFirst_Name() {
		return this.first_Name;
	}

	public void setFirst_Name(String first_Name) {
		this.first_Name = first_Name;
	}

	public BigDecimal getHourly_Rate() {
		return this.hourly_Rate;
	}

	public void setHourly_Rate(BigDecimal hourly_Rate) {
		this.hourly_Rate = hourly_Rate;
	}

	public String getInitials() {
		return this.initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public String getISSS_Number() {
		return this.ISSS_Number;
	}

	public void setISSS_Number(String ISSS_Number) {
		this.ISSS_Number = ISSS_Number;
	}

	public String getLast_Name() {
		return this.last_Name;
	}

	public void setLast_Name(String last_Name) {
		this.last_Name = last_Name;
	}

	public String getLicence_Number() {
		return this.licence_Number;
	}

	public void setLicence_Number(String licence_Number) {
		this.licence_Number = licence_Number;
	}

	public String getMiddle_Name() {
		return this.middle_Name;
	}

	public void setMiddle_Name(String middle_Name) {
		this.middle_Name = middle_Name;
	}

	public String getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getNIT_Number() {
		return this.NIT_Number;
	}

	public void setNIT_Number(String NIT_Number) {
		this.NIT_Number = NIT_Number;
	}

	public String getNUP_Number() {
		return this.NUP_Number;
	}

	public void setNUP_Number(String NUP_Number) {
		this.NUP_Number = NUP_Number;
	}

	public String getOther_Phone() {
		return this.other_Phone;
	}

	public void setOther_Phone(String other_Phone) {
		this.other_Phone = other_Phone;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public byte getPay_By_Amount() {
		return this.pay_By_Amount;
	}

	public void setPay_By_Amount(byte pay_By_Amount) {
		this.pay_By_Amount = pay_By_Amount;
	}

	public BigDecimal getPay_By_Amount_Value() {
		return this.pay_By_Amount_Value;
	}

	public void setPay_By_Amount_Value(BigDecimal pay_By_Amount_Value) {
		this.pay_By_Amount_Value = pay_By_Amount_Value;
	}

	public byte getPay_By_Percent() {
		return this.pay_By_Percent;
	}

	public void setPay_By_Percent(byte pay_By_Percent) {
		this.pay_By_Percent = pay_By_Percent;
	}

	public BigDecimal getPay_By_Percent_Value() {
		return this.pay_By_Percent_Value;
	}

	public void setPay_By_Percent_Value(BigDecimal pay_By_Percent_Value) {
		this.pay_By_Percent_Value = pay_By_Percent_Value;
	}

	public String getPhone_Cell_1() {
		return this.phone_Cell_1;
	}

	public void setPhone_Cell_1(String phone_Cell_1) {
		this.phone_Cell_1 = phone_Cell_1;
	}

	public String getPhone_Cell_2() {
		return this.phone_Cell_2;
	}

	public void setPhone_Cell_2(String phone_Cell_2) {
		this.phone_Cell_2 = phone_Cell_2;
	}

	public String getPhone_Home_1() {
		return this.phone_Home_1;
	}

	public void setPhone_Home_1(String phone_Home_1) {
		this.phone_Home_1 = phone_Home_1;
	}

	public String getPhone_Home_2() {
		return this.phone_Home_2;
	}

	public void setPhone_Home_2(String phone_Home_2) {
		this.phone_Home_2 = phone_Home_2;
	}

	public byte[] getPhoto() {
		return this.photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getPosition_Held() {
		return this.position_Held;
	}

	public void setPosition_Held(String position_Held) {
		this.position_Held = position_Held;
	}

	public String getResposabilities() {
		return this.resposabilities;
	}

	public void setResposabilities(String resposabilities) {
		this.resposabilities = resposabilities;
	}

	public byte[] getSignature() {
		return this.signature;
	}

	public void setSignature(byte[] signature) {
		this.signature = signature;
	}

	public String getSpecialities() {
		return this.specialities;
	}

	public void setSpecialities(String specialities) {
		this.specialities = specialities;
	}

	public String getSpouse_Email() {
		return this.spouse_Email;
	}

	public void setSpouse_Email(String spouse_Email) {
		this.spouse_Email = spouse_Email;
	}

	public String getSpouse_Name() {
		return this.spouse_Name;
	}

	public void setSpouse_Name(String spouse_Name) {
		this.spouse_Name = spouse_Name;
	}

	public String getSpouse_Phone() {
		return this.spouse_Phone;
	}

	public void setSpouse_Phone(String spouse_Phone) {
		this.spouse_Phone = spouse_Phone;
	}

	public String getSupervisor() {
		return this.supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getWorking_here_since() {
		return this.working_here_since;
	}

	public void setWorking_here_since(Date working_here_since) {
		this.working_here_since = working_here_since;
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

	public List<Appointment> getAppointments() {
		return this.appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public Appointment addAppointment(Appointment appointment) {
		getAppointments().add(appointment);
		appointment.setDoctor(this);

		return appointment;
	}

	public Appointment removeAppointment(Appointment appointment) {
		getAppointments().remove(appointment);
		appointment.setDoctor(null);

		return appointment;
	}

	public List<Consultation> getConsultations() {
		return this.consultations;
	}

	public void setConsultations(List<Consultation> consultations) {
		this.consultations = consultations;
	}

	public Consultation addConsultation(Consultation consultation) {
		getConsultations().add(consultation);
		consultation.setDoctor(this);

		return consultation;
	}

	public Consultation removeConsultation(Consultation consultation) {
		getConsultations().remove(consultation);
		consultation.setDoctor(null);

		return consultation;
	}

	public List<Diagno> getDiagnos() {
		return this.diagnos;
	}

	public void setDiagnos(List<Diagno> diagnos) {
		this.diagnos = diagnos;
	}

	public Diagno addDiagno(Diagno diagno) {
		getDiagnos().add(diagno);
		diagno.setDoctor(this);

		return diagno;
	}

	public Diagno removeDiagno(Diagno diagno) {
		getDiagnos().remove(diagno);
		diagno.setDoctor(null);

		return diagno;
	}

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public List<Exam> getExams() {
		return this.exams;
	}

	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}

	public Exam addExam(Exam exam) {
		getExams().add(exam);
		exam.setDoctor(this);

		return exam;
	}

	public Exam removeExam(Exam exam) {
		getExams().remove(exam);
		exam.setDoctor(null);

		return exam;
	}

	public List<Lab> getLabs() {
		return this.labs;
	}

	public void setLabs(List<Lab> labs) {
		this.labs = labs;
	}

	public Lab addLab(Lab lab) {
		getLabs().add(lab);
		lab.setDoctor(this);

		return lab;
	}

	public Lab removeLab(Lab lab) {
		getLabs().remove(lab);
		lab.setDoctor(null);

		return lab;
	}

	public List<Patient> getPatients() {
		return this.patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	public Patient addPatient(Patient patient) {
		getPatients().add(patient);
		patient.setDoctor(this);

		return patient;
	}

	public Patient removePatient(Patient patient) {
		getPatients().remove(patient);
		patient.setDoctor(null);

		return patient;
	}

	public List<Pediatricpsc> getPediatricpscs() {
		return this.pediatricpscs;
	}

	public void setPediatricpscs(List<Pediatricpsc> pediatricpscs) {
		this.pediatricpscs = pediatricpscs;
	}

	public Pediatricpsc addPediatricpsc(Pediatricpsc pediatricpsc) {
		getPediatricpscs().add(pediatricpsc);
		pediatricpsc.setDoctor(this);

		return pediatricpsc;
	}

	public Pediatricpsc removePediatricpsc(Pediatricpsc pediatricpsc) {
		getPediatricpscs().remove(pediatricpsc);
		pediatricpsc.setDoctor(null);

		return pediatricpsc;
	}

	public List<Prescription> getPrescriptions() {
		return this.prescriptions;
	}

	public void setPrescriptions(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

	public Prescription addPrescription(Prescription prescription) {
		getPrescriptions().add(prescription);
		prescription.setDoctor(this);

		return prescription;
	}

	public Prescription removePrescription(Prescription prescription) {
		getPrescriptions().remove(prescription);
		prescription.setDoctor(null);

		return prescription;
	}

	public List<Surgery> getSurgeries() {
		return this.surgeries;
	}

	public void setSurgeries(List<Surgery> surgeries) {
		this.surgeries = surgeries;
	}

	public Surgery addSurgery(Surgery surgery) {
		getSurgeries().add(surgery);
		surgery.setDoctor(this);

		return surgery;
	}

	public Surgery removeSurgery(Surgery surgery) {
		getSurgeries().remove(surgery);
		surgery.setDoctor(null);

		return surgery;
	}

}