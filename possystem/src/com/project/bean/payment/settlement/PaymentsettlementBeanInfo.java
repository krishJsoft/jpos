package com.project.bean.payment.settlement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.bo.interfaces.IPaymentcollectionBO;
import com.project.bo.interfaces.IPaymentsettlementBO;
import com.project.model.invoice.branch.BranchinvoiceModel;
import com.project.model.invoice.supplier.SupplierinvoiceModel;
import com.project.model.payment.collection.PaymentcollectionModel;
import com.project.model.payment.collection.PaymentcollectionapportionModel;
import com.project.model.payment.settlement.PaymentsettlementModel;
import com.project.model.payment.settlement.PaymentsettlementapportionModel;
import com.project.bean.delivery.DeliveryOrderBean;
import com.project.bean.invoice.BranchInvoiceBeanInfo;
import com.project.bean.payment.collection.PaymentcollectionBean;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.factory.ErrorFactory;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.common.validation.ValidatorUtil;
import com.project.home.ProjectHome;
import com.project.login.LoginBean;

public class PaymentsettlementBeanInfo {
	PaymentsettlementBean paymentsettlementBean = (PaymentsettlementBean) BeanContext.getReference("paymentsettlementBean");
	
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	public static Logger log = LoggerFactory.getLogger(BranchInvoiceBeanInfo.class);
	FacesContext context = FacesContext.getCurrentInstance();
	Configuration config = Configuration.getConfiguration();
	String projectHome = config.getValue(IConfiguration.PROJECT_HOME_FILENAME);
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	static ErrorFactory errorFactory = ErrorFactory.getInstance();

	Integer balanceQuantity = 0;
	Integer receivedQuantity = 0;
	
