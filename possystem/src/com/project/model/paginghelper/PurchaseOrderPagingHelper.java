package com.project.model.paginghelper;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.project.bo.interfaces.IPurchaseOrderBO;
import com.project.model.datamodel.purchase.PurchaseorderModel;


public class PurchaseOrderPagingHelper extends LazyDataModel<PurchaseorderModel>{

	private static final long serialVersionUID = 1L;
	private List<PurchaseorderModel> orderObj;

	Integer supplierId;	
	String purchaseOrderNo;
	Date dateFrom;
	 Date dateTo;
	 String status;
	IPurchaseOrderBO purchaseOrderBO;
	Integer totalCount = 0;
	Integer branchRecordId;
	int[] searchResultIDList;

	public PurchaseOrderPagingHelper(String purchaseOrderNo, Integer supplierId, Date dateFrom, Date dateTo,  String status, IPurchaseOrderBO purchaseOrderBO,Integer branchRecordId) {
		super();

		this.supplierId = supplierId;
		this.dateFrom = dateFrom;
		this.dateTo =dateTo;
		this.purchaseOrderNo = purchaseOrderNo;
		this.status = status;
		this.purchaseOrderBO = purchaseOrderBO;
		this.branchRecordId=branchRecordId;
		setRowCount(totalCount);
	}

	@Override
	public List<PurchaseorderModel> load(int start, int howMany, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		try {
			orderObj = purchaseOrderBO.getPurchaseorderList(null,purchaseOrderNo, supplierId, dateFrom, dateTo, status, start, howMany,branchRecordId);

			if (getRowCount() <= 0) {
				totalCount = purchaseOrderBO.getPurchaseorderCount(purchaseOrderNo, supplierId, dateFrom, dateTo, status,branchRecordId);
				setRowCount(totalCount);
			}

			if (sortField != null) {
				Collections.sort(orderObj, new PurchaseOrderLazySorter(sortField,sortOrder));
			}
			setPageSize(howMany);
			
			/*if (getRowCount() <= 0) {
				searchResultIDList = purchaseOrderBO.getPurchaseorderCount(purchaseOrderNo, supplierId, dateFrom, dateTo, status,branchRecordId);
				setRowCount(searchResultIDList.length);
			}
			 if(searchResultIDList.length>0){
			orderObj = purchaseOrderBO.getPurchaseorderList(searchResultIDList,null, null, null, null, null, start, howMany,null);
			if (sortField != null) {
				Collections.sort(orderObj, new PurchaseOrderLazySorter(sortField,sortOrder));
			}
			 }			
			setPageSize(howMany);	*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return orderObj;
	}

	@Override
	public Object getRowKey(PurchaseorderModel orderdata) {
		return orderdata.getPurchaseOrderId();
	}

	@Override
	public PurchaseorderModel getRowData(String purchaseRequestId) {
		Integer id = Integer.valueOf(purchaseRequestId);

		for (PurchaseorderModel orderdata : orderObj) {
			if (id.equals(orderdata.getPurchaseOrderId())) {
				return orderdata;
			}
		}

		return null;
	}

}
