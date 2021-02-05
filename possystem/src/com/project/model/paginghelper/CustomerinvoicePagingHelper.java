package com.project.model.paginghelper;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import com.project.bo.interfaces.ICustomerinvoiceBO;
import com.project.model.invoice.customer.CustomerinvoiceModel;

public class CustomerinvoicePagingHelper extends LazyDataModel<CustomerinvoiceModel>{

	private static final long serialVersionUID = 1L;
	private List<CustomerinvoiceModel> invoiceObj;
	
	Integer customerId;	
	Integer branchId;	
	String invoiceNo;
	Date dateFrom;
	Date dateTo;
	String status;
	ICustomerinvoiceBO customerinvoiceBO;
	Integer totalCount = 0;		

	public CustomerinvoicePagingHelper(String invoiceNo, Integer customerId,
			Date dateFrom, Date dateTo, String status,  ICustomerinvoiceBO customerinvoiceBO) {
		super();

		this.customerId = customerId;		
		this.dateFrom = dateFrom;
		this.dateTo =dateTo;		
		this.status=status;
		this.invoiceNo = invoiceNo;
		this.customerinvoiceBO=customerinvoiceBO;
		setRowCount(totalCount);
	}

	@Override
	public List<CustomerinvoiceModel> load(int start, int howMany, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		try {
			invoiceObj = customerinvoiceBO.getCustomerinvoiceList(invoiceNo,customerId, dateFrom, dateTo,status, start, howMany);

			if (getRowCount() <= 0) {
				totalCount = customerinvoiceBO.getCustomerinvoiceCount(invoiceNo,customerId, dateFrom, dateTo,status);
				setRowCount(totalCount);
			}

			if (sortField != null) {
				Collections.sort(invoiceObj, new CustomerinvoiceLazySorter(sortField,sortOrder));
			}
			setPageSize(howMany);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return invoiceObj;
	}

	@Override
	public Object getRowKey(CustomerinvoiceModel orderdata) {
		return orderdata.getCustomerInvoiceId();
	}

	@Override
	public CustomerinvoiceModel getRowData(String customerId) {
		Integer id = Integer.valueOf(customerId);

		for (CustomerinvoiceModel orderdata : invoiceObj) {
			if (id.equals(orderdata.getCustomerInvoiceId())) {
				return orderdata;
			}
		}

		return null;
	}

}

