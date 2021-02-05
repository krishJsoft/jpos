package com.project.bean.admin;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.factory.ErrorFactory;
import com.project.common.validation.CommonFactoryBean;
import com.project.home.ProjectHome;
import com.project.login.LoginBean;

public class DepartmentBeanInfo {

	
	DepartmentBean departmentBean = (DepartmentBean) BeanContext.getReference("departmentBean");
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	public static Logger log = LoggerFactory.getLogger(DepartmentBeanInfo.class);
	FacesContext context = FacesContext.getCurrentInstance();
	Configuration config = Configuration.getConfiguration();
	String projectHomefile = config.getValue(IConfiguration.PROJECT_HOME_FILENAME);
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	static ErrorFactory errorFactory = ErrorFactory.getInstance();
	
	public void listDepartment() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/admin/department.xhtml");
		projectHome.setTitlePage("Admin --> Department -->Search Department");
		navHandler.handleNavigation(context, null, projectHomefile+ "?faces-redirect=true");
	}

	
	
	public void newDepartment() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");		
		projectHome.setContentpage("/admin/addEditDepartment.xhtml");
		projectHome.setTitlePage("Admin --> Department --> Add/Edit Department");
		navHandler.handleNavigation(context, null, projectHomefile+ "?faces-redirect=true");
	}
	
}
