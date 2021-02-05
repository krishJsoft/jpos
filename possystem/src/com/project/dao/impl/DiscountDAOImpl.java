package com.project.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.project.dao.interfaces.IDiscountDAO;
import com.project.model.his.Discount;
import com.project.model.his.Discountremarks;
import com.project.util.StringConstants;

@Repository("discountRepository")
public class DiscountDAOImpl implements IDiscountDAO {
	
	public static Logger log = LoggerFactory.getLogger(DiscountDAOImpl.class);
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
	public List<Discount> fetchDiscountList(int branchId) throws Exception {
		List<Discount> dataList=new ArrayList<Discount>();
		try {
			String hsql="SELECT d FROM Discount d INNER JOIN FETCH d.branch b WHERE b.branchId=:branchId";
			Query query=em.createQuery(hsql);
			query.setParameter("branchId", branchId);
			dataList=query.getResultList();
			
		}catch (Exception ex) {
			log.info("Error in fetchDiscountList DiscountDAOImpl " + ex);
			throw ex;
		}
		return dataList;
	}

	@Override
	public List<Discountremarks> fetchDiscountRemarksList(int branchId) throws Exception {
		List<Discountremarks> dataList=new ArrayList<Discountremarks>();
		try {
			String hsql="SELECT dr FROM Discountremarks dr INNER JOIN FETCH dr.branch b WHERE b.branchId=:branchId";
			Query query=em.createQuery(hsql);
			query.setParameter("branchId", branchId);
			dataList=query.getResultList();
			
		}catch (Exception ex) {
			log.info("Error in fetchDiscountRemarksList DiscountDAOImpl " + ex);
			throw ex;
		}
		return dataList;
	}
	
	
}
