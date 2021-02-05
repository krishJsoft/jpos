package com.project.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.project.dao.interfaces.IStaffDAO;
import com.project.model.his.Branch;
import com.project.model.his.Branchstaffmember;
import com.project.util.StringConstants;

/*
 * @author Gopal Ar
 * @version 1.0
 * @since 17 June 2013
 * 
 */

@Repository("staffRepository")
public class StaffDAOImpl implements IStaffDAO {
	
	public static Logger log = LoggerFactory.getLogger(StaffDAOImpl.class);
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
	public boolean createNewStaff(Branchstaffmember staff) throws Exception {
		try {
			em.persist(staff);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in createNewStaff StaffDAOImpl " + e);
			throw e;
		}
	}

	@Override
	public boolean updateStaff(Branchstaffmember staff) throws Exception {
		try {
			em.merge(staff);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in updateStaff StaffDAOImpl " + e);
			throw e;
		}
	}

	@Override
	public boolean deleteStaff(Branchstaffmember staff) throws Exception {
		try {
			em.remove(staff);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in deleteStaff StaffDAOImpl " + e);
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Branchstaffmember> findByStaffList(Integer branchId,
			String identificationNumber, Integer roleId, String Status) throws Exception {
		List<Branchstaffmember> branchstaffmember=null;
		StringBuilder buf = new StringBuilder("SELECT b FROM Branchstaffmember b inner join fetch b.branch br inner join fetch b.department d inner join fetch b.role r WHERE 1 = 1 ");
				
		try {
			
		if (branchId !=null && branchId !=0) {
			buf.append(" AND br.branchId =  :branchId  ");
		}	
		if (identificationNumber !=null && (!identificationNumber.equalsIgnoreCase(""))) {
			buf.append(" And b.identificationNumber = :identificationNumber ");
		}		
		if (roleId !=null && roleId !=0) {
			buf.append(" AND r.roleId = :roleId  ");
		}		
		if (Status !=null && (!Status.equalsIgnoreCase(""))) {
			buf.append(" And b.status = :status ");
		}		
		
		buf.append(" order by b.firstName");
		
		Query query = em.createQuery(buf.toString());		
		
		if (branchId !=null && branchId !=0) {
			query.setParameter("branchId", branchId);
		}		
		if (identificationNumber !=null && (!identificationNumber.equalsIgnoreCase(""))) {
			query.setParameter("identificationNumber", identificationNumber);
		}			
		if (roleId !=null && roleId !=0) {
			query.setParameter("roleId", roleId);
		}			
		if (Status !=null && (!Status.equalsIgnoreCase(""))) {
			query.setParameter("status", Status);
		}		 
		branchstaffmember=query.getResultList();
		
		} catch (Exception e) {
			log.info("Error in findByStaffList StaffDAOImpl " + e);
			throw e;
		}
		return branchstaffmember;
	}

	@Override
	public Branchstaffmember getBranchstaffmemberDetails(Integer staffId)
			throws Exception {
		Branchstaffmember retData = null;
		String sqlQuery = "select t from Branchstaffmember t inner join fetch t.branch inner join fetch t.department  inner join fetch t.role  WHERE t.staffId = :staffId ";
		try {
			retData = (Branchstaffmember) em.createQuery(sqlQuery).setParameter("staffId", staffId).getSingleResult();

		} catch (Exception e) {
			log.info("Error in getBranchstaffmemberDetails StaffDAOImpl " + e);
			throw e;
		}
		return retData;
	}

	@Override
	public boolean findStaffIcExites(String identificationNumber) throws Exception {
		boolean exits = true;
		try {
			String hslQuery = "from Branchstaffmember p where p.identificationNumber = :identificationNumber";
			Query query = em.createQuery(hslQuery).setParameter("identificationNumber", identificationNumber);
			@SuppressWarnings("unchecked")
			List<Branchstaffmember> identificationNumberExits = query.getResultList();
			if (identificationNumberExits.isEmpty()) {
				exits = false;
			}
		} catch (Exception e) {
			log.info("Error in findStaffIcExites StaffDAOImpl " + e);
			throw e;
		}
		return exits;
	}
	
	
	@Override
	public boolean findStaffEmailExites(String emailAddress) throws Exception {
		boolean exits = true;
		try {
			String hslQuery = "from Branchstaffmember p where p.emailAddress = :emailAddress";
			Query query = em.createQuery(hslQuery).setParameter("emailAddress", emailAddress);
			@SuppressWarnings("unchecked")
			List<Branchstaffmember> identificationNumberExits = query.getResultList();
			if (identificationNumberExits.isEmpty()) {
				exits = false;
			}
		} catch (Exception e) {
			log.info("Error in findStaffEmailExites StaffDAOImpl " + e);
			throw e;
		}
		return exits;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Branchstaffmember> findStaffMemberlistLogin(String username) {

		String sqlQuery="from Branchstaffmember b inner join fetch b.branch br inner join fetch b.department d inner join fetch b.role r where b.staffCode = :username and b.status= :status";
		return  em.createQuery(sqlQuery).setParameter("username", username).setParameter("status", "1").getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Branchstaffmember> findByStaffList(Integer branchId,String identificationNumber, Integer roleId, String Status,String userType) throws Exception {
		List<Branchstaffmember> branchstaffmember=null;
		StringBuilder buf = new StringBuilder("SELECT b FROM Branchstaffmember b inner join fetch b.branch br inner join fetch b.department d inner join fetch b.role r WHERE 1 = 1 ");
				
		try {
			
		if (branchId !=null && branchId !=0) {
			buf.append(" AND br.branchId =  :branchId  ");
		}	
		if (identificationNumber !=null && (!identificationNumber.equalsIgnoreCase(""))) {
			buf.append(" And b.identificationNumber = :identificationNumber ");
		}		
		if (roleId !=null && roleId !=0) {
			buf.append(" AND r.roleId = :roleId  ");
		}		
		if (Status !=null && (!Status.equalsIgnoreCase(""))) {
			buf.append(" And b.status = :status ");
		}	
		if (userType !=null && (!userType.equalsIgnoreCase(""))) {
			buf.append(" And b.userType = :userType ");
		}
		
		buf.append(" order by b.firstName");
		
		Query query = em.createQuery(buf.toString());		
		
		if (branchId !=null && branchId !=0) {
			query.setParameter("branchId", branchId);
		}		
		if (identificationNumber !=null && (!identificationNumber.equalsIgnoreCase(""))) {
			query.setParameter("identificationNumber", identificationNumber);
		}			
		if (roleId !=null && roleId !=0) {
			query.setParameter("roleId", roleId);
		}			
		if (Status !=null && (!Status.equalsIgnoreCase(""))) {
			query.setParameter("status", Status);
		}	
		
		if (userType !=null && (!userType.equalsIgnoreCase(""))) {
			query.setParameter("userType", userType);
		}	
		
		
		branchstaffmember=query.getResultList();
		
		} catch (Exception e) {
			log.info("Error in findByStaffList StaffDAOImpl " + e);
			throw e;
		}
		return branchstaffmember;
	}
	@Override
	public boolean isRoleAssigned(Integer roleId, int branchId) throws Exception {
		boolean isRoleAssigned=false;
		try {
			StringBuilder hql=new StringBuilder("Select 1 FROM Branchstaffmember bs WHERE bs.role.roleId=:roleId and bs.branch.branchId=:branchId ");
			Query query=em.createQuery(hql.toString());
			query.setParameter("roleId", roleId);
			query.setParameter("branchId", branchId);
			
			if(query.getResultList().size()>0) {
				isRoleAssigned=true;
			}

		}catch(Exception ex) {
			ex.printStackTrace();
			log.info("Error in isRoleAssigned StaffDAOImpl " + ex);
		}
		return isRoleAssigned;
	}
	@Override
	public List<Branchstaffmember> findStaffMemberlistLogin2(String username, String password, String status,
			Integer branchId) throws Exception {
		List<Branchstaffmember> branchstaffmember=null;
		StringBuilder buf = new StringBuilder("SELECT b FROM Branchstaffmember b inner join fetch b.branch br inner join fetch b.department d inner join fetch b.role r WHERE 1 = 1 ");
				
		try {
			
		if (username !=null && (!username.equalsIgnoreCase(""))) {
			buf.append(" And b.staffCode = :username ");
		}
		if (password !=null && (!password.equalsIgnoreCase(""))) {
			buf.append(" And b.password = :password ");
		}
		if (status !=null && (!status.equalsIgnoreCase(""))) {
			buf.append(" And b.status = :status ");
		}		
		if (branchId !=null && branchId !=0) {
			buf.append(" AND br.branchId =  :branchId  ");
		}		
		
		buf.append(" order by b.firstName");
		
		Query query = em.createQuery(buf.toString());		
		
		if (username !=null && (!username.equalsIgnoreCase(""))) {
			query.setParameter("username", username);
		}		 
		if (password !=null && (!password.equalsIgnoreCase(""))) {
			query.setParameter("password", password);
		}		 
		if (status !=null && (!status.equalsIgnoreCase(""))) {
			query.setParameter("status", status);
		}		 
		if (branchId !=null && branchId !=0) {
			query.setParameter("branchId", branchId);
		}		
		
		branchstaffmember=query.getResultList();
		
		} catch (Exception e) {
			log.info("Error in findByStaffList StaffDAOImpl " + e);
			throw e;
		}
		return branchstaffmember;
	}

	
	

}
