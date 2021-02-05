package com.project.bo.interfaces;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.project.model.his.Salesorder;
import com.project.model.his.Salesorderbreakdown;
import com.project.model.his.Salesorderbreakdownhold;
import com.project.model.his.Salesorderhold;
import com.project.model.datamodel.SalesPurchaseDashboradModel;
import com.project.model.datamodel.stock.DeliveryorderModel;
import com.project.model.sale.sales.BranchSalesModel;
import com.project.model.sale.sales.BranchSalesSummaryModel;
import com.project.model.sale.sales.PaymentCollectionModel;
import com.project.model.sale.sales.QuotationModel;
import com.project.model.sale.sales.SalesorderModel;
import com.project.model.sale.sales.SalesorderbreakdownModel;


public interface ISalesorderBO {
	
	public List<SalesorderModel> findBySalesorderListAll() throws Exception;

	public int getSalesorderCount(String salesOrderNo,Integer customerId,Integer branchId, Date dateFrom,Date dateTo, String status,Integer branchRecordId,String salesType,String paymentType, String salesRep) throws Exception;
	
	public List<SalesorderModel> getDailySalesListChart(Date dateFrom,Date dateTo) throws Exception;
	
	public List<SalesorderbreakdownModel> getDailyTopSalesItemChart(Date dateFrom,Date dateTo, int rank) throws Exception;
	
	public List<SalesorderModel> getDailyStaffSalesChart(Date dateFrom,Date dateTo,Integer staffId) throws Exception;
	
	public List<SalesorderModel> getMonthlySalesListChart(int year, int branchId) throws Exception;
	
	public List<SalesorderModel> getQuartelySalesListChart(int year,int branchId) throws Exception;
	
	public List<SalesorderModel> getSalesorderList(int[] ids,String salesOrderNo,Integer customerId,Integer branchId, Date dateFrom,Date dateTo, String status, int start, int howMany,Integer branchRecordId,String salesType,String paymentType, String salesRep)
			throws Exception;
	
	public List<SalesorderbreakdownModel> getOrderRerportListCategoryBased(Date dateFrom, Date dateTo)	throws Exception;
	
	public List<SalesorderModel> getSalesorderReportList(String salesOrderNo,	Integer customerId, Integer branchId, Date dateFrom, Date dateTo,
			String status,Integer branchRecordId, String paymentType , String salesRep) throws Exception;
			
	public List<SalesorderModel> getSalesorderholdList(Integer salesOrderId, Integer counterId,Integer branchRecordId) throws Exception;
	
	public SalesorderModel getSalesorderDetails(Integer salesOrderId, String salesType) throws Exception;
	
	public SalesorderModel getSalesorderPosDetails(Integer salesOrderId, String salesType) throws Exception;
	
	public SalesorderModel getBranchSalesorderDetails(Integer salesOrderId , String salesType)	throws Exception;
	
	public SalesorderModel getSalesorderDetailsCategoryBased(Date dataFrom, Date dateTo,Integer salesOrderId) throws Exception;
	
	public SalesorderModel getBranchSalesItemDetails(Integer salesOrderId , String salesType)	throws Exception;
	
	public SalesorderModel getSalesorderMasterDetails(Integer salesOrderId)	throws Exception;
			
	public SalesorderModel createNewSalesorder(SalesorderModel salesorder,List<Integer> salesId) throws Exception;
	
	public SalesorderModel holdSalesorder(SalesorderModel salesorder)	throws Exception;
	
	public boolean createNewSalesorder( DeliveryorderModel deliveryorder , QuotationModel quotationModel,SalesorderModel salesorderModel)
			throws Exception;
	
	public boolean updateSalesorder(SalesorderModel salesorder) throws Exception;
	
	public boolean approveSalesorder(SalesorderModel salesorder,DeliveryorderModel deliveryorder, QuotationModel quotationModel,Integer branchId) throws Exception;
	
	public boolean deleteSalesorder(SalesorderModel salesorder) throws Exception;		
	
	public boolean deleteSalesorderhold(SalesorderModel salesorder) throws Exception ;
	
	public boolean deleteSalesorderbreakdownhold(SalesorderbreakdownModel salesorder) throws Exception;	
	
	public List<SalesorderbreakdownModel> getSalesorderbreakdownList(int[] ids,Integer breakdownId , Date dateFrom,Date dateTo,int start, int howMany,Integer branchRecordId)	throws Exception;
	
	public List<SalesorderbreakdownModel> getSalesorderbreakdownReportList(Date dateFrom, Date dateTo, Integer branchRecordId,String barcode , String productName)
			throws Exception;
	
	
	public int getSalesorderbreakdownCount(Date dateFrom,Date dateTo,Integer branchRecordId) throws Exception;
	
	

	public boolean deleteSalesorderbreakdown(SalesorderbreakdownModel salesorderbreakdown) throws Exception;
	
	public boolean updateSalesorderbreakdown(SalesorderbreakdownModel salesorderbreakdown) throws Exception;	
	
	
	
	public List<BranchSalesModel> getBranchSalesByProduct(Integer branchId,Date dateFrom, Date dateTo, int start, int howMany,Integer branchRecordId) throws Exception;

