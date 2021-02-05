package com.project.dao.interfaces;

import java.util.Date;
import java.util.List;

import com.project.model.his.Customerinvoice;
import com.project.model.his.Customerinvoicebreakdown;

public interface ICustomerinvoiceDAO {

	
	public List<Customerinvoice> findByCustomerinvoiceListAll() throws Exception;

	public int getCustomerinvoiceCount(String invoiceNo,Integer customerId, Date dateFrom,Date dateTo, String status) throws Exception;

	public List<Customerinvoice> getCustomerinvoiceList(String invoiceNo,Integer customerId, Date dateFrom,Date dateTo, String status, int start, int howMany)
			throws Exception;		
	
	public Customerinvoice getCustomerinvoiceDetails(Integer customerInvoiceId) throws Exception;

	public Customerinvoice getCustomerinvoiceMasterDetails(Integer customerInvoiceId)	throws Exception;
			
	public boolean createNewCustomerinvoice(Customerinvoice customerinvoice) throws Exception;

	public boolean updateCustomerinvoice(Customerinvoice customerinvoice) throws Exception;
	
	public boolean deleteCustomerinvoice(Customerinvoice customerinvoice) throws Exception;
	
	public List<Customerinvoicebreakdown> getCustomerinvoicebreakdownList(String invoiceNo,String salesOrderNo,int start, int howMany)	throws Exception;
	
	public int getCustomerinvoicebreakdownCount(String invoiceNo,String salesOrderNo) throws Exception;

	public boolean deleteCustomerinvoicebreakdown(Customerinvoicebreakdown customerinvoicebreakdown) throws Exception;	
	

}
