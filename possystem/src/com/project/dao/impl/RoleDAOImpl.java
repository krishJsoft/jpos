package com.project.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.interfaces.IRoleDAO;
import com.project.model.his.Function;
import com.project.model.his.Module;
import com.project.model.his.Rolefunctionlink;
import com.project.model.his.Role;
import com.project.util.StringConstants;


@Repository("RoleRepository")
@Transactional
public class RoleDAOImpl implements IRoleDAO {
	
	public static Logger log = LoggerFactory.getLogger(RoleDAOImpl.class);

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

	public void save(Role role) {
		log.info("Save Userrole Values  : " +ToStringBuilder.reflectionToString(role,ToStringStyle.MULTI_LINE_STYLE));
		em.persist(role);
	}
	
	@Override
	public void update(Role role) {
		log.info("Update Userrole Values  : " +ToStringBuilder.reflectionToString(role,ToStringStyle.MULTI_LINE_STYLE));
		em.merge(role);
	}

	@Override
	public void delete(Role role) {
		log.info("delete Userrole Values  : " +ToStringBuilder.reflectionToString(role,ToStringStyle.MULTI_LINE_STYLE));
		em.remove(role);
		// TODO Auto-generated method stub
	}

	@SuppressWarnings("unchecked")
	public List<Role> findByRoleListAll(Integer roleId,Integer branchId) {
		
		StringBuilder buf = new StringBuilder("select r from Role r  WHERE 1 = 1 ");
		
		if (roleId !=null && roleId !=0) {
			buf.append(" AND r.roleId =  :roleId  ");
		}
		
		if (branchId !=null && branchId !=0) {
			buf.append(" AND r.branch.branchId =  :branchId  ");
		}
		
		Query query = em.createQuery(buf.toString());
		
		if (roleId !=null && roleId !=0) 
		{
			query.setParameter("roleId", roleId);
		}	
		if (branchId !=null && branchId !=0) 
		{
			query.setParameter("branchId", branchId);
		}	
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Function> findbyRoleModuleListAll() {
		String sqlQuery = "select f from Function f inner join fetch f.module m inner join fetch m.modulemaster inner join fetch f.functionmaster where m.status!='0' ";
		return em.createQuery(sqlQuery).getResultList();
	}

	
	@SuppressWarnings("unchecked")
	public List<Module> findModuleListAll() {
		String sqlQuery = "select f from Module f inner join fetch f.modulemaster mm where f.status!='0' order by  mm.mastermoduleId  ";
		return em.createQuery(sqlQuery).getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Role> findByRolesingleList(Integer id) {
		String sqlQuery = "select r from Role r WHERE roleId = :roleId";
		return em.createQuery(sqlQuery).setParameter("roleid", id).getResultList();
	}

	public Role get(Integer id) {	
		Role dept = (Role) em.createQuery("select r from Role r WHERE r.roleId = :roleId").setParameter("roleId", id).getSingleResult();

		// Persists to db
		return dept;
	}

	@SuppressWarnings("unchecked")
	public List<Rolefunctionlink> findbyRolefunctionListAll(Integer id) {
		String sqlQuery = "select l from Rolefunctionlink l " 
				+ " inner join fetch l.function "
				+ " inner join fetch l.role "
				+ "where l.role.roleId = :roleId";
		return em.createQuery(sqlQuery).setParameter("roleId", id).getResultList();
	}

	@SuppressWarnings("unchecked")
	public boolean findRoleNameExits(String roleName) {
		boolean exits=true;
		List<Role> roleExit;
		roleExit= em.createQuery("select r from Role r WHERE r.roleName = :roleName").setParameter("roleName", roleName).getResultList();
		if(roleExit.isEmpty()){
			exits=false;
		}
		return exits;
	}

	@SuppressWarnings("unchecked")
	public List<Role> searchList(Integer id) {
		if(id!=null && id!=0){
			String sqlQuery = "select r from Role r WHERE roleId = :roleId";
			return em.createQuery(sqlQuery).setParameter("roleId", id).getResultList();
		}else{
			String sqlQuery = "select r from Role r";
			return em.createQuery(sqlQuery).getResultList();
		}
	}

}
