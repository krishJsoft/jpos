package com.project.home;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;


import com.project.bo.interfaces.IProductBO;
import com.project.bo.interfaces.ISalesorderBO;
import com.project.model.datamodel.ProcessActivityModel;
import com.project.model.datamodel.ProductModel;
import com.project.model.datamodel.ProductpriceModel;
import com.project.model.datamodel.SalesPurchaseDashboradModel;
import com.project.model.invoice.branch.BranchinvoiceModel;
import com.project.model.sale.sales.BranchSalesSummaryModel;
import com.project.model.sale.sales.ExpensesTransactionModel;
import com.project.util.DateUtil;

import com.project.bean.sales.sale.PosBean;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.common.validation.ValidatorUtil;
import com.project.login.LoginBean;
import com.project.model.paginghelper.ProductPagingHelper;

//Author Gopal Ar 
//2013-08

@ManagedBean(name = "dashboardBean")
@SessionScoped
public class DashboardBean {

	
	//List<ProductModel> productlist = new ArrayList<ProductModel>();	   
    CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
    FacesContext context = FacesContext.getCurrentInstance();
    Configuration config = Configuration.getConfiguration();
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	private List<ProductModel> productlist = new ArrayList<ProductModel>();
	private LazyDataModel<ProductModel> productModel = null;
	private Map<String, Boolean> checkmonth = new HashMap<String, Boolean>();	
	private List<ProductModel> selectedProducts= new ArrayList<ProductModel>();
	
	
	private List<ProductModel> productAlertlist = new ArrayList<ProductModel>();
	List<SalesPurchaseDashboradModel> dashList = new ArrayList<SalesPurchaseDashboradModel>();
	List<BranchSalesSummaryModel> topcustomers = new ArrayList<BranchSalesSummaryModel>();
	List<BranchSalesSummaryModel> topsuppliers = new ArrayList<BranchSalesSummaryModel>();	
	List<ProcessActivityModel> processs = new ArrayList<ProcessActivityModel>();	
	List<SalesPurchaseDashboradModel> dashList1 = new ArrayList<SalesPurchaseDashboradModel>();
	List<ExpensesTransactionModel> expsTrans=new ArrayList<ExpensesTransactionModel>();
	
    Integer purchaseRequestPendingCount=0;
    Integer purchaseOrderPendingCount=0;
    Integer deliveryCount=0;
    Integer deliveryPendingCount=0;
    Integer receivedinvoicePendingCount=0;
    Integer payableinvoicePendingCount=0;
    Integer receivedpaymentPendingCount=0;
    Integer payablepaymentPendingCount=0;
    private Integer saleMonthCheckedCount=0;
    
    
	IProductBO productBO=objectMapController.getProductBO();	
	
	ISalesorderBO salesOrderBO=objectMapController.getSalesOrderBO();
	
	public IProductBO getProductBO() {
		return productBO;
	}

	public void setProductBO(IProductBO productBO) {
		this.productBO = productBO;
	}
	

	public Integer getPurchaseRequestPendingCount() {
		return purchaseRequestPendingCount;
	}

	public void setPurchaseRequestPendingCount(Integer purchaseRequestPendingCount) {
		this.purchaseRequestPendingCount = purchaseRequestPendingCount;
	}

	public Integer getPurchaseOrderPendingCount() {
		return purchaseOrderPendingCount;
	}

	public void setPurchaseOrderPendingCount(Integer purchaseOrderPendingCount) {
		this.purchaseOrderPendingCount = purchaseOrderPendingCount;
	}

	public Integer getDeliveryCount() {
		return deliveryCount;
	}

	public void setDeliveryCount(Integer deliveryCount) {
		this.deliveryCount = deliveryCount;
	}

	public Integer getDeliveryPendingCount() {
		return deliveryPendingCount;
	}

	public void setDeliveryPendingCount(Integer deliveryPendingCount) {
		this.deliveryPendingCount = deliveryPendingCount;
	}
	
	
	public Integer getReceivedinvoicePendingCount() {
		return receivedinvoicePendingCount;
	}

	public void setReceivedinvoicePendingCount(Integer receivedinvoicePendingCount) {
		this.receivedinvoicePendingCount = receivedinvoicePendingCount;
	}

	public Integer getPayableinvoicePendingCount() {
		return payableinvoicePendingCount;
	}

	public void setPayableinvoicePendingCount(Integer payableinvoicePendingCount) {
		this.payableinvoicePendingCount = payableinvoicePendingCount;
	}

