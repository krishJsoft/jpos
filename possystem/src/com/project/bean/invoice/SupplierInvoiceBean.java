package com.project.bean.invoice;

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

import org.primefaces.model.LazyDataModel;

import com.project.bo.interfaces.IAdminDespatchBO;
import com.project.bo.interfaces.IBranchinvoiceBO;
import com.project.bo.interfaces.IDeliveryorderBO;
import com.project.bo.interfaces.IPurchaseOrderBO;
import com.project.bo.interfaces.ISalesorderBO;
import com.project.bo.interfaces.ISupplierinvoiceBO;
import com.project.model.datamodel.AdmindespatchModel;
import com.project.model.datamodel.purchase.PurchaseorderModel;
import com.project.model.datamodel.stock.DeliveryorderModel;
import com.project.model.invoice.branch.BranchinvoicebreakdownModel;
import com.project.model.invoice.supplier.SupplierinvoiceModel;
import com.project.model.invoice.supplier.SupplierinvoicebreakdownModel;
import com.project.bean.admin.AdminDespatchBean;
import com.project.bean.sales.sale.SalesorderBeanInfo;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.util.DateUtil;
import com.project.common.util.DecimalUtil;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.UIComponent;
import com.project.login.LoginBean;
import com.project.model.paginghelper.SupplierinvoicePagingHelper;


/*
 * @author Gopal Ar
 * @version 1.0
 * @since 29 Oct 2013
 * 
 */

@ManagedBean(name = "supplierInvoiceBean")
@SessionScoped
public class SupplierInvoiceBean {

	SupplierinvoiceModel supplierinvoice = new SupplierinvoiceModel();
	List<BranchinvoicebreakdownModel> branchinvoicebreakdowns = new ArrayList<BranchinvoicebreakdownModel>();
	SupplierinvoicebreakdownModel supplierinvoicebreakdown = new SupplierinvoicebreakdownModel();
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	Configuration config = Configuration.getConfiguration();
	private LazyDataModel<SupplierinvoiceModel> supplierinvoiceModel = null;
	private LazyDataModel<SupplierinvoicebreakdownModel> supplierinvoicebreakdownModel = null;
	DeliveryorderModel deliveryorder = new DeliveryorderModel();
	private List<SelectItem> selectDeliveryOrderNoList = new ArrayList<SelectItem>();
	AdminDespatchBean adminDespatchBean = (AdminDespatchBean) BeanContext.getReference("adminDespatchBean");
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	List<AdmindespatchModel> adespatchList = new ArrayList<AdmindespatchModel>();
	
	private Integer branchId;
	private Date dateFrom;
	private Date dateTo;
	private String status;
	private String salesOrderNo;
	private int branchInvoiceId;
	private String invoiceNo;
	private String action = "submit";
	private int activeIndex = 0;
	private Integer supplierId;
	private Integer deliveryOrderId;
	private Integer supplybranchId;
	
	
	ISalesorderBO salesOrderBO=objectMapController.getSalesOrderBO();	
	IBranchinvoiceBO branchinvoiceBO=objectMapController.getBranchinvoiceBO();	
	ISupplierinvoiceBO supplierinvoiceBO=objectMapController.getSupplierinvoiceBO();
	IDeliveryorderBO deliveryOrderBO=objectMapController.getDeliveryOrderBO();
	IPurchaseOrderBO purchaseOrderBO=objectMapController.getPurchaseOrderBO();
	
	public ISalesorderBO getSalesOrderBO() {
		return salesOrderBO;
	}

	public void setSalesOrderBO(ISalesorderBO salesOrderBO) {
		this.salesOrderBO = salesOrderBO;
	}

	public IBranchinvoiceBO getBranchinvoiceBO() {
		return branchinvoiceBO;
	}

	public void setBranchinvoiceBO(IBranchinvoiceBO branchinvoiceBO) {
		this.branchinvoiceBO = branchinvoiceBO;
	}	
	

	public ISupplierinvoiceBO getSupplierinvoiceBO() {
		return supplierinvoiceBO;
	}

	public void setSupplierinvoiceBO(ISupplierinvoiceBO supplierinvoiceBO) {
		this.supplierinvoiceBO = supplierinvoiceBO;
	}	

