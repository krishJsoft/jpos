package com.project.bean.sales.sale;

import java.awt.Font;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.servlet.http.HttpServletRequest;
import javax.swing.JTextPane;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.bean.admin.SystemSettingBean;
import com.project.bo.interfaces.IItemRemarksBO;
import com.project.bo.interfaces.IProductBO;
import com.project.bo.interfaces.IProductCategoryBO;
import com.project.bo.interfaces.ISalesorderBO;
import com.project.bo.interfaces.ISystemconfigurationBO;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.factory.ErrorFactory;
import com.project.common.util.ObjectMapController;
import com.project.common.util.PrintUtility;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.common.validation.ValidatorUtil;
import com.project.home.ProjectHome;
import com.project.login.LoginBean;
import com.project.model.datamodel.ItemRemarksListModel;
import com.project.model.datamodel.KitchenprinterlistModal;
import com.project.model.datamodel.ProductModel;
import com.project.model.datamodel.ProductcategoryModel;
import com.project.model.datamodel.purchase.PurchaseorderModel;
import com.project.model.datamodel.purchase.PurchaseorderbreakdownsModel;
import com.project.model.datamodel.purchase.PurchaserequestbreakdownModel;
import com.project.model.datamodel.stock.DeliveryorderModel;
import com.project.model.datamodel.stock.DeliveryorderbreakdownModel;
import com.project.model.his.Product;
import com.project.model.sale.sales.DoctorsPrescriptionsModel;
import com.project.model.sale.sales.HoteltableModel;
import com.project.model.sale.sales.KitchenorderModel;
import com.project.model.sale.sales.KitchenorderbreakdownModel;
import com.project.model.sale.sales.KitchenorderremarksbreakdownModel;
import com.project.model.sale.sales.PoscashtransactionModel;
import com.project.model.sale.sales.PoscounterModel;
import com.project.model.sale.sales.QuotationbreakdownModel;
import com.project.model.sale.sales.SalesorderModel;
import com.project.model.sale.sales.SalesorderbreakdownModel;
import com.project.model.sale.sales.QuotationModel;
import com.project.util.DateUtil;

public class SalesorderBeanInfo {
	
	
	SalesorderBean salesorderBean = (SalesorderBean) BeanContext.getReference("salesorderBean");
	PosBean posBean = (PosBean) BeanContext.getReference("posBean");
	
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	SystemSettingBean systemSettingBean = (SystemSettingBean) BeanContext.getReference("systemSettingBean");
	public static Logger log = LoggerFactory.getLogger(SalesorderBeanInfo.class);
	FacesContext context = FacesContext.getCurrentInstance();
	Configuration config = Configuration.getConfiguration();
	String projectHomefile = config.getValue(IConfiguration.PROJECT_HOME_FILENAME);
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	IItemRemarksBO itemRemarksBO=objectMapController.getItemRemarksBO();
	IProductCategoryBO productCategoryBO=objectMapController.getProductCategoryBO();
	private ISystemconfigurationBO systemConfigBO=objectMapController.getSystemconfigurationBO();

	static ErrorFactory errorFactory = ErrorFactory.getInstance();
	String projectregno = config.getValue(IConfiguration.PRODUCT_REG_NUMBER);
	String projectgsnno = config.getValue(IConfiguration.PRODUCT_GST_NUMBER);

	Integer balanceQuantity = 0;
	Integer receivedQuantity = 0;
	
