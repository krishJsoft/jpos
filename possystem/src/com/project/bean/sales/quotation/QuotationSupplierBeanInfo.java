package com.project.bean.sales.quotation;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

import com.project.bo.interfaces.IQuotationBO;
import com.project.bo.interfaces.IQuotationSupplierBO;
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


public class QuotationSupplierBeanInfo {

	QuotationSupplierBean quotationsupplierBean = (QuotationSupplierBean) BeanContext.getReference("quotationsupplierBean");
	PurchaseRequestBean purchaseRequestBean = (PurchaseRequestBean) BeanContext.getReference("purchaseRequestBean");
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
		projectHome.setContentpage("/sales/quotationsupplierList.xhtml");
		projectHome.setTitlePage("Sales --> RFQ -->Quotation Listing");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
	public void newQuotation() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/sales/addeditquotationsupplier.xhtml");
		projectHome.setTitlePage("Sales --> RFQ -->Add/Edit RFQ");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
	public void quotationSuppliers() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/sales/quotationsuppliers.xhtml");
		projectHome.setTitlePage("Sales --> RFQ -->Suppliers");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
	public void quotationSuppliersComparision() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/sales/quotationsuppliersdetail.xhtml");
		projectHome.setTitlePage("Sales --> RFQ -->Suppliers Quotation");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
	public void quotationSuppliersComparision1() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/sales/quotationsupplierscomparisiondetail.xhtml");
		projectHome.setTitlePage("Sales --> RFQ -->Suppliers Quotation Comparison");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
	
	public void createNewQuotation()
	{
		QuotationModel quotationModel = new QuotationModel();
		IQuotationSupplierBO quoationBO;
		boolean saveSuccess = false;
		StringBuilder buf = new StringBuilder("");
		try
		{	quoationBO=quotationsupplierBean.getQuotationBO();
				
		if(validateQuotation())
		{
			quotationModel = quotationsupplierBean.getQuotation();				
			quotationModel.setQuotationbreakdowns(quotationsupplierBean.getQuotationbreakdowns());
			quotationModel.setCreatedBy(loginBean.getUserName());
			quotationModel.setCreatedDate(DateUtil.getTodayDate());
			quotationModel.setQuotationDueDate(quotationsupplierBean.getQuotation().getQuotationDueDate());
			quotationModel.setQuotationDate(DateUtil.getTodayDate());
			
			quotationModel.setDeliveryTerms(quotationsupplierBean.getQuotation().getDeliveryTerms());
			quotationModel.setPaymentTerms(quotationsupplierBean.getQuotation().getPaymentTerms());
			quotationModel.setRemarks(quotationsupplierBean.getQuotation().getRemarks());
			quotationModel.setStatus("1");
			quotationModel.setBranchRecordId(loginBean.getBranch().getBranchId());
			quotationModel.setTotalTax(quotationsupplierBean.getTotalTaxAmount());				
			
			if(purchaseRequestBean.getRequestIds()!=null && purchaseRequestBean.getRequestIds().size()!=0)
			{				
				int count=0;
				for(Integer id :purchaseRequestBean.getRequestIds())
				{
					if(count==0)
					{
					buf.append(""+id);
					}
					else
					{
					buf.append(","+id);
					}
					count=count+1;
				}
			}
			quotationModel.setRequestIds(""+buf); // Consolidated Purchase Request Ids				
			
		    saveSuccess=quoationBO.createNewQuotation(quotationModel);
		}
		    
			if (saveSuccess) {					
				quotationsupplierBean.resetQuotationList();
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
		IQuotationSupplierBO quotationBO;
		boolean updateSuccess = false;
		boolean itemRemoved = true;
		try
		{	quotationBO=quotationsupplierBean.getQuotationBO();
		    quotation = quotationsupplierBean.getQuotation();
		    quotation.setCreatedBy(loginBean.getUserName());
		    quotation.setLastModifedDate(DateUtil.getTodayDate());		    
		    quotation.setStatus("1");
		    quotation.setTotalTax(quotationsupplierBean.getTotalTaxAmount());
		    if(validateQuotation())
			{
		    quotation.setQuotationbreakdowns(quotationsupplierBean.getQuotationbreakdowns());		    
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
		   quotationsupplierBean.setItemaction("submit"); 
		   updateSuccess=quotationBO.updateQuotation(quotation);
			}
			if (updateSuccess) {					
				quotationsupplierBean.resetQuotation();
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
		IQuotationSupplierBO quotationBO;		
		 QuotationModel quotationData=null;
		try {
			 quotationBO = quotationsupplierBean.getQuotationBO();
			 quotationData = quotationBO.getQuotationDetails(quotationId);
			 quotationsupplierBean.setQuotation(quotationData);		 
			 quotationsupplierBean.setItemaction("update");
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
		QuotationSupplierBean quotationsupplierBean = (QuotationSupplierBean) BeanContext.getReference("quotationsupplierBean");
		FacesContext context = FacesContext.getCurrentInstance();	
		try
		{
			
			
			if (factoryBean.checkIsNullAssignMessage(quotationsupplierBean.getQuotation().getPaymentTerms(),
					"quotation.label.paymentTerms", "paymentTerms")) {
				valid = false;
			}	
			if (factoryBean.checkIsNullAssignMessage(quotationsupplierBean.getQuotation().getDeliveryTerms(),
					"quotation.label.deliveryTerms", "deliveryTerms")) {
				valid = false;
			}
			if (factoryBean.checkIsNullAssignMessage(quotationsupplierBean.getQuotation().getQuotationDueDate(),
					"quotation.label.quotationDueDate", "quotationDueDate")) {
				valid = false;
			}
			
			if(quotationsupplierBean.getQuotationbreakdowns().size()==0)
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
