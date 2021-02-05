package com.project.bean.payment.settlement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.data.PageEvent;
import org.primefaces.model.LazyDataModel;

import com.project.bo.interfaces.IBranchBO;
import com.project.bo.interfaces.IBranchinvoiceBO;
import com.project.bo.interfaces.ICustomerBO;
import com.project.bo.interfaces.IPaymentcollectionBO;
import com.project.bo.interfaces.IPaymentsettlementBO;
import com.project.bo.interfaces.ISupplierBO;
import com.project.bo.interfaces.ISupplierinvoiceBO;

import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.CustomerModel;
import com.project.model.invoice.branch.BranchinvoiceModel;
import com.project.model.invoice.branch.BranchinvoicebreakdownModel;
import com.project.model.invoice.supplier.SupplierinvoiceModel;
import com.project.model.payment.collection.PaymentcollectionModel;
import com.project.model.payment.collection.PaymentcollectionapportionModel;
import com.project.model.payment.settlement.PaymentsettlementModel;
/*
 * @author Gopal Ar
 * @version 1.0
 * @since 12 Dec 2013
 * 
 */
import com.project.model.payment.settlement.PaymentsettlementapportionModel;
import com.project.bean.admin.CommonListBeanInfo;
import com.project.bean.payment.collection.PaymentcollectionBeanInfo;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.util.DateUtil;
import com.project.common.util.DecimalUtil;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.common.validation.ValidatorUtil;
import com.project.home.ProjectHomeBeanInfo;
import com.project.login.LoginBean;
import com.project.model.paginghelper.PaymentcollectionPagingHelper;
import com.project.model.paginghelper.PaymentsettlementPagingHelper;

@ManagedBean(name = "paymentsettlementBean")
@SessionScoped
public class PaymentsettlementBean {
	
	    PaymentsettlementModel paymentsettlement = new PaymentsettlementModel();
		List<PaymentsettlementapportionModel> paymentsettlementapportions = new ArrayList<PaymentsettlementapportionModel>();
		List<PaymentsettlementapportionModel> recordRevert = new ArrayList<PaymentsettlementapportionModel>();
		BranchinvoicebreakdownModel branchinvoicebreakdown = new BranchinvoicebreakdownModel();
		ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
		LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
		PaymentsettlementapportionModel settlementApportion = new PaymentsettlementapportionModel();
		private LazyDataModel<PaymentsettlementModel> settlementModel = null;
		IPaymentsettlementBO paymentsettlementBO = objectMapController.getPaymentsettlementBO();
		ISupplierinvoiceBO supplierinvoiceBO=objectMapController.getSupplierinvoiceBO();
		ICustomerBO customerBo = objectMapController.getCustomerBO();
		ISupplierBO supplierBo = objectMapController.getSupplierBO();
		IBranchBO branchBo = objectMapController.getBranchBO();
		List<SupplierinvoiceModel> invoiceList=new ArrayList<SupplierinvoiceModel>();
		CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
		Configuration config = Configuration.getConfiguration();
		private Integer supplierId;	
		private Integer supplybranchId;
		private Date dateFrom;
		private Date dateTo;
		private String status;
		private String paymentNo;
		private int collectionId;
		private String invoiceNo;
		private String action = "submit";
		private CustomerModel customer;
		boolean saveConfirm=true;
		private Map<String, String> receivalbeAmount = new HashMap<String, String>();
		private BigDecimal totalApportionAmount = new BigDecimal(0.00);		
		private BigDecimal balanceApportionAmount = new BigDecimal(0.00);
		
	
		
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

		public String getPaymentNo() {
			return paymentNo;
		}

		public void setPaymentNo(String paymentNo) {
			this.paymentNo = paymentNo;
		}

		public int getCollectionId() {
			return collectionId;
		}

