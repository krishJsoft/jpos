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

import com.project.dao.interfaces.IExpensesDAO;
import com.project.model.his.ExpensesList;
import com.project.model.his.ExpensesTransaction;
import com.project.model.sale.sales.ExpensesTransactionModel;
import com.project.util.StringConstants;

@Repository("expensesRepository")
public class ExpensesDAOImpl implements IExpensesDAO {

	public static Logger log = LoggerFactory.getLogger(ExpensesDAOImpl.class);
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
	public boolean createNewExpTrans(ExpensesTransaction expTran) throws Exception {

		try {
			em.persist(expTran);
			return true;
		}catch(Exception ex){
			log.info("Error in createNewExpTrans ExpensesDAOImpl "+ex);
			throw ex;
		}
		

	}
	
	@Transactional
	public boolean createNewExpName(ExpensesList exp) throws Exception {
		try {
			em.persist(exp);
			return true;
		}catch(Exception ex) {
			log.info("Error in ceateNewExpName "+ex);
			throw ex;
		}
	}
	
	@Override
	public List<ExpensesList> expensesListAll(int branchId) throws Exception {
		String hsql="from ExpensesList expl join fetch expl.branch b where b.branchId=:branchId";
		Query query=em.createQuery(hsql);
		query.setParameter("branchId", branchId);
		List<ExpensesList> expList=query.getResultList();
		return expList;
	}
	
	@Override
	public List<ExpensesTransaction> expensesTransactionAll(int branchId) throws Exception {
		String hsql="from ExpensesTransaction expt join fetch expt.expList expl join fetch "
				+ "expt.branch b where b.branchId=:branchId order by expt.createdDate desc";
		Query query=em.createQuery(hsql);
		query.setParameter("branchId", branchId);
		
		return query.getResultList();
	}
	
	@Override
	public ExpensesTransaction expensesTrans(int expTransId) throws Exception {
		try {
			String hsql="FROM ExpensesTransaction expt "
					+ "join fetch expt.branch b "
					+ "join fetch expt.staff s "
					+"left outer join fetch expt.staffCreditedTo sct "
					+ "join fetch expt.expList expl "
					+ "join fetch expt.poscounter pc "
					+ "where expt.id=:expTransId ";
			
			Query query=em.createQuery(hsql);
			query.setParameter("expTransId", expTransId);
			ExpensesTransaction expTrans=(ExpensesTransaction) query.getSingleResult();
			return expTrans;
		}catch(Exception e) {
			e.printStackTrace();
			log.info("Error in expensesTrans ExpensesDAOImpl " + e);
			throw e;
		}
	}
	
	@Transactional
	public boolean updateExpTrans(ExpensesTransaction expTrans) throws Exception {
		try {
			em.merge(expTrans);
			return true;
		} catch (Exception e) {
			log.info("Error in updateExpTrans ExpensesDAOImpl " + e);
			throw e;
		}
	}
	
	@Override
	public List<Object[]> getAnnualExpenses(Date firstDayYear, Date endDayYear, int branchId) throws Exception {
		try {
			String hsql="select expt, sum(expt.ammount),expt.createdDate from ExpensesTransaction expt join fetch expt.branch b join fetch expt.expList where expt.activeStatus=1 and b.branchId=:branchId "
					+ "and expt.createdDate between :firstDayYear and :endDayYear "
					+ "group by MONTH(expt.createdDate) "
					+ "order by expt.createdDate asc";
			Query query=em.createQuery(hsql);
			query.setParameter("branchId", branchId);
			query.setParameter("firstDayYear", firstDayYear);
			query.setParameter("endDayYear",endDayYear);
			
			List<Object[]> expList=query.getResultList();

			return expList;
			
			
		}catch(Exception ex) {
			log.info("Error in getAnnualExpenses ExpensesDAOImpl " + ex);
			throw ex;
		}
		
	}
	
	@Override
	public List<Object[]> getMonthlyExpensesDetail(Date startDate, Date endDate, Integer expensesId,Integer staffId,int branchId) throws Exception {
		try {
			String hsql="select expt, sum(expt.ammount),expt.createdDate,expl.id,expl.expensesName,sct.firstName from ExpensesTransaction expt "
					+ "join fetch expt.branch b join fetch expt.expList expl "
					+ "left outer join fetch expt.staffCreditedTo sct "
					+ "where expt.activeStatus=1 and b.branchId=:branchId ";

			/*	
				if(expensesId!=null && expensesId>0) {
					hsql+="and expl.id=:expensesId ";
				}
				if(staffId!=null && staffId>0) {
					hsql+="and expt.staffCreditedTo.staffId=:staffId ";
				}
*/
				hsql+= "and expt.createdDate between :startDate and :endDate "
					+ "group by expl.id ";
				
					hsql+=",expt.staffCreditedTo.staffId ";
			Query query=em.createQuery(hsql);
			query.setParameter("branchId", branchId);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate",endDate);
			/*
			if(expensesId!=null && expensesId>0) {
				query.setParameter("expensesId",expensesId);
			}
			if(staffId!=null && staffId>0) {
				query.setParameter("staffId",staffId);
			}
*/
			
			List<Object[]> expList=query.getResultList();

			return expList;
			
			
		}catch(Exception ex) {
			log.info("Error in getAnnualExpenses ExpensesDAOImpl " + ex);
			throw ex;
		}
	}
	
