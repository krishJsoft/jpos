package com.project.bo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bo.interfaces.ICustomerBO;
import com.project.dao.interfaces.ICustomerDAO;
import com.project.model.datamodel.CustomerModel;
import com.project.model.his.Branch;
import com.project.model.his.Customer;


/*
 * @author Gopal Ar
 * @version 1.0
 * @since 25 June 2013
 * 
 */

@Service("customerBO")
public class CustomerBOImpl implements ICustomerBO {
	
	public static Logger log = LoggerFactory.getLogger(BranchBOImpl.class);

	@Resource(name = "customerRepository")
	private ICustomerDAO customerRepository;
	
	@Transactional
	@Override
	public boolean createNewCustomer(CustomerModel customer) throws Exception {
		boolean saveSuccess = false;
		Customer customerObj = new Customer();
		try {
			
			customerObj.setAddress(customer.getAddress());
			customerObj.setAvailablePoints(customer.getAvailablePoints());
			customerObj.setBalanceAmount(customer.getBalanceAmount());
			customerObj.setBusiness(customer.getBusiness());
			customerObj.setCity(customer.getCity());
			customerObj.setCountry(customer.getCountry());
			customerObj.setCreatedBy(customer.getCreatedBy());
			customerObj.setCreatedDate(customer.getCreatedDate());
			customerObj.setCustomerName(customer.getCustomerName());
			customerObj.setDeliveryMethod(customer.getDeliveryMethod());
			customerObj.setDiscount(customer.getDiscount());
			customerObj.setFaxNo(customer.getFaxNo());
			customerObj.setIdentificationCompanyRegNo(customer.getIdentificationCompanyRegNo());
			customerObj.setLastModifiedDate(customer.getLastModifiedDate());
			customerObj.setLastRedemptionDate(customer.getLastRedemptionDate());
			customerObj.setLoyaltyCardCode(customer.getLoyaltyCardCode());
			customerObj.setMobileNo(customer.getMobileNo());
			customerObj.setNotifyBy(customer.getNotifyBy());
			customerObj.setPaymentMethod(customer.getPaymentMethod());
			customerObj.setPaymentTerm(customer.getPaymentTerm());
			customerObj.setPhoneNo(customer.getPhoneNo());
			customerObj.setPostCode(customer.getPostCode());
			customerObj.setPricingCurrency(customer.getPricingCurrency());
			customerObj.setSalesRep(customer.getSalesRep());
			customerObj.setState(customer.getState());
			customerObj.setTitle(customer.getTitle());		
			customerObj.setStatus(customer.getStatus());
			customerObj.setContactPerson(customer.getContactPerson());
			customerObj.setAgeGroup(customer.getAgeGroup());
			customerObj.setRace(customer.getRace());
			customerObj.setGender(customer.getGender());
			
			
			Branch branch = new Branch();
			branch.setBranchId(customer.getBranchId());			
			customerObj.setBranch(branch);
			
			
			saveSuccess = customerRepository.createNewCustomer(customerObj);			
		}

		catch (Exception e) {
			log.info("Error in createNewCustomer CustomerBOImpl " + e);
			throw e;
		}
		return saveSuccess;
	}
	
	@Transactional
	@Override
	public boolean updateCustomer(CustomerModel customer) throws Exception {
		boolean updateSuccess = false;
		Customer customerObj = customerRepository.getCustomerDetails(customer.getCustomerId());
		try {
			
			customerObj.setAddress(customer.getAddress());
			customerObj.setAvailablePoints(customer.getAvailablePoints());
			customerObj.setBalanceAmount(customer.getBalanceAmount());
			customerObj.setBusiness(customer.getBusiness());
			customerObj.setCity(customer.getCity());
			customerObj.setCountry(customer.getCountry());
			customerObj.setCreatedBy(customer.getCreatedBy());
			customerObj.setCreatedDate(customer.getCreatedDate());
			customerObj.setCustomerName(customer.getCustomerName());
			customerObj.setDeliveryMethod(customer.getDeliveryMethod());
			customerObj.setDiscount(customer.getDiscount());
			customerObj.setFaxNo(customer.getFaxNo());
			customerObj.setIdentificationCompanyRegNo(customer.getIdentificationCompanyRegNo());
			customerObj.setLastModifiedDate(customer.getLastModifiedDate());
			customerObj.setLastRedemptionDate(customer.getLastRedemptionDate());
			customerObj.setLoyaltyCardCode(customer.getLoyaltyCardCode());
			customerObj.setMobileNo(customer.getMobileNo());
			customerObj.setNotifyBy(customer.getNotifyBy());
			customerObj.setPaymentMethod(customer.getPaymentMethod());
			customerObj.setPaymentTerm(customer.getPaymentTerm());
			customerObj.setPhoneNo(customer.getPhoneNo());
			customerObj.setPostCode(customer.getPostCode());
			customerObj.setPricingCurrency(customer.getPricingCurrency());
			customerObj.setSalesRep(customer.getSalesRep());
			customerObj.setState(customer.getState());
			customerObj.setTitle(customer.getTitle());		
			customerObj.setStatus(customer.getStatus());
			customerObj.setContactPerson(customer.getContactPerson());
			Branch branch = new Branch();
			branch.setBranchId(customer.getBranchId());		
			
			customerObj.setAgeGroup(customer.getAgeGroup());
			customerObj.setRace(customer.getRace());
			customerObj.setGender(customer.getGender());
			
			
			customerObj.setBranch(branch);
			
			updateSuccess = customerRepository.updateCustomer(customerObj);			
		}

		catch (Exception e) {
			log.info("Error in updateCustomer CustomerBOImpl " + e);
			throw e;
		}
		return updateSuccess;
	
	}
	
