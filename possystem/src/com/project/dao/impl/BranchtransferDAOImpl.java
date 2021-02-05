package com.project.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.project.dao.interfaces.IBranchtransferDAO;
import com.project.model.his.Branchtransfer;
import com.project.model.his.Branchtransferbreakdown;
import com.project.util.ConvertUtil;
import com.project.util.StringConstants;

@Repository("branchtransferRepository")
public class BranchtransferDAOImpl implements IBranchtransferDAO {
	
	
	public static Logger log = LoggerFactory.getLogger(BranchtransferDAOImpl.class);
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
	public List<Branchtransfer> findByBranchtransferListAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getBranchtransferCount(String transferNo, Integer branchId,
			Date dateFrom, Date dateTo, String status, Integer branchRecordId,String transferType)
			throws Exception {
		int countResult = 0;
		int[] retList = null;
		try {
			
			StringBuilder buf = new StringBuilder("SELECT  count(q) FROM Branchtransfer q   WHERE 1 = 1  ");

			if ((dateFrom != null) && (dateFrom != null)) {
				buf.append("  AND q.createdDate between  :dateFrom and :dateTo   ");
			}	
			
			if (branchId !=null && branchId !=0) {
				buf.append(" AND q.branch1.branchId =  :branchId  ");
			}	
			if (branchRecordId !=null && branchRecordId !=0) {
				
				if(transferType.equalsIgnoreCase("1"))
				{
				buf.append(" AND q.branch2.branchId =  :branchRecordId  ");
				}
				
				if(transferType.equalsIgnoreCase("2"))
				{
				buf.append(" AND q.branch2.branchId !=  :branchRecordId  ");
				if (status != null  && (status.equalsIgnoreCase("1")) ) 
				{
				buf.append(" AND  t.status !='1' "); // new Request without approved
				}
				}
			}	
			
				
			if (transferNo != null && transferNo != "" && (!transferNo.equalsIgnoreCase("0")) && (!transferNo.equalsIgnoreCase(""))) 
			{
				buf.append(" AND  q.transferNo =:transferNo ");
			}
			
			if (status != null && status != "" && (!status.equalsIgnoreCase("0")) && (!status.equalsIgnoreCase(""))) 
			{
				buf.append(" AND  q.status =:status ");
			}
			
			Query query = em.createQuery(buf.toString());

			if ((dateFrom != null) && (dateTo != null))
			{
				query.setParameter("dateFrom", dateFrom);
				query.setParameter("dateTo", dateTo);
			}
			
			if (branchId !=null && branchId !=0) 
			{
				query.setParameter("branchId", branchId);
			}
			if (transferNo != null && transferNo != "" && (!transferNo.equalsIgnoreCase("0")) && (!transferNo.equalsIgnoreCase(""))) 
			{
				query.setParameter("transferNo", transferNo);
			}			
			if (status != null && status != "" && (!status.equalsIgnoreCase("0")) && (!status.equalsIgnoreCase("")))  
			{
				query.setParameter("status", status);
			}			
			if (branchRecordId !=null && branchRecordId !=0) 
			{
			query.setParameter("branchRecordId", branchRecordId);
			}
			
			Number cResults = (Number) query.getSingleResult();
			countResult = Integer.parseInt(String.valueOf(cResults));		
			}		
			catch (Exception e) {
			log.info("Error in getBranchtransferCount of BranchtransferDAOImpl "
					+ e.toString());
			throw e;
		} 

		return countResult;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Branchtransfer> getBranchtransferList(int[] ids,
			String transferNo, Integer branchId, Date dateFrom, Date dateTo,
			String status, int start, int howMany, Integer branchRecordId,String transferType)
			throws Exception {
		List<Branchtransfer> data = null;
		try {			
		StringBuilder buf = new StringBuilder(
				"select t from Branchtransfer t inner join fetch t.branchtransferbreakdowns prb inner join fetch prb.product inner join fetch t.branch1 c inner join fetch t.branch2  WHERE 1 = 1  ");

		if ((dateFrom != null) && (dateFrom != null)) {
			buf.append("  AND t.createdDate between  :dateFrom and :dateTo   ");
		}		
		if (branchId !=null && branchId !=0) {
			buf.append(" AND c.branchId =  :branchId  ");
		}		
		
		if (ids !=null && ids.length !=0) {
			buf.append("  and t.branchsalesId in (:ids) ");
		}	
		
		if (branchRecordId !=null && branchRecordId !=0) {
			
			if(transferType.equalsIgnoreCase("1"))
			{
			buf.append(" AND t.branch2.branchId =  :branchRecordId  ");
			}
			
			if(transferType.equalsIgnoreCase("2"))
			{
			buf.append(" AND t.branch2.branchId !=  :branchRecordId  ");
			if (status != null  && (status.equalsIgnoreCase("1")) ) 
			{
			buf.append(" AND  t.status !='1' "); // new Request without approved
			}
			}
			
			
		}	
		if (transferNo != null && transferNo != "" && (!transferNo.equalsIgnoreCase("0")) && (!transferNo.equalsIgnoreCase(""))) 
		{
			buf.append(" AND  t.transferNo =:transferNo ");
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
		if (branchId !=null && branchId !=0) 
		{
			query.setParameter("branchId", branchId);
		}
		
		if (ids !=null && ids.length !=0) {
			query.setParameter("ids", ConvertUtil.getIntegerList(ids));
		}
		
		if (transferNo != null && transferNo != "" && (!transferNo.equalsIgnoreCase("0")) && (!transferNo.equalsIgnoreCase(""))) 
		{
			query.setParameter("transferNo", transferNo);
		}			
		if (status != null && status != "" && (!status.equalsIgnoreCase("0")) && (!status.equalsIgnoreCase("")))  
		{
			query.setParameter("status", status);
		}			

		if (branchRecordId !=null && branchRecordId !=0) 
		{
		query.setParameter("branchRecordId", branchRecordId);
		}		
		query.setFirstResult(start);
		query.setMaxResults(howMany);
		data = query.getResultList();
		}	
		catch (Exception e) {
		log.info("Error in getBranchtransferList of BranchtransferDAOImpl "
				+ e.toString());
		throw e;
	} 	
	return data;
	}
	
	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Branchtransfer> getBranchtransferListReport(String transferNo,
			Integer branchId, Date dateFrom, Date dateTo, String status,
			Integer branchRecordId, String branchStatus,String transferType) throws Exception {
		List<Branchtransfer> data = null;
		try {			
		StringBuilder buf = new StringBuilder(
				"select t from Branchtransfer t inner join fetch t.branchtransferbreakdowns prb inner join fetch prb.product inner join fetch t.branch1 c inner join fetch t.branch2 WHERE 1 = 1  ");

		if ((dateFrom != null) && (dateFrom != null)) {
			buf.append("  AND t.createdDate between  :dateFrom and :dateTo   ");
		}		
		if (branchId !=null && branchId !=0) {
			buf.append(" AND c.branchId =  :branchId  ");
		}		
		
		if (branchRecordId !=null && branchRecordId !=0) {
			if(transferType.equalsIgnoreCase("1"))
			{
			buf.append(" AND t.branch2.branchId =  :branchRecordId  ");
			}
			
			if(transferType.equalsIgnoreCase("2"))
			{
			buf.append(" AND t.branch2.branchId !=  :branchRecordId  ");
			buf.append(" AND  t.status !='1' "); // new Request without approved
			}
		}	
		if (transferNo != null && transferNo != "" && (!transferNo.equalsIgnoreCase("0")) && (!transferNo.equalsIgnoreCase(""))) 
		{
			buf.append(" AND  t.salesNo =:salesNo ");
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
		if (branchId !=null && branchId !=0) 
		{
			query.setParameter("branchId", branchId);
		}
		
		
		if (transferNo != null && transferNo != "" && (!transferNo.equalsIgnoreCase("0")) && (!transferNo.equalsIgnoreCase(""))) 
		{
			query.setParameter("transferNo", transferNo);
		}			
		if (status != null && status != "" && (!status.equalsIgnoreCase("0")) && (!status.equalsIgnoreCase("")))  
		{
			query.setParameter("status", status);
		}			

		if (branchRecordId !=null && branchRecordId !=0) 
		{
		query.setParameter("branchRecordId", branchRecordId);
		}		
	
		data = query.getResultList();
		}	
		catch (Exception e) {
		log.info("Error in getBranchtransferListReport of BranchtransferDAOImpl "
				+ e.toString());
		throw e;
	} 	
	return data;
	}
	
	
	
	@Override
	public Branchtransfer getBranchtransferDetails(Integer branchtransferId)
			throws Exception {
		Branchtransfer retData = null;
		String sqlQuery = "select t from Branchtransfer t inner join fetch t.branch1 c inner join fetch t.branch2  inner join fetch t.branchtransferbreakdowns qbd inner join fetch qbd.product WHERE t.branchtransferId = :branchtransferId ";
		try {
			retData = (Branchtransfer) em.createQuery(sqlQuery).setParameter("branchtransferId", branchtransferId).getSingleResult();

		} catch (Exception e) {
			log.info("Error in getBranchtransferDetails BranchtransferDAOImpl " + e);
			throw e;
		}
		return retData;
	}

	@Override
	public boolean createNewBranchtransfer(Branchtransfer branchtransfer)
			throws Exception {
		try {
			em.persist(branchtransfer);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in createNewBranchtransfer BranchtransferDAOImpl " + e);
			throw e;
		}
	}

	@Override
	public boolean updateBranchtransfer(Branchtransfer branchtransfer)
			throws Exception {
		try {
			em.merge(branchtransfer);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in updateBranchtransfer BranchtransferDAOImpl " + e);
			throw e;
		}
	}

	@Override
	public boolean deleteBranchtransfer(Branchtransfer branchtransfer)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Branchtransferbreakdown getBranchtransferbreakdownDetails(
			Integer branchtransferBreakdownId) throws Exception {
		Branchtransferbreakdown retData = null;
		String sqlQuery = "select t from Branchtransferbreakdown t   WHERE t.branchtransferBreakdownId = :branchtransferBreakdownId ";
		try {
			retData = (Branchtransferbreakdown) em.createQuery(sqlQuery).setParameter("branchtransferBreakdownId", branchtransferBreakdownId).getSingleResult();

		} catch (Exception e) {
			log.info("Error in getBranchtransferbreakdownDetails BranchtransferDAOImpl " + e);
			throw e;
		}
		return retData;
	}

	@Override
	public boolean deleteBranchtransferbreakdown(
			Branchtransferbreakdown branchtransferbreakdown) throws Exception {
		try {
			em.remove(branchtransferbreakdown);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in deleteBranchtransferbreakdown BranchtransferDAOImpl " + e);
			throw e;
		}
	}

	


}
