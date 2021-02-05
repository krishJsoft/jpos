package com.project.bean.sales.sale;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.LazyDataModel;

import com.project.bo.interfaces.ISalesorderBO;
import com.project.model.datamodel.CustomerModel;
import com.project.model.datamodel.ProductModel;
import com.project.model.invoice.supplier.SupplierinvoiceModel;
import com.project.model.sale.sales.BranchSalesModel;
import com.project.model.sale.sales.BranchSalesSummaryModel;
import com.project.model.sale.sales.SalesTotalModel;
import com.project.model.sale.sales.SalesorderbreakdownModel;
import com.project.model.sale.sales.SalesorderreturnModel;
import com.project.util.DateUtil;
import com.project.bean.admin.CommonListBean;
import com.project.bean.admin.CommonListBeanInfo;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.util.ObjectMapController;
import com.project.common.util.Words;
import com.project.common.validation.UIComponent;
import com.project.login.LoginBean;
import com.project.model.paginghelper.BranchSalesSummaryPagingHelper;
import com.project.model.paginghelper.CustomerSalesSummaryDetailPagingHelper;
import com.project.model.paginghelper.CustomerSalesSummaryPagingHelper;

/*
 * @author Gopal Ar
 * @version 1.0
 * @since 25 Sep 2013
 * 
 */

@ManagedBean(name = "salesSummaryBean")
@SessionScoped
public class SalesSummaryBean {

	List<BranchSalesSummaryModel> branchSalesSummary = new ArrayList<BranchSalesSummaryModel>();	
	List<BranchSalesModel> branchSalesModel1 = new ArrayList<BranchSalesModel>();
	private LazyDataModel<BranchSalesModel> branchSalesModel = null;	
	private LazyDataModel<BranchSalesSummaryModel> branchSalesSummaryModel = null;	
	BranchSalesSummaryModel branchSalesSummaryDetail=new BranchSalesSummaryModel();
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");

	Configuration config = Configuration.getConfiguration();
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	private Integer branchId;
	private Integer customerId;
	private Integer supplierId;
	private Date dateFrom;
	private Date dateTo;
	private String status;
	private String branchName;	
	private String customerName;
	private String amountwords;
	String requestIdsbuf="";


	private List<String> selectedOptions=new ArrayList<String>();
	List<Integer> branchIds = new ArrayList<Integer>();
	List<Integer> customerIds = new ArrayList<Integer>();
	private CustomerModel customer;
	SalesTotalModel total = new SalesTotalModel();
	SalesTotalModel total1 = new SalesTotalModel();
	SalesTotalModel grandtotal = new SalesTotalModel();
	List<Integer> supplierIds = new ArrayList<Integer>();
	
	DataTable dataList = new DataTable();
	
	ISalesorderBO salesOrderBO=objectMapController.getSalesOrderBO();

	public ISalesorderBO getSalesOrderBO() {
		return salesOrderBO;
	}

	public void setSalesOrderBO(ISalesorderBO salesOrderBO) {
		this.salesOrderBO = salesOrderBO;
	}

	public List<BranchSalesSummaryModel> getBranchSalesSummary() {
		return branchSalesSummary;
	}

	public void setBranchSalesSummary(
			List<BranchSalesSummaryModel> branchSalesSummary) {
		this.branchSalesSummary = branchSalesSummary;
	}
	
	public BranchSalesSummaryModel getBranchSalesSummaryDetail() {
		return branchSalesSummaryDetail;
	}

