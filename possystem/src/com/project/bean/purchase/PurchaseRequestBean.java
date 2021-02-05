package com.project.bean.purchase;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.data.PageEvent;
import org.primefaces.model.LazyDataModel;

import com.project.bo.interfaces.IAdminDespatchBO;
import com.project.bo.interfaces.IBranchBO;
import com.project.bo.interfaces.IProductBO;
import com.project.bo.interfaces.IPurchaseOrderBO;
import com.project.bo.interfaces.IPurchaserequestBO;
import com.project.bo.interfaces.ISupplierBO;

import com.project.model.datamodel.AdmindespatchModel;
import com.project.model.datamodel.ProductModel;
import com.project.model.datamodel.ProductpriceModel;
import com.project.model.datamodel.ProductsupplierModel;
import com.project.model.datamodel.RolePrivilegesModel;
import com.project.model.datamodel.SupplierModel;
import com.project.model.datamodel.purchase.PurchaseorderConsolidateModel;
import com.project.model.datamodel.purchase.PurchaseorderModel;
import com.project.model.datamodel.purchase.PurchaseorderdeliveryaddressModel;
import com.project.model.datamodel.purchase.PurchaserequestConsolidateModel;
import com.project.model.datamodel.purchase.PurchaserequestModel;
import com.project.model.datamodel.purchase.PurchaserequestbreakdownModel;
import com.project.model.datamodel.stock.DeliveryorderbreakdownModel;
import com.project.util.DateUtil;
import com.project.bean.admin.CommonListBeanInfo;
import com.project.bean.admin.CustomerBeanInfo;
import com.project.bean.admin.ProductBeanInfo;
import com.project.bean.sales.quotation.QuotationSupplierBean;
import com.project.bean.sales.quotation.QuotationSupplierBeanInfo;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.util.DecimalUtil;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.common.validation.ValidatorUtil;
import com.project.home.DashboardBean;
import com.project.login.LoginBean;
import com.project.model.paginghelper.ProductPagingHelper;
import com.project.model.paginghelper.PurchaseRequestPagingHelper;


/*
 * @author Gopal Ar
 * @version 1.0
 * @since 17 July 2013
 * 
 */

@ManagedBean(name = "purchaseRequestBean")
@SessionScoped
public class PurchaseRequestBean {

	PurchaserequestModel purchaserequest = new PurchaserequestModel();
	List<PurchaserequestbreakdownModel> purchaserequestbreakdowns = new ArrayList<PurchaserequestbreakdownModel>();
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	List<SelectItem> supplierQty = new ArrayList<SelectItem>();
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	private LazyDataModel<PurchaserequestModel> purchaserequestModel = null;
	Configuration config = Configuration.getConfiguration();
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	private Map<Integer, String> supplierRemarks = new HashMap<Integer, String>();
	
	List<PurchaserequestConsolidateModel> purchaserequestConsolidateList = new ArrayList<PurchaserequestConsolidateModel>();
	List<PurchaseorderConsolidateModel> purchaseorderConsolidateList = new ArrayList<PurchaseorderConsolidateModel>();
	
	private String productCode;
	private Integer itemCount;
	private ProductModel product;
	private Integer supplierId;
	private String supplierName;
	private String uomName;
	private Integer branchId;	
	private String referenceNo;
	private Date dateFrom;
	private Date dateTo;
	private String status;
	private Integer rowId;
	private String sortId="t.purchaseRequestId";
	String branchstatus;
	String branchview;
	
	private Map<Integer, Boolean> checkMap = new HashMap<Integer, Boolean>();
	private boolean checkAll;  
	private boolean productCodedisable=false;
	private String action = "submit";
	private String itemaction = "submit";
	List<String> productcodes = new ArrayList<String>();
	List<Integer> supplierids = new ArrayList<Integer>();	 
	List<Integer> requestIds = new ArrayList<Integer>();
	
	private Map<String, Integer> additionalQty = new HashMap<String, Integer>();
	private Map<String, BigDecimal> priceQty = new HashMap<String, BigDecimal>();
	private Map<String, BigDecimal> purchasePrice = new HashMap<String, BigDecimal>();
	private Map<String, BigDecimal> salableTaxPrice = new HashMap<String, BigDecimal>();
	
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
	IPurchaserequestBO purchaseRequestBO=objectMapController.getPurchaseRequestBO();
													// service

	public IPurchaserequestBO getPurchaseRequestBO() {
		return purchaseRequestBO;
	}

	public void setPurchaseRequestBO(IPurchaserequestBO purchaseRequestBO) {
		this.purchaseRequestBO = purchaseRequestBO;
	}
	
	
	IPurchaseOrderBO purchaseOrderBO=objectMapController.getPurchaseOrderBO();
	
	public IPurchaseOrderBO getPurchaseOrderBO() {
		return purchaseOrderBO;
	}

	public void setPurchaseOrderBO(IPurchaseOrderBO purchaseOrderBO) {
		this.purchaseOrderBO = purchaseOrderBO;
	}
	
	IProductBO productBO=objectMapController.getProductBO();	
	
	public IProductBO getProductBO() {
		return productBO;
	}

	public void setProductBO(IProductBO productBO) {
		this.productBO = productBO;
	}		

	ISupplierBO supplierBO=objectMapController.getSupplierBO();
	
		
	public ISupplierBO getSupplierBO() {
		return supplierBO;
	}

