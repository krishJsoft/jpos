package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the doctorprescription database table.
 * 
 */
@Entity
@Table(name="doctorprescription")
public class Doctorprescription implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int doctorPrescriptionId;

	@Column(length=50)
	private String branchName;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(length=50)
	private String doctorName;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	@Column(length=50)
	private String patientName;

	@Column(length=45)
	private String prescriptionNo;

	@Column(length=500)
	private String remarks;

	@Column(length=1)
	private String status;

	@Column(precision=10, scale=2)
	private BigDecimal totalAmount;

	@Temporal(TemporalType.TIMESTAMP)
	private Date treatmentDate;

	//bi-directional many-to-one association to Commissionbreakdown
	@OneToMany(mappedBy="doctorprescription")
	private List<Commissionbreakdown> commissionbreakdowns;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchRecordId")
	private Branch branch;

	//bi-directional many-to-one association to Customer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="customerId")
	private Customer customer;

	//bi-directional many-to-one association to Branchstaffmember
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="doctorId")
	private Branchstaffmember branchstaffmember;

	//bi-directional many-to-one association to Doctorprescriptionbreakdown
	@OneToMany(mappedBy="doctorprescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Doctorprescriptionbreakdown> doctorprescriptionbreakdowns;

	public Doctorprescription() {
	}

	public int getDoctorPrescriptionId() {
		return this.doctorPrescriptionId;
	}

	public void setDoctorPrescriptionId(int doctorPrescriptionId) {
		this.doctorPrescriptionId = doctorPrescriptionId;
	}

	public String getBranchName() {
		return this.branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDoctorName() {
		return this.doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPrescriptionNo() {
		return this.prescriptionNo;
	}

	public void setPrescriptionNo(String prescriptionNo) {
		this.prescriptionNo = prescriptionNo;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getTreatmentDate() {
		return this.treatmentDate;
	}

	public void setTreatmentDate(Date treatmentDate) {
		this.treatmentDate = treatmentDate;
	}

	public List<Commissionbreakdown> getCommissionbreakdowns() {
		return this.commissionbreakdowns;
	}

	public void setCommissionbreakdowns(List<Commissionbreakdown> commissionbreakdowns) {
		this.commissionbreakdowns = commissionbreakdowns;
	}

	public Commissionbreakdown addCommissionbreakdown(Commissionbreakdown commissionbreakdown) {
		getCommissionbreakdowns().add(commissionbreakdown);
		commissionbreakdown.setDoctorprescription(this);

		return commissionbreakdown;
	}

	public Commissionbreakdown removeCommissionbreakdown(Commissionbreakdown commissionbreakdown) {
		getCommissionbreakdowns().remove(commissionbreakdown);
		commissionbreakdown.setDoctorprescription(null);

		return commissionbreakdown;
	}

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Branchstaffmember getBranchstaffmember() {
		return this.branchstaffmember;
	}

	public void setBranchstaffmember(Branchstaffmember branchstaffmember) {
		this.branchstaffmember = branchstaffmember;
	}

	public List<Doctorprescriptionbreakdown> getDoctorprescriptionbreakdowns() {
		return this.doctorprescriptionbreakdowns;
	}

	public void setDoctorprescriptionbreakdowns(List<Doctorprescriptionbreakdown> doctorprescriptionbreakdowns) {
		this.doctorprescriptionbreakdowns = doctorprescriptionbreakdowns;
	}

	public Doctorprescriptionbreakdown addDoctorprescriptionbreakdown(Doctorprescriptionbreakdown doctorprescriptionbreakdown) {
		getDoctorprescriptionbreakdowns().add(doctorprescriptionbreakdown);
		doctorprescriptionbreakdown.setDoctorprescription(this);

		return doctorprescriptionbreakdown;
	}

	public Doctorprescriptionbreakdown removeDoctorprescriptionbreakdown(Doctorprescriptionbreakdown doctorprescriptionbreakdown) {
		getDoctorprescriptionbreakdowns().remove(doctorprescriptionbreakdown);
		doctorprescriptionbreakdown.setDoctorprescription(null);

		return doctorprescriptionbreakdown;
	}

}