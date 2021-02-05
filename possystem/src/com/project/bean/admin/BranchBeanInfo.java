package com.project.bean.admin;

import java.math.BigDecimal;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.bo.interfaces.IBranchBO;
import com.project.bo.interfaces.IStaffBO;
import com.project.model.datamodel.BranchModel;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.factory.ErrorFactory;
import com.project.common.util.CommonUtil;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.home.ProjectHome;
import com.project.login.LoginBean;


public class BranchBeanInfo {

	BranchBean branchBean = (BranchBean) BeanContext.getReference("branchBean");
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	public static Logger log = LoggerFactory.getLogger(BranchBeanInfo.class);
	FacesContext context = FacesContext.getCurrentInstance();
	Configuration config = Configuration.getConfiguration();
	String projectHomefile = config.getValue(IConfiguration.PROJECT_HOME_FILENAME);
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	static ErrorFactory errorFactory = ErrorFactory.getInstance();
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	
	public void listBranch() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/admin/branches.xhtml");
		projectHome.setTitlePage("Admin --> Branch -->Search Branches");
		navHandler.handleNavigation(context, null, projectHomefile+ "?faces-redirect=true");
	}

	
	
	public void newBranch() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/admin/addEditBranch.xhtml");
		projectHome.setTitlePage("Admin --> Branches -->Add/Edit Branch");
		navHandler.handleNavigation(context, null, projectHomefile+ "?faces-redirect=true");
	}
	
	
	
	public void saveBranch()
	{	
		if(validateBranch())
		{	
			boolean saveSuccess = false;			
			try
			{
			BranchModel branch=branchBean.getBranch();
			branch.setIsHQ("0");
			branch.setCreatedBy(loginBean.getLoginName());
			branch.setCreatedDate(new Date());
			branch.setLastModifiedDate(new Date());			
			branch.setHdbranchId(loginBean.getBranch().getBranchId());
			branch.setCustomersalesPoint(loginBean.getBranch().getCustomersalesPoint());
			branch.setCustomersalesValue(loginBean.getBranch().getCustomersalesValue());
			branch.setRedemPoint(loginBean.getBranch().getRedemPoint());
			branch.setRedemValue(loginBean.getBranch().getRedemValue());
			
			IBranchBO branchBO=branchBean.getBranchBO();
			IStaffBO staffBO=objectMapController.getStaffBO();
			saveSuccess=branchBO.createNewBranch(branch);
			
			if (saveSuccess) {					
				branchBean.resetSearchBranch();
				listBranch();
				CommonListBeanInfo.getAllBranchtList();
				CommonListBeanInfo.getAllBrancht();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"branchPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("branch.label.save.success"),null));				
			}
			
			}
			catch(Exception e)
			{
				context.addMessage(
				UIComponent.findComponent(context.getViewRoot(),"branchPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), factoryBean.getMessageFactory().getMessage("branch.label.save.failed")));

			}
		}
		
	}
	
	
	public void editBranch(Integer branchId)
	{
		IBranchBO branchBO=branchBean.getBranchBO();
		try {
			branchBean.setBranch(branchBO.getBranchDetails(branchId));
			branchBean.setAction("update");
			newBranch();
		} catch (Exception e) {
			context.addMessage(
			UIComponent.findComponent(context.getViewRoot(),"branchPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			e.printStackTrace();
		}
	}
	public void updateBranch()
	{
		boolean saveSuccess = false;
		if(validateBranch())
		{	
		try
		{
		BranchModel branch=branchBean.getBranch();
		branch.setCreatedBy(loginBean.getLoginName());		
		branch.setLastModifiedDate(new Date());			
		
		IBranchBO branchBO=branchBean.getBranchBO();
		saveSuccess=branchBO.updateBranch(branch);
		
		if (saveSuccess) {					
			branchBean.resetSearchBranch();
			listBranch();
			CommonListBeanInfo.getAllBranchtList();
			CommonListBeanInfo.getAllBrancht();
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"branchPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("branch.label.update.success"),null));
			
		}
		
		}
		catch(Exception e)
		{
			context.addMessage(
			UIComponent.findComponent(context.getViewRoot(),"branchPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), factoryBean.getMessageFactory().getMessage("branch.label.update.failed")));

		}
		}
	
	}
	
	
	public void updateOranization()
	{
		boolean saveSuccess = false;
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");	
		
		try
		{
		BranchModel branch=branchBean.getBranch();
		branch.setCreatedBy(loginBean.getLoginName());		
		branch.setLastModifiedDate(new Date());			
		
		IBranchBO branchBO=branchBean.getBranchBO();
		saveSuccess=branchBO.updateBranch(branch);
		
		if (saveSuccess) {					
			CommonListBeanInfo.getAllBranchtList();
			CommonListBeanInfo.getAllBrancht();
			
			loginBean.setBranch(branch);
			
			projectHome.userHome();
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"branchPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("branch.label.update.success"),null));
			
		}
		
		}
		catch(Exception e)
		{
			context.addMessage(
			UIComponent.findComponent(context.getViewRoot(),"branchPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), factoryBean.getMessageFactory().getMessage("branch.label.update.failed")));

		}
		
	
	}
	
	
	
	
	public void deleteBranch(Integer branchId)
	{
		IBranchBO branchBO=branchBean.getBranchBO();
		try
		{
			branchBO.deleteBranch(branchBO.getBranchDetails(branchId));	
		}
		catch(Exception e)
		{
		context.addMessage(
		UIComponent.findComponent(context.getViewRoot(),"branchPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), factoryBean.getMessageFactory().getMessage("branch.label.update.failed")));
	
		}
		
		
	}
	
	public void resetBranch()
	{
		BranchModel branch= new BranchModel();
		branchBean.setBranch(branch);
		branchBean.setAction("submit");
	}
	
	
	
	 /* Start Branch  Validation */
	
	public boolean validateBranch() {
		boolean valid = true;		
		IBranchBO branchBO=branchBean.getBranchBO();
		String messageValue=null;
		IStaffBO staffBO=objectMapController.getStaffBO();
		try
		{
		
		if (factoryBean.checkIsNullAssignMessage(branchBean.getBranch().getBranchName(),
				"branch.label.branchName", "branchName")) {
			valid = false;
		}
		
		
		else {
			if (branchBean.getAction().equalsIgnoreCase("submit")) {
				if (branchBO.findBranchNameExites(branchBean.getBranch().getBranchName())) {
					messageValue = factoryBean.getErrorFactory().getError("branch.errors.branchName.exists");
					factoryBean.reportErrorToMessageHandler("",messageValue, "branchName");
					valid = false;
				}
			}else{  
				
				if(! branchBean.getBranch().getBranchName().equalsIgnoreCase(branchBean.getBranch().getBranchOldName()) ){
					
					
					if (branchBO.findBranchNameExites(branchBean.getBranch().getBranchName())) {
						messageValue = factoryBean.getErrorFactory().getError("branch.errors.branchName.exists");
						factoryBean.reportErrorToMessageHandler(messageValue,messageValue, "branchName");
						valid = false;
					}
				}
			}
		}
		
		
		if (factoryBean.checkIsNullAssignMessage(branchBean.getBranch().getBranchCode(),
				"branch.label.branchCode", "branchCode")) {
			valid = false;
		}		
		
		
		
		else {
			if (branchBean.getAction().equalsIgnoreCase("submit")) {
				if (branchBO.findBranchCodeExites(branchBean.getBranch().getBranchCode())) {
					messageValue = factoryBean.getErrorFactory().getError("branch.errors.branchCode.exists");
					factoryBean.reportErrorToMessageHandler("",messageValue, "branchCode");
					valid = false;
				}
			}else{  
				
				if(! branchBean.getBranch().getBranchCode().equalsIgnoreCase(branchBean.getBranch().getBranchOldCode()) ){
					
					
					if (branchBO.findBranchCodeExites(branchBean.getBranch().getBranchCode())) {
						messageValue = factoryBean.getErrorFactory().getError("branch.errors.branchCode.exists");
						factoryBean.reportErrorToMessageHandler(messageValue,messageValue, "branchCode");
						valid = false;
					}
				}
			}
		}
		
		
		
		if (factoryBean.checkIsNullAssignMessage(branchBean.getBranch().getAddress(),
				"branch.label.address", "address")) {
			valid = false;
		}
		
		if (factoryBean.checkIsNullAssignMessage(branchBean.getBranch().getPostCode(),
				"branch.label.postCode", "postCode")) {
			valid = false;
		}
		
		if (factoryBean.checkIsNullAssignMessage(branchBean.getBranch().getPhoneNo(),
				"branch.label.phoneNo", "phoneNo")) {
			valid = false;
		}
		
		if (factoryBean.checkIsNullAssignMessage(branchBean.getBranch().getEmailAddress(),
				"branch.label.emailAddress", "emailAddress")) {
			valid = false;
		}
			

		else {
			if (branchBean.getAction().equalsIgnoreCase("submit")) {
				if (staffBO.findStaffEmailExites(branchBean.getBranch().getEmailAddress())) {
					messageValue = factoryBean.getErrorFactory().getError("branchstaff.errors.email.exists");
					factoryBean.reportErrorToMessageHandler(messageValue,messageValue, "emailAddress");
					valid = false;
				}
			}else{  
				
				if(! branchBean.getBranch().getEmailAddress().equalsIgnoreCase(branchBean.getBranch().getEmailOldAddress()) ){
					
					if (staffBO.findStaffEmailExites(branchBean.getBranch().getEmailAddress())) {
						messageValue = factoryBean.getErrorFactory().getError("branchstaff.errors.email.exists");
						factoryBean.reportErrorToMessageHandler(messageValue,messageValue, "emailAddress");
						valid = false;
					}
				}
			}
		}
		
		
		
		}
		catch(Exception e)
		{
			context.addMessage(
			UIComponent.findComponent(context.getViewRoot(),"branchPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

		}
		
		
		return valid;

	}

	/* End Department Process Validation */

	
}
