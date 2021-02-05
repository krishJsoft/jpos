package com.project.model.datamodel;

import java.util.ArrayList;

public class BranchstaffmemberModel {

	private int staffId;

	private String allowAllBranches;

	private String designation;

	private String themeName;

	private String emailAddress;

	private String emailOldAddress;

	private String encrypted;

	private String firstName;

	private String forceReset;

	private boolean commission;

	private int idDepartment;

	private String departmentName;

	private String identificationNumber;

	private String identificationOldNumber;

	private int invalidAttempts;

	private String lastName;

	private String password;

	private String status;

	private String roleName;

	private String branchName;

	private int branchId;

	private String branchtype;

	private int roleId;

	private String city;

	private String country;

	private String phoneNo;

	private String postCode;

	private String state;

	private String address;

	private String reminder;

	private String staffCode;

	private String bankName;

	private String bankbranchName;

	private String accountNo;

	private String photoName;

	private ArrayList<String> moduleList = new ArrayList<String>();
	private ArrayList<String> functionList = new ArrayList<String>();

	BranchModel branch = new BranchModel();

	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public String getAllowAllBranches() {
		return allowAllBranches;
	}

	public void setAllowAllBranches(String allowAllBranches) {
		this.allowAllBranches = allowAllBranches;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getEncrypted() {
		return encrypted;
	}

	public void setEncrypted(String encrypted) {
		this.encrypted = encrypted;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getForceReset() {
		return forceReset;
	}

	public void setForceReset(String forceReset) {
		this.forceReset = forceReset;
	}

	public int getIdDepartment() {
		return idDepartment;
	}

	public void setIdDepartment(int idDepartment) {
		this.idDepartment = idDepartment;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public String getIdentificationOldNumber() {
		return identificationOldNumber;
	}

	public void setIdentificationOldNumber(String identificationOldNumber) {
		this.identificationOldNumber = identificationOldNumber;
	}

	public int getInvalidAttempts() {
		return invalidAttempts;
	}

	public void setInvalidAttempts(int invalidAttempts) {
		this.invalidAttempts = invalidAttempts;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isCommission() {
		return commission;
	}

	public void setCommission(boolean commission) {
		this.commission = commission;
	}

	public String getReminder() {
		return reminder;
	}

	public void setReminder(String reminder) {
		this.reminder = reminder;
	}

	public String getBranchtype() {
		return branchtype;
	}

	public void setBranchtype(String branchtype) {
		this.branchtype = branchtype;
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

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public BranchModel getBranch() {
		return branch;
	}

	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}

	public String getEmailOldAddress() {
		return emailOldAddress;
	}

	public void setEmailOldAddress(String emailOldAddress) {
		this.emailOldAddress = emailOldAddress;
	}

	public ArrayList<String> getModuleList() {
		return moduleList;
	}

	public void setModuleList(ArrayList<String> moduleList) {
		this.moduleList = moduleList;
	}

	public ArrayList<String> getFunctionList() {
		return functionList;
	}

	public void setFunctionList(ArrayList<String> functionList) {
		this.functionList = functionList;
	}

}
