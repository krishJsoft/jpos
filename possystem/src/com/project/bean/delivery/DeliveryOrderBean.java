package com.project.bean.delivery;

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
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.data.PageEvent;
import org.primefaces.model.LazyDataModel;

import com.project.bo.interfaces.IBranchsalesBO;
import com.project.bo.interfaces.IDeliveryorderBO;
import com.project.bo.interfaces.IProductBO;
import com.project.bo.interfaces.IProductbatchBO;
import com.project.bo.interfaces.IPurchaseOrderBO;

import com.project.model.datamodel.purchase.PurchaseorderModel;
import com.project.model.datamodel.purchase.PurchaseorderbreakdownsModel;
import com.project.model.datamodel.purchase.PurchaserequestbreakdownModel;
import com.project.model.datamodel.stock.DeliveryorderModel;
import com.project.model.datamodel.stock.DeliveryorderbreakdownModel;
import com.project.model.datamodel.stock.ProductbatchModel;
import com.project.model.sale.sales.branchsale.BranchsaleModel;
import com.project.model.sale.sales.branchsale.BranchsalesbreakdownModel;
import com.project.util.DateUtil;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.common.validation.ValidatorUtil;
import com.project.login.LoginBean;
import com.project.model.paginghelper.DeliveryOrderPagingHelper;
import com.project.model.paginghelper.ReceivedProductHistoryPagingHelper;

/*
 * @author Gopal Ar
 * @version 1.0
 * @since 12 Aug 2013
 * 
 */

@ManagedBean(name = "deliveryOrderBean")
@SessionScoped
public class DeliveryOrderBean {

	
	DeliveryorderModel deliveryorder = new DeliveryorderModel();
	ProductbatchModel batch = new ProductbatchModel();
	List<ProductbatchModel> productBatchList = new ArrayList<ProductbatchModel>();
	DeliveryorderbreakdownModel deliveryProduct = new DeliveryorderbreakdownModel();
	List<DeliveryorderbreakdownModel> deliveryorderbreakdowns = new ArrayList<DeliveryorderbreakdownModel>();
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	private LazyDataModel<DeliveryorderModel> deliveryorderModel = null;
	private LazyDataModel<DeliveryorderbreakdownModel> deliveryorderbreakdownModel = null;
	
	Configuration config = Configuration.getConfiguration();
	PurchaseorderModel purchaseorder = new PurchaseorderModel();
	private Map<String, String> receivalbeQuantity = new HashMap<String, String>();
	private Map<String, Date> receivalbeExpDate = new HashMap<String, Date>();
	
	private Map<String, List<ProductbatchModel>> productBatch = new HashMap<String, List<ProductbatchModel>>();
		
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	PurchaserequestbreakdownModel deliveryData = new PurchaserequestbreakdownModel();
	private Map<String, Boolean> checkAmountApportion = new HashMap<String, Boolean>();
	private String deliveryOrderNo;
	private String purchaseOrderNo;
	private String grnNo;
	String action = "submit";
	private Integer purchaseOrderId;
	private Integer branchsalesId;
	BranchsaleModel branchsale = new BranchsaleModel();
	
	private Integer branchId;
	private Integer supplierId;
	private Date dateFrom;
	private Date dateTo;
	private String status;
	private Integer productId;
	private Integer rowId;
	private Integer batchcount=0;
	
	String barcode="";
	String productName="";
	
	private List<SelectItem> selectPurchaseOrderNoList = new ArrayList<SelectItem>();
	private List<SelectItem> selectBranchList = new ArrayList<SelectItem>();
	private List<SelectItem> selectSalesOrderNoList = new ArrayList<SelectItem>();
	
	IDeliveryorderBO deliveryOrderBO=objectMapController.getDeliveryOrderBO();

	IPurchaseOrderBO purchaseOrderBO=objectMapController.getPurchaseOrderBO();
	
	IProductbatchBO productbatchBO=objectMapController.getProductbatchBO();	
	
	
	
	IProductBO productBO=objectMapController.getProductBO();
	IBranchsalesBO branchsalesBO=objectMapController.getBranchsalesBO();
	
	public IPurchaseOrderBO getPurchaseOrderBO() {
		return purchaseOrderBO;
	}

	public void setPurchaseOrderBO(IPurchaseOrderBO purchaseOrderBO) {
		this.purchaseOrderBO = purchaseOrderBO;
	}
		
	
	public IProductbatchBO getProductbatchBO() {
		return productbatchBO;
	}

	public void setProductbatchBO(IProductbatchBO productbatchBO) {
		this.productbatchBO = productbatchBO;
	}

	public DeliveryorderModel getDeliveryorder() {
		return deliveryorder;
	}

