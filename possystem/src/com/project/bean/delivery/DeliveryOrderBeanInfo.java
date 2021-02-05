package com.project.bean.delivery;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.model.datamodel.purchase.PurchaseorderModel;
import com.project.model.datamodel.purchase.PurchaseorderbreakdownsModel;
import com.project.model.datamodel.purchase.PurchaserequestbreakdownModel;
import com.project.model.datamodel.stock.DeliveryorderModel;
import com.project.model.datamodel.stock.DeliveryorderbreakdownModel;
import com.project.model.datamodel.stock.ProductbatchModel;
import com.project.model.sale.sales.branchsale.BranchsaleModel;
import com.project.model.sale.sales.branchsale.BranchsalesbreakdownModel;
import com.project.util.DateUtil;
import com.project.bo.interfaces.IDeliveryorderBO;
import com.project.bo.interfaces.IProductbatchBO;
import com.project.bo.interfaces.IPurchaseOrderBO;
import com.project.bean.admin.ProductCategoryBean;
import com.project.bean.purchase.PurchaseOrderBean;
import com.project.bean.purchase.PurchaseOrderBeanInfo;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.factory.ErrorFactory;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.common.validation.ValidatorUtil;
import com.project.home.ProjectHome;
import com.project.login.LoginBean;
public class DeliveryOrderBeanInfo {

	PurchaseOrderBean purchaseOrderBean = (PurchaseOrderBean) BeanContext.getReference("purchaseOrderBean");
	DeliveryOrderBean deliveryOrderBean = (DeliveryOrderBean) BeanContext.getReference("deliveryOrderBean");

	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	public static Logger log = LoggerFactory.getLogger(DeliveryOrderBeanInfo.class);
	FacesContext context = FacesContext.getCurrentInstance();
	Configuration config = Configuration.getConfiguration();
	String projectHome = config.getValue(IConfiguration.PROJECT_HOME_FILENAME);
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	static ErrorFactory errorFactory = ErrorFactory.getInstance();

	Integer balanceQuantity = 0;
	Integer itemCount = 0;
	Integer batchCount = 0;
	
	Integer receivedQuantity = 0;
	
