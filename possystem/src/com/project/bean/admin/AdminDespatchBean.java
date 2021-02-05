package com.project.bean.admin;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.project.bo.interfaces.IAdminDespatchBO;
import com.project.model.datamodel.AdmindespatchModel;
import com.project.common.factory.BeanContext;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.UIComponent;
import com.project.login.LoginBean;
import com.project.model.paginghelper.ProductPagingHelper;


/*
 * @author Gopal Ar
 * @version 1.0
 * @since 16 July 2013
 * 
 */

@ManagedBean(name = "adminDespatchBean")
@SessionScoped
public class AdminDespatchBean {

	private String action = "submit";
	AdmindespatchModel adespatch = new AdmindespatchModel();
	List<AdmindespatchModel> adespatchList = new ArrayList<AdmindespatchModel>();
	List<AdmindespatchModel> adespatchListObj = new ArrayList<AdmindespatchModel>();
	FacesContext context = FacesContext.getCurrentInstance();
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	private String despatchType;
	
	
	IAdminDespatchBO adminDespatchBO=objectMapController.getAdminDespatchBO();

	public IAdminDespatchBO getAdminDespatchBO() {
		return adminDespatchBO;
	}

	public void setAdminDespatchBO(IAdminDespatchBO adminDespatchBO) {
		this.adminDespatchBO = adminDespatchBO;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public AdmindespatchModel getAdespatch() {
		return adespatch;
	}

	public void setAdespatch(AdmindespatchModel adespatch) {
		this.adespatch = adespatch;
	}
	
	public String getDespatchType() {
		return despatchType;
	}

	public void setDespatchType(String despatchType) {
		this.despatchType = despatchType;
	}

	public List<AdmindespatchModel> getAdespatchList() {
		
		try
		{		
		adespatchList=adminDespatchBO.findByAdmindespatchList(loginBean.getBranch().getBranchId(),this.getDespatchType());
		this.setAdespatchListObj(adespatchList);	
		}
		catch(Exception e){					
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"despatchPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
			}
		
		return adespatchList;
		
		
	}

	public void setAdespatchList(List<AdmindespatchModel> adespatchList) {
		this.adespatchList = adespatchList;
	}

	public List<AdmindespatchModel> getAdespatchListObj() {
		return adespatchListObj;
	}

	public void setAdespatchListObj(List<AdmindespatchModel> adespatchListObj) {
		this.adespatchListObj = adespatchListObj;
	}

	
	public void listDespatch() {		
		this.searchDespatch();
		AdminDespatchBeanInfo adminDespatchBeanInfo = new AdminDespatchBeanInfo();
		adminDespatchBeanInfo.listDespatch();
	}

	public void newDespatch() {
		AdminDespatchBeanInfo adminDespatchBeanInfo = new AdminDespatchBeanInfo();
		adminDespatchBeanInfo.newDespatch();	
		resetDespatch();
	}
	
	public void resetDespatch()
	{
		AdminDespatchBeanInfo adminDespatchBeanInfo = new AdminDespatchBeanInfo();
		adminDespatchBeanInfo.resetDespatch();
	}
	
	
	public void saveDespatch()
	{
		AdminDespatchBeanInfo adminDespatchBeanInfo = new AdminDespatchBeanInfo();
		adminDespatchBeanInfo.saveDespatch();	
	}
	public void updateDespatch()
	{
		AdminDespatchBeanInfo adminDespatchBeanInfo = new AdminDespatchBeanInfo();
		adminDespatchBeanInfo.updateDespatch();		
	}
	public void deleteDespatch()
	{
		AdminDespatchBeanInfo adminDespatchBeanInfo = new AdminDespatchBeanInfo();
		adminDespatchBeanInfo.deleteDespatch();	
	}	
	
	
	public void searchDespatch()
	{
		try
		{
			this.getAdespatchList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void resetSearchDespatch()
	{
		this.setDespatchType(null);
		searchDespatch();
	}

	
	public void editDespatch(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	try {
		String despatchId = "";
		despatchId = (String) event.getComponent().getAttributes().get("despatchId").toString();		
		AdminDespatchBeanInfo adminDespatchBeanInfo = new AdminDespatchBeanInfo();
		adminDespatchBeanInfo.editDespatch(Integer.parseInt(despatchId));		
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"despatchPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	 
	 
}
