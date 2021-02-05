package com.project.bean.admin;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.bo.interfaces.IBranchBO;
import com.project.bo.interfaces.IStaffBO;
import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.BranchstaffmemberModel;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.factory.ErrorFactory;
import com.project.common.util.EmailProcess;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.home.ProjectHome;
import com.project.login.LoginBean;

public class BranchStaffBeanInfo {
	
	BranchStaffBean branchStaffBean = (BranchStaffBean) BeanContext.getReference("branchStaffBean");
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	public static Logger log = LoggerFactory.getLogger(BranchBeanInfo.class);
	FacesContext context = FacesContext.getCurrentInstance();
	Configuration config = Configuration.getConfiguration();
	String projectHomefile = config.getValue(IConfiguration.PROJECT_HOME_FILENAME);
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	static ErrorFactory errorFactory = ErrorFactory.getInstance();
	
	public void listStaff() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/admin/branchStaff.xhtml");
		projectHome.setTitlePage("Admin --> Staff --> Add/Edit Staff");
		navHandler.handleNavigation(context, null, projectHomefile+ "?faces-redirect=true");
	}

	
	
	public void newStaff() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/admin/addEditBranchStaff.xhtml");
		projectHome.setTitlePage("Admin --> Staff --> Add/Edit Staff");
		navHandler.handleNavigation(context, null, projectHomefile+ "?faces-redirect=true");
	}
	
	public void mmyProfile() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/admin/addEditmyProfile.xhtml");
		projectHome.setTitlePage("My Account --> Change MyProfile");
		navHandler.handleNavigation(context, null, projectHomefile+ "?faces-redirect=true");
	}
	
	

	
	
	public void createNewStaff()
	{	
		branchStaffBean.getBranchstaff().setBranchId(loginBean.getBranch().getBranchId());
		BranchstaffmemberModel branchstaff=branchStaffBean.getBranchstaff();			
		branchstaff.setPassword(config.getValue(IConfiguration.PROJECT_DEFAULT_PASSWORD));
		branchstaff.setThemeName(config.getValue(IConfiguration.PROJECT_THEMENAME));
		if(validateBranchStaff())
		{	
			boolean saveSuccess = false;			
			try
			{			
			IStaffBO staffBO=branchStaffBean.getStaffBO();
			saveSuccess=staffBO.createNewStaff(branchstaff);
			
			if (saveSuccess) {
				EmailProcess emailProcess = new EmailProcess();
				emailProcess.sendEmailToNewUser(branchstaff.getStaffCode() , branchstaff.getEmailAddress(), config.getValue(IConfiguration.PROJECT_DEFAULT_PASSWORD));				
				
				branchStaffBean.resetSearchBranchStaff();
				listStaff();
				CommonListBeanInfo.getStaffList();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"branchStaffPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("branchstaff.label.save.success"),null));				
			}			
			}
			catch(Exception e)
			{
				context.addMessage(
				UIComponent.findComponent(context.getViewRoot(),"branchStaffPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), factoryBean.getMessageFactory().getMessage("branch.label.save.failed")));
			}
		}
		
	}
	
	
	public void editBranchStaff(Integer staffId)
	{
		IStaffBO staffBO=branchStaffBean.getStaffBO();
		try {
			branchStaffBean.setBranchstaff(staffBO.getBranchstaffmemberDetails(staffId));
			branchStaffBean.setAction("update");
			newStaff();
		} catch (Exception e) {
			context.addMessage(
			UIComponent.findComponent(context.getViewRoot(),"branchStaffPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			e.printStackTrace();
		}
	}
	
	public void editMyProfile(Integer staffId)
	{
		IStaffBO staffBO=branchStaffBean.getStaffBO();
		try {
			branchStaffBean.setBranchstaff(staffBO.getBranchstaffmemberDetails(staffId));
			branchStaffBean.setAction("update");
			mmyProfile();
		} catch (Exception e) {
			context.addMessage(
			UIComponent.findComponent(context.getViewRoot(),"branchStaffPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			e.printStackTrace();
		}
	}
	
	
	public void updateBranchStaff()
	{
		boolean saveSuccess = false;
		if(validateBranchStaff())
		{	
		try
		{
		BranchstaffmemberModel branchstaff=branchStaffBean.getBranchstaff();
		
		IStaffBO staffBO=branchStaffBean.getStaffBO();
		saveSuccess=staffBO.updateStaff(branchstaff);		
		if (saveSuccess) {					
			branchStaffBean.resetSearchBranchStaff();
			listStaff();
			CommonListBeanInfo.getStaffList();
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"branchStaffPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("branchstaff.label.update.success"),null));			
		}		
		}
		catch(Exception e)
		{
			context.addMessage(
			UIComponent.findComponent(context.getViewRoot(),"branchStaffPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), factoryBean.getMessageFactory().getMessage("branch.label.update.failed")));
		}
		}
	
	}
	
	public void updateMyProfileStaff()
	{
		boolean saveSuccess = false;
		if(validateBranchStaff())
		{	
		try
		{
		BranchstaffmemberModel branchstaff=branchStaffBean.getBranchstaff();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");		
		IStaffBO staffBO=branchStaffBean.getStaffBO();
		saveSuccess=staffBO.updateStaff(branchstaff);		
		if (saveSuccess) {					
			projectHome.userHome();
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"branchStaffPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("branchstaff.label.update.success"),null));			
		}		
		}
		catch(Exception e)
		{
			context.addMessage(
			UIComponent.findComponent(context.getViewRoot(),"branchStaffPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), factoryBean.getMessageFactory().getMessage("branch.label.update.failed")));
		}
		}
	
	}
	
	
	public void deleteBranchStaff()
	{
		branchStaffBean.searchBranchStaff();
	}
	
	public void resetBranchStaff()
	{
		BranchstaffmemberModel branchstaff= new BranchstaffmemberModel();
		branchStaffBean.setBranchstaff(branchstaff);
		branchStaffBean.setAction("submit");
	}
	
	
	 /* Start Branch  Validation */
	
		public boolean validateBranchStaff() {
			boolean valid = true;		
			IStaffBO staffBO=branchStaffBean.getStaffBO();
			String messageValue = null;
			try
			{
			if (factoryBean.checkIsNullAssignMessage(branchStaffBean.getBranchstaff().getBranchId(),
					"branchstaff.label.branchName", "branchName")) {
				valid = false;
			}
			
			if (factoryBean.checkIsZeroAssignMessage(
					String.valueOf(branchStaffBean.getBranchstaff().getBranchId()),
					"branchstaff.label.branchName", "branchName")) {
				valid = false;
			}
			
			if (factoryBean.checkIsNullAssignMessage(branchStaffBean.getBranchstaff().getIdDepartment(),
					"branchstaff.label.departmentName", "departmentName")) {
				valid = false;
			}
			
			if (factoryBean.checkIsZeroAssignMessage(
					String.valueOf(branchStaffBean.getBranchstaff().getIdDepartment()),
					"branchstaff.label.departmentName", "departmentName")) {
				valid = false;
			}
			
			if (factoryBean.checkIsNullAssignMessage(branchStaffBean.getBranchstaff().getFirstName(),
					"branchstaff.label.firstName", "firstName")) {
				valid = false;
			}
			
			if (factoryBean.checkIsNullAssignMessage(branchStaffBean.getBranchstaff().getIdentificationNumber(),
					"branchstaff.label.identificationNumber", "identificationNumber")) {
				valid = false;
			}
			
			
			
			else {
				if (branchStaffBean.getAction().equalsIgnoreCase("submit")) {
					if (staffBO.findStaffIcExites(branchStaffBean.getBranchstaff().getIdentificationNumber())) {
						messageValue = factoryBean.getErrorFactory().getError("branchstaff.errors.identificationNumber.exists");
						factoryBean.reportErrorToMessageHandler(messageValue,messageValue, "identificationNumber");
						valid = false;
					}
				}else{  
					
					if(! branchStaffBean.getBranchstaff().getIdentificationNumber().equalsIgnoreCase(branchStaffBean.getBranchstaff().getIdentificationOldNumber()) ){
											
						if (staffBO.findStaffIcExites(branchStaffBean.getBranchstaff().getIdentificationNumber())) {
							messageValue = factoryBean.getErrorFactory().getError("branchstaff.errors.identificationNumber.exists");
							factoryBean.reportErrorToMessageHandler(messageValue,messageValue, "identificationNumber");
							valid = false;
						}
					}
				}
			}
		
			
			
			
			if (factoryBean.checkIsNullAssignMessage(branchStaffBean.getBranchstaff().getEmailAddress(),
					"branchstaff.label.emailAddress", "emailAddress")) {
				valid = false;
			}
			

			else {
				if (branchStaffBean.getAction().equalsIgnoreCase("submit")) {
					if (staffBO.findStaffEmailExites(branchStaffBean.getBranchstaff().getEmailAddress())) {
						messageValue = factoryBean.getErrorFactory().getError("branchstaff.errors.email.exists");
						factoryBean.reportErrorToMessageHandler(messageValue,messageValue, "emailAddress");
						valid = false;
					}
				}else{  
					
					if(! branchStaffBean.getBranchstaff().getEmailAddress().equalsIgnoreCase(branchStaffBean.getBranchstaff().getEmailOldAddress()) ){
						
						if (staffBO.findStaffEmailExites(branchStaffBean.getBranchstaff().getEmailAddress())) {
							messageValue = factoryBean.getErrorFactory().getError("branchstaff.errors.email.exists");
							factoryBean.reportErrorToMessageHandler(messageValue,messageValue, "emailAddress");
							valid = false;
						}
					}
				}
			}
			
		
			
			if (factoryBean.checkIsNullAssignMessage(branchStaffBean.getBranchstaff().getRoleId(),
					"branchstaff.label.roleName", "roleName")) {
				valid = false;
			}
					
			
			if (factoryBean.checkIsZeroAssignMessage(
					String.valueOf(branchStaffBean.getBranchstaff().getRoleId()),
					"branchstaff.label.roleName", "roleName")) {
				valid = false;
			}
			
			
			if (factoryBean.checkIsNullAssignMessage(branchStaffBean.getBranchstaff().getStatus(),
					"branchstaff.label.status", "status")) {
				valid = false;
			}
			if (factoryBean.checkIsZeroAssignMessage(
					String.valueOf(branchStaffBean.getBranchstaff().getStatus()),
					"branchstaff.label.status", "status")) {
				valid = false;
			}
			
			
			
			}
			
			catch(Exception e)
			{
				context.addMessage(
				UIComponent.findComponent(context.getViewRoot(),"branchStaffPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

			}
			return valid;

		}

		/* End Department Process Validation */

		
}
