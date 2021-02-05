package com.project.bean.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.bo.interfaces.IBranchinvoiceBO;
import com.project.bo.interfaces.ICustomerinvoiceBO;
import com.project.bo.interfaces.ISalesorderBO;

import com.project.model.datamodel.stock.DeliveryorderbreakdownModel;
import com.project.model.invoice.branch.BranchinvoiceModel;
import com.project.model.invoice.customer.CustomerinvoiceModel;
import com.project.model.invoice.customer.CustomerinvoicebreakdownModel;
import com.project.model.sale.sales.SalesorderModel;
import com.project.model.sale.sales.SalesorderbreakdownModel;
import com.project.util.DateUtil;
import com.project.bean.delivery.DeliveryOrderBean;
import com.project.bean.delivery.DeliveryOrderBeanInfo;
import com.project.bean.sales.sale.SalesorderBean;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.factory.ErrorFactory;
import com.project.common.util.DecimalUtil;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.common.validation.ValidatorUtil;
import com.project.home.ProjectHome;
import com.project.login.LoginBean;

public class CustomerInvoiceBeanInfo {

	
	CustomerInvoiceBean customerInvoiceBean = (CustomerInvoiceBean) BeanContext.getReference("customerInvoiceBean");
	DeliveryOrderBean deliveryOrderBean = (DeliveryOrderBean) BeanContext.getReference("deliveryOrderBean");

	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	public static Logger log = LoggerFactory.getLogger(DeliveryOrderBeanInfo.class);
	FacesContext context = FacesContext.getCurrentInstance();
	Configuration config = Configuration.getConfiguration();
	String projectHome = config.getValue(IConfiguration.PROJECT_HOME_FILENAME);
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	static ErrorFactory errorFactory = ErrorFactory.getInstance();

	Integer balanceQuantity = 0;
	Integer receivedQuantity = 0;
	
