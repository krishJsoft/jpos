package com.project.bean.sales.doctors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage; 
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.bo.interfaces.IDoctorsPrescriptionsBO;
import com.project.model.sale.sales.DoctorsPrescriptionsBreakdownModel;

import com.project.util.ConvertUtil;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.factory.ErrorFactory;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.home.ProjectHome;
import com.project.login.LoginBean;

public class DoctorsPrescriptionBeanInfo {

	DoctorsPrescriptionBean dpBean = (DoctorsPrescriptionBean) BeanContext.getReference("dpBean");
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	public static Logger log = LoggerFactory.getLogger(DoctorsPrescriptionBeanInfo.class);
	FacesContext context = FacesContext.getCurrentInstance();
	Configuration config = Configuration.getConfiguration();
	String projectHome = config.getValue(IConfiguration.PROJECT_HOME_FILENAME);
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	static ErrorFactory errorFactory = ErrorFactory.getInstance();

	
	/*public static void main(String[] args) {
		String pres = "DP".concat(StringUtils.leftPad("1", 13, "0"));
		System.out.println(pres.length());
	}*/
	
	
	public void addDoctorsPrescription(){
		editDoctorsPrescription();
	}
	
	
	public void doctorsPrescriptionHome() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/sales/doctorsPrescriptions.xhtml");
		projectHome.setTitlePage("Sales --> Doctor Prescription ");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}

	
	
	public void editDoctorsPrescription() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/sales/addEditDoctorsPrescriptions.xhtml");
		projectHome.setTitlePage("Sales --> Add/Edit Doctor Prescription ");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
 
	
	public void updateDoctorsPrescriptions(){
		FacesContext context = FacesContext.getCurrentInstance();	
		try {
			List<DoctorsPrescriptionsBreakdownModel> itemsToDelete = new ArrayList<DoctorsPrescriptionsBreakdownModel>();

			/*if(dpBean.oldDpbModels != null & dpBean.oldDpbModels.size() > 0){
				if(dpBean.dpbModels == null || dpBean.dpbModels.size() == 0){
					itemsToDelete = dpBean.oldDpbModels;
				}else{		
					
					for(DoctorsPrescriptionsBreakdownModel oldBreakdown : dpBean.oldDpbModels){
						boolean itemDeleted = true;
						for(DoctorsPrescriptionsBreakdownModel newBreakdown : dpBean.dpbModels){
							if(oldBreakdown.getProduct().getBarcode().equalsIgnoreCase(newBreakdown.getProduct().getBarcode())){
								itemDeleted = false;
								break;
							}
						}
						if(itemDeleted){
							itemsToDelete.add(oldBreakdown);
						}
					}
				}				
				if(itemsToDelete != null & itemsToDelete.size() > 0){
					for(DoctorsPrescriptionsBreakdownModel deleteBreakdown : itemsToDelete){
						dpBean.getDpBO().deleteDoctorPrescriptionBreakdown(deleteBreakdown);
					}
				}
			}*/
			
			dpBean.dpModel.setDpBreakdownModels(dpBean.dpbModels);
			
			dpBean.getDpBO().updateDoctorPrescription(dpBean.dpModel);
			dpBean.search();
			doctorsPrescriptionHome();

		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"dpPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));
		}
	}

}
