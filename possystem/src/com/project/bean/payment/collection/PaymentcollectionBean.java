package com.project.bean.payment.collection;

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
import javax.faces.model.SelectItem;
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
import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.CustomerModel;
import com.project.model.datamodel.purchase.PurchaserequestbreakdownModel;
import com.project.model.invoice.branch.BranchinvoiceModel;
import com.project.model.invoice.branch.BranchinvoicebreakdownModel;
import com.project.model.payment.collection.PaymentcollectionModel;
import com.project.model.payment.collection.PaymentcollectionapportionModel;
import com.project.model.payment.settlement.PaymentsettlementModel;
import com.project.model.sale.sales.QuotationModel;
import com.project.bean.admin.CommonListBeanInfo;
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


/*
 * @author Gopal Ar
 * @version 1.0
 * @since 12 Dec 2013
 * 
 */

@ManagedBean(name = "paymentcollectionBean")
@SessionScoped
public class PaymentcollectionBean {

	PaymentcollectionModel paymentcollection = new PaymentcollectionModel();
	List<PaymentcollectionapportionModel> paymentcollectionapportions = new ArrayList<PaymentcollectionapportionModel>();
	List<PaymentcollectionapportionModel> recordRevert = new ArrayList<PaymentcollectionapportionModel>();
	BranchinvoicebreakdownModel branchinvoicebreakdown = new BranchinvoicebreakdownModel();
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	PaymentcollectionapportionModel collectionApportion = new PaymentcollectionapportionModel();
	private LazyDataModel<PaymentcollectionModel> collectionModel = null;
	IPaymentcollectionBO paymentcollectionBO = objectMapController.getPaymentcollectionBO();
	IBranchinvoiceBO branchinvoiceBO=objectMapController.getBranchinvoiceBO();
	ICustomerBO customerBo = objectMapController.getCustomerBO();
	IBranchBO branchBo = objectMapController.getBranchBO();
	IPaymentsettlementBO paymentsettlementBO= objectMapController.getPaymentsettlementBO();
	List<BranchinvoiceModel> invoiceList=new ArrayList<BranchinvoiceModel>();
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	Configuration config = Configuration.getConfiguration();
	private Integer branchId;
	private Integer customerId;
	private Date dateFrom;
	private Date dateTo;
	private String status;
	private String paymentNo;
	private int collectionId;
	private Integer settlementId;
	private String invoiceNo;
	private String action = "submit";
	private CustomerModel customer;
	boolean saveConfirm=true;
	private Map<String, String> receivalbeAmount = new HashMap<String, String>();
	private BigDecimal totalApportionAmount = new BigDecimal(0.00);
	private BigDecimal balanceApportionAmount = new BigDecimal(0.00);
	private List<SelectItem> selectbillNoList = new ArrayList<SelectItem>();
	private boolean paidAmountDisable=false;
	
	public IPaymentcollectionBO getPaymentcollectionBO() {
		return paymentcollectionBO;
	}

	public void setPaymentcollectionBO(IPaymentcollectionBO paymentcollectionBO) {
		this.paymentcollectionBO = paymentcollectionBO;
	}

	public PaymentcollectionModel getPaymentcollection() {
		return paymentcollection;
	}

	public void setPaymentcollection(PaymentcollectionModel paymentcollection) {
		this.paymentcollection = paymentcollection;
	}

	public List<PaymentcollectionapportionModel> getPaymentcollectionapportions() {
		return paymentcollectionapportions;
	}

	public void setPaymentcollectionapportions(
			List<PaymentcollectionapportionModel> paymentcollectionapportions) {
		this.paymentcollectionapportions = paymentcollectionapportions;
	}

	public List<PaymentcollectionapportionModel> getRecordRevert() {
		return recordRevert;
	}

	public void setRecordRevert(List<PaymentcollectionapportionModel> recordRevert) {
		this.recordRevert = recordRevert;
	}

	public BranchinvoicebreakdownModel getBranchinvoicebreakdown() {
		return branchinvoicebreakdown;
	}

