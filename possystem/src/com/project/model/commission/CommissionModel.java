package com.project.model.commission;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.project.model.datamodel.BranchstaffmemberModel;
import com.project.model.sale.sales.DoctorsPrescriptionsBreakdownModel;

public class CommissionModel {

	private int commissionId;

	private BigDecimal commisionAmount;

	private Date commisionDate;

	private String commissionPeriod;

	private String commissionType;

	private String status;

	List<DoctorsPrescriptionsBreakdownModel> doctorsPrescriptions = new ArrayList<DoctorsPrescriptionsBreakdownModel>();

	List<CommissionbreakdownModel> commissionbreakdowns;

	BranchstaffmemberModel branchstaffmember;

	public int getCommissionId() {
		return commissionId;
	}

	public void setCommissionId(int commissionId) {
		this.commissionId = commissionId;
	}

	public BigDecimal getCommisionAmount() {
		return commisionAmount;
	}

	public void setCommisionAmount(BigDecimal commisionAmount) {
		this.commisionAmount = commisionAmount;
	}

	public Date getCommisionDate() {
		return commisionDate;
	}

	public void setCommisionDate(Date commisionDate) {
		this.commisionDate = commisionDate;
	}

	public String getCommissionPeriod() {
		return commissionPeriod;
	}

	public void setCommissionPeriod(String commissionPeriod) {
		this.commissionPeriod = commissionPeriod;
	}

	public String getCommissionType() {
		return commissionType;
	}

	public void setCommissionType(String commissionType) {
		this.commissionType = commissionType;
	}

	public List<CommissionbreakdownModel> getCommissionbreakdowns() {
		return commissionbreakdowns;
	}

	public void setCommissionbreakdowns(
			List<CommissionbreakdownModel> commissionbreakdowns) {
		this.commissionbreakdowns = commissionbreakdowns;
	}

	public BranchstaffmemberModel getBranchstaffmember() {
		return branchstaffmember;
	}

	public void setBranchstaffmember(BranchstaffmemberModel branchstaffmember) {
		this.branchstaffmember = branchstaffmember;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<DoctorsPrescriptionsBreakdownModel> getDoctorsPrescriptions() {
		return doctorsPrescriptions;
	}

	public void setDoctorsPrescriptions(
			List<DoctorsPrescriptionsBreakdownModel> doctorsPrescriptions) {
		this.doctorsPrescriptions = doctorsPrescriptions;
	}

}
