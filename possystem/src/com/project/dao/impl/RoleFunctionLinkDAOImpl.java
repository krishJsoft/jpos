package com.project.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.interfaces.RoleFunctionLinkDAO;
import com.project.model.his.Rolefunctionlink;
import com.project.util.StringConstants;



@Repository("RoleFunctionLinkRepository")
@Transactional
public class RoleFunctionLinkDAOImpl implements RoleFunctionLinkDAO {
	
	public static Logger log = LoggerFactory.getLogger(RoleFunctionLinkDAOImpl.class);
	
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
	
	
	public void save (Rolefunctionlink roleFunctionLink){
		log.info("Save Rolefunctionslink Values  :  Function ID :" );
		em.persist(roleFunctionLink);
	}

	@Override
	public void update(Rolefunctionlink rolefunctionLink) {
		// TODO Auto-generated method stub
	}

	
	public void delete(Integer id) {
		Rolefunctionlink role =em.find(Rolefunctionlink.class, id);
		em.remove(role);
	}

	@SuppressWarnings("unchecked")
	public List<Rolefunctionlink> findbyRolefunctionListAll(Integer id) {
		String sqlQuery = " from Rolefunctionlink rf inner join fetch rf.function f inner join fetch rf.role r where rf.role.roleId = :roleId";
		return em.createQuery(sqlQuery).setParameter("roleId", id).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Rolefunctionlink> findfunctionbyRoleListAll(Integer roleId) {
		
		String sqlQuery = " from Rolefunctionlink rf inner join fetch rf.function f inner join fetch rf.role r where rf.role.roleId = :roleId";
		return em.createQuery(sqlQuery).setParameter("roleId", roleId).getResultList();
	
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<Rolefunctionlink> findfunctionbyRoleListBranch(Integer branchId) {		
		String sqlQuery = " from Rolefunctionlink rf inner join fetch rf.function f inner join fetch rf.role r where r.branch.branchId = :branchId";
		return em.createQuery(sqlQuery).setParameter("branchId", branchId).getResultList();
	
	}
		
	
	

}
