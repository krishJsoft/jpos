package com.project.model.paginghelper;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.project.bo.interfaces.ISalesorderBO;
import com.project.model.sale.sales.SalesorderModel;

public class SalesorderModelPagingHelper extends LazyDataModel<SalesorderModel>{

	private static final long serialVersionUID = 1L;
	private List<SalesorderModel> orderObj;

	Integer customerId;	
	Integer branchId;	
	String salesOrderNo;
	Date dateFrom;
	Date dateTo;
	String status;
	String salesType;
	ISalesorderBO salesOrderBO;
	Integer totalCount = 0;
	Integer branchRecordId;	
	int[] searchResultIDList;
	String paymentType;
	String salesRep;

	public SalesorderModelPagingHelper(String salesOrderNo, Integer customerId,Integer branchId, Date dateFrom, Date dateTo,  String status, ISalesorderBO salesOrderBO,Integer branchRecordId,String salesType,String paymentType, String salesRep) {
		super();

		this.customerId = customerId;
		this.branchId = branchId;
		this.dateFrom = dateFrom;
		this.dateTo =dateTo;
		this.salesOrderNo = salesOrderNo;
		this.status = status;
		this.salesType=salesType;
		this.salesOrderBO = salesOrderBO;
		this.branchRecordId=branchRecordId;
		this.paymentType=paymentType;
		this.salesRep=salesRep;
		setRowCount(totalCount);
	}

	@Override
	public List<SalesorderModel> load(int start, int howMany, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		try {
			orderObj = salesOrderBO.getSalesorderList(null,salesOrderNo, customerId,branchId, dateFrom, dateTo, status, start, howMany,branchRecordId,salesType,paymentType,salesRep);

			if (getRowCount() <= 0) {
				totalCount = salesOrderBO.getSalesorderCount(salesOrderNo, customerId,branchId, dateFrom, dateTo, status,branchRecordId,salesType,paymentType,salesRep);
				setRowCount(totalCount);
			}

			if (sortField != null) {
				Collections.sort(orderObj, new SalesOrderLazySorter(sortField,sortOrder));
			}
			setPageSize(howMany);
			
			/*if (getRowCount() <= 0) {
				searchResultIDList = salesOrderBO.getSalesorderCount(salesOrderNo, customerId,branchId, dateFrom, dateTo, status,branchRecordId);
				setRowCount(searchResultIDList.length);
			}
			 if(searchResultIDList.length>0){
			orderObj = salesOrderBO.getSalesorderList(searchResultIDList,null, null,null, null, null, null, start, howMany,branchRecordId);
			if (sortField != null) {
				Collections.sort(orderObj, new SalesOrderLazySorter(sortField,sortOrder));
			}
			 }			
			setPageSize(howMany);	*/
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return orderObj;
	}

	@Override
	public Object getRowKey(SalesorderModel orderdata) {
		return orderdata.getSalesOrderId();
	}

	@Override
	public SalesorderModel getRowData(String deliveryRequestId) {
		Integer id = Integer.valueOf(deliveryRequestId);

		for (SalesorderModel orderdata : orderObj) {
			if (id.equals(orderdata.getSalesOrderId())) {
				return orderdata;
			}
		}

		return null;
	}

}
