package com.project.bo.interfaces;

import java.util.Date;
import java.util.List;

import com.project.model.invoice.customer.CustomerinvoiceModel;
import com.project.model.invoice.customer.CustomerinvoicebreakdownModel;



public interface ICustomerinvoiceBO {

	
	public List<CustomerinvoiceModel> findByCustomerinvoiceListAll() throws Exception;

	public int getCustomerinvoiceCount(String invoiceNo,Integer customerId, Date dateFrom,Date dateTo, String status) throws Exception;

	public List<CustomerinvoiceModel> getCustomerinvoiceList(String invoiceNo,Integer customerId, Date dateFrom,Date dateTo, String status, int start, int howMany)
			throws Exception;		
	
	public CustomerinvoiceModel getCustomerinvoiceDetails(Integer customerInvoiceId) throws Exception;

	public CustomerinvoiceModel getCustomerinvoiceMasterDetails(Integer customerInvoiceId)	throws Exception;
			
	public boolean createNewCustomerinvoice(CustomerinvoiceModel customerinvoice) throws Exception;

	public boolean updateCustomerinvoice(CustomerinvoiceModel customerinvoice) throws Exception;
	
	public boolean approveCustomerinvoice(CustomerinvoiceModel customerinvoice) throws Exception;
	
	public boolean deleteCustomerinvoice(CustomerinvoiceModel customerinvoice) throws Exception;
	
	public List<CustomerinvoicebreakdownModel> getCustomerinvoicebreakdownList(String invoiceNo,String salesOrderNo,int start, int howMany)	throws Exception;
	
	public int getCustomerinvoicebreakdownCount(String invoiceNo,String salesOrderNo) throws Exception;

	public boolean deleteCustomerinvoicebreakdown(CustomerinvoicebreakdownModel customerinvoicebreakdown) throws Exception;	
	
	
}
