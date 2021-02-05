package com.project.bo.interfaces;

import java.util.List;

import com.project.model.datamodel.CustomerModel;

/*
 * @author Gopal Ar
 * @version 1.0
 * @since 25 June 2013
 * 
 */


public interface ICustomerBO {

	public boolean createNewCustomer(CustomerModel customer) throws Exception;

	public boolean updateCustomer(CustomerModel customer) throws Exception;

	public boolean deleteCustomer(CustomerModel customer) throws Exception;
	
	public List<CustomerModel> findByCustomerList(String identificationCompanyRegNo,String Status  , String customerName,String loyaltyCardCode,int start , int howmany) throws Exception;
	
	public List<CustomerModel> findByCustomerReportList(String identificationCompanyRegNo,String Status  , String customerName,String loyaltyCardCode) throws Exception;
	
	public int getCustomerCount(String identificationCompanyRegNo,String Status , String customerName,String loyaltyCardCode) throws Exception;
			
	public List<CustomerModel> getCustomerList(String customerName) throws Exception;
	
	public CustomerModel getCustomerDetails(Integer customerId) throws Exception;
	
    public boolean findCustomerIcExites(String identificationCompanyRegNo) throws Exception;
	
}
