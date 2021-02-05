package com.project.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.project.dao.interfaces.ICustomerinvoiceDAO;
import com.project.model.his.Customerinvoice;
import com.project.model.his.Customerinvoicebreakdown;
import com.project.model.his.Salesorder;
import com.project.model.his.Salesorderbreakdown;
import com.project.util.StringConstants;


/*
 * @author Gopal Ar
 * @version 1.0
 * @since 20 Sep 2013
 */

@Repository("customerInvoiceRepository")
public class CustomerinvoiceDAOImpl implements ICustomerinvoiceDAO {
	
	public static Logger log = LoggerFactory.getLogger(CustomerinvoiceDAOImpl.class);
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
	public List<Customerinvoice> findByCustomerinvoiceListAll()
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCustomerinvoiceCount(String invoiceNo, Integer customerId,
			Date dateFrom, Date dateTo, String status) throws Exception {
		int countResult = 0;
		try {
			
			StringBuilder buf = new StringBuilder("SELECT COUNT(p) FROM Customerinvoice p   WHERE 1 = 1 ");

			if ((dateFrom != null) && (dateFrom != null)) {
				buf.append("  AND p.invoiceDate between  :dateFrom and :dateTo   ");
			}							
					
			if (invoiceNo != null && invoiceNo != "" && (!invoiceNo.equalsIgnoreCase("0")) && (!invoiceNo.equalsIgnoreCase(""))) 
			{
				buf.append(" AND  p.invoiceNo =:invoiceNo ");
			}
			
			if (customerId !=null && customerId !=0) {
				buf.append(" AND p.customer.customerId =  :customerId  ");
			}			
			
			if (status != null && status != "" && (!status.equalsIgnoreCase("0")) && (!status.equalsIgnoreCase(""))) 
			{
				buf.append(" AND  p.status =:status ");
			}
			
			Query query = em.createQuery(buf.toString());

			if ((dateFrom != null) && (dateTo != null))
			{
				query.setParameter("dateFrom", dateFrom);
				query.setParameter("dateTo", dateTo);
			}
			if (customerId !=null && customerId !=0) 
			{
				query.setParameter("customerId", customerId);
			}							
			if (invoiceNo != null && invoiceNo != "" && (!invoiceNo.equalsIgnoreCase("0")) && (!invoiceNo.equalsIgnoreCase(""))) 
			{
				query.setParameter("invoiceNo", invoiceNo);
			}			
			if (status != null && status != "" && (!status.equalsIgnoreCase("0")) && (!status.equalsIgnoreCase("")))  
			{
				query.setParameter("status", status);
			}				
			Number cResults = (Number) query.getSingleResult();
			countResult = Integer.parseInt(String.valueOf(cResults));
			}		
			catch (Exception e) {
			log.info("Error in getCustomerinvoiceCount of CustomerinvoiceDAOImpl "
					+ e.toString());
			throw e;
		} 

		return countResult;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customerinvoice> getCustomerinvoiceList(String invoiceNo,
			Integer customerId, Date dateFrom, Date dateTo, String status,
			int start, int howMany) throws Exception {
		List<Customerinvoice> data = null;
		try {			
		StringBuilder buf = new StringBuilder("select t from Customerinvoice t inner join fetch t.customer c  WHERE 1 = 1 ");

		if ((dateFrom != null) && (dateFrom != null)) {
			buf.append("  AND t.invoiceDate between  :dateFrom and :dateTo   ");
		}		
		if (customerId !=null && customerId !=0) {
			buf.append(" AND c.customerId =  :customerId  ");
		}					
		if (invoiceNo != null && invoiceNo != "" && (!invoiceNo.equalsIgnoreCase("0")) && (!invoiceNo.equalsIgnoreCase(""))) 
		{
			buf.append(" AND  t.invoiceNo =:invoiceNo ");
		}		
		if (status != null && status != "" && (!status.equalsIgnoreCase("0")) && (!status.equalsIgnoreCase(""))) 
		{
			buf.append(" AND  t.status =:status ");
		}		
		Query query = em.createQuery(buf.toString());

		if ((dateFrom != null) && (dateTo != null))
		{
			query.setParameter("dateFrom", dateFrom);
			query.setParameter("dateTo", dateTo);
		}		
		if (invoiceNo != null && invoiceNo != "" && (!invoiceNo.equalsIgnoreCase("0")) && (!invoiceNo.equalsIgnoreCase(""))) 
		{
			query.setParameter("invoiceNo", invoiceNo);
		}			
		if (customerId !=null && customerId !=0) 
		{
			query.setParameter("customerId", customerId);
		}
				
		if (status != null && status != "" && (!status.equalsIgnoreCase("0")) && (!status.equalsIgnoreCase("")))  
		{
			query.setParameter("status", status);
		}			

		query.setFirstResult(start);
		query.setMaxResults(howMany);
		data = query.getResultList();
		}	
		catch (Exception e) {
		log.info("Error in getCustomerinvoiceList of CustomerinvoiceDAOImpl "	+ e.toString());
		throw e;
	} 	
	return data;
	}

	@Override
	public Customerinvoice getCustomerinvoiceDetails(Integer customerInvoiceId)
			throws Exception {
		Customerinvoice retData = null;
		String sqlQuery = "select t from Customerinvoice t inner join fetch t.customer c inner join fetch t.customerinvoicebreakdowns cib inner join fetch cib.product WHERE t.customerInvoiceId = :customerInvoiceId ";
		try {
			retData = (Customerinvoice) em.createQuery(sqlQuery).setParameter("customerInvoiceId", customerInvoiceId).getSingleResult();

		} catch (Exception e) {
			log.info("Error in getCustomerinvoiceDetails CustomerinvoiceDAOImpl " + e);
			throw e;
		}
		return retData;
	}

	@Override
	public Customerinvoice getCustomerinvoiceMasterDetails(
			Integer customerInvoiceId) throws Exception {
		Customerinvoice retData = null;
		String sqlQuery = "select t from Customerinvoice t inner join fetch t.customer c  WHERE t.customerInvoiceId = :customerInvoiceId ";
		try {
			retData = (Customerinvoice) em.createQuery(sqlQuery).setParameter("customerInvoiceId", customerInvoiceId).getSingleResult();

		} catch (Exception e) {
			log.info("Error in getCustomerinvoiceMasterDetails CustomerinvoiceDAOImpl " + e);
			throw e;
		}
		return retData;
	}

	@Override
	public boolean createNewCustomerinvoice(Customerinvoice customerinvoice)
			throws Exception {
		try {
			em.persist(customerinvoice);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in createNewCustomerinvoice CustomerinvoiceDAOImpl " + e);
			throw e;
		}
	}

	@Override
	public boolean updateCustomerinvoice(Customerinvoice customerinvoice)
			throws Exception {
		try {
			em.merge(customerinvoice);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in updateCustomerinvoice CustomerinvoiceDAOImpl " + e);
			throw e;
		}
	}

	@Override
	public boolean deleteCustomerinvoice(Customerinvoice customerinvoice)
			throws Exception {
		try {
			em.remove(customerinvoice);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in deleteCustomerinvoice CustomerinvoiceDAOImpl " + e);
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customerinvoicebreakdown> getCustomerinvoicebreakdownList(String invoiceNo,String salesOrderNo , int start, int howMany)
			throws Exception {
		List<Customerinvoicebreakdown> salesorder=null;		
		try {			
			StringBuilder buf = new StringBuilder("select t from Customerinvoicebreakdown t inner join fetch t.salesorder inner join fetch t.product WHERE 1 = 1");

			if (invoiceNo != null && invoiceNo != "" && (!invoiceNo.equalsIgnoreCase("0")) && (!invoiceNo.equalsIgnoreCase(""))) 
			{
				buf.append(" AND  t.invoiceNo =:invoiceNo ");
			}	
			
			if (salesOrderNo != null && salesOrderNo != "" && (!salesOrderNo.equalsIgnoreCase("0")) && (!salesOrderNo.equalsIgnoreCase(""))) 
			{
				buf.append(" AND  t.salesOrderNo =:salesOrderNo ");
			}	
			
			Query query = em.createQuery(buf.toString());
			
			if (invoiceNo != null && invoiceNo != "" && (!invoiceNo.equalsIgnoreCase("0")) && (!invoiceNo.equalsIgnoreCase(""))) 
			{
				query.setParameter("invoiceNo", invoiceNo);
			}	
			
			if (salesOrderNo != null && salesOrderNo != "" && (!salesOrderNo.equalsIgnoreCase("0")) && (!salesOrderNo.equalsIgnoreCase(""))) 
			{
				query.setParameter("salesOrderNo", salesOrderNo);
			}	
			
			query.setFirstResult(start);
			query.setMaxResults(howMany);
			salesorder = query.getResultList();
			
			} catch (Exception e) {
				log.info("Error in getCustomerinvoicebreakdownList CustomerinvoiceDAOImpl " + e);
				throw e;
			}
			return salesorder;
	}

	@Override
	public int getCustomerinvoicebreakdownCount(String invoiceNo,String salesOrderNo)
			throws Exception {
		int countResult = 0;
		try {			
			StringBuilder buf = new StringBuilder("SELECT COUNT(p) FROM Customerinvoicebreakdown p  WHERE 1 = 1 ");
			
			if (invoiceNo != null && invoiceNo != "" && (!invoiceNo.equalsIgnoreCase("0")) && (!invoiceNo.equalsIgnoreCase(""))) 
			{
				buf.append(" AND  t.invoiceNo =:invoiceNo ");
			}	
			
			if (salesOrderNo != null && salesOrderNo != "" && (!salesOrderNo.equalsIgnoreCase("0")) && (!salesOrderNo.equalsIgnoreCase(""))) 
			{
				buf.append(" AND  t.salesOrderNo =:salesOrderNo ");
			}	
			
			Query query = em.createQuery(buf.toString());
			
			if (invoiceNo != null && invoiceNo != "" && (!invoiceNo.equalsIgnoreCase("0")) && (!invoiceNo.equalsIgnoreCase(""))) 
			{
				query.setParameter("invoiceNo", invoiceNo);
			}	
			
			if (salesOrderNo != null && salesOrderNo != "" && (!salesOrderNo.equalsIgnoreCase("0")) && (!salesOrderNo.equalsIgnoreCase(""))) 
			{
				query.setParameter("salesOrderNo", salesOrderNo);
			}		
			Number cResults = (Number) query.getSingleResult();
			countResult = Integer.parseInt(String.valueOf(cResults));
			}		
			catch (Exception e) {
			log.info("Error in getCustomerinvoicebreakdownCount of CustomerinvoiceDAOImpl "	+ e.toString());
			throw e;
		} 

		return countResult;
	}

	@Override
	public boolean deleteCustomerinvoicebreakdown(
			Customerinvoicebreakdown customerinvoicebreakdown) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