	public void setSupplierBO(ISupplierBO supplierBO) {
		this.supplierBO = supplierBO;
	}	
	
	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}

	public String getProductCode() {
		return productCode;
	}	
	
	public Map<Integer, Boolean> getCheckMap() {
		return checkMap;
	}

	public void setCheckMap(Map<Integer, Boolean> checkMap) {
		this.checkMap = checkMap;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}	
	
	public boolean isCheckAll() {
		return checkAll;
	}

	public void setCheckAll(boolean checkAll) {
		this.checkAll = checkAll;
	}

	public Integer getItemCount() {
		return itemCount;
	}

	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}
	

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getUomName() {
		return uomName;
	}

	public void setUomName(String uomName) {
		this.uomName = uomName;
	}

	
	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
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

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}			
	
	public String getSortId() {
		return sortId;
	}
	public void setSortId(String sortId) {
		this.sortId = sortId;
	}
	public String getItemaction() {
		return itemaction;
	}
	
	public String getBranchview() {
		return branchview;
	}

	public void setBranchview(String branchview) {
		this.branchview = branchview;
	}


	public void setItemaction(String itemaction) {
		this.itemaction = itemaction;
	}

	public boolean isProductCodedisable() {
		return productCodedisable;
	}

	public void setProductCodedisable(boolean productCodedisable) {
		this.productCodedisable = productCodedisable;
	}	

	public Map<String, Integer> getAdditionalQty() {
		return additionalQty;
	}

	public void setAdditionalQty(Map<String, Integer> additionalQty) {
		this.additionalQty = additionalQty;
	}

	public Map<String, BigDecimal> getPriceQty() {
		return priceQty;
	}

	public void setPriceQty(Map<String, BigDecimal> priceQty) {
		this.priceQty = priceQty;
	}	

	public Map<String, BigDecimal> getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Map<String, BigDecimal> purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	
	public Map<Integer, String> getSupplierRemarks() {
		return supplierRemarks;
	}

	public void setSupplierRemarks(Map<Integer, String> supplierRemarks) {
		this.supplierRemarks = supplierRemarks;
	}

	
	
	public Map<String, BigDecimal> getSalableTaxPrice() {
		return salableTaxPrice;
	}
	public void setSalableTaxPrice(Map<String, BigDecimal> salableTaxPrice) {
		this.salableTaxPrice = salableTaxPrice;
	}
	public List<Integer> getRequestIds() {
		return requestIds;
	}

	public void setRequestIds(List<Integer> requestIds) {
		this.requestIds = requestIds;
	}
	
	public String getBranchstatus() {
		return branchstatus;
	}
	public void setBranchstatus(String branchstatus) {
		this.branchstatus = branchstatus;
	}
	public List<String> getProductcodes() {
		
		productcodes.clear();		
		for(PurchaserequestbreakdownModel data:purchaserequestbreakdowns)
		{
			productcodes.add(data.getProductCode());
		}		
		return productcodes;
	}

	public void setProductcodes(List<String> productcodes) {
		this.productcodes = productcodes;
	}

	public List<Integer> getSupplierids() {
		supplierids.clear();		
		for(PurchaserequestbreakdownModel data:purchaserequestbreakdowns)
		{
			supplierids.add(data.getSupplierId());
		}	
		return supplierids;
	}

	public void setSupplierids(List<Integer> supplierids) {
		this.supplierids = supplierids;
	}

	public PurchaserequestModel getPurchaserequest() {		
		purchaserequest.setBranchName(loginBean.getUserLoginModel().getBranchName());
		purchaserequest.setBranchId(loginBean.getUserLoginModel().getBranchId());
		return purchaserequest;
	}

	public void setPurchaserequest(PurchaserequestModel purchaserequest) {
		this.purchaserequest = purchaserequest;
	}

	public List<PurchaserequestbreakdownModel> getPurchaserequestbreakdowns() {
		return purchaserequestbreakdowns;
	}

	public void setPurchaserequestbreakdowns(
			List<PurchaserequestbreakdownModel> purchaserequestbreakdowns) {
		this.purchaserequestbreakdowns = purchaserequestbreakdowns;
	}
	
	public LazyDataModel<PurchaserequestModel> getPurchaserequestModel() {		
		return purchaserequestModel;
	}
	
	public List<PurchaserequestConsolidateModel> getPurchaserequestConsolidateList() {
		return purchaserequestConsolidateList;
	}

	public void setPurchaserequestConsolidateList(
			List<PurchaserequestConsolidateModel> purchaserequestConsolidateList) {
		this.purchaserequestConsolidateList = purchaserequestConsolidateList;
	}
	
	public List<PurchaseorderConsolidateModel> getPurchaseorderConsolidateList() {
		return purchaseorderConsolidateList;
	}

	public void setPurchaseorderConsolidateList(
			List<PurchaseorderConsolidateModel> purchaseorderConsolidateList) {
		this.purchaseorderConsolidateList = purchaseorderConsolidateList;
	}

	public LazyDataModel<PurchaserequestModel> getPurchaserequestList() {
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{		
		this.setPurchaserequestModel(null);
		purchaserequestModel = new PurchaseRequestPagingHelper(referenceNo,branchId, dateFrom, dateTo,status,purchaseRequestBO,this.getSortId(),this.getBranchview());		
		this.setPurchaserequestModel(purchaserequestModel);	
		this.checkMap.clear();
		}
		catch(Exception e){					
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"productPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
			}			
		return purchaserequestModel;
	}
	
	
	public void searchPurchaseRequest()
	{
		try
		{
			PurchaseRequestBeanInfo purchaseRequestBeanInfo = new PurchaseRequestBeanInfo();
			if(!purchaseRequestBeanInfo.validateRequestSearch())	
			{
			Date dateNow = new Date ();				 
		    SimpleDateFormat dateformatDDMMYYYY = new SimpleDateFormat("dd/MM/yyyy");		 
		    String nowYYYYMMDD = new String( dateformatDDMMYYYY.format(dateNow));    
			this.setDateFrom(DateUtil.getFromTodayDateTime());
			this.setDateTo(DateUtil.getToTodayDateTime());
			}					
			this.getPurchaserequestList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void searchPurchaseRequestReport()
	{
		try
		{
			PurchaseRequestBeanInfo purchaseRequestBeanInfo = new PurchaseRequestBeanInfo();
			if(!purchaseRequestBeanInfo.validateRequestSearch())	
			{
			Date dateNow = new Date ();				 
		    SimpleDateFormat dateformatDDMMYYYY = new SimpleDateFormat("dd/MM/yyyy");		 
		    String nowYYYYMMDD = new String( dateformatDDMMYYYY.format(dateNow));    
			this.setDateFrom(DateUtil.getFromTodayDateTime());
			this.setDateTo(DateUtil.getToTodayDateTime());
			}					
			//this.getPurchaserequestList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	

	public void setPurchaserequestModel(
			LazyDataModel<PurchaserequestModel> purchaserequestModel) {
		this.purchaserequestModel = purchaserequestModel;
	}
	
	
	
	public void viewPurchaseRequest(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	try {
		String purchaseRequestId = "";
		purchaseRequestId = (String) event.getComponent().getAttributes().get("purchaseRequestId").toString();		
		PurchaseRequestBeanInfo purchaseRequestBeanInfo = new PurchaseRequestBeanInfo();
		purchaserequest=purchaseRequestBeanInfo.editPurchaseRequest(Integer.parseInt(purchaseRequestId));	
		purchaserequestbreakdowns=purchaserequest.getPurchaserequestbreakdowns();
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	
	public void editPurchaseRequest(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	try {
		String purchaseRequestId = "";
		purchaseRequestId = (String) event.getComponent().getAttributes().get("purchaseRequestId").toString();		
		PurchaseRequestBeanInfo purchaseRequestBeanInfo = new PurchaseRequestBeanInfo();
		purchaserequest=purchaseRequestBeanInfo.editPurchaseRequest(Integer.parseInt(purchaseRequestId));	
		purchaserequestbreakdowns=purchaserequest.getPurchaserequestbreakdowns();
		
		purchaseRequestBeanInfo.newPurchaseRequest();
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	
	public void approvePurchaseRequest(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	try {
		String purchaseRequestId = "";
		purchaseRequestId = (String) event.getComponent().getAttributes().get("purchaseRequestId").toString();		
		PurchaseRequestBeanInfo purchaseRequestBeanInfo = new PurchaseRequestBeanInfo();
		purchaserequest=purchaseRequestBeanInfo.editPurchaseRequest(Integer.parseInt(purchaseRequestId));			
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	
	public void approvePurchaseRequestConfirm()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		boolean updateSuccess=false;
		try {			
			PurchaseRequestBeanInfo purchaseRequestBeanInfo = new PurchaseRequestBeanInfo();
			
			
			purchaserequest=purchaseRequestBeanInfo.editPurchaseRequest(purchaserequest.getPurchaseRequestId());	
			purchaserequest.setStatus(config.getValue(IConfiguration.PURCHASEREQUEST_STATUS_PROCESSED_VALUE));			
			updateSuccess=purchaseRequestBO.approvePurchaserequest(purchaserequest);
			
			if (updateSuccess) {					
				this.resetPurchaseRequest();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("purchaserequest.label.approved.success"),null));				
			}
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}	
	
	
	
	public void requestHqPurchaseRequest(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	try {
		String purchaseRequestId = "";
		purchaseRequestId = (String) event.getComponent().getAttributes().get("purchaseRequestId").toString();		
		PurchaseRequestBeanInfo purchaseRequestBeanInfo = new PurchaseRequestBeanInfo();
		purchaserequest=purchaseRequestBeanInfo.editPurchaseRequest(Integer.parseInt(purchaseRequestId));			
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	
	public void requestHqPurchaseRequestConfirm()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		boolean updateSuccess=false;
		try {			
			PurchaseRequestBeanInfo purchaseRequestBeanInfo = new PurchaseRequestBeanInfo();
			
			
			purchaserequest=purchaseRequestBeanInfo.editPurchaseRequest(purchaserequest.getPurchaseRequestId());	
			purchaserequest.setBranchstatus(config.getValue(IConfiguration.PURCHASEREQUEST_BRANCHSTATUS_PROCESSED_VALUE));	
			purchaserequest.setBranchview("1");
			updateSuccess=purchaseRequestBO.approvePurchaserequest(purchaserequest);
			
			if (updateSuccess) {					
				this.resetPurchaseRequest();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("purchaserequest.label.sync.success"),null));				
			}
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}	
	
	
	
	public void loadPurchaseRequest()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try {			
			PurchaseRequestBeanInfo purchaseRequestBeanInfo = new PurchaseRequestBeanInfo();
			purchaserequest=purchaseRequestBeanInfo.editPurchaseRequest(purchaserequest.getPurchaseRequestId());	
			purchaserequestbreakdowns=purchaserequest.getPurchaserequestbreakdowns();			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}	
	
	public void updatePurchaseRequest()  throws Exception 
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			PurchaseRequestBeanInfo  purchaseRequestBeanInfo = new PurchaseRequestBeanInfo();
			purchaseRequestBeanInfo.updatePurchaseRequest();
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
			}
	}
	
	public void editPurchaseRequestProduct(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	try {
		String purchaseRequestBreakdownId = "";
		purchaseRequestBreakdownId = (String) event.getComponent().getAttributes().get("purchaseRequestBreakdownId").toString();
		PurchaserequestbreakdownModel c = purchaserequestbreakdowns.get(Integer.parseInt(purchaseRequestBreakdownId));
		this.setProductCode(c.getProductCode());		
		ProductModel product = new ProductModel();
		product.setBarcode(c.getProductCode());	
		product.setProductName(c.getProductName());
		this.setProduct(product);		
		this.setSupplierId(c.getSupplierId());		
		this.setItemCount(c.getQuantityRequired());
		this.setAction("update");
		this.setProductCodedisable(true);
		this.setRowId(Integer.parseInt(purchaseRequestBreakdownId));
		this.loadSupplier();
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}	
	
	public void updatePurchaseRequestProduct() throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();	
	try {		
		this.setProductCodedisable(false);
		PurchaserequestbreakdownModel c = purchaserequestbreakdowns.get(this.getRowId());			
		purchaserequestbreakdowns.remove(c);
		addRequestItem();		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}

	public void getDynamicPurchaseRequestItemList(PurchaserequestbreakdownModel item)	
	{
		this.purchaserequestbreakdowns.add(item);
	}
		
	public void makePurchaseRequestfromLowStock()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		DashboardBean dashboardBean = (DashboardBean) BeanContext.getReference("dashboardBean");
		List<ProductsupplierModel> products=null;
		List<ProductModel> selectedProducts= new ArrayList<ProductModel>();
		
		selectedProducts=dashboardBean.getSelectedProducts();
		this.resetPurchaseRequest();
		try
		{
		for(ProductModel proItem:selectedProducts)
		{
			this.setProduct(proItem);
			products=productBO.getProductSuppliersByBarcode(proItem.getBarcode(),null);
			this.setSupplierId(products.get(0).getSupplierId());
			this.setItemCount(1);
			addRequestItem();
		}				
		dashboardBean.getSelectedProducts().clear();
		PurchaseRequestBeanInfo  purchaseRequestBeanInfo = new PurchaseRequestBeanInfo();
		purchaseRequestBeanInfo.newPurchaseRequest();
		}
		catch(Exception e)
		{
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));
		}
		
	}
	
	
	
	public void addRequestItem()
	{			
		PurchaserequestbreakdownModel item = new PurchaserequestbreakdownModel();
		//ProductModel product = new ProductModel();	
		SupplierModel supplierData = new SupplierModel();
		FacesContext context = FacesContext.getCurrentInstance();		
		boolean datamatch=false;
		int rowCount=0;
		 	try {		 		
		 		if(validatePurchaseAddItem())
		 		{
		 			//product=productBO.getProductDetailsByBarcode(this.getProduct().getBarcode(),loginBean.getBranch().getBranchId());
		 			product=productBO.getProductList(null, null, null, this.getProduct().getBarcode(), null, 0, 1, loginBean.getBranch().getBranchId(), null,null,null,null).get(0);
		 			
		 			//item.setContactPerson(product.getSupplierName());		 			
		 			 for(PurchaserequestbreakdownModel data:purchaserequestbreakdowns)
		 			 {			 				
		 				 if(data.getProductCode().equalsIgnoreCase(this.getProduct().getBarcode()) && data.getSupplierId()==this.getSupplierId())
		 				 {
		 					PurchaserequestbreakdownModel c = purchaserequestbreakdowns.get(rowCount);
		 					c.setQuantityRequired(this.getItemCount()+c.getQuantityRequired());
		 					purchaserequestbreakdowns.set(rowCount, c);		
		 					datamatch=true;
		 				 }		 				 
		 				rowCount++;
		 			 }
		 			if(!datamatch)
		 			{
		 			supplierData=supplierBO.getSupplierDetails(this.getSupplierId());		 
		 			item.setContactPerson(supplierData.getCompanyName());
		 			item.setSupplierId(this.getSupplierId());		 			
		 			item.setProductId(product.getProductId());
		 			item.setProductName(product.getProductName());
		 			item.setProductCode(this.getProduct().getBarcode());
		 			item.setQuantityRequired(this.getItemCount());
		 			item.setUomName(product.getUom());
		 			item.setTaxAmount(product.getTaxAmount());
		 			item.setTaxCode(product.getTaxCode());
		 			this.getDynamicPurchaseRequestItemList(item);
		 			}
		 			resetItem();
		 		}
		 		
		 		
		} catch (Exception e) {			
			
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));
		}				
	}
	
	public void removeItem(ActionEvent event)
	{
		String purchaseRequestBreakdownId = "";
		purchaseRequestBreakdownId = (String) event.getComponent().getAttributes().get("purchaseRequestBreakdownId").toString();
		this.setRowId(Integer.parseInt(purchaseRequestBreakdownId));
	}
	
	public void removeItemConfirm()
	{
		PurchaserequestbreakdownModel c = purchaserequestbreakdowns.get(this.getRowId());			
		purchaserequestbreakdowns.remove(c);
	}	
	
	
	
	public void handleSelect(SelectEvent event) {  
		CommonListBeanInfo commoninfo = new CommonListBeanInfo();
		//commoninfo.getAllSupplierListByProduct(this.getProduct().getBarcode());
	}
	
	public void loadSupplier() {  
		CommonListBeanInfo commoninfo = new CommonListBeanInfo();
		//commoninfo.getAllSupplierListByProduct(this.getProductCode());
	}	
	
	public void selectProduct(SelectEvent event)
	{
		this.product=((ProductModel) event.getObject());	
		this.setSupplierId(null);
		CommonListBeanInfo commoninfo = new CommonListBeanInfo();
		//commoninfo.getAllSupplierListByProduct(this.getProduct().getBarcode());
	}
	
	public List<SelectItem> getSupplierQty() {					
		return supplierQty;
	}	
	public void setSupplierQty(List<SelectItem> supplierQty) {
		this.supplierQty = supplierQty;
	}	
	
	public void savePurchaseRequest()
	{		
		PurchaseRequestBeanInfo  purchaseRequestBeanInfo = new PurchaseRequestBeanInfo();
		purchaseRequestBeanInfo.createNewPurchaseRequest();		
	}	
	
	public void resetPurchaseRequest()
	{
		this.setProductCode(null);
		this.setItemCount(1);
		this.setSupplierId(0);
		this.purchaserequestbreakdowns.clear();		
		purchaserequest = new PurchaserequestModel();
		purchaserequest.setBranchName(loginBean.getUserLoginModel().getBranchName());
		purchaserequest.setBranchId(loginBean.getUserLoginModel().getBranchId());		
		this.setPurchaserequest(purchaserequest);
		this.setAction("submit");
		this.setItemaction("submit");
		this.setProductCodedisable(false);
		
	}
	
	
	public void resetSearch()
	{
		this.setDateFrom(DateUtil.getFromTodayDateTime());
		this.setDateTo(DateUtil.getToTodayDateTime());
		this.setBranchId(0);
		this.setStatus(null);
		this.setReferenceNo(null);
		searchPurchaseRequest();
	}
	
	
	public void resetItem()
	{
		this.setProductCode(null);
		this.setItemCount(1);		
		this.getSupplierQty().clear();
		this.setSupplierId(0);
		this.setAction("submit");
		this.setProduct(new ProductModel());
		//this.setItemaction("submit");
		this.setProductCodedisable(false);
	}

	public void newPurchaseRequest()
	{
		PurchaseRequestBeanInfo  purchaseRequestBeanInfo = new PurchaseRequestBeanInfo();
		purchaseRequestBeanInfo.newPurchaseRequest();
		this.resetPurchaseRequest();
	}
	
	public void listPurchaseRequest()	{

		PurchaseRequestBeanInfo  purchaseRequestBeanInfo = new PurchaseRequestBeanInfo();
		purchaseRequestBeanInfo.listPurchaseRequest();		
	}
	
	
	public void onRowSelect(SelectEvent event) {  
	       
		FacesContext context = FacesContext.getCurrentInstance();
		try {						
			purchaserequest=((PurchaserequestModel) event.getObject());	
			this.setPurchaserequestbreakdowns(purchaserequest.getPurchaserequestbreakdowns());
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			
			}
		
    }  
	
	public void consolidatePR()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			List<Integer> requestIds = new ArrayList<Integer>();
			List<Integer> branchIds = new ArrayList<Integer>();
			
			if(validatePurchase())
			{
			for(PurchaserequestModel data : purchaserequestModel){
			
			for (Entry<Integer, Boolean> entry : checkMap.entrySet()) {
				if (entry.getValue()) {  
					if(entry.getKey().equals(data.getPurchaseRequestId())){
						requestIds.add(data.getPurchaseRequestId());
						branchIds.add(data.getBranchId());
						break;
					}
				}
			}
			}	
			purchaserequestbreakdowns=purchaseRequestBO.getPurchaserequestbreakdownListByProduct(requestIds);
			PurchaseRequestBeanInfo  purchaseRequestBeanInfo = new PurchaseRequestBeanInfo();
			purchaseRequestBeanInfo.consolidatePurchaseRequest();
			}
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			
			}
	}
	
	
	
	
	
	public void purchaseRequestView()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			List<Integer> requestIds = new ArrayList<Integer>();
			List<Integer> branchIds = new ArrayList<Integer>();
			
			if(validatePurchase())
			{
			for(PurchaserequestModel data : purchaserequestModel){
			
			for (Entry<Integer, Boolean> entry : checkMap.entrySet()) {
				if (entry.getValue()) {  
					if(entry.getKey().equals(data.getPurchaseRequestId())){
						requestIds.add(data.getPurchaseRequestId());
						if(!branchIds.contains(data.getBranchId()))
						{
						branchIds.add(data.getBranchId());
						}
						break;
					}
				}
			}
			}
			this.setRequestIds(requestIds);
			purchaseorderConsolidateList=purchaseRequestBO.getPurchaseorderConsolidation(requestIds);
			purchaserequestbreakdowns.clear();
			priceQty.clear();
			additionalQty.clear();
			purchasePrice.clear();
			salableTaxPrice.clear();
			for(PurchaseorderConsolidateModel data:purchaseorderConsolidateList)
			{				
				for(PurchaserequestbreakdownModel productData:data.getProductList())
				{
									
					purchaserequestbreakdowns.add(productData);
					this.priceQty.put(productData.getId(),productData.getUnitPrice());		
					this.additionalQty.put(productData.getId(),0);					
					
					BigDecimal priceAmt = productData.getUnitPrice().multiply(new BigDecimal(productData.getQuantityRequired()));	
					BigDecimal taxAmt = productData.getTaxCode().multiply((priceAmt.divide(new BigDecimal(100))));					
					this.salableTaxPrice.put(productData.getId(),taxAmt);	
					this.purchasePrice.put(productData.getId(), priceAmt.add(taxAmt));
					
				}
			}
			
			PurchaseRequestBeanInfo  purchaseRequestBeanInfo = new PurchaseRequestBeanInfo();
			purchaseRequestBeanInfo.purchaseRequestView();
			}
		
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			
	}
	}
	
	
	
	
	
	public void consolidateSummaryPurchaseRequest()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			List<Integer> requestIds = new ArrayList<Integer>();
			List<Integer> branchIds = new ArrayList<Integer>();
			
			if(validatePurchase())
			{
			for(PurchaserequestModel data : purchaserequestModel){
			
			for (Entry<Integer, Boolean> entry : checkMap.entrySet()) {
				if (entry.getValue()) {  
					if(entry.getKey().equals(data.getPurchaseRequestId())){
						requestIds.add(data.getPurchaseRequestId());
						if(!branchIds.contains(data.getBranchId()))
						{
						branchIds.add(data.getBranchId());
						}
						break;
					}
				}
			}
			}
			this.setRequestIds(requestIds);
			purchaserequestConsolidateList=purchaseRequestBO.getPurchaserequestbreakdownList(requestIds ,branchIds );
			
			
			PurchaseRequestBeanInfo  purchaseRequestBeanInfo = new PurchaseRequestBeanInfo();
			purchaseRequestBeanInfo.consolidateSummaryPurchaseRequest();
			}
		
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			
			}
	}
	
	
	public void purchaseRequestSummaryProductView()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			List<Integer> requestIds = new ArrayList<Integer>();
			List<Integer> branchIds = new ArrayList<Integer>();
			
			if(validatePurchase())
			{
			for(PurchaserequestModel data : purchaserequestModel){
			
			for (Entry<Integer, Boolean> entry : checkMap.entrySet()) {
				if (entry.getValue()) {  
					if(entry.getKey().equals(data.getPurchaseRequestId())){
						requestIds.add(data.getPurchaseRequestId());
						if(!branchIds.contains(data.getBranchId()))
						{
						branchIds.add(data.getBranchId());
						}
						break;
					}
				}
			}
			}
			this.setRequestIds(requestIds);
			purchaserequestConsolidateList=purchaseRequestBO.getPurchaserequestbreakdownList(requestIds ,branchIds );
						
			PurchaseRequestBeanInfo  purchaseRequestBeanInfo = new PurchaseRequestBeanInfo();
			purchaseRequestBeanInfo.purchaseRequestView();
			}
		
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			
			}
	}
	
	
	
	
	public void requestForQuotation()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		QuotationSupplierBean quotationsupplierBean = (QuotationSupplierBean) BeanContext.getReference("quotationsupplierBean");
		
		try {
			List<Integer> requestIds = new ArrayList<Integer>();
			List<Integer> branchIds = new ArrayList<Integer>();
			
			if(validatePurchase())
			{
			for(PurchaserequestModel data : purchaserequestModel){
			
			for (Entry<Integer, Boolean> entry : checkMap.entrySet()) {
				if (entry.getValue()) {  
					if(entry.getKey().equals(data.getPurchaseRequestId())){
						requestIds.add(data.getPurchaseRequestId());
						if(!branchIds.contains(data.getBranchId()))
						{
						branchIds.add(data.getBranchId());
						}
						break;
					}
				}
			}
			}
			this.setRequestIds(requestIds);
			purchaseorderConsolidateList=purchaseRequestBO.getPurchaseorderConsolidation(requestIds);
			for(PurchaseorderConsolidateModel data:purchaseorderConsolidateList)
			{				
				for(PurchaserequestbreakdownModel productData:data.getProductList())
				{
					ProductModel product = new ProductModel();
					product.setProductId(productData.getProductId());
					product.setProductCode(productData.getProductCode());
					product.setBarcode(productData.getProductCode());
					
					quotationsupplierBean.setProduct(product);					
					quotationsupplierBean.setItemCount(new BigDecimal(productData.getQuantityRequired()));					
					quotationsupplierBean.addQuotationItem();
									
				}
			}
			quotationsupplierBean.setAction("submit");
			quotationsupplierBean.setItemaction("submit");
			QuotationSupplierBeanInfo qinfo = new QuotationSupplierBeanInfo();
			qinfo.newQuotation();
			}
		
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			
	}
	}
	
	
	
	
	public void createPurchaseOrder()
	{
		
		FacesContext context = FacesContext.getCurrentInstance();
		boolean createPO=false;
		PurchaseOrderBean purchaseOrderBean = (PurchaseOrderBean) BeanContext.getReference("purchaseOrderBean");
		PurchaserequestModel purchaserequest = new PurchaserequestModel();
		List<PurchaserequestbreakdownModel> purchaserequestbreakdownsTemp = new ArrayList<PurchaserequestbreakdownModel>();
		try {		
			
			
			
			purchaserequest.setBranchId(loginBean.getLogdetail().getBranchId());
			purchaserequest.setCreatedBy(loginBean.getUserName());
			purchaserequest.setCreatedDate(DateUtil.getTodayDate());
			purchaserequest.setLastModifiedDate(DateUtil.getTodayDate());
			purchaserequest.setStatus("2");		
			
			for(PurchaserequestbreakdownModel dataTemp:purchaserequestbreakdowns)
			{				
				if (ValidatorUtil.isNotNull(this.getAdditionalQty().get(dataTemp.getId())) && ValidatorUtil.isNotNull(this.getPriceQty().get(dataTemp.getId()))) 
				{
					PurchaserequestbreakdownModel newRequestData = new PurchaserequestbreakdownModel();						
					String additionalQtyValue=""+this.getAdditionalQty().get(dataTemp.getId());
					
					if((!additionalQtyValue.equalsIgnoreCase("")) && (!additionalQtyValue.equalsIgnoreCase("0")))
					{						
						newRequestData.setDiscount(dataTemp.getDiscount());
						newRequestData.setDiscountAmount(dataTemp.getDiscountAmount());							
						newRequestData.setProductId(dataTemp.getProductId());									
						newRequestData.setQuantityRequired(Integer.parseInt(additionalQtyValue));	
						newRequestData.setUnitPrice(new BigDecimal(""+this.getPriceQty().get(dataTemp.getId())));
						newRequestData.setSubTotal(new BigDecimal(Integer.parseInt(additionalQtyValue)).multiply(newRequestData.getUnitPrice()));
						newRequestData.setSupplierId(dataTemp.getSupplierId());
						
						purchaserequestbreakdownsTemp.add(newRequestData);
					}								
				}				
			}				    
			purchaserequest.setPurchaserequestbreakdowns(purchaserequestbreakdownsTemp);
			
			List<PurchaseorderModel> PurchaseorderList=purchaseRequestBO.createPurchaseorderList(this.getRequestIds(),purchaserequestbreakdowns,priceQty,supplierRemarks,purchaserequest,loginBean.getBranch().getBranchId());
			//createPO=purchaseOrderBO.createBulkPurchaseorder(PurchaseorderList,this.getRequestIds());				
			
				this.getRequestIds().clear();				
				Date dateNow = new Date ();				 
			    SimpleDateFormat dateformatDDMMYYYY = new SimpleDateFormat("dd/MM/yyyy");		 
			    String nowYYYYMMDD = new String( dateformatDDMMYYYY.format(dateNow));	       
			    purchaseOrderBean.setDateFrom(DateUtil.getFromTodayDateTime());
			    purchaseOrderBean.setDateTo(DateUtil.getToTodayDateTime());			
				
				PurchaseRequestBeanInfo  purchaseRequestBeanInfo = new PurchaseRequestBeanInfo();
				purchaseRequestBeanInfo.listPurchaseRequest();
				
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("purchaseorder.label.created.success"),null));				
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			
			}
	}
	
	
	
	
	
	public void selectAddRemove(){
		
		for (PurchaserequestModel data : purchaserequestModel) {		
			if(data.getStatus().equalsIgnoreCase(config.getValue(IConfiguration.PURCHASEREQUEST_STATUS_PROCESSED_VALUE)) && data.getBranchtype().equalsIgnoreCase(config.getValue(IConfiguration.PROJECT_TYPE_HQ_VALUE)))
			{	
				if(checkAll)
				{
				checkMap.put(data.getPurchaseRequestId(), Boolean.TRUE);
				}
				else
				{
				checkMap.put(data.getPurchaseRequestId(), Boolean.FALSE);
				}
			}		
			
			if(data.getBranchstatus().equalsIgnoreCase(config.getValue(IConfiguration.PURCHASEREQUEST_BRANCHSTATUS_PROCESSED_VALUE)) && data.getBranchtype().equalsIgnoreCase(config.getValue(IConfiguration.PROJECT_TYPE_BRANCH_VALUE)))
			{	
				if(checkAll)
				{
				checkMap.put(data.getPurchaseRequestId(), Boolean.TRUE);
				}
				else
				{
				checkMap.put(data.getPurchaseRequestId(), Boolean.FALSE);
				}
			}	
			
		}
		}


		public void generatePOViewIndividual(ActionEvent event)
		{
			FacesContext context = FacesContext.getCurrentInstance();	
			try {
				String purchaseRequestId = "";
				purchaseRequestId = (String) event.getComponent().getAttributes().get("purchaseRequestId").toString();		
				PurchaserequestModel purchaserequestModel=purchaseRequestBO.getPurchaserequestDetails(Integer.parseInt(purchaseRequestId));			
				List<Integer> branchids = new ArrayList<Integer>();
				branchids.add(purchaserequestModel.getBranchId());				
				List<Integer> requestIds = new ArrayList<Integer>();
				requestIds.add(purchaserequestModel.getPurchaseRequestId());					
				this.setRequestIds(requestIds);				
				purchaserequestConsolidateList=purchaseRequestBO.getPurchaserequestbreakdownList(requestIds ,branchids);
				PurchaseRequestBeanInfo  purchaseRequestBeanInfo = new PurchaseRequestBeanInfo();
				purchaseRequestBeanInfo.purchaseRequestView();
				
				
			} catch (Exception e) {
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
				
				}
		}
		
	
		
		public boolean validatePurchase() {
			boolean valid = false;		
			
			String messageValue=null;
			FacesContext context = FacesContext.getCurrentInstance();	

			try
			{			
			if (checkMap==null && checkMap.isEmpty()) {								
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please choose the records", null));
				valid = false;
			}	
			else
			{
				for (Entry<Integer, Boolean> entry : checkMap.entrySet()) {
					if (entry.getValue()) {  
							valid=true;
							break;
						}
					else
					{
						valid=false;
					}					
						}
						if(!valid)
						{
						context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please choose the records", null));
						valid = false;
						}
						
			}
			}
			catch(Exception e)
			{
				context.addMessage(
				UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

			}			
			return valid;

		}

		
	
		public boolean validatePurchaseAddItem()
		{
			boolean valid=true;
			FacesContext context = FacesContext.getCurrentInstance();	
			try
			{   
				if (factoryBean.checkIsNullAssignMessage(this.getProduct().getBarcode(),
						"purchaserequest.label.productCode", "productCode")) {
					valid = false;
				}
				/*else
				{
					 valid=productBO.findbarcodeExites(this.getProduct().getBarcode());
					 if(!valid)
					 {
						context.addMessage(UIComponent.findComponent(context.getViewRoot(),"productCode").getClientId(context),
						new FacesMessage(FacesMessage.SEVERITY_ERROR,factoryBean.getMessageFactory().getMessage("purchaserequest.label.barcode.notexists"),null));
						}
				}	*/			
				if (factoryBean.checkIsNullAssignMessage(this.getSupplierId(),
						"purchaserequest.label.supplierId", "supplierId")) {
					valid = false;
				}
				
				else if (factoryBean.checkIsZeroAssignMessage(String.valueOf(this.getSupplierId()),
						"purchaserequest.label.supplierId", "supplierId")) {
					valid = false;
				}				 
			}
			catch(Exception e)
			{
				context.addMessage(
				UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

			}		
			return valid;
		}
	
		
		
		public void getPurchaseSubTotal(Integer rowId,Integer supplierId)
		{
			FacesContext context = FacesContext.getCurrentInstance();				
			String additionalQtyValue = "0";	
			Integer totalQtyValue = 0;
			BigDecimal priceAmt=new BigDecimal(0.00);
			BigDecimal taxAmt=new BigDecimal(0.00);
			BigDecimal PriceQtyValue = new BigDecimal(0.00);
			PurchaserequestbreakdownModel c = new PurchaserequestbreakdownModel();
			for(PurchaseorderConsolidateModel data:purchaseorderConsolidateList)
			{				
				if(data.getSupplierId()==supplierId)
				{				
				 c = data.getProductList().get(rowId);				
				}
			}			
			
			Integer receivableQuantity=0;
			try {
				if (ValidatorUtil.isNotNull(this.getAdditionalQty().get(c.getId())) && ValidatorUtil.isNotNull(this.getPriceQty().get(c.getId()))) 
				{
					PriceQtyValue=new BigDecimal(""+this.getPriceQty().get(c.getId()));
					this.priceQty.put(c.getId(),PriceQtyValue);
					additionalQtyValue=""+this.getAdditionalQty().get(c.getId());
					if(additionalQtyValue.equalsIgnoreCase(""))
					{
						additionalQtyValue="0";
						this.additionalQty.put(c.getId(),0);
					}					
					totalQtyValue=c.getQuantityRequired()+Integer.parseInt(additionalQtyValue);
					
					if(PriceQtyValue.doubleValue()==0)
					{						
						this.priceQty.put(c.getId(),new BigDecimal(0.00));
					}					
					priceAmt=PriceQtyValue.multiply(new BigDecimal(totalQtyValue));					
					taxAmt = c.getTaxCode().multiply((priceAmt.divide(new BigDecimal(100))));					
					this.salableTaxPrice.put(c.getId(),taxAmt);	
					this.purchasePrice.put(c.getId(), priceAmt.add(taxAmt));
					
				}
				else
				{					
					priceAmt = c.getUnitPrice().multiply(new BigDecimal(c.getQuantityRequired()));			
					taxAmt = c.getTaxCode().multiply((priceAmt.divide(new BigDecimal(100))));					
					this.salableTaxPrice.put(c.getId(),taxAmt);	
					this.purchasePrice.put(c.getId(), priceAmt.add(taxAmt));
				
				}
				
			} catch (Exception e) {
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"purchaseRequestPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
				}
		}
		
		
		
		public void sortByOrder(String sortId)
		{						
			this.setSortId(sortId);
			searchPurchaseRequest();
			
		}

		
		
		public void printPurchaseRequest(ActionEvent event) {
			
			FacesContext faces = FacesContext.getCurrentInstance();	
			try{				
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 			
			String purchaseRequestId = "";
			purchaseRequestId = (String) event.getComponent().getAttributes().get("purchaseRequestId").toString();
			faces.getExternalContext().redirect(request.getScheme() + "://" + request.getServerName() +":" + request.getServerPort() +"/"+config.getValue(IConfiguration.PROJECT_INSTANCE_NAME)+"/frameset?__report=report/purchase/purchaseRequestDetail.rptdesign&__format=pdf&&__pageoverflow=0&__asattachment=true&__overwrite=false&purchaseRequestId=" + purchaseRequestId+"&userId="+loginBean.getUserId());
			faces.responseComplete();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		
		
			public void printPurchaseRequestConsolidate(ActionEvent event) {
			
			FacesContext faces = FacesContext.getCurrentInstance();	
			try{				
				
				StringBuilder requestIdsbuf = new StringBuilder("");
				String comma=",";
				int count=0;
			
				for(Integer id:requestIds)
				{
					if(count==requestIds.size()-1)
					{
					requestIdsbuf.append(String.valueOf(id));
					}
					else
					{
					requestIdsbuf.append(String.valueOf(id).concat(comma));	
					}
					count=count+1;
				}
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();			
			faces.getExternalContext().redirect(request.getScheme() + "://" + request.getServerName() +":" + request.getServerPort() +"/"+config.getValue(IConfiguration.PROJECT_INSTANCE_NAME)+"/frameset?__report=report/purchaseReportConsolidate.rptdesign&__format=pdf&&__pageoverflow=0&__asattachment=true&__overwrite=false&requestIdds=" +requestIdsbuf+"&userId="+loginBean.getUserId() );
			faces.responseComplete();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
			
			public void printPurchaseRequestConsolidateSummary(ActionEvent event) {
				
				FacesContext faces = FacesContext.getCurrentInstance();	
				try{				
				HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 			
				String purchaseRequestId = "";
				purchaseRequestId = (String) event.getComponent().getAttributes().get("purchaseRequestId").toString();
				faces.getExternalContext().redirect(request.getScheme() + "://" + request.getServerName() +":" + request.getServerPort() +"/"+config.getValue(IConfiguration.PROJECT_INSTANCE_NAME)+"/frameset?__report=report/purchaseRequestDetail.rptdesign&__format=pdf&&__pageoverflow=0&__asattachment=true&__overwrite=false&purchaseRequestId=" + purchaseRequestId+"&userId="+loginBean.getUserId() );
				faces.responseComplete();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
			}


		
		
}
