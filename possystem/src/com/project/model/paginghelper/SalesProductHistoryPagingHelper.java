package com.project.model.paginghelper;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import com.project.bo.interfaces.ISalesorderBO;
import com.project.model.sale.sales.SalesorderbreakdownModel;


public class SalesProductHistoryPagingHelper extends LazyDataModel<SalesorderbreakdownModel>{

	private static final long serialVersionUID = 1L;
	private List<SalesorderbreakdownModel> orderObj;

	
	Date dateFrom;
	Date dateTo;	
	ISalesorderBO salesOrderBO;
	Integer totalCount = 0;
	Integer branchRecordId;	
	int[] searchResultIDList;
	
	public SalesProductHistoryPagingHelper( Date dateFrom, Date dateTo , ISalesorderBO salesOrderBO,Integer branchRecordId) {
		super();

		
		this.dateFrom = dateFrom;
		this.dateTo =dateTo;		
		this.salesOrderBO = salesOrderBO;
		this.branchRecordId=branchRecordId;
		setRowCount(totalCount);
	}

	@Override
	public List<SalesorderbreakdownModel> load(int start, int howMany, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		try {
			orderObj = salesOrderBO.getSalesorderbreakdownList(null,null,dateFrom, dateTo, start, howMany,branchRecordId);

			if (getRowCount() <= 0) {
				totalCount = salesOrderBO.getSalesorderbreakdownCount(dateFrom, dateTo,branchRecordId);
				setRowCount(totalCount);
			}

			if (sortField != null) {
				Collections.sort(orderObj, new SalesProductHistoryLazySorter(sortField,sortOrder));
			}
			setPageSize(howMany);
			
			/*if (getRowCount() <= 0) {
				searchResultIDList = salesOrderBO.getSalesorderbreakdownCount(dateFrom, dateTo,branchRecordId);
				setRowCount(searchResultIDList.length);
			}
			 if(searchResultIDList.length>0){
			orderObj = salesOrderBO.getSalesorderbreakdownList(searchResultIDList,null,null, null, start, howMany,branchRecordId);
			if (sortField != null) {
				Collections.sort(orderObj, new SalesProductHistoryLazySorter(sortField,sortOrder));
			}
			 }			
			setPageSize(howMany);	*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return orderObj;
	}

	@Override
	public Object getRowKey(SalesorderbreakdownModel orderdata) {
		return orderdata.getSalesOrderBreakdownId();
	}

	@Override
	public SalesorderbreakdownModel getRowData(String deliveryRequestId) {
		Integer id = Integer.valueOf(deliveryRequestId);

		for (SalesorderbreakdownModel orderdata : orderObj) {
			if (id.equals(orderdata.getSalesOrderBreakdownId())) {
				return orderdata;
			}
		}

		return null;
	}

}


