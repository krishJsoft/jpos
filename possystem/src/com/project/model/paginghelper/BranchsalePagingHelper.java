package com.project.model.paginghelper;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.project.bo.interfaces.IBranchsalesBO;
import com.project.model.invoice.branch.BranchinvoiceModel;
import com.project.model.sale.sales.branchsale.BranchsaleModel;



public class BranchsalePagingHelper  extends LazyDataModel<BranchsaleModel> {

	private static final long serialVersionUID = 1L;
	private List<BranchsaleModel> requestObj;

	Integer branchId;	
	 String salesNo;
	 Date dateFrom;
	 Date dateTo;
	 String status;
	 IBranchsalesBO branchsalesBO;
	Integer totalCount = 0;
	Integer branchRecordId;	
	int[] searchResultIDList;

	public BranchsalePagingHelper(String salesNo, Integer branchId, Date dateFrom, Date dateTo,  String status, IBranchsalesBO branchsalesBO,Integer branchRecordId ) {
		super();

		this.branchId = branchId;
		this.dateFrom = dateFrom;
		this.dateTo =dateTo;
		this.salesNo = salesNo;
		this.status = status;
		this.branchsalesBO = branchsalesBO;
		this.branchRecordId=branchRecordId;
		setRowCount(totalCount);
	}

	@Override
	public List<BranchsaleModel> load(int start, int howMany, String sortField,SortOrder sortOrder, Map<String, Object> filters) {
	
		try {
			requestObj = branchsalesBO.getBranchsaleList(null,salesNo, branchId, dateFrom, dateTo, status, start, howMany,branchRecordId);

			if (getRowCount() <= 0) {
				totalCount = branchsalesBO.getBranchsaleCount(salesNo, branchId, dateFrom, dateTo, status,branchRecordId);
				setRowCount(totalCount);
			}

			if (sortField != null) {
				Collections.sort(requestObj, new BranchsaleSorter(sortField,sortOrder));
			}
			setPageSize(howMany);			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return requestObj;
	}

	@Override
	public Object getRowKey(BranchsaleModel requestdata) {
		return requestdata.getBranchsalesId();
	}

	@Override
	public BranchsaleModel getRowData(String quotationId) {
		Integer id = Integer.valueOf(quotationId);

		for (BranchsaleModel requestdata : requestObj) {
			if (id.equals(requestdata.getBranchsalesId())) {
				return requestdata;
			}
		}

		return null;
	}
	
}
