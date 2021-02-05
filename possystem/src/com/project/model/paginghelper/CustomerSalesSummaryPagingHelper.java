package com.project.model.paginghelper;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.project.bo.interfaces.ISalesorderBO;
import com.project.model.sale.sales.BranchSalesSummaryModel;

public class CustomerSalesSummaryPagingHelper extends LazyDataModel<BranchSalesSummaryModel>{

	private static final long serialVersionUID = 1L;
	private List<BranchSalesSummaryModel> orderObj;
	
	Integer customerId;	
	List<Integer> customerIds;
	Integer branchId;	
	String salesOrderNo;
	Date dateFrom;
	Date dateTo;
	String status;
	ISalesorderBO salesOrderBO;
	Integer totalCount = 0;		
	Integer branchRecordId;	

	public CustomerSalesSummaryPagingHelper(List<Integer> customerIds, Date dateFrom, Date dateTo,  ISalesorderBO salesOrderBO,Integer branchRecordId) {
		super();

		this.customerIds = customerIds;		
		this.dateFrom = dateFrom;
		this.dateTo =dateTo;		
		this.branchRecordId=branchRecordId;
		this.salesOrderBO = salesOrderBO;
		setRowCount(totalCount);
	}

	@Override
	public List<BranchSalesSummaryModel> load(int start, int howMany, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		try {
			orderObj = salesOrderBO.getCustomerSalesByBranch(customerIds, dateFrom, dateTo, start, howMany,branchRecordId);

			if (getRowCount() <= 0) {
				totalCount = salesOrderBO.getCustomerSalesByBranchCount(customerIds, dateFrom, dateTo,branchRecordId);
				setRowCount(totalCount);
			}

			if (sortField != null) {
				Collections.sort(orderObj, new CustomerSalesSummaryLazySorter(sortField,sortOrder));
			}
			setPageSize(howMany);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderObj;
	}

	@Override
	public Object getRowKey(BranchSalesSummaryModel orderdata) {
		return orderdata.getCustomerId();
	}

	@Override
	public BranchSalesSummaryModel getRowData(String customerId) {
		Integer id = Integer.valueOf(customerId);

		for (BranchSalesSummaryModel orderdata : orderObj) {
			if (id.equals(orderdata.getCustomerId())) {
				return orderdata;
			}
		}

		return null;
	}

}
