package com.project.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.interfaces.ISalesorderDAO;
import com.project.model.his.Deliveryorder;
import com.project.model.his.Deliveryorderbreakdown;
import com.project.model.his.Kitchenorderbreakdown;
import com.project.model.his.Kitchenorderremarksbreakdown;
import com.project.model.his.Kitchensorder;
import com.project.model.his.Pospayment;
import com.project.model.his.Quotation;
import com.project.model.his.Quotationbreakdown;
import com.project.model.his.Salesitem;
import com.project.model.his.Salesitembreakdown;
import com.project.model.his.Salesorder;
import com.project.model.his.Salesorderbreakdown;
import com.project.model.his.Salesorderbreakdownhold;
import com.project.model.his.Salesorderhold;
import com.project.model.his.Supplier;
import com.project.model.sale.sales.SalesorderModel;
import com.project.model.sale.sales.SalesorderbreakdownModel;
import com.project.util.ConvertUtil;
import com.project.util.StringConstants;
import com.google.common.primitives.Ints;

/*
 * @author Gopal Ar
 * @version 1.0
 * @since 10 Sep 2013
 */

@Repository("salesOrderRepository")
public class SalesorderDAOImpl implements ISalesorderDAO {
	
	public static Logger log = LoggerFactory.getLogger(SalesorderDAOImpl.class);
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
	public List<Salesorder> findBySalesorderListAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSalesorderCount(String salesOrderNo, Integer customerId,
			Integer branchId, Date dateFrom, Date dateTo, String status,Integer branchRecordId,String salesType,String paymentType, String salesRep)
			throws Exception {
		int countResult = 0;
		int[] retList = null;
		try {
			
			StringBuilder buf = new StringBuilder(
					"SELECT count(p) FROM Salesorder p   WHERE 1 = 1 ");

			if ((dateFrom != null) && (dateFrom != null)) {
				buf.append("  AND p.createdDate between  :dateFrom and :dateTo   ");
			}							
					
			if (salesOrderNo != null && salesOrderNo != "" && (!salesOrderNo.equalsIgnoreCase("0")) && (!salesOrderNo.equalsIgnoreCase(""))) 
			{
				buf.append(" AND  p.salesOrderNo =:salesOrderNo ");
			}
			
			if (customerId !=null && customerId !=0) {
				buf.append(" AND p.customer.customerId =  :customerId  ");
			}
			
			if (branchId !=null && branchId !=0) {
				buf.append(" AND p.branch2.branchId =  :branchId  ");
			}	
			
			if (branchRecordId !=null && branchRecordId !=0) {
				buf.append(" AND p.branch1.branchId =  :branchRecordId  ");
			}	
			
			if (status != null && status != "" && (!status.equalsIgnoreCase("0")) && (!status.equalsIgnoreCase(""))) 
			{
				buf.append(" AND  p.status =:status ");
			}
			if (salesType != null && salesType != "" && (!salesType.equalsIgnoreCase("0")) && (!salesType.equalsIgnoreCase(""))) 
			{
				buf.append(" AND  p.salesType =:salesType ");
			}
			if ((paymentType != null) && (paymentType != "")) {
				buf.append("  AND p.paymentType=:paymentType ");
			}
			if ((salesRep != null) && (salesRep != "")) {
				buf.append("  AND p.salesRep=:salesRep ");
			}
			
			Query query = em.createQuery(buf.toString());

			if ((dateFrom != null) && (dateTo != null))
			{
				query.setParameter("dateFrom", dateFrom);
				query.setParameter("dateTo", dateTo);
			}
			if (customerId !=null && customerId !=0) 
			{
				query.setParameter("customerId", customerId);
			}	
			
			if (branchId !=null && branchId !=0) 
			{
				query.setParameter("branchId", branchId);
			}	
			
			if (branchRecordId !=null && branchRecordId !=0) {
				query.setParameter("branchRecordId", branchRecordId);
			}
			
			if (salesOrderNo != null && salesOrderNo != "" && (!salesOrderNo.equalsIgnoreCase("0")) && (!salesOrderNo.equalsIgnoreCase(""))) 
			{
				query.setParameter("salesOrderNo", salesOrderNo);
			}			
			if (status != null && status != "" && (!status.equalsIgnoreCase("0")) && (!status.equalsIgnoreCase("")))  
			{
				query.setParameter("status", status);
			}	
			if (salesType != null && salesType != "" && (!salesType.equalsIgnoreCase("0")) && (!salesType.equalsIgnoreCase("")))  
			{
				query.setParameter("salesType", salesType);
			}	
			if ((paymentType != null) && (paymentType != "")) {
				query.setParameter("paymentType", paymentType);
			}
			if ((salesRep != null) && (salesRep != "")) {
				query.setParameter("salesRep", salesRep);
			}
			
			Number cResults = (Number) query.getSingleResult();
			countResult = Integer.parseInt(String.valueOf(cResults));
			
			//List<Integer> retData = query.getResultList();
			//retList = Ints.toArray(retData);
			}		
			catch (Exception e) {
			log.info("Error in getSalesorderCount of SalesorderDAOImpl "
					+ e.toString());
			throw e;
		} 

		return countResult;
	}


	
	
	
	
		
	@SuppressWarnings("unchecked")
	public List<Object[]> getDailySalesListChart(Date dateFrom,Date dateTo) throws Exception{
		List<Object[]> data = null;
		try{
			String buf="SELECT sum(so.totalAmount),so.createdDate from Salesorder so "
					+ "WHERE so.createdDate BETWEEN  :dateFrom AND :dateTo "
					+ "GROUP BY DATE(so.createdDate)";
		
			Query query = em.createQuery(buf.toString());
			query.setParameter("dateFrom", dateFrom);
			query.setParameter("dateTo", dateTo);
			
			data = query.getResultList();
		
		}
		catch (Exception e) {
			log.info("Error in getDailySalesListChart of SalesorderDAOImpl "
					+ e.toString());
			throw e;
		} 	
		return data;
		
	}
	