	public void listSettlement() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/paymentsettlement/paymentSettlementList.xhtml");
		projectHome.setTitlePage("Payment --> Payable --> Supplier Settlement");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}

	
	
	public void newSettlement() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/paymentsettlement/addEditPaymentSettlement.xhtml");
		projectHome.setTitlePage("Payment --> Payable --> Add/Edit Supplier Settlement");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
	
	public void editSettlement() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/paymentsettlement/addEditPaymentSettlement.xhtml");
		projectHome.setTitlePage("Payment --> Payable --> Add/Edit Supplier Settlement");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
	public void settlementApportion() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/paymentsettlement/addEditSettlementApportion.xhtml");
		projectHome.setTitlePage("Payment --> Payable --> Supplier Settlement Apportion");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
	public void reverSettlementConfirm() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		ProjectHome projectHome = (ProjectHome) BeanContext.getReference("projectHome");
		projectHome.setContentpage("/paymentsettlement/addEditSettlementRevert.xhtml");
		projectHome.setTitlePage("Payment --> Payable --> Supplier Settlement Revert");
		navHandler.handleNavigation(context, null, projectHome+ "?faces-redirect=true");
	}
	
	
	
	
	public void saveSettlement()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		PaymentsettlementBean paymentsettlementBean = (PaymentsettlementBean) BeanContext.getReference("paymentsettlementBean");				
		boolean saveSuccess=false;
		
		List<PaymentsettlementapportionModel> paymentcollectionapportions = new ArrayList<PaymentsettlementapportionModel>();
		PaymentsettlementModel paymentsettlement = paymentsettlementBean.getPaymentsettlement();
		IPaymentsettlementBO paymentsettlementBO = paymentsettlementBean.getPaymentsettlementBO();
		try
		{
			if(this.validateSettlementSave()){
				
				paymentsettlement.setProcessedBy(loginBean.getUserName());
				paymentsettlement.setStatus(config.getValue(IConfiguration.COLLECTION_STATUS_NEWORDER_VALUE));
				paymentsettlement.setClearAmount(new BigDecimal(0.0));
				paymentsettlement.setBranchRecordId(loginBean.getBranch().getBranchId());
				
				
				for (SupplierinvoiceModel supplierInvoice : paymentsettlementBean.getInvoiceList()) {
					BigDecimal bilamont = new BigDecimal(0);
					BigDecimal apportionbalanceamount = new BigDecimal(0);
								
					if (ValidatorUtil.isNotNull(supplierInvoice.getPendingAmount())) 
						{
						bilamont = supplierInvoice.getPendingAmount();
						}				
					if (ValidatorUtil.isNotNull(paymentsettlementBean.getReceivalbeAmount().get(supplierInvoice.getInvoiceNo()))) 
					{
						apportionbalanceamount = new BigDecimal(paymentsettlementBean.getReceivalbeAmount().get(supplierInvoice.getInvoiceNo()));
								
					if (apportionbalanceamount.doubleValue()!=0) {				
						PaymentsettlementapportionModel PaymentcollectionapportionObj = new PaymentsettlementapportionModel();				
						PaymentcollectionapportionObj.setAllocatedAmount(apportionbalanceamount);
						PaymentcollectionapportionObj.setAllocatedBy(loginBean.getUserName());
						PaymentcollectionapportionObj.setAllocatedDate(new Date());
						//PaymentcollectionapportionObj.setPaymentsettlement(paymentsettlement);
						PaymentcollectionapportionObj.setSupplierinvoice(supplierInvoice);
						
						paymentcollectionapportions.add(PaymentcollectionapportionObj);
					}	
					}			
				}		
				//paymentsettlement.setSettlementAmount(paymentsettlement.getSettlementAmount());
				
				paymentsettlement.setPaymentsettlementapportions(paymentcollectionapportions);				
				saveSuccess=paymentsettlementBO.createNewPaymentsettlement(paymentsettlement);
			
			if(saveSuccess)
			{				
				listSettlement();
				paymentsettlementBean.resetSettlement();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"settlementPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("paymentsettlement.label.created.success"),null));				
			}
			}
			
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"settlementPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));				
			log.info("Error in saveSettlement of PaymentsettlementBeanInfo "+ e.toString());
		}
	}
	
	
	
	public void updateSettlement()
	{
		FacesContext context = FacesContext.getCurrentInstance();	
		PaymentsettlementBean paymentsettlementBean = (PaymentsettlementBean) BeanContext.getReference("paymentsettlementBean");
		boolean updateSuccess=false;		
		PaymentsettlementModel paymentsettlement = paymentsettlementBean.getPaymentsettlement();
		IPaymentsettlementBO paymentsettlementBO = paymentsettlementBean.getPaymentsettlementBO();
		try
		{
			if(this.validateSettlementSave()){				
				paymentsettlement.setProcessedBy(loginBean.getUserName());
				paymentsettlement.setClearAmount(new BigDecimal(0.0));
				paymentsettlement.setStatus(config.getValue(IConfiguration.COLLECTION_STATUS_NEWORDER_VALUE));
				updateSuccess=paymentsettlementBO.updatePaymentsettlement(paymentsettlement);
			if(updateSuccess)
			{				
				listSettlement();
				paymentsettlementBean.resetSettlement();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"settlementPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("paymentsettlement.label.update.success"),null));				
			}
			}
			
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"settlementPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));				
			log.info("Error in updateSettlement of PaymentsettlementBeanInfo "+ e.toString());
		}
	}
	
	
	
	
	public void approveSettlement()
	{
		FacesContext context = FacesContext.getCurrentInstance();	
		PaymentsettlementBean paymentsettlementBean = (PaymentsettlementBean) BeanContext.getReference("paymentsettlementBean");
		boolean updateSuccess=false;
		PaymentsettlementModel paymentsettlement = paymentsettlementBean.getPaymentsettlement();
		IPaymentsettlementBO paymentsettlementBO = paymentsettlementBean.getPaymentsettlementBO();
		try
		{			
			paymentsettlement.setApprovedBy(loginBean.getUserName());
			paymentsettlement.setApprovedDate(new Date());
			paymentsettlement.setStatus(config.getValue(IConfiguration.COLLECTION_STATUS_APPROVED_VALUE));
			updateSuccess=paymentsettlementBO.updatePaymentsettlement(paymentsettlement);			
			
			if(updateSuccess)
			{	
				listSettlement();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"settlementPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("paymentsettlement.label.approved.success"),null));				
			}					
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"settlementPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));				
			log.info("Error in approveSettlement of PaymentsettlementBeanInfo "+ e.toString());
		}
	}
	
	
	
	public void settleSettlement()
	{
		FacesContext context = FacesContext.getCurrentInstance();	
		PaymentsettlementBean paymentsettlementBean = (PaymentsettlementBean) BeanContext.getReference("paymentsettlementBean");
		boolean updateSuccess=false;
		PaymentsettlementModel paymentsettlement = paymentsettlementBean.getPaymentsettlement();
		IPaymentsettlementBO paymentsettlementBO = paymentsettlementBean.getPaymentsettlementBO();
		try
		{			
			paymentsettlement.setApprovedBy(loginBean.getUserName());
			paymentsettlement.setApprovedDate(new Date());
			paymentsettlement.setStatus(config.getValue(IConfiguration.COLLECTION_STATUS_SETTLED_VALUE));
			updateSuccess=paymentsettlementBO.updatePaymentsettlement(paymentsettlement);			
			
			if(updateSuccess)
			{	
				listSettlement();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"settlementPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("paymentsettlement.label.approved.success"),null));				
			}					
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"settlementPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));				
			log.info("Error in approveSettlement of PaymentsettlementBeanInfo "+ e.toString());
		}
	}
	
	
	public boolean validateSettlementSave()
	{
		boolean valid = true;
		PaymentsettlementBean paymentsettlementBean = (PaymentsettlementBean) BeanContext.getReference("paymentsettlementBean");
		FacesContext context = FacesContext.getCurrentInstance();	
		try
		{
			
			
			if (factoryBean.checkIsNullAssignMessage(paymentsettlementBean.getPaymentsettlement().getSettlementType(),
					"paymentsettlement.label.settlementType", "collectionType")) {
				valid = false;
				paymentsettlementBean.setSaveConfirm(false);				
			}	
			else if (factoryBean.checkIsZeroAssignMessage(paymentsettlementBean.getPaymentsettlement().getSettlementType(),
					"paymentsettlement.label.settlementType", "collectionType")) {
					valid = false;
					paymentsettlementBean.setSaveConfirm(false);
				}
			
			else if(paymentsettlementBean.getPaymentsettlement().getSettlementType().equalsIgnoreCase("1"))
			{

				if (factoryBean.checkIsNullAssignMessage(paymentsettlementBean.getSupplierId(),
						"paymentsettlement.label.supplierName", "supplierName")) {
					valid = false;
					paymentsettlementBean.setSaveConfirm(false);				
				}	
				else if (factoryBean.checkIsZeroAssignMessage(""+paymentsettlementBean.getSupplierId(),
						"paymentsettlement.label.supplierName", "supplierName")) {
						valid = false;
						paymentsettlementBean.setSaveConfirm(false);
					}
			
			}
			
			else if(paymentsettlementBean.getPaymentsettlement().getSettlementType().equalsIgnoreCase("1"))
			{
				if (factoryBean.checkIsNullAssignMessage(paymentsettlementBean.getSupplybranchId(),
						"paymentcollection.label.branchName", "branchId")) {
					valid = false;
					paymentsettlementBean.setSaveConfirm(false);				
				}	
				else if (factoryBean.checkIsZeroAssignMessage(""+paymentsettlementBean.getSupplybranchId(),
						"paymentcollection.label.branchName", "branchId")) {
						valid = false;
						paymentsettlementBean.setSaveConfirm(false);
					}
			}
			
			
			
			if (factoryBean.checkIsNullAssignMessage(paymentsettlementBean.getPaymentsettlement().getSettlementDate(),
					"paymentsettlement.label.processedDate", "processedDate")) {
				valid = false;
				paymentsettlementBean.setSaveConfirm(false);
			}	
			if (factoryBean.checkIsNullAssignMessage(paymentsettlementBean.getPaymentsettlement().getPaymentMode(),
					"paymentsettlement.label.paymentMode", "paymentMode")) {
				valid = false;paymentsettlementBean.setSaveConfirm(false);
				
			}	
			
			else if (factoryBean.checkIsZeroAssignMessage(paymentsettlementBean.getPaymentsettlement().getPaymentMode(),
					"paymentsettlement.label.paymentMode", "paymentMode")) {
					valid = false;
					paymentsettlementBean.setSaveConfirm(false);
				}	
			
			if (factoryBean.checkIsNullAssignMessage(paymentsettlementBean.getPaymentsettlement().getReferenceNumber(),
					"paymentsettlement.label.referenceNumber", "referenceNumber")) {
				valid = false;
				paymentsettlementBean.setSaveConfirm(false);
			}	
		
			/*if (factoryBean.checkIsNullAssignMessage(paymentsettlementBean.getPaymentsettlement().getSettlementAmount(),
					"paymentsettlement.label.settlementAmount", "collectionAmount")) {
				valid = false;
				paymentsettlementBean.setSaveConfirm(false);
						
			}	
			else if (factoryBean.checkIsZeroAssignMessage(""+paymentsettlementBean.getPaymentsettlement().getSettlementAmount(),
					"paymentsettlement.label.settlementAmount", "collectionAmount")) {
					valid = false;
					paymentsettlementBean.setSaveConfirm(false);
				}	*/
			
			if(paymentsettlementBean.getPaymentsettlement().getSettlementAmount()==null)
			{
				context.addMessage(UIComponent.findComponent(context.getViewRoot(), "dataTable").getClientId(context), new FacesMessage(
				FacesMessage.SEVERITY_ERROR, "No Paid Invoice Records Found!", null));	
				valid = false;
				paymentsettlementBean.setSaveConfirm(false);
			}
			
			if(paymentsettlementBean.getPaymentsettlement().getSettlementAmount().doubleValue()==0)
			{
				context.addMessage(UIComponent.findComponent(context.getViewRoot(), "dataTable").getClientId(context), new FacesMessage(
				FacesMessage.SEVERITY_ERROR, "No Paid Invoice Records Found!", null));	
				valid = false;
				paymentsettlementBean.setSaveConfirm(false);
			}			
			if(paymentsettlementBean.getInvoiceList().size()==0)
			{
				context.addMessage(UIComponent.findComponent(context.getViewRoot(), "dataTable").getClientId(context), new FacesMessage(
				FacesMessage.SEVERITY_ERROR, "No Invoice Records Found!", null));	
				valid = false;
				paymentsettlementBean.setSaveConfirm(false);
			}
			
		}
		catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"settlementPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		
		}	
		
		return valid;
	}
	
	
	
	
	public boolean validateSettlementSearch() {
		boolean valid = true;		
	
		PaymentsettlementBean paymentsettlementBean = (PaymentsettlementBean) BeanContext.getReference("paymentsettlementBean");
		
		if(paymentsettlementBean.getPaymentNo()!=null)
		{
			if(paymentsettlementBean.getPaymentNo().equalsIgnoreCase(""))
			{
				paymentsettlementBean.setPaymentNo(null);
			}
		}		
		
		if(paymentsettlementBean.getSupplierId()!=null)
		{
			if(paymentsettlementBean.getSupplierId()==0)
			{
				paymentsettlementBean.setSupplierId(null);
			}
		}	
		
		
		
		
		if(paymentsettlementBean.getStatus()!=null)
		{
			if(paymentsettlementBean.getStatus().equalsIgnoreCase("") || paymentsettlementBean.getStatus().equalsIgnoreCase("0") )
			{
				paymentsettlementBean.setStatus(null);
			}
		}		
		
		if(paymentsettlementBean.getSupplierId()==null &&  paymentsettlementBean.getDateFrom()==null &&paymentsettlementBean.getDateTo()==null && paymentsettlementBean.getStatus()==null  && paymentsettlementBean.getPaymentNo()==null)
		{	
			valid = false;
		}		
		
		else
		{
			valid = true;
		}			
		return valid;

	}
	
	
	public void paymentSettlementApportion()
	{
		PaymentsettlementBean paymentsettlementBean = (PaymentsettlementBean) BeanContext.getReference("paymentsettlementBean");
		PaymentsettlementModel paymentsettlement = paymentsettlementBean.getPaymentsettlement();
		IPaymentsettlementBO paymentsettlementBO = paymentsettlementBean.getPaymentsettlementBO();
		FacesContext context = FacesContext.getCurrentInstance();
		List<PaymentsettlementapportionModel> paymentcollectionapportions = new ArrayList<PaymentsettlementapportionModel>();

		boolean updateSuccess=false;
		try
		{
		for (SupplierinvoiceModel supplierInvoice : paymentsettlementBean.getInvoiceList()) {
			BigDecimal bilamont = new BigDecimal(0);
			BigDecimal apportionbalanceamount = new BigDecimal(0);
						
			if (ValidatorUtil.isNotNull(supplierInvoice.getPendingAmount())) 
				{
				bilamont = supplierInvoice.getPendingAmount();
				}				
			if (ValidatorUtil.isNotNull(paymentsettlementBean.getReceivalbeAmount().get(supplierInvoice.getInvoiceNo()))) 
			{
				apportionbalanceamount = new BigDecimal(paymentsettlementBean.getReceivalbeAmount().get(supplierInvoice.getInvoiceNo()));
						
			if (apportionbalanceamount.doubleValue()!=0) {				
				PaymentsettlementapportionModel PaymentcollectionapportionObj = new PaymentsettlementapportionModel();				
				PaymentcollectionapportionObj.setAllocatedAmount(apportionbalanceamount);
				PaymentcollectionapportionObj.setAllocatedBy(loginBean.getUserName());
				PaymentcollectionapportionObj.setAllocatedDate(new Date());
				PaymentcollectionapportionObj.setPaymentsettlement(paymentsettlement);
				PaymentcollectionapportionObj.setSupplierinvoice(supplierInvoice);
				
				paymentcollectionapportions.add(PaymentcollectionapportionObj);
			}	
			}			
		}		
		updateSuccess=paymentsettlementBO.createNewPaymentsettlementapportion(paymentcollectionapportions, paymentsettlement);
		if(updateSuccess)
		{	
			listSettlement();
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"settlementPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("paymentsettlement.label.apportion.success"),null));				
		}			
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"settlementPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
	}
	
	
	
	public void paymentSettlementRevert()
	{
		PaymentsettlementBean paymentsettlementBean = (PaymentsettlementBean) BeanContext.getReference("paymentsettlementBean");
		PaymentsettlementModel paymentsettlement = paymentsettlementBean.getPaymentsettlement();
		IPaymentsettlementBO paymentsettlementBO = paymentsettlementBean.getPaymentsettlementBO();
		FacesContext context = FacesContext.getCurrentInstance();

		boolean updateSuccess=false;
		try
		{				
		updateSuccess=paymentsettlementBO.revertPaymentsettlementapportion(paymentsettlementBean.getPaymentsettlementapportions(), paymentsettlement);
		if(updateSuccess)
		{	
			listSettlement();
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"settlementPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("paymentsettlement.label.revert.success"),null));				
		}				
		
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"settlementPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

		}
	}


	
	
	
}
