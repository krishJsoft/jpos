package com.project.model.paginghelper;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.project.bo.interfaces.IPurchaserequestBO;
import com.project.bo.interfaces.IQuotationBO;
import com.project.model.datamodel.purchase.PurchaserequestModel;
import com.project.model.sale.sales.QuotationModel;


public class QuotationPagingHelper extends LazyDataModel<QuotationModel>{

	private static final long serialVersionUID = 1L;
	private List<QuotationModel> requestObj;

	Integer customerId;	
	 String quoteNo;
	 Date dateFrom;
	 Date dateTo;
	 String status;
	IQuotationBO quotationBO;
	Integer totalCount = 0;
	Integer branchRecordId;	
	int[] searchResultIDList;

	public QuotationPagingHelper(String quoteNo, Integer customerId, Date dateFrom, Date dateTo,  String status, IQuotationBO quotationBO,Integer branchRecordId ) {
		super();

		this.customerId = customerId;
		this.dateFrom = dateFrom;
		this.dateTo =dateTo;
		this.quoteNo = quoteNo;
		this.status = status;
		this.quotationBO = quotationBO;
		this.branchRecordId=branchRecordId;
		setRowCount(totalCount);
	}

	@Override
	public List<QuotationModel> load(int start, int howMany, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		try {
			requestObj = quotationBO.getQuotationList(null,quoteNo, customerId, dateFrom, dateTo, status, start, howMany,branchRecordId);

			if (getRowCount() <= 0) {
				totalCount = quotationBO.getQuotationCount(quoteNo, customerId, dateFrom, dateTo, status,branchRecordId);
				setRowCount(totalCount);
			}

			if (sortField != null) {
				Collections.sort(requestObj, new QuotationLazySorter(sortField,sortOrder));
			}
			setPageSize(howMany);
			
			/*if (getRowCount() <= 0) {
				searchResultIDList = quotationBO.getQuotationCount(quoteNo, customerId, dateFrom, dateTo, status,branchRecordId);
				setRowCount(searchResultIDList.length);
			}
			 if(searchResultIDList.length>0){
				 requestObj = quotationBO.getQuotationList(searchResultIDList,null, null, null, null, null, start, howMany,branchRecordId);
			if (sortField != null) {
				Collections.sort(requestObj, new QuotationLazySorter(sortField,sortOrder));
			}
			 }			
			setPageSize(howMany);	*/
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return requestObj;
	}

	@Override
	public Object getRowKey(QuotationModel requestdata) {
		return requestdata.getQuotationId();
	}

	@Override
	public QuotationModel getRowData(String quotationId) {
		Integer id = Integer.valueOf(quotationId);

		for (QuotationModel requestdata : requestObj) {
			if (id.equals(requestdata.getQuotationId())) {
				return requestdata;
			}
		}

		return null;
	}
}