	public void setBranchSalesSummaryDetail(
			BranchSalesSummaryModel branchSalesSummaryDetail) {
		this.branchSalesSummaryDetail = branchSalesSummaryDetail;
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

	public void setSupplierId(Integer supplierrId) {
		this.supplierId = supplierrId;
	}

	public String getRequestIdsbuf() {
		return requestIdsbuf;
	}

	public void setRequestIdsbuf(String requestIdsbuf) {
		this.requestIdsbuf = requestIdsbuf;
	}

	public List<String> getSelectedOptions() {
		return selectedOptions;
	}

	public void setSelectedOptions(List<String> selectedOptions) {
		this.selectedOptions = selectedOptions;
	}
	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
				
	
	public String getAmountwords() {
		return amountwords;
	}

	public void setAmountwords(String amountwords) {
		this.amountwords = amountwords;
	}

	public DataTable getDataList() {
		return dataList;
	}

	public void setDataList(DataTable dataList) {
		this.dataList = dataList;
	}

	public LazyDataModel<BranchSalesModel> getBranchSalesModel() {
		return branchSalesModel;
	}

	public void setBranchSalesModel(LazyDataModel<BranchSalesModel> branchSalesModel) {
		this.branchSalesModel = branchSalesModel;
	}	
	
	public LazyDataModel<BranchSalesSummaryModel> getBranchSalesSummaryModel() {
		return branchSalesSummaryModel;
	}

	public void setBranchSalesSummaryModel(
			LazyDataModel<BranchSalesSummaryModel> branchSalesSummaryModel) {
		this.branchSalesSummaryModel = branchSalesSummaryModel;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}	
	

	public List<BranchSalesModel> getBranchSalesModel1() {
		return branchSalesModel1;
	}

	public void setBranchSalesModel1(List<BranchSalesModel> branchSalesModel1) {
		this.branchSalesModel1 = branchSalesModel1;
	}

	public SalesTotalModel getTotal() {
		return total;
	}

	public void setTotal(SalesTotalModel total) {
		this.total = total;
	}	
	
	public SalesTotalModel getGrandtotal() {
		return grandtotal;
	}

	public void setGrandtotal(SalesTotalModel grandtotal) {
		this.grandtotal = grandtotal;
	}	
	
	public SalesTotalModel getTotal1() {
		return total1;
	}

	public void setTotal1(SalesTotalModel total1) {
		this.total1 = total1;
	}

	public List<Integer> getBranchIds() {			
		branchIds.clear();
		for (int i = 0; i < getSelectBranchList().size(); i++) {
			branchIds.add(Integer.parseInt(getSelectBranchList()
					.get(i).getValue().toString()));
		}		
		return branchIds;
	}

	public void setBranchIds(List<Integer> branchIds) {
		this.branchIds = branchIds;
	}
	

	public List<Integer> getSupplierIds() {
		
		supplierIds.clear();
		CommonListBean commonListBean = (CommonListBean) BeanContext.getReference("commonListBean");
		for (int i = 0; i < commonListBean.getSelectSupplierList().size(); i++) {
			supplierIds.add(Integer.parseInt(commonListBean.getSelectSupplierList().get(i).getValue().toString()));
		}		
		return supplierIds;
	}

	public void setSupplierIds(List<Integer> supplierIds) {
		this.supplierIds = supplierIds;
	}

	public List<Integer> getCustomerIds() {
		return customerIds;
	}

	public void setCustomerIds(List<Integer> customerIds) {
		this.customerIds = customerIds;
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
	
	private List<SelectItem> selectBranchList = new ArrayList<SelectItem>();

	public List<SelectItem> getSelectBranchList() {
		CommonListBean commonListBean = (CommonListBean) BeanContext.getReference("commonListBean");
		return selectBranchList=commonListBean.getSelectBranchList();
	}

	public void setSelectBranchList(List<SelectItem> selectBranchList) {
		this.selectBranchList = selectBranchList;
	}
	
	
	public CustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
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
	
	
	public void getBranchListReport()
	{
		StringBuilder requestIdsbufTemp = new StringBuilder("");
		String comma=",";
		int count=0;
		FacesContext context = FacesContext.getCurrentInstance();			
		if(selectedOptions.size()==0)
		{
			for(Integer Ids:this.getBranchIds())
			{
				selectedOptions.add(String.valueOf(Ids));
			}
		}
		for(String id:selectedOptions)
		{
			if(count==selectedOptions.size()-1)
			{
				requestIdsbufTemp.append(String.valueOf(id));
			}
			else
			{
				requestIdsbufTemp.append(String.valueOf(id).concat(comma));	
			}
			count=count+1;
		}		
		
		this.setRequestIdsbuf(requestIdsbufTemp.toString());	
		context.getPartialViewContext().getRenderIds().add("reportForm1:branchIds1");
	}
	
	public void searchBranchSalesSummary()
	{		
		FacesContext context = FacesContext.getCurrentInstance();	
		branchSalesSummaryDetail=new BranchSalesSummaryModel();
		validateSummarySearch();
		if(this.getBranchId()!=null && this.getBranchId()!=0)	
		{	
			branchIds.clear();
			branchIds.add(this.getBranchId());
			this.setBranchIds(branchIds);
		}
		else
		{
			branchIds=getBranchIds();
		}
		try {
			branchSalesSummary=salesOrderBO.getBranchSalesByBranch(branchIds, dateFrom, dateTo,loginBean.getBranch().getBranchId());
			getBranchSalesSubTotal();
			}
		catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
		
	}
	
	
	public void searchBranchSalesSummaryDetail()
	{		
		FacesContext context = FacesContext.getCurrentInstance();	
		try {
			branchSalesModel=new BranchSalesSummaryPagingHelper(branchId, dateFrom, dateTo,salesOrderBO,loginBean.getBranch().getBranchId());
			getBranchSalesGrandTotal();
			}
		catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
		
	}
	
	
	
	public void searchSupplierPurchaseSummary()
	{		
		FacesContext context = FacesContext.getCurrentInstance();	
		branchSalesSummaryDetail=new BranchSalesSummaryModel();
		validateSummarySearch();
		if(this.getSupplierId()!=null && this.getSupplierId()!=0)	
		{	
			supplierIds.clear();
			supplierIds.add(this.getBranchId());
			this.setSupplierIds(supplierIds);
		}
		else
		{
			supplierIds=getSupplierIds();
		}
		try {
			//branchSalesSummary=purchaseOrderBO.getBranchPurchaseBySupplier(supplierIds, dateFrom, dateTo,loginBean.getBranch().getBranchId());
			getBranchSalesSubTotal();
			
			 Words w=Words.getInstance(total.getNormalPriceTotal().longValue());
			this.setAmountwords(w.getNumberInWords());			  
			}
		catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
		
	}
	
	
	
	public void resetBranchSalesSummary()
	{
		this.setCustomerId(0);
		this.setBranchId(0);
		this.setDateFrom(DateUtil.getFromTodayDateTime());
		this.setDateTo(DateUtil.getToTodayDateTime());
		this.setStatus(null);	
		branchSalesSummary.clear();		
		this.setBranchSalesModel(null);	
		searchBranchSalesSummary();	
		total = new SalesTotalModel();
		total1 = new SalesTotalModel();
		grandtotal = new SalesTotalModel();
		
	}
	
	
	
	public void viewSalesSummaryDetail(ToggleEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	try {
		String branchId = "";
		String branchName="";
		String salesrow="";
		
		BranchSalesSummaryModel c = ((BranchSalesSummaryModel) event.getData());
		
		this.setBranchId(c.getBranchId());
		this.setBranchName(c.getBranchName());		
		searchBranchSalesSummaryDetail();		
		getGrandTotal(c);
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}		
	
	
	public LazyDataModel<BranchSalesSummaryModel> searchCustomerSalesSummary()
	{		
		FacesContext context = FacesContext.getCurrentInstance();	
		branchSalesSummaryDetail=new BranchSalesSummaryModel();
		validateSummarySearch();
		if(this.getCustomer()!=null && this.getCustomer().getCustomerId()!=0)	
		{	
			customerIds.clear();
			customerIds.add(this.getCustomer().getCustomerId());
			this.setBranchIds(customerIds);
		}
		else
		{
			customerIds=getCustomerIds();
		}
		try { 
			branchSalesSummaryModel=new CustomerSalesSummaryPagingHelper(customerIds,dateFrom,  dateTo,  salesOrderBO,loginBean.getBranch().getBranchId());
			getCustomerSalesSubTotal(branchSalesSummaryModel);
			}
		catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
		
		return branchSalesSummaryModel;
	}
	
	
	
	public void searchCustomerSalesSummaryDetail()
	{		
		FacesContext context = FacesContext.getCurrentInstance();			
		try {
			//branchSalesModel=new CustomerSalesSummaryDetailPagingHelper(customerId, dateFrom, dateTo, salesOrderBO,loginBean.getBranch().getBranchId());
			branchSalesModel1=salesOrderBO.getCustomerSalesByProductReport(customerId, dateFrom, dateTo, loginBean.getBranch().getBranchId());
			//getBranchSalesGrandTotal();
			}
		catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
		
	}
	
	
	public void searchSupplierPurchaseSummaryDetail(ToggleEvent event)
	{		
		FacesContext context = FacesContext.getCurrentInstance();			
		try {			
			BranchSalesSummaryModel c = ((BranchSalesSummaryModel) event.getData());			
			//this.setSupplierId(c.getSupplierId());						
			//branchSalesModel1=purchaseOrderBO.getSupplierPurchaseByProductReport(c.getSupplierId(), dateFrom, dateTo, loginBean.getBranch().getBranchId());
			getGrandTotal(c);	
			}
		catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
		
	}
	
	
	
	
	public void resetCustomerSalesSummary()
	{
		this.setCustomerId(0);
		this.setBranchId(0);
		this.setDateFrom(DateUtil.getFromTodayDateTime());
		this.setDateTo(DateUtil.getToTodayDateTime());
		this.setStatus(null);	
		branchSalesSummary.clear();			
		searchBranchSalesSummary();	
		this.setBranchSalesModel(null);	
		this.setBranchSalesSummaryModel(null);
		customer=new CustomerModel();
		total = new SalesTotalModel();
		total1 = new SalesTotalModel();
		grandtotal = new SalesTotalModel();
		
	}
	
	
	
	public void viewCustomerSalesSummaryDetail(ToggleEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	try {
		String customerId = "";
		String customerName="";
		String salesrow="";		
		BranchSalesSummaryModel c = ((BranchSalesSummaryModel) event.getData());
		this.setCustomerId(c.getCustomerId());
		this.setCustomerName(c.getCustomerName());		
		searchCustomerSalesSummaryDetail();		
		getGrandTotal(c);
		
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	public void validateSummarySearch()
	{
		if ((dateFrom == null) && (dateTo == null))
		{
			this.setDateFrom(DateUtil.getFromTodayDateTime());
			this.setDateTo(DateUtil.getToTodayDateTime());
		}		
	}
	
	public void getBranchSalesSubTotal()
	{
		 Integer totalQuantity=0;
		 BigDecimal normalPriceTotal = new BigDecimal("0.00");
		 BigDecimal purchasePriceTotal= new BigDecimal("0.00");
		 BigDecimal marginTotal= new BigDecimal("0.00");
		 BigDecimal margin= new BigDecimal("0.00");
		 total = new SalesTotalModel();
		for(BranchSalesSummaryModel data:branchSalesSummary)
		{
			totalQuantity=totalQuantity+data.getTotalQuantity();
			normalPriceTotal=normalPriceTotal.add(data.getNormalPriceTotal());
			//purchasePriceTotal=purchasePriceTotal.add(data.getPurchasePriceTotal());
			//marginTotal=marginTotal.add(data.getMarginTotal());
			//margin=margin.add(margin);			
		}
		
		total.setMargin(margin);
		total.setMarginTotal(marginTotal);
		total.setNormalPriceTotal(normalPriceTotal);
		total.setPurchasePriceTotal(purchasePriceTotal);
		total.setTotalQuantity(totalQuantity);
		this.setTotal(total);
		
	}	
	
	@SuppressWarnings("unchecked")
	public void getCustomerSalesSubTotal(LazyDataModel<BranchSalesSummaryModel> dataList1)
	{
		 Integer totalQuantity=0;
		 BigDecimal normalPriceTotal = new BigDecimal("0.00");
		 BigDecimal purchasePriceTotal= new BigDecimal("0.00");
		 BigDecimal marginTotal= new BigDecimal("0.00");
		 BigDecimal margin= new BigDecimal("0.00");
		 total = new SalesTotalModel();
			
		
	        for(BranchSalesSummaryModel data:dataList1) {	       	
	        	
	        	totalQuantity=totalQuantity+data.getTotalQuantity();
	  			normalPriceTotal=normalPriceTotal.add(data.getNormalPriceTotal());
	  			purchasePriceTotal=purchasePriceTotal.add(data.getPurchasePriceTotal());
	  			marginTotal=marginTotal.add(data.getMarginTotal());
	  			margin=margin.add(margin);
	          }
	     
		
		total.setMargin(margin);
		total.setMarginTotal(marginTotal);
		total.setNormalPriceTotal(normalPriceTotal);
		total.setPurchasePriceTotal(purchasePriceTotal);
		total.setTotalQuantity(totalQuantity);
		this.setTotal(total);
		
	}	
	
	
	
	public void getBranchSalesGrandTotal()
	{
		 Integer totalQuantity=0;
		 BigDecimal normalPriceTotal = new BigDecimal("0.00");
		 BigDecimal purchasePriceTotal= new BigDecimal("0.00");
		 BigDecimal marginTotal= new BigDecimal("0.00");
		 BigDecimal margin= new BigDecimal("0.00");
		 total1 = new SalesTotalModel();
		 
		 if(branchSalesModel!=null)
		 {
		for(BranchSalesModel data:branchSalesModel)
		{
			totalQuantity=totalQuantity+data.getTotalQuantity();
			normalPriceTotal=normalPriceTotal.add(data.getNormalPriceTotal());
			purchasePriceTotal=purchasePriceTotal.add(data.getPurchasePriceTotal());
			marginTotal=marginTotal.add(data.getMarginTotal());
			margin=margin.add(margin);			
		}
		 }
		total1.setMargin(margin);
		total1.setMarginTotal(marginTotal);
		total1.setNormalPriceTotal(normalPriceTotal);
		total1.setPurchasePriceTotal(purchasePriceTotal);
		total1.setTotalQuantity(totalQuantity);
		this.setTotal1(total1);
		
	}
	
	public void getGrandTotal(BranchSalesSummaryModel c)
	{
		 Integer totalQuantity=0;
		 BigDecimal normalPriceTotal = new BigDecimal("0.00");
		 BigDecimal purchasePriceTotal= new BigDecimal("0.00");
		 BigDecimal marginTotal= new BigDecimal("0.00");
		 BigDecimal margin= new BigDecimal("0.00");
		 grandtotal = new SalesTotalModel();
		
		totalQuantity=totalQuantity+c.getTotalQuantity();
		normalPriceTotal=normalPriceTotal.add(c.getNormalPriceTotal());
		purchasePriceTotal=purchasePriceTotal.add(c.getPurchasePriceTotal());
		marginTotal=marginTotal.add(c.getMarginTotal());
		margin=margin.add(margin);		
		
		
		grandtotal.setMargin(margin);
		grandtotal.setMarginTotal(marginTotal);
		grandtotal.setNormalPriceTotal(normalPriceTotal);
		grandtotal.setPurchasePriceTotal(purchasePriceTotal);
		grandtotal.setTotalQuantity(totalQuantity);
		this.setTotal1(grandtotal);
		
	}
	
	
	public void calculateTotalValue()
	{
		getBranchSalesGrandTotal();
	}
	
	
	public void printSalesSummary(ActionEvent event) {
		
		FacesContext faces = FacesContext.getCurrentInstance();	
		try{				
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 			
		//branchSalesModel = (BranchSalesModel) event.getComponent().getAttributes().get("sales");
		//faces.getExternalContext().redirect(request.getScheme() + "://" + request.getServerName() +":" + request.getServerPort() +"/"+config.getValue(IConfiguration.PROJECT_INSTANCE_NAME)+"/frameset?__report=/report/sales/returnDetailReport.rptdesign&__format=pdf&&__pageoverflow=0&__asattachment=true&__overwrite=false&resturnid=" + salesreturn.getSalesreturnid()+"&userId="+loginBean.getUserId());
		faces.responseComplete();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	
}
