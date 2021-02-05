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

import com.project.dao.interfaces.IMembershipDAO;
import com.project.model.datamodel.MembershipPaymentTransactionModel;
import com.project.model.his.ExpensesList;
import com.project.model.his.Membership;
import com.project.model.his.MembershipConfiguration;
import com.project.model.his.MembershipPaymentTransaction;
import com.project.util.StringConstants;

@Repository("membershipRepository")
public class MembershipDAOImpl implements IMembershipDAO{

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
	public boolean createNewMember(Membership obj) throws Exception {
		try {
			em.persist(obj);
			return true;
		}catch(Exception ex){
			log.info("Error in createNewMember MembershipDAOImpl " +ex);
			throw ex;
		}
	}
	
	@Transactional
	public boolean updateMember(Membership obj) throws Exception {
		try {
			em.merge(obj);
			return true;
		}catch(Exception ex) {
			log.info("Error in updateMember MembershipDAOImpl " + ex );
			throw ex;
		}
	}
	
	@Override
	public List<Membership> fetchActiveMembershipList(int branchId) throws Exception {
		try {
			String hsql="from Membership m join fetch m.branch b where b.branchId=:branchId and m.status=1";
			Query query=em.createQuery(hsql);
			query.setParameter("branchId",branchId);
			List<Membership> dataList=query.getResultList();
			return dataList;
			
		}catch(Exception ex) {
			log.info("Error in fetchActiveMembershipList MembershipDAOImpl " +ex);
			throw ex;
		}
	}
	
	@Override
	public List<Membership> fetchMembershipList(int branchId) throws Exception {
		try {
			String hsql="from Membership m join fetch m.branch b where b.branchId=:branchId";
			Query query=em.createQuery(hsql);
			query.setParameter("branchId",branchId);
			List<Membership> dataList=query.getResultList();
			return dataList;
			
		}catch(Exception ex) {
			log.info("Error in fetchMembershipList MembershipDAOImpl " +ex);
			throw ex;
		}
	}
	
	@Override
	public Membership fetchSelectedMember(int memberId) throws Exception {
		try {
			String hsql="from Membership m join fetch m.branch b join fetch m.createdBy s where m.id=:memberId ";
			Query query=em.createQuery(hsql);
			query.setParameter("memberId",memberId);
			Membership dataList=(Membership) query.getSingleResult();
			return dataList;
			
		}catch(Exception ex) {
			log.info("Error in fetchSelectedMember MembershipDAOImpl " +ex);
			throw ex;
		}
	}
	
	@Transactional
	public Boolean createPaymentTransaction(MembershipPaymentTransaction obj) throws Exception {
		try {
			em.persist(obj);
			return true;
		}catch(Exception ex) {
			log.info("Error in createPaymentTransaction MembershipDAOImpl " +ex);
			throw ex;
		}
	}
	
	@Transactional
	public Boolean updateConfiguration(MembershipConfiguration configData) throws Exception {
		try {
			em.merge(configData);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			log.info("Error in updateConfiguration MembershipDAOImpl " +ex);
			throw ex;
		}
	}
	
	@Override
	public MembershipConfiguration fetchConfiguration(int branchId) throws Exception {
		try {
			String hsql="from MembershipConfiguration mc join fetch mc.branch b where b.branchId=:branchId";
			Query query=em.createQuery(hsql);
			query.setParameter("branchId", branchId);
			MembershipConfiguration config=(MembershipConfiguration) query.getSingleResult();
			return config;
		}catch(Exception ex) {
			log.info("Error in fetchConfiguration MembershipDAOImpl " +ex);
			throw ex;
		}
	}
	
	@Override
	public boolean memberIsExist(int branchId, String identificationNumber) throws Exception {
		boolean exist=true;
		try {
			String hsql="from Membership m join fetch m.branch b where b.branchId=:branchId and m.identificationNumber=:identificationNumber";
			Query query=em.createQuery(hsql);
			query.setParameter("branchId", branchId);
			query.setParameter("identificationNumber", identificationNumber);
			List<ExpensesList> memberList=query.getResultList();
			if(memberList.isEmpty()) {
				exist=false;
			}
		}catch(Exception ex) {
			log.info("Error in memberIdIsExist MembershipDAOImpl " +ex);
			throw ex;
		}
		return exist;
	}
	
	@Override
	public MembershipPaymentTransaction fetchSelectedPaymentTransaction(int transactionId) throws Exception {
		try {
			String hsql="from MembershipPaymentTransaction mp join fetch mp.member m join fetch mp.branch b join fetch mp.staff s where mp.id=:transactionId";
			Query query=em.createQuery(hsql);
			query.setParameter("transactionId", transactionId);
			MembershipPaymentTransaction paymentTrans=(MembershipPaymentTransaction) query.getSingleResult();
			return paymentTrans;
			
		}catch(Exception ex) {
			log.info("Error in fetchSelectedPaymentTransaction MembershipDAOImpl " +ex);
			throw ex;
		}
	}
	
	@Override
	public List<MembershipPaymentTransaction> fetchAllPaymentTransaction(int branchId) throws Exception {
		try {
			String hsql="from MembershipPaymentTransaction mp join fetch mp.member m join fetch mp.branch b join fetch mp.staff s where b.branchId=:branchId and mp.status=1 ORDER BY mp.createdDate desc";
			Query query=em.createQuery(hsql);
			query.setParameter("branchId", branchId);
			List<MembershipPaymentTransaction> paymentTransList=(List<MembershipPaymentTransaction>) query.getResultList();
			return paymentTransList;
		}catch(Exception ex) {
			ex.printStackTrace();
			log.info("Error in fetchAllPaymentTransaction MembershipDAOImpl " +ex);
			throw ex;
		}
	}
	
	@Transactional
	public boolean deleteSelectedPaymentTransaction(int transactionId) throws Exception {
		try {
			MembershipPaymentTransaction transaction=em.find(MembershipPaymentTransaction.class, transactionId);
			em.remove(transaction);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			log.info("Error in deleteSelectedPaymentTransaction MembershipDAOImpl " +ex);
			throw ex;
		}
	}

	@Transactional
	public boolean updateSelectedPaymentTransaction(MembershipPaymentTransaction membershipPayment) throws Exception {
		try {
			em.merge(membershipPayment);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			log.info("Error in updateSelectedPaymentTransaction MembershipDAOImpl " +ex);
			throw ex;
		}
	}
	@Override
	public boolean adjustSelectedMembershipExpiredDate(int parseInt, Date newDate) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
}
