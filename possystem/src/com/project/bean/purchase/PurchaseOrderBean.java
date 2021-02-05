package com.project.bean.purchase;

import java.io.File;
import java.text.SimpleDateFormat;
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
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.data.PageEvent;
import org.primefaces.model.LazyDataModel;

import com.project.bo.interfaces.IDeliveryorderBO;
import com.project.bo.interfaces.IPurchaseOrderBO;
import com.project.model.datamodel.purchase.PurchaseorderConsolidateModel;
import com.project.model.datamodel.purchase.PurchaseorderModel;
import com.project.model.datamodel.purchase.PurchaseorderbreakdownsModel;
import com.project.model.datamodel.purchase.PurchaseorderdeliveryaddressModel;
import com.project.model.datamodel.purchase.PurchaserequestBranchModel;

import com.project.model.datamodel.purchase.PurchaserequestbreakdownModel;
import com.project.model.datamodel.stock.DeliveryorderModel;

import com.project.util.DateUtil;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.util.EmailProcess;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.login.LoginBean;
import com.project.model.paginghelper.PurchaseOrderPagingHelper;

/*
 * @author Gopal Ar
 * @version 1.0
 * @since 12 Aug 2013
 * 
 */

@ManagedBean(name = "purchaseOrderBean")
@SessionScoped
public class PurchaseOrderBean {

	PurchaseorderModel purchaseorder = new PurchaseorderModel();
	List<PurchaserequestbreakdownModel> purchaserequestbreakdowns = new ArrayList<PurchaserequestbreakdownModel>();
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	private List<PurchaseorderbreakdownsModel> purchaseRevertItemlList = new ArrayList<PurchaseorderbreakdownsModel>();
	
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	private LazyDataModel<PurchaseorderModel> purchaseorderModel = null;
	Configuration config = Configuration.getConfiguration();
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	private Map<Integer, String> deliveryAddress = new HashMap<Integer, String>();
	List<DeliveryorderModel> deliveryorderList = new ArrayList<DeliveryorderModel>();
	
	
	private String purchaseOrderNo;
	private Integer supplierId;
	private Integer branchId;
	private Date dateFrom;
	private Date dateTo;
	private String status;	
	private Integer rowId;
	
	 protected int first;  
	 List<String> errorList = new ArrayList<String>();
	 
	    
	 public int getFirst() {  
	         return first;  
	    }  
	 public void setFirst(int first) {  
	         this.first = first;  
	    }  
	 public void onPageChange(PageEvent event) {  
	         this.setFirst(((DataTable) event.getSource()).getFirst());  
	    }  
	
	IPurchaseOrderBO purchaseOrderBO=objectMapController.getPurchaseOrderBO();
	
	public IPurchaseOrderBO getPurchaseOrderBO() {
		return purchaseOrderBO;
	}

	public void setPurchaseOrderBO(IPurchaseOrderBO purchaseOrderBO) {
		this.purchaseOrderBO = purchaseOrderBO;
	}
	
	IDeliveryorderBO deliveryOrderBO=objectMapController.getDeliveryOrderBO();
	
	public PurchaseorderModel getPurchaseorder() {
		return purchaseorder;
	}

	

	public void setPurchaseorder(PurchaseorderModel purchaseorder) {
		this.purchaseorder = purchaseorder;
	}

	public List<PurchaserequestbreakdownModel> getPurchaserequestbreakdowns() {
		return purchaserequestbreakdowns;
	}

	public void setPurchaserequestbreakdowns(
			List<PurchaserequestbreakdownModel> purchaserequestbreakdowns) {
		this.purchaserequestbreakdowns = purchaserequestbreakdowns;
	}

	public LazyDataModel<PurchaseorderModel> getPurchaseorderModel() {
		return purchaseorderModel;
	}

	public void setPurchaseorderModel(
			LazyDataModel<PurchaseorderModel> purchaseorderModel) {
		this.purchaseorderModel = purchaseorderModel;
	}

