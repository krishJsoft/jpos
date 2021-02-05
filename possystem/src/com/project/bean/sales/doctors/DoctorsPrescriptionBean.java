package com.project.bean.sales.doctors;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import com.project.bo.interfaces.ICustomerBO;
import com.project.bo.interfaces.IDoctorsPrescriptionsBO;
import com.project.bo.interfaces.IProductBO;
import com.project.bo.interfaces.IPurchaserequestBO;


import com.project.model.datamodel.BranchstaffmemberModel;
import com.project.model.datamodel.CustomerModel;
import com.project.model.datamodel.ProductModel;
import com.project.model.datamodel.ProductpriceModel;
import com.project.model.datamodel.ProductsupplierModel;
import com.project.model.sale.sales.DoctorsPrescriptionsBreakdownModel;
import com.project.model.sale.sales.DoctorsPrescriptionsModel;
import com.project.model.sale.sales.QuotationbreakdownModel;
import com.project.util.ConvertUtil;
import com.project.util.DateUtil;
import com.project.bean.admin.CommonListBeanInfo;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.common.validation.ValidatorUtil;
import com.project.login.LoginBean;
import com.project.model.paginghelper.DoctorsPrescriptionsPagingHelper;

@ManagedBean(name = "dpBean")
@SessionScoped
public class DoctorsPrescriptionBean {

	private String patientId;
	private String prescptNo;
	private Integer doctorId;
	private String branchName;
	private String status;
	private Date fromDate;
	private Date toDate;  
	private Integer rowId;
	private String supplieraction = "submit";
	
	private String barcodeBreakdownToRemove; // temporary variable, used for temporarily saving the barcode of breakdown before remove confirmation

	private List<String> productcodes = new ArrayList<String>();

	private String ACTION_SAVE = "save";
	private String ACTION_UPDATE = "update";
	private CustomerModel customer = new CustomerModel();
	ProductModel product = new ProductModel();
	
	DoctorsPrescriptionsModel oldDpModel = new DoctorsPrescriptionsModel();
	DoctorsPrescriptionsBreakdownModel oldDpbModel = new DoctorsPrescriptionsBreakdownModel();
	List<DoctorsPrescriptionsBreakdownModel> oldDpbModels = new ArrayList<DoctorsPrescriptionsBreakdownModel>();

	DoctorsPrescriptionsModel dpModel = new DoctorsPrescriptionsModel();
	DoctorsPrescriptionsBreakdownModel dpbModel = new DoctorsPrescriptionsBreakdownModel();
	List<DoctorsPrescriptionsBreakdownModel> dpbModels = new ArrayList<DoctorsPrescriptionsBreakdownModel>();
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	List<SelectItem> statuses = new ArrayList<SelectItem>();
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	private LazyDataModel<DoctorsPrescriptionsModel> dpModels = null;
	Configuration config = Configuration.getConfiguration();
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");



	private String action;

	IDoctorsPrescriptionsBO dpBO=objectMapController.getDpBO();
	IPurchaserequestBO purchaseRequestBO=objectMapController.getPurchaseRequestBO();
	IProductBO productBO=objectMapController.getProductBO();
	ICustomerBO customerBo = objectMapController.getCustomerBO();
	public IPurchaserequestBO getPurchaseRequestBO() {
		return purchaseRequestBO;
	}

	public void setPurchaseRequestBO(IPurchaserequestBO purchaseRequestBO) {
		this.purchaseRequestBO = purchaseRequestBO;
	}

	public IProductBO getProductBO() {
		return productBO;
	}

	public void setProductBO(IProductBO productBO) {
		this.productBO = productBO;
	}	

	public void search(){
		
		if(!this.validateSearch())	
		{					
		Date dateNow = new Date ();				 
	    SimpleDateFormat dateformatDDMMYYYY = new SimpleDateFormat("dd/MM/yyyy");		 
	    String nowYYYYMMDD = new String( dateformatDDMMYYYY.format(dateNow));    
		this.setFromDate(DateUtil.getFromTodayDateTime());
		this.setToDate(DateUtil.getToTodayDateTime());
		}			
		this.getDpList();
	}

	public void resetAll(){
		this.setPatientId(null);
		this.setPrescptNo(null);
		this.setCustomer(new CustomerModel());
		this.setDoctorId(null);
		this.setBranchName(null);
		this.setStatus(null);
		this.setFromDate(null);
		this.setToDate(null);

		this.setDpModel(null);
		this.setDpbModels(null);
		this.setDpbModel(null);

		this.setAction(null);

		this.setOldDpbModel(null);
		this.setOldDpbModels(null);
		this.setOldDpModel(null);

		search();
	}

