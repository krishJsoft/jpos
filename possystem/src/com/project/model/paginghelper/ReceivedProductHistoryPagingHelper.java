package com.project.model.paginghelper;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.project.bo.interfaces.IDeliveryorderBO;
import com.project.model.datamodel.stock.DeliveryorderbreakdownModel;


public class ReceivedProductHistoryPagingHelper extends LazyDataModel<DeliveryorderbreakdownModel>{

	private static final long serialVersionUID = 1L;
	private List<DeliveryorderbreakdownModel> orderObj;

	
	Date dateFrom;
	Date dateTo;	
	IDeliveryorderBO deliveryOrderBO;
	Integer totalCount = 0;
	Integer branchRecordId;	
	int[] searchResultIDList;

	public ReceivedProductHistoryPagingHelper(  Date dateFrom, Date dateTo , IDeliveryorderBO deliveryOrderBO,Integer branchRecordId) {
		super();

		
		this.dateFrom = dateFrom;
		this.dateTo =dateTo;		
		this.deliveryOrderBO = deliveryOrderBO;
		this.branchRecordId=branchRecordId;
		setRowCount(totalCount);
	}

	@Override
	public List<DeliveryorderbreakdownModel> load(int start, int howMany, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		try {
			orderObj = deliveryOrderBO.getDeliveryorderbreakdownList(null,dateFrom, dateTo, start, howMany,branchRecordId);

			if (getRowCount() <= 0) {
				totalCount = deliveryOrderBO.getDeliveryorderbreakdownCount(dateFrom, dateTo,branchRecordId);
				setRowCount(totalCount);
			}

			if (sortField != null) {
				Collections.sort(orderObj, new ReceivedProductHistoryLazySorter(sortField,sortOrder));
			}
			setPageSize(howMany);
			
			
			/*if (getRowCount() <= 0) {
				searchResultIDList =  deliveryOrderBO.getDeliveryorderbreakdownCount(dateFrom, dateTo,branchRecordId);
				setRowCount(searchResultIDList.length);
			}
			 if(searchResultIDList.length>0){
			orderObj = deliveryOrderBO.getDeliveryorderbreakdownList(searchResultIDList,null, null, start, howMany,branchRecordId);
			if (sortField != null) {
				Collections.sort(orderObj, new ReceivedProductHistoryLazySorter(sortField,sortOrder));
			}
			 }			
			setPageSize(howMany);	*/
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return orderObj;
	}

	@Override
	public Object getRowKey(DeliveryorderbreakdownModel orderdata) {
		return orderdata.getDeliveryOrderBreakdownId();
	}

	@Override
	public DeliveryorderbreakdownModel getRowData(String deliveryRequestId) {
		Integer id = Integer.valueOf(deliveryRequestId);

		for (DeliveryorderbreakdownModel orderdata : orderObj) {
			if (id.equals(orderdata.getDeliveryOrderBreakdownId())) {
				return orderdata;
			}
		}

		return null;
	}

}

