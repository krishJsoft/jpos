package com.project.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.interfaces.IShiftDAO;
import com.project.model.his.Shift;
import com.project.util.StringConstants;

@Repository("shiftRepository")
public class ShiftDAOImpl implements IShiftDAO{
	public static Logger log = LoggerFactory.getLogger(ShiftDAOImpl.class);

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
	public Boolean createNewShift(Shift shiftObj) throws Exception {
		try {
			em.persist(shiftObj);
			return true;
		} catch (Exception ex) {
			
			log.info("Error in createNewShift ShiftDAOImpl " + ex);
			throw ex;
		}
	}

	@Override
	public List<Shift> loadShiftAll() throws Exception {
		
		String sqlQuery=" from Shift ";
		return em.createQuery(sqlQuery).getResultList();
	}

	@Transactional
	@Override
	public boolean updateShift(Shift shiftObj) throws Exception {
		try {
			em.merge(shiftObj);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in updateShift ShiftDAOImpl " + e);
			throw e;
		}
	}

	@Override
	public boolean shiftIsExist(String shiftName) throws Exception {
		boolean exist = true;
		try {
			String hsql="from Shift s where s.shiftName=:shiftName";
			Query query = em.createQuery(hsql);
			query.setParameter("shiftName", shiftName);
			
			@SuppressWarnings("unchecked")
			List<Shift> shiftNameExist=query.getResultList();
			if(shiftNameExist.isEmpty()) {
				exist=false;
			}

		}catch(Exception ex) {
			log.info("Error in shiftIsExist ShiftDAOImpl " + ex);
			throw ex;
		}
		return exist;
	}

	@Override
	public Shift loadShift(int shiftID) throws Exception {
		String sqlQuery=" from Shift s where s.id=:shiftID";
		return (Shift) em.createQuery(sqlQuery).setParameter("shiftID", shiftID).getSingleResult();
	}

	@Override
	public boolean deleteShift(Shift shift) throws Exception {
		try {
			em.remove(shift);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.info("Error in deleteShift ShiftDAOImpl " + ex);
			throw ex;
		}
	}
}
