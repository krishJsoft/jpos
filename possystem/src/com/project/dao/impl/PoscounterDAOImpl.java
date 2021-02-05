package com.project.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.project.dao.interfaces.IPoscounterDAO;
import com.project.model.his.Poscashtransaction;
import com.project.model.his.Poscounter;
import com.project.util.StringConstants;

/*
 * @author Gopal Ar
 * @version 1.0
 * @since 23 Nov 2013
 * 
 */

@Repository("poscounterRepository")
public class PoscounterDAOImpl implements IPoscounterDAO {
	
	public static Logger log = LoggerFactory.getLogger(PoscounterDAOImpl.class);
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
	public boolean createNewPoscounter(Poscounter poscounter) throws Exception {
		try {
			em.persist(poscounter);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in createNewPoscounter PoscounterDAOImpl " + e);
			throw e;
		}
	}

	@Override
	public boolean updatePoscounter(Poscounter poscounter) throws Exception {
		try {
			em.merge(poscounter);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in updatePoscounter PoscounterDAOImpl " + e);
			throw e;
		}
	}

	@Override
	public boolean deletePoscounter(Poscounter poscounter) throws Exception {
		try {
			em.remove(poscounter);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in deletePoscounter PoscounterDAOImpl " + e);
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Poscounter> findByPoscounterList(Integer branchId) throws Exception {
		List<Poscounter> poscounter=null;
		StringBuilder buf = new StringBuilder("SELECT b FROM Poscounter b where b.branch.branchId=:branchId  ");				
		try {		
		Query query = em.createQuery(buf.toString());	
		poscounter=query.setParameter("branchId", branchId).getResultList();		
		} 
		catch (Exception e) {
			log.info("Error in findByAdmindespatchList PoscounterDAOImpl " + e);
			throw e;
		}
		return poscounter;
	}

	@Override
	public Poscounter getPoscounterDetails(Integer counterId) throws Exception {
		Poscounter retData = null;
		String sqlQuery = "select t from Poscounter t  WHERE t.counterId = :counterId ";
		try {
			retData = (Poscounter) em.createQuery(sqlQuery).setParameter("counterId", counterId).getSingleResult();

		} catch (Exception e) {
			log.info("Error in getPoscounterDetails PoscounterDAOImpl " + e);
			throw e;
		}
		return retData;
	}

	@Override
	public boolean findPoscounterExites(String counterNo,Integer branchId) throws Exception {
		boolean exits = true;
		try {
			String hslQuery = "from Poscounter p where p.counterNo = :counterNo and  p.branch.branchId=:branchId";
			Query query = em.createQuery(hslQuery).setParameter("counterNo", counterNo).setParameter("branchId", branchId) ;
			@SuppressWarnings("unchecked")
			List<Poscounter> PoscounterExits = query.getResultList();
			if (PoscounterExits.isEmpty()) {
				exits = false;
			}
		} catch (Exception e) {
			log.info("Error in findPoscounterExites PoscounterDAOImpl " + e);
			throw e;
		}
		return exits;
	
	}
	
	@Override
	public boolean createNewPoscashtransaction(
			Poscashtransaction poscashtransaction) throws Exception {
		try {
			em.persist(poscashtransaction);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in createNewPoscashtransaction PoscounterDAOImpl " + e);
			throw e;
		}
	}

	@Override
	public boolean updatePoscashtransaction(Poscashtransaction poscashtransaction) throws Exception {
		try {
			em.merge(poscashtransaction);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in updatePoscashtransaction PoscounterDAOImpl " + e);
			throw e;
		}
	}

	
	@Override
	public boolean updatePosCounterClose(String stringQuery) throws Exception {
		try {
			Query query = em.createQuery(stringQuery.toString());
			query.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in updatePosCounterClose PoscounterDAOImpl " + e);
			throw e;
		}
	}
	
	
	@Override
	public Poscashtransaction getPoscashtransactionDetails(Integer cashid) throws Exception {
		Poscashtransaction retData = null;
		String sqlQuery = "select t from Poscashtransaction t inner join fetch t.poscounter c  WHERE t.cashid = :cashid ";
		try {
			retData = (Poscashtransaction) em.createQuery(sqlQuery).setParameter("cashid", cashid).getSingleResult();

		} catch (Exception e) {
			log.info("Error in getPoscashtransactionDetails PoscounterDAOImpl " + e);
			throw e;
		}
		return retData;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Poscashtransaction> findByPoscashtransactionList(Integer counterId , String transactionType,String status,Date dateFrom, Date dateTo,String transactionStatus,Integer branchRecordId ) throws Exception {
		List<Poscashtransaction> poscashtransaction=null;
		StringBuilder buf = new StringBuilder("SELECT b FROM Poscashtransaction b inner join fetch b.poscounter c WHERE 1=1");				
		try {		
			
		if ((dateFrom != null) && (dateFrom != null)) {
				buf.append("  AND b.lastupdatedDate between  :dateFrom and :dateTo   ");
		}
		
		if (counterId !=null && counterId!=0) {
		buf.append(" AND c.counterId =:counterId  ");
		}	
		
		if (branchRecordId !=null && branchRecordId!=0) {
			buf.append(" AND c.branch.branchId =:branchRecordId  ");
			}	
		
		if (transactionType !=null) {
		buf.append(" AND b.transactionType =:transactionType  ");
		}	
		
		if (status !=null && (!status.equalsIgnoreCase(""))) {
			buf.append(" AND b.status =:status  ");
		}	
		
		if (transactionStatus !=null && (!transactionStatus.equalsIgnoreCase(""))) {
			buf.append(" AND b.transactionStatus =:transactionStatus  ");
		}
		
			
		Query query = em.createQuery(buf.toString());
		
		if ((dateFrom != null) && (dateTo != null))
		{
			query.setParameter("dateFrom", dateFrom);
			query.setParameter("dateTo", dateTo);
		}	
		
		if (counterId !=null && counterId!=0) {
			query.setParameter("counterId", counterId);
		}	
		
		if (branchRecordId !=null && branchRecordId!=0) {
			query.setParameter("branchRecordId", branchRecordId);
			}	
		
		
		if (transactionType !=null) {
			query.setParameter("transactionType", transactionType);
		}		
		if (status !=null && (!status.equalsIgnoreCase(""))) {
			query.setParameter("status", status);
		}	
		
		if (transactionStatus !=null && (!transactionStatus.equalsIgnoreCase(""))) {
			query.setParameter("transactionStatus", transactionStatus); // Approved by Superuser
		}			
		poscashtransaction=query.getResultList();		
		} 
		catch (Exception e) {
			log.info("Error in findByPoscashtransactionList PoscounterDAOImpl " + e);
			throw e;
		}
		return poscashtransaction;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> getPoscashtransactionCounter(Integer counterId ,Date dateFrom, Date dateTo,String status,String transactionStatus,Integer branchRecordId) throws Exception {
		
		List<Object[]> poscashtransaction=null;	
		
		try {				
			
			StringBuilder buf = new StringBuilder(
					"select t, sum(t.creditamount), sum(t.debitAmount), sum(t.receivedAmount), sum(t.totalTax) from Poscashtransaction t inner join fetch t.poscounter c  WHERE 1=1  ");
			
			if ((dateFrom != null) && (dateFrom != null)) {
				buf.append("  AND t.lastupdatedDate between  :dateFrom and :dateTo   ");
			}	
			
			if (counterId !=null && counterId!=0) {
				buf.append(" AND c.counterId =:counterId  ");
			}		
			
			if (branchRecordId !=null && branchRecordId!=0) {
				buf.append(" AND c.branch.branchId =:branchRecordId  ");
				}	
			
			if (status !=null && (!status.equalsIgnoreCase(""))) {
				buf.append(" AND t.status =:status  ");
			}	
			
			if (transactionStatus !=null && (!transactionStatus.equalsIgnoreCase(""))) {
				buf.append(" AND t.transactionStatus =:transactionStatus  ");
			}
			
			
			Query query = em.createQuery(buf.toString());
			
			if ((dateFrom != null) && (dateTo != null))
			{
				query.setParameter("dateFrom", dateFrom);
				query.setParameter("dateTo", dateTo);
			}	
			
			if (counterId !=null && counterId!=0) {
				query.setParameter("counterId", counterId);
			}		
			if (branchRecordId !=null && branchRecordId!=0) {
				query.setParameter("branchRecordId", branchRecordId);
				}	
			
			
			if (status !=null && (!status.equalsIgnoreCase(""))) {
				query.setParameter("status", status);
			}				
			
			if (transactionStatus !=null && (!transactionStatus.equalsIgnoreCase(""))) {
				query.setParameter("transactionStatus", transactionStatus); // Approved by Superuser
			}	
			
			
			
			poscashtransaction=query.getResultList();
			
			} catch (Exception e) {
				log.info("Error in getPoscashtransactionCounter SalesorderDAOImpl " + e);
				throw e;
			}
			return poscashtransaction;
	}
	@Override
	public List<Object[]> getposcounterReportDaily(Integer counterId, Date dateFrom, Date dateTo, String status,
			String transactionStatus,String transactionType, Integer branchId) throws Exception {

		List<Object[]> poscashtransaction=null;	
		
		try {				
			
			StringBuilder buf = new StringBuilder(
					"select t, sum(t.creditamount), sum(t.debitAmount), sum(t.receivedAmount), sum(t.totalTax) from Poscashtransaction t "
					+ " WHERE 1=1  ");
			
			if ((dateFrom != null) && (dateFrom != null)) {
				buf.append("  AND t.lastupdatedDate between  :dateFrom and :dateTo   ");
			}	
			
			if (counterId !=null && counterId!=0) {
				buf.append(" AND t.poscounter.counterId =:counterId  ");
			}		
			
			if (branchId !=null && branchId!=0) {
				buf.append(" AND t.branch.branchId =:branchId  ");
				}	
			
			if (status !=null && (!status.equalsIgnoreCase(""))) {
				buf.append(" AND t.status =:status  ");
			}	
			
			if (transactionStatus !=null && (!transactionStatus.equalsIgnoreCase(""))) {
				buf.append(" AND t.transactionStatus =:transactionStatus  ");
			}
			if (transactionType !=null && (!transactionType.equalsIgnoreCase(""))) {
				buf.append(" AND t.transactionType =:transactionType  ");
			}
			

			Query query = em.createQuery(buf.toString());
			
			if ((dateFrom != null) && (dateTo != null))
			{
				query.setParameter("dateFrom", dateFrom);
				query.setParameter("dateTo", dateTo);
			}	
			
			if (counterId !=null && counterId!=0) {
				query.setParameter("counterId", counterId);
			}		
			if (branchId !=null && branchId!=0) {
				query.setParameter("branchId", branchId);
				}	
			
			
			if (status !=null && (!status.equalsIgnoreCase(""))) {
				query.setParameter("status", status);
			}				
			
			if (transactionStatus !=null && (!transactionStatus.equalsIgnoreCase(""))) {
				query.setParameter("transactionStatus", transactionStatus); // Approved by Superuser
			}	
			
			if (transactionType !=null && (!transactionType.equalsIgnoreCase(""))) {
				query.setParameter("transactionType", transactionType); 
			}	
			
			
			poscashtransaction=query.getResultList();
			
			} catch (Exception e) {
				log.info("Error in getPoscashtransactionCounter SalesorderDAOImpl " + e);
				throw e;
			}
			return poscashtransaction;
	}
	@Override
	public List<Object[]> getAnnualPosCounterReport(Integer counterId, Date dateFrom, Date dateTo, String status,
			String transactionStatus, String transactionType, Integer branchId) throws Exception {

			List<Object[]> poscashtransaction=null;	
			
			try {				
				
				StringBuilder buf = new StringBuilder(
						"select t, sum(t.creditamount), sum(t.debitAmount), sum(t.receivedAmount), sum(t.totalTax),t.lastupdatedDate from Poscashtransaction t "
						+ "inner join fetch t.poscounter c  WHERE 1=1  ");
				
				if ((dateFrom != null) && (dateFrom != null)) {
					buf.append("  AND t.lastupdatedDate between  :dateFrom and :dateTo   ");
				}	
				
				if (counterId !=null && counterId!=0) {
					buf.append(" AND c.counterId =:counterId  ");
				}		
				
				if (branchId !=null && branchId!=0) {
					buf.append(" AND c.branch.branchId =:branchId  ");
					}	
				
				if (status !=null && (!status.equalsIgnoreCase(""))) {
					buf.append(" AND t.status =:status  ");
				}	
				
				if (transactionStatus !=null && (!transactionStatus.equalsIgnoreCase(""))) {
					buf.append(" AND t.transactionStatus =:transactionStatus  ");
				}
				if (transactionType !=null && (!transactionType.equalsIgnoreCase(""))) {
					buf.append(" AND t.transactionType =:transactionType  ");
				}
				
				buf.append(" GROUP BY  MONTH(t.lastupdatedDate) ");
				Query query = em.createQuery(buf.toString());
				
				if ((dateFrom != null) && (dateTo != null))
				{
					query.setParameter("dateFrom", dateFrom);
					query.setParameter("dateTo", dateTo);
				}	
				
				if (counterId !=null && counterId!=0) {
					query.setParameter("counterId", counterId);
				}		
				if (branchId !=null && branchId!=0) {
					query.setParameter("branchId", branchId);
					}	
				
				
				if (status !=null && (!status.equalsIgnoreCase(""))) {
					query.setParameter("status", status);
				}				
				
				if (transactionStatus !=null && (!transactionStatus.equalsIgnoreCase(""))) {
					query.setParameter("transactionStatus", transactionStatus); // Approved by Superuser
				}	
				
				if (transactionType !=null && (!transactionType.equalsIgnoreCase(""))) {
					query.setParameter("transactionType", transactionType); 
				}	
				
				
				poscashtransaction=query.getResultList();
				
				} catch (Exception e) {
					log.info("Error in getAnnualPosCounterReport SalesorderDAOImpl " + e);
					throw e;
				}
				return poscashtransaction;
	}
		
	
}
