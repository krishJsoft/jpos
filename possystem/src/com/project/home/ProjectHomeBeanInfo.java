package com.project.home;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;


import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.util.SessionBeanMapController;

public class ProjectHomeBeanInfo {
	
	Configuration config = Configuration.getConfiguration();
	String projectHome = config.getValue(IConfiguration.PROJECT_HOME_FILENAME);
	
	
	public void menuPageRedirect() {
		
		
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();		
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
	
	public void clearSessionObject()
	{
		SessionBeanMapController controllerMap = new SessionBeanMapController();
		
		controllerMap.clearAdminSession();
		controllerMap.clearCommissionSession();
		controllerMap.clearInvoiceSession();
		controllerMap.clearPaymentsSession();
		controllerMap.clearPurchaseSession();
		controllerMap.clearReportSession();
		controllerMap.clearSalesSession();
		controllerMap.clearStockSession();
		controllerMap.clearInvoiceSession();
		controllerMap.clearPaymentsSession();
		controllerMap.clearTaxSession();
		
		controllerMap.activateAdminSession();
		controllerMap.activateCommissionSession();
		controllerMap.activateInvoiceSession();
		controllerMap.activatePaymentsSession();
		controllerMap.activatePurchaseSession();
		controllerMap.activateReportSession();
		controllerMap.activateSalesSession();
		controllerMap.activateStockSession();
		controllerMap.activateInvoiceSession();
		controllerMap.activatePaymentsSession();
		controllerMap.activateTaxSession();
	}
	

}
