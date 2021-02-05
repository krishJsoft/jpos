package com.project.model.paginghelper;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.project.bo.interfaces.IBranchinvoiceBO;
import com.project.bo.interfaces.ICustomerinvoiceBO;
import com.project.model.invoice.branch.BranchinvoiceModel;


public class BranchinvoicePagingHelper extends LazyDataModel<BranchinvoiceModel>{

	private static final long serialVersionUID = 1L;
	private List<BranchinvoiceModel> invoiceObj;
	
	Integer customerId;	
	Integer branchId;	
	String invoiceNo;
	Date dateFrom;
	Date dateTo;
	String status;
	IBranchinvoiceBO branchinvoiceBO;
	Integer totalCount = 0;	
	Integer branchRecordId;
	int[] searchResultIDList;

	public BranchinvoicePagingHelper(Integer customerId,String invoiceNo, Integer branchId,
			Date dateFrom, Date dateTo, String status,  IBranchinvoiceBO branchinvoiceBO,Integer branchRecordId) {
		super();

		this.branchId = branchId;		
		this.dateFrom = dateFrom;
		this.dateTo =dateTo;		
		this.status=status;
		this.invoiceNo = invoiceNo;
		this.branchinvoiceBO=branchinvoiceBO;
		this.branchRecordId=branchRecordId;
		this.customerId=customerId;
		setRowCount(totalCount);
	}

	@Override
	public List<BranchinvoiceModel> load(int start, int howMany, String sortField, SortOrder sortOrder, Map<String,Object> filters) {		
		try {
			/*invoiceObj = branchinvoiceBO.getBranchinvoiceList(customerId,invoiceNo,branchId, dateFrom, dateTo,status, start, howMany,branchRecordId);

			if (getRowCount() <= 0) {
				totalCount = branchinvoiceBO.getBranchinvoiceCount(customerId,invoiceNo,branchId, dateFrom, dateTo,status,branchRecordId);
				setRowCount(totalCount);
			}

			if (sortField != null) {
				Collections.sort(invoiceObj, new BrabchinvoiceLazySorter(sortField,sortOrder));
			}
			setPageSize(howMany);*/
			
			if (getRowCount() <= 0) {
				totalCount =  branchinvoiceBO.getBranchinvoiceCount(customerId,invoiceNo,branchId, dateFrom, dateTo,status,branchRecordId);
				setRowCount(totalCount);
			}
			 if(totalCount>0){
				 invoiceObj = branchinvoiceBO.getBranchinvoiceList(null,customerId,invoiceNo,branchId, dateFrom, dateTo,status, start, howMany,branchRecordId);
			if (sortField != null) {
				Collections.sort(invoiceObj, new BrabchinvoiceLazySorter(sortField,sortOrder));
			}
			 }			
			setPageSize(howMany);	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return invoiceObj;
	}

	@Override
	public Object getRowKey(BranchinvoiceModel orderdata) {
		return orderdata.getBranchInvoiceId();
	}

	@Override
	public BranchinvoiceModel getRowData(String branchId) {
		Integer id = Integer.valueOf(branchId);

		for (BranchinvoiceModel orderdata : invoiceObj) {
			if (id.equals(orderdata.getBranchInvoiceId())) {
				return orderdata;
			}
		}

		return null;
	}

}


