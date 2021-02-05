package com.project.bean.admin;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.project.bo.interfaces.IPoscounterBO;

import com.project.model.sale.sales.PoscounterModel;
import com.project.common.factory.BeanContext;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.UIComponent;
import com.project.login.LoginBean;

/*
 * @author Gopal Ar
 * @version 1.0
 * @since 23 Nov 2013
 * 
 */

@ManagedBean(name = "poscounterBean")
@SessionScoped
public class PoscounterBean {
	
	private String action = "submit";
	PoscounterModel poscounter = new PoscounterModel();
	List<PoscounterModel> poscounterList = new ArrayList<PoscounterModel>();
	List<PoscounterModel> poscounterListObj = new ArrayList<PoscounterModel>();
	FacesContext context = FacesContext.getCurrentInstance();
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	
	IPoscounterBO poscounterBO=objectMapController.getPoscounterBO();

	public IPoscounterBO getPoscounterBO() {
		return poscounterBO;
	}

	public void setPoscounterBO(IPoscounterBO poscounterBO) {
		this.poscounterBO = poscounterBO;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public PoscounterModel getPoscounter() {
		return poscounter;
	}

	public void setPoscounter(PoscounterModel poscounter) {
		this.poscounter = poscounter;
	}

	public List<PoscounterModel> getPoscounterList() {
		
		try
		{		
		poscounterListObj=poscounterBO.findByPoscounterList(loginBean.getBranch().getBranchId());
		this.setPoscounterList(poscounterListObj);	
		}
		catch(Exception e){					
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"counterPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
			}
		
		return poscounterListObj;
	}

	public void setPoscounterList(List<PoscounterModel> poscounterList) {
		this.poscounterList = poscounterList;
	}

	public List<PoscounterModel> getPoscounterListObj() {
		return poscounterListObj;
	}

	public void setPoscounterListObj(List<PoscounterModel> poscounterListObj) {
		this.poscounterListObj = poscounterListObj;
	}
	
	
	
	public void listCounter() {		
		
		PoscounterBeanInfo poscounterBeanInfo = new PoscounterBeanInfo();
		poscounterBeanInfo.listCounter();
		searchCounter();
	}

	public void newCounter() {
		PoscounterBeanInfo poscounterBeanInfo = new PoscounterBeanInfo();
		poscounterBeanInfo.newCounter();	
		resetCounter();
	}
	
	public void saveCounter()
	{
		PoscounterBeanInfo poscounterBeanInfo = new PoscounterBeanInfo();
		poscounterBeanInfo.saveCounter();		
	}
	public void updateCounter()
	{
		PoscounterBeanInfo poscounterBeanInfo = new PoscounterBeanInfo();
		poscounterBeanInfo.updateCounter();		
	}
	public void deleteCounter()
	{
		PoscounterBeanInfo poscounterBeanInfo = new PoscounterBeanInfo();
		poscounterBeanInfo.deleteCounter();		
	}	
	public void resetCounter()
	{
		PoscounterBeanInfo poscounterBeanInfo = new PoscounterBeanInfo();
		poscounterBeanInfo.resetCounter();
		
	}
	
	public void searchCounter()
	{
		try
		{
			this.getPoscounterList();
		}
		catch(Exception e)
		{
			
		}
	}
	
	
	
	public void editCounter(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	//this.setMessage(null);
	try {
		String counterId = "";
		counterId = (String) event.getComponent().getAttributes().get("counterId").toString();		
		PoscounterBeanInfo poscounterBeanInfo = new PoscounterBeanInfo();
		poscounterBeanInfo.editCounter(Integer.parseInt(counterId));		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"counterPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	
	
	
	
	
	
}
