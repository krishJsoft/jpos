package com.project.bean.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedProperty;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.LazyDataModel;

import com.project.bo.interfaces.IBranchBO;
import com.project.model.datamodel.BranchModel;
import com.project.util.DateUtil;

import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.UIComponent;
import com.project.login.LoginBean;


/*
 * @author Gopal Ar
 * @version 1.0
 * @since 08 June 2013
 * 
 */

@ManagedBean(name = "branchBean")
@SessionScoped
public class BranchBean {

	private String branchName;
	private String city;
	private String status;
	private String state;
	private String action = "submit";
	private int branchId;
	BranchModel branch = new BranchModel();
	List<BranchModel> branchList = new ArrayList<BranchModel>();
	List<BranchModel> branchListObj = new ArrayList<BranchModel>();
	private LazyDataModel<BranchModel> lazyBranchModel;
	Configuration config = Configuration.getConfiguration();
	
	FacesContext context = FacesContext.getCurrentInstance();
	List<BranchModel> filterBranch = new ArrayList<BranchModel>();
	private int activeIndex=0;
	int activeIndex1=0;
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	
	private IBranchBO branchBO=objectMapController.getBranchBO();
	
	public IBranchBO getBranchBO() {
		return branchBO;
	}

	public void setBranchBO(IBranchBO branchBO) {
		this.branchBO = branchBO;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public BranchModel getBranch() {
		return branch;
	}

	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}	
	
	public int getActiveIndex() {
		return activeIndex;
	}

	public void setActiveIndex(int activeIndex) {
		this.activeIndex = activeIndex;
	}

	
	public int getActiveIndex1() {
		return activeIndex1;
	}

	public void setActiveIndex1(int activeIndex1) {
		this.activeIndex1 = activeIndex1;
	}

	public List<BranchModel> getFilterBranch() {
		return filterBranch;
	}

	public void setFilterBranch(List<BranchModel> filterBranch) {
		this.filterBranch = filterBranch;
	}

	public List<BranchModel> getBranchList() {		
		try
		{	branchList.clear();
			
			branchList=branchBO.findByBranchList(branchId, city, state, status);
			this.setBranchListObj(branchList);
			this.resetSearchBranch();
		}
		catch(Exception e){
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"branchPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
		}
		return branchList;
	}

	public void setBranchList(List<BranchModel> branchList) {
		this.branchList = branchList;
	}	
	
	public List<BranchModel> getBranchListObj() {
		return branchListObj;
	}

	public void setBranchListObj(List<BranchModel> branchListObj) {
		this.branchListObj = branchListObj;
	}

	public LazyDataModel<BranchModel> getLazyBranchModel() {
		return lazyBranchModel;
	}

	public void setLazyBranchModel(LazyDataModel<BranchModel> lazyBranchModel) {
		this.lazyBranchModel = lazyBranchModel;
	}

	public void listBranch() {		
		this.searchBranch();
		BranchBeanInfo branchBeanInfo = new BranchBeanInfo();
		branchBeanInfo.listBranch();
	}

	public void newBranch() {
		BranchBeanInfo branchBeanInfo = new BranchBeanInfo();
		branchBeanInfo.newBranch();	
		resetBranch();
	}
	
	public void saveBranch()
	{
		BranchBeanInfo branchBeanInfo = new BranchBeanInfo();
		branchBeanInfo.saveBranch();		
	}
	public void updateBranch()
	{
		BranchBeanInfo branchBeanInfo = new BranchBeanInfo();
		branchBeanInfo.updateBranch();		
	}
	
	public void updateOranization()
	{
		BranchBeanInfo branchBeanInfo = new BranchBeanInfo();
		branchBeanInfo.updateOranization();		
	}
	
	
	public void deleteBranch(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	try {
		String branchId = "";
		branchId = (String) event.getComponent().getAttributes().get("branchId").toString();	
		
		BranchBeanInfo branchBeanInfo = new BranchBeanInfo();
		branchBeanInfo.deleteBranch(Integer.parseInt(branchId));	
		getBranchList();
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"branchPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	
	public void resetBranch()
	{
		BranchBeanInfo branchBeanInfo = new BranchBeanInfo();
		branchBeanInfo.resetBranch();
		
	}
	
	public void searchBranch()
	{
		try
		{
			this.getBranchList();
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void resetSearchBranch()
	{
		this.branchId=0;
		this.city=null;
		this.state=null;
		this.status=null;
		this.getBranchList();
	}

	
	public void editBranch(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	//this.setMessage(null);
	try {
		String branchId = "";
		branchId = (String) event.getComponent().getAttributes().get("branchId").toString();		
		//this.setBranchId(Integer.parseInt(branchId));
		BranchBeanInfo branchBeanInfo = new BranchBeanInfo();
		branchBeanInfo.editBranch(Integer.parseInt(branchId));
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"branchPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	
	public void viewBranch(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();	
	try {
		String branchId = "";
		branchId = (String) event.getComponent().getAttributes().get("branchId").toString();		
		this.setBranchId(Integer.parseInt(branchId));
		branch=branchBO.getBranchDetails(Integer.parseInt(branchId));
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"branchPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	public void handleClose(CloseEvent event) {
		resetSearchBranch();
    }
	
	
	 
	 
	 
	 public void upload(FileUploadEvent event) {  		      
	    	FacesContext context = FacesContext.getCurrentInstance();			    	 	  
	        try {
	            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }	       		        
	        context.addMessage(UIComponent.findComponent(context.getViewRoot(),"branchPanel").getClientId(context),
	       	new FacesMessage(FacesMessage.SEVERITY_INFO, "Success! ",  event.getFile().getFileName() + " is uploaded."));	             
	        
	    }  
	
	
	 public void copyFile(String fileName, InputStream in) {
		 try {
  			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			fileName = "companylogo.jpg";
			String trainingDir = servletContext.getRealPath("")	+ File.separator + "images"+ File.separator ;
			System.out.println("Local "  +trainingDir);
			InputStream rein =in;
			File trDir = new File(trainingDir);   
			if(!trDir.exists())
			{
				trDir.mkdir();
			}     			
			
       OutputStream out = new FileOutputStream(new File(trainingDir + fileName));   
       
        FacesContext context = FacesContext.getCurrentInstance();	
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();		
		String reportDir = servletContext.getRealPath("")	+ File.separator + "report"  + File.separator + "images"+ File.separator ;
		
		System.out.println("Report "  +reportDir);
		System.out.println("Name "  +servletContext.getServletContextName());
		reportDir=reportDir.replace(servletContext.getServletContextName(), config.getValue(IConfiguration.PROJECT_INSTANCE_NAME));
		System.out.println("Report "  +reportDir);
		
	  OutputStream reportout = new FileOutputStream(new File(reportDir + fileName));   
		 
       int read = 0;
       byte[] bytes = new byte[1024];
    
       while ((read = in.read(bytes)) != -1) {
           out.write(bytes, 0, read);
           reportout.write(bytes, 0, read);
       }             
       in.close();
       out.flush();
       out.close(); 
       reportout.flush();
       reportout.close();
       System.out.println("Success ");
       
       } catch (IOException e) {
       System.out.println(e.getMessage());
       }           
		} 

	
	 
	
}
