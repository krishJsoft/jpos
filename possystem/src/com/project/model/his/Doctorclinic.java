package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the doctorclinic database table.
 * 
 */
@Entity
@Table(name="doctorclinic")
public class Doctorclinic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int doctorClinicId;

	@Column(length=250)
	private String address;

	@Column(length=50)
	private String city;

	@Column(length=200)
	private String clinicAddress;

	@Column(length=50)
	private String clinicCity;

	@Column(length=50)
	private String clinicCountry;

	@Column(length=20)
	private String clinicFaxNo;

	@Column(length=100)
	private String clinicName;
	
	@Column(length=100)
	private String email;	

	@Column(length=20)
	private String clinicPhoneNo;

	@Column(length=10)
	private String clinicPostCode;

	@Column(length=30)
	private String clinicRegNo;

	@Column(length=50)
	private String clinicState;

	@Column(precision=10, scale=2)
	private BigDecimal commission;

	private int commissionFrom;

	private int commissionTo;

	@Column(length=50)
	private String country;

	@Column(length=100)
	private String doctorName;

	@Column(length=20)
	private String identificationNumber;

	@Column(length=15)
	private String mobileNo;

	@Column(length=10)
	private String postcode;

	@Column(length=50)
	private String state;

	@Column(length=1)
	private String status;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date joinedDate;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branchRecordId")
	private Branch branch;

	public Doctorclinic() {
	}

	public int getDoctorClinicId() {
		return this.doctorClinicId;
	}

	public void setDoctorClinicId(int doctorClinicId) {
		this.doctorClinicId = doctorClinicId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getClinicAddress() {
		return this.clinicAddress;
	}

	public void setClinicAddress(String clinicAddress) {
		this.clinicAddress = clinicAddress;
	}

	public String getClinicCity() {
		return this.clinicCity;
	}

	public void setClinicCity(String clinicCity) {
		this.clinicCity = clinicCity;
	}

	public String getClinicCountry() {
		return this.clinicCountry;
	}

	public void setClinicCountry(String clinicCountry) {
		this.clinicCountry = clinicCountry;
	}

	public String getClinicFaxNo() {
		return this.clinicFaxNo;
	}

	public void setClinicFaxNo(String clinicFaxNo) {
		this.clinicFaxNo = clinicFaxNo;
	}

	public String getClinicName() {
		return this.clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

	public String getClinicPhoneNo() {
		return this.clinicPhoneNo;
	}

	public void setClinicPhoneNo(String clinicPhoneNo) {
		this.clinicPhoneNo = clinicPhoneNo;
	}

	public String getClinicPostCode() {
		return this.clinicPostCode;
	}

	public void setClinicPostCode(String clinicPostCode) {
		this.clinicPostCode = clinicPostCode;
	}

	public String getClinicRegNo() {
		return this.clinicRegNo;
	}

	public void setClinicRegNo(String clinicRegNo) {
		this.clinicRegNo = clinicRegNo;
	}

	public String getClinicState() {
		return this.clinicState;
	}

	public void setClinicState(String clinicState) {
		this.clinicState = clinicState;
	}

	public BigDecimal getCommission() {
		return this.commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	public int getCommissionFrom() {
		return this.commissionFrom;
	}

	public void setCommissionFrom(int commissionFrom) {
		this.commissionFrom = commissionFrom;
	}

	public int getCommissionTo() {
		return this.commissionTo;
	}

	public void setCommissionTo(int commissionTo) {
		this.commissionTo = commissionTo;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDoctorName() {
		return this.doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getIdentificationNumber() {
		return this.identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public String getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
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

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}
	
	

}