	public void listSalesOrder() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/sales/salesOrder.xhtml");
		projectHome.setTitlePage("Sales --> SalesOrder -->Search SalesOrder");
		navHandler.handleNavigation(context, null, projectHomefile+ "?faces-redirect=true");
	}

	
	
	public void newSalesOrder() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/sales/addEditBranchSalesOrder.xhtml");
		projectHome.setTitlePage("Sales --> SalesOrder --> Add/Edit SalesOrder");
		navHandler.handleNavigation(context, null, projectHomefile+ "?faces-redirect=true");
	}
	
	
	public void editSalesOrder() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/sales/EditSalesOrder.xhtml");
		projectHome.setTitlePage("Sales --> SalesOrder --> Add/Edit SalesOrder");
		navHandler.handleNavigation(context, null, projectHomefile+ "?faces-redirect=true");
	}
	
	
	public void saveSalesOrder(SalesorderModel salesorderModel)
	{			
		List<DeliveryorderbreakdownModel> deliverydataList = new ArrayList<DeliveryorderbreakdownModel>();
		List<SalesorderbreakdownModel> salesdataList = new ArrayList<SalesorderbreakdownModel>();
		List<QuotationbreakdownModel>  quotationdataList = new ArrayList<QuotationbreakdownModel>();
		DeliveryorderModel deliveryorder = new DeliveryorderModel();
		QuotationModel quotationModel = new QuotationModel();
		boolean updateSuccess=false;
		BigDecimal totalDeliveryAmt = new BigDecimal(0);		
		BigDecimal productAmount = new BigDecimal(0);
		
		
		ISalesorderBO salesOrderBO=salesorderBean.getSalesOrderBO();		
		deliveryorder=salesorderBean.getDeliveryorder();		
		quotationModel=salesorderBean.getQuotation();
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{	
			salesorderModel.setCreatedDate(DateUtil.getTodayDate());
			salesorderModel.setCreatedBy(loginBean.getUserName());
			salesorderModel.setStatus(config.getValue(IConfiguration.SALESORDER_STATUS_NEWORDER_VALUE));		
			salesorderModel.setSalesType(salesorderBean.getSalesType());
			salesorderModel.setSalesRep(loginBean.getUserName());		
			salesorderModel.setTotalAmount(salesorderBean.getTotalAmount());
			
			salesorderModel.setBranchtype(loginBean.getBranch().getBranchtype());
			salesorderModel.setBranchRecordId(loginBean.getBranch().getBranchId());
			
			if(salesorderBean.getSalesType().equalsIgnoreCase("1"))
			{
				int soldQuantityCount=deliveryorder.getSoldQuantityCount();
				int balanceQuantityCount=deliveryorder.getBalanceQuantityCount();
				int totalItemCount=deliveryorder.getTotalItemCount();
				
				salesorderModel.setCustomerBranchId(salesorderBean.getBranchId());
				salesorderModel.setDeliveryOrderNo(salesorderBean.deliveryorder.getDeliveryOrderNo());			
		
				for(DeliveryorderbreakdownModel productData:salesorderBean.getDeliveryorder().getDeliveryorderbreakdowns())
				{
					SalesorderbreakdownModel data=new SalesorderbreakdownModel();			
					if (ValidatorUtil.isNotNull(salesorderBean.getSalableQuantity().get(productData.getId()))) 
					{
						balanceQuantity	=Integer.parseInt(salesorderBean.getSalableQuantity().get(productData.getId()));
						if(balanceQuantity!=0)
						{
							productData.setBalanceQuantity(productData.getBalanceQuantity().subtract(new BigDecimal(balanceQuantity)));			
							productData.setSoldQuantity(productData.getSoldQuantity().add(new BigDecimal(balanceQuantity)));				
							deliverydataList.add(productData);					
				
							data.setProductId(productData.getProductId());
							data.setProductName(productData.getProductName());				
							data.setDiscount(productData.getDiscount());
							data.setDiscountAmount(productData.getDiscountAmount());	
							data.setPurchasePrice(productData.getPurchasePrice());
							data.setQuantity(new BigDecimal(balanceQuantity));			
							data.setSubTotal(new BigDecimal(""+salesorderBean.getSalablePrice().get(productData.getId())));
							data.setUnitPrice(productData.getUnitPrice());	
							data.setDeliveryOrderBreakdownId(productData.getDeliveryOrderBreakdownId());
							data.setTaxAmount(new BigDecimal(""+salesorderBean.getSalableTaxPrice().get(productData.getId())));
							
							
							soldQuantityCount=soldQuantityCount+balanceQuantity;
							balanceQuantityCount=balanceQuantityCount-balanceQuantity;
													
							salesdataList.add(data);	
						}
					}			
				}		
				salesorderModel.setSalesorderbreakdowns(salesdataList);		
				
				/*deliveryorder.setSoldQuantityCount(soldQuantityCount);
				deliveryorder.setBalanceQuantityCount(balanceQuantityCount);
				if(deliveryorder.getTotalItemCount()==deliveryorder.getSoldQuantityCount())
				{
					deliveryorder.setStatus(config.getValue(IConfiguration.DELIVERYORDER_STATUS_ORDERED_VALUE));
				}
				*/
				deliveryorder.setDeliveryorderbreakdowns(deliverydataList);	
		
			}			
			
			
			if(salesorderBean.getSalesType().equalsIgnoreCase("2"))
			{
				
				BigDecimal soldQuantityCount=quotationModel.getSoldQuantityCount();
				BigDecimal balanceQuantityCount=quotationModel.getBalanceQuantityCount();
				BigDecimal totalItemCount=quotationModel.getTotalItemCount();
				
				
				salesorderModel.setCustomerBranchId(salesorderBean.getCustomer().getCustomerId());
				salesorderModel.setQuoteNo(salesorderBean.quotation.getQuotationNo());
				
				for(QuotationbreakdownModel productData:salesorderBean.getQuotation().getQuotationbreakdowns())
				{
					SalesorderbreakdownModel data=new SalesorderbreakdownModel();			
					if (ValidatorUtil.isNotNull(salesorderBean.getSalableQuantity().get(productData.getId()))) 
					{
						 BigDecimal	balanceQuantity	=new BigDecimal(salesorderBean.getSalableQuantity().get(productData.getId()));
						if(balanceQuantity.doubleValue()!=0)
						{
							productData.setBalanceQuantity(new BigDecimal(productData.getBalanceQuantity().doubleValue()- balanceQuantity.doubleValue()));			
							productData.setSoldQuantity(new BigDecimal(productData.getSoldQuantity().doubleValue()+ balanceQuantity.doubleValue()));								
							quotationdataList.add(productData);					
				
							data.setProductId(productData.getProductId());
							data.setProductName(productData.getProductName());				
							data.setDiscount(productData.getDiscount());
							data.setDiscountAmount(productData.getDiscountAmount());				
							data.setQuantity(balanceQuantity);			
							data.setSubTotal(new BigDecimal(""+salesorderBean.getSalablePrice().get(productData.getId())));
							data.setUnitPrice(productData.getUnitPrice());	
							data.setPurchasePrice(productData.getPurchasePrice());
							data.setDeliveryOrderBreakdownId(productData.getQuotationBreakdownId());							
							data.setTaxAmount(new BigDecimal(""+salesorderBean.getSalableTaxPrice().get(productData.getId())));
							
							
							soldQuantityCount=soldQuantityCount.add(balanceQuantity);
							balanceQuantityCount=balanceQuantityCount.subtract(balanceQuantity);
							
							salesdataList.add(data);	
						}
					}			
				}		
			/*	quotationModel.setSoldQuantityCount(soldQuantityCount);
				quotationModel.setBalanceQuantityCount(balanceQuantityCount);
				if(quotationModel.getTotalItemCount()==quotationModel.getSoldQuantityCount())
				{
					quotationModel.setStatus(config.getValue(IConfiguration.QUOTATION_STATUS_ORDERED_VALUE));
				}*/
				salesorderModel.setSalesorderbreakdowns(salesdataList);		
				quotationModel.setQuotationbreakdowns(quotationdataList);
				}	
		
			updateSuccess=salesOrderBO.createNewSalesorder(deliveryorder,quotationModel, salesorderModel);
		
		if (updateSuccess) {					
			salesorderBean.changeSalesType();
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("salesorder.label.created.success"),null));				
		}
		
		
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));				
			log.info("Error in saveSalesOrder of SalesorderBeanInfo "+ e.toString());
		}
	}
	
		
	
	
	public boolean savePOSSalesOrder()
	{					
		List<SalesorderbreakdownModel> salesdataList = new ArrayList<SalesorderbreakdownModel>();	
		List<SalesorderModel> salesorderdataList = new ArrayList<SalesorderModel>();	
		boolean updateSuccess=false;
		
		SalesorderModel salesorderModel=posBean.getSalesorder();	
		salesorderModel.setRegNumber(projectregno);
		salesorderModel.setGstNumber(projectgsnno);

		ISalesorderBO salesOrderBO=salesorderBean.getSalesOrderBO();		
		PoscounterModel poscounter=posBean.getPoscounter();
		DoctorsPrescriptionsModel dpModel = new DoctorsPrescriptionsModel();
		PoscashtransactionModel poscashtransaction = new PoscashtransactionModel();
		poscashtransaction=salesorderModel.getPoscashtransaction();
		poscashtransaction.setPoscounter(poscounter);
		poscashtransaction.setCounterId(poscounter.getCounterId());
		poscashtransaction.setLastupdatedBy(loginBean.getLogdetail().getEmailAddress());
		poscashtransaction.setLastupdatedDate(new Date());
		poscashtransaction.setType("1");					
		//poscashtransaction.setCreditamount(salesorderModel.getTotalAmount());
		poscashtransaction.setStatus("1");
		poscashtransaction.setTransactionType("1");
		poscashtransaction.setTransactionStatus("2");						
		poscashtransaction.setPaymentCount(1);
		//poscashtransaction.setPaymentType("Cash");
		poscashtransaction.setBranchRecordId(loginBean.getBranch().getBranchId());
		poscashtransaction.setBranchtype(loginBean.getBranch().getBranchtype());
		
		
		
		
		salesorderModel.setPoscashtransaction(poscashtransaction);
		
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{	
			
			salesorderModel.setCreatedDate(new Date());
			salesorderModel.setCreatedBy(loginBean.getLogdetail().getEmailAddress());
			salesorderModel.setStatus(config.getValue(IConfiguration.SALESORDER_STATUS_ORDERED_VALUE));		
			salesorderModel.setSalesType(posBean.getSalesType());
			salesorderModel.setSalesRep(loginBean.getUserName());		
			salesorderModel.setTotalAmount(posBean.getTotalAmount());
			salesorderModel.setTotalTax(posBean.getTotalTaxAmount());
			salesorderModel.setReceivedAmount(posBean.getReceivedAmount());
			salesorderModel.setChangeAmount(posBean.getBalanceAmount());
			salesorderModel.setTotalBeforeDiscount(posBean.getTotalBeforeDiscount());
			salesorderModel.setDiscountRate(posBean.getDiscountRate());
			salesorderModel.setDiscountRemarks(posBean.getDiscountRemarks());
			
			if(posBean.getCustomer().getCustomerId()==0)
			{
			salesorderModel.setCustomerBranchId(1);
			}
			else
			{
			salesorderModel.setCustomerBranchId(posBean.getCustomer().getCustomerId());	
			}
			
			
			salesorderModel.setPrescriptionNo(null);
			
			salesorderModel.setBranchtype(loginBean.getBranch().getBranchtype());
			salesorderModel.setBranchRecordId(loginBean.getBranch().getBranchId());
			salesorderModel.setBranchId(loginBean.getBranch().getBranchId());
			
			
			salesorderModel.setPospayments(posBean.getPaymentCollectionModel());
			
			salesorderModel.setSalesorderbreakdowns(posBean.getSalesorderbreakdowns());				
			
			
			if(systemSettingBean.getMyConfig().getSystemType().getKitchenOrderType().equalsIgnoreCase("AFTER PAYMENT")
					&& systemSettingBean.getSystemConfig().getKitchenOrder()==true 
					) {
				this.multipleKitchenPrint(salesorderModel);
			}
			
			salesorderModel=salesOrderBO.createNewSalesorder(salesorderModel,posBean.getSalesId());
			
			posBean.setSalesOrderId(salesorderModel.getSalesOrderId());
			
			HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
			request.getSession().getServletContext().setAttribute("salesOrderId", salesorderModel.getSalesOrderId());  
			
			posBean.setCardOption("normal");
			posBean.kitchedOrder.clear();
			posBean.setKitchenOrderBeforeSales(null);
			posBean.resetPos();
			updateSuccess=true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"posPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));				
			log.info("Error in saveSalesOrder of SalesorderBeanInfo "+ e.toString());
		}
		return updateSuccess;
	}
	
	
	
	public boolean saveSplitOrder() {
		boolean updateSuccess=false;
		
		SalesorderModel salesorderModel=posBean.getSalesorder();		
		ISalesorderBO salesOrderBO=salesorderBean.getSalesOrderBO();		
		
			
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{	
			salesorderModel.setCreatedDate(DateUtil.getTodayDate());
			salesorderModel.setCreatedBy(loginBean.getLogdetail().getEmailAddress());
			salesorderModel.setStatus(config.getValue(IConfiguration.SALESORDER_STATUS_NEWORDER_VALUE));		
			salesorderModel.setSalesType(posBean.getSalesType());
			salesorderModel.setSalesRep(loginBean.getUserName());		
			salesorderModel.setTotalAmount(posBean.getTotalAmount());
			salesorderModel.setTotalTax(posBean.getTotalTaxAmount());
			salesorderModel.setReceivedAmount(posBean.getReceivedAmount());
			salesorderModel.setChangeAmount(posBean.getBalanceAmount());
			
			if(posBean.getCustomer()==null)
			{
				salesorderModel.setCustomerBranchId(1);	
			}
			else if(posBean.getCustomer().getCustomerId()==0)
			{
			salesorderModel.setCustomerBranchId(1);
			}
			else
			{
			salesorderModel.setCustomerBranchId(posBean.getCustomer().getCustomerId());	
			}
			
			salesorderModel.setPrescriptionNo(null);
			
			salesorderModel.setBranchtype(loginBean.getBranch().getBranchtype());
			salesorderModel.setBranchRecordId(loginBean.getBranch().getBranchId());
			salesorderModel.setBranchId(loginBean.getBranch().getBranchId());
			
			
			salesorderModel.setPospayments(posBean.getPaymentCollectionModel());
			salesorderModel.setSalesorderbreakdowns(posBean.getSalesorderbreakdowns());				
			salesorderModel.setSplitFrom(salesorderModel.getSalesOrderNo());
			
			salesorderModel=salesOrderBO.createNewKitchensorder(salesorderModel);
			
			posBean.salesId.add(salesorderModel.getSalesOrderId());
			
			updateSuccess=true;
			
		}
		
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"posPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));				
			log.info("Error in saveSplitOrder of SalesorderBeanInfo "+ e.toString());
		}
		return updateSuccess;
	}
	

	public boolean saveKitchenOrder()
	{	
		boolean updateSuccess=false;
		SalesorderModel salesorderModel=posBean.getSalesorder();		
		ISalesorderBO salesOrderBO=salesorderBean.getSalesOrderBO();		
		
			
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{	
			salesorderModel.setCreatedDate(DateUtil.getTodayDate());
			salesorderModel.setCreatedBy(loginBean.getLogdetail().getEmailAddress());
			salesorderModel.setStatus(config.getValue(IConfiguration.SALESORDER_STATUS_NEWORDER_VALUE));		
			salesorderModel.setSalesType(posBean.getSalesType());
			salesorderModel.setSalesRep(loginBean.getUserName());		
			salesorderModel.setTotalAmount(posBean.getTotalAmount());
			salesorderModel.setTotalTax(posBean.getTotalTaxAmount());
			salesorderModel.setReceivedAmount(posBean.getReceivedAmount());
			salesorderModel.setChangeAmount(posBean.getBalanceAmount());
			
			if(posBean.getCustomer()==null)
			{
				salesorderModel.setCustomerBranchId(1);	
			}
			else if(posBean.getCustomer().getCustomerId()==0)
			{
			salesorderModel.setCustomerBranchId(1);
			}
			else
			{
			salesorderModel.setCustomerBranchId(posBean.getCustomer().getCustomerId());	
			}
			
			salesorderModel.setPrescriptionNo(null);
			
			salesorderModel.setBranchtype(loginBean.getBranch().getBranchtype());
			salesorderModel.setBranchRecordId(loginBean.getBranch().getBranchId());
			salesorderModel.setBranchId(loginBean.getBranch().getBranchId());
			
			
			salesorderModel.setPospayments(posBean.getPaymentCollectionModel());
			salesorderModel.setSalesorderbreakdowns(posBean.getSalesorderbreakdowns());				
			salesorderModel.setSplitInc(".1");
			
			
			SalesorderModel kitchenModel=salesorderModel;
			salesorderModel=salesOrderBO.createNewKitchensorder(salesorderModel);
			if(systemSettingBean.getMyConfig().getKitchenOrder()==true && salesorderModel!=null) {
				this.multipleKitchenPrint(kitchenModel);
			}
			posBean.setSalesOrderId(salesorderModel.getSalesOrderId());
			//salesorderdataList.add(salesOrderBO.getSalesorderPosDetails(70, "3"));
			//posBean.setSalesOrder(salesorderdataList);
			
			

			HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
			request.getSession().getServletContext().setAttribute("salesOrderId", salesorderModel.getSalesOrderId());  
			//posBean.printBill();
			
			
			
			
			posBean.resetPos();
			updateSuccess=true;
		    
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"posPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));				
			log.info("Error in saveKitchenOrder of SalesorderBeanInfo "+ e.toString());
		}
		return updateSuccess;
	}
	
	public boolean updateSplitOrder(List<SalesorderbreakdownModel> list) {

		boolean updateSuccess=false;
		
		
		SalesorderModel salesorderModel=posBean.getSalesorderSplit();		
		ISalesorderBO salesOrderBO=salesorderBean.getSalesOrderBO();		
			
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{	
			salesorderModel.setCreatedDate(DateUtil.getTodayDate());
			salesorderModel.setCreatedBy(loginBean.getLogdetail().getEmailAddress());
			salesorderModel.setStatus(config.getValue(IConfiguration.SALESORDER_STATUS_NEWORDER_VALUE));		
			salesorderModel.setSalesType(posBean.getSalesType());
			salesorderModel.setSalesRep(loginBean.getUserName());		
			//salesorderModel.setTotalAmount(posBean.getTotalAmount()); prevent invalid total
			salesorderModel.setTotalTax(posBean.getTotalTaxAmount());
			salesorderModel.setReceivedAmount(posBean.getReceivedAmount());
			salesorderModel.setChangeAmount(posBean.getBalanceAmount());
			
			if(posBean.getCustomer()==null)
			{
				salesorderModel.setCustomerBranchId(1);	
			}
			else if(posBean.getCustomer().getCustomerId()==0)
			{
			salesorderModel.setCustomerBranchId(1);
			}
			else
			{
			salesorderModel.setCustomerBranchId(posBean.getCustomer().getCustomerId());	
			}
			
			salesorderModel.setPrescriptionNo(null);
			
			salesorderModel.setBranchtype(loginBean.getBranch().getBranchtype());
			salesorderModel.setBranchRecordId(loginBean.getBranch().getBranchId());
			salesorderModel.setBranchId(loginBean.getBranch().getBranchId());
			salesorderModel.setPospayments(posBean.getPaymentCollectionModel());
			salesorderModel.setSalesorderbreakdowns(list);				
			int i=0;
			
			
			
			// update quantity of the order
			List<SalesorderbreakdownModel> dataPrev=posBean.getSalesorderbreakdowns();
				
			
			
			List<SalesorderbreakdownModel> prevItem=salesorderModel.getSalesorderbreakdowns();
			List<Integer> itemIds = posBean.getSplitbreakdownids();
			List<Integer> checkedIds=new ArrayList<Integer>();
			BigDecimal totalAmt=new BigDecimal(0);
			for(SalesorderbreakdownModel data:salesorderModel.getSalesorderbreakdowns()) {
				
				/*
				
				if(i==0) {
					checkedIds.add(data.getSalesOrderBreakdownId());
				}
				if(!checkedIds.contains(data.getSalesOrderBreakdownId())) {
					checkedIds.add(data.getSalesOrderBreakdownId());
					if(itemIds.contains(data.getSalesOrderBreakdownId())) {
						data.setQuantity(data.getQuantity().add(BigDecimal.ONE));
						data.setSubTotal(data.getUnitPrice().multiply(data.getQuantity()));
					}
				}
				i++;*/
				/*for(SalesorderbreakdownModel data2:prevItem) {
					if(data.getSalesOrderBreakdownId()==data2.getSalesOrderBreakdownId()) {
						
						data.setQuantity(data.getQuantity().add(BigDecimal.ONE));
						data.setSubTotal(data.getUnitPrice().multiply(data.getQuantity()));
						break;
					}
				}
				*/
				if(i!=0)
				{
					if(data.getSalesOrderBreakdownId()==salesorderModel.getSalesorderbreakdowns().get((i-1)).getSalesOrderBreakdownId()) {
						data.setQuantity(data.getQuantity().add(BigDecimal.ONE));
						data.setSubTotal(data.getUnitPrice().multiply(data.getQuantity()));
					}
				
				}
				i++;
				totalAmt=totalAmt.add(data.getUnitPrice());
			
			}
			salesorderModel.setTotalAmount(totalAmt); // prevent invalid total refer top
			salesorderModel=salesOrderBO.updateSplitOrder(salesorderModel);
		
			updateSuccess=true;
			
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"posPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));				
			log.info("Error in updateSplitOrder of SalesorderBeanInfo "+ e.toString());
		}
		return updateSuccess;
	}
	

	public boolean updateKitchenorder()
	{	
		boolean updateSuccess=false;
		
		SalesorderModel salesorderModel=posBean.getSalesorder();		
		ISalesorderBO salesOrderBO=salesorderBean.getSalesOrderBO();		
		
			
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{	
			salesorderModel.setCreatedDate(DateUtil.getTodayDate());
			salesorderModel.setCreatedBy(loginBean.getLogdetail().getEmailAddress());
			salesorderModel.setStatus(config.getValue(IConfiguration.SALESORDER_STATUS_NEWORDER_VALUE));		
			salesorderModel.setSalesType(posBean.getSalesType());
			salesorderModel.setSalesRep(loginBean.getUserName());		
			salesorderModel.setTotalAmount(posBean.getTotalAmount());
			salesorderModel.setTotalTax(posBean.getTotalTaxAmount());
			salesorderModel.setReceivedAmount(posBean.getReceivedAmount());
			salesorderModel.setChangeAmount(posBean.getBalanceAmount());
			
			if(posBean.getCustomer()==null)
			{
				salesorderModel.setCustomerBranchId(1);	
			}
			else if(posBean.getCustomer().getCustomerId()==0)
			{
			salesorderModel.setCustomerBranchId(1);
			}
			else
			{
			salesorderModel.setCustomerBranchId(posBean.getCustomer().getCustomerId());	
			}
			
			salesorderModel.setPrescriptionNo(null);
			
			salesorderModel.setBranchtype(loginBean.getBranch().getBranchtype());
			salesorderModel.setBranchRecordId(loginBean.getBranch().getBranchId());
			salesorderModel.setBranchId(loginBean.getBranch().getBranchId());
			salesorderModel.setPospayments(posBean.getPaymentCollectionModel());
			salesorderModel.setSalesorderbreakdowns(posBean.getSalesorderbreakdowns());				
			
			SalesorderModel kitchenModel=salesorderModel;
			
			salesorderModel=salesOrderBO.updateKitchenorder(salesorderModel);
			if(systemSettingBean.getMyConfig().getKitchenOrder()==true && salesorderModel!=null) {
				this.multipleKitchenPrint(kitchenModel);
			}
			posBean.setSalesOrderId(salesorderModel.getSalesOrderId());
			//salesorderdataList.add(salesOrderBO.getSalesorderPosDetails(70, "3"));
			//posBean.setSalesOrder(salesorderdataList);
			HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
			request.getSession().getServletContext().setAttribute("salesOrderId", salesorderModel.getSalesOrderId());  
			//posBean.printBill();
			
			
			posBean.resetPos();
			updateSuccess=true;
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"posPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));				
			log.info("Error in updateKitchenorder of SalesorderBeanInfo "+ e.toString());
		}
		return updateSuccess;
	}
	
	
	private void multipleKitchenPrint(SalesorderModel salesorderModel) {
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy h:mm:ss a");
			String table=salesorderModel.getTableName();
			int printLength=50;
			
			String underLine="";
			String dashLine= "";
			HashMap<Integer,String> printerTextMap=new HashMap<Integer,String>();  
			List<KitchenprinterlistModal> printerList=systemConfigBO.getKitchenPrinterList(null, loginBean.getBranch().getBranchId());
			for(KitchenprinterlistModal printer:printerList) {
				printerTextMap.put(printer.getId(), "");
			}
			
			for(int i=0;i<29;i++) {
				underLine+="_";
				
			}
			for(int i=0;i<printLength;i++) {
				dashLine+="-";
	
			}
	
			int defaultFontSize=8;
			int itemFontSize=10;
			
			String htmlPrint="<html><head></head><body>";
			String htmlHeader="<table width='100%' >"
					+ "<tr>"
					+ "<td align='left' style='font-size:"+defaultFontSize+"px' >STAFF : "+salesorderModel.getSalesRep()
					+ "</td>"
					+ "<td align='right' style='font-size:"+defaultFontSize+"px' >"+sdf.format(new Date())
					+ "</td>"
					+ "</tr>";
			
			//if(table || card)
			if(systemSettingBean.getMyConfig().getSystemType().getHasTableNo()) 
			{
				htmlHeader+="<tr>";
				htmlHeader+="<td align='left' style='font-size:"+defaultFontSize+"px'>"
						+ "TABLE : " +salesorderModel.getTableName()
						+ "</td>";
				if(systemSettingBean.getMyConfig().getSystemType().getHasCardNo()==true ) {
					htmlHeader+="<td align='right' style='font-size:"+defaultFontSize+"px'>"
							+ "CARD NO : " +salesorderModel.getCardNo()
							+ "</td>";
				}else {
					htmlHeader+="<td ></td>";
				}
				htmlHeader+="</tr>";
				
			}else {
				htmlHeader+="<tr>";
				htmlHeader+="<td align='right' style='font-size:"+defaultFontSize+"px'>"
							+ "ORDER NO : " +salesorderModel.getSalesOrderNo()
							+ "</td>";
				htmlHeader+="</tr>";
			}
			
			htmlHeader+="</table>";
			String htmlHeaderItem="<table width='100%'  >"
					+ "<tr>"
					+ "<td align='left' style='font-size:"+defaultFontSize+"px;border-bottom:1px solid black;border-top:1px solid black' width='80%'>"
					+ "ITEM NAME"
					+ "</td>"
					+ "<td align='right' style='font-size:"+defaultFontSize+"px;border-bottom:1px solid black;border-top:1px solid black'>"
					+ "QTY"
					+ "</td>"
					+ "</tr>";
			
				ProductModel pro = null;
				for(SalesorderbreakdownModel data:salesorderModel.getSalesorderbreakdowns()) {
	
				if(data.getStatus()==null || data.getStatus().equalsIgnoreCase("cancel"))
				{
					
					if(data.getSetOption()==1) {
						for(SalesorderbreakdownModel setItemdata:data.getSalesProductSetList()) {
							ProductcategoryModel categoryData=productCategoryBO.getProductCategoryDetails2(setItemdata.getProduct().getCategoryId());
							
							if(categoryData.getPrinter()!=null && categoryData.getPrinter().getId()>0) {
								String htmlItemTable="";
								htmlItemTable=this.convertToRecieptTemplate(setItemdata,data.getProductName());
							
								if(systemSettingBean.getMyConfig().getPrintMode().equalsIgnoreCase("multiple")) {
									int i=data.getQuantity().intValue();
									while(i>0) {
										this.sendToPrinter(categoryData.getPrinter().getId(), htmlHeader, htmlHeaderItem, htmlItemTable);
										i--;
									}
									
								}else {//if single item print mode
									printerTextMap.put(categoryData.getPrinter().getId(), htmlItemTable);
								}

							}
						}
					}else {
						ProductcategoryModel categoryData=productCategoryBO.getProductCategoryDetails2(data.getProduct().getCategoryId());
						if(categoryData.getPrinter()!=null && categoryData.getPrinter().getId()>0) {
							String htmlItemTable="";
							htmlItemTable=this.convertToRecieptTemplate(data,null);
							
							if(systemSettingBean.getMyConfig().getPrintMode().equalsIgnoreCase("multiple")) {
								int i=data.getQuantity().intValue();
								while(i>0) {
									this.sendToPrinter(categoryData.getPrinter().getId(), htmlHeader, htmlHeaderItem, htmlItemTable);
									i--;
								}
							}else {//if single item print mode
								printerTextMap.put(categoryData.getPrinter().getId(), htmlItemTable);
							}
						}
						
					}
					
				}
			}
				
			if(systemSettingBean.getMyConfig().getPrintMode().equalsIgnoreCase("single")) {
				for(Entry<Integer, String> e : printerTextMap.entrySet()) {
					Integer printerId = e.getKey();
					String htmlItem = e.getValue();
					this.sendToPrinter(printerId, htmlHeaderItem, htmlHeaderItem, htmlItem);
				}
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public String convertToRecieptTemplate(SalesorderbreakdownModel data,String setItemName) {
		String template="";
		String status="",remarks="";
		
		int itemFontSize=10;
		
		int itemQty=data.getQuantity().intValue();		
		String itemName=data.getProductName();
		if(setItemName!=null && !setItemName.isEmpty()) {
			itemName="(SET) - "+itemName;
		}
		
		if(data.getStatus()!=null) {
			status=" ["+data.getStatus().toUpperCase()+"]";
		}
		
		if(data.getPacking()==1) {
			itemName+="- {P}";
		}
		
		if(systemSettingBean.getMyConfig().getPrintMode().equalsIgnoreCase("multiple")) {
			itemQty=1;
		}
	
	
		if(data.getKitchenorderremarksbreakdownModel().size()>0) {
			for(KitchenorderremarksbreakdownModel remark:data.getKitchenorderremarksbreakdownModel()) {
				
				if(remark.getRemarks().getRemarksListID()>1) {
					remarks+="-"+remark.getRemarks().getRemarksName()+ " ";
				}
			}
		}
		
		template="<tr>"
				+ "<td align='left' style='font-size:"+itemFontSize+"px;border-bottom:1px dashed black;'>"
				+itemName
				+status
				+ "<br/><i>"+remarks+"</i>"
				+ "</td>"
				+ "<td align='right'  style='font-size:"+itemFontSize+"px;border-bottom:1px dashed black'>"+itemQty
				+ "</td>"
				+ "</tr>";
		
		return template;
	}
	
	public void sendToPrinter(Integer printerId,String header,String itemHeader, String item) {
		
		if(item!=null && item!="") {
			String printText="<html>"
					+ "<head>"
					+ "</head>"
					+ "<body>"
					+header
					+itemHeader
					+item
					+"</table>"
					+"</body>"
					+ "</html>";
			
		    PrinterJob job = PrinterJob.getPrinterJob();
		    JTextPane textToPrint = new JTextPane(); 
	        textToPrint.setContentType( "text/html" );
		    
		    HTMLDocument doc = (HTMLDocument)textToPrint.getDocument();
			HTMLEditorKit editorKit = (HTMLEditorKit)textToPrint.getEditorKit();
			
			Paper paper = new Paper();
	        paper.setImageableArea(0, 0,80*72, 297*72);
	        
	        PageFormat format = new PageFormat();
	        format.setPaper(paper);
	        
	        KitchenprinterlistModal printer;
			try {
				printer = systemConfigBO.getKitchenPrinterDetails(printerId);
				PrintService ps = PrintUtility.findPrintService(printer.getPrinterName());
				if(ps!=null) {
					editorKit.insertHTML(doc, doc.getLength(), printText, 0, 0, null);
			        job.setPrintService(ps);   
			        job.setPrintable(textToPrint.getPrintable(null, null),format);
			        job.print();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public boolean holdPOSSalesOrder()
	{					
		List<SalesorderbreakdownModel> salesdataList = new ArrayList<SalesorderbreakdownModel>();	
		List<SalesorderModel> salesorderdataList = new ArrayList<SalesorderModel>();	
		boolean updateSuccess=false;
		
		SalesorderModel salesorderModel=posBean.getSalesorder();		
		ISalesorderBO salesOrderBO=salesorderBean.getSalesOrderBO();		
		PoscounterModel poscounter=posBean.getPoscounter();
		DoctorsPrescriptionsModel dpModel = new DoctorsPrescriptionsModel();
			
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{	
			salesorderModel.setCreatedDate(DateUtil.getTodayDate());
			salesorderModel.setCreatedBy(loginBean.getLogdetail().getEmailAddress());
			salesorderModel.setStatus(config.getValue(IConfiguration.SALESORDER_STATUS_ORDERED_VALUE));		
			salesorderModel.setSalesType(posBean.getSalesType());
			salesorderModel.setSalesRep(loginBean.getUserName());		
			salesorderModel.setTotalAmount(posBean.getTotalAmount());
			salesorderModel.setTotalTax(posBean.getTotalTaxAmount());
			salesorderModel.setReceivedAmount(posBean.getReceivedAmount());
			salesorderModel.setChangeAmount(posBean.getBalanceAmount());
			salesorderModel.setCounterId(poscounter.getCounterId());
			
			if(posBean.getCustomer().getCustomerId()==0)
			{
			salesorderModel.setCustomerBranchId(1);
			}
			else
			{
			salesorderModel.setCustomerBranchId(posBean.getCustomer().getCustomerId());	
			}
			
			salesorderModel.setPrescriptionNo(null);
			
			salesorderModel.setBranchtype(loginBean.getBranch().getBranchtype());
			salesorderModel.setBranchRecordId(loginBean.getBranch().getBranchId());
			salesorderModel.setBranchId(loginBean.getBranch().getBranchId());
			if(posBean.getPrescptNo()!=null && (!posBean.getPrescptNo().equalsIgnoreCase("")) &&posBean.getPrescptNo()!="")
			{
			dpModel=posBean.getDpModel();
			dpModel.setStatus(config.getValue(IConfiguration.DOCTOR_STATUS_ORDERED_VALUE));
			salesorderModel.setDpModel(posBean.getDpModel());
			salesorderModel.setPrescriptionNo(posBean.getPrescptNo());
			}	
			
			salesorderModel.setSalesorderbreakdowns(posBean.getSalesorderbreakdowns());						
			salesorderModel=salesOrderBO.holdSalesorder(salesorderModel);			
			posBean.resetPos();
			updateSuccess=true;
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"posPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));				
			log.info("Error in holdPOSSalesOrder of SalesorderBeanInfo "+ e.toString());
		}
		return updateSuccess;
	}
	
	
	
	
	
	public void updateSalesOrder(SalesorderModel salesorderModel)
	{			
		List<DeliveryorderbreakdownModel> deliverydataList = new ArrayList<DeliveryorderbreakdownModel>();
		List<SalesorderbreakdownModel> salesdataList = new ArrayList<SalesorderbreakdownModel>();
		List<QuotationbreakdownModel>  quotationdataList = new ArrayList<QuotationbreakdownModel>();
		DeliveryorderModel deliveryorder = new DeliveryorderModel();
		QuotationModel quotationModel = new QuotationModel();
		boolean updateSuccess=false;
		
		ISalesorderBO salesOrderBO=salesorderBean.getSalesOrderBO();		
		deliveryorder=salesorderBean.getDeliveryorder();		
		quotationModel=salesorderBean.getQuotation();
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{	
			salesorderModel.setCreatedDate(DateUtil.getTodayDate());
			salesorderModel.setCreatedBy(loginBean.getUserName());
			salesorderModel.setStatus(config.getValue(IConfiguration.SALESORDER_STATUS_NEWORDER_VALUE));		
			salesorderModel.setSalesType(salesorderModel.getSalesType());
			salesorderModel.setSalesRep(loginBean.getUserName());		
			salesorderModel.setTotalAmount(salesorderBean.getTotalAmount());
			salesorderModel.setBranchtype(loginBean.getBranch().getBranchtype());
			salesorderModel.setBranchRecordId(loginBean.getBranch().getBranchId());
			
			if(salesorderModel.getSalesType().equalsIgnoreCase("1"))
			{
				
				
				salesorderModel.setCustomerBranchId(salesorderModel.getBranch().getBranchId());
				salesorderModel.setDeliveryOrderNo(salesorderBean.deliveryorder.getDeliveryOrderNo());			
		
				for(SalesorderbreakdownModel productData:salesorderModel.getSalesorderbreakdowns())
				{
					DeliveryorderbreakdownModel data=new DeliveryorderbreakdownModel();			
					if (ValidatorUtil.isNotNull(salesorderBean.getSalableQuantity().get(productData.getId()))) 
					{
						BigDecimal balanceQuantity	=new BigDecimal(salesorderBean.getSalableQuantity().get(productData.getId()));					
							
							
							//Update Delivery Order Quantity			
							data.setBalanceQuantity(new BigDecimal((productData.getDeliveryQuoationBalanceQuantity().doubleValue()+productData.getQuantity().doubleValue())-balanceQuantity.doubleValue()));					
							data.setSoldQuantity(new BigDecimal((productData.getDeliveryQuoationSoldQuantity().doubleValue()-productData.getQuantity().doubleValue())+balanceQuantity.doubleValue()));
							data.setProductId(productData.getProductId());
							data.setDeliveryOrderBreakdownId(productData.getDeliveryOrderBreakdownId());
							deliverydataList.add(data);	
							
							productData.setProductId(productData.getProductId());
							productData.setProductName(productData.getProductName());				
							productData.setDiscount(productData.getDiscount());
							productData.setDiscountAmount(productData.getDiscountAmount());				
							productData.setQuantity(balanceQuantity);			
							productData.setSubTotal(new BigDecimal(""+salesorderBean.getSalablePrice().get(productData.getId())));
							productData.setUnitPrice(productData.getUnitPrice());	
							productData.setDeliveryOrderBreakdownId(productData.getDeliveryOrderBreakdownId());
							productData.setTaxAmount(new BigDecimal(""+salesorderBean.getSalableTaxPrice().get(productData.getId())));
							
							salesdataList.add(productData);	
						
					}			
				}		
				salesorderModel.setSalesorderbreakdowns(salesdataList);					
				deliveryorder.setDeliveryorderbreakdowns(deliverydataList);	
		
			}			
			
			
			
			
			if(salesorderModel.getSalesType().equalsIgnoreCase("2"))
			{
				
				
				salesorderModel.setCustomerBranchId(salesorderModel.getCustomer().getCustomerId());
				salesorderModel.setQuoteNo(salesorderBean.quotation.getQuotationNo());
				
				for(SalesorderbreakdownModel productData:salesorderModel.getSalesorderbreakdowns())
				{
					QuotationbreakdownModel data=new QuotationbreakdownModel();			
					if (ValidatorUtil.isNotNull(salesorderBean.getSalableQuantity().get(productData.getId()))) 
					{
						BigDecimal balanceQuantity	=new BigDecimal(salesorderBean.getSalableQuantity().get(productData.getId()));
						
							//Update Quotation Order Quantity			
							data.setBalanceQuantity(new BigDecimal(productData.getDeliveryQuoationBalanceQuantity().doubleValue()+productData.getQuantity().doubleValue()- balanceQuantity.doubleValue()));					
							data.setSoldQuantity((productData.getDeliveryQuoationSoldQuantity().subtract(productData.getQuantity())).add(balanceQuantity));
							data.setProductId(productData.getProductId());
							data.setQuotationBreakdownId(productData.getDeliveryOrderBreakdownId());
							quotationdataList.add(data);	
							
				
							productData.setProductId(productData.getProductId());
							productData.setProductName(productData.getProductName());				
							productData.setDiscount(productData.getDiscount());
							productData.setDiscountAmount(productData.getDiscountAmount());				
							productData.setQuantity(balanceQuantity);			
							productData.setSubTotal(new BigDecimal(""+salesorderBean.getSalablePrice().get(productData.getId())));
							productData.setUnitPrice(productData.getUnitPrice());	
							productData.setDeliveryOrderBreakdownId(productData.getDeliveryOrderBreakdownId());									
							productData.setTaxAmount(new BigDecimal(""+salesorderBean.getSalableTaxPrice().get(productData.getId())));

							salesdataList.add(productData);	
						
					}			
				}		
			
				salesorderModel.setSalesorderbreakdowns(salesdataList);		
				quotationModel.setQuotationbreakdowns(quotationdataList);
				}	
		
			 updateSuccess=salesOrderBO.updateSalesorder(salesorderModel);
		
			 if(updateSuccess){
				 if(salesorderModel.getSalesType().equalsIgnoreCase("1"))
					{
				//updateSuccess=deliveryOrderBO.updateDeliveryorderForSales(deliveryorder);
					}
				 if(salesorderModel.getSalesType().equalsIgnoreCase("2"))
					{
					// updateSuccess=quotationBO.updateQuotationForSales(quotationModel); 
					}				
					
			this.listSalesOrder();
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("salesorder.label.update.success"),null));				
		
			 }
		
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));				
			log.info("Error in saveSalesOrder of SalesorderBeanInfo "+ e.toString());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean validateSalesOrderSearch() {
		boolean valid = true;		
	
		SalesorderBean salesorderBean = (SalesorderBean) BeanContext.getReference("salesorderBean");
		
		if(salesorderBean.getDeliveryOrderNo()!=null)
		{
			if(salesorderBean.getDeliveryOrderNo().equalsIgnoreCase(""))
			{
				salesorderBean.setDeliveryOrderNo(null);
			}
		}		
		
		if(salesorderBean.getCustomerId()!=null)
		{
			if(salesorderBean.getCustomerId()==0)
			{
				salesorderBean.setCustomerId(null);
			}
		}	
		
		if(salesorderBean.getBranchId()!=null)
		{
			if(salesorderBean.getBranchId()==0)
			{
				salesorderBean.setBranchId(null);
			}
		}		
		
		if(salesorderBean.getStatus()!=null)
		{
			if(salesorderBean.getStatus().equalsIgnoreCase("") || salesorderBean.getStatus().equalsIgnoreCase("0") )
			{
				salesorderBean.setStatus(null);
			}
		}	
		
		if(salesorderBean.getSalesOrderNo()!=null)
		{
			if(salesorderBean.getSalesOrderNo().equalsIgnoreCase("") || salesorderBean.getSalesOrderNo().equalsIgnoreCase("0") )
			{
				salesorderBean.setSalesOrderNo(null);
			}
		}	
		
		if(salesorderBean.getCustomerId()==null && salesorderBean.getBranchId()==null &&  salesorderBean.getDateFrom()==null && salesorderBean.getDateTo()==null && salesorderBean.getStatus()==null  && salesorderBean.getSalesOrderNo()==null)
		{	
			valid = false;
		}		
		
		else
		{
			valid = true;
		}			
		return valid;

	}
	
	public boolean updatePOSSalesOrder()
	{					
		List<SalesorderbreakdownModel> salesdataList = new ArrayList<SalesorderbreakdownModel>();	
		List<SalesorderModel> salesorderdataList = new ArrayList<SalesorderModel>();	
		boolean updateSuccess=false;
		
		SalesorderModel salesorderModel=posBean.getSalesorder();

		ISalesorderBO salesOrderBO=salesorderBean.getSalesOrderBO();		
		
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{	
			
			salesorderModel.setStatus(config.getValue(IConfiguration.SALESORDER_STATUS_ORDERED_VALUE));		
					
			salesorderModel.setTotalAmount(posBean.getTotalAmount());
			salesorderModel.setTotalTax(posBean.getTotalTaxAmount());
			salesorderModel.setReceivedAmount(posBean.getReceivedAmount());
			salesorderModel.setChangeAmount(posBean.getBalanceAmount());
			salesorderModel.setTotalBeforeDiscount(posBean.getTotalBeforeDiscount());
			salesorderModel.setDiscountRate(posBean.getDiscountRate());
			salesorderModel.setDiscountRemarks(posBean.getDiscountRemarks());
			salesorderModel.setBranchtype(loginBean.getBranch().getBranchtype());
			salesorderModel.setBranchRecordId(loginBean.getBranch().getBranchId());
			salesorderModel.setBranchId(loginBean.getBranch().getBranchId());
			
			salesorderModel.setSalesorderbreakdowns(posBean.getSalesorderbreakdowns());
			
			
			updateSuccess=salesOrderBO.updateSalesorder(salesorderModel);
			
			posBean.setSalesOrderId(salesorderModel.getSalesOrderId());
			HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
			request.getSession().getServletContext().setAttribute("salesOrderId", salesorderModel.getSalesOrderId());  
			
			posBean.setCardOption("normal");
		
			posBean.kitchedOrder.clear();
			posBean.setKitchenOrderBeforeSales(null);
			posBean.resetPos();
			updateSuccess=true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"posPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));				
			log.info("Error in saveSalesOrder of SalesorderBeanInfo "+ e.toString());
		}
		return updateSuccess;
	}
/*
 Integer printerId = e.getKey();
	        String htmlItem = e.getValue();
	        
			if(htmlItem!=null && htmlItem!="") {
				String printText="<html>"
						+ "<head>"
						+ "</head>"
						+ "<body>"
						+htmlHeader
						+htmlHeaderItem
						+htmlItem
						+"</table>"
						+"</body>"
						+ "</html>";
				
			    PrinterJob job = PrinterJob.getPrinterJob();
			    JTextPane textToPrint = new JTextPane(); 
		        textToPrint.setContentType( "text/html" );
			    
			    HTMLDocument doc = (HTMLDocument)textToPrint.getDocument();
				HTMLEditorKit editorKit = (HTMLEditorKit)textToPrint.getEditorKit();
				
				Paper paper = new Paper();
		        paper.setImageableArea(0, 0,80*72, 297*72);
		        
		        PageFormat format = new PageFormat();
		        format.setPaper(paper);
		        
		        KitchenprinterlistModal printer= systemConfigBO.getKitchenPrinterDetails(printerId);
		        PrintService ps = PrintUtility.findPrintService(printer.getPrinterName());
				if(ps!=null) {
					editorKit.insertHTML(doc, doc.getLength(), printText, 0, 0, null);
			        job.setPrintService(ps);   
			        job.setPrintable(textToPrint.getPrintable(null, null),format);
			        job.print();
				}
				
			}

  
 */
}
