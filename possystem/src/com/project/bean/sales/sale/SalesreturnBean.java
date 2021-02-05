package com.project.bean.sales.sale;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.data.PageEvent;
import org.primefaces.model.LazyDataModel;

import com.project.bo.interfaces.IProductBO;
import com.project.bo.interfaces.IQuotationBO;
import com.project.bo.interfaces.ISalesorderBO;
import com.project.bo.interfaces.ISalesreturnBO;

import com.project.model.datamodel.CustomerModel;
import com.project.model.datamodel.stock.DeliveryorderModel;
import com.project.model.sale.sales.QuotationModel;
import com.project.model.sale.sales.SalesorderModel;
import com.project.model.sale.sales.SalesorderbreakdownModel;
import com.project.model.sale.sales.SalesorderreturnModel;
import com.project.model.sale.sales.SalesreturnModel;
import com.project.model.sale.sales.branchsale.BranchsalesbreakdownModel;
import com.project.util.DateUtil;
import com.project.bean.admin.CommonListBeanInfo;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.util.DecimalUtil;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.UIComponent;
import com.project.common.validation.ValidatorUtil;
import com.project.login.LoginBean;
import com.project.model.paginghelper.SalesReturnPagingHelper;

/*
 * @author Gopal Ar
 * @version 1.0
 * @since 30 Dec 2013
 */

@ManagedBean(name = "salesreturnBean")
@SessionScoped
public class SalesreturnBean {

	SalesorderModel salesorder = new SalesorderModel();
	List<SalesorderbreakdownModel> salesorderbreakdowns = new ArrayList<SalesorderbreakdownModel>();
	Configuration config = Configuration.getConfiguration();
	private Map<String, String> returnQuantity = new HashMap<String, String>();
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	SalesorderreturnModel salesreturn = new SalesorderreturnModel();
	ISalesorderBO salesOrderBO = objectMapController.getSalesOrderBO();
	ISalesreturnBO salesreturnBO = objectMapController.getSalesreturnBO();
	IProductBO productBo = objectMapController.getProductBO();
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	boolean saveConfirm=false;
	private Integer branchId;
	private Integer customerId;
	private Date dateFrom;
	private Date dateTo;
	private String status;
	private CustomerModel customer;
	BigDecimal returnTotal =new BigDecimal(0.00);
	
	private LazyDataModel<SalesorderreturnModel> salesreturnModel = null;
	
	 public ISalesreturnBO getSalesreturnBO() {
		return salesreturnBO;
	}
	public void setSalesreturnBO(ISalesreturnBO salesreturnBO) {
		this.salesreturnBO = salesreturnBO;
	}

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
	 
	
	public Integer getBranchId() {
		return branchId;
	}
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	private String salesOrderNo;
	
	private String salesOrdersNo;
	
		public String getSalesOrdersNo() {
		return salesOrdersNo;
	}
	public void setSalesOrdersNo(String salesOrdersNo) {
		this.salesOrdersNo = salesOrdersNo;
	}
	public SalesorderModel getSalesorder() {
		return salesorder;
	}

	public void setSalesorder(SalesorderModel salesorder) {
		this.salesorder = salesorder;
	}

	public Map<String, String> getReturnQuantity() {
		return returnQuantity;
	}

	public void setReturnQuantity(Map<String, String> returnQuantity) {
		this.returnQuantity = returnQuantity;
	}	
	
	public String getSalesOrderNo() {
		return salesOrderNo;
	}

	public void setSalesOrderNo(String salesOrderNo) {
		this.salesOrderNo = salesOrderNo;
	}	
	
	public boolean isSaveConfirm() {
		return saveConfirm;
	}

	public void setSaveConfirm(boolean saveConfirm) {
		this.saveConfirm = saveConfirm;
	}	