	public List<ProductModel> getProductAlertlist() {
		return productAlertlist;
	}

	public void setProductAlertlist(List<ProductModel> productAlertlist) {
		this.productAlertlist = productAlertlist;
	}

	public List<ProductModel> getProductlist() {
		return productlist;
	}

	public void setProductlist(List<ProductModel> productlist) {
		this.productlist = productlist;
	}

	public LazyDataModel<ProductModel> getProductModel() {
		return productModel;
	}

	public void setProductModel(LazyDataModel<ProductModel> productModel) {
		this.productModel = productModel;
	}	
	
	public List<ProductModel> getSelectedProducts() {
		return selectedProducts;
	}

	public void setSelectedProducts(List<ProductModel> selectedProducts) {
		this.selectedProducts = selectedProducts;
	}

	public Integer getReceivedpaymentPendingCount() {
		return receivedpaymentPendingCount;
	}

	public void setReceivedpaymentPendingCount(Integer receivedpaymentPendingCount) {
		this.receivedpaymentPendingCount = receivedpaymentPendingCount;
	}

	public Integer getPayablepaymentPendingCount() {
		return payablepaymentPendingCount;
	}

	public void setPayablepaymentPendingCount(Integer payablepaymentPendingCount) {
		this.payablepaymentPendingCount = payablepaymentPendingCount;
	}	
	

	public Integer getSaleMonthCheckedCount() {
		return saleMonthCheckedCount;
	}

	public void setSaleMonthCheckedCount(Integer saleMonthCheckedCount) {
		this.saleMonthCheckedCount = saleMonthCheckedCount;
	}

	public List<SalesPurchaseDashboradModel> getDashList() {
		return dashList;
	}

	public void setDashList(List<SalesPurchaseDashboradModel> dashList) {
		this.dashList = dashList;
	}	
	
	public List<SalesPurchaseDashboradModel> getDashList1() {
		return dashList1;
	}

	public void setDashList1(List<SalesPurchaseDashboradModel> dashList1) {
		this.dashList1 = dashList1;
	}


	public List<ExpensesTransactionModel> getExpsTrans() {
		return expsTrans;
	}

	public void setExpsTrans(List<ExpensesTransactionModel> expsTrans) {
		this.expsTrans = expsTrans;
	}

	public Map<String, Boolean> getCheckmonth() {
		return checkmonth;
	}

	public void setCheckmonth(Map<String, Boolean> checkmonth) {
		this.checkmonth = checkmonth;
	}


	
	public List<BranchSalesSummaryModel> getTopcustomers() {
		return topcustomers;
	}

	public void setTopcustomers(List<BranchSalesSummaryModel> topcustomers) {
		this.topcustomers = topcustomers;
	}
	
	
	public List<BranchSalesSummaryModel> getTopsuppliers() {
		return topsuppliers;
	}

	public void setTopsuppliers(List<BranchSalesSummaryModel> topsuppliers) {
		this.topsuppliers = topsuppliers;
	}

	public List<ProcessActivityModel> getProcesss() {
		return processs;
	}

	public void setProcesss(List<ProcessActivityModel> processs) {
		this.processs = processs;
	}
	
