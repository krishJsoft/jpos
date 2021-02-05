package com.project.model.paginghelper;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.project.bo.interfaces.IQuotationBO;
import com.project.bo.interfaces.ISalesorderBO;
import com.project.bo.interfaces.ISalesreturnBO;
import com.project.model.sale.sales.QuotationModel;
import com.project.model.sale.sales.SalesorderModel;
import com.project.model.sale.sales.SalesorderreturnModel;
import com.project.model.sale.sales.SalesreturnModel;

public class SalesReturnPagingHelper extends LazyDataModel<SalesorderreturnModel>{

	private static final long serialVersionUID = 1L;
	private List<SalesorderreturnModel> orderObj;

	Integer customerId;	
	Integer branchId;	
	String salesOrderNo;
	Date dateFrom;
	Date dateTo;
	String status;
	ISalesreturnBO salesreturnBO;
	Integer totalCount = 0;
	int[] searchResultIDList;

	public SalesReturnPagingHelper(String salesOrderNo, Integer customerId,Integer branchId, Date dateFrom, Date dateTo,  String status, ISalesreturnBO salesreturnBO) {
		super();

		this.customerId = customerId;
		this.branchId = branchId;
		this.dateFrom = dateFrom;
		this.dateTo =dateTo;
		this.salesOrderNo = salesOrderNo;
		this.status = status;
		this.salesreturnBO = salesreturnBO;
		setRowCount(totalCount);
	}

	@Override
	public List<SalesorderreturnModel> load(int start, int howMany, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		try {			

			/*if (getRowCount() <= 0) {
				searchResultIDList = salesreturnBO.getSalesreturnCount(salesOrderNo, customerId,branchId, dateFrom, dateTo, status);
				setRowCount(searchResultIDList.length);
			}
			 if(searchResultIDList.length>0)
			 {
			orderObj = salesreturnBO.getSalesreturnList(searchResultIDList,salesOrderNo, customerId,branchId, dateFrom, dateTo, status, start, howMany);
			if (sortField != null) {
				Collections.sort(orderObj, new SalesReturnLazySorter(sortField,sortOrder));
			}
			 }			
			setPageSize(howMany);*/			
			
			orderObj = salesreturnBO.getSalesreturnList(searchResultIDList,salesOrderNo, customerId,branchId, dateFrom, dateTo, status, start, howMany);

			if (getRowCount() <= 0) {
				totalCount = salesreturnBO.getSalesreturnCount(salesOrderNo, customerId,branchId, dateFrom, dateTo, status);
				setRowCount(totalCount);
			}

			if (sortField != null) {
				Collections.sort(orderObj, new SalesReturnLazySorter(sortField,sortOrder));
			}
			setPageSize(howMany);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return orderObj;
	}

	@Override
	public Object getRowKey(SalesorderreturnModel orderdata) {
		return orderdata.getSalesreturnid();
	}

	@Override
	public SalesorderreturnModel getRowData(String returnId) {
		Integer id = Integer.valueOf(returnId);

		for (SalesorderreturnModel orderdata : orderObj) {
			if (id.equals(orderdata.getSalesreturnid())) {
				return orderdata;
			}
		}

		return null;
	}

}
