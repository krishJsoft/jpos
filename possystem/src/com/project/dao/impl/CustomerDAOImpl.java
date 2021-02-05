package com.project.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.project.dao.interfaces.ICustomerDAO;
import com.project.model.his.Customer;
import com.project.model.his.Product;
import com.project.util.StringConstants;


/*
 * @author Gopal Ar
 * @version 1.0
 * @since 25 June 2013
 * 
 */

@Repository("customerRepository")
public class CustomerDAOImpl implements ICustomerDAO {
	
	public static Logger log = LoggerFactory.getLogger(CustomerDAOImpl.class);
	long l = 0;
	private EntityManager em = null;

	@PersistenceContext(unitName=StringConstants.HIS_SERVER_PERSISTENCE_NAME)
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	public EntityManager getEm() {
		return em;
	}
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public boolean createNewCustomer(Customer customer) throws Exception {
		try {
			em.persist(customer);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in createNewCustomer CustomerDAOImpl " + e);
			throw e;
		}
	}

	@Override
	public boolean updateCustomer(Customer customer) throws Exception {
		try {
			em.merge(customer);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in updateCustomer CustomerDAOImpl " + e);
			throw e;
		}
	}

	@Override
	public boolean deleteCustomer(Customer customer) throws Exception {
		try {
			em.remove(customer);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in deleteCustomer CustomerDAOImpl " + e);
			throw e;
		}
	}

	
	
