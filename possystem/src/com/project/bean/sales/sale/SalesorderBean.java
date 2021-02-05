package com.project.bean.sales.sale;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
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

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.data.PageEvent;
import org.primefaces.model.LazyDataModel;

import com.project.bean.admin.CommonListBeanInfo;
import com.project.bo.interfaces.IProductBO;
import com.project.bo.interfaces.ISalesorderBO;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.util.DecimalUtil;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.common.validation.ValidatorUtil;
import com.project.home.ProjectHome;
import com.project.home.ProjectHomeBeanInfo;
import com.project.login.LoginBean;
import com.project.model.datamodel.CustomerModel;
import com.project.model.datamodel.ProductModel;
import com.project.model.datamodel.RolePrivilegesModel;
import com.project.model.datamodel.SalesPurchaseDashboradModel;
import com.project.model.datamodel.purchase.PurchaseorderModel;
import com.project.model.datamodel.purchase.PurchaseorderbreakdownsModel;
import com.project.model.datamodel.purchase.PurchaserequestbreakdownModel;
import com.project.model.datamodel.stock.DeliveryorderModel;
import com.project.model.datamodel.stock.DeliveryorderbreakdownModel;

import com.project.model.paginghelper.SalesProductHistoryPagingHelper;
import com.project.model.sale.sales.QuotationModel;
import com.project.model.sale.sales.QuotationbreakdownModel;
import com.project.model.sale.sales.SalesorderModel;
import com.project.model.sale.sales.SalesorderbreakdownModel;
import com.project.model.paginghelper.SalesorderModelPagingHelper;
import com.project.util.DateUtil;

/*
 * @author Gopal Ar
 * @version 1.0
 * @since 10 Sep 2013
 * 
 */

@ManagedBean(name = "salesorderBean")
@SessionScoped
public class SalesorderBean {

	SalesorderModel salesorder = new SalesorderModel();
	SalesorderbreakdownModel sobreakdownmodel=new SalesorderbreakdownModel();
	DeliveryorderModel deliveryorder = new DeliveryorderModel();
	QuotationModel quotation = new QuotationModel();
	private String salesType="1";
	boolean saveConfirm=false;
	List<SalesorderbreakdownModel> salesorderbreakdowns = new ArrayList<SalesorderbreakdownModel>();
	List<SalesorderModel> categoryReport = new ArrayList<SalesorderModel>();
	List<SalesorderModel> salesReport = new ArrayList<SalesorderModel>();
	

	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	private LazyDataModel<SalesorderModel> salesorderModel = null;
	private LazyDataModel<SalesorderbreakdownModel> salesorderbreakdownModel = null;
	Configuration config = Configuration.getConfiguration();
	private Map<String, String> salableQuantity = new HashMap<String, String>();
	private Map<String, String> salableQuotationQuantity = new HashMap<String, String>();
	private Map<String, BigDecimal> salablePrice = new HashMap<String, BigDecimal>();
	private Map<String, BigDecimal> salableTaxPrice = new HashMap<String, BigDecimal>();
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");	

	List<SalesorderModel> salesorderlist = new ArrayList<SalesorderModel>();

	private String searchBy="salesOrder";
	private String deliveryOrderNo;
	private String salesOrderNo;
	private String purchaseOrderNo;
	private String grnNo;
	private String quotationNo;
	private Integer salesOrderId;	
	private Integer annualSalesItemSummaryYear=LocalDate.now().getYear();
	