		public void setCollectionId(int collectionId) {
			this.collectionId = collectionId;
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
		
		
		public Integer getSupplybranchId() {
			return supplybranchId;
		}
		public void setSupplybranchId(Integer supplybranchId) {
			this.supplybranchId = supplybranchId;
		}
		
		public boolean isSaveConfirm() {
			return saveConfirm;
		}

		public void setSaveConfirm(boolean saveConfirm) {
			this.saveConfirm = saveConfirm;
		}
		

		public Map<String, String> getReceivalbeAmount() {
			return receivalbeAmount;
		}

		public void setReceivalbeAmount(Map<String, String> receivalbeAmount) {
			this.receivalbeAmount = receivalbeAmount;
		}	
		
		public BigDecimal getTotalApportionAmount() {
			return totalApportionAmount;
		}

		public void setTotalApportionAmount(BigDecimal totalApportionAmount) {
			this.totalApportionAmount = totalApportionAmount;
		}		
		
		

		public BigDecimal getBalanceApportionAmount() {
			return balanceApportionAmount;
		}
		public void setBalanceApportionAmount(BigDecimal balanceApportionAmount) {
			this.balanceApportionAmount = balanceApportionAmount;
		}
		public PaymentsettlementModel getPaymentsettlement() {
			return paymentsettlement;
		}
		public void setPaymentsettlement(PaymentsettlementModel paymentsettlement) {
			this.paymentsettlement = paymentsettlement;
		}
		public List<PaymentsettlementapportionModel> getPaymentsettlementapportions() {
			return paymentsettlementapportions;
		}
		public void setPaymentsettlementapportions(
				List<PaymentsettlementapportionModel> paymentsettlementapportions) {
			this.paymentsettlementapportions = paymentsettlementapportions;
		}
		public List<PaymentsettlementapportionModel> getRecordRevert() {
			return recordRevert;
		}
		public void setRecordRevert(List<PaymentsettlementapportionModel> recordRevert) {
			this.recordRevert = recordRevert;
		}
		public BranchinvoicebreakdownModel getBranchinvoicebreakdown() {
			return branchinvoicebreakdown;
		}
		public void setBranchinvoicebreakdown(
				BranchinvoicebreakdownModel branchinvoicebreakdown) {
			this.branchinvoicebreakdown = branchinvoicebreakdown;
		}
		public PaymentsettlementapportionModel getSettlementApportion() {
			return settlementApportion;
		}
		public void setSettlementApportion(
				PaymentsettlementapportionModel settlementApportion) {
			this.settlementApportion = settlementApportion;
		}
		public LazyDataModel<PaymentsettlementModel> getSettlementModel() {
			return settlementModel;
		}
		public void setSettlementModel(
				LazyDataModel<PaymentsettlementModel> settlementModel) {
			this.settlementModel = settlementModel;
		}
		public IPaymentsettlementBO getPaymentsettlementBO() {
			return paymentsettlementBO;
		}
		public void setPaymentsettlementBO(IPaymentsettlementBO paymentsettlementBO) {
			this.paymentsettlementBO = paymentsettlementBO;
		}
		public List<SupplierinvoiceModel> getInvoiceList() {
			return invoiceList;
		}
		public void setInvoiceList(List<SupplierinvoiceModel> invoiceList) {
			this.invoiceList = invoiceList;
		}
		public Integer getSupplierId() {
			return supplierId;
		}
		public void setSupplierId(Integer supplierId) {
			this.supplierId = supplierId;
		}
	
		
		public LazyDataModel<PaymentsettlementModel> getSettlementList() {
			FacesContext context = FacesContext.getCurrentInstance();
			try
			{		
			this.setSettlementModel(null);	
			settlementModel = new PaymentsettlementPagingHelper(paymentNo,supplierId, dateFrom, dateTo,status,paymentsettlementBO,loginBean.getBranch().getBranchId(),supplybranchId);		
			this.setSettlementModel(settlementModel);	
			}
			catch(Exception e){					
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"settlementPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
				}
			return settlementModel;
		}
		
		
		
		public void viewSettlement(ActionEvent event) throws Exception 
		{
		FacesContext context = FacesContext.getCurrentInstance();		
		try {		
			paymentsettlement = (PaymentsettlementModel) event.getComponent().getAttributes().get("settlement");	
			paymentsettlementapportions=paymentsettlementBO.getPaymentsettlementapportionList(paymentsettlement.getSettlementId(), null);
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"settlementPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
			}
		}
		
		public void editSettlement(ActionEvent event) throws Exception 
		{
		FacesContext context = FacesContext.getCurrentInstance();	
		PaymentsettlementBeanInfo paymentcollectionBeanInfo = new PaymentsettlementBeanInfo();
		try {		
			paymentsettlement = (PaymentsettlementModel) event.getComponent().getAttributes().get("settlement");
			paymentsettlement=paymentsettlementBO.getPaymentsettlementMasterDetails(paymentsettlement.getSettlementId());
			this.setSupplierId(paymentsettlement.getSupplier().getSupplierId());			
			this.setAction("update");
			paymentcollectionBeanInfo.editSettlement();
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"settlementPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
			}
		}	
		
		public void approveSettlementConfirm(ActionEvent event)  throws Exception 
		{		
			FacesContext context = FacesContext.getCurrentInstance();		
			try {			
				paymentsettlement = (PaymentsettlementModel) event.getComponent().getAttributes().get("settlement");
			} catch (Exception e) {
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"settlementPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
				throw e;
				}		
		}	
		
		
		public void settleSettlementConfirm(ActionEvent event)  throws Exception 
		{		
			FacesContext context = FacesContext.getCurrentInstance();		
			try {			
				paymentsettlement = (PaymentsettlementModel) event.getComponent().getAttributes().get("settlement");
			} catch (Exception e) {
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"settlementPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
				throw e;
				}		
		}	
		
		public void settleSettlement()
		{
			PaymentsettlementBeanInfo paymentcollectionBeanInfo = new PaymentsettlementBeanInfo();
			paymentcollectionBeanInfo.settleSettlement();
			searchSettlement();
		}	
		
		
		public void resetSettlement()
		{
			paymentsettlement = new PaymentsettlementModel();
			this.setAction("submit");
			this.setSupplierId(null);	
			this.setSupplybranchId(0);
			this.setBalanceApportionAmount(new BigDecimal("0.00"));
			this.setTotalApportionAmount(new BigDecimal("0.00"));	
			this.invoiceList.clear();
			ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();			
			homeinfo.clearSessionObject();	
		}	
		
		
		
		
		public void resetSettlement1()
		{
			//paymentcollection = new PaymentcollectionModel();
			this.getPaymentsettlement().setBranch(new BranchModel());
			paymentsettlement.setSettlementAmount(new BigDecimal("0.00"));
			paymentsettlement.setComments(null);
			paymentsettlement.setSettlementDate(null);
			paymentsettlement.setReferenceNumber(null);
			paymentsettlement.setPaymentMode(null);
			this.setAction("submit");
			//this.setBranchId(0);
			//this.setCustomer(new CustomerModel());
			this.setBalanceApportionAmount(new BigDecimal("0.00"));
			this.setTotalApportionAmount(new BigDecimal("0.00"));	
			this.invoiceList.clear();
		}
		
		public void approveSettlement()
		{
			PaymentsettlementBeanInfo paymentcollectionBeanInfo = new PaymentsettlementBeanInfo();
			paymentcollectionBeanInfo.approveSettlement();
			searchSettlement();
		}			
		
		public void saveSettlement()
		{
			PaymentsettlementBeanInfo paymentcollectionBeanInfo = new PaymentsettlementBeanInfo();
			paymentcollectionBeanInfo.saveSettlement();
		
			
		}
		
		public void updateSettlement()
		{
			PaymentsettlementBeanInfo paymentcollectionBeanInfo = new PaymentsettlementBeanInfo();
			paymentcollectionBeanInfo.updateSettlement();
			
			
		}
		
		public void validateSettlementSave()
		{
			PaymentsettlementBeanInfo paymentcollectionBeanInfo = new PaymentsettlementBeanInfo();		
			paymentcollectionBeanInfo.validateSettlementSave();
		}
		
		
		public void searchSettlement()
		{
			PaymentsettlementBeanInfo paymentcollectionBeanInfo = new PaymentsettlementBeanInfo();
			try
			{   if(!paymentcollectionBeanInfo.validateSettlementSearch())
			{
				this.setDateFrom(DateUtil.getFromTodayDateTime());
				this.setDateTo(DateUtil.getToTodayDateTime());
			}
				this.getSettlementList();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}	
		
		public void resetSettlementSearch()
		{
			this.setSupplierId(0);			
			this.setDateFrom(DateUtil.getFromTodayDateTime());
			this.setDateTo(DateUtil.getToTodayDateTime());
			this.setStatus(null);
			this.setPaymentNo(null);
			searchSettlement();
		}
		
		public void listSettlement()
		{
			PaymentsettlementBeanInfo paymentcollectionBeanInfo = new PaymentsettlementBeanInfo();
			FacesContext context = FacesContext.getCurrentInstance();	
			try {
				paymentcollectionBeanInfo.listSettlement();
				}
			catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"settlementPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
			}	
		
		public void newSettlement()
		{
			PaymentsettlementBeanInfo paymentcollectionBeanInfo = new PaymentsettlementBeanInfo();
			FacesContext context = FacesContext.getCurrentInstance();	
			try {
				resetSettlement();
				paymentcollectionBeanInfo.newSettlement();			
				}
			catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"settlementPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
			}			
		
		
		public void handleSelectSupplier() { 		
			
			FacesContext context = FacesContext.getCurrentInstance();	
			resetSettlement1();
			try {
				if(paymentsettlement.getSettlementType().equalsIgnoreCase("1"))
				{	
				if(this.getSupplierId()!=0)
				{
					this.getPaymentsettlement().setSupplier(supplierBo.getSupplierDetails(this.getSupplierId()));
					inivoiceCollection();
				}
				}
				if(paymentsettlement.getSettlementType().equalsIgnoreCase("2"))
				{	
					if(this.getSupplybranchId()!=0)
					{
						this.getPaymentsettlement().setBranch(branchBo.getBranchDetails(this.getSupplybranchId()));
						inivoiceCollection();
					}
				}
			}
			catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"settlementPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
		}
		
		
		
		public void apportionSettlement(ActionEvent event) throws Exception 
		{
		FacesContext context = FacesContext.getCurrentInstance();	
		PaymentsettlementBeanInfo paymentcollectionBeanInfo = new PaymentsettlementBeanInfo();
		try {		
			paymentsettlement = (PaymentsettlementModel) event.getComponent().getAttributes().get("settlement");
			paymentsettlement=paymentsettlementBO.getPaymentsettlementMasterDetails(paymentsettlement.getSettlementId());
			this.setSupplierId(paymentsettlement.getSupplier().getSupplierId());			
			this.setTotalApportionAmount(new BigDecimal(0.00));
			this.getReceivalbeAmount().clear();	
			if(paymentsettlement.getSettlementType().equalsIgnoreCase("1"))
			{	
			this.setInvoiceList(supplierinvoiceBO.getSupplierinvoiceListApportion(paymentsettlement.getSupplier().getSupplierId(), "2", null,loginBean.getBranch().getBranchId(),null));
			}
			if(paymentsettlement.getSettlementType().equalsIgnoreCase("2"))
			{	
			this.setInvoiceList(supplierinvoiceBO.getSupplierinvoiceListApportion(null, "2", null,loginBean.getBranch().getBranchId(),paymentsettlement.getBranch().getBranchId()));
	
			}
			paymentcollectionBeanInfo.settlementApportion();
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"settlementPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
			}
		}	
		
		
		public void inivoiceCollection() throws Exception 
		{
		FacesContext context = FacesContext.getCurrentInstance();			
		try {		
			
			this.setTotalApportionAmount(new BigDecimal(0.00));
			this.getReceivalbeAmount().clear();
			if(paymentsettlement.getSettlementType().equalsIgnoreCase("1"))
			{								
				this.setInvoiceList(supplierinvoiceBO.getSupplierinvoiceListApportion(this.getSupplierId(), "2", "1",loginBean.getBranch().getBranchId(),null));
			}
			else
			{				
				this.setInvoiceList(supplierinvoiceBO.getSupplierinvoiceListApportion(null, "2", "2",loginBean.getBranch().getBranchId(),supplybranchId));
			}		
			//paymentcollectionBeanInfo.collectionApportion();
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"collectionPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
			}
		}	
		
		
		
		
		public void reverSettlementConfirm(ActionEvent event) throws Exception 
		{
		FacesContext context = FacesContext.getCurrentInstance();	
		PaymentsettlementBeanInfo paymentcollectionBeanInfo = new PaymentsettlementBeanInfo();
		try {		
			paymentsettlement = (PaymentsettlementModel) event.getComponent().getAttributes().get("settlement");
			paymentsettlement=paymentsettlementBO.getPaymentsettlementMasterDetails(paymentsettlement.getSettlementId());
			this.setTotalApportionAmount(new BigDecimal(0.00));
			this.getReceivalbeAmount().clear();
			
			paymentsettlementapportions=paymentsettlementBO.getPaymentsettlementapportionList(paymentsettlement.getSettlementId(), null);
			paymentcollectionBeanInfo.reverSettlementConfirm();
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"settlementPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
			}
		}	
		
		
		
		public void validateAmountPaid(Integer rowId)
		{
			FacesContext context = FacesContext.getCurrentInstance();
			try
			{
				int count = 0;
				BigDecimal cleartotalAmount = new BigDecimal("0.00");						
				BigDecimal tempTotal = new BigDecimal("0.00");						
				BigDecimal pendingAmount = this.paymentsettlement.getUnclearAmount();		
				
				if(validatePaid(rowId))
				{
				for (SupplierinvoiceModel amountPaid : this.getInvoiceList()) {
					BigDecimal bilamont = new BigDecimal(0);
					BigDecimal apportionbalanceamount = new BigDecimal(0);			
					
					if (ValidatorUtil.isNotNull(amountPaid.getPendingAmount())) 
						{
						bilamont = amountPaid.getPendingAmount();
						}				
					if (ValidatorUtil.isNotNull(this.getReceivalbeAmount().get(amountPaid.getInvoiceNo()))) 
					{
						apportionbalanceamount = new BigDecimal(this.getReceivalbeAmount().get(amountPaid.getInvoiceNo()));
					}				
								
					tempTotal=apportionbalanceamount;
					
					if (tempTotal.doubleValue()>bilamont.doubleValue()) {
						tempTotal=new BigDecimal("0.00");								
						context.addMessage(UIComponent.findComponent(context.getViewRoot(), "settlementPanel").getClientId(context), new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Please Check the Balance Amount", null));					
						this.receivalbeAmount.put(amountPaid.getInvoiceNo(),String.valueOf("0.00"));					
						break;
					}				
					cleartotalAmount = cleartotalAmount.add(apportionbalanceamount);
							
					
					if (pendingAmount.doubleValue() < cleartotalAmount.doubleValue()) {
						if (count == 0) {					
							
							context.addMessage(UIComponent.findComponent(context.getViewRoot(), "settlementPanel").getClientId(context), new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Amount Paid Total is more than Pending Apportionment Amount , please adjust the Amount Paid", null));
									
							count = 1;
							this.receivalbeAmount.put(amountPaid.getInvoiceNo(),String.valueOf("0.00"));
							break;
						}
					} else {
						this.setTotalApportionAmount(cleartotalAmount);
					}		
				}
							
				}
			}
			catch(Exception e)
			{
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"settlementPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
			}
			
			
		}

		
		
		public void validateAmountPaidinCollection(Integer rowId)
		{
			FacesContext context = FacesContext.getCurrentInstance();
			try
			{						
				calculateApportionStatus();
				if(!validatePaid(rowId))
				{
				calculateApportionStatus();	
				}
			}
			catch(Exception e)
			{
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"dataTable").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
			}		
			
		}
		
		
		public void calculateApportionStatus()
		{
			BigDecimal cleartotalAmount = new BigDecimal("0.00");			
			BigDecimal apportionbalanceamount = new BigDecimal(0);
			for (SupplierinvoiceModel amountPaid : this.getInvoiceList()) {			
							
				if (ValidatorUtil.isNotNull(this.getReceivalbeAmount().get(amountPaid.getInvoiceNo()))) 
				{
					apportionbalanceamount = new BigDecimal(this.getReceivalbeAmount().get(amountPaid.getInvoiceNo()));
					cleartotalAmount = cleartotalAmount.add(apportionbalanceamount);
				}	
				
				this.setTotalApportionAmount(cleartotalAmount);
				this.paymentsettlement.setSettlementAmount(cleartotalAmount);
				//this.setBalanceApportionAmount(this.paymentsettlement.getSettlementAmount().subtract(cleartotalAmount));
				
			}
		}
		
		
		
		public boolean validatePaid(Integer rowId)
		{
			FacesContext context = FacesContext.getCurrentInstance();		
			SupplierinvoiceModel c = this.getInvoiceList().get(rowId);
			BigDecimal receivableAmount=new  BigDecimal(0.00);
			boolean valid= true;
			try {
				if (ValidatorUtil.isNotNull(this.getReceivalbeAmount().get(c.getInvoiceNo()))) 
				{
					receivableAmount = new BigDecimal(this.getReceivalbeAmount().get(c.getInvoiceNo()));
					receivableAmount=DecimalUtil.formatRoundupCents(receivableAmount);
					this.receivalbeAmount.put(c.getInvoiceNo(),String.valueOf(receivableAmount));
				}	
					if(c.getPendingAmount().doubleValue()<receivableAmount.doubleValue())
					{
						context.addMessage(UIComponent.findComponent(context.getViewRoot(), "dataTable").getClientId(context), new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Please Check the Balance Amount", null));	
						//this.setBalanceApportionAmount(this.paymentsettlement.getSettlementAmount().subtract(this.getTotalApportionAmount()));
						this.receivalbeAmount.put(c.getInvoiceNo(),String.valueOf("0.00"));
						valid=false;
					}
					
					/*if (this.paymentsettlement.getSettlementAmount().doubleValue()<this.totalApportionAmount.doubleValue()) {
													
							context.addMessage(UIComponent.findComponent(context.getViewRoot(), "dataTable").getClientId(context), new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Amount Paid Total is more than Pending Apportionment Amount , please adjust the Amount Paid", null));
							this.receivalbeAmount.put(c.getInvoiceNo(),String.valueOf("0.00"));						
							valid=false;	
					} */
					
					
				
			} catch (Exception e) {
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"dataTable").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
				}
			
			return valid;
		}
		
		
		
		/*public boolean validatePaid(Integer rowId)
		{
			FacesContext context = FacesContext.getCurrentInstance();		
			SupplierinvoiceModel c = this.getInvoiceList().get(rowId);
			BigDecimal receivableAmount=new  BigDecimal(0.00);
			boolean valid= true;
			try {
				if (ValidatorUtil.isNotNull(this.getReceivalbeAmount().get(c.getInvoiceNo()))) 
				{
					receivableAmount = new BigDecimal(this.getReceivalbeAmount().get(c.getInvoiceNo()));
					receivableAmount=DecimalUtil.formatRoundupCents(receivableAmount);
					this.receivalbeAmount.put(c.getInvoiceNo(),String.valueOf(receivableAmount));
				}	
					if(c.getPendingAmount().doubleValue()<receivableAmount.doubleValue())
					{
						context.addMessage(UIComponent.findComponent(context.getViewRoot(), "settlementPanel").getClientId(context), new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Please Check the Balance Amount", null));					
						this.receivalbeAmount.put(c.getInvoiceNo(),String.valueOf("0.00"));
						valid=false;
					}
				
			} catch (Exception e) {
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"settlementPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
				}
			
			return valid;
		}*/
		
		
		public void paymentSettlementApportion()
		{
			PaymentsettlementBeanInfo paymentcollectionBeanInfo = new PaymentsettlementBeanInfo();	
			paymentcollectionBeanInfo.paymentSettlementApportion();
		}
		
		public void paymentSettlementRevert()
		{
			PaymentsettlementBeanInfo paymentcollectionBeanInfo = new PaymentsettlementBeanInfo();		
			paymentcollectionBeanInfo.paymentSettlementRevert();
		}
		
		public void revertSettlementConfirm(ActionEvent event)  throws Exception 
		{		
			recordRevert.clear();
			FacesContext context = FacesContext.getCurrentInstance();		
			try {			
				settlementApportion = (PaymentsettlementapportionModel) event.getComponent().getAttributes().get("settlement");
				recordRevert.add(settlementApportion);
			} catch (Exception e) {
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"settlementPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
				throw e;
				}		
		}	
		
		
		public void paymentSettlementRecordRevert()
		{
			
			FacesContext context = FacesContext.getCurrentInstance();
			boolean updateSuccess=false;
			try
			{				
			updateSuccess=paymentsettlementBO.revertPaymentsettlementapportionRecord(recordRevert, paymentsettlement);
			if(updateSuccess)
			{	
				paymentsettlementapportions=paymentsettlementBO.getPaymentsettlementapportionList(paymentsettlement.getSettlementId(), null);
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"settlementPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("paymentsettlement.label.revert.success"),null));				
			}				
			
			}
			catch(Exception e)
			{
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"settlementPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

			}
		}
		
		
		

		public void getSettlementPriceRoundup()
		{
			try
			{		
				this.paymentsettlement.setSettlementAmount(DecimalUtil.formatRoundupCents(this.paymentsettlement.getSettlementAmount()));
			
			for (SupplierinvoiceModel amountPaid : this.getInvoiceList()) {				
				this.receivalbeAmount.put(amountPaid.getInvoiceNo(),String.valueOf("0.00"));	
				this.setTotalApportionAmount(new BigDecimal("0.00"));
				this.setBalanceApportionAmount(this.paymentsettlement.getSettlementAmount());
				}	
			}
			catch(Exception e)
			{
				
			}
		}
		
	
		
		public void printPayment(ActionEvent event) {
			
			FacesContext faces = FacesContext.getCurrentInstance();	
			try{				
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 			
			paymentsettlement = (PaymentsettlementModel) event.getComponent().getAttributes().get("settlement");
			faces.getExternalContext().redirect(request.getScheme() + "://" + request.getServerName() +":" + request.getServerPort() +"/"+config.getValue(IConfiguration.PROJECT_INSTANCE_NAME)+"/frameset?__report=report/payment/supplierPaymentDetailReport.rptdesign&__format=pdf&&__pageoverflow=0&__asattachment=true&__overwrite=false&settlementId=" +paymentsettlement.getSettlementId()+"&userId="+loginBean.getUserId() );
			faces.responseComplete();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		
		
	}
