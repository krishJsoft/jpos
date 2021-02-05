package com.project.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.project.dao.interfaces.ISupplierDAO;
import com.project.model.his.Productsupplier;
import com.project.model.his.Supplier;
import com.project.util.StringConstants;


/*
 * @author Gopal Ar
 * @version 1.0
 * @since 25 June 2013
 * 
 */

@Repository("supplierRepository")
public class SupplierDAOImpl implements ISupplierDAO {
	
	public static Logger log = LoggerFactory.getLogger(SupplierDAOImpl.class);
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
	public boolean createNewSupplier(Supplier supplier) throws Exception {
		try {
			em.persist(supplier);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in createNewSupplier SupplierDAOImpl " + e);
			throw e;
		}
	}

	@Override
	public boolean updateSupplier(Supplier supplier) throws Exception {
		try {
			em.merge(supplier);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in updateSupplier SupplierDAOImpl " + e);
			throw e;
		}
	}

	@Override
	public boolean deleteSupplier(Supplier supplier) throws Exception {
		try {
			em.remove(supplier);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in deleteSupplier SupplierDAOImpl " + e);
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Supplier> findBySupplierList(String companyRegNo, String Status)
			throws Exception {
		List<Supplier> supplierList=null;
		StringBuilder buf = new StringBuilder("SELECT b FROM Supplier b  WHERE 1 = 1  And b.supplierName!='UNKNOWN'");
				
		try {
			
		if (companyRegNo !=null && (!companyRegNo.equalsIgnoreCase(""))) {
			buf.append(" AND b.companyRegNo =  :companyRegNo  ");
		}	
		
		if (Status !=null && (!Status.equalsIgnoreCase(""))) {
			buf.append(" And b.status = :status ");
		}		
		
		buf.append(" order by b.companyName");
		
		Query query = em.createQuery(buf.toString());		
		
		if (companyRegNo !=null && (!companyRegNo.equalsIgnoreCase(""))) {
			query.setParameter("companyRegNo", companyRegNo);
		}	
		
		if (Status !=null && (!Status.equalsIgnoreCase(""))) {
			query.setParameter("status", Status);
		}		 
		supplierList=query.getResultList();
		
		} catch (Exception e) {
			log.info("Error in findBySupplierList SupplierDAOImpl " + e);
			throw e;
		}
		return supplierList;
	}

	@Override
	public Supplier getSupplierDetails(Integer supplierId) throws Exception {
		Supplier retData = null;
		String sqlQuery = "select t from Supplier t   WHERE t.supplierId = :supplierId ";
		try {
			retData = (Supplier) em.createQuery(sqlQuery).setParameter("supplierId", supplierId).getSingleResult();

		} catch (Exception e) {
			log.info("Error in getSupplierDetails SupplierDAOImpl " + e);
			throw e;
		}
		return retData;
	}

	@Override
	public boolean findSupplierCompanyRegNoExites(String companyRegNo)
			throws Exception {
		boolean exits = true;
		try {
			String hslQuery = "from Supplier p where p.companyRegNo = :companyRegNo";
			Query query = em.createQuery(hslQuery).setParameter("companyRegNo", companyRegNo);
			@SuppressWarnings("unchecked")
			List<Supplier> companyRegNoExits = query.getResultList();
			if (companyRegNoExits.isEmpty()) {
				exits = false;
			}
		} catch (Exception e) {
			log.info("Error in findSupplierCompanyRegNoExites SupplierDAOImpl " + e);
			throw e;
		}
		return exits;
	}

	
	
	@Override
	public boolean findSupplierCodeExites(String supplierCode)
			throws Exception {
		boolean exits = true;
		try {
			String hslQuery = "from Supplier p where p.supplierCode = :supplierCode";
			Query query = em.createQuery(hslQuery).setParameter("supplierCode", supplierCode);
			@SuppressWarnings("unchecked")
			List<Supplier> supplierCodeExits = query.getResultList();
			if (supplierCodeExits.isEmpty()) {
				exits = false;
			}
		} catch (Exception e) {
			log.info("Error in findSupplierCodeExites SupplierDAOImpl " + e);
			throw e;
		}
		return exits;
	}

	

	@Override
	public boolean findSupplierEmailExites(String email) throws Exception {
		boolean exits = true;
		try {
			String hslQuery = "from Supplier p where p.email = :email";
			Query query = em.createQuery(hslQuery).setParameter("email", email);
			@SuppressWarnings("unchecked")
			List<Supplier> supplierCodeExits = query.getResultList();
			if (supplierCodeExits.isEmpty()) {
				exits = false;
			}
		} catch (Exception e) {
			log.info("Error in findSupplierEmailExites SupplierDAOImpl " + e);
			throw e;
		}
		return exits;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Supplier> findsupplierLogin(String email)	throws Exception {
		List<Supplier> supplierList=null;
		StringBuilder buf = new StringBuilder("SELECT b FROM Supplier b  WHERE 1 = 1 ");
				
		try {
			
		if (email !=null && (!email.equalsIgnoreCase(""))) {
			buf.append(" AND b.email =  :email  ");
		}		
		buf.append(" And b.status = :status ");			
	
		Query query = em.createQuery(buf.toString());		
		
		if (email !=null && (!email.equalsIgnoreCase(""))) {
			query.setParameter("email", email);
		}			
		query.setParameter("status", "1");		 
		supplierList=query.getResultList();
		
		} catch (Exception e) {
			log.info("Error in findsupplierLogin SupplierDAOImpl " + e);
			throw e;
		}
		return supplierList;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Productsupplier> findproductsupplierList(Integer supplierId,Integer productId)	throws Exception {
		List<Productsupplier> supplierList=null;
		StringBuilder buf = new StringBuilder("SELECT b FROM Productsupplier b inner join fetch b.product p inner join fetch b.supplier s WHERE 1 = 1 ");
				
		try {
			
		if (supplierId !=null && supplierId!=0) {
			buf.append(" AND s.supplierId =  :supplierId  ");
		}	
		if (productId !=null && productId!=0) {
			buf.append(" AND p.productId =  :productId  ");
		}	
	
		Query query = em.createQuery(buf.toString());		
		
		if (supplierId !=null && supplierId!=0) {
			query.setParameter("supplierId", supplierId);
		}	
		if (productId !=null && productId!=0) {
			query.setParameter("productId", productId);
		}	
			 
		supplierList=query.getResultList();
		
		} catch (Exception e) {
			log.info("Error in findproductsupplierList SupplierDAOImpl " + e);
			throw e;
		}
		return supplierList;
	}


}
