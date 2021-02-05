package com.project.bean.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.data.PageEvent;
import org.primefaces.model.LazyDataModel;

import com.project.bo.impl.BranchBOImpl;
import com.project.bo.interfaces.ICustomerBO;
import com.project.model.datamodel.CustomerModel;
import com.project.model.datamodel.ProductModel;
import com.project.util.DateUtil;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.UIComponent;
import com.project.login.LoginBean;
import com.project.model.paginghelper.CustomerPagingHelper;
import com.project.model.paginghelper.ProductPagingHelper;

/*
 *  @author Gopal
 * @version 1.0
 * @since 08 June 2013
 * 
 */

@ManagedBean(name = "customerBean")
@SessionScoped
public class CustomerBean {

	public static Logger log = LoggerFactory.getLogger(CustomerBean.class);

	CustomerModel customer= new CustomerModel();
	List<CustomerModel> customerlist = new ArrayList<CustomerModel>();
	List<CustomerModel> customerListObj = new ArrayList<CustomerModel>();
	private LazyDataModel<CustomerModel> customerModel = null;
	FacesContext context = FacesContext.getCurrentInstance();
	List<CustomerModel> filterCustomer = new ArrayList<CustomerModel>();
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	Configuration config = Configuration.getConfiguration();
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	int activeIndex = 0;
	boolean tabStep1 = false;
	boolean tabStep2 = true;
	boolean tabStep3 = true;	
	boolean nextActionStatus = false;
	boolean previousActionStatus = true;
	String action = "submit";
	String nextaction = "";
	int activeIndex1=0;
	String loyaltyCardCode;
	
	private String  identificationCompanyRegNo;	
	private Integer branchId;
	private String  status;
	private String  customerName;
	
	
	private ICustomerBO customerBO=objectMapController.getCustomerBO();	
	
	 protected int first;  
	    
	    public int getFirst() {  
	         return first;  
	    }  
	    public void setFirst(int first) {  
	         this.first = first;  
	    }  
	    public void onPageChange(PageEvent event) {  
	         this.setFirst(((DataTable) event.getSource()).getFirst());  
	    }  
	    

	public ICustomerBO getCustomerBO() {
		return customerBO;	}

	public void setCustomerBO(ICustomerBO customerBO) {
		this.customerBO = customerBO;
	}
	
	public CustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

	
	public List<CustomerModel> getFilterCustomer() {
		return filterCustomer;
	}

	public void setFilterCustomer(List<CustomerModel> filterCustomer) {
		this.filterCustomer = filterCustomer;
	}

	
	
	/*public List<CustomerModel> getCustomerlist() {
		try
		{	
		customerlist.clear();
		if(this.validateCustomerSearch())	
		{	
		    customerlist=customerBO.findByCustomerList(identificationCompanyRegNo, status,customerName);
			this.setCustomerListObj(customerlist);
		}
		}
		catch(Exception e){					
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"customerPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
		}
		return customerlist;
	}*/
	
	public String getLoyaltyCardCode() {
		return loyaltyCardCode;
	}
	public void setLoyaltyCardCode(String loyaltyCardCode) {
		this.loyaltyCardCode = loyaltyCardCode;
	}
	public LazyDataModel<CustomerModel> getCustomerSearchList() {	
		try
		{
		this.setCustomerModel(null);			
		customerModel = new CustomerPagingHelper(customerBO,identificationCompanyRegNo, status,customerName,loyaltyCardCode);			
		this.setCustomerModel(customerModel);	
		}
		catch(Exception e){					
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"customerPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
			}
		return customerModel;
	}
	

	public void setCustomerlist(List<CustomerModel> customerlist) {
		this.customerlist = customerlist;
	}

	public List<CustomerModel> getCustomerListObj() {
		return customerListObj;
	}

	public void setCustomerListObj(List<CustomerModel> customerListObj) {
		this.customerListObj = customerListObj;
	}	

	public LazyDataModel<CustomerModel> getCustomerModel() {
		return customerModel;
	}

