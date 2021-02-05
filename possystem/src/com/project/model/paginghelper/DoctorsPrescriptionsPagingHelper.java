package com.project.model.paginghelper;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.project.bo.interfaces.IDoctorsPrescriptionsBO;
import com.project.model.sale.sales.DoctorsPrescriptionsModel;

public class DoctorsPrescriptionsPagingHelper extends LazyDataModel<DoctorsPrescriptionsModel>{

	private static final long serialVersionUID = 1L;
	private List<DoctorsPrescriptionsModel> dpModels;
	
	String patientId;
	String prescptNo;
	Integer doctorId;
	String status;
	Date fromDate;
	Date toDate;
	IDoctorsPrescriptionsBO dpBO;
	Integer totalCount = 0;
	Integer branchRecordId;	

	public DoctorsPrescriptionsPagingHelper(String patientId, String prescptNo, Integer doctorId, Date fromDate, Date toDate,  String status, IDoctorsPrescriptionsBO dpBO,Integer branchRecordId) {
		super();

		this.patientId = patientId;
		this.prescptNo = prescptNo;
		this.doctorId = doctorId;
		this.fromDate = fromDate;
		this.toDate =toDate;
		this.status = status;
		this.dpBO = dpBO;
		this.branchRecordId=branchRecordId;
		setRowCount(totalCount);
	}

	@Override
	public List<DoctorsPrescriptionsModel> load(int start, int howMany, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		try {
			dpModels = dpBO.getDoctorPrescriptionList(patientId, prescptNo, doctorId, fromDate, toDate, status, start, howMany, branchRecordId);
			if (getRowCount() <= 0) {
				totalCount = dpBO.getDoctorPrescriptionCount(patientId, prescptNo, doctorId, fromDate, toDate, status, branchRecordId);
				setRowCount(totalCount);
			}
			if (sortField != null) {
				Collections.sort(dpModels, new DoctorsPrescriptionsLazySorter(sortField,sortOrder));
			}
			setPageSize(howMany);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dpModels;
	}

	@Override
	public Object getRowKey(DoctorsPrescriptionsModel orderdata) {
		return orderdata.getDoctorPrescriptionId();
	}

	@Override
	public DoctorsPrescriptionsModel getRowData(String customerId) {
		Integer id = Integer.valueOf(customerId);

		for (DoctorsPrescriptionsModel orderdata : dpModels) {
			if (id.equals(orderdata.getDoctorPrescriptionId())) {
				return orderdata;
			}
		}

		return null;
	}

}
