package com.project.bean.admin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;










import org.primefaces.context.RequestContext;

import com.project.model.datamodel.TaxmasterModel;
import com.project.model.datamodel.TaxmasterModel;
import com.project.common.factory.BeanContext;
import com.project.common.factory.ErrorFactory;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.home.ProjectHome;
import com.project.login.LoginBean;

@ManagedBean(name = "taxBean")
@SessionScoped
public class TaxBean {

	List<TaxmasterModel> taxList = new ArrayList<TaxmasterModel>();
	List<TaxmasterModel> taxListObj = new ArrayList<TaxmasterModel>();
	TaxmasterModel taxObj = new TaxmasterModel();	
	private String action = "submit";
	private String itemaction = "submit";
	
	FacesMessage msg = null;
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");

	CommonListBean commonListBean = (CommonListBean) BeanContext.getReference("commonListBean");
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	static ErrorFactory errorFactory = ErrorFactory.getInstance();	


	public TaxmasterModel getTaxObj() {
		return taxObj;
	}

	public void setTaxObj(TaxmasterModel taxObj) {
		this.taxObj = taxObj;
	}
	

	public List<TaxmasterModel> getBranchTaxList() {
		try {
			taxList = commonListBean.getCommonListBO().getTaxmasterList(loginBean.getBranch().getBranchId());
			this.setTaxListObj(taxList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return taxList;
	}

	public List<TaxmasterModel> getTaxListObj() {
		return taxListObj;
	}

	public void setTaxListObj(List<TaxmasterModel> taxListObj) {
		this.taxListObj = taxListObj;
	}	
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getItemaction() {
		return itemaction;
	}

	public void setItemaction(String itemaction) {
		this.itemaction = itemaction;
	}

	public void newTax() {
		TaxBeanInfo taxBeanInfo = new TaxBeanInfo();
		resetTax();
		taxBeanInfo.newTax();
	}
	
	public void listTax() {
		TaxBeanInfo taxBeanInfo = new TaxBeanInfo();
		resetTax();
		taxBeanInfo.listTax();
	}
		
	public void saveTax()
	{
		TaxBeanInfo taxBeanInfo = new TaxBeanInfo();   	
		CommonListBeanInfo clistinfo = new CommonListBeanInfo();
		RequestContext context = RequestContext.getCurrentInstance(); 		
		 if(taxBeanInfo.validateTax())
		    {
			taxBeanInfo.saveTax(); 
			clistinfo.getAllTaxList();
		    context.addCallbackParam("validity", true); 
		    }
		    else
		    {
		    context.addCallbackParam("validity", false);  	
		    }
	}	
	

	
	public void updateTax()
	{
		TaxBeanInfo taxBeanInfo = new TaxBeanInfo();
		RequestContext context = RequestContext.getCurrentInstance(); 		
		 if(taxBeanInfo.validateTax())
		    {
			 taxBeanInfo.updateTax(); 
		    context.addCallbackParam("validity", true); 
		    }
		    else
		    {
		    context.addCallbackParam("validity", false);  	
		    }		 
		
	}	
	
	
	public void editTax(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	try {
		String taxid = "";
		taxid = (String) event.getComponent().getAttributes().get("taxid").toString();				
		TaxBeanInfo taxBeanInfo = new TaxBeanInfo();
		taxBeanInfo.editTax(Integer.parseInt(taxid));		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"taxPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	public void resetTax()
	{
		taxObj = new TaxmasterModel();		
		taxObj.setBranch(loginBean.getBranch());		
		taxObj.setTaxvalue(new BigDecimal(0.00));			
		this.setAction("submit");
		this.setItemaction("submit");		
	}
	
}
