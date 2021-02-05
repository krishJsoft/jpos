package com.project.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.project.dao.interfaces.IInvoicetaxshipDAO;
import com.project.model.his.Customerinvoice;
import com.project.model.his.Invoicetaxship;
import com.project.util.StringConstants;

/*
 * @author Gopal Ar
 * @version 1.0
 * @since 20 Sep 2013
 */

@Repository("invoicetaxshipRepository")
public class InvoicetaxshipDAOImpl implements IInvoicetaxshipDAO {
	
	public static Logger log = LoggerFactory.getLogger(InvoicetaxshipDAOImpl.class);
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
	public boolean createInvoicetaxship(Invoicetaxship invoicetaxship)
			throws Exception {
		try {
			em.persist(invoicetaxship);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in createInvoicetaxship InvoicetaxshipDAOImpl " + e);
			throw e;
		}
	}

	@Override
	public boolean updateInvoicetaxship(Invoicetaxship invoicetaxship)
			throws Exception {
		try {
			em.merge(invoicetaxship);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in updateInvoicetaxship InvoicetaxshipDAOImpl " + e);
			throw e;
		}
	}

	@Override
	public boolean deleteInvoicetaxship(Invoicetaxship invoicetaxship)
			throws Exception {
		try {
			em.remove(invoicetaxship);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in deleteInvoicetaxship InvoicetaxshipDAOImpl " + e);
			throw e;
		}
	}

	@Override
	public Invoicetaxship getInvoicetaxshipDetails(Integer invoiceTaxShipId)
			throws Exception {
		Invoicetaxship retData = null;
		String sqlQuery = "select t from Invoicetaxship t  WHERE t.invoiceTaxShipId = :invoiceTaxShipId ";
		try {
			retData = (Invoicetaxship) em.createQuery(sqlQuery).setParameter("invoiceTaxShipId", invoiceTaxShipId).getSingleResult();

		} catch (Exception e) {
			log.info("Error in getInvoicetaxshipDetails InvoicetaxshipDAOImpl " + e);
			throw e;
		}
		return retData;
	}

	@Override
	public Invoicetaxship getInvoicetaxshipDetails(String invoiceNo)
			throws Exception {
		Invoicetaxship retData = null;
		String sqlQuery = "select t from Invoicetaxship t  WHERE t.invoiceNo = :invoiceNo ";
		try {
			retData = (Invoicetaxship) em.createQuery(sqlQuery).setParameter("invoiceNo", invoiceNo).getSingleResult();

		} catch (Exception e) {
			log.info("Error in getInvoicetaxshipDetails InvoicetaxshipDAOImpl " + e);
			throw e;
		}
		return retData;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Invoicetaxship> getInvoicetaxshipList(String invoiceNo) throws Exception
	{
		List<Invoicetaxship> retData = null;
		String sqlQuery = "select t from Invoicetaxship t  WHERE t.invoiceNo = :invoiceNo ";
		try {
			retData =  em.createQuery(sqlQuery).setParameter("invoiceNo", invoiceNo).getResultList();

		} catch (Exception e) {
			log.info("Error in getInvoicetaxshipList InvoicetaxshipDAOImpl " + e);
			throw e;
		}
		return retData;
	}

}
