package com.project.bean.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.bo.interfaces.ICustomerBO;
import com.project.bo.interfaces.IDeliveryorderBO;
import com.project.bo.interfaces.ISalesorderBO;
import com.project.bo.interfaces.ISupplierinvoiceBO;
import com.project.model.datamodel.stock.DeliveryorderModel;
import com.project.model.datamodel.stock.DeliveryorderbreakdownModel;
import com.project.model.invoice.supplier.SupplierinvoiceModel;
import com.project.model.invoice.supplier.SupplierinvoicebreakdownModel;
import com.project.model.sale.sales.SalesorderModel;
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

public class SupplierInvoiceBeanInfo {
	
	SupplierInvoiceBean supplierInvoiceBean = (SupplierInvoiceBean) BeanContext.getReference("supplierInvoiceBean");
	DeliveryOrderBean deliveryOrderBean = (DeliveryOrderBean) BeanContext.getReference("deliveryOrderBean");

	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	public static Logger log = LoggerFactory.getLogger(SupplierInvoiceBeanInfo.class);
	FacesContext context = FacesContext.getCurrentInstance();
	Configuration config = Configuration.getConfiguration();
	String projectHome = config.getValue(IConfiguration.PROJECT_HOME_FILENAME);
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	static ErrorFactory errorFactory = ErrorFactory.getInstance();

	Integer balanceQuantity = 0;
	Integer receivedQuantity = 0;
	
