package com.project.model.sale.sales;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.project.model.datamodel.BranchstaffmemberModel;
import com.project.model.datamodel.CustomerModel;

public class DoctorsPrescriptionsModel {

	private int doctorPrescriptionId;
	private String doctorName;
	private String patientId;
	private String prescptNo;
	private int branchId;
	private String branchName;
	private String status;
	private Date createdDate;
	private Date treatmentDate;
	private Date modifiedDate;
	private String patientName;
	private String remarks;
	private BigDecimal totalAmount;
	private BigDecimal commissionAmount;
	Integer branchRecordId;

	private List<DoctorsPrescriptionsBreakdownModel> dpBreakdownModels;
	BranchstaffmemberModel branchstaffmember = new BranchstaffmemberModel();
	CustomerModel customer = new CustomerModel();

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPrescptNo() {
		return prescptNo;
	}

	public void setPrescptNo(String prescptNo) {
		this.prescptNo = prescptNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getDoctorPrescriptionId() {
		return doctorPrescriptionId;
	}

	public void setDoctorPrescriptionId(int doctorPrescriptionId) {
		this.doctorPrescriptionId = doctorPrescriptionId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getTreatmentDate() {
		return treatmentDate;
	}

	public void setTreatmentDate(Date treatmentDate) {
		this.treatmentDate = treatmentDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<DoctorsPrescriptionsBreakdownModel> getDpBreakdownModels() {
		return dpBreakdownModels;
	}

	public void setDpBreakdownModels(
			List<DoctorsPrescriptionsBreakdownModel> dpBreakdownModels) {
		this.dpBreakdownModels = dpBreakdownModels;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public BranchstaffmemberModel getBranchstaffmember() {
		return branchstaffmember;
	}

	public void setBranchstaffmember(BranchstaffmemberModel branchstaffmember) {
		this.branchstaffmember = branchstaffmember;
	}

	public CustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

	public BigDecimal getCommissionAmount() {
		return commissionAmount;
	}

	public void setCommissionAmount(BigDecimal commissionAmount) {
		this.commissionAmount = commissionAmount;
	}

	public Integer getBranchRecordId() {
		return branchRecordId;
	}

	public void setBranchRecordId(Integer branchRecordId) {
		this.branchRecordId = branchRecordId;
	}

}
