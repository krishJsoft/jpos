package com.project.model.commission;

import java.util.List;

import com.project.model.sale.sales.DoctorsPrescriptionsBreakdownModel;
import com.project.model.sale.sales.SalesorderbreakdownModel;

public class CommissionbreakdownModel {

	private int commissionbreakdownId;

	CommissionModel commission;

	private List<SalesorderbreakdownModel> salesorderbreakdowns;

	private List<DoctorsPrescriptionsBreakdownModel> doctorsPrescriptions;

	public int getCommissionbreakdownId() {
		return commissionbreakdownId;
	}

	public void setCommissionbreakdownId(int commissionbreakdownId) {
		this.commissionbreakdownId = commissionbreakdownId;
	}

	public CommissionModel getCommission() {
		return commission;
	}

	public void setCommission(CommissionModel commission) {
		this.commission = commission;
	}

	public List<SalesorderbreakdownModel> getSalesorderbreakdowns() {
		return salesorderbreakdowns;
	}

	public void setSalesorderbreakdowns(
			List<SalesorderbreakdownModel> salesorderbreakdowns) {
		this.salesorderbreakdowns = salesorderbreakdowns;
	}

	public List<DoctorsPrescriptionsBreakdownModel> getDoctorsPrescriptions() {
		return doctorsPrescriptions;
	}

	public void setDoctorsPrescriptions(
			List<DoctorsPrescriptionsBreakdownModel> doctorsPrescriptions) {
		this.doctorsPrescriptions = doctorsPrescriptions;
	}

}
