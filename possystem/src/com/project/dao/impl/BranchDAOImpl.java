package com.project.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.interfaces.IBranchDAO;
import com.project.modal.report.ReturnvalueModal;
import com.project.model.his.Branch;
import com.project.util.StringConstants;

/*
 * @author Gopal Ar
 * @version 1.0
 * @since 17 June 2013
 * 
 */

@Repository("branchRepository")
public class BranchDAOImpl implements IBranchDAO {
	
	public static Logger log = LoggerFactory.getLogger(BranchDAOImpl.class);
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
	public ReturnvalueModal createNewBranch(Branch branch) throws Exception {
		ReturnvalueModal returnVal=new ReturnvalueModal();
		try {
			em.persist(branch);
			returnVal.setSuccess(true);
			returnVal.setPrimaryId(branch.getBranchId());
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in createNewBranch BranchDAOImpl " + e);
			throw e;
		}
		return returnVal;
	}

	@Transactional
	@Override
	public boolean updateBranch(Branch branch) throws Exception {
		try {
			em.merge(branch);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in updateBranch BranchDAOImpl " + e);
			throw e;
		}
	}

	@Override
	public boolean deleteBranch(Branch branch) throws Exception {
		try {
			em.remove(branch);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in deleteBranch BranchDAOImpl " + e);
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Branch> findByBranchList(Integer branchId, String cityName,
			String stateName, String Status) throws Exception {
		List<Branch> branches=null;
		StringBuilder buf = new StringBuilder("SELECT b FROM Branch b WHERE 1 = 1  And b.branchName!='UNKNOWN' ");
				
		try {
			
		if (branchId !=null && branchId !=0) {
			buf.append(" And b.branchId = :branchId ");
		}	
		if (cityName !=null && (!cityName.equalsIgnoreCase(""))) {
			buf.append(" And b.city = :city ");
		}		
		if (stateName !=null && (!stateName.equalsIgnoreCase(""))) {
			buf.append(" And b.state = :state ");
		}		
		if (Status !=null && (!Status.equalsIgnoreCase(""))) {
			buf.append(" And b.status = :status ");
		}		
		
		buf.append(" order by b.branchName");
		Query query = em.createQuery(buf.toString());		
		
		if (branchId !=null && branchId !=0) {
			query.setParameter("branchId", branchId);
		}		
		if (cityName !=null && (!cityName.equalsIgnoreCase(""))) {
			query.setParameter("city", cityName);
		}			
		if (stateName !=null && (!stateName.equalsIgnoreCase(""))) {
			query.setParameter("state", stateName);
		}			
		if (Status !=null && (!Status.equalsIgnoreCase(""))) {
			query.setParameter("status", Status);
		}		 
		branches=query.getResultList();
		
		} catch (Exception e) {
			log.info("Error in findByBranchList BranchDAOImpl " + e);
			throw e;
		}
		return branches;
	}

	@Override
	public Branch getBranchDetailsByName(String branchName) throws Exception {
		
		Branch retData = null;
		String sqlQuery = "select t from Branch t " + " WHERE t.branchName = :branchName ";
		try {
			retData = (Branch) em.createQuery(sqlQuery).setParameter("branchName", branchName).getSingleResult();

		} catch (Exception e) {
			log.info("Error in getBranchDetails BranchDAOImpl " + e);
			throw e;
		}
		return retData;
	}

	@Override
	public Branch getBranchDetails(Integer branchId) throws Exception {
		Branch retData = null;
		String sqlQuery = "select t from Branch t  WHERE t.branchId = :branchId ";
		try {
			retData = (Branch) em.createQuery(sqlQuery).setParameter("branchId", branchId).getSingleResult();

		} catch (Exception e) {
			log.info("Error in getBranchDetails BranchDAOImpl " + e);
			throw e;
		}
		return retData;
	}

	@Override
	public boolean findBranchNameExites(String branchName) throws Exception {
		boolean exits = true;
		try {
			String hslQuery = "from Branch p where p.branchName = :branchName";
			Query query = em.createQuery(hslQuery).setParameter("branchName", branchName);
			@SuppressWarnings("unchecked")
			List<Branch> branchNameExits = query.getResultList();
			if (branchNameExits.isEmpty()) {
				exits = false;
			}
		} catch (Exception e) {
			log.info("Error in findBranchNameExites BranchDAOImpl " + e);
			throw e;
		}
		return exits;
	}

	@Override
	public boolean findBranchCodeExites(String branchCode) throws Exception {
		boolean exits = true;
		try {
			String hslQuery = "from Branch p where p.branchCode = :branchCode";
			Query query = em.createQuery(hslQuery).setParameter("branchCode", branchCode);
			@SuppressWarnings("unchecked")
			List<Branch> branchNameExits = query.getResultList();
			if (branchNameExits.isEmpty()) {
				exits = false;
			}
		} catch (Exception e) {
			log.info("Error in findBranchCodeExites BranchDAOImpl " + e);
			throw e;
		}
		return exits;
	}

}
