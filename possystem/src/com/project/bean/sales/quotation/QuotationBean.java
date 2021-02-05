package com.project.bean.sales.quotation;

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

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import com.project.bo.interfaces.IProductBO;
import com.project.bo.interfaces.IQuotationBO;
import com.project.model.datamodel.CustomerModel;
import com.project.model.datamodel.ProductModel;
import com.project.model.datamodel.ProductpriceModel;
import com.project.model.datamodel.purchase.PurchaserequestModel;
import com.project.model.datamodel.purchase.PurchaserequestbreakdownModel;

import com.project.model.sale.sales.QuotationModel;
import com.project.model.sale.sales.QuotationbreakdownModel;
import com.project.model.sale.sales.branchsale.BranchsalesbreakdownModel;
import com.project.util.DateUtil;
import com.project.bean.admin.CommonListBeanInfo;
import com.project.bean.purchase.PurchaseRequestBeanInfo;
import com.project.common.config.Configuration;
import com.project.common.config.IConfiguration;
import com.project.common.factory.BeanContext;
import com.project.common.util.DecimalUtil;
import com.project.common.util.ObjectMapController;
import com.project.common.validation.CommonFactoryBean;
import com.project.common.validation.UIComponent;
import com.project.common.validation.ValidatorUtil;
import com.project.login.LoginBean;
import com.project.model.paginghelper.PosSalesSorder;
import com.project.model.paginghelper.QuotationOrderSort;
import com.project.model.paginghelper.QuotationPagingHelper;

@ManagedBean(name = "quotationBean")
@SessionScoped
public class QuotationBean {

	QuotationModel quotation = new QuotationModel();
	List<QuotationbreakdownModel> quotationbreakdowns = new ArrayList<QuotationbreakdownModel>();
	private LazyDataModel<QuotationModel> quotationModel = null;
	CommonFactoryBean factoryBean = (CommonFactoryBean) BeanContext.getReference("commonFactoryBean");
	List<SelectItem> supplierQty = new ArrayList<SelectItem>();
	LoginBean loginBean = (LoginBean) BeanContext.getReference("loginBean");
	Configuration config = Configuration.getConfiguration();
	ObjectMapController objectMapController = (ObjectMapController) BeanContext.getReference("objectMapController");
	private Map<Integer, BigDecimal> priceQty = new HashMap<Integer, BigDecimal>();
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
	private BigDecimal itemCount= new BigDecimal(1.00);
	private String remarks;
	private String status;
	private BigDecimal totalAmount;
	private BigDecimal totalDiscountAmount;
	private BigDecimal grandTotalAmount;
	private BigDecimal totalTaxAmount = new BigDecimal(0.00);
	private int customerId;
	private String customerName;
	private Date dateFrom;
	private Date dateTo;
	private String action = "submit";
	private String itemaction = "submit";
	private Integer rowId;
	private ProductModel product;
	private boolean productCodedisable = false;
	List<String> productcodes = new ArrayList<String>();
	private CustomerModel customer;
	private boolean customerNamedisable = false;
	List<String> customerNames = new ArrayList<String>();
	int sno=0;
	
	IQuotationBO quotationBO=objectMapController.getQuotationBO();										
	IProductBO productBO=objectMapController.getProductBO();
	
	public IQuotationBO getQuotationBO() {
		return quotationBO;
	}

	public void setQuotationBO(IQuotationBO quotationBO) {
		this.quotationBO = quotationBO;
	}

	public QuotationModel getQuotation() {
		return quotation;
	}

	public void setQuotation(QuotationModel quotation) {
		this.quotation = quotation;
	}

	
	public Map<Integer, BigDecimal> getPriceQty() {
		return priceQty;
	}

	public void setPriceQty(Map<Integer, BigDecimal> priceQty) {
		this.priceQty = priceQty;
	}

	public LazyDataModel<QuotationModel> getQuotationModel() {
		return quotationModel;
	}

	public void setQuotationModel(LazyDataModel<QuotationModel> quotationModel) {
		this.quotationModel = quotationModel;
	}

	public CustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

	public boolean isCustomerNamedisable() {
		return customerNamedisable;
	}

	public void setCustomerNamedisable(boolean customerNamedisable) {
		this.customerNamedisable = customerNamedisable;
	}

	public String getCustomerName() {
		return customerName;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
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

	public LazyDataModel<QuotationModel> getquotationList() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.setQuotationModel(null);
			quotationModel = new QuotationPagingHelper(quotationNo, customerId,	dateFrom, dateTo, status, quotationBO,loginBean.getBranch().getBranchId());
			this.setQuotationModel(quotationModel);
			// this.checkMap.clear();
		} catch (Exception e) {
			context.addMessage(
					UIComponent.findComponent(context.getViewRoot(),
							"productPanel").getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), null));
		}
		return quotationModel;
	}

	public List<QuotationbreakdownModel> getQuotationbreakdowns() {
		return quotationbreakdowns;
	}

	public void setQuotationbreakdowns(
			List<QuotationbreakdownModel> quotationbreakdowns) {
		this.quotationbreakdowns = quotationbreakdowns;
	}

	public List<QuotationbreakdownModel> getQuotationbreakdownModel() {
		return quotationbreakdowns;
	}

	public void setQuotationbreakdownModel(
			List<QuotationbreakdownModel> quotationbreakdownModel) {
		this.quotationbreakdowns = quotationbreakdownModel;
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

	public BigDecimal getItemCount() {
		return itemCount;
	}

	public void setItemCount(BigDecimal itemCount) {
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

	public void searchQuotation() {
		try {
			if(!this.validateQuotationSearch())	
			{					
			Date dateNow = new Date ();				 
		    SimpleDateFormat dateformatDDMMYYYY = new SimpleDateFormat("dd/MM/yyyy");		 
		    String nowYYYYMMDD = new String( dateformatDDMMYYYY.format(dateNow));    
			this.setDateFrom(DateUtil.getFromTodayDateTime());
			this.setDateTo(DateUtil.getToTodayDateTime());
			}					
			this.getquotationList();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void resetQuotationSearch()
	{	
			this.setDateFrom(DateUtil.getFromTodayDateTime());
			this.setDateTo(DateUtil.getToTodayDateTime());			
			this.setStatus(null);
			this.setQuotationNo(null);
			this.getquotationList();
			this.setCustomerId(0);
	}

	public void newQuotation() {
		QuotationBeanInfo quotationBeanInfo = new QuotationBeanInfo();
		quotationBeanInfo.newQuotation();
		this.resetQuotation();
	}
	
	
	public void selectProduct(SelectEvent event)
	{
		this.product=((ProductModel) event.getObject());			
	}
	
	public void selectCustomer(SelectEvent event)
	{
		this.customer=((CustomerModel) event.getObject());			
	}
	

	public void addQuotationItem() {
		QuotationbreakdownModel item = new QuotationbreakdownModel();
		ProductModel producttemp = new ProductModel();
		FacesContext context = FacesContext.getCurrentInstance();
		List<ProductpriceModel> productPriceList;
		boolean datamatch = false;
		int rowCount = 0;

		boolean exits = false;
		try {
			producttemp = productBO.getProductList(null, null, null, this.product.getBarcode(), null, 0, 1, loginBean.getBranch().getBranchId(), null,null,null,null).get(0);
			productPriceList = productBO.getSortedProductpriceBarcode(producttemp.getProductId(),loginBean.getBranch().getBranchId());
			
			if(productPriceList!=null && productPriceList.size()==1)
			{
				productPriceList.add(productPriceList.get(0));
			}

			for (QuotationbreakdownModel data : quotationbreakdowns) {
				if (data.getProductCode().equalsIgnoreCase(
						this.product.getBarcode())) {
					QuotationbreakdownModel c = quotationbreakdowns.get(rowCount);

					item.setProductId(producttemp.getProductId());
					item.setProductName(producttemp.getProductName());
					item.setProductCode(this.product.getBarcode());
					item.setUomName(producttemp.getUom());
					item.setExpiryDate(producttemp.getExpiryDate());
					item.setUnitPrice(producttemp.getNormalPrice());
					this.priceQty.put(producttemp.getProductId(),producttemp.getNormalPrice());
					item.setDiscount(""+producttemp.getDiscount());
					item.setQuantityRequired(this.getItemCount().add(c.getQuantityRequired()));
					item.setBalanceQuantity(this.getItemCount());
					
					item.setTaxCode(producttemp.getTaxCode());					
					BigDecimal priceAmt = item.getUnitPrice().multiply(item.getQuantityRequired());
					BigDecimal taxAmt= producttemp.getTaxCode().multiply((priceAmt.divide(new BigDecimal(100))));
					item.setTaxAmount(taxAmt);
					
					item.setSno(sno);
					
					for (int i = 0; i < productPriceList.size(); i++) {
						if (!exits) {
							for (int j = i + 1; j < productPriceList.size(); j++) {
								BigDecimal initVal = productPriceList.get(i).getQuantityFrom();
								BigDecimal nextVal = productPriceList.get(j).getQuantityFrom();
								if (item.getQuantityRequired().doubleValue() >= initVal.doubleValue()	&& item.getQuantityRequired().doubleValue() < nextVal.doubleValue()) {
									item.setDiscountAmount(productPriceList.get(i).getDiscountAmount());
									exits = true;
									break;
								} else {
									item.setDiscountAmount(productPriceList.get(j).getDiscountAmount());
									i++;
								}
							}
							BigDecimal tmpSubTotal = extractSubtotal(item.getUnitPrice(),item.getDiscountAmount(),item.getQuantityRequired(),producttemp.getTaxCode());
							item.setSubTotal(tmpSubTotal);
							//this.getDynamicQuotationItemList(item);
							quotationbreakdowns.set(rowCount, item);	
							sno=sno+1;
						}
					}
					//quotationbreakdowns.remove(c);
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
				item.setUnitPrice(producttemp.getNormalPrice());
				this.priceQty.put(producttemp.getProductId(),producttemp.getNormalPrice());
				item.setDiscount(""+producttemp.getDiscount());
				item.setQuantityRequired(this.getItemCount());
				item.setBalanceQuantity(this.getItemCount());
				
				item.setTaxCode(producttemp.getTaxCode());				
				BigDecimal priceAmt = item.getUnitPrice().multiply(item.getQuantityRequired());
				BigDecimal taxAmt= producttemp.getTaxCode().multiply((priceAmt.divide(new BigDecimal(100))));
				item.setTaxAmount(taxAmt);				
				
				item.setSno(sno);
				
				for (int i = 0; i < productPriceList.size(); i++) {
					if (!exits) {
						for (int j = i + 1; j < productPriceList.size(); j++) {
							BigDecimal initVal = productPriceList.get(i).getQuantityFrom();
							BigDecimal nextVal = productPriceList.get(j).getQuantityFrom();
							if (item.getQuantityRequired().doubleValue() >= initVal.doubleValue() && item.getQuantityRequired().doubleValue() < nextVal.doubleValue()) {
								item.setDiscountAmount(productPriceList.get(i).getDiscountAmount());
								exits = true;
								break;
							} else {
								item.setDiscountAmount(productPriceList.get(j).getDiscountAmount());
								i++;
							}
						}
						BigDecimal tmpSubTotal = extractSubtotal(item.getUnitPrice(), item.getDiscountAmount(),item.getQuantityRequired(),producttemp.getTaxCode());
						item.setSubTotal(tmpSubTotal);
						this.getDynamicQuotationItemList(item);
						sno=sno+1;
					}
				}
			}
			
			extractQuoteTotal();
			Collections.sort(quotationbreakdowns, new QuotationOrderSort());
			resetAddItem();
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"quotationListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),null));
		}
	}
	
	
	
	
	
	public void addSalesPriceModifyItem(Integer rowId) {
		QuotationbreakdownModel item = new QuotationbreakdownModel();
		ProductModel producttemp = new ProductModel();
		FacesContext context = FacesContext.getCurrentInstance();
		List<ProductpriceModel> productPriceList;
		boolean datamatch = false;
		int rowCount = 0;
		
		boolean exits = false;
		try {
			
			item = quotationbreakdowns.get(rowId);					
			List<ProductModel> productList= new ArrayList<ProductModel>();
			productList = productBO.getProductList(null, null, null, item.getProductCode(), null, 0, 1, loginBean.getBranch().getBranchId(), null,null,null,null);
			
			if(productList.size()==0)
			{
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"posPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_ERROR, null,"Barcode not Exists"));
			}
			else
			{
			producttemp=productList.get(0);
			productPriceList = productBO.getSortedProductpriceBarcode(producttemp.getProductId(),loginBean.getBranch().getBranchId());
						
			if (ValidatorUtil.isNotNull(this.getPriceQty().get(item.getProductId()))) 
			{			
				item.setUnitPrice(this.getPriceQty().get(item.getProductId()));
			}
			
			if(productPriceList!=null && productPriceList.size()==1)
			{
				productPriceList.add(productPriceList.get(0));
			}					
					
					BigDecimal priceAmt = item.getUnitPrice().multiply(item.getQuantityRequired());
					BigDecimal taxAmt= producttemp.getTaxCode().multiply((priceAmt.divide(new BigDecimal(100))));
					item.setTaxAmount(taxAmt);					
					
					for (int i = 0; i < productPriceList.size(); i++) {
						if (!exits) {
							for (int j = i + 1; j < productPriceList.size(); j++) {
								BigDecimal initVal = productPriceList.get(i).getQuantityFrom();
								BigDecimal nextVal = productPriceList.get(j).getQuantityFrom();
								if (item.getQuantityRequired().doubleValue() >= initVal.doubleValue()	&& item.getQuantityRequired().doubleValue() < nextVal.doubleValue()) {
									item.setDiscountAmount(productPriceList.get(i).getDiscountAmount());
									exits = true;
									break;
								} else {
									item.setDiscountAmount(productPriceList.get(j).getDiscountAmount());
									i++;
								}
							}
							BigDecimal tmpSubTotal = extractSubtotal(item.getUnitPrice(),item.getDiscountAmount(),item.getQuantityRequired(),producttemp.getTaxCode());
							item.setSubTotal(tmpSubTotal);
							quotationbreakdowns.set(rowId, item);								
						}
					}										
			
			extractQuoteTotal();
			//Collections.sort(branchsalesbreakdowns, new PosSalesSorder());
			resetAddItem();
			}

		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"quotationListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),null));
		}
	}
	
	
	
	
	

	public BigDecimal extractSubtotal(BigDecimal unitPrice,
		BigDecimal discAmount, BigDecimal quant,BigDecimal taxCode) {
		BigDecimal quantityValue = quant;
		BigDecimal priceAmt = unitPrice.multiply(quantityValue);
		BigDecimal discAmt = discAmount.multiply(quantityValue);
		
		BigDecimal taxAmt = taxCode.multiply((priceAmt.divide(new BigDecimal(100))));
		priceAmt=priceAmt.add(taxAmt);
		
		BigDecimal tempTot = priceAmt.subtract(discAmt);
		return tempTot;
	}
	
	public void extractQuoteTotal() {
		FacesContext context = FacesContext.getCurrentInstance();
		try
		{
		    BigDecimal totalAmount =new BigDecimal(0.00);
		    BigDecimal totalDiscountAmount =new BigDecimal(0.00);
		    BigDecimal grandTotalAmount =new BigDecimal(0.00);
		    BigDecimal totalTaxAmount =new BigDecimal(0.00);
		    
			for(QuotationbreakdownModel item:quotationbreakdowns)
			{
				totalAmount=totalAmount.add(item.getSubTotal());
				totalDiscountAmount=totalDiscountAmount.add(item.getDiscountAmount());
				totalTaxAmount=totalTaxAmount.add(item.getTaxAmount());
			}
			this.setTotalAmount(DecimalUtil.formatRoundupCents(totalAmount));
			this.setTotalDiscountAmount(totalDiscountAmount);	
			this.setTotalTaxAmount(totalTaxAmount);			
			//grandTotalAmount=(totalAmount.add(totalTaxAmount)).subtract(totalDiscountAmount);
			grandTotalAmount=(totalAmount.add(totalTaxAmount));
			this.setGrandTotalAmount(grandTotalAmount);	
		}
		catch(Exception e)
			{
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),	"quotationListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(),null));
			}
		
		}
		
	
	

	public void getDynamicQuotationItemList(QuotationbreakdownModel item) {
		this.quotationbreakdowns.add(item);
	}

	public List<ProductModel> getProductCode(String productString) {
		List<ProductModel> results = new ArrayList<ProductModel>();
		CommonListBeanInfo commoninfo = new CommonListBeanInfo();
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			results = commoninfo.getAllProductList(productString);
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),
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
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
		return results;
	}

	public void resetQuotationList() {

		this.setItemCount(new BigDecimal(1));
		this.quotationbreakdowns.clear();
		quotation = new QuotationModel();
		this.setQuotation(quotation);
		this.setAction("submit");
		this.setItemaction("submit");
		this.setProductCodedisable(false);
	}

	public void saveQuotation() {
		QuotationBeanInfo quotationBeanInfo = new QuotationBeanInfo();
		quotationBeanInfo.createNewQuotation();
	}
	
	public void updateQuotation() throws Exception{
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			QuotationBeanInfo  quotationBeanInfo = new QuotationBeanInfo();
			quotationBeanInfo.updateQuotation();
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
			}
	}
	
	public void resetQuotation(){
		
		this.setItemCount(new BigDecimal(1.00));
		this.quotationbreakdowns.clear();		
		quotation = new QuotationModel();
		this.setQuotation(quotation);
		this.setAction("submit");
		this.setItemaction("submit");
		this.setProductCodedisable(false);
		customer = new CustomerModel();
		product = new ProductModel();
		this.setCustomer(customer);
		this.setProduct(product);
		this.setTotalAmount(new BigDecimal("0.00"));		
		this.setTotalDiscountAmount(new BigDecimal("0.00"));			
		this.setGrandTotalAmount(new BigDecimal("0.00"));	
		this.priceQty.clear();
	}
	
	public void resetAddItem()
	{
		this.setItemCount(new BigDecimal(1.00));
		this.product = new ProductModel();
	}


	public void listQuotation() {

		QuotationBeanInfo quotationBeanInfo = new QuotationBeanInfo();
		quotationBeanInfo.listQuotation();
	}

	public void removeItem(ActionEvent event) {
		String quotationBreakdownId = "";
		quotationBreakdownId = (String) event.getComponent().getAttributes().get("quotationBreakdownId").toString();
		this.setRowId(Integer.parseInt(quotationBreakdownId));
	}

	public void removeItemConfirm() {
		QuotationbreakdownModel c = quotationbreakdowns.get(this.getRowId());
		quotationbreakdowns.remove(c);
		extractQuoteTotal();
	}

	public void viewQuotation(ActionEvent event) throws Exception {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			String quotationId = "";
			quotationId = (String) event.getComponent().getAttributes().get("quotationId").toString();
			QuotationBeanInfo quotationBeanInfo = new QuotationBeanInfo();
			quotation = quotationBeanInfo.editQuotation(Integer.parseInt(quotationId));
			quotationbreakdowns = quotation.getQuotationbreakdowns();
			this.setTotalAmount(quotation.getTotalAmount());			
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
		}
	}

	public void editQuotation(ActionEvent event) throws Exception {

		FacesContext context = FacesContext.getCurrentInstance();
		try {
			String quotationId = "";
			quotationId = (String) event.getComponent().getAttributes().get("quotationId").toString();		
			QuotationBeanInfo quotationBeanInfo = new QuotationBeanInfo();
			quotation = quotationBeanInfo.editQuotation(Integer.parseInt(quotationId));	
			quotationbreakdowns = quotation.getQuotationbreakdowns();
			this.setSno(quotation.getSno());
			this.setTotalAmount(quotation.getTotalAmount());
			
			CustomerModel customer = new CustomerModel();			
			customer.setCustomerId(quotation.getCustomerId());
			customer.setCustomerName(quotation.getCustomerName());
			this.setCustomer(customer);
			
			this.priceQty.clear();
			for(QuotationbreakdownModel item:quotationbreakdowns)
			{
				this.priceQty.put(item.getProductId(), item.getUnitPrice());
			}
			
			
			quotationBeanInfo.newQuotation();
			extractQuoteTotal();
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			throw e;
			}
	}
	
	public void loadQuotation()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try {			
			QuotationBeanInfo quotationBeanInfo = new QuotationBeanInfo();
			quotation = quotationBeanInfo.editQuotation(quotation.getQuotationId());	
			quotationbreakdowns = quotation.getQuotationbreakdowns();		
			this.setTotalAmount(quotation.getTotalAmount());			
			CustomerModel customer = new CustomerModel();			
			customer.setCustomerId(quotation.getCustomerId());
			customer.setCustomerName(quotation.getCustomerName());
			this.setCustomer(customer);
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}	

	
	
	
	public void approveQuotation(ActionEvent event) throws Exception 
	{
	FacesContext context = FacesContext.getCurrentInstance();
	try {
		String quotationId = "";
		quotationId = (String) event.getComponent().getAttributes().get("quotationId").toString();
		QuotationBeanInfo quotationBeanInfo = new QuotationBeanInfo();
		quotation = quotationBeanInfo.editQuotation(Integer.parseInt(quotationId));
		quotationbreakdowns = quotation.getQuotationbreakdowns();
		this.setTotalAmount(quotation.getTotalAmount());			
		
	} catch (Exception e) {
		context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),
		new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		throw e;
	}
	}
	
	
	public void approveQuotationConfirm()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		boolean updateSuccess=false;
		try {						
			quotation.setStatus(config.getValue(IConfiguration.QUOTATION_STATUS_PROCESSED_VALUE));			
			updateSuccess=quotationBO.approveQuotation(quotation);
			
			if (updateSuccess) {					
				this.resetQuotation();
				context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),
				new FacesMessage(FacesMessage.SEVERITY_INFO,factoryBean.getMessageFactory().getMessage("quotation.label.approved.success"),null));				
			}
			
		} catch (Exception e) {
			context.addMessage(UIComponent.findComponent(context.getViewRoot(),"quotationListPanel").getClientId(context),
			new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));		
			}
	}	
	
	
	
	public boolean validateQuotationSearch() {
		boolean valid = true;		
	
		if(this.getQuotationNo()!=null)
		{
			if(this.getQuotationNo().equalsIgnoreCase(""))
			{
				this.setQuotationNo(null);
			}
		}		
		
		
		if(this.getStatus()!=null)
		{
			if(this.getStatus().equalsIgnoreCase("") || this.getStatus().equalsIgnoreCase("0") )
			{
				this.setStatus(null);
			}
		}		
		
		if(this.getDateFrom()==null && this.getDateTo()==null && this.getStatus()==null  && this.getQuotationNo()==null)
		{	
			valid = false;
		}		
		
		else
		{
			valid = true;
		}			
		return valid;

	}
	
	public void printQuotation(ActionEvent event) {
		
		FacesContext faces = FacesContext.getCurrentInstance();	
		try{				
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 			
		String quotationId = "";
		quotationId = (String) event.getComponent().getAttributes().get("quotationId").toString();
		faces.getExternalContext().redirect(request.getScheme() + "://" + request.getServerName() +":" + request.getServerPort() +"/"+config.getValue(IConfiguration.PROJECT_INSTANCE_NAME)+"/frameset?__report=report/sales/quotationDetailReport.rptdesign&__format=pdf&&__pageoverflow=0&__asattachment=true&__overwrite=false&quotationId=" + quotationId+"&userId="+loginBean.getUserId() );
		faces.responseComplete();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	
}
