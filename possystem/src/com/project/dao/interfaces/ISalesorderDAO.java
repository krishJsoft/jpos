package com.project.dao.interfaces;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.project.model.his.Kitchenorderbreakdown;
import com.project.model.his.Kitchensorder;
import com.project.model.his.Pospayment;
import com.project.model.his.Quotation;
import com.project.model.his.Salesitem;
import com.project.model.his.Salesitembreakdown;
import com.project.model.his.Salesorder;
import com.project.model.his.Salesorderbreakdown;
import com.project.model.his.Salesorderbreakdownhold;
import com.project.model.his.Salesorderhold;
import com.project.model.sale.sales.SalesorderModel;
import com.project.model.sale.sales.SalesorderbreakdownModel;


public interface ISalesorderDAO {
	
	public List<Salesorder> findBySalesorderListAll() throws Exception;

	public int getSalesorderCount(String salesOrderNo,Integer customerId,Integer branchId, Date dateFrom,Date dateTo, String status,Integer branchRecordId,String salesType,String paymentType, String salesRep) throws Exception;

	public List<Salesorder> getSalesorderList(int[] ids,String salesOrderNo,Integer customerId,Integer branchId, Date dateFrom,Date dateTo, String status, int start, int howMany,Integer branchRecordId,String salesType,String paymentType, String salesRep)
			throws Exception;		
	
	public List<Object[]> getSalesorderReportListCategoryBased(Date dateFrom,Date dateTo) throws Exception;
	
	public List<Salesorder> getSalesorderReportList(String salesOrderNo,Integer customerId, Integer branchId, Date dateFrom, Date dateTo,
			String status, Integer branchRecordId, String paymentType , String salesRep) throws Exception;
	
	public List<Salesorderhold> getSalesorderholdList(Integer salesOrderId,Integer counterId, Integer branchRecordId) throws Exception;
	
	public List<Object[]> getDailySalesListChart(Date dateFrom,Date dateTo) throws Exception;
	
	public List<Object[]>  getDailyStaffSalesChart(Date dateFrom,Date dateTo,Integer staffId) throws Exception;
	
	public List<Object[]> getDailyTopSalesItemChart(Date dateFrom,Date dateTo,int rank) throws Exception;
	
	public List<Object[]> getMonthlySalesListChart(int year,int branchId) throws Exception;
	
	public List<Object[]> getQuartelySalesListChart(int year,int branchId) throws Exception;
	
	public List<Object[]> getSalesorderDetailsCategoryBased(Date dataFrom, Date dateTo,Integer salesOrderId ) throws Exception;
	
	public Salesorder getSalesorderDetails(Integer salesOrderId , String salesType) throws Exception;

	public Salesorder getBranchSalesorderDetails(Integer salesOrderId , String salesType) throws Exception;
	
	public Salesorder getSalesorderMasterDetails(Integer salesOrderId)	throws Exception;
			
	public boolean createNewSalesorder(Salesorder salesorder) throws Exception;
	
	public boolean createNewSalesduplicateorder(Salesitem salesorder) throws Exception;
	
	public boolean deleteSalesitem(Salesitem salesorder) throws Exception;
	
	public boolean deleteSalesitembreakdown(Salesitembreakdown salesitembreakdown) throws Exception;

	public boolean holdSalesorder(Salesorderhold salesorder)	throws Exception;
	
	public boolean updateSalesorder(Salesorder salesorder) throws Exception;
	
	public boolean deleteSalesorder(Salesorder salesorder) throws Exception;
	
	public boolean deleteSalesorderhold(Salesorderhold salesorder) throws Exception ;
	
	public boolean deleteSalesorderbreakdownhold(Salesorderbreakdownhold salesorder) throws Exception;
	
	public List<Salesorderbreakdown> getSalesorderbreakdownList(int[] ids,Integer breakdownId ,Date dateFrom,Date dateTo,int start, int howMany,Integer branchRecordId)	throws Exception;
	
	public List<Salesorderbreakdown> getSalesorderbreakdownReportList(Date dateFrom, Date dateTo, Integer branchRecordId,String barcode , String productName)
			throws Exception;
			
	public int getSalesorderbreakdownCount(Date dateFrom,Date dateTo,Integer branchRecordId) throws Exception;

	public boolean deleteSalesorderbreakdown(Salesorderbreakdown salesorderbreakdown) throws Exception;	
	
	public boolean updateSalesorderbreakdown(Salesorderbreakdown salesorderbreakdown) throws Exception;
	
	
	
	
	public List<Object[]> getBranchSalesByProduct(Integer branchId,Date dateFrom, Date dateTo, int start, int howMany,Integer branchRecordId) throws Exception;
	
	public List<Object[]> getBranchSalesByProductReport(Integer branchId,Date dateFrom, Date dateTo, Integer branchRecordId) throws Exception;
	
	public List<Object[]> getBranchSalesByProductCount(Integer branchId,Date dateFrom, Date dateTo,Integer branchRecordId) throws Exception;
	
