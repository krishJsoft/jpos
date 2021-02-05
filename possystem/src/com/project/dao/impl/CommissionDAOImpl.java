package com.project.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.interfaces.ICommissionDAO;
import com.project.model.his.Commission;
import com.project.model.his.Customer;
import com.project.model.his.Doctorprescription;
import com.project.util.StringConstants;

/*
 * @author Gopal Ar
 * @version 1.0
 * @since 15 Jan 2014
 * 
 */

@Repository("commissionRepository")
public class CommissionDAOImpl implements ICommissionDAO {

	public static Logger log = LoggerFactory.getLogger(CommissionDAOImpl.class);
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
	
	@Transactional
	@Override
	public boolean createNewCommission(Commission commission) throws Exception {
		try {
			em.persist(commission);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in createNewCommission CommissionDAOImpl " + e);
			throw e;
		}
	}

	@Transactional
	@Override
	public boolean updateCommission(Commission commission) throws Exception {
		try {
			em.merge(commission);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in updateCommission CommissionDAOImpl " + e);
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Commission> findByCommissionList(Date dateFrom, Date dateTo,
			String status, Integer staffId, int start, int howMany,Integer branchRecordId)
			throws Exception {
		List<Commission> data = null;
		try {			
			StringBuilder buf = new StringBuilder(
					"select dp from Commission dp  inner join fetch dp.branchstaffmember bs   WHERE 1 = 1 and dp.branch.branchId=:branchRecordId  ");

			if ((dateFrom != null) && (dateTo != null)) {
				buf.append("  AND dp.commisionDate between  :dateFrom and :dateTo   ");
			}		
			
			if (staffId != null && staffId !=0) 
			{
				buf.append(" AND  bs.staffId =:staffId ");
			}
			
			if (status != null && status != "" && (!status.equalsIgnoreCase("0")) && (!status.equalsIgnoreCase(""))) 
			{
				buf.append(" AND  dp.status =:status ");
			}

			Query query = em.createQuery(buf.toString());

			if ((dateFrom != null) && (dateTo != null))
			{
				query.setParameter("dateFrom", dateFrom);
				query.setParameter("dateTo", dateTo);
			}		
			if (staffId != null && staffId !=0) 
			{
				query.setParameter("staffId", staffId);
			}

			if (status != null && (!status.trim().equals("")) && (!status.equalsIgnoreCase("0")))  
			{
				query.setParameter("status", status);
			}			

			query.setParameter("branchRecordId", branchRecordId);
			
			
			query.setFirstResult(start);
			query.setMaxResults(howMany);
			data = query.getResultList();
		}	
		catch (Exception e) {
			log.info("Error in findByDoctorclinicList of CommissionDAOImpl "
					+ e.toString());
			throw e;
		} 	
		return data;
	}
	

	

	@SuppressWarnings("unchecked")
	@Override
	public List<Commission> findByCommissionListReport(Date dateFrom, Date dateTo,String status, Integer staffId,Integer branchRecordId)
			throws Exception {
		List<Commission> data = null;
		try {			
			StringBuilder buf = new StringBuilder(
					"select dp from Commission dp  inner join fetch dp.branchstaffmember bs   WHERE 1 = 1 and dp.branch.branchId=:branchRecordId  ");

			if ((dateFrom != null) && (dateTo != null)) {
				buf.append("  AND dp.commisionDate between  :dateFrom and :dateTo   ");
			}		
			
			if (staffId != null && staffId !=0) 
			{
				buf.append(" AND  bs.staffId =:staffId ");
			}
			
			if (status != null && status != "" && (!status.equalsIgnoreCase("0")) && (!status.equalsIgnoreCase(""))) 
			{
				buf.append(" AND  dp.status =:status ");
			}

			Query query = em.createQuery(buf.toString());

			if ((dateFrom != null) && (dateTo != null))
			{
				query.setParameter("dateFrom", dateFrom);
				query.setParameter("dateTo", dateTo);
			}		
			if (staffId != null && staffId !=0) 
			{
				query.setParameter("staffId", staffId);
			}

			if (status != null && (!status.trim().equals("")) && (!status.equalsIgnoreCase("0")))  
			{
				query.setParameter("status", status);
			}			

			query.setParameter("branchRecordId", branchRecordId);			
			
			data = query.getResultList();
		}	
		catch (Exception e) {
			log.info("Error in findByCommissionListReport of CommissionDAOImpl "
					+ e.toString());
			throw e;
		} 	
		return data;
	}
	

	
	@Override
	public Commission getCommissionDetails(Integer commissionId,String commissionType)
			throws Exception {
		Commission commission = null;
		String sqlQuery ="";
		if(commissionType.equalsIgnoreCase("1"))
		{
		sqlQuery = "select c from Commission c  inner join fetch c.branchstaffmember bs inner join fetch c.commissionbreakdowns cb  inner join fetch cb.doctorprescription dp   WHERE c.commissionId = :commissionId ";
		}
		
		if(commissionType.equalsIgnoreCase("2"))
		{
		sqlQuery = "select c from Commission c  inner join fetch c.branchstaffmember bs inner join fetch c.commissionbreakdowns cb  inner join fetch cb.salesorder s WHERE c.commissionId = :commissionId ";
		}
		
		try {
			commission = (Commission) em.createQuery(sqlQuery).setParameter("commissionId", commissionId).getSingleResult();

		} catch (Exception e) {
			log.info("Error in getCommissionDetails CommissionDAOImpl " + e);
			throw e;
		}
		return commission;
	}
	
	
	@Override
	public int getCommissionCount(Date dateFrom, Date dateTo, String status,
			Integer staffId,Integer branchRecordId) throws Exception {
		int countResult = 0;
		try {
			
			StringBuilder buf = new StringBuilder(
					"select COUNT(dp) from Commission dp  WHERE 1 = 1 and dp.branch.branchId=:branchRecordId ");

			if ((dateFrom != null) && (dateTo != null)) {
				buf.append("  AND dp.commisionDate between  :dateFrom and :dateTo   ");
			}		
			
			if (staffId != null && staffId !=0) 
			{
				buf.append(" AND  dp.branchstaffmember.staffId =:staffId ");
			}
			
			if (status != null && status != "" && (!status.equalsIgnoreCase("0")) && (!status.equalsIgnoreCase(""))) 
			{
				buf.append(" AND  dp.status =:status ");
			}

			Query query = em.createQuery(buf.toString());

			if ((dateFrom != null) && (dateTo != null))
			{
				query.setParameter("dateFrom", dateFrom);
				query.setParameter("dateTo", dateTo);
			}		
			if (staffId != null && staffId !=0) 
			{
				query.setParameter("staffId", staffId);
			}

			if (status != null && (!status.trim().equals("")) && (!status.equalsIgnoreCase("0")))  
			{
				query.setParameter("status", status);
			}			
			
			query.setParameter("branchRecordId", branchRecordId);
			
			Number cResults = (Number) query.getSingleResult();
			countResult = Integer.parseInt(String.valueOf(cResults));
			}		
			catch (Exception e) {
			log.info("Error in getCommissionCount of CommissionDAOImpl "
					+ e.toString());
			throw e;
		} 

		return countResult;
	}
		

}
