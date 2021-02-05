package com.project.model.paginghelper;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.project.bo.interfaces.IGstaccounBO;
import com.project.model.tax.GstaccountModel;
import com.project.model.tax.GstpurchaseaccountbreakdownModel;

public class GstaccountpurchasePagingHelper extends LazyDataModel<GstpurchaseaccountbreakdownModel>{
	

	private static final long serialVersionUID = 1L;
	private List<GstpurchaseaccountbreakdownModel> gstaccountObj;
	
	Integer staffId;		
	Date dateFrom;
	Date dateTo;
	String status;
	IGstaccounBO gstaccounBO;
	Integer totalCount = 0;		
	Integer branchRecordId;	
	Integer accounttaxid;
	
	public GstaccountpurchasePagingHelper(Integer branchId, Integer accounttaxid, IGstaccounBO gstaccounBO) {
		super();		
		this.gstaccounBO=gstaccounBO;
		this.branchRecordId=branchId;
		this.accounttaxid=accounttaxid;
		setRowCount(totalCount);
	}

	@Override
	public List<GstpurchaseaccountbreakdownModel> load(int start, int howMany, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		try {
			gstaccountObj = gstaccounBO.getGstpurchaseaccountbreakdownList(branchRecordId,accounttaxid, start, howMany);
			if (getRowCount() <= 0) {
				totalCount = gstaccounBO.getGstpurchaseaccountbreakdownCount(branchRecordId,accounttaxid);
				setRowCount(totalCount);
			}
			if (sortField != null) {
				Collections.sort(gstaccountObj, new GstaccountpurchaseSorter(sortField,sortOrder));
			}
			setPageSize(howMany);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gstaccountObj;
	}

	@Override
	public Object getRowKey(GstpurchaseaccountbreakdownModel orderdata) {
		return orderdata.getGstpurchaseaccountbreakdownId();
	}

	@Override
	public GstpurchaseaccountbreakdownModel getRowData(String commissionId) {
		Integer id = Integer.valueOf(commissionId);

		for (GstpurchaseaccountbreakdownModel orderdata : gstaccountObj) {
			if (id.equals(orderdata.getGstpurchaseaccountbreakdownId())) {
				return orderdata;
			}
		}

		return null;
	}




}
