package com.project.bo.interfaces;

import java.util.Date;
import java.util.List;

import com.project.model.his.Supplierinvoice;

public interface ITest {
	
	public List<Supplierinvoice> findBySupplierinvoiceListAll() throws Exception;

	public int getSupplierinvoiceCount(String invoiceNo,Integer supplierId, Date dateFrom,Date dateTo, String status,Integer branchRecordId) throws Exception;

}
