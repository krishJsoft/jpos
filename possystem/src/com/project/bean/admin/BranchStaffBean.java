package com.project.bean.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;

import com.project.bo.interfaces.IBranchBO;
import com.project.bo.interfaces.IStaffBO;
import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.BranchstaffmemberModel;
import com.project.common.factory.BeanContext;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.UIComponent;
import com.project.home.ProjectHome;
import com.project.login.LoginBean;

/*
 *  @author Gopal
 * @version 1.0
 * @since 08 June 2013
 * 
 */

@ManagedBean(name = "branchStaffBean")
@SessionScoped
public class BranchStaffBean {

	private String  staffName;
	private String  identificationNo;
	private Integer roleId;
	private Integer branchId;
	private String  status;
	private String  action = "submit";

	BranchstaffmemberModel branchstaff = new BranchstaffmemberModel();
	List<BranchstaffmemberModel> branchstafflist = new ArrayList<BranchstaffmemberModel>();
	List<BranchstaffmemberModel> branchStaffListObj = new ArrayList<BranchstaffmemberModel>();
	FacesContext context = FacesContext.getCurrentInstance();
	private List<BranchstaffmemberModel> filteredBranchstaff; 
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");	
	private IStaffBO staffBO=objectMapController.getStaffBO();	
		
	public IStaffBO getStaffBO() {
		return staffBO;
	}

	public void setStaffBO(IStaffBO staffBO) {
		this.staffBO = staffBO;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}	
	
	public String getIdentificationNo() {
		return identificationNo;
	}

	public void setIdentificationNo(String identificationNo) {
		this.identificationNo = identificationNo;
	}
	
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public BranchstaffmemberModel getBranchstaff() {
		return branchstaff;
	}

	public void setBranchstaff(BranchstaffmemberModel branchstaff) {
		this.branchstaff = branchstaff;
	}

	public List<BranchstaffmemberModel> getBranchstafflist() {
		
		
		try
		{	branchstafflist.clear();
		    branchstafflist=staffBO.findByStaffList(loginBean.getBranch().getBranchId(), identificationNo, roleId, status);
			this.setBranchStaffListObj(branchstafflist);
		}
		catch(Exception e){					
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"branchStaffPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
		}
		
		return branchstafflist;
	}

	public void setBranchstafflist(List<BranchstaffmemberModel> branchstafflist) {
		this.branchstafflist = branchstafflist;
	}

	
	public List<BranchstaffmemberModel> getBranchStaffListObj() {
		return branchStaffListObj;
	}

	public void setBranchStaffListObj(
			List<BranchstaffmemberModel> branchStaffListObj) {
		this.branchStaffListObj = branchStaffListObj;
	}	
	
	public List<BranchstaffmemberModel> getFilteredBranchstaff() {
		return filteredBranchstaff;
	}

	public void setFilteredBranchstaff(
			List<BranchstaffmemberModel> filteredBranchstaff) {
		this.filteredBranchstaff = filteredBranchstaff;
	}

	public void listStaff() {
		BranchStaffBeanInfo branchStaffBeanInfo = new BranchStaffBeanInfo();
		branchStaffBeanInfo.listStaff();
	}	
	
	public void newStaff() {
		BranchStaffBeanInfo branchStaffBeanInfo = new BranchStaffBeanInfo();
		branchStaffBeanInfo.newStaff();	
		resetBranchStaff();
	}
	
	public void createNewStaff()
	{
		BranchStaffBeanInfo branchStaffBeanInfo = new BranchStaffBeanInfo();
		branchStaffBeanInfo.createNewStaff();		
	}
	
	public void updateBranchStaff()
	{
		BranchStaffBeanInfo branchStaffBeanInfo = new BranchStaffBeanInfo();
		branchStaffBeanInfo.updateBranchStaff();		
	}
	
	public void updateMyProfileStaff()
	{
		BranchStaffBeanInfo branchStaffBeanInfo = new BranchStaffBeanInfo();
		branchStaffBeanInfo.updateMyProfileStaff();		
	}
	
	public void deleteBranchStaff(ActionEvent event)
	{
		boolean deleteSuccess=false;
		try {
			String branchStaffId = "";
			branchStaffId = (String) event.getComponent().getAttributes().get("branchStaffId").toString();	
			BranchstaffmemberModel staff=staffBO.getBranchstaffmemberDetails(Integer.parseInt(branchStaffId));
			deleteSuccess=staffBO.deleteStaff(staff);
			resetSearchBranchStaff();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public void resetBranchStaff()
	{
		BranchStaffBeanInfo branchStaffBeanInfo = new BranchStaffBeanInfo();
		branchStaffBeanInfo.resetBranchStaff();		
	}
	
	
	
	public void searchBranchStaff()
	{
		try
		{
			this.getBranchstafflist();
		}
		catch(Exception e)
		{
			
		}
	}
	
	
	public void resetSearchBranchStaff()
	{
		
		this.branchId=0;
		this.identificationNo=null;
		this.roleId=0;
		this.status=null;
		this.getBranchstafflist();
	}
	
	
	public void editBranchStaff(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	
	try {
		String branchStaffId = "";
		branchStaffId = (String) event.getComponent().getAttributes().get("branchStaffId").toString();		
		BranchStaffBeanInfo branchStaffBeanInfo = new BranchStaffBeanInfo();
		branchStaffBeanInfo.editBranchStaff(Integer.parseInt(branchStaffId));
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"branchStaffPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	
	public void editMyProfile(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	
	try {
		String branchStaffId = "";
		branchStaffId = (String) event.getComponent().getAttributes().get("branchStaffId").toString();		
		BranchStaffBeanInfo branchStaffBeanInfo = new BranchStaffBeanInfo();
		branchStaffBeanInfo.editMyProfile(Integer.parseInt(branchStaffId));
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"branchStaffPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	 public void upload(FileUploadEvent event) {  		      
	    	FacesContext context = FacesContext.getCurrentInstance();			    	 	  
	        try {
	            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }	       		        
	        context.addMessage(UIComponent.findComponent(context.getViewRoot(),"branchStaffPanel").getClientId(context),
	       	new FacesMessage(FacesMessage.SEVERITY_INFO, "Success! ",  event.getFile().getFileName() + " is uploaded."));	             
	        
	    }  
	
	
	 public void copyFile(String fileName, InputStream in) {
     try {
     	ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
     	BranchStaffBean branchStaffBean = (BranchStaffBean) BeanContext.getReference("branchStaffBean");	
			fileName = branchstaff.getIdentificationNumber() + ".jpg";
			String trainingDir = servletContext.getRealPath("")	+ File.separator + "photo" + File.separator + "staff"+ File.separator ;
		
			File trDir = new File(trainingDir);   
			if(!trDir.exists())
			{
				trDir.mkdir();
			}     			
			
          OutputStream out = new FileOutputStream(new File(trainingDir + fileName));             
          int read = 0;
          byte[] bytes = new byte[1024];
       
          while ((read = in.read(bytes)) != -1) {
              out.write(bytes, 0, read);
          }             
          in.close();
          out.flush();
          out.close();             
          branchstaff.setPhotoName(fileName);
          } catch (IOException e) {
          System.out.println(e.getMessage());
          }           
		} 

	
	
}
