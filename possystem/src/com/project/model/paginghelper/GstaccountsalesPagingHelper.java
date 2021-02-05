package com.project.model.paginghelper;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.project.bo.interfaces.IGstaccounBO;
import com.project.model.tax.GstaccountModel;
import com.project.model.tax.GstsalesaccountbreakdownModel;

public class GstaccountsalesPagingHelper extends LazyDataModel<GstsalesaccountbreakdownModel>{
	

		private static final long serialVersionUID = 1L;
		private List<GstsalesaccountbreakdownModel> gstaccountObj;
		
		Integer staffId;		
		Date dateFrom;
		Date dateTo;
		String status;
		IGstaccounBO gstaccounBO;
		Integer totalCount = 0;		
		Integer branchRecordId;	
		Integer accounttaxid;
		
		public GstaccountsalesPagingHelper(Integer branchId,Integer accounttaxid,IGstaccounBO gstaccounBO) {
			super();
			this.branchRecordId=branchId;
			this.accounttaxid=accounttaxid;
			this.gstaccounBO=gstaccounBO;
			setRowCount(totalCount);
		}

		@Override
		public List<GstsalesaccountbreakdownModel> load(int start, int howMany, String sortField,
				SortOrder sortOrder, Map<String, Object> filters) {
			try {
				gstaccountObj = gstaccounBO.getGstsalesaccountbreakdownList(branchRecordId,accounttaxid,start,howMany);
				if (getRowCount() <= 0) {
					totalCount = gstaccounBO.getGstsalesaccountbreakdownCount(branchRecordId,accounttaxid);
					setRowCount(totalCount);
				}
				if (sortField != null) {
					Collections.sort(gstaccountObj, new GstaccountsalesSorter(sortField,sortOrder));
				}
				setPageSize(howMany);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return gstaccountObj;
		}

		@Override
		public Object getRowKey(GstsalesaccountbreakdownModel orderdata) {
			return orderdata.getGstsalesaccountbreakdownid();
		}

		@Override
		public GstsalesaccountbreakdownModel getRowData(String commissionId) {
			Integer id = Integer.valueOf(commissionId);

			for (GstsalesaccountbreakdownModel orderdata : gstaccountObj) {
				if (id.equals(orderdata.getGstsalesaccountbreakdownid())) {
					return orderdata;
				}
			}

			return null;
		}



}