	public BigDecimal getReturnTotal() {
		return returnTotal;
	}
	public void setReturnTotal(BigDecimal returnTotal) {
		this.returnTotal = returnTotal;
	}
	public CustomerModel getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}
	public SalesorderreturnModel getSalesreturn() {
		return salesreturn;
	}
	public void setSalesreturn(SalesorderreturnModel salesreturn) {
		this.salesreturn = salesreturn;
	}
	public LazyDataModel<SalesorderreturnModel> getSalesreturnModel() {
		return salesreturnModel;
	}
	public void setSalesreturnModel(LazyDataModel<SalesorderreturnModel> salesreturnModel) {
		this.salesreturnModel = salesreturnModel;
	}
	
	
	public LazyDataModel<SalesorderreturnModel> getSalesorderReturnList() {
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{		
		this.setSalesreturnModel(null);	
		salesreturnModel = new SalesReturnPagingHelper(salesOrderNo,customerId,loginBean.getBranch().getBranchId(), dateFrom, dateTo,status,salesreturnBO);		
		this.setSalesreturnModel(salesreturnModel);	
		}
		catch(Exception e){					
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"salesreturnPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
			}
		return salesreturnModel;
	}	
	
	
	public void searchSalesOrder(ActionEvent event) throws Exception 
	{	
		FacesContext context = FacesContext.getCurrentInstance();		
		try {			
			returnQuantity.clear();			
			salesorder=salesreturnBO.getSalesorderMasterDetails(salesOrdersNo,loginBean.getBranch().getBranchId());	
			if(salesorder==null)
			{
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesOrderNo").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_ERROR,"Sales Order No , not exists!", null));
			}
							
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesreturnPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
			}
	}
	
	
	public void returnSalesOrder(ActionEvent event) throws Exception 
	{	
		FacesContext context = FacesContext.getCurrentInstance();		
		try {			
			returnQuantity.clear();			
			salesorder=salesreturnBO.getSalesorderMasterDetails(salesOrderNo,loginBean.getBranch().getBranchId());	
			if(salesorder==null)
			{
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesOrderNo").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_ERROR,"Sales Order No , not exists!", null));
			}
							
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesreturnPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
			}
	}
	
	
	public void viewSalesreturn(ActionEvent event)
	{		
		FacesContext context = FacesContext.getCurrentInstance();
		salesreturn = (SalesorderreturnModel) event.getComponent().getAttributes().get("sales");	
		try {				
			salesreturn=salesreturnBO.getSalesreturnDetails(salesreturn.getSalesreturnid());							
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesreturnPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			}		
	}
	
	
	public void approveSalesreturnConfirm(ActionEvent event)
	{		
		FacesContext context = FacesContext.getCurrentInstance();			
		try {				
			salesreturn = (SalesorderreturnModel) event.getComponent().getAttributes().get("sales");				
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesreturnPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			}		
	}
		
	
	public void approveSalesreturn()
	{		
		FacesContext context = FacesContext.getCurrentInstance();	
		boolean approvedSuccess=false;
		try {				
			salesreturn.setStatus(config.getValue(IConfiguration.RETURN_STATUS_PROCESSED_VALUE));			
			approvedSuccess=salesreturnBO.updateSalesreturn(salesreturn);			
			if(approvedSuccess)
			{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesreturnPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_INFO, "Approved Success", null));
			}
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesreturnPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			}		
	}
	
	
	public void deleteSalesreturn()
	{		
		FacesContext context = FacesContext.getCurrentInstance();	
		boolean approvedSuccess=false;
		try {
			approvedSuccess=salesreturnBO.deleteSalesreturn(salesreturn);
			if(approvedSuccess)
			{
				for(SalesreturnModel tempdata:salesreturn.getSalesreturn())
				{
					SalesorderbreakdownModel data = new SalesorderbreakdownModel();
					data.setSalesOrderBreakdownId(tempdata.getSalesorderbreakdownmodel().getSalesOrderBreakdownId());
					
					List<SalesorderbreakdownModel> sList=salesOrderBO.getSalesorderbreakdownList(null,tempdata.getSalesorderbreakdownmodel().getSalesOrderBreakdownId(), null, null, 0, 1,null);
					if(sList!=null && sList.size()!=0)
					{
						data=sList.get(0);
						data.setReturnquantity(new BigDecimal(data.getReturnquantity().intValue()-tempdata.getReturnQuantity()));				
						salesOrderBO.updateSalesorderbreakdown(data);
					}					
				}
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesreturnPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete Success", null));
			}
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesreturnPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			}		
	}
	
		
	public void validateEditQuantity(Integer rowId)
	{
		FacesContext context = FacesContext.getCurrentInstance();				
		SalesorderbreakdownModel c = salesorder.getSalesorderbreakdowns().get(rowId);
		BigDecimal payableQuantity= new BigDecimal(0.00);
		try {
			if (ValidatorUtil.isNotNull(this.getReturnQuantity().get(c.getId()))) 
			{
				payableQuantity = new BigDecimal(""+this.getReturnQuantity().get(c.getId()));
			}	
				if(c.getQuantity().doubleValue()<payableQuantity.doubleValue())
				{
					context.addMessage(UIComponent.findComponent(context.getViewRoot(),"receivable").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please Check the Return Quantity should be less than Sales Quantity", null));
					this.returnQuantity.put(c.getId(),String.valueOf("0"));					
				}	
				
				extractReturnTotal();
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesreturnPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}
	
	
	public void validateSalesReturnOrder()
	{
		boolean valid = true;		
		Integer payableQuantity=0;	
		this.setSaveConfirm(false);
		if(salesorder!=null)
		{
		for(SalesorderbreakdownModel c : salesorder.getSalesorderbreakdowns())
		{
			if (ValidatorUtil.isNotNull(this.getReturnQuantity().get(c.getId()))) 
			{
				payableQuantity = new Integer(this.getReturnQuantity().get(c.getId()));
				if(payableQuantity!=0)
				{
					this.setSaveConfirm(true);					
					break;
				}
				else
				{
					this.setSaveConfirm(false);					
				}
			}				
		}	
		}
	}
	
	
	public void extractReturnTotal() {
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{
		    BigDecimal totalAmount =new BigDecimal(0.00);
		    BigDecimal returntotalAmount =new BigDecimal(0.00);
		    BigDecimal payableQuantity =new BigDecimal(0.00);
		   
			for(SalesorderbreakdownModel item:salesorder.getSalesorderbreakdowns())
			{
				if (ValidatorUtil.isNotNull(this.getReturnQuantity().get(item.getId()))) 
				{
					payableQuantity = new BigDecimal(""+this.getReturnQuantity().get(item.getId()));
								
				if(item.getTaxAmount()==null)
				{
					item.setTaxAmount(new BigDecimal(0.00));
				}
				
				if(item.getDiscountAmount()==null)
				{
					item.setDiscountAmount(new BigDecimal(0.00));
				}
				
				BigDecimal taxAmt = item.getTaxCode().multiply((item.getUnitPrice().divide(new BigDecimal(100))));
				//BigDecimal tax=new BigDecimal(item.getTaxAmount().doubleValue()/item.getQuantity().doubleValue());
				BigDecimal discount=new BigDecimal(item.getDiscountAmount().doubleValue()/item.getQuantity().doubleValue());
				
				totalAmount=(item.getUnitPrice().add(taxAmt)).subtract(discount);
				totalAmount=totalAmount.multiply(payableQuantity);				
				returntotalAmount=returntotalAmount.add(DecimalUtil.formatRoundupCents(totalAmount));
				}
			}
			this.setReturnTotal(returntotalAmount);
		}
		catch(Exception e)
			{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"salesListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),null));
			}
		
		}
		
	
	
	
	public void returnSalesConfirm()
	{
		SalesreturnBeanInfo salesreturnBeanInfo = new SalesreturnBeanInfo();
		salesreturnBeanInfo.saveReturnItem();
		resetRetrun();
	}
	
	public void returnSalesPOSConfirm()
	{
		SalesreturnBeanInfo salesreturnBeanInfo = new SalesreturnBeanInfo();
		salesreturnBeanInfo.savePOSReturnItem();
		resetRetrun();
	}
	
	
	public void searchSalesReturn()
	{		
		try
		{   if(!this.validateSalesOrderSearch())
		{
			this.setDateFrom(DateUtil.getFromTodayDateTime());
			this.setDateTo(DateUtil.getToTodayDateTime());
		}
			this.getSalesorderReturnList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
	
	
	public void resetSalesReturnOrderSearch()
	{		
		this.setCustomerId(0);
		this.setBranchId(0);
		this.setDateFrom(DateUtil.getFromTodayDateTime());
		this.setDateTo(DateUtil.getToTodayDateTime());
		this.setStatus(null);
		this.setSalesOrderNo(null);
		getSalesorderReturnList();
	}
	
	
	public void listSalesReturnOrder()
	{
		SalesreturnBeanInfo salesreturnBeanInfo = new SalesreturnBeanInfo();
		FacesContext context = FacesContext.getCurrentInstance();	
		try {
			salesreturnBeanInfo.listSalesReturnOrder();
			}
		catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesreturnPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
		}	
	
	
	public void newSalesReturnOrder()
	{
		SalesreturnBeanInfo salesreturnBeanInfo = new SalesreturnBeanInfo();
		FacesContext context = FacesContext.getCurrentInstance();	
		try {
			resetRetrun();
			salesreturnBeanInfo.newSalesReturnOrder();			
			}
		catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesreturnPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
		}		

	
	public boolean validateSalesOrderSearch() {
		boolean valid = true;		
	
		SalesreturnBean salesreturnBean = (SalesreturnBean) BeanContext.getReference("salesreturnBean");
		
		if(salesreturnBean.getSalesOrderNo()!=null)
		{
			if(salesreturnBean.getSalesOrderNo().equalsIgnoreCase(""))
			{
				salesreturnBean.setSalesOrderNo(null);
			}
		}		
		
		if(salesreturnBean.getCustomerId()!=null)
		{
			if(salesreturnBean.getCustomerId()==0)
			{
				salesreturnBean.setCustomerId(null);
			}
		}	
		
		if(salesreturnBean.getBranchId()!=null)
		{
			if(salesreturnBean.getBranchId()==0)
			{
				salesreturnBean.setBranchId(null);
			}
		}		
		
		if(salesreturnBean.getStatus()!=null)
		{
			if(salesreturnBean.getStatus().equalsIgnoreCase("") || salesreturnBean.getStatus().equalsIgnoreCase("0") )
			{
				salesreturnBean.setStatus(null);
			}
		}		
		
		if(salesreturnBean.getCustomerId()==null && salesreturnBean.getBranchId()==null &&  salesreturnBean.getDateFrom()==null && salesreturnBean.getDateTo()==null && salesreturnBean.getStatus()==null  && salesreturnBean.getSalesOrderNo()==null)
		{	
			valid = false;
		}		
		
		else
		{
			valid = true;
		}			
		return valid;
	}
	
	
	public List<CustomerModel> getCustomerName(String customerString) {
		List<CustomerModel> results = new ArrayList<CustomerModel>();
		CommonListBeanInfo commoninfo = new CommonListBeanInfo();
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			results = commoninfo.getAllCustomerList(customerString);
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesreturnPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
		return results;
	}

	
	public void resetRetrun()
	{
		this.setSalesreturn(new SalesorderreturnModel());
		this.returnQuantity.clear();
		salesorderbreakdowns.clear();
		this.setSalesOrderNo(null);
		this.setSalesorder(new SalesorderModel());
		this.setReturnTotal(new BigDecimal(0.00));
	}
	
	
	public void printSalesReturnOrder(ActionEvent event) {
		
		FacesContext faces = FacesContext.getCurrentInstance();	
		try{				
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 			
		salesreturn = (SalesorderreturnModel) event.getComponent().getAttributes().get("sales");
		faces.getExternalContext().redirect(request.getScheme() + "://" + request.getServerName() +":" + request.getServerPort() +"/"+config.getValue(IConfiguration.PROJECT_INSTANCE_NAME)+"/frameset?__report=report/sales/returnDetailReport.rptdesign&__format=pdf&&__pageoverflow=0&__asattachment=true&__overwrite=false&resturnid=" + salesreturn.getSalesreturnid()+"&userId="+loginBean.getUserId());
		faces.responseComplete();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}


}
