package com.project.bo.interfaces;

import java.util.Date;
import java.util.List;

import com.project.model.sale.sales.branchsale.BranchsaleModel;
import com.project.model.sale.sales.branchsale.BranchsalesbreakdownModel;


public interface IBranchsalesBO {

	
public int getBranchsaleCount(String salesNo, Integer branchId, Date dateFrom,Date dateTo, String status,Integer branchRecordId) throws Exception;
	
	public BranchsaleModel getBranchsaleDetails(Integer branchsalesId) throws Exception;

	public boolean createNewBranchsale(BranchsaleModel branchsale) throws Exception;

	public boolean updateBranchsale(BranchsaleModel branchsale) throws Exception;
	
	public boolean updateMasterBranchsale(BranchsaleModel branchsale) throws Exception;
	
	public boolean deleteBranchsale(BranchsaleModel branchsale) throws Exception;
	
	public BranchsaleModel getBranchsaleDetailsByNo(String salesNo) throws Exception;
	
	public BranchsalesbreakdownModel getBranchsalesbreakdownDetails(Integer branchsalesBreakdownId) throws Exception;
	
	public boolean deleteBranchsalesbreakdown(BranchsalesbreakdownModel branchsalesbreakdown) throws Exception;

	public List<BranchsaleModel> getBranchsaleList(int[] ids,String salesNo, Integer branchId,Date dateFrom, Date dateTo, String status, int start, int howMany,Integer branchRecordId)	throws Exception;
	
	public List<BranchsaleModel> getBranchsaleListReport(String salesNo, Integer branchId, Date dateFrom,Date dateTo, String status,Integer branchRecordId,String branchStatus)	throws Exception;

	public BranchsalesbreakdownModel getBranchsalesbreakdown(Integer branchsalesBreakdownId) throws Exception;
	
	public List<BranchsaleModel> getBranchsaleByCuctomerList(Integer branchId, String status,Integer branchRecordId) throws Exception;

	public List<BranchsalesbreakdownModel> getBranchsalesbreakdownCustomer(Integer branchId) throws Exception;
	
	public boolean approveBranchsales(BranchsaleModel branchsales)	throws Exception;
	
}
