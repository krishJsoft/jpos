package com.project.model.paginghelper;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.project.bo.interfaces.IDeliveryorderBO;
import com.project.model.datamodel.stock.DeliveryorderModel;

/*
 * @author Gopal Ar
 * @version 1.0
 * @since 12 Aug 2013
 * 
 */

public class DeliveryOrderPagingHelper extends LazyDataModel<DeliveryorderModel>{

	private static final long serialVersionUID = 1L;
	private List<DeliveryorderModel> orderObj;

	Integer supplierId;	
	String deliveryOrderNo;
	Date dateFrom;
	Date dateTo;
	String status;
	IDeliveryorderBO deliveryOrderBO;
	Integer totalCount = 0;
	String purchaseOrderNo;
	Integer branchRecordId;	
	int[] searchResultIDList;
	
	public DeliveryOrderPagingHelper(String deliveryOrderNo, Integer supplierId, Date dateFrom, Date dateTo,  String status, IDeliveryorderBO deliveryOrderBO,String purchaseOrderNo,Integer branchRecordId) {
		super();

		this.supplierId = supplierId;
		this.dateFrom = dateFrom;
		this.dateTo =dateTo;
		this.deliveryOrderNo = deliveryOrderNo;
		this.status = status;
		this.deliveryOrderBO = deliveryOrderBO;
		this.purchaseOrderNo=purchaseOrderNo;
		this.branchRecordId=branchRecordId;
		setRowCount(totalCount);
	}

	@Override
	public List<DeliveryorderModel> load(int start, int howMany, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		try {
			orderObj = deliveryOrderBO.getDeliveryorderList(null,deliveryOrderNo,purchaseOrderNo, supplierId, dateFrom, dateTo, status, start, howMany,branchRecordId);

			if (getRowCount() <= 0) {
				totalCount = deliveryOrderBO.getDeliveryorderCount(deliveryOrderNo,purchaseOrderNo, supplierId, dateFrom, dateTo, status,branchRecordId);
				setRowCount(totalCount);
			}

			if (sortField != null) {
				Collections.sort(orderObj, new DeliveryOrderLazySorter(sortField,sortOrder));
			}
			setPageSize(howMany);
			
			
			/*if (getRowCount() <= 0) {
				searchResultIDList = deliveryOrderBO.getDeliveryorderCount(deliveryOrderNo,purchaseOrderNo, supplierId, dateFrom, dateTo, status,branchRecordId);
				setRowCount(searchResultIDList.length);
			}
			 if(searchResultIDList.length>0){
			orderObj = deliveryOrderBO.getDeliveryorderList(searchResultIDList,null,null, null, null, null, null, start, howMany,branchRecordId);
			if (sortField != null) {
				Collections.sort(orderObj, new DeliveryOrderLazySorter(sortField,sortOrder));
			}
			 }			
			setPageSize(howMany);*/	
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return orderObj;
	}

	@Override
	public Object getRowKey(DeliveryorderModel orderdata) {
		return orderdata.getDeliveryOrderId();
	}

	@Override
	public DeliveryorderModel getRowData(String deliveryRequestId) {
		Integer id = Integer.valueOf(deliveryRequestId);

		for (DeliveryorderModel orderdata : orderObj) {
			if (id.equals(orderdata.getDeliveryOrderId())) {
				return orderdata;
			}
		}

		return null;
	}

}
