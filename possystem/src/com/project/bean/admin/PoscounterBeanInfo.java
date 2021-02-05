package com.project.bean.admin;

import java.math.BigDecimal;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.bo.interfaces.IBranchBO;
import com.project.bo.interfaces.IPoscounterBO;
import com.project.model.datamodel.BranchModel;
import com.project.model.sale.sales.PoscounterModel;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.factory.ErrorFactory;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.home.ProjectHome;
import com.project.login.LoginBean;

public class PoscounterBeanInfo {
	
	
	PoscounterBean poscounterBean = (PoscounterBean) BeanContext.getReference("poscounterBean");
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	public static Logger log = LoggerFactory.getLogger(BranchBeanInfo.class);
	FacesContext context = FacesContext.getCurrentInstance();
	Configuration config = Configuration.getConfiguration();
	String projectHomefile = config.getValue(IConfiguration.PROJECT_HOME_FILENAME);
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	static ErrorFactory errorFactory = ErrorFactory.getInstance();
	
	public void listCounter() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/admin/terminals.xhtml");
		projectHome.setTitlePage("Admin --> System Setup -->Search Terminals");
		navHandler.handleNavigation(context, null, projectHomefile+ "?faces-redirect=true");
	}

	
	
	public void newCounter() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/admin/addEditCounter.xhtml");
		projectHome.setTitlePage("Admin --> System Setup -->Add/Edit Terminal");
		navHandler.handleNavigation(context, null, projectHomefile+ "?faces-redirect=true");
	}
	
	
	public void saveCounter()
	{	
		CommonListBeanInfo common = new CommonListBeanInfo();
		
		if(validateCounter())
		{	
			boolean saveSuccess = false;
			try
			{
			PoscounterModel counter=poscounterBean.getPoscounter();		
			counter.setBranchId(loginBean.getBranch().getBranchId());	
			counter.setCurrentbalance(new BigDecimal(0.00));
			counter.setOpeningbalance(new BigDecimal(0.00));
			IPoscounterBO poscounterBO=poscounterBean.getPoscounterBO();
			saveSuccess=poscounterBO.createNewPoscounter(counter);
			
			if (saveSuccess) {
				listCounter();
				poscounterBean.getPoscounterList();
				CommonListBeanInfo.getAllTerminalList();				
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"counterPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("counter.label.save.success"),null));				
			}
			
			}
			catch(Exception e)
			{
				context.addMessage(
				UIComponent.findComponent(context.getViewRoot(),"counterPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), factoryBean.getMessageFactory().getMessage("counter.label.save.failed")));

			}
		}
		
	}
	
	
	public void editCounter(Integer counterId)
	{
		IPoscounterBO poscounterBO=poscounterBean.getPoscounterBO();
		try {
			poscounterBean.setPoscounter(poscounterBO.getPoscounterDetails(counterId));
			poscounterBean.setAction("update");
			newCounter();
		} catch (Exception e) {
			context.addMessage(
			UIComponent.findComponent(context.getViewRoot(),"counterPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			e.printStackTrace();
		}
	}
	public void updateCounter()
	{
		boolean saveSuccess = false;
		CommonListBeanInfo common = new CommonListBeanInfo();
		if(validateCounter())
		{	
		try
		{
			PoscounterModel counter=poscounterBean.getPoscounter();			
			IPoscounterBO poscounterBO=poscounterBean.getPoscounterBO();
			saveSuccess=poscounterBO.updatePoscounter(counter);
			
		
		if (saveSuccess) {					
			listCounter();
			poscounterBean.getPoscounterList();
			CommonListBeanInfo.getAllTerminalList();
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"counterPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("counter.label.update.success"),null));
			
		}
		
		}
		catch(Exception e)
		{
			context.addMessage(
			UIComponent.findComponent(context.getViewRoot(),"counterPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), factoryBean.getMessageFactory().getMessage("counter.label.update.failed")));

		}
		}
	
	}
	public void deleteCounter()
	{
		poscounterBean.searchCounter();
	}
	
	public void resetCounter()
	{
		PoscounterModel poscounter= new PoscounterModel();
		poscounterBean.setPoscounter(poscounter);
		poscounterBean.setAction("submit");
	}
	
	public boolean validateCounter()
	{
		boolean valid = true;
		
		String messageValue=null;
		
		try
		{
			PoscounterModel counter=poscounterBean.getPoscounter();
			
		if (factoryBean.checkIsNullAssignMessage(counter.getCounterNo(),
					"counter.label.counterNo", "counterNo")) {
				valid = false;
			}
			
		/*if (factoryBean.checkIsNullAssignMessage(counter.getCurrentbalance(),
				"counter.label.currentbalance", "currentbalance")) {
			valid = false;
		}
		
		if (factoryBean.checkIsNullAssignMessage(counter.getOpeningbalance(),
				"counter.label.openingbalance", "openingbalance")) {
			valid = false;
		}*/
		
		if (factoryBean.checkIsNullAssignMessage(counter.getStatus(),
				"branch.label.Status", "Status")) {
			valid = false;
		}
			
		}
		catch(Exception e)
		{
			context.addMessage(
			UIComponent.findComponent(context.getViewRoot(),"counterPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), factoryBean.getMessageFactory().getMessage("counter.label.update.failed")));

		}
		
		return valid;
	}

}