	public List<Object[]> getDailyStaffSalesChart(Date dateFrom,Date dateTo,Integer staffId) throws Exception{
		List<Object[]> data = null;
		try{
			String buf="SELECT kso.createdBy, DATE(kso.createdDate) createdDate,sum(kso.totalAmount) FROM kitchensorder kso "
					+ "WHERE kso.createdDate BETWEEN :dateFrom AND  :dateTo "
					+ "AND kso.createdBy =  "
					+ "("
					+ "SELECT bsm.emailAddress from branchstaffmembers bsm "
					+ "WHERE bsm.staffId = :staffId "
					+ ") "
					+ "GROUP BY createdDate "
					+ "ORDER BY createdDate";
		
			Query query = em.createNativeQuery(buf.toString());
			query.setParameter("dateFrom", dateFrom);
			query.setParameter("dateTo", dateTo);
			query.setParameter("staffId", staffId);
			data = query.getResultList();
		
		}
		catch (Exception e) {
			log.info("Error in getDailySalesListChart of SalesorderDAOImpl "
					+ e.toString());
			throw e;
		} 	
		return data;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getDailyTopSalesItemChart(Date dateFrom,Date dateTo,int rank) throws Exception{
		List<Object[]> data = null;
		try{
			
			String buf=""
					+"SELECT ranked.productName,ranked.createdDate, ranked.total "
					+"FROM "
					+ "("
					+"SELECT t1.productName, t1.createdDate createdDate,t1.total "
					+ ", @createdDate_rank/*'*/:=/*'*/ IF(@current_createdDate=createdDate,@createdDate_rank+1,1) AS createdDate_rank, "
					+ "@current_createdDate/*'*/:=/*'*/ createdDate "
					+ "FROM "
					+ "( "
					+ "SELECT SUM(sob.subTotal) total, po.productName , DATE(sob.createdDate) createdDate from salesorderbreakdown sob inner join product po "
					+ "WHERE sob.createdDate BETWEEN :dateFrom AND :dateTo AND po.productID = sob.ProductId "
					+ "GROUP BY DATE(sob.createdDate) ,sob.ProductId "
					+ "Order by createdDate, total DESC "
					+ ")  t1 "
					+ "ORDER BY createdDate, total) ranked "
					+ "WHERE createdDate_rank <= :rank "
				//	+"order by createdDate,total "
					;
		
			Query query = em.createNativeQuery(buf.toString());
			
			query.setParameter("dateFrom", dateFrom);
			query.setParameter("dateTo", dateTo);
			query.setParameter("rank", rank);
			//data = query.getResultList(); // executed to to prevent an unknown bug
			data = query.getResultList();
		
		}
		catch (Exception e) {
			log.info("Error in getDailyTopSalesItemChart of SalesorderDAOImpl "
					+ e.toString());
			throw e;
		} 	
		return data;
		
	}
	public List<Object[]> getQuartelySalesListChart(int year,int branchId) throws Exception{
		List<Object[]> data = null;
		try{
			String buf="SELECT sum(so.totalAmount), QUARTER(so.createdDate),so from Salesorder so "
					+ "WHERE YEAR(so.createdDate) = :year "
					+ "AND so.branch1.branchId=:branchId "
					+ "GROUP BY QUARTER(so.createdDate)";
			Query query = em.createQuery(buf.toString());
			query.setParameter("year", year);
			query.setParameter("branchId", branchId);
			data = query.getResultList();
		
		}
		catch (Exception e) {
			log.info("Error in getQuartelySalesListChart of SalesorderDAOImpl "
					+ e.toString());
			throw e;
		} 	
		return data;
	}
	
	public List<Object[]> getMonthlySalesListChart(int year,int branchId) throws Exception{
		List<Object[]> data = null;
		try{
			String buf="SELECT sum(so.totalAmount), MONTH(so.createdDate),so from Salesorder so "
					+ "WHERE YEAR (so.createdDate) = :year   "
					+ "AND so.branch1.branchId=:branchId "
					+ "GROUP BY MONTH(so.createdDate) ";
		
			Query query = em.createQuery(buf.toString());
			query.setParameter("year", year);
			query.setParameter("branchId", branchId);
			data = query.getResultList();
		
		}
		catch (Exception e) {
			e.printStackTrace();
			log.info("Error in getMonthlySalesListChart of SalesorderDAOImpl "
					+ e.toString());
			throw e;
		} 	
		return data;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Salesorder> getSalesorderList(int[] ids,String salesOrderNo,
			Integer customerId, Integer branchId, Date dateFrom, Date dateTo,
			String status, int start, int howMany,Integer branchRecordId,String salesType,String paymentType, String salesRep) throws Exception {
		List<Salesorder> data = null;
		try {			
		StringBuilder buf = new StringBuilder(
				"select t from Salesorder t inner join fetch t.branch2 b inner join fetch t.customer c  WHERE 1 = 1 ");

		if ((dateFrom != null) && (dateFrom != null)) {
			buf.append("  AND t.createdDate between  :dateFrom and :dateTo   ");
		}		
		if (customerId !=null && customerId !=0) {
			buf.append(" AND c.customerId =  :customerId  ");
		}			
		if (branchId !=null && branchId !=0) {
			buf.append(" AND b.branchId =  :branchId  ");
		}	
		
		if (ids !=null && ids.length !=0) {
			buf.append("  and t.salesOrderId in (:ids) ");
		}	
		
		if (branchRecordId !=null && branchRecordId !=0) {
			buf.append(" AND t.branch1.branchId =  :branchRecordId  ");
		}	
		if (salesOrderNo != null && salesOrderNo != "" && (!salesOrderNo.equalsIgnoreCase("0")) && (!salesOrderNo.equalsIgnoreCase(""))) 
		{
			buf.append(" AND  t.salesOrderNo =:salesOrderNo ");
		}		
		if (status != null && status != "" && (!status.equalsIgnoreCase("0")) && (!status.equalsIgnoreCase(""))) 
		{
			buf.append(" AND  t.status =:status ");
		}
		if (salesType != null && salesType != "" && (!salesType.equalsIgnoreCase("0")) && (!salesType.equalsIgnoreCase("")))  
		{
			buf.append(" AND  t.salesType =:salesType ");
		}	
		if ((paymentType != null) && (paymentType != "")) {
			buf.append("  AND t.paymentType=:paymentType ");
		}
		if ((salesRep != null) && (salesRep != "")) {
			buf.append("  AND t.salesRep=:salesRep ");
		}
		
		buf.append(" order by t.salesOrderId desc ");
		
		Query query = em.createQuery(buf.toString());

		if ((dateFrom != null) && (dateTo != null))
		{
			query.setParameter("dateFrom", dateFrom);
			query.setParameter("dateTo", dateTo);
		}		
		if (salesOrderNo != null && salesOrderNo != "" && (!salesOrderNo.equalsIgnoreCase("0")) && (!salesOrderNo.equalsIgnoreCase(""))) 
		{
			query.setParameter("salesOrderNo", salesOrderNo);
		}			
		if (customerId !=null && customerId !=0) 
		{
			query.setParameter("customerId", customerId);
		}
		if (branchId !=null && branchId !=0) 
		{
			query.setParameter("branchId", branchId);
		}		
		if (ids !=null && ids.length !=0) {
			query.setParameter("ids", ConvertUtil.getIntegerList(ids));
		}
		if (branchRecordId !=null && branchRecordId !=0) {
			query.setParameter("branchRecordId", branchRecordId);
		}
		if (status != null && status != "" && (!status.equalsIgnoreCase("0")) && (!status.equalsIgnoreCase("")))  
		{
			query.setParameter("status", status);
		}
		if (salesType != null && salesType != "" && (!salesType.equalsIgnoreCase("0")) && (!salesType.equalsIgnoreCase("")))  
		{
			query.setParameter("salesType", salesType);
		}	
		if ((paymentType != null) && (paymentType != "")) {
			query.setParameter("paymentType", paymentType);
		}
		if ((salesRep != null) && (salesRep != "")) {
			query.setParameter("salesRep", salesRep);
		}
		query.setFirstResult(start);
		query.setMaxResults(howMany);
		data = query.getResultList();
		}	
		catch (Exception e) {
		log.info("Error in getDeliveryorderList of SalesorderDAOImpl "
				+ e.toString());
		throw e;
	} 	
	return data;
	}

	
	@Override
	public List<Salesorder> getSalesorderReportList(String salesOrderNo,Integer customerId, Integer branchId, Date dateFrom, Date dateTo,
			String status, Integer branchRecordId, String paymentType , String salesRep) throws Exception {
		List<Salesorder> data = null;
		List<Salesorder> dbdata = new ArrayList<Salesorder>();

		try {			
			StringBuilder buf = new StringBuilder(
					"select t from Salesorder t inner join fetch t.branch2 b inner join fetch t.customer c  WHERE 1 = 1 ");
	
			if ((dateFrom != null) && (dateFrom != null)) {
				buf.append("  AND t.createdDate between  :dateFrom and :dateTo   ");
			}		
			if (customerId !=null && customerId !=0) {
				buf.append(" AND c.customerId =  :customerId  ");
			}			
			if (branchId !=null && branchId !=0) {
				buf.append(" AND b.branchId =  :branchId  ");
			}	
			
			
			if (branchRecordId !=null && branchRecordId !=0) {
				buf.append(" AND t.branch1.branchId =  :branchRecordId  ");
			}	
			if (salesOrderNo != null && salesOrderNo != "" && (!salesOrderNo.equalsIgnoreCase("0")) && (!salesOrderNo.equalsIgnoreCase(""))) 
			{
				buf.append(" AND  t.salesOrderNo =:salesOrderNo ");
			}		
			if (status != null && status != "" && (!status.equalsIgnoreCase("0")) && (!status.equalsIgnoreCase(""))) 
			{
				buf.append(" AND  t.status =:status ");
			}
			
			if ((paymentType != null) && (paymentType != "")) {
				buf.append("  AND t.paymentType=:paymentType ");
			}
			if ((salesRep != null) && (salesRep != "")) {
				buf.append("  AND t.salesRep=:salesRep ");
			}
			
			Query query = em.createQuery(buf.toString());
	
			if ((dateFrom != null) && (dateTo != null))
			{
				query.setParameter("dateFrom", dateFrom);
				query.setParameter("dateTo", dateTo);
			}		
			if (salesOrderNo != null && salesOrderNo != "" && (!salesOrderNo.equalsIgnoreCase("0")) && (!salesOrderNo.equalsIgnoreCase(""))) 
			{
				query.setParameter("salesOrderNo", salesOrderNo);
			}			
			if (customerId !=null && customerId !=0) 
			{
				query.setParameter("customerId", customerId);
			}
			if (branchId !=null && branchId !=0) 
			{
				query.setParameter("branchId", branchId);
			}		
			
			if (branchRecordId !=null && branchRecordId !=0) {
				query.setParameter("branchRecordId", branchRecordId);
			}
			if (status != null && status != "" && (!status.equalsIgnoreCase("0")) && (!status.equalsIgnoreCase("")))  
			{
				query.setParameter("status", status);
			}			
			
			if ((paymentType != null) && (paymentType != "")) {
				query.setParameter("paymentType", paymentType);
			}
			if ((salesRep != null) && (salesRep != "")) {
				query.setParameter("salesRep", salesRep);
			}
			
			
			data = query.getResultList();
			
			for(Salesorder dat:data)
			{
				StringBuilder buf1 = new StringBuilder("select t from Salesorderbreakdown t  inner join fetch t.product p inner join fetch p.productcategory c where t.salesorder.salesOrderId="+dat.getSalesOrderId() );
				Query query1 = em.createQuery(buf1.toString());
				List<Salesorderbreakdown> salesorderbreakdowns = query1.getResultList();
			
				dat.setSalesorderbreakdowns(salesorderbreakdowns);		
				dbdata.add(dat);
			}
			
		}	
		catch (Exception e) {
			log.info("Error in getSalesorderReportList of SalesorderDAOImpl "
				+ e.toString());
			throw e;
		} 	
		return dbdata;
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Salesitem> getSalesorderReportListowner(String salesOrderNo,Integer customerId, Integer branchId, Date dateFrom, Date dateTo,
			String status, Integer branchRecordId) throws Exception {
		List<Salesitem> data = null;
		try {			
		StringBuilder buf = new StringBuilder(
				"select t from Salesitem t inner join fetch t.branch2 b inner join fetch t.customer c  WHERE 1 = 1 ");

		if ((dateFrom != null) && (dateFrom != null)) {
			buf.append("  AND t.createdDate between  :dateFrom and :dateTo   ");
		}		
		if (customerId !=null && customerId !=0) {
			buf.append(" AND c.customerId =  :customerId  ");
		}			
		if (branchId !=null && branchId !=0) {
			buf.append(" AND t.branch1.branchId =  :branchId  ");
		}			
		
		if (branchRecordId !=null && branchRecordId !=0) {
			buf.append(" AND  b.branchId=  :branchRecordId  ");
			
		}	
		if (salesOrderNo != null && salesOrderNo != "" && (!salesOrderNo.equalsIgnoreCase("0")) && (!salesOrderNo.equalsIgnoreCase(""))) 
		{
			buf.append(" AND  t.salesOrderNo =:salesOrderNo ");
		}		
		if (status != null && status != "" && (!status.equalsIgnoreCase("0")) && (!status.equalsIgnoreCase(""))) 
		{
			buf.append(" AND  t.status =:status ");
		}
		
		Query query = em.createQuery(buf.toString());

		if ((dateFrom != null) && (dateTo != null))
		{
			query.setParameter("dateFrom", dateFrom);
			query.setParameter("dateTo", dateTo);
		}		
		if (salesOrderNo != null && salesOrderNo != "" && (!salesOrderNo.equalsIgnoreCase("0")) && (!salesOrderNo.equalsIgnoreCase(""))) 
		{
			query.setParameter("salesOrderNo", salesOrderNo);
		}			
		if (customerId !=null && customerId !=0) 
		{
			query.setParameter("customerId", customerId);
		}
		if (branchId !=null && branchId !=0) 
		{
			query.setParameter("branchId", branchId);
		}		
		
		if (branchRecordId !=null && branchRecordId !=0) {
			query.setParameter("branchRecordId", branchRecordId);
		}
		if (status != null && status != "" && (!status.equalsIgnoreCase("0")) && (!status.equalsIgnoreCase("")))  
		{
			query.setParameter("status", status);
		}			
		data = query.getResultList();
		}	
		catch (Exception e) {
		log.info("Error in getSalesorderReportList of SalesorderDAOImpl "
				+ e.toString());
		throw e;
	} 	
	return data;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Kitchensorder> getKitchenorderReportList(String tableNo,String cardNo,String salesOrderNo,String createdby,Integer customerId, Integer branchId, Date dateFrom, Date dateTo,
			String status, Integer branchRecordId) throws Exception {
		List<Kitchensorder> data = null;
		List<Kitchensorder> dbdata = new ArrayList<Kitchensorder>();
		try {			
		StringBuilder buf = new StringBuilder(
				"select t from Kitchensorder t "
				+ "inner join fetch t.branch2 b "
				+ "inner join fetch t.customer c  "
				+ "WHERE 1 = 1 ");

		if ((dateFrom != null) && (dateFrom != null)) {
			buf.append("  AND t.createdDate between  :dateFrom and :dateTo   ");
		}		
		if (customerId !=null && customerId !=0) {
			buf.append(" AND c.customerId =  :customerId  ");
		}			
		if (branchId !=null && branchId !=0) {
			buf.append(" AND b.branchId =  :branchId  ");
		}		
		
		if (createdby != null && createdby != "" && (!createdby.equalsIgnoreCase("0")) && (!createdby.equalsIgnoreCase(""))) 
		{
			buf.append(" AND  t.createdBy =:createdBy ");
		}	
		
		if (branchRecordId !=null && branchRecordId !=0) {
			buf.append(" AND t.branch1.branchId =  :branchRecordId  ");
		}	
		if (salesOrderNo != null && salesOrderNo != "" && (!salesOrderNo.equalsIgnoreCase("0")) && (!salesOrderNo.equalsIgnoreCase(""))) 
		{
			buf.append(" AND  t.salesOrderNo =:salesOrderNo ");
		}	
		if (tableNo != null && tableNo != "" && (!tableNo.equalsIgnoreCase("0")) && (!tableNo.equalsIgnoreCase(""))) 
		{
			buf.append(" AND  t.tableName =:tableNo ");
		}	
		if (cardNo != null && cardNo != "" && (!cardNo.equalsIgnoreCase("0")) && (!cardNo.equalsIgnoreCase(""))) 
		{
			buf.append(" AND  t.cardNo =:cardNo ");
		}	
		if (status != null && status != "" && (!status.equalsIgnoreCase("0")) && (!status.equalsIgnoreCase(""))) 
		{
			buf.append(" AND  t.status =:status ");
		}
		
		Query query = em.createQuery(buf.toString());

		if ((dateFrom != null) && (dateTo != null))
		{
			query.setParameter("dateFrom", dateFrom);
			query.setParameter("dateTo", dateTo);
		}		
		if (salesOrderNo != null && salesOrderNo != "" && (!salesOrderNo.equalsIgnoreCase("0")) && (!salesOrderNo.equalsIgnoreCase(""))) 
		{
			query.setParameter("salesOrderNo", salesOrderNo);
		}	
		if (tableNo != null && tableNo != "" && (!tableNo.equalsIgnoreCase("0")) && (!tableNo.equalsIgnoreCase(""))) 
		{
			query.setParameter("tableNo", tableNo);
		}	
		if (cardNo != null && cardNo != "" && (!cardNo.equalsIgnoreCase("0")) && (!cardNo.equalsIgnoreCase(""))) 
		{
			query.setParameter("cardNo", cardNo);
		}	
		
		if (createdby != null && createdby != "" && (!createdby.equalsIgnoreCase("0")) && (!createdby.equalsIgnoreCase(""))) 
		{
			query.setParameter("createdBy", createdby);
		}	
		
		
		if (customerId !=null && customerId !=0) 
		{
			query.setParameter("customerId", customerId);
		}
		if (branchId !=null && branchId !=0) 
		{
			query.setParameter("branchId", branchId);
		}		
		
		if (branchRecordId !=null && branchRecordId !=0) {
			query.setParameter("branchRecordId", branchRecordId);
		}
		if (status != null && status != "" && (!status.equalsIgnoreCase("0")) && (!status.equalsIgnoreCase("")))  
		{
			query.setParameter("status", status);
		}			
		data = query.getResultList();
		
		for(Kitchensorder dat:data)
		{
			StringBuilder buf1 = new StringBuilder("select t from Kitchenorderbreakdown t  "
					+ "inner join fetch t.remarks r "
					+ "inner join fetch t.product p "
					+ "inner join fetch p.productcategory c "
					+ "inner join fetch t.kitchenorderremarksbreakdown kr "
					+ "where t.salesorder.salesOrderId="+dat.getSalesOrderId() );
			//inner join fetch t.kitchenorderremarksbreakdown kr 
			Query query1 = em.createQuery(buf1.toString());
			List<Kitchenorderbreakdown> salesorderbreakdowns = query1.getResultList();
			/*
			for(Kitchenorderbreakdown data2:salesorderbreakdowns) {
				StringBuilder buf1a = new StringBuilder("SELECT kr FROM Kitchenorderremarksbreakdown kr  INNER JOIN FETCH kr.remarks r WHERE kr.kitchenorderbreakdown.salesOrderBreakdownId="+data2.getSalesOrderBreakdownId() );
				Query query1a = em.createQuery(buf1a.toString());
				List<Kitchenorderremarksbreakdown> kitchenorderremarksbreakdown = query1a.getResultList();
				
				data2.setKitchenorderremarksbreakdown(kitchenorderremarksbreakdown);
			}
		*/
			int i=0;
			for(Kitchenorderbreakdown data2:salesorderbreakdowns) {
				StringBuilder buf2 = new StringBuilder("select t from Kitchenorderbreakdown t  "
						+ "inner join fetch t.product p "
						+ "inner join fetch p.productcategory c "
						+ "inner join fetch t.kitchenorderremarksbreakdown kr "
						+ "inner join fetch kr.remarks r "
						+ "where t.parentSetItem.salesOrderBreakdownId="+data2.getSalesOrderBreakdownId() );
				Query query2 = em.createQuery(buf2.toString());
				List<Kitchenorderbreakdown> salesorderchildbreakdowns = query2.getResultList();
				salesorderbreakdowns.get(i).setChildSetItem(salesorderchildbreakdowns);
				i++;
			}
		dat.setSalesorderbreakdowns(salesorderbreakdowns);
		dbdata.add(dat);
		}
		
		
		}	
		catch (Exception e) {
		log.info("Error in getKitchenorderReportList of SalesorderDAOImpl "
				+ e.toString());
		throw e;
	} 	
	return dbdata;
	}

	
	
	@Override
	public Kitchensorder getKitchensorderDetails(Integer salesOrderId) throws Exception {
		
		Kitchensorder retData = null;		
		String sqlQuery4 = "select t from Kitchensorder t inner join fetch t.branch1 sb inner join fetch t.branch2 b inner join fetch t.customer c inner join fetch t.salesorderbreakdowns sob inner join fetch sob.product p inner join fetch p.productcategory pc inner join fetch sob.remarks r  WHERE t.salesOrderId = :salesOrderId ";
	
		try {			
			retData = (Kitchensorder) em.createQuery(sqlQuery4).setParameter("salesOrderId", salesOrderId).getSingleResult();			

		} catch (Exception e) {
			log.info("Error in getKitchensorderDetails SalesorderDAOImpl " + e);
			throw e;
		}
		return retData;
	}

	
	
	
	@Override
	public boolean deleteKitchenorder(Kitchensorder salesorder)
			throws Exception {
		try {
			em.remove(salesorder);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in deleteKitchenorder SalesorderDAOImpl " + e);
			throw e;
		}
	}
	
	

	@Override
	public boolean deleteKitchenorderbreakdown(Kitchenorderbreakdown salesorder)
			throws Exception {
		try {
			em.remove(salesorder);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in deleteKitchenorderbreakdown SalesorderDAOImpl " + e);
			throw e;
		}
	}
	
	
	
	@Override
	public Kitchensorder updateKitchenorder(Kitchensorder salesorder)
			throws Exception {
		try {
			em.merge(salesorder);
			return salesorder;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in updateKitchenorder SalesorderDAOImpl " + e);
			throw e;
		}
	}
	
	
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Salesorderhold> getSalesorderholdList(Integer salesOrderId,Integer counterId, Integer branchRecordId) throws Exception {
		List<Salesorderhold> data = null;
		List<Salesorderhold> dataList = new ArrayList<Salesorderhold>();
		try {			
		StringBuilder buf = new StringBuilder(
				"select t from Salesorderhold t inner join fetch t.branch2 b inner join fetch t.customer c  inner join fetch t.poscounter pc  WHERE 1 = 1 ");

		
		if (salesOrderId !=null && salesOrderId !=0) {
			buf.append(" AND t.salesOrderId =  :salesOrderId  ");
		}			
		if (counterId !=null && counterId !=0) {
			buf.append(" AND pc.counterId =  :counterId  ");
		}			
		
		if (branchRecordId !=null && branchRecordId !=0) {
			buf.append(" AND t.branch1.branchId =  :branchRecordId  ");
		}	
				
		Query query = em.createQuery(buf.toString());
		
		if (salesOrderId !=null && salesOrderId !=0) 
		{
			query.setParameter("salesOrderId", salesOrderId);
		}
		if (counterId !=null && counterId !=0) 
		{
			query.setParameter("counterId", counterId);
		}		
		
		if (branchRecordId !=null && branchRecordId !=0) {
			query.setParameter("branchRecordId", branchRecordId);
		}		
		data = query.getResultList();
		
		
		for(Salesorderhold item:data)		
		{
			List<Salesorderbreakdownhold> listhold=getSalesorderbreakdownholdList(item.getSalesOrderId());
			item.setSalesorderbreakdowns(listhold);
			dataList.add(item);
		}
		}	
		catch (Exception e) {
		log.info("Error in getSalesorderholdList of SalesorderDAOImpl "
				+ e.toString());
		throw e;
	} 	
	return dataList;
	}

	
	
	
	@SuppressWarnings("unchecked")
	public List<Salesorderbreakdownhold> getSalesorderbreakdownholdList(Integer salesOrderId) throws Exception {
		List<Salesorderbreakdownhold> data = null;
		try {			
		StringBuilder buf = new StringBuilder(
				"select t from Salesorderbreakdownhold t inner join fetch t.product  WHERE 1 = 1 ");
		
		if (salesOrderId !=null && salesOrderId !=0) {
			buf.append(" AND t.salesorder.salesOrderId =  :salesOrderId  ");
		}		
		
		Query query = em.createQuery(buf.toString());
		
		if (salesOrderId !=null && salesOrderId !=0) 
		{
			query.setParameter("salesOrderId", salesOrderId);
		}
		
		data = query.getResultList();		
		}	
		catch (Exception e) {
		log.info("Error in getSalesorderbreakdownholdList of SalesorderDAOImpl "
				+ e.toString());
		throw e;
	} 	
	return data;
	}
	
	
	
	@Override
	public Salesorder getBranchSalesorderDetails(Integer salesOrderId , String salesType) throws Exception {
		
		Salesorder retData = null;		
		String sqlQuery4 = "select t from Salesorder t "
				+ "inner join fetch t.branch1 sb "
				+ "inner join fetch t.branch2 b "
				+ "inner join fetch t.customer c "
				+ "inner join fetch t.salesorderbreakdowns sob "
				+ "inner join fetch sob.product   "
				+ "WHERE t.salesOrderId = :salesOrderId "
				+ "AND sob.parentSetItem is null ";
		
	
		try {			
			retData = (Salesorder) em.createQuery(sqlQuery4).setParameter("salesOrderId", salesOrderId).getSingleResult();			

		} catch (Exception e) {
			log.info("Error in getBranchSalesorderDetails SalesorderDAOImpl " + e);
			throw e;
		}
		return retData;
	}

public List<Object[]> getSalesorderDetailsCategoryBased(Date dateFrom,Date dateTo,Integer categoryId) throws Exception {
		
		List<Object[]> retData = null;
		
		String sqlQuery3 = "select  p.productId,p.productName,s.unitPrice,s.taxAmount,sum(s.quantity),sum(s.subTotal),c.name,s "
				+ "from Salesorderbreakdown s inner join fetch s.product p inner join fetch p.productcategory c "
				+ " where s.createdDate between  :dateFrom and :dateTo "
				+ "and s.unitPrice!=0 "
				+"and c.categoryId = :categoryId "
				+ "group by p.productId" ;
		
	
		try {			
			Query query=em.createQuery(sqlQuery3);
			
			query.setParameter("dateFrom", dateFrom);
			query.setParameter("dateTo", dateTo);
			query.setParameter("categoryId", categoryId);
			retData= query.getResultList();
			
				

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in getSalesorderDetailsCategoryBased SalesorderDAOImpl " + e);
			throw e;
		}
		return retData;
	}
	
	@Override
	public Salesitem getBranchSalesitemDetails(Integer salesOrderId , String salesType) throws Exception {
		
		Salesitem retData = null;		
		String sqlQuery4 = "select t from Salesitem t "
				+ "inner join fetch t.branch1 sb "
				+ "inner join fetch t.branch2 b "
				+ "inner join fetch t.customer c "
				+ "inner join fetch t.salesorderbreakdowns sob "
				+ "inner join fetch sob.product   "
				+ "WHERE t.salesOrderId = :salesOrderId "
				+ "AND sob.parentSetItem is null ";
	
		try {			
			retData = (Salesitem) em.createQuery(sqlQuery4).setParameter("salesOrderId", salesOrderId).getSingleResult();			

		} catch (Exception e) {
			log.info("Error in getBranchSalesitemDetails SalesorderDAOImpl " + e);
			throw e;
		}
		return retData;
	}

	
	
	
	@Override
	public Salesorder getSalesorderDetails(Integer salesOrderId , String salesType)
			throws Exception {
		
		Salesorder retData = null;
		String sqlQuery1 = "select t from Salesorder t inner join fetch t.branch2 b inner join fetch t.customer c inner join fetch t.salesorderbreakdowns sob inner join fetch sob.product  inner join fetch sob.deliveryorderbreakdown  WHERE t.salesOrderId = :salesOrderId ";
		
		String sqlQuery2 = "select t from Salesorder t inner join fetch t.branch2 b inner join fetch t.customer c inner join fetch t.salesorderbreakdowns sob inner join fetch sob.product inner join fetch sob.quotationbreakdown   WHERE t.salesOrderId = :salesOrderId ";

		String sqlQuery3 = "select t from Salesorder t "
				+ "inner join fetch t.customer c "
				+ "inner join fetch t.salesorderbreakdowns sob  "
				+ "inner join fetch sob.product  "
				+ "WHERE t.salesOrderId = :salesOrderId "
				+ "AND sob.parentSetItem is null "
				+ "ORDER BY sob.status DESC ";

		String sqlQuery4 = "select t from Salesorder t inner join fetch t.branch2 b inner join fetch t.customer c inner join fetch t.salesorderbreakdowns sob inner join fetch sob.product   WHERE t.salesOrderId = :salesOrderId ";

		
		try {
			if(salesType.equalsIgnoreCase("1"))
			{
			retData = (Salesorder) em.createQuery(sqlQuery1).setParameter("salesOrderId", salesOrderId).getSingleResult();
			}
			if(salesType.equalsIgnoreCase("2"))
			{
			retData = (Salesorder) em.createQuery(sqlQuery2).setParameter("salesOrderId", salesOrderId).getSingleResult();
			}			
			if(salesType.equalsIgnoreCase("3"))
			{
			retData = (Salesorder) em.createQuery(sqlQuery3).setParameter("salesOrderId", salesOrderId).getSingleResult();
			}
			if(salesType.equalsIgnoreCase("4"))
			{
			retData = (Salesorder) em.createQuery(sqlQuery4).setParameter("salesOrderId", salesOrderId).getSingleResult();
			}
			
			int i=0;
			for(Salesorderbreakdown data2:retData.getSalesorderbreakdowns()) {
				StringBuilder buf2 = new StringBuilder("select t from Salesorderbreakdown t  "
						+ "inner join fetch t.product p "
						+ "inner join fetch p.productcategory c "
						+ "where t.parentSetItem.salesOrderBreakdownId="+data2.getSalesOrderBreakdownId() );
				Query query2 = em.createQuery(buf2.toString());
				List<Salesorderbreakdown> salesorderchildbreakdowns = query2.getResultList();
				retData.getSalesorderbreakdowns().get(i).setChildSetItem(salesorderchildbreakdowns);
				i++;
			}

		} catch (Exception e) {
			log.info("Error in getSalesorderDetails SalesorderDAOImpl " + e);
			throw e;
		}
		return retData;
	}

	@Override
	public Salesorder getSalesorderMasterDetails(Integer salesOrderId)
			throws Exception {
		Salesorder retData = null;
		String sqlQuery = "select t from Salesorder t inner join fetch t.branch2 b inner join fetch t.customer c  WHERE t.salesOrderId = :salesOrderId ";
		try {
			retData = (Salesorder) em.createQuery(sqlQuery).setParameter("salesOrderId", salesOrderId).getSingleResult();

		} catch (Exception e) {
			log.info("Error in getSalesorderMasterDetails SalesorderDAOImpl " + e);
			throw e;
		}
		return retData;
	}

	@Override
	public boolean createNewSalesorder(Salesorder salesorder) throws Exception {
		try {
			em.persist(salesorder);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in createNewSalesorder SalesorderDAOImpl " + e);
			throw e;
		}
	}
	
	
	@Override
	public boolean createNewSalesduplicateorder(Salesitem salesorder) throws Exception {
		try {
			em.persist(salesorder);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in createNewSalesduplicateorder SalesorderDAOImpl " + e);
			throw e;
		}
	}

	
	@Override
	public boolean createNewKitchensorder(Kitchensorder salesorder) throws Exception {
		try {
			em.persist(salesorder);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in createNewKitchensorder SalesorderDAOImpl " + e);
			throw e;
		}
	}
	
	
	
	@Override
	public boolean holdSalesorder(Salesorderhold salesorder) throws Exception {
		try {
			em.persist(salesorder);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in holdSalesorder SalesorderDAOImpl " + e);
			throw e;
		}
	}
	
	@Transactional
	@Override
	public boolean updateSalesorder(Salesorder salesorder) throws Exception {
		try {
			em.merge(salesorder);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in updateSalesorder SalesorderDAOImpl " + e);
			throw e;
		}
	}

	@Override
	public boolean deleteSalesorder(Salesorder salesorder) throws Exception {
		try {
			em.remove(salesorder);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in deleteSalesorder SalesorderDAOImpl " + e);
			throw e;
		}
	}
	
	
	@Override
	public boolean deleteSalesitem(Salesitem salesorder) throws Exception {
		try {
			em.remove(salesorder);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in deleteSalesitem SalesorderDAOImpl " + e);
			throw e;
		}
	}
	
	@Override
	public boolean deleteSalesitembreakdown(Salesitembreakdown salesitembreakdown) throws Exception {
		try {
			em.remove(salesitembreakdown);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in deleteSalesitem SalesorderDAOImpl " + e);
			throw e;
		}
		
	}
	
	
	@Transactional
	@Override
	public boolean deleteSalesordernative(String salesOrderNo ,Integer salesOrderId , Integer branchRecordId) throws Exception {
		try {
			
			String pospaymentsqlQuery = " delete from pospayment where salesOrderId="+salesOrderId;
			em.createNativeQuery(pospaymentsqlQuery).executeUpdate();
			
			String poscashtransactionsqlQuery = " delete from poscashtransaction where salesOrderNo='"+salesOrderNo+"'";
			em.createNativeQuery(poscashtransactionsqlQuery).executeUpdate();
			
			String branchinvoicebreakdownqlQuery = " delete from branchinvoicebreakdown where salesOrderNo='"+salesOrderNo+"'";
			em.createNativeQuery(branchinvoicebreakdownqlQuery).executeUpdate();
			
			String branchinvoiceqlQuery = " delete from branchinvoice where salesOrderNo='"+salesOrderNo+"'";
			em.createNativeQuery(branchinvoiceqlQuery).executeUpdate();
			
			
			List<Salesitem> salesitemlist =this.getSalesorderReportListowner(salesOrderNo, null, null, null, null, null, branchRecordId);
			Salesitem data=null;
			if(salesitemlist!=null && salesitemlist.size()!=0)
			{
				 data=salesitemlist.get(0);
				 String salesitembreakdownsqlQuery = " delete from salesitembreakdown where SalesOrderId="+data.getSalesOrderId();
				 em.createNativeQuery(salesitembreakdownsqlQuery).executeUpdate();
				 
				 String salesitemsqlQuery = " delete from salesitem where SalesOrderId="+data.getSalesOrderId();
				 em.createNativeQuery(salesitemsqlQuery).executeUpdate();
				 
			}
			
			 String salesitembreakdownsqlQuery = " delete from salesorderbreakdown where SalesOrderId="+salesOrderId;
			 em.createNativeQuery(salesitembreakdownsqlQuery).executeUpdate();
			 
			 String salesitemsqlQuery = " delete from salesorder where salesOrderNo='"+salesOrderNo+"'";
			 em.createNativeQuery(salesitemsqlQuery).executeUpdate();
			
			 
			
			
			
			
			
			
			
			
			//em.remove(salesorder);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in deleteSalesordernative SalesorderDAOImpl " + e);
			throw e;
		}
	}
	
	
	
	
	
	
	
	@Override
	public boolean deleteSalesorderhold(Salesorderhold salesorder) throws Exception {
		try {
			em.remove(getSalesorderhold(salesorder.getSalesOrderId()));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in deleteSalesorderhold SalesorderDAOImpl " + e);
			throw e;
		}
	}
	
	@Override
	public boolean deleteSalesorderbreakdownhold(Salesorderbreakdownhold salesorder) throws Exception {
		try {
			em.remove(getSalesorderbreakdownhold(salesorder.getSalesOrderBreakdownId()));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in deleteSalesorderbreakdownhold SalesorderDAOImpl " + e);
			throw e;
		}
	}
	
	
	public Salesorderhold getSalesorderhold(Integer salesOrderId) throws Exception {
		Salesorderhold retData = null;
		String sqlQuery = "select t from Salesorderhold t  WHERE t.salesOrderId = :salesOrderId ";
		try {
			retData = (Salesorderhold) em.createQuery(sqlQuery).setParameter("salesOrderId", salesOrderId).getSingleResult();

		} catch (Exception e) {
			log.info("Error in getSalesorderhold SalesorderDAOImpl " + e);
			throw e;
		}
		return retData;
	}
	
	
	public Salesorderbreakdownhold getSalesorderbreakdownhold(Integer salesOrderBreakdownId) throws Exception {
		Salesorderbreakdownhold retData = null;
		String sqlQuery = "select t from Salesorderbreakdownhold t  WHERE t.salesOrderBreakdownId = :salesOrderBreakdownId ";
		try {
			retData = (Salesorderbreakdownhold) em.createQuery(sqlQuery).setParameter("salesOrderBreakdownId", salesOrderBreakdownId).getSingleResult();

		} catch (Exception e) {
			log.info("Error in getSalesorderbreakdownhold SalesorderDAOImpl " + e);
			throw e;
		}
		return retData;
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Salesorderbreakdown> getSalesorderbreakdownReportList(
			Date dateFrom, Date dateTo, Integer branchRecordId, String barcode,
			String productName) throws Exception {
		List<Salesorderbreakdown> salesorder=null;		
		try {			
			StringBuilder buf = new StringBuilder("select t from Salesorderbreakdown t inner join fetch t.salesorder so inner join fetch t.product p WHERE 1 = 1");

			
			if (branchRecordId != null && branchRecordId!=0) {
				buf.append("  AND so.branch1.branchId=:branchRecordId ");
			}
			
			if (barcode !=null && (!barcode.equalsIgnoreCase(""))) {
				buf.append("  and p.barcode=:barcode ");
			}
			
			if (productName !=null && (!productName.equalsIgnoreCase(""))) {
				buf.append("  and p.productName=:productName ");
			}
			
			if ((dateFrom != null) && (dateFrom != null)) {
				buf.append("  AND t.createdDate between  :dateFrom and :dateTo   ");
			}	
			
			buf.append("  AND so.status!='1'  ");
			
			
			Query query = em.createQuery(buf.toString());
			
			if ((dateFrom != null) && (dateTo != null))
			{
				query.setParameter("dateFrom", dateFrom);
				query.setParameter("dateTo", dateTo);
			}	
			
			if (barcode !=null && (!barcode.equalsIgnoreCase(""))) {
				query.setParameter("barcode", barcode);
			}
			
			if (productName !=null && (!productName.equalsIgnoreCase(""))) {
				query.setParameter("productName", productName);
			}
			
			if (branchRecordId != null && branchRecordId!=0) {
				query.setParameter("branchRecordId", branchRecordId);
			}			
			salesorder = query.getResultList();
			
			} catch (Exception e) {
				log.info("Error in getSalesorderbreakdownReportList SalesorderDAOImpl " + e);
				throw e;
			}
			return salesorder;
	}

	

	@SuppressWarnings("unchecked")
	@Override
	public List<Salesorderbreakdown> getSalesorderbreakdownList(int[] ids,Integer breakdownId,Date dateFrom,
			Date dateTo, int start, int howMany,Integer branchRecordId) throws Exception {
			List<Salesorderbreakdown> salesorder=null;		
		try {			
			StringBuilder buf = new StringBuilder("select t from Salesorderbreakdown t inner join fetch t.salesorder so inner join fetch t.product WHERE 1 = 1");

			if (breakdownId != null && breakdownId!=0) {
				buf.append("  AND t.salesOrderBreakdownId=:salesOrderBreakdownId ");
			}	
			
			if (branchRecordId != null && branchRecordId!=0) {
				buf.append("  AND so.branch1.branchId=:branchRecordId ");
			}
			
			if (ids !=null && ids.length !=0) {
				buf.append("  and t.salesOrderBreakdownId in (:ids) ");
			}	
			
			if ((dateFrom != null) && (dateFrom != null)) {
				buf.append("  AND t.createdDate between  :dateFrom and :dateTo   ");
			}	
			
			buf.append("  AND so.status!='1'  ");
			
			
			Query query = em.createQuery(buf.toString());
			
			if ((dateFrom != null) && (dateTo != null))
			{
				query.setParameter("dateFrom", dateFrom);
				query.setParameter("dateTo", dateTo);
			}	
			if (breakdownId != null && breakdownId!=0) {
				query.setParameter("salesOrderBreakdownId", breakdownId);
			}
			
			if (ids !=null && ids.length !=0) {
				query.setParameter("ids", ConvertUtil.getIntegerList(ids));
			}
			
			if (branchRecordId != null && branchRecordId!=0) {
				query.setParameter("branchRecordId", branchRecordId);
			}
			query.setFirstResult(start);
			query.setMaxResults(howMany);
			salesorder = query.getResultList();
			
			} catch (Exception e) {
				log.info("Error in getSalesorderbreakdownList SalesorderDAOImpl " + e);
				throw e;
			}
			return salesorder;
	}

	@Override
	public int getSalesorderbreakdownCount(Date dateFrom, Date dateTo,Integer branchRecordId)
			throws Exception {
		int countResult = 0;
		int[] retList = null;
		try {			
			StringBuilder buf = new StringBuilder("SELECT count(p) FROM Salesorderbreakdown p  WHERE 1 = 1 ");
			
			if ((dateFrom != null) && (dateFrom != null)) {
				buf.append("  AND p.createdDate between  :dateFrom and :dateTo   ");
			}		
			if (branchRecordId != null && branchRecordId!=0) {
				buf.append("  AND p.salesorder.branch1.branchId=:branchRecordId ");
			}		
			
			buf.append("  AND p.salesorder.status!='1'  ");
			
			Query query = em.createQuery(buf.toString());
			if ((dateFrom != null) && (dateTo != null))
			{
				query.setParameter("dateFrom", dateFrom);
				query.setParameter("dateTo", dateTo);
			}	
			if (branchRecordId != null && branchRecordId!=0) {
				query.setParameter("branchRecordId", branchRecordId);
			}	
			Number cResults = (Number) query.getSingleResult();
			countResult = Integer.parseInt(String.valueOf(cResults));
			
			//List<Integer> retData = query.getResultList();
			//retList = Ints.toArray(retData);
			}		
			catch (Exception e) {
			log.info("Error in getSalesorderbreakdownCount of SalesorderDAOImpl "	+ e.toString());
			throw e;
		} 

		return countResult;
	}

	@Override
	public boolean deleteSalesorderbreakdown(
			Salesorderbreakdown salesorderbreakdown) throws Exception {
		try {
			em.remove(salesorderbreakdown);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in deleteSalesorderbreakdown SalesorderDAOImpl " + e);
			throw e;
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getBranchSalesByProductCount(Integer branchId,Date dateFrom, Date dateTo,Integer branchRecordId) throws Exception {
		int countResult = 0;
		List<Object[]> result=null;
		List<String> salesTypes= new ArrayList<String>(); // Branchs
		salesTypes.add("1");
		
		try {			
			StringBuilder buf = new StringBuilder("SELECT COUNT(p) , sum(p.quantity), sum(p.subTotal), sum(p.purchasePrice) from Salesorderbreakdown p   WHERE p.salesorder.salesType in :salesType and p.salesorder.status!= :status and  p.salesorder.branch2.branchId =:branchId and p.salesorder.branch1.branchId =:branchRecordId");
			
			if ((dateFrom != null) && (dateFrom != null)) {
				buf.append("  AND p.createdDate between  :dateFrom and :dateTo   ");
			}	
			
			//buf.append(" group by p.product.productId");
			
			Query query = em.createQuery(buf.toString());
			
			if ((dateFrom != null) && (dateTo != null))
			{
				query.setParameter("dateFrom", dateFrom);
				query.setParameter("dateTo", dateTo);
			}			
			query.setParameter("branchId", branchId);
			query.setParameter("branchRecordId", branchRecordId);			
			
			query.setParameter("salesType", salesTypes);
			query.setParameter("status", "1");
			
			result=query.getResultList();
			
			//Number cResults = (Number) query.getSingleResult();
			//countResult = Integer.parseInt(String.valueOf(cResults));
			}		
			catch (Exception e) {
			log.info("Error in getBranchSalesByProductCount of SalesorderDAOImpl "	+ e.toString());
			throw e;
		} 

		return result;
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getBranchSalesByProduct(Integer branchId,Date dateFrom, Date dateTo, int start, int howMany,Integer branchRecordId) throws Exception {
		
		List<Object[]> purchaserequest=null;
		List<String> salesTypes= new ArrayList<String>(); // Branchs
		salesTypes.add("1");
		
		
		StringBuilder buf = new StringBuilder("select t, sum(t.quantity), sum(t.subTotal), sum(t.purchasePrice) from Salesorderbreakdown t inner join fetch t.product p inner join fetch t.salesorder  pr  WHERE pr.salesType in :salesType and pr.status!= :status and  pr.branch2.branchId =:branchId and pr.branch1.branchId =:branchRecordId ");
		try {	
			
			if ((dateFrom != null) && (dateFrom != null)) {
				buf.append("  AND t.createdDate between  :dateFrom and :dateTo   ");
				}			
			
			buf.append(" group by p.productId");
			
			Query query = em.createQuery(buf.toString());
			
			if ((dateFrom != null) && (dateTo != null))
			{
				query.setParameter("dateFrom", dateFrom);
				query.setParameter("dateTo", dateTo);
			}		
			query.setParameter("branchId", branchId);
			query.setParameter("branchRecordId", branchRecordId);	
			query.setParameter("salesType", salesTypes);			
			query.setParameter("status", "1");
			
			query.setFirstResult(start);
			query.setMaxResults(howMany);
			purchaserequest=query.getResultList();
			
			} catch (Exception e) {
				log.info("Error in getBranchSalesByProduct SalesorderDAOImpl " + e);
				throw e;
			}
			return purchaserequest;
	}	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getBranchSalesByProductReport(Integer branchId,Date dateFrom, Date dateTo, Integer branchRecordId) throws Exception {
		
		List<Object[]> purchaserequest=null;
		List<String> salesTypes= new ArrayList<String>(); // Branchs
		salesTypes.add("1");
		
		
		StringBuilder buf = new StringBuilder("select t, sum(t.quantity), sum(t.subTotal), sum(t.purchasePrice) from Salesorderbreakdown t inner join fetch t.product p inner join fetch t.salesorder  pr  WHERE pr.salesType in :salesType and pr.status!= :status and  pr.branch2.branchId =:branchId and pr.branch1.branchId =:branchRecordId ");
		try {	
			
			if ((dateFrom != null) && (dateFrom != null)) {
				buf.append("  AND t.createdDate between  :dateFrom and :dateTo   ");
				}			
			
			buf.append(" group by p.productId");
			
			Query query = em.createQuery(buf.toString());
			
			if ((dateFrom != null) && (dateTo != null))
			{
				query.setParameter("dateFrom", dateFrom);
				query.setParameter("dateTo", dateTo);
			}		
			query.setParameter("branchId", branchId);
			query.setParameter("branchRecordId", branchRecordId);	
			query.setParameter("salesType", salesTypes);			
			query.setParameter("status", "1");			
			
			purchaserequest=query.getResultList();
			
			} catch (Exception e) {
				log.info("Error in getBranchSalesByProduct SalesorderDAOImpl " + e);
				throw e;
			}
			return purchaserequest;
	}	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getBranchSalesByBranch(List<Integer> branchIds ,Date dateFrom, Date dateTo,Integer branchRecordId) throws Exception {
		
		List<Object[]> purchaserequest=null;
		List<String> salesTypes= new ArrayList<String>(); // Branchs
		salesTypes.add("1");
		
		StringBuilder buf = new StringBuilder("select t, sum(sbd.quantity), sum(sbd.subTotal), sum(sbd.purchasePrice) from Salesorder t inner join fetch t.branch2  inner join fetch t.salesorderbreakdowns sbd   WHERE t.salesType in :salesType and t.status!= :status and t.branch2.branchId in :branchId and t.branch1.branchId =:branchRecordId ");
		try {					
			if ((dateFrom != null) && (dateFrom != null)) {
				buf.append("  AND sbd.createdDate between  :dateFrom and :dateTo   ");
				}				
			buf.append(" group by t.branch2.branchId");	
			
			Query query = em.createQuery(buf.toString());	
			
			if ((dateFrom != null) && (dateTo != null))
			{
				query.setParameter("dateFrom", dateFrom);
				query.setParameter("dateTo", dateTo);
			}				
			query.setParameter("branchId", branchIds);
			query.setParameter("branchRecordId", branchRecordId);	
			query.setParameter("salesType", salesTypes);	
			query.setParameter("status", "1");
			
			purchaserequest=query.getResultList();
			
			} catch (Exception e) {
				log.info("Error in getBranchSalesByBranch SalesorderDAOImpl " + e);
				throw e;
			}
			return purchaserequest;
	}
	
	
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getCustomerSalesByProductReport(Integer customerId,Date dateFrom, Date dateTo,Integer branchRecordId) throws Exception {
		
		List<Object[]> purchaserequest=null;
		List<String> salesTypes= new ArrayList<String>(); // Customers
		salesTypes.add("2");
		salesTypes.add("3");
		
		StringBuilder buf = new StringBuilder("select t, sum(t.quantity), sum(t.subTotal), sum(t.purchasePrice) from Salesorderbreakdown t inner join fetch t.product p inner join fetch t.salesorder  pr  WHERE pr.salesType in :salesType and  pr.status!= :status and pr.customer.customerId =:customerId and pr.branch1.branchId =:branchRecordId ");
		try {							
			
			if ((dateFrom != null) && (dateFrom != null)) {
				buf.append("  AND t.createdDate between  :dateFrom and :dateTo  ");
			}				
			buf.append(" group by p.productId");
			
			Query query = em.createQuery(buf.toString());
			
			if ((dateFrom != null) && (dateTo != null))
			{
				query.setParameter("dateFrom", dateFrom);
				query.setParameter("dateTo", dateTo);
			}			
			query.setParameter("customerId", customerId);
			query.setParameter("branchRecordId", branchRecordId);
			query.setParameter("salesType",salesTypes);	
			query.setParameter("status", "1");
			
			purchaserequest=query.getResultList();			
			} catch (Exception e) {
				log.info("Error in getCustomerSalesByProductReport SalesorderDAOImpl " + e);
				throw e;
			}
			return purchaserequest;
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getCustomerSalesByProduct(Integer customerId,Date dateFrom, Date dateTo, int start, int howMany,Integer branchRecordId) throws Exception {
		
		List<Object[]> purchaserequest=null;
		List<String> salesTypes= new ArrayList<String>(); // Customers
		salesTypes.add("2");
		salesTypes.add("3");
		
		StringBuilder buf = new StringBuilder("select t, sum(t.quantity), sum(t.subTotal), sum(t.purchasePrice) from Salesorderbreakdown t inner join fetch t.product p inner join fetch t.salesorder  pr  WHERE pr.salesType in :salesType and  pr.status!= :status and pr.customer.customerId =:customerId and pr.branch1.branchId =:branchRecordId ");
		try {							
			
			if ((dateFrom != null) && (dateFrom != null)) {
				buf.append("  AND t.createdDate between  :dateFrom and :dateTo  ");
			}				
			buf.append(" group by p.productId  ");
			
			Query query = em.createQuery(buf.toString());
			
			if ((dateFrom != null) && (dateTo != null))
			{
				query.setParameter("dateFrom", dateFrom);
				query.setParameter("dateTo", dateTo);
			}			
			query.setParameter("customerId", customerId);
			query.setParameter("branchRecordId", branchRecordId);
			query.setParameter("salesType",salesTypes);	
			query.setParameter("status", "1");
			
			query.setFirstResult(start);
			query.setMaxResults(howMany);
			purchaserequest=query.getResultList();			
			} catch (Exception e) {
				log.info("Error in getCustomerSalesByProduct SalesorderDAOImpl " + e);
				throw e;
			}
			return purchaserequest;
	}
	
	
	@Override
	public int getCustomerSalesByProductCount(Integer customerId,Date dateFrom, Date dateTo,Integer branchRecordId) throws Exception {
		int countResult = 0;
		
		List<String> salesTypes= new ArrayList<String>(); // Customers
		salesTypes.add("2");
		salesTypes.add("3");
		
		try {			
			StringBuilder buf = new StringBuilder("SELECT COUNT(p) from Salesorderbreakdown p   WHERE p.salesorder.salesType in :salesType and  p.salesorder.status!= :status and p.salesorder.customer.customerId =:customerId and p.salesorder.branch1.branchId =:branchRecordId ");
			
			if ((dateFrom != null) && (dateFrom != null)) {
				buf.append("  AND p.createdDate between  :dateFrom and :dateTo   ");
			}				
			//buf.append(" group by p.product.productId");
			
			Query query = em.createQuery(buf.toString());
			
			if ((dateFrom != null) && (dateTo != null))
			{
				query.setParameter("dateFrom", dateFrom);
				query.setParameter("dateTo", dateTo);
			}			
			query.setParameter("customerId", customerId);
			query.setParameter("branchRecordId", branchRecordId);
			query.setParameter("salesType", salesTypes);
			query.setParameter("status", "1");
			
			Number cResults = (Number) query.getSingleResult();
			countResult = Integer.parseInt(String.valueOf(cResults));
			}		
			catch (Exception e) {
			log.info("Error in getCustomerSalesByProductCount of SalesorderDAOImpl "	+ e.toString());
			throw e;
		} 

		return countResult;
	}

	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getCustomerSalesByBranchReport(List<Integer> customerIds ,Date dateFrom, Date dateTo,Integer branchRecordId) throws Exception {
		
		List<Object[]> purchaserequest=null;	
		List<String> salesTypes= new ArrayList<String>(); // Customers
		salesTypes.add("2");
		salesTypes.add("3");
		
		//String sqlQuery="select t, sum(sbd.quantity), sum(sbd.subTotal), sum(sbd.purchasePrice) from Salesorder t inner join fetch t.branch  inner join fetch t.salesorderbreakdowns sbd   WHERE t.salesType = :salesType and t.customer.customerId in :customerId group by t.customer.customerId";
		try {				
			//Query query = em.createQuery(sqlQuery).setParameter("customerId", customerIds).setParameter("salesType", "2");
			StringBuilder buf = new StringBuilder(
					"select t, sum(sbd.quantity), sum(sbd.subTotal), sum(sbd.purchasePrice) from Salesorder t inner join fetch t.customer  inner join fetch t.salesorderbreakdowns sbd   WHERE t.salesType in :salesType  and t.status!= :status  and t.branch1.branchId =:branchRecordId ");
			
			if ((dateFrom != null) && (dateFrom != null)) {
				buf.append("  AND t.createdDate between  :dateFrom and :dateTo   ");
			}	
			
			if (customerIds !=null && customerIds.size()!=0) {
				buf.append(" AND t.customer.customerId in :customerId  ");
			}					
			buf.append(" group by t.customer.customerId");
			
			Query query = em.createQuery(buf.toString());
			
			if ((dateFrom != null) && (dateTo != null))
			{
				query.setParameter("dateFrom", dateFrom);
				query.setParameter("dateTo", dateTo);
			}	
			
			query.setParameter("salesType", salesTypes);	
			query.setParameter("status", "1");
					
			if (customerIds !=null && customerIds.size() !=0) 
			{
				query.setParameter("customerId", customerIds);
			}
			
			query.setParameter("branchRecordId", branchRecordId);
			
			purchaserequest=query.getResultList();
			
			} catch (Exception e) {
				log.info("Error in getCustomerSalesByBranchReport SalesorderDAOImpl " + e);
				throw e;
			}
			return purchaserequest;
	}
	
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getCustomerSalesByBranch(List<Integer> customerIds ,Date dateFrom, Date dateTo, int start, int howMany,Integer branchRecordId) throws Exception {
		
		List<Object[]> purchaserequest=null;	
		List<String> salesTypes= new ArrayList<String>(); // Customers
		salesTypes.add("2");
		salesTypes.add("3");
		
		//String sqlQuery="select t, sum(sbd.quantity), sum(sbd.subTotal), sum(sbd.purchasePrice) from Salesorder t inner join fetch t.branch  inner join fetch t.salesorderbreakdowns sbd   WHERE t.salesType = :salesType and t.customer.customerId in :customerId group by t.customer.customerId";
		try {				
			//Query query = em.createQuery(sqlQuery).setParameter("customerId", customerIds).setParameter("salesType", "2");
			if(this.getSalesorderCount(null, null, null, dateFrom, dateTo, null, branchRecordId, null , null , null)!=0)
			{
			
			StringBuilder buf = new StringBuilder(
					"select t, sum(sbd.quantity), sum(sbd.subTotal), sum(sbd.purchasePrice) from Salesorder t inner join fetch t.customer  inner join fetch t.salesorderbreakdowns sbd   WHERE t.salesType in :salesType  and t.status!= :status  and t.branch1.branchId =:branchRecordId ");
			
			if ((dateFrom != null) && (dateFrom != null)) {
				buf.append("  AND t.createdDate between  :dateFrom and :dateTo   ");
			}	
			
			if (customerIds !=null && customerIds.size()!=0) {
				buf.append(" AND t.customer.customerId in :customerId  ");
			}					
			buf.append(" group by t.customer.customerId order by sum(sbd.subTotal) desc  ");
			
			Query query = em.createQuery(buf.toString());
			
			if ((dateFrom != null) && (dateTo != null))
			{
				query.setParameter("dateFrom", dateFrom);
				query.setParameter("dateTo", dateTo);
			}	
			
			query.setParameter("salesType", salesTypes);	
			query.setParameter("status", "1");
					
			if (customerIds !=null && customerIds.size() !=0) 
			{
				query.setParameter("customerId", customerIds);
			}
			
			query.setParameter("branchRecordId", branchRecordId);
			
			query.setFirstResult(start);
			query.setMaxResults(howMany);
			purchaserequest=query.getResultList();
			}
			
			} catch (Exception e) {
				log.info("Error in getCustomerSalesByBranch SalesorderDAOImpl " + e);
				throw e;
			}
			return purchaserequest;
	}
	
	
	
	
	@Override
	public int getCustomerSalesByBranchCount(List<Integer> customerIds ,Date dateFrom, Date dateTo,Integer branchRecordId) throws Exception {
		int countResult = 0;
		
		List<String> salesTypes= new ArrayList<String>(); // Customers
		salesTypes.add("2");
		salesTypes.add("3");
		
		try {			
			StringBuilder buf = new StringBuilder("SELECT COUNT(p) from Salesorder p  WHERE p.salesType in :salesType   and p.status!= :status  and p.branch1.branchId =:branchRecordId ");
			
			if ((dateFrom != null) && (dateFrom != null)) {
				buf.append("  AND p.createdDate between  :dateFrom and :dateTo   ");
			}			
			
			if (customerIds !=null && customerIds.size()!=0) {
				buf.append(" AND p.customer.customerId in :customerId  ");
			}					
			//buf.append(" group by p.customer.customerId");
			
			Query query = em.createQuery(buf.toString());
			
			if ((dateFrom != null) && (dateTo != null))
			{
				query.setParameter("dateFrom", dateFrom);
				query.setParameter("dateTo", dateTo);
			}	
			
			query.setParameter("salesType", salesTypes);	
			query.setParameter("status", "1");
					
			if (customerIds !=null && customerIds.size() !=0) 
			{
				query.setParameter("customerId", customerIds);
			}
			query.setParameter("branchRecordId", branchRecordId);
			
			Number cResults = (Number) query.getSingleResult();
			countResult = Integer.parseInt(String.valueOf(cResults));
			}		
			catch (Exception e) {
			log.info("Error in getCustomerSalesByBranchCount of SalesorderDAOImpl "	+ e.toString());
			throw e;
		} 

		return countResult;
	}
	
	@Override
	public boolean updateSalesorderbreakdown(
			Salesorderbreakdown salesorderbreakdown) throws Exception {
		try {
			em.merge(salesorderbreakdown);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in updateSalesorderbreakdown SalesorderDAOImpl " + e);
			throw e;
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getStaffSalesCommission(String status, Date dateFrom,
			Date dateTo) throws Exception {
		List<Object[]> commssionStaffList=null;
		
		StringBuilder buf = new StringBuilder("select s , sum(sb.salesCommission) from Salesorder s  inner join fetch s.salesorderbreakdowns sb  WHERE 1 = 1 ");
		try {					
			if ((dateFrom != null) && (dateFrom != null)) {
				buf.append("  AND s.createdDate between  :dateFrom and :dateTo   ");
				}
			
			if (status != null && status != "" && (!status.equalsIgnoreCase("0")) && (!status.equalsIgnoreCase(""))) 
			{
				buf.append(" AND  s.status =:status ");
			}
			
			buf.append(" group by s.salesRep");	
			
			Query query = em.createQuery(buf.toString());	
			
			if ((dateFrom != null) && (dateTo != null))
			{
				query.setParameter("dateFrom", dateFrom);
				query.setParameter("dateTo", dateTo);
			}				
			if (status != null && (!status.trim().equals("")) && (!status.equalsIgnoreCase("0")))  
			{
				query.setParameter("status", status);
			}				
			commssionStaffList=query.getResultList();			
			} catch (Exception e) {
				log.info("Error in getStaffSalesCommission SalesorderDAOImpl " + e);
				throw e;
			}
			return commssionStaffList;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getStaffSalesCommissionProduct(
			List<Integer> salesOrderId) throws Exception {
		List<Object[]> commssionStaffList=null;
		
		StringBuilder buf = new StringBuilder("select sb , sum(sb.salesCommission), sum(sb.quantity) from Salesorderbreakdown sb inner join fetch sb.product p inner join fetch sb.salesorder s  WHERE 1 = 1 ");
		try {					
			if (salesOrderId != null && salesOrderId.size() > 0) {
				buf.append(" AND  s.salesOrderId in :salesOrderId ");
			}			
			buf.append(" group by p.productId");	
			
			Query query = em.createQuery(buf.toString());	
			
			if (salesOrderId != null && salesOrderId.size() > 0) {
				query.setParameter("salesOrderId", salesOrderId);
			}						
			commssionStaffList=query.getResultList();				
			} catch (Exception e) {
				log.info("Error in getStaffSalesCommissionProduct SalesorderDAOImpl " + e);
				throw e;
			}
			return commssionStaffList;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Salesorder> getStaffSalesCommissionList(String salesOrderNo,
			Date dateFrom, Date dateTo, String status, String salesRep)
			throws Exception {
		List<Salesorder> data = null;
		try {			
		StringBuilder buf = new StringBuilder(
				"select t from Salesorder t  WHERE 1 = 1  ");

		if ((dateFrom != null) && (dateFrom != null)) {
			buf.append("  AND t.createdDate between  :dateFrom and :dateTo   ");
		}		
			
		if (salesOrderNo != null && salesOrderNo != "" && (!salesOrderNo.equalsIgnoreCase("0")) && (!salesOrderNo.equalsIgnoreCase(""))) 
		{
			buf.append(" AND  t.salesOrderNo =:salesOrderNo ");
		}		
		if (status != null && status != "" && (!status.equalsIgnoreCase("0")) && (!status.equalsIgnoreCase(""))) 
		{
			buf.append(" AND  t.status =:status ");
		}
		if (salesRep != null && salesRep != "" && (!salesRep.equalsIgnoreCase("0")) && (!salesRep.equalsIgnoreCase(""))) 
		{
			buf.append(" AND  t.salesRep =:salesRep ");
		}
		
		Query query = em.createQuery(buf.toString());

		if ((dateFrom != null) && (dateTo != null))
		{
			query.setParameter("dateFrom", dateFrom);
			query.setParameter("dateTo", dateTo);
		}		
		if (salesOrderNo != null && salesOrderNo != "" && (!salesOrderNo.equalsIgnoreCase("0")) && (!salesOrderNo.equalsIgnoreCase(""))) 
		{
			query.setParameter("salesOrderNo", salesOrderNo);
		}					
		if (status != null && status != "" && (!status.equalsIgnoreCase("0")) && (!status.equalsIgnoreCase("")))  
		{
			query.setParameter("status", status);
		}	
		if (salesRep != null && salesRep != "" && (!salesRep.equalsIgnoreCase("0")) && (!salesRep.equalsIgnoreCase("")))  
		{
			query.setParameter("salesRep", salesRep);
		}	
		
		
		
		data = query.getResultList();
		}	
		catch (Exception e) {
		log.info("Error in getStaffSalesCommissionList of SalesorderDAOImpl "
				+ e.toString());
		throw e;
	} 	
	return data;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getSalesByBranchProductReport(Date dateFrom, Date dateTo,Integer limit,Integer branchRecordId) throws Exception {
		
		List<Object[]> salesList=null;			
			try {			
				if(this.getSalesorderCount(null, null, null, dateFrom, dateTo, null, branchRecordId, null , null , null)!=0)
				{
			StringBuilder buf = new StringBuilder("select sbd,sum(sbd.quantity), sum(sbd.subTotal), sum(sbd.purchasePrice) from Salesorderbreakdown sbd inner join fetch sbd.product p inner join fetch sbd.salesorder s  WHERE 1 = 1 and s.branch1.branchId =:branchRecordId ");
			
			if ((dateFrom != null) && (dateFrom != null)) {
				buf.append("  AND s.createdDate between  :dateFrom and :dateTo   ");
			}								
			buf.append(" group by p.productId order by sum(sbd.quantity) desc ");			
			
			Query query = em.createQuery(buf.toString());
			
			if ((dateFrom != null) && (dateTo != null))
			{
				query.setParameter("dateFrom", dateFrom);
				query.setParameter("dateTo", dateTo);
			}						
			query.setParameter("branchRecordId", branchRecordId);				
			query.setFirstResult(0);
			query.setMaxResults(limit);			
			salesList=query.getResultList();	
				}
			} catch (Exception e) {
				log.info("Error in getSalesByBranchProductReport SalesorderDAOImpl " + e);
				throw e;
			}
			return salesList;
	}
	
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getSalesDashboard(Date dateFrom, Date dateTo,Integer branchRecordId , String usertype) throws Exception {
		
		List<Object[]> salesList=null;			
			try {				
				
				StringBuilder buf =null;
				
				if(this.getSalesorderCount(null, null, null, null, null, null, branchRecordId, null , null , null)!=0)
				{
					if(usertype.equalsIgnoreCase("0"))
					{
						buf = new StringBuilder("select sbd, sum(sbd.subTotal),MONTH(s.createdDate), sum(sbd.quantity) ,sum(sbd.taxAmount) from Salesorderbreakdown sbd inner join fetch sbd.product p inner join fetch sbd.salesorder s  WHERE 1 = 1 and s.branch1.branchId =:branchRecordId and s.status !='1' ");
					}
					if(usertype.equalsIgnoreCase("1"))
					{
						buf = new StringBuilder("select sbd, sum(sbd.subTotal),MONTH(s.createdDate), sum(sbd.quantity) ,sum(sbd.taxAmount) from Salesitembreakdown sbd inner join fetch sbd.product p inner join fetch sbd.salesorder s  WHERE 1 = 1 and s.branch2.branchId =:branchRecordId and s.status !='1' ");
					}
					
			if ((dateFrom != null) && (dateFrom != null)) {
				buf.append("  AND s.createdDate between  :dateFrom and :dateTo   ");
			}								
			buf.append(" group by MONTH(s.createdDate) order by s.createdDate asc ");			
			
			Query query = em.createQuery(buf.toString());
			
			if ((dateFrom != null) && (dateTo != null))
			{
				query.setParameter("dateFrom", dateFrom);
				query.setParameter("dateTo", dateTo);
			}						
			query.setParameter("branchRecordId", branchRecordId);			
					
			salesList=query.getResultList();
				}
				
			} catch (Exception e) {
				log.info("Error in getSalesDashboard SalesorderDAOImpl " + e);
				throw e; 
			}
			return salesList;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Pospayment> getPospaymentList(String salesOrderNo,Integer salesOrderId) throws Exception {
		List<Pospayment> salesorder=null;		
		try {			
			StringBuilder buf = new StringBuilder("select t from Pospayment t inner join fetch t.salesorder so  WHERE 1 = 1");

			if (salesOrderNo != null && salesOrderNo != "" && (!salesOrderNo.equalsIgnoreCase("0")) && (!salesOrderNo.equalsIgnoreCase("")))  
			{
				buf.append("  AND so.salesOrderNo=:salesOrderNo ");
			}	
			
			if (salesOrderId != null && salesOrderId!=0) {
				buf.append("  AND so.salesOrderId=:salesOrderId ");
			}						
			
			Query query = em.createQuery(buf.toString());		
	
			if (salesOrderId != null && salesOrderId!=0) {
				query.setParameter("salesOrderId", salesOrderId);
			}
			
			if (salesOrderNo != null && salesOrderNo != "" && (!salesOrderNo.equalsIgnoreCase("0")) && (!salesOrderNo.equalsIgnoreCase("")))  
			{
				query.setParameter("salesOrderNo", salesOrderNo);
			}			
			salesorder = query.getResultList();
			
			} catch (Exception e) {
				log.info("Error in getPospaymentList SalesorderDAOImpl " + e);
				throw e;
			}
			return salesorder;
	}
	
	
	@Override
	public boolean findKitchenCardExites(String cardNo)	throws Exception {
		boolean exits = true;
		try {
			String hslQuery = "from Kitchensorder p where p.cardNo = :cardNo and p.status=:status ";
			Query query = em.createQuery(hslQuery).setParameter("cardNo", cardNo).setParameter("status", "1");
			@SuppressWarnings("unchecked")
			List<Kitchensorder> KitchensorderCodeExits = query.getResultList();
			if (KitchensorderCodeExits.isEmpty()) {
				exits = false;
			}
		} catch (Exception e) {
			log.info("Error in findKitchenCardExites SalesorderDAOImpl " + e);
			throw e;
		}
		return exits;
	}
	@Override
	public boolean cardInUse(String cardNo) throws Exception {
		boolean inUse=false;
		try {
			String hsql="from Kitchensorder p where p.cardNo = :cardNo and p.status=:status ";
			Query query=em.createQuery(hsql);
			query.setParameter("cardNo", cardNo);
			query.setParameter("status", "1");
			@SuppressWarnings("unchecked")
			List<Kitchensorder> KitchensorderCodeExits = query.getResultList();
			if (!KitchensorderCodeExits.isEmpty()) {
				inUse = true;
			}
			
		}catch (Exception ex) {
			log.info("Error in cardInUse SalesorderDAOImpl " + ex);
			throw ex;
		}
		return inUse;
	}
	
	
	@Override
	public SalesorderbreakdownModel getTopCategorySalesByStaff(Integer top, Integer staffId, Date date) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Kitchensorder> getTopSalesStaff(Integer top, Date date, int branchId) throws Exception{
		List<Kitchensorder> data = null;
		try {			
			StringBuilder hsql= new StringBuilder(
					"SELECT ko FROM Kitchensorder ko INNER JOIN FETCH ko.branch1 b WHERE 1=1 ");
	
			if(date!=null) {
				hsql.append("AND DATE(ko.createdDate)=:createdDate ");
			}
			
			if (branchId !=0) {
				hsql.append("AND b.branchId=:branchId  ");
			}
			
			hsql.append("GROUP BY ko.createdBy "
					+ "ORDER BY ko.totalAmount DESC "
					);
			
			Query query = em.createQuery(hsql.toString());
			
			if(date!=null) {
				query.setParameter("createdDate", date);
	
			}
			if (branchId !=0) {
				query.setParameter("branchId", branchId);
			}
			if(top!=null) {
				query.setMaxResults(top);
			}
			data = query.getResultList();
		}	
		catch (Exception e) {
		e.printStackTrace();
		}
		return data;
	}
	
	@Override
	public List<Kitchensorder> getKitchenorderList2(Date dateFrom, Date dateTo, String status, int branchId2)
			throws Exception {
		List<Kitchensorder> data = null;
		List<Kitchensorder> dbdata = new ArrayList<Kitchensorder>();
		try {			
		StringBuilder buf = new StringBuilder(
				"select t from Kitchensorder t inner join fetch t.branch1 b WHERE 1 = 1 ");

		if ((dateFrom != null) && (dateFrom != null)) {
			buf.append("  AND t.createdDate between  :dateFrom and :dateTo   ");
		}		
		
		if ( branchId2 !=0) {
			buf.append(" AND b.branchId =  :branchRecordId  ");
		}	
		
		if (status != null && status != "" ) 
		{
			buf.append(" AND  t.status =:status ");
		}
		
		Query query = em.createQuery(buf.toString());

		if ((dateFrom != null) && (dateTo != null))
		{
			query.setParameter("dateFrom", dateFrom);
			query.setParameter("dateTo", dateTo);
		}			
		
		if (branchId2 !=0) {
			query.setParameter("branchRecordId", branchId2);
		}
		if (status != null && status != "" )  
		{
			query.setParameter("status", status);
		}			
		data = query.getResultList();
		
		for(Kitchensorder dat:data)
		{
		StringBuilder buf1 = new StringBuilder("select t from Kitchenorderbreakdown t  inner join fetch t.remarks r inner join fetch t.product p inner join fetch p.productcategory c where t.salesorder.salesOrderId="+dat.getSalesOrderId() );
		Query query1 = em.createQuery(buf1.toString());
		List<Kitchenorderbreakdown> salesorderbreakdowns = query1.getResultList();
		
		dat.setSalesorderbreakdowns(salesorderbreakdowns);		
		dbdata.add(dat);
		}
		
		
		}	
		catch (Exception e) {
		log.info("Error in getKitchenorderList2 of SalesorderDAOImpl "
				+ e.toString());
		throw e;
	} 	
	return dbdata;
	}

	@Override
	public List<Object[]> getSalesorderReportListCategoryDateBased(Date dateFrom,Date dateTo,int branchId) throws Exception {
		List<Object[]> retData = null;		// 
		String sqlQuery4 = "select  c.name,sum(s.subTotal),sum(s.taxAmount),sum(s.quantity),c.categoryId,s.createdDate,s "
				+ "from Salesorderbreakdown s inner join fetch s.product p inner join fetch p.productcategory c "
				+ " where s.createdDate between  :dateFrom and :dateTo  "
				+ "and s.unitPrice!=0 "
				+ "group by Date(s.createdDate),c.name" ;
		
		
		try {			
			//retData = (Salesorder) em.createQuery(sqlQuery4);
			
			Query query=em.createQuery(sqlQuery4);
			query.setParameter("dateFrom", dateFrom);
			query.setParameter("dateTo", dateTo);
			retData=query.getResultList();
			

		} catch (Exception e) {
			log.info("Error in Salesorderbreakdown SalesorderDAOImpl " + e);
			throw e;
		}
		return retData;
	}
	
	@Override
	public List<Pospayment> getPospaymentDetails(Date datesFrom, Date datesTo, String type,Integer branchId) throws Exception {
		List<Pospayment> salesorder=null;		
		try {			
			StringBuilder buf = new StringBuilder("select t from Pospayment t inner join fetch t.salesorder so inner join fetch t.branch b WHERE 1 = 1");

			if ((datesFrom != null) && (datesTo != null)) {
				buf.append("  AND so.createdDate between  :dateFrom and :dateTo   ");
			}	
			if (type!=null && !type.isEmpty() )  
			{
				buf.append("  AND t.amountType=:type ");
			}	
			if (branchId !=null && branchId !=0) {
				buf.append(" AND t.branch.branchId =  :branchId  ");
			}	
			buf.append("group by so.salesOrderId,t.amountType ");
			
			Query query = em.createQuery(buf.toString());		
			if ((datesFrom != null) && (datesTo != null)) {
				query.setParameter("dateFrom",datesFrom);
				query.setParameter("dateTo",datesTo);
			}	
			if (type!=null && !type.isEmpty())  
			{
				query.setParameter("type",type);
			}
			if (branchId !=null && branchId !=0) {
				query.setParameter("branchId",branchId);
			}	
						
			salesorder = query.getResultList();
			
			} catch (Exception ex) {
				log.info("Error in getPospaymentDetails SalesorderDAOImpl " + ex);
				throw ex;
			}
			return salesorder;
	}
	@Override
	public List<Salesorder> getDiscountReport(Date dateFrom, Date dateTo, int branchId) {
		List<Salesorder> data = null;
		try {			
		StringBuilder buf = new StringBuilder(
				"SELECT s FROM Salesorder s  WHERE 1 = 1 ");

		if ((dateFrom != null) && (dateFrom != null)) {
			buf.append("  AND s.createdDate BETWEEN :dateFrom AND :dateTo   ");
		}
				
		buf.append(" AND s.discountRate>0");
		Query query = em.createQuery(buf.toString());
		if ((dateFrom != null) && (dateFrom != null)) {
				query.setParameter("dateFrom", dateFrom);
				query.setParameter("dateTo",dateTo);
		}
	//	query.setParameter("branchId", branchId);
		data = query.getResultList();
		}	
		catch (Exception ex) {
		log.info("Error in getDiscountReport of SalesorderDAOImpl "+ex.toString());
		throw ex;
	} 	
	return data;
	}
	
	@Override
	public List<Object[]> getSalesorderReportListCategoryBased(Date dateFrom,Date dateTo) throws Exception {
		List<Object[]> retData = null;		// 
		String sqlQuery4 = "select  c.name,sum(s.subTotal),sum(s.taxAmount),sum(s.quantity),c.categoryId,s.createdDate,s "
				+ "from Salesorderbreakdown s inner join fetch s.product p inner join fetch p.productcategory c "
				+ " where s.createdDate between  :dateFrom and :dateTo "
				+ "and s.unitPrice!=0 "
				+ "group by c.name" ;
		
		
		try {			
			//retData = (Salesorder) em.createQuery(sqlQuery4);
			
			Query query=em.createQuery(sqlQuery4);
			query.setParameter("dateFrom", dateFrom);
			query.setParameter("dateTo", dateTo);
			retData=query.getResultList();
			
			

		} catch (Exception e) {
			log.info("Error in Salesorderbreakdown SalesorderDAOImpl " + e);
			throw e;
		}
		return retData;
	}
	@Override
	public Salesorder getCategoryReportWithItems(Date dateFrom, Date dateTo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean productIsSales(int productId) throws Exception {
		boolean isSales=false;
		try {
			StringBuilder hql=new StringBuilder("Select 1 FROM Salesorderbreakdown s WHERE s.product.productId=:productId ");
			Query query=em.createQuery(hql.toString());
			query.setParameter("productId", productId);
			
			if(query.getResultList().size()>0) {
				isSales=true;
			}

		}catch(Exception ex) {
			ex.printStackTrace();
			log.info("Error in productIsSales SalesorderDAOImpl " + ex);
		}
		return isSales;
	}
	@Override
	public boolean productIsKitchenOrder(int productId) throws Exception {
		boolean isKitchenOrder=false;
		try {
			StringBuilder hql=new StringBuilder("Select 1 FROM Kitchenorderbreakdown kb WHERE kb.product.productId=:productId ");
			Query query=em.createQuery(hql.toString());
			query.setParameter("productId", productId);
			
			if(query.getResultList().size()>0) {
				isKitchenOrder=true;
			}

		}catch(Exception ex) {
			ex.printStackTrace();
			log.info("Error in productIsKitchenOrder SalesorderDAOImpl " + ex);
		}
		return isKitchenOrder;
	}
	@Override
	public List<Object[]> getDailySales(Date dateFrom, Date dateTo, int branchId , String paymentType ,  String salesRep) throws Exception {
		List<Object[]> dataList =null;
		try {			
			StringBuilder buf = new StringBuilder(
					"SELECT "
					+ "s.createdDate, "
					+ "SUM(s.totalAmount), "
					+ "SUM(s.totalTax), "
					+ "SUM(s.totalBeforeDiscount), "
					+ "s FROM Salesorder s  WHERE 1 = 1 ");
	
			if ((dateFrom != null) && (dateFrom != null)) {
				buf.append("  AND s.createdDate BETWEEN :dateFrom AND :dateTo   ");
			}
			if ((paymentType != null) && (paymentType != "")) {
				buf.append("  AND s.paymentType=:paymentType ");
			}
			if ((salesRep != null) && (salesRep != "")) {
				buf.append("  AND s.salesRep=:salesRep ");
			}
			if(branchId>0) {
				buf.append("  AND s.branch1.branchId=:branchId ");
			}
			
			buf.append("GROUP BY DATE(s.createdDate) ORDER BY DATE(s.createdDate) ");
			Query query = em.createQuery(buf.toString());
			if ((dateFrom != null) && (dateFrom != null)) {
					query.setParameter("dateFrom", dateFrom);
					query.setParameter("dateTo",dateTo);
			}
			if(branchId>0) {
				query.setParameter("branchId", branchId);
			}
			
			if ((paymentType != null) && (paymentType != "")) {
				query.setParameter("paymentType", paymentType);
			}
			if ((salesRep != null) && (salesRep != "")) {
				query.setParameter("salesRep", salesRep);
			}
			
			dataList = query.getResultList();
		}	
		catch (Exception ex) {
			log.info("Error in getDiscountReport of SalesorderDAOImpl "+ex.toString());
			throw ex;
		} 	
		return dataList;
	}
	@Override
	public List<Quotation> getOnlineOrderList(String createdby, Integer customerId, Date dateFrom, Date dateTo,
			Date deliveryTimeFrom, Date deliveryTimeTo, String status, Integer branchId) throws Exception {
		List<Quotation> data = null;
		List<Quotation> dataList = new ArrayList<Quotation>();
		try {			
		StringBuilder buf = new StringBuilder(
				"select q from Quotation q inner join fetch q.branch b inner join fetch q.customer c  WHERE 1 = 1 ");
		
		if ((dateFrom != null) && (dateTo != null)) {
			buf.append("  AND q.createdDate between  :dateFrom and :dateTo   ");
		}	
		
		if ((deliveryTimeFrom != null) && (deliveryTimeTo != null)) {
			buf.append("  AND q.deliveryTime between  :deliveryTimeFrom and :deliveryTimeTo   ");
		}	
		
		if (customerId !=null && customerId !=0) {
			buf.append(" AND c.customerId =  :customerId  ");
		}	
		
		if (branchId !=null && branchId !=0) {
			buf.append(" AND b.branchId =  :branchId  ");
		}		
		
		if (createdby != null && createdby != "" && (!createdby.equalsIgnoreCase("0")) && (!createdby.equalsIgnoreCase(""))) 
		{
			buf.append(" AND  q.createdBy =:createdBy ");
		}	
		
		if (status != null && status != "" && (!status.equalsIgnoreCase(""))) 
		{
			buf.append(" AND  q.status =:status ");
		}
		
		
		Query query = em.createQuery(buf.toString());

		if ((dateFrom != null) && (dateTo != null))
		{
			query.setParameter("dateFrom", dateFrom);
			query.setParameter("dateTo", dateTo);
		}
		
		if ((deliveryTimeFrom != null) && (deliveryTimeTo != null)) {
			query.setParameter("deliveryTimeFrom", dateFrom);
			query.setParameter("deliveryTimeTo", dateTo);
		}

		if (customerId !=null && customerId !=0) 
		{
			query.setParameter("customerId", customerId);
		}
		
		if (branchId !=null && branchId !=0) 
		{
			query.setParameter("branchId", branchId);
		}
		
		if (createdby != null && createdby != "" && (!createdby.equalsIgnoreCase("0")) && (!createdby.equalsIgnoreCase(""))) 
		{
			query.setParameter("createdBy", createdby);
		}
		
		if (status != null && status != "" && (!status.equalsIgnoreCase("")))
		{
			query.setParameter("status", status);
		}
		
		data = query.getResultList();
		
		for(Quotation order:data)
		{
			StringBuilder buf1 = new StringBuilder("select qb from Quotationbreakdown qb inner join fetch qb.product p  inner join fetch p.productcategory pc where qb.quotation.quotationId="+order.getQuotationId() );
			//inner join fetch t.kitchenorderremarksbreakdown kr 
			Query query1 = em.createQuery(buf1.toString());
			List<Quotationbreakdown> quotationbreakdown = query1.getResultList();
		
			order.setQuotationbreakdowns(quotationbreakdown);		
			dataList.add(order);
		}
		
		
		}	
		catch (Exception e) {
		log.info("Error in getOnlineOrderList of SalesorderDAOImpl "
				+ e.toString());
		throw e;
	} 	
	return dataList;
	}
	@Override
	public Quotation getOnlineOrderDetails(Integer quotationId, String quotationNo) {
		// TODO Auto-generated method stub
		Quotation data = null;

		try {
			StringBuilder buf = new StringBuilder(
					"select q from Quotation q inner join fetch q.branch b inner join fetch q.customer c  WHERE 1 = 1 ");
			
			if (quotationId !=null && quotationId !=0) {
				buf.append(" AND q.quotationId =  :quotationId  ");
			}	
			if (quotationNo != null && quotationNo != "" && (!quotationNo.equalsIgnoreCase("0")) && (!quotationNo.equalsIgnoreCase(""))) 
			{
				buf.append(" AND  q.quotationNo =:quotationNo ");
			}
			
			Query query = em.createQuery(buf.toString());
			
			if (quotationId !=null && quotationId !=0) 
			{
				query.setParameter("quotationId", quotationId);
			}
			
			
			if (quotationNo != null && quotationNo != "" )  
			{
				query.setParameter("quotationNo", quotationNo);
			}
		
			data = (Quotation) query.getSingleResult();
			if(data!=null) {
				StringBuilder buf1 = new StringBuilder("select qb from Quotationbreakdown qb inner join fetch qb.product p  inner join fetch p.productcategory pc where qb.quotation.quotationId="+data.getQuotationId() );
				//inner join fetch t.kitchenorderremarksbreakdown kr 
				Query query1 = em.createQuery(buf1.toString());
				List<Quotationbreakdown> quotationbreakdown = query1.getResultList();
			
				data.setQuotationbreakdowns(quotationbreakdown);
			}
			
			
		}catch (Exception e) {
			log.info("Error in getOnlineOrderDetails of SalesorderDAOImpl "
					+ e.toString());
			throw e;
		}
		return data;
	}
	@Override
	public Quotation updateOnlineOrder(Quotation orderObj) throws Exception {
		try {
			em.merge(orderObj);
			return orderObj;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in updateOnlineOrder SalesorderDAOImpl " + e);
			throw e;
		}
	}
	@Override
	public boolean updateSalesitem(Salesitem salesItem) throws Exception {
		try {
			em.merge(salesItem);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error in updateSalesitem SalesorderDAOImpl " + e);
			throw e;
		}
	}
	
	
	
	
}
