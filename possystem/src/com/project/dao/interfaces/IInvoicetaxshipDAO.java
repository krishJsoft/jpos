package com.project.dao.interfaces;

import java.util.List;

import com.project.model.his.Invoicetaxship;

public interface IInvoicetaxshipDAO {

	
	public boolean createInvoicetaxship(Invoicetaxship invoicetaxship) throws Exception;

	public boolean updateInvoicetaxship(Invoicetaxship invoicetaxship) throws Exception;

	public boolean deleteInvoicetaxship(Invoicetaxship invoicetaxship) throws Exception;	
		
	public Invoicetaxship getInvoicetaxshipDetails(Integer invoiceTaxShipId) throws Exception;
	
	public Invoicetaxship getInvoicetaxshipDetails(String invoiceNo) throws Exception;
	
	public List<Invoicetaxship> getInvoicetaxshipList(String invoiceNo) throws Exception;
	
}
