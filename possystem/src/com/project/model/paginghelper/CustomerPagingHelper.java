package com.project.model.paginghelper;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.project.bo.interfaces.ICustomerBO;
import com.project.bo.interfaces.IProductBO;
import com.project.model.datamodel.CustomerModel;
import com.project.model.datamodel.ProductModel;

public class CustomerPagingHelper extends LazyDataModel<CustomerModel> {

	private static final long serialVersionUID = 1L;
	private List<CustomerModel> customerObj;

	
	String Status;
	ICustomerBO customerBO;
	Integer totalCount = 0;
	String identificationCompanyRegNo;
	String customerName;
	String loyaltyCardCode;
	

	public CustomerPagingHelper(ICustomerBO customerBO,String identificationCompanyRegNo, String Status,String customerName,String loyaltyCardCode) {
		super();

		this.identificationCompanyRegNo = identificationCompanyRegNo;
		this.customerName = customerName;
		this.loyaltyCardCode = loyaltyCardCode;
		this.Status = Status;
		this.customerBO = customerBO;		
		setRowCount(totalCount);
	}
	
	
	@Override
	public List<CustomerModel> load(int start, int howMany,String sortField, SortOrder sortOrder, Map<String, Object> filters) {
				
		try {			
			customerObj = customerBO.findByCustomerList(identificationCompanyRegNo, Status,customerName,loyaltyCardCode, start, howMany);	
			
			if (getRowCount() <= 0) {
				totalCount = customerBO.getCustomerCount(identificationCompanyRegNo, Status,customerName,loyaltyCardCode);
				setRowCount(totalCount);
			}	
			
			if(sortField != null) {  			     
			  Collections.sort(customerObj, new CustomerLazySorter(sortField, sortOrder));    
			    }    			
			setPageSize(howMany);
			
			/*if (getRowCount() <= 0) {
				searchResultIDList = productBO.getProductCount(categoryId, supplierId, barcode, status,branchRecordId,productName);
				setRowCount(searchResultIDList.length);
			}
			 if(searchResultIDList.length>0){
			productObj = productBO.getProductList(searchResultIDList,null, null, null, null, start, howMany,branchRecordId,null);	
			if (sortField != null) {
				Collections.sort(productObj, new ProductLazySorter(sortField,sortOrder));
			}
			 }			
			setPageSize(howMany);	*/
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return customerObj;
	}

	@Override
	public Object getRowKey(CustomerModel customerdata) {
		return customerdata.getCustomerId();
	}

	@Override
	public CustomerModel getRowData(String customerId) {
		Integer id = Integer.valueOf(customerId);

		for (CustomerModel customer : customerObj) {
			if (id.equals(customer.getCustomerId())) {
				return customer;
			}
		}

		return null;
	}
	
	
    

}
