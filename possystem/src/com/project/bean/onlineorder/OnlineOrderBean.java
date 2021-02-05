package com.project.bean.onlineorder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import com.project.bean.sales.sale.SalesorderBean;
import com.project.bo.interfaces.ISalesorderBO;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.util.ObjectMapController;
import com.project.login.LoginBean;
import com.project.modal.report.OnlineorderreportModal;
import com.project.model.sale.sales.QuotationModel;
import com.project.model.sale.sales.QuotationbreakdownModel;
import com.project.model.sale.sales.SalesorderModel;
import com.project.model.sale.sales.SalesorderbreakdownModel;
import com.project.util.DateUtil;

@ManagedBean(name = "onlineOrderBean")
@SessionScoped
public class OnlineOrderBean {
	
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	Configuration config = Configuration.getConfiguration();

	String projectgst = config.getValue(IConfiguration.PRODUCT_GST_AMOUNT);
	String projectdefaultgst = config.getValue(IConfiguration.PRODUCT_DEFAULTGST_AMOUNT);

	BigDecimal unitpricedeductionAmt = new BigDecimal(projectgst);
	BigDecimal unitpricetaxAmt = new BigDecimal(projectdefaultgst);

	ISalesorderBO salesOrderBO=objectMapController.getSalesOrderBO();										

	QuotationModel orderDetail=new QuotationModel();
	List<QuotationModel> orderList=new ArrayList<QuotationModel>();
//	List<QuotationbreakdownModel> orderbreakdownlist=new ArrayList<QuotationbreakdownModel>();
	
	List<OnlineorderreportModal> orderReport=new ArrayList<OnlineorderreportModal>();
	
	Date dailyReportDateFrom;
	Date dailyReportDateTo;
	String dailyReportDeliveryStatus;
	
	public QuotationModel getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(QuotationModel orderDetail) {
		this.orderDetail = orderDetail;
	}