	public void listDeliveryOrder() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/delivery/deliveryOrder.xhtml");
		projectHome.setTitlePage("Stock --> GRN -->Search DeliveryOrder");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}

	
	
	public void newDeliveryOrder() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/delivery/addEditDeliveryOrder.xhtml");
		projectHome.setTitlePage("Stock --> GRN --> Add/Edit DeliveryOrder");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
	public void newBranchDeliveryOrder() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/delivery/addEditBranchDeliveryOrder.xhtml");
		projectHome.setTitlePage("Stock --> GRN --> Add/Edit DeliveryOrder");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
	
	
	
	public void editDeliveryOrder() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/delivery/EditDeliveryOrder.xhtml");
		projectHome.setTitlePage("Stock --> GRN --> Add/Edit DeliveryOrder");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
	
	
	public void saveDeliveryOrder(PurchaseorderModel purchaseorderModel)
	{			
		List<DeliveryorderbreakdownModel> deliverydataList = new ArrayList<DeliveryorderbreakdownModel>();
		List<PurchaseorderbreakdownsModel> purchasedataList = new ArrayList<PurchaseorderbreakdownsModel>();
		boolean updateSuccess=false;
		int receivedQuantityCount=purchaseorderModel.getReceivedQuantityCount();
		int balanceQuantityCount=purchaseorderModel.getBalanceQuantityCount();
		int totalItemCount=purchaseorderModel.getTotalItemCount();
		List<ProductbatchModel> productBatchList= new ArrayList<ProductbatchModel>();
		BigDecimal totalDeliveryAmt = new BigDecimal(0);		
		BigDecimal productAmount = new BigDecimal(0);
		batchCount = 0;
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{
			
		
		IDeliveryorderBO deliveryOrderBO=deliveryOrderBean.getDeliveryOrderBO();
		IProductbatchBO productbatchBO=deliveryOrderBean.getProductbatchBO();
		deliveryOrderBean.setBranchId(loginBean.getBranch().getBranchId());
		DeliveryorderModel deliveryorder = new DeliveryorderModel();
		deliveryorder.setPurchaseOrderDate(purchaseorderModel.getPurchaseOrderDate());
		deliveryorder.setPurchaseOrderNo(purchaseorderModel.getPurchaseOrderNo());
		deliveryorder.setCreatedDate(DateUtil.getTodayDate());
		deliveryorder.setCreatedBy(loginBean.getUserName());
		deliveryorder.setSupplierId(deliveryOrderBean.getSupplierId());		
		//deliveryorder.setBranchId(deliveryOrderBean.getBranchId());
		deliveryorder.setBranchId(loginBean.getBranch().getBranchId());
		deliveryorder.setStatus(config.getValue(IConfiguration.DELIVERYORDER_STATUS_NEWORDER_VALUE));
		deliveryorder.setDeliveryOrderNo(deliveryOrderBean.getGrnNo());
		deliveryorder.setRemarks(deliveryOrderBean.getDeliveryorder().getRemarks());
		deliveryorder.setInvoiceNo(deliveryOrderBean.getDeliveryorder().getInvoiceNo());
		deliveryorder.setBranchRecordId(loginBean.getBranch().getBranchId());
		deliveryorder.setBranchtype(loginBean.getBranch().getBranchtype());
		deliveryorder.setDeliveryType("1"); // Supplier
		
		for(PurchaserequestbreakdownModel productData:purchaseorderModel.getBranchModel().iterator().next().getProductList())
		{
			PurchaseorderbreakdownsModel product = new PurchaseorderbreakdownsModel();	
			
			if (ValidatorUtil.isNotNull(deliveryOrderBean.getReceivalbeQuantity().get(productData.getId()))) 
			{
			balanceQuantity	=Integer.parseInt(deliveryOrderBean.getReceivalbeQuantity().get(productData.getId()));
			itemCount=itemCount+balanceQuantity;
			if(balanceQuantity!=0)
			{
			DeliveryorderbreakdownModel deliverydata =buildDeliveryData(productData);	
			deliverydata.setPurchaseOrderBreakdownId(productData.getPurchaseRequestBreakdownId());
			
			totalDeliveryAmt = totalDeliveryAmt.add(deliverydata.getSubTotal());			
			receivedQuantityCount=receivedQuantityCount+balanceQuantity;
			balanceQuantityCount=balanceQuantityCount-balanceQuantity;
			
			deliverydataList.add(deliverydata);
			}
			}
			product.setBalanceQuantity(productData.getBalanceQuantity()- balanceQuantity);			
			product.setReceivedQuantity(productData.getReceivedQuantity()+ balanceQuantity);			
			purchasedataList.add(product); // Update Purchase order quantity for this branch
		}
		
		
		// Batch Details
		for(PurchaserequestbreakdownModel productData:purchaseorderModel.getBranchModel().iterator().next().getProductList())
		{
		List<ProductbatchModel> productBatchListTemp= new ArrayList<ProductbatchModel>();
		
		
		if (ValidatorUtil.isNotNull(deliveryOrderBean.getProductBatch().get(productData.getId()))) 
		{
			productBatchListTemp = deliveryOrderBean.getProductBatch().get(productData.getId());
		}
		
		for(ProductbatchModel data:productBatchListTemp)
		{
			data.setProductId(productData.getProductId());
			data.setDeliveryOrderNo(deliveryOrderBean.getGrnNo());		
			data.setBranchId(loginBean.getBranch().getBranchId());			
			productBatchList.add(data);
			batchCount=batchCount+data.getQuantity();
			
		}
		}		
	
		purchaseorderModel.setPurchaseOrderId(deliveryOrderBean.getPurchaseOrderId());
		purchaseorderModel.setSupplierId(deliveryOrderBean.getSupplierId());	
		purchaseorderModel.setBranchId(deliveryOrderBean.getBranchId());		
		purchaseorderModel.setPurchaseorderbreakdowns(purchasedataList);	
		
		
		deliveryorder.setTotalAmount(totalDeliveryAmt);		
		deliveryorder.setDeliveryorderbreakdowns(deliverydataList);
		deliveryorder.setItemCount(itemCount);
		
		deliveryorder.setProductBatchList(productBatchList);
		if(validateDeliveryOrder(deliveryorder))
		{
		updateSuccess=deliveryOrderBO.createNewDeliveryorder(deliveryorder, purchaseorderModel);
		
		}
		if (updateSuccess) {
			//productbatchBO.createProductbatch(productBatchList);
			this.resetDeliveryOrder();
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"deliveryPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("deliveryorder.label.created.success"),null));				
		}		
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"deliveryPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));				
			log.info("Error in saveDeliveryOrder of DeliveryOrderBeanInfo "+ e.toString());
		}
	}
	
	
	
	
	public void updateDeliveryOrder()
	{
		List<DeliveryorderbreakdownModel> deliverydataList = new ArrayList<DeliveryorderbreakdownModel>();
		List<PurchaseorderbreakdownsModel> purchasedataList = new ArrayList<PurchaseorderbreakdownsModel>();
		boolean updateSuccess=false;
		batchCount = 0;
		PurchaseorderModel purchaseorderModel=deliveryOrderBean.getPurchaseorder();
		DeliveryorderModel deliveryorder =deliveryOrderBean.getDeliveryorder();	
		deliveryorder.setDeliveryOrderNo(deliveryOrderBean.getGrnNo());
		BigDecimal totalDeliveryAmt = new BigDecimal(0);		
		BigDecimal productAmount = new BigDecimal(0);		
		List<ProductbatchModel> productBatchList= new ArrayList<ProductbatchModel>();
		
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{
		IDeliveryorderBO deliveryOrderBO=deliveryOrderBean.getDeliveryOrderBO();
		IPurchaseOrderBO purchaseOrderBO=deliveryOrderBean.getPurchaseOrderBO();
		IProductbatchBO productbatchBO=deliveryOrderBean.getProductbatchBO();
		
		for(DeliveryorderbreakdownModel productData:deliveryOrderBean.getDeliveryorder().getDeliveryorderbreakdowns())
		{
			PurchaseorderbreakdownsModel product = new PurchaseorderbreakdownsModel();
			if (ValidatorUtil.isNotNull(deliveryOrderBean.getReceivalbeQuantity().get(productData.getId()))) 
			{
			balanceQuantity	=Integer.parseInt(deliveryOrderBean.getReceivalbeQuantity().get(productData.getId()));
			itemCount=itemCount+balanceQuantity;
			
			DeliveryorderbreakdownModel deliverydata =buildDeliveryData(productData);
			totalDeliveryAmt = totalDeliveryAmt.add(deliverydata.getSubTotal());
			deliverydataList.add(deliverydata);
			
			//Update Purchase Order Quantity
			product.setBalanceQuantity((new BigDecimal(productData.getPurchaseOrderBalanceQuantity()).add(productData.getQuantity())).subtract(new BigDecimal(balanceQuantity)).intValue());
			product.setReceivedQuantity((new BigDecimal(productData.getPurchaseOrderReceivedQuantity()).subtract(productData.getQuantity())).add(new BigDecimal(balanceQuantity)).intValue());
			product.setProductId(productData.getProductId());
			product.setPurchaseOrderBreakdownId(productData.getPurchaseOrderBreakdownId());
			purchasedataList.add(product);
			
			}
			
			// Batch Details
			
			List<ProductbatchModel> productBatchListTemp= new ArrayList<ProductbatchModel>();
			if (ValidatorUtil.isNotNull(deliveryOrderBean.getProductBatch().get(productData.getId()))) 
			{
			productBatchListTemp = deliveryOrderBean.getProductBatch().get(productData.getId());
			}
			for(ProductbatchModel data:productBatchListTemp)
			{
			data.setProductId(productData.getProductId());
			data.setDeliveryOrderNo(deliveryOrderBean.getGrnNo());
			data.setBranchId(loginBean.getBranch().getBranchId());	
			productBatchList.add(data);
			batchCount=batchCount+data.getQuantity();
			}
			
			
		}		
		purchaseorderModel.setPurchaseorderbreakdowns(purchasedataList);		
		
		deliveryorder.setTotalAmount(totalDeliveryAmt);		
		deliveryorder.setDeliveryorderbreakdowns(deliverydataList);		
		deliveryorder.setItemCount(itemCount);
		
		
		
		
		for(DeliveryorderbreakdownModel productData:deliveryOrderBean.getDeliveryorder().getDeliveryorderbreakdowns())
		{
		
		}
		
		
		//deliveryorder.setProductBatchList(productBatchList);
		
		if(validateDeliveryOrder(deliveryorder))
		{
		 updateSuccess=deliveryOrderBO.updateDeliveryorder(deliveryorder);
		}
		if(updateSuccess){
			updateSuccess=purchaseOrderBO.updatePurchaseorderForDelivery(purchaseorderModel);
			}

		
				
		if (updateSuccess) {	
			productbatchBO.updateProductbatch(productBatchList);
			this.listDeliveryOrder();
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"deliveryPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("deliveryorder.label.update.success"),null));				
				
		}
		}
		
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"deliveryPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));				
			log.info("Error in updateDeliveryOrder of DeliveryOrderBeanInfo "+ e.toString());
		}
	}
	
	
	
	
	
	public void saveBranchDeliveryOrder(BranchsaleModel branchsale)
	{			
		List<DeliveryorderbreakdownModel> deliverydataList = new ArrayList<DeliveryorderbreakdownModel>();
		List<PurchaseorderbreakdownsModel> purchasedataList = new ArrayList<PurchaseorderbreakdownsModel>();
		boolean updateSuccess=false;		
		List<ProductbatchModel> productBatchList= new ArrayList<ProductbatchModel>();
		BigDecimal totalDeliveryAmt = new BigDecimal(0);		
		BigDecimal productAmount = new BigDecimal(0);
		batchCount = 0;
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{		
		IDeliveryorderBO deliveryOrderBO=deliveryOrderBean.getDeliveryOrderBO();
		IProductbatchBO productbatchBO=deliveryOrderBean.getProductbatchBO();		
		DeliveryorderModel deliveryorder = new DeliveryorderModel();		
		deliveryorder.setCreatedDate(DateUtil.getTodayDate());
		deliveryorder.setCreatedBy(loginBean.getUserName());				
		deliveryorder.setBranchId(deliveryOrderBean.getBranchId());		
		deliveryorder.setStatus(config.getValue(IConfiguration.DELIVERYORDER_STATUS_NEWORDER_VALUE));
		deliveryorder.setDeliveryOrderNo(deliveryOrderBean.getGrnNo());
		deliveryorder.setRemarks(deliveryOrderBean.getDeliveryorder().getRemarks());
		deliveryorder.setInvoiceNo(deliveryOrderBean.getDeliveryorder().getInvoiceNo());
		deliveryorder.setBranchRecordId(loginBean.getBranch().getBranchId());
		deliveryorder.setBranchtype(loginBean.getBranch().getBranchtype());
		deliveryorder.setDeliveryType("2"); //Deliveery by Branch 
		
		for(BranchsalesbreakdownModel productData:branchsale.getBranchsalesbreakdowns())
		{
			int totalItemCount=0;
			DeliveryorderbreakdownModel data =new 	DeliveryorderbreakdownModel();	
			ProductbatchModel batchdata=new ProductbatchModel();
			
			data.setDiscount(productData.getDiscount());
			data.setDiscountAmount(productData.getDiscountAmount());			
			data.setQuantity(productData.getQuantity());			
			data.setSubTotal(productData.getSubTotal());
			data.setUnitPrice(productData.getUnitPrice());	
			data.setExpDate(productData.getExpiryDate());			
			data.setCreatedDate(new Date());
			data.setBalanceQuantity(new BigDecimal(0.00));
			data.setSoldQuantity(new BigDecimal(0.00));
			data.setSalesbalanceQuantity(productData.getQuantity());
			data.setProductId(productData.getProductId());
			data.setTaxAmount(productData.getTaxAmount());
			data.setTaxCode(productData.getTaxCode());
			
			totalItemCount=totalItemCount+productData.getQuantity().intValue();
			
			batchdata.setProductId(productData.getProductId());
			batchdata.setDeliveryOrderNo(deliveryOrderBean.getGrnNo());		
			batchdata.setBranchId(loginBean.getBranch().getBranchId());	
			batchdata.setBatchNo(productData.getBatchNo());
			batchdata.setExpairyDate(productData.getExpiryDate());
			batchdata.setDeliveryOrderNo(deliveryOrderBean.getGrnNo());
			batchdata.setQuantity(productData.getQuantity().intValue());
			
			productBatchList.add(batchdata);
			batchCount=batchCount+data.getQuantity().intValue();				
			deliverydataList.add(data);				
			
		}	
		
		deliveryorder.setTotalAmount(branchsale.getTotalAmount());		
		deliveryorder.setDeliveryorderbreakdowns(deliverydataList);
		deliveryorder.setItemCount(itemCount);		
		deliveryorder.setProductBatchList(productBatchList);
		if(validateBranchDeliveryOrder(deliveryorder))
		{
		updateSuccess=deliveryOrderBO.createNewBranchDeliveryorder(deliveryorder, branchsale);
		
		}
		if (updateSuccess) {			
			this.resetDeliveryOrder();
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"deliveryPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("deliveryorder.label.created.success"),null));				
		}		
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"deliveryPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));				
			log.info("Error in saveDeliveryOrder of DeliveryOrderBeanInfo "+ e.toString());
		}
	}
	
	
	
	public DeliveryorderbreakdownModel buildDeliveryData(PurchaserequestbreakdownModel productData)
	{
		DeliveryorderbreakdownModel product = new DeliveryorderbreakdownModel();		
		balanceQuantity	=Integer.parseInt(deliveryOrderBean.getReceivalbeQuantity().get(productData.getId()));		
		product.setProductId(productData.getProductId());	
		product.setProductName(productData.getProductName());
		product.setQuantity(new BigDecimal(balanceQuantity));			
		product.setSalesbalanceQuantity(new BigDecimal(balanceQuantity));
		product.setBalanceQuantity(new BigDecimal(balanceQuantity));
		product.setSoldQuantity(new BigDecimal(0.00));
		product.setUnitPrice(productData.getUnitPrice());
		product.setSubTotal(productData.getUnitPrice().multiply(new BigDecimal(balanceQuantity)));		
		product.setExpDate(deliveryOrderBean.getReceivalbeExpDate().get(productData.getId()));
		
		return product;
	}
	
	
	public DeliveryorderbreakdownModel buildDeliveryData(DeliveryorderbreakdownModel productData)
	{
		DeliveryorderbreakdownModel product = new DeliveryorderbreakdownModel();		
		balanceQuantity	=Integer.parseInt(deliveryOrderBean.getReceivalbeQuantity().get(productData.getId()));		
		product.setProductId(productData.getProductId());	
		product.setProductName(productData.getProductName());
		product.setQuantity(new BigDecimal(balanceQuantity));			
		product.setSalesbalanceQuantity(new BigDecimal(balanceQuantity));
		product.setBalanceQuantity(new BigDecimal(balanceQuantity));
		product.setSoldQuantity(new BigDecimal(0.00));
		product.setUnitPrice(productData.getUnitPrice());
		product.setSubTotal(productData.getUnitPrice().multiply(new BigDecimal(balanceQuantity)));		
		product.setExpDate(deliveryOrderBean.getReceivalbeExpDate().get(productData.getId()));
		
		return product;
	}
	
	
	public void resetDeliveryOrder()
	{
		deliveryOrderBean.resetDeliveryOrder();
	}
	
	
	
	
	public boolean validateBranchDeliveryOrder(DeliveryorderModel deliveryorder)
	
	{
		DeliveryOrderBean deliveryOrderBean = (DeliveryOrderBean) BeanContext.getReference("deliveryOrderBean");
 		boolean valid=true;		
		String messageValue="";
		IDeliveryorderBO deliveryOrderBO=deliveryOrderBean.getDeliveryOrderBO();
		try
		{
		if (factoryBean.checkIsNullAssignMessage(deliveryorder.getDeliveryOrderNo(),
				"purchaseorder.label.grnno", "grnno")) {
			valid = false;
		}		
		
		else {
			List<DeliveryorderModel> deliveryList=deliveryOrderBO.getDeliveryorderList(null,deliveryorder.getDeliveryOrderNo(), null,deliveryOrderBean.getSupplierId(), null, null, null, 0, 3,null);
			
			if (deliveryOrderBean.getAction().equalsIgnoreCase("submit")) {
				if (deliveryList!=null && (!deliveryList.isEmpty())) {
					messageValue = factoryBean.getErrorFactory().getError("supplier.errors.grn.exists");
					factoryBean.reportErrorToMessageHandler(messageValue,messageValue, "grnno");
					valid = false;
				}
			}else{  
				
				if(! (deliveryorder.getDeliveryOrderNo()).equalsIgnoreCase(deliveryorder.getDeliveryOrderOldNo()) ){
											
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
			log.info("Error in validateBranchDeliveryOrder of DeliveryOrderBeanInfo "+ e.toString());	
		}
		
		return valid;
	}


	
	public boolean validateDeliveryOrder(DeliveryorderModel deliveryorder)
	
	{
		DeliveryOrderBean deliveryOrderBean = (DeliveryOrderBean) BeanContext.getReference("deliveryOrderBean");
 		boolean valid=true;		
		String messageValue="";
		IDeliveryorderBO deliveryOrderBO=deliveryOrderBean.getDeliveryOrderBO();
		try
		{
		if(deliveryorder.getDeliveryOrderNo()==null || deliveryorder.getDeliveryOrderNo()=="")
		{
		deliveryorder.setDeliveryOrderNo(deliveryorder.getInvoiceNo());
		}	
			
		if (factoryBean.checkIsNullAssignMessage(deliveryorder.getDeliveryOrderNo(),
				"purchaseorder.label.grnno", "grnno")) {
			valid = false;
		}		
		
		if (factoryBean.checkIsNullAssignMessage(deliveryOrderBean.getDeliveryorder().getSupplier().getSupplierCode(),
				"purchaseorder.label.grnno", "supplierCode")) {
			valid = false;
		}	
		
		if (deliveryorder.getItemCount()!=batchCount) {
			messageValue = factoryBean.getErrorFactory().getError("supplier.errors.batch.itemcount.notmatch");
			factoryBean.reportErrorToMessageHandler(messageValue,messageValue, "deliveryPanel");
			valid = false;
			}
		
		if (deliveryorder.getItemCount()==0) {
			messageValue = factoryBean.getErrorFactory().getError("supplier.errors.grn.itemcount");
			factoryBean.reportErrorToMessageHandler(messageValue,messageValue, "deliveryPanel");
			valid = false;
			}
		
		else {
			List<DeliveryorderModel> deliveryList=deliveryOrderBO.getDeliveryorderList(null,deliveryorder.getDeliveryOrderNo(), null,deliveryOrderBean.getSupplierId(), null, null, null, 0, 3,null);
			
			if (deliveryOrderBean.getAction().equalsIgnoreCase("submit")) {
				if (deliveryList!=null && (!deliveryList.isEmpty())) {
					messageValue = factoryBean.getErrorFactory().getError("supplier.errors.grn.exists");
					factoryBean.reportErrorToMessageHandler(messageValue,messageValue, "grnno");
					valid = false;
				}
			}else{  
				
				if(! (deliveryorder.getDeliveryOrderNo()).equalsIgnoreCase(deliveryorder.getDeliveryOrderOldNo()) ){
											
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
			log.info("Error in updateDeliveryOrder of DeliveryOrderBeanInfo "+ e.toString());	
		}
		
		return valid;
	}
	
	public boolean validateDeliveryHistorySearch() {
		boolean valid = true;		
	
		DeliveryOrderBean deliveryOrderBean = (DeliveryOrderBean) BeanContext.getReference("deliveryOrderBean");
		
		if(deliveryOrderBean.getDeliveryOrderNo()!=null)
		{
			if(deliveryOrderBean.getDeliveryOrderNo().equalsIgnoreCase(""))
			{
				deliveryOrderBean.setDeliveryOrderNo(null);
			}
		}		
		
		if(deliveryOrderBean.getPurchaseOrderNo()!=null)
		{
			if(deliveryOrderBean.getPurchaseOrderNo().equalsIgnoreCase(""))
			{
				deliveryOrderBean.setPurchaseOrderNo(null);
			}
		}		
		
		
		if(deliveryOrderBean.getSupplierId()!=null)
		{
			if(deliveryOrderBean.getSupplierId()==0)
			{
				deliveryOrderBean.setSupplierId(null);
			}
		}		
		if(deliveryOrderBean.getStatus()!=null)
		{
			if(deliveryOrderBean.getStatus().equalsIgnoreCase("") || deliveryOrderBean.getStatus().equalsIgnoreCase("0") )
			{
				deliveryOrderBean.setStatus(null);
			}
		}		
		
		if(deliveryOrderBean.getSupplierId()==null && deliveryOrderBean.getDateFrom()==null && deliveryOrderBean.getDateTo()==null && deliveryOrderBean.getStatus()==null  && deliveryOrderBean.getDeliveryOrderNo()==null && deliveryOrderBean.getPurchaseOrderNo()==null )
		{	
			valid = false;
		}		
		
		else
		{
			valid = true;
		}			
		return valid;

	}

	
	
}
