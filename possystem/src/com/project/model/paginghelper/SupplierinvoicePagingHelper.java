package com.project.model.paginghelper;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.project.bo.interfaces.ISupplierinvoiceBO;
import com.project.model.invoice.supplier.SupplierinvoiceModel;


public class SupplierinvoicePagingHelper extends LazyDataModel<SupplierinvoiceModel>{

	private static final long serialVersionUID = 1L;
	private List<SupplierinvoiceModel> invoiceObj;
	
	Integer supplierId;	
	Integer branchId;	
	String invoiceNo;
	Date dateFrom;
	Date dateTo;
	String status;
	ISupplierinvoiceBO supplierinvoiceBO;
	Integer totalCount = 0;		
	Integer branchRecordId;
	Integer supplybranchId;
	int[] searchResultIDList;

	public SupplierinvoicePagingHelper(String invoiceNo, Integer supplierId,
			Date dateFrom, Date dateTo, String status,  ISupplierinvoiceBO supplierinvoiceBO,Integer branchRecordId,Integer supplybranchId) {
		super();

		this.supplierId = supplierId;		
		this.dateFrom = dateFrom;
		this.dateTo =dateTo;		
		this.status=status;
		this.invoiceNo = invoiceNo;
		this.supplierinvoiceBO=supplierinvoiceBO;
		this.branchRecordId=branchRecordId;
		this.supplybranchId=supplybranchId;
		setRowCount(totalCount);
	}

	@Override
	public List<SupplierinvoiceModel> load(int start, int howMany, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		try {					
			if (getRowCount() <= 0) {
				totalCount = supplierinvoiceBO.getSupplierinvoiceCount(invoiceNo,supplierId, dateFrom, dateTo,status,branchRecordId,supplybranchId);
				setRowCount(totalCount);
			}
			 if(totalCount>0)
			 {
			 invoiceObj = supplierinvoiceBO.getSupplierinvoiceList(null,invoiceNo,supplierId, dateFrom, dateTo,status, start, howMany,branchRecordId,supplybranchId);
			if (sortField != null) {
				Collections.sort(invoiceObj, new SupplierinvoiceLazySorter(sortField,sortOrder));
			}
			 }			
			setPageSize(howMany);			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return invoiceObj;
	}

	@Override
	public Object getRowKey(SupplierinvoiceModel orderdata) {
		return orderdata.getSupplierInvoiceId();
	}

	@Override
	public SupplierinvoiceModel getRowData(String supplierInvoiceId) {
		Integer id = Integer.valueOf(supplierInvoiceId);

		for (SupplierinvoiceModel orderdata : invoiceObj) {
			if (id.equals(orderdata.getSupplierInvoiceId())) {
				return orderdata;
			}
		}

		return null;
	}

}
