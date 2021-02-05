package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the branchstaffmembers database table.
 * 
 */
@Entity
@Table(name="branchstaffmembers")
public class Branchstaffmember implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int staffId;

	@Column(length=250)
	private String address;

	@Column(length=1)
	private String allowAllBranches;

	@Column(length=45)
	private String city;
	
	@Column(length=100)
	private String bankName;
	
	@Column(length=100)
	private String bankbranchName;
	
	@Column(length=45)
	private String accountNo;
	

	@Column(nullable=false, length=6)
	private String commission;

	@Column(length=45)
	private String country;

	@Column(length=45)
	private String designation;

	@Column(length=45)
	private String emailAddress;

	@Column(length=1)
	private String encrypted;

	@Column(length=45)
	private String firstName;

	@Column(length=1)
	private String forceReset;

	@Column(length=45)
	private String identificationNumber;

	@Column(nullable=false)
	private int invalidAttempts;

	@Column(length=45)
	private String lastName;

	@Column(length=45)
	private String password;

	@Column(length=25)
	private String phoneNo;

	@Column(length=10)
	private String postCode;

	@Column(length=500)
	private String reminder;

	@Column(length=45)
	private String state;

	@Column(length=1)
	private String status;

	@Column(length=45)
	private String themeName;
	
	@Column(length=20)
	private String staffCode;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BranchId")
	private Branch branch;

	//bi-directional many-to-one association to Department
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="departmentId")
	private Department department;

	//bi-directional many-to-one association to Role
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="RoleId")
	private Role role;

	//bi-directional many-to-one association to Commission
	@OneToMany(mappedBy="branchstaffmember")
	private List<Commission> commissions;

	//bi-directional many-to-one association to Doctorprescription
	@OneToMany(mappedBy="branchstaffmember")
	private List<Doctorprescription> doctorprescriptions;

	public Branchstaffmember() {
	}

	public int getStaffId() {
		return this.staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAllowAllBranches() {
		return this.allowAllBranches;
	}

	public void setAllowAllBranches(String allowAllBranches) {
		this.allowAllBranches = allowAllBranches;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCommission() {
		return this.commission;
	}

	public void setCommission(String commission) {
		this.commission = commission;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getEncrypted() {
		return this.encrypted;
	}

	public void setEncrypted(String encrypted) {
		this.encrypted = encrypted;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getForceReset() {
		return this.forceReset;
	}

	public void setForceReset(String forceReset) {
		this.forceReset = forceReset;
	}

	public String getIdentificationNumber() {
		return this.identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public int getInvalidAttempts() {
		return this.invalidAttempts;
	}

	public void setInvalidAttempts(int invalidAttempts) {
		this.invalidAttempts = invalidAttempts;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getReminder() {
		return this.reminder;
	}

	public void setReminder(String reminder) {
		this.reminder = reminder;
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

	public String getThemeName() {
		return this.themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Commission> getCommissions() {
		return this.commissions;
	}

	public void setCommissions(List<Commission> commissions) {
		this.commissions = commissions;
	}

	public Commission addCommission(Commission commission) {
		getCommissions().add(commission);
		commission.setBranchstaffmember(this);

		return commission;
	}

	public Commission removeCommission(Commission commission) {
		getCommissions().remove(commission);
		commission.setBranchstaffmember(null);

		return commission;
	}

	public List<Doctorprescription> getDoctorprescriptions() {
		return this.doctorprescriptions;
	}

	public void setDoctorprescriptions(List<Doctorprescription> doctorprescriptions) {
		this.doctorprescriptions = doctorprescriptions;
	}

	public Doctorprescription addDoctorprescription(Doctorprescription doctorprescription) {
		getDoctorprescriptions().add(doctorprescription);
		doctorprescription.setBranchstaffmember(this);

		return doctorprescription;
	}

	public Doctorprescription removeDoctorprescription(Doctorprescription doctorprescription) {
		getDoctorprescriptions().remove(doctorprescription);
		doctorprescription.setBranchstaffmember(null);

		return doctorprescription;
	}

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	
	public String getBankbranchName() {
		return bankbranchName;
	}

	public void setBankbranchName(String bankbranchName) {
		this.bankbranchName = bankbranchName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
	

	
}