	public void getLatestDashboardRecord()
	{		
		DashboardBeanInfo info = new DashboardBeanInfo();
		ChartBean chartBean = (ChartBean) BeanContext.getReference("chartBean");
		PosBean posBean = (PosBean) BeanContext.getReference("posBean");
		try
		{
			
		/*processs.clear();
		processs.add(getTotalPurchaseRequestPending());	
		processs.add(getTotalPurchaseOrderPending());
		processs.add(getTotalDeliveryReceivedSupplier());
		
		processs.add(getTotalInvoiceIssuedToCustomer());
		processs.add(getTotalInvoiceReceivedSupplier());
		processs.add(getTotalPaymentReceivedfromCustomer());
		processs.add(getTotalPaymentPendingtoSupplier());		*/
		
		posBean.setStatus("1");
		posBean.getKitchenOrders();		
		//getProductAlert();		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"dashboardPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
	}
	
	public void onCheckSalesMonthReport(AjaxBehaviorEvent event) {
	    javax.faces.component.UIComponent component = event.getComponent();

		String monthName=(String) component .getAttributes().get("monthName");
		if(this.getSaleMonthCheckedCount()==null) {
			this.setSaleMonthCheckedCount(0);
		}
		int counter=this.getSaleMonthCheckedCount();
		
		if(this.getCheckmonth().get(monthName)) {
			counter++;
		}else {
			counter--;
		}
		
		this.setSaleMonthCheckedCount(counter);
	}
	
	public void printrequiredMonth()
	{
		boolean validChecked=false;
		dashList1.clear();
		int counter=0;
		for(SalesPurchaseDashboradModel data:dashList)
		{		
			if (ValidatorUtil.isNotNull(this.getCheckmonth().get(data.getMonthName()))) 
			{		
				if(this.getCheckmonth().get(data.getMonthName()))
				{
				SalesPurchaseDashboradModel datam = new SalesPurchaseDashboradModel();
				datam.setDuplicateTax(data.getDuplicateTax());
				datam.setEndDate(data.getEndDate());
				datam.setMonthName(data.getMonthName());
				datam.setMonthNo(data.getMonthNo());
				datam.setOriginalTax(data.getOriginalTax());
				datam.setPurcahsesubTotal(data.getPurcahsesubTotal());
				datam.setQuantity(data.getQuantity());
				datam.setStartDate(data.getStartDate());
				datam.setSubTotal(data.getSubTotal());
				datam.setExpensesAmmount(data.getExpensesAmmount());
				
				
				dashList1.add(datam);
				counter++;
				}
				
			}
			
		}
		if(counter>0) {
			
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('annualsalesprintDig').show();");
		}else {
			RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Please select the Month"));
		}
		
	}
	

	public void getSalesSummaryRecord() {
		DashboardBeanInfo info = new DashboardBeanInfo();
		ChartBean chartBean = (ChartBean) BeanContext.getReference("chartBean");
		String usertype="0";
		if(loginBean.getLogdetail().getDesignation().equalsIgnoreCase("owner"))
		{
			usertype="1";
		}
		else
		{
			usertype="0";
		}
		
		try
		{		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		this.dashList.clear();
		
		if(chartBean.getSalesyear()!=0)
		{
		year=chartBean.getSalesyear();
		}
		//int countData=salesOrderBO.getSalesorderCount(null, null, null, DateUtil.getFirstDayYear(year), DateUtil.getEndDayYear(year), null, loginBean.getBranch().getBranchId(),null);
	
		this.dashList=info.getSalesSummaryDashboard(year,usertype);	
		//this.topcustomers=salesOrderBO.getCustomerSalesByBranch(null, DateUtil.getFirstDayYear(year),DateUtil.getEndDayYear(year), 0, 9, loginBean.getBranch().getBranchId());
		//chartBean.getSalesPurchasechart();
		//chartBean.topCustomerBarchart();
		//getTopSupplier();
		}
		catch(Exception e)
		{
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"dashboardPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
	}
	public void getSalesRecord()
	{		
		DashboardBeanInfo info = new DashboardBeanInfo();
		ChartBean chartBean = (ChartBean) BeanContext.getReference("chartBean");
		String usertype="0";
		if(loginBean.getLogdetail().getDesignation().equalsIgnoreCase("owner"))
		{
			usertype="1";
		}
		else
		{
			usertype="0";
		}
		
		try
		{		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		this.dashList.clear();
		
		if(chartBean.getSalesyear()!=0)
		{
		year=chartBean.getSalesyear();
		}
		//int countData=salesOrderBO.getSalesorderCount(null, null, null, DateUtil.getFirstDayYear(year), DateUtil.getEndDayYear(year), null, loginBean.getBranch().getBranchId(),null);
	
		this.dashList=info.getSalesDashboard(year,usertype);	
		//this.topcustomers=salesOrderBO.getCustomerSalesByBranch(null, DateUtil.getFirstDayYear(year),DateUtil.getEndDayYear(year), 0, 9, loginBean.getBranch().getBranchId());
		//chartBean.getSalesPurchasechart();
		//chartBean.topCustomerBarchart();
		//getTopSupplier();
		}
		catch(Exception e)
		{
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"dashboardPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
	}
	
	
	
	public void getLatestDashboardSalesRecord()
	{		
		DashboardBeanInfo info = new DashboardBeanInfo();
		ChartBean chartBean = (ChartBean) BeanContext.getReference("chartBean");
		try
		{		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		this.dashList.clear();
		
		if(chartBean.getSalesyear()!=0)
		{
		year=chartBean.getSalesyear();
		}
		//int countData=salesOrderBO.getSalesorderCount(null, null, null, DateUtil.getFirstDayYear(year), DateUtil.getEndDayYear(year), null, loginBean.getBranch().getBranchId(),null);
	
		this.dashList=info.getSalesDashboard(year,"0");	
		
		this.topcustomers=salesOrderBO.getCustomerSalesByBranch(null, DateUtil.getFirstDayYear(year),DateUtil.getEndDayYear(year), 0, 9, loginBean.getBranch().getBranchId());
		chartBean.getSalesPurchasechart();
		chartBean.topCustomerBarchart();
		getTopSupplier();
		}
		catch(Exception e)
		{
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"dashboardPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
	}
	
	
	public void getTopSupplier()
	{
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		ChartBean chartBean = (ChartBean) BeanContext.getReference("chartBean");		
		if(chartBean.getSalesyear()!=0)
		{
		year=chartBean.getSalesyear();
		}
		DashboardBeanInfo info = new DashboardBeanInfo();		
		try
		{	
	//	this.topsuppliers=supplierinvoiceBO.getSupplierPurchaseByBranch(null, DateUtil.getFirstDayYear(year),DateUtil.getEndDayYear(year), 0, 9, loginBean.getBranch().getBranchId());

		chartBean.topSupplierBarchart();
		}
		catch(Exception e)
		{
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"dashboardPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
	}
	
	public void getTopsalesProduct()
	{		
		DashboardBeanInfo info = new DashboardBeanInfo();
		ChartBean chartBean = (ChartBean) BeanContext.getReference("chartBean");
		try
		{			
		chartBean.getTopsalesProductTest();
		}
		catch(Exception e)
		{
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"dashboardPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
	}
	
	
	public LazyDataModel<ProductModel> getProductAlert()
	{
		try
		{
		this.setProductModel(null);				
		productModel = new ProductPagingHelper(null, null, null,null,productBO,loginBean.getBranch().getBranchId(),null,1,null,"normal");	
		//productlist=productBO.findByProductListAll(loginBean.getBranch().getBranchId());
		this.setProductModel(productModel);	
		}
		catch(Exception e){					
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"dashboardPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
			}
		return null;
	}
	
	
	
	
	
	/*public List<ProductModel> getProductAlert()
	{
		try
		{
		this.setProductModel(null);				
		//productModel = new ProductPagingHelper(null, null, null,null,productBO,loginBean.getBranch().getBranchId(),null,1);	
		productlist=productBO.findByProductListAll(loginBean.getBranch().getBranchId());
		//this.setProductModel(productModel);	
		}
		catch(Exception e){					
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"dashboardPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
			}
		return null;
	}*/
	
	
	
	
	public ProcessActivityModel getTotalPurchaseRequestPending()
	{	
		ProcessActivityModel process = new ProcessActivityModel();
		purchaseRequestPendingCount=0;		
		int[] searchResultIDList;
		LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
		try
		{
		if(loginBean.getBranch().getBranchtype().contentEquals(config.getValue(IConfiguration.PROJECT_TYPE_BRANCH_VALUE)))
		{
		//	this.purchaseRequestPendingCount=purchaseRequestBO.getPurchaserequestCount(null, loginBean.getBranch().getBranchId(), DateUtil.getFirstDate(), DateUtil.getToTodayDateTime(), config.getValue(IConfiguration.PURCHASEREQUEST_STATUS_NEWORDER_VALUE),null);

		}	
		else
		{			
		//	this.purchaseRequestPendingCount=purchaseRequestBO.getPurchaserequestCount(null, null, DateUtil.getFirstDate(), DateUtil.getToTodayDateTime(), config.getValue(IConfiguration.PURCHASEREQUEST_STATUS_NEWORDER_VALUE),"2");

		}			
		//purchaseRequestPendingCount=searchResultIDList.length;
		process.setProcessName("Purchase Request Pending");
		process.setCount(this.purchaseRequestPendingCount);
		process.setId("1");
		
		}
		catch(Exception e){					
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"dashboardPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
			}
		return process;
	}
	
	public ProcessActivityModel getTotalPurchaseOrderPending()
	{
		ProcessActivityModel process = new ProcessActivityModel();
		int purchaseOrderPendingCount=0;
		try
		{
			//this.purchaseOrderPendingCount=purchaseOrderBO.getPurchaseorderCount(null, null, DateUtil.getFirstDate(), DateUtil.getToTodayDateTime(), config.getValue(IConfiguration.PURCHASEORDER_STATUS_NEWORDER_VALUE),loginBean.getBranch().getBranchId());
		//count=purchaseOrderPendingCount.length;
			process.setProcessName("Total Purchase Order Pending");
			process.setCount(this.purchaseOrderPendingCount);
			process.setId("2");
		}
		
		catch(Exception e){					
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"dashboardPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
			}
		return process;
	}	
	
	
	public ProcessActivityModel getTotalDeliveryReceivedSupplier()
	{
		ProcessActivityModel process = new ProcessActivityModel();
		try
		{
			//this.deliveryCount=deliveryOrderBO.getDeliveryorderCount(null, null,null,  DateUtil.getFirstDate(), DateUtil.getToTodayDateTime(), config.getValue(IConfiguration.DELIVERYORDER_STATUS_NEWORDER_VALUE),loginBean.getBranch().getBranchId());
			process.setProcessName("Delivery Order Pending");
			process.setCount(this.deliveryCount);
			process.setId("3");
		}
		catch(Exception e){					
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"dashboardPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
			}
		return process;
	}	

	
	public ProcessActivityModel getTotalDeliveryOrderPendingSupplier()
	{
		ProcessActivityModel process = new ProcessActivityModel();
		int count=0;
		try
		{
			
		}
		catch(Exception e){					
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"dashboardPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
			}
		return process;
	}
	
	
	
	public ProcessActivityModel getTotalInvoiceReceivedSupplier()
	{
		ProcessActivityModel process = new ProcessActivityModel();
		int receivedinvoicePendingCount=0;
		try
		{
			
		}
		catch(Exception e){					
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"dashboardPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
			}
		return process;
	}
	
	
	
	
	public ProcessActivityModel getTotalInvoiceIssuedToCustomer()
	{
		ProcessActivityModel process = new ProcessActivityModel();
		int payableinvoicePendingCount=0;
		try
		{
			
		}
		catch(Exception e){					
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"dashboardPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
			}
		return process;
	}
	
	
	public ProcessActivityModel getTotalPaymentReceivedfromCustomer()
	{
		ProcessActivityModel process = new ProcessActivityModel();
		int receivedpaymentPendingCount=0;		
		try
		{
			
		}
		catch(Exception e){					
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"dashboardPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
			}
		return process;
	}
	
	
	public ProcessActivityModel getTotalPaymentPendingtoSupplier()
	{
		ProcessActivityModel process = new ProcessActivityModel();
		int payablepaymentPendingCount=0;
		try
		{
			
		}
		catch(Exception e){					
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"dashboardPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
			}
		return process;
	}
	
	
	
	public void gotoTotalPurchaseRequestPending()
	{	
		
		
	}
	
	public void gotoTotalPurchaseOrderPending()
	{				
		
	}	
	
	
	public void gotoTotalDeliveryReceivedSupplier()
	{
		
	}	

	
	public void gotoTotalDeliveryOrderPendingSupplier()	{	
		
		
		
	}
	
	
	public void gotoTotalInvoiceReceivedSupplier()	{	
		
		
		
	}
	
	
	public void gotoTotalInvoiceIssuedToCustomer()	{	
		
		
	}
	
	
	
	public void gotoTotalPaymentReceivedfromCustomer()	{	
		
		
	}
	

	public void gotoTotalPaymentPendingtoSupplier()	{	
	
	
}
	
	
	
	public void gotoProcess(ActionEvent event)
		{		
		FacesContext context = FacesContext.getCurrentInstance();		
		try {			
			ProcessActivityModel processData = (ProcessActivityModel) event.getComponent().getAttributes().get("processs");
			
			if (processData.getId().equalsIgnoreCase("1")) {				
				
				gotoTotalPurchaseRequestPending();
			}
			if (processData.getId().equalsIgnoreCase("2")) {				
				
				gotoTotalPurchaseOrderPending();
			}
			if (processData.getId().equalsIgnoreCase("3")) {				
				
				gotoTotalDeliveryReceivedSupplier();
			}
			if (processData.getId().equalsIgnoreCase("4")) {				
	
				gotoTotalInvoiceReceivedSupplier();
			}
			if (processData.getId().equalsIgnoreCase("5")) {				
	
				gotoTotalInvoiceIssuedToCustomer();
			}
			if (processData.getId().equalsIgnoreCase("6")) {				
				
				gotoTotalPaymentReceivedfromCustomer();
			}
			if (processData.getId().equalsIgnoreCase("7")) {				
				
				gotoTotalPaymentPendingtoSupplier();
			}
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"dashboardPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			
			}				
	
	

}

	
}
