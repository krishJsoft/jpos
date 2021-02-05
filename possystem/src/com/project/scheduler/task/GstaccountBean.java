package com.project.scheduler.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.data.PageEvent;
import org.primefaces.model.LazyDataModel;

import com.project.bean.commission.DoctorCommissionBean;

import com.project.bo.interfaces.IGstaccounBO;
import com.project.common.config.Configuration;

import com.project.common.factory.BeanContext;
import com.project.common.util.DateUtil;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.login.LoginBean;
import com.project.model.paginghelper.GstaccountPagingHelper;
import com.project.model.paginghelper.GstaccountpurchasePagingHelper;
import com.project.model.paginghelper.GstaccountsalesPagingHelper;
import com.project.model.tax.GstaccountModel;
import com.project.model.tax.GstpurchaseaccountbreakdownModel;
import com.project.model.tax.GstsalesaccountbreakdownModel;

@ManagedBean(name = "gstaccountBean")
@SessionScoped
public class GstaccountBean {

	
	private String status;
	private Date dateFrom;
	private Date dateTo;
	private Integer staffId;
	protected int first;  
	int activeIndex = 0;

	Configuration config = Configuration.getConfiguration();
	ObjectMapController objectMapController = (ObjectMapController) BeanContext	.getReference("objectMapController");
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	List<SelectItem> statuses = new ArrayList<SelectItem>();
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	private LazyDataModel<GstaccountModel> gstaccountModel = null;
	private LazyDataModel<GstpurchaseaccountbreakdownModel> gstaccountpurchaseModel = null;
	private LazyDataModel<GstsalesaccountbreakdownModel> gstaccountsalesModel = null;
	
	GstaccountModel gst= new GstaccountModel();

	IGstaccounBO gstaccounBO=objectMapController.getGstaccounBO();
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
		    
	 public LazyDataModel<GstaccountModel> getGstaccountModel() {
		return gstaccountModel;
	}

	public void setGstaccountModel(LazyDataModel<GstaccountModel> gstaccountModel) {
		this.gstaccountModel = gstaccountModel;
	}

	public GstaccountModel getGst() {
		return gst;
	}

	public void setGst(GstaccountModel gst) {
		this.gst = gst;
	}

	public int getFirst() {  
	        return first;  
	   }  
	 public void setFirst(int first) {  
	         this.first = first;  
	    }  
	 public void onPageChange(PageEvent event) {  
	         this.setFirst(((DataTable) event.getSource()).getFirst());  
	         }  

		
	
	public int getActiveIndex() {
		return activeIndex;
	}

	public void setActiveIndex(int activeIndex) {
		this.activeIndex = activeIndex;
	}	
	
	
	public LazyDataModel<GstpurchaseaccountbreakdownModel> getGstaccountpurchaseModel() {
		return gstaccountpurchaseModel;
	}

	public void setGstaccountpurchaseModel(
			LazyDataModel<GstpurchaseaccountbreakdownModel> gstaccountpurchaseModel) {
		this.gstaccountpurchaseModel = gstaccountpurchaseModel;
	}

	public LazyDataModel<GstsalesaccountbreakdownModel> getGstaccountsalesModel() {
		return gstaccountsalesModel;
	}

	public void setGstaccountsalesModel(
			LazyDataModel<GstsalesaccountbreakdownModel> gstaccountsalesModel) {
		this.gstaccountsalesModel = gstaccountsalesModel;
	}

	public LazyDataModel<GstpurchaseaccountbreakdownModel> getGstAccountPurchaseList() {
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{		
		this.setGstaccountpurchaseModel(null);	
		gstaccountpurchaseModel = new GstaccountpurchasePagingHelper(null,gst.getAccounttaxid(),gstaccounBO);		
		this.setGstaccountpurchaseModel(gstaccountpurchaseModel);	
		}
		catch(Exception e){					
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"commissionPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
			}
		return gstaccountpurchaseModel;
	}
	
	
	
	public LazyDataModel<GstsalesaccountbreakdownModel> getGstAccountSalesList() {
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{		
		this.setGstaccountsalesModel(null);	
		gstaccountsalesModel = new GstaccountsalesPagingHelper(null,gst.getAccounttaxid(),gstaccounBO);		
		this.setGstaccountsalesModel(gstaccountsalesModel);	
		}
		catch(Exception e){					
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"commissionPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
			}
		return gstaccountsalesModel;
	}
	
	
	

	public LazyDataModel<GstaccountModel> getGstAccountList() {
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{		
		this.setGstaccountModel(null);	
		gstaccountModel = new GstaccountPagingHelper(loginBean.getBranch().getBranchId(),dateFrom, dateTo,status,gstaccounBO);		
		this.setGstaccountModel(gstaccountModel);	
		}
		catch(Exception e){					
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"commissionPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
			}
		return gstaccountModel;
	}
	
	
	public void searchGst()
	{		
		try
		{   if(!this.validateTaxSearch())
		{
			this.setDateFrom(DateUtil.getFromTodayDateTime());
			this.setDateTo(DateUtil.getToTodayDateTime());
		}
			this.getGstAccountList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void resetGstSearch()
	{
		this.setStaffId(0);		
		this.setDateFrom(DateUtil.getFromTodayDateTime());
		this.setDateTo(DateUtil.getToTodayDateTime());
		this.setStatus(null);		
		searchGst();
	}
	
	
	public boolean validateTaxSearch() {
		boolean valid = true;			
		
		if(this.getDateFrom()==null &&this.getDateTo()==null)
		{	
			valid = false;
		}			
		else
		{
			valid = true;
		}			
		return valid;

	}
	
	
	 public void onCommisionTabChange(TabChangeEvent event) {  
	        FacesMessage msg = new FacesMessage("Tab Changed", "Active Tab: " + event.getTab().getTitle());  	      
	        if (this.getActiveIndex() == 1) {
	        	
			}
	        
	    }  
	 
	
	 public void viewCommission(ActionEvent event) throws Exception 
		{
		FacesContext context = FacesContext.getCurrentInstance();
		try {		
			gst = (GstaccountModel) event.getComponent().getAttributes().get("commission");									
			gst=gstaccounBO.getGstaccountMasterDetails(gst.getAccounttaxid(), loginBean.getBranch().getBranchId());			
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"commissionPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
			}
		}	
	 
	 
	 public void viewGstAccountSales(ActionEvent event) throws Exception 
		{
		FacesContext context = FacesContext.getCurrentInstance();
		try {		
			gst = (GstaccountModel) event.getComponent().getAttributes().get("sales");									
			gst=gstaccounBO.getGstaccountMasterDetails(gst.getAccounttaxid(), loginBean.getBranch().getBranchId());			
			getGstAccountSalesList();
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"commissionPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
			}
		}	
	 
	 
	 public void viewGstAccountPurchse(ActionEvent event) throws Exception 
		{
		FacesContext context = FacesContext.getCurrentInstance();
		try {		
			gst = (GstaccountModel) event.getComponent().getAttributes().get("purchase");									
			gst=gstaccounBO.getGstaccountMasterDetails(gst.getAccounttaxid(), loginBean.getBranch().getBranchId());			
			getGstAccountPurchaseList();
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"commissionPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
			}
		}	
	 
	 
	 public void printCommission(ActionEvent event) {
			
			FacesContext faces = FacesContext.getCurrentInstance();	
			try{				
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 			
			gst = (GstaccountModel) event.getComponent().getAttributes().get("commission");	
			//faces.getExternalContext().redirect(request.getScheme() + "://" + request.getServerName() +":" + request.getServerPort() +"/"+config.getValue(IConfiguration.PROJECT_INSTANCE_NAME)+"/frameset?__report=report/commission/commissiondetailReport.rptdesign&__format=pdf&&__pageoverflow=0&__asattachment=true&__overwrite=false&commissionId=" + commission.getCommissionId()+"&userId="+loginBean.getUserId()+"&commissionType="+commission.getCommissionType());
			faces.responseComplete();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
	 
}