	@Transactional
	@Override
	public boolean deleteCustomer(CustomerModel customer) throws Exception {
		boolean deleteSuccess = false;
		
		try {
			Customer customerObj = customerRepository.getCustomerDetails(customer.getCustomerId());			
			deleteSuccess = customerRepository.deleteCustomer(customerObj);			
			}
		catch (Exception e) {
			log.info("Error in deleteCustomer CustomerBOImpl " + e);
			throw e;
		}
		return deleteSuccess;
	}
	
	
	@Override
	public int getCustomerCount(String identificationCompanyRegNo, String Status,String customerName,String loyaltyCardCode) throws Exception {
		try {
			return customerRepository.getCustomerCount(identificationCompanyRegNo, Status, customerName, loyaltyCardCode);
		} catch (Exception e) {
			log.info("Error in getCustomerCount of CustomerBOImpl "+ e.toString());
			throw e;
		}
	}


	@Override
	public List<CustomerModel> findByCustomerList(String identificationCompanyRegNo, String Status,String customerName,String loyaltyCardCode,int start,int howmany) throws Exception {
		 List<CustomerModel> customerObjList = new ArrayList<CustomerModel>();	
		   List<Customer> customerList = new ArrayList<Customer>();
		try {			
			customerList = customerRepository.findByCustomerList(identificationCompanyRegNo, Status,customerName,loyaltyCardCode,start,howmany);
			
			for (Customer customer : customerList) {
				
			CustomerModel customerObj = new CustomerModel();	
			customerObj.setAddress(customer.getAddress());
			customerObj.setAvailablePoints(customer.getAvailablePoints());
			customerObj.setBalanceAmount(customer.getBalanceAmount());
			customerObj.setBusiness(customer.getBusiness());
			customerObj.setCity(customer.getCity());
			customerObj.setCountry(customer.getCountry());
			customerObj.setCreatedBy(customer.getCreatedBy());
			customerObj.setCreatedDate(customer.getCreatedDate());
			customerObj.setCustomerName(customer.getCustomerName());
			customerObj.setDeliveryMethod(customer.getDeliveryMethod());
			customerObj.setDiscount(customer.getDiscount());
			customerObj.setFaxNo(customer.getFaxNo());
			customerObj.setIdentificationCompanyRegNo(customer.getIdentificationCompanyRegNo());
			customerObj.setIdentificationCompanyOldRegNo(customer.getIdentificationCompanyRegNo());
			customerObj.setLastModifiedDate(customer.getLastModifiedDate());
			customerObj.setLastRedemptionDate(customer.getLastRedemptionDate());
			customerObj.setLoyaltyCardCode(customer.getLoyaltyCardCode());
			customerObj.setMobileNo(customer.getMobileNo());
			customerObj.setNotifyBy(customer.getNotifyBy());
			customerObj.setPaymentMethod(customer.getPaymentMethod());
			customerObj.setPaymentTerm(customer.getPaymentTerm());
			customerObj.setPhoneNo(customer.getPhoneNo());
			customerObj.setPostCode(customer.getPostCode());
			customerObj.setPricingCurrency(customer.getPricingCurrency());
			customerObj.setSalesRep(customer.getSalesRep());
			customerObj.setState(customer.getState());
			customerObj.setTitle(customer.getTitle());				
			customerObj.setCustomerId(customer.getCustomerId());
			customerObj.setBranchId(customer.getBranch().getBranchId());
			customerObj.setBranchName(customer.getBranch().getBranchName());
			customerObj.setStatus(customer.getStatus());
			customerObj.setContactPerson(customer.getContactPerson());
			customerObj.setPhotoName(customer.getIdentificationCompanyRegNo() + ".jpg");	
			customerObj.setAgeGroup(customer.getAgeGroup());
			customerObj.setRace(customer.getRace());
			customerObj.setGender(customer.getGender());
			
			
			customerObjList.add(customerObj);
			}
		}
		catch (Exception e) {
			log.info("Error in findByCustomerList CustomerBOImpl " + e);
			throw e;
		}
		return customerObjList;
	}