    BigDecimal totalAmount =new BigDecimal(0.00);
    BigDecimal totalTaxAmount =new BigDecimal(0.00);
	private Integer branchId;
	private Integer customerId;
	private Date dateFrom;
	private Date dateTo;
	private Date dailyReportDateFrom;
	private Date dailyReportDateTo;
	private String status;
	private String barcode;
	private String productName;
	private String customerBranchName;
	private Integer customerBranchId;
	private Integer deliveryOrderId;
	private CustomerModel customer;
	private Integer quotationId;	
	private Integer limit;	
	public String getSearchBy() {
		return searchBy;
	}

	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}

	SalesPurchaseDashboradModel sales;
	private Map<String, Boolean> checkAmountApportion = new HashMap<String, Boolean>();	
	
	private List<SelectItem> selectQuotationList = new ArrayList<SelectItem>();
	private List<SelectItem> selectGrnList = new ArrayList<SelectItem>();
	
	
	ISalesorderBO salesOrderBO=objectMapController.getSalesOrderBO();
	IProductBO productBo= objectMapController.getProductBO();

	
	public ISalesorderBO getSalesOrderBO() {
		return salesOrderBO;
	}

	public void setSalesOrderBO(ISalesorderBO salesOrderBO) {
		this.salesOrderBO = salesOrderBO;
	}
	


	
	 public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}


	public List<SalesorderModel> getSalesorderlist() {
		return salesorderlist;
	}

	public void setSalesorderlist(List<SalesorderModel> salesorderlist) {
		this.salesorderlist = salesorderlist;
		
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
	
	public SalesorderModel getSalesorder() {
		return salesorder;
	}

	public void setSalesorder(SalesorderModel salesorder) {
		this.salesorder = salesorder;
	}

	public List<SalesorderbreakdownModel> getSalesorderbreakdowns() {
		return salesorderbreakdowns;
	}

	public void setSalesorderbreakdowns(
			List<SalesorderbreakdownModel> salesorderbreakdowns) {
		this.salesorderbreakdowns = salesorderbreakdowns;
	}

	public List<SalesorderModel> getCategoryReport() {
		return categoryReport;
	}

	public void setCategoryReport(List<SalesorderModel> categoryReport) {
		this.categoryReport = categoryReport;
	}

	public List<SalesorderModel> getSalesReport() {
		return salesReport;
	}

	public void setSalesReport(List<SalesorderModel> salesReport) {
		this.salesReport = salesReport;
	}

	public LazyDataModel<SalesorderModel> getSalesorderModel() {
		return salesorderModel;
	}

	public void setSalesorderModel(
			LazyDataModel<SalesorderModel> salesorderModel) {
		this.salesorderModel = salesorderModel;
	}

	public LazyDataModel<SalesorderbreakdownModel> getSalesorderbreakdownModel() {
		return salesorderbreakdownModel;
	}

	public void setSalesorderbreakdownModel(
			LazyDataModel<SalesorderbreakdownModel> salesorderbreakdownModel) {
		this.salesorderbreakdownModel = salesorderbreakdownModel;
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

	public Map<String, String> getSalableQuantity() {
		return salableQuantity;
	}

	public void setSalableQuantity(Map<String, String> salableQuantity) {
		this.salableQuantity = salableQuantity;
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

	public String getGrnNo() {
		return grnNo;
	}

	public void setGrnNo(String grnNo) {
		this.grnNo = grnNo;
	}

	public String getQuotationNo() {
		return quotationNo;
	}

	public void setQuotationNo(String quotationNo) {
		this.quotationNo = quotationNo;
	}

	public Integer getSalesOrderId() {
		return salesOrderId;
	}

	public void setSalesOrderId(Integer salesOrderId) {
		this.salesOrderId = salesOrderId;
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

	public Date getDailyReportDateFrom() {
		return dailyReportDateFrom;
	}

	public void setDailyReportDateFrom(Date dailyReportDateFrom) {
		this.dailyReportDateFrom = dailyReportDateFrom;
	}

	public Date getDailyReportDateTo() {
		return dailyReportDateTo;
	}

	public void setDailyReportDateTo(Date dailyReportDateTo) {
		this.dailyReportDateTo = dailyReportDateTo;
	}

	public Integer getAnnualSalesItemSummaryYear() {
		return annualSalesItemSummaryYear;
	}

	public void setAnnualSalesItemSummaryYear(Integer annualSalesItemSummaryYear) {
		this.annualSalesItemSummaryYear = annualSalesItemSummaryYear;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCustomerBranchName() {
		return customerBranchName;
	}

	public void setCustomerBranchName(String customerBranchName) {
		this.customerBranchName = customerBranchName;
	}

	public Integer getCustomerBranchId() {
		return customerBranchId;
	}

	public void setCustomerBranchId(Integer customerBranchId) {
		this.customerBranchId = customerBranchId;
	}

	public List<SelectItem> getSelectQuotationList() {
		return selectQuotationList;
	}

	public void setSelectQuotationList(List<SelectItem> selectQuotationList) {
		this.selectQuotationList = selectQuotationList;
	}

	public List<SelectItem> getSelectGrnList() {
		return selectGrnList;
	}

	public void setSelectGrnList(List<SelectItem> selectGrnList) {
		this.selectGrnList = selectGrnList;
	}	
	
	
	public Integer getDeliveryOrderId() {
		return deliveryOrderId;
	}

	public void setDeliveryOrderId(Integer deliveryOrderId) {
		this.deliveryOrderId = deliveryOrderId;
	}
	
	
	public Integer getQuotationId() {
		return quotationId;
	}

	public void setQuotationId(Integer quotationId) {
		this.quotationId = quotationId;
	}

	public DeliveryorderModel getDeliveryorder() {
		return deliveryorder;
	}

	public void setDeliveryorder(DeliveryorderModel deliveryorder) {
		this.deliveryorder = deliveryorder;
	}	

	public CustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}		
	
	public QuotationModel getQuotation() {
		return quotation;
	}

	public void setQuotation(QuotationModel quotation) {
		this.quotation = quotation;
	}

	public String getSalesType() {
		return salesType;
	}

	public void setSalesType(String salesType) {
		this.salesType = salesType;
	}	
	
	
	public Map<String, BigDecimal> getSalablePrice() {
		return salablePrice;
	}

	public void setSalablePrice(Map<String, BigDecimal> salablePrice) {
		this.salablePrice = salablePrice;
	}
	
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}	
	

	public String getSalesOrderNo() {
		return salesOrderNo;
	}

	public void setSalesOrderNo(String salesOrderNo) {
		this.salesOrderNo = salesOrderNo;
	}


	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}	
	
	
	public BigDecimal getTotalTaxAmount() {
		return totalTaxAmount;
	}

	public void setTotalTaxAmount(BigDecimal totalTaxAmount) {
		this.totalTaxAmount = totalTaxAmount;
	}

	public boolean isSaveConfirm() {
		return saveConfirm;
	}

	public void setSaveConfirm(boolean saveConfirm) {
		this.saveConfirm = saveConfirm;
	}
	

	public Map<String, Boolean> getCheckAmountApportion() {
		return checkAmountApportion;
	}

	public void setCheckAmountApportion(Map<String, Boolean> checkAmountApportion) {
		this.checkAmountApportion = checkAmountApportion;
	}

	public Map<String, BigDecimal> getSalableTaxPrice() {
		return salableTaxPrice;
	}

	public void setSalableTaxPrice(Map<String, BigDecimal> salableTaxPrice) {
		this.salableTaxPrice = salableTaxPrice;
	}
	
	
	public SalesPurchaseDashboradModel getSales() {
		return sales;
	}

	public void setSales(SalesPurchaseDashboradModel sales) {
		this.sales = sales;
	}

	public LazyDataModel<SalesorderModel> getSalesorderList() {
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{		
		this.setSalesorderModel(null);	
		salesorderModel = new SalesorderModelPagingHelper(salesOrderNo,customerId,branchId, dateFrom, dateTo,status,salesOrderBO,loginBean.getBranch().getBranchId(),null , null , null);		
		this.setSalesorderModel(salesorderModel);	
		}
		catch(Exception e){					
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"salesPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
			}
		return salesorderModel;
	}
	
	
	public LazyDataModel<SalesorderModel> getPosSalesorderList() {
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{		
			
		
			
		this.setSalesorderModel(null);	
		salesorderModel = new SalesorderModelPagingHelper(salesOrderNo,customerId,branchId, dateFrom, dateTo,status,salesOrderBO,loginBean.getBranch().getBranchId(),"3" , this.getSalesorder().getPaymentType(),this.getSalesorder().getSalesRep() );		
		this.setSalesorderModel(salesorderModel);	
		
		}
		catch(Exception e){					
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"salesPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
			}
		return salesorderModel;
	}
	
	public void dailyInvoiceList(ActionEvent event) {
		SalesorderModel so=new SalesorderModel();
		so = (SalesorderModel) event.getComponent().getAttributes().get("sales");
		Date date=so.getCreatedDate();
	
		dateFrom=new Date();
		dateFrom.setTime(date.getTime());
		dateFrom.setHours(00);
		dateFrom.setMinutes(00);
		dateFrom.setSeconds(00);
		
		dateTo=new Date();
		dateTo.setTime(date.getTime());
		dateTo.setHours(23);
		dateTo.setMinutes(59);
		dateTo.setSeconds(59);

		getPosSalesorderList();
		searchSalesOrderSummary();
		

		 this.getSalesorder().setPaymentType(null);
		 this.getSalesorder().setSalesRep(null);
		 
		 
		
	}
	

	public void searchPosSalesOrder()
	{
		SalesorderBeanInfo salesorderBeanInfo = new SalesorderBeanInfo();
		try
		{   if(!salesorderBeanInfo.validateSalesOrderSearch())
		{
			this.setDateFrom(DateUtil.getFromTodayDateTime());
			this.setDateTo(DateUtil.getToTodayDateTime());
		}
			this.setSearchBy("salesOrder");
			this.getPosSalesorderList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
	
	public void searchSalesOrder()
	{
		SalesorderBeanInfo salesorderBeanInfo = new SalesorderBeanInfo();
		try
		{   if(!salesorderBeanInfo.validateSalesOrderSearch())
		{
			this.setDateFrom(DateUtil.getFromTodayDateTime());
			this.setDateTo(DateUtil.getToTodayDateTime());
		}
			this.getSalesorderList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	


	public void searchSalesOrderSummary()
	{
		
		totalAmount=new BigDecimal("0.00");
		totalTaxAmount=new BigDecimal("0.00");
		salesorderlist.clear();
		
		SalesorderBeanInfo salesorderBeanInfo = new SalesorderBeanInfo();
		try
		{   if(!salesorderBeanInfo.validateSalesOrderSearch())
		{
			this.setDateFrom(DateUtil.getFromTodayDateTime());
			this.setDateTo(DateUtil.getToTodayDateTime());
		}
		salesorderlist=salesOrderBO.getSalesorderReportList(null, null, null, dateFrom, dateTo, null, loginBean.getBranch().getBranchId(), this.getSalesorder().getPaymentType(),this.getSalesorder().getSalesRep());
		
		for(SalesorderModel data:salesorderlist)
		{
			totalAmount=totalAmount.add(data.getTotalAmount());
			totalTaxAmount=totalTaxAmount.add(data.getTotalTax());
			
			
		}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
	
	
	
	
	
	public void resetSalesOrderSummary()
	{
		this.setCustomerId(0);
		this.setBranchId(0);
		this.setDateFrom(DateUtil.getFromTodayDateTime());
		this.setDateTo(DateUtil.getToTodayDateTime());
		this.setStatus(null);
		this.setSalesOrderNo(null);
		searchSalesOrderSummary();
	}
	
	
	
	public void searchSalesOrderSummaryowner()
	{
		SalesorderBeanInfo salesorderBeanInfo = new SalesorderBeanInfo();
		try
		{   if(!salesorderBeanInfo.validateSalesOrderSearch())
		{
			this.setDateFrom(DateUtil.getFromTodayDateTime());
			this.setDateTo(DateUtil.getToTodayDateTime());
		}
		salesorderlist=salesOrderBO.getSalesorderReportListowner(null, null, null, dateFrom, dateTo, null, loginBean.getBranch().getBranchId());
		
		for(SalesorderModel data:salesorderlist)
		{
			totalAmount=totalAmount.add(data.getTotalAmount());
			totalTaxAmount=totalTaxAmount.add(data.getTotalTax());
			
		}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
	
	
	
	
	
	public void resetSalesOrderSummaryowner()
	{
		this.setCustomerId(0);
		this.setBranchId(0);
		this.setDateFrom(DateUtil.getFromTodayDateTime());
		this.setDateTo(DateUtil.getToTodayDateTime());
		this.setStatus(null);
		this.setSalesOrderNo(null);
		searchSalesOrderSummaryowner();
	}
	
	
	
	
	public void resetSalesOrderSearch()
	{
		this.setCustomerId(0);
		this.setBranchId(0);
		this.setDateFrom(DateUtil.getFromTodayDateTime());
		this.setDateTo(DateUtil.getToTodayDateTime());
		this.setStatus(null);
		this.setSalesOrderNo(null);
		searchSalesOrder();
	}
	
	public void listSalesOrder()
	{
		SalesorderBeanInfo salesorderBeanInfo = new SalesorderBeanInfo();
		FacesContext context = FacesContext.getCurrentInstance();	
		try {
			salesorderBeanInfo.listSalesOrder();
			}
		catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
		}	
	
	public void newSalesOrder()
	{
		SalesorderBeanInfo salesorderBeanInfo = new SalesorderBeanInfo();
		FacesContext context = FacesContext.getCurrentInstance();	
		try {
			salesorderBeanInfo.newSalesOrder();
			changeSalesType();
			}
		catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
		}	

	
	public void saveSalesOrder()
	{
		SalesorderBeanInfo salesorderBeanInfo = new SalesorderBeanInfo();
		
		FacesContext context = FacesContext.getCurrentInstance();	
		try {
			salesorderBeanInfo.saveSalesOrder(salesorder);
			}
		catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
		}
	
	
	public void validateSalesOrder()
	{
		boolean valid = true;		
		
		SalesorderBean salesorderBean = (SalesorderBean) BeanContext.getReference("salesorderBean");
		if(salesorderBean.getTotalAmount()!=null)
		{
			if(salesorderBean.getTotalAmount().doubleValue()==0.00)
			{
				salesorderBean.setSaveConfirm(false);
			}
			else
			{
				salesorderBean.setSaveConfirm(true);
			}
		}		
	}
	
	
	public void updateSalesOrder()
	{
		SalesorderBeanInfo salesorderBeanInfo = new SalesorderBeanInfo();
		FacesContext context = FacesContext.getCurrentInstance();	
		try {
			salesorderBeanInfo.updateSalesOrder(salesorder);
			}
		catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
		}
	
	
	public void changeSalesType()
	{
		selectGrnList.clear();
		salableQuantity.clear();	
		selectQuotationList.clear();
		checkAmountApportion.clear();
		deliveryorder = new DeliveryorderModel();
		quotation = new QuotationModel();
		this.setDeliveryOrderId(0);
		this.setQuotationId(0);
		this.setBranchId(0);
	}
	
	public void resetSalesPriceQuantity()
	{
		salableQuantity.clear();	
		salablePrice.clear();
		checkAmountApportion.clear();
		this.setTotalAmount(new BigDecimal("0.00"));
		deliveryorder = new DeliveryorderModel();
		quotation = new QuotationModel();
	}	
	
	public void annualSalesOrderCategoryBased(ActionEvent event )throws Exception {
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {		
			this.sales = (SalesPurchaseDashboradModel) event.getComponent().getAttributes().get("sales");		
			
			this.setDateFrom(sales.getStartDate());
			this.setDateTo(sales.getEndDate());
			
		
				searchSalesOrderSummaryCategoryBased();
		
			ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");			
			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();						
			projectHome.setTitlePage("Sales --> Sales Order Summary (Category Based)");
			projectHome.setContentpage("/reports/salesItemReport/salesOrderSummaryCategoryBased.xhtml");					
			homeinfo.menuPageRedirect();
			
		}catch (Exception e) {
			e.printStackTrace();
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
					throw e;
					
		}	
	}
	
	public void searchAnnualSalesItemSummary() {
		totalAmount=new BigDecimal("0.00");
		totalTaxAmount=new BigDecimal("0.00");
		try
		{ 
			 Calendar cal = Calendar.getInstance();
			 cal.set(this.getAnnualSalesItemSummaryYear(), 0, 1, 0, 0,0);
			 dateFrom=new Date();
			 dateFrom=cal.getTime();
			 
			 cal.set(Calendar.MONTH,11);
			 cal.set(Calendar.DATE,31);
			 cal.set(Calendar.HOUR,23);
			 cal.set(Calendar.MINUTE,59);
			 cal.set(Calendar.SECOND,59);
			 dateTo=new Date();
			 dateTo=cal.getTime();
			 
			
			salesorderbreakdowns=salesOrderBO.getOrderRerportListCategoryBased(dateFrom,dateTo);
			if(categoryReport!=null) {
				categoryReport.clear();

			}

			for (SalesorderbreakdownModel data : salesorderbreakdowns) {
				
				totalAmount=totalAmount.add(data.getUnitPrice());
				totalTaxAmount=totalTaxAmount.add(data.getTaxAmount());
				
				SalesorderModel so=new SalesorderModel();
				so=salesOrderBO.getSalesorderDetailsCategoryBased(dateFrom,dateTo,data.getProduct().getCategoryId());

				categoryReport.add(so);
			}	
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void searchSalesOrderSummaryCategoryDateBased(){

		//salesorderlist.clear();
	//moo
		totalAmount=new BigDecimal("0.00");
		totalTaxAmount=new BigDecimal("0.00");
		try
		{ 

			if(dateFrom==null) {
				this.setDateFrom(DateUtil.getFromTodayDateTime());
				
			}
			if(dateTo==null) {
				this.setDateTo(DateUtil.getToTodayDateTime());
			}
			
			salesorderbreakdowns=salesOrderBO.getOrderRerportListCategoryBased(dateFrom,dateTo);
			if(categoryReport!=null) {
				categoryReport.clear();

			}
			for (SalesorderbreakdownModel data : salesorderbreakdowns) {
				
				totalAmount=totalAmount.add(data.getUnitPrice());
				totalTaxAmount=totalTaxAmount.add(data.getTaxAmount());
				
				SalesorderModel so=new SalesorderModel();
				so=salesOrderBO.getSalesorderDetailsCategoryBased(dateFrom,dateTo,data.getProduct().getCategoryId());

				categoryReport.add(so);
				
			}	
			
			
			this.setSearchBy("salesCategory");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void searchSalesOrderSummaryCategoryBased(){

		//salesorderlist.clear();
	//moo
		totalAmount=new BigDecimal("0.00");
		totalTaxAmount=new BigDecimal("0.00");
		try
		{ 
			
			salesorderbreakdowns=salesOrderBO.getOrderRerportListCategoryBased(dateFrom,dateTo);
			//salesOrderBO.getCategoryReportWithItems(dateFrom,dateTo);
			if(categoryReport!=null) {
				categoryReport.clear();	
			}
			
			for (SalesorderbreakdownModel data : salesorderbreakdowns) {
				
				totalAmount=totalAmount.add(data.getUnitPrice());
				totalTaxAmount=totalTaxAmount.add(data.getTaxAmount());
				
				SalesorderModel so=new SalesorderModel();
				so=salesOrderBO.getSalesorderDetailsCategoryBased(dateFrom,dateTo,data.getProduct().getCategoryId());
				categoryReport.add(so);
			}
			this.setSearchBy("salesCategory");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void annualSalesOrder(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	try {		
		this.sales = (SalesPurchaseDashboradModel) event.getComponent().getAttributes().get("sales");		
		
		this.setDateFrom(sales.getStartDate());
		this.setDateTo(sales.getEndDate());
		
		if(loginBean.getLogdetail().getDesignation().equalsIgnoreCase("owner"))
		{
			searchSalesOrderSummaryowner();
		}
		else
		{
			searchSalesOrderSummary();
		}
		
		
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");			
		ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();						
		projectHome.setTitlePage("Sales --> Sales Order Summary");
		projectHome.setContentpage("/sales/salesOrdersummary.xhtml");					
		homeinfo.menuPageRedirect();
		
		
		//projectHome.menusalesOrderSummary();
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}	
	
	public void dailySalesReport() {
		try {
			if(dailyReportDateFrom==null) {
				this.setDailyReportDateFrom(DateUtil.getFromTodayDateTime());
				
			}
			if(dailyReportDateTo==null) {
				this.setDailyReportDateTo(DateUtil.getToTodayDateTime());
			}else {
				dailyReportDateTo.setHours(23);
				dailyReportDateTo.setMinutes(59);
				dailyReportDateTo.setSeconds(59);
			}
			
			this.salesReport=salesOrderBO.getDailySales(this.dailyReportDateFrom, this.dailyReportDateTo,loginBean.getBranch().getBranchId() , this.getSalesorder().getPaymentType(),this.getSalesorder().getSalesRep() );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void viewSalesOrderCategoryBased(ActionEvent event)throws Exception{
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		
		try {		
			sobreakdownmodel = (SalesorderbreakdownModel) event.getComponent().getAttributes().get("sales");
			
			
			//salesorder = (SalesorderModel) event.getComponent().getAttributes().get("sales");	
			
			salesorder=salesOrderBO.getSalesorderDetailsCategoryBased(dateFrom,dateTo,sobreakdownmodel.getProduct().getCategoryId() );
				
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
			}
	}
	
	
	
	
	public void viewSalesOrder(ActionEvent event) throws Exception 
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try {		
			salesorder = (SalesorderModel) event.getComponent().getAttributes().get("sales");	
			
			if(loginBean.getLogdetail().getDesignation().equalsIgnoreCase("owner"))
			{
				salesorder=salesOrderBO.getBranchSalesItemDetails(salesorder.getSalesOrderId() ,salesorder.getSalesType() );
			}
			else
			{
				salesorder=salesOrderBO.getBranchSalesorderDetails(salesorder.getSalesOrderId() ,salesorder.getSalesType());
			}
			
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
			}
	}	
	
	
	public void editSalesOrder(ActionEvent event) throws Exception 
	{	
		FacesContext context = FacesContext.getCurrentInstance();
		SalesorderBeanInfo salesorderBeanInfo = new SalesorderBeanInfo();
		try {
			
			salableQuantity.clear();
			salesorder = (SalesorderModel) event.getComponent().getAttributes().get("sales");		
			salesorder=salesOrderBO.getSalesorderDetails(salesorder.getSalesOrderId() ,salesorder.getSalesType());
			
			if(salesorder.getSalesType().equalsIgnoreCase("1"))
			{
			//List<DeliveryorderModel> deliveryorderList=deliveryOrderBO.getDeliveryorderList(null,salesorder.getDeliveryOrderNo(),null, null, null, null, null, 0, 1,null);
			//deliveryorder=deliveryorderList.get(0);
			}
			else
			{
			//List<QuotationModel> quotationList=quotationBO.getQuotationList(null,salesorder.getQuoteNo(), null, null, null, null, 0, 1,null);
			//quotation=quotationList.get(0);
			}
			
			for (SalesorderbreakdownModel data : salesorder.getSalesorderbreakdowns()) {
				salableQuantity.put(data.getId(),String.valueOf(data.getQuantity()));
				salablePrice.put(data.getId(),data.getSubTotal());
				salableTaxPrice.put(data.getId(),data.getTaxAmount());
			}	
			this.setTotalAmount(salesorder.getTotalAmount());
			
			salesorderBeanInfo.editSalesOrder();
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
			}
	}
	
	
	public void approveSalesOrder(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	try {
				
		salesorder = (SalesorderModel) event.getComponent().getAttributes().get("sales");		
		salesorder=salesOrderBO.getSalesorderDetails(salesorder.getSalesOrderId() ,salesorder.getSalesType());
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	
	public void viewSalesOrderDetailPrint(Integer salesorderId) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	
	try {	
		salesorder=salesOrderBO.getSalesorderDetails(salesorderId ,"3");
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	
	public void approveSalesOrderConfirm()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		boolean updateSuccess=false;
		try {
			salesorder.setStatus(config.getValue(IConfiguration.SALESORDER_STATUS_PROCESSED_VALUE));				
			salesorder.setBranchtype(loginBean.getBranch().getBranchtype());
			salesorder.setBranchRecordId(loginBean.getBranch().getBranchId());			
			
			if(salesorder.getSalesType().equalsIgnoreCase("1"))
			{
			//List<DeliveryorderModel> deliveryorderList=deliveryOrderBO.getDeliveryorderList(null,salesorder.getDeliveryOrderNo(),null, null, null, null, null, 0, 1,null);
			//deliveryorder=deliveryorderList.get(0);
			}
			else
			{
			//List<QuotationModel> quotationList=quotationBO.getQuotationList(null,salesorder.getQuoteNo(), null, null, null, null, 0, 1,null);
			//quotation=quotationList.get(0);
			}
			
			updateSuccess=salesOrderBO.approveSalesorder(salesorder,deliveryorder,quotation,loginBean.getBranch().getBranchId());
			
			if (updateSuccess) {
				this.searchSalesOrder();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("salesorder.label.approved.success"),null));				
			}
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}
		
	
	
	public void loadGRNListByBranch()
	{
		List<DeliveryorderModel> dataList=null;
		selectGrnList.clear();
		resetSalesPriceQuantity();
		FacesContext context = FacesContext.getCurrentInstance();	
		try {
			if(this.getBranchId()!=null && this.getBranchId()!=0)
			{
		
		}
		}
		catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
		}
	
	
	public void loadDeliveryOrderItem()
	{
		FacesContext context = FacesContext.getCurrentInstance();	
		deliveryorder = new DeliveryorderModel();
		List<Integer> branchIds = new ArrayList<Integer>();
		List<DeliveryorderbreakdownModel> deliverydataList = new ArrayList<DeliveryorderbreakdownModel>();
		resetSalesPriceQuantity();		
		try {
			if(this.getBranchId()!=null && this.getBranchId()!=0)
			{
			branchIds.add(this.getBranchId());
			
			
			deliveryorder.setDeliveryorderbreakdowns(deliverydataList);
			}
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}
	
	
	public List<CustomerModel> getCustomerName(String customerString) {
		List<CustomerModel> results = new ArrayList<CustomerModel>();
		CommonListBeanInfo commoninfo = new CommonListBeanInfo();
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			results = commoninfo.getAllCustomerList(customerString);
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
		return results;
	}
	
	public void customerSelect(SelectEvent event) {  		
	
		FacesContext context = FacesContext.getCurrentInstance();
		this.setCustomerId(this.getCustomer().getCustomerId());
		context.getPartialViewContext().getRenderIds().add("reportForm1:customerId1");
	}
	
	
	public void loadQuotationItem()
	{
		FacesContext context = FacesContext.getCurrentInstance();	
		quotation = new QuotationModel();		
		resetSalesPriceQuantity();
		try {
			if(this.getQuotationId()!=null && this.getQuotationId()!=0)
			{		
			//quotation=quotationBO.getQuotationDetails(this.getQuotationId());	
			}
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}
	
	
	public void handleSelect(SelectEvent event) {  
		
		List<QuotationModel> dataList=null;	
		selectQuotationList.clear();
		resetSalesPriceQuantity();
		FacesContext context = FacesContext.getCurrentInstance();	
		try {	
			if(this.getCustomer()!=null)
			{
		//dataList=quotationBO.getQuotationByCuctomerList(this.getCustomer().getCustomerId(), "2",loginBean.getBranch().getBranchId());
			}
		for(QuotationModel branchIdTemp:dataList)
		{			
			selectQuotationList.add(new SelectItem(branchIdTemp.getQuotationId(),branchIdTemp.getQuotationNo()));
		}		
		}
		catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
	}
	
	
	public void validateBranchQuantity(Integer rowId)
	{
		FacesContext context = FacesContext.getCurrentInstance();	
		CommonListBeanInfo commonListBeanInfo = new CommonListBeanInfo();
		Integer payableQuantity = 0;			
		DeliveryorderbreakdownModel c = deliveryorder.getDeliveryorderbreakdowns().get(rowId);
		Integer receivableQuantity=0;
		try {
			if (ValidatorUtil.isNotNull(this.getSalableQuantity().get(c.getId()))) 
			{
				payableQuantity = new Integer(this.getSalableQuantity().get(c.getId()));
			}	
				if(c.getBalanceQuantity().intValue()<payableQuantity)
				{
					context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please Check the Sales Quantity should be less than Balance Quantity", null));
					this.salableQuantity.put(c.getId(),String.valueOf("0"));
					this.salablePrice.put(c.getId(),new BigDecimal(String.valueOf("0.00")));
					this.salableTaxPrice.put(c.getId(),new BigDecimal(String.valueOf("0.00")));	
				}				
				else
				{
					
					ProductModel p=productBo.getProductDetails(c.getProductId(), loginBean.getBranch().getBranchId());
					BigDecimal priceAmt=extractSalesSubtotal(c,rowId,p.getTaxCode());	
					this.salablePrice.put(c.getId(),priceAmt);
					priceAmt = c.getUnitPrice().multiply(new BigDecimal(this.getSalableQuantity().get(c.getId())));	
					BigDecimal taxAmt = p.getTaxCode().multiply((priceAmt.divide(new BigDecimal(100))));					
					this.salableTaxPrice.put(c.getId(),taxAmt);					
					
					extractSalesTotal();					
				}
				
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}
	
	public void selectAddRemoveAmount(Integer rowId){	
		
		DeliveryorderbreakdownModel c = deliveryorder.getDeliveryorderbreakdowns().get(rowId);	
		
			if (ValidatorUtil.isNotNull(this.getCheckAmountApportion().get(c.getId()))) 
			{
				if(this.getCheckAmountApportion().get(c.getId()))
				{			
					this.salableQuantity.put(c.getId(),String.valueOf(c.getBalanceQuantity()));
					validateBranchQuantity(rowId);
					
				}
				else
				{
					this.salableQuantity.put(c.getId(),String.valueOf("0"));
					this.salablePrice.put(c.getId(),new BigDecimal(String.valueOf("0.00")));
					this.salableTaxPrice.put(c.getId(),new BigDecimal(String.valueOf("0.00")));	
					extractSalesTotal();
				}
			}
		}

		public void selectAddRemoveQAmount(Integer rowId){	
		
			QuotationbreakdownModel c = quotation.getQuotationbreakdowns().get(rowId);	
		
			if (ValidatorUtil.isNotNull(this.getCheckAmountApportion().get(c.getId()))) 
			{
				if(this.getCheckAmountApportion().get(c.getId()))
				{			
					this.salableQuantity.put(c.getId(),String.valueOf(c.getBalanceQuantity()));
					validateQuotaionQuantity(rowId);
					
				}
				else
				{
					this.salableQuantity.put(c.getId(),String.valueOf("0"));
					this.salablePrice.put(c.getId(),new BigDecimal(String.valueOf("0.00")));
					this.salableTaxPrice.put(c.getId(),new BigDecimal(String.valueOf("0.00")));					
					extractQuoteTotal();
				}
			}
		}
	
	
	public void validateQuotaionQuantity(Integer rowId)
	{
		FacesContext context = FacesContext.getCurrentInstance();	
		Integer balanceQuantity = 0;			
		QuotationbreakdownModel c = quotation.getQuotationbreakdowns().get(rowId);
		BigDecimal payableQuantity =new BigDecimal(0.00);
		try {
			if (ValidatorUtil.isNotNull(this.getSalableQuantity().get(c.getId()))) 
			{
				payableQuantity = new BigDecimal(this.getSalableQuantity().get(c.getId()));
			}	
				if(c.getBalanceQuantity().doubleValue()<payableQuantity.doubleValue())
				{
					context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please Check the Sales Quantity should be less than Balance Quantity", null));
					this.salableQuantity.put(c.getId(),String.valueOf("0"));
					this.salablePrice.put(c.getId(),new BigDecimal(String.valueOf("0.00")));
					this.salableTaxPrice.put(c.getId(),new BigDecimal(String.valueOf("0.00")));
				}
				else
				{
					BigDecimal priceAmt=extractSubtotal(c);			
					this.salablePrice.put(c.getId(),priceAmt);
					priceAmt = c.getUnitPrice().multiply(new BigDecimal(this.getSalableQuantity().get(c.getId())));	
					BigDecimal taxAmt = c.getTaxCode().multiply((priceAmt.divide(new BigDecimal(100))));					
					this.salableTaxPrice.put(c.getId(),taxAmt);
					extractQuoteTotal();
				}
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}
	
	
		
	
	
	public BigDecimal extractSubtotal(QuotationbreakdownModel c) {			
		   
		    BigDecimal priceAmt = new BigDecimal("0.00");
		    if(this.getSalableQuantity().get(c.getId())!=null)
			{		   	
			priceAmt = c.getUnitPrice().multiply(new BigDecimal(this.getSalableQuantity().get(c.getId())));	
			BigDecimal taxAmt = c.getTaxCode().multiply((priceAmt.divide(new BigDecimal(100))));
			priceAmt=priceAmt.add(taxAmt);
			priceAmt = priceAmt.subtract(c.getDiscountAmount());
			}
			return priceAmt;
		}	
	
	public void extractQuoteTotal() {
		FacesContext context = FacesContext.getCurrentInstance();	
		try
		{
	    BigDecimal totalAmount =new BigDecimal(0.00);	  
		for(QuotationbreakdownModel item:quotation.getQuotationbreakdowns())
		{	
			if(this.getSalablePrice().get(item.getId())!=null)
			{
			totalAmount=totalAmount.add(new BigDecimal(""+this.getSalablePrice().get(item.getId())));	
			}
		}
		this.setTotalAmount(DecimalUtil.formatRoundupCents(totalAmount));	
		}
		catch(Exception e)
		{
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"salesPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
	}	
	
	
	public BigDecimal extractSalesSubtotal(DeliveryorderbreakdownModel c,Integer rowId,BigDecimal taxCode) {		    
	    
	    CommonListBeanInfo commonListBeanInfo = new CommonListBeanInfo(); 
	    BigDecimal priceAmt = new BigDecimal("0.00");
	    if(this.getSalableQuantity().get(c.getId())!=null)
		{
	    	 BigDecimal salableQuantityValues = new BigDecimal("0.00");
	    	 salableQuantityValues = new BigDecimal(this.getSalableQuantity().get(c.getId()));
	         c=commonListBeanInfo.getSalesPrice(c, salableQuantityValues);
	    
	    //BigDecimal originalPrice = c.getUnitPrice().subtract(c.getDiscountAmount());	    
		//priceAmt = originalPrice.multiply(new BigDecimal(this.getSalableQuantity().get(c.getId())));
		
		priceAmt = c.getUnitPrice().multiply(new BigDecimal(this.getSalableQuantity().get(c.getId())));	
		BigDecimal taxAmt = taxCode.multiply((priceAmt.divide(new BigDecimal(100))));
		priceAmt=priceAmt.add(taxAmt);
		priceAmt = priceAmt.subtract(c.getDiscountAmount());
		
		DeliveryorderbreakdownModel c1 = deliveryorder.getDeliveryorderbreakdowns().get(rowId);
		c1.setDiscountAmount(c.getDiscountAmount());
		
		
		
		
		deliveryorder.getDeliveryorderbreakdowns().set(rowId, c1);
		}
		return priceAmt;
	}	

	public void extractSalesTotal() {
		FacesContext context = FacesContext.getCurrentInstance();	
		try
		{
    BigDecimal totalAmount =new BigDecimal(0.00);	  
	for(DeliveryorderbreakdownModel item:deliveryorder.getDeliveryorderbreakdowns())
	{	
		if(this.getSalablePrice().get(item.getId())!=null)
		{
		totalAmount=totalAmount.add(new BigDecimal(""+this.getSalablePrice().get(item.getId())));	
		}
	}
	this.setTotalAmount(DecimalUtil.formatRoundupCents(totalAmount));
		}
		catch(Exception e)
		{
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"salesPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
	
	}
	
	public LazyDataModel<SalesorderbreakdownModel> getSalesorderbreakdownList() {
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{		
		this.setSalesorderbreakdownModel(null);	
		salesorderbreakdownModel = new SalesProductHistoryPagingHelper( dateFrom, dateTo,salesOrderBO,loginBean.getBranch().getBranchId());		
		this.setSalesorderbreakdownModel(salesorderbreakdownModel);	
		}
		catch(Exception e){					
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"salesPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
			}
		return salesorderbreakdownModel;
	}
	
	public void searchSalesorderbreakdown()
	{
		try
		{
			if(this.getDateFrom()==null && this.getDateTo()==null )
			{
				this.setDateFrom(DateUtil.getFromTodayDateTime());
				this.setDateTo(DateUtil.getToTodayDateTime());
			}
			this.getSalesorderbreakdownList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
	
	public void resetSalesHistory()
	{
		this.setDateFrom(DateUtil.getFromTodayDateTime());
		this.setDateTo(DateUtil.getToTodayDateTime());
		searchSalesorderbreakdown();
	}
	
	public void validateEditQuantity(Integer rowId)
	{
		FacesContext context = FacesContext.getCurrentInstance();	
		Integer balanceQuantity = 0;			
		SalesorderbreakdownModel c = salesorder.getSalesorderbreakdowns().get(rowId);
		BigDecimal payableQuantity= new BigDecimal(0.00);
		try {
			if (ValidatorUtil.isNotNull(this.getSalableQuantity().get(c.getId()))) 
			{
				payableQuantity = new BigDecimal(this.getSalableQuantity().get(c.getId()));
			}	
				if(c.getBalanceEditQuantity().doubleValue()<payableQuantity.doubleValue())
				{
					context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please Check the Sales Quantity should be less than Balance Quantity", null));
					this.salableQuantity.put(c.getId(),String.valueOf("0"));
					this.salablePrice.put(c.getId(),new BigDecimal(String.valueOf("0.00")));
					this.salableTaxPrice.put(c.getId(),new BigDecimal(String.valueOf("0.00")));		
				}
				else
				{
					BigDecimal priceAmt=extractEditSubtotal(c);
					this.salablePrice.put(c.getId(),priceAmt);
					
					if(c.getTaxCode()==null)
					{
						c.setTaxCode(new BigDecimal("0.00"));
					}
					priceAmt = c.getUnitPrice().multiply(new BigDecimal(this.getSalableQuantity().get(c.getId())));	
					BigDecimal taxAmt = c.getTaxCode().multiply((priceAmt.divide(new BigDecimal(100))));					
					this.salableTaxPrice.put(c.getId(),taxAmt);
					
					extractTotal();
				}
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}
	
	public BigDecimal extractEditSubtotal(SalesorderbreakdownModel c) {			
	    
	    BigDecimal priceAmt = new BigDecimal("0.00");
	    if(this.getSalableQuantity().get(c.getId())!=null)
		{		
	    	if(c.getTaxCode()==null)
			{
				c.setTaxCode(new BigDecimal("0.00"));
			}
		priceAmt = c.getUnitPrice().multiply(new BigDecimal(this.getSalableQuantity().get(c.getId())));	
		BigDecimal taxAmt = c.getTaxCode().multiply((priceAmt.divide(new BigDecimal(100))));
		priceAmt=priceAmt.add(taxAmt);
		priceAmt = priceAmt.subtract(c.getDiscountAmount());
		
		}
		return priceAmt;
	}	
	
	public void extractTotal() {
		FacesContext context = FacesContext.getCurrentInstance();	
		try
		{
	    BigDecimal totalAmount =new BigDecimal(0.00);	  
		for(SalesorderbreakdownModel item:salesorder.getSalesorderbreakdowns())
		{	
			if(this.getSalablePrice().get(item.getId())!=null)
			{
			totalAmount=totalAmount.add(new BigDecimal(""+this.getSalablePrice().get(item.getId())));	
			}
		}
		this.setTotalAmount(DecimalUtil.formatRoundupCents(totalAmount));		
		}
		catch(Exception e)
		{
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
	}	
	
	public void printSalesOrder(ActionEvent event) {
		
		FacesContext faces = FacesContext.getCurrentInstance();	
		try{				
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 			
		salesorder = (SalesorderModel) event.getComponent().getAttributes().get("sales");		
		//salesorder=salesOrderBO.getSalesorderDetails(salesorder.getSalesOrderId() ,salesorder.getSalesType());
		
		faces.getExternalContext().redirect(request.getScheme() + "://" + request.getServerName() +":" + request.getServerPort() +"/"+config.getValue(IConfiguration.PROJECT_INSTANCE_NAME)+"/frameset?__report=report/sales/salesDetailReport.rptdesign&__format=pdf&&__pageoverflow=0&__asattachment=true&__overwrite=false&salesOrderId=" + salesorder.getSalesOrderId()+"&salesType="+salesorder.getSalesType()+"&userId="+loginBean.getUserId() );
		faces.responseComplete();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void deleteSalesordernative() throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	try {		
		//salesorder = (SalesorderModel) event.getComponent().getAttributes().get("sales");	
		
		salesOrderBO.deleteSalesordernative(salesorder.getSalesOrderNo(), salesorder.getSalesOrderId(), loginBean.getBranch().getBranchId());
		searchPosSalesOrder();
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}	
	
	public void printAlertLongList(ActionEvent event) {
		
		Integer printList=(Integer) event.getComponent().getAttributes().get("maxPrintListSize");
		Integer printListSize=(Integer) event.getComponent().getAttributes().get("maxPrintListSize");
		String printDlgId=(String) event.getComponent().getAttributes().get("printDlgId");
		if(printListSize > printList) {
			RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Please select the Month"));
		}else {
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('"+printDlgId+"').show();");
		}
		
	}
	
}
