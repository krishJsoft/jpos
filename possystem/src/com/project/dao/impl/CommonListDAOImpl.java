package com.project.dao.impl;

import java.io.Serializable;
import java.sql.DatabaseMetaData;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.interfaces.ICommonListDAO;
import com.project.model.his.Autonum;
import com.project.model.his.Department;
import com.project.model.his.Designation;
import com.project.model.his.Function;
import com.project.model.his.Hoteltable;
import com.project.model.his.Hoteltablearea;
import com.project.model.his.Module;
import com.project.model.his.Rolefunctionlink;
import com.project.model.his.Taxcode;
import com.project.model.his.Taxmaster;
import com.project.model.invoice.customer.CustomerPerformanceModel;
import com.project.util.StringConstants;


/**
 * Gopal Ar , gopalabe@gmail.com , Dec 02-2012
 */

@Repository("commonListRepository")
public class CommonListDAOImpl implements ICommonListDAO, Serializable {

	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(CommonListDAOImpl.class);
	private EntityManager em = null;

	@PersistenceContext(unitName=StringConstants.HIS_SERVER_PERSISTENCE_NAME)
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> getDepartmentListByBranch(Integer branchId) throws Exception {
		List<Department> data = null;
		try {
			String sqlQuery = " select dept from Department dept where dept.branch.branchId=:branchId ";
			data = em.createQuery(sqlQuery).setParameter("branchId", branchId).getResultList();
		} catch (Exception e) {
			logger.info("Error in getDepartmentList of CommonListDAOImpl "
					+ e.toString());
			throw e;
		} finally {

		}
		return data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Designation> getDesignationList() throws Exception {
		List<Designation> data = null;
		try {
			String sqlQuery = " select desg from Designation desg";
			data = em.createQuery(sqlQuery).getResultList();
		} catch (Exception e) {
			logger.info("Error in getDesignationList of CommonListDAOImpl "
					+ e.toString());
			throw e;
		} finally {

		}
		return data;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Module> getModuleList() throws Exception {
		List<Module> data = null;
		try {
			String sqlQuery = " select m from Module m ";
			data = em.createQuery(sqlQuery).getResultList();
		} catch (Exception e) {
			logger.info("Error in getModuleList of CommonListDAOImpl "
					+ e.toString());
			throw e;
		} finally {

		}
		return data;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Function> getFunctionList(Integer moduleId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Function> getFunctionList() throws Exception {
		List<Function> data = null;
		try {
			String sqlQuery = " select f from Function f ";
			data = em.createQuery(sqlQuery).getResultList();
		} catch (Exception e) {
			logger.info("Error in getFunctionList of CommonListDAOImpl "
					+ e.toString());
			throw e;
		} finally {

		}
		return data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rolefunctionlink> getRolefunctionlinkList(Integer roleId)
			throws Exception {
		List<Rolefunctionlink> data = null;
		try {
			String sqlQuery = " from Rolefunctionslink rf inner join fetch rf.function f where rf.role.roleId = :roleid";
			data = em.createQuery(sqlQuery).setParameter("roleid", roleId)
					.getResultList();
		} catch (Exception e) {
			logger.info("Error in getRolefunctionlinkList of CommonListDAOImpl "
					+ e.toString());
			throw e;
		} finally {

		}
		return data;
	}

	@Override
	public Autonum getAutonumDetail(Integer branchRecordId) throws Exception {
		Autonum retData = null;
		String sqlQuery = "select e from Autonum e ";
		try {
			StringBuilder buf = new StringBuilder("from Autonum rf WHERE 1=1 ");

			if (branchRecordId !=null && branchRecordId!=0) {
				buf.append(" AND rf.branch.branchId =  :branchRecordId  ");
			}				
			
			Query query = em.createQuery(buf.toString());	
			
			if (branchRecordId !=null && branchRecordId!=0) {
				query.setParameter("branchRecordId", branchRecordId);
			}	
			
			retData = (Autonum) query.getSingleResult();
		} catch (Exception e) {
			logger.info("Error in getAutonumDetail of CommonListDAOImpl "
					+ e.toString());
			throw e;
		}
		return retData;
	}

	@Override
	public Autonum updateAutonumDetail(Autonum tutonum) throws Exception {
		try {
			em.merge(tutonum);
			return tutonum;
		} catch (Exception e) {			
			logger.info("Error in updateAutonumDetail of CommonListDAOImpl "+ e.toString());
			throw e;
		} finally {
			
		}
	}

	
	@Override
	public void saveDepartment(Department depObj) throws Exception {
		try {
			em.persist(depObj);
			
		} catch (Exception e) {			
			logger.info("Error in saveDepartment of CommonListDAOImpl "+ e.toString());
			throw e;
		} finally {
			
		}
		
	}

	@Override
	public void updateDepartment(Department depObj) throws Exception {
		try {
			em.merge(depObj);
			
		} catch (Exception e) {			
			logger.info("Error in updateDepartment of CommonListDAOImpl "+ e.toString());
			throw e;
		} finally {
			
		}
		
	}

	@Override
	public void deleteDepartment(Department depObj) throws Exception {
		try {
			em.remove(depObj);
		} catch (Exception e) {			
			logger.info("Error in deleteDepartment of CommonListDAOImpl "+ e.toString());
			throw e;
		} finally {
			
		}
		
	}

	@Override
	public Department getDepartment(Integer departmentId) throws Exception {
		Department retData = null;
		String sqlQuery = "select d from Department d " + " WHERE d.departmentId = :departmentId ";
		try {
			retData = (Department) em.createQuery(sqlQuery).setParameter("departmentId", departmentId).getSingleResult();

		} catch (Exception e) {
			logger.info("Error in getDepartment of CommonListDAOImpl "+ e.toString());
			throw e;
		}
		return retData;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> getDepartmentList(Integer departmentId,Integer branchId)
			throws Exception {
		List<Department> data = null;
		try {			
			StringBuilder buf = new StringBuilder("select d from Department d WHERE 1=1 ");

			if (departmentId !=null && departmentId!=0) {
				buf.append(" AND d.departmentId =  :departmentId  ");
			}	
			
			if (branchId !=null && branchId!=0) {
				buf.append(" AND d.branch.branchId =  :branchId  ");
			}	
			
			Query query = em.createQuery(buf.toString());	
			
			if (departmentId !=null && departmentId!=0) {
				query.setParameter("departmentId", departmentId);
			}	
			if (branchId !=null && branchId!=0) {
				query.setParameter("branchId", branchId);
			}	
			data = query.getResultList();
		} catch (Exception e) {
			logger.info("Error in getDepartmentList of CommonListDAOImpl "	+ e.toString());
			throw e;
		} finally {

		}
		return data;
	}

	
	
	@Override
	public void updateAutonum(Autonum autonum) throws Exception {
		try {
			em.merge(autonum);
			
		} catch (Exception e) {			
			logger.info("Error in updateAutonum of CommonListDAOImpl "+ e.toString());
			throw e;
		} finally {
			
		}
		
	}
		
	
	@Override
	public void createAutonum(Autonum autonum) throws Exception {
		try {
			em.persist(autonum);
			
		} catch (Exception e) {			
			logger.info("Error in createAutonum of CommonListDAOImpl "+ e.toString());
			throw e;
		} finally {
			
		}
		
	}
		
	
	@Transactional
	@Override
	public void deleteAutonum(Autonum autonum) throws Exception {
		try {
			em.remove(autonum);
			
		} catch (Exception e) {			
			logger.info("Error in deleteAutonum of CommonListDAOImpl "+ e.toString());
			throw e;
		} 
			
	}
			
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Autonum> getAutonumList(Integer branchRecordId) throws Exception {
		List<Autonum> data = null;
		try {			
			StringBuilder buf = new StringBuilder("from Autonum rf WHERE 1=1 ");

			if (branchRecordId !=null && branchRecordId!=0) {
				buf.append(" AND rf.branch.branchId =  :branchRecordId  ");
			}				
			
			Query query = em.createQuery(buf.toString());	
			
			if (branchRecordId !=null && branchRecordId!=0) {
				query.setParameter("branchRecordId", branchRecordId);
			}				
			data = query.getResultList();
			
		} catch (Exception e) {
			logger.info("Error in getAutonumList of CommonListDAOImpl "
					+ e.toString());
			throw e;
		} finally {

		}
		return data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Taxcode> getTaxcodeList(Integer branchId) throws Exception {
		List<Taxcode> data = null;
		try {
			String sqlQuery = " select t from Taxcode t where t.branch.branchId=:branchId ";
			data = em.createQuery(sqlQuery).setParameter("branchId", branchId).getResultList();
		} catch (Exception e) {
			logger.info("Error in getTaxcodeList of CommonListDAOImpl "
					+ e.toString());
			throw e;
		} finally {

		}
		return data;
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	public String getDatabasePath(String s) throws Exception {
		
		List<Object[]> data=null;
		String DBPath="";
		try {
			String sqlQuery = " select @@basedir ";
			data = em.createNativeQuery(sqlQuery).getResultList();
			
				org.hibernate.engine.spi.SessionImplementor sessionImp =(org.hibernate.engine.spi.SessionImplementor) em.getDelegate();
				DatabaseMetaData metadata = sessionImp.connection().getMetaData();				
				metadata.getDatabaseProductName();
				metadata.getURL();
				
				String databaseName = metadata.getDatabaseProductName();
				String URL = metadata.getURL();
				String driver = metadata.getDriverName();
				String userName = metadata.getUserName();
			
				
				
			/*	for(Object[] tempdata :data)
				{					
					DBPath=""+tempdata[0];
				}*/
			   
		} catch (Exception e) {
			logger.info("Error in getDatabasePath of CommonListDAOImpl "
					+ e.toString());
			throw e;
		} finally {

		}
		return DBPath;
	}
	
	
	
	@Override
	public Taxmaster getTaxmasterDetail(Integer taxid) throws Exception {
		Taxmaster retData = null;
		String sqlQuery = "select e from Taxmaster e ";
		try {
			StringBuilder buf = new StringBuilder("select rf from Taxmaster rf inner join fetch rf.branch WHERE 1=1 ");

			if (taxid !=null && taxid!=0) {
				buf.append(" AND rf.taxid =  :taxid  ");
			}				
			
			Query query = em.createQuery(buf.toString());	
			
			if (taxid !=null && taxid!=0) {
				query.setParameter("taxid", taxid);
			}	
			
			retData = (Taxmaster) query.getSingleResult();
		} catch (Exception e) {
			logger.info("Error in getTaxmasterDetail of CommonListDAOImpl "
					+ e.toString());
			throw e;
		}
		return retData;
	}

	@Override
	public void updateTaxmaster(Taxmaster taxmaster) throws Exception {
		try {
			em.merge(taxmaster);
			
		} catch (Exception e) {			
			logger.info("Error in updateTaxmaster of CommonListDAOImpl "+ e.toString());
			throw e;
		} finally {
			
		}
		
	}

	@Transactional
	@Override
	public void createTaxmaster(Taxmaster taxmaster) throws Exception {
		try {
			em.persist(taxmaster);
			
		} catch (Exception e) {			
			logger.info("Error in createTaxmaster of CommonListDAOImpl "+ e.toString());
			throw e;
		} finally {
			
		}
	}
	
	@Transactional
	@Override
	public void deleteTaxmaster(Taxmaster taxmaster) throws Exception {
		try {
			em.remove(taxmaster);			
		} catch (Exception e) {			
			logger.info("Error in deleteTaxmaster of CommonListDAOImpl "+ e.toString());
			throw e;
		} finally {
			
		}
	}
	
	

	@Override
	public List<Taxmaster> getTaxmasterList(Integer branchRecordId)
			throws Exception {
		List<Taxmaster> data = null;
		try {			
			StringBuilder buf = new StringBuilder("from Taxmaster rf WHERE 1=1 ");				
			
			
			if (branchRecordId !=null && branchRecordId!=0) {
				buf.append(" AND rf.branch.branchId =  :branchid  ");
			}				
			
			Query query = em.createQuery(buf.toString());	
			
			if (branchRecordId !=null && branchRecordId!=0) {
				query.setParameter("branchid", branchRecordId);
			}		
			
			
			data = query.getResultList();			
		} catch (Exception e) {
			logger.info("Error in getTaxmasterList of CommonListDAOImpl "
					+ e.toString());
			throw e;
		} finally {

		}
		return data;
	}

	
	@Override
	public List<Hoteltable> getHoteltableList(Integer branchId , String tableNo)
			throws Exception {
		List<Hoteltable> data = null;
		try {
			StringBuilder buf = new StringBuilder("select h from Hoteltable h where h.branch.branchId=:branchId ");	
			
			if (tableNo !=null && (!tableNo.equalsIgnoreCase(""))) {
				buf.append(" AND h.tableNo =  :tableNo  ");
			}
			
			Query query = em.createQuery(buf.toString());	
			
			query.setParameter("branchId", branchId);

			
			if (tableNo !=null && (!tableNo.equalsIgnoreCase(""))) {
				query.setParameter("tableNo", tableNo);
			}		
			
			data = query.getResultList();
			
		} catch (Exception e) {
			logger.info("Error in getHoteltableList of CommonListDAOImpl "
					+ e.toString());
			throw e;
		} finally {

		}
		return data;
	}

	
	@Transactional
	@Override
	public void createHoteltable(Hoteltable tab) throws Exception {
		try {
			if(tab.getTableid()==0)
			{
			em.persist(tab);	
			}
			else
			{
			em.merge(tab);
			}
		} catch (Exception e) {			
			logger.info("Error in createHoteltable of CommonListDAOImpl "+ e.toString());
			throw e;
		} finally {
			
		}
	}

	@Override
	public List<Hoteltablearea> fetchHoteltablearea(String areaName, Integer branchId) throws Exception {
		List<Hoteltablearea> data = null;
		try {
			StringBuilder buf = new StringBuilder("select hta from Hoteltablearea hta where hta.branch.branchId=:branchId ");	
			
			if (areaName !=null && (!areaName.equalsIgnoreCase(""))) {
				buf.append(" AND hta.areaName =  :areaName  ");
			}
			
			Query query = em.createQuery(buf.toString());
			query.setParameter("branchId", branchId);
			
			if (areaName !=null && (!areaName.equalsIgnoreCase(""))) {
				query.setParameter("areaName", areaName);
			}		
			
			data = query.getResultList();
			
		} catch (Exception e) {
			logger.info("Error in fetchHoteltablearea of CommonListDAOImpl "
					+ e.toString());
			throw e;
		}
		
		return data;
	}

	@Override
	public Boolean createHotelTableArea(Hoteltablearea tableArea) throws Exception {
		try {
			em.persist(tableArea);
			return true;
		} catch (Exception e) {			
			logger.info("Error in createHotelTableArea of CommonListDAOImpl "+ e.toString());
			throw e;
		} 
	}

	@Override
	public List<Hoteltable> fetchHotelTable(List<String> tableNameList, String status, Integer areaId, Integer branchId)
			throws Exception {
		List<Hoteltable> data = null;
		try {
/*			StringBuilder buf = new StringBuilder("SELECT h FROM Hoteltable h "
					+ "LEFT OUTER JOIN FETCH h.hoteltablearea hta "
					+ "WHERE h.branch.branchId=:branchId ");
					*/	
			StringBuilder buf=new StringBuilder("SELECT h.* FROM hoteltable as h "
					+ "left outer join hoteltablearea  as hta " 
					+"ON h.hoteltableareaId=hta.areaid " 
					+"WHERE h.branchrecordid=:branchId ");
			if (tableNameList !=null && tableNameList.size()>0) {
				buf.append(" AND h.tableName in  (:tableNameList)");
			}
			
			if (status !=null && (!status.equalsIgnoreCase(""))) {
				buf.append(" AND h.status =  :status  ");
			}
			
			if (areaId !=null ) {
				buf.append(" AND h.hoteltableareaId=  :areaId  ");
			}else {
				if (tableNameList ==null || tableNameList.size()==0) {	
					buf.append(" AND h.hoteltableareaId IS NULL ");
				}
			}
			buf.append("ORDER BY CAST(h.tableName AS unsigned)");
			
			//Query query = em.createQuery(buf.toString());
			Query query = em.createNativeQuery(buf.toString(),Hoteltable.class);
			query.setParameter("branchId", branchId);
			
			if (tableNameList !=null && tableNameList.size()>0) {
				query.setParameter("tableNameList", tableNameList);
			}
			
			if (status !=null && (!status.equalsIgnoreCase(""))) {
				query.setParameter("status", status);
			}
			
			if (areaId !=null) {
				query.setParameter("areaId", areaId);
			}		
			
			data = (List<Hoteltable>)query.getResultList();
			
		} catch (Exception e) {
			logger.info("Error in fetchHotelTable of CommonListDAOImpl "
					+ e.toString());
			throw e;
		}
		
		return data;
	}

	@Override
	@Transactional
	public boolean updateTableLayout(List<Hoteltable> dataList) throws Exception {
		try {
		    Session session = em.unwrap( Session.class );
		    for ( Hoteltable hotelTable: dataList ) {
		        session.update( hotelTable );
		    }

			return true;
		} catch (Exception e) {			
			logger.info("Error in updateTableLayout of CommonListDAOImpl "+ e.toString());
			throw e;
		}
	}
	
	

}
