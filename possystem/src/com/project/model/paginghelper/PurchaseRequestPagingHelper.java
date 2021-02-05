package com.project.model.paginghelper;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.project.bo.interfaces.IPurchaserequestBO;
import com.project.model.datamodel.purchase.PurchaserequestModel;


public class PurchaseRequestPagingHelper extends LazyDataModel<PurchaserequestModel>{

	private static final long serialVersionUID = 1L;
	private List<PurchaserequestModel> requestObj;

	Integer branchId;	
	 String referenceNo;
	 Date dateFrom;
	 Date dateTo;
	 String status;
	IPurchaserequestBO purchaseRequestBO;
	Integer totalCount = 0;
	String sortId;
	String branchstatus="";
	int[] searchResultIDList;

	public PurchaseRequestPagingHelper(String referenceNo, Integer branchId, Date dateFrom, Date dateTo,  String status, IPurchaserequestBO purchaseRequestBO,String sortId,String branchstatus) {
		super();

		this.branchId = branchId;
		this.dateFrom = dateFrom;
		this.dateTo =dateTo;
		this.referenceNo = referenceNo;
		this.status = status;
		this.purchaseRequestBO = purchaseRequestBO;
		setRowCount(totalCount);
		this.branchstatus=branchstatus;
		this.sortId=sortId;
		
	}

	@Override
	public List<PurchaserequestModel> load(int start, int howMany, String sortField,
			SortOrder sortOrder, Map<String,Object> filters) {

		try {
			String orderBy="ASC";		
			if(sortField!=null)
			{
				sortId="t."+sortField;
			}						
			if(sortField!=null && sortId.equalsIgnoreCase("t.branchName"))
			{
				sortId="b.branchName";
			}			
			if(sortField==null)
			{
				sortId="b.branchName";
			}			
			if (sortOrder != null)
			{
			if(SortOrder.ASCENDING.equals(sortOrder))
			{
				orderBy="ASC";
			}
			else
			{
				orderBy="DESC";
			}
			}			
			
			requestObj = purchaseRequestBO.getPurchaserequestList(null,referenceNo, branchId, dateFrom, dateTo, status, start, howMany,sortId,orderBy,branchstatus);

			if (getRowCount() <= 0) {
				totalCount = purchaseRequestBO.getPurchaserequestCount(referenceNo, branchId, dateFrom, dateTo, status,branchstatus);
				setRowCount(totalCount);
			}

			if (sortField != null) {
				Collections.sort(requestObj, new PurchaseRequestLazySorter(sortField,sortOrder));
			}
			setPageSize(howMany);
			
			
			
			
			/*if (getRowCount() <= 0) {
				searchResultIDList = purchaseRequestBO.getPurchaserequestCount(referenceNo, branchId, dateFrom, dateTo, status,branchstatus);
				setRowCount(searchResultIDList.length);
			}
			 if(searchResultIDList.length>0)			 {
				 requestObj = purchaseRequestBO.getPurchaserequestList(searchResultIDList,null, null, null, null, null, start, howMany,sortId,orderBy,null);
			if (sortField != null) {
				Collections.sort(requestObj, new PurchaseRequestLazySorter(sortField,sortOrder));
			}
			 }			
			setPageSize(howMany);	*/
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return requestObj;
	}

	@Override
	public Object getRowKey(PurchaserequestModel requestdata) {
		return requestdata.getPurchaseRequestId();
	}

	@Override
	public PurchaserequestModel getRowData(String purchaseRequestId) {
		Integer id = Integer.valueOf(purchaseRequestId);

		for (PurchaserequestModel requestdata : requestObj) {
			if (id.equals(requestdata.getPurchaseRequestId())) {
				return requestdata;
			}
		}

		return null;
	}

}