	public IDeliveryorderBO getDeliveryOrderBO() {
		return deliveryOrderBO;
	}

	public void setDeliveryOrderBO(IDeliveryorderBO deliveryOrderBO) {
		this.deliveryOrderBO = deliveryOrderBO;
	}

	public List<BranchinvoicebreakdownModel> getBranchinvoicebreakdowns() {
		return branchinvoicebreakdowns;
	}

	public void setBranchinvoicebreakdowns(
			List<BranchinvoicebreakdownModel> branchinvoicebreakdowns) {
		this.branchinvoicebreakdowns = branchinvoicebreakdowns;
	}


	public DeliveryorderModel getDeliveryorder() {
		return deliveryorder;
	}

	public void setDeliveryorder(DeliveryorderModel deliveryorder) {
		this.deliveryorder = deliveryorder;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
	

	public Integer getSupplybranchId() {
		return supplybranchId;
	}

	public void setSupplybranchId(Integer supplybranchId) {
		this.supplybranchId = supplybranchId;
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

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getSalesOrderNo() {
		return salesOrderNo;
	}

	public void setSalesOrderNo(String salesOrderNo) {
		this.salesOrderNo = salesOrderNo;
	}

	public int getBranchInvoiceId() {
		return branchInvoiceId;
	}

	public void setBranchInvoiceId(int branchInvoiceId) {
		this.branchInvoiceId = branchInvoiceId;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getActiveIndex() {
		return activeIndex;
	}

	public void setActiveIndex(int activeIndex) {
		this.activeIndex = activeIndex;
	}
	
	
	public Integer getDeliveryOrderId() {
		return deliveryOrderId;
	}

	public void setDeliveryOrderId(Integer deliveryOrderId) {
		this.deliveryOrderId = deliveryOrderId;
	}

	public SupplierinvoiceModel getSupplierinvoice() {
		return supplierinvoice;
	}

	public void setSupplierinvoice(SupplierinvoiceModel supplierinvoice) {
		this.supplierinvoice = supplierinvoice;
	}

	public SupplierinvoicebreakdownModel getSupplierinvoicebreakdown() {
		return supplierinvoicebreakdown;
	}

	public void setSupplierinvoicebreakdown(
			SupplierinvoicebreakdownModel supplierinvoicebreakdown) {
		this.supplierinvoicebreakdown = supplierinvoicebreakdown;
	}

	public LazyDataModel<SupplierinvoiceModel> getSupplierinvoiceModel() {
		return supplierinvoiceModel;
	}

	public void setSupplierinvoiceModel(
			LazyDataModel<SupplierinvoiceModel> supplierinvoiceModel) {
		this.supplierinvoiceModel = supplierinvoiceModel;
	}

	public LazyDataModel<SupplierinvoicebreakdownModel> getSupplierinvoicebreakdownModel() {
		return supplierinvoicebreakdownModel;
	}

	public void setSupplierinvoicebreakdownModel(
			LazyDataModel<SupplierinvoicebreakdownModel> supplierinvoicebreakdownModel) {
		this.supplierinvoicebreakdownModel = supplierinvoicebreakdownModel;
	}
	
	
	public List<SelectItem> getSelectDeliveryOrderNoList() {
		return selectDeliveryOrderNoList;
	}

	public void setSelectDeliveryOrderNoList(
			List<SelectItem> selectDeliveryOrderNoList) {
		this.selectDeliveryOrderNoList = selectDeliveryOrderNoList;
	}

	public void resetEditInvoice()
	{
		supplierinvoice = new SupplierinvoiceModel();
		deliveryorder= new DeliveryorderModel();
		this.setAction("submit");
		this.setSupplierId(0);
		this.setDeliveryOrderId(0);
	}

	
	public void resetInvoice()
	{
		supplierinvoice = new SupplierinvoiceModel();
		deliveryorder= new DeliveryorderModel();
		this.setAction("submit");
		
	}
	
	public void generateInvoice() throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	SupplierInvoiceBeanInfo supplierInvoiceBeanInfo = new SupplierInvoiceBeanInfo();
	resetInvoice();
	try {
		if(this.getDeliveryOrderId()!=null && this.getDeliveryOrderId()!=0)
		{
		adminDespatchBean.getAdespatchList();	
		deliveryorder=deliveryOrderBO.getDeliveryorderDetails(deliveryOrderId);
		supplierinvoice=supplierInvoiceBeanInfo.generateInvoice();
		//supplierInvoiceBeanInfo.newSupplierInvoice();		
		}
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	
	public void viewInvoice(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();		
	try {		
		supplierinvoice = (SupplierinvoiceModel) event.getComponent().getAttributes().get("invoice");
		supplierinvoice=supplierinvoiceBO.getSupplierinvoiceDetails(supplierinvoice.getSupplierInvoiceId());	
		deliveryorder=deliveryOrderBO.getDeliveryorderList(null, supplierinvoice.getDeliveryOrderNo(), null, null, null, null, null, 0, 1, null).get(0);
		supplierinvoice.setBranch(deliveryorder.getBranch());
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	public void editInvoice(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();	
	SupplierInvoiceBeanInfo supplierInvoiceBeanInfo = new SupplierInvoiceBeanInfo();
	try {
		adminDespatchBean.getAdespatchList();
		supplierinvoice = (SupplierinvoiceModel) event.getComponent().getAttributes().get("invoice");
		supplierinvoice=supplierinvoiceBO.getSupplierinvoiceDetails(supplierinvoice.getSupplierInvoiceId());
		
		deliveryorder=deliveryOrderBO.getDeliveryorderList(null, supplierinvoice.getDeliveryOrderNo(), null, null, null, null, null, 0, 1, null).get(0);
		supplierinvoice.setBranch(deliveryorder.getBranch());
		this.setSupplierId(supplierinvoice.getSupplier().getSupplierId());
		this.setDeliveryOrderId(deliveryorder.getDeliveryOrderId());
		this.setAction("update");
		supplierInvoiceBeanInfo.newSupplierInvoice();
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}	
	
	public void approveInvoiceConfirm(ActionEvent event)  throws Exception 
	{		
		FacesContext context = FacesContext.getCurrentInstance();		
		try {			
			supplierinvoice = (SupplierinvoiceModel) event.getComponent().getAttributes().get("invoice");
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
			}		
	}	
	
	
	public void approveInvoice()
	{
		SupplierInvoiceBeanInfo supplierInvoiceBeanInfo = new SupplierInvoiceBeanInfo();	
		supplierInvoiceBeanInfo.approveInvoice();
	}		
	
	
	public void saveInvoice()
	{
		SupplierInvoiceBeanInfo supplierInvoiceBeanInfo = new SupplierInvoiceBeanInfo();	
		supplierInvoiceBeanInfo.saveInvoice();
	}
	
	public void updateInvoice()
	{
		SupplierInvoiceBeanInfo supplierInvoiceBeanInfo = new SupplierInvoiceBeanInfo();	
		supplierInvoiceBeanInfo.updateInvoice();
	}
	
	
	public LazyDataModel<SupplierinvoiceModel> getInvoiceList() {
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{		
		this.setSupplierinvoiceModel(null);	
		supplierinvoiceModel = new SupplierinvoicePagingHelper(invoiceNo,supplierId, dateFrom, dateTo,status,supplierinvoiceBO,loginBean.getBranch().getBranchId(),supplybranchId);		
		this.setSupplierinvoiceModel(supplierinvoiceModel);	
		}
		catch(Exception e){					
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"invoicePanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
			}
		return supplierinvoiceModel;
	}
	
	
	public void searchInvoice()
	{
		SupplierInvoiceBeanInfo supplierInvoiceBeanInfo = new SupplierInvoiceBeanInfo();	
		try
		{   if(!supplierInvoiceBeanInfo.validateInvoiceSearch())
		{
			this.setDateFrom(DateUtil.getFromTodayDateTime());
			this.setDateTo(DateUtil.getToTodayDateTime());
		}
			this.getInvoiceList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
	
	public void resetInvoiceSearch()
	{
		this.setBranchId(0);		
		this.setDateFrom(DateUtil.getFromTodayDateTime());
		this.setDateTo(DateUtil.getToTodayDateTime());
		this.setStatus(null);
		this.setInvoiceNo(null);
		this.setSupplierId(0);
		searchInvoice();
	}
	
	public void listInvoice()
	{
		SupplierInvoiceBeanInfo supplierInvoiceBeanInfo = new SupplierInvoiceBeanInfo();	
		FacesContext context = FacesContext.getCurrentInstance();	
		try {
			supplierInvoiceBeanInfo.listSupplierInvoice();
			}
		catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
		}	
	
	public void newInvoice()
	{
		SupplierInvoiceBeanInfo supplierInvoiceBeanInfo = new SupplierInvoiceBeanInfo();
		FacesContext context = FacesContext.getCurrentInstance();	
		resetEditInvoice();
		try {
			supplierInvoiceBeanInfo.newSupplierInvoice();
			}
		catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
		}	

	
	public void loadDeiveryOrderNo()
	{
		FacesContext context = FacesContext.getCurrentInstance();	
		selectDeliveryOrderNoList.clear();
		List<String> puchaseorderNoList = new ArrayList<String>(); 
		
		try {
			if(this.getSupplierId()!=null && this.getSupplierId()!=0)
			{
			// Supplier Delivered by Branch Purchase Ordered
			
			if(loginBean.getBranch().getBranchtype().equalsIgnoreCase(config.getValue(IConfiguration.PROJECT_TYPE_BRANCH_VALUE)))
			{
			List<DeliveryorderModel> orderList=null;			
			orderList=deliveryOrderBO.getDeliveryorderList(this.getSupplierId() , "0",loginBean.getBranch().getBranchId(),null);
			for(DeliveryorderModel data1:orderList)
			{	int count;
				count=purchaseOrderBO.getPurchaseorderCount(data1.getPurchaseOrderNo(), this.getSupplierId(), null, null, null, loginBean.getBranch().getBranchId());
				if(count!=0)
				{
					selectDeliveryOrderNoList.add(new SelectItem(data1.getDeliveryOrderId(),data1.getDeliveryOrderNo()));
				}				
			}			
			}
			
			// Supplier Delivered by HQ Purchase Ordered
			
			if(loginBean.getBranch().getBranchtype().equalsIgnoreCase(config.getValue(IConfiguration.PROJECT_TYPE_HQ_VALUE)))
			{
				List<DeliveryorderModel> orderList=null;			
				orderList=deliveryOrderBO.getDeliveryorderList(this.getSupplierId() , "0",null,null);
				for(DeliveryorderModel data1:orderList)
				{	int count;
					count=purchaseOrderBO.getPurchaseorderCount(data1.getPurchaseOrderNo(), this.getSupplierId(), null, null, null, loginBean.getBranch().getBranchId());
					if(count!=0)
					{
						selectDeliveryOrderNoList.add(new SelectItem(data1.getDeliveryOrderId(),data1.getDeliveryOrderNo()));
					}				
				}			
			}
			}
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}
	
	
	public void selectDespatch()
	{
		try{
		IAdminDespatchBO adminDespatchBO=adminDespatchBean.getAdminDespatchBO();
		supplierinvoice.getInvoicedispatch().setDespatchAmount(adminDespatchBO.getAdminDespatchDetails(supplierinvoice.getInvoicedispatch().getDispatch()).getUnitPrice());
		}
		catch(Exception e)
		{
			
		}
		
	}
	
	
	public void calculateInvoiceAmount()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{
			supplierinvoice.setTotalAmount(DecimalUtil.formatRoundupCents((supplierinvoice.getTax().add(supplierinvoice.getInvoiceAmount().add(supplierinvoice.getInvoicedispatch().getDespatchAmount())))));
		}
		catch(Exception e)
		{
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
	}
	
	
	
	public void printInvoice(ActionEvent event) {
		
		FacesContext faces = FacesContext.getCurrentInstance();	
		try{				
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 			
		String quotationId = "";
		supplierinvoice = (SupplierinvoiceModel) event.getComponent().getAttributes().get("invoice");
		faces.getExternalContext().redirect(request.getScheme() + "://" + request.getServerName() +":" + request.getServerPort() +"/"+config.getValue(IConfiguration.PROJECT_INSTANCE_NAME)+"/frameset?__report=report/invoice/supplierInvoiceDetail.rptdesign&__format=pdf&&__pageoverflow=0&__asattachment=true&__overwrite=false&supplierInvoiceId=" + supplierinvoice.getSupplierInvoiceId()+"&userId="+loginBean.getUserId() );
		faces.responseComplete();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
}
