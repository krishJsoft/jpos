package com.project.bean.admin;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.exolab.castor.types.Date;

import com.project.bo.interfaces.IShiftBO;
import com.project.common.factory.BeanContext;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.CommonFactoryBean;
import com.project.login.LoginBean;
import com.project.model.datamodel.ShiftModel;

@ManagedBean(name = "shiftBean")
@SessionScoped
public class ShiftBean {
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	private IShiftBO shiftBO=objectMapController.getShiftBO();

	private String shiftName;
	private String timeIn;
	private String timeOut;
	

	List<ShiftModel> shiftList;

	private String  action = "submit";

	ShiftModel shift = new ShiftModel();

	
	public IShiftBO getShiftBO() {
		return shiftBO;
	}

	public void setShiftBO(IShiftBO shiftBO) {
		this.shiftBO = shiftBO;
	}
	
	public String getShiftName() {
		return shiftName;
	}
	
	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}
	
	public String getTimeIn() {
		return timeIn;
	}
	
	public void setTimeIn(String timeIn) {
		this.timeIn = timeIn;
	}
	
	public String getTimeOut() {
		return timeOut;
	}
	
	public void setTimeOut(String timeOut) {
		this.timeOut = timeOut;
	}

	public List<ShiftModel> getShiftList() {
		return shiftList;
	}

	public void setShiftList(List<ShiftModel> shiftList) {
		this.shiftList = shiftList;
	}
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	public ShiftModel getShift() {
		return shift;
	}

	public void setShift(ShiftModel shift) {
		this.shift = shift;
	}

	public void loadShiftList() {
		try {
			List<ShiftModel> shiftListObj;
			shiftListObj=shiftBO.loadShiftAll();
			this.setShiftList(shiftListObj);		
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void createNewShift() {
		try {
			java.util.Date in=null;
			java.util.Date out=null;
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
			String dateIn="23/01/2018 "+this.getTimeIn().toUpperCase();
			String dateOut="23/01/2018 "+this.getTimeOut().toUpperCase();
			in=df.parse(dateIn);
			out=df.parse(dateOut);
			

			Calendar c = Calendar.getInstance();
			c.setTime(out);
			c.set(Calendar.SECOND,59);
			out=c.getTime();
			
			shift.setTimein(in);
			shift.setTimeout(out);
			if(shiftValidation()) {
	
				shift.setBranchId(loginBean.getBranch().getBranchId());
				Boolean savesuccess=shiftBO.createNewShift(shift);
				if(savesuccess) {
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!   " +shift.getShiftName(), "Created Successfully");
					FacesContext.getCurrentInstance().addMessage(null, msg);
					reset();
					loadShiftList();
					CommonListBeanInfo.getShiftList();
				}
				
			}
	
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		
	}
	
	public void updateShift() {
		try {
			boolean saveSuccess=false;
			
			java.util.Date in=null;
			java.util.Date out=null;
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
			
			String dateIn="23/01/2018 "+this.getTimeIn().toUpperCase();
			String dateOut="23/01/2018 "+this.getTimeOut().toUpperCase();
			in=df.parse(dateIn);
			out=df.parse(dateOut);
			
			
			Calendar c = Calendar.getInstance();
			c.setTime(out);
			c.set(Calendar.SECOND,59);
			out=c.getTime();
					
			shift.setTimein(in);
			shift.setTimeout(out);
			
			shift.setBranchId(loginBean.getBranch().getBranchId());
			saveSuccess=shiftBO.updateShift(shift);
			if(saveSuccess) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!   " +shift.getShiftName(), "Updated Successfully");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				reset();
				loadShiftList();
				CommonListBeanInfo.getShiftList();
				this.setAction("submit");
			}
		}catch(Exception ex) {
			
		}
	}
	
	public void editShift(ActionEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			String shiftId = "";
			shiftId = (String) event.getComponent().getAttributes().get("shiftId").toString();
			ShiftModel shiftObj=shiftBO.loadShift(Integer.parseInt(shiftId));
			this.setShift(shiftObj);
			
			String in=null;
			String out=null;
			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
			
			in=df.format(shift.getTimein());
			out=df.format(shift.getTimeout());
	
			
			this.setTimeIn( in.substring(11, in.length()) );
			this.setTimeOut( out.substring(11, out.length()) );
			this.setAction("update");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public boolean shiftValidation() {
		CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
		String messageValue=null;
		boolean valid=true;
		try {
			
			
			if(factoryBean.checkIsNullAssignMessage(shift.getTimein(), "shift.label.shiftStart", "timeIn")) {
				valid=false;
			}
			if(factoryBean.checkIsNullAssignMessage(shift.getTimeout(), "shift.label.shiftEnd", "timeOut")) {
				valid=false;
			}
			if(factoryBean.checkIsNullAssignMessage(shift.getShiftName(), "shift.label.shiftName", "shiftName")) {
				valid=false;
			}
			else {
				if(shiftBO.shiftIsExist(shift.getShiftName())) {
					messageValue=factoryBean.getErrorFactory().getError("shift.errors.shiftName.exists");
					factoryBean.reportErrorToMessageHandler(messageValue, messageValue, "shiftName");
					valid=false;
				}
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return valid;
	}
	
	public void deleteShift(ActionEvent event){
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			String shiftId = "";
			shiftId = (String) event.getComponent().getAttributes().get("shiftId").toString();
			ShiftModel shiftObj=shiftBO.loadShift(Integer.parseInt(shiftId));
			boolean deleteSucces=shiftBO.deleteShift(shiftObj);
			
			if(deleteSucces) {
				CommonListBeanInfo commonListBeanInfo = (CommonListBeanInfo) BeanContext.getReference("commonListBeanInfo");
				commonListBeanInfo.getShiftList();
				reset();
				loadShiftList();
			}
			
			this.setAction("update");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void reset() {
		shift.setShiftName(null);
		shift.setTimein(null);
		shift.setTimeout(null);
		this.setTimeIn(null);
		this.setTimeOut(null);

	}
}
