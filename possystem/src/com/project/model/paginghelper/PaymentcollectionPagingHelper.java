package com.project.model.paginghelper;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.project.bo.interfaces.IPaymentcollectionBO;
import com.project.model.payment.collection.PaymentcollectionModel;


public class PaymentcollectionPagingHelper extends LazyDataModel<PaymentcollectionModel>{

	private static final long serialVersionUID = 1L;
	private List<PaymentcollectionModel> collectionObj;
	
	Integer customerId;	
	Integer branchId;	
	String paymentNo;
	Date dateFrom;
	Date dateTo;
	String status;
	IPaymentcollectionBO paymentcollectionBO;
	Integer totalCount = 0;		
	Integer branchRecordId;
	int[] searchResultIDList;

	public PaymentcollectionPagingHelper(String paymentNo, Integer branchId, Integer customerId,
			Date dateFrom, Date dateTo, String status,  IPaymentcollectionBO paymentcollectionBO,Integer branchRecordId) {
		super();

		this.branchId = branchId;
		this.customerId = customerId;
		this.dateFrom = dateFrom;
		this.dateTo =dateTo;		
		this.status=status;
		this.paymentNo = paymentNo;
		this.paymentcollectionBO=paymentcollectionBO;
		this.branchRecordId=branchRecordId;
		setRowCount(totalCount);
	}

	@Override
	public List<PaymentcollectionModel> load(int start, int howMany, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		try {
			/*collectionObj = paymentcollectionBO.getPaymentcollectionList(paymentNo, branchId, customerId, dateFrom, dateTo, status, start, howMany,branchRecordId);

			if (getRowCount() <= 0) {
				totalCount = paymentcollectionBO.getPaymentcollectionCount(paymentNo, branchId, customerId, dateFrom, dateTo, status,branchRecordId);
				setRowCount(totalCount);
			}

			if (sortField != null) {
				Collections.sort(collectionObj, new PaymentcollectionLazySorter(sortField,sortOrder));
			}
			setPageSize(howMany);*/
			
			if (getRowCount() <= 0) {
				totalCount = paymentcollectionBO.getPaymentcollectionCount(paymentNo, branchId, customerId, dateFrom, dateTo, status,branchRecordId);
				setRowCount(totalCount);
			}
			 if(totalCount>0){
			 collectionObj = paymentcollectionBO.getPaymentcollectionList(searchResultIDList,paymentNo, branchId, customerId, dateFrom, dateTo, status, start, howMany,branchRecordId);
			if (sortField != null) {
				Collections.sort(collectionObj, new PaymentcollectionLazySorter(sortField,sortOrder));
			}
			 }			
			setPageSize(howMany);	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return collectionObj;
	}

	@Override
	public Object getRowKey(PaymentcollectionModel orderdata) {
		return orderdata.getCollectionId();
	}

	@Override
	public PaymentcollectionModel getRowData(String collectionId) {
		Integer id = Integer.valueOf(collectionId);

		for (PaymentcollectionModel orderdata : collectionObj) {
			if (id.equals(orderdata.getCollectionId())) {
				return orderdata;
			}
		}

		return null;
	}

}