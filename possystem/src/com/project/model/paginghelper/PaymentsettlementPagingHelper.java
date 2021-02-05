package com.project.model.paginghelper;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.project.bo.interfaces.IPaymentcollectionBO;
import com.project.bo.interfaces.IPaymentsettlementBO;
import com.project.model.payment.collection.PaymentcollectionModel;
import com.project.model.payment.settlement.PaymentsettlementModel;

public class PaymentsettlementPagingHelper extends LazyDataModel<PaymentsettlementModel>{

	private static final long serialVersionUID = 1L;
	private List<PaymentsettlementModel> collectionObj;
	
	Integer supplierId;	
	Integer branchId;	
	String paymentNo;
	Date dateFrom;
	Date dateTo;
	String status;
	IPaymentsettlementBO paymentsettlementBO;
	Integer totalCount = 0;		
	Integer branchRecordId;
	Integer supplyBranchId;
	int[] searchResultIDList;

	public PaymentsettlementPagingHelper(String paymentNo, Integer supplierId,
			Date dateFrom, Date dateTo, String status,  IPaymentsettlementBO paymentsettlementBO,Integer branchRecordId,Integer supplyBranchId) {
		super();

		this.supplierId = supplierId;
		this.dateFrom = dateFrom;
		this.dateTo =dateTo;		
		this.status=status;
		this.paymentNo = paymentNo;
		this.paymentsettlementBO=paymentsettlementBO;
		this.branchRecordId=branchRecordId;
		this.supplyBranchId=supplyBranchId;
		setRowCount(totalCount);
	}

	@Override
	public List<PaymentsettlementModel> load(int start, int howMany, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		try {
			/*collectionObj = paymentsettlementBO.getPaymentsettlementList(paymentNo, supplierId,  dateFrom, dateTo, status, start, howMany,branchRecordId);

			if (getRowCount() <= 0) {
				totalCount = paymentsettlementBO.getPaymentsettlementCount(paymentNo, supplierId,  dateFrom, dateTo, status,branchRecordId);
				setRowCount(totalCount);
			}

			if (sortField != null) {
				Collections.sort(collectionObj, new PaymentsettlementLazySorter(sortField,sortOrder));
			}
			setPageSize(howMany);*/
			
			
			if (getRowCount() <= 0) {
				totalCount = paymentsettlementBO.getPaymentsettlementCount(paymentNo, supplierId,  dateFrom, dateTo, status,branchRecordId,supplyBranchId);
				setRowCount(totalCount);
			}
			 if(totalCount>0){
				 collectionObj =  paymentsettlementBO.getPaymentsettlementList(searchResultIDList,paymentNo, supplierId,  dateFrom, dateTo, status, start, howMany,branchRecordId,supplyBranchId);
			if (sortField != null) {
				Collections.sort(collectionObj, new PaymentsettlementLazySorter(sortField,sortOrder));
			}
			 }			
			setPageSize(howMany);	
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return collectionObj;
	}

	@Override
	public Object getRowKey(PaymentsettlementModel orderdata) {
		return orderdata.getSettlementId();
	}

	@Override
	public PaymentsettlementModel getRowData(String collectionId) {
		Integer id = Integer.valueOf(collectionId);

		for (PaymentsettlementModel orderdata : collectionObj) {
			if (id.equals(orderdata.getSettlementId())) {
				return orderdata;
			}
		}

		return null;
	}

}