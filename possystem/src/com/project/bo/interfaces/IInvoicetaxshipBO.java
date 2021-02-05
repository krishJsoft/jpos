package com.project.bo.interfaces;

import java.util.List;

import com.project.model.invoice.customer.InvoicetaxshipModel;

public interface IInvoicetaxshipBO {

	public boolean createInvoicetaxship(InvoicetaxshipModel invoicetaxship) throws Exception;

	public boolean updateInvoicetaxship(InvoicetaxshipModel invoicetaxship) throws Exception;

	public boolean deleteInvoicetaxship(InvoicetaxshipModel invoicetaxship) throws Exception;	
		
	public InvoicetaxshipModel getInvoicetaxshipDetails(Integer invoiceTaxShipId) throws Exception;
	
	public InvoicetaxshipModel getInvoicetaxshipDetails(String invoiceNo) throws Exception;
	
	public List<InvoicetaxshipModel> getInvoicetaxshipList(String invoiceNo) throws Exception;
}