	@Override
	public boolean nameIsExist(int branchId,String expName) throws Exception {
		boolean exist=true;
		try {
			String hsql="from ExpensesList expl join fetch expl.branch b where b.branchId=:branchId and expl.expensesName=:expName";
			Query query=em.createQuery(hsql);
			query.setParameter("branchId", branchId);
			query.setParameter("expName", expName);
			List<ExpensesList> expList=query.getResultList();
			if(expList.isEmpty()) {
				exist=false;
			}
			
		}catch(Exception ex) {
			log.info("Error in nameIsExist ExpensesDAOImpl " + ex);
			throw ex;
		}
		return exist;
	}
	@Override
	public List<ExpensesTransaction> getExpensesTransactionList(int counterId,Date dateFrom, Date dateTo, int branchId)
			throws Exception {
		try {
			String hsql="select expt from ExpensesTransaction expt join fetch expt.branch b"
					+ " join fetch expt.expList exp "
					+ "join fetch expt.poscounter pc "
					+ "where expt.activeStatus=1 and b.branchId=:branchId ";
			if(dateFrom!=null && dateTo!=null) {
				hsql+="and expt.createdDate between :dateFrom and :dateTo ";
			}
			
			if(counterId>0) {
				hsql+="and pc.counterId=:counterId ";
			}	
			
			hsql+="order by expt.createdDate asc ";

			Query query=em.createQuery(hsql);
			query.setParameter("branchId", branchId);
			if(dateFrom!=null && dateTo!=null) {
				query.setParameter("dateFrom", dateFrom);
				query.setParameter("dateTo",dateTo);
			}
		
			if(counterId>0) {
				query.setParameter("counterId",counterId);
			}
			List<ExpensesTransaction> expList=query.getResultList();

			return expList;
			
			
		}catch(Exception ex) {
			log.info("Error in getExpensesTransactionList ExpensesDAOImpl " + ex);
			throw ex;
		}
	}


	@Override
	public List<Object[]>geDailyExpensesDetail(Date startDate, Date endDate,Integer expensesId,Integer staffId, int branchId) throws Exception {
		try {
			String hsql="select expt, sum(expt.ammount),expt.createdDate,expl.id,expl.expensesName,sct.firstName from ExpensesTransaction expt join fetch expt.branch b join fetch expt.expList expl left outer join fetch expt.staffCreditedTo sct "
					+ "where expt.activeStatus=1 and b.branchId=:branchId ";

			
				if(expensesId!=null && expensesId>0) {
					hsql+="and expl.id=:expensesId ";
				}
				if(staffId!=null && staffId>0) {
					hsql+="and expt.staffCreditedTo.staffId=:staffId ";
				}
					hsql+= "and expt.createdDate between :startDate and :endDate "
					+ "GROUP BY expl.id ,DAY(expt.createdDate) ";
					
				//if(staffId!=null && staffId>0) {
					hsql+=",expt.staffCreditedTo.staffId ";
				//}
			
					hsql+= "ORDER BY Day(expt.createdDate) desc,expl.expensesName";
			Query query=em.createQuery(hsql);
			query.setParameter("branchId", branchId);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate",endDate);
			if(expensesId!=null && expensesId>0) {
				query.setParameter("expensesId",expensesId);
			}
			if(staffId!=null && staffId>0) {
				query.setParameter("staffId",staffId);
			}
			
			List<Object[]> expList=query.getResultList();

			return expList;
			
			
		}catch(Exception ex) {
			log.info("Error in getAnnualExpenses ExpensesDAOImpl " + ex);
			throw ex;
		}
	}
	@Override
	public List<ExpensesTransaction> loadExpensesByPosTerminal(int counterId, int branchId) throws Exception {
		String hsql="from ExpensesTransaction expt join fetch expt.expList expl join fetch "
				+ "expt.branch b join fetch expt.poscounter pc where b.branchId=:branchId "
				+ "and pc.counterId=:counterId order by expt.createdDate desc";
		Query query=em.createQuery(hsql);
		query.setParameter("branchId", branchId);
		query.setParameter("counterId", counterId);

		
		return query.getResultList();
	}
	
	
}
