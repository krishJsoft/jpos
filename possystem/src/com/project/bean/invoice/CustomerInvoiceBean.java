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

import org.primefaces.model.LazyDataModel;

import com.project.bo.interfaces.ICustomerinvoiceBO;
import com.project.bo.interfaces.ISalesorderBO;
import com.project.model.datamodel.CustomerModel;
import com.project.model.invoice.customer.CustomerinvoiceModel;
import com.project.model.invoice.customer.CustomerinvoicebreakdownModel;
import com.project.model.sale.sales.SalesorderModel;
import com.project.util.DateUtil;
import com.project.bean.admin.CommonListBeanInfo;
import com.project.bean.sales.sale.SalesorderBeanInfo;
import com.project.common.factory.BeanContext;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.UIComponent;
import com.project.model.paginghelper.CustomerinvoicePagingHelper;


/*
 * @author Gopal Ar
 * @version 1.0
 * @since 20 Sep 2013
 * 
 */

@ManagedBean(name = "customerInvoiceBean")
@SessionScoped
public class CustomerInvoiceBean {

	CustomerinvoiceModel customerinvoice = new CustomerinvoiceModel();
	CustomerinvoicebreakdownModel customerinvoicebreakdown = new CustomerinvoicebreakdownModel();
	List<CustomerinvoicebreakdownModel> customerinvoicebreakdownModelList = new ArrayList<CustomerinvoicebreakdownModel>();
	private LazyDataModel<CustomerinvoiceModel> customerinvoiceModel = null;
	private LazyDataModel<CustomerinvoicebreakdownModel> customerinvoicebreakdownModel = null;
	SalesorderModel salesorder = new SalesorderModel();
	private CustomerModel customer;
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");

	private Integer customerId;
	private Date dateFrom;
	private Date dateTo;
	private String status;
	private String salesOrderNo;
	private int customerInvoiceId;
	private String invoiceNo;
	private String action="submit";
	private int activeIndex=0;
	
	ISalesorderBO salesOrderBO=objectMapController.getSalesOrderBO();	
	
	ICustomerinvoiceBO customerinvoiceBO=objectMapController.getCustomerinvoiceBO();		

	public ISalesorderBO getSalesOrderBO() {
		return salesOrderBO;
	}

	public void setSalesOrderBO(ISalesorderBO salesOrderBO) {
		this.salesOrderBO = salesOrderBO;
	}

	public ICustomerinvoiceBO getCustomerinvoiceBO() {
		return customerinvoiceBO;
	}

	public void setCustomerinvoiceBO(ICustomerinvoiceBO customerinvoiceBO) {
		this.customerinvoiceBO = customerinvoiceBO;
	}

	private List<SelectItem> selectSalesOrderNoList = new ArrayList<SelectItem>();

	public CustomerinvoiceModel getCustomerinvoice() {
		return customerinvoice;
	}

	public void setCustomerinvoice(CustomerinvoiceModel customerinvoice) {
		this.customerinvoice = customerinvoice;
	}

	public CustomerinvoicebreakdownModel getCustomerinvoicebreakdown() {
		return customerinvoicebreakdown;
	}

	public void setCustomerinvoicebreakdown(
			CustomerinvoicebreakdownModel customerinvoicebreakdown) {
		this.customerinvoicebreakdown = customerinvoicebreakdown;
	}

	public List<CustomerinvoicebreakdownModel> getCustomerinvoicebreakdownModelList() {
		return customerinvoicebreakdownModelList;
	}

	public void setCustomerinvoicebreakdownModelList(
			List<CustomerinvoicebreakdownModel> customerinvoicebreakdownModelList) {
		this.customerinvoicebreakdownModelList = customerinvoicebreakdownModelList;
	}

	public LazyDataModel<CustomerinvoiceModel> getCustomerinvoiceModel() {
		return customerinvoiceModel;
	}

	public void setCustomerinvoiceModel(
			LazyDataModel<CustomerinvoiceModel> customerinvoiceModel) {
		this.customerinvoiceModel = customerinvoiceModel;
	}

	public LazyDataModel<CustomerinvoicebreakdownModel> getCustomerinvoicebreakdownModel() {
		return customerinvoicebreakdownModel;
	}