	public void setCustomerModel(LazyDataModel<CustomerModel> customerModel) {
		this.customerModel = customerModel;
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

	public int getActiveIndex1() {
		return activeIndex1;
	}

	public void setActiveIndex1(int activeIndex1) {
		this.activeIndex1 = activeIndex1;
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
	
	public String getIdentificationCompanyRegNo() {
		return identificationCompanyRegNo;
	}

	public void setIdentificationCompanyRegNo(String identificationCompanyRegNo) {
		this.identificationCompanyRegNo = identificationCompanyRegNo;
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void onTabChange(TabChangeEvent event) {
		CustomerBeanInfo customerBeanInfo = new CustomerBeanInfo();
		customerBeanInfo.onTabChange(event);
	}
	
	
	
	public void nextAction(ActionEvent event) {

		CustomerBeanInfo customerBeanInfo = new CustomerBeanInfo();
		//customerBeanInfo.nextAction1(event);
	}

	public void previousAction(ActionEvent event) {

		CustomerBeanInfo customerBeanInfo = new CustomerBeanInfo();
		//customerBeanInfo.previousAction(event);
	}
	
	public String onFlowProcess(FlowEvent event) {  
        log.info("Current wizard step:" + event.getOldStep());  
        log.info("Next step:" + event.getNewStep());  
        
        CustomerBeanInfo customerBeanInfo = new CustomerBeanInfo();		
        
         return customerBeanInfo.onFlowProcess(event);
       
    }  	
	
	public void saveCustomer()
	{
		CustomerBeanInfo customerBeanInfo = new CustomerBeanInfo();
		customerBeanInfo.saveCustomer();		
	}
	public void updateCustomer()
	{
		CustomerBeanInfo customerBeanInfo = new CustomerBeanInfo();
		customerBeanInfo.updateCustomer();		
	}
	public void deleteBranch()
	{
		CustomerBeanInfo customerBeanInfo = new CustomerBeanInfo();
		customerBeanInfo.deleteCustomer();		
	}	
	public void resetCustomer()
	{
		CustomerBeanInfo customerBeanInfo = new CustomerBeanInfo();
		customerBeanInfo.resetCustomer();
		
	}
	
	public void resetStep1()
	{		
		customer.setTitle(null);
		customer.setIdentificationCompanyRegNo(null);
		customer.setCustomerName(null);
		customer.setBusiness(null);
		customer.setAddress(null);		
		customer.setEmailAddress(null);
		customer.setCity(null);
		customer.setState(null);
		customer.setCountry(null);
		customer.setPhoneNo(null);
		customer.setMobileNo(null);
		customer.setPostCode(null);
		customer.setFaxNo(null);
		customer.setContactPerson(null);		
		this.setAction("submit");
	}
	
	public void resetStep2()
	{		
		customer.setBranchId(0);
		customer.setPricingCurrency(null);		
		customer.setSalesRep(null);
		customer.setDiscount(new BigDecimal("0.00"));
		customer.setNotifyBy(null);
		customer.setDeliveryMethod(null);
		customer.setPaymentTerm(null);
		customer.setPaymentMethod(null);		
		customer.setLoyaltyCardCode(null);
		customer.setLastRedemptionDate(null);
		customer.setAvailablePoints(0);	
		this.setAction("submit");
	}	
	
	public void listCustomer() {		
		this.searchCustomer();
		CustomerBeanInfo customerBeanInfo = new CustomerBeanInfo();
		customerBeanInfo.listCustomer();
	}
	public void newCustomer() {
		CustomerBeanInfo customerBeanInfo = new CustomerBeanInfo();
		customerBeanInfo.newCustomer();	
		resetStep1();
		resetStep2();
	}
	
	public void searchCustomer()
	{
		try
		{
			this.getCustomerSearchList();
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void resetSearchCustomer()
	{
		this.branchId=0;		
		this.status=null;
		this.identificationCompanyRegNo=null;
		this.customerName=null;
		this.loyaltyCardCode=null;
		this.getCustomerSearchList();
	}

	
	public void editCustomer(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();	
	try {
		String customerId = "";
		customerId = (String) event.getComponent().getAttributes().get("customerId").toString();		
		
		CustomerBeanInfo customerBeanInfo = new CustomerBeanInfo();
		customerBeanInfo.editCustomer(Integer.parseInt(customerId));
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"customerPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	
	public void viewCustomer(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();	
	try {
		String customerId = "";
		customerId = (String) event.getComponent().getAttributes().get("customerId").toString();			
		customer=customerBO.getCustomerDetails(Integer.parseInt(customerId));
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"customerPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	
	 
	
	public boolean validateCustomerSearch() {
		boolean valid = true;	
		
		
		if(identificationCompanyRegNo!=null)
		{
			if(identificationCompanyRegNo.equalsIgnoreCase(""))
			{
				this.setIdentificationCompanyRegNo(null);
			}
		}				
		if(customerName!=null)
		{
			if(customerName.equalsIgnoreCase(""))
			{
				this.setCustomerName(null);
			}
		}				
		
		if(this.getStatus()!=null)
		{
			if(this.getStatus().equalsIgnoreCase(""))
			{
				this.setStatus(null);
			}
		}		
		
		if( this.getStatus()==null  && this.getIdentificationCompanyRegNo()==null && this.getCustomerName()==null)
		{	
			valid = false;
		}		
		
		else
		{
			valid = true;
		}			
		return valid;

	}

	
	 public void upload(FileUploadEvent event) {  		      
	    	FacesContext context = FacesContext.getCurrentInstance();			    	 	  
	        try {
	            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }	       		        
	        context.addMessage(UIComponent.findComponent(context.getViewRoot(),"customerPanel").getClientId(context),
	       	new FacesMessage(FacesMessage.SEVERITY_INFO, "Success! ",  event.getFile().getFileName() + " is uploaded."));	             
	        
	    }  
	
	
	 public void copyFile(String fileName, InputStream in) {
        try {
        	ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
     	    CustomerBean customerBean = (CustomerBean) BeanContext.getReference("customerBean");	
			fileName = customerBean.getCustomer().getIdentificationCompanyRegNo() + ".jpg";
			String trainingDir = servletContext.getRealPath("")	+ File.separator + "photo" + File.separator + "customer"+ File.separator ;
		
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
             customerBean.getCustomer().setPhotoName(fileName);
             } catch (IOException e) {
             System.out.println(e.getMessage());
             }           
 		} 
 
	
	 public void printCustomer(ActionEvent event) {
			
			FacesContext faces = FacesContext.getCurrentInstance();	
			try{				
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 			
			String customerId = "";
			customerId = (String) event.getComponent().getAttributes().get("customerId").toString();			
			faces.getExternalContext().redirect(request.getScheme() + "://" + request.getServerName() +":" + request.getServerPort() +"/"+config.getValue(IConfiguration.PROJECT_INSTANCE_NAME)+"/frameset?__report=/report/admin/customerDetail.rptdesign&__format=pdf&&__pageoverflow=0&__asattachment=true&__overwrite=false&customerId=" + customerId+"&userId="+loginBean.getUserId() );
			faces.responseComplete();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
	
	
	
	
}
