package com.project.home;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;

import com.project.bo.interfaces.IExpensesBO;
import com.project.bo.interfaces.IPoscounterBO;
import com.project.bo.interfaces.ISalesorderBO;
import com.project.model.datamodel.SalesPurchaseDashboradModel;
import com.project.model.sale.sales.ExpensesTransactionModel;
import com.project.model.sale.sales.PoscashtransactionModel;
import com.project.util.DateUtil;
import com.project.common.config.Configuration;
import com.project.common.factory.BeanContext;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.CommonFactoryBean;
import com.project.login.LoginBean;
import com.project.modal.report.PoscounterreportModal;

public class DashboardBeanInfo {

	
	
	 	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	    FacesContext context = FacesContext.getCurrentInstance();
	    Configuration config = Configuration.getConfiguration();
		ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
		LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
		
		
		private Date startDateTimeOfMonth(int month,int year) {
			Date dateTime=null;
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);
				cal.set(Calendar.MONTH, month);
				cal.set(Calendar.YEAR, year);
				cal.set(Calendar.DAY_OF_MONTH, 1);    
			dateTime = cal.getTime();
			return dateTime;
		}
		
		private Date endDateTimeOfMonth(int month,int year) {
			Date dateTime=null;
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			cal.set(Calendar.MILLISECOND, 999);
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, month); // 11 = december
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); // new years eve
			
			dateTime = cal.getTime();
			return dateTime;
		}
		
		public List<SalesPurchaseDashboradModel> getSalesDashboard(int year , String usertype)
		{
			List<SalesPurchaseDashboradModel> dashList = new ArrayList<SalesPurchaseDashboradModel>();
			List<SalesPurchaseDashboradModel> purchasedashList = new ArrayList<SalesPurchaseDashboradModel>();
			List<SalesPurchaseDashboradModel> dashList1 = new ArrayList<SalesPurchaseDashboradModel>();
			List<PoscounterreportModal> dashListSalesSummary = new ArrayList<PoscounterreportModal>();
			

			ISalesorderBO salesOrderBO=objectMapController.getSalesOrderBO();
			IExpensesBO expensesBO=objectMapController.getExpensesBO();

			   
			Date start = this.startDateTimeOfMonth(0, year); //0 is jannuary
			Date end = this.endDateTimeOfMonth(11, year); //11 is december
			//IDeliveryorderBO deliveryOrderBO=objectMapController.getDeliveryOrderBO();
			try
			{				
			
				dashList=salesOrderBO.getSalesDashboard(start,end,loginBean.getBranch().getBranchId() , usertype );
				//purchasedashList=deliveryOrderBO.getPurchaseDashboard(DateUtil.getFirstDayYear(year),DateUtil.getEndDayYear(year),loginBean.getBranch().getBranchId());
				List<ExpensesTransactionModel> expDash=expensesBO.getAnnualExpenses(start,end,loginBean.getBranch().getBranchId());
				for(Integer i=1;i<13;i++){			
					BigDecimal subTotal= new BigDecimal(0.00);
					BigDecimal purchasesubTotal= new BigDecimal(0.00);
					BigDecimal taxsubTotal= new BigDecimal(0.00);
					BigDecimal expensesAmmount=new BigDecimal(0.00);
					
					subTotal=getSalesMonthAmount(dashList,i);
					purchasesubTotal=getPurchaseMonthAmount(purchasedashList,i);
					taxsubTotal=getTaxMonthAmount(dashList,i);
					expensesAmmount=getExpensesAmmount(expDash,i);
					
					SalesPurchaseDashboradModel data= new SalesPurchaseDashboradModel();
					
					data.setMonthName(DateUtil.getMonthForInt(i-1));
					data.setSubTotal(subTotal);
					data.setPurcahsesubTotal(purchasesubTotal);				
					data.setOriginalTax(taxsubTotal);
					data.setStartDate(this.startDateTimeOfMonth(i-1, year));
					data.setEndDate(this.endDateTimeOfMonth(i-1, year));
					data.setExpensesAmmount(expensesAmmount);
					
					
					dashList1.add(data);
				}			
			
			}			
			catch(Exception e)
			{
				
			}			
			return dashList1;
		}		
		
		
		public BigDecimal getSalesMonthAmount(List<SalesPurchaseDashboradModel> dashList,Integer monthNo)
		{
			BigDecimal subTotal= new BigDecimal(0.00);
			
			for(SalesPurchaseDashboradModel d : dashList){
				if(d.getMonthNo() != null && d.getMonthNo()==monthNo)
		        {	
		        	subTotal=d.getSubTotal();		      
		        }
			}			
			return subTotal;
		}
		
		public BigDecimal getExpensesAmmount(List<ExpensesTransactionModel> expList,Integer monthNo) {
			BigDecimal ammount=new BigDecimal(0.00);
			for(ExpensesTransactionModel data:expList) {
				if(data.getCreatedDate() != null && (data.getCreatedDate().getMonth()+1)==monthNo) {
					ammount=data.getAmmount();
				}
			}
			return ammount;
			
		}
		
		public BigDecimal getPurchaseMonthAmount(List<SalesPurchaseDashboradModel> dashList,Integer monthNo)
		{
			BigDecimal subTotal= new BigDecimal(0.00);
			
			for(SalesPurchaseDashboradModel d : dashList){
				if(d.getMonthNo() != null && d.getMonthNo()==monthNo)
		        {	
		        	subTotal=d.getPurcahsesubTotal();	      
		        }
			}			
			return subTotal;
		}
		
		
		public BigDecimal getTaxMonthAmount(List<SalesPurchaseDashboradModel> dashList,Integer monthNo)
		{
			BigDecimal subTotal= new BigDecimal(0.00);
			
			for(SalesPurchaseDashboradModel d : dashList){
				if(d.getMonthNo() != null && d.getMonthNo()==monthNo)
		        {	
		        	subTotal=d.getOriginalTax();	      
		        }
			}			
			return subTotal;
		}
		
		
		public List<SalesPurchaseDashboradModel> getSalesItemMovment(int year)
		{
			List<SalesPurchaseDashboradModel> dashList = new ArrayList<SalesPurchaseDashboradModel>();
			List<SalesPurchaseDashboradModel> purchasedashList = new ArrayList<SalesPurchaseDashboradModel>();
			List<SalesPurchaseDashboradModel> dashList1 = new ArrayList<SalesPurchaseDashboradModel>();
			ISalesorderBO salesOrderBO=objectMapController.getSalesOrderBO();
			//IDeliveryorderBO deliveryOrderBO=objectMapController.getDeliveryOrderBO();
			try
			{			
			dashList=salesOrderBO.getSalesDashboard(DateUtil.getFirstDayYear(year),DateUtil.getEndDayYear(year),loginBean.getBranch().getBranchId(), "0");
			//purchasedashList=deliveryOrderBO.getPurchaseDashboard(DateUtil.getFirstDayYear(year),DateUtil.getEndDayYear(year),loginBean.getBranch().getBranchId());
			
			for(Integer i=1;i<13;i++){			
				BigDecimal subTotal= new BigDecimal(0.00);
				BigDecimal purchasesubTotal= new BigDecimal(0.00);
				subTotal=getSalesMonthAmount(dashList,i);
				purchasesubTotal=getPurchaseMonthAmount(purchasedashList,i);
				SalesPurchaseDashboradModel data= new SalesPurchaseDashboradModel();
				
				data.setMonthName(DateUtil.getMonthForInt(i-1));
				data.setSubTotal(subTotal);
				data.setPurcahsesubTotal(purchasesubTotal);				
				
				dashList1.add(data);
			}					
			}			
			catch(Exception e)
			{
				
			}			
			return dashList1;
		}

		public List<SalesPurchaseDashboradModel> getSalesSummaryDashboard(int year, String usertype) {

			List<SalesPurchaseDashboradModel> dashListSalesSummary = new ArrayList<SalesPurchaseDashboradModel>();
			
			IPoscounterBO poscounterBO=objectMapController.getPoscounterBO();
			IExpensesBO expensesBO=objectMapController.getExpensesBO();

			Date start = this.startDateTimeOfMonth(0, year); //0 is jannuary
			Date end = this.endDateTimeOfMonth(11, year); //11 is december
			try
			{				
				
				List<PoscashtransactionModel> salesCash=poscounterBO.getAnnualPosCounterReport(null, start, end, 
						null,null,"1",loginBean.getBranch().getBranchId());
				List<PoscashtransactionModel> debitcredit=poscounterBO.getAnnualPosCounterReport(null, start, end, 
						null,null,"0",loginBean.getBranch().getBranchId());
			
				List<ExpensesTransactionModel> expDash=expensesBO.getAnnualExpenses(start,end,loginBean.getBranch().getBranchId());

				//purchasedashList=deliveryOrderBO.getPurchaseDashboard(DateUtil.getFirstDayYear(year),DateUtil.getEndDayYear(year),loginBean.getBranch().getBranchId());
				for(Integer i=1;i<13;i++){			
					BigDecimal credit= new BigDecimal(0.00);
					BigDecimal debit= new BigDecimal(0.00);
					BigDecimal sales= new BigDecimal(0.00);
					BigDecimal balance=new BigDecimal(0.00);
					BigDecimal tax= new BigDecimal(0.00);
					BigDecimal expensesAmmount=new BigDecimal(0.00);
					
					credit=getPosCashCreditMonthlyAmount(debitcredit,i);
					debit=getPosCashDebitMonthAmount(debitcredit,i);
					sales=getPosCashSalesMonthAmount(salesCash,i);
					tax=getPosCashTaxMonthAmount(salesCash,i);
					
					
					balance=credit.add(sales).subtract(debit).subtract(expensesAmmount);
					expensesAmmount=getExpensesAmmount(expDash,i);
					
					SalesPurchaseDashboradModel data= new SalesPurchaseDashboradModel();
					
					data.setMonthName(DateUtil.getMonthForInt(i-1));
					data.setDebitAmount(debit);
					data.setCreditamount(credit);				
					data.setSalesamount(sales);
					
					data.setOriginalTax(tax);
					data.setExpensesAmmount(expensesAmmount);
					data.setBalanceAmount(balance);
					
					data.setStartDate(this.startDateTimeOfMonth(i-1, year));
					data.setEndDate(this.endDateTimeOfMonth(i-1, year));
					
					
					dashListSalesSummary.add(data);
				}			
				
			}			
			catch(Exception e)
			{
				
			}			
			return dashListSalesSummary;
		}

		

		private BigDecimal getPosCashTaxMonthAmount(List<PoscashtransactionModel> posCounterDash, Integer monthNo) {
			BigDecimal amount= new BigDecimal(0.00);
			
			for(PoscashtransactionModel data : posCounterDash){
				if(data.getLastupdatedDate() != null && (data.getLastupdatedDate().getMonth()+1)==monthNo) {
					amount=data.getTotalTax();
				}
			}			
			return amount;
		}

	
		private BigDecimal getPosCashSalesMonthAmount(List<PoscashtransactionModel> posCounterDash, Integer monthNo) {
			BigDecimal amount= new BigDecimal(0.00);
			
			for(PoscashtransactionModel data : posCounterDash){
				if(data.getLastupdatedDate() != null && (data.getLastupdatedDate().getMonth()+1)==monthNo) {
					amount=data.getCreditamount();
				}
			}			
			return amount;
		}

		private BigDecimal getPosCashDebitMonthAmount(List<PoscashtransactionModel> posCounterDash, Integer monthNo) {
			BigDecimal amount= new BigDecimal(0.00);
			
			for(PoscashtransactionModel data : posCounterDash){
				if(data.getLastupdatedDate() != null && (data.getLastupdatedDate().getMonth()+1)==monthNo) {
					amount=data.getDebitAmount();
				}
			}			
			return amount;
		}

		private BigDecimal getPosCashCreditMonthlyAmount(List<PoscashtransactionModel> posCounterDash, Integer monthNo) {
			BigDecimal amount= new BigDecimal(0.00);
			
			for(PoscashtransactionModel data : posCounterDash){
				if(data.getLastupdatedDate() != null && (data.getLastupdatedDate().getMonth()+1)==monthNo) {
					amount=data.getCreditamount();
				}
			}			
			return amount;
		}


	
		
		
		
}
