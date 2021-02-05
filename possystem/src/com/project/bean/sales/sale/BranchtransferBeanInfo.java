package com.project.bean.sales.sale;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

import com.project.bo.interfaces.IBranchtransferBO;
import com.project.bo.interfaces.IBranchtransferBO;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.factory.ErrorFactory;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.home.ProjectHome;
import com.project.login.LoginBean;
import com.project.model.sale.sales.BranchtransferModel;
import com.project.util.DateUtil;

public class BranchtransferBeanInfo {

	
	
	BranchtransferBean branchtransferBean = (BranchtransferBean) BeanContext.getReference("branchtransferBean");
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	FacesContext context = FacesContext.getCurrentInstance();
	Configuration config = Configuration.getConfiguration();
	String projectHome = config.getValue(IConfiguration.PROJECT_HOME_FILENAME);
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	static ErrorFactory errorFactory = ErrorFactory.getInstance();
	
	public void listTransfer() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/sales/branchTransferList.xhtml");
		projectHome.setTitlePage("Sales --> Branch -->Branch Request Listing");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
	public void newTransfer() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/sales/addeditbranchTransfer.xhtml");
		projectHome.setTitlePage("Sales --> Branch -->Add/Edit Branch Request");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
	
	
	public void createNewTransfer()
	{
		BranchtransferModel branchtransferModel = new BranchtransferModel();
		IBranchtransferBO branchtransferBO;
		boolean saveSuccess = false;
		try
		{	branchtransferBO=branchtransferBean.getBranchtransferBO();
				
		if(validateTransfer())
		{
			branchtransferModel = branchtransferBean.getBranchtransfer();	
			branchtransferModel.setBranchId(branchtransferBean.getBranchId());
			
			branchtransferModel.setBranchtransferbreakdowns(branchtransferBean.getBranchtransferbreakdowns());
			branchtransferModel.setCreatedBy(loginBean.getUserName());
			branchtransferModel.setCreatedDate(DateUtil.getTodayDate());			;
			branchtransferModel.setStatus("1");		
			branchtransferModel.setBranchStatus("1");
			branchtransferModel.setBranchRecordId(loginBean.getBranch().getBranchId());			
		    saveSuccess=branchtransferBO.createNewBranchtransfer(branchtransferModel);
		}
		    
			if (saveSuccess) {					
				branchtransferBean.resetSalesList();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesListPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("transfer.label.save.success"),null));				
			}
		}
		catch(Exception e)
		{				
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));
		}
	}	
	
		
	public void updateTransfer()
	{		
		BranchtransferModel branchtransfer = new BranchtransferModel();
		IBranchtransferBO branchtransferBO;
		boolean updateSuccess = false;
		boolean itemRemoved = true;
		try
		{	branchtransferBO=branchtransferBean.getBranchtransferBO();
	    	branchtransfer = branchtransferBean.getBranchtransfer();
	    	branchtransfer.setCreatedBy(loginBean.getUserName());
	    	branchtransfer.setBranchId(branchtransferBean.getBranchId());
	    	branchtransfer.setBranchRecordId(loginBean.getBranch().getBranchId());
		    branchtransfer.setLastModifedDate(DateUtil.getTodayDate());		    
		    branchtransfer.setStatus("1");
		  
		    if(validateTransfer())
			{
		   	branchtransfer.setBranchtransferbreakdowns(branchtransferBean.getBranchtransferbreakdowns());
		    
		   	branchtransferBean.setItemaction("submit"); 
		   updateSuccess=branchtransferBO.updateBranchtransfer(branchtransfer);
			}
			if (updateSuccess) {					
				branchtransferBean.resetSales();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesListPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("transfer.label.update.success"),null));				
			}
		}
		catch(Exception e)
		{				
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));
			
		}
		
	}	
	

	
		
	public BranchtransferModel editTransfer(Integer branchsalesId)
	{
		IBranchtransferBO branchtransferBO;
		 BranchtransferModel branchsaleModelData=null;
		try {
			branchtransferBO=branchtransferBean.getBranchtransferBO();
			 branchsaleModelData = branchtransferBO.getBranchtransferDetails(branchsalesId);
			 branchtransferBean.setBranchtransfer(branchsaleModelData);	 
			 branchtransferBean.setItemaction("update");
		} catch (Exception e) {
			context.addMessage(
			UIComponent.findComponent(context.getViewRoot(),"salesListPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			e.printStackTrace();
		}		
		return  branchsaleModelData;
	}
	
	
	
	
	public boolean validateTransfer()
	{
		boolean valid = true;
		BranchtransferBean branchtransferBean = (BranchtransferBean) BeanContext.getReference("branchtransferBean");
		FacesContext context = FacesContext.getCurrentInstance();	
		try
		{
			if (factoryBean.checkIsNullAssignMessage(branchtransferBean.getBranchId(),"quotation.label.customerName", "customerName")) {
				valid = false;
			}	
			
			else if (factoryBean.checkIsZeroAssignMessage(""+branchtransferBean.getBranchId(), "quotation.label.customerName",  "customerName")){
				valid = false;
			}				
			
			
			
			if(branchtransferBean.getBranchtransferbreakdowns().size()==0)
			{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesListPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "No Records found in Quotation"));
			}
		}
		catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesListPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		
		}	
		
		return valid;
	}
	
	
}
