package com.project.bean.admin;

import java.io.File;
import java.io.FileInputStream;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.io.FileUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.project.bo.interfaces.ISupplierBO;
import com.project.model.datamodel.SupplierModel;
import com.project.model.datamodel.SupplierdocumentModel;
import com.project.util.DateUtil;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.util.ObjectMapController;
import com.project.common.util.UploadFileFilter;
import com.project.common.validation.UIComponent;

/*
 *  @author Gopal
 * @version 1.0
 * @since 08 June 2013
 * 
 */

@ManagedBean(name = "supplierBean")
@SessionScoped
public class SupplierBean {

	public static Logger log = LoggerFactory.getLogger(SupplierBean.class);

	SupplierModel supplier= new SupplierModel();
	List<SupplierModel> supplierlist = new ArrayList<SupplierModel>();
	List<SupplierModel> supplierListObj = new ArrayList<SupplierModel>();
	FacesContext context = FacesContext.getCurrentInstance();
	List<SupplierdocumentModel> doclist = new ArrayList<SupplierdocumentModel>();
	
	List<SupplierModel> filterSupplier = new ArrayList<SupplierModel>();
	UploadFileFilter fileFilter = new UploadFileFilter();
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");

	Configuration config = Configuration.getConfiguration();
	
	String fileLocation;
	int activeIndex = 0;
	boolean tabStep1 = false;
	boolean tabStep2 = true;
	boolean tabStep3 = true;	
	boolean nextActionStatus = false;
	boolean previousActionStatus = true;
	String action = "submit";
	String nextaction = "";
	private StreamedContent file;
	private String  companyRegNo;
	private String  status;
	private String documentName;
	int activeIndex1 = 0;
	
	
	ISupplierBO supplierBO=objectMapController.getSupplierBO();	
	
