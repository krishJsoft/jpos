package com.project.bean.sales.sale;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.bo.interfaces.ISalesreturnBO;
import com.project.model.sale.sales.PoscashtransactionModel;
import com.project.model.sale.sales.PoscounterModel;
import com.project.model.sale.sales.SalesorderModel;
import com.project.model.sale.sales.SalesorderbreakdownModel;
import com.project.model.sale.sales.SalesorderreturnModel;
import com.project.model.sale.sales.SalesreturnModel;
import com.project.bean.delivery.DeliveryOrderBeanInfo;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.factory.ErrorFactory;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.common.validation.ValidatorUtil;
import com.project.home.ProjectHome;
import com.project.login.LoginBean;

public class SalesreturnBeanInfo {

	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	public static Logger log = LoggerFactory.getLogger(DeliveryOrderBeanInfo.class);
	FacesContext context = FacesContext.getCurrentInstance();
	Configuration config = Configuration.getConfiguration();
	String projectHome = config.getValue(IConfiguration.PROJECT_HOME_FILENAME);
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	static ErrorFactory errorFactory = ErrorFactory.getInstance();
	
	
	public void listSalesReturnOrder() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/sales/salesReturn.xhtml");
		projectHome.setTitlePage("Sales --> Search Sales Return");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
	
	public void newSalesReturnOrder() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/sales/addEditSalesReturn.xhtml");
		projectHome.setTitlePage("Sales -->  Add/Edit Sales Return");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
	public void savePOSReturnItem()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		SalesreturnBean salesreturnBean = (SalesreturnBean) BeanContext.getReference("salesreturnBean");
		PosBean posBean = (PosBean) BeanContext.getReference("posBean");
		PoscounterModel poscounter=posBean.getPoscounter();
		Integer returnquantity=0;
		boolean updateSuccess=false;
		ISalesreturnBO salesreturnBO=salesreturnBean.getSalesreturnBO();
		SalesorderModel salesorder = salesreturnBean.getSalesorder();
		SalesorderreturnModel salesreturnObj = new SalesorderreturnModel();
		List<SalesreturnModel> rdataList = new ArrayList<SalesreturnModel>();
		List<SalesorderbreakdownModel> salesList = new ArrayList<SalesorderbreakdownModel>();
		salesreturnObj.setLaetupdatedDate(new Date());
		salesreturnObj.setReturndate(new Date());
		salesreturnObj.setSalesorder(salesorder);
		salesreturnObj.setSalesOrderNo(salesorder.getSalesOrderNo());
		salesreturnObj.setStatus(config.getValue(IConfiguration.RETURN_STATUS_ORDERED_VALUE));
		salesreturnObj.setUpdatedBy(loginBean.getUserName());
		salesreturnObj.setSupplierstatus(config.getValue(IConfiguration.RETURN_STATUS_ORDERED_VALUE)); // Direct Approved
		salesreturnObj.setBranchRecordId(loginBean.getBranch().getBranchId());
		salesreturnObj.setTotalAmount(salesreturnBean.getReturnTotal());
		
		
		
		PoscashtransactionModel poscashtransaction = new PoscashtransactionModel();
		
		poscashtransaction.setLastupdatedBy(loginBean.getLogdetail().getEmailAddress());
		poscashtransaction.setLastupdatedDate(new Date());
		poscashtransaction.setType("2"); // Cash out
		poscashtransaction.setCounterId(poscounter.getCounterId());
		poscashtransaction.setDebitAmount(salesreturnBean.getReturnTotal()); // CASH OUT
		poscashtransaction.setCreditamount(new BigDecimal(0.00));
		poscashtransaction.setStatus("1"); // Counter Open
		poscashtransaction.setTransactionType("1"); // POS		
		poscashtransaction.setTransactionStatus("2"); // Direct Approved
		poscashtransaction.setPaymentType("Cash");
		poscashtransaction.setPaymentCount(1);
		
		poscashtransaction.setBranchtype(loginBean.getBranch().getBranchtype());
		poscashtransaction.setBranchRecordId(loginBean.getBranch().getBranchId());
		
