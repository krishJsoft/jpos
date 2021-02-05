package com.project.dao.interfaces;

import java.util.Date;
import java.util.List;

import com.project.model.his.Commission;

public interface ICommissionDAO {

	
	public boolean createNewCommission(Commission commission) throws Exception;

	public boolean updateCommission(Commission commission) throws Exception;
	
	public int getCommissionCount(Date dateFrom,Date dateTo, String status, Integer staffId,Integer branchRecordId) throws Exception;

	public List<Commission> findByCommissionList(Date dateFrom,Date dateTo, String status, Integer staffId, int start, int howMany,Integer branchRecordId) throws Exception;
		
	public List<Commission> findByCommissionListReport(Date dateFrom, Date dateTo,String status, Integer staffId,Integer branchRecordId) throws Exception;
	
	public Commission getCommissionDetails(Integer commissionId,String commissionType) throws Exception;
	
		
}
