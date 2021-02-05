package com.project.bean.payment.collection;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.bo.interfaces.IBranchinvoiceBO;
import com.project.bo.interfaces.IPaymentcollectionBO;
import com.project.bo.interfaces.ISalesorderBO;
import com.project.model.invoice.branch.BranchinvoiceModel;
import com.project.model.invoice.customer.CustomerinvoiceModel;
import com.project.model.payment.collection.PaymentcollectionModel;
import com.project.model.payment.collection.PaymentcollectionapportionModel;
import com.project.model.sale.sales.SalesorderModel;
import com.project.util.DateUtil;
import com.project.bean.delivery.DeliveryOrderBean;
import com.project.bean.invoice.BranchInvoiceBean;
import com.project.bean.invoice.BranchInvoiceBeanInfo;
import com.project.bean.purchase.PurchaseRequestBean;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.factory.ErrorFactory;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.common.validation.ValidatorUtil;
import com.project.home.ProjectHome;
import com.project.login.LoginBean;

public class PaymentcollectionBeanInfo {
	
	
	PaymentcollectionBean paymentcollectionBean = (PaymentcollectionBean) BeanContext.getReference("paymentcollectionBean");
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
	
	public void listCollection() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/paymentcollection/paymentCollectionList.xhtml");
		projectHome.setTitlePage("Payment --> Receivable --> Branch/customer Collection");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}

	
	
	public void newCollection() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/paymentcollection/addEditPaymentCollection.xhtml");
		projectHome.setTitlePage("Payment --> Receivable --> Add/Edit Branch/Customer Collection");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
	public void editCollection() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/paymentcollection/addEditPaymentCollection.xhtml");
		projectHome.setTitlePage("Payment --> Receivable --> Add/Edit Branch/Customer Collection");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
	public void collectionApportion() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/paymentcollection/addEditCollectionApportion.xhtml");
		projectHome.setTitlePage("Payment --> Receivable --> Branch/Customer Collection Apportion");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
	public void reverCollectionConfirm() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/paymentcollection/addEditCollectionRevert.xhtml");
		projectHome.setTitlePage("Payment --> Receivable --> Branch/Customer Collection Revert");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
	
	
	
	public void saveCollection()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		PaymentcollectionBean paymentcollectionBean = (PaymentcollectionBean) BeanContext.getReference("paymentcollectionBean");				
		boolean saveSuccess=false;
		
		PaymentcollectionModel paymentcollection = paymentcollectionBean.getPaymentcollection();
		IPaymentcollectionBO paymentcollectionBO = paymentcollectionBean.getPaymentcollectionBO();		
		List<PaymentcollectionapportionModel> paymentcollectionapportions = new ArrayList<PaymentcollectionapportionModel>();

		try
		{
			if(this.validateCollectionSave()){
				
				paymentcollection.setProcessedBy(loginBean.getUserName());
				paymentcollection.setStatus(config.getValue(IConfiguration.COLLECTION_STATUS_APPROVED_VALUE));
				paymentcollection.setClearAmount(new BigDecimal(0.0));
				paymentcollection.setBranchRecordId(loginBean.getBranch().getBranchId());
				paymentcollection.setApprovedBy(loginBean.getUserName());
				paymentcollection.setApprovedDate(new Date());
				paymentcollection.setCollectionDate(new Date());
				
				for (BranchinvoiceModel amountPaid : paymentcollectionBean.getInvoiceList()) {
					BigDecimal bilamont = new BigDecimal(0);
					BigDecimal apportionbalanceamount = new BigDecimal(0);
								
					if (ValidatorUtil.isNotNull(amountPaid.getPendingAmount())) 
						{
						bilamont = amountPaid.getPendingAmount();
						}				
					if (ValidatorUtil.isNotNull(paymentcollectionBean.getReceivalbeAmount().get(amountPaid.getInvoiceNo()))) 
					{
						apportionbalanceamount = new BigDecimal(paymentcollectionBean.getReceivalbeAmount().get(amountPaid.getInvoiceNo()));
								
					if (apportionbalanceamount.doubleValue()!=0) {				
						PaymentcollectionapportionModel PaymentcollectionapportionObj = new PaymentcollectionapportionModel();				
						PaymentcollectionapportionObj.setAllocatedAmount(apportionbalanceamount);
						PaymentcollectionapportionObj.setAllocatedBy(loginBean.getUserName());
						PaymentcollectionapportionObj.setAllocatedDate(new Date());
						//PaymentcollectionapportionObj.setPaymentcollection(paymentcollection);
						PaymentcollectionapportionObj.setBranchinvoice(amountPaid);						
						paymentcollectionapportions.add(PaymentcollectionapportionObj);
					}	
					}					
				}				
				paymentcollection.setPaymentcollectionapportions(paymentcollectionapportions);
				saveSuccess=paymentcollectionBO.createNewPaymentcollection(paymentcollection);
			
			if(saveSuccess)
			{				
				listCollection();
				paymentcollectionBean.resetCollection();		
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"collectionPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("paymentcollection.label.created.success"),null));				
			}
			}
			
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"collectionPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));				
			log.info("Error in saveCollection of PaymentcollectionBeanInfo "+ e.toString());
		}
	}
	
	
	
	public void updateCollection()
	{
		FacesContext context = FacesContext.getCurrentInstance();	
		PaymentcollectionBean paymentcollectionBean = (PaymentcollectionBean) BeanContext.getReference("paymentcollectionBean");
		boolean updateSuccess=false;		
		PaymentcollectionModel paymentcollection = paymentcollectionBean.getPaymentcollection();
		IPaymentcollectionBO paymentcollectionBO = paymentcollectionBean.getPaymentcollectionBO();
		try
		{
			if(this.validateCollectionSave()){				
				paymentcollection.setProcessedBy(loginBean.getUserName());
				paymentcollection.setClearAmount(new BigDecimal(0.0));
				paymentcollection.setStatus(config.getValue(IConfiguration.COLLECTION_STATUS_NEWORDER_VALUE));
				updateSuccess=paymentcollectionBO.updatePaymentcollection(paymentcollection);			
			if(updateSuccess)
			{				
				listCollection();
				paymentcollectionBean.resetCollection();		
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"collectionPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("paymentcollection.label.update.success"),null));				
			}
			}
			
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"collectionPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));				
			log.info("Error in updateCollection of PaymentcollectionBeanInfo "+ e.toString());
		}
	}
	
	
	
	
	public void approveCollection()
	{
		FacesContext context = FacesContext.getCurrentInstance();	
		PaymentcollectionBean paymentcollectionBean = (PaymentcollectionBean) BeanContext.getReference("paymentcollectionBean");
		boolean updateSuccess=false;
		PaymentcollectionModel paymentcollection = paymentcollectionBean.getPaymentcollection();
		IPaymentcollectionBO paymentcollectionBO = paymentcollectionBean.getPaymentcollectionBO();
		try
		{			
			paymentcollection.setApprovedBy(loginBean.getUserName());
			paymentcollection.setApprovedDate(new Date());
			paymentcollection.setStatus(config.getValue(IConfiguration.COLLECTION_STATUS_APPROVED_VALUE));
			updateSuccess=paymentcollectionBO.updatePaymentcollection(paymentcollection);			
			
			if(updateSuccess)
			{	
				listCollection();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"collectionPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("paymentcollection.label.approved.success"),null));				
			}					
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"collectionPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));				
			log.info("Error in approveCollection of PaymentcollectionBeanInfo "+ e.toString());
		}
	}
	
	
	
	public void approveApportionCompleteCollection()
	{
		FacesContext context = FacesContext.getCurrentInstance();	
		PaymentcollectionBean paymentcollectionBean = (PaymentcollectionBean) BeanContext.getReference("paymentcollectionBean");
		boolean updateSuccess=false;
		PaymentcollectionModel paymentcollection = paymentcollectionBean.getPaymentcollection();
		IPaymentcollectionBO paymentcollectionBO = paymentcollectionBean.getPaymentcollectionBO();
		try
		{			
			paymentcollection.setApprovedBy(loginBean.getUserName());
			paymentcollection.setApprovedDate(new Date());
			paymentcollection.setStatus(config.getValue(IConfiguration.COLLECTION_STATUS_CLOSED_VALUE));
			updateSuccess=paymentcollectionBO.updatePaymentcollection(paymentcollection);			
			
			if(updateSuccess)
			{	
				listCollection();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"collectionPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("paymentcollection.label.approved.success"),null));				
			}					
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"collectionPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));				
			log.info("Error in approveCollection of PaymentcollectionBeanInfo "+ e.toString());
		}
	}
	
	
	
	public boolean validateCollectionSave()
	{
		boolean valid = true;
		PaymentcollectionBean paymentcollectionBean = (PaymentcollectionBean) BeanContext.getReference("paymentcollectionBean");
		FacesContext context = FacesContext.getCurrentInstance();	
		try
		{
			
			if (factoryBean.checkIsNullAssignMessage(paymentcollectionBean.getPaymentcollection().getCollectionType(),
					"paymentcollection.label.collectionType", "collectionType")) {
				valid = false;
				paymentcollectionBean.setSaveConfirm(false);				
			}	
			else if (factoryBean.checkIsZeroAssignMessage(paymentcollectionBean.getPaymentcollection().getCollectionType(),
					"paymentcollection.label.collectionType", "collectionType")) {
					valid = false;
					paymentcollectionBean.setSaveConfirm(false);
				}
			
			
			else if(paymentcollectionBean.getPaymentcollection().getCollectionType().equalsIgnoreCase("1"))
			{
				if (factoryBean.checkIsNullAssignMessage(paymentcollectionBean.getBranchId(),
						"paymentcollection.label.branchName", "branchId")) {
					valid = false;
					paymentcollectionBean.setSaveConfirm(false);				
				}	
				else if (factoryBean.checkIsZeroAssignMessage(""+paymentcollectionBean.getBranchId(),
						"paymentcollection.label.branchName", "branchId")) {
						valid = false;
						paymentcollectionBean.setSaveConfirm(false);
					}
			}
			
			
			else if(paymentcollectionBean.getPaymentcollection().getCollectionType().equalsIgnoreCase("2"))
			{
				if (factoryBean.checkIsNullAssignMessage(paymentcollectionBean.getCustomer(),
						"paymentcollection.label.customerName", "customerName")) {
					valid = false;
					paymentcollectionBean.setSaveConfirm(false);				
				}	
				else if (factoryBean.checkIsZeroAssignMessage(""+paymentcollectionBean.getCustomer().getCustomerId(),
						"paymentcollection.label.customerName", "customerName")) {
						valid = false;
						paymentcollectionBean.setSaveConfirm(false);
					}
			}
			
			if (factoryBean.checkIsNullAssignMessage(paymentcollectionBean.getPaymentcollection().getCollectionDate(),
					"paymentcollection.label.processedDate", "processedDate")) {
				valid = false;
				paymentcollectionBean.setSaveConfirm(false);
			}	
			if (factoryBean.checkIsNullAssignMessage(paymentcollectionBean.getPaymentcollection().getPaymentMode(),
					"paymentcollection.label.paymentMode", "paymentMode")) {
				valid = false;
				paymentcollectionBean.setSaveConfirm(false);
				
			}	
			
			else if (factoryBean.checkIsZeroAssignMessage(paymentcollectionBean.getPaymentcollection().getPaymentMode(),
					"paymentcollection.label.paymentMode", "paymentMode")) {
					valid = false;
					paymentcollectionBean.setSaveConfirm(false);
				}	
			
			if (factoryBean.checkIsNullAssignMessage(paymentcollectionBean.getPaymentcollection().getReferenceNumber(),
					"paymentcollection.label.referenceNumber", "referenceNumber")) {
				valid = false;
				paymentcollectionBean.setSaveConfirm(false);
			}	
			if (factoryBean.checkIsNullAssignMessage(paymentcollectionBean.getPaymentcollection().getCollectionAmount(),
					"paymentcollection.label.collectionAmount", "collectionAmount")) {
				valid = false;
				paymentcollectionBean.setSaveConfirm(false);
						
			}	
			else if (factoryBean.checkIsZeroAssignMessage(""+paymentcollectionBean.getPaymentcollection().getCollectionAmount(),
					"paymentcollection.label.collectionAmount", "collectionAmount")) {
					valid = false;
					paymentcollectionBean.setSaveConfirm(false);
				}	
			
			if(paymentcollectionBean.getInvoiceList().size()==0)
			{
				context.addMessage(UIComponent.findComponent(context.getViewRoot(), "dataTable").getClientId(context), new FacesMessage(
				FacesMessage.SEVERITY_ERROR, "No Invoice Records Found!", null));	
				valid = false;
				paymentcollectionBean.setSaveConfirm(false);
			}
			
		}
		catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"collectionPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		
		}	
		
		return valid;
	}
	
	
	
	
	public boolean validateCollectionSearch() {
		boolean valid = true;		
	
		PaymentcollectionBean paymentcollectionBean = (PaymentcollectionBean) BeanContext.getReference("paymentcollectionBean");
		
		if(paymentcollectionBean.getPaymentNo()!=null)
		{
			if(paymentcollectionBean.getPaymentNo().equalsIgnoreCase(""))
			{
				paymentcollectionBean.setPaymentNo(null);
			}
		}		
		
		if(paymentcollectionBean.getBranchId()!=null)
		{
			if(paymentcollectionBean.getBranchId()==0)
			{
				paymentcollectionBean.setBranchId(null);
			}
		}	
		
		
		if(paymentcollectionBean.getCustomerId()!=null)
		{
			if(paymentcollectionBean.getCustomerId()==0)
			{
				paymentcollectionBean.setCustomerId(null);
			}
		}	
		
		
		if(paymentcollectionBean.getStatus()!=null)
		{
			if(paymentcollectionBean.getStatus().equalsIgnoreCase("") || paymentcollectionBean.getStatus().equalsIgnoreCase("0") )
			{
				paymentcollectionBean.setStatus(null);
			}
		}		
		
		if(paymentcollectionBean.getBranchId()==null &&  paymentcollectionBean.getDateFrom()==null &&paymentcollectionBean.getDateTo()==null && paymentcollectionBean.getStatus()==null  && paymentcollectionBean.getPaymentNo()==null)
		{	
			valid = false;
		}		
		
		else
		{
			valid = true;
		}			
		return valid;

	}
	
	
	public void paymentCollectionApportion()
	{
		PaymentcollectionBean paymentcollectionBean = (PaymentcollectionBean) BeanContext.getReference("paymentcollectionBean");
		PaymentcollectionModel paymentcollection = paymentcollectionBean.getPaymentcollection();
		IPaymentcollectionBO paymentcollectionBO = paymentcollectionBean.getPaymentcollectionBO();
		FacesContext context = FacesContext.getCurrentInstance();
		List<PaymentcollectionapportionModel> paymentcollectionapportions = new ArrayList<PaymentcollectionapportionModel>();

		boolean updateSuccess=false;
		try
		{
		for (BranchinvoiceModel amountPaid : paymentcollectionBean.getInvoiceList()) {
			BigDecimal bilamont = new BigDecimal(0);
			BigDecimal apportionbalanceamount = new BigDecimal(0);
						
			if (ValidatorUtil.isNotNull(amountPaid.getPendingAmount())) 
				{
				bilamont = amountPaid.getPendingAmount();
				}				
			if (ValidatorUtil.isNotNull(paymentcollectionBean.getReceivalbeAmount().get(amountPaid.getInvoiceNo()))) 
			{
				apportionbalanceamount = new BigDecimal(paymentcollectionBean.getReceivalbeAmount().get(amountPaid.getInvoiceNo()));
						
			if (apportionbalanceamount.doubleValue()!=0) {				
				PaymentcollectionapportionModel PaymentcollectionapportionObj = new PaymentcollectionapportionModel();				
				PaymentcollectionapportionObj.setAllocatedAmount(apportionbalanceamount);
				PaymentcollectionapportionObj.setAllocatedBy(loginBean.getUserName());
				PaymentcollectionapportionObj.setAllocatedDate(new Date());
				PaymentcollectionapportionObj.setPaymentcollection(paymentcollection);
				PaymentcollectionapportionObj.setBranchinvoice(amountPaid);
				
				paymentcollectionapportions.add(PaymentcollectionapportionObj);
			}	
			}
			
		}
		
		updateSuccess=paymentcollectionBO.createNewPaymentcollectionapportion(paymentcollectionapportions, paymentcollection);
		if(updateSuccess)
		{	
			listCollection();
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"collectionPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("paymentcollection.label.apportion.success"),null));				
		}		
		
		
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"collectionPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

		}
	}
	
	
	
	public void paymentCollectionRevert()
	{
		PaymentcollectionBean paymentcollectionBean = (PaymentcollectionBean) BeanContext.getReference("paymentcollectionBean");
		PaymentcollectionModel paymentcollection = paymentcollectionBean.getPaymentcollection();
		IPaymentcollectionBO paymentcollectionBO = paymentcollectionBean.getPaymentcollectionBO();
		FacesContext context = FacesContext.getCurrentInstance();

		boolean updateSuccess=false;
		try
		{				
		updateSuccess=paymentcollectionBO.revertPaymentcollectionapportion(paymentcollectionBean.getPaymentcollectionapportions(), paymentcollection);
		if(updateSuccess)
		{	
			listCollection();
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"collectionPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("paymentcollection.label.revert.success"),null));				
		}				
		
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"collectionPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

		}
	}


	
	
	
}
