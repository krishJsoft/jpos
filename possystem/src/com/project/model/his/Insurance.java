package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the insurances database table.
 * 
 */
@Entity
@Table(name="insurances")
public class Insurance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int insurance_ID;

	@Column(length=50)
	private String account_Number;

	@Column(length=50)
	private String address1;

	@Column(length=50)
	private String address2;

	@Column(length=50)
	private String billing_Method;

	@Column(length=50)
	private String city;

	@Column(length=50)
	private String claim_Number;

	@Column(length=50)
	private String contact_Person_Last;

	@Column(length=50)
	private String contact_Person_Midle;

	@Column(length=50)
	private String contact_Person_name;

	@Column(name="create_date", length=250)
	private String createDate;

	@Column(name="create_uid", length=250)
	private String createUid;

	@Column(length=50)
	private String departamento;

	@Column(length=50)
	private String department_or_Title;

	@Column(length=50)
	private String email;

	@Column(length=50)
	private String fax;

	@Column(length=50)
	private String group;

	@Column(length=50)
	private String insurance_Type;

	@Column(length=50)
	private String licence_Number;

	@Column(length=50)
	private String municipio;

	@Lob
	private String notes;

	@Column(length=50)
	private String numero_NIT;

	@Column(length=50)
	private String payor_ID_Number;

	@Column(length=50)
	private String phone1;

	@Column(length=50)
	private String phone2;

	@Column(length=50)
	private String referral_Number;

	@Column(length=50)
	private String registro_IVA;

	@Column(length=50)
	private String vender_Number;

	@Column(name="write_date", length=250)
	private String writeDate;

	@Column(name="write_uid", length=250)
	private String writeUid;

	//bi-directional many-to-one association to InsurancePayment
	@OneToMany(mappedBy="insurance")
	private List<InsurancePayment> insurancePayments;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchId")
	private Branch branch;

	//bi-directional many-to-one association to Consultation
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Consultation_id")
	private Consultation consultation;

	//bi-directional many-to-one association to Invoice
	@OneToMany(mappedBy="insurance")
	private List<Invoice> invoices;

	public Insurance() {
	}

	public int getInsurance_ID() {
		return this.insurance_ID;
	}

	public void setInsurance_ID(int insurance_ID) {
		this.insurance_ID = insurance_ID;
	}

	public String getAccount_Number() {
		return this.account_Number;
	}

	public void setAccount_Number(String account_Number) {
		this.account_Number = account_Number;
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

	public String getBilling_Method() {
		return this.billing_Method;
	}

	public void setBilling_Method(String billing_Method) {
		this.billing_Method = billing_Method;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getClaim_Number() {
		return this.claim_Number;
	}

	public void setClaim_Number(String claim_Number) {
		this.claim_Number = claim_Number;
	}

	public String getContact_Person_Last() {
		return this.contact_Person_Last;
	}

	public void setContact_Person_Last(String contact_Person_Last) {
		this.contact_Person_Last = contact_Person_Last;
	}

	public String getContact_Person_Midle() {
		return this.contact_Person_Midle;
	}

	public void setContact_Person_Midle(String contact_Person_Midle) {
		this.contact_Person_Midle = contact_Person_Midle;
	}

	public String getContact_Person_name() {
		return this.contact_Person_name;
	}

	public void setContact_Person_name(String contact_Person_name) {
		this.contact_Person_name = contact_Person_name;
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

	public String getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getDepartment_or_Title() {
		return this.department_or_Title;
	}

	public void setDepartment_or_Title(String department_or_Title) {
		this.department_or_Title = department_or_Title;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getGroup() {
		return this.group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getInsurance_Type() {
		return this.insurance_Type;
	}

	public void setInsurance_Type(String insurance_Type) {
		this.insurance_Type = insurance_Type;
	}

	public String getLicence_Number() {
		return this.licence_Number;
	}

	public void setLicence_Number(String licence_Number) {
		this.licence_Number = licence_Number;
	}

	public String getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getNumero_NIT() {
		return this.numero_NIT;
	}

	public void setNumero_NIT(String numero_NIT) {
		this.numero_NIT = numero_NIT;
	}

	public String getPayor_ID_Number() {
		return this.payor_ID_Number;
	}

	public void setPayor_ID_Number(String payor_ID_Number) {
		this.payor_ID_Number = payor_ID_Number;
	}

	public String getPhone1() {
		return this.phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return this.phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getReferral_Number() {
		return this.referral_Number;
	}

	public void setReferral_Number(String referral_Number) {
		this.referral_Number = referral_Number;
	}

	public String getRegistro_IVA() {
		return this.registro_IVA;
	}

	public void setRegistro_IVA(String registro_IVA) {
		this.registro_IVA = registro_IVA;
	}

	public String getVender_Number() {
		return this.vender_Number;
	}

	public void setVender_Number(String vender_Number) {
		this.vender_Number = vender_Number;
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

	public List<InsurancePayment> getInsurancePayments() {
		return this.insurancePayments;
	}

	public void setInsurancePayments(List<InsurancePayment> insurancePayments) {
		this.insurancePayments = insurancePayments;
	}

	public InsurancePayment addInsurancePayment(InsurancePayment insurancePayment) {
		getInsurancePayments().add(insurancePayment);
		insurancePayment.setInsurance(this);

		return insurancePayment;
	}

	public InsurancePayment removeInsurancePayment(InsurancePayment insurancePayment) {
		getInsurancePayments().remove(insurancePayment);
		insurancePayment.setInsurance(null);

		return insurancePayment;
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

	public List<Invoice> getInvoices() {
		return this.invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	public Invoice addInvoice(Invoice invoice) {
		getInvoices().add(invoice);
		invoice.setInsurance(this);

		return invoice;
	}

	public Invoice removeInvoice(Invoice invoice) {
		getInvoices().remove(invoice);
		invoice.setInsurance(null);

		return invoice;
	}

}