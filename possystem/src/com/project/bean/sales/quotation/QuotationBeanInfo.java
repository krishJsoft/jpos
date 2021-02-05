package com.project.bean.sales.quotation;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

import com.project.bo.interfaces.IQuotationBO;
import com.project.model.sale.sales.QuotationModel;
import com.project.model.sale.sales.QuotationbreakdownModel;
import com.project.util.DateUtil;
import com.project.bean.purchase.PurchaseRequestBean;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.factory.ErrorFactory;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.home.ProjectHome;
import com.project.login.LoginBean;


public class QuotationBeanInfo {

	QuotationBean quotationBean = (QuotationBean) BeanContext.getReference("quotationBean");
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	FacesContext context = FacesContext.getCurrentInstance();
	Configuration config = Configuration.getConfiguration();
	String projectHome = config.getValue(IConfiguration.PROJECT_HOME_FILENAME);
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	static ErrorFactory errorFactory = ErrorFactory.getInstance();
	
	public void listQuotation() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/sales/quotationList.xhtml");
		projectHome.setTitlePage("Sales --> quotationList -->Quotation Listing");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
	public void newQuotation() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/sales/addeditquotation.xhtml");
		projectHome.setTitlePage("Sales --> quotationList -->Add/Edit Quotation");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
	
	
	public void createNewQuotation()
	{
		QuotationModel quotationModel = new QuotationModel();
		IQuotationBO quoationBO;
		boolean saveSuccess = false;
		try
		{	quoationBO=quotationBean.getQuotationBO();
				
		if(validateQuotation())
		{
			quotationModel = quotationBean.getQuotation();			
			quotationModel.setCustomerId(quotationBean.getCustomer().getCustomerId());
			quotationModel.setCustomerName(quotationBean.getCustomer().getCustomerName());
			quotationModel.setQuotationbreakdowns(quotationBean.getQuotationbreakdowns());
			quotationModel.setCreatedBy(loginBean.getUserName());
			quotationModel.setCreatedDate(DateUtil.getTodayDate());
			quotationModel.setDeliveryTerms(quotationBean.getQuotation().getDeliveryTerms());
			quotationModel.setPaymentTerms(quotationBean.getQuotation().getPaymentTerms());
			quotationModel.setRemarks(quotationBean.getQuotation().getRemarks());
			quotationModel.setStatus("1");		
			quotationModel.setBranchRecordId(loginBean.getBranch().getBranchId());
			quotationModel.setTotalTax(quotationBean.getTotalTaxAmount());
			
		    saveSuccess=quoationBO.createNewQuotation(quotationModel);
		}
		    
			if (saveSuccess) {					
				quotationBean.resetQuotationList();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("quotation.label.save.success"),null));				
			}
		}
		catch(Exception e)
		{				
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));
		}
	}	
	
		
	public void updateQuotation()
	{		
		QuotationModel quotation = new QuotationModel();
		IQuotationBO quotationBO;
		boolean updateSuccess = false;
		boolean itemRemoved = true;
		try
		{	quotationBO=quotationBean.getQuotationBO();
		    quotation = quotationBean.getQuotation();
		    quotation.setCreatedBy(loginBean.getUserName());
		    quotation.setLastModifedDate(DateUtil.getTodayDate());		    
		    quotation.setStatus("1");
		    quotation.setTotalTax(quotationBean.getTotalTaxAmount());
		    if(validateQuotation())
			{
		    quotation.setQuotationbreakdowns(quotationBean.getQuotationbreakdowns());		    
		    /*QuotationModel p = quotationBO.getQuotationDetails(quotation.getQuotationId());		
			   
			 for(QuotationbreakdownModel oldItem:p.getQuotationbreakdowns()){
				 itemRemoved = true;
			 for (QuotationbreakdownModel newItem :quotation.getQuotationbreakdowns() ) {								 
					if(oldItem.getQuotationBreakdownId()==newItem.getQuotationBreakdownId()){
						itemRemoved = false;							
						break;									
					}
			}
			if(itemRemoved){
				quotationBO.deleteQuotationbreakdown(oldItem);					
				}			
			 }*/		 
		   quotationBean.setItemaction("submit"); 
		   updateSuccess=quotationBO.updateQuotation(quotation);
			}
			if (updateSuccess) {					
				quotationBean.resetQuotation();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("quotation.label.update.success"),null));				
			}
		}
		catch(Exception e)
		{				
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));
			
		}
		
	}	
	

	
		
	public QuotationModel editQuotation(Integer quotationId)
	{
		 IQuotationBO quotationBO;		
		 QuotationModel quotationData=null;
		try {
			 quotationBO = quotationBean.getQuotationBO();
			 quotationData = quotationBO.getQuotationDetails(quotationId);
			 quotationBean.setQuotation(quotationData);		 
			 quotationBean.setItemaction("update");
		} catch (Exception e) {
			context.addMessage(
			UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			e.printStackTrace();
		}		
		return  quotationData;
	}
	
	
	
	
	public boolean validateQuotation()
	{
		boolean valid = true;
		QuotationBean quotationBean = (QuotationBean) BeanContext.getReference("quotationBean");
		FacesContext context = FacesContext.getCurrentInstance();	
		try
		{
			if (factoryBean.checkIsNullAssignMessage(quotationBean.getCustomer().getCustomerId(),
					"quotation.label.customerName", "customerName")) {
				valid = false;
			}	
			
			else if (factoryBean.checkIsZeroAssignMessage(""+quotationBean.getCustomer().getCustomerId(), "quotation.label.customerName",  "customerName")){
				valid = false;
			}	
			
			
			if (factoryBean.checkIsNullAssignMessage(quotationBean.getQuotation().getPaymentTerms(),
					"quotation.label.paymentTerms", "paymentTerms")) {
				valid = false;
			}	
			if (factoryBean.checkIsNullAssignMessage(quotationBean.getQuotation().getDeliveryTerms(),
					"quotation.label.deliveryTerms", "deliveryTerms")) {
				valid = false;
			}
			if (factoryBean.checkIsNullAssignMessage(quotationBean.getQuotation().getQuotationDueDate(),
					"quotation.label.quotationDueDate", "quotationDueDate")) {
				valid = false;
			}
			
			if(quotationBean.getQuotationbreakdowns().size()==0)
			{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "No Records found in Quotation"));
			}
		}
		catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		
		}	
		
		return valid;
	}
	
	
	
	
}
