package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@Table(name="customer")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int customerId;

	@Column(length=245)
	private String address;

	@Column(nullable=false)
	private int availablePoints;

	@Column(precision=10, scale=2)
	private BigDecimal balanceAmount;

	@Column(length=45)
	private String business;

	@Column(length=100)
	private String city;

	@Column(length=45)
	private String contactPerson;

	@Column(length=45)
	private String country;

	@Column(length=45)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(length=25)
	private String gender;
	
	@Column(length=145)
	private String customerName;
	
	@Column(length=45)
	private String race;
	
	@Column(length=145)
	private String ageGroup;
	

	@Column(length=45)
	private String deliveryMethod;

	@Column(precision=10, scale=2)
	private BigDecimal discount;

	@Column(length=45)
	private String emailAddress;

	@Column(length=25)
	private String faxNo;

	@Column(length=45)
	private String identificationCompanyRegNo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastRedemptionDate;

	@Column(length=45)
	private String loyaltyCardCode;

	@Column(length=25)
	private String mobileNo;

	@Column(length=45)
	private String notifyBy;

	@Column(length=45)
	private String paymentMethod;

	@Column(length=45)
	private String paymentTerm;

	@Column(length=25)
	private String phoneNo;

	@Column(length=15)
	private String postCode;

	@Column(length=45)
	private String pricingCurrency;

	@Column(length=45)
	private String salesRep;

	@Column(length=100)
	private String state;

	@Column(length=1)
	private String status;

	@Column(length=15)
	private String title;
	
	@Column(length=45)
	private String password;

	//bi-directional many-to-one association to Branchinvoice
	@OneToMany(mappedBy="customer")
	private List<Branchinvoice> branchinvoices;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BranchId")
	private Branch branch;

	//bi-directional many-to-one association to Customerinvoice
	@OneToMany(mappedBy="customer")
	private List<Customerinvoice> customerinvoices;

	//bi-directional many-to-one association to Doctorprescription
	@OneToMany(mappedBy="customer")
	private List<Doctorprescription> doctorprescriptions;

	//bi-directional many-to-one association to Loyaltyredemption
	@OneToMany(mappedBy="customer")
	private List<Loyaltyredemption> loyaltyredemptions;

	//bi-directional many-to-one association to Paymentcollection
	@OneToMany(mappedBy="customer")
	private List<Paymentcollection> paymentcollections;

	//bi-directional many-to-one association to Quotation
	@OneToMany(mappedBy="customer")
	private List<Quotation> quotations;

	//bi-directional many-to-one association to Salesorder
	@OneToMany(mappedBy="customer")
	private List<Salesorder> salesorders;
	
	//bi-directional many-to-one association to Surgery
	@OneToMany(mappedBy="customer")
	private List<Surgery> surgeries;

	//bi-directional many-to-one association to Systemreview
	@OneToMany(mappedBy="customer")
	private List<Systemreview> systemreviews;

	//bi-directional many-to-one association to Vaccination
	@OneToMany(mappedBy="customer")
	private List<Vaccination> vaccinations;

	//bi-directional many-to-one association to Workflow
	@OneToMany(mappedBy="customer")
	private List<Workflow> workflows;
	
	//bi-directional many-to-one association to Pediatricpsc
	@OneToMany(mappedBy="customer")
	private List<Pediatricpsc> pediatricpscs;

	//bi-directional many-to-one association to Perinatal
	@OneToMany(mappedBy="customer")
	private List<Perinatal> perinatals;

	//bi-directional many-to-one association to Prescription
	@OneToMany(mappedBy="customer")
	private List<Prescription> prescriptions;
	
	//bi-directional many-to-one association to MedicalHisto
	@OneToMany(mappedBy="customer")
	private List<MedicalHisto> medicalHistos;

	//bi-directional many-to-one association to Medicationreaction
	@OneToMany(mappedBy="customer")
	private List<Medicationreaction> medicationreactions;

	//bi-directional many-to-one association to Patientallergy
	@OneToMany(mappedBy="customer")
	private List<Patientallergy> patientallergies;
	

	//bi-directional many-to-one association to Exam
	@OneToMany(mappedBy="customer")
	private List<Exam> exams;

	//bi-directional many-to-one association to InpatientReg
	@OneToMany(mappedBy="customer")
	private List<InpatientReg> inpatientRegs;

	//bi-directional many-to-one association to Invoice
	@OneToMany(mappedBy="customer")
	private List<Invoice> invoices;

	//bi-directional many-to-one association to Lab
	@OneToMany(mappedBy="customer")
	private List<Lab> labs;
	

	//bi-directional many-to-one association to Diagno
	@OneToMany(mappedBy="customer")
	private List<Diagno> diagnos;
	

	//bi-directional many-to-one association to Clinicchart
	@OneToMany(mappedBy="customer")
	private List<Clinicchart> cliniccharts;

	//bi-directional many-to-one association to ConsultPayment
	@OneToMany(mappedBy="customer")
	private List<ConsultPayment> consultPayments;

	//bi-directional many-to-one association to Consultation
	@OneToMany(mappedBy="customer")
	private List<Consultation> consultations;
	

	//bi-directional many-to-one association to Appointment
	@OneToMany(mappedBy="customer")
	private List<Appointment> appointments;

	public Customer() {
	}

	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAvailablePoints() {
		return this.availablePoints;
	}

	public void setAvailablePoints(int availablePoints) {
		this.availablePoints = availablePoints;
	}

	public BigDecimal getBalanceAmount() {
		return this.balanceAmount;
	}

	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public String getBusiness() {
		return this.business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getContactPerson() {
		return this.contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDeliveryMethod() {
		return this.deliveryMethod;
	}

	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public BigDecimal getDiscount() {
		return this.discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFaxNo() {
		return this.faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public String getIdentificationCompanyRegNo() {
		return this.identificationCompanyRegNo;
	}

	public void setIdentificationCompanyRegNo(String identificationCompanyRegNo) {
		this.identificationCompanyRegNo = identificationCompanyRegNo;
	}

	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Date getLastRedemptionDate() {
		return this.lastRedemptionDate;
	}

	public void setLastRedemptionDate(Date lastRedemptionDate) {
		this.lastRedemptionDate = lastRedemptionDate;
	}

	public String getLoyaltyCardCode() {
		return this.loyaltyCardCode;
	}

	public void setLoyaltyCardCode(String loyaltyCardCode) {
		this.loyaltyCardCode = loyaltyCardCode;
	}

	public String getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getNotifyBy() {
		return this.notifyBy;
	}

	public void setNotifyBy(String notifyBy) {
		this.notifyBy = notifyBy;
	}

	public String getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentTerm() {
		return this.paymentTerm;
	}

	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}

	public String getPhoneNo() {
		return this.phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPostCode() {
		return this.postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getPricingCurrency() {
		return this.pricingCurrency;
	}

	public void setPricingCurrency(String pricingCurrency) {
		this.pricingCurrency = pricingCurrency;
	}

	public String getSalesRep() {
		return this.salesRep;
	}

	public void setSalesRep(String salesRep) {
		this.salesRep = salesRep;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Branchinvoice> getBranchinvoices() {
		return this.branchinvoices;
	}

	public void setBranchinvoices(List<Branchinvoice> branchinvoices) {
		this.branchinvoices = branchinvoices;
	}

	public Branchinvoice addBranchinvoice(Branchinvoice branchinvoice) {
		getBranchinvoices().add(branchinvoice);
		branchinvoice.setCustomer(this);

		return branchinvoice;
	}

	public Branchinvoice removeBranchinvoice(Branchinvoice branchinvoice) {
		getBranchinvoices().remove(branchinvoice);
		branchinvoice.setCustomer(null);

		return branchinvoice;
	}

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public List<Customerinvoice> getCustomerinvoices() {
		return this.customerinvoices;
	}

	public void setCustomerinvoices(List<Customerinvoice> customerinvoices) {
		this.customerinvoices = customerinvoices;
	}

	public Customerinvoice addCustomerinvoice(Customerinvoice customerinvoice) {
		getCustomerinvoices().add(customerinvoice);
		customerinvoice.setCustomer(this);

		return customerinvoice;
	}

	public Customerinvoice removeCustomerinvoice(Customerinvoice customerinvoice) {
		getCustomerinvoices().remove(customerinvoice);
		customerinvoice.setCustomer(null);

		return customerinvoice;
	}

	public List<Doctorprescription> getDoctorprescriptions() {
		return this.doctorprescriptions;
	}

	public void setDoctorprescriptions(List<Doctorprescription> doctorprescriptions) {
		this.doctorprescriptions = doctorprescriptions;
	}

	public Doctorprescription addDoctorprescription(Doctorprescription doctorprescription) {
		getDoctorprescriptions().add(doctorprescription);
		doctorprescription.setCustomer(this);

		return doctorprescription;
	}

	public Doctorprescription removeDoctorprescription(Doctorprescription doctorprescription) {
		getDoctorprescriptions().remove(doctorprescription);
		doctorprescription.setCustomer(null);

		return doctorprescription;
	}

	public List<Loyaltyredemption> getLoyaltyredemptions() {
		return this.loyaltyredemptions;
	}

	public void setLoyaltyredemptions(List<Loyaltyredemption> loyaltyredemptions) {
		this.loyaltyredemptions = loyaltyredemptions;
	}

	public Loyaltyredemption addLoyaltyredemption(Loyaltyredemption loyaltyredemption) {
		getLoyaltyredemptions().add(loyaltyredemption);
		loyaltyredemption.setCustomer(this);

		return loyaltyredemption;
	}

	public Loyaltyredemption removeLoyaltyredemption(Loyaltyredemption loyaltyredemption) {
		getLoyaltyredemptions().remove(loyaltyredemption);
		loyaltyredemption.setCustomer(null);

		return loyaltyredemption;
	}

	public List<Paymentcollection> getPaymentcollections() {
		return this.paymentcollections;
	}

	public void setPaymentcollections(List<Paymentcollection> paymentcollections) {
		this.paymentcollections = paymentcollections;
	}

	public Paymentcollection addPaymentcollection(Paymentcollection paymentcollection) {
		getPaymentcollections().add(paymentcollection);
		paymentcollection.setCustomer(this);

		return paymentcollection;
	}

	public Paymentcollection removePaymentcollection(Paymentcollection paymentcollection) {
		getPaymentcollections().remove(paymentcollection);
		paymentcollection.setCustomer(null);

		return paymentcollection;
	}

	public List<Quotation> getQuotations() {
		return this.quotations;
	}

	public void setQuotations(List<Quotation> quotations) {
		this.quotations = quotations;
	}

	public Quotation addQuotation(Quotation quotation) {
		getQuotations().add(quotation);
		quotation.setCustomer(this);

		return quotation;
	}

	public Quotation removeQuotation(Quotation quotation) {
		getQuotations().remove(quotation);
		quotation.setCustomer(null);

		return quotation;
	}

	public List<Salesorder> getSalesorders() {
		return this.salesorders;
	}

	public void setSalesorders(List<Salesorder> salesorders) {
		this.salesorders = salesorders;
	}

	public Salesorder addSalesorder(Salesorder salesorder) {
		getSalesorders().add(salesorder);
		salesorder.setCustomer(this);

		return salesorder;
	}

	public Salesorder removeSalesorder(Salesorder salesorder) {
		getSalesorders().remove(salesorder);
		salesorder.setCustomer(null);

		return salesorder;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getAgeGroup() {
		return ageGroup;
	}

	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}

	public List<Surgery> getSurgeries() {
		return surgeries;
	}

	public void setSurgeries(List<Surgery> surgeries) {
		this.surgeries = surgeries;
	}

	public List<Systemreview> getSystemreviews() {
		return systemreviews;
	}

	public void setSystemreviews(List<Systemreview> systemreviews) {
		this.systemreviews = systemreviews;
	}

	public List<Vaccination> getVaccinations() {
		return vaccinations;
	}

	public void setVaccinations(List<Vaccination> vaccinations) {
		this.vaccinations = vaccinations;
	}

	public List<Workflow> getWorkflows() {
		return workflows;
	}

	public void setWorkflows(List<Workflow> workflows) {
		this.workflows = workflows;
	}

	public List<Pediatricpsc> getPediatricpscs() {
		return pediatricpscs;
	}

	public void setPediatricpscs(List<Pediatricpsc> pediatricpscs) {
		this.pediatricpscs = pediatricpscs;
	}

	public List<Perinatal> getPerinatals() {
		return perinatals;
	}

	public void setPerinatals(List<Perinatal> perinatals) {
		this.perinatals = perinatals;
	}

	public List<Prescription> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

	public List<MedicalHisto> getMedicalHistos() {
		return medicalHistos;
	}

	public void setMedicalHistos(List<MedicalHisto> medicalHistos) {
		this.medicalHistos = medicalHistos;
	}

	public List<Medicationreaction> getMedicationreactions() {
		return medicationreactions;
	}

	public void setMedicationreactions(List<Medicationreaction> medicationreactions) {
		this.medicationreactions = medicationreactions;
	}

	public List<Patientallergy> getPatientallergies() {
		return patientallergies;
	}

	public void setPatientallergies(List<Patientallergy> patientallergies) {
		this.patientallergies = patientallergies;
	}

	public List<Exam> getExams() {
		return exams;
	}

	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}

	public List<InpatientReg> getInpatientRegs() {
		return inpatientRegs;
	}

	public void setInpatientRegs(List<InpatientReg> inpatientRegs) {
		this.inpatientRegs = inpatientRegs;
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	public List<Lab> getLabs() {
		return labs;
	}

	public void setLabs(List<Lab> labs) {
		this.labs = labs;
	}

	public List<Diagno> getDiagnos() {
		return diagnos;
	}

	public void setDiagnos(List<Diagno> diagnos) {
		this.diagnos = diagnos;
	}

	public List<Clinicchart> getCliniccharts() {
		return cliniccharts;
	}

	public void setCliniccharts(List<Clinicchart> cliniccharts) {
		this.cliniccharts = cliniccharts;
	}

	public List<ConsultPayment> getConsultPayments() {
		return consultPayments;
	}

	public void setConsultPayments(List<ConsultPayment> consultPayments) {
		this.consultPayments = consultPayments;
	}

	public List<Consultation> getConsultations() {
		return consultations;
	}

	public void setConsultations(List<Consultation> consultations) {
		this.consultations = consultations;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	
	
	
	

}