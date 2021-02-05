package com.project.bean.invoice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.LazyDataModel;

import com.project.bo.interfaces.IAdminDespatchBO;
import com.project.bo.interfaces.IBranchinvoiceBO;
import com.project.bo.interfaces.IBranchsalesBO;
import com.project.bo.interfaces.ISalesorderBO;
import com.project.model.datamodel.AdmindespatchModel;
import com.project.model.datamodel.CustomerModel;
import com.project.model.invoice.branch.BranchinvoiceModel;
import com.project.model.invoice.branch.BranchinvoicebreakdownModel;
import com.project.model.sale.sales.SalesorderModel;
import com.project.model.sale.sales.branchsale.BranchsaleModel;
import com.project.bean.admin.AdminDespatchBean;
import com.project.bean.admin.CommonListBeanInfo;
import com.project.bean.sales.sale.SalesorderBeanInfo;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.util.DateUtil;
import com.project.common.util.DecimalUtil;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.UIComponent;
import com.project.login.LoginBean;
import com.project.model.paginghelper.BranchinvoicePagingHelper;

/*
 * @author Gopal Ar
 * @version 1.0
 * @since 10 Oct 2013
 * 
 */

@ManagedBean(name = "branchInvoiceBean")
@SessionScoped
public class BranchInvoiceBean {

	BranchinvoiceModel branchinvoice = new BranchinvoiceModel();
	List<BranchinvoicebreakdownModel> branchinvoicebreakdowns = new ArrayList<BranchinvoicebreakdownModel>();
	BranchinvoicebreakdownModel branchinvoicebreakdown = new BranchinvoicebreakdownModel();
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	AdminDespatchBean adminDespatchBean = (AdminDespatchBean) BeanContext.getReference("adminDespatchBean");
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	Configuration config = Configuration.getConfiguration();
	List<AdmindespatchModel> adespatchList = new ArrayList<AdmindespatchModel>();
	private LazyDataModel<BranchinvoiceModel> branchinvoiceModel = null;
	private LazyDataModel<BranchinvoicebreakdownModel> branchinvoicebreakdownModel = null;
	SalesorderModel salesorder = new SalesorderModel();
	BranchsaleModel branchsale = new BranchsaleModel();
	
	private Integer branchId;
	private Date dateFrom;
	private Date dateTo;
	private String status;
	private String salesOrderNo;
	private int branchInvoiceId;
	private String invoiceNo;
	private String action = "submit";
	private int activeIndex = 0;
	private CustomerModel customer;
	private Integer customerId;
	private String outstandingstatus="1,2";
	
	ISalesorderBO salesOrderBO=objectMapController.getSalesOrderBO();
	
	IBranchinvoiceBO branchinvoiceBO=objectMapController.getBranchinvoiceBO();	
	IBranchsalesBO branchsalesBO=objectMapController.getBranchsalesBO();
	
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
	

	public IBranchsalesBO getBranchsalesBO() {
		return branchsalesBO;
	}

	public void setBranchsalesBO(IBranchsalesBO branchsalesBO) {
		this.branchsalesBO = branchsalesBO;
	}

	public BranchinvoiceModel getBranchinvoice() {
		return branchinvoice;
	}

	public void setBranchinvoice(BranchinvoiceModel branchinvoice) {
		this.branchinvoice = branchinvoice;
	}

	public List<BranchinvoicebreakdownModel> getBranchinvoicebreakdowns() {
		return branchinvoicebreakdowns;
	}

	public void setBranchinvoicebreakdowns(
			List<BranchinvoicebreakdownModel> branchinvoicebreakdowns) {
		this.branchinvoicebreakdowns = branchinvoicebreakdowns;
	}

	public BranchinvoicebreakdownModel getBranchinvoicebreakdown() {
		return branchinvoicebreakdown;
	}

	public void setBranchinvoicebreakdown(
			BranchinvoicebreakdownModel branchinvoicebreakdown) {
		this.branchinvoicebreakdown = branchinvoicebreakdown;
	}

	public LazyDataModel<BranchinvoiceModel> getBranchinvoiceModel() {
		return branchinvoiceModel;
	}

