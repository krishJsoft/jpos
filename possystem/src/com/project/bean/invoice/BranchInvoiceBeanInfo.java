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
import com.project.bo.interfaces.IBranchsalesBO;
import com.project.bo.interfaces.ISalesorderBO;
import com.project.model.invoice.branch.BranchinvoiceModel;
import com.project.model.invoice.branch.BranchinvoicebreakdownModel;
import com.project.model.invoice.customer.CustomerinvoiceModel;
import com.project.model.sale.sales.SalesorderModel;
import com.project.model.sale.sales.SalesorderbreakdownModel;
import com.project.model.sale.sales.branchsale.BranchsaleModel;
import com.project.util.DateUtil;
import com.project.bean.delivery.DeliveryOrderBean;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.factory.ErrorFactory;
import com.project.common.util.DecimalUtil;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.home.ProjectHome;
import com.project.login.LoginBean;

public class BranchInvoiceBeanInfo {
	
	BranchInvoiceBean branchInvoiceBean = (BranchInvoiceBean) BeanContext.getReference("branchInvoiceBean");
	DeliveryOrderBean deliveryOrderBean = (DeliveryOrderBean) BeanContext.getReference("deliveryOrderBean");

	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	public static Logger log = LoggerFactory.getLogger(BranchInvoiceBeanInfo.class);
	FacesContext context = FacesContext.getCurrentInstance();
	Configuration config = Configuration.getConfiguration();
	String projectHome = config.getValue(IConfiguration.PROJECT_HOME_FILENAME);
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	static ErrorFactory errorFactory = ErrorFactory.getInstance();

	Integer balanceQuantity = 0;
	Integer receivedQuantity = 0;
	
