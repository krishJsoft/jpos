package com.project.bean.sales.sale;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

import com.project.bo.interfaces.IBranchsalesBO;
import com.project.model.sale.sales.branchsale.BranchsaleModel;
import com.project.util.DateUtil;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.factory.ErrorFactory;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.home.ProjectHome;
import com.project.login.LoginBean;

public class BranchsaleBeanInfo {

	
	
	BranchsaleBean branchsaleBean = (BranchsaleBean) BeanContext.getReference("branchsaleBean");
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	FacesContext context = FacesContext.getCurrentInstance();
	Configuration config = Configuration.getConfiguration();
	String projectHome = config.getValue(IConfiguration.PROJECT_HOME_FILENAME);
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	static ErrorFactory errorFactory = ErrorFactory.getInstance();
	
	public void listSales() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/sales/branchSalesList.xhtml");
		projectHome.setTitlePage("Sales --> Branch -->Branch Sales Listing");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
	public void newSales() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/sales/addeditbranchSales.xhtml");
		projectHome.setTitlePage("Sales --> Branch -->Add/Edit Branch Sales");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
	
	
	public void createNewSales()
	{
		BranchsaleModel branchsaleModel = new BranchsaleModel();
		IBranchsalesBO branchsalesBO;
		boolean saveSuccess = false;
		try
		{	branchsalesBO=branchsaleBean.getBranchsalesBO();
				
		if(validateSales())
		{
			branchsaleModel = branchsaleBean.getBranchsale();		
			branchsaleModel.setBranchId(branchsaleBean.getBranchId());
			
			branchsaleModel.setBranchsalesbreakdowns(branchsaleBean.getBranchsalesbreakdowns());
			branchsaleModel.setCreatedBy(loginBean.getUserName());
			branchsaleModel.setCreatedDate(DateUtil.getTodayDate());
			branchsaleModel.setDeliveryTerms(branchsaleBean.getBranchsale().getDeliveryTerms());
			branchsaleModel.setPaymentTerms(branchsaleBean.getBranchsale().getPaymentTerms());
			branchsaleModel.setRemarks(branchsaleBean.getBranchsale().getRemarks());
			branchsaleModel.setStatus("1");		
			branchsaleModel.setBranchStatus("1");
			branchsaleModel.setBranchRecordId(loginBean.getBranch().getBranchId());
			branchsaleModel.setTotalTax(branchsaleBean.getTotalTaxAmount());
			
		    saveSuccess=branchsalesBO.createNewBranchsale(branchsaleModel);
		}
		    
			if (saveSuccess) {					
				branchsaleBean.resetSalesList();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesListPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("label.created.success"),null));				
			}
		}
		catch(Exception e)
		{				
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));
		}
	}	
	
		
	public void updateSales()
	{		
		BranchsaleModel branchsale = new BranchsaleModel();
		IBranchsalesBO branchsalesBO;
		boolean updateSuccess = false;
		boolean itemRemoved = true;
		try
		{	branchsalesBO=branchsaleBean.getBranchsalesBO();
	    	branchsale = branchsaleBean.getBranchsale();
	    	branchsale.setCreatedBy(loginBean.getUserName());
	    	branchsale.setBranchId(branchsaleBean.getBranchId());
	    	branchsale.setBranchRecordId(loginBean.getBranch().getBranchId());
		    branchsale.setLastModifedDate(DateUtil.getTodayDate());		    
		    branchsale.setStatus("1");
		    branchsale.setTotalTax(branchsaleBean.getTotalTaxAmount());
		    if(validateSales())
			{
		   	branchsale.setBranchsalesbreakdowns(branchsaleBean.getBranchsalesbreakdowns());  
		    
		   	branchsaleBean.setItemaction("submit"); 
		   updateSuccess=branchsalesBO.updateBranchsale(branchsale);
			}
			if (updateSuccess) {					
				branchsaleBean.resetSales();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesListPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("label.update.success"),null));				
			}
		}
		catch(Exception e)
		{				
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));
			
		}
		
	}	
	

	
		
	public BranchsaleModel editSales(Integer branchsalesId)
	{
		IBranchsalesBO branchsalesBO;
		 BranchsaleModel branchsaleModelData=null;
		try {
			branchsalesBO=branchsaleBean.getBranchsalesBO();
			 branchsaleModelData = branchsalesBO.getBranchsaleDetails(branchsalesId);
			 branchsaleBean.setBranchsale(branchsaleModelData);	 
			 branchsaleBean.setItemaction("update");
		} catch (Exception e) {
			context.addMessage(
			UIComponent.findComponent(context.getViewRoot(),"salesListPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			e.printStackTrace();
		}		
		return  branchsaleModelData;
	}
	
	
	
	
	public boolean validateSales()
	{
		boolean valid = true;
		BranchsaleBean branchsaleBean = (BranchsaleBean) BeanContext.getReference("branchsaleBean");
		FacesContext context = FacesContext.getCurrentInstance();	
		try
		{
			if (factoryBean.checkIsNullAssignMessage(branchsaleBean.getBranchId(),"branchstaff.label.branchName", "customerName")) {
				valid = false;
			}	
			
			else if (factoryBean.checkIsZeroAssignMessage(""+branchsaleBean.getBranchId(), "branchstaff.label.branchName",  "customerName")){
				valid = false;
			}				
			
			if (factoryBean.checkIsNullAssignMessage(branchsaleBean.getBranchsale().getPaymentTerms(),
					"quotation.label.paymentTerms", "paymentTerms")) {
				valid = false;
			}	
			if (factoryBean.checkIsNullAssignMessage(branchsaleBean.getBranchsale().getDeliveryTerms(),
					"quotation.label.deliveryTerms", "deliveryTerms")) {
				valid = false;
			}
			
			
			if(branchsaleBean.getBranchsalesbreakdowns().size()==0)
			{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesListPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "No Records found in Quotation"));
			}
		}
		catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesListPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		
		}	
		
		return valid;
	}
	
	
}
