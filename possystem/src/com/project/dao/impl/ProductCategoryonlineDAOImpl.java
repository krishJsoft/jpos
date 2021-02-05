package com.project.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.interfaces.IProductcategoryonlineDAO;
import com.project.model.his.Productcategory;
import com.project.util.StringConstants;

@Repository("productCategoryonlineRepository")
public class ProductCategoryonlineDAOImpl implements IProductcategoryonlineDAO{
	
	public static Logger log = LoggerFactory.getLogger(ProductCategoryonlineDAOImpl.class);
	long l = 0;
	private EntityManager em = null;
	
	@PersistenceContext(unitName="onlinedb",type=PersistenceContextType.TRANSACTION)
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
	public Productcategory fetchOnlineCategoryDetails(Integer categoryId, String name, Integer level,
			String status, Integer onlineBranchId) throws Exception {
		Productcategory data = null;
		StringBuilder buf = new StringBuilder("select pc from Productcategory pc  INNER JOIN FETCH pc.branch b WHERE 1 = 1 ");
		try {
			

			if (categoryId !=null && categoryId !=0) {
				buf.append(" And pc.categoryId = :categoryId ");
			}	
			
			if (name !=null && (!name.equalsIgnoreCase(""))) {
				buf.append(" And pc.name = :name ");
			}	
			
			if (level !=null && level !=0) {
				buf.append(" And pc.level = :level ");
			}	
			
			if (status !=null && (!status.equalsIgnoreCase(""))) {
				buf.append(" And pc.status = :status ");
			}	
			
			if (onlineBranchId !=null && onlineBranchId !=0) {
				buf.append(" And b.branchId = :branchId ");
			}	
			
			Query query = em.createQuery(buf.toString());		

			if (categoryId !=null && categoryId !=0) {
				query.setParameter("categoryId", categoryId);

			}	
			
			if (name !=null && (!name.equalsIgnoreCase(""))) {
				query.setParameter("name", name);			
	
			}
			
			if (level !=null && level !=0) {
				query.setParameter("level", level);
			}	
			
			if (status !=null && (!status.equalsIgnoreCase(""))) {
				query.setParameter("status", status);			
			}	
			if (onlineBranchId !=null && onlineBranchId !=0) {
				query.setParameter("branchId", onlineBranchId);

			}	
			
			data = (Productcategory)query.getSingleResult();

		} catch (Exception e) {
			log.info("Error in fetchOnlineCategoryDetails ProductCategoryonlineDAOImpl " + e);
			throw e;
		}
		return data;
	}
	
	
	@Override
	public boolean createNewProductcategory(Productcategory pcategory) throws Exception {
		try {
			
		//	em.getTransaction().begin();
			em.persist(pcategory);
		//	em.getTransaction().commit();
			//em.persist(pcategory);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in createNewProductcategory ProductCategoryonlineDAOImpl " + e);
			throw e;
		}
	}	
	
}
