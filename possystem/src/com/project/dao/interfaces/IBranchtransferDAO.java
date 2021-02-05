package com.project.dao.interfaces;

import java.util.Date;
import java.util.List;

import com.project.model.his.Branchtransfer;
import com.project.model.his.Branchtransferbreakdown;



public interface IBranchtransferDAO {

	
	public List<Branchtransfer> findByBranchtransferListAll() throws Exception;
	
	public int getBranchtransferCount(String transferNo, Integer branchId, Date dateFrom,Date dateTo, String status,Integer branchRecordId,String transferType) throws Exception;
	
	public Branchtransfer getBranchtransferDetails(Integer branchtransferId) throws Exception;

	public boolean createNewBranchtransfer(Branchtransfer branchtransfer) throws Exception;

	public boolean updateBranchtransfer(Branchtransfer branchtransfer) throws Exception;
	
	public boolean deleteBranchtransfer(Branchtransfer branchtransfer) throws Exception;	
	
	public Branchtransferbreakdown getBranchtransferbreakdownDetails(Integer branchtransferBreakdownId) throws Exception;
	
	public boolean deleteBranchtransferbreakdown(Branchtransferbreakdown branchtransferbreakdown) throws Exception;

	public List<Branchtransfer> getBranchtransferList(int[] ids,String transferNo, Integer branchId,Date dateFrom, Date dateTo, String status, int start, int howMany,Integer branchRecordId,String transferType)	throws Exception;
	
	public List<Branchtransfer> getBranchtransferListReport(String transferNo, Integer branchId, Date dateFrom,Date dateTo, String status,Integer branchRecordId,String branchStatus,String transferType)	throws Exception;

}