	public List<Object[]> getBranchSalesByBranch(List<Integer> branchIds ,Date dateFrom, Date dateTo,Integer branchRecordId) throws Exception;	
	
	public int getCustomerSalesByProductCount(Integer customerId,Date dateFrom, Date dateTo,Integer branchRecordId) throws Exception;	
	
	public List<Object[]> getCustomerSalesByProduct(Integer customerId,Date dateFrom, Date dateTo, int start, int howMany,Integer branchRecordId) throws Exception;
	
	public List<Object[]> getCustomerSalesByProductReport(Integer customerId,Date dateFrom, Date dateTo,Integer branchRecordId) throws Exception;

	public List<Object[]> getCustomerSalesByBranch(List<Integer> customerIds ,Date dateFrom, Date dateTo, int start, int howMany,Integer branchRecordId) throws Exception;
	
	public List<Object[]> getCustomerSalesByBranchReport(List<Integer> customerIds ,Date dateFrom, Date dateTo,Integer branchRecordId) throws Exception;
	
	public int getCustomerSalesByBranchCount(List<Integer> customerIds ,Date dateFrom, Date dateTo,Integer branchRecordId) throws Exception;
	
	
	
	public List<Object[]> getStaffSalesCommission(String status, Date dateFrom,	Date dateTo) throws Exception;
	
	public List<Object[]> getStaffSalesCommissionProduct(List<Integer> salesOrderId) throws Exception;
	
	public List<Salesorder> getStaffSalesCommissionList(String salesOrderNo,Date dateFrom,Date dateTo, String status, String salesRep)	throws Exception;		

	public List<Object[]> getSalesByBranchProductReport(Date dateFrom, Date dateTo,Integer limit,Integer branchRecordId) throws Exception;
	
	public List<Object[]> getSalesDashboard(Date dateFrom, Date dateTo,Integer branchRecordId , String usertype) throws Exception;
	
	public Salesitem getBranchSalesitemDetails(Integer salesOrderId , String salesType) throws Exception;
	
	public List<Pospayment> getPospaymentList(String salesOrderNo ,Integer salesOrderId)throws Exception;
	
	public boolean createNewKitchensorder(Kitchensorder salesorder) throws Exception;
	
	public List<Kitchensorder> getKitchenorderReportList(String tableNo , String cardNo , String salesOrderNo,String createdby,Integer customerId, Integer branchId, Date dateFrom, Date dateTo,
			String status, Integer branchRecordId) throws Exception;
	
	public boolean deleteKitchenorder(Kitchensorder salesorder) throws Exception ;
	
	public boolean deleteKitchenorderbreakdown(Kitchenorderbreakdown salesorder) throws Exception;	
	
	public Kitchensorder updateKitchenorder(Kitchensorder salesorder) throws Exception ;
	
	public Kitchensorder getKitchensorderDetails(Integer salesOrderId) throws Exception;
	
	public boolean findKitchenCardExites(String cardNo)	throws Exception;
	
	public List<Salesitem> getSalesorderReportListowner(String salesOrderNo,Integer customerId, Integer branchId, Date dateFrom, Date dateTo,
			String status, Integer branchRecordId) throws Exception;
	
	public boolean deleteSalesordernative(String salesOrderNo ,Integer salesOrderId , Integer branchRecordId) throws Exception;

	public boolean cardInUse(String cardNo) throws Exception;

	public SalesorderbreakdownModel getTopCategorySalesByStaff(Integer top, Integer staffId, Date date);

	public List<Kitchensorder> getTopSalesStaff(Integer top, Date date, int branchId) throws Exception;


	public List<Kitchensorder> getKitchenorderList2(Date dateFrom, Date dateTo, String status, int branchId2) throws Exception;

	List<Object[]> getSalesorderReportListCategoryDateBased(Date dateFrom, Date dateTo, int branchId) throws Exception;

	public List<Pospayment> getPospaymentDetails(Date datesFrom, Date datesTo, String type,Integer branchId) throws Exception;

	public List<Salesorder> getDiscountReport(Date dateFrom, Date dateTo,  int branchId);

	public Salesorder getCategoryReportWithItems(Date dateFrom, Date dateTo) throws Exception;

	public boolean productIsSales(int productId) throws Exception;

	public boolean productIsKitchenOrder(int productId) throws Exception;

	public List<Object[]> getDailySales(Date dateFrom, Date dateTo, int branchId, String paymentType ,  String salesRepId) throws Exception;

	public List<Quotation> getOnlineOrderList(String createdby, Integer customerId, Date dateFrom, Date dateTo,
			Date deliveryTimeFrom, Date deliveryTimeTo, String status, Integer branchId) throws Exception;

	public Quotation getOnlineOrderDetails(Integer quotationId, String quotationNo) throws Exception;

	public Quotation updateOnlineOrder(Quotation orderObj) throws Exception;

	public boolean updateSalesitem(Salesitem salesItem) throws Exception;




	
}
