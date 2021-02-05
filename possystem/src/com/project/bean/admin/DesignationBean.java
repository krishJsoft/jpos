package com.project.bean.admin;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;


import com.project.model.datamodel.DesignationModel;
import com.project.common.factory.BeanContext;



@ManagedBean(name="designationBean")
@SessionScoped
public class DesignationBean {
	
	
	List<DesignationModel> designationList;
	private String designationname;	
	private int iddesignation;
	private String action="submit";
	

	 CommonListBean commonListBean = (CommonListBean) BeanContext
				.getReference("commonListBean");


	public List<DesignationModel> getDesignationList(){
		try
		{
		designationList=commonListBean.getCommonListBO().getDesignationList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return designationList;
	}
	
	
	public int getIddesignation() {
		return this.iddesignation;
	}

	public void setIddesignation(int iddesignation) {
		this.iddesignation = iddesignation;
	}

	public String getDesignationname() {
		return this.designationname;
	}

	public void setDesignationname(String designationname) {
		this.designationname = designationname;
	}
	
	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	
	public void saveDepartment()
	{
		DesignationModel dept = new DesignationModel();
		dept.setDesignationname(designationname);		
		//commonListBean.getCommonListBO().save(dept);
		
	}
	public void resetDepartment()
	{
		this.setDesignationname(null);		
		this.setAction("submit");
	}


	public void attrListenerDelete(ActionEvent event)
	  {
		  String id ="";
			id = (String)event.getComponent().getAttributes().get("paramid").toString();			
		  this.setIddesignation(Integer.parseInt(id));
	  }
	
	 public void confirmListenerDelete(ActionEvent event)
	  {
		  String id ="";
			id = (String)event.getComponent().getAttributes().get("paramid").toString();			
			//commonListBean.getCommonListBO().delete(Integer.parseInt(id));
	  }
	  
/*	 public void departmentEdit(ActionEvent event)
	  {
		  String id ="";
			id = (String)event.getComponent().getAttributes().get("paramid").toString();			
		 // this.setIddesignation(id);
		  this.setIddesignation(Integer.parseInt(id));

		
		    DesignationModel deptupdateObj = commonListBean.getCommonListBO().get(Integer.parseInt(id));		
			this.setDesignationname(deptupdateObj.getDesignationname());			
			this.setAction("update");		  
		 
	  }
	 
	 
	 public void departmentUpdate()
		{
		    System.out.println("iddepartment"+iddesignation);
		    DesignationModel deptupdateObj = commonListBean.getCommonListBO().get(iddesignation);	
		    deptupdateObj.setDesignationname(this.getDesignationname());		   	
		    commonListBean.getCommonListBO().update(deptupdateObj);
			resetDepartment();
			this.setAction("submit");	
		}
	 */
	 
	
}
