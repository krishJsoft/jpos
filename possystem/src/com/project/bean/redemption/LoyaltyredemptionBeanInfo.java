package com.project.bean.redemption;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.bean.sales.doctors.DoctorsPrescriptionBean;
import com.project.bean.sales.doctors.DoctorsPrescriptionBeanInfo;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.factory.ErrorFactory;
import com.project.common.validation.CommonFactoryBean;
import com.project.home.ProjectHome;
import com.project.login.LoginBean;

public class LoyaltyredemptionBeanInfo {

	
	LoyaltyredemptionBean loyaltyredemptionBean = (LoyaltyredemptionBean) BeanContext.getReference("loyaltyredemptionBean");
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	public static Logger log = LoggerFactory.getLogger(LoyaltyredemptionBeanInfo.class);
	FacesContext context = FacesContext.getCurrentInstance();
	Configuration config = Configuration.getConfiguration();
	String projectHome = config.getValue(IConfiguration.PROJECT_HOME_FILENAME);
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	static ErrorFactory errorFactory = ErrorFactory.getInstance();

	
	public void listRedemption() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/redemption/redemption.xhtml");
		projectHome.setTitlePage("Loyalty-Redemption --> Redemptions ");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}

	
	
	public void newRedemption() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/redemption/addEditRedemption.xhtml");
		projectHome.setTitlePage("Loyalty-Redemption --> Add/Edit Loyalty Redemption ");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
}
