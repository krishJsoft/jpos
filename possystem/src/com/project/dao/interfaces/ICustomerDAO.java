package com.project.dao.interfaces;

import java.util.List;

import com.project.model.datamodel.CustomerModel;
import com.project.model.his.Customer;

/*
 * @author Gopal Ar
 * @version 1.0
 * @since 25 June 2013
 * 
 */


public interface ICustomerDAO {

	
	public boolean createNewCustomer(Customer customer) throws Exception;

	public boolean updateCustomer(Customer customer) throws Exception;

	public boolean deleteCustomer(Customer customer) throws Exception;
	
	public List<Customer> findByCustomerList(String identificationCompanyRegNo,String Status, String customerName,String loyaltyCardCode,int start,int howmany) throws Exception;
	
	public List<Customer> findByCustomerReportList(String identificationCompanyRegNo,String Status, String customerName,String loyaltyCardCode) throws Exception;

	
	public int getCustomerCount(String identificationCompanyRegNo,String Status, String customerName,String loyaltyCardCode) throws Exception;
		
	public List<Customer> getCustomerList(String customerName) throws Exception;
	
	public Customer getCustomerDetails(Integer customerId) throws Exception;
	
    public boolean findCustomerIcExites(String identificationCompanyRegNo) throws Exception;
	
    
}
