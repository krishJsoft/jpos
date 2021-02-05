package com.project.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.project.dao.interfaces.IProductbatchDAO;
import com.project.model.his.Batchmomenthistry;
import com.project.model.his.Invoicedispatch;
import com.project.model.his.Productbatch;
import com.project.model.his.Salesorderbreakdown;
import com.project.util.StringConstants;


/*
 * @author Gopal Ar
 * @version 1.0
 * @since 01 Nov 2013
 */

@Repository("productbatchRepository")
public class ProductbatchDAOImpl implements IProductbatchDAO {
	
	public static Logger log = LoggerFactory.getLogger(ProductbatchDAOImpl.class);
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
	public boolean createProductbatch(Productbatch productbatch)
			throws Exception {
		try {
			em.persist(productbatch);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in createProductbatch ProductbatchDAOImpl " + e);
			throw e;
		}
	}

	@Override
	public boolean updateProductbatch(Productbatch productbatch)
			throws Exception {
		try {
			em.merge(productbatch);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in updateProductbatch ProductbatchDAOImpl " + e);
			throw e;
		}
	}

	@Override
	public boolean deleteProductbatch(Productbatch productbatch)
			throws Exception {
		try {
			em.remove(productbatch);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in deleteProductbatch ProductbatchDAOImpl " + e);
			throw e;
		}
	}

