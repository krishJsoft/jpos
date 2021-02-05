package com.project.model.paginghelper;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import com.project.bo.interfaces.ILoyaltyredemptionBO;
import com.project.model.redemption.LoyaltyredemptionModel;

public class LoyaltyredemptionBeanPagingHelper extends LazyDataModel<LoyaltyredemptionModel>{

	private static final long serialVersionUID = 1L;
	private List<LoyaltyredemptionModel> redemObj;
	
	Integer customerId;		
	Date dateFrom;
	Date dateTo;
	String status;
	ILoyaltyredemptionBO redemptionBO;
	Integer totalCount = 0;		
	String loyaltyCode;
	Integer branchRecordId;

	public LoyaltyredemptionBeanPagingHelper(
			Date dateFrom, Date dateTo, String status,Integer customerId,String loyaltyCode, ILoyaltyredemptionBO redemptionBO,Integer branchRecordId) {
		super();

		this.customerId = customerId;		
		this.dateFrom = dateFrom;
		this.dateTo =dateTo;		
		this.status=status;		
		this.redemptionBO=redemptionBO;
		this.loyaltyCode=loyaltyCode;
		this.branchRecordId=branchRecordId;
		setRowCount(totalCount);
	}

	@Override
	public List<LoyaltyredemptionModel> load(int start, int howMany, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		try {
			redemObj = redemptionBO.findByLoyaltyredemptionList(dateFrom, dateTo, status, customerId, loyaltyCode, start, howMany,branchRecordId);
			if (getRowCount() <= 0) {
				totalCount = redemptionBO.getLoyaltyredemptionCount(dateFrom, dateTo, status, customerId, loyaltyCode,branchRecordId);
				setRowCount(totalCount);
			}
			if (sortField != null) {
				Collections.sort(redemObj, new LoyaltyredemptionLazySorter(sortField,sortOrder));
			}
			setPageSize(howMany);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return redemObj;
	}

	@Override
	public Object getRowKey(LoyaltyredemptionModel orderdata) {
		return orderdata.getRedemptionId();
	}

	@Override
	public LoyaltyredemptionModel getRowData(String redemptionId) {
		Integer id = Integer.valueOf(redemptionId);

		for (LoyaltyredemptionModel orderdata : redemObj) {
			if (id.equals(orderdata.getRedemptionId())) {
				return orderdata;
			}
		}

		return null;
	}

}