package com.project.bean.commission;

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

import com.project.bo.interfaces.ICommissionBO;
import com.project.model.commission.CommissionModel;
import com.project.model.payment.collection.PaymentcollectionModel;
import com.project.model.sale.sales.DoctorsPrescriptionsBreakdownModel;
import com.project.model.sale.sales.SalesorderModel;
import com.project.bean.payment.collection.PaymentcollectionBean;
import com.project.bean.payment.collection.PaymentcollectionBeanInfo;
import com.project.bean.sales.quotation.QuotationBean;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.util.DateUtil;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.login.LoginBean;
import com.project.model.paginghelper.CommissionPagingHelper;
import com.project.model.paginghelper.PaymentcollectionPagingHelper;

@ManagedBean(name = "doctorCommissionBean")
@SessionScoped
public class DoctorCommissionBean {

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
	private LazyDataModel<CommissionModel> commissionModel = null;
	CommissionModel commission= new CommissionModel();

	ICommissionBO commissionBO=objectMapController.getCommissionBO();
	
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

	public LazyDataModel<CommissionModel> getCommissionModel() {
		return commissionModel;
	}

	public void setCommissionModel(
			LazyDataModel<CommissionModel> commissionModel) {
		this.commissionModel = commissionModel;
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

	
	public CommissionModel getCommission() {
		return commission;
	}

	public void setCommission(CommissionModel commission) {
		this.commission = commission;
	}

	public LazyDataModel<CommissionModel> getCommissionList() {
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{		
		this.setCommissionModel(null);	
		commissionModel = new CommissionPagingHelper(dateFrom, dateTo,status,staffId,commissionBO,loginBean.getBranch().getBranchId());		
		this.setCommissionModel(commissionModel);	
		}
		catch(Exception e){					
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"commissionPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
			}
		return commissionModel;
	}
	
	
	public void searchCommission()
	{		
		try
		{   if(!this.validateCommissionSearch())
		{
			this.setDateFrom(DateUtil.getFromTodayDateTime());
			this.setDateTo(DateUtil.getToTodayDateTime());
		}
			this.getCommissionList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void resetCommissionSearch()
	{
		this.setStaffId(0);		
		this.setDateFrom(DateUtil.getFromTodayDateTime());
		this.setDateTo(DateUtil.getToTodayDateTime());
		this.setStatus(null);		
		searchCommission();
	}
	
	
	public boolean validateCommissionSearch() {
		boolean valid = true;		
	
		DoctorCommissionBean doctorCommissionBean = (DoctorCommissionBean) BeanContext.getReference("doctorCommissionBean");
				
		if(doctorCommissionBean.getStaffId()!=null)
		{
			if(doctorCommissionBean.getStaffId()==0)
			{
				doctorCommissionBean.setStaffId(null);
			}
		}				
		if(doctorCommissionBean.getStatus()!=null)
		{
			if(doctorCommissionBean.getStatus().equalsIgnoreCase("") || doctorCommissionBean.getStatus().equalsIgnoreCase("0") )
			{
				doctorCommissionBean.setStatus(null);
			}
		}			
		if(doctorCommissionBean.getStaffId()==null &&  doctorCommissionBean.getDateFrom()==null &&doctorCommissionBean.getDateTo()==null && doctorCommissionBean.getStatus()==null  )
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
			commission = (CommissionModel) event.getComponent().getAttributes().get("commission");									
			commission=commissionBO.getCommissionDetails(commission.getCommissionId(), commission.getCommissionType());			
			
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
			commission = (CommissionModel) event.getComponent().getAttributes().get("commission");	
			faces.getExternalContext().redirect(request.getScheme() + "://" + request.getServerName() +":" + request.getServerPort() +"/"+config.getValue(IConfiguration.PROJECT_INSTANCE_NAME)+"/frameset?__report=report/commission/commissiondetailReport.rptdesign&__format=pdf&&__pageoverflow=0&__asattachment=true&__overwrite=false&commissionId=" + commission.getCommissionId()+"&userId="+loginBean.getUserId()+"&commissionType="+commission.getCommissionType());
			faces.responseComplete();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
	 
}