	@Override
	public Productbatch getProductbatchDetails(Integer batchId)
			throws Exception {
		Productbatch retData = null;
		String sqlQuery = "select t from Productbatch t  WHERE t.batchId = :batchId ";
		try {
			retData = (Productbatch) em.createQuery(sqlQuery).setParameter("batchId", batchId).getSingleResult();

		} catch (Exception e) {
			log.info("Error in getProductbatchDetails ProductbatchDAOImpl " + e);
			throw e;
		}
		return retData;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Productbatch> getProductbatchDetails(String batchNo ,String deliveryOrderNo,
			Integer productId,String status,Date dateFrom, Date dateTo,Integer branchRecordId,String productCode,Date expairyDate) throws Exception {
		List<Productbatch> batchList=null;		
		try {			
			StringBuilder buf = new StringBuilder("select t from Productbatch t  inner join fetch t.product p WHERE 1 = 1");

			if (deliveryOrderNo != null && deliveryOrderNo != "" && (!deliveryOrderNo.equalsIgnoreCase("0")) && (!deliveryOrderNo.equalsIgnoreCase(""))) 
				{
				buf.append("  AND t.deliveryOrderNo= :deliveryOrderNo   ");	
				}	
			
			if (batchNo != null && batchNo != "" && (!batchNo.equalsIgnoreCase("0")) && (!batchNo.equalsIgnoreCase(""))) 
				{
				buf.append("  AND t.batchNo= :batchNo   ");	
				}	
			
			if (productId != null && productId!=0) {
				buf.append("  AND p.productId= :productId   ");	
				}
			
			if (productCode != null && productCode != "" && (!productCode.equalsIgnoreCase("0")) && (!productCode.equalsIgnoreCase(""))) 
			{
				buf.append("  AND p.barcode= :productCode   ");	
				}
			
			if (branchRecordId != null && branchRecordId!=0) {
				buf.append("  AND t.branch.branchId= :branchRecordId   ");	
				}
			
			if (status != null && status != "" && (!status.equalsIgnoreCase("0")) && (!status.equalsIgnoreCase(""))) 
			{
				buf.append("  AND t.status= :status   ");	
				}	
			if ((dateFrom != null) && (dateFrom != null)) {
				buf.append("  AND t.expairyDate between  :dateFrom and :dateTo   ");
			}	
			
			if (expairyDate != null) {
				buf.append("  AND t.expairyDate = :expairyDate   ");
			}	
			
			buf.append(" order by  t.expairyDate asc  ");
			
			Query query = em.createQuery(buf.toString());			
			if (deliveryOrderNo != null && deliveryOrderNo != "" && (!deliveryOrderNo.equalsIgnoreCase("0")) && (!deliveryOrderNo.equalsIgnoreCase(""))) 
			{
				query.setParameter("deliveryOrderNo", deliveryOrderNo);				
			}					
			
			if (batchNo != null && batchNo != "" && (!batchNo.equalsIgnoreCase("0")) && (!batchNo.equalsIgnoreCase(""))) 
			{
				query.setParameter("batchNo", batchNo);				
			}					
			
			if ((productId != null) && productId!=0 )
			{
				query.setParameter("productId", productId);				
			}	
			
			if (productCode != null && productCode != "" && (!productCode.equalsIgnoreCase("0")) && (!productCode.equalsIgnoreCase(""))) 
			{
				query.setParameter("productCode", productCode);		
			}
			
			if (branchRecordId != null && branchRecordId!=0) {				
				query.setParameter("branchRecordId", branchRecordId);	
			}
			
			if (status != null && status != "" && (!status.equalsIgnoreCase("0")) && (!status.equalsIgnoreCase(""))) 
			{
				query.setParameter("status", status);				
			}	
			if (expairyDate !=null)
			{
				query.setParameter("expairyDate", expairyDate);				
			}	
			
			if ((dateFrom != null) && (dateTo != null))
			{
				query.setParameter("dateFrom", dateFrom);
				query.setParameter("dateTo", dateTo);
			}	
			
			batchList = query.getResultList();
			
			} catch (Exception e) {
				log.info("Error in getProductbatchDetails ProductbatchDAOImpl " + e);
				throw e;
			}
			return batchList;
	}
	
	
	
	@Override
	public boolean createBatchmomenthistry(Batchmomenthistry productbatch)
			throws Exception {
		try {
			em.persist(productbatch);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in createBatchmomenthistry ProductbatchDAOImpl " + e);
			throw e;
		}
	}
	
	
	@Override
	public boolean updateBatchmomenthistry(Batchmomenthistry productbatch)
			throws Exception {
		try {
			em.merge(productbatch);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in updateBatchmomenthistry ProductbatchDAOImpl " + e);
			throw e;
		}
	}
	
	
	@Override
	public boolean deleteBatchmomenthistry(Batchmomenthistry productbatch)
			throws Exception {
		try {
			em.remove(productbatch);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in deleteBatchmomenthistry ProductbatchDAOImpl " + e);
			throw e;
		}
	}
	
	
	@Override
	public Batchmomenthistry getBatchmomenthistryDetails(Integer batchMomentId)
			throws Exception {
		Batchmomenthistry retData = null;
		String sqlQuery = "select t from Batchmomenthistry t  WHERE t.batchMomentId = :batchMomentId ";
		try {
			retData = (Batchmomenthistry) em.createQuery(sqlQuery).setParameter("batchMomentId", batchMomentId).getSingleResult();

		} catch (Exception e) {
			log.info("Error in getBatchmomenthistryDetails ProductbatchDAOImpl " + e);
			throw e;
		}
		return retData;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Batchmomenthistry> getBatchmomenthistryDetails(String batchNo,
			Integer productId, String status, Integer branchRecordId,
			String productCode, Date expdate) throws Exception {
		List<Batchmomenthistry> batchList=null;		
		try {			
			StringBuilder buf = new StringBuilder("select t from Batchmomenthistry t  inner join fetch t.product p WHERE 1 = 1");

			if (batchNo != null && batchNo != "" && (!batchNo.equalsIgnoreCase("0")) && (!batchNo.equalsIgnoreCase(""))) 
				{
				buf.append("  AND t.batchNo= :batchNo   ");	
				}	
			
			if (productId != null && productId!=0) {
				buf.append("  AND p.productId= :productId   ");	
				}
			
			if (productCode != null && productCode != "" && (!productCode.equalsIgnoreCase("0")) && (!productCode.equalsIgnoreCase(""))) 
			{
				buf.append("  AND p.barcode= :productCode   ");	
				}
			
			if (branchRecordId != null && branchRecordId!=0) {
				buf.append("  AND t.branch.branchId= :branchRecordId   ");	
				}
			
			if (status != null && status != "" && (!status.equalsIgnoreCase("0")) && (!status.equalsIgnoreCase(""))) 
			{
				buf.append("  AND t.status= :status   ");	
				}	
			
			
			if (expdate != null) {
				buf.append("  AND t.expdate = :expdate   ");
			}	
			
			buf.append(" order by  t.expdate asc  ");
			
			Query query = em.createQuery(buf.toString());			
			
			if (batchNo != null && batchNo != "" && (!batchNo.equalsIgnoreCase("0")) && (!batchNo.equalsIgnoreCase(""))) 
			{
				query.setParameter("batchNo", batchNo);				
			}					
			
			if ((productId != null) && productId!=0 )
			{
				query.setParameter("productId", productId);				
			}	
			
			if (productCode != null && productCode != "" && (!productCode.equalsIgnoreCase("0")) && (!productCode.equalsIgnoreCase(""))) 
			{
				query.setParameter("productCode", productCode);		
			}
			
			if (branchRecordId != null && branchRecordId!=0) {				
				query.setParameter("branchRecordId", branchRecordId);	
			}
			
			if (status != null && status != "" && (!status.equalsIgnoreCase("0")) && (!status.equalsIgnoreCase(""))) 
			{
				query.setParameter("status", status);				
			}	
			if (expdate !=null)
			{
				query.setParameter("expdate", expdate);				
			}						
			
			batchList = query.getResultList();
			
			} catch (Exception e) {
				log.info("Error in getBatchmomenthistryDetails ProductbatchDAOImpl " + e);
				throw e;
			}
			return batchList;
	}

}