	public void listSupplierInvoice() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/invoice/supplier/supplierInvoiceList.xhtml");
		projectHome.setTitlePage("Invoice --> Received --> Supplier Invoice");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}

	
	
	public void newSupplierInvoice() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/invoice/supplier/addEditSupplierInvoice.xhtml");
		projectHome.setTitlePage("Invoice --> Supplier Invoice --> Add/Edit Supplier Invoice");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
	
	public void saveInvoice()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		SupplierInvoiceBean supplierInvoiceBean = (SupplierInvoiceBean) BeanContext.getReference("supplierInvoiceBean");
		DeliveryorderModel deliveryorder = new DeliveryorderModel();		
		boolean saveSuccess=false;
		BigDecimal totalInvoiceAmt = new BigDecimal(0);	
		BigDecimal taxAmount = new BigDecimal(0);	
		try
		{
			if(validateInvoice())
			{
			deliveryorder=supplierInvoiceBean.getDeliveryorder();
			SupplierinvoiceModel supplierinvoice = new SupplierinvoiceModel();
			supplierinvoice=supplierInvoiceBean.getSupplierinvoice();
			ISupplierinvoiceBO supplierinvoiceBO=supplierInvoiceBean.getSupplierinvoiceBO();
			ISalesorderBO salesOrderBO=supplierInvoiceBean.getSalesOrderBO();
			IDeliveryorderBO deliveryOrderBO=supplierInvoiceBean.getDeliveryOrderBO();
			
			supplierinvoice.getInvoicedispatch().setCreatedDate(DateUtil.getTodayDate());
			supplierinvoice.getInvoicedispatch().setCreatedBy(loginBean.getUserName());			
			
			//taxAmount = supplierinvoice.getInvoicetaxship().getTaxingScheme().multiply((supplierinvoice.getInvoiceAmount().divide(new BigDecimal(100))));
			//taxAmount=DecimalUtil.formatRoundupCents(taxAmount);
			totalInvoiceAmt=supplierinvoice.getInvoiceAmount().add(supplierinvoice.getInvoicedispatch().getDespatchAmount());	
			totalInvoiceAmt=totalInvoiceAmt.add(supplierinvoice.getTax());	
			//totalInvoiceAmt=DecimalUtil.formatRoundupCents(totalInvoiceAmt);
			
			//supplierinvoice.setTotalAmount(totalInvoiceAmt);	
			supplierinvoice.setTotalAmount(DecimalUtil.formatRoundupCents(totalInvoiceAmt));
			supplierinvoice.setCreatedBy(loginBean.getUserName());
			supplierinvoice.setCreatedDate(DateUtil.getTodayDate());
			supplierinvoice.setDispatchAmount(DecimalUtil.formatRoundupCents(supplierinvoice.getInvoicedispatch().getDespatchAmount()));
			supplierinvoice.setInvoiceDate(DateUtil.getTodayDate());
			supplierinvoice.setInvoiceAmount(supplierinvoice.getInvoiceAmount());			
			supplierinvoice.setDeliveryOrderNo(supplierinvoice.getDeliveryOrderNo());
			supplierinvoice.setPaidAmount(new BigDecimal(0.00));
			supplierinvoice.setPendingAmount(DecimalUtil.formatRoundupCents(totalInvoiceAmt));
			//supplierinvoice.setTax(taxAmount);	
			supplierinvoice.setBranchRecordId(loginBean.getBranch().getBranchId());
			supplierinvoice.setInvoicetype("1");
			
			supplierinvoice.setStatus(config.getValue(IConfiguration.INVOICE_STATUS_NEWORDER_VALUE));					
		
			saveSuccess=supplierinvoiceBO.createNewSupplierinvoice(supplierinvoice);
			
			if(saveSuccess)
			{	
				supplierInvoiceBean.resetEditInvoice();
				listSupplierInvoice();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("supplierinvoice.label.created.success"),null));				
			}
			}
			
			
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));				
			log.info("Error in saveInvoice of SupplierInvoiceBeanInfo "+ e.toString());
		}
	}
	
	
	
	public void updateInvoice()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		SupplierInvoiceBean supplierInvoiceBean = (SupplierInvoiceBean) BeanContext.getReference("supplierInvoiceBean");		
		boolean updateSuccess=false;
		BigDecimal totalInvoiceAmt = new BigDecimal(0);	
		BigDecimal taxAmount = new BigDecimal(0);	
			
		try
		{
			if(validateInvoice())
			{
			SupplierinvoiceModel supplierinvoice = new SupplierinvoiceModel();
			supplierinvoice=supplierInvoiceBean.getSupplierinvoice();
			ISupplierinvoiceBO supplierinvoiceBO=supplierInvoiceBean.getSupplierinvoiceBO();
			
			supplierinvoice.getInvoicedispatch().setCreatedDate(DateUtil.getTodayDate());
			supplierinvoice.getInvoicedispatch().setCreatedBy(loginBean.getUserName());			
			
			totalInvoiceAmt=supplierinvoice.getInvoiceAmount().add(supplierinvoice.getInvoicedispatch().getDespatchAmount());	
			totalInvoiceAmt=totalInvoiceAmt.add(supplierinvoice.getTax());
			
			//supplierinvoice.setTotalAmount(totalInvoiceAmt);
			supplierinvoice.setTotalAmount(DecimalUtil.formatRoundupCents(totalInvoiceAmt));
			supplierinvoice.setCreatedBy(loginBean.getUserName());
			supplierinvoice.setCreatedDate(DateUtil.getTodayDate());
			supplierinvoice.setDispatchAmount(supplierinvoice.getInvoicedispatch().getDespatchAmount());
			supplierinvoice.setInvoiceDate(DateUtil.getTodayDate());
			supplierinvoice.setInvoiceAmount(supplierinvoice.getInvoiceAmount());			
			supplierinvoice.setDeliveryOrderNo(supplierinvoice.getDeliveryOrderNo());
			supplierinvoice.setPaidAmount(new BigDecimal(0.00));			
			supplierinvoice.setPendingAmount(DecimalUtil.formatRoundupCents(totalInvoiceAmt));
			//supplierinvoice.setTax(taxAmount);			
			supplierinvoice.setStatus(config.getValue(IConfiguration.INVOICE_STATUS_NEWORDER_VALUE));		
			supplierinvoice.setInvoicetype("1");
			
			updateSuccess=supplierinvoiceBO.updateSupplierinvoice(supplierinvoice);
			
			if(updateSuccess)
			{	
				supplierInvoiceBean.resetEditInvoice();
				listSupplierInvoice();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("supplierinvoice.label.update.success"),null));				
			}		
			}
			
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));				
			log.info("Error in updateInvoice of SupplierInvoiceBeanInfo "+ e.toString());
		}
	}
	
	
	
	
	public void approveInvoice()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		SupplierInvoiceBean supplierInvoiceBean = (SupplierInvoiceBean) BeanContext.getReference("supplierInvoiceBean");				
		boolean updateSuccess=false;		
		try
		{			
			SupplierinvoiceModel supplierinvoice = new SupplierinvoiceModel();
			supplierinvoice=supplierInvoiceBean.getSupplierinvoice();
			ISupplierinvoiceBO supplierinvoiceBO=supplierInvoiceBean.getSupplierinvoiceBO();
							
			supplierinvoice.setApprovedBy(loginBean.getUserName());
			supplierinvoice.setApprovedDate(DateUtil.getTodayDate());					
			supplierinvoice.setStatus(config.getValue(IConfiguration.INVOICE_STATUS_PROCESSED_VALUE));			
			updateSuccess=supplierinvoiceBO.approveSupplierinvoice(supplierinvoice);
			if(updateSuccess)
			{	
				listSupplierInvoice();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("supplierinvoice.label.approved.success"),null));				
			}		
			
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));				
			log.info("Error in approveInvoice of SupplierInvoiceBeanInfo "+ e.toString());
		}
	}
	
	
	
	
	public SupplierinvoiceModel generateInvoice()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		SupplierInvoiceBean supplierInvoiceBean = (SupplierInvoiceBean) BeanContext.getReference("supplierInvoiceBean");			
		List<SupplierinvoicebreakdownModel> supplierinvoicebreakdowns = new ArrayList<SupplierinvoicebreakdownModel>();			
		SupplierinvoiceModel supplierinvoice = new SupplierinvoiceModel();	
		BigDecimal totalInvoiceAmt = new BigDecimal(0);	
		BigDecimal taxAmount = new BigDecimal(0);	
		try
		{
		DeliveryorderModel deliveryorder = new DeliveryorderModel();
		deliveryorder=supplierInvoiceBean.getDeliveryorder();
		supplierinvoice.setSupplier(deliveryorder.getSupplier());
		supplierinvoice.setInvoiceAmount(deliveryorder.getTotalAmount());			
		supplierinvoice.setDeliveryOrderNo(deliveryorder.getDeliveryOrderNo());
		supplierinvoice.setBranch(deliveryorder.getBranch());
		supplierinvoice.setInvoiceNo(deliveryorder.getInvoiceNo());
		
		
		supplierinvoice.getInvoicetaxship().setAddress(deliveryorder.getBranch().getAddress());
		supplierinvoice.getInvoicetaxship().setCity(deliveryorder.getBranch().getCity());
		supplierinvoice.getInvoicetaxship().setState(deliveryorder.getBranch().getState());
		supplierinvoice.getInvoicetaxship().setPostCode(deliveryorder.getBranch().getPostCode());
		supplierinvoice.getInvoicetaxship().setCountry(deliveryorder.getBranch().getCountry());
		
		for(DeliveryorderbreakdownModel productData:deliveryorder.getDeliveryorderbreakdowns())
		{
			        SupplierinvoicebreakdownModel data=new SupplierinvoicebreakdownModel();					
				
					data.setProductId(productData.getProductId());
					data.setProductCode(productData.getProductCode());
					data.setProductName(productData.getProductName());				
					data.setDiscount(productData.getDiscount());
					data.setDiscountAmount(productData.getDiscountAmount());
					data.setUom(productData.getUomName());
					data.setQuantity(productData.getQuantity());			
					data.setSubTotal(productData.getSubTotal());
					data.setUnitPrice(productData.getUnitPrice());	
					data.setTaxAmount(productData.getTaxAmount());
					data.setTaxCode(productData.getTaxCode());
					//data.setPurchasePrice(productData.getPurchasePrice());
					totalInvoiceAmt=totalInvoiceAmt.add(productData.getSubTotal());
					taxAmount=taxAmount.add(productData.getTaxAmount());
					supplierinvoicebreakdowns.add(data);							
		}					
		supplierinvoice.setSupplierinvoicebreakdowns(supplierinvoicebreakdowns);
		supplierinvoice.setInvoiceAmount(totalInvoiceAmt.subtract(taxAmount));
		supplierinvoice.setTax(taxAmount);
		supplierinvoice.setTotalAmount(DecimalUtil.formatRoundupCents(totalInvoiceAmt));
		
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));				
			log.info("Error in generateInvoice of SupplierInvoiceBeanInfo "+ e.toString());
		}
		
		return supplierinvoice;
	}
	
	
	
	public boolean validateInvoiceSearch() {
		boolean valid = true;		
	
		SupplierInvoiceBean supplierInvoiceBean = (SupplierInvoiceBean) BeanContext.getReference("supplierInvoiceBean");
		
		if(supplierInvoiceBean.getInvoiceNo()!=null)
		{
			if(supplierInvoiceBean.getInvoiceNo().equalsIgnoreCase(""))
			{
				supplierInvoiceBean.setInvoiceNo(null);
			}
		}		
		
		if(supplierInvoiceBean.getSupplierId()!=null)
		{
			if(supplierInvoiceBean.getSupplierId()==0)
			{
				supplierInvoiceBean.setSupplierId(null);
			}
		}	
		
		
		if(supplierInvoiceBean.getStatus()!=null)
		{
			if(supplierInvoiceBean.getStatus().equalsIgnoreCase("") || supplierInvoiceBean.getStatus().equalsIgnoreCase("0") )
			{
				supplierInvoiceBean.setStatus(null);
			}
		}		
		
		if(supplierInvoiceBean.getSupplierId()==null &&  supplierInvoiceBean.getDateFrom()==null && supplierInvoiceBean.getDateTo()==null && supplierInvoiceBean.getStatus()==null  && supplierInvoiceBean.getInvoiceNo()==null)
		{	
			valid = false;
		}		
		
		else
		{
			valid = true;
		}			
		return valid;

	}
	
	
	
	public boolean validateInvoice() {
		boolean valid = true;		
		String messageValue = null;
		ISupplierinvoiceBO supplierinvoiceBO=supplierInvoiceBean.getSupplierinvoiceBO();
		SupplierInvoiceBean supplierInvoiceBean = (SupplierInvoiceBean) BeanContext.getReference("supplierInvoiceBean");
	
		try
		{
		if (factoryBean.checkIsNullAssignMessage(supplierInvoiceBean.getSupplierId(),
				"product.label.supplierName", "supplierName")) {
			valid = false;
		}
		if (factoryBean.checkIsZeroAssignMessage(String.valueOf(supplierInvoiceBean.getSupplierId()),"product.label.supplierName", "supplierName")) {
			valid = false;
		}
				
		if (factoryBean.checkIsNullAssignMessage(supplierInvoiceBean.getDeliveryOrderId(),
				"purchaseorder.label.grnno", "deliveryOrderId")) {
			valid = false;
		}
		if (factoryBean.checkIsZeroAssignMessage(String.valueOf(supplierInvoiceBean.getDeliveryOrderId()),"purchaseorder.label.grnno", "deliveryOrderId")) {
			valid = false;
		}
		
		if (factoryBean.checkIsNullAssignMessage(supplierInvoiceBean.getSupplierinvoice().getInvoiceNo(),
				"customerinvoice.taxship.invoiceNo", "invoiceNo")) {
			valid = false;
		}
		
		
		else {
			if (supplierInvoiceBean.getAction().equalsIgnoreCase("submit")) {
				if (supplierinvoiceBO.findInvoiceNoExites(supplierInvoiceBean.getSupplierinvoice().getInvoiceNo())) {
					messageValue = factoryBean.getErrorFactory().getError("supplier.errors.invoice.exists");
					factoryBean.reportErrorToMessageHandler(messageValue,messageValue, "invoiceNo");
					valid = false;
				}
			}else{  
				
				if(! supplierInvoiceBean.getSupplierinvoice().getInvoiceNo().equalsIgnoreCase(supplierInvoiceBean.getSupplierinvoice().getInvoiceOldNo()) ){
											
					if (supplierinvoiceBO.findInvoiceNoExites(supplierInvoiceBean.getSupplierinvoice().getInvoiceNo())) {
						messageValue = factoryBean.getErrorFactory().getError("supplier.errors.invoice.exists");
						factoryBean.reportErrorToMessageHandler(messageValue,messageValue, "invoiceNo");
						valid = false;
					}
				}
			}
		}
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"invoicePanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));				
			log.info("Error in generateInvoice of SupplierInvoiceBeanInfo "+ e.toString());
		}
	
		
		return valid;
	}
				
}