	public List<BranchSalesSummaryModel> getBranchSalesByBranch(List<Integer> branchIds ,Date dateFrom, Date dateTo,Integer branchRecordId) throws Exception;
	
	public List<BranchSalesSummaryModel> getBranchSalesByBranchReport(String branchIdss, Date dateFrom, Date dateTo,Integer branchRecordId)
			throws Exception;
	
	public List<BranchSalesModel> getBranchSalesByProductReport(Integer branchId,Date dateFrom, Date dateTo,Integer branchRecordId) throws Exception;

			
	public List<BranchSalesModel> getCustomerSalesByProductReport(Integer customerId,Date dateFrom, Date dateTo,Integer branchRecordId) throws Exception;

	public List<BranchSalesModel> getCustomerSalesByProduct(Integer customerId,Date dateFrom, Date dateTo, int start, int howMany,Integer branchRecordId ) throws Exception;
	
	
	
	public List<BranchSalesSummaryModel> getCustomerSalesByBranchReport(String customerIds ,Date dateFrom, Date dateTo,Integer branchRecordId) throws Exception;

	public List<BranchSalesSummaryModel> getCustomerSalesByBranch(List<Integer> customerIds ,Date dateFrom, Date dateTo, int start, int howMany,Integer branchRecordId) throws Exception;
	
	
	
	public BranchSalesSummaryModel getBranchSalesByProductCount(Integer branchId,Date dateFrom, Date dateTo,Integer branchRecordId) throws Exception;
			
	public int getCustomerSalesByProductCount(Integer customerId,Date dateFrom, Date dateTo,Integer branchRecordId) throws Exception;	

	public int getCustomerSalesByBranchCount(List<Integer> customerIds ,Date dateFrom, Date dateTo,Integer branchRecordId) throws Exception;
	
	public List<SalesorderModel> getStaffSalesCommission(String status, Date dateFrom,Date dateTo,Integer staffId) throws Exception;
	
	public List<SalesorderbreakdownModel> getSalesByBranchProductReport(Date dateFrom, Date dateTo,Integer limit,Integer branchRecordId) throws Exception;
	
	public List<SalesPurchaseDashboradModel> getSalesDashboard(Date dateFrom, Date dateTo,Integer branchRecordId , String usertype) throws Exception;
	
	public SalesorderModel createNewKitchensorder(SalesorderModel salesorder)	throws Exception;
	
	public List<SalesorderModel> getKitchenorderReportList(String tableNo,String cardNo,String salesOrderNo,	String createdby,	Integer customerId, Integer branchId, Date dateFrom, Date dateTo,String status,Integer branchRecordId) throws Exception;
	
	public boolean deleteKitchenorder(SalesorderModel salesorder) throws Exception ;
	
	public boolean deleteKitchenorderbreakdown(SalesorderbreakdownModel salesorder) throws Exception;	
	
	public SalesorderModel updateKitchenorder(SalesorderModel salesorder) throws Exception ;
	
	public boolean findKitchenCardExites(String cardNo)	throws Exception;
	
	public List<SalesorderModel> getSalesorderReportListowner(String salesOrderNo,
			Integer customerId, Integer branchId, Date dateFrom, Date dateTo,
			String status,Integer branchRecordId) throws Exception;
	
	public boolean deleteSalesordernative(String salesOrderNo ,Integer salesOrderId , Integer branchRecordId) throws Exception;

	public boolean cardInUse(String cardNo) throws Exception;

	public List<SalesorderbreakdownModel> getTopCategorySalesByStaff(Integer top, Integer staffId, Date date);

	public List<SalesorderModel> getTopSalesStaff(Integer top, Date date, int branchId) throws Exception;

	public List<SalesorderModel> getKitchenorderList2( Date dateFrom, Date dateTo, String status,  int branchId2) throws Exception;

	List<SalesorderbreakdownModel> getOrderRerportListCategoryDateBased(Date dateFrom, Date dateTo, int branchId) throws Exception;

	public List<PaymentCollectionModel> getPosPaymentDetails(Date datesFrom, Date datesTo, String type,
			Integer branchId) throws Exception;

	SalesorderModel updateSplitOrder(SalesorderModel salesorder) throws Exception;

	public List<SalesorderModel> getDiscountReport(Date dateFrom, Date dateTo, int branchId) throws Exception;

	public List<SalesorderModel> getCategoryReportWithItems(Date dateFrom, Date dateTo) throws Exception;

	public boolean productISSales(int productId) throws Exception;

	public boolean productIsKitchenOrder(int parseInt) throws Exception;

	public List<SalesorderModel> getDailySales(Date dateFrom, Date dateTo, int branchId ,  String paymentType ,  String salesRep) throws Exception;

	public SalesorderModel getKitchenorderDetails(int kitchenOrderId) throws Exception;

	SalesorderModel updateKitchenorderSplit(SalesorderModel salesorder) throws Exception;
	
	public List<QuotationModel> getOnlineOrderList(String createdby,Integer customerId,Date dateFrom, Date dateTo,Date deliveryTimeFrom,Date deliveryTimeTo,String status,Integer branchId) throws Exception;

	public QuotationModel updateOnlineOrder(QuotationModel orderDetail) throws Exception;

	public QuotationModel createNewOnlineSalesOrder(QuotationModel orderDetail) throws Exception;
	
	
	
}
