package com.project.model.paginghelper;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.project.bo.interfaces.IGstaccounBO;
import com.project.model.tax.GstaccountModel;



public class GstaccountPagingHelper extends LazyDataModel<GstaccountModel>{

	private static final long serialVersionUID = 1L;
	private List<GstaccountModel> gstaccountObj;
	
	Integer staffId;		
	Date dateFrom;
	Date dateTo;
	String status;
	IGstaccounBO gstaccounBO;
	Integer totalCount = 0;		
	Integer branchRecordId;	

	public GstaccountPagingHelper(Integer branchId, Date dateFrom, Date dateTo,	String status, IGstaccounBO gstaccounBO) {
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
	public List<GstaccountModel> load(int start, int howMany, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		try {
			gstaccountObj = gstaccounBO.getGstaccountList(branchRecordId,dateFrom, dateTo, status, start, howMany);
			if (getRowCount() <= 0) {
				totalCount = gstaccounBO.getGstaccountCount(branchRecordId,dateFrom, dateTo, status);
				setRowCount(totalCount);
			}
			if (sortField != null) {
				Collections.sort(gstaccountObj, new GstaccountSorter(sortField,sortOrder));
			}
			setPageSize(howMany);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gstaccountObj;
	}

	@Override
	public Object getRowKey(GstaccountModel orderdata) {
		return orderdata.getAccounttaxid();
	}

	@Override
	public GstaccountModel getRowData(String commissionId) {
		Integer id = Integer.valueOf(commissionId);

		for (GstaccountModel orderdata : gstaccountObj) {
			if (id.equals(orderdata.getAccounttaxid())) {
				return orderdata;
			}
		}

		return null;
	}



	
}
