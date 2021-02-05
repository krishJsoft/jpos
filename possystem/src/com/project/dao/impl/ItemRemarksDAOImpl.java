package com.project.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.interfaces.IItemRemarksDAO;
import com.project.model.his.Itemremarksfunctionlist;
import com.project.model.his.Itemremarkslist;
import com.project.model.his.Productcategory;
import com.project.util.StringConstants;

@Repository("itemRemarksRespository")
public class ItemRemarksDAOImpl implements IItemRemarksDAO{
	public static Logger log = LoggerFactory.getLogger(ExpensesDAOImpl.class);
	long l = 0;
	private EntityManager em = null;

	@PersistenceContext(unitName=StringConstants.HIS_SERVER_PERSISTENCE_NAME,type = PersistenceContextType.EXTENDED)
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
	public List<Itemremarkslist> fetchItemRemarksList(Integer remarksId, Integer branchId) throws Exception {
		List<Itemremarkslist> dataList=null;
		try {
			StringBuilder hsql = new StringBuilder(
					"SELECT r from Itemremarkslist r inner join fetch r.branch b  WHERE 1 = 1 "
			);
		
			if (remarksId !=null && remarksId !=0) 
			{
				hsql.append("AND r.id =  :remarksId  ");
			}
			if (branchId !=null && branchId !=0) 
			{
				hsql.append("AND b.branchId =  :branchId  ");
			}
			
			hsql.append("ORDER BY r.remarksName ASC ");
			Query query=em.createQuery(hsql.toString());
			if (remarksId !=null && remarksId !=0) 
			{
				query.setParameter("remarksId", remarksId);
			}
			if (branchId !=null && branchId !=0) 
			{
				query.setParameter("branchId", branchId);
			}
			dataList=query.getResultList();
			
		}catch(Exception ex) {
			log.info("Error in fetchItemRemarksList ItemRemarksDAOImpl " +ex);
			throw ex;
		}
		return dataList;
	}
	
	@Override
	public List<Itemremarksfunctionlist> fetchItemRemarksFunctionList(Integer remarksId, Integer remarksListId,
			Integer branchId, Integer categoryId) throws Exception {
		List<Itemremarksfunctionlist> dataList=null;

		try {
			StringBuilder hsql = new StringBuilder(
					"SELECT ir from Itemremarksfunctionlist ir inner join fetch ir.remarks r inner join fetch ir.branch b inner join fetch ir.productcategory pc WHERE 1 = 1 "
			);
			if (remarksListId !=null && remarksListId !=0) 
			{
				hsql.append("AND r.id =  :remarksListId  ");
			}
			if (branchId !=null && branchId !=0) 
			{
				hsql.append("AND b.branchId =  :branchId  ");
			}
			if(categoryId !=null && categoryId !=0) {
				hsql.append("AND pc.categoryId  in (:categoryId) ");
				//hsql.append("AND pc.parentCategoryId  in (:categoryId) ");
			}
			hsql.append("ORDER BY pc.name,r.remarksName ");
			Query query=em.createQuery(hsql.toString());
			if (remarksListId !=null && remarksListId !=0) 
			{
				query.setParameter("remarksListId", remarksListId);
			}
			
			
			if (branchId !=null && branchId !=0) 
			{
				query.setParameter("branchId", branchId);
			}
			if (categoryId !=null && categoryId !=0) 
			{
				query.setParameter("categoryId", categoryId);
			}	
			dataList=query.getResultList();
		}catch(Exception ex) {
			log.info("Error in fetchItemRemarksList ItemRemarksDAOImpl " +ex);
			throw ex;
		}
		return dataList;
	}

	@Override
	public List<Productcategory> fetchRemarksItemCategory() throws Exception {
		List<Productcategory> dataList=null;
		try {
			Productcategory d=new Productcategory();
			String hsql="SELECT pc FROM Productcategory pc "
					+ "WHERE pc.parentCategoryId = 38 AND pc.categoryId != 124 "
					//+ "WHERE pc.parentCategoryId between 110 AND 135 AND pc.parentCategoryId != 124 "
					+ "ORDER BY pc.name";
					
			Query query=em.createQuery(hsql);
			dataList=query.getResultList();
			
		}catch(Exception ex) {
			log.info("Error in fetchRemarksItemCategory ItemRemarksDAOImpl " +ex);
			throw ex;
		}
		
		return dataList;
	}

	@Transactional
	@Override
	public boolean createRemarks(Itemremarksfunctionlist remarks) {
	
		try {
			em.persist(remarks);
			return true;
		}catch (Exception ex) {
			ex.printStackTrace();
			log.info("Error in fetchRemarksItemCategory ItemRemarksDAOImpl " + ex);
			throw ex;
		}
	}
	
