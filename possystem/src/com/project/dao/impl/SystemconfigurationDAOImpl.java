package com.project.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.interfaces.ISystemconfigurationDAO;
import com.project.model.his.Kitchenprintercategorylink;
import com.project.model.his.Kitchenprinterlist;
import com.project.model.his.Systemconfiguration;
import com.project.model.his.Systemtype;
import com.project.util.StringConstants;


@Repository("systemconfigurationRepository")
public class SystemconfigurationDAOImpl implements ISystemconfigurationDAO {

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
	public Systemconfiguration getConfiguration(int branchId) throws Exception {
		Systemconfiguration configData=null;
		try {
			String hsql = "FROM Systemconfiguration sc inner join fetch sc.branch b join fetch sc.systemtype st WHERE sc.branch.branchId = :branchId";
			Query query = em.createQuery(hsql).setParameter("branchId", branchId);
			
			configData =  (Systemconfiguration) query.getSingleResult();
			
		}catch(Exception ex) {
			ex.printStackTrace();
			log.info("Error in getConfiguration SystemconfigurationDAOImpl "+ex);
			throw ex;

		}
		return configData;
	}
	@Override
	public List<Kitchenprinterlist> getKitchenPrinterList(String kitchenName, int branchId)
			throws Exception {
		List<Kitchenprinterlist> dataList=null;
		try {
			StringBuilder hsql = new StringBuilder("FROM Kitchenprinterlist kp "
					+ "join fetch kp.branch b "
					+ "WHERE 1 = 1 ");
		
			if (kitchenName !=null && kitchenName!="") {
				hsql.append("AND kp.kitchenName =  :kitchenName  ");
			}	
			
			if (branchId >0) {
				hsql.append("AND b.branchId =  :branchId  ");
			}	
			
			
			Query query = em.createQuery(hsql.toString());
			
			if (kitchenName !=null && kitchenName!="") {
				query.setParameter("kitchenName", kitchenName);
			}	
			
			if (branchId >0) {
				query.setParameter("branchId", branchId);
			}	
			
			dataList =  query.getResultList();
			
		}catch(Exception ex) {
			log.info("Error in getKitchenPrinterList SystemconfigurationDAOImpl "+ex);
			throw ex;

		}
		return dataList;
	}
	
	@Transactional
	@Override
	public Boolean createNewKitchenPrinter(Kitchenprinterlist printer) throws Exception {
		try {
			em.persist(printer);
			return true;
		}catch(Exception ex) {
			log.info("Error in createNewKitchenPrinter "+ex);
			throw ex;
		}
	}
	@Override
	public List<Kitchenprintercategorylink> getKitchenPrinterCategoryLinkList(Integer printerId, Integer categoryId,
			int branchId) throws Exception {
		List<Kitchenprintercategorylink> dataList=null;
		try {
			StringBuilder hsql = new StringBuilder("FROM Kitchenprintercategorylink kpl "
					+ "join fetch kpl.branch b "
					+ "join fetch kpl.printer p "
					+ "WHERE 1 = 1 ");
		
			if (printerId !=null && printerId!=0) {
				hsql.append("AND p.id =  :printerId  ");
			}	
			
			if (categoryId !=null && categoryId!=0) {
				hsql.append("AND kpl.categoryId =  :categoryId  ");
			}	
			
			if (branchId >0) {
				hsql.append("AND b.branchId =  :branchId  ");
			}	
			
			
			Query query = em.createQuery(hsql.toString());
			
			if (printerId !=null && printerId!=0) {
				query.setParameter("printerId", printerId);
			}	
			
			if (categoryId !=null && categoryId!=0) {
				query.setParameter("categoryId", branchId);
			}	
			
			if (branchId >0) {
				query.setParameter("branchId", branchId);
			}	
			
			dataList =  query.getResultList();
			
		}catch(Exception ex) {
			log.info("Error in getKitchenPrinterCategoryLinkList SystemconfigurationDAOImpl "+ex);
			throw ex;

		}
		return dataList;
	}
	@Override
	public Kitchenprinterlist getKitchenPrinterDetails(Integer printerId) throws Exception {
		Kitchenprinterlist data=null;
		try {
			StringBuilder hsql = new StringBuilder("FROM Kitchenprinterlist kp "
					+ "join fetch kp.branch b "
					+ "WHERE kp.id=:printerId");
		
			Query query = em.createQuery(hsql.toString()).setParameter("printerId", printerId);
						
			data =  (Kitchenprinterlist) query.getSingleResult();
			
		}catch(Exception ex) {
			log.info("Error in getKitchenPrinterDetails SystemconfigurationDAOImpl "+ex);
			throw ex;

		}
		return data;
	}
	@Override
	public List<Systemtype> fetchSystemTypeList() throws Exception {
		List<Systemtype> objList=null;
		try {
			String hsql = "FROM Systemtype st ";
			Query query = em.createQuery(hsql);
			objList=query.getResultList();
			
		}catch(Exception ex) {
			log.info("Error in getConfiguration SystemconfigurationDAOImpl "+ex);
			throw ex;

		}
		return objList;
	}
	@Override
	public boolean updateSystemConfiguration(Systemconfiguration config) throws Exception {
		try {
			em.merge(config);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in updateSystemConfiguration SystemconfigurationDAOImpl " + e);
			throw e;
		}
	}
	
	@Override
	public Systemtype getSystemTypeDetails(int typeId) throws Exception {
		Systemtype data=null;
		try {
			String hsql = "FROM Systemtype st WHERE st.id=:typeId";
			Query query = em.createQuery(hsql).setParameter("typeId", typeId);
			data=(Systemtype) query.getSingleResult();
			
		}catch(Exception ex) {
			log.info("Error in getSystemTypeDetails SystemconfigurationDAOImpl "+ex);
			throw ex;

		}
		return data;
	}
	
	@Override
	public boolean updateKitchenPrinter(Kitchenprinterlist printer) throws Exception {
		try {
			em.merge(printer);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in updateKitchenPrinter SystemconfigurationDAOImpl " + e);
			throw e;
		}
	}

}
