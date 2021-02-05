package com.project.bean.admin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FlowEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.project.bo.interfaces.IDoctorClinicBO;
import com.project.model.datamodel.DoctorclinicModel;
import com.project.common.factory.BeanContext;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.UIComponent;
import com.project.login.LoginBean;


/*
 *  @author Gopal
 * @version 1.0
 * @since 02 july 2013
 * 
 */

@ManagedBean(name = "doctorClinicBean")
@SessionScoped
public class DoctorClinicBean {

	
	public static Logger log = LoggerFactory.getLogger(DoctorClinicBean.class);

	DoctorclinicModel doctor= new DoctorclinicModel();
	List<DoctorclinicModel> doctorlist = new ArrayList<DoctorclinicModel>();
	List<DoctorclinicModel> doctorListObj = new ArrayList<DoctorclinicModel>();
	FacesContext context = FacesContext.getCurrentInstance();
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	
	int activeIndex = 0;
	boolean tabStep1 = false;
	boolean tabStep2 = true;
	boolean tabStep3 = true;	
	boolean nextActionStatus = false;
	boolean previousActionStatus = true;
	String action = "submit";
	String nextaction = "";
	
	private String  identificationNumber;
	private String  clinicName;
	private String  status;
	private String doctorName;
	
	IDoctorClinicBO doctorBO=objectMapController.getDoctorBO();		

	public IDoctorClinicBO getDoctorBO() {
		return doctorBO;
	}

	public void setDoctorBO(IDoctorClinicBO doctorBO) {
		this.doctorBO = doctorBO;
	}	
	
	
	public DoctorclinicModel getDoctor() {
		return doctor;
	}


	public void setDoctor(DoctorclinicModel doctor) {
		this.doctor = doctor;
	}


	public List<DoctorclinicModel> getDoctorlist() {
		
		try
		{	doctorlist.clear();
		    doctorlist=doctorBO.findByDoctorclinicList(identificationNumber, doctorName, status,loginBean.getBranch().getBranchId());
			this.setDoctorListObj(doctorlist);
		}
		catch(Exception e){					
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"doctorPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
		}
		
		
		return doctorlist;
	}


	public void setDoctorlist(List<DoctorclinicModel> doctorlist) {
		this.doctorlist = doctorlist;
	}


	public List<DoctorclinicModel> getDoctorListObj() {
		return doctorListObj;
	}


	public void setDoctorListObj(List<DoctorclinicModel> doctorListObj) {
		this.doctorListObj = doctorListObj;
	}


	public FacesContext getContext() {
		return context;
	}


	public void setContext(FacesContext context) {
		this.context = context;
	}


	public int getActiveIndex() {
		return activeIndex;
	}


	public void setActiveIndex(int activeIndex) {
		this.activeIndex = activeIndex;
	}


	public boolean isTabStep1() {
		return tabStep1;
	}


	public void setTabStep1(boolean tabStep1) {
		this.tabStep1 = tabStep1;
	}


	public boolean isTabStep2() {
		return tabStep2;
	}


	public void setTabStep2(boolean tabStep2) {
		this.tabStep2 = tabStep2;
	}


	public boolean isTabStep3() {
		return tabStep3;
	}


	public void setTabStep3(boolean tabStep3) {
		this.tabStep3 = tabStep3;
	}


	public boolean isNextActionStatus() {
		return nextActionStatus;
	}


	public void setNextActionStatus(boolean nextActionStatus) {
		this.nextActionStatus = nextActionStatus;
	}


	public boolean isPreviousActionStatus() {
		return previousActionStatus;
	}


	public void setPreviousActionStatus(boolean previousActionStatus) {
		this.previousActionStatus = previousActionStatus;
	}


	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}


	public String getNextaction() {
		return nextaction;
	}


	public void setNextaction(String nextaction) {
		this.nextaction = nextaction;
	}


	public String getIdentificationNumber() {
		return identificationNumber;
	}


	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}


	public String getClinicName() {
		return clinicName;
	}


	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}	

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String onFlowProcess(FlowEvent event) {         
        DoctorClinicBeanInfo DoctorClinicBeanInfo = new DoctorClinicBeanInfo();	        
         return DoctorClinicBeanInfo.onFlowProcess(event);
       
    }  	
	
	public void saveDoctor()
	{
		 DoctorClinicBeanInfo doctorClinicBeanInfo = new DoctorClinicBeanInfo();	        
		 doctorClinicBeanInfo.saveDoctor();		
	}
	public void updateDoctor()
	{
		DoctorClinicBeanInfo doctorClinicBeanInfo = new DoctorClinicBeanInfo();	        
		doctorClinicBeanInfo.updateDoctor();		
	}
	public void deleteDoctor()
	{
		DoctorClinicBeanInfo doctorClinicBeanInfo = new DoctorClinicBeanInfo();
		doctorClinicBeanInfo.deleteDoctor();		
	}	
	public void resetDoctor()
	{
		DoctorClinicBeanInfo doctorClinicBeanInfo = new DoctorClinicBeanInfo();
		doctorClinicBeanInfo.resetDoctor();
		
	}
	
	public void resetStep1()
	{		
		
		doctor.setAddress(null);			
		doctor.setCity(null);
		doctor.setState(null);
		doctor.setCountry(null);		
		doctor.setMobileNo(null);
		doctor.setIdentificationNumber(null);
		doctor.setCommission(new BigDecimal("0.00"));
		doctor.setCommissionFrom(0);
		doctor.setCommissionTo(0);
	}
	
	public void resetStep2()
	{		
		doctor.setClinicPostCode(null);
		doctor.setClinicFaxNo(null);
		doctor.setClinicPhoneNo(null);
		doctor.setClinicAddress(null);			
		doctor.setClinicCity(null);
		doctor.setClinicState(null);
		doctor.setClinicCountry(null);
	}	
	
	public void listDoctor() {		
		this.searchDoctor();
		DoctorClinicBeanInfo doctorClinicBeanInfo = new DoctorClinicBeanInfo();
		doctorClinicBeanInfo.listDoctor();
	}
	public void newDoctor() {
		DoctorClinicBeanInfo doctorClinicBeanInfo = new DoctorClinicBeanInfo();
		doctorClinicBeanInfo.newDoctor();	
		resetStep1();
		resetStep2();
		resetDoctor();
	}
	
	public void searchDoctor()
	{
		try
		{
			this.getDoctorlist();
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void resetSearchDoctor()
	{
		this.identificationNumber=null;
		this.status=null;
		this.clinicName=null;
		this.doctorName=null;
		getDoctorlist();
	}

	
	public void editDoctor(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();	
	try {
		String doctorClinicId = "";
		doctorClinicId = (String) event.getComponent().getAttributes().get("doctorClinicId").toString();		
		
		DoctorClinicBeanInfo doctorClinicBeanInfo = new DoctorClinicBeanInfo();
		doctorClinicBeanInfo.editDoctor(Integer.parseInt(doctorClinicId));
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"doctorPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	

	
	
}