	@Override
	public List<CustomerModel> findByCustomerReportList(String identificationCompanyRegNo, String Status,String customerName,String loyaltyCardCode) throws Exception {
		 List<CustomerModel> customerObjList = new ArrayList<CustomerModel>();	
		   List<Customer> customerList = new ArrayList<Customer>();
		try {			
			customerList = customerRepository.findByCustomerReportList(identificationCompanyRegNo, Status,customerName,loyaltyCardCode);
			
			for (Customer customer : customerList) {
				
			CustomerModel customerObj = new CustomerModel();	
			customerObj.setAddress(customer.getAddress());
			customerObj.setAvailablePoints(customer.getAvailablePoints());
			customerObj.setBalanceAmount(customer.getBalanceAmount());
			customerObj.setBusiness(customer.getBusiness());
			customerObj.setCity(customer.getCity());
			customerObj.setCountry(customer.getCountry());
			customerObj.setCreatedBy(customer.getCreatedBy());
			customerObj.setCreatedDate(customer.getCreatedDate());
			customerObj.setCustomerName(customer.getCustomerName());
			customerObj.setDeliveryMethod(customer.getDeliveryMethod());
			customerObj.setDiscount(customer.getDiscount());
			customerObj.setFaxNo(customer.getFaxNo());
			customerObj.setIdentificationCompanyRegNo(customer.getIdentificationCompanyRegNo());
			customerObj.setIdentificationCompanyOldRegNo(customer.getIdentificationCompanyRegNo());
			customerObj.setLastModifiedDate(customer.getLastModifiedDate());
			customerObj.setLastRedemptionDate(customer.getLastRedemptionDate());
			customerObj.setLoyaltyCardCode(customer.getLoyaltyCardCode());
			customerObj.setMobileNo(customer.getMobileNo());
			customerObj.setNotifyBy(customer.getNotifyBy());
			customerObj.setPaymentMethod(customer.getPaymentMethod());
			customerObj.setPaymentTerm(customer.getPaymentTerm());
			customerObj.setPhoneNo(customer.getPhoneNo());
			customerObj.setPostCode(customer.getPostCode());
			customerObj.setPricingCurrency(customer.getPricingCurrency());
			customerObj.setSalesRep(customer.getSalesRep());
			customerObj.setState(customer.getState());
			customerObj.setTitle(customer.getTitle());				
			customerObj.setCustomerId(customer.getCustomerId());
			customerObj.setBranchId(customer.getBranch().getBranchId());
			customerObj.setBranchName(customer.getBranch().getBranchName());
			customerObj.setStatus(customer.getStatus());
			customerObj.setContactPerson(customer.getContactPerson());
			customerObj.setPhotoName(customer.getIdentificationCompanyRegNo() + ".jpg");	
			customerObj.setAgeGroup(customer.getAgeGroup());
			customerObj.setRace(customer.getRace());
			customerObj.setGender(customer.getGender());
			
			
			customerObjList.add(customerObj);
			}
		}
		catch (Exception e) {
			log.info("Error in findByCustomerList CustomerBOImpl " + e);
			throw e;
		}
		return customerObjList;
	}
	
	
	@Override
	public List<CustomerModel> getCustomerList(String customerName) throws Exception {
		 List<CustomerModel> customerObjList = new ArrayList<CustomerModel>();	
		   List<Customer> customerList = new ArrayList<Customer>();
		try {			
			customerList = customerRepository.getCustomerList(customerName);
			
			for (Customer customer : customerList) {
				
			CustomerModel customerObj = new CustomerModel();	
			customerObj.setAddress(customer.getAddress());
			customerObj.setAvailablePoints(customer.getAvailablePoints());
			customerObj.setBalanceAmount(customer.getBalanceAmount());
			customerObj.setBusiness(customer.getBusiness());
			customerObj.setCity(customer.getCity());
			customerObj.setCountry(customer.getCountry());
			customerObj.setCreatedBy(customer.getCreatedBy());
			customerObj.setCreatedDate(customer.getCreatedDate());
			customerObj.setCustomerName(customer.getCustomerName());
			customerObj.setDeliveryMethod(customer.getDeliveryMethod());
			customerObj.setDiscount(customer.getDiscount());
			customerObj.setFaxNo(customer.getFaxNo());
			customerObj.setIdentificationCompanyRegNo(customer.getIdentificationCompanyRegNo());
			customerObj.setIdentificationCompanyOldRegNo(customer.getIdentificationCompanyRegNo());
			customerObj.setLastModifiedDate(customer.getLastModifiedDate());
			customerObj.setLastRedemptionDate(customer.getLastRedemptionDate());
			customerObj.setLoyaltyCardCode(customer.getLoyaltyCardCode());
			customerObj.setMobileNo(customer.getMobileNo());
			customerObj.setNotifyBy(customer.getNotifyBy());
			customerObj.setPaymentMethod(customer.getPaymentMethod());
			customerObj.setPaymentTerm(customer.getPaymentTerm());
			customerObj.setPhoneNo(customer.getPhoneNo());
			customerObj.setPostCode(customer.getPostCode());
			customerObj.setPricingCurrency(customer.getPricingCurrency());
			customerObj.setSalesRep(customer.getSalesRep());
			customerObj.setState(customer.getState());
			customerObj.setTitle(customer.getTitle());				
			customerObj.setCustomerId(customer.getCustomerId());			
			customerObj.setStatus(customer.getStatus());
			customerObj.setContactPerson(customer.getContactPerson());
			customerObj.setPhotoName(customer.getIdentificationCompanyRegNo() + ".jpg");	
			customerObj.setAgeGroup(customer.getAgeGroup());
			customerObj.setRace(customer.getRace());
			customerObj.setGender(customer.getGender());
			
			customerObjList.add(customerObj);
			}
		}
		catch (Exception e) {
			log.info("Error in findByCustomerList CustomerBOImpl " + e);
			throw e;
		}
		return customerObjList;
	}

	
	
	
	
