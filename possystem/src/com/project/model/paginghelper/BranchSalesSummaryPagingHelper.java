package com.project.model.paginghelper;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.project.bo.interfaces.ISalesorderBO;
import com.project.model.sale.sales.BranchSalesModel;
import com.project.model.sale.sales.BranchSalesSummaryModel;

public class BranchSalesSummaryPagingHelper extends LazyDataModel<BranchSalesModel>{

	private static final long serialVersionUID = 1L;
	private List<BranchSalesModel> orderObj;
	
	Integer customerId;	
	Integer branchId;	
	String salesOrderNo;
	Date dateFrom;
	Date dateTo;
	String status;
	ISalesorderBO salesOrderBO;
	Integer totalCount = 0;		
	Integer branchRecordId;	
	BranchSalesSummaryModel data=null;

	public BranchSalesSummaryPagingHelper(Integer branchId, Date dateFrom, Date dateTo,  ISalesorderBO salesOrderBO,Integer branchRecordId) {
		super();

	
		this.branchId = branchId;
		this.dateFrom = dateFrom;
		this.dateTo =dateTo;			
		this.salesOrderBO = salesOrderBO;
		this.branchRecordId=branchRecordId;
		setRowCount(totalCount);
	}

	@Override
	public List<BranchSalesModel> load(int start, int howMany, String sortField,SortOrder sortOrder, Map<String, Object> filters) {

		try {
			orderObj = salesOrderBO.getBranchSalesByProduct(branchId, dateFrom, dateTo, start, howMany,branchRecordId);

			if (getRowCount() <= 0) {
				data= salesOrderBO.getBranchSalesByProductCount(branchId, dateFrom, dateTo,branchRecordId);
				setRowCount(data.getTotalCount());
			}
				
			
			if (sortField != null) {
				Collections.sort(orderObj, new BranchSalesSummaryLazySorter(sortField,sortOrder));
			}
			setPageSize(howMany);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return orderObj;
	}

	@Override
	public Object getRowKey(BranchSalesModel orderdata) {
		return orderdata.getProductId();
	}

	@Override
	public BranchSalesModel getRowData(String productId) {
		Integer id = Integer.valueOf(productId);

		for (BranchSalesModel orderdata : orderObj) {
			if (id.equals(orderdata.getProductId())) {
				return orderdata;
			}
		}

		return null;
	}

}