	public void setBranchinvoiceModel(
			LazyDataModel<BranchinvoiceModel> branchinvoiceModel) {
		this.branchinvoiceModel = branchinvoiceModel;
	}

	public LazyDataModel<BranchinvoicebreakdownModel> getBranchinvoicebreakdownModel() {
		return branchinvoicebreakdownModel;
	}

	public void setBranchinvoicebreakdownModel(
			LazyDataModel<BranchinvoicebreakdownModel> branchinvoicebreakdownModel) {
		this.branchinvoicebreakdownModel = branchinvoicebreakdownModel;
	}

	public SalesorderModel getSalesorder() {
		return salesorder;
	}

	public void setSalesorder(SalesorderModel salesorder) {
		this.salesorder = salesorder;
	}

	
	
	public BranchsaleModel getBranchsale() {
		return branchsale;
	}

	public void setBranchsale(BranchsaleModel branchsale) {
		this.branchsale = branchsale;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
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
	
	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
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
	
	public CustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

	public void resetEditInvoice()
	{
		branchinvoice = new BranchinvoiceModel();
		this.setAction("submit");
	}
	
	
	
	public String getOutstandingstatus() {
		return outstandingstatus;
	}

	public void setOutstandingstatus(String outstandingstatus) {
		this.outstandingstatus = outstandingstatus;
	}

	public List<AdmindespatchModel> getAdespatchList() {
		return adespatchList;
	}

	public void setAdespatchList(List<AdmindespatchModel> adespatchList) {
		this.adespatchList = adespatchList;
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

	public void generateInvoice(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	BranchInvoiceBeanInfo branchInvoiceBeanInfo = new BranchInvoiceBeanInfo();
	resetEditInvoice();
	try {
		String salesOrderId = "";
		String salesType="";
		salesOrderId = (String) event.getComponent().getAttributes().get("salesOrderId").toString();	
		salesType = (String) event.getComponent().getAttributes().get("salesType").toString();
		salesorder=salesOrderBO.getSalesorderDetails(Integer.parseInt(salesOrderId) , salesType);
		branchinvoice=branchInvoiceBeanInfo.generateInvoice();
		branchinvoice.setInvoiceType(salesType);
		adminDespatchBean.getAdespatchList();
		branchInvoiceBeanInfo.newBranchInvoice();
			
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	
	
	public void generateBranchsalesInvoice(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	BranchInvoiceBeanInfo branchInvoiceBeanInfo = new BranchInvoiceBeanInfo();
	resetEditInvoice();
	try {
		String salesNo = "";
		String salesType="";		 
		branchsale = (BranchsaleModel) event.getComponent().getAttributes().get("sales");	
		salesorder=salesOrderBO.getSalesorderReportList(branchsale.getSalesNo(), null, null, null, null, null, loginBean.getBranch().getBranchId(), null, null).get(0);

		
		salesorder=salesOrderBO.getBranchSalesorderDetails(salesorder.getSalesOrderId() , "4");
		salesorder.setBranchSales("Yes");
		branchinvoice=branchInvoiceBeanInfo.generateInvoice();
		branchinvoice.setInvoiceType(salesorder.getSalesType());
		adminDespatchBean.getAdespatchList();
		branchInvoiceBeanInfo.newBranchInvoice();
			
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	
	public void viewInvoice(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();		
	try {		
		branchinvoice = (BranchinvoiceModel) event.getComponent().getAttributes().get("invoice");
		branchinvoice=branchinvoiceBO.getBranchinvoiceDetails(branchinvoice.getBranchInvoiceId());	
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	public void editInvoice(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();	
	BranchInvoiceBeanInfo branchInvoiceBeanInfo = new BranchInvoiceBeanInfo();
	try {
		
		branchinvoice = (BranchinvoiceModel) event.getComponent().getAttributes().get("invoice");
		branchinvoice=branchinvoiceBO.getBranchinvoiceDetails(branchinvoice.getBranchInvoiceId());
		adminDespatchBean.getAdespatchList();
		this.setAction("update");
		branchInvoiceBeanInfo.newBranchInvoice();
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
			branchinvoice = (BranchinvoiceModel) event.getComponent().getAttributes().get("invoice");
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
			}		
	}	
	
	
	public void approveInvoice()
	{
		BranchInvoiceBeanInfo branchInvoiceBeanInfo = new BranchInvoiceBeanInfo();		
		branchInvoiceBeanInfo.approveInvoice();
	}		
	
	
	public void saveInvoice()
	{
		BranchInvoiceBeanInfo branchInvoiceBeanInfo = new BranchInvoiceBeanInfo();
		branchInvoiceBeanInfo.saveInvoice();
	}
	
	public void updateInvoice()
	{
		BranchInvoiceBeanInfo branchInvoiceBeanInfo = new BranchInvoiceBeanInfo();
		branchInvoiceBeanInfo.updateInvoice();
	}
	
	
	public LazyDataModel<BranchinvoiceModel> getInvoiceList() {
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{		
		this.setBranchinvoiceModel(null);	
		if(this.customer!=null)
		{
			customerId=this.getCustomer().getCustomerId();
		}
		branchinvoiceModel = new BranchinvoicePagingHelper(customerId,invoiceNo,branchId, dateFrom, dateTo,status,branchinvoiceBO,loginBean.getBranch().getBranchId());		
		this.setBranchinvoiceModel(branchinvoiceModel);	
		}
		catch(Exception e){					
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"invoicePanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
			}
		return branchinvoiceModel;
	}
	
	
	public void searchInvoice()
	{
		BranchInvoiceBeanInfo branchInvoiceBeanInfo = new BranchInvoiceBeanInfo();
		try
		{   if(!branchInvoiceBeanInfo.validateInvoiceSearch())
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
		this.setCustomerId(null);
		this.setCustomer(null);
		searchInvoice();
	}
	
	public void listInvoice()
	{
		BranchInvoiceBeanInfo branchInvoiceBeanInfo = new BranchInvoiceBeanInfo();
		FacesContext context = FacesContext.getCurrentInstance();	
		try {
			branchInvoiceBeanInfo.listBranchInvoice();
			}
		catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
		}	
	
	public void newInvoice()
	{
		SalesorderBeanInfo salesorderBeanInfo = new SalesorderBeanInfo();
		FacesContext context = FacesContext.getCurrentInstance();	
		try {
			salesorderBeanInfo.newSalesOrder();			
			}
		catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
		}	

	
	public void calculateInvoiceAmount()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{
			branchinvoice.setTotalAmount(DecimalUtil.formatRoundupCents((branchinvoice.getTax().add(branchinvoice.getInvoiceAmount().add(branchinvoice.getInvoicedispatch().getDespatchAmount())))));
		}
		catch(Exception e)
		{
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
	}
	
	public void getDispatchAmountRoundup()
	{
		try
		{		
	//	this.setDiscountAmount(DecimalUtil.formatRoundupCents(this.getDiscountAmount()));
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void selectDespatch()
	{
		try{
		IAdminDespatchBO adminDespatchBO=adminDespatchBean.getAdminDespatchBO();
		branchinvoice.getInvoicedispatch().setDespatchAmount(adminDespatchBO.getAdminDespatchDetails(branchinvoice.getInvoicedispatch().getDispatch()).getUnitPrice());
		calculateInvoiceAmount();
		}
		catch(Exception e)
		{
			
		}
		
	}
	
	
	public void getBranchListReport()
	{		
		FacesContext context = FacesContext.getCurrentInstance();		
		context.getPartialViewContext().getRenderIds().add("reportForm1:branchIds1");
	}
	
	
	public void printInvoice(ActionEvent event) {
		
		FacesContext faces = FacesContext.getCurrentInstance();	
		try{				
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 			
		String quotationId = "";
		branchinvoice = (BranchinvoiceModel) event.getComponent().getAttributes().get("invoice");
		faces.getExternalContext().redirect(request.getScheme() + "://" + request.getServerName() +":" + request.getServerPort() +"/"+config.getValue(IConfiguration.PROJECT_INSTANCE_NAME)+"/frameset?__report=report/invoice/branchcustomerInvoiceDetail.rptdesign&__format=pdf&&__pageoverflow=0&__asattachment=true&__overwrite=false&branchInvoiceId=" + branchinvoice.getBranchInvoiceId()+"&userId="+loginBean.getUserId() );
		faces.responseComplete();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}	
}