	public void setDeliveryorder(DeliveryorderModel deliveryorder) {
		this.deliveryorder = deliveryorder;
	}

	public List<DeliveryorderbreakdownModel> getDeliveryorderbreakdowns() {
		return deliveryorderbreakdowns;
	}

	public void setDeliveryorderbreakdowns(
			List<DeliveryorderbreakdownModel> deliveryorderbreakdowns) {
		this.deliveryorderbreakdowns = deliveryorderbreakdowns;
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

	public LazyDataModel<DeliveryorderModel> getDeliveryorderModel() {
		return deliveryorderModel;
	}

	public void setDeliveryorderModel(
			LazyDataModel<DeliveryorderModel> deliveryorderModel) {
		this.deliveryorderModel = deliveryorderModel;
	}

	public String getDeliveryOrderNo() {
		return deliveryOrderNo;
	}

	public void setDeliveryOrderNo(String deliveryOrderNo) {
		this.deliveryOrderNo = deliveryOrderNo;
	}	
	
	public String getPurchaseOrderNo() {
		return purchaseOrderNo;
	}

	public void setPurchaseOrderNo(String purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}	

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
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
	
	
	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getGrnNo() {
		return grnNo;
	}

	public void setGrnNo(String grnNo) {
		this.grnNo = grnNo;
	}

	public Integer getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(Integer purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public IDeliveryorderBO getDeliveryOrderBO() {
		return deliveryOrderBO;
	}

	public void setDeliveryOrderBO(IDeliveryorderBO deliveryOrderBO) {
		this.deliveryOrderBO = deliveryOrderBO;
	}	
	
	
	public Map<String, String> getReceivalbeQuantity() {
		return receivalbeQuantity;
	}	

	
	public Map<String, Date> getReceivalbeExpDate() {
		return receivalbeExpDate;
	}

	public void setReceivalbeExpDate(Map<String, Date> receivalbeExpDate) {
		this.receivalbeExpDate = receivalbeExpDate;
	}

	public void setReceivalbeQuantity(Map<String, String> receivalbeQuantity) {
		this.receivalbeQuantity = receivalbeQuantity;
	}
	
	

	public Map<String, Boolean> getCheckAmountApportion() {
		return checkAmountApportion;
	}

	public void setCheckAmountApportion(Map<String, Boolean> checkAmountApportion) {
		this.checkAmountApportion = checkAmountApportion;
	}

	public List<SelectItem> getSelectPurchaseOrderNoList() {
		return selectPurchaseOrderNoList;
	}

	public void setSelectPurchaseOrderNoList(
			List<SelectItem> selectPurchaseOrderNoList) {
		this.selectPurchaseOrderNoList = selectPurchaseOrderNoList;
	}

	public List<SelectItem> getSelectBranchList() {
		return selectBranchList;
	}

	public void setSelectBranchList(List<SelectItem> selectBranchList) {
		this.selectBranchList = selectBranchList;
	}	
	
	public PurchaseorderModel getPurchaseorder() {
		return purchaseorder;
	}	

	public List<SelectItem> getSelectSalesOrderNoList() {
		return selectSalesOrderNoList;
	}

	public void setSelectSalesOrderNoList(List<SelectItem> selectSalesOrderNoList) {
		this.selectSalesOrderNoList = selectSalesOrderNoList;
	}

	public void setPurchaseorder(PurchaseorderModel purchaseorder) {
		this.purchaseorder = purchaseorder;
	}	

	public Map<String, List<ProductbatchModel>> getProductBatch() {
		return productBatch;
	}

	public void setProductBatch(Map<String, List<ProductbatchModel>> productBatch) {
		this.productBatch = productBatch;
	}
	
	public ProductbatchModel getBatch() {
		return batch;
	}

	public void setBatch(ProductbatchModel batch) {
		this.batch = batch;
	}	

	public List<ProductbatchModel> getProductBatchList() {
		return productBatchList;
	}

	public void setProductBatchList(List<ProductbatchModel> productBatchList) {
		this.productBatchList = productBatchList;
	}	
	

	public PurchaserequestbreakdownModel getDeliveryData() {
		return deliveryData;
	}

	public void setDeliveryData(PurchaserequestbreakdownModel deliveryData) {
		this.deliveryData = deliveryData;
	}
	
	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}	
	
	public Integer getBatchcount() {
		return batchcount;
	}

	public void setBatchcount(Integer batchcount) {
		this.batchcount = batchcount;
	}
	

	public DeliveryorderbreakdownModel getDeliveryProduct() {
		return deliveryProduct;
	}

	public void setDeliveryProduct(DeliveryorderbreakdownModel deliveryProduct) {
		this.deliveryProduct = deliveryProduct;
	}

	
	
	public Integer getBranchsalesId() {
		return branchsalesId;
	}

	public void setBranchsalesId(Integer branchsalesId) {
		this.branchsalesId = branchsalesId;
	}

	public BranchsaleModel getBranchsale() {
		return branchsale;
	}

	public void setBranchsale(BranchsaleModel branchsale) {
		this.branchsale = branchsale;
	}

	public LazyDataModel<DeliveryorderModel> getDeliveryorderList() {
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{		
		this.setDeliveryorderModel(null);	
		deliveryorderModel = new DeliveryOrderPagingHelper(deliveryOrderNo,supplierId, dateFrom, dateTo,status,deliveryOrderBO,purchaseOrderNo,branchId);		
		this.setDeliveryorderModel(deliveryorderModel);	
		}
		catch(Exception e){					
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"deliveryPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
			}
		return deliveryorderModel;
	}
	
	
	public void searchDeliveryOrder()
	{
		DeliveryOrderBeanInfo deliveryOrderBeanInfo = new DeliveryOrderBeanInfo();
		LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
		
		if(loginBean.getBranch().getBranchtype().contentEquals(config.getValue(IConfiguration.PROJECT_TYPE_BRANCH_VALUE)))
		{
			this.setBranchId(loginBean.getBranch().getBranchId());
		}
		else
		{
			this.setBranchId(null);
		}
		
		try
		{   if(!deliveryOrderBeanInfo.validateDeliveryHistorySearch())
		{
			this.setDateFrom(DateUtil.getFromTodayDateTime());
			this.setDateTo(DateUtil.getToTodayDateTime());
		}
			this.getDeliveryorderList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
	
	public void resetDeliveryOrderSearch()
	{
		this.setSupplierId(0);
		this.setDateFrom(DateUtil.getFromTodayDateTime());
		this.setDateTo(DateUtil.getToTodayDateTime());
		this.setStatus(null);
		this.setDeliveryOrderNo(null);
		searchDeliveryOrder();
	}
	
	public void resetDeliveryOrder()
	{
		purchaseorder = new PurchaseorderModel();		
		receivalbeQuantity.clear();
		receivalbeExpDate.clear();
		selectBranchList.clear();
		this.setBranchId(null);
		this.setSupplierId(null);
		this.setPurchaseOrderId(null);
		selectPurchaseOrderNoList.clear();
		selectSalesOrderNoList.clear();
		branchsale = new BranchsaleModel();
		this.setAction("submit");
		this.setGrnNo(null);
		checkAmountApportion.clear();
	}
	
	public void resetDeliveryHistory()
	{
		this.setDateFrom(DateUtil.getFromTodayDateTime());
		this.setDateTo(DateUtil.getToTodayDateTime());
		searchDeliveryorderbreakdown();
	}
	
	
	public LazyDataModel<DeliveryorderbreakdownModel> getDeliveryorderbreakdownModel() {
		return deliveryorderbreakdownModel;
	}

	public void setDeliveryorderbreakdownModel(
			LazyDataModel<DeliveryorderbreakdownModel> deliveryorderbreakdownModel) {
		this.deliveryorderbreakdownModel = deliveryorderbreakdownModel;
	}

	
	
	public LazyDataModel<DeliveryorderbreakdownModel> getDeliveryorderbreakdownList() {
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{		
		this.setDeliveryorderbreakdownModel(null);	
		deliveryorderbreakdownModel = new ReceivedProductHistoryPagingHelper( dateFrom, dateTo,deliveryOrderBO,loginBean.getBranch().getBranchId());		
		this.setDeliveryorderbreakdownModel(deliveryorderbreakdownModel);	
		}
		catch(Exception e){					
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"deliveryPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
			}
		return deliveryorderbreakdownModel;
	}
	
	
	public void searchDeliveryorderbreakdown()
	{
		try
		{
			if(this.getDateFrom()==null && this.getDateTo()==null )
			{
				this.setDateFrom(DateUtil.getFromTodayDateTime());
				this.setDateTo(DateUtil.getToTodayDateTime());
			}
			this.getDeliveryorderbreakdownList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
	
	
	
	public void searchBatchHistory(ActionEvent event)
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			DeliveryorderbreakdownModel deliveryProduct = null;
			deliveryProduct = (DeliveryorderbreakdownModel) event.getComponent().getAttributes().get("deliveryProduct");		
			this.setProductBatchList(productbatchBO.getProductbatchDetails(null,deliveryProduct.getDeliveryOrderNo(),deliveryProduct.getProductId(),null,null,null,null,null,null));
			this.setDeliveryProduct(deliveryProduct);
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"deliveryPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			
			}
		
	}
	
	
	public void viewDeliveryOrder(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	try {
		String deliveryOrderId = "";
		deliveryOrderId = (String) event.getComponent().getAttributes().get("deliveryOrderId").toString();		
		deliveryorder=deliveryOrderBO.getDeliveryorderDetails(Integer.parseInt(deliveryOrderId));
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"deliveryPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	
	public void editDeliveryOrder(ActionEvent event) throws Exception 
	{	
		resetDeliveryOrder();
		DeliveryOrderBeanInfo deliveryOrderBeanInfo = new DeliveryOrderBeanInfo();
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			String deliveryOrderId = "";			
			receivalbeExpDate.clear();
			receivalbeExpDate.clear();		
			deliveryOrderId = (String) event.getComponent().getAttributes().get("deliveryOrderId").toString();		
			deliveryorder=deliveryOrderBO.getDeliveryorderDetails(Integer.parseInt(deliveryOrderId));
			purchaseorder=purchaseOrderBO.getPurchaseorderDetails(deliveryorder.getPurchaseOrderNo(),deliveryorder.getBranchId());
			
			for (DeliveryorderbreakdownModel data : deliveryorder.getDeliveryorderbreakdowns()) {
				receivalbeQuantity.put(data.getId(),String.valueOf(data.getQuantity()));
				receivalbeExpDate.put(data.getId(), data.getExpDate());		
				
				List<ProductbatchModel> batchDataList = productbatchBO.getProductbatchDetails(null,deliveryorder.getDeliveryOrderNo(), data.getProductId(),null,null,null,null,null,null);
				productBatch.put(data.getId(), batchDataList);
				
			}
			this.setAction("update");
			this.setGrnNo(deliveryorder.getDeliveryOrderNo());
			this.setSupplierId(deliveryorder.getSupplier().getSupplierId());
			
			deliveryOrderBeanInfo.editDeliveryOrder();
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"deliveryPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
		}
	}
	
	
	public void approveDeliveryOrder(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	try {
		String deliveryOrderId = "";
		deliveryOrderId = (String) event.getComponent().getAttributes().get("deliveryOrderId").toString();		
		deliveryorder=deliveryOrderBO.getDeliveryorderDetails(Integer.parseInt(deliveryOrderId));
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"deliveryPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	
	public void approveDeliveryOrderConfirm()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		boolean updateSuccess=false;
		try {		
			deliveryorder.setStatus(config.getValue(IConfiguration.DELIVERYORDER_STATUS_PROCESSED_VALUE));	
			purchaseorder=purchaseOrderBO.getPurchaseorderDetails(deliveryorder.getPurchaseOrderNo(),deliveryorder.getBranchId());
			purchaseorder.setBranchId(deliveryorder.getBranchId());
			updateSuccess=deliveryOrderBO.approveDeliveryorder(deliveryorder,purchaseorder,loginBean.getBranch().getBranchId());
			
			if (updateSuccess) {					
				this.searchDeliveryOrder();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"deliveryPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("deliveryorder.label.approved.success"),null));				
			}
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"deliveryPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}
	
	public void approveBranchDeliveryOrderConfirm()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		boolean updateSuccess=false;
		try {		
			deliveryorder.setStatus(config.getValue(IConfiguration.DELIVERYORDER_STATUS_PROCESSED_VALUE));	
			deliveryorder.setBranchRecordId(loginBean.getBranch().getBranchId());
			updateSuccess=deliveryOrderBO.approveBranchDeliveryorder(deliveryorder);
			
			if (updateSuccess) {					
				this.searchDeliveryOrder();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"deliveryPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("deliveryorder.label.approved.success"),null));				
			}
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"deliveryPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}
	
	
	
	
	public void loadPurchaseOrderNo()
	{
		FacesContext context = FacesContext.getCurrentInstance();	
		selectPurchaseOrderNoList.clear();
		this.setPurchaseOrderId(0);
		purchaseorder = new PurchaseorderModel();
		try {
			if(this.getSupplierId()!=null && this.getSupplierId()!=0)
			{
			List<PurchaseorderModel> orderList=null;			
			orderList=purchaseOrderBO.getPurchaseorderList(this.getSupplierId() , config.getValue(IConfiguration.PURCHASEORDER_STATUS_ORDERED_VALUE),loginBean.getBranch().getBranchId());
			
			if (orderList!=null) {					
				
				for(PurchaseorderModel data:orderList)
				{
					selectPurchaseOrderNoList.add(new SelectItem(data.getPurchaseOrderId(),data.getPurchaseOrderNo()));
					deliveryorder.setSupplier(data.getSupplier());
				}
			}
			}
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"deliveryPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}
	
	
	public void loadPurchaseOrderNo1()
	{
		FacesContext context = FacesContext.getCurrentInstance();	
		selectPurchaseOrderNoList.clear();
		this.setPurchaseOrderId(0);
		purchaseorder = new PurchaseorderModel();
		try {
			if(this.getSupplierId()!=null && this.getSupplierId()!=0)
			{
			List<PurchaseorderModel> orderList=null;			
			orderList=purchaseOrderBO.getPurchaseorderList(this.getSupplierId() , config.getValue(IConfiguration.PURCHASEORDER_STATUS_ORDERED_VALUE),loginBean.getBranch().getBranchId());
			
			if (orderList!=null) {					
				
				for(PurchaseorderModel data:orderList)
				{
					selectPurchaseOrderNoList.add(new SelectItem(data.getPurchaseOrderId(),data.getPurchaseOrderNo()));
					deliveryorder.setSupplier(data.getSupplier());
				}
			}
			}
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"deliveryPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}
	
	
	
	public void loadSalesOrderNo()
	{
		FacesContext context = FacesContext.getCurrentInstance();	
		selectSalesOrderNoList.clear();
		this.setPurchaseOrderId(0);
		purchaseorder = new PurchaseorderModel();
		try {
			if(this.getBranchId()!=null && this.getBranchId()!=0)
			{
			List<BranchsaleModel> orderList=null;			
			orderList=branchsalesBO.getBranchsaleListReport(null, loginBean.getBranch().getBranchId(), null, null, config.getValue(IConfiguration.SALESORDER_STATUS_PROCESSED_VALUE), branchId,"1");
			
			if (orderList!=null) {					
				
				for(BranchsaleModel data:orderList)
				{
					selectSalesOrderNoList.add(new SelectItem(data.getBranchsalesId(),data.getSalesNo()));					
				}
			}
			}
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"deliveryPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}
	
	
	
	
	
	public void loadSalesOrderItem()
	{
		FacesContext context = FacesContext.getCurrentInstance();	
		purchaseorder = new PurchaseorderModel();
		List<Integer> branchIds = new ArrayList<Integer>();
		receivalbeQuantity.clear();
		branchIds.clear();
		try {			
			
			branchsale=branchsalesBO.getBranchsaleDetails(branchsalesId);
			productBatchList.clear();
			
			if(branchsale.getBranchsalesbreakdowns()!=null && branchsale.getBranchsalesbreakdowns().size()!=0)
			{
			for(BranchsalesbreakdownModel deliveryData:branchsale.getBranchsalesbreakdowns())
			{
								
				batch= new ProductbatchModel();
				batch.setBatchNo("");
				batch.setExpairyDate(deliveryData.getExpiryDate());
				batch.setQuantity(deliveryData.getQuantity().intValue());
				
				productBatchList.add(batch);
				productBatch.put(deliveryData.getId(), productBatchList);
			    batch= new ProductbatchModel();
			   
			}
			this.setGrnNo(branchsale.getDeliveryOrderNo());
			}
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"deliveryPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}
	
	
	
	public void loadPurchaseOrderItem()
	{
		FacesContext context = FacesContext.getCurrentInstance();	
		purchaseorder = new PurchaseorderModel();
		List<Integer> branchIds = new ArrayList<Integer>();
		receivalbeQuantity.clear();
		branchIds.clear();
		try {
			
			branchIds.add(loginBean.getBranch().getBranchId());
			purchaseorder=purchaseOrderBO.getPurchaseorderDetailforDelivery(this.getPurchaseOrderId(),branchIds);	
			productBatchList.clear();
			
			if(purchaseorder.getBranchModel()!=null && purchaseorder.getBranchModel().size()!=0)
			{
			for(PurchaserequestbreakdownModel deliveryData:purchaseorder.getBranchModel().iterator().next().getProductList())
			{
				List<ProductbatchModel> batchDataList = new ArrayList<ProductbatchModel>();
				productBatch.put(deliveryData.getId(), batchDataList);
			}
			}
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"deliveryPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}
	
	
	public void loadBranchList()
	{
		List<PurchaseorderbreakdownsModel> dataList=null;
		selectBranchList.clear();
		this.setBranchId(0);
		FacesContext context = FacesContext.getCurrentInstance();	
		purchaseorder = new PurchaseorderModel();
		try {
			if(this.getPurchaseOrderId()!=null && this.getPurchaseOrderId()!=0)
			{
		dataList=purchaseOrderBO.getPurchaseorderBranchList(this.getPurchaseOrderId());		
		for(PurchaseorderbreakdownsModel branchIdTemp:dataList)
		{	
			if(loginBean.getBranch().getBranchId()==branchIdTemp.getBranchId())
			{
			selectBranchList.add(new SelectItem(branchIdTemp.getBranchId(),branchIdTemp.getBranchName()));
			break;
			}
		}
		}
		}
		catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"deliveryPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
		}
	
	
	public void validateQuantity(Integer rowId)
	{
		FacesContext context = FacesContext.getCurrentInstance();	
		Integer balanceQuantity = 0;			
		PurchaserequestbreakdownModel c = purchaseorder.getBranchModel().iterator().next().getProductList().get(rowId);
		Integer receivableQuantity=0;
		try {
			if (ValidatorUtil.isNotNull(this.getReceivalbeQuantity().get(c.getId()))) 
			{
				receivableQuantity = new Integer(this.getReceivalbeQuantity().get(c.getId()));
			}	
				if(c.getBalanceQuantity()<receivableQuantity)
				{
					context.addMessage(UIComponent.findComponent(context.getViewRoot(),"receivable").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please Check the Receivable Quantity should be less than Balance Quantity", null));
					this.receivalbeQuantity.put(c.getId(),String.valueOf("0"));
				}
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"receivable").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}
	
	
	public void selectAddRemoveAmount(Integer rowId){			
		
		FacesContext context = FacesContext.getCurrentInstance();			
		PurchaserequestbreakdownModel c = purchaseorder.getBranchModel().iterator().next().getProductList().get(rowId);		
		try {
			if (ValidatorUtil.isNotNull(this.getCheckAmountApportion().get(c.getId()))) 
			{
				if(this.getCheckAmountApportion().get(c.getId()))
				{			
					this.receivalbeQuantity.put(c.getId(),String.valueOf(c.getBalanceQuantity()));					
				}
				else
				{
					this.receivalbeQuantity.put(c.getId(),String.valueOf("0"));					
				}
			}			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"receivable").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}		
			
		}	
	
	
	
	public void validateProductExpDate(Integer rowId)
	{
		FacesContext context = FacesContext.getCurrentInstance();						
		PurchaserequestbreakdownModel c = purchaseorder.getBranchModel().iterator().next().getProductList().get(rowId);		
		try {
			
				if(!DateUtil.isGreaterThanToday(this.getReceivalbeExpDate().get(c.getId())))
				{
					context.addMessage(UIComponent.findComponent(context.getViewRoot(),"receivableExpDate").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please Check the Receivable ExpDate should be greater than Current Date", null));
					this.receivalbeExpDate.put(c.getId(),null);
				}
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"receivable").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}
	
	
	
	
	public void validateEditQuantity(Integer rowId)
	{
		FacesContext context = FacesContext.getCurrentInstance();	
		Integer balanceQuantity = 0;			
		DeliveryorderbreakdownModel c = deliveryorder.getDeliveryorderbreakdowns().get(rowId);
		Integer receivableQuantity=0;
		try {
			if (ValidatorUtil.isNotNull(this.getReceivalbeQuantity().get(c.getId()))) 
			{
				BigDecimal rec = new BigDecimal(this.getReceivalbeQuantity().get(c.getId()));
				receivableQuantity = rec.intValue();
			}	
				if(c.getBalanceEditQuantity().intValue()<receivableQuantity)
				{
					context.addMessage(UIComponent.findComponent(context.getViewRoot(),"receivable").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please Check the Receivable Quantity should be less than Balance Quantity", null));
					this.receivalbeQuantity.put(c.getId(),String.valueOf("0"));
				}
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"receivable").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}
	
	
	
	
	public void validateProductEditExpDate(Integer rowId)
	{
		FacesContext context = FacesContext.getCurrentInstance();						
		DeliveryorderbreakdownModel c = deliveryorder.getDeliveryorderbreakdowns().get(rowId);		
		try {
			
				if(!DateUtil.isGreaterThanToday(this.getReceivalbeExpDate().get(c.getId())))
				{
					context.addMessage(UIComponent.findComponent(context.getViewRoot(),"receivableExpDate").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please Check the Receivable ExpDate should be greater than Current Date", null));
					this.receivalbeExpDate.put(c.getId(),null);
				}
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"receivable").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}
	
	
	
	
	public void saveDeliveryOrder()
	{
		DeliveryOrderBeanInfo deliveryOrderBeanInfo = new DeliveryOrderBeanInfo();
		FacesContext context = FacesContext.getCurrentInstance();	
		try {
			deliveryOrderBeanInfo.saveDeliveryOrder(purchaseorder);
			}
		catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"deliveryPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
		}
	
	
	public void saveBranchDeliveryOrder()
	{
		DeliveryOrderBeanInfo deliveryOrderBeanInfo = new DeliveryOrderBeanInfo();
		FacesContext context = FacesContext.getCurrentInstance();	
		try {
			deliveryOrderBeanInfo.saveBranchDeliveryOrder(branchsale);
			}
		catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"deliveryPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
		}
	
	
	public void updateDeliveryOrder()
	{
		DeliveryOrderBeanInfo deliveryOrderBeanInfo = new DeliveryOrderBeanInfo();
		FacesContext context = FacesContext.getCurrentInstance();	
		try {
			deliveryOrderBeanInfo.updateDeliveryOrder();
			}
		catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"deliveryPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
		}
	
	
	public void listDeliveryOrder()
	{
		DeliveryOrderBeanInfo deliveryOrderBeanInfo = new DeliveryOrderBeanInfo();
		FacesContext context = FacesContext.getCurrentInstance();	
		try {
			deliveryOrderBeanInfo.listDeliveryOrder();
			}
		catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"deliveryPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
		}
	
	
	public void newDeliveryOrder()
	{
		DeliveryOrderBeanInfo deliveryOrderBeanInfo = new DeliveryOrderBeanInfo();
		FacesContext context = FacesContext.getCurrentInstance();	
		try {
			deliveryOrderBeanInfo.newDeliveryOrder();
			resetDeliveryOrder();
			}
		catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"deliveryPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
		}
	
	
	public void newBranchDeliveryOrder()
	{
		DeliveryOrderBeanInfo deliveryOrderBeanInfo = new DeliveryOrderBeanInfo();
		FacesContext context = FacesContext.getCurrentInstance();	
		try {
			deliveryOrderBeanInfo.newBranchDeliveryOrder();
			resetDeliveryOrder();
			}
		catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"deliveryPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
		}
	
	
	
	
		public boolean validateDeliveryOrder()
	
		{
		DeliveryOrderBean deliveryOrderBean = (DeliveryOrderBean) BeanContext.getReference("deliveryOrderBean");
 		boolean valid=true;		
		String messageValue="";
		FacesContext context = FacesContext.getCurrentInstance();			
		try
		{
		if(this.getGrnNo()==null || this.getGrnNo()=="")
		{
		this.setGrnNo(deliveryorder.getInvoiceNo());
		}			
		if (factoryBean.checkIsNullAssignMessage(this.getGrnNo(),
				"purchaseorder.label.grnno", "grnno")) {
			valid = false;
		}		
		
		if (factoryBean.checkIsNullAssignMessage(deliveryOrderBean.getDeliveryorder().getSupplier().getSupplierCode(),
				"purchaseorder.label.grnno", "grnno")) {
			valid = false;
		}	
				
		else {
			List<DeliveryorderModel> deliveryList=deliveryOrderBO.getDeliveryorderList(null,this.getGrnNo(),null, deliveryOrderBean.getSupplierId(), null, null, null, 0, 3,null);
			
			if (deliveryOrderBean.getAction().equalsIgnoreCase("submit")) {
				if (deliveryList!=null && (!deliveryList.isEmpty())) {
					messageValue = factoryBean.getErrorFactory().getError("supplier.errors.grn.exists");
					factoryBean.reportErrorToMessageHandler(messageValue,messageValue, "grnno");
					valid = false;
				}
			}else{  
				
				if(! (this.getGrnNo()).equalsIgnoreCase(deliveryorder.getDeliveryOrderOldNo()) ){
											
					if (deliveryList!=null &&(!deliveryList.isEmpty())) {
						messageValue = factoryBean.getErrorFactory().getError("supplier.errors.grn.exists");
						factoryBean.reportErrorToMessageHandler(messageValue,messageValue, "grnno");
						valid = false;
					}
				}
			}
		}	
		
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"deliveryPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));			
			
		}
		
		return valid;
		}
	
		
		public void batchList(Integer productId,PurchaserequestbreakdownModel deliveryData)
		{
			 
			 this.setProductId(productId);
			 batch= new ProductbatchModel();
			 this.setDeliveryData(deliveryData);
			 
			if (ValidatorUtil.isNotNull(this.productBatch.get(deliveryData.getId()))) 
			{
				productBatchList = this.productBatch.get(deliveryData.getId());
				this.setBatchcount(getTotalBatchQuantity(productBatchList));
			}
			
		}
		
		
		public void batchBranchList(Integer productId,BranchsalesbreakdownModel deliveryData)
		{
			 
			 this.setProductId(productId);
			 batch= new ProductbatchModel();
			// this.setDeliveryData(deliveryData);
			 
			if (ValidatorUtil.isNotNull(this.productBatch.get(deliveryData.getId()))) 
			{
				productBatchList = this.productBatch.get(deliveryData.getId());
				this.setBatchcount(getTotalBatchQuantity(productBatchList));
			}
			
		}
		
		
		public void batchListEdit(Integer productId,DeliveryorderbreakdownModel deliveryData1)
		{
			 BeanUtilsBean beanUtils = new BeanUtilsBean();		
			 try
			 {
			 this.setProductId(productId);
			 batch= new ProductbatchModel();		 
			 
			 deliveryData.setProductCode(deliveryData1.getProductCode());
			 deliveryData.setProductName(deliveryData1.getProductName());
			 deliveryData.setProductId(productId);		
			 deliveryData.setId(deliveryData1.getId());
			 this.setDeliveryData(deliveryData);
			 
			if (ValidatorUtil.isNotNull(this.productBatch.get(deliveryData1.getId()))) 
			{
				productBatchList = this.productBatch.get(deliveryData1.getId());
				this.setBatchcount(getTotalBatchQuantity(productBatchList));
			}
			}
			 catch(Exception e)
			 {
				 
			 }
			
		}
		
		
		public void addDeliveryOrderBatch()
		{
			
			FacesContext context = FacesContext.getCurrentInstance();	
			try {
				
				if (ValidatorUtil.isNotNull(this.getReceivalbeQuantity().get(deliveryData.getId()))) 
				{
					if(batch.getQuantity()==0)
					{
						context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quantity").getClientId(context),
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Enetr Valid Quantity", null));
						
					}
					
					if(ValidatorUtil.isNull(batch.getBatchNo()))
					{
						context.addMessage(UIComponent.findComponent(context.getViewRoot(),"batchNo").getClientId(context),
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Batch No is Required", null));
						
					}
					
					if(ValidatorUtil.isNull(batch.getExpairyDate()))
					{
						context.addMessage(UIComponent.findComponent(context.getViewRoot(),"receivableExpDate").getClientId(context),
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Expairy Date is Required", null));
						
					}
					else if(ValidatorUtil.isNotNull(batch.getExpairyDate()) && (!DateUtil.isGreaterThanToday(batch.getExpairyDate())))
					{
						context.addMessage(UIComponent.findComponent(context.getViewRoot(),"receivableExpDate").getClientId(context),
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please Check the Product Expairy Date , should be greater than Current Date", null));
						batch.setExpairyDate(null);
					}
					else
					{							
					if(Integer.parseInt(this.getReceivalbeQuantity().get(deliveryData.getId()))>=(getTotalBatchQuantity(productBatchList)+batch.getQuantity()))
					{
						productBatchList.add(batch);
						productBatch.put(deliveryData.getId(), productBatchList);
					    batch= new ProductbatchModel();
					    this.setBatchcount(getTotalBatchQuantity(productBatchList));
					}
					else
					{
						context.addMessage(UIComponent.findComponent(context.getViewRoot(),"batchPanel").getClientId(context),
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Quantity should be less or equal to Receivable Quantity", null));
						batch= new ProductbatchModel();
					}
					}
				}
				else
				{
					context.addMessage(UIComponent.findComponent(context.getViewRoot(),"batchPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Enetr Receivable Quantity", null));
				}
				
				}
			catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"deliveryPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
			}
		
		
		public int getTotalBatchQuantity(List<ProductbatchModel> productBatchList)
		{			
			int count=0;			
			for(ProductbatchModel data:productBatchList)
			{
				count=count+data.getQuantity();
			}
			return count;
		}
		
		public void removeItem(ActionEvent event)
		{
			String batchId = "";
			batchId = (String) event.getComponent().getAttributes().get("batchId").toString();
			this.setRowId(Integer.parseInt(batchId));
		}
		
		public void removeItemConfirm()
		{
			ProductbatchModel c = productBatchList.get(this.getRowId());			
			productBatchList.remove(c);			
			this.setBatchcount(getTotalBatchQuantity(productBatchList));
		}	
		
		public void printDeliveryOrder(ActionEvent event) {
			
			FacesContext faces = FacesContext.getCurrentInstance();	
			try{				
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 			
			String deliveryOrderId = "";
			deliveryOrderId = (String) event.getComponent().getAttributes().get("deliveryOrderId").toString();
			faces.getExternalContext().redirect(request.getScheme() + "://" + request.getServerName() +":" + request.getServerPort() +"/"+config.getValue(IConfiguration.PROJECT_INSTANCE_NAME)+"/frameset?__report=report/stock/deliveryDetailReport.rptdesign&__format=pdf&&__pageoverflow=0&__asattachment=true&__overwrite=false&deliveryOrderId=" + deliveryOrderId+"&userId="+loginBean.getUserId() );
			faces.responseComplete();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		
	
}