	@Override
	public int getCustomerCount(String identificationCompanyRegNo,
			String Status , String customerName,String loyaltyCardCode) throws Exception {
		int countResult = 0;
		int[] retList = null;
		try {
			
			StringBuilder buf = new StringBuilder(
					"SELECT COUNT(b) FROM Customer b   WHERE 1 = 1 And b.customerName !='UNKNOWN' ");
			
			if (identificationCompanyRegNo !=null && (!identificationCompanyRegNo.equalsIgnoreCase(""))) {
				buf.append(" AND b.identificationCompanyRegNo =  :identificationCompanyRegNo ");
			}			
			if (customerName !=null && (!customerName.equalsIgnoreCase(""))) {
				buf.append(" AND b.customerName like :customerName ");
			}			
			if (Status !=null && (!Status.equalsIgnoreCase(""))) {
				buf.append(" And b.status = :status ");
			}				
			
			if (loyaltyCardCode != null && loyaltyCardCode != "" && (!loyaltyCardCode.equalsIgnoreCase("0")) && (!loyaltyCardCode.equalsIgnoreCase("")))  
			{
				buf.append(" AND  b.loyaltyCardCode =:loyaltyCardCode ");
			}			
			
			Query query = em.createQuery(buf.toString());

			if (identificationCompanyRegNo !=null && (!identificationCompanyRegNo.equalsIgnoreCase(""))) {
				query.setParameter("identificationCompanyRegNo", identificationCompanyRegNo);
			}	
			if (customerName !=null && (!customerName.equalsIgnoreCase(""))) {				
				query.setParameter("customerName", customerName+"%");
			}			
			if (Status !=null && (!Status.equalsIgnoreCase(""))) {
				query.setParameter("status", Status);
			}				
			if (loyaltyCardCode != null && loyaltyCardCode != "" && (!loyaltyCardCode.equalsIgnoreCase("0")) && (!loyaltyCardCode.equalsIgnoreCase("")))  
			{
				query.setParameter("loyaltyCardCode", loyaltyCardCode);
			}
			Number cResults = (Number) query.getSingleResult();
			countResult = Integer.parseInt(String.valueOf(cResults));
			

			//List<Integer> retData = query.getResultList();
			//retList = Ints.toArray(retData);		
			
			
			}
		
			catch (Exception e) {
			log.info("Error in getCustomerCount of CustomerDAOImpl "
					+ e.toString());
			throw e;
		} 

		return countResult;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> findByCustomerList(String identificationCompanyRegNo,
			String Status , String customerName,String loyaltyCardCode,int start,int howmany) throws Exception {
		List<Customer> customerList=null;
		StringBuilder buf = new StringBuilder("SELECT b FROM Customer b inner join fetch b.branch br  WHERE 1 = 1 And b.customerName !='UNKNOWN' ");
				
		try {			
		if (identificationCompanyRegNo !=null && (!identificationCompanyRegNo.equalsIgnoreCase(""))) {
			buf.append(" AND b.identificationCompanyRegNo =  :identificationCompanyRegNo ");
		}			
		if (customerName !=null && (!customerName.equalsIgnoreCase(""))) {
			buf.append(" AND b.customerName like  :customerName ");
		}			
		if (Status !=null && (!Status.equalsIgnoreCase(""))) {
			buf.append(" And b.status = :status ");
		}				
		
		if (loyaltyCardCode != null && loyaltyCardCode != "" && (!loyaltyCardCode.equalsIgnoreCase("0")) && (!loyaltyCardCode.equalsIgnoreCase("")))  
		{
			buf.append(" AND  b.loyaltyCardCode =:loyaltyCardCode ");
		}
		
		buf.append(" order by b.customerName");
		
		Query query = em.createQuery(buf.toString());		
		
		if (identificationCompanyRegNo !=null && (!identificationCompanyRegNo.equalsIgnoreCase(""))) {
			query.setParameter("identificationCompanyRegNo", identificationCompanyRegNo);
		}	
		if (customerName !=null && (!customerName.equalsIgnoreCase(""))) {
			query.setParameter("customerName", customerName+"%");
		}			
		if (Status !=null && (!Status.equalsIgnoreCase(""))) {
			query.setParameter("status", Status);
		}				
		if (loyaltyCardCode != null && loyaltyCardCode != "" && (!loyaltyCardCode.equalsIgnoreCase("0")) && (!loyaltyCardCode.equalsIgnoreCase("")))  
		{
			query.setParameter("loyaltyCardCode", loyaltyCardCode);
		}
		query.setFirstResult(start);
		query.setMaxResults(howmany);
		customerList=query.getResultList();
		
		} catch (Exception e) {
			log.info("Error in findByCustomerList CustomerDAOImpl " + e);
			throw e;
		}
		return customerList;
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> findByCustomerReportList(String identificationCompanyRegNo,
			String Status , String customerName,String loyaltyCardCode) throws Exception {
		List<Customer> customerList=null;
		StringBuilder buf = new StringBuilder("SELECT b FROM Customer b inner join fetch b.branch br  WHERE 1 = 1 And b.customerName !='UNKNOWN' ");
				
		try {			
		if (identificationCompanyRegNo !=null && (!identificationCompanyRegNo.equalsIgnoreCase(""))) {
			buf.append(" AND b.identificationCompanyRegNo =  :identificationCompanyRegNo ");
		}			
		if (customerName !=null && (!customerName.equalsIgnoreCase(""))) {
			buf.append(" AND b.customerName like  :customerName ");
		}			
		if (Status !=null && (!Status.equalsIgnoreCase(""))) {
			buf.append(" And b.status = :status ");
		}				
		
		if (loyaltyCardCode != null && loyaltyCardCode != "" && (!loyaltyCardCode.equalsIgnoreCase("0")) && (!loyaltyCardCode.equalsIgnoreCase("")))  
		{
			buf.append(" AND  b.loyaltyCardCode =:loyaltyCardCode ");
		}
		
		buf.append(" order by b.customerName");
		
		Query query = em.createQuery(buf.toString());		
		
		if (identificationCompanyRegNo !=null && (!identificationCompanyRegNo.equalsIgnoreCase(""))) {
			query.setParameter("identificationCompanyRegNo", identificationCompanyRegNo);
		}	
		if (customerName !=null && (!customerName.equalsIgnoreCase(""))) {
			query.setParameter("customerName", customerName+"%");
		}			
		if (Status !=null && (!Status.equalsIgnoreCase(""))) {
			query.setParameter("status", Status);
		}				
		if (loyaltyCardCode != null && loyaltyCardCode != "" && (!loyaltyCardCode.equalsIgnoreCase("0")) && (!loyaltyCardCode.equalsIgnoreCase("")))  
		{
			query.setParameter("loyaltyCardCode", loyaltyCardCode);
		}
		//query.setFirstResult(start);
		//query.setMaxResults(howmany);
		customerList=query.getResultList();
		
		} catch (Exception e) {
			log.info("Error in findByCustomerReportList CustomerDAOImpl " + e);
			throw e;
		}
		return customerList;
	}

	
	
	@Override
	public Customer getCustomerDetails(Integer customerId) throws Exception {
		Customer retData = null;
		String sqlQuery = "select t from Customer t inner join fetch t.branch br  WHERE t.customerId = :customerId ";
		try {
			retData = (Customer) em.createQuery(sqlQuery).setParameter("customerId", customerId).getSingleResult();
		} catch (Exception e) {
			log.info("Error in getCustomerDetails CustomerDAOImpl " + e);
			throw e;
		}
		return retData;
	}

	@Override
	public boolean findCustomerIcExites(String identificationCompanyRegNo)
			throws Exception {
		boolean exits = true;
		try {
			String hslQuery = "from Customer p where p.identificationCompanyRegNo = :identificationCompanyRegNo";
			Query query = em.createQuery(hslQuery).setParameter("identificationCompanyRegNo", identificationCompanyRegNo);
			@SuppressWarnings("unchecked")
			List<Customer> identificationCompanyRegNoExits = query.getResultList();
			if (identificationCompanyRegNoExits.isEmpty()) {
				exits = false;
			}
		} catch (Exception e) {
			log.info("Error in findCustomerIcExites CustomerDAOImpl " + e);
			throw e;
		}
		return exits;
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getCustomerList(String customerName) throws Exception {
		List<Customer> data = null;
		try {			
		StringBuilder buf = new StringBuilder("SELECT p FROM Customer p  WHERE p.customerName like :customerName  ");	
		Query query = em.createQuery(buf.toString());
		query.setParameter("customerName", customerName+"%");			
		data = query.getResultList();
		}	
		catch (Exception e) {
		log.info("Error in getCustomerList By String of CustomerDAOImpl "
				+ e.toString());
		throw e;
	} 	
	return data;
	}

}
