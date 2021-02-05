package com.project.bean.admin;


import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

import org.primefaces.event.FlowEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.bo.interfaces.IDoctorClinicBO;
import com.project.model.datamodel.DoctorclinicModel;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.factory.ErrorFactory;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.home.ProjectHome;
import com.project.login.LoginBean;

public class DoctorClinicBeanInfo {

	
	
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	DoctorClinicBean doctorClinicBean = (DoctorClinicBean) BeanContext.getReference("doctorClinicBean");
	public static Logger log = LoggerFactory.getLogger(BranchBeanInfo.class);
	FacesContext context = FacesContext.getCurrentInstance();
	Configuration config = Configuration.getConfiguration();
	String projectHome = config.getValue(IConfiguration.PROJECT_HOME_FILENAME);
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	static ErrorFactory errorFactory = ErrorFactory.getInstance();
	
	
	
	
	public void listDoctor() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/admin/doctorClinic.xhtml");
		projectHome.setTitlePage("Admin --> Doctors -->Search Doctor");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}

	
	
	public void newDoctor() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/admin/addEditDoctorClinic.xhtml");
		projectHome.setTitlePage("Admin --> Doctors -->Add/Edit Doctor");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
	
	
	
	public void saveDoctor()
	{	
		if(doctorStep1Validation() && doctorStep2Validation())
		{	
			boolean saveSuccess = false;			
			try
			{
			DoctorclinicModel doctor=doctorClinicBean.getDoctor();
			doctor.setBranchId(loginBean.getBranch().getBranchId());
			
			IDoctorClinicBO doctorBO=doctorClinicBean.getDoctorBO();
			saveSuccess=doctorBO.createNewDoctorclinic(doctor);
			
			if (saveSuccess) {					
				doctorClinicBean.resetSearchDoctor();
				listDoctor();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"doctorPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("doctor.label.save.success"),null));				
			}
			
			}
			catch(Exception e)
			{
				context.addMessage(
				UIComponent.findComponent(context.getViewRoot(),"doctorPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), factoryBean.getMessageFactory().getMessage("doctor.label.save.failed")));

			}
		}
		
	}
	
	
	public void editDoctor(Integer doctorClinicId)
	{
		IDoctorClinicBO doctorBO=doctorClinicBean.getDoctorBO();
		try {
			doctorClinicBean.setDoctor(doctorBO.getDoctorclinicDetails(doctorClinicId));			
			doctorClinicBean.setAction("update");
			newDoctor();
		} catch (Exception e) {
			context.addMessage(
			UIComponent.findComponent(context.getViewRoot(),"doctorPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			e.printStackTrace();
		}
	}
	public void updateDoctor()
	{
		boolean saveSuccess = false;
		if(doctorStep1Validation() && doctorStep2Validation())
		{	
		try
		{
		DoctorclinicModel doctor=doctorClinicBean.getDoctor();			
		IDoctorClinicBO doctorBO=doctorClinicBean.getDoctorBO();
		saveSuccess=doctorBO.updateDoctorclinic(doctor);
		
		if (saveSuccess) {					
			doctorClinicBean.resetSearchDoctor();
			listDoctor();
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"doctorPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("doctor.label.update.success"),null));
			
		}
		
		}
		catch(Exception e)
		{
			context.addMessage(
			UIComponent.findComponent(context.getViewRoot(),"doctorPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), factoryBean.getMessageFactory().getMessage("doctor.label.update.failed")));

		}
		}
	
	}
	public void deleteDoctor()
	{
		doctorClinicBean.searchDoctor();
	}
	
	public void resetDoctor()
	{
		DoctorclinicModel doctor=doctorClinicBean.getDoctor();	
		doctorClinicBean.setDoctor(doctor);
		doctorClinicBean.setAction("submit");
	}
	
	
	

		public boolean doctorStep1Validation()
		{
			boolean valid = true;			
			String messageValue = null;
			IDoctorClinicBO doctorBO=null;
			try
			{
				doctorBO=doctorClinicBean.getDoctorBO();
				if (factoryBean.checkIsNullAssignMessage(doctorClinicBean.getDoctor().getDoctorName(),
						"doctor.label.doctorName", "doctorName")) {
					valid = false;
				}
				
				if (factoryBean.checkIsNullAssignMessage(doctorClinicBean.getDoctor().getIdentificationNumber(),
						"doctor.label.identificationNumber", "identificationNumber")) {
					valid = false;
				}
				if (factoryBean.checkIsNullAssignMessage(doctorClinicBean.getDoctor().getMobileNo(),
						"doctor.label.mobileNo", "mobileNo")) {
					valid = false;
				}
				if (factoryBean.checkIsNullAssignMessage(doctorClinicBean.getDoctor().getEmail(),
						"doctor.label.email", "email")) {
					valid = false;
				}
				if (factoryBean.checkIsNullAssignMessage(doctorClinicBean.getDoctor().getJoinedDate(),
						"doctor.label.joinedDate", "joinedDate")) {
					valid = false;
				}
				if (factoryBean.checkIsNullAssignMessage(doctorClinicBean.getDoctor().getStatus(),
						"doctor.label.status", "status")) {
					valid = false;
				}
				
				
				else {
					if (doctorClinicBean.getAction().equalsIgnoreCase("submit")) {
						if (doctorBO.findDoctorIcExites(doctorClinicBean.getDoctor().getIdentificationNumber())) {
							messageValue = factoryBean.getErrorFactory().getError("doctor.errors.identificationNumber.exists");
							factoryBean.reportErrorToMessageHandler(messageValue,messageValue, "identificationNumber");
							valid = false;
						}
					}else{  
						
						if(! doctorClinicBean.getDoctor().getIdentificationNumber().equalsIgnoreCase(doctorClinicBean.getDoctor().getIdentificationOldNumber()) ){
													
							if (doctorBO.findDoctorIcExites(doctorClinicBean.getDoctor().getIdentificationNumber())) {
								messageValue = factoryBean.getErrorFactory().getError("doctor.errors.identificationNumber.exists");
								factoryBean.reportErrorToMessageHandler(messageValue,messageValue, "identificationNumber");
								valid = false;
							}
						}
					}
				}
			
				
				
				
			}			
			catch(Exception e)
			{
				context.addMessage(
				UIComponent.findComponent(context.getViewRoot(),"doctorPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

			}
			return valid;		
		
		 }
	
		
		public boolean doctorStep2Validation()
		{
			boolean valid = true;			
			String messageValue = null;
			try
			{
			
			
			}			
			catch(Exception e)
			{
				context.addMessage(
				UIComponent.findComponent(context.getViewRoot(),"doctorPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

			}
			return valid;		
		
		 }
	
		
		public boolean doctorStep3Validation()
		{
			boolean valid = true;			
			String messageValue = null;
			try
			{
			
			
			}			
			catch(Exception e)
			{
				context.addMessage(
				UIComponent.findComponent(context.getViewRoot(),"doctorPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

			}
			return valid;		
		
		 }
	
		
		public String onFlowProcess(FlowEvent event) {

			DoctorClinicBean doctorClinicBean = (DoctorClinicBean) BeanContext.getReference("doctorClinicBean");			
			
			boolean valid = true;
			String activeStep="";

			if (event.getOldStep().equalsIgnoreCase("step1")) {
				valid = doctorStep1Validation();
				if (valid) {				
					
					activeStep=event.getNewStep();
					
				} else {
					
					activeStep=event.getOldStep();
				}
			}

			else if (event.getOldStep().equalsIgnoreCase("step2")) {
				valid = this.doctorStep2Validation();
				if (valid) {					
					
										
					activeStep=event.getNewStep();
				}
				else
				{
					activeStep=event.getOldStep();
				}
			}
			
			
			else if (event.getOldStep().equalsIgnoreCase("step3")) {
				valid = this.doctorStep3Validation();
				if (valid) {					
					
					
					activeStep=event.getNewStep();
				}
				else
				{
					activeStep=event.getOldStep();
				}
			}	
			
			return activeStep;
			
			      }
		
		
}
