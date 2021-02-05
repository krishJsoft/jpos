package com.project.model.paginghelper;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.project.bo.interfaces.ICommissionBO;
import com.project.model.commission.CommissionModel;

/*
 * @author Gopal Ar
 * @version 1.0
 * @since 15 Jan 2014
 * 
 */

public class CommissionPagingHelper extends LazyDataModel<CommissionModel>{

	private static final long serialVersionUID = 1L;
	private List<CommissionModel> commissionObj;
	
	Integer staffId;		
	Date dateFrom;
	Date dateTo;
	String status;
	ICommissionBO commissionBO;
	Integer totalCount = 0;		
	Integer branchRecordId;	

	public CommissionPagingHelper(
			Date dateFrom, Date dateTo, String status,Integer staffId, ICommissionBO commissionBO,Integer branchRecordId) {
		super();

		this.staffId = staffId;		
		this.dateFrom = dateFrom;
		this.dateTo =dateTo;		
		this.status=status;		
		this.commissionBO=commissionBO;
		this.branchRecordId=branchRecordId;
		setRowCount(totalCount);
	}

	@Override
	public List<CommissionModel> load(int start, int howMany, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		try {
			commissionObj = commissionBO.findByCommissionList(dateFrom, dateTo, status, staffId, start, howMany,branchRecordId);
			if (getRowCount() <= 0) {
				totalCount = commissionBO.getCommissionCount(dateFrom, dateTo, status, staffId,branchRecordId);
				setRowCount(totalCount);
			}
			if (sortField != null) {
				Collections.sort(commissionObj, new CommissionLazySorter(sortField,sortOrder));
			}
			setPageSize(howMany);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commissionObj;
	}

	@Override
	public Object getRowKey(CommissionModel orderdata) {
		return orderdata.getCommissionId();
	}

	@Override
	public CommissionModel getRowData(String commissionId) {
		Integer id = Integer.valueOf(commissionId);

		for (CommissionModel orderdata : commissionObj) {
			if (id.equals(orderdata.getCommissionId())) {
				return orderdata;
			}
		}

		return null;
	}

}