package com.project.bean.admin;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.bo.interfaces.ICommonListBO;
import com.project.bo.interfaces.IStaffBO;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.factory.ErrorFactory;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.home.ProjectHome;
import com.project.login.LoginBean;
import com.project.model.datamodel.TaxmasterModel;


public class TaxBeanInfo {

	
	TaxBean taxBean = (TaxBean) BeanContext.getReference("taxBean");
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	public static Logger log = LoggerFactory.getLogger(TaxBeanInfo.class);
	FacesContext context = FacesContext.getCurrentInstance();
	Configuration config = Configuration.getConfiguration();
	String projectHomefile = config.getValue(IConfiguration.PROJECT_HOME_FILENAME);
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	static ErrorFactory errorFactory = ErrorFactory.getInstance();
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	
	
	public void listTax() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/admin/taxmaster.xhtml");
		projectHome.setTitlePage("Admin --> Tax -->Search Tax");
		navHandler.handleNavigation(context, null, projectHomefile+ "?faces-redirect=true");
	}

	
	
	public void newTax() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/admin/addtax.xhtml");
		projectHome.setTitlePage("Admin --> Tax -->Add/Edit Tax");
		navHandler.handleNavigation(context, null, projectHomefile+ "?faces-redirect=true");
	}
	
	
	public void saveTax()
	{	
		
			boolean saveSuccess = false;			
			try
			{
			TaxmasterModel tax = taxBean.getTaxObj();			
			ICommonListBO commonListBO =objectMapController.getCommonListBO();			
			commonListBO.createTaxmaster(tax);
				
				taxBean.getBranchTaxList();	
				taxBean.listTax();
				taxBean.resetTax();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"taxPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("tax.label.save.success"),null));				
		
			
			}
			catch(Exception e)
			{
				context.addMessage(
				UIComponent.findComponent(context.getViewRoot(),"taxPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), factoryBean.getMessageFactory().getMessage("tax.label.save.failed")));

			}
				
	}
	
	
	
	public void editTax(Integer taxid)
	{
		ICommonListBO commonListBO =objectMapController.getCommonListBO();	
		try {			
			taxBean.setTaxObj(commonListBO.getTaxmasterDetail(taxid));					
			taxBean.setAction("update");				
		} catch (Exception e) {
			context.addMessage(
			UIComponent.findComponent(context.getViewRoot(),"taxPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			e.printStackTrace();
		}
	}
	
	
	
	public void updateTax()
	{
		boolean saveSuccess = false;
		
		try
		{
			TaxmasterModel tax = taxBean.getTaxObj();			
			ICommonListBO commonListBO =objectMapController.getCommonListBO();				
			commonListBO.updateTaxmaster(tax);
			
			taxBean.getBranchTaxList();	
			taxBean.listTax();
			taxBean.resetTax();
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"taxPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("tax.label.update.success"),null));	
		
		}
		catch(Exception e)
		{
			context.addMessage(
			UIComponent.findComponent(context.getViewRoot(),"taxPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), factoryBean.getMessageFactory().getMessage("tax.label.update.failed")));

		}
		
	
	}
	
	
	
	public boolean validateTax() {
		boolean valid = true;				
		String messageValue=null;
		IStaffBO staffBO=objectMapController.getStaffBO();
		try
		{			
		if (factoryBean.checkIsNullAssignMessage(taxBean.getTaxObj().getTaxname(),"tax.label.taxname", "taxname")) {
			valid = false;
		}	
		if (factoryBean.checkIsNullAssignMessage(taxBean.getTaxObj().getTaxvalue(),"tax.label.taxvalue", "taxvalue")) {
			valid = false;
		}			
		}
		catch(Exception e)
		{
			context.addMessage(
			UIComponent.findComponent(context.getViewRoot(),"taxPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

		}			
		return valid;

	}

	
}
