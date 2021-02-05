package com.project.bean.admin;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FlowEvent;
import org.primefaces.event.TabChangeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.bo.interfaces.IBranchBO;
import com.project.bo.interfaces.ICustomerBO;
import com.project.bo.interfaces.IStaffBO;
import com.project.bo.interfaces.ISupplierBO;
import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.CustomerModel;
import com.project.model.datamodel.SupplierModel;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.factory.ErrorFactory;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.home.ProjectHome;
import com.project.login.LoginBean;

public class SupplierBeanInfo {
	
	
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	SupplierBean supplierBean = (SupplierBean) BeanContext.getReference("supplierBean");
	public static Logger log = LoggerFactory.getLogger(BranchBeanInfo.class);
	FacesContext context = FacesContext.getCurrentInstance();
	Configuration config = Configuration.getConfiguration();
	String projectHomefile = config.getValue(IConfiguration.PROJECT_HOME_FILENAME);
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	static ErrorFactory errorFactory = ErrorFactory.getInstance();
	String activeStep="";
	
	
	
	public void listSupplier() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/admin/supplier.xhtml");
		projectHome.setTitlePage("Admin --> Suppliers -->Search Supplier");
		navHandler.handleNavigation(context, null, projectHomefile+ "?faces-redirect=true");
	}

	
	
	public void newSupplier() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/admin/addEditSupplier.xhtml");
		projectHome.setTitlePage("Admin --> Suppliers -->Add/Edit Supplier");
		navHandler.handleNavigation(context, null, projectHomefile+ "?faces-redirect=true");
	}
	
	
	
	
	public void saveSupplier()
	{	
		
		
		if(supplierStep1Validation() && supplierStep2Validation())
		{	
			boolean saveSuccess = false;			
			try
			{
			SupplierModel supplier=supplierBean.getSupplier();
			supplier.setCreatedBy(loginBean.getLoginName());
			supplier.setCreatedDate(new Date());
			supplier.setLastModifiedDate(new Date());			
			supplier.setDoclist(supplierBean.getDoclist());
			ISupplierBO supplierBO=supplierBean.getSupplierBO();
			saveSuccess=supplierBO.createNewSupplier(supplier);
			
			if (saveSuccess) {					
				supplierBean.resetSearchSupplier();
				supplierBean.deleteBeforcopy();
				supplierBean.copyTo();
				listSupplier();
				resetSupplier();
				CommonListBeanInfo.getAllSupplierList();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"supplierPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("supplier.label.save.success"),null));				
			}
			
			}
			catch(Exception e)
			{
				context.addMessage(
				UIComponent.findComponent(context.getViewRoot(),"supplierPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), factoryBean.getMessageFactory().getMessage("customer.label.save.failed")));

			}
		}
		
	}
	
	
	
	
	
	public void editSupplier(Integer supplierId)
	{
		ISupplierBO supplierBO=supplierBean.getSupplierBO();
		try {
			supplierBean.setSupplier(supplierBO.getSupplierDetails(supplierId));			
			supplierBean.setAction("update");
			
			newSupplier();
			
		} catch (Exception e) {
			context.addMessage(
			UIComponent.findComponent(context.getViewRoot(),"supplierPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			e.printStackTrace();
		}
	}
	public void updateSupplier()
	{
		boolean saveSuccess = false;
		if(supplierStep1Validation() && supplierStep2Validation())
		{	
		try
		{
		SupplierModel supplier=supplierBean.getSupplier();		
		supplier.setCreatedBy(loginBean.getLoginName());		
		supplier.setLastModifiedDate(new Date());			
		
		ISupplierBO supplierBO=supplierBean.getSupplierBO();
		saveSuccess=supplierBO.updateSupplier(supplier);
		supplierBean.setAction("submit");
		if (saveSuccess) {					
			supplierBean.resetSearchSupplier();
			supplierBean.deleteBeforcopy();
			supplierBean.copyTo();
			listSupplier();
			resetSupplier();
			CommonListBeanInfo.getAllSupplierList();
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"supplierPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("supplier.label.update.success"),null));
			
		}
		
		}
		catch(Exception e)
		{
			context.addMessage(
			UIComponent.findComponent(context.getViewRoot(),"supplierPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), factoryBean.getMessageFactory().getMessage("customer.label.update.failed")));

		}
		}
	
	}
	public void deleteSupplier()
	{
		supplierBean.searchSupplier();
	}
	
	public void resetSupplier()
	{
		SupplierModel supplier=supplierBean.getSupplier();	
		supplierBean.setSupplier(supplier);
		supplierBean.setAction("submit");
		supplierBean.getDoclist().clear();
	}
	
	
	
	
	
	public void onTabChange(TabChangeEvent event) {

		

		supplierBean.setActiveIndex(supplierBean.getActiveIndex());
		if (supplierBean.getActiveIndex() == 0) {
			supplierBean.setPreviousActionStatus(true);
			supplierBean.setNextActionStatus(false);

		}

		if (!supplierStep1Validation()) {
			supplierBean.setActiveIndex(0);
		} else if (!supplierStep1Validation()) {
			supplierBean.setActiveIndex(1);
		}
		else if (!supplierStep1Validation()) {
			supplierBean.setActiveIndex(2);
		}
	}
	

		public boolean supplierStep1Validation()
		{
			boolean valid = true;			
			String messageValue = null;
			ISupplierBO supplierBO=null;
			try
			{
				supplierBO=supplierBean.getSupplierBO();
				if (factoryBean.checkIsNullAssignMessage(supplierBean.getSupplier().getSupplierName(),
						"supplier.label.supplierName", "supplierName")) {
					valid = false;
				}				
				
				
				if (factoryBean.checkIsNullAssignMessage(supplierBean.getSupplier().getSupplierCode(),
						"supplier.label.supplierCode", "supplierCode")) {
					valid = false;
				}
				
				if (factoryBean.checkIsNullAssignMessage(supplierBean.getSupplier().getAddress(),
						"supplier.label.address", "address")) {
					valid = false;
				}
				if (factoryBean.checkIsNullAssignMessage(supplierBean.getSupplier().getCity(),
						"supplier.label.city", "city")) {
					valid = false;
				}
				if (factoryBean.checkIsNullAssignMessage(supplierBean.getSupplier().getState(),
						"supplier.label.state", "state")) {
					valid = false;
				}
				
				
				
				
				else {
					if (supplierBean.getAction().equalsIgnoreCase("submit")) {
						if (supplierBO.findSupplierCompanyRegNoExites(supplierBean.getSupplier().getSupplierCode())) {
							messageValue = factoryBean.getErrorFactory().getError("supplier.errors.supplierCode.exists");
							factoryBean.reportErrorToMessageHandler(messageValue,messageValue, "supplierCode");
							valid = false;
						}
					}else{  
						
						if(! supplierBean.getSupplier().getSupplierCode().equalsIgnoreCase(supplierBean.getSupplier().getSupplierOldCode()) ){
													
							if (supplierBO.findSupplierCodeExites(supplierBean.getSupplier().getSupplierCode())) {
								messageValue = factoryBean.getErrorFactory().getError("supplier.errors.supplierCode.exists");
								factoryBean.reportErrorToMessageHandler(messageValue,messageValue, "supplierCode");
								valid = false;
							}
						}
					}
				}
				
				
				
				if (factoryBean.checkIsNullAssignMessage(supplierBean.getSupplier().getContactPerson(),
						"supplier.label.contactPerson", "contactPerson")) {
					valid = false;
				}
			
				
				if (factoryBean.checkIsNullAssignMessage(supplierBean.getSupplier().getContactPersonNumber(),
						"supplier.label.contactPersonNumber", "contactPersonNumber")) {
					valid = false;
				}	
				
				
				
				if (factoryBean.checkIsNullAssignMessage(supplierBean.getSupplier().getEmail(),
						"supplier.label.email", "emailAddress")) {
					valid = false;
				}
				
				if (factoryBean.checkIsNullAssignMessage(supplierBean.getSupplier().getCompanyRegNo(),
						"supplier.label.companyRegNo", "companyRegNo")) {
					valid = false;
				}
				
				else {
					if (supplierBean.getAction().equalsIgnoreCase("submit")) {
						if (supplierBO.findSupplierCompanyRegNoExites(supplierBean.getSupplier().getCompanyRegNo())) {
							messageValue = factoryBean.getErrorFactory().getError("supplier.errors.companyRegNo.exists");
							factoryBean.reportErrorToMessageHandler(messageValue,messageValue, "companyRegNo");
							valid = false;
						}
					}else{  
						
						if(! supplierBean.getSupplier().getCompanyRegNo().equalsIgnoreCase(supplierBean.getSupplier().getCompanyOldRegNo()) ){
													
							if (supplierBO.findSupplierCompanyRegNoExites(supplierBean.getSupplier().getCompanyRegNo())) {
								messageValue = factoryBean.getErrorFactory().getError("supplier.errors.companyRegNo.exists");
								factoryBean.reportErrorToMessageHandler(messageValue,messageValue, "companyRegNo");
								valid = false;
							}
						}
					}
				}
			
				activeStep="step1";
				
				
			}			
			catch(Exception e)
			{
				context.addMessage(
				UIComponent.findComponent(context.getViewRoot(),"supplierPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

			}
			return valid;		
		
		 }
	
		
		public boolean supplierStep2Validation()
		{
			boolean valid = true;			
			String messageValue = null;
			try
			{
				if (factoryBean.checkIsNullAssignMessage(supplierBean.getSupplier().getBankName(),
						"supplier.label.bankName", "bankName")) {
					valid = false;
				}
				if (factoryBean.checkIsNullAssignMessage(supplierBean.getSupplier().getBranchName(),
						"supplier.label.branchName", "branchName")) {
					valid = false;
				}
				if (factoryBean.checkIsNullAssignMessage(supplierBean.getSupplier().getAccountNumber(),
						"supplier.label.accountNumber", "accountNumber")) {
					valid = false;
				}
				
				activeStep="step2";
			
			}			
			catch(Exception e)
			{
				context.addMessage(
				UIComponent.findComponent(context.getViewRoot(),"supplierPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

			}
			return valid;		
		
		 }
	
		
		public boolean customerStep3Validation()
		{
			boolean valid = true;			
			String messageValue = null;
			try
			{
			
			
			}			
			catch(Exception e)
			{
				context.addMessage(
				UIComponent.findComponent(context.getViewRoot(),"supplierPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

			}
			return valid;		
		
		 }
	
		
		public String onFlowProcess(FlowEvent event) {

			SupplierBean supplierBean = (SupplierBean) BeanContext.getReference("supplierBean");			
			
			log.info("Current wizard step:" + event.getOldStep());  
	        log.info("Next step:" + event.getNewStep());
	       
			boolean valid = true;
			

			if (event.getOldStep().equalsIgnoreCase("step1")) {
				valid = supplierStep1Validation();
				if (valid) {				
					
					activeStep=event.getNewStep();
					
				} else {
					
					activeStep=event.getOldStep();
				}
			}

			else if (event.getOldStep().equalsIgnoreCase("step2")) {
				valid = this.supplierStep2Validation();
				if (valid) {					
					
										
					activeStep=event.getNewStep();
				}
				else
				{
					activeStep=event.getOldStep();
				}
			}
			
			
			else if (event.getOldStep().equalsIgnoreCase("step3")) {
				valid = this.customerStep3Validation();
				if (valid) {					
					
					
					activeStep=event.getNewStep();
				}
				else
				{
					activeStep=event.getOldStep();
				}
			}	
			
			return activeStep;
			
			      }
		
		
		
		
	
		
		

}