	public String getPurchaseOrderNo() {
		return purchaseOrderNo;
	}

	public void setPurchaseOrderNo(String purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	
	
	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
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
	
	
	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public Map<Integer, String> getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Map<Integer, String> deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}	
	

	public List<String> getErrorList() {
		return errorList;
	}
	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}
	public List<PurchaseorderbreakdownsModel> getPurchaseRevertItemlList() {
		return purchaseRevertItemlList;
	}

	public void setPurchaseRevertItemlList(
			List<PurchaseorderbreakdownsModel> purchaseRevertItemlList) {
		this.purchaseRevertItemlList = purchaseRevertItemlList;
	}	
	
	
	public List<DeliveryorderModel> getDeliveryorderList() {
		return deliveryorderList;
	}

	public void setDeliveryorderList(List<DeliveryorderModel> deliveryorderList) {
		this.deliveryorderList = deliveryorderList;
	}

	public LazyDataModel<PurchaseorderModel> getPurchaseorderList() {
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{
		
		this.setPurchaseorderModel(null);
		purchaseorderModel = new PurchaseOrderPagingHelper(purchaseOrderNo,supplierId, dateFrom, dateTo,status,purchaseOrderBO,loginBean.getBranch().getBranchId());		
		this.setPurchaseorderModel(purchaseorderModel);	
		
		}
		catch(Exception e){					
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"purchaseRequestPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
			}
		
		
		return purchaseorderModel;
	}
	
	
	public void searchPurchaseOrder()
	{
		try
		{	
			if(!this.validatePurchaseOrderSearch())	
			{					
			Date dateNow = new Date ();				 
		    SimpleDateFormat dateformatDDMMYYYY = new SimpleDateFormat("dd/MM/yyyy");		 
		    String nowYYYYMMDD = new String( dateformatDDMMYYYY.format(dateNow));    
			this.setDateFrom(DateUtil.getFromTodayDateTime());
			this.setDateTo(DateUtil.getToTodayDateTime());
			}					
			this.getPurchaseorderList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void searchPurchaseOrderReport()
	{
		try
		{	
			if(!this.validatePurchaseOrderSearch())	
			{					
			Date dateNow = new Date ();				 
		    SimpleDateFormat dateformatDDMMYYYY = new SimpleDateFormat("dd/MM/yyyy");		 
		    String nowYYYYMMDD = new String( dateformatDDMMYYYY.format(dateNow));    
			this.setDateFrom(DateUtil.getFromTodayDateTime());
			this.setDateTo(DateUtil.getToTodayDateTime());
			}					
			//this.getPurchaseorderList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void resetPurchaseOrder()
	{	
			this.setDateFrom(DateUtil.getFromTodayDateTime());
			this.setDateTo(DateUtil.getToTodayDateTime());
			this.setSupplierId(0);
			this.setStatus(null);
			this.setPurchaseOrderNo(null);
			searchPurchaseOrder();
	}
	
	public void viewPurchaseOrder(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	try {
		String purchaseOrderId = "";
		purchaseOrderId = (String) event.getComponent().getAttributes().get("purchaseOrderId").toString();		
		purchaseorder=purchaseOrderBO.getPurchaseorderDetails(Integer.parseInt(purchaseOrderId));
		deliveryorderList=deliveryOrderBO.findByDeliveryorderListAll(null, purchaseorder.getPurchaseOrderNo(), null, null, null, null,loginBean.getBranch().getBranchId());
		
		//sendPOEmail(purchaseorder);
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	
	
	public void editPurchaseOrder(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	PurchaseOrderBeanInfo purchaseOrderBeanInfo = new PurchaseOrderBeanInfo();		
	try {
		String purchaseOrderId = "";
		deliveryAddress.clear();
		purchaseRevertItemlList.clear();
		purchaseOrderId = (String) event.getComponent().getAttributes().get("purchaseOrderId").toString();		
		purchaseorder=purchaseOrderBO.getPurchaseorderDetails(Integer.parseInt(purchaseOrderId));		
		for(PurchaseorderdeliveryaddressModel addressdata:purchaseorder.getPurchaseorderdeliveryaddresses())
		{
			deliveryAddress.put(addressdata.getBranchId(), addressdata.getDeliveryAddress());
		}
		
		purchaseOrderBeanInfo.newPurchaseOrder();
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	
	public void updatePurchaseOrder() throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	PurchaseOrderBeanInfo purchaseOrderBeanInfo = new PurchaseOrderBeanInfo();		
	try {	
		
		purchaseOrderBeanInfo.updatePurchaseOrder();
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	
	
	public void listPurchaseOrder() throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	PurchaseOrderBeanInfo purchaseOrderBeanInfo = new PurchaseOrderBeanInfo();		
	try {		
		purchaseOrderBeanInfo.listPurchaseOrder();
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
		
	
	
	public void approvePurchaseOrder(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	PurchaseOrderBeanInfo purchaseOrderBeanInfo = new PurchaseOrderBeanInfo();
	try {
		String purchaseOrderId = "";
		purchaseOrderId = (String) event.getComponent().getAttributes().get("purchaseOrderId").toString();		
		//purchaseorder=purchaseOrderBO.getPurchaseorderMasterDetails(Integer.parseInt(purchaseOrderId));
		purchaseorder=purchaseOrderBO.getPurchaseorderDetails(Integer.parseInt(purchaseOrderId));
		purchaseOrderBeanInfo.validatePurchaseOrderProcess();
		
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	
	public void approvePurchaseOrderConfirm()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		PurchaseOrderBeanInfo purchaseOrderBeanInfo = new PurchaseOrderBeanInfo();
		boolean updateSuccess=false;
		try {			
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			//purchaseorder.setStatus(config.getValue(IConfiguration.PURCHASEORDER_STATUS_PROCESSED_VALUE));				
			//updateSuccess=purchaseOrderBO.approvePurchaseorder(purchaseorder);	
			
			purchaseorder.setStatus(config.getValue(IConfiguration.PURCHASEORDER_STATUS_ORDERED_VALUE));				
			purchaseOrderBO.approvePurchaseorder(purchaseorder);
			sendPOEmail(purchaseorder);
			
			if (updateSuccess) {					
				this.searchPurchaseOrder();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("purchaseorder.label.approved.success"),null));				
			}
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}
	
	
	
	
	public void emailPurchaseOrder(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	try {
		String purchaseOrderId = "";
		purchaseOrderId = (String) event.getComponent().getAttributes().get("purchaseOrderId").toString();		
		purchaseorder=purchaseOrderBO.getPurchaseorderDetails(Integer.parseInt(purchaseOrderId));
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	
	public void emailPurchaseOrderConfirm()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		PurchaseOrderBeanInfo purchaseOrderBeanInfo = new PurchaseOrderBeanInfo();		
		EmailProcess email = new EmailProcess();
		try {			
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();			
			purchaseOrderBeanInfo.generatePurchaseOrderPDF();
			String filePath = servletContext.getRealPath("") + File.separator+ "document" + File.separator + "purchase_order" + File.separator	+purchaseorder.getPurchaseOrderNo()+".pdf";
			purchaseorder.setFilePath(filePath);
			email.emailPurchaseOrder(purchaseorder.getFilePath(), purchaseorder);	
			
			purchaseorder.setStatus(config.getValue(IConfiguration.PURCHASEORDER_STATUS_ORDERED_VALUE));				
			purchaseOrderBO.approvePurchaseorder(purchaseorder);
			
			this.searchPurchaseOrder();
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("purchaseorder.label.email.success"),null));				
			
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}
	
	
	public void revertItem(Integer rowId,Integer branchId)
	{		
		this.setRowId(rowId);
		this.setBranchId(branchId);
	}
	
	
	public void revertPurchaseOrderConfirm()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		PurchaseOrderBeanInfo purchaseOrderBeanInfo = new PurchaseOrderBeanInfo();		
		PurchaseorderbreakdownsModel pob=new PurchaseorderbreakdownsModel();
		try {			
			PurchaserequestbreakdownModel c = new PurchaserequestbreakdownModel();
			
			for(PurchaserequestBranchModel data:purchaseorder.getBranchModel())
			{				
				if(data.getBranchId()==this.getBranchId())
				{				
					 c = data.getProductList().get(rowId);	
					 PurchaseorderbreakdownsModel deleteItem = new PurchaseorderbreakdownsModel();
					 deleteItem.setProductId(c.getProductId());
					 deleteItem.setReferenceNo(c.getReferenceNo());
					 deleteItem.setPurchaseOrderBreakdownId(c.getPurchaseRequestBreakdownId());
					 deleteItem.setBranchId(data.getBranchId());
					 deleteItem.setQuantity(c.getQuantityRequired());
					 deleteItem.setSubTotal(c.getSubTotal());
					 deleteItem.setSupplierId(purchaseorder.getSupplier().getSupplierId());
					 deleteItem.setTaxAmount(c.getTaxAmount());
					 deleteItem.setTaxCode(c.getTaxCode());
					 purchaseRevertItemlList.add(deleteItem);					 
					 data.getProductList().remove(c);
					 pob=this.purchaseorder.getPurchaseorderbreakdowns().get(rowId);	
					 this.purchaseorder.getPurchaseorderbreakdowns().remove(pob); // 
					 
					break;
				}
			}		
			
			
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}
	
	
	public boolean validatePurchaseOrderSearch() {
		boolean valid = true;		
	
		if(this.getPurchaseOrderNo()!=null)
		{
			if(this.getPurchaseOrderNo().equalsIgnoreCase(""))
			{
				this.setPurchaseOrderNo(null);
			}
		}		
		
		if(this.getSupplierId()!=null)
		{
			if(this.getSupplierId()==0)
			{
				this.setSupplierId(null);
			}
		}		
		if(this.getStatus()!=null)
		{
			if(this.getStatus().equalsIgnoreCase("") || this.getStatus().equalsIgnoreCase("0") )
			{
				this.setStatus(null);
			}
		}		
		
		if(this.getSupplierId()==null && this.getDateFrom()==null && this.getDateTo()==null && this.getStatus()==null  && this.getPurchaseOrderNo()==null)
		{	
			valid = false;
		}		
		
		else
		{
			valid = true;
		}			
		return valid;

	}

    public void printPurchaseOrder(ActionEvent event) {
		
		FacesContext faces = FacesContext.getCurrentInstance();	
		try{				
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 			
		String purchaseOrderId = "";
		purchaseOrderId = (String) event.getComponent().getAttributes().get("purchaseOrderId").toString();
		faces.getExternalContext().redirect(request.getScheme() + "://" + request.getServerName() +":" + request.getServerPort() +"/"+config.getValue(IConfiguration.PROJECT_INSTANCE_NAME)+"/frameset?__report=report/purchase/purchaseOrderDetail.rptdesign&__format=pdf&&__pageoverflow=0&__asattachment=true&__overwrite=false&purchaseOrderId=" + purchaseOrderId+"&userId="+loginBean.getUserId());
		faces.responseComplete();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
    
    
	
	
	public void sendPOEmail(PurchaseorderModel purchaseorder )
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try {			
			EmailProcess email = new EmailProcess();
			Map<String, Object> reportParameters = new HashMap<String, Object>();
			
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 			
		
			String urlPath=request.getScheme() + "://" + request.getServerName() +":" + request.getServerPort() +"/"+config.getValue(IConfiguration.PROJECT_INSTANCE_NAME);
			
			reportParameters.put("purchaseOrderId", purchaseorder.getPurchaseOrderId());
			reportParameters.put("userId", loginBean.getUserId());
			
			email.emailPurchaseOrder2(urlPath, purchaseorder,reportParameters);
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}
	
	
	
	
	
}
