package com.project.dao.impl;

import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.interfaces.IProductCategoryDAO;
import com.project.model.datamodel.ProductcategoryModel;
import com.project.model.his.Kategoriler;
import com.project.model.his.Productcategory;
import com.project.model.his.Productmaterail;
import com.project.model.his.Uomtype;
import com.project.util.StringConstants;

/*
 * @author Gopal Ar
 * @version 1.0
 * @since 17 June 2013
 * 
 */

@Repository("productCategoryRepository")
public class ProductCategoryDAOImpl implements IProductCategoryDAO {

	public static Logger log = LoggerFactory.getLogger(ProductCategoryDAOImpl.class);
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
	public boolean createNewProductcategory(Productcategory pcategory)
			throws Exception {
		try {
			
			em.persist(pcategory);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in createNewProductcategory ProductCategoryDAOImpl " + e);
			throw e;
		}
	}

	@Override
	public boolean updateProductcategory(Productcategory pcategory)
			throws Exception {
		try {
			em.merge(pcategory);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in updateProductcategory ProductCategoryDAOImpl " + e);
			throw e;
		}
	}

	@Override
	public boolean deleteProductcategory(Productcategory pcategory)
			throws Exception {
		try {
			em.remove(pcategory);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in deleteProductcategory ProductCategoryDAOImpl " + e);
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Productcategory> findByProductcategoryList(Integer categoryId,
			String Status) throws Exception {
		List<Productcategory> categories=null;
		StringBuilder buf = new StringBuilder("SELECT b FROM Productcategory b WHERE 1 = 1 ");
				
		try {
			
		if (categoryId !=null && categoryId !=0) {
			buf.append(" And b.categoryId = :categoryId ");
		}	
		
		if (Status !=null && (!Status.equalsIgnoreCase(""))) {
			buf.append(" And b.status = :status ");
		}		
		
		buf.append(" order by b.categoryId");
		Query query = em.createQuery(buf.toString());		
		
		if (categoryId !=null && categoryId !=0) {
			query.setParameter("categoryId", categoryId);
		}		
		
		if (Status !=null && (!Status.equalsIgnoreCase(""))) {
			query.setParameter("status", Status);
		}		 
		categories=query.getResultList();
		
		} catch (Exception e) {
			log.info("Error in findByProductcategoryList ProductCategoryDAOImpl " + e);
			throw e;
		}
		return categories;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Productcategory> findByProductcategoryChildList(
			Integer parentCategoryId, String Status) throws Exception {
		List<Productcategory> categories=null;
		StringBuilder buf = new StringBuilder("SELECT b FROM Productcategory b inner join fetch b.productcategory p WHERE 1 = 1 ");
				
		try {
			
		if (parentCategoryId !=null && parentCategoryId !=0) {
			buf.append(" And p.categoryId = :categoryId ");
		}	
		
		if (Status !=null && (!Status.equalsIgnoreCase(""))) {
			buf.append(" And b.status = :status ");
		}		
		
		buf.append(" order by b.name");
		Query query = em.createQuery(buf.toString());		
		
		if (parentCategoryId !=null && parentCategoryId !=0) {
			query.setParameter("categoryId", parentCategoryId);
		}		
		
		if (Status !=null && (!Status.equalsIgnoreCase(""))) {
			query.setParameter("status", Status);
		}		 
		categories=query.getResultList();
		
		} catch (Exception e) {
			log.info("Error in findByProductcategoryChildList ProductCategoryDAOImpl " + e);
			throw e;
		}
		return categories;
	}

	@Override
	public Productcategory getProductcategoryDetails(Integer categoryId)
			throws Exception {
		Productcategory retData = null;
		String sqlQuery = "select t from Productcategory t  WHERE t.categoryId = :categoryId ";
		try {
			retData = (Productcategory) em.createQuery(sqlQuery).setParameter("categoryId", categoryId).getSingleResult();

		} catch (Exception e) {
			log.info("Error in getProductcategoryDetails ProductCategoryDAOImpl " + e);
			throw e;
		}
		return retData;
	}

	
	@Override
	public boolean findCategoryExites(Integer categoryId, Integer parentCategoryId,String categoryName)
			throws Exception {
		boolean exits = true;
		try {
			String hslQuery = "from Productcategory p where p.parentCategoryId = :parentCategoryId and p.name= :categoryName";
			Query query = em.createQuery(hslQuery).setParameter("parentCategoryId", categoryId).setParameter("categoryName", categoryName);
			@SuppressWarnings("unchecked")
			List<Productcategory> categoryNameExits = query.getResultList();
			if (categoryNameExits.isEmpty()) {
				exits = false;
			}
		} catch (Exception e) {
			log.info("Error in getProductcategoryDetails ProductCategoryDAOImpl " + e);
			throw e;
		}
		return exits;
	}
	
	
	
	
	@Override
	public boolean createNewBom(Productmaterail bom)	throws Exception {
		try {
			em.persist(bom);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in createNewBom ProductCategoryDAOImpl " + e);
			throw e;
		}
	}

	@Override
	public boolean updateBom(Productmaterail bom)
			throws Exception {
		try {
			em.merge(bom);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in updateBom ProductCategoryDAOImpl " + e);
			throw e;
		}
	}

	@Override
	public boolean deleteBom(Productmaterail bom)
			throws Exception {
		try {
			em.remove(bom);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in deleteBom ProductCategoryDAOImpl " + e);
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Productmaterail> findByBomList(Integer productId,	String Status) throws Exception {
		List<Productmaterail> categories=null;
		StringBuilder buf = new StringBuilder("SELECT b FROM Productmaterail b inner join fetch b.productchild inner join fetch b.productparent inner join fetch b.product   WHERE 1 = 1 ");
				
		try {
			
		if (productId !=null && productId !=0) {
			buf.append(" And b.product.productId = :productId ");
		}	
		
		if (Status !=null && (!Status.equalsIgnoreCase(""))) {
			buf.append(" And b.status = :status ");
		}		
		
		buf.append(" order by b.product.productId");
		
		Query query = em.createQuery(buf.toString());		
		
		if (productId !=null && productId !=0) {
			query.setParameter("productId", productId);
		}		
		
		if (Status !=null && (!Status.equalsIgnoreCase(""))) {
			query.setParameter("status", Status);
		}		 
		categories=query.getResultList();
		
		} catch (Exception e) {
			log.info("Error in findByBomList ProductCategoryDAOImpl " + e);
			throw e;
		}
		return categories;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Productmaterail> findByBomChildList(
			Integer parentCategoryId, String Status) throws Exception {
		List<Productmaterail> categories=null;
		StringBuilder buf = new StringBuilder("SELECT b FROM Productmaterail b inner join fetch b.productcategory p WHERE 1 = 1 ");
				
		try {
			
		if (parentCategoryId !=null && parentCategoryId !=0) {
			buf.append(" And p.categoryId = :categoryId ");
		}	
		
		if (Status !=null && (!Status.equalsIgnoreCase(""))) {
			buf.append(" And b.status = :status ");
		}		
		
		buf.append(" order by b.name");
		Query query = em.createQuery(buf.toString());		
		
		if (parentCategoryId !=null && parentCategoryId !=0) {
			query.setParameter("categoryId", parentCategoryId);
		}		
		
		if (Status !=null && (!Status.equalsIgnoreCase(""))) {
			query.setParameter("status", Status);
		}		 
		categories=query.getResultList();
		
		} catch (Exception e) {
			log.info("Error in findByProductcategoryChildList ProductCategoryDAOImpl " + e);
			throw e;
		}
		return categories;
	}

	@Override
	public Productmaterail getBomDetails(Integer bomId)
			throws Exception {
		Productmaterail retData = null;
		String sqlQuery = "select t from Productmaterail t  WHERE t.categoryId = :categoryId ";
		try {
			retData = (Productmaterail) em.createQuery(sqlQuery).setParameter("bomId", bomId).getSingleResult();

		} catch (Exception e) {
			log.info("Error in getProductcategoryDetails ProductCategoryDAOImpl " + e);
			throw e;
		}
		return retData;
	}

	
	@Override
	public boolean findBomExites(Integer categoryId, Integer parentCategoryId,String categoryName)
			throws Exception {
		boolean exits = true;
		try {
			String hslQuery = "from Productcategory p where p.parentCategoryId = :parentCategoryId and p.name= :categoryName";
			Query query = em.createQuery(hslQuery).setParameter("parentCategoryId", categoryId).setParameter("categoryName", categoryName);
			@SuppressWarnings("unchecked")
			List<Productcategory> categoryNameExits = query.getResultList();
			if (categoryNameExits.isEmpty()) {
				exits = false;
			}
		} catch (Exception e) {
			log.info("Error in getProductcategoryDetails ProductCategoryDAOImpl " + e);
			throw e;
		}
		return exits;
	}
	
	
	
	
	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Kategoriler> findAll()
			{
		List<Kategoriler> categories=null;
		StringBuilder buf = new StringBuilder("SELECT k FROM Kategoriler k");
				
		try {		
		Query query = em.createQuery(buf.toString());		
		categories=query.getResultList();
		
		} catch (Exception e) {
			log.info("Error in findAll ProductCategoryDAOImpl " + e);
			//throw e;
		}
		return categories;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Kategoriler findByKategoriId(Integer kategoriId)
			{
		Kategoriler retData = null;
		String sqlQuery = "select t from Kategoriler t  WHERE t.kategoriId = :kategoriId ";
		try {
			retData = (Kategoriler) em.createQuery(sqlQuery).setParameter("kategoriId", kategoriId).getSingleResult();

		} catch (Exception e) {
			log.info("Error in findByKategoriId ProductCategoryDAOImpl " + e);
			//throw e;
		}			
		return retData;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Kategoriler> findByKategoriAdi(Integer kategoriAdi)
			{
		List<Kategoriler> categories=null;
		StringBuilder buf = new StringBuilder("SELECT k FROM Kategoriler k WHERE 1 = 1 ");
				
		try {
			
		if (kategoriAdi !=null && kategoriAdi !=0) {
			buf.append(" And k.kategoriAdi = :kategoriAdi ");
		}				
		
		Query query = em.createQuery(buf.toString());		
		
		if (kategoriAdi !=null && kategoriAdi !=0) {
			query.setParameter("kategoriAdi", kategoriAdi);
		}		
		categories=query.getResultList();
		
		} catch (Exception e) {
			log.info("Error in findByProductcategoryList ProductCategoryDAOImpl " + e);
			//throw e;
		}
		return categories;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Kategoriler> findByKatUstId(Integer katUstId)  {
		List<Kategoriler> categories=null;
		StringBuilder buf = new StringBuilder("SELECT k FROM Kategoriler k WHERE 1 = 1 ");
				
		try {
			
		if (katUstId !=null && katUstId !=0) {
			buf.append(" And k.katUstId = :katUstId ");
		}				
		
		Query query = em.createQuery(buf.toString());		
		
		if (katUstId !=null && katUstId !=0) {
			query.setParameter("kategoriAdi", katUstId);
		}		
		categories=query.getResultList();
		
		} catch (Exception e) {
			log.info("Error in findByProductcategoryList ProductCategoryDAOImpl " + e);
			//throw e;
		}
		return categories;
	}

	@Override
	public boolean createNewUom(Uomtype uom) throws Exception {
		try {
			em.persist(uom);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in createNewUom ProductCategoryDAOImpl " + e);
			throw e;
		}
	}

	@Override
	public boolean updateUom(Uomtype uom) throws Exception {
		try {
			em.merge(uom);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in updateUom ProductCategoryDAOImpl " + e);
			throw e;
		}
	}

	@Override
	public boolean deleteUom(Uomtype uom) throws Exception {
		try {
			em.remove(uom);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in deleteUom ProductCategoryDAOImpl " + e);
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Uomtype> findByUomtypeList(String UOMName) throws Exception {
		List<Uomtype> uomtyps=null;
		StringBuilder buf = new StringBuilder("SELECT k FROM Uomtype k WHERE 1 = 1 ");
				
		try {
			
			if (UOMName !=null && (!UOMName.equalsIgnoreCase(""))) {
			buf.append(" And k.UOMName = :UOMName ");
		}				
		
		Query query = em.createQuery(buf.toString());		
		
		if (UOMName !=null && (!UOMName.equalsIgnoreCase(""))) {
			query.setParameter("UOMName", UOMName);
		}		
		uomtyps=query.getResultList();
		
		} catch (Exception e) {
			log.info("Error in findByUomtypeList ProductCategoryDAOImpl " + e);
			//throw e;
		}
		return uomtyps;
	}

	@Override
	public boolean findUomtypeExites(String UOMName) throws Exception {
		boolean exits = true;
		try {
			String hslQuery = "from Uomtype p where p.UOMName = :UOMName";
			Query query = em.createQuery(hslQuery).setParameter("UOMName", UOMName);
			@SuppressWarnings("unchecked")
			List<Uomtype> uomtypeExits = query.getResultList();
			if (uomtypeExits.isEmpty()) {
				exits = false;
			}
		} catch (Exception e) {
			log.info("Error in findUomtypeExites ProductCategoryDAOImpl " + e);
			throw e;
		}
		return exits;
	}

	@Override
	public Uomtype getUomtypeDetails(Integer UOMId) throws Exception {
		Uomtype retData = null;
		String sqlQuery = "select t from Uomtype t  WHERE t.UOMId = :UOMId ";
		try {
			retData = (Uomtype) em.createQuery(sqlQuery).setParameter("UOMId", UOMId).getSingleResult();

		} catch (Exception e) {
			log.info("Error in getUomtypeDetails ProductCategoryDAOImpl " + e);
			throw e;
		}
		return retData;
	}


	@Override
	public List<Integer> getValidCategoryIdWithProducts() throws Exception {
		List<Integer> catId=null;
		try {
			String statement="SELECT DISTINCT pc.categoryid FROM Product p " + 
					"INNER JOIN Productcategory pc " + 
					"WHERE pc.categoryid=p.categoryid AND pc.status='1' "+
					"ORDER BY pc.name";
			Query q=em.createNativeQuery(statement);
			catId=q.getResultList();
		}catch(Exception ex) {
			log.info("Error in getValidCategoryIdWithProducts ProductCategoryDAOImpl " + ex);
			throw ex;
		}
		return catId;
	}

	@Override
	public List<Productcategory> findByParentCategoryId(Integer categoryId, String Status) throws Exception {
		List<Productcategory> categories=null;
		StringBuilder buf = new StringBuilder("SELECT pc FROM Productcategory pc WHERE 1 = 1 ");
				
		try {
			
		if (categoryId !=null && categoryId !=0) {
			buf.append(" And pc.parentCategoryId = :categoryId ");
		}	
		
		if (Status !=null && (!Status.equalsIgnoreCase(""))) {
			buf.append(" And pc.status = :status ");
		}		
		
		buf.append(" ORDER BY pc.name");
		Query query = em.createQuery(buf.toString());		
		
		if (categoryId !=null && categoryId !=0) {
			query.setParameter("categoryId", categoryId);
		}		
		
		if (Status !=null && (!Status.equalsIgnoreCase(""))) {
			query.setParameter("status", Status);
		}		 
		categories=query.getResultList();
		
		} catch (Exception e) {
			log.info("Error in findByParentCategoryId ProductCategoryDAOImpl " + e);
			throw e;
		}
		return categories;
		
	}

	@Override
	public List<Productcategory> loadCategoryWithoutPrinter(Integer branchId) throws Exception {
		List<Productcategory> categories=null;
		try {
			StringBuilder buf = new StringBuilder("SELECT pc FROM Productcategory pc "
					+ "LEFT JOIN pc.printer pr "
					+ "JOIN FETCH pc.branch b "
					+ "WHERE 1 = 1 "
					+ "AND pr.id is null "
					+ "AND pc.level >2 ");
			if (branchId !=null && branchId !=0) {
				buf.append(" And b.branchId = :branchId ");
			}	
			buf.append(" ORDER BY pc.name");

			Query query = em.createQuery(buf.toString());	
			
			if (branchId !=null && branchId !=0) {
				query.setParameter("branchId", branchId);
			}		 
			categories=query.getResultList();
			
		}catch (Exception e) {
			log.info("Error in loadCategoryWithoutPrinter ProductCategoryDAOImpl " + e);
			throw e;
		}
		return categories;
	
	}
	
	@Override
	public List<Productcategory> loadCategoryListForPrinter(Integer printerId, Integer branchId) throws Exception {
		List<Productcategory> categories=null;
		StringBuilder buf = new StringBuilder("SELECT pc FROM Productcategory pc "
				+ "JOIN FETCH pc.printer pr "
				+ "JOIN FETCH pc.branch b "
				+ "WHERE 1 = 1 ");
				
		try {
			
		if (printerId !=null && printerId !=0) {
			buf.append("And pr.id = :printerId ");
		}	
		
		if (branchId !=null && branchId !=0) {
			buf.append(" And b.branchId = :branchId ");
		}		
		buf.append(" ORDER BY pc.name");
		Query query = em.createQuery(buf.toString());		
		
		if (printerId !=null && printerId !=0) {
			query.setParameter("printerId", printerId);
		}		
		
		if (branchId !=null && branchId !=0) {
			query.setParameter("branchId", branchId);
		}		 
		categories=query.getResultList();
		
		} catch (Exception e) {
			log.info("Error in loadCategoryListForPrinter ProductCategoryDAOImpl " + e);
			throw e;
		}
		return categories;
	}

	@Transactional
	@Override
	public void updatePrinterIdForCategory(Integer printerId,List<Integer> idList) throws Exception {
		try {
			
			StringBuilder buf = new StringBuilder("UPDATE Productcategory SET printerId=:printerId ");
			for(int i=0;i<idList.size();i++) {
				if(i==0)
					buf.append("WHERE categoryId=:categoryId"+i+" ");
				else {
					buf.append("OR categoryId=:categoryId"+i+" ");	
				}
							
			}
		
			Query query = em.createNativeQuery(buf.toString());//createQuery();
			
			query.setParameter("printerId",printerId);

			for(int i=0;i<idList.size();i++) {
				query.setParameter("categoryId"+i,idList.get(i));
			}
			
			int result = query.executeUpdate();
				
		} catch (Exception ex) {
			log.info("Error in updatePrinterIdForCategory ProductDAOImpl " + ex);
			throw ex;
		}
		
	}

	@Override
	public Productcategory getProductcategoryDetails2(int categoryId) throws Exception {
		Productcategory retData = null;
		String sqlQuery = "FROM Productcategory pc LEFT JOIN FETCH pc.printer pr JOIN FETCH pc.branch WHERE pc.categoryId = :categoryId ";
		try {
			retData = (Productcategory) em.createQuery(sqlQuery).setParameter("categoryId", categoryId).getSingleResult();

		} catch (Exception e) {
			log.info("Error in getProductcategoryDetails2 ProductCategoryDAOImpl " + e);
			throw e;
		}
		return retData;
	}

	@Override
	public List<Integer> getValidCategoryIdWithProducts2(int branchId) throws Exception {
		List<Integer> catId=null;

		try {
			String statement="SELECT DISTINCT pc.categoryid FROM Product p " + 
					"INNER JOIN Productcategory pc " + 
					"WHERE pc.categoryid=p.categoryid AND pc.status='1' AND pc.branchId=:branchId "
					+ "AND pc.online !=1 "
					+" ORDER BY pc.name ";
			Query q=em.createNativeQuery(statement);
			q.setParameter("branchId",branchId);
			catId=q.getResultList();
		}catch(Exception ex) {
			log.info("Error in getValidCategoryIdWithProducts2 ProductCategoryDAOImpl " + ex);
			throw ex;
		}
		return catId;
	}

	@Override
	public List<Productcategory> findByProductcategoryList2(Integer categoryId, String Status, 
			Integer branchId,Boolean online,Integer syncStatus)
			throws Exception {
		List<Productcategory> categories=null;
		StringBuilder buf = new StringBuilder("SELECT b FROM Productcategory b JOIN FETCH b.branch br WHERE 1 = 1 ");
				
		try {
			
		if (categoryId !=null && categoryId !=0) {
			buf.append(" And b.categoryId = :categoryId ");
		}	
		
		if (Status !=null && (!Status.equalsIgnoreCase(""))) {
			buf.append(" And b.status = :status ");
		}	
		
		if (branchId !=null && branchId!=0) {
			buf.append(" And br.branchId=:branchId ");
		}	
		
		if (online !=null ) {
			buf.append(" And b.online=:online ");
		}	
		
		if (syncStatus !=null ) {
			buf.append(" And b.syncStatus >= :syncStatus ");
		}		
		buf.append(" order by b.categoryId ");
		Query query = em.createQuery(buf.toString());		
		
		if (categoryId !=null && categoryId !=0) {
			query.setParameter("categoryId", categoryId);
		}		
		
		if (Status !=null && (!Status.equalsIgnoreCase(""))) {
			query.setParameter("status", Status);
		}	
		if (branchId !=null && branchId!=0) {
			query.setParameter("branchId", branchId);
		}	
		if (online !=null ) {
			query.setParameter("online", online);
		}	
		if (syncStatus !=null ) {
			query.setParameter("syncStatus", syncStatus);
		}	
		categories=query.getResultList();
		
		} catch (Exception e) {
			log.info("Error in findByProductcategoryList2 ProductCategoryDAOImpl " + e);
			throw e;
		}
		return categories;
	}

	@Override
	public List<Productcategory> getCategoryPrinterList(Integer branchId) throws Exception {
		List<Productcategory> categories=null;
		StringBuilder buf = new StringBuilder("SELECT pc FROM Productcategory pc JOIN FETCH  pc.branch b JOIN FETCH pc.printer pr "
				+ "WHERE b.branchId=:branchId ");
				
		try {
			
		
		buf.append(" GROUP BY pr.id ");
		Query query = em.createQuery(buf.toString());		
		
		if (branchId !=null && branchId!=0) {
			query.setParameter("branchId", branchId);
		}	
		categories=query.getResultList();
		
		} catch (Exception e) {
			log.info("Error in findByProductcategoryList2 ProductCategoryDAOImpl " + e);
			throw e;
		}
		return categories;
	}



}