	public void listBranchInvoice() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/invoice/branch/branchInvoiceList.xhtml");
		projectHome.setTitlePage("Invoice --> Issued --> Branch/Customer Invoices");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}

	
	
	public void newBranchInvoice() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/invoice/branch/addEditBranchInvoice.xhtml");
		projectHome.setTitlePage("Invoice -->Issued --> Add/Edit Branch/Customer Invoice");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
	
	public void saveInvoice()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		BranchInvoiceBean branchInvoiceBean = (BranchInvoiceBean) BeanContext.getReference("branchInvoiceBean");
		SalesorderModel salesorder = new SalesorderModel();			
		boolean saveSuccess=false;
		BigDecimal totalInvoiceAmt = new BigDecimal(0);	
		BigDecimal taxAmount = new BigDecimal(0);	
		boolean branchSales=false;
		try
		{
			salesorder=branchInvoiceBean.getSalesorder();
			BranchinvoiceModel branchinvoice = new BranchinvoiceModel();
			CustomerinvoiceModel customerinvoice = new CustomerinvoiceModel();
			branchinvoice=branchInvoiceBean.getBranchinvoice();
			IBranchinvoiceBO branchinvoiceBO=branchInvoiceBean.getBranchinvoiceBO();
			ISalesorderBO salesOrderBO=branchInvoiceBean.getSalesOrderBO();
			IBranchsalesBO branchsalesBO=branchInvoiceBean.getBranchsalesBO();
			
			branchinvoice.getInvoicedispatch().setCreatedDate(DateUtil.getTodayDate());
			branchinvoice.getInvoicedispatch().setCreatedBy(loginBean.getUserName());			
			branchinvoice.setBranchRecordId(loginBean.getBranch().getBranchId());
			salesorder.setBranchRecordId(loginBean.getBranch().getBranchId());
			//taxAmount = branchinvoice.getInvoicetaxship().getTaxingScheme().multiply((branchinvoice.getInvoiceAmount().divide(new BigDecimal(100))));
			//taxAmount=DecimalUtil.formatRoundupCents(taxAmount);
			totalInvoiceAmt=branchinvoice.getInvoiceAmount().add(branchinvoice.getInvoicedispatch().getDespatchAmount());	
			totalInvoiceAmt=totalInvoiceAmt.add(branchinvoice.getTax());		
			//totalInvoiceAmt=DecimalUtil.formatRoundupCents(totalInvoiceAmt);
			
			//branchinvoice.setTotalAmount(totalInvoiceAmt);	
			branchinvoice.setTotalAmount(DecimalUtil.formatRoundupCents(totalInvoiceAmt));
			branchinvoice.setCreatedBy(loginBean.getUserName());
			branchinvoice.setCreatedDate(DateUtil.getTodayDate());
			branchinvoice.setDispatchAmount(branchinvoice.getInvoicedispatch().getDespatchAmount());
			branchinvoice.setInvoiceDate(DateUtil.getTodayDate());
			branchinvoice.setInvoiceAmount(branchinvoice.getInvoiceAmount());			
			branchinvoice.setSalesOrderNo(branchinvoice.getSalesOrderNo());
			branchinvoice.setPaidAmount(new BigDecimal(0.00));
			branchinvoice.setPendingAmount(DecimalUtil.formatRoundupCents(totalInvoiceAmt));
			//branchinvoice.setTax(taxAmount);
			
			branchinvoice.setStatus(config.getValue(IConfiguration.INVOICE_STATUS_NEWORDER_VALUE));					
			branchinvoice.setAccountstatus("0");
			saveSuccess=branchinvoiceBO.createNewBranchinvoice(branchinvoice);
			
			if(saveSuccess)
			{				
				
				if(salesorder.getBranchSales()!=null)
				{
				if(salesorder.getBranchSales().equalsIgnoreCase("Yes")) // Invoice from Branch Sales 
				{
					branchSales=true;
					BranchsaleModel branchsale = branchInvoiceBean.getBranchsale();					
					branchsale.setStatus(config.getValue(IConfiguration.SALESORDER_STATUS_ORDERED_VALUE));
					branchsalesBO.updateMasterBranchsale(branchsale);
				}
				}
				if(!branchSales)
				{
					salesorder.setStatus(config.getValue(IConfiguration.SALESORDER_STATUS_ORDERED_VALUE));				
					salesOrderBO.updateSalesorder(salesorder);
				}
				
				listBranchInvoice();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("branchinvoice.label.created.success"),null));				
			}
			
			
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));				
			log.info("Error in saveInvoice of BranchInvoiceBeanInfo "+ e.toString());
		}
	}
	
	
	
	
	
	
	public void updateInvoice()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		BranchInvoiceBean branchInvoiceBean = (BranchInvoiceBean) BeanContext.getReference("branchInvoiceBean");				
		boolean updateSuccess=false;
		BigDecimal totalInvoiceAmt = new BigDecimal(0);	
		BigDecimal taxAmount = new BigDecimal(0);	
			
		try
		{
			
			BranchinvoiceModel branchinvoice=branchInvoiceBean.getBranchinvoice();
			IBranchinvoiceBO branchinvoiceBO=branchInvoiceBean.getBranchinvoiceBO();
			
			branchinvoice.getInvoicedispatch().setCreatedDate(DateUtil.getTodayDate());
			branchinvoice.getInvoicedispatch().setCreatedBy(loginBean.getUserName());			
						
			totalInvoiceAmt=branchinvoice.getInvoiceAmount().add(branchinvoice.getInvoicedispatch().getDespatchAmount());	
			totalInvoiceAmt=totalInvoiceAmt.add(branchinvoice.getTax());
		
			branchinvoice.setTotalAmount(DecimalUtil.formatRoundupCents(totalInvoiceAmt));
			branchinvoice.setCreatedBy(loginBean.getUserName());
			branchinvoice.setCreatedDate(DateUtil.getTodayDate());
			branchinvoice.setDispatchAmount(DecimalUtil.formatRoundupCents(branchinvoice.getInvoicedispatch().getDespatchAmount()));
			branchinvoice.setInvoiceDate(DateUtil.getTodayDate());
			branchinvoice.setInvoiceAmount(branchinvoice.getInvoiceAmount());			
			branchinvoice.setSalesOrderNo(branchinvoice.getSalesOrderNo());
			branchinvoice.setPaidAmount(new BigDecimal(0.00));
			branchinvoice.setPendingAmount(DecimalUtil.formatRoundupCents(totalInvoiceAmt));					
			branchinvoice.setStatus(config.getValue(IConfiguration.INVOICE_STATUS_NEWORDER_VALUE));			
			updateSuccess=branchinvoiceBO.updateBranchinvoice(branchinvoice);
			
			if(updateSuccess)
			{	
				listBranchInvoice();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("branchinvoice.label.update.success"),null));				
			}		
			
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));				
			log.info("Error in updateInvoice of BranchInvoiceBeanInfo "+ e.toString());
		}
	}
	
	
	
	
	public void approveInvoice()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		BranchInvoiceBean branchInvoiceBean = (BranchInvoiceBean) BeanContext.getReference("branchInvoiceBean");				
		boolean updateSuccess=false;		
		try
		{			
			BranchinvoiceModel branchinvoice=branchInvoiceBean.getBranchinvoice();
			IBranchinvoiceBO branchinvoiceBO=branchInvoiceBean.getBranchinvoiceBO();				
			branchinvoice.setApprovedBy(loginBean.getUserName());
			branchinvoice.setApprovedDate(DateUtil.getTodayDate());					
			branchinvoice.setStatus(config.getValue(IConfiguration.INVOICE_STATUS_PROCESSED_VALUE));			
			updateSuccess=branchinvoiceBO.approveBranchinvoice(branchinvoice);
			if(updateSuccess)
			{	
				listBranchInvoice();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("branchinvoice.label.approved.success"),null));				
			}		
			
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));				
			log.info("Error in approveInvoice of BranchInvoiceBeanInfo "+ e.toString());
		}
	}
	
	
	
	
	public BranchinvoiceModel generateInvoice()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		BranchInvoiceBean branchInvoiceBean = (BranchInvoiceBean) BeanContext.getReference("branchInvoiceBean");
		SalesorderModel salesorder = new SalesorderModel();		
		List<BranchinvoicebreakdownModel> branchinvoicebreakdowns = new ArrayList<BranchinvoicebreakdownModel>();			
		BranchinvoiceModel branchinvoice = new BranchinvoiceModel();
		BigDecimal totalInvoiceAmt = new BigDecimal(0);	
		BigDecimal taxAmount = new BigDecimal(0);	
		try
		{
		salesorder=branchInvoiceBean.getSalesorder();
		branchinvoice.setBranch(salesorder.getBranch());	
		branchinvoice.setCustomer(salesorder.getCustomer());
		branchinvoice.setInvoiceType(salesorder.getSalesType());
		
					
		branchinvoice.setSalesOrderNo(salesorder.getSalesOrderNo());	
		
		if(salesorder.getSalesType().equalsIgnoreCase("1"))
		{
		branchinvoice.getInvoicetaxship().setAddress(salesorder.getBranch().getAddress());
		branchinvoice.getInvoicetaxship().setCity(salesorder.getBranch().getCity());
		branchinvoice.getInvoicetaxship().setState(salesorder.getBranch().getState());
		branchinvoice.getInvoicetaxship().setPostCode(salesorder.getBranch().getPostCode());
		branchinvoice.getInvoicetaxship().setCountry(salesorder.getBranch().getCountry());
		}
		if(salesorder.getSalesType().equalsIgnoreCase("2"))
		{
		branchinvoice.getInvoicetaxship().setAddress(salesorder.getCustomer().getAddress());
		branchinvoice.getInvoicetaxship().setCity(salesorder.getCustomer().getCity());
		branchinvoice.getInvoicetaxship().setState(salesorder.getCustomer().getState());
		branchinvoice.getInvoicetaxship().setPostCode(salesorder.getCustomer().getPostCode());
		branchinvoice.getInvoicetaxship().setCountry(salesorder.getCustomer().getCountry());
		}
		if(salesorder.getSalesType().equalsIgnoreCase("3"))
		{
		branchinvoice.getInvoicetaxship().setAddress(salesorder.getCustomer().getAddress());
		branchinvoice.getInvoicetaxship().setCity(salesorder.getCustomer().getCity());
		branchinvoice.getInvoicetaxship().setState(salesorder.getCustomer().getState());
		branchinvoice.getInvoicetaxship().setPostCode(salesorder.getCustomer().getPostCode());
		branchinvoice.getInvoicetaxship().setCountry(salesorder.getCustomer().getCountry());
		}
		
		
		
		for(SalesorderbreakdownModel productData:salesorder.getSalesorderbreakdowns())
		{
			     BranchinvoicebreakdownModel data=new BranchinvoicebreakdownModel();					
				
					data.setProductId(productData.getProductId());
					data.setProductCode(productData.getProductCode());
					data.setProductName(productData.getProductName());				
					data.setDiscount(productData.getDiscount());
					data.setDiscountAmount(productData.getDiscountAmount());
					data.setUom(productData.getUom());
					data.setQuantity(productData.getQuantity());			
					data.setSubTotal(productData.getSubTotal());
					data.setUnitPrice(productData.getUnitPrice());	
					data.setPurchasePrice(productData.getPurchasePrice());	
					data.setTaxAmount(productData.getTaxAmount());
					data.setTaxCode(productData.getTaxCode());					
					totalInvoiceAmt=totalInvoiceAmt.add(productData.getSubTotal());
					
					branchinvoicebreakdowns.add(data);							
		}				
		
		branchinvoice.setInvoiceAmount(totalInvoiceAmt.subtract(salesorder.getTotalTax()));
		branchinvoice.setTax(salesorder.getTotalTax());
		branchinvoice.setTotalAmount(DecimalUtil.formatRoundupCents(totalInvoiceAmt));
		
		branchinvoice.setBranchinvoicebreakdowns(branchinvoicebreakdowns);
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));				
			log.info("Error in generateInvoice of BranchInvoiceBeanInfo "+ e.toString());
		}
		
		return branchinvoice;
	}
	
	
	
	public boolean validateInvoiceSearch() {
		boolean valid = true;		
	
		BranchInvoiceBean branchInvoiceBean = (BranchInvoiceBean) BeanContext.getReference("branchInvoiceBean");
		
		if(branchInvoiceBean.getInvoiceNo()!=null)
		{
			if(branchInvoiceBean.getInvoiceNo().equalsIgnoreCase(""))
			{
				branchInvoiceBean.setInvoiceNo(null);
			}
		}	
		
		if(branchInvoiceBean.getCustomer()!=null)
		{
			if(branchInvoiceBean.getCustomer().getCustomerId()==0)
			{
				branchInvoiceBean.setCustomerId(null);
			}
		}	
		
		if(branchInvoiceBean.getBranchId()!=null)
		{
			if(branchInvoiceBean.getBranchId()==0)
			{
				branchInvoiceBean.setBranchId(null);
			}
		}	
		
		
		if(branchInvoiceBean.getStatus()!=null)
		{
			if(branchInvoiceBean.getStatus().equalsIgnoreCase("") || branchInvoiceBean.getStatus().equalsIgnoreCase("0") )
			{
				branchInvoiceBean.setStatus(null);
			}
		}		
		
		if(branchInvoiceBean.getCustomerId()==null  && branchInvoiceBean.getBranchId()==null &&  branchInvoiceBean.getDateFrom()==null && branchInvoiceBean.getDateTo()==null && branchInvoiceBean.getStatus()==null  && branchInvoiceBean.getInvoiceNo()==null)
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
