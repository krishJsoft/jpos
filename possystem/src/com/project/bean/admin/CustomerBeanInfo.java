package com.project.bean.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.TabChangeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.bo.interfaces.IBranchBO;
import com.project.bo.interfaces.ICustomerBO;
import com.project.bo.interfaces.IStaffBO;
import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.CustomerModel;
import com.project.model.datamodel.SupplierdocumentModel;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.factory.ErrorFactory;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.home.ProjectHome;
import com.project.login.LoginBean;

public class CustomerBeanInfo {
	
	
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	CustomerBean customerBean = (CustomerBean) BeanContext.getReference("customerBean");
	public static Logger log = LoggerFactory.getLogger(BranchBeanInfo.class);
	FacesContext context = FacesContext.getCurrentInstance();
	Configuration config = Configuration.getConfiguration();
	String projectHome = config.getValue(IConfiguration.PROJECT_HOME_FILENAME);
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	static ErrorFactory errorFactory = ErrorFactory.getInstance();

	
	
	
	public void listCustomer() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/admin/customer.xhtml");
		projectHome.setTitlePage("Admin --> Customers -->Search Customer");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}

	
	
	public void newCustomer() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/admin/addEditCustomer.xhtml");
		projectHome.setTitlePage("Admin --> Customers -->Add/Edit Customer");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
	
	
	
	public void saveCustomer()
	{	
		if(customerStep1Validation() && customerStep2Validation())
		{	
			

			boolean saveSuccess = false;			
			try
			{
			CustomerModel customer=customerBean.getCustomer();
			customer.setCreatedBy(loginBean.getLoginName());
			customer.setCreatedDate(new Date());
			customer.setLastModifiedDate(new Date());			

			ICustomerBO customerBO=customerBean.getCustomerBO();
			saveSuccess=customerBO.createNewCustomer(customer);
			
			if (saveSuccess) {					
				customerBean.resetStep1();
				customerBean.resetStep2();
				customerBean.resetSearchCustomer();
				listCustomer();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"customerPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("customer.label.save.success"),null));				
			}
			
			}
			catch(Exception e)
			{
				context.addMessage(
				UIComponent.findComponent(context.getViewRoot(),"customerPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), factoryBean.getMessageFactory().getMessage("customer.label.save.failed")));

			}
		}
		
	}
	
	
	public void editCustomer(Integer customerId)
	{
		ICustomerBO customerBO=customerBean.getCustomerBO();
		try {
			customerBean.setCustomer(customerBO.getCustomerDetails(customerId));			
			customerBean.setAction("update");
			newCustomer();
		} catch (Exception e) {
			context.addMessage(
			UIComponent.findComponent(context.getViewRoot(),"customerPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			e.printStackTrace();
		}
	}
	public void updateCustomer()
	{
		boolean saveSuccess = false;
		if(customerStep1Validation() && customerStep2Validation())
		{	
		try
		{
		CustomerModel customer=customerBean.getCustomer();		
		customer.setCreatedBy(loginBean.getLoginName());		
		customer.setLastModifiedDate(new Date());			
		
		ICustomerBO customerBO=customerBean.getCustomerBO();
		saveSuccess=customerBO.updateCustomer(customer);
		
		if (saveSuccess) {					
			customerBean.resetSearchCustomer();
			customerBean.resetStep1();
			customerBean.resetStep2();
			listCustomer();
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"customerPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("customer.label.update.success"),null));
			
		}
		
		}
		catch(Exception e)
		{
			context.addMessage(
			UIComponent.findComponent(context.getViewRoot(),"customerPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), factoryBean.getMessageFactory().getMessage("customer.label.update.failed")));

		}
		}
	
	}
	public void deleteCustomer()
	{
		customerBean.searchCustomer();
	}
	
	public void resetCustomer()
	{
		CustomerModel customer=customerBean.getCustomer();			
		//customerBean.setCustomer(customer);
		customerBean.resetStep1();
		customerBean.resetStep2();
		customerBean.setAction("submit");
	}
	
	
	
	
	
	public void onTabChange(TabChangeEvent event) {

		

		customerBean.setActiveIndex(customerBean.getActiveIndex());
		if (customerBean.getActiveIndex() == 0) {
			customerBean.setPreviousActionStatus(true);
			customerBean.setNextActionStatus(false);

		}

		if (!customerStep1Validation()) {
			customerBean.setActiveIndex(0);
		} else if (!customerStep1Validation()) {
			customerBean.setActiveIndex(1);
		}
		else if (!customerStep1Validation()) {
			customerBean.setActiveIndex(2);
		}
	}
	

		public boolean customerStep1Validation()
		{
			boolean valid = true;			
			String messageValue = null;
			ICustomerBO customerBO=null;
			try
			{
				customerBO=customerBean.getCustomerBO();
				if (factoryBean.checkIsNullAssignMessage(customerBean.getCustomer().getTitle(),
						"customer.label.title", "title")) {
					valid = false;
				}
				
				if (factoryBean.checkIsZeroAssignMessage(
						String.valueOf(customerBean.getCustomer().getTitle()),
						"customer.label.title", "title")) {
					valid = false;
				}
				
				if (factoryBean.checkIsNullAssignMessage(customerBean.getCustomer().getCustomerName(),
						"customer.label.customerName", "customerName")) {
					valid = false;
				}
				
				if (factoryBean.checkIsNullAssignMessage(customerBean.getCustomer().getLoyaltyCardCode(),
						"customer.label.loyaltyCardCode", "loyaltyCardCode")) {
					valid = false;
				}
			
				/*if (factoryBean.checkIsNullAssignMessage(customerBean.getCustomer().getPhoneNo(),
						"customer.label.phoneNo", "phoneNo")) {
					valid = false;
				}
			
				
				else {
					if (customerBean.getAction().equalsIgnoreCase("submit")) {
						if (customerBO.findCustomerIcExites(customerBean.getCustomer().getIdentificationCompanyRegNo())) {
							messageValue = factoryBean.getErrorFactory().getError("branchstaff.errors.identificationNumber.exists");
							factoryBean.reportErrorToMessageHandler(messageValue,messageValue, "identificationCompanyRegNo");
							valid = false;
						}
					}else{  
						
						if(! customerBean.getCustomer().getIdentificationCompanyRegNo().equalsIgnoreCase(customerBean.getCustomer().getIdentificationCompanyOldRegNo()) ){
													
							if (customerBO.findCustomerIcExites(customerBean.getCustomer().getIdentificationCompanyRegNo())) {
								messageValue = factoryBean.getErrorFactory().getError("branchstaff.errors.identificationNumber.exists");
								factoryBean.reportErrorToMessageHandler(messageValue,messageValue, "identificationCompanyRegNo");
								valid = false;
							}
						}
					}
				}*/
			
				
				
				
			}			
			catch(Exception e)
			{
				context.addMessage(
				UIComponent.findComponent(context.getViewRoot(),"customerPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

			}
			return valid;		
		
		 }
	
		
		public boolean customerStep2Validation()
		{
			boolean valid = true;			
			String messageValue = null;
			try
			{
			
			
			}			
			catch(Exception e)
			{
				context.addMessage(
				UIComponent.findComponent(context.getViewRoot(),"customerPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

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
				UIComponent.findComponent(context.getViewRoot(),"customerPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

			}
			return valid;		
		
		 }
	
		
		public String onFlowProcess(FlowEvent event) {

			CustomerBean customerBean = (CustomerBean) BeanContext.getReference("customerBean");			
			
			log.info("Current wizard step:" + event.getOldStep());  
	        log.info("Next step:" + event.getNewStep());
	       
			boolean valid = true;
			String activeStep="";

			if (event.getOldStep().equalsIgnoreCase("step1")) {
				valid = customerStep1Validation();
				if (valid) {				
					
					activeStep=event.getNewStep();
					
				} else {
					
					activeStep=event.getOldStep();
				}
			}

			else if (event.getOldStep().equalsIgnoreCase("step2")) {
				valid = this.customerStep2Validation();
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
