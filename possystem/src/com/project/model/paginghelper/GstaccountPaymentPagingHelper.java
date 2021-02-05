package com.project.model.paginghelper;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.project.bo.interfaces.IGstaccounBO;
import com.project.model.tax.GstaccountPaymentModel;

public class GstaccountPaymentPagingHelper extends LazyDataModel<GstaccountPaymentModel>{

	private static final long serialVersionUID = 1L;
	private List<GstaccountPaymentModel> gstaccountObj;
	
	Integer staffId;		
	Date dateFrom;
	Date dateTo;
	String status;
	IGstaccounBO gstaccounBO;
	Integer totalCount = 0;		
	Integer branchRecordId;	

	public GstaccountPaymentPagingHelper(Integer branchId, Date dateFrom, Date dateTo,	String status, IGstaccounBO gstaccounBO) {
		super();

		this.staffId = staffId;		
		this.dateFrom = dateFrom;
		this.dateTo =dateTo;		
		this.status=status;		
		this.gstaccounBO=gstaccounBO;
		this.branchRecordId=branchId;
		setRowCount(totalCount);
	}

	@Override
	public List<GstaccountPaymentModel> load(int start, int howMany, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		try {
			gstaccountObj = gstaccounBO.getGstaccountPaymentList(branchRecordId,dateFrom, dateTo, status, start, howMany);
			if (getRowCount() <= 0) {
				totalCount = gstaccounBO.getGstaccountPaymentCount(branchRecordId,dateFrom, dateTo, status);
				setRowCount(totalCount);
			}
			if (sortField != null) {
				Collections.sort(gstaccountObj, new GstaccountPaymentSorter(sortField,sortOrder));
			}
			setPageSize(howMany);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gstaccountObj;
	}

	@Override
	public Object getRowKey(GstaccountPaymentModel orderdata) {
		return orderdata.getAccounttaxpaymentid();
	}

	@Override
	public GstaccountPaymentModel getRowData(String commissionId) {
		Integer id = Integer.valueOf(commissionId);

		for (GstaccountPaymentModel orderdata : gstaccountObj) {
			if (id.equals(orderdata.getAccounttaxpaymentid())) {
				return orderdata;
			}
		}

		return null;
	}



	
}
