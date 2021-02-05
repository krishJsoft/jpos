package com.project.model.paginghelper;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.project.bo.interfaces.ISalesorderBO;
import com.project.model.sale.sales.BranchSalesModel;

public class CustomerSalesSummaryDetailPagingHelper extends LazyDataModel<BranchSalesModel>{

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
	private int rowIndex;
    private int rowCount;

	public CustomerSalesSummaryDetailPagingHelper(Integer customerId, Date dateFrom, Date dateTo,   ISalesorderBO salesOrderBO,Integer branchRecordId) {
		super();

		this.customerId = customerId;		
		this.dateFrom = dateFrom;
		this.dateTo =dateTo;		
		this.branchRecordId=branchRecordId;
		this.salesOrderBO = salesOrderBO;
		
		setRowCount(totalCount);
	}

	@Override
	public List<BranchSalesModel> load(int start, int howMany, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		try {
			orderObj = salesOrderBO.getCustomerSalesByProduct(customerId, dateFrom, dateTo, start, howMany,branchRecordId);

			if (getRowCount() <= 0) {
				totalCount = salesOrderBO.getCustomerSalesByProductCount(customerId, dateFrom, dateTo,branchRecordId);
				setRowCount(totalCount);
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
	
	@SuppressWarnings("unchecked")
	@Override
    public void setWrappedData(Object list) {
        this.orderObj = (List<BranchSalesModel>) list;
    }
 
    @Override
    public Object getWrappedData() {
        return orderObj;
    }
    
    
    @Override
    public int getRowIndex() {
        return this.rowIndex;
    }
 
    @Override
    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }
 
    @Override
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }
 
    @Override
    public int getRowCount() {
        return this.rowCount;
    }
    

}