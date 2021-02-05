package com.project.bean.sales.sale;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import com.project.bean.admin.CommonListBeanInfo;
import com.project.bo.interfaces.IBranchsalesBO;
import com.project.bo.interfaces.IBranchtransferBO;
import com.project.bo.interfaces.IProductBO;
import com.project.bo.interfaces.IQuotationBO;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.util.DecimalUtil;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.common.validation.ValidatorUtil;
import com.project.login.LoginBean;
import com.project.model.datamodel.BranchModel;
import com.project.model.datamodel.CustomerModel;
import com.project.model.datamodel.ProductModel;
import com.project.model.datamodel.ProductpriceModel;
import com.project.model.datamodel.purchase.PurchaserequestbreakdownModel;
import com.project.model.paginghelper.BranchsalePagingHelper;
import com.project.model.paginghelper.BranchtransferPagingHelper;
import com.project.model.sale.sales.BranchtransferModel;
import com.project.model.sale.sales.BranchtransferbreakdownModel;

import com.project.util.DateUtil;

@ManagedBean(name = "branchtransferBean")
@SessionScoped
public class BranchtransferBean {

	
	BranchtransferModel branchtransfer = new BranchtransferModel();
	List<BranchtransferbreakdownModel> branchtransferbreakdowns = new ArrayList<BranchtransferbreakdownModel>();
	private LazyDataModel<BranchtransferModel> branchtransferModel = null;
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	List<SelectItem> supplierQty = new ArrayList<SelectItem>();
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	Configuration config = Configuration.getConfiguration();
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");

	private String productCode;
	private int quotationId;
	private String createdBy;
	private Date createdDate;
	private String deliveryTerms;
	private Date lastModifedDate;
	private String paymentTerms;
	private Date quotationDate;
	private Date quotationDueDate;
	private String quotationNo;
	private int itemCount=1;
	private String remarks;
	private String status;
	private BigDecimal totalAmount;
	private BigDecimal totalDiscountAmount;
	private BigDecimal grandTotalAmount;
	private BigDecimal totalTaxAmount = new BigDecimal(0.00);
	private int branchId;
	private String branchName;
	private Date dateFrom;
	private Date dateTo;
	private String action = "submit";
	private String itemaction = "submit";
	private String processaction = "";
	private Integer rowId;
	private ProductModel product;
	private boolean productCodedisable = false;
	List<String> productcodes = new ArrayList<String>();
	private BranchModel branch;
	private boolean customerNamedisable = false;
	List<String> customerNames = new ArrayList<String>();
	int sno=0;
	String transferType="";
	boolean validity=true;
	private Integer totalQuantity;
	
	private Map<String, Integer> receivalbeQuantity = new HashMap<String, Integer>();
	private Map<String, Date> receivalbeExpDate = new HashMap<String, Date>();
	
	IQuotationBO quotationBO=objectMapController.getQuotationBO();										
	IProductBO productBO=objectMapController.getProductBO();
	IBranchtransferBO branchtransferBO=objectMapController.getBranchtransferBO();
	
	

	public IBranchtransferBO getBranchtransferBO() {
		return branchtransferBO;
	}

	public void setBranchtransferBO(IBranchtransferBO branchtransferBO) {
		this.branchtransferBO = branchtransferBO;
	}

	public boolean isCustomerNamedisable() {
		return customerNamedisable;
	}

	public void setCustomerNamedisable(boolean customerNamedisable) {
		this.customerNamedisable = customerNamedisable;
	}	

	public Map<String, Integer> getReceivalbeQuantity() {
		return receivalbeQuantity;
	}

	public void setReceivalbeQuantity(Map<String, Integer> receivalbeQuantity) {
		this.receivalbeQuantity = receivalbeQuantity;
	}

	public Map<String, Date> getReceivalbeExpDate() {
		return receivalbeExpDate;
	}

	public void setReceivalbeExpDate(Map<String, Date> receivalbeExpDate) {
		this.receivalbeExpDate = receivalbeExpDate;
	}

	public String getTransferType() {
		return transferType;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
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

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getItemaction() {
		return itemaction;
	}

	public void setItemaction(String itemaction) {
		this.itemaction = itemaction;
	}	
	

	public String getProcessaction() {
		return processaction;
	}

	public void setProcessaction(String processaction) {
		this.processaction = processaction;
	}

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}	
	
	public BigDecimal getTotalDiscountAmount() {
		return totalDiscountAmount;
	}

	public void setTotalDiscountAmount(BigDecimal totalDiscountAmount) {
		this.totalDiscountAmount = totalDiscountAmount;
	}
	public BigDecimal getGrandTotalAmount() {
		return grandTotalAmount;
	}

	public void setGrandTotalAmount(BigDecimal grandTotalAmount) {
		this.grandTotalAmount = grandTotalAmount;
	}	

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public IProductBO getProductBO() {
		return productBO;
	}

	public void setProductBO(IProductBO productBO) {
		this.productBO = productBO;
	}

	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}

	public String getProductCode() {
		return productCode;
	}	

	public BigDecimal getTotalTaxAmount() {
		return totalTaxAmount;
	}

	public void setTotalTaxAmount(BigDecimal totalTaxAmount) {
		this.totalTaxAmount = totalTaxAmount;
	}	
	

	public boolean isValidity() {
		return validity;
	}

	public void setValidity(boolean validity) {
		this.validity = validity;
	}	

	public Integer getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Integer totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public BranchtransferModel getBranchtransfer() {
		return branchtransfer;
	}

	public void setBranchtransfer(BranchtransferModel branchtransfer) {
		this.branchtransfer = branchtransfer;
	}

	public List<BranchtransferbreakdownModel> getBranchtransferbreakdowns() {
		return branchtransferbreakdowns;
	}

	public void setBranchtransferbreakdowns(
			List<BranchtransferbreakdownModel> branchtransferbreakdowns) {
		this.branchtransferbreakdowns = branchtransferbreakdowns;
	}

	public LazyDataModel<BranchtransferModel> getBranchtransferModel() {
		return branchtransferModel;
	}

	public void setBranchtransferModel(
			LazyDataModel<BranchtransferModel> branchtransferModel) {
		this.branchtransferModel = branchtransferModel;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public List<String> getProductcodes() {
		return productcodes;
	}

	public void setProductcodes(List<String> productcodes) {
		this.productcodes = productcodes;
	}

	public BranchModel getBranch() {
		return branch;
	}

	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}

	public List<String> getCustomerNames() {
		return customerNames;
	}

	public void setCustomerNames(List<String> customerNames) {
		this.customerNames = customerNames;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public LazyDataModel<BranchtransferModel> getSalesList() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.setBranchtransferModel(null);
			branchtransferModel = new BranchtransferPagingHelper(null, branchId,dateFrom, dateTo, status, branchtransferBO,loginBean.getBranch().getBranchId(),this.getTransferType());
			this.setBranchtransferModel(branchtransferModel);
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"productPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
		return branchtransferModel;
	}

	
	public CommonFactoryBean getFactoryBean() {
		return factoryBean;
	}

	public void setFactoryBean(CommonFactoryBean factoryBean) {
		this.factoryBean = factoryBean;
	}

	public List<SelectItem> getSupplierQty() {
		return supplierQty;
	}

	public void setSupplierQty(List<SelectItem> supplierQty) {
		this.supplierQty = supplierQty;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public Configuration getConfig() {
		return config;
	}

	public void setConfig(Configuration config) {
		this.config = config;
	}

	public int getQuotationId() {
		return quotationId;
	}

	public void setQuotationId(int quotationId) {
		this.quotationId = quotationId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDeliveryTerms() {
		return deliveryTerms;
	}

	public void setDeliveryTerms(String deliveryTerms) {
		this.deliveryTerms = deliveryTerms;
	}

	public Date getLastModifedDate() {
		return lastModifedDate;
	}

	public void setLastModifedDate(Date lastModifedDate) {
		this.lastModifedDate = lastModifedDate;
	}

	public String getPaymentTerms() {
		return paymentTerms;
	}

	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	public Date getQuotationDate() {
		return quotationDate;
	}

	public void setQuotationDate(Date quotationDate) {
		this.quotationDate = quotationDate;
	}

	public Date getQuotationDueDate() {
		return quotationDueDate;
	}

	public void setQuotationDueDate(Date quotationDueDate) {
		this.quotationDueDate = quotationDueDate;
	}

	public String getQuotationNo() {
		return quotationNo;
	}

	public void setQuotationNo(String quotationNo) {
		this.quotationNo = quotationNo;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public boolean isProductCodedisable() {
		return productCodedisable;
	}

	public void setProductCodedisable(boolean productCodedisable) {
		this.productCodedisable = productCodedisable;
	}

	public void searchSales() {
		try {
			if(!this.validateSalesSearch())	
			{					
			Date dateNow = new Date ();				 
		    SimpleDateFormat dateformatDDMMYYYY = new SimpleDateFormat("dd/MM/yyyy");		 
		    String nowYYYYMMDD = new String( dateformatDDMMYYYY.format(dateNow));    
		    
		    if(this.getTransferType().equalsIgnoreCase("1"))
		    {
			this.setDateFrom(DateUtil.getFromTodayDateTime());
			this.setDateTo(DateUtil.getToTodayDateTime());
		    }
		    else
		    {
		    this.setStatus("2");	
		    }
			}					
			this.getSalesList();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void resetSalesSearch()
	{	
			this.setDateFrom(DateUtil.getFromTodayDateTime());
			this.setDateTo(DateUtil.getToTodayDateTime());			
			this.setStatus(null);
			this.setQuotationNo(null);
			this.getSalesList();
			this.setBranchId(0);
	}

	public void newSales() {
		BranchtransferBeanInfo branchsaleBeanInfo = new BranchtransferBeanInfo();
		branchsaleBeanInfo.newTransfer();
		this.resetSales();
	}
	
	
	public void selectProduct(SelectEvent event)
	{
		this.product=((ProductModel) event.getObject());			
	}
	
	public void selectCustomer(SelectEvent event)
	{
		//this.customer=((CustomerModel) event.getObject());			
	}
	

	public void addSalesItem() {
		BranchtransferbreakdownModel item = new BranchtransferbreakdownModel();
		ProductModel producttemp = new ProductModel();
		FacesContext context = FacesContext.getCurrentInstance();
		List<ProductpriceModel> productPriceList;
		boolean datamatch = false;
		int rowCount = 0;

		boolean exits = false;
		try {
			producttemp = productBO.getProductList(null, null, null, this.product.getBarcode(), null, 0, 1, loginBean.getBranch().getBranchId(), null,null,null,null).get(0);
			//productPriceList = productBO.getSortedProductpriceBarcode(producttemp.getProductId(),loginBean.getBranch().getBranchId());
		

			for (BranchtransferbreakdownModel data : branchtransferbreakdowns) {
				if (data.getProductCode().equalsIgnoreCase(
						this.product.getBarcode())) {
					BranchtransferbreakdownModel c = branchtransferbreakdowns.get(rowCount);

					item.setProductId(producttemp.getProductId());
					item.setProductName(producttemp.getProductName());
					item.setProductCode(this.product.getBarcode());
					item.setUomName(producttemp.getUom());
					item.setExpiryDate(producttemp.getExpiryDate());
					item.setBatchNo(producttemp.getBatchNo());					
					item.setQuantity(this.getItemCount()+c.getQuantity());
					item.setBalanceQuantity(this.getItemCount());
					
					
					item.setSno(sno);
					branchtransferbreakdowns.set(rowCount, item);	
					sno=sno+1;
					datamatch = true;
				}
				rowCount++;
			}
			if (!datamatch) {
				item.setProductId(producttemp.getProductId());
				item.setProductName(producttemp.getProductName());
				item.setProductCode(this.product.getBarcode());
				item.setUomName(producttemp.getUom());
				item.setExpiryDate(producttemp.getExpiryDate());
				item.setBatchNo(producttemp.getBatchNo());				
				item.setQuantity(this.getItemCount());
				item.setBalanceQuantity(this.getItemCount());
				
				item.setSno(sno);
				this.getDynamicSalesItemList(item);
				sno=sno+1;
			}			
			//extractQuoteTotal();
			//Collections.sort(branchtransferbreakdowns, new QuotationOrderSort());
			resetAddItem();
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"salesListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),null));
		}
	}

	public BigDecimal extractSubtotal(BigDecimal unitPrice,
		BigDecimal discAmount, int quant,BigDecimal taxCode) {
		BigDecimal quantityValue = new BigDecimal(quant);
		BigDecimal priceAmt = unitPrice.multiply(quantityValue);
		BigDecimal discAmt = discAmount.multiply(quantityValue);
		
		BigDecimal taxAmt = taxCode.multiply((priceAmt.divide(new BigDecimal(100))));
		priceAmt=priceAmt.add(taxAmt);
		
		BigDecimal tempTot = priceAmt.subtract(discAmt);
		return tempTot;
	}
	
	
	
	

	public void getDynamicSalesItemList(BranchtransferbreakdownModel item) {
		this.branchtransferbreakdowns.add(item);
	}

	public List<ProductModel> getProductCode(String productString) {
		List<ProductModel> results = new ArrayList<ProductModel>();
		CommonListBeanInfo commoninfo = new CommonListBeanInfo();
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			results = commoninfo.getAllProductList(productString);
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
		return results;
	}

	public List<CustomerModel> getCustomerName(String customerString) {
		List<CustomerModel> results = new ArrayList<CustomerModel>();
		CommonListBeanInfo commoninfo = new CommonListBeanInfo();
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			results = commoninfo.getAllCustomerList(customerString);
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
		return results;
	}

	public void resetSalesList() {

		this.setItemCount(1);
		this.branchtransferbreakdowns.clear();
		branchtransfer = new BranchtransferModel();
		this.setBranchtransfer(branchtransfer);
		this.setAction("submit");
		this.setItemaction("submit");
		this.setProductCodedisable(false);
	}

	public void saveSales() {
		BranchtransferBeanInfo branchsaleBeanInfo = new BranchtransferBeanInfo();
		branchsaleBeanInfo.createNewTransfer();
	}
	
	public void updateSales() throws Exception{
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			BranchtransferBeanInfo branchsaleBeanInfo = new BranchtransferBeanInfo();
			branchsaleBeanInfo.updateTransfer();
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
			}
	}
	
	public void resetSales(){
		
		this.setItemCount(1);
		this.branchtransferbreakdowns.clear();		
		branchtransfer = new BranchtransferModel();
		this.setBranchtransfer(branchtransfer);
		this.setAction("submit");
		this.setItemaction("submit");
		this.setProductCodedisable(false);
		//customer = new CustomerModel();
		product = new ProductModel();
		//this.setCustomer(customer);
		this.setProduct(product);
		this.setTotalAmount(new BigDecimal("0.00"));		
		this.setTotalDiscountAmount(new BigDecimal("0.00"));			
		this.setGrandTotalAmount(new BigDecimal("0.00"));	
		
	}
	
	public void resetAddItem()
	{
		this.setItemCount(1);
		this.product = new ProductModel();
	}


	public void listSales() {

		BranchtransferBeanInfo branchsaleBeanInfo = new BranchtransferBeanInfo();
		branchsaleBeanInfo.listTransfer();
	}

	public void removeItem(ActionEvent event) {
		String branchsalesId = "";
		branchsalesId = (String) event.getComponent().getAttributes().get("branchsalesId").toString();
		this.setRowId(Integer.parseInt(branchsalesId));
	}

	public void removeItemConfirm() {
		BranchtransferbreakdownModel c = branchtransferbreakdowns.get(this.getRowId());
		branchtransferbreakdowns.remove(c);
		//extractQuoteTotal();
	}

	public void viewSales(ActionEvent event) throws Exception {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			String branchsalesId = "";
			branchsalesId = (String) event.getComponent().getAttributes().get("branchsalesId").toString();
			BranchtransferBeanInfo branchsaleBeanInfo = new BranchtransferBeanInfo();
			branchtransfer = branchsaleBeanInfo.editTransfer(Integer.parseInt(branchsalesId));
			branchtransferbreakdowns = branchtransfer.getBranchtransferbreakdowns();
					
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
		}
	}

	public void editSales(ActionEvent event) throws Exception {

		FacesContext context = FacesContext.getCurrentInstance();
		try {
			String branchsalesId = "";
			branchsalesId = (String) event.getComponent().getAttributes().get("branchsalesId").toString();		
			BranchtransferBeanInfo branchsaleBeanInfo = new BranchtransferBeanInfo();
			branchtransfer = branchsaleBeanInfo.editTransfer(Integer.parseInt(branchsalesId));	
			branchtransferbreakdowns = branchtransfer.getBranchtransferbreakdowns();					
			this.setBranch(branchtransfer.getBranch());		
			this.setBranchId(branchtransfer.getBranch().getBranchId());
			branchsaleBeanInfo.newTransfer();
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
			}
	}
	
	public void loadSales()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try {			
			BranchtransferBeanInfo branchsaleBeanInfo = new BranchtransferBeanInfo();
			//branchtransfer = quotationBeanInfo.editQuotation(quotation.getQuotationId());	
			//branchtransferbreakdowns = branchtransfer.getQuotationbreakdowns();		
			//this.setTotalAmount(branchtransfer.getTotalAmount());			
			CustomerModel customer = new CustomerModel();			
			//customer.setCustomerId(quotation.getCustomerId());
			//customer.setCustomerName(quotation.getCustomerName());
			//this.setCustomer(customer);
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}	

	
	
	
	public void approveSales(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	try {
		String branchsalesId = "";
		branchsalesId = (String) event.getComponent().getAttributes().get("branchsalesId").toString();
		processaction = (String) event.getComponent().getAttributes().get("processaction").toString();
		BranchtransferBeanInfo branchsaleBeanInfo = new BranchtransferBeanInfo();
		branchtransfer = branchsaleBeanInfo.editTransfer(Integer.parseInt(branchsalesId));
		branchtransferbreakdowns = branchtransfer.getBranchtransferbreakdowns();
		this.setProcessaction(processaction);
		
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesListPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
	}
	}
	
	
	public void approveSalesConfirm()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		boolean updateSuccess=false;
		try {						
			
			if(this.getProcessaction().equalsIgnoreCase("approve"))
			{
				branchtransfer.setStatus(config.getValue(IConfiguration.TRANSFER_STATUS_PROCESSED_VALUE));			
				updateSuccess=branchtransferBO.approveBranchtransfer(branchtransfer);
			}
			if(this.getProcessaction().equalsIgnoreCase("accept"))
			{
				branchtransfer.setStatus(config.getValue(IConfiguration.TRANSFER_STATUS_ACCEPT_VALUE));			
				updateSuccess=branchtransferBO.approveBranchtransfer(branchtransfer);
			}
			if(this.getProcessaction().equalsIgnoreCase("reject"))
			{
				branchtransfer.setStatus(config.getValue(IConfiguration.TRANSFER_STATUS_REJECT_LABLE));			
				updateSuccess=branchtransferBO.approveBranchtransfer(branchtransfer);
			}
			
			
			if (updateSuccess) {					
				this.resetSales();
				if(this.getProcessaction().equalsIgnoreCase("approve"))
				{
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesListPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("transfer.label.approved.success"),null));		
				}
				if(this.getProcessaction().equalsIgnoreCase("accept"))
				{
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesListPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("transfer.label.accept.success"),null));		
				}
				if(this.getProcessaction().equalsIgnoreCase("reject"))
				{
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesListPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("transfer.label.reject.success"),null));		
				}
			}
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"salesListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}	
	
	
	
	public boolean validateSalesSearch() {
		boolean valid = true;		
	
		
		
		if(this.getStatus()!=null)
		{
			if(this.getStatus().equalsIgnoreCase("") || this.getStatus().equalsIgnoreCase("0") )
			{
				this.setStatus(null);
			}
		}		
		
		if(this.getBranchId()==0 && this.getDateFrom()==null && this.getDateTo()==null && this.getStatus()==null  && this.getQuotationNo()==null)
		{	
			valid = false;
		}		
		
		else
		{
			valid = true;
		}			
		return valid;

	}
	
	
	public void transferProductConfirm()
	{
		
		if(this.transferItemvalidate())
		{
			
		}
	}
	
	public boolean transferItemvalidate()
	{
		FacesContext context = FacesContext.getCurrentInstance();	
		boolean valid=true;
		RequestContext reqcontext = RequestContext.getCurrentInstance(); 
		try {
			if(totalQuantity==null)
			{
				valid=false;
				this.setValidity(false);
			}
			if(totalQuantity!=null)
			{
				if(totalQuantity==0)
				{
					valid=false;
					this.setValidity(false);
					context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"salesListPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Enter  Quantity",null));
				}				
			}			
			if(valid)
			{
				valid=true;
				this.setValidity(true);
			}			
			reqcontext.addCallbackParam("validity", this.isValidity());
		}	
			
		catch (Exception e) 
	 	{			 		
	 		context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"salesListPanel").getClientId(context),
	 		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),null));
	 		
	 	}	 
		return valid;
	}
	
	
	public void printSales(ActionEvent event) {
		
		FacesContext faces = FacesContext.getCurrentInstance();	
		try{				
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 			
		String quotationId = "";
		quotationId = (String) event.getComponent().getAttributes().get("quotationId").toString();
		faces.getExternalContext().redirect(request.getScheme() + "://" + request.getServerName() +":" + request.getServerPort() +"/"+config.getValue(IConfiguration.PROJECT_INSTANCE_NAME)+"/frameset?__report=/report/sales/quotationDetailReport.rptdesign&__format=pdf&&__pageoverflow=0&__asattachment=true&__overwrite=false&quotationId=" + quotationId+"&userId="+loginBean.getUserId() );
		faces.responseComplete();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	
	public void validateQuantity(Integer rowId)
	{
		FacesContext context = FacesContext.getCurrentInstance();	
		Integer balanceQuantity = 0;			
		BranchtransferbreakdownModel c = branchtransferbreakdowns.get(rowId);
		Integer receivableQuantity=0;
		try {
			if (ValidatorUtil.isNotNull(this.getReceivalbeQuantity().get(c.getId()))) 
			{
				receivableQuantity = Integer.parseInt((""+this.getReceivalbeQuantity().get(c.getId())));
			}	
				if(c.getBalanceQuantity()<receivableQuantity)
				{
					context.addMessage(UIComponent.findComponent(context.getViewRoot(),"transferdataTable1").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please Check the Transfer Quantity should be less than Balance Quantity", null));
					this.receivalbeQuantity.put(c.getId(),0);
				}
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"transferdataTable1").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}
	
	
	
}