	public void setBranchinvoicebreakdown(
			BranchinvoicebreakdownModel branchinvoicebreakdown) {
		this.branchinvoicebreakdown = branchinvoicebreakdown;
	}	


	public Integer getSettlementId() {
		return settlementId;
	}

	public void setSettlementId(Integer settlementId) {
		this.settlementId = settlementId;
	}

	public LazyDataModel<PaymentcollectionModel> getCollectionModel() {
		return collectionModel;
	}

	public void setCollectionModel(
			LazyDataModel<PaymentcollectionModel> collectionModel) {
		this.collectionModel = collectionModel;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
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
	    	

	public CustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
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

	public PaymentcollectionapportionModel getCollectionApportion() {
		return collectionApportion;
	}

	public void setCollectionApportion(
			PaymentcollectionapportionModel collectionApportion) {
		this.collectionApportion = collectionApportion;
	}	
	

	public BigDecimal getBalanceApportionAmount() {
		return balanceApportionAmount;
	}

	public void setBalanceApportionAmount(BigDecimal balanceApportionAmount) {
		this.balanceApportionAmount = balanceApportionAmount;
	}

	public List<BranchinvoiceModel> getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(List<BranchinvoiceModel> invoiceList) {
		this.invoiceList = invoiceList;
	}	

	public List<SelectItem> getSelectbillNoList() {
		return selectbillNoList;
	}

	public void setSelectbillNoList(List<SelectItem> selectbillNoList) {
		this.selectbillNoList = selectbillNoList;
	}
	
	public boolean isPaidAmountDisable() {
		return paidAmountDisable;
	}

	public void setPaidAmountDisable(boolean paidAmountDisable) {
		this.paidAmountDisable = paidAmountDisable;
	}

	public LazyDataModel<PaymentcollectionModel> getCollectionList() {
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{		
		this.setCollectionModel(null);	
		if(this.customer!=null)
		{
			customerId=this.getCustomer().getCustomerId();
		}
		collectionModel = new PaymentcollectionPagingHelper(paymentNo,branchId,customerId, dateFrom, dateTo,status,paymentcollectionBO,loginBean.getBranch().getBranchId());		
		this.setCollectionModel(collectionModel);	
		}
		catch(Exception e){					
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"collectionPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
			}
		return collectionModel;
	}
	
	
	
	public void viewCollection(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();		
	try {		
		paymentcollection = (PaymentcollectionModel) event.getComponent().getAttributes().get("collection");	
		paymentcollectionapportions=paymentcollectionBO.getPaymentcollectionapportionList(paymentcollection.getCollectionId(), null);
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"collectionPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}
	
	public void editCollection(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();	
	PaymentcollectionBeanInfo paymentcollectionBeanInfo = new PaymentcollectionBeanInfo();
	try {		
		paymentcollection = (PaymentcollectionModel) event.getComponent().getAttributes().get("collection");
		paymentcollection=paymentcollectionBO.getPaymentcollectionMasterDetails(paymentcollection.getCollectionId());
		if(paymentcollection.getCollectionType().equalsIgnoreCase("1"))
		{
			this.setBranchId(paymentcollection.getBranch().getBranchId());
		}
		else
		{
			this.setCustomer(paymentcollection.getCustomer());
		}
		this.setAction("update");
		paymentcollectionBeanInfo.editCollection();		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"collectionPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}	
	
	public void approveCollectionConfirm(ActionEvent event)  throws Exception 
	{		
		FacesContext context = FacesContext.getCurrentInstance();		
		try {			
			paymentcollection = (PaymentcollectionModel) event.getComponent().getAttributes().get("collection");
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"collectionPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
			}		
	}	
	
	
	public void approveApportionCompleteCollectionConfirm(ActionEvent event)  throws Exception 
	{		
		FacesContext context = FacesContext.getCurrentInstance();		
		try {			
			paymentcollection = (PaymentcollectionModel) event.getComponent().getAttributes().get("collection");
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"collectionPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
			}		
	}	
	
	
	public void approveApportionCompleteCollection()
	{
		PaymentcollectionBeanInfo paymentcollectionBeanInfo = new PaymentcollectionBeanInfo();	
		paymentcollectionBeanInfo.approveApportionCompleteCollection();
		searchCollection();
	}		
	
	
	
	public void resetCollection()
	{
		paymentcollection = new PaymentcollectionModel();
		this.setAction("submit");
		this.setBranchId(0);
		this.setCustomer(new CustomerModel());
		this.setBalanceApportionAmount(new BigDecimal("0.00"));
		this.setTotalApportionAmount(new BigDecimal("0.00"));	
		this.invoiceList.clear();
		selectbillNoList.clear();
		this.setPaidAmountDisable(false);
		ProjectHomeBeanInfo homeinfo = new ProjectHomeBeanInfo();			
		homeinfo.clearSessionObject();	
	}
	
	
	public void resetCollection1()
	{
		//paymentcollection = new PaymentcollectionModel();
		this.getPaymentcollection().setBranch(new BranchModel());
		paymentcollection.setCollectionAmount(new BigDecimal("0.00"));
		paymentcollection.setComments(null);
		paymentcollection.setCollectionDate(null);
		paymentcollection.setReferenceNumber(null);
		paymentcollection.setPaymentMode(null);
		this.setAction("submit");
		//this.setBranchId(0);
		//this.setCustomer(new CustomerModel());
		this.setBalanceApportionAmount(new BigDecimal("0.00"));
		this.setTotalApportionAmount(new BigDecimal("0.00"));	
		this.invoiceList.clear();
		selectbillNoList.clear();
		this.setPaidAmountDisable(false);
	}
	
	
	public void approveCollection()
	{
		PaymentcollectionBeanInfo paymentcollectionBeanInfo = new PaymentcollectionBeanInfo();	
		paymentcollectionBeanInfo.approveCollection();
		searchCollection();
	}		
	
	
	public void saveCollection()
	{
		PaymentcollectionBeanInfo paymentcollectionBeanInfo = new PaymentcollectionBeanInfo();
		paymentcollectionBeanInfo.saveCollection();
		
	}
	
	public void updateCollection()
	{
		PaymentcollectionBeanInfo paymentcollectionBeanInfo = new PaymentcollectionBeanInfo();
		paymentcollectionBeanInfo.updateCollection();
		
	}
	
	public void validateCollectionSave()
	{
		PaymentcollectionBeanInfo paymentcollectionBeanInfo = new PaymentcollectionBeanInfo();		
		paymentcollectionBeanInfo.validateCollectionSave();
	}
	
	
	public void searchCollection()
	{
		PaymentcollectionBeanInfo paymentcollectionBeanInfo = new PaymentcollectionBeanInfo();
		try
		{   if(!paymentcollectionBeanInfo.validateCollectionSearch())
		{
			this.setDateFrom(DateUtil.getFromTodayDateTime());
			this.setDateTo(DateUtil.getToTodayDateTime());
		}
			this.getCollectionList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
	
	public void resetCollectionSearch()
	{
		this.setBranchId(0);
		this.setCustomerId(null);
		this.setDateFrom(DateUtil.getFromTodayDateTime());
		this.setDateTo(DateUtil.getToTodayDateTime());
		this.setStatus(null);
		this.setPaymentNo(null);
		searchCollection();
	}
	
	public void listCollection()
	{
		PaymentcollectionBeanInfo paymentcollectionBeanInfo = new PaymentcollectionBeanInfo();
		FacesContext context = FacesContext.getCurrentInstance();	
		try {
			paymentcollectionBeanInfo.listCollection();
			}
		catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"collectionPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
		}	
	
	public void newCollection()
	{
		PaymentcollectionBeanInfo paymentcollectionBeanInfo = new PaymentcollectionBeanInfo();
		FacesContext context = FacesContext.getCurrentInstance();	
		try {
			resetCollection();
			paymentcollectionBeanInfo.newCollection();			
			}
		catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"collectionPanel").getClientId(context),
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
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"collectionPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
		return results;
	}
	
	
	public void handleSelect(SelectEvent event) { 		
		
		FacesContext context = FacesContext.getCurrentInstance();	
		try {	
			resetCollection1();
			if(this.getCustomer()!=null)
			{
		this.setCustomer(customerBo.getCustomerDetails(customer.getCustomerId()));
		this.getPaymentcollection().setCustomer(this.getCustomer());
		this.invoiceList.clear();
		inivoiceCollection();
			}		
		}
		catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"collectionPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
	}
	
	
	public void handleSelectBranch() { 		
		
		FacesContext context = FacesContext.getCurrentInstance();	
		resetCollection1();
		selectbillNoList = new ArrayList<SelectItem>();	
		try {	
			if(this.getBranchId()!=0)
			{
				
				this.invoiceList.clear();				
				List<PaymentsettlementModel> billNoList=paymentsettlementBO.getPaymentsettlementList(null, null, null, null, null, "4",0, 200, this.getBranchId(), loginBean.getBranch().getBranchId());
			    for(PaymentsettlementModel data:billNoList)
			    {
			    selectbillNoList.add(new SelectItem(data.getSettlementId(), data.getPaymentNo()));
			    }
				//inivoiceCollection();
			}		
		}
		catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"collectionPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
	}
	
	
	public void getSettlementDetails()
	{
		FacesContext context = FacesContext.getCurrentInstance();	
		//resetCollection1();
		selectbillNoList = new ArrayList<SelectItem>();	
		receivalbeAmount.clear();
		try {	
			if(this.getSettlementId()!=0)
			{
				this.setPaidAmountDisable(true);
				paymentcollection=paymentcollectionBO.getPaymentcollectionSettlemetDetails(this.getSettlementId());				
				this.getPaymentcollection().setBranch(branchBo.getBranchDetails(this.getBranchId()));
				this.setInvoiceList(paymentcollection.getInvoiceList());
				this.setTotalApportionAmount(paymentcollection.getCollectionAmount());
				this.setSettlementId(paymentcollection.getSettlementId());
				
				for(BranchinvoiceModel invoice:paymentcollection.getInvoiceList())
				{				
				this.receivalbeAmount.put(invoice.getInvoiceNo(),""+invoice.getPaidAmount());	
				}
			}		
		}
		catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"collectionPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
		
	}
	
	
	
	public void changeAmount()
	{
		FacesContext context = FacesContext.getCurrentInstance();	
		int count = 0;
		BigDecimal cleartotalAmount = new BigDecimal("0.00");						
		BigDecimal tempTotal = new BigDecimal("0.00");						
		BigDecimal pendingAmount = this.paymentcollection.getUnclearAmount();	
		try {	
				getCollectionPriceRoundup();
				this.setTotalApportionAmount(cleartotalAmount);
				for (BranchinvoiceModel amountPaid : this.getInvoiceList()) {							
					if (ValidatorUtil.isNotNull(this.getReceivalbeAmount().get(amountPaid.getInvoiceNo()))) 
					{
						this.receivalbeAmount.put(amountPaid.getInvoiceNo(),String.valueOf("0.00"));
					}														
				}					
		}
		catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"collectionPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
		}
	}
	
	
	
	public void apportionCollection(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();	
	PaymentcollectionBeanInfo paymentcollectionBeanInfo = new PaymentcollectionBeanInfo();
	try {		
		paymentcollection = (PaymentcollectionModel) event.getComponent().getAttributes().get("collection");
		paymentcollection=paymentcollectionBO.getPaymentcollectionMasterDetails(paymentcollection.getCollectionId());
		this.setTotalApportionAmount(new BigDecimal(0.00));
		this.getReceivalbeAmount().clear();
		if(paymentcollection.getCollectionType().equalsIgnoreCase("1"))
		{
			this.setBranchId(paymentcollection.getBranch().getBranchId());
			this.setInvoiceList(branchinvoiceBO.getBranchinvoiceListApportion(paymentcollection.getBranch().getBranchId(), null, "2", "1",loginBean.getBranch().getBranchId()));
		}
		else
		{
			this.setCustomer(paymentcollection.getCustomer());
			this.setInvoiceList(branchinvoiceBO.getBranchinvoiceListApportion(null, paymentcollection.getCustomer().getCustomerId(), "2", "2",loginBean.getBranch().getBranchId()));
		}		
		paymentcollectionBeanInfo.collectionApportion();
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"collectionPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}	
	
	
	public void inivoiceCollection() throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();	
	PaymentcollectionBeanInfo paymentcollectionBeanInfo = new PaymentcollectionBeanInfo();
	try {		
		//paymentcollection = (PaymentcollectionModel) event.getComponent().getAttributes().get("collection");
		//paymentcollection=paymentcollectionBO.getPaymentcollectionMasterDetails(paymentcollection.getCollectionId());
		this.setTotalApportionAmount(new BigDecimal(0.00));
		this.getReceivalbeAmount().clear();
		if(paymentcollection.getCollectionType().equalsIgnoreCase("1"))
		{
			this.setBranchId(paymentcollection.getBranch().getBranchId());
			this.setInvoiceList(branchinvoiceBO.getBranchinvoiceListApportion(paymentcollection.getBranch().getBranchId(), null, "2", "1",loginBean.getBranch().getBranchId()));
		}
		else
		{
			this.setCustomer(paymentcollection.getCustomer());
			this.setInvoiceList(branchinvoiceBO.getBranchinvoiceListApportion(null, paymentcollection.getCustomer().getCustomerId(), "2", "2",loginBean.getBranch().getBranchId()));
		}		
		//paymentcollectionBeanInfo.collectionApportion();
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"collectionPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
		}
	}	
	
	
	public void reverCollectionConfirm(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();	
	PaymentcollectionBeanInfo paymentcollectionBeanInfo = new PaymentcollectionBeanInfo();
	try {		
		paymentcollection = (PaymentcollectionModel) event.getComponent().getAttributes().get("collection");
		paymentcollection=paymentcollectionBO.getPaymentcollectionMasterDetails(paymentcollection.getCollectionId());
		this.setTotalApportionAmount(new BigDecimal(0.00));
		this.getReceivalbeAmount().clear();
		if(paymentcollection.getCollectionType().equalsIgnoreCase("1"))
		{
			this.setBranchId(paymentcollection.getBranch().getBranchId());			
		}
		else
		{
			this.setCustomer(paymentcollection.getCustomer());
		}		
		paymentcollectionapportions=paymentcollectionBO.getPaymentcollectionapportionList(paymentcollection.getCollectionId(), null);
		paymentcollectionBeanInfo.reverCollectionConfirm();
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"collectionPanel").getClientId(context),
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
			BigDecimal pendingAmount = this.paymentcollection.getUnclearAmount();		
			
			if(validatePaid(rowId))
			{
			for (BranchinvoiceModel amountPaid : this.getInvoiceList()) {
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
					context.addMessage(UIComponent.findComponent(context.getViewRoot(), "collectionPanel").getClientId(context), new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Please Check the Balance Amount", null));					
					this.receivalbeAmount.put(amountPaid.getInvoiceNo(),String.valueOf("0.00"));					
					break;
				}				
				cleartotalAmount = cleartotalAmount.add(apportionbalanceamount);
						
				
				if (pendingAmount.doubleValue() < cleartotalAmount.doubleValue()) {
					if (count == 0) {					
						
						context.addMessage(UIComponent.findComponent(context.getViewRoot(), "collectionPanel").getClientId(context), new FacesMessage(
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
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"collectionPanel").getClientId(context),
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
		for (BranchinvoiceModel amountPaid : this.getInvoiceList()) {			
						
			if (ValidatorUtil.isNotNull(this.getReceivalbeAmount().get(amountPaid.getInvoiceNo()))) 
			{
				apportionbalanceamount = new BigDecimal(this.getReceivalbeAmount().get(amountPaid.getInvoiceNo()));
			}	
			cleartotalAmount = cleartotalAmount.add(apportionbalanceamount);
			this.setTotalApportionAmount(cleartotalAmount);
			this.setBalanceApportionAmount(this.paymentcollection.getCollectionAmount().subtract(cleartotalAmount));
				
		}
	}
	
	
	public boolean validatePaid(Integer rowId)
	{
		FacesContext context = FacesContext.getCurrentInstance();		
		BranchinvoiceModel c = this.getInvoiceList().get(rowId);
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
					this.setBalanceApportionAmount(this.paymentcollection.getCollectionAmount().subtract(this.getTotalApportionAmount()));
					this.receivalbeAmount.put(c.getInvoiceNo(),String.valueOf("0.00"));
					valid=false;
				}
				
				if (this.paymentcollection.getCollectionAmount().doubleValue()<this.totalApportionAmount.doubleValue()) {
												
						context.addMessage(UIComponent.findComponent(context.getViewRoot(), "dataTable").getClientId(context), new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Amount Paid Total is more than Pending Apportionment Amount , please adjust the Amount Paid", null));
						this.receivalbeAmount.put(c.getInvoiceNo(),String.valueOf("0.00"));						
						valid=false;	
				} 
				
				
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"dataTable").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
		
		return valid;
	}
	
	
	public void paymentCollectionApportion()
	{
		PaymentcollectionBeanInfo paymentcollectionBeanInfo = new PaymentcollectionBeanInfo();		
		paymentcollectionBeanInfo.paymentCollectionApportion();
	}
	
	public void paymentCollectionRevert()
	{
		PaymentcollectionBeanInfo paymentcollectionBeanInfo = new PaymentcollectionBeanInfo();		
		paymentcollectionBeanInfo.paymentCollectionRevert();
	}
	
	public void revertCollectionConfirm(ActionEvent event)  throws Exception 
	{		
		recordRevert.clear();
		FacesContext context = FacesContext.getCurrentInstance();		
		try {			
			collectionApportion = (PaymentcollectionapportionModel) event.getComponent().getAttributes().get("collection");
			recordRevert.add(collectionApportion);
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"collectionPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
			}		
	}	
	
	
	public void paymentCollectionRecordRevert()
	{
		
		FacesContext context = FacesContext.getCurrentInstance();
		boolean updateSuccess=false;
		try
		{				
		updateSuccess=paymentcollectionBO.revertPaymentcollectionRecord(this.getRecordRevert(), this.getPaymentcollection());
		if(updateSuccess)
		{	
			paymentcollectionapportions=paymentcollectionBO.getPaymentcollectionapportionList(paymentcollection.getCollectionId(), null);
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"collectionPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("paymentcollection.label.revert.success"),null));				
		}				
		
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"collectionPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

		}
	}
	
	
	public void getCollectionPriceRoundup()
	{
		try
		{		
		this.paymentcollection.setCollectionAmount(DecimalUtil.formatRoundupCents(this.paymentcollection.getCollectionAmount()));
		
		for (BranchinvoiceModel amountPaid : this.getInvoiceList()) {				
			this.receivalbeAmount.put(amountPaid.getInvoiceNo(),String.valueOf("0.00"));	
			this.setTotalApportionAmount(new BigDecimal("0.00"));
			this.setBalanceApportionAmount(this.paymentcollection.getCollectionAmount());
			}	
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
	
	public void printPayment(ActionEvent event) {
		
		FacesContext faces = FacesContext.getCurrentInstance();	
		try{				
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 			
		String purchaseRequestId = "";
		paymentcollection = (PaymentcollectionModel) event.getComponent().getAttributes().get("collection");
		faces.getExternalContext().redirect(request.getScheme() + "://" + request.getServerName() +":" + request.getServerPort() +"/"+config.getValue(IConfiguration.PROJECT_INSTANCE_NAME)+"/frameset?__report=report/payment/branchcustomerPaymentDetailReportList.rptdesign&__format=pdf&&__pageoverflow=0&__asattachment=true&__overwrite=false&collectionId=" + paymentcollection.getCollectionId()+"&userId="+loginBean.getUserId() );
		faces.responseComplete();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
}
