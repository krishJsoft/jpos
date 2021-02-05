package com.project.bean.admin;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.bo.interfaces.ICommonListBO;
import com.project.model.datamodel.AutonumModel;
import com.project.model.datamodel.DepartmentModel;
import com.project.common.factory.BeanContext;
import com.project.common.factory.ErrorFactory;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.login.LoginBean;

@ManagedBean(name = "systemAutonumBean")
@SessionScoped
public class SystemAutonumBean {

	public static Logger log = LoggerFactory.getLogger(SystemAutonumBean.class);
	FacesContext context = FacesContext.getCurrentInstance();

	private List<AutonumModel> autonumList;
	private AutonumModel autonum = new AutonumModel();
	private AutonumModel autonumModel = new AutonumModel();
	ObjectMapController objectMapController = (ObjectMapController) BeanContext
			.getReference("objectMapController");
	
	CommonListBean commonListBean = (CommonListBean) BeanContext
			.getReference("commonListBean");
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext
			.getReference("commonFactoryBean");
	static ErrorFactory errorFactory = ErrorFactory.getInstance();
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	

	public List<AutonumModel> getAutonumList() {
		return autonumList;
	}

	public void setAutonumList(List<AutonumModel> autonumList) {
		this.autonumList = autonumList;
	}

	public AutonumModel getAutonum() {		
		return autonum;
	}

	public void setAutonum(AutonumModel autonum) {
		this.autonum = autonum;
	}

	public AutonumModel getAutonumModel() {
		
		ICommonListBO commonListBO=commonListBean.getCommonListBO();
		try
		{
		autonumModel=commonListBO.getAutonumList(loginBean.getBranch().getBranchId()).get(0);
		autonum=autonumModel;
		}
		catch(Exception e)
		{
			
		}
		return autonumModel;
	}

	public void setAutonumModel(AutonumModel autonumModel) {
		this.autonumModel = autonumModel;
	}

	
	
	 public void updateAutonum()
	  {
		  
		  FacesContext context = FacesContext.getCurrentInstance();
		  ICommonListBO commonListBO=commonListBean.getCommonListBO();
		  autonum.setBranchRecordId(loginBean.getBranch().getBranchId());
		  
		     boolean valid = true;
			 boolean updateSuccess = false;
			try {	
				 commonListBO.updateAutonum(autonum);
				 context.addMessage(UIComponent.findComponent(context.getViewRoot(),"autonumPanel").getClientId(context),
				 new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("autonum.label.update.success"),null));
								
			} catch (Exception e)
			{
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"autonumPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_ERROR,null,e.toString()));
			}		
		  
	  }
	  
	
}