	public void listCustomerInvoice() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/invoice/customer/customerInvoiceList.xhtml");
		projectHome.setTitlePage("Invoice --> Issued --> Customer Invoice");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}

	
	
	public void newCustomerInvoice() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/invoice/customer/addEditCustomerInvoice.xhtml");
		projectHome.setTitlePage("Invoice --> Customer Invoice --> Add/Edit Customer Invoice");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
	
	public void saveInvoice()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		CustomerInvoiceBean customerInvoiceBean = (CustomerInvoiceBean) BeanContext.getReference("customerInvoiceBean");
		SalesorderModel salesorder = new SalesorderModel();			
		boolean saveSuccess=false;
		BigDecimal totalInvoiceAmt = new BigDecimal(0);	
		BigDecimal taxAmount = new BigDecimal(0);	
		try
		{
			salesorder=customerInvoiceBean.getSalesorder();
			CustomerinvoiceModel customerinvoice=customerInvoiceBean.getCustomerinvoice();
			ICustomerinvoiceBO customerinvoiceBO=customerInvoiceBean.getCustomerinvoiceBO();
			ISalesorderBO salesOrderBO=customerInvoiceBean.getSalesOrderBO();
			
			customerinvoice.getInvoicedispatch().setCreatedDate(DateUtil.getTodayDate());
			customerinvoice.getInvoicedispatch().setCreatedBy(loginBean.getUserName());			
			
			taxAmount = customerinvoice.getInvoicetaxship().getTaxingScheme().multiply((customerinvoice.getInvoiceAmount().divide(new BigDecimal(100))));
			taxAmount=DecimalUtil.formatRoundupCents(taxAmount);
			totalInvoiceAmt=customerinvoice.getInvoiceAmount().add(customerinvoice.getInvoicedispatch().getDespatchAmount());	
			totalInvoiceAmt=totalInvoiceAmt.add(taxAmount);	
			totalInvoiceAmt=DecimalUtil.formatRoundupCents(totalInvoiceAmt);
			
			
			customerinvoice.setTotalAmount(totalInvoiceAmt);			
			customerinvoice.setCreatedBy(loginBean.getUserName());
			customerinvoice.setCreatedDate(DateUtil.getTodayDate());
			customerinvoice.setDispatchAmount(DecimalUtil.formatRoundupCents(customerinvoice.getInvoicedispatch().getDespatchAmount()));
			customerinvoice.setInvoiceDate(DateUtil.getTodayDate());
			customerinvoice.setInvoiceAmount(customerinvoice.getInvoiceAmount());			
			customerinvoice.setSalesOrderNo(customerinvoice.getSalesOrderNo());
			customerinvoice.setPaidAmount(new BigDecimal(0.00));
			customerinvoice.setPendingAmount(totalInvoiceAmt);
			customerinvoice.setTax(taxAmount);
			
			customerinvoice.setStatus(config.getValue(IConfiguration.INVOICE_STATUS_NEWORDER_VALUE));					
		
			saveSuccess=customerinvoiceBO.createNewCustomerinvoice(customerinvoice);
			
			if(saveSuccess)
			{	
				salesorder.setStatus(config.getValue(IConfiguration.SALESORDER_STATUS_ORDERED_VALUE));				
				salesOrderBO.updateSalesorder(salesorder);	
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("customerinvoice.label.created.success"),null));				
			}
			
			
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));				
			log.info("Error in saveInvoice of CustomerInvoiceBeanInfo "+ e.toString());
		}
	}
	
	
	
	public void updateInvoice()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		CustomerInvoiceBean customerInvoiceBean = (CustomerInvoiceBean) BeanContext.getReference("customerInvoiceBean");				
		boolean updateSuccess=false;
		BigDecimal totalInvoiceAmt = new BigDecimal(0);	
		BigDecimal taxAmount = new BigDecimal(0);	
			
		try
		{
			
			CustomerinvoiceModel customerinvoice=customerInvoiceBean.getCustomerinvoice();
			ICustomerinvoiceBO customerinvoiceBO=customerInvoiceBean.getCustomerinvoiceBO();		
			
			customerinvoice.getInvoicedispatch().setCreatedDate(DateUtil.getTodayDate());
			customerinvoice.getInvoicedispatch().setCreatedBy(loginBean.getUserName());			
			
						
			taxAmount = customerinvoice.getInvoicetaxship().getTaxingScheme().multiply((customerinvoice.getInvoiceAmount().divide(new BigDecimal(100))));
			taxAmount=DecimalUtil.formatRoundupCents(taxAmount);
			totalInvoiceAmt=customerinvoice.getInvoiceAmount().add(customerinvoice.getInvoicedispatch().getDespatchAmount());	
			totalInvoiceAmt=totalInvoiceAmt.add(taxAmount);	
			totalInvoiceAmt=DecimalUtil.formatRoundupCents(totalInvoiceAmt);
					
			
			customerinvoice.setTotalAmount(totalInvoiceAmt);			
			customerinvoice.setCreatedBy(loginBean.getUserName());
			customerinvoice.setCreatedDate(DateUtil.getTodayDate());
			customerinvoice.setDispatchAmount(DecimalUtil.formatRoundupCents(customerinvoice.getInvoicedispatch().getDespatchAmount()));
			customerinvoice.setInvoiceDate(DateUtil.getTodayDate());
			customerinvoice.setInvoiceAmount(customerinvoice.getInvoiceAmount());			
			customerinvoice.setSalesOrderNo(customerinvoice.getSalesOrderNo());
			customerinvoice.setPaidAmount(new BigDecimal(0.00));
			customerinvoice.setPendingAmount(totalInvoiceAmt);
			customerinvoice.setTax(taxAmount);			
			customerinvoice.setStatus(config.getValue(IConfiguration.INVOICE_STATUS_NEWORDER_VALUE));			
			updateSuccess=customerinvoiceBO.updateCustomerinvoice(customerinvoice);
			
			if(updateSuccess)
			{	
				listCustomerInvoice();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("customerinvoice.label.update.success"),null));				
			}		
			
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));				
			log.info("Error in updateInvoice of CustomerInvoiceBeanInfo "+ e.toString());
		}
	}
	
	public void approveInvoice()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		CustomerInvoiceBean customerInvoiceBean = (CustomerInvoiceBean) BeanContext.getReference("customerInvoiceBean");				
		boolean updateSuccess=false;		
		try
		{			
			CustomerinvoiceModel customerinvoice=customerInvoiceBean.getCustomerinvoice();
			ICustomerinvoiceBO customerinvoiceBO=customerInvoiceBean.getCustomerinvoiceBO();			
			customerinvoice.setApprovedBy(loginBean.getUserName());
			customerinvoice.setApprovedDate(DateUtil.getTodayDate());					
			customerinvoice.setStatus(config.getValue(IConfiguration.INVOICE_STATUS_PROCESSED_VALUE));			
			updateSuccess=customerinvoiceBO.approveCustomerinvoice(customerinvoice);
			if(updateSuccess)
			{	
				listCustomerInvoice();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("customerinvoice.label.approved.success"),null));				
			}		
			
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));				
			log.info("Error in approveInvoice of BranchInvoiceBeanInfo "+ e.toString());
		}
	}
	
	
	
	public CustomerinvoiceModel generateInvoice()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		CustomerInvoiceBean customerInvoiceBean = (CustomerInvoiceBean) BeanContext.getReference("customerInvoiceBean");
		SalesorderModel salesorder = new SalesorderModel();		
		List<CustomerinvoicebreakdownModel> customerinvoicebreakdowns = new ArrayList<CustomerinvoicebreakdownModel>();			
		CustomerinvoiceModel customerinvoice = new CustomerinvoiceModel();		
		try
		{
		salesorder=customerInvoiceBean.getSalesorder();
		customerinvoice.setCustomer(salesorder.getCustomer());
		customerinvoice.setInvoiceAmount(salesorder.getTotalAmount());
		customerinvoice.setSalesOrderNo(salesorder.getSalesOrderNo());
		
		for(SalesorderbreakdownModel productData:salesorder.getSalesorderbreakdowns())
		{
			CustomerinvoicebreakdownModel data=new CustomerinvoicebreakdownModel();					
				
					data.setProductId(productData.getProductId());
					data.setProductCode(productData.getProductCode());
					data.setProductName(productData.getProductName());				
					data.setDiscount(productData.getDiscount());
					data.setDiscountAmount(productData.getDiscountAmount());
					data.setUom(productData.getUom());
					data.setQuantity(productData.getQuantity().intValue());			
					data.setSubTotal(productData.getSubTotal());
					data.setUnitPrice(productData.getUnitPrice());	
					data.setPurchasePrice(productData.getPurchasePrice());
					
					customerinvoicebreakdowns.add(data);							
		}					
		customerinvoice.setCustomerinvoicebreakdowns(customerinvoicebreakdowns);
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));				
			log.info("Error in generateInvoice of CustomerInvoiceBeanInfo "+ e.toString());
		}
		
		return customerinvoice;
	}
	
	
	
	public boolean validateInvoiceSearch() {
		boolean valid = true;		
	
		CustomerInvoiceBean customerInvoiceBean = (CustomerInvoiceBean) BeanContext.getReference("customerInvoiceBean");
		
		if(customerInvoiceBean.getInvoiceNo()!=null)
		{
			if(customerInvoiceBean.getInvoiceNo().equalsIgnoreCase(""))
			{
				customerInvoiceBean.setInvoiceNo(null);
			}
		}		
		
		if(customerInvoiceBean.getCustomerId()!=null)
		{
			if(customerInvoiceBean.getCustomerId()==0)
			{
				customerInvoiceBean.setCustomerId(null);
			}
		}	
		
		
		if(customerInvoiceBean.getStatus()!=null)
		{
			if(customerInvoiceBean.getStatus().equalsIgnoreCase("") || customerInvoiceBean.getStatus().equalsIgnoreCase("0") )
			{
				customerInvoiceBean.setStatus(null);
			}
		}		
		
		if(customerInvoiceBean.getCustomerId()==null &&  customerInvoiceBean.getDateFrom()==null && customerInvoiceBean.getDateTo()==null && customerInvoiceBean.getStatus()==null  && customerInvoiceBean.getInvoiceNo()==null)
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
