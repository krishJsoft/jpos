package com.project.model.paginghelper;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.project.bo.interfaces.IBranchsalesBO;
import com.project.bo.interfaces.IBranchtransferBO;
import com.project.model.sale.sales.BranchtransferModel;
import com.project.model.sale.sales.branchsale.BranchsaleModel;

public class BranchtransferPagingHelper extends LazyDataModel<BranchtransferModel> {

	private static final long serialVersionUID = 1L;
	private List<BranchtransferModel> requestObj;

	Integer branchId;	
	 String transferNo;
	 Date dateFrom;
	 Date dateTo;
	 String status;
	 String transferType;
	 IBranchtransferBO branchtransferBO;
	Integer totalCount = 0;
	Integer branchRecordId;	
	int[] searchResultIDList;

	public BranchtransferPagingHelper(String transferNo, Integer branchId, Date dateFrom, Date dateTo,  String status, IBranchtransferBO branchtransferBO,Integer branchRecordId,String transferType ) {
		super();

		this.branchId = branchId;
		this.dateFrom = dateFrom;
		this.dateTo =dateTo;
		this.transferNo = transferNo;
		this.status = status;
		this.branchtransferBO = branchtransferBO;
		this.transferType=transferType;
		this.branchRecordId=branchRecordId;
		setRowCount(totalCount);
	}

	@Override
	public List<BranchtransferModel> load(int start, int howMany, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		try {
			requestObj = branchtransferBO.getBranchtransferList(null,transferNo, branchId, dateFrom, dateTo, status, start, howMany,branchRecordId,transferType);

			if (getRowCount() <= 0) {
				totalCount = branchtransferBO.getBranchtransferCount(transferNo, branchId, dateFrom, dateTo, status,branchRecordId,transferType);
				setRowCount(totalCount);
			}

			if (sortField != null) {
				Collections.sort(requestObj, new BranchtransferSorter(sortField,sortOrder));
			}
			setPageSize(howMany);			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return requestObj;
	}

	@Override
	public Object getRowKey(BranchtransferModel requestdata) {
		return requestdata.getBranchtransferId();
	}

	@Override
	public BranchtransferModel getRowData(String quotationId) {
		Integer id = Integer.valueOf(quotationId);

		for (BranchtransferModel requestdata : requestObj) {
			if (id.equals(requestdata.getBranchtransferId())) {
				return requestdata;
			}
		}

		return null;
	}
	
}