	public List<QuotationModel> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<QuotationModel> orderList) {
		this.orderList = orderList;
	}
	
	public List<OnlineorderreportModal> getOrderReport() {
		return orderReport;
	}

	public void setOrderReport(List<OnlineorderreportModal> orderReport) {
		this.orderReport = orderReport;
	}

	public Date getDailyReportDateFrom() {
		return dailyReportDateFrom;
	}

	public void setDailyReportDateFrom(Date dailyReportDateFrom) {
		this.dailyReportDateFrom = dailyReportDateFrom;
	}

	public Date getDailyReportDateTo() {
		return dailyReportDateTo;
	}

	public void setDailyReportDateTo(Date dailyReportDateTo) {
		this.dailyReportDateTo = dailyReportDateTo;
	}

	public String getDailyReportDeliveryStatus() {
		return dailyReportDeliveryStatus;
	}

	public void setDailyReportDeliveryStatus(String dailyReportDeliveryStatus) {
		this.dailyReportDeliveryStatus = dailyReportDeliveryStatus;
	}

	public void loadOnlineOrderList() {
		
		try {
			Date dateFrom=new Date();
			dateFrom=DateUtil.getFromTodayDateTime();
			
			Date dateTo=new Date();
	        Calendar calendar = Calendar.getInstance();

			calendar.setTime(dateTo);
	        calendar.set(Calendar.MILLISECOND, 0);
	        calendar.set(Calendar.SECOND, 59);
	        calendar.set(Calendar.MINUTE, 59);
	        calendar.set(Calendar.HOUR, 23);	  
	        dateTo=calendar.getTime();
	        
			orderList=salesOrderBO.getOnlineOrderList(null,null, dateFrom, dateTo,null, null, null, loginBean.getBranch().getBranchId());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadOnlineReport() {
		try {
			
			Calendar calendar = Calendar.getInstance();

			
			if(dailyReportDateFrom==null) {
				
				this.setDailyReportDateFrom(DateUtil.getFromTodayDateTime());
				
			}
			if(dailyReportDateTo==null) {
				
				this.setDailyReportDateTo(DateUtil.getToTodayDateTime());
				
			}else {
				
				calendar.setTime(dailyReportDateTo);
		        calendar.set(Calendar.MILLISECOND, 0);
		        calendar.set(Calendar.SECOND, 59);
		        calendar.set(Calendar.MINUTE, 59);
		        calendar.set(Calendar.HOUR_OF_DAY, 23);
		        
		        dailyReportDateTo=calendar.getTime();
		        
			}
			
	        
			orderList=salesOrderBO.getOnlineOrderList(null,null, dailyReportDateFrom, dailyReportDateTo,null, null, this.dailyReportDeliveryStatus, loginBean.getBranch().getBranchId());
			if(this.getOrderReport()!=null) {
				this.getOrderReport().clear();	
			}
			
			BigDecimal totalAmount=new BigDecimal("0");
			for(QuotationModel data:orderList) {
				totalAmount=totalAmount.add(data.getTotalAmount());
			}
			OnlineorderreportModal report=new OnlineorderreportModal();
			
			report.setTotalAmount(totalAmount);
			if(this.getDailyReportDeliveryStatus().equals("0")) {
				
				report.setStatus(config.getValue(IConfiguration.DELIVERYORDER_STATUS_CLOSED_LABLE));
				
			}else if(this.getDailyReportDeliveryStatus().equals("1")) {
				
				report.setStatus(config.getValue(IConfiguration.DELIVERYORDER_STATUS_NEWORDER_LABLE));
				
			}else if(this.getDailyReportDeliveryStatus().equals("2")) {
				
				report.setStatus(config.getValue(IConfiguration.DELIVERYORDER_STATUS_PROCESSED_LABLE));
				
			}else if(this.getDailyReportDeliveryStatus().equals("3")) {
				
				report.setStatus(config.getValue(IConfiguration.DELIVERYORDER_STATUS_ORDERED_LABLE));
				
			}else if(this.getDailyReportDeliveryStatus().equals("5")) {
				
				report.setStatus(config.getValue(IConfiguration.DELIVERYORDER_STATUS_CANCEL_LABLE));
				
			}
			
			orderReport.add(report);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void vieworderdetails(ActionEvent event) {
		orderDetail=(QuotationModel) event.getComponent().getAttributes().get("order");
		
	}
	
	public void updateorderstatus() {
		try {
			if(orderDetail.getStatus().equalsIgnoreCase("0")) { // delivered
				SalesorderModel salesorderModel=new SalesorderModel();
				salesorderModel.setCreatedBy(loginBean.getLogdetail().getEmailAddress());
				salesorderModel.setCreatedDate(new Date());
				salesorderModel.setSalesRep(loginBean.getLogdetail().getEmailAddress());
				salesorderModel.setStatus(config.getValue(IConfiguration.SALESORDER_STATUS_ORDERED_VALUE));
				salesorderModel.setQuoteNo(orderDetail.getQuotationNo());
				
				salesorderModel.setPaymentType("Cash");
				salesorderModel.setSalesType("2");//online order
				
				salesorderModel.setBranchtype(loginBean.getBranch().getBranchtype());
				salesorderModel.setBranchId(loginBean.getBranch().getBranchId());
				salesorderModel.setBranchRecordId(loginBean.getBranch().getBranchId());
				salesorderModel.setCustomerBranchId(loginBean.getBranch().getBranchId());
				
				salesorderModel.setTotalAmount(orderDetail.getTotalAmount());
				salesorderModel.setTotalTax(orderDetail.getTotalTax());
				//salesorderModel.setReceivedAmount(orderDetail.getTotalAmount());
				//salesorderModel.setBalanceAmount(BigDecimal.ZERO);
				
				salesorderModel.setTotalBeforeDiscount(salesorderModel.getTotalAmount());
				salesorderModel.setDiscountRate(0);
				
				List<SalesorderbreakdownModel> salesorderBreakdownModel= new ArrayList<SalesorderbreakdownModel>();
				for(QuotationbreakdownModel orderBreakdown:orderDetail.getQuotationbreakdowns()) {
					SalesorderbreakdownModel data=new SalesorderbreakdownModel();
					
									
					data.setDiscount(orderBreakdown.getDiscount());
					data.setDiscountAmount(orderBreakdown.getDiscountAmount());				
					data.setQuantity(orderBreakdown.getQuantity());			
					data.setSubTotal(orderBreakdown.getSubTotal());
					data.setToalItemSupplied(0);
					data.setTotalItemOrdered(0);
					data.setTotalItemPending(0);
					data.setUnitPrice(orderBreakdown.getUnitPrice());
					data.setProductId(orderBreakdown.getProductId());
					data.setProductName(orderBreakdown.getProductName());
					data.setCreatedDate(salesorderModel.getCreatedDate());
					data.setExpDate(salesorderModel.getCreatedDate());
					data.setReturnquantity(BigDecimal.ZERO);
					data.setTaxCode(orderBreakdown.getTaxCode());
					data.setTaxAmount(orderBreakdown.getTaxAmount());
					data.setGstCode(orderBreakdown.getGstCode());
					data.setGstAmount(orderBreakdown.getGstAmount());
					
					BigDecimal unitpriceAmt = unitpricedeductionAmt.multiply((data.getUnitPrice().divide(new BigDecimal(100))));
					data.setUnitduplicatePrice(data.getUnitPrice().subtract(unitpriceAmt));
					
					
					salesorderBreakdownModel.add(data);	
				}
				salesorderModel.setSalesorderbreakdowns(salesorderBreakdownModel);
				salesorderModel=salesOrderBO.createNewSalesorder(salesorderModel,null);
				
			}
			orderDetail=salesOrderBO.updateOnlineOrder(orderDetail);
			loadOnlineOrderList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