	public SupplierModel getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierModel supplier) {
		this.supplier = supplier;
	}
	
	public List<SupplierModel> getFilterSupplier() {
		return filterSupplier;
	}

	public void setFilterSupplier(List<SupplierModel> filterSupplier) {
		this.filterSupplier = filterSupplier;
	}	

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	
	
	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
	
	public ServletContext getServeletContx()
	{
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext(); 	

		return servletContext;
	}

	public List<SupplierModel> getSupplierlist() {
		
		try
		{	supplierlist.clear();
		    supplierlist=supplierBO.findBySupplierList(companyRegNo, status);
			this.setSupplierListObj(supplierlist);
		}
		catch(Exception e){					
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"supplierPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
		}
		return supplierlist;
				
	}

	public void setSupplierlist(List<SupplierModel> supplierlist) {
		this.supplierlist = supplierlist;
	}

	public List<SupplierModel> getSupplierListObj() {
		return supplierListObj;
	}

	public void setSupplierListObj(List<SupplierModel> supplierListObj) {
		this.supplierListObj = supplierListObj;
	}

	public ISupplierBO getSupplierBO() {
		return supplierBO;
	}

	public void setSupplierBO(ISupplierBO supplierBO) {
		this.supplierBO = supplierBO;
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
	
	
	
	

	public int getActiveIndex1() {
		return activeIndex1;
	}

	public void setActiveIndex1(int activeIndex1) {
		this.activeIndex1 = activeIndex1;
	}

	public String getCompanyRegNo() {
		return companyRegNo;
	}

	public void setCompanyRegNo(String companyRegNo) {
		this.companyRegNo = companyRegNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}	
	
	public List<SupplierdocumentModel> getDoclist() {
		return doclist;
	}

	public void setDoclist(List<SupplierdocumentModel> doclist) {
		this.doclist = doclist;
	}
		


	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
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
        
        SupplierBeanInfo supplierBeanInfo = new SupplierBeanInfo();	        
         return supplierBeanInfo.onFlowProcess(event);
       
    }  	
	
	public void saveSupplier()
	{
		 SupplierBeanInfo supplierBeanInfo = new SupplierBeanInfo();	        
		 supplierBeanInfo.saveSupplier();		
	}
	public void updateSupplier()
	{
		SupplierBeanInfo supplierBeanInfo = new SupplierBeanInfo();	        
		supplierBeanInfo.updateSupplier();		
	}
	public void deleteSupplier()
	{
		SupplierBeanInfo supplierBeanInfo = new SupplierBeanInfo();
		supplierBeanInfo.deleteSupplier();		
	}	
	public void resetSupplier()
	{
		SupplierBeanInfo supplierBeanInfo = new SupplierBeanInfo();
		supplierBeanInfo.resetSupplier();
		
	}
	
	public void resetStep1()
	{		
		
		supplier.setAddress(null);		
		supplier.setEmail(null);
		supplier.setCity(null);
		supplier.setState(null);
		supplier.setCountry(null);
		supplier.setPhoneNo(null);
		supplier.setMobileNo(null);
		supplier.setPostCode(null);
		supplier.setFaxNo(null);
		supplier.setCompanyName(null);
		supplier.setCompanyRegNo(null);
		supplier.setSupplierName(null);
		supplier.setSupplierOldCode(null);
		supplier.setSupplierCode(null);
		supplier.setContactPerson(null);
		supplier.setContactPersonNumber(null);
		
	}
	
	public void resetStep2()
	{		
		supplier.setBankName(null);
		supplier.setBranchName(null);
		supplier.setAccountNumber(null);
		supplier.setRemarks(null);
		supplier.setDeliveryMethod(null);
		supplier.setTaxSchedule(null);
		supplier.setPaymentTerms(null);
		supplier.setStatus(null);
	}	
	
	public void listSupplier() {		
		this.searchSupplier();
		SupplierBeanInfo supplierBeanInfo = new SupplierBeanInfo();
		supplierBeanInfo.listSupplier();
	}
	public void newSupplier() {
		resetStep1();
		resetStep2();
		resetSupplier();
		SupplierBeanInfo supplierBeanInfo = new SupplierBeanInfo();
		supplierBeanInfo.newSupplier();	
		
	}
	
	public void searchSupplier()
	{
		try
		{
			this.getSupplierlist();
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void resetSearchSupplier()
	{
		this.companyRegNo=null;
		this.status=null;
		getSupplierlist();
	}

	
	public void editSupplier(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();	
	try {
		String supplierId = "";
		supplierId = (String) event.getComponent().getAttributes().get("supplierId").toString();		
		
		SupplierBeanInfo supplierBeanInfo = new SupplierBeanInfo();
		supplierBeanInfo.editSupplier(Integer.parseInt(supplierId));
		//this.setDoclist(supplier.getDoclist());
		  this.copyFrom();
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"supplierPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	public void viewSupplier(ActionEvent event) throws Exception 
	{
		FacesContext context = FacesContext.getCurrentInstance();	
		try {
			String supplierId = "";
			supplierId = (String) event.getComponent().getAttributes().get("supplierId").toString();			
			supplier=supplierBO.getSupplierDetails(Integer.parseInt(supplierId));
			  this.copyFrom();
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"supplierPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
			}
	}
	
	/* public void onSupplierTabChange(TabChangeEvent event) {  
	        FacesMessage msg = new FacesMessage("Tab Changed", "Active Tab: " + event.getTab().getTitle());   
	        PurchaseOrderBean purchaseOrderBean = (PurchaseOrderBean) BeanContext.getReference("purchaseOrderBean");	      
	        
	        if (this.getActiveIndex1() == 1) {
	        	purchaseOrderBean.setDateFrom(DateUtil.getFirstDate());
	        	purchaseOrderBean.setDateTo(DateUtil.getToTodayDateTime());
	        	purchaseOrderBean.setSupplierId(supplier.getSupplierId());
	        	purchaseOrderBean.searchPurchaseOrder();
			}
	        
	    }  */
	 
	

    public void upload(FileUploadEvent event) {  
      
    	FacesContext context = FacesContext.getCurrentInstance();	
    	String uploadtemplocation = config.getValue(IConfiguration.supplier_documentupload_location_temp);
    	String uploadlocation = config.getValue(IConfiguration.supplier_documentupload_location);
    	
    	uploadtemplocation =this.getServeletContx().getRealPath("")	+ File.separator + uploadtemplocation + File.separator;
    	uploadlocation =this.getServeletContx().getRealPath("")	+ File.separator + uploadlocation + File.separator;
    			  
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        SupplierdocumentModel doc = new SupplierdocumentModel();   
        doc.setCompanyName(supplier.getCompanyName());
        doc.setDocumentName(event.getFile().getFileName());      
        doc.setFileextention(fileFilter.getFileExtension(event.getFile().getFileName()));
        doc.setFilelocation(uploadlocation);
        doclist.add(doc);       
      
        context.addMessage(UIComponent.findComponent(context.getViewRoot(),"supplierPanel").getClientId(context),
       	new FacesMessage(FacesMessage.SEVERITY_INFO, "Success! ",  event.getFile().getFileName() + " is uploaded."));
              
        
    }  

    public void copyFile(String fileName, InputStream in) {
           try {
        	   String uploadtemplocation = config.getValue(IConfiguration.supplier_documentupload_location_temp);
        		String uploadlocation = config.getValue(IConfiguration.supplier_documentupload_location);
        		
        		uploadtemplocation =this.getServeletContx().getRealPath("")	+ File.separator + uploadtemplocation + File.separator;
        		uploadlocation =this.getServeletContx().getRealPath("")	+ File.separator + uploadlocation + File.separator;
        			
        		String destination=uploadtemplocation;    
        		
        		String trainingDir = destination + supplier.getCompanyRegNo()+"/";    			
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
                
                } catch (IOException e) {
                System.out.println(e.getMessage());
                }           
    		} 
    
	
   
    
    public void deletesupplierTempfile(File uploadtemplocation){
    	try
    	{

    		
    		String uploadlocation = config.getValue(IConfiguration.supplier_documentupload_location);    		
    		uploadlocation =this.getServeletContx().getRealPath("")	+ File.separator + uploadlocation + File.separator;   		
	
    		File[] c = uploadtemplocation.listFiles();
           
            for (File file : c){
                if (file.isDirectory()){                  
                	deletesupplierTempfile(file);
                    file.delete();
                } else {
                    file.delete();
                }
            }
    	}    	
    	catch(Exception e)
    	{
    		System.out.println(e.toString());
    	}
    	
    }
    
    
    public void deletesupplierfile(){
    	try
    	{
    		String uploadtemplocation = config.getValue(IConfiguration.supplier_documentupload_location_temp);
    		String uploadlocation = config.getValue(IConfiguration.supplier_documentupload_location);
    		
    		uploadtemplocation =this.getServeletContx().getRealPath("")	+ File.separator + uploadtemplocation + File.separator;
    		uploadlocation =this.getServeletContx().getRealPath("")	+ File.separator + uploadlocation + File.separator;
    			
    		File fin = new File(uploadlocation);
    		for(File f : fin.listFiles())
            {
                if(f.isDirectory()) 
                {
                	FileUtils.deleteDirectory(new File(f.getAbsolutePath()));
                    
                }
                else
                {
                    f.delete();
                }
            }
    	}    	
    	catch(Exception e)
    	{
    		System.out.println(e.toString());
    	}
    	
    }   
    
    
    public void deleteFileFromList(ActionEvent event)
    {
    	try
    	{
    		String uploadtemplocation = config.getValue(IConfiguration.supplier_documentupload_location_temp);
    		String uploadlocation = config.getValue(IConfiguration.supplier_documentupload_location);
    		
    		uploadtemplocation =this.getServeletContx().getRealPath("")	+ File.separator + uploadtemplocation + File.separator;
    		uploadlocation =this.getServeletContx().getRealPath("")	+ File.separator + uploadlocation + File.separator;
    			
    		String documentName = "";
    		documentName = (String) event.getComponent().getAttributes().get("documentName").toString();	    		   		
    		File fin = new File(uploadtemplocation+supplier.getCompanyRegNo()+"/"+documentName);
    		if(fin.exists())
    		{
    		fin.delete();
    		}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    
    public  void copyTo() {        
    	try
    	{
    		String uploadtemplocation = config.getValue(IConfiguration.supplier_documentupload_location_temp);
    		String uploadlocation = config.getValue(IConfiguration.supplier_documentupload_location);
    		
    		uploadtemplocation =this.getServeletContx().getRealPath("")	+ File.separator + uploadtemplocation + File.separator;
    		uploadlocation =this.getServeletContx().getRealPath("")	+ File.separator + uploadlocation + File.separator;
    			
    		File fin = new File(uploadtemplocation+supplier.getCompanyRegNo()+"/");    		
    		File dest = new File(uploadlocation);    		
    		if(!fin.exists()) {	 
    			fin.mkdir();
    		}
    			String trainingDir = dest.getAbsolutePath() + "/"+supplier.getCompanyRegNo();    			
    			File trDir = new File(trainingDir);   
    			if(!trDir.exists())
    			{
    				trDir.mkdir();
    			}   			
    			try {
    			    FileUtils.copyDirectory(fin, trDir);
    			} catch (IOException e) {
    			    e.printStackTrace();
    			} 	
    		
    	}    	
    	catch(Exception e)
    	{
    		System.out.println(e.toString());
    	}
    }
    
    
  
	public  void copyFrom() {        
    	try
    	{
    		String uploadtemplocation = config.getValue(IConfiguration.supplier_documentupload_location_temp);
    		String uploadlocation = config.getValue(IConfiguration.supplier_documentupload_location);
    		
    		uploadtemplocation =this.getServeletContx().getRealPath("")	+ File.separator + uploadtemplocation + File.separator;
    		uploadlocation =this.getServeletContx().getRealPath("")	+ File.separator + uploadlocation + File.separator;
    			
    		File fin = new File(uploadtemplocation+supplier.getCompanyRegNo()+"/");    		
    		File dest = new File(uploadlocation);    
    		doclist.clear();
    		if(!fin.exists()) {	  
    			fin.mkdir();
    		}
    		
    		 for(File file: fin.listFiles()) 
	    		{
    			 file.delete();
	    		}    		 
    			String trainingDir = dest.getAbsolutePath() + "/"+supplier.getCompanyRegNo();    			
    			File trDir = new File(trainingDir);   
    			if(!trDir.exists())
    			{
    				trDir.mkdir();
    			}   			
    			try {
    			    FileUtils.copyDirectory(trDir,fin);
    			    
    			    for(File file: trDir.listFiles()) 
    	    		{
    			    	if(fileFilter.accept(file))
    			    	{
    			    	SupplierdocumentModel doc = new SupplierdocumentModel();   
    			        doc.setCompanyName(supplier.getCompanyName());
    			        doc.setDocumentName(file.getName());      
    			        doc.setFileextention(fileFilter.getFileExtension(file.getName()));
    			        doc.setFilelocation(uploadlocation);
    			        doclist.add(doc);
    			    	}
    	    		}
    			    
    			    
    			} catch (IOException e) {
    			    e.printStackTrace();
    			} 	
    		
    	}    	
    	catch(Exception e)
    	{
    		System.out.println(e.toString());
    	}
    }
    
    
    
    public  void deleteBeforcopy() {        
    	try
    	{  
    		String uploadtemplocation = config.getValue(IConfiguration.supplier_documentupload_location_temp);
    		String uploadlocation = config.getValue(IConfiguration.supplier_documentupload_location);
    		
    		uploadtemplocation =this.getServeletContx().getRealPath("")	+ File.separator + uploadtemplocation + File.separator;
    		uploadlocation =this.getServeletContx().getRealPath("")	+ File.separator + uploadlocation + File.separator;
    			
    		File dest = new File(uploadlocation);       				
    			String trainingDir = dest.getAbsolutePath() + "/"+supplier.getCompanyRegNo();    			
    			File trDir = new File(trainingDir);   
    			if(trDir.exists())
    			{  			
    	    		for(File file: trDir.listFiles()) 
    	    		{
    	    			file.delete();
    	    		}
    			}     		
    	}    	
    	catch(Exception e)
    	{
    		System.out.println(e.toString());
    	}
    }
    

    
    public void downloadFileFromList(ActionEvent event)
    {
    	try
    	{    		
    		String uploadtemplocation = config.getValue(IConfiguration.supplier_documentupload_location_temp);
    		String uploadlocation = config.getValue(IConfiguration.supplier_documentupload_location);
    		
    		uploadtemplocation =this.getServeletContx().getRealPath("")	+ File.separator + uploadtemplocation + File.separator;
    		uploadlocation =this.getServeletContx().getRealPath("")	+ File.separator + uploadlocation + File.separator;
    			
    		String documentName = "";
    		documentName = (String) event.getComponent().getAttributes().get("documentName").toString();	    		   		
    		File fin = new File(uploadtemplocation+supplier.getCompanyRegNo()+"/"+documentName);
    		
    		InputStream stream =  new FileInputStream(fin);
            file = new DefaultStreamedContent(stream, fileFilter.getFileExtension(fin.getName()), documentName); 
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    public void viewFileFromList(ActionEvent event)
    {
    	try
    	
    	{    	
    		String uploadtemplocation = config.getValue(IConfiguration.supplier_documentupload_location_temp);
    		String uploadlocation = config.getValue(IConfiguration.supplier_documentupload_location);
    		
    		uploadtemplocation =this.getServeletContx().getRealPath("")	+ File.separator + uploadtemplocation + File.separator;
    		uploadlocation =this.getServeletContx().getRealPath("")	+ File.separator + uploadlocation + File.separator;
    			
    		String documentName = "";
    		documentName = (String) event.getComponent().getAttributes().get("documentName").toString();	    		   		
    		File fin = new File(uploadtemplocation+supplier.getCompanyRegNo()+"/"+documentName);
    		this.setFileLocation(uploadtemplocation+supplier.getCompanyRegNo()+"/"+documentName);
    		 
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }

}