	@Override
	public CustomerModel getCustomerDetails(Integer customerId)
			throws Exception {
		     CustomerModel customerObj = new CustomerModel();	
			
			try {			
				Customer customer = customerRepository.getCustomerDetails(customerId);
				
				customerObj.setAddress(customer.getAddress());
				customerObj.setAvailablePoints(customer.getAvailablePoints());
				customerObj.setBalanceAmount(customer.getBalanceAmount());
				customerObj.setBusiness(customer.getBusiness());
				customerObj.setCity(customer.getCity());
				customerObj.setCountry(customer.getCountry());
				customerObj.setCreatedBy(customer.getCreatedBy());
				customerObj.setCreatedDate(customer.getCreatedDate());
				customerObj.setCustomerName(customer.getCustomerName());
				customerObj.setDeliveryMethod(customer.getDeliveryMethod());
				customerObj.setDiscount(customer.getDiscount());
				customerObj.setFaxNo(customer.getFaxNo());
				customerObj.setIdentificationCompanyRegNo(customer.getIdentificationCompanyRegNo());
				customerObj.setIdentificationCompanyOldRegNo(customer.getIdentificationCompanyRegNo());
				customerObj.setLastModifiedDate(customer.getLastModifiedDate());
				customerObj.setLastRedemptionDate(customer.getLastRedemptionDate());
				customerObj.setLoyaltyCardCode(customer.getLoyaltyCardCode());
				customerObj.setMobileNo(customer.getMobileNo());
				customerObj.setNotifyBy(customer.getNotifyBy());
				customerObj.setPaymentMethod(customer.getPaymentMethod());
				customerObj.setPaymentTerm(customer.getPaymentTerm());
				customerObj.setPhoneNo(customer.getPhoneNo());
				customerObj.setPostCode(customer.getPostCode());
				customerObj.setPricingCurrency(customer.getPricingCurrency());
				customerObj.setSalesRep(customer.getSalesRep());
				customerObj.setState(customer.getState());
				customerObj.setTitle(customer.getTitle());				
				customerObj.setCustomerId(customer.getCustomerId());
				customerObj.setStatus(customer.getStatus());
				customerObj.setBranchId(customer.getBranch().getBranchId());
				customerObj.setBranchName(customer.getBranch().getBranchName());
				customerObj.setContactPerson(customer.getContactPerson());
				customerObj.setAgeGroup(customer.getAgeGroup());
				customerObj.setRace(customer.getRace());
				customerObj.setGender(customer.getGender());
				customerObj.setPhotoName(customer.getIdentificationCompanyRegNo() + ".jpg");	
				
			}
			catch (Exception e) {
				log.info("Error in getCustomerDetails CustomerBOImpl " + e);
				throw e;
			}
			return customerObj;
	}

	@Override
	public boolean findCustomerIcExites(String identificationCompanyRegNo)
			throws Exception {
		boolean exists = false;		
		try {
			exists = customerRepository.findCustomerIcExites(identificationCompanyRegNo);
		}
		catch (Exception e) {
			log.info("Error in findCustomerIcExites CustomerBOImpl " + e);
			throw e;
		}
		return exists;
	}

}