		salesreturnObj.setPoscashtransaction(poscashtransaction);		
		
		
		try
		{
			for(SalesorderbreakdownModel c : salesorder.getSalesorderbreakdowns())
			{
				
				
				if (ValidatorUtil.isNotNull(salesreturnBean.getReturnQuantity().get(c.getId()))) 
				{
					SalesreturnModel salesreturn = new SalesreturnModel();					
					SalesorderbreakdownModel salesorderbreakdownmodel = new SalesorderbreakdownModel();						
					salesorderbreakdownmodel.setSalesOrderBreakdownId(c.getSalesOrderBreakdownId());
					//salesorderbreakdownmodel.set
					salesreturn.setSalesorderbreakdownmodel(c);
					
					salesreturn.setSupplier(c.getSupplier());
					
					returnquantity = new Integer(salesreturnBean.getReturnQuantity().get(c.getId()));
					salesreturn.setProcessBy(loginBean.getUserName());
					salesreturn.setReplacementQuantity(0);					
					salesreturn.setReturnQuantity(returnquantity);
					salesreturn.setStatus(config.getValue(IConfiguration.RETURN_STATUS_NEWORDER_VALUE));					
					
					if(c.getReturnquantity()==null)
					{
						c.setReturnquantity(new BigDecimal(0));
					}
					c.setReturnquantity(c.getReturnquantity().add(new BigDecimal(returnquantity))); //add return item		
					salesList.add(c); //add return item to  Update Return Quantity in Original Sales Order
					rdataList.add(salesreturn);		
					
					
				}
				
			}
			
			salesorder.setSalesorderbreakdowns(salesList);
			
			salesreturnObj.setSalesorder(salesorder);
			salesreturnObj.setSalesreturn(rdataList);
			updateSuccess=salesreturnBO.createNewSalesreturnPos(salesreturnObj);
			
			if(updateSuccess)
			{
				salesreturnBean.getReturnQuantity().clear();
				salesreturnBean.setSalesorder(null);
				salesreturnBean.setSalesOrderNo(null);
				//listSalesReturnOrder();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesreturnPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,"Success",null));
			}
			
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesreturnPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));				
			log.info("Error in saveReturnItem of SalesreturnBeanInfo "+ e.toString());
		}
	}
	
	
	
	
	
	public void saveReturnItem()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		SalesreturnBean salesreturnBean = (SalesreturnBean) BeanContext.getReference("salesreturnBean");
		PosBean posBean = (PosBean) BeanContext.getReference("posBean");
		PoscounterModel poscounter=posBean.getPoscounter();
		Integer returnquantity=0;
		boolean updateSuccess=false;
		ISalesreturnBO salesreturnBO=salesreturnBean.getSalesreturnBO();
		SalesorderModel salesorder = salesreturnBean.getSalesorder();
		SalesorderreturnModel salesreturnObj = new SalesorderreturnModel();
		List<SalesreturnModel> rdataList = new ArrayList<SalesreturnModel>();
		List<SalesorderbreakdownModel> salesList = new ArrayList<SalesorderbreakdownModel>();
		salesreturnObj.setLaetupdatedDate(new Date());
		salesreturnObj.setReturndate(new Date());
		salesreturnObj.setSalesorder(salesorder);
		salesreturnObj.setSalesOrderNo(salesorder.getSalesOrderNo());
		salesreturnObj.setStatus(config.getValue(IConfiguration.RETURN_STATUS_NEWORDER_VALUE));
		salesreturnObj.setUpdatedBy(loginBean.getUserName());
		salesreturnObj.setSupplierstatus(config.getValue(IConfiguration.RETURN_STATUS_NEWORDER_VALUE));
		salesreturnObj.setBranchRecordId(loginBean.getBranch().getBranchId());
		salesreturnObj.setTotalAmount(salesreturnBean.getReturnTotal());
		
		
		
		PoscashtransactionModel poscashtransaction = new PoscashtransactionModel();
		
		poscashtransaction.setLastupdatedBy(loginBean.getLogdetail().getEmailAddress());
		poscashtransaction.setLastupdatedDate(new Date());
		poscashtransaction.setType("2"); // Cash out
		poscashtransaction.setCounterId(poscounter.getCounterId());
		poscashtransaction.setDebitAmount(salesreturnBean.getReturnTotal());
		poscashtransaction.setCreditamount(new BigDecimal(0.00));
		poscashtransaction.setStatus("1"); // Counter Open
		poscashtransaction.setTransactionType("1"); // POS		
		poscashtransaction.setTransactionStatus("2"); // Direct Approved
		poscashtransaction.setPaymentType("Cash");
		poscashtransaction.setPaymentCount(1);
		
		poscashtransaction.setBranchtype(loginBean.getBranch().getBranchtype());
		poscashtransaction.setBranchRecordId(loginBean.getBranch().getBranchId());
		
		salesreturnObj.setPoscashtransaction(poscashtransaction);		
		
		
		try
		{
			for(SalesorderbreakdownModel c : salesorder.getSalesorderbreakdowns())
			{
				
				
				if (ValidatorUtil.isNotNull(salesreturnBean.getReturnQuantity().get(c.getId()))) 
				{
					SalesreturnModel salesreturn = new SalesreturnModel();					
					SalesorderbreakdownModel salesorderbreakdownmodel = new SalesorderbreakdownModel();						
					salesorderbreakdownmodel.setSalesOrderBreakdownId(c.getSalesOrderBreakdownId());
					//salesorderbreakdownmodel.set
					salesreturn.setSalesorderbreakdownmodel(c);
					
					salesreturn.setSupplier(c.getSupplier());
					
					returnquantity = new Integer(salesreturnBean.getReturnQuantity().get(c.getId()));
					salesreturn.setProcessBy(loginBean.getUserName());
					salesreturn.setReplacementQuantity(0);					
					salesreturn.setReturnQuantity(returnquantity);
					salesreturn.setStatus(config.getValue(IConfiguration.RETURN_STATUS_NEWORDER_VALUE));					
					if(c.getReturnquantity()==null)
					{
						c.setReturnquantity(new BigDecimal(0));
					}
					c.setReturnquantity(c.getReturnquantity().add(new BigDecimal(returnquantity))); //add return item		
					salesList.add(c); //add return item to  Update Return Quantity in Original Sales Order
					rdataList.add(salesreturn);		
					
					
				}
				
			}
			
			salesorder.setSalesorderbreakdowns(salesList);
			
			salesreturnObj.setSalesorder(salesorder);
			salesreturnObj.setSalesreturn(rdataList);
			updateSuccess=salesreturnBO.createNewSalesreturn(salesreturnObj);
			
			if(updateSuccess)
			{
				salesreturnBean.getReturnQuantity().clear();
				salesreturnBean.setSalesorder(null);
				salesreturnBean.setSalesOrderNo(null);
				listSalesReturnOrder();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesreturnPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,"Success",null));
			}
			
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesreturnPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));				
			log.info("Error in saveReturnItem of SalesreturnBeanInfo "+ e.toString());
		}
	}
	
}
