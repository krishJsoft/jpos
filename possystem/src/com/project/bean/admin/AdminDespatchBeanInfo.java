package com.project.bean.admin;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.bo.interfaces.IAdminDespatchBO;
import com.project.bo.interfaces.IProductBO;
import com.project.model.datamodel.AdmindespatchModel;
import com.project.model.datamodel.ProductModel;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.factory.ErrorFactory;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.home.ProjectHome;
import com.project.login.LoginBean;

public class AdminDespatchBeanInfo {

	
	AdminDespatchBean adminDespatchBean = (AdminDespatchBean) BeanContext.getReference("adminDespatchBean");
	ProductCategoryBean productCategoryBean = (ProductCategoryBean) BeanContext.getReference("productCategoryBean");
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	public static Logger log = LoggerFactory.getLogger(BranchBeanInfo.class);
	FacesContext context = FacesContext.getCurrentInstance();
	Configuration config = Configuration.getConfiguration();
	String projectHome = config.getValue(IConfiguration.PROJECT_HOME_FILENAME);
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	static ErrorFactory errorFactory = ErrorFactory.getInstance();
	
	public void listDespatch() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/admin/adminDespatch.xhtml");
		projectHome.setTitlePage("Admin --> Despatches --> Search Despatch");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}

	
	
	public void newDespatch() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		productCategoryBean.getDefaultRootNode();
		projectHome.setContentpage("/admin/addEditAdminDespatch.xhtml");
		projectHome.setTitlePage("Admin --> Despatches --> Add/Edit Despatch");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
	
	public void saveDespatch()
	{	
		if(validateDespatch())
		{	
			boolean saveSuccess = false;			
			try
			{
			AdmindespatchModel despatch=adminDespatchBean.getAdespatch();					
			despatch.setBranchId(loginBean.getBranch().getBranchId());
			IAdminDespatchBO adminDespatchBO=adminDespatchBean.getAdminDespatchBO();
			saveSuccess=adminDespatchBO.createNewAdminDespatch(despatch);
			
			if (saveSuccess) {					
				adminDespatchBean.resetSearchDespatch();
				adminDespatchBean.listDespatch();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"despatchPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("adespatch.label.save.success"),null));				
			}
			
			}
			catch(Exception e)
			{
				context.addMessage(
				UIComponent.findComponent(context.getViewRoot(),"despatchPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), factoryBean.getMessageFactory().getMessage("product.label.save.failed")));

			}
		}
		
	}
	
	
	public void editDespatch(Integer despatchId)
	{
		IAdminDespatchBO adminDespatchBO=adminDespatchBean.getAdminDespatchBO();
		try {
			adminDespatchBean.setAdespatch(adminDespatchBO.getAdminDespatchDetails(despatchId));
			adminDespatchBean.setAction("update");
			newDespatch();
		} catch (Exception e) {
			context.addMessage(
			UIComponent.findComponent(context.getViewRoot(),"despatchPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			e.printStackTrace();
		}
	}
	public void updateDespatch()
	{
		boolean saveSuccess = false;
		if(validateDespatch())
		{	
		try
		{		
		AdmindespatchModel despatch=adminDespatchBean.getAdespatch();		
		IAdminDespatchBO adminDespatchBO=adminDespatchBean.getAdminDespatchBO();
		saveSuccess=adminDespatchBO.updateAdminDespatch(despatch);
		
		if (saveSuccess) {					
			adminDespatchBean.resetSearchDespatch();
			adminDespatchBean.listDespatch();
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"despatchPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("adespatch.label.update.success"),null));
			
		}
		
		}
		catch(Exception e)
		{
			context.addMessage(
			UIComponent.findComponent(context.getViewRoot(),"despatchPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), factoryBean.getMessageFactory().getMessage("product.label.update.failed")));

		}
		}
	
	}
	public void deleteDespatch()
	{
		adminDespatchBean.searchDespatch();
	}
	
	public void resetDespatch()
	{
		AdmindespatchModel despatch= new AdmindespatchModel();
		adminDespatchBean.setAdespatch(despatch);
		adminDespatchBean.setAction("submit");
	}
	
	 /* Start   validateDespatch */
	
	public boolean validateDespatch() {
		boolean valid = true;		
		IAdminDespatchBO adminDespatchBO=adminDespatchBean.getAdminDespatchBO();
		String messageValue=null;
		
		try
		{
		
		if (factoryBean.checkIsNullAssignMessage(adminDespatchBean.getAdespatch().getUnitPrice(),
					"adespatch.label.unitPrice", "unitPrice")) {
				valid = false;
			}
			
		if (factoryBean.checkIsZeroAssignMessage(""+adminDespatchBean.getAdespatch().getUnitPrice(),"adespatch.label.unitPrice", "unitPrice")) {
			valid = false;
		}
		if (factoryBean.checkIsNullAssignMessage(adminDespatchBean.getAdespatch().getDespatchType(),
				"adespatch.label.despatchType", "despatchType")) {
			valid = false;
		}
		if (factoryBean.checkIsNullAssignMessage(adminDespatchBean.getAdespatch().getSpecification(),
				"adespatch.label.specification", "specification")) {
			valid = false;
		}
		
		/*if (factoryBean.checkIsNullAssignMessage(adminDespatchBean.getAdespatch().getUom(),
				"adespatch.label.uom", "uom")) {
			valid = false;
		}*/
		
		else {
			if (adminDespatchBean.getAction().equalsIgnoreCase("submit")) {
				if (adminDespatchBO.findAdminDespatchExites(adminDespatchBean.getAdespatch().getDespatchType(),adminDespatchBean.getAdespatch().getUom(),loginBean.getBranch().getBranchId())) {
					messageValue = factoryBean.getErrorFactory().getError("adespatch.errors.uom.exists");
					factoryBean.reportErrorToMessageHandler(messageValue,messageValue, "despatchType");
					valid = false;
				}
			}else{  
				
				if(! adminDespatchBean.getAdespatch().getDespatchType().equalsIgnoreCase(adminDespatchBean.getAdespatch().getDespatchOldType()) && adminDespatchBean.getAdespatch().getUom().equalsIgnoreCase(adminDespatchBean.getAdespatch().getUomold()) ){
										
					if (adminDespatchBO.findAdminDespatchExites(adminDespatchBean.getAdespatch().getDespatchType(),adminDespatchBean.getAdespatch().getUom(),loginBean.getBranch().getBranchId())) {
						messageValue = factoryBean.getErrorFactory().getError("adespatch.errors.uom.exists");
						factoryBean.reportErrorToMessageHandler(messageValue,messageValue, "despatchType");
						valid = false;
					}
				}
			}
		}
		
		
		
		
		}
		catch(Exception e)
		{
			context.addMessage(
			UIComponent.findComponent(context.getViewRoot(),"despatchPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

		}
		
		
		return valid;

	}

	
	
	
}