	public void addDoctorsPrescription(){
		DoctorsPrescriptionBeanInfo dpBeanInfo = new DoctorsPrescriptionBeanInfo();

		this.setAction(ACTION_SAVE);
		this.setDpModel(new DoctorsPrescriptionsModel());
		this.setDpbModel(new DoctorsPrescriptionsBreakdownModel());
		this.setDpbModels(new ArrayList<DoctorsPrescriptionsBreakdownModel>());
		this.setOldDpModel(new DoctorsPrescriptionsModel());
		this.setOldDpbModel(new DoctorsPrescriptionsBreakdownModel());
		this.setOldDpbModels(new ArrayList<DoctorsPrescriptionsBreakdownModel>());
		this.setCustomer(new CustomerModel());		
		dpBeanInfo.addDoctorsPrescription();
	}


	public void editDoctorsPrescription(ActionEvent event){
		FacesContext context = FacesContext.getCurrentInstance();	
		String dpId = "";
		dpId = (String) event.getComponent().getAttributes().get("dpId").toString();
		this.dpModel.setDoctorPrescriptionId(Integer.parseInt(dpId));
		this.setDpbModel(new DoctorsPrescriptionsBreakdownModel());
		this.setDpbModels(null);

		try {
			dpModel = dpBO.getDoctorPrescriptionDetails(Integer.parseInt(dpId));
			this.setDpbModels(dpModel.getDpBreakdownModels());

			this.setOldDpbModels(dpModel.getDpBreakdownModels());
			this.setOldDpModel(dpModel);
			this.setCustomer(dpModel.getCustomer());
			this.setAction(ACTION_UPDATE);

			DoctorsPrescriptionBeanInfo dpBeanInfo = new DoctorsPrescriptionBeanInfo();
			dpBeanInfo.editDoctorsPrescription();

		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"dpPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));
		}
	}
	
	public void viewDoctorsPrescription(ActionEvent event){
		FacesContext context = FacesContext.getCurrentInstance();	
		
		this.setDpbModel(new DoctorsPrescriptionsBreakdownModel());
		this.setDpbModels(null);

		try {
			dpModel = (DoctorsPrescriptionsModel) event.getComponent().getAttributes().get("dp");
			this.setDpbModels(dpModel.getDpBreakdownModels());

		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"dpPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));
		}
	}	
	
	
	
	public void approveDoctorsPrescriptionConfirm(ActionEvent event){
		FacesContext context = FacesContext.getCurrentInstance();	
	
		boolean approveSuccess=false;		
		try {			
			dpModel = (DoctorsPrescriptionsModel) event.getComponent().getAttributes().get("dp");
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"dpPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));
		}
	}	
	
	
	public void approveDoctorsPrescription(){
		FacesContext context = FacesContext.getCurrentInstance();	
	
		boolean approveSuccess=false;
		
		try {			
			dpModel.setStatus(config.getValue(IConfiguration.DOCTOR_STATUS_PROCESSED_VALUE));
			approveSuccess=dpBO.approveDoctorPrescription(dpModel);
			
			if(approveSuccess)
			{
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"dpPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,"Approved Success",null));
			}

		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"dpPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));
		}
	}	
	
	

	public void updateDoctorsPrescription(){
		DoctorsPrescriptionBeanInfo dpBeanInfo = new DoctorsPrescriptionBeanInfo();
		dpBeanInfo.updateDoctorsPrescriptions();
	}

	