	@Transactional
	@Override
	public boolean createRemarksName(Itemremarkslist remarksNameObj) {
		try {
			em.persist(remarksNameObj);
			return true;
		}catch (Exception ex) {
			ex.printStackTrace();
			log.info("Error in createRemarksName ItemRemarksDAOImpl " + ex);
			throw ex;
		}
	}

	@Override
	public Itemremarksfunctionlist fetchSelectedItemRemarksList(Integer remarksId, Integer remarksListId,
			Integer categoryId, Integer branchId) {
		Itemremarksfunctionlist data=null;
		try {
			StringBuilder hsql = new StringBuilder(
					"SELECT ir from Itemremarksfunctionlist ir inner join fetch ir.remarks r inner join fetch ir.branch b inner join fetch ir.productcategory pc WHERE 1 = 1 "
			);
			
			if (remarksId !=null && remarksId !=0) 
			{
				hsql.append("AND ir.id =  :remarksId  ");
			}
			if (remarksListId !=null && remarksListId !=0) 
			{
				hsql.append("AND r.id =  :remarksListId  ");
			}
			if (branchId !=null && branchId !=0) 
			{
				hsql.append("AND b.branchId =  :branchId  ");
			}
			if(categoryId !=null && categoryId !=0) {
				//hsql.append("AND pc.categoryId  in (:categoryId) ");
				hsql.append("AND pc.parentCategoryId  in (:categoryId) ");
			}
			hsql.append("ORDER BY r.remarksName");
			
			Query query=em.createQuery(hsql.toString());
			
			if (remarksId !=null && remarksId !=0) 
			{
				query.setParameter("remarksId", remarksId);
			}
			if (remarksListId !=null && remarksListId !=0) 
			{
				query.setParameter("remarksListId", remarksListId);
			}
			
			if (branchId !=null && branchId !=0) 
			{
				query.setParameter("branchId", branchId);
			}
			if (categoryId !=null && categoryId !=0) 
			{
				query.setParameter("categoryId", categoryId);
			}	
			
			data=(Itemremarksfunctionlist)query.getSingleResult();
		}catch(Exception ex) {
			log.info("Error in fetchSelectedItemRemarksList ItemRemarksDAOImpl " +ex);
			throw ex;
		}
		return data;
	}

	@Transactional
	@Override
	public boolean deleteRemarks(Itemremarksfunctionlist ItemRemarksFunctionList) {
		try {
			em.remove(ItemRemarksFunctionList);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.info("Error in deleteRemarks ItemRemarksDAOImpl " + ex);
			throw ex;
		}
	}

	@Override
	public boolean remarksCategoryIsExist(Integer remarksListId, int categoryId, int branchId) {
		
		boolean exist=true;
		try {
			String hsql = "SELECT ir FROM Itemremarksfunctionlist ir INNER JOIN FETCH "
					+ "ir.remarks r INNER JOIN FETCH ir.branch b "
					+ "INNER JOIN FETCH ir.productcategory pc "
					+ "WHERE r.id=:remarksListId "
					+ "AND pc.categoryId=:categoryId "
					+ "AND b.branchId=:branchId ";
			Query query = em.createQuery(hsql);
			query.setParameter("remarksListId", remarksListId);
			query.setParameter("categoryId", categoryId);
			query.setParameter("branchId",branchId);
			
			@SuppressWarnings("unchecked")
			List<Itemremarksfunctionlist> data = query.getResultList();
			if (data.isEmpty()) {
				exist = false;
			}
		}catch (Exception ex) {
			ex.printStackTrace();
			log.info("Error in deleteRemarks ItemRemarksDAOImpl " + ex);
			throw ex;
		}
		return exist;
	}
	
	@Transactional
	@Override
	public boolean createRemarksFunctionList(List<Itemremarksfunctionlist> remarksFunctionList) {

        int batchSize = 10;
        int i=0;
		try {
		
			for(Itemremarksfunctionlist data:remarksFunctionList) {
				
				if (i % batchSize == 0 && i > 0) {
					em.flush();
					em.clear();
	            }
				em.persist(data);
				

				i++;
			}
			
			em.flush();
			em.clear();
			return true;
			
		}catch (Exception ex) {
			ex.printStackTrace();
			log.info("Error in createRemarksFunctionList ItemRemarksDAOImpl " + ex);
			throw ex;
		}
	}
	
	@Transactional
	@Override
	public boolean deleteRemarksFunctionList(List<Itemremarksfunctionlist> remarksFunctionList) {

        int batchSize = 10;
        int i=0;
		try {
		
			for(Itemremarksfunctionlist data:remarksFunctionList) {
				
				if (i % batchSize == 0 && i > 0) {
					em.flush();
					em.clear();
	            }
				em.remove(em.contains(data) ? data : em.merge(data));

				i++;
			}
			
			em.flush();
			em.clear();
			return true;
			
		}catch (Exception ex) {
			ex.printStackTrace();
			log.info("Error in deleteRemarksFunctionList ItemRemarksDAOImpl " + ex);
			throw ex;
		}
	}
	
}