	public void setCustomerinvoicebreakdownModel(
			LazyDataModel<CustomerinvoicebreakdownModel> customerinvoicebreakdownModel) {
		this.customerinvoicebreakdownModel = customerinvoicebreakdownModel;
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

	public String getSalesOrderNo() {
		return salesOrderNo;
	}

	public void setSalesOrderNo(String salesOrderNo) {
		this.salesOrderNo = salesOrderNo;
	}

	public int getCustomerInvoiceId() {
		return customerInvoiceId;
	}

	public void setCustomerInvoiceId(int customerInvoiceId) {
		this.customerInvoiceId = customerInvoiceId;
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

	public List<SelectItem> getSelectSalesOrderNoList() {
		return selectSalesOrderNoList;
	}

	public void setSelectSalesOrderNoList(
			List<SelectItem> selectSalesOrderNoList) {
		this.selectSalesOrderNoList = selectSalesOrderNoList;
	}	
	
	public SalesorderModel getSalesorder() {
		return salesorder;
	}

	public void setSalesorder(SalesorderModel salesorder) {
		this.salesorder = salesorder;
	}
	
	

	public CustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

	public int getActiveIndex() {
		return activeIndex;
	}

	public void setActiveIndex(int activeIndex) {
		this.activeIndex = activeIndex;
	}
	
	public void resetEditInvoice()
	{
		customerinvoice = new CustomerinvoiceModel();
		this.setAction("submit");
	}

	public void generateInvoice(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	CustomerInvoiceBeanInfo customerInvoiceBeanInfo = new CustomerInvoiceBeanInfo();
	resetEditInvoice();
	try {
		String salesOrderId = "";
		String salesType="";
		salesOrderId = (String) event.getComponent().getAttributes().get("salesOrderId").toString();	
		salesType = (String) event.getComponent().getAttributes().get("salesType").toString();
		salesorder=salesOrderBO.getSalesorderDetails(Integer.parseInt(salesOrderId) , salesType);
		customerinvoice=customerInvoiceBeanInfo.generateInvoice();
		
		if(salesType.equalsIgnoreCase("2"))
		{
			customerInvoiceBeanInfo.newCustomerInvoice();
		}
		
		if(salesType.equalsIgnoreCase("1"))
		{
			customerInvoiceBeanInfo.newCustomerInvoice();
		}
		
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
		customerinvoice = (CustomerinvoiceModel) event.getComponent().getAttributes().get("invoice");
		customerinvoice=customerinvoiceBO.getCustomerinvoiceDetails(customerinvoice.getCustomerInvoiceId());		
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	public void editInvoice(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();	
	CustomerInvoiceBeanInfo customerInvoiceBeanInfo = new CustomerInvoiceBeanInfo();
	try {
		
		customerinvoice = (CustomerinvoiceModel) event.getComponent().getAttributes().get("invoice");
		customerinvoice=customerinvoiceBO.getCustomerinvoiceDetails(customerinvoice.getCustomerInvoiceId());		
		this.setAction("update");
		customerInvoiceBeanInfo.newCustomerInvoice();
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}	
	
	
	public void saveInvoice()
	{
		CustomerInvoiceBeanInfo customerInvoiceBeanInfo = new CustomerInvoiceBeanInfo();
		customerInvoiceBeanInfo.saveInvoice();
	}
	
	public void updateInvoice()
	{
		CustomerInvoiceBeanInfo customerInvoiceBeanInfo = new CustomerInvoiceBeanInfo();
		customerInvoiceBeanInfo.updateInvoice();
	}
	
	
	public void approveInvoiceConfirm(ActionEvent event)  throws Exception 
	{		
		FacesContext context = FacesContext.getCurrentInstance();		
		try {			
			customerinvoice = (CustomerinvoiceModel) event.getComponent().getAttributes().get("invoice");
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
			}		
	}	
	
	
	public void approveInvoice()
	{
		CustomerInvoiceBeanInfo customerInvoiceBeanInfo = new CustomerInvoiceBeanInfo();		
		customerInvoiceBeanInfo.approveInvoice();
	}		
	
	
	
	public LazyDataModel<CustomerinvoiceModel> getInvoiceList() {
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{		
		this.setCustomerinvoiceModel(null);	
		customerinvoiceModel = new CustomerinvoicePagingHelper(invoiceNo,customerId, dateFrom, dateTo,status,customerinvoiceBO);		
		this.setCustomerinvoiceModel(customerinvoiceModel);	
		}
		catch(Exception e){					
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"invoicePanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
			}
		return customerinvoiceModel;
	}
	
	
	public void searchInvoice()
	{
		CustomerInvoiceBeanInfo customerInvoiceBeanInfo = new CustomerInvoiceBeanInfo();
		try
		{   if(!customerInvoiceBeanInfo.validateInvoiceSearch())
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
		this.setCustomerId(0);		
		this.setDateFrom(DateUtil.getFromTodayDateTime());
		this.setDateTo(DateUtil.getToTodayDateTime());
		this.setStatus(null);
		this.setInvoiceNo(null);
		searchInvoice();
	}
	
	public void listInvoice()
	{
		SalesorderBeanInfo salesorderBeanInfo = new SalesorderBeanInfo();
		FacesContext context = FacesContext.getCurrentInstance();	
		try {
			salesorderBeanInfo.listSalesOrder();
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

	
	
	public List<CustomerModel> getCustomerName(String customerString) {
		List<CustomerModel> results = new ArrayList<CustomerModel>();
		CommonListBeanInfo commoninfo = new CommonListBeanInfo();
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			results = commoninfo.getAllCustomerList(customerString);
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
		return results;
	}

}
