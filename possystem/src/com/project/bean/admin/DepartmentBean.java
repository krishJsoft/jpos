package com.project.bean.admin;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;


import com.project.model.datamodel.DepartmentModel;
import com.project.common.factory.BeanContext;
import com.project.common.factory.ErrorFactory;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.home.ProjectHome;
import com.project.login.LoginBean;

@ManagedBean(name = "departmentBean")
@SessionScoped
public class DepartmentBean {

	List<DepartmentModel> departmentList;
	List<DepartmentModel> departmentListObj;
	DepartmentModel departmentObj = new DepartmentModel();
	private String departmentName;
	private String departmentCode;
	private int departmentId;
	private String action = "submit";
	static Logger logger = Logger.getLogger(DepartmentBean.class);
	FacesMessage msg = null;
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");

	CommonListBean commonListBean = (CommonListBean) BeanContext
			.getReference("commonListBean");
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext
			.getReference("commonFactoryBean");
	static ErrorFactory errorFactory = ErrorFactory.getInstance();
	
	
	
	
	public DepartmentModel getDepartmentObj() {
		return departmentObj;
	}

	public void setDepartmentObj(DepartmentModel departmentObj) {
		this.departmentObj = departmentObj;
	}

	public List<DepartmentModel> getDepartmentList() {
		try {
			departmentList = commonListBean.getCommonListBO().getDepartmentList(this.getDepartmentId(),loginBean.getBranch().getBranchId());
			this.setDepartmentListObj(departmentList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return departmentList;
	}
	
	public List<DepartmentModel> getDepartmentListObj() {
		return departmentListObj;
	}

	public void setDepartmentListObj(List<DepartmentModel> departmentListObj) {
		this.departmentListObj = departmentListObj;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void saveDepartment() {
		 FacesContext context = FacesContext.getCurrentInstance();
		 boolean valid = true;
		 boolean saveSuccess = false;
		 DepartmentBeanInfo departmentBeanInfo = new DepartmentBeanInfo();
		try {
			valid = this.validateDepartment(); // check Validation
			if (valid) {
			DepartmentModel dept = new DepartmentModel();
			dept.setDepartmentName(departmentObj.getDepartmentName());
			dept.setDepartmentCode(departmentObj.getDepartmentCode());
			dept.setBranchId(loginBean.getBranch().getBranchId());
			dept.setDescription(departmentObj.getDescription());
			commonListBean.getCommonListBO().saveDepartment(dept);
			
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"departmentPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("department.label.save.success"),null));
			
			//getDepartmentList();
			resetDepartment();
			departmentBeanInfo.listDepartment();
			//commonListBean.getSelectDepartmentList();
			CommonListBeanInfo.getAllDepartmentList();
			}
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"departmentPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,factoryBean.getMessageFactory().getMessage("department.label.save.failed"),e.toString()));
		}

	}

	public void resetDepartment() {
		departmentObj.setDepartmentName(null);
		departmentObj.setDepartmentCode(null);
		departmentObj.setDescription(null);
		this.setAction("submit");
		this.setDepartmentId(0);
		searchDepartment();
	}

	public void attrListenerDelete(ActionEvent event) {
		String id = "";
		id = (String) event.getComponent().getAttributes().get("paramid")
				.toString();
		this.setDepartmentId(Integer.parseInt(id));
	}

	public void confirmListenerDelete(ActionEvent event) {
		String id = "";
		id = (String) event.getComponent().getAttributes().get("paramid")
				.toString();
		// commonListBean.getCommonListBO().delete(Integer.parseInt(id));
	}

	
	  public void departmentEdit(ActionEvent event) 	  
	  { 
	  String id =""; id =(String)event.getComponent().getAttributes().get("paramid").toString();
	  this.setDepartmentId(Integer.parseInt(id));
	  DepartmentBeanInfo departmentBeanInfo = new DepartmentBeanInfo();
	  DepartmentModel deptupdateObj;
	  FacesContext context = FacesContext.getCurrentInstance();
	
		try {			
			 deptupdateObj = commonListBean.getCommonListBO().getDepartment(Integer.parseInt(id));
			 departmentObj.setDepartmentCode(deptupdateObj.getDepartmentCode());
			 departmentObj.setDepartmentName(deptupdateObj.getDepartmentName());
			 departmentObj.setDescription(deptupdateObj.getDescription());
			 this.setAction("update");		  
			 departmentBeanInfo.newDepartment();  	
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"departmentPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,factoryBean.getMessageFactory().getMessage("department.label.common.error"),e.toString()));
		}		
	  
	  }
	 
	
	  
	  public void updateDepartment()
	  {
		  DepartmentModel deptupdateObj = new DepartmentModel();
		  FacesContext context = FacesContext.getCurrentInstance();
		  DepartmentBeanInfo departmentBeanInfo = new DepartmentBeanInfo();
		     boolean valid = true;
			 boolean updateSuccess = false;
			try {
				valid = this.validateDepartment(); // check Validation
				if (valid) {
				 deptupdateObj.setDepartmentCode(departmentObj.getDepartmentCode());
				 deptupdateObj.setDepartmentName(departmentObj.getDepartmentName());
				 deptupdateObj.setDepartmentId(this.getDepartmentId());
				 deptupdateObj.setDescription(departmentObj.getDescription());
				 commonListBean.getCommonListBO().updateDepartment(deptupdateObj);
				 this.setAction("save");
				 this.resetDepartment();
				 departmentBeanInfo.listDepartment();
				 commonListBean.getSelectDepartmentList();
				 context.addMessage(UIComponent.findComponent(context.getViewRoot(),"departmentPanel").getClientId(context),
				 new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("department.label.update.success"),null));
				 getDepartmentList();
				}
				
			} catch (Exception e) {
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"departmentPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_ERROR,factoryBean.getMessageFactory().getMessage("department.label.update.failed"),e.toString()));
			}		
		  
	  }
	  
	  
	  
	  public void listDepartment() {
		  DepartmentBeanInfo departmentBeanInfo = new DepartmentBeanInfo();
		  departmentBeanInfo.listDepartment();
		}

		
		
		public void newDepartment() {
			 DepartmentBeanInfo departmentBeanInfo = new DepartmentBeanInfo();
			 departmentBeanInfo.newDepartment();
		}
		
		public void searchDepartment()
		{
			getDepartmentList();
		}
		
		
		
	  /* Start Department  Validation */
		
		public boolean validateDepartment() {
			boolean valid = true;
			
		
			
			if (factoryBean.checkIsNullAssignMessage(departmentObj.getDepartmentName(),
					"department.label.departmentname", "departmentname")) {
				valid = false;
			}
			
			if (factoryBean.checkIsNullAssignMessage(departmentObj.getDepartmentCode(),
					"department.label.departmentcode", "departmentcode")) {
				valid = false;
			}
			
			return valid;

		}

		/* End Department Process Validation */

		public void deleteDepartment(ActionEvent event) throws Exception {
			FacesContext context = FacesContext.getCurrentInstance();

			String id =""; id =(String)event.getComponent().getAttributes().get("paramid").toString();
			this.setDepartmentId(Integer.parseInt(id));
			try {
				boolean deleteSuccess=false;
				DepartmentModel dept=commonListBean.getCommonListBO().getDepartment(Integer.parseInt(id));
				commonListBean.getCommonListBO().deleteDepartment(dept);
				resetDepartment();
				
			} catch (Exception e) {
				if(e.getCause().toString().toLowerCase().contains("constraintviolationexception")) {
					context.addMessage(UIComponent.findComponent(context.getViewRoot(),"departmentPanel").getClientId(context),
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to delete, there are users registered under that department.", null));
				}else {
					context.addMessage(UIComponent.findComponent(context.getViewRoot(),"departmentPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
					throw e;
				}
				
			}	
		}

		
}
