package com.project.bean.admin;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.project.model.datamodel.RoleModel;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.home.ProjectHome;



public class RoleBeanInfo {

	public static Logger log = LoggerFactory.getLogger(RoleBeanInfo.class);
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");

	Configuration config = Configuration.getConfiguration();
	String projectHomefile = config.getValue(IConfiguration.PROJECT_HOME_FILENAME);
	
	public void saveRole() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage( UIComponent.findComponent(context.getViewRoot(), "addRole").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_ERROR,
						factoryBean.getMessageFactory().getMessage("addRole.label.save.success"), null));
	}
	
	
	public boolean validateRole(RoleModel roleInfo, List<RoleModel> rolesList, boolean isUpdate){
		boolean valid = true;
		if (factoryBean.checkIsNullAssignMessage(roleInfo.getRoleName(), "addRole.label.rolename", "roleName")) {
			valid = false;
		}
		
		if (factoryBean.checkIsNullAssignMessage(roleInfo.getRoleDescription(), "addRole.label.roledescription", "roleDescript")) {
			valid = false;
		}
		if(!valid){
			return valid;
		}
		
		if(rolesList == null || rolesList.size() == 0){
			return valid;
		}
		
		
		// Checking duplicate Role Names.
		for(RoleModel rm : rolesList){
			if(isUpdate){
				if(roleInfo.getRoleId() == rm.getRoleId()){
					continue;
				}
			}
			if(rm.getRoleName().trim().equalsIgnoreCase(roleInfo.getRoleName().trim())){
				factoryBean.alreadyExistError("addRole.label.rolename", "roleName");
				valid = false;
				break;
			}
		}
		
		
		return valid;
	}
	
	
	public void newRole() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/admin/addRole.xhtml");
		projectHome.setTitlePage("Roles --> Add/Edit Role");
		navHandler.handleNavigation(context, null, projectHomefile + "?faces-redirect=true");
	}

	
	public void editRole() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/admin/addRole.xhtml");
		projectHome.setTitlePage("Roles -->Add/Edit Role");
		navHandler.handleNavigation(context, null, projectHomefile+ "?faces-redirect=true");
	}

	
	public boolean isRoleAssigned(){
		boolean isAssigned = true;
	/*	try {
			RoleBean roleBean = (RoleBean) BeanContext.getReference("roleBean");
			UsersBean usersBean = (UsersBean) BeanContext.getReference("usersBean");
			UsersBO usersBO = usersBean.getUsersBO();
			if(roleBean.getSelectedRoleId() == 0){
				log.error("Cannot check the current role has access rights or not, SelectedRoleId is Null");
				return false;
			}
			isAssigned = usersBO.findRoleasignuser(roleBean.getSelectedRoleId());
		} catch (Exception e) {
			log.error("Exception @ isRoleAssigned() : "+e.getMessage());
		}*/
		return isAssigned;
	}
	

	public void updateRole() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(UIComponent.findComponent(context.getViewRoot(), "addRole").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_ERROR, factoryBean.getMessageFactory().getMessage(
								"addRole.label.Edit.success"), null));
	}

	
	public void addPrivileges() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/admin/rolePrivileges.xhtml");
		projectHome.setTitlePage("Roles -->Role Privileges");
		navHandler.handleNavigation(context, null, projectHomefile+ "?faces-redirect=true");
	}

	
	public void saveRolePrivileges() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage( UIComponent.findComponent(context.getViewRoot(), "addfunctionsRole").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_ERROR, factoryBean.getMessageFactory().getMessage(
								"addRoleFunction.label.save.success"), null));
	}

	
	public void cancel() {
		 goToRoleHomePage();
	}
	
	public void goToRoleHomePage(){
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/admin/Roleslist.xhtml");
		projectHome.setTitlePage("Admin --> Roles -->Search Role");
		navHandler.handleNavigation(context, null, projectHomefile+ "?faces-redirect=true");
	}

}