	public void cancel(){
		this.resetAll();
		DoctorsPrescriptionBeanInfo dpBeanInfo = new DoctorsPrescriptionBeanInfo();
		dpBeanInfo.doctorsPrescriptionHome();
	}

	
	public void handleSelect(SelectEvent event) {  
		try
		{
		this.setCustomer(customerBo.getCustomerDetails(customer.getCustomerId()));
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void productSelect(SelectEvent event) {  
		try
		{		
		ProductModel productModel =productBO.getProductDetailsByBarcode(this.getDpbModel().getProduct().getBarcode(),loginBean.getBranch().getBranchId());
		dpbModel.setProduct(productModel);		
		}
		catch(Exception e)
		{
			
		}
	}
	
	
	
	public void selectProduct(SelectEvent event)
	{
		dpbModel.setProduct(((ProductModel) event.getObject()));			
	}
	
	public void selectCustomer(SelectEvent event)
	{
		this.customer=((CustomerModel) event.getObject());			
	}

	public void addBreakdown(){
		FacesContext context = FacesContext.getCurrentInstance();	
		try {
			if(!validateBreakdown()){
				return;
			}
			List <ProductpriceModel> productPriceList;
			ProductModel productModel = new ProductModel();
		//	productModel=productBO.getProductDetailsByBarcode(this.getDpbModel().getProduct().getBarcode(),loginBean.getBranch().getBranchId());
			productModel = productBO.getProductList(null, null, null, this.getDpbModel().getProduct().getBarcode(), null, 0, 1, loginBean.getBranch().getBranchId(), null,null,null,null).get(0);
			
			dpbModel.setProduct(productModel);	
			boolean productPriceFound=false;	
			
			if(dpbModels != null && dpbModels.size() > 0){


				for(DoctorsPrescriptionsBreakdownModel tmpDpbModel : dpbModels){
					if(tmpDpbModel.getProduct().getBarcode().equalsIgnoreCase(dpbModel.getProduct().getBarcode())){
						context.addMessage(UIComponent.findComponent(context.getViewRoot(),"dpPanel").getClientId(context),
								new FacesMessage(FacesMessage.SEVERITY_ERROR,"Barcode already exists",null));
						return;
					}
				} 
			}

			//productPriceList=productBO.getSortedProductpriceByBarcode(dpbModel.getProduct().getBarcode(),loginBean.getBranch().getBranchId()); 
			productPriceList = productBO.getSortedProductpriceBarcode(productModel.getProductId(),loginBean.getBranch().getBranchId());

			if(productPriceList!=null && productPriceList.size()==1)
			{
				productPriceList.add(productPriceList.get(0));
			}
			
			dpbModel.setUnitPrice(productModel.getNormalPrice());
			
			for(int i=0; i<productPriceList.size();i++){					
				if(!productPriceFound)
				{
				for(int j=i+1;j<productPriceList.size();j++){
					BigDecimal initVal = productPriceList.get(i).getQuantityFrom();
					BigDecimal nextVal = productPriceList.get(j).getQuantityFrom();
					if(dpbModel.getQuantity().doubleValue() >= initVal.doubleValue() && dpbModel.getQuantity().doubleValue() < nextVal.doubleValue()){
						dpbModel.setDiscountAmount(productPriceList.get(i).getDiscountAmount());
						productPriceFound=true;
						break;							
					}else{
						dpbModel.setDiscountAmount(productPriceList.get(j).getDiscountAmount());
						i++;
					}
				}
				
				BigDecimal tmpSubTotal = extractSubtotal(dpbModel.getUnitPrice(),dpbModel.getDiscountAmount(), dpbModel.getQuantity());
				dpbModel.setSubTotal(tmpSubTotal);
				}
			}
			dpbModel.setDoctorCommission(productModel.getDoctorPrice().multiply(dpbModel.getQuantity()));
			dpbModels.add(dpbModel);
			extractDoctorTotal();
			this.restBreakdown();

		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"dpPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));
		}
	}

	
	public BigDecimal extractSubtotal(BigDecimal unitPrice,BigDecimal discAmount, BigDecimal quant){
		BigDecimal priceAmt = unitPrice.multiply(quant);
		BigDecimal discAmt = discAmount.multiply(quant);
		BigDecimal tempTot = priceAmt.subtract(discAmt);
		return tempTot;
	}
	
	
	public void extractDoctorTotal() {
	    BigDecimal totalAmount =new BigDecimal(0.00);
	    BigDecimal totalDiscountAmount =new BigDecimal(0.00);
	    BigDecimal grandTotalAmount =new BigDecimal(0.00);
	    
		for(DoctorsPrescriptionsBreakdownModel tmpDpbModel : dpbModels)
		{
			totalAmount=totalAmount.add(tmpDpbModel.getSubTotal());
			totalDiscountAmount=totalDiscountAmount.add(tmpDpbModel.getDiscountAmount());
		}
		 dpModel.setTotalAmount(totalAmount);
		//this.setTotalDiscountAmount((totalDiscountAmount));
		//grandTotalAmount=totalAmount.subtract(totalDiscountAmount);
		//this.setGrandTotalAmount(grandTotalAmount);
	}
	
	public void saveDoctorsPrescription()
	{			
		FacesContext context = FacesContext.getCurrentInstance();		
		try {		
			DoctorsPrescriptionBeanInfo dpBeanInfo = new DoctorsPrescriptionBeanInfo();
			
			LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
			BranchstaffmemberModel logdetail =loginBean.getLogdetail();
			if(dpbModels == null || dpbModels.size() == 0){
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"dpPanel").getClientId(context),
						new FacesMessage(FacesMessage.SEVERITY_ERROR,"No Precriptions Available to Save",null));
				return;
			}
				
			if(this.getCustomer()!=null)
			{
			if (this.getCustomer().getCustomerName().equalsIgnoreCase(""))
			{				
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"patientName").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_ERROR,"Patient Name Required",null));
				return;
			}			
			}
			if (factoryBean.checkIsNullAssignMessage(this.dpModel.getCreatedDate(),
					"doctorsprescriptions.label.date", "date")) {
				return;
			}


			//dpModel.setDoctorName("DoctorName");
			dpModel.setCreatedDate(this.dpModel.getCreatedDate());
			dpModel.setModifiedDate(new Date());		    
			dpModel.setStatus("1");
			dpModel.setDpBreakdownModels(dpbModels);
			dpModel.setBranchstaffmember(logdetail);
			dpModel.setCustomer(this.getCustomer());
			dpModel.setBranchRecordId(loginBean.getBranch().getBranchId());
			
			String  prescriptionNo =dpBO.createNewDoctorPrescription(dpModel);
			dpModel.setPrescptNo(prescriptionNo);
			
			/*if (saveSuccess) {					
					context.addMessage(UIComponent.findComponent(context.getViewRoot(),"dpPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("purchaserequest.label.save.success"),null));				
				}*/ 

			this.addDoctorsPrescription();
			
			this.search();
			dpBeanInfo.doctorsPrescriptionHome();

		} catch (Exception e) {			
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"dpPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));
		}				
	}

	public void removeBreakdown(ActionEvent event){
		barcodeBreakdownToRemove = (String) event.getComponent().getAttributes().get("barcode").toString();		
		String dpbcode = "";
		dpbcode = (String) event.getComponent().getAttributes().get("dpbcode").toString();		
		this.setRowId(Integer.parseInt(dpbcode));
		DoctorsPrescriptionsBreakdownModel c = dpbModels.get(this.getRowId());
		this.setProduct(c.getProduct());
	}
	
	
	public void editSubscriptionItem(ActionEvent event)
	{
		String dpbcode = "";
		dpbcode = (String) event.getComponent().getAttributes().get("dpbcode").toString();		
		this.setRowId(Integer.parseInt(dpbcode));
		DoctorsPrescriptionsBreakdownModel c = dpbModels.get(this.getRowId());
		this.setDpbModel(c);		
		this.setSupplieraction("update");		
	}
	
	public void updateSubscriptionItemConfirm()
	{			
		DoctorsPrescriptionsBreakdownModel c = dpbModels.get(this.getRowId());
		dpbModels.remove(c);		
		addBreakdown();
		resetDpbreakdown();		
	}	
	
	
	public void resetDpbreakdown()
	{
		this.setSupplieraction("submit");
		this.setDpbModel(new DoctorsPrescriptionsBreakdownModel());	
	}

	public void removeBreakdownConfirm(){
		FacesContext context = FacesContext.getCurrentInstance();	
		try {
			List<DoctorsPrescriptionsBreakdownModel> tmpDpbModels = new ArrayList<DoctorsPrescriptionsBreakdownModel>();
			BigDecimal subTotalRemoved = new BigDecimal(0.00);
			
			if(dpbModels != null && dpbModels.size() > 0){
				for(DoctorsPrescriptionsBreakdownModel tmpDpbModel : dpbModels){
					if(!tmpDpbModel.getProduct().getBarcode().equalsIgnoreCase(barcodeBreakdownToRemove)){
						tmpDpbModels.add(tmpDpbModel);
					}else{
						subTotalRemoved =extractSubtotal(tmpDpbModel.getUnitPrice(),tmpDpbModel.getDiscountAmount(), tmpDpbModel.getQuantity());
					}
					
				}
			}
			this.setDpbModels(tmpDpbModels);
			dpModel.setTotalAmount(dpModel.getTotalAmount().subtract(subTotalRemoved));
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"dpPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR,e.toString(),null));
		}
	}


	public void restBreakdown(){
		this.setDpbModel(new DoctorsPrescriptionsBreakdownModel());
	}


	public void resetOnUpdate(){


		if(oldDpbModels != null && oldDpbModels.size() > 0){
			for(DoctorsPrescriptionsBreakdownModel old : oldDpbModels){
			}
		}

		this.setDpbModel(new DoctorsPrescriptionsBreakdownModel());
		this.setDpbModels(null);
		this.setDpModel(null);
		this.setDpModel(this.getOldDpModel());
		this.setDpbModels(this.getOldDpbModels());
	}

	public void restOnSave(){
		this.addDoctorsPrescription();
	}

	public List<CustomerModel> getCustomerName(String customerString) {
		List<CustomerModel> results = new ArrayList<CustomerModel>();
		CommonListBeanInfo commoninfo = new CommonListBeanInfo();
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			results = commoninfo.getAllCustomerList(customerString);
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"dpPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
		return results;
	}



	public CustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getPrescptNo() {
		return prescptNo;
	}
	public void setPrescptNo(String prescptNo) {
		this.prescptNo = prescptNo;
	}
	
	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}




	public IDoctorsPrescriptionsBO getDpBO() {
		return dpBO;
	}




	public void setDpBO(IDoctorsPrescriptionsBO dpBO) {
		this.dpBO = dpBO;
	}

	public DoctorsPrescriptionsModel getDpModel() {
		return dpModel;
	}

	public void setDpModel(DoctorsPrescriptionsModel dpModel) {
		this.dpModel = dpModel;
	}

	public List<SelectItem> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<SelectItem> statuses) {
		this.statuses = statuses;
	}

	public LazyDataModel<DoctorsPrescriptionsModel> getDpList() {
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{		
			this.setDpModels(null);
			dpModels = new DoctorsPrescriptionsPagingHelper(""+this.getCustomer().getCustomerId(), prescptNo, doctorId, fromDate, toDate, status, dpBO,loginBean.getBranch().getBranchId());
			this.setDpModels(dpModels);

		}
		catch(Exception e){					
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"dpPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));	
		}			
		return dpModels;
	}

	public void setDpModels(LazyDataModel<DoctorsPrescriptionsModel> dpModels) {
		this.dpModels = dpModels;
	}

	public List<DoctorsPrescriptionsBreakdownModel> getDpbModels() {
		return dpbModels;
	}

	public void setDpbModels(List<DoctorsPrescriptionsBreakdownModel> dpbModels) {
		this.dpbModels = dpbModels;
	}

	public DoctorsPrescriptionsBreakdownModel getDpbModel() {
		return dpbModel;
	}

	public void setDpbModel(DoctorsPrescriptionsBreakdownModel dpbModel) {
		this.dpbModel = dpbModel;
	}

	public LazyDataModel<DoctorsPrescriptionsModel> getDpModels() {
		return dpModels;
	}


	public List<String> getProductcodes() {
		return this.productcodes;
	}

	public void setProductcodes(List<String> productcodes) {
		this.productcodes = productcodes;
	}

	public List<ProductModel> getProductCode(String productString)
	{
		List<ProductModel> results = new ArrayList<ProductModel>();
		CommonListBeanInfo commoninfo = new CommonListBeanInfo();
		FacesContext context = FacesContext.getCurrentInstance();	
		try
		{
			results=commoninfo.getAllProductListByName(productString);				
		}
		catch(Exception e)
		{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"dpPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}		
		return results;
	}

	public DoctorsPrescriptionsModel getOldDpModel() {
		return oldDpModel;
	}

	public void setOldDpModel(DoctorsPrescriptionsModel oldDpModel) {
		this.oldDpModel = oldDpModel;
	}

	public DoctorsPrescriptionsBreakdownModel getOldDpbModel() {
		return oldDpbModel;
	}

	public void setOldDpbModel(DoctorsPrescriptionsBreakdownModel oldDpbModel) {
		this.oldDpbModel = oldDpbModel;
	}

	public List<DoctorsPrescriptionsBreakdownModel> getOldDpbModels() {
		return oldDpbModels;
	}

	public void setOldDpbModels(
			List<DoctorsPrescriptionsBreakdownModel> oldDpbModels) {
		this.oldDpbModels = oldDpbModels;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}


	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public String getSupplieraction() {
		return supplieraction;
	}

	public void setSupplieraction(String supplieraction) {
		this.supplieraction = supplieraction;
	}

	public boolean validateBreakdown()
	{
		boolean valid=true;
		FacesContext context = FacesContext.getCurrentInstance();	
		try
		{   
			
			if(factoryBean.checkIsNullAssignMessage(this.dpbModel.getProduct(), "doctorsprescriptions.label.productCode", "productCode")){
				valid = false;
			}else if (factoryBean.checkIsNullAssignMessage(this.dpbModel.getProduct().getBarcode(),
					"doctorsprescriptions.label.productCode", "productCode")) {
				valid = false;
			}else if(this.getDpbModels() != null && this.getDpbModels().size() > 0){
				for(DoctorsPrescriptionsBreakdownModel tmpBreakdown : this.getDpbModels()){
					if(this.dpbModel.getProduct().getBarcode().equalsIgnoreCase(tmpBreakdown.getProduct().getBarcode())){
						factoryBean.alreadyExistError("doctorsprescriptions.label.productCode ", "productCode");
						valid = false;  
					}
				}
			}



			if (factoryBean.checkIsNullAssignMessage(this.dpbModel.getFormName(),
					"doctorsprescriptions.label.form", "medicineForm")) {
				valid = false;
			}
			
			if (this.dpbModel.getQuantity().doubleValue() == 0){
				factoryBean.requiredMessageError("doctorsprescriptions.label.quantity", "quantity");
				valid = false;
			}


		/*	if (ValidatorUtil.isZero(this.dpbModel.getFrequency())){
				factoryBean.requiredMessageError("doctorsprescriptions.label.frequency", "frequency");
				valid = false;
			}
			
			
			if(ValidatorUtil.isZero(this.dpbModel.getTakeA()) && ValidatorUtil.isZero(this.dpbModel.getTakeA())  && ValidatorUtil.isZero(this.dpbModel.getTakeA()) ){
				factoryBean.requiredMessageError("doctorsprescriptions.label.take", "takeN");
				valid = false;
			}

			if(ValidatorUtil.isZero(this.dpbModel.getMedicationPeriod()) ){
				factoryBean.requiredMessageError("doctorsprescriptions.label.medicaitonPeriod", "medicaitonPeriod");
				valid = false;
			}
		 	*/
			return valid;
		}
		catch(Exception e)
		{
			context.addMessage(
					UIComponent.findComponent(context.getViewRoot(),"dpPanel").getClientId(context),new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}		
		return valid;
	}


	
	
	public boolean validateSearch() {
		boolean valid = true;		
	
		if(this.getPrescptNo()!=null)
		{
			if(this.getPrescptNo().equalsIgnoreCase(""))
			{
				this.setPrescptNo(null);
			}
		}		
		
		if(this.getCustomer()!=null)
		{
			if(this.getCustomer().getCustomerId()==0)
			{
				this.setCustomer(new CustomerModel());
			}
		}
		
		if(this.getStatus()!=null)
		{
			if(this.getStatus().equalsIgnoreCase("") || this.getStatus().equalsIgnoreCase("0") )
			{
				this.setStatus(null);
			}
		}		
		if(this.getCustomer()==null)
		{			
		this.setCustomer(new CustomerModel());			
		}
		
		if(this.getFromDate()==null && this.getToDate()==null && this.getStatus()==null  && this.getPrescptNo()==null && this.getCustomer().getCustomerId()==0)
		{	
			valid = false;
		}		
		
		
		else
		{
			valid = true;
		}			
		return valid;

	}
	
	

	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}

	public void printDoctorPrescription(ActionEvent event) {
		
		FacesContext faces = FacesContext.getCurrentInstance();	
		try{				
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 		
		dpModel = (DoctorsPrescriptionsModel) event.getComponent().getAttributes().get("dp");
		faces.getExternalContext().redirect(request.getScheme() + "://" + request.getServerName() +":" + request.getServerPort() +"/"+config.getValue(IConfiguration.PROJECT_INSTANCE_NAME)+"/frameset?__report=report/sales/doctorPrescriptionDetailReport.rptdesign&__format=pdf&&__pageoverflow=0&__asattachment=true&__overwrite=false&doctorPrescriptionId=" + dpModel.getDoctorPrescriptionId()+"&userId="+loginBean.getUserId() );
		faces.responseComplete();